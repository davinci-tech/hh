package com.huawei.health;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ope;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes8.dex */
public class NfcEcgDeviceActivity extends Activity {
    private Bundle e;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.e = intent.getExtras();
        String stringExtra = intent.getStringExtra("PAYLOAD_FROM_NFC");
        LogUtil.a("NfcEcgDeviceActivity", stringExtra);
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        d(stringExtra);
    }

    private void d(String str) {
        String str2;
        String str3;
        String[] split;
        try {
            str2 = new URL("https://url.cloud.huawei.com/1Lfn1eswP6?a=d&" + str.substring(0, str.length() - 1)).getQuery();
        } catch (MalformedURLException e) {
            LogUtil.b("NfcEcgDeviceActivity", e.getClass().getSimpleName());
            str2 = null;
        }
        if (TextUtils.isEmpty(str2) || (split = (str3 = str2.split("&")[0]).split("=")) == null || split.length < 1) {
            return;
        }
        ope.e().deN_(split[1], str2.replace(str3, ""), this.e, this);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
