package com.shouye.demo.util;

import com.shouye.demo.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static UserRepository userRepository;

    @Autowired
    public JwtUtil(UserRepository userRepository){
        JwtUtil.userRepository=userRepository;
    }

    public static final long EXPIRATION_TIME=3600_000_000L; //过期时间:1000hour
    static final String SECRET="ThisIsASecret";
    static final String TOKEN_PREFIX="Bearer";
    static final String HEADER_STRING="Authorization";

    public static String generateToken(String username, Date generateTime){  //生成Token
        HashMap<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("generateTime",generateTime);
        String jwt= Jwts.builder().setClaims(map).setExpiration(new Date(generateTime.getTime()+EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512,SECRET).compact();
        return jwt;
    }


    public static Map<String,Object> validateToken(String token){         //验证Token
        Map<String,Object> resp=new HashMap<String, Object>();
        if(token!=null){   //解析token
            try{
                Map<String,Object> body=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
                String username=(String)(body.get("username"));
                Date generateTime=new Date((Long)body.get("generateTime"));
                if (username==null||username.isEmpty()){
                    resp.put("ERR_MSG",Constants.ERR_MSG_USERNAME_EMPTY);
                    return resp;
                }
                if (userRepository.SelectUser(username).getLastLoginTime().after(generateTime)){
                    resp.put("ERR_MSG",Constants.ERR_MSG_LOGIN_DOU);
                    return resp;
                }
                resp.put("username",username);
                resp.put("generateTime",generateTime);
                return resp;
            }catch (SignatureException | MalformedJwtException e){

                // TODO: handle exception
                // don't trust the JWT!
                // jwt 解析错误

                resp.put("ERR_MSG",Constants.ERR_MSG_TOKEN_ERR);
                return resp;
            }catch (ExpiredJwtException e){
                // TODO: handle exception
                // jwt 已经过期，在设置jwt的时候如果设置了过期时间，这里会自动判断jwt是否已经过期，如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
                resp.put("ERR_MSG",Constants.ERR_MSG_TOKEN_EXP);
                return resp;
            }
        }else {
            resp.put("ERR_MSG",Constants.ERR_MSG_TOKEN_EMPTY);
            return resp;
        }
    }
}
