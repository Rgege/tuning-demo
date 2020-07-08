package com.allen.tuning.entity.rsp;

import com.allen.tuning.constant.ApiResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author rui.xiong
 * @date 2020-07-07 17:35
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = -2401909365769839697L;

    /**
     * 执行结果
     */
    private boolean success;
    /**
     * 描述信息 当success=false时返回
     *
     * @see ApiResponseCode#getMessage()
     */
    private String message;
    /**
     * 状态码
     *
     * @see ApiResponseCode#getCode()
     */
    private Integer code;
    /**
     * 业务实体
     */
    private AbstractData data;

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public static ApiResponse commonSuccessResponse(AbstractData data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setCode(ApiResponseCode.SUCCESS.getCode());
        apiResponse.setData(new DemoData());
        return apiResponse;
    }

    /**
     * 失败返回
     *
     * @param responseCode
     * @return
     */
    public static ApiResponse failedResponse(ApiResponseCode responseCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setCode(responseCode.getCode());
        apiResponse.setMessage(responseCode.getMessage());
        return apiResponse;
    }

    /**
     * 失败返回 自定义code message
     *
     * @param code
     * @param message
     * @return
     */
    public static ApiResponse failedResponse(Integer code, String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        return apiResponse;
    }

    /**
     * 失败返回--自定义话语
     *
     * @param message
     * @return
     */
    public static ApiResponse failedMessageResponse(String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setCode(ApiResponseCode.FAILED.getCode());
        apiResponse.setMessage(message);
        return apiResponse;
    }

    public static ApiResponse paramMissResponse(String paramName) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(false);
        apiResponse.setCode(ApiResponseCode.REQUIRED_PARAMETER_MISSING.getCode());
        apiResponse.setMessage(String.format(ApiResponseCode.REQUIRED_PARAMETER_MISSING.getMessage(), paramName));
        return apiResponse;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AbstractData getData() {
        return data;
    }

    public void setData(AbstractData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
