package com.util.tls;

import com.learninggenie.common.data.model.communication.GetChatGroupRequest;
import com.learninggenie.common.data.model.communication.IMGetChatGroupResponse;
import com.learninggenie.common.exception.LearningGenieRuntimeException;
import com.learninggenie.common.utils.HttpUtil;
import com.learninggenie.common.utils.JsonUtil;
import com.learninggenie.common.utils.ResourceUtil;
import com.learninggenie.common.utils.StringUtil;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by Zjj on 2017/1/18.
 */
public class ImConfig {
    private static Logger logger = LoggerFactory.getLogger(ImConfig.class);
    public static String sig;
    public static final String QC_ACCOUNT_TYPE = "10090";
    public static final String IDENTIFIER = "1032348736";
    public static final long QC_SDK_APP_ID = 1400023543;
    public static final String QC_PUBLIC_KEY = ResourceUtil.getResourceAsString("testKeys/public_key");
    public static final String QC_PRIVATE_KEY = ResourceUtil.getResourceAsString("testKeys/private_key");
//    public static final String QC_ACCOUNT_TYPE = "10774";
//    public static final String IDENTIFIER = "1561345727";
//    public static final long QC_SDK_APP_ID = 1400025082;
//    public static final String QC_PUBLIC_KEY = ResourceUtil.getResourceAsString("keys/public_key");
//    public static final String QC_PRIVATE_KEY = ResourceUtil.getResourceAsString("keys/private_key");


    public static final String QC_IM_HOST = "https://console.tim.qq.com";

    public static String generateSig() {
        try {
            if (!StringUtil.isEmptyOrBlank(sig)) {
                if (!ImSignUtil.checkUserSig(sig, IDENTIFIER)) {
                    sig = ImSignUtil.getUserSig(IDENTIFIER);
                }
            } else {
                sig = ImSignUtil.getUserSig(IDENTIFIER);
            }
            return sig;
        } catch (Exception e) {
            throw new LearningGenieRuntimeException("get user sig failed");
        }

    }

    public static void main(String[] args) throws IOException, DataFormatException {
        generateSig();
        logger.info(sig);
        ImSignUtil.checkUserSig(sig, IDENTIFIER);
        //-------------------------------------------------//
//        CreateChatGroupRequest request = new CreateChatGroupRequest();
//        request.setType("Public");
//        request.setOwner_Account("1032348736");
//        request.setName("2");
//        request.setGroupId("02F87D73-B464-E611-BCDE-06538D331EA1");
//        if (StringUtil.isEmptyOrBlank(request.getGroupId()) || StringUtil.isEmptyOrBlank(request.getName()) || StringUtil.isEmptyOrBlank(request.getType())) {
//            throw new BusinessException(ErrorCode.PARAM_ERROR);
//        }
//        String url = HttpUtil.formatUrl("group_open_http_svc","create_group");
//        String json = HttpUtil.sendPost(url, JsonUtil.toJson(request), HTTP.UTF_8);
//        IMCreateChatGroupResponse response = JsonUtil.fromJson(json,IMCreateChatGroupResponse.class);
//        logger.info(json);
//        if (!response.getActionStatus().equalsIgnoreCase(HttpStatus.OK.getReasonPhrase())) {
//            logger.info(response.getErrorInfo());
//            throw new BusinessException(ErrorCode.PARAM_ERROR,response.getErrorInfo());
//        }
        //-------------------------------------------------//
        GetChatGroupRequest request = new GetChatGroupRequest();
        request.getGroupIdList().add("823F814F-F4EC-E411-AF66-02C72B94B99B");
        String url = HttpUtil.formatUrl("group_open_http_svc","get_group_info");
        String json = HttpUtil.sendPost(url, JsonUtil.toJson(request), HTTP.UTF_8);
        logger.info(json);
        IMGetChatGroupResponse response = JsonUtil.fromJson(json,IMGetChatGroupResponse.class);
        if (!response.getActionStatus().equalsIgnoreCase(HttpStatus.OK.getReasonPhrase())) {
            logger.info(response.getErrorInfo());
        }
        //-------------------------------------------------//
//        BatchCreateMemberRequest request = new BatchCreateMemberRequest();
//        request.getAccounts().add("08D78B7F-23A5-4A43-B00F-0002B64B1AA7");
//        String url = HttpUtil.formatUrl("im_open_login_svc","multiaccount_import");
//        String json = HttpUtil.sendPost(url,JsonUtil.toJson(request), HTTP.UTF_8);
//        logger.info(json);
//        IMBatchCreateMemberResponse response = JsonUtil.fromJson(json,IMBatchCreateMemberResponse.class);
//        if (!response.getActionStatus().equalsIgnoreCase(HttpStatus.OK.getReasonPhrase())){
//            logger.info(response.getErrorInfo());
//            throw new BusinessException(ErrorCode.PARAM_ERROR,response.getErrorInfo());
        }
        //-------------------------------------------------//
//        AddUserChatGroupRequest request = new AddUserChatGroupRequest();
//        request.setGroupId("02F87D73-B464-E611-BCDE-06538D331EA1");
//        request.getMemberList().add(new IMMemberModel("08D78B7F-23A5-4A43-B00F-0002B64B1AA7"));
//        String url = HttpUtil.formatUrl("group_open_http_svc","add_group_member");
//        String json = HttpUtil.sendPost(url,JsonUtil.toJson(request), HTTP.UTF_8);
//        logger.info(json);
//        IMAddUserGroupResponse response = JsonUtil.fromJson(json,IMAddUserGroupResponse.class);
//        if (!response.getActionStatus().equalsIgnoreCase(HttpStatus.OK.getReasonPhrase()))
//            logger.info(response.getErrorInfo());
//            throw new BusinessException(ErrorCode.PARAM_ERROR,response.getErrorInfo());
//    }
}
