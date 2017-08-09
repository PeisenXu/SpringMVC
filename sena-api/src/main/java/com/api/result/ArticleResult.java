package com.api.result;

import java.io.Serializable;

public class ArticleResult<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public ArticleResult()
    {

    }

    public ArticleResult(int code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
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

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public static <T> ArticleResult<T> result(String msg, T data)
    {
        return new ArticleResult<T>(0, msg, data);
    }

    public static <T> ArticleResult<T> result(int code, String msg)
    {
        return new ArticleResult<T>(code, msg, null);
    }

    public static <T> ArticleResult<T> result(T data)
    {
        return new ArticleResult<T>(0, null, data);
    }

}
