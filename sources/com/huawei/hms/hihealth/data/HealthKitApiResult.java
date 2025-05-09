package com.huawei.hms.hihealth.data;

/* loaded from: classes4.dex */
public class HealthKitApiResult {
    private boolean isResultGzipped = false;
    private String response;

    public void setResultGzipped(boolean z) {
        this.isResultGzipped = z;
    }

    public void setResponse(String str) {
        this.response = str;
    }

    public boolean isResultGzipped() {
        return this.isResultGzipped;
    }

    public String getResponse() {
        return this.response;
    }
}
