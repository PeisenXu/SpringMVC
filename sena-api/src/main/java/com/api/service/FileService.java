package com.api.service;

import com.common.model.FileInfo;

/**
 * Created by Sena on 2017-07-18.
 */
public interface FileService {
    FileInfo getFile(String fileId);

    Object getIp(String ip, String type);
}
