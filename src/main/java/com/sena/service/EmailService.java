package com.sena.service;

import com.sena.model.EmailModel;
import com.sena.result.Result;

/**
 * Created by Sena on 2017/3/16.
 */
public interface EmailService {
    Result<String> sendEmail(EmailModel emailModel);
}
