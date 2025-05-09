package com.huawei.pluginachievement.manager.model;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class AchieveMessage implements Serializable {
    private static final long serialVersionUID = -6552584080262021471L;
    private String content;
    private int gainCount;
    private String gainTime;
    private String medalId;
    private int medalLevel;
    private String medalType = "";
    private int msgType;

    public int getMsgType() {
        return this.msgType;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public int acquireMedalLevel() {
        return this.medalLevel;
    }

    public void saveMedalLevel(int i) {
        this.medalLevel = i;
    }

    public String acquireMedalId() {
        return this.medalId;
    }

    public void saveMedalId(String str) {
        this.medalId = str;
    }

    public String acquireMedalType() {
        return this.medalType;
    }

    public void saveMedalType(String str) {
        this.medalType = str;
    }

    public int acquireGainCount() {
        return this.gainCount;
    }

    public void saveGainCount(int i) {
        this.gainCount = i;
    }

    public String getGainTime() {
        return this.gainTime;
    }

    public void setGainTime(String str) {
        this.gainTime = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String toString() {
        return "AchieveMessage{medalId='" + this.medalId + "', gainCount=" + this.gainCount + ", msgType=" + this.msgType + ", medalType='" + this.medalType + "', medalLevel=" + this.medalLevel + ", gainTime='" + this.gainTime + "', content='" + this.content + "'}";
    }
}
