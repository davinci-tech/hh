package com.huawei.openalliance.ad.linked.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    protected C0197a f7167a = new C0197a();

    /* renamed from: com.huawei.openalliance.ad.linked.view.a$a, reason: collision with other inner class name */
    public static class C0197a {

        /* renamed from: a, reason: collision with root package name */
        protected Drawable f7168a;
        protected int b;
        protected int c = 12;
    }

    public C0197a a() {
        return this.f7167a;
    }

    public a(Context context) {
        C0197a c0197a;
        Resources resources;
        int i;
        if (bz.a(context).g()) {
            c0197a = this.f7167a;
            resources = context.getResources();
            i = R.drawable._2131428540_res_0x7f0b04bc;
        } else {
            c0197a = this.f7167a;
            resources = context.getResources();
            i = R.drawable._2131428538_res_0x7f0b04ba;
        }
        c0197a.f7168a = resources.getDrawable(i);
        this.f7167a.b = context.getResources().getColor(R.color._2131297961_res_0x7f0906a9);
    }
}
