package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public class hp {

    /* renamed from: a, reason: collision with root package name */
    private static ht f6916a = hu.a();
    private String d;
    private int b = 4;
    private Integer c = null;
    private boolean e = false;

    public void b(int i, String str, String str2) {
        if (a(i)) {
            String str3 = "HiAdSDK." + str;
            f6916a.a(c(i, str3, str2), i, str3);
        }
    }

    public boolean a(int i) {
        Integer num = this.c;
        return num == null ? this.e && i >= this.b : this.e && i >= num.intValue();
    }

    public void a(String str, String str2) {
        f6916a.a(c(4, str, str2), 4, str);
    }

    public void a(Integer num) {
        this.c = num;
    }

    public void a(int i, String str, Throwable th) {
        if (th != null) {
            a(i);
        }
    }

    public void a(int i, String str, String str2, Throwable th) {
        if (th != null) {
            a(i);
        }
    }

    public void a(int i, String str, String str2) {
        this.b = i;
        this.d = str2;
        f6916a.a(str, "HiAdSDKLog");
        this.e = true;
    }

    private hv c(int i, String str, String str2) {
        hv hvVar = new hv(this.d, i, str);
        hvVar.a((hv) str2);
        return hvVar;
    }
}
