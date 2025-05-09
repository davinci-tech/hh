package com.huawei.datatype;

import defpackage.jdy;

/* loaded from: classes3.dex */
public class EcgMetaData {
    private int averageHeartRate;
    private String dataSources;
    private String ecgAppVersion;
    private long ecgArrhyType;
    private int ecgDataLength;
    private String packageName;
    private long userSymptom;

    public long getEcgArrhyType() {
        return ((Long) jdy.d(Long.valueOf(this.ecgArrhyType))).longValue();
    }

    public void setEcgArrhyType(long j) {
        this.ecgArrhyType = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getAverageHeartRate() {
        return ((Integer) jdy.d(Integer.valueOf(this.averageHeartRate))).intValue();
    }

    public void setAverageHeartRate(int i) {
        this.averageHeartRate = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getUserSymptom() {
        return ((Long) jdy.d(Long.valueOf(this.userSymptom))).longValue();
    }

    public void setUserSymptom(long j) {
        this.userSymptom = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getEcgDataLength() {
        return ((Integer) jdy.d(Integer.valueOf(this.ecgDataLength))).intValue();
    }

    public void setEcgDataLength(int i) {
        this.ecgDataLength = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getEcgAppVersion() {
        return this.ecgAppVersion;
    }

    public void setEcgAppVersion(String str) {
        this.ecgAppVersion = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getDataSources() {
        return this.dataSources;
    }

    public void setDataSources(String str) {
        this.dataSources = str;
    }
}
