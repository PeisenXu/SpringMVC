package com.api.message;

/**
 * 返回前端的错误码和错误信息 错误码为6位，成功为 0 100001-199999为系统错误消息 200001-299999为用户错误消息 200001-399999为业务错误消息
 *
 * @author 黎俊鹏
 * @version 1.0
 * @see
 */
public class MessageInfo {
    /*********************************************************
     * 系统错误码和消息 100001-199999
     ****************************/

    /**
     * MD5加密失败
     */
    public static final int SYSTEM_MD5_CREATE_ERRO = 100001;

    /**
     * 添加用户失败
     */
    public static final int SYSTEM_CREATE_USER_ERRO_ERRO = 100002;

    /**
     * 发送邮件失败
     */
    public static final int SYSTEM_SEND_EMAIL_ERRO = 100003;

    /**
     * INTERNAL_SERVER_ERROR
     */
    public static final int INTERNAL_SERVER_ERROR = 100004;

    /*********************************************************
     * 用户错误码和消息 200001-299999
     ****************************/
    /**
     * 用户名为空
     */
    public static final int USER_LOGINNAME_IS_NULL_CODE = 200001;

    /**
     * 请输入密码
     */
    public static final int USER_PASSWORD_IS_NULL_CODE = 200002;

    /**
     * 用户名或密码错误
     */
    public static final int USER_LOGIN_ERROR_CODE = 200003;

    /**
     * 请输入邮箱
     */
    public static final int USER_EMAIL_IS_NULL_CODE = 200004;

    /**
     * 参数缺失
     */
    public static final int USER_PARAM_IS_NULL_CODE = 200005;

    /**
     * 参数过大
     */
    public static final int USER_PARAM_IS_TOO_BIG = 200006;

    /**
     * 参数未找到
     */
    public static final int USER_PARAM_IS_NOT_FOUND = 200007;

    /*********************************************************
     * 业务错误码和消息 300001-399999
     ****************************/
}
