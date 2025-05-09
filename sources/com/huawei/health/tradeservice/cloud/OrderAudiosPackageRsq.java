package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class OrderAudiosPackageRsq extends BaseRsp {

    @SerializedName("packageList")
    private ArrayList<SleepAudioSeries> packageList;

    @SerializedName("totalCount")
    private int totalCount;

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public ArrayList<SleepAudioSeries> getPackageList() {
        return this.packageList;
    }

    public void setPackageList(ArrayList<SleepAudioSeries> arrayList) {
        this.packageList = arrayList;
    }

    public static class SleepAudioSeries {

        @SerializedName("sleepAudioSeries")
        private SleepAudioSerie sleepAudioSeries;

        public SleepAudioSerie getSleepAudioSeries() {
            return this.sleepAudioSeries;
        }

        public void setSleepAudioSeries(SleepAudioSerie sleepAudioSerie) {
            this.sleepAudioSeries = sleepAudioSerie;
        }
    }

    public static class SleepAudioSerie {

        @SerializedName("enableNewUrl")
        private int enableNewUrl;

        public int getEnableNewUrl() {
            return this.enableNewUrl;
        }

        public void setEnableNewUrl(int i) {
            this.enableNewUrl = i;
        }
    }
}
