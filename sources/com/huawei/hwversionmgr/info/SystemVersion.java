package com.huawei.hwversionmgr.info;

/* loaded from: classes5.dex */
public class SystemVersion {
    private String currentVersion;
    private String securityPatchLevel;
    private String softwareOSVersion;

    public String getSoftwareOSVersion() {
        return this.softwareOSVersion;
    }

    public void setSoftwareOSVersion(String str) {
        this.softwareOSVersion = str;
    }

    public String getCurrentVersion() {
        return this.currentVersion;
    }

    public void setCurrentVersion(String str) {
        this.currentVersion = str;
    }

    public String getSecurityPatchLevel() {
        return this.securityPatchLevel;
    }

    public void setSecurityPatchLevel(String str) {
        this.securityPatchLevel = str;
    }
}
