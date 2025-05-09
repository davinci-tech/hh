package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes9.dex */
public class ip {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7071a = "ip";
    private String b;
    private int c;
    private ia d;
    private Context e;

    public boolean a(String str, String str2) {
        if (1 == this.c || com.huawei.openalliance.ad.utils.cz.b(this.b) || com.huawei.openalliance.ad.utils.dn.a(this.e, str2, this.b)) {
            return true;
        }
        ho.b(f7071a, "check sha256 failed! file path is : " + str2);
        this.d.b(str);
        return false;
    }

    public ip(Context context, String str, ia iaVar, int i) {
        this.b = str;
        this.c = i;
        this.d = iaVar;
        this.e = context.getApplicationContext();
    }
}
