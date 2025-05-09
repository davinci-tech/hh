package com.huawei.hwversionmgr.info;

/* loaded from: classes5.dex */
public class SecurityInfo {
    private String challenge;
    private String deviceCertificate;
    private String keyAttestation;
    private String securityMode;

    public String getChallenge() {
        return this.challenge;
    }

    public void setChallenge(String str) {
        this.challenge = str;
    }

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

    public String getSecurityMode() {
        return this.securityMode;
    }

    public void setSecurityMode(String str) {
        this.securityMode = str;
    }
}
