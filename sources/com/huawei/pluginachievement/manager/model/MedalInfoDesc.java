package com.huawei.pluginachievement.manager.model;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class MedalInfoDesc implements Serializable {
    private int gainCount;
    private String gainTime;
    private String grayDescription;
    private int isNewConfig;
    private String lightDescription;
    private String medalId;
    private int medalLabel;
    private String medalType;
    private String medalTypeLevel;
    private String message;
    private String promotionName;
    private String promotionURL;
    private int reachStatus;
    private String text;

    public MedalInfoDesc(String str) {
        this.medalId = str;
    }

    public String acquireMedalId() {
        return this.medalId;
    }

    public String acquireText() {
        return this.text;
    }

    public void saveText(String str) {
        this.text = str;
    }

    public String acquireLightDescription() {
        return this.lightDescription;
    }

    public void saveLightDescription(String str) {
        this.lightDescription = str;
    }

    public int acquireGainCount() {
        return this.gainCount;
    }

    public void saveGainCount(int i) {
        this.gainCount = i;
    }

    public void saveReachStatus(int i) {
        this.reachStatus = i;
    }

    public String acquireGainTime() {
        return this.gainTime;
    }

    public void saveGainTime(String str) {
        this.gainTime = str;
    }

    public void saveMessage(String str) {
        this.message = str;
    }

    public int acquireIsNewConfig() {
        return this.isNewConfig;
    }

    public void saveIsNewConfig(int i) {
        this.isNewConfig = i;
    }

    public String acquireGrayDescription() {
        return this.grayDescription;
    }

    public void saveGrayDescription(String str) {
        this.grayDescription = str;
    }

    public String acquireMedalTypeLevel() {
        return this.medalTypeLevel;
    }

    public void saveMedalTypeLevel(String str) {
        this.medalTypeLevel = str;
    }

    public String acquirePromotionName() {
        return this.promotionName;
    }

    public void savePromotionName(String str) {
        this.promotionName = str;
    }

    public String acquirePromotionURL() {
        return this.promotionURL;
    }

    public void savePromotionURL(String str) {
        this.promotionURL = str;
    }

    public String acquireMedalType() {
        return this.medalType;
    }

    public void saveMedalType(String str) {
        this.medalType = str;
    }

    public int acquireMedalLabel() {
        return this.medalLabel;
    }

    public void saveMedalLabel(int i) {
        this.medalLabel = i;
    }

    public String toString() {
        return "MedalInfoDesc{medalId='" + this.medalId + "', medalTypeLevel='" + this.medalTypeLevel + "', text='" + this.text + "', message='" + this.message + "', lightDescription='" + this.lightDescription + "', grayDescription='" + this.grayDescription + "', gainCount=" + this.gainCount + ", reachStatus=" + this.reachStatus + ", isNewConfig=" + this.isNewConfig + ", gainTime='" + this.gainTime + "', promotionName='" + this.promotionName + "', promotionURL='" + this.promotionURL + "'}";
    }
}
