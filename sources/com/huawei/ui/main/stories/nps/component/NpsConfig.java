package com.huawei.ui.main.stories.nps.component;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class NpsConfig {

    @SerializedName("activeConfigModel")
    private ActiveConfigModel mActiveConfigModel;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("needInvestigate")
    private Map<Integer, int[]> mNeedInvestigate = new HashMap();

    @SerializedName("deviceList")
    private List<Integer> mDeviceList = new ArrayList(10);

    @SerializedName("deviceWhitelist")
    private List<String> mDeviceWhitelist = new ArrayList(10);

    @SerializedName("healthVersion")
    private String mHealthVersion = "";

    @SerializedName("batch")
    private int mBatch = 3;

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public Map<Integer, int[]> getNeedInvestigate() {
        return this.mNeedInvestigate;
    }

    public void setNeedInvestigate(Map<Integer, int[]> map) {
        this.mNeedInvestigate = map;
    }

    public List<Integer> getDeviceList() {
        return this.mDeviceList;
    }

    public void setDeviceList(List<Integer> list) {
        this.mDeviceList = list;
    }

    public List<String> getDeviceWhitelist() {
        return this.mDeviceWhitelist;
    }

    public void setDeviceWhitelist(List<String> list) {
        this.mDeviceWhitelist = list;
    }

    public String getHealthVersion() {
        return this.mHealthVersion;
    }

    public void setHealthVersion(String str) {
        this.mHealthVersion = str;
    }

    public int getBatch() {
        return this.mBatch;
    }

    public void setBatch(int i) {
        this.mBatch = i;
    }

    public ActiveConfigModel getActiveConfigModel() {
        return this.mActiveConfigModel;
    }

    public void setActiveConfigModel(ActiveConfigModel activeConfigModel) {
        this.mActiveConfigModel = activeConfigModel;
    }

    public String toString() {
        return "NpsConfig{version=" + this.mVersion + ", needInvestigate=" + this.mNeedInvestigate + ", deviceList=" + this.mDeviceList + ", deviceWhitelist=" + this.mDeviceWhitelist + ", healthVersion=" + this.mHealthVersion + ", batch=" + this.mBatch + ", activeConfigModel=" + this.mActiveConfigModel + '}';
    }
}
