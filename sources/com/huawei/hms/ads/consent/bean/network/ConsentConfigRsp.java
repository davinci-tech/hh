package com.huawei.hms.ads.consent.bean.network;

import com.huawei.hms.ads.consent.bean.AdProvider;
import java.util.List;

/* loaded from: classes9.dex */
public class ConsentConfigRsp {
    private List<AdProvider> companies;
    private int isNeedConsent;
    private int retcode;

    public void setRetcode(int i) {
        this.retcode = i;
    }

    public void setIsNeedConsent(int i) {
        this.isNeedConsent = i;
    }

    public void setCompanies(List<AdProvider> list) {
        this.companies = list;
    }

    public int getRetcode() {
        return this.retcode;
    }

    public int getIsNeedConsent() {
        return this.isNeedConsent;
    }

    public List<AdProvider> getCompanies() {
        return this.companies;
    }

    public ConsentConfigRsp(com.huawei.openalliance.ad.beans.server.ConsentConfigRsp consentConfigRsp) {
        this.retcode = -1;
        if (consentConfigRsp != null) {
            this.retcode = consentConfigRsp.a();
            this.isNeedConsent = consentConfigRsp.b();
            this.companies = consentConfigRsp.c();
        }
    }
}
