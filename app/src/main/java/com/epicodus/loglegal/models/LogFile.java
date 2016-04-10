package com.epicodus.loglegal.models;

import org.parceler.Parcel;

@Parcel
public class LogFile {
    String name;
    String logFileId;
    private String index;

    public LogFile() {}

    public LogFile(String name, String logFileId) {
        this.name = name;
        this.logFileId = logFileId;

        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public String getLogFileId() {
        return logFileId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
