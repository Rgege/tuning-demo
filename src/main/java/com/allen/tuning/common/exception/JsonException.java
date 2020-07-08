package com.allen.tuning.common.exception;

/**
 *
 * @author rui.xiong
 * @date 2020-07-08 16:51
 */
public class JsonException extends Exception{

    public JsonException() {
        super();
    }

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
