package com.allen.tuning.constant;

/**
 * @author rui.xiong
 * @date 2020-07-07 17:59
 */
public enum ApiResponseCode {
    SUCCESS(0000000, "success"),


    FAILED(1000000, "failed"),
    API_UNDEFINED(1000001, "undefined api"),
    REQUIRED_PARAMETER_MISSING(1000002, "required parameter:%s missing"),
    ILLEGAL_PARAMETER(1000003, "illegal parameter：%s"),
    SYSTEM_ERROR(1000500, "The system is busy, please try again later"),
    BIZ_ERROR(1000004,"biz error")
    ;

    private Integer code;
    private String message;

    ApiResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
