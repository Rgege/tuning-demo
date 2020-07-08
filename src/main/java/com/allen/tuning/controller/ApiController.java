package com.allen.tuning.controller;

import com.allen.tuning.api.AbstractApi;
import com.allen.tuning.api.factory.ApiFactory;
import com.allen.tuning.common.annotation.RequestResolver;
import com.allen.tuning.constant.ApiResponseCode;
import com.allen.tuning.entity.req.ApiRequest;
import com.allen.tuning.entity.rsp.ApiResponse;
import com.allen.tuning.entity.rsp.DemoData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rui.xiong
 * @date 2020-07-07 17:34
 */
@Controller
@RequestMapping("/api/")
public class ApiController {

    @RequestMapping("execute")
    @ResponseBody
    @RequestResolver
    public ApiResponse execute(ApiRequest param, HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isBlank(param.getApiName())){
            return ApiResponse.paramMissResponse("apiName");
        }
        AbstractApi api= ApiFactory.getApiByName(param.getApiName());
        if (null==api){
            return ApiResponse.failedResponse(ApiResponseCode.API_UNDEFINED);
        }
        return ApiResponse.commonSuccessResponse(new DemoData());
    }
}
