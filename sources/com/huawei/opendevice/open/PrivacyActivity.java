package com.huawei.opendevice.open;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.CountryConfig;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import defpackage.lso;

/* loaded from: classes9.dex */
public class PrivacyActivity extends a {
    private boolean e = false;

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void a(String str, String str2, String str3) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void b() {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void b(String str) {
    }

    @Override // com.huawei.opendevice.open.a
    protected int e() {
        return R.layout.opendevice_web;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public String getCurrentPageUrl() {
        return null;
    }

    @Override // com.huawei.opendevice.open.a, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean b = bz.b(this);
        this.e = b;
        ho.b("PrivacyActivity", "onCreate, isInnerDevice: %s", Boolean.valueOf(b));
        if (this.e && ao.j(this)) {
            ao.b(this, Constants.HMS_PRIVACY);
            finish();
        }
    }

    @Override // com.huawei.opendevice.open.a, com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.opendevice.open.a
    protected String d() {
        if (bz.a(this).d()) {
            return "privacyThirdCN";
        }
        return "privacy" + d(new CountryCodeBean(this).a(), null);
    }

    @Override // com.huawei.opendevice.open.a
    protected void a(c cVar) {
        lso.b(this, cVar);
    }

    private String d(String str, String[] strArr) {
        if (CountryConfig.isDR1(str, strArr)) {
            return "CN";
        }
        if (CountryConfig.isDR2(str, strArr)) {
            return "HK";
        }
        if (CountryConfig.isDR3(str, strArr)) {
            return "EU";
        }
        if (CountryConfig.isDR4(str, strArr)) {
            return "RU";
        }
        ho.c("PrivacyActivity", "getSiteCode error, countryCode not belong to any site.");
        return "UNKNOWN";
    }
}
