package com.shouye.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private int errorNum;
    private String errorMsg;
}
