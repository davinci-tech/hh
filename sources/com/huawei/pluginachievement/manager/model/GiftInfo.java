package com.huawei.pluginachievement.manager.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes6.dex */
public class GiftInfo {

    @SerializedName("activityCode")
    private String mActivityCode;

    @SerializedName("awardItemId")
    private String mAwardItemId;

    @SerializedName("awardName")
    private String mAwardName;

    @SerializedName("awardType")
    private String mAwardType;

    @SerializedName("dayConsumed")
    private int mDayConsumed;

    @SerializedName("dayNum")
    private int mDayNum;

    @SerializedName("detailUrl")
    private String mDetailUrl;

    @SerializedName("endTime")
    private long mEndTime;

    @SerializedName("kPoint")
    private int mKakaPoint;

    @SerializedName("num")
    private int mNum;

    @SerializedName("pictures")
    private Map<String, String> mPictures;

    @SerializedName("remainingNum")
    private int mRemainingNum;

    @SerializedName("startTime")
    private long mStartTime;

    @SerializedName("ticketType")
    private String mTicketType;

    @SerializedName("weight")
    private int mWeight;

    public String getActivityCode() {
        return this.mActivityCode;
    }

    public void setActivityCode(String str) {
        this.mActivityCode = str;
    }

    public String getAwardItemId() {
        return this.mAwardItemId;
    }

    public void setAwardItemId(String str) {
        this.mAwardItemId = str;
    }

    public String getAwardType() {
        return this.mAwardType;
    }

    public void setAwardType(String str) {
        this.mAwardType = str;
    }

    public int getDayConsumed() {
        return this.mDayConsumed;
    }

    public void setDayConsumed(int i) {
        this.mDayConsumed = i;
    }

    public int getDayNum() {
        return this.mDayNum;
    }

    public void setDayNum(int i) {
        this.mDayNum = i;
    }

    public String getTicketType() {
        return this.mTicketType;
    }

    public void setTicketType(String str) {
        this.mTicketType = str;
    }

    public int getWeight() {
        return this.mWeight;
    }

    public void setWeight(int i) {
        this.mWeight = i;
    }

    public String getAwardName() {
        return this.mAwardName;
    }

    public void setAwardName(String str) {
        this.mAwardName = str;
    }

    public Map<String, String> getPictures() {
        return this.mPictures;
    }

    public void setPictures(Map<String, String> map) {
        this.mPictures = map;
    }

    public String getDetailUrl() {
        return this.mDetailUrl;
    }

    public void setDetailUrl(String str) {
        this.mDetailUrl = str;
    }

    public int getNum() {
        return this.mNum;
    }

    public void setNum(int i) {
        this.mNum = i;
    }

    public int getRemainingNum() {
        return this.mRemainingNum;
    }

    public void setRemainingNum(int i) {
        this.mRemainingNum = i;
    }

    public int getKakaPoint() {
        return this.mKakaPoint;
    }

    public void setKakaPoint(int i) {
        this.mKakaPoint = i;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }
}
