package com.api.model;

import java.io.InputStream;

/**
 * Created by Sena on 2017-07-18.
 */
public class FileInfo {
    private String name;
    private InputStream stream;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }
}
