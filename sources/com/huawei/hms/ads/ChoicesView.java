package com.huawei.hms.ads;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.rr;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;

/* loaded from: classes4.dex */
public class ChoicesView extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    private dk f4282a;

    public void setAdChoiceIcon(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ho.a("ChoicesView", "updateIcon from server.");
        m.d(new AnonymousClass1(str));
    }

    public void b() {
        ho.a("ChoicesView", "updateIcon from local.");
        setImageResource(R.drawable._2131428513_res_0x7f0b04a1);
    }

    public void a(int i) {
        ho.a("ChoicesView", "changeChoiceViewSize dp = %d", Integer.valueOf(i));
        Resources resources = getContext().getResources();
        setLayoutParams(new RelativeLayout.LayoutParams(resources.getDimensionPixelSize(i), resources.getDimensionPixelSize(i)));
    }

    public void a() {
        setImageResource(R.drawable._2131428514_res_0x7f0b04a2);
    }

    private void a(Context context) {
        Resources resources = getContext().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131363283_res_0x7f0a05d3);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen._2131363283_res_0x7f0a05d3);
        ho.a("ChoicesView", "adChoiceViewWidth = %d", Integer.valueOf(dimensionPixelSize));
        setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize2));
        setImageResource(R.drawable._2131428553_res_0x7f0b04c9);
        this.f4282a = dh.a(context, "normal");
    }

    public ChoicesView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        a(context);
    }

    /* renamed from: com.huawei.hms.ads.ChoicesView$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f4283a;

        @Override // java.lang.Runnable
        public void run() {
            rt rtVar = new rt();
            rtVar.b(false);
            rtVar.c(true);
            rtVar.a("icon");
            rtVar.c(this.f4283a);
            ru a2 = new rr(ChoicesView.this.getContext(), rtVar).a();
            if (a2 != null) {
                String a3 = a2.a();
                if (TextUtils.isEmpty(a3)) {
                    return;
                }
                String c = ChoicesView.this.f4282a.c(a3);
                if (TextUtils.isEmpty(c)) {
                    return;
                }
                az.a(ChoicesView.this.getContext(), c, new az.a() { // from class: com.huawei.hms.ads.ChoicesView.1.1
                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a(final Drawable drawable) {
                        if (drawable != null) {
                            dj.a(new Runnable() { // from class: com.huawei.hms.ads.ChoicesView.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ChoicesView.this.setImageDrawable(drawable);
                                }
                            });
                        }
                    }

                    @Override // com.huawei.openalliance.ad.utils.az.a
                    public void a() {
                        ho.a("ChoicesView", "download icon fail, use local icon");
                        dj.a(new Runnable() { // from class: com.huawei.hms.ads.ChoicesView.1.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                ChoicesView.this.b();
                            }
                        });
                    }
                });
            }
        }

        AnonymousClass1(String str) {
            this.f4283a = str;
        }
    }

    public ChoicesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public ChoicesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ChoicesView(Context context) {
        super(context, null);
        a(context);
    }
}
