package com.allen.tuning.api;

import com.allen.tuning.common.exception.BizException;
import com.allen.tuning.common.exception.JsonException;
import com.allen.tuning.common.exception.ParamException;
import com.allen.tuning.common.exception.SystemException;
import com.allen.tuning.common.util.JsonUtils;
import com.allen.tuning.constant.ApiResponseCode;
import com.allen.tuning.entity.rsp.AbstractData;
import com.allen.tuning.entity.rsp.ApiResponse;

/**
 * @author rui.xiong
 * @date 2020-07-08 14:55
 */
public abstract class AbstractApi<REQ, RSP extends AbstractData> {

    /**
     * @param paramObj
     * @return
     */
    public ApiResponse apiExct(Object paramObj) {
        if (isParamNotNull() && paramObj==null){
            return ApiResponse.failedMessageResponse("param can not be null");
        }

        ApiResponse response;
        try {
            REQ param = getParam(paramObj);
            paramVerify(param);
            response = ApiResponse.commonSuccessResponse(procBiz(param));
        } catch (ParamException e) {
            e.printStackTrace();
            response = ApiResponse.failedResponse(ApiResponseCode.ILLEGAL_PARAMETER.getCode(),
                    String.format(ApiResponseCode.ILLEGAL_PARAMETER.getMessage(), e.getMessage()));
        } catch (BizException e) {
            e.printStackTrace();
            response = ApiResponse.failedResponse(e.getCode(), e.getMessage());
        } catch (SystemException e) {
            e.printStackTrace();
            response = ApiResponse.failedResponse(ApiResponseCode.SYSTEM_ERROR);
        } catch (JsonException e) {
            e.printStackTrace();
            response = ApiResponse.failedMessageResponse("param serialize error");
        }


        return response;
    }

    /**
     * 参数校验
     *
     * @param param
     * @throws ParamException
     */
    protected abstract void paramVerify(REQ param) throws ParamException;

    /**
     * 业务处理
     *
     * @param param
     * @return
     * @throws BizException
     * @throws SystemException
     */
    protected abstract RSP procBiz(REQ param) throws BizException, SystemException;

    protected abstract Class<REQ> getReqClass();

    private REQ getParam(Object paramObj) throws JsonException {

        return JsonUtils.toJavaObj(paramObj,getReqClass());
    }

    private boolean paramNotNull;

    public boolean isParamNotNull() {
        return paramNotNull;
    }

    public void setParamNotNull(boolean paramNotNull) {
        this.paramNotNull = paramNotNull;
    }
}
