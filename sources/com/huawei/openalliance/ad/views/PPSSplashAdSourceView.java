package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.LabelPosition;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes9.dex */
public class PPSSplashAdSourceView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private PPSSplashLabelView f7956a;
    private TextView b;
    private Integer c;
    private Integer d;
    private WeakReference<jk> e;
    private boolean f;
    private boolean g;

    protected int getRootLayoutId() {
        this.g = a(getContext(), this.c, this.d);
        return b() ? R.layout.hiad_splash_ad_source_with_click : R.layout.hiad_splash_ad_source;
    }

    public void a(jk jkVar, Integer num, Integer num2, boolean z) {
        ho.b("PPSSplashAdSourceView", "setAdLabelConfig %s %s %s", num, num2, Boolean.valueOf(z));
        this.e = new WeakReference<>(jkVar);
        this.c = num;
        this.d = num2;
        this.f = z;
    }

    public void a(ContentRecord contentRecord, boolean z, int i, int i2, boolean z2) {
        ho.b("PPSSplashAdSourceView", "positionAndSet");
        a(getContext());
        String P = contentRecord.P() == null ? LabelPosition.LOWER_LEFT : contentRecord.P();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131363379_res_0x7f0a0633);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen._2131363379_res_0x7f0a0633);
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if ("tr".equals(P)) {
                a(i, i2, z2, dimensionPixelSize, dimensionPixelSize2, layoutParams2);
            } else {
                a(z, i, i2, z2, dimensionPixelSize, dimensionPixelSize2, layoutParams2);
            }
            setLayoutParams(layoutParams2);
        }
        a(contentRecord);
        b(contentRecord);
    }

    public void a() {
        PPSSplashLabelView pPSSplashLabelView = this.f7956a;
        if (pPSSplashLabelView != null) {
            pPSSplashLabelView.b();
        }
    }

    private boolean b() {
        ho.b("PPSSplashAdSourceView", "isSplashClickable: %s, isShowTransparency: %s", Boolean.valueOf(this.g), Boolean.valueOf(this.f));
        return this.g || this.f;
    }

    private void b(ContentRecord contentRecord) {
        TextView textView;
        int i;
        MetaData h = contentRecord.h();
        if (h == null || this.b == null) {
            return;
        }
        String c = cz.c(h.k());
        if (TextUtils.isEmpty(c)) {
            textView = this.b;
            i = 8;
        } else {
            this.b.setText(c);
            textView = this.b;
            i = 0;
        }
        textView.setVisibility(i);
    }

    public static boolean a(Context context, Integer num, Integer num2) {
        if (!bz.a(context).d() || num == null || num2 == null) {
            return false;
        }
        return ((num.intValue() == 1 || num.intValue() == 4) && (num2.intValue() == 2 || num2.intValue() == 3)) || ((num.intValue() == 2 || num.intValue() == 3) && (num2.intValue() == 1 || num2.intValue() == 3));
    }

    private void a(boolean z, int i, int i2, boolean z2, int i3, int i4, RelativeLayout.LayoutParams layoutParams) {
        layoutParams.addRule(12);
        layoutParams.addRule(20);
        layoutParams.leftMargin = i3;
        layoutParams.setMarginStart(i3);
        layoutParams.bottomMargin = i4;
        if (i2 == 0) {
            if (bz.b(getContext()) && z2) {
                layoutParams.setMarginStart(layoutParams.leftMargin + i);
                layoutParams.leftMargin += i;
            } else if (!bz.b(getContext()) || (bz.b(getContext()) && TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1)) {
                layoutParams.setMarginStart(dd.f(getContext()));
                layoutParams.leftMargin = dd.f(getContext());
            }
            if (z) {
                return;
            }
            if (!com.huawei.openalliance.ad.utils.x.n(getContext()) && !com.huawei.openalliance.ad.utils.x.q(getContext())) {
                return;
            }
        } else if (z) {
            return;
        }
        layoutParams.bottomMargin += dd.f(getContext());
    }

    private void a(ContentRecord contentRecord) {
        PPSSplashLabelView pPSSplashLabelView;
        int i;
        String N = contentRecord.N();
        MetaData h = contentRecord.h();
        AdSource a2 = (h == null || h.K() == null) ? null : AdSource.a(h.K());
        this.f7956a.setDataAndRefreshUi(contentRecord);
        if (TextUtils.isEmpty(N) || !this.f7956a.a()) {
            pPSSplashLabelView = this.f7956a;
            i = 8;
        } else {
            PPSSplashLabelView pPSSplashLabelView2 = this.f7956a;
            WeakReference<jk> weakReference = this.e;
            pPSSplashLabelView2.a(weakReference != null ? weakReference.get() : null, contentRecord, this.f);
            this.f7956a.a(a2, N, this.c, this.g);
            pPSSplashLabelView = this.f7956a;
            i = 0;
        }
        pPSSplashLabelView.setVisibility(i);
    }

    private void a(Context context) {
        ho.b("PPSSplashAdSourceView", "init");
        inflate(context, getRootLayoutId(), this);
        PPSSplashLabelView pPSSplashLabelView = (PPSSplashLabelView) findViewById(R.id.hiad_ad_label);
        this.f7956a = pPSSplashLabelView;
        pPSSplashLabelView.setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.hiad_ad_source);
        this.b = textView;
        textView.setVisibility(8);
    }

    private void a(int i, int i2, boolean z, int i3, int i4, RelativeLayout.LayoutParams layoutParams) {
        int f;
        layoutParams.addRule(10);
        layoutParams.addRule(21);
        layoutParams.rightMargin = i3;
        layoutParams.setMarginEnd(i3);
        layoutParams.topMargin = i4;
        if (i2 != 0) {
            layoutParams.topMargin += i;
            return;
        }
        if (!z) {
            layoutParams.setMarginEnd(layoutParams.rightMargin + i);
            layoutParams.rightMargin += i;
        }
        if (bz.b(getContext())) {
            layoutParams.setMarginEnd(layoutParams.rightMargin + dd.f(getContext()));
            f = layoutParams.rightMargin + dd.f(getContext());
        } else {
            layoutParams.setMarginEnd(dd.f(getContext()));
            f = dd.f(getContext());
        }
        layoutParams.rightMargin = f;
        layoutParams.topMargin += ao.a(getContext(), 12.0f);
    }

    public PPSSplashAdSourceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.f = false;
        this.g = false;
    }

    public PPSSplashAdSourceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = false;
        this.g = false;
    }

    public PPSSplashAdSourceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = false;
        this.g = false;
    }

    public PPSSplashAdSourceView(Context context) {
        super(context, null);
        this.f = false;
        this.g = false;
    }
}
