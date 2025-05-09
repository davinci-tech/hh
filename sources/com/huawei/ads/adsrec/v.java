package com.huawei.ads.adsrec;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.vh;
import defpackage.vy;
import defpackage.wa;
import java.util.Set;

/* loaded from: classes2.dex */
public class v implements e0 {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1686a;
    private final vy d = vy.b();

    @Override // com.huawei.ads.adsrec.e0
    public void a(String str, Set<String> set) {
        if (TextUtils.isEmpty(str) || set == null) {
            return;
        }
        wa c = this.d.c(this.f1686a, str);
        c.e(set);
        this.d.a(c);
    }

    @Override // com.huawei.ads.adsrec.e0
    public void a(vh vhVar) {
        HiAdLog.i("DeduplicateTask", "start to distinct");
        if (vhVar == null || TextUtils.isEmpty(vhVar.d())) {
            return;
        }
        wa c = this.d.c(this.f1686a, vhVar.d());
        c.b(vhVar.j());
        this.d.a(c);
        HiAdLog.i("DeduplicateTask", "end to distinct");
    }

    @Override // com.huawei.ads.adsrec.e0
    public void a() {
        this.d.e();
    }

    public v(Context context) {
        this.f1686a = context;
    }
}
