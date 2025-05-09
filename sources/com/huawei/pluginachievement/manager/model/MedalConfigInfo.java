package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class MedalConfigInfo extends mcz {
    private int actionType;
    private int activityId;
    private String clientTypes;
    private String endTime;
    private int eventStatus;
    private int goal;
    private String grayDescription;
    private String grayDetailStyle;
    private String grayListStyle;
    private String grayPromotionName;
    private String grayPromotionUrl;
    private int isNewConfig;
    private String lightDescription;
    private String lightDetailStyle;
    private String lightListStyle;
    private String lightPromotionName;
    private String lightPromotionUrl;
    private String location;
    private String medalID;
    private int medalLabel;
    private int medalLevel;
    private String medalName;
    private String medalType;
    private int medalUnit;
    private String message;
    private String phoneTypes;
    private int reachStatus;
    private int repeatable;
    private String shareImageUrl;
    private String startTime;
    private String takeEffectTime;
    private long timestamp;

    public MedalConfigInfo() {
        super(9);
    }

    public String acquireMedalName() {
        return this.medalName;
    }

    public void saveMedalName(String str) {
        this.medalName = str;
    }

    public String acquireMedalID() {
        return this.medalID;
    }

    public void saveMedalID(String str) {
        this.medalID = str;
    }

    public String acquireMessage() {
        return this.message;
    }

    public void saveMessage(String str) {
        this.message = str;
    }

    public String acquireGrayDescription() {
        return this.grayDescription;
    }

    public void saveGrayDescription(String str) {
        this.grayDescription = str;
    }

    public String acquireLightDescription() {
        return this.lightDescription;
    }

    public void saveLightDescription(String str) {
        this.lightDescription = str;
    }

    public String acquireGrayPromotionName() {
        return this.grayPromotionName;
    }

    public void saveGrayPromotionName(String str) {
        this.grayPromotionName = str;
    }

    public String acquireGrayPromotionUrl() {
        return this.grayPromotionUrl;
    }

    public void saveGrayPromotionUrl(String str) {
        this.grayPromotionUrl = str;
    }

    public String acquireLightPromotionName() {
        return this.lightPromotionName;
    }

    public void saveLightPromotionName(String str) {
        this.lightPromotionName = str;
    }

    public String acquireLightPromotionUrl() {
        return this.lightPromotionUrl;
    }

    public void saveLightPromotionUrl(String str) {
        this.lightPromotionUrl = str;
    }

    public String acquireGrayListStyle() {
        return this.grayListStyle;
    }

    public void saveGrayListStyle(String str) {
        this.grayListStyle = str;
    }

    public String acquireLightListStyle() {
        return this.lightListStyle;
    }

    public void saveLightListStyle(String str) {
        this.lightListStyle = str;
    }

    public String acquireGrayDetailStyle() {
        return this.grayDetailStyle;
    }

    public void saveGrayDetailStyle(String str) {
        this.grayDetailStyle = str;
    }

    public String acquireLightDetailStyle() {
        return this.lightDetailStyle;
    }

    public void saveLightDetailStyle(String str) {
        this.lightDetailStyle = str;
    }

    public String acquireShareImageUrl() {
        return this.shareImageUrl;
    }

    public void saveShareImageUrl(String str) {
        this.shareImageUrl = str;
    }

    public int acquireGoal() {
        return this.goal;
    }

    public void saveGoal(int i) {
        this.goal = i;
    }

    public String acquireLocation() {
        return this.location;
    }

    public void saveLocation(String str) {
        this.location = str;
    }

    public int acquireActivityId() {
        return this.activityId;
    }

    public void saveActivityId(int i) {
        this.activityId = i;
    }

    public String acquireStartTime() {
        return this.startTime;
    }

    public void saveStartTime(String str) {
        this.startTime = str;
    }

    public String acquireEndTime() {
        return this.endTime;
    }

    public void saveEndTime(String str) {
        this.endTime = str;
    }

    public String acquireTakeEffectTime() {
        return this.takeEffectTime;
    }

    public void saveTakeEffectTime(String str) {
        this.takeEffectTime = str;
    }

    public String getClientTypes() {
        return this.clientTypes;
    }

    public void setClientTypes(String str) {
        this.clientTypes = str;
    }

    public String getPhoneTypes() {
        return this.phoneTypes;
    }

    public void setPhoneTypes(String str) {
        this.phoneTypes = str;
    }

    public int acquireRepeatable() {
        return this.repeatable;
    }

    public void saveRepeatable(int i) {
        this.repeatable = i;
    }

    public int acquireIsNewConfig() {
        return this.isNewConfig;
    }

    public void saveIsNewConfig(int i) {
        this.isNewConfig = i;
    }

    public long acquireTimestamp() {
        return this.timestamp;
    }

    public void saveTimestamp(long j) {
        this.timestamp = j;
    }

    public int acquireReachStatus() {
        return this.reachStatus;
    }

    public void saveReachStatus(int i) {
        this.reachStatus = i;
    }

    public int acquireEventStatus() {
        return this.eventStatus;
    }

    public void saveEventStatus(int i) {
        this.eventStatus = i;
    }

    public int acquireActionType() {
        return this.actionType;
    }

    public void saveActionType(int i) {
        this.actionType = i;
    }

    public String acquireMedalType() {
        return this.medalType;
    }

    public void saveMedalType(String str) {
        this.medalType = str;
    }

    public int acquireMedalLevel() {
        return this.medalLevel;
    }

    public void saveMedalLevel(int i) {
        this.medalLevel = i;
    }

    public int acquireMedalUnit() {
        return this.medalUnit;
    }

    public void saveMedalUnit(int i) {
        this.medalUnit = i;
    }

    public int acquireMedalLabel() {
        return this.medalLabel;
    }

    public void saveMedalLabel(int i) {
        this.medalLabel = i;
    }

    public String toString() {
        return "MedalConfigInfo{medalName='" + this.medalName + "', medalID='" + this.medalID + "', message='" + this.message + "', medalType='" + this.medalType + "', medalLevel='" + this.medalLevel + "', medalUnit='" + this.medalUnit + "', medalLabel='" + this.medalLabel + "', grayDescription='" + this.grayDescription + "', lightDescription='" + this.lightDescription + "', grayPromotionName='" + this.grayPromotionName + "', grayPromotionUrl='" + this.grayPromotionUrl + "', lightPromotionName='" + this.lightPromotionName + "', lightPromotionUrl='" + this.lightPromotionUrl + "', grayListStyle='" + this.grayListStyle + "', lightListStyle='" + this.lightListStyle + "', grayDetailStyle='" + this.grayDetailStyle + "', lightDetailStyle='" + this.lightDetailStyle + "', shareImageUrl='" + this.shareImageUrl + "', actionType=" + this.actionType + ", goal=" + this.goal + ", location='" + this.location + "', activityId=" + this.activityId + ", startTime='" + this.startTime + "', endTime='" + this.endTime + "', takeEffectTime='" + this.takeEffectTime + "', repeatable=" + this.repeatable + ", isNewConfig=" + this.isNewConfig + ", timestamp=" + this.timestamp + ", reachStatus=" + this.reachStatus + ", eventStatus=" + this.eventStatus + '}';
    }
}
