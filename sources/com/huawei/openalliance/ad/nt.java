package com.huawei.openalliance.ad;

import android.content.Context;
import android.view.View;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class nt {

    /* renamed from: a, reason: collision with root package name */
    private Context f7338a;
    private ContentRecord b;
    private String c;
    private qq d;
    private cs e;
    private WeakReference<View> f;

    public boolean a(Long l, Integer num, Integer num2) {
        View view = this.f.get();
        if (view == null) {
            return false;
        }
        String e = com.huawei.openalliance.ad.utils.dd.e(view);
        a.C0207a c0207a = new a.C0207a();
        c0207a.a(l).a(num).b(num2).a(this.c).e(vd.b(view)).d(e);
        this.d.a(c0207a.a());
        return true;
    }

    public boolean a(Context context, MaterialClickInfo materialClickInfo) {
        ho.b("SplashIconViewPresenter", "onClick");
        ta a2 = sz.a(this.f7338a, this.b, new HashMap(0));
        if (!a2.a()) {
            return false;
        }
        b.a aVar = new b.a();
        aVar.b(a2.c()).a((Integer) 23).a(materialClickInfo).d(this.c);
        this.d.a(aVar.a());
        return true;
    }

    public void a(View view) {
        this.f = new WeakReference<>(view);
    }

    public void a(int i) {
        this.e.b(this.b, i);
    }

    public void a() {
        this.e.c(this.b);
    }

    public nt(Context context, ContentRecord contentRecord, String str) {
        this.f7338a = context.getApplicationContext();
        this.b = contentRecord;
        this.c = str;
        this.d = new ou(this.f7338a, new si(this.f7338a, 1), contentRecord);
        this.e = new com.huawei.openalliance.ad.analysis.c(this.f7338a);
    }
}
