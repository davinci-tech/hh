package com.huawei.healthcloud.plugintrack.golf.bean;

import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwsportmodel.TrackGolfCourseSegment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GolfHistoryGraph {
    private String avgHandicap;
    private String bestHandicap;
    private ArrayList<HeartRateData> heartRateList;
    private ArrayList<HeartRateData> invalidHeartRateList;
    private List<TrackGolfCourseSegment> recordCard;

    public String getAvgHandicap() {
        return this.avgHandicap;
    }

    public void setAvgHandicap(String str) {
        this.avgHandicap = str;
    }

    public String getBestHandicap() {
        return this.bestHandicap;
    }

    public void setBestHandicap(String str) {
        this.bestHandicap = str;
    }

    public ArrayList<HeartRateData> getHeartRateList() {
        return this.heartRateList;
    }

    public void setHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.heartRateList = arrayList;
    }

    public List<TrackGolfCourseSegment> getRecordCard() {
        return this.recordCard;
    }

    public void setRecordCard(List<TrackGolfCourseSegment> list) {
        this.recordCard = list;
    }

    public ArrayList<HeartRateData> getInvalidHeartRateList() {
        return this.invalidHeartRateList;
    }

    public void setInvalidHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.invalidHeartRateList = arrayList;
    }

    public String toString() {
        return "GolfHistoryGraph{avgHandicap=" + this.avgHandicap + ", bestHandicap=" + this.bestHandicap + ", heartRateList=" + this.heartRateList + ", invalidHeartRateList=" + this.invalidHeartRateList + ", recordCard=" + this.recordCard + "}";
    }
}
