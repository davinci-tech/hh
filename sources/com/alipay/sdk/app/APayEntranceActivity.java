package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import defpackage.kh;
import defpackage.kl;
import defpackage.lv;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class APayEntranceActivity extends Activity {
    public static final ConcurrentHashMap<String, a> d = new ConcurrentHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    public lv f852a;
    public String c;
    public String e;

    public interface a {
        void a(String str);
    }

    @Override // android.app.Activity
    public void finish() {
        String str = this.e;
        kl.a(this.f852a, "biz", "BSAFinish", str + "|" + TextUtils.isEmpty(this.c));
        if (TextUtils.isEmpty(this.c)) {
            this.c = kh.a();
            lv lvVar = this.f852a;
            if (lvVar != null) {
                lvVar.e(true);
            }
        }
        if (str != null) {
            a remove = d.remove(str);
            if (remove != null) {
                remove.a(this.c);
            } else {
                kl.c(this.f852a, "wr", "refNull", "session=" + str);
            }
        }
        try {
            super.finish();
        } catch (Throwable th) {
            kl.e(this.f852a, "wr", "APStartFinish", th);
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        kl.a(this.f852a, "biz", "BSAOnAR", this.e + "|" + i + "," + i2);
        if (i == 1000) {
            if (intent != null) {
                try {
                    this.c = intent.getStringExtra("result");
                } catch (Throwable unused) {
                }
            }
            finish();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                return;
            }
            String string = extras.getString("ap_order_info");
            String string2 = extras.getString("ap_target_packagename");
            this.e = extras.getString("ap_session");
            String string3 = extras.getString("ap_local_info", "{}");
            if (!TextUtils.isEmpty(this.e)) {
                lv b = lv.e.b(this.e);
                this.f852a = b;
                kl.a(b, "biz", "BSAEntryCreate", this.e + "|" + SystemClock.elapsedRealtime());
            }
            Intent intent = new Intent();
            intent.putExtra("order_info", string);
            intent.putExtra("localInfo", string3);
            intent.setClassName(string2, "com.alipay.android.app.flybird.ui.window.FlyBirdWindowActivity");
            try {
                startActivityForResult(intent, 1000);
            } catch (Throwable th) {
                kl.e(this.f852a, "wr", "APStartEx", th);
                finish();
            }
            if (this.f852a != null) {
                Context applicationContext = getApplicationContext();
                lv lvVar = this.f852a;
                kl.c(applicationContext, lvVar, string, lvVar.b);
                this.f852a.a(true);
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
