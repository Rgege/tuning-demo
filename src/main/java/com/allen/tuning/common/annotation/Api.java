package com.allen.tuning.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author rui.xiong
 * @date 2020-07-08 14:53
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Api {
    /**
     * api name
     */
    String apiName();

    /**
     * 业务参数是不是必须
     */
    boolean paramNotNull() default false;
}
