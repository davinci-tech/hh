package com.huawei.pluginachievement.manager.model;

/* loaded from: classes6.dex */
public class ExperienceHistoryBean {
    private long completeTime;
    private String dayOfMonth;
    private int experienceValue;
    private String monthOfYear;
    private int newLevel;
    private String reasonDesc;
    private int reasonId;
    private String time;

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExperienceHistoryBean experienceHistoryBean = (ExperienceHistoryBean) obj;
        return this.completeTime == experienceHistoryBean.completeTime && this.reasonId == experienceHistoryBean.reasonId;
    }

    public int getReasonId() {
        return this.reasonId;
    }

    public void setReasonId(int i) {
        this.reasonId = i;
    }

    public int getExperienceValue() {
        return this.experienceValue;
    }

    public void setExperienceValue(int i) {
        this.experienceValue = i;
    }

    public long getCompleteTime() {
        return this.completeTime;
    }

    public void setCompleteTime(long j) {
        this.completeTime = j;
    }

    public String getReasonDesc() {
        return this.reasonDesc;
    }

    public void setReasonDesc(String str) {
        this.reasonDesc = str;
    }

    public int getNewLevel() {
        return this.newLevel;
    }

    public void setNewLevel(int i) {
        this.newLevel = i;
    }

    public String getMonthOfYear() {
        return this.monthOfYear;
    }

    public void setMonthOfYear(String str) {
        this.monthOfYear = str;
    }

    public String getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(String str) {
        this.dayOfMonth = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }
}
