package com.alipay.sdk.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.operation.utils.Constants;
import defpackage.kl;
import defpackage.lv;

/* loaded from: classes8.dex */
public class H5OpenAuthActivity extends H5PayActivity {
    public boolean d = false;

    @Override // com.alipay.sdk.app.H5PayActivity
    public void e() {
    }

    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity
    public void onDestroy() {
        if (this.d) {
            try {
                lv a2 = lv.e.a(getIntent());
                if (a2 != null) {
                    kl.e(this, a2, "", a2.b);
                }
            } catch (Throwable unused) {
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            lv a2 = lv.e.a(intent);
            try {
                super.startActivity(intent);
                Uri data = intent != null ? intent.getData() : null;
                if (data == null || !data.toString().startsWith("alipays://platformapi/startapp")) {
                    return;
                }
                finish();
            } catch (Throwable th) {
                String uri = (intent == null || intent.getData() == null) ? Constants.NULL : intent.getData().toString();
                if (a2 != null) {
                    kl.a(a2, "biz", "StartActivityEx", th, uri);
                }
                this.d = true;
                throw th;
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
