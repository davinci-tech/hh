package com.huawei.wear.wallet.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hms.ui.SafeIntent;
import com.huawei.wallet.proxy.InitWalletProxy;
import defpackage.stq;
import defpackage.tei;
import defpackage.tek;
import java.util.WeakHashMap;

/* loaded from: classes9.dex */
public class ThirdInvokeActivity extends FragmentActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f11216a = "";
    private SafeIntent b;
    private WeakHashMap<String, String> d;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        tek.c("ThirdInvokeActivity", getApplicationContext());
        try {
            InitWalletProxy.c();
            stq.b("ThirdInvokeActivity", "plugin is available");
            if (!tek.b(this)) {
                stq.e("ThirdInvokeActivity", "onCreate, agreement is not accepted");
                finish();
                return;
            }
            if (b()) {
                finish();
                return;
            }
            c();
            if (TextUtils.isEmpty(this.f11216a) || !this.d.containsKey(this.f11216a)) {
                return;
            }
            stq.b("ThirdInvokeActivity", "action enter");
            SafeIntent safeIntent = new SafeIntent(new Intent());
            safeIntent.putExtra("thirdInvoke", true);
            safeIntent.putExtra("cardType", this.b.getStringExtra("cardType"));
            safeIntent.putExtra("passTypeId", this.b.getStringExtra("passTypeId"));
            safeIntent.putExtra("passId", this.b.getStringExtra("passId"));
            safeIntent.putExtra("passGroup", this.b.getStringExtra("passGroup"));
            safeIntent.putExtra("productId", this.b.getStringExtra("productId"));
            safeIntent.setClassName(getPackageName(), this.d.get(this.f11216a));
            safeIntent.setFlags(268468224);
            tei.eYV_(this, safeIntent);
            finish();
        } catch (ClassNotFoundException unused) {
            stq.e("ThirdInvokeActivity", "onCreate, not PluginAvailable");
            finish();
        }
    }

    private boolean b() {
        Uri data = new SafeIntent(getIntent()).getData();
        if (data == null) {
            return false;
        }
        String scheme = data.getScheme();
        String host = data.getHost();
        if (!TextUtils.isEmpty(host) && !host.contains("@")) {
            String path = data.getPath();
            if (TextUtils.equals(scheme, "wallet") && TextUtils.equals(host, "com.huawei.health") && TextUtils.equals(path, "/financeclient/token2")) {
                SafeIntent safeIntent = new SafeIntent(new Intent());
                safeIntent.setData(data);
                safeIntent.setClassName(getPackageName(), ComponentInfo.PluginPay_A_101);
                tei.eYV_(this, safeIntent);
                finish();
                return true;
            }
        }
        return false;
    }

    private void c() {
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        this.d = weakHashMap;
        weakHashMap.put(ComponentInfo.PluginPay_A_258, ComponentInfo.PluginPay_A_47);
        this.d.put("com.huawei.wear.wallet.action.HOUSEHOLD_MATCH", ComponentInfo.PluginPay_A_262);
        this.d.put("com.huawei.wear.wallet.action.ADD_CARD", ComponentInfo.PluginPay_A_48);
        SafeIntent safeIntent = new SafeIntent(getIntent());
        this.b = safeIntent;
        this.f11216a = safeIntent.getAction();
        stq.c("ThirdInvokeActivity", "initParams action=" + this.f11216a, false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
