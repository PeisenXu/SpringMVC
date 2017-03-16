package com.sena.service;

import com.sena.exception.system.EmailException;
import com.sena.model.EmailModel;

/**
 * Created by Sena on 2017/3/16.
 */
public interface EmailService {
    void sendAsync(EmailModel email) throws EmailException;
}
