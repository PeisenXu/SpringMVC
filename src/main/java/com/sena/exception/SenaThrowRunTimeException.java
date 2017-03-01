package com.sena.exception;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Shaow on 2017/2/28.
 */
public class SenaThrowRunTimeException extends RuntimeException{

    @ResponseBody
    public SenaThrowRunTimeException(String exMessage) {
        super(exMessage);
    }

    public SenaThrowRunTimeException(String exMessage, Throwable cause) {
        super(exMessage, cause);
    }
}
