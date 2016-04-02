package com.epicodus.loglegal.models;

import org.parceler.Parcel;

@Parcel
public class LogFile {
    String name;
    String logFileId;

    public LogFile() {}

    public LogFile(String name, String logFileId) {
        this.name = name;
        this.logFileId = logFileId;
    }

    public String getName() {
        return name;
    }

    public String getLogFileId() {
        return logFileId;
    }
}
