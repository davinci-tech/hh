package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.alipay.sdk.m.j.d;
import com.alipay.sdk.m.x.c;
import com.huawei.haf.language.LanguageInstallHelper;
import defpackage.kh;
import defpackage.kl;
import defpackage.kr;
import defpackage.lv;
import defpackage.ma;
import defpackage.md;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class H5PayActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    public String f855a;
    public String b;
    public c c;
    public String e;
    public String f;
    public WeakReference<lv> g;
    public String h;
    public boolean j;

    private void a() {
        try {
            super.requestWindowFeature(1);
            getWindow().addFlags(8192);
        } catch (Throwable th) {
            ma.c(th);
        }
    }

    public void e() {
        Object obj = PayTask.h;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.app.Activity
    public void finish() {
        e();
        super.finish();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1010) {
            d.aS_((lv) md.b(this.g), i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        c cVar = this.c;
        if (cVar == null) {
            finish();
            return;
        }
        if (cVar.a()) {
            cVar.b();
            return;
        }
        if (!cVar.b()) {
            super.onBackPressed();
        }
        kh.a(kh.a());
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        a();
        super.onCreate(bundle);
        try {
            lv a2 = lv.e.a(getIntent());
            if (a2 == null) {
                finish();
                return;
            }
            this.g = new WeakReference<>(a2);
            if (kr.a().ac()) {
                setRequestedOrientation(3);
            } else {
                setRequestedOrientation(1);
            }
            try {
                Bundle extras = getIntent().getExtras();
                String string = extras.getString("url", null);
                this.f855a = string;
                if (!md.g(string)) {
                    finish();
                    return;
                }
                this.e = extras.getString("cookie", null);
                this.b = extras.getString("method", null);
                this.f = extras.getString("title", null);
                this.h = extras.getString("version", "v1");
                this.j = extras.getBoolean("backisexit", false);
                try {
                    com.alipay.sdk.m.x.d dVar = new com.alipay.sdk.m.x.d(this, a2, this.h);
                    setContentView(dVar);
                    dVar.d(this.f, this.b, this.j);
                    dVar.a(this.f855a, this.e);
                    dVar.a(this.f855a);
                    this.c = dVar;
                } catch (Throwable th) {
                    kl.e(a2, "biz", "GetInstalledAppEx", th);
                    finish();
                }
            } catch (Exception unused) {
                finish();
            }
        } catch (Exception unused2) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c cVar = this.c;
        if (cVar != null) {
            cVar.c();
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        try {
            super.setRequestedOrientation(i);
        } catch (Throwable th) {
            try {
                kl.e((lv) md.b(this.g), "biz", "H5PayDataAnalysisError", th);
            } catch (Throwable unused) {
            }
        }
    }
}
