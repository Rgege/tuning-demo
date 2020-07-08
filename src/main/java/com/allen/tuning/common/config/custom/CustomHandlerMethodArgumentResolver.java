package com.allen.tuning.common.config.custom;

import com.allen.tuning.common.annotation.RequestResolver;
import com.allen.tuning.common.util.JsonUtils;
import com.allen.tuning.entity.req.ApiRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rui.xiong
 * @date 2020-07-08 17:23
 */
@Component
public class CustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasMethodAnnotation(RequestResolver.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        System.out.println("=================");
        //获取HttpServletRequest
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        ApiRequest apiRequest=JsonUtils.toJavaObj(request.getInputStream(),ApiRequest.class);

        //获取HttpServletResponse
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        return apiRequest;
    }
}
