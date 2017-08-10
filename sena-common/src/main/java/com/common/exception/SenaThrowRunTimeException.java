package com.common.exception;

/**
 * Created by Shaow on 2017/2/28.
 */
public class SenaThrowRunTimeException extends RuntimeException {

    public SenaThrowRunTimeException(String exMessage) {
        super(exMessage);
    }

    public SenaThrowRunTimeException(String exMessage, Throwable cause) {
        super(exMessage, cause);
    }
}
