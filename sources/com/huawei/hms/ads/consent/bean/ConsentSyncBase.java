package com.huawei.hms.ads.consent.bean;

import java.util.List;

/* loaded from: classes9.dex */
public class ConsentSyncBase {
    protected List<String> adProviderIds;
    protected int consentStatus;

    public void setConsentStatus(int i) {
        this.consentStatus = i;
    }

    public void setAdProviderIds(List<String> list) {
        this.adProviderIds = list;
    }

    public int getConsentStatus() {
        return this.consentStatus;
    }

    public List<String> getAdProviderIds() {
        return this.adProviderIds;
    }

    public ConsentSyncBase(List<String> list, int i) {
        this.adProviderIds = list;
        this.consentStatus = i;
    }

    public ConsentSyncBase() {
    }
}
