package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class HwSportDetailInfo {

    @SerializedName("chartPage")
    private ArrayList<String> mChartPage;

    @SerializedName("detailFieldIndoor")
    private ArrayList<String> mDetailFieldIndoor;

    @SerializedName("detailFieldWear")
    private ArrayList<String> mDetailFieldWear;

    @SerializedName("detailOtherField")
    private ArrayList<String> mDetailOtherField;

    @SerializedName("segmentPage")
    private String mSegmentPage;

    @SerializedName("shareDataWaterMark")
    private ArrayList<String> mShareDataWaterMark;

    @SerializedName("subSports")
    private ArrayList<String> mSubSports;

    @SerializedName("trackPage")
    private HashMap<String, String> mTrackPage;

    public ArrayList<String> getDetailFieldWear() {
        return this.mDetailFieldWear;
    }

    public void setDetailFieldWear(ArrayList<String> arrayList) {
        this.mDetailFieldWear = arrayList;
    }

    public ArrayList<String> getDetailFieldIndoor() {
        return this.mDetailFieldIndoor;
    }

    public void setDetailFieldIndoor(ArrayList<String> arrayList) {
        this.mDetailFieldIndoor = arrayList;
    }

    public ArrayList<String> getChartPage() {
        return (ArrayList) this.mChartPage.clone();
    }

    public void setChartPage(ArrayList<String> arrayList) {
        this.mChartPage = arrayList;
    }

    public HashMap<String, String> getTrackPage() {
        return (HashMap) this.mTrackPage.clone();
    }

    public void setTrackPage(HashMap<String, String> hashMap) {
        this.mTrackPage = hashMap;
    }

    public String getSegmentPage() {
        return this.mSegmentPage;
    }

    public void setSegmentPage(String str) {
        this.mSegmentPage = str;
    }

    public ArrayList<String> getSubSports() {
        return (ArrayList) this.mSubSports.clone();
    }

    public void setSubSports(ArrayList<String> arrayList) {
        this.mSubSports = arrayList;
    }

    public ArrayList<String> getShareDataWaterMark() {
        return (ArrayList) this.mShareDataWaterMark.clone();
    }

    public void setShareDataWaterMark(ArrayList<String> arrayList) {
        this.mShareDataWaterMark = (ArrayList) arrayList.clone();
    }

    public ArrayList<String> getDetailOtherField() {
        return this.mDetailOtherField;
    }

    public void setDetailOtherField(ArrayList<String> arrayList) {
        this.mDetailOtherField = arrayList;
    }
}
