package com.huawei.openalliance.ad.beans.server;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.base.ReqBean;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.utils.af;

/* loaded from: classes9.dex */
public class ConsentConfigReq extends ReqBean {
    private String consentVersion;
    private String countryCode;
    private Integer debugFlag;
    private String langCode;
    private String pkgName;

    public String d() {
        return this.consentVersion;
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String c() {
        return Constants.KIT_APP_ID;
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String b() {
        return Constants.KIT_CONSENT_CONFIG_URI;
    }

    public void a(String str) {
        this.consentVersion = str;
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a(Context context) {
        return context.getString(af.a() ? R.string._2130851126_res_0x7f023536 : R.string._2130851127_res_0x7f023537);
    }

    @Override // com.huawei.openalliance.ad.beans.base.ReqBean
    public String a() {
        return "consentlookup";
    }

    public ConsentConfigReq(com.huawei.hms.ads.consent.bean.network.ConsentConfigReq consentConfigReq) {
        this.consentVersion = consentConfigReq.getConsentVersion();
        this.pkgName = consentConfigReq.getPkgName();
        this.countryCode = consentConfigReq.getCountryCode();
        this.langCode = consentConfigReq.getLangCode();
        this.debugFlag = consentConfigReq.getDebugFlag();
    }

    public ConsentConfigReq() {
    }
}
