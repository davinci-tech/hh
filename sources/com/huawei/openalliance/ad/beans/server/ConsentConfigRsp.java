package com.huawei.openalliance.ad.beans.server;

import com.huawei.hms.ads.consent.bean.AdProvider;
import com.huawei.openalliance.ad.beans.base.RspBean;
import java.util.List;

/* loaded from: classes9.dex */
public class ConsentConfigRsp extends RspBean {
    private List<AdProvider> companies;
    private int retcode = -1;
    private int isNeedConsent = 1;

    public List<AdProvider> c() {
        return this.companies;
    }

    public int b() {
        return this.isNeedConsent;
    }

    public int a() {
        return this.retcode;
    }
}
