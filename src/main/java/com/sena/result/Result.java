package com.sena.result;

import java.io.Serializable;

public class Result<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    public Result()
    {

    }

    public Result(int code, String message, T data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public static <T> Result<T> result(String message, T data)
    {
        return new Result<T>(0, message, data);
    }

    public static <T> Result<T> result(int code, String message)
    {
        return new Result<T>(code, message, null);
    }

    public static <T> Result<T> result(T data)
    {
        return new Result<T>(0, null, data);
    }

}
