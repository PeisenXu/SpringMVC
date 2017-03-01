package com.sena.message;

/**
 * 返回前端的错误码和错误信息 错误码为6位，成功为 0 100001-199999为系统错误消息 200001-299999为用户错误消息 200001-399999为业务错误消息
 *
 * @author 黎俊鹏
 * @version 1.0
 * @see
 */
public class MessageInfo {
    /**
     * 成功
     */
    public static final int SUCCESS = 0;

    /**
     * 失败
     */
    public static final int FAILTURE = -1;

    /**
     * 接口返回成功消息
     */

    /**
     * 登录成功
     */
    public static final String USER_LOGIN_SUCCESS = "登录成功";

    /*********************************************************
     * 系统错误码和消息 100001-199999
     ****************************/

    /*********************************************************
     * 用户错误码和消息 200001-299999
     ****************************/
    /**
     * 用户名为空
     */
    public static final int USER_LOGINNAME_IS_NULL_CODE = 200001;

    public static final String USER_LOGINNAME_IS_NULL_MESSAGE = "请输入登录名";

    /**
     * 请输入密码
     */
    public static final int USER_PASSWORD_IS_NULL_CODE = 200002;

    public static final String USER_PASSWORD_IS_NULL_MESSAGE = "请输入密码";

    /**
     * 用户名或密码错误
     */
    public static final int USER_LOGIN_ERROR_CODE = 200003;

    public static final String USER_LOGIN_ERROR_MESSAGE = "用户名或密码错误";

    /*********************************************************
     * 业务错误码和消息 300001-399999
     ****************************/
}
