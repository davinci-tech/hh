package com.huawei.hwcloudjs.service.auth.bean;

import android.text.TextUtils;
import com.huawei.hwcloudjs.f.d;
import com.huawei.hwcloudjs.service.http.annotation.RequestField;

/* loaded from: classes9.dex */
public class a extends com.huawei.hwcloudjs.d.b.a.a<b> {
    private static final String b = "AuthRequestBean";

    @RequestField("appid")
    private String appId;

    @RequestField("nsp_svc")
    private String nspSvc = "nsp.scope.app.get";

    @RequestField("type")
    private String type = "9";

    public String i() {
        return this.type;
    }

    public String h() {
        return this.nspSvc;
    }

    public String g() {
        return this.appId;
    }

    @Override // com.huawei.hwcloudjs.d.b.a.b
    public String d() {
        String c = com.huawei.hwcloudjs.d.c.a.a.b().c();
        d.c(b, "genUrl url:" + c, false);
        if (TextUtils.isEmpty(c)) {
            d.b(b, "AuthRequest url is empty!", true);
        }
        return c;
    }

    public void c(String str) {
        this.type = str;
    }

    @Override // com.huawei.hwcloudjs.d.b.a.b
    public b c() {
        return new b();
    }

    public void b(String str) {
        this.nspSvc = str;
    }

    public void a(String str) {
        this.appId = str;
    }

    public a(String str) {
        this.appId = str;
    }

    public a() {
    }
}
