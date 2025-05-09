package com.huawei.ui.main.stories.nps.interactors.db;

import java.io.Serializable;

/* loaded from: classes7.dex */
public class QuestionSurveyTable implements Serializable {
    private String deviceId;
    private String deviceType;
    private int id = 0;
    private long lastSurveyTime;
    private String surveyId;
    private int times;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public long getLastSurveyTime() {
        return this.lastSurveyTime;
    }

    public void setLastSurveyTime(long j) {
        this.lastSurveyTime = j;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public int getTimes() {
        return this.times;
    }

    public void setTimes(int i) {
        this.times = i;
    }

    public String getSurveyId() {
        return this.surveyId;
    }

    public void setSurveyId(String str) {
        this.surveyId = str;
    }

    public String toString() {
        return "QuestionSurveyTable{, lastSurveyTime=" + this.lastSurveyTime + ", deviceType='" + this.deviceType + "', times=" + this.times + ", surveyId=" + this.surveyId + ", id=" + this.id + '}';
    }
}
