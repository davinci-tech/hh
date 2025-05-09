package com.huawei.hwcloudjs.d.a;

import android.content.Context;
import com.huawei.hwcloudjs.d.a.c;
import com.huawei.hwcloudjs.service.auth.bean.AuthBean;
import java.util.List;

/* loaded from: classes5.dex */
class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f6203a;
    final /* synthetic */ String b;
    final /* synthetic */ List c;
    final /* synthetic */ c.a d;
    final /* synthetic */ Context e;
    final /* synthetic */ c f;

    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        aVar = this.f.f;
        AuthBean a2 = aVar.a(this.f6203a);
        com.huawei.hwcloudjs.f.d.c("AuthManager", "getValidCache bean", true);
        com.huawei.hwcloudjs.f.d.c("AuthManager", "getValidCache bean:" + a2, false);
        if (a2 == null || !d.a(this.b, this.c, a2)) {
            com.huawei.hwcloudjs.f.d.b("AuthManager", "checkH5App is null", true);
            this.f.b(this.f6203a, this.b, this.c, this.d, this.e);
        } else {
            com.huawei.hwcloudjs.f.d.c("AuthManager", "checkH5App is passed", true);
            this.d.a(0);
            this.f.a(this.f6203a, a2);
        }
    }

    b(c cVar, String str, String str2, List list, c.a aVar, Context context) {
        this.f = cVar;
        this.f6203a = str;
        this.b = str2;
        this.c = list;
        this.d = aVar;
        this.e = context;
    }
}
