package com.api.service;

import com.api.result.Result;
import com.api.model.EmailModel;

/**
 * Created by Sena on 2017/3/16.
 */
public interface EmailService {
    Result<String> sendEmail(EmailModel emailModel);
}