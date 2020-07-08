package com.allen.tuning.common.exception;

/**
 *
 * @author rui.xiong
 * @date 2020-07-08 16:51
 */
public class ParamException extends Exception{

    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
