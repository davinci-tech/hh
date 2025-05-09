package com.huawei.health.marketing.request;

import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes3.dex */
public class ActivityIdInfo {
    private String activityId;
    private String beginDate;
    private String endDate;
    private int numberOfPeople;
    private List<String> portraitUrls;

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public String getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(String str) {
        this.beginDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int i) {
        this.numberOfPeople = i;
    }

    public List<String> getPortraitUrls() {
        return this.portraitUrls;
    }

    public void setPortraitUrls(List<String> list) {
        this.portraitUrls = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ActivityIdInfo{activityId='");
        sb.append(this.activityId);
        sb.append("', beginDate='");
        sb.append(this.beginDate);
        sb.append("', endDate='");
        sb.append(this.endDate);
        sb.append("', numberOfPeople=");
        sb.append(this.numberOfPeople);
        sb.append(", portraitUrls: ");
        Object obj = this.portraitUrls;
        if (obj == null) {
            obj = Constants.NULL;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }
}
