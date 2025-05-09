package com.huawei.hms.hatool;

import android.content.Context;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    s0 f4578a;
    s0 b;
    Context c;
    String d;

    @Deprecated
    public b c(boolean z) {
        v.a("hmsSdk", "Builder.setEnableUDID(boolean isReportUDID) is execute.");
        this.f4578a.j().c(z);
        this.b.j().c(z);
        return this;
    }

    @Deprecated
    public b b(boolean z) {
        v.a("hmsSdk", "Builder.setEnableSN(boolean isReportSN) is execute.");
        this.f4578a.j().b(z);
        this.b.j().b(z);
        return this;
    }

    public void a() {
        if (this.c == null) {
            v.b("hmsSdk", "analyticsConf create(): context is null,create failed!");
            return;
        }
        v.a("hmsSdk", "Builder.create() is execute.");
        z0 z0Var = new z0("_hms_config_tag");
        z0Var.b(new s0(this.f4578a));
        z0Var.a(new s0(this.b));
        m.a().a(this.c);
        g0.a().a(this.c);
        q.c().a(z0Var);
        m.a().a(this.d);
    }

    @Deprecated
    public b a(boolean z) {
        v.a("hmsSdk", "Builder.setEnableImei(boolean isReportAndroidImei) is execute.");
        this.f4578a.j().a(z);
        this.b.j().a(z);
        return this;
    }

    public b a(String str) {
        v.a("hmsSdk", "Builder.setAppID is execute");
        this.d = str;
        return this;
    }

    public b a(int i, String str) {
        s0 s0Var;
        v.a("hmsSdk", "Builder.setCollectURL(int type,String collectURL) is execute.TYPE : " + i);
        if (!p1.b(str)) {
            str = "";
        }
        if (i == 0) {
            s0Var = this.f4578a;
        } else {
            if (i != 1) {
                v.f("hmsSdk", "Builder.setCollectURL(int type,String collectURL): invalid type!");
                return this;
            }
            s0Var = this.b;
        }
        s0Var.b(str);
        return this;
    }

    public b(Context context) {
        if (context != null) {
            this.c = context.getApplicationContext();
        }
        this.f4578a = new s0();
        this.b = new s0();
    }
}
