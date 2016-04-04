package com.epicodus.loglegal.models;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Incident {
    long timeStamp;
    long negativeTimeStamp;

    String logFileId;
    String incidentId;
    String incidentDate;
    String incidentTime;
    String witnesses;
    String description;
    String policeBadgeNumber;

    public Incident() {}

    public Incident(String logFileId, String incidentId, String incidentDate, String incidentTime, String witnesses, String description, String policeBadgeNumber) {
        this.timeStamp = new Date().getTime();
        this.negativeTimeStamp = timeStamp * -1;

        this.logFileId = logFileId;
        this.incidentId = incidentId;
        this.incidentDate = incidentDate;
        this.incidentTime = incidentTime;
        this.witnesses = witnesses;
        this.description = description;
        this.policeBadgeNumber = policeBadgeNumber;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public long getNegativeTimeStamp() {
        return negativeTimeStamp;
    }

    public String getLogFileId() {
        return logFileId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public String getWitnesses() {
        return witnesses;
    }

    public String getDescription() {
        return description;
    }

    public String getPoliceBadgeNumber() {
        return policeBadgeNumber;
    }
}
