package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import defpackage.kh;
import defpackage.kl;
import defpackage.lv;
import defpackage.ma;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: classes8.dex */
public final class PayResultActivity extends Activity {
    public static final HashMap<String, Object> b = new HashMap<>();
    public lv d = null;

    public static final class b {
        public static volatile String c;
        public static volatile String d;
    }

    public static final class c implements Runnable {
        public final /* synthetic */ Activity e;

        public c(Activity activity) {
            this.e = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.e.finish();
        }
    }

    public static void aO_(Activity activity, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        Intent intent = new Intent();
        try {
            intent.setPackage("hk.alipay.wallet");
            intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, "UTF-8") + "&orderSuffix=" + URLEncoder.encode(str2, "UTF-8") + "&packageName=" + URLEncoder.encode(str3, "UTF-8") + "&externalPkgName=" + URLEncoder.encode(str3, "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            ma.c(e);
        }
        if (activity != null) {
            try {
                activity.startActivity(intent);
            } catch (Throwable unused) {
                activity.finish();
            }
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            if (!TextUtils.isEmpty(intent.getStringExtra("orderSuffix"))) {
                b.d = intent.getStringExtra("phonecashier.pay.hash");
                String stringExtra = intent.getStringExtra("orderSuffix");
                String stringExtra2 = intent.getStringExtra("externalPkgName");
                lv a2 = lv.e.a(intent);
                this.d = a2;
                if (a2 == null) {
                    finish();
                }
                aO_(this, b.d, stringExtra, stringExtra2);
                a(this, 300);
                return;
            }
            if (this.d == null) {
                finish();
            }
            String stringExtra3 = intent.getStringExtra("phonecashier.pay.result");
            int intExtra = intent.getIntExtra("phonecashier.pay.resultOrderHash", 0);
            if (intExtra != 0 && TextUtils.equals(b.d, String.valueOf(intExtra))) {
                if (TextUtils.isEmpty(stringExtra3)) {
                    a(b.d);
                } else {
                    a(stringExtra3, b.d);
                }
                b.d = "";
                a(this, 300);
                return;
            }
            kl.c(this.d, "biz", "SchemePayWrongHashEx", "Expected " + b.d + ", got " + intExtra);
            a(b.d);
            a(this, 300);
        } catch (Throwable unused) {
            finish();
        }
    }

    public static void a(String str) {
        b.c = kh.a();
        a(b, str);
    }

    public static void a(String str, String str2) {
        b.c = str;
        a(b, str2);
    }

    public static void a(Activity activity, int i) {
        new Handler().postDelayed(new c(activity), i);
    }

    public static boolean a(HashMap<String, Object> hashMap, String str) {
        Object obj;
        if (hashMap == null || str == null || (obj = hashMap.get(str)) == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
