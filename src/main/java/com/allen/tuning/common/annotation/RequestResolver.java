package com.allen.tuning.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 *
 * @author rui.xiong
 * @date 2020-07-08 19:07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RequestResolver {

}
