package com.study.apnea.model.bean.algorithm;

/* loaded from: classes10.dex */
public class OsaAppOutputS {
    private OsaAppOutputHrS appHr;
    private OsaAppOutputOsaS appOsa;
    private OsaAppOutputSpo2S appSpo2;
    private String appVersion;
    private String date;
    private String deviceMac;
    private int deviceSize;
    private int deviceType;
    private String deviceVersion;
    private long endTimeStamp;
    private OsaTagS[] osaTagBuffer;
    private int osaTagBufferLen;
    private long returnCode;
    private long startTimeStamp;

    public OsaAppOutputS() {
    }

    public OsaAppOutputS(long j, long j2, long j3, OsaAppOutputOsaS osaAppOutputOsaS, OsaAppOutputSpo2S osaAppOutputSpo2S, OsaAppOutputHrS osaAppOutputHrS, OsaTagS[] osaTagSArr, int i) {
        setFields(j, j2, j3, osaAppOutputOsaS, osaAppOutputSpo2S, osaAppOutputHrS, osaTagSArr, i);
    }

    public void setFields(long j) {
        this.returnCode = j;
    }

    public void setFields(long j, long j2, long j3, OsaAppOutputOsaS osaAppOutputOsaS, OsaAppOutputSpo2S osaAppOutputSpo2S, OsaAppOutputHrS osaAppOutputHrS, OsaTagS[] osaTagSArr, int i) {
        this.returnCode = j;
        this.startTimeStamp = j2;
        this.endTimeStamp = j3;
        this.appOsa = osaAppOutputOsaS;
        this.appSpo2 = osaAppOutputSpo2S;
        this.appHr = osaAppOutputHrS;
        this.osaTagBuffer = osaTagSArr;
        this.osaTagBufferLen = i;
    }

    public long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public long getEndTimeStamp() {
        return this.endTimeStamp;
    }

    public OsaAppOutputOsaS getAppOsa() {
        return this.appOsa;
    }

    public OsaAppOutputSpo2S getAppSpo2() {
        return this.appSpo2;
    }

    public OsaAppOutputHrS getAppHr() {
        return this.appHr;
    }

    public void setStartTimeStamp(long j) {
        this.startTimeStamp = j;
    }

    public void setEndTimeStamp(long j) {
        this.endTimeStamp = j;
    }

    public void setAppOsa(OsaAppOutputOsaS osaAppOutputOsaS) {
        this.appOsa = osaAppOutputOsaS;
    }

    public void setAppSpo2(OsaAppOutputSpo2S osaAppOutputSpo2S) {
        this.appSpo2 = osaAppOutputSpo2S;
    }

    public void setAppHr(OsaAppOutputHrS osaAppOutputHrS) {
        this.appHr = osaAppOutputHrS;
    }

    public long getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(long j) {
        this.returnCode = j;
    }

    public OsaTagS[] getOsaTagBuffer() {
        return this.osaTagBuffer;
    }

    public int getOsaTagBufferLen() {
        return this.osaTagBufferLen;
    }

    public void setOsaTagBuffer(OsaTagS[] osaTagSArr) {
        this.osaTagBuffer = osaTagSArr;
    }

    public void setOsaTagBufferLen(int i) {
        this.osaTagBufferLen = i;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public int getDeviceSize() {
        return this.deviceSize;
    }

    public void setDeviceSize(int i) {
        this.deviceSize = i;
    }

    public String getOutputString() {
        StringBuffer stringBuffer = new StringBuffer("returnCode=");
        stringBuffer.append(this.returnCode).append(";\ndate=");
        stringBuffer.append(this.date).append(";\nstartTimeStamp=");
        stringBuffer.append(this.startTimeStamp).append(";\nendTimeStamp=");
        stringBuffer.append(this.endTimeStamp).append(";\nosaTagBufferLen=");
        stringBuffer.append(this.osaTagBufferLen).append(";\n");
        if (this.appOsa != null) {
            stringBuffer.append("appOsa=[").append(this.appOsa.getOutputString()).append("]\n");
        }
        if (this.appSpo2 != null) {
            stringBuffer.append("appSpo2=[").append(this.appSpo2.getOutputString()).append("]\n");
        }
        if (this.appHr != null) {
            stringBuffer.append("appHr=[").append(this.appHr.getOutputString()).append("]\n");
        }
        OsaTagS[] osaTagSArr = this.osaTagBuffer;
        if (osaTagSArr != null && osaTagSArr != null) {
            stringBuffer.append("osaTagBuffer=[");
            for (OsaTagS osaTagS : this.osaTagBuffer) {
                stringBuffer.append(osaTagS.getOutputString()).append(",\n");
            }
            stringBuffer.append("]");
        }
        stringBuffer.append("deviceType=").append(this.deviceType).append(";\nappVersion=");
        stringBuffer.append(this.appVersion).append(";\ndeviceVersion=");
        stringBuffer.append(this.deviceVersion).append(";\ndeviceMac=");
        stringBuffer.append(this.deviceMac).append(";\ndeviceSize=");
        stringBuffer.append(this.deviceSize).append(";\n");
        return stringBuffer.toString();
    }
}
