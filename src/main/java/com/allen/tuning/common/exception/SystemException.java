package com.allen.tuning.common.exception;

/**
 *
 * @author rui.xiong
 * @date 2020-07-08 16:51
 */
public class SystemException extends Exception{

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
