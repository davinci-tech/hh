package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class MedalLocation extends mcz {
    private String firstTabDesc;
    private int firstTabPriority;
    private int gainedCount;
    private String medalGainedTime;
    private String medalID;
    private String medalName;
    private int medalWeight;
    private String secondTabDesc;
    private int secondTabPriority;
    private long timestamp;

    public MedalLocation() {
        super(8);
    }

    public String acquireMedalID() {
        return this.medalID;
    }

    public void saveMedalID(String str) {
        this.medalID = str;
    }

    public String acquireMedalName() {
        return this.medalName;
    }

    public void saveMedalName(String str) {
        this.medalName = str;
    }

    public int acquireFirstTabPriority() {
        return this.firstTabPriority;
    }

    public void saveFirstTabPriority(int i) {
        this.firstTabPriority = i;
    }

    public String acquireFirstTabDesc() {
        return this.firstTabDesc;
    }

    public void saveFirstTabDesc(String str) {
        this.firstTabDesc = str;
    }

    public int acquireSecondTabPriority() {
        return this.secondTabPriority;
    }

    public void saveSecondTabPriority(int i) {
        this.secondTabPriority = i;
    }

    public String acquireSecondTabDesc() {
        return this.secondTabDesc;
    }

    public void saveSecondTabDesc(String str) {
        this.secondTabDesc = str;
    }

    public int acquireMedalWeight() {
        return this.medalWeight;
    }

    public void saveMedalWeight(int i) {
        this.medalWeight = i;
    }

    public long acquireTimestamp() {
        return this.timestamp;
    }

    public void saveTimestamp(long j) {
        this.timestamp = j;
    }

    public String acquireMedalGainedTime() {
        return this.medalGainedTime;
    }

    public void saveMedalGainedTime(String str) {
        this.medalGainedTime = str;
    }

    public int acquireGainedCount() {
        return this.gainedCount;
    }

    public void saveGainedCount(int i) {
        this.gainedCount = i;
    }

    public String toString() {
        return "MedalLocation{medalName='" + this.medalName + "', medalID='" + this.medalID + "', firstTabPriority=" + this.firstTabPriority + ", firstTabDesc='" + this.firstTabDesc + "', secondTabPriority=" + this.secondTabPriority + ", secondTabDesc='" + this.secondTabDesc + "', medalWeight=" + this.medalWeight + ", medalGainedTime='" + this.medalGainedTime + "', gainedCount=" + this.gainedCount + ", timestamp=" + this.timestamp + '}';
    }
}
