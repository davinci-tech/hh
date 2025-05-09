package com.huawei.opendevice.open;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.utils.ao;
import defpackage.lso;

/* loaded from: classes9.dex */
public class WhyThisAdStatementActivity extends a {
    private Context c;

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
        Context applicationContext = getApplicationContext();
        this.c = applicationContext;
        boolean b = bz.b(applicationContext);
        boolean j = ao.j(this.c);
        if (b && j) {
            ao.b(this.c, Constants.WHY_THIS_AD_DEFAULT_URL);
            finish();
        }
    }

    @Override // com.huawei.opendevice.open.a, com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.opendevice.open.a
    protected int h() {
        return !bz.a(getContext()).d() ? R.string._2130851031_res_0x7f0234d7 : super.h();
    }

    @Override // com.huawei.opendevice.open.a
    protected String d() {
        return "whyThisAdThird";
    }

    @Override // com.huawei.opendevice.open.a
    protected void a(c cVar) {
        lso.d(this, cVar);
    }
}
