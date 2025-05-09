package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class MedalBasic extends mcz {
    private String medalID;
    private String medalName;
    private long timestamp;
    private String veinUrl;

    public MedalBasic() {
        super(7);
    }

    public String acquireMedalName() {
        return this.medalName;
    }

    public void saveMedalName(String str) {
        this.medalName = str;
    }

    public String acquireVeinUrl() {
        return this.veinUrl;
    }

    public void saveVeinUrl(String str) {
        this.veinUrl = str;
    }

    public long acquireTimestamp() {
        return this.timestamp;
    }

    public void saveTimestamp(long j) {
        this.timestamp = j;
    }

    public String acquireMedalID() {
        return this.medalID;
    }

    public void saveMedalID(String str) {
        this.medalID = str;
    }

    public String toString() {
        return "MedalBasic{medalName='" + this.medalName + "', medalID='" + this.medalID + "', veinUrl='" + this.veinUrl + "', timestamp=" + this.timestamp + '}';
    }
}
