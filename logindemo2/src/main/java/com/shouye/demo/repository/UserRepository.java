package com.shouye.demo.repository;

import com.shouye.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Mapper
@Repository
public interface UserRepository extends Serializable {

    @Insert("insert into usertest(username,password,email,lastLoginTime) values(#{user.username},#{user.password},#{user.email},#{user.lastLoginTime})")
    User InsertUser(@Param("user")User user);

    @Delete("delete from usertest where username=#{username}")
    int deleteUser(@Param("username")String username);

    @Select("select * from usertest where username=#{username}")
    User SelectUser(@Param("username")String username);

    @Update({
            "<script>",
            "update usertest set",
            "<if test='username!=null'>", " username = #{username}, ", "</if>",
            "<if test='password!=null'>", " password = #{password}, ", "</if>",
            "<if test='email!=null'>", " email = #{email}, ", "</if>",
            "<if test='lastLoginTime!=null'>", " lastLoginTime = #{lastLoginTime}, ", "</if>",
            "id=id where id = #{id}",
            "</script>"
    })
    int updateUserbysingle(@Param("username")String username, @Param("password")String password, @Param("email")String email, @Param("lastLoginTime") Date lastLoginTime, @Param("id")int id);

    @Update({
            "<script>",
            "update usertest set",
            "<if test='user.username!=null'>", " username = #{user.username}, ", "</if>",
            "<if test='user.password!=null'>", " password = #{user.password}, ", "</if>",
            "<if test='user.email!=null'>", " email = #{user.email}, ", "</if>",
            "<if test='user.lastLoginTime!=null'>", " lastLoginTime = #{user.lastLoginTime}, ", "</if>",
            "id=id where id = #{user.id}",
            "</script>"
    })
    int updateUser(@Param("user")User user);

    @Update("update usertest set lastLoginTime=#{lastLoginTime} where username=#{username}")
    int updateLastLoginTimeByUserName(@Param("lastLoginTime")Date lastLoginTime,@Param("username")String username);


}
