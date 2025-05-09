package com.huawei.hwidauth.c;

import android.content.Context;
import defpackage.ksi;
import java.io.IOException;

/* loaded from: classes9.dex */
public class i extends k {
    private Context e;

    public i(Context context) {
        this.e = context;
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return "/oauth2/v3/x509?client_id=104589299&hms_version=" + ksi.c(this.e) + "&sdkVersion=6.12.0.302";
    }

    @Override // com.huawei.hwidauth.c.k
    public String c() {
        return "";
    }

    @Override // com.huawei.hwidauth.c.k
    public String a_() {
        return "";
    }

    @Override // com.huawei.hwidauth.c.k
    public String a() throws IllegalArgumentException, IllegalStateException, IOException {
        return "";
    }
}
