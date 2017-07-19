package com.sena.service;

import com.sena.model.FileInfo;

/**
 * Created by Sena on 2017-07-18.
 */
public interface FileService {
    FileInfo getFile(String fileId);

    String getIp(String ip);
}
