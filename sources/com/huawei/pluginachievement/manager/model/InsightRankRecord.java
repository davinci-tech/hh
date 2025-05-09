package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;
import org.json.JSONArray;

/* loaded from: classes6.dex */
public class InsightRankRecord extends mcz {
    private String ageSegment;
    private String gender;
    private JSONArray insightRankItemJsonArray;
    private String rankType;
    private long rankVersion;

    public InsightRankRecord() {
        super(21);
        this.insightRankItemJsonArray = new JSONArray();
    }

    public String getRankType() {
        return this.rankType;
    }

    public void setRankType(String str) {
        this.rankType = str;
    }

    public String getAgeSegment() {
        return this.ageSegment;
    }

    public void setAgeSegment(String str) {
        this.ageSegment = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public JSONArray getInsightRankItemJsonArray() {
        return this.insightRankItemJsonArray;
    }

    public void setInsightRankItemJsonArray(JSONArray jSONArray) {
        this.insightRankItemJsonArray = jSONArray;
    }

    public long getRankVersion() {
        return this.rankVersion;
    }

    public void setRankVersion(long j) {
        this.rankVersion = j;
    }

    public String toString() {
        return "InsightRankRecord{rankType='" + this.rankType + "', rankVersion=" + this.rankVersion + ", ageSegment='" + this.ageSegment + "', gender='" + this.gender + "', insightRankItemJsonArray=" + this.insightRankItemJsonArray + '}';
    }
}
