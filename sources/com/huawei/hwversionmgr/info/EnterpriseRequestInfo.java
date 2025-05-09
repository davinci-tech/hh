package com.huawei.hwversionmgr.info;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class EnterpriseRequestInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String deviceCertificate;
    private String deviceId;
    private String expiredTime;
    private String keyAttestation;

    public String getDeviceCertificate() {
        return this.deviceCertificate;
    }

    public void setDeviceCertificate(String str) {
        this.deviceCertificate = str;
    }

    public String getKeyAttestation() {
        return this.keyAttestation;
    }

    public void setKeyAttestation(String str) {
        this.keyAttestation = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getExpiredTime() {
        return this.expiredTime;
    }

    public void setExpiredTime(String str) {
        this.expiredTime = str;
    }
}
