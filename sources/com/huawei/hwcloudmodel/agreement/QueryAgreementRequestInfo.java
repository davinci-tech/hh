package com.huawei.hwcloudmodel.agreement;

/* loaded from: classes5.dex */
public class QueryAgreementRequestInfo {
    private int agrType;
    private String country;

    public QueryAgreementRequestInfo(int i, String str) {
        this.agrType = i;
        this.country = str;
    }

    public int getAgrType() {
        return this.agrType;
    }

    public void setAgrType(int i) {
        this.agrType = i;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }
}
