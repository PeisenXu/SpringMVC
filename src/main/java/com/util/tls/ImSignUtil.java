package com.util.tls;

import com.learninggenie.common.exception.LearningGenieRuntimeException;
import com.learninggenie.common.utils.StringUtil;

import java.io.IOException;
import java.util.zip.DataFormatException;

import static com.learninggenie.common.utils.tls.tls_sigature.*;

/**
 * Created by Zjj on 2017/1/12.
 */
public class ImSignUtil {
    /**
     * 生成UserSig
     *
     * @param identifier 用户ID
     * @return
     * @throws Exception
     */
    public static String getUserSig(String identifier) throws IOException {
        GenTLSSignatureResult result = GenTLSSignatureEx(ImConfig.QC_SDK_APP_ID, identifier);
        String sig = result.urlSig;
        if (StringUtil.isEmptyOrBlank(result.urlSig))
            throw new LearningGenieRuntimeException("get user sig failed");
        System.out.println("---\ngenerate sig:\n" + sig + "\n---\n");
        return sig;
    }

    /**
     * 验证签名
     *
     * @param userSig 签名
     * @param identifier 用户ID
     * @return
     * @throws Exception
     */
    public static boolean checkUserSig(String userSig, String identifier) throws DataFormatException {
        CheckTLSSignatureResult checkResult = CheckTLSSignatureEx(userSig, ImConfig.QC_SDK_APP_ID, identifier);
        return checkResult.verifyResult;
    }

    public static void main(String[] args) {
        try {

            String userSig = getUserSig("1032348736");
            System.out.println("UserSig: " + userSig);

            boolean checkResult = checkUserSig(userSig, "1032348736");
            System.out.println("Check UserSig Result: " + checkResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

