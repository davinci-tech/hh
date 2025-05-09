package com.huawei.hwcloudjs.e.a;

import android.content.Context;

/* loaded from: classes9.dex */
final class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String[] f6219a;
    final /* synthetic */ Context b;
    final /* synthetic */ int c;

    @Override // java.lang.Runnable
    public void run() {
        for (String str : this.f6219a) {
            if (c.d().d(str) == null) {
                com.huawei.hwcloudjs.f.d.c("CacheRequestManager", "enter into preload requestBean " + str, false);
                com.huawei.hwcloudjs.e.a.a.b bVar = (com.huawei.hwcloudjs.e.a.a.b) com.huawei.hwcloudjs.d.b.b.a(new com.huawei.hwcloudjs.e.a.a.a(str), this.b);
                if (bVar.c() == 1) {
                    com.huawei.hwcloudjs.f.d.c("CacheRequestManager", "getcache url success:" + str, false);
                    c.d().a(str, new a(str, bVar.d(), bVar.d().length(), this.c));
                } else {
                    com.huawei.hwcloudjs.f.d.b("CacheRequestManager", "getcache url failed!", true);
                }
            }
        }
    }

    d(String[] strArr, Context context, int i) {
        this.f6219a = strArr;
        this.b = context;
        this.c = i;
    }
}
