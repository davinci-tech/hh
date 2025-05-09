package com.huawei.ui.main.stories.nps.component;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ActiveConfigModel {

    @SerializedName("afterRunFilter")
    private List<String> mAfterRunFilter;

    @SerializedName("afterRunPeriod")
    private long mAfterRunPeriod;

    @SerializedName("devicesNpsRatioMap")
    private Map<String, Integer> mDevicesNpsRatioMap;

    @SerializedName("noStepsController")
    private List<String> mNoStepsFilter;

    @SerializedName("noStepsPeriod")
    private long mNoStepsPeriod;

    @SerializedName("npsInvalidDevices")
    private List<String> mNpsInvalidDevices;

    @SerializedName("npsInvalidPeriod")
    private List<String> mNpsInvalidPeriod;

    @SerializedName("npsInvalidPhoneForDeviceNps")
    private List<String> mNpsInvalidPhoneForDeviceNps;

    @SerializedName("phoneNpsRatio")
    private int mPhoneNpsRatio = -1;

    @SerializedName("devicesNpsRatio")
    private int mDevicesNpsRatio = -1;

    @SerializedName("phoneWithDeviceNpsRatio")
    private int mPhoneWithDeviceNpsRatio = -1;

    public enum NpsRatioType {
        PHONE_NPS_RATIO,
        DEVICES_NPS_RATIO,
        PHONE_WITH_DEVICES_NPS_RATIO
    }

    public List<String> getNpsInvalidDevices() {
        return this.mNpsInvalidDevices;
    }

    public void setNpsInvalidDevices(List<String> list) {
        this.mNpsInvalidDevices = list;
    }

    public List<String> getNpsInvalidPeriod() {
        return this.mNpsInvalidPeriod;
    }

    public void setNpsInvalidPeriod(List<String> list) {
        this.mNpsInvalidPeriod = list;
    }

    public long getNoStepsPeriod() {
        return this.mNoStepsPeriod;
    }

    public void setNoStepsPeriod(long j) {
        this.mNoStepsPeriod = j;
    }

    public List<String> getNoStepsFilter() {
        return this.mNoStepsFilter;
    }

    public void setNoStepsFilter(List<String> list) {
        this.mNoStepsFilter = list;
    }

    public long getAfterRunPeriod() {
        return this.mAfterRunPeriod;
    }

    public void setAfterRunPeriod(long j) {
        this.mAfterRunPeriod = j;
    }

    public List<String> getAfterRunFilter() {
        return this.mAfterRunFilter;
    }

    public void setAfterRunFilter(List<String> list) {
        this.mAfterRunFilter = list;
    }

    public int getPhoneNpsRatio() {
        return this.mPhoneNpsRatio;
    }

    public void setPhoneNpsRatio(int i) {
        this.mPhoneNpsRatio = i;
    }

    public int getDevicesNpsRatio() {
        return this.mDevicesNpsRatio;
    }

    public void setDevicesNpsRatio(int i) {
        this.mDevicesNpsRatio = i;
    }

    public int getPhoneWithDeviceNpsRatio() {
        return this.mPhoneWithDeviceNpsRatio;
    }

    public void setPhoneWithDeviceNpsRatio(int i) {
        this.mPhoneWithDeviceNpsRatio = i;
    }

    public List<String> getNpsInvalidPhoneForDeviceNps() {
        return this.mNpsInvalidPhoneForDeviceNps;
    }

    public void setNpsInvalidPhoneForDeviceNps(List<String> list) {
        this.mNpsInvalidPhoneForDeviceNps = list;
    }

    public Map<String, Integer> getDevicesNpsRatioMap() {
        return this.mDevicesNpsRatioMap;
    }

    public void setDevicesNpsRatioMap(Map<String, Integer> map) {
        this.mDevicesNpsRatioMap = map;
    }

    public String toString() {
        return "ActiveConfigModel{mNpsInvalidDevices=" + this.mNpsInvalidDevices + ", mNpsInvalidPeriod=" + this.mNpsInvalidPeriod + ", mNoStepsPeriod=" + this.mNoStepsPeriod + ", mNoStepsFilter=" + this.mNoStepsFilter + ", mAfterRunPeriod=" + this.mAfterRunPeriod + ", mAfterRunFilter=" + this.mAfterRunFilter + ", mPhoneNpsRatio=" + this.mPhoneNpsRatio + ", mDevicesNpsRatio=" + this.mDevicesNpsRatio + ", mPhoneWithDeviceNpsRatio=" + this.mPhoneWithDeviceNpsRatio + '}';
    }
}
