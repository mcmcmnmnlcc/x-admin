package com.jeeplus.common.myResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


public class Result<T> {
    @ApiModelProperty(value="返回标志",name="flag",example="true")
    private boolean flag;
    @ApiModelProperty(value="返回码",name="code",example="20000")
    private Integer code;
    @ApiModelProperty(value="返回信息",name="message",example="查询成功")
    private String message;
    @ApiModelProperty(value="返回的数据",name="data",example="")
    private T data;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;

        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
