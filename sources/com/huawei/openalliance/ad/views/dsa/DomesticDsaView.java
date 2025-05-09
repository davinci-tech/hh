package com.huawei.openalliance.ad.views.dsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.activity.SplashFeedbackActivity;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.hi;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.d;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.views.h;

/* loaded from: classes5.dex */
public class DomesticDsaView extends h {
    protected Boolean k;
    private RelativeLayout l;
    private TextView m;
    private View n;
    private RelativeLayout o;
    private TextView p;
    private ContentRecord q;
    private Integer r;
    private jb s;
    private hi t;

    public void setDsaJumpListener(hi hiVar) {
        this.t = hiVar;
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void setAdContent(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        this.q = contentRecord;
        a(this.t);
        f();
        c();
        g();
    }

    protected int getLayoutId() {
        return x.t(getContext()) ? R.layout.hiad_domestic_dsa_view_tv : R.layout.hiad_domestic_dsa_view;
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void d(Context context) {
        if (x.t(getContext())) {
            h();
        } else {
            super.d(context);
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void c(Context context) {
        this.d = (x.n(context) || (x.q(context) && x.r(context))) ? 0.4f : x.t(context) ? 0.18f : 0.56f;
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void c() {
        try {
            ho.b("DomesticDsaView", "adapterView mFeedbackViewPaddingLeft = %s, mFeedbackViewPaddingRight= %s", Integer.valueOf(this.g), Integer.valueOf(this.h));
            if (d()) {
                this.b.setPadding(this.g, 0, this.h, 0);
                this.b.requestLayout();
                this.b.getViewTreeObserver().addOnGlobalLayoutListener(this.j);
            }
        } catch (Throwable th) {
            ho.c("DomesticDsaView", "adapterView error, %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void b(Context context) {
        if (dd.c()) {
            Bitmap b = az.b(getResources().getDrawable(R.drawable._2131428545_res_0x7f0b04c1));
            ImageView imageView = (ImageView) findViewById(R.id.why_this_ad_right_arrow);
            if (imageView != null) {
                imageView.setImageBitmap(b);
            }
            ImageView imageView2 = (ImageView) findViewById(R.id.splash_feedback_right_arrow);
            if (imageView2 != null) {
                imageView2.setImageBitmap(b);
            }
        }
    }

    public void b() {
        RelativeLayout relativeLayout = this.o;
        if (relativeLayout == null || !relativeLayout.getGlobalVisibleRect(new Rect())) {
            return;
        }
        this.o.setPressed(false);
        this.o.performClick();
    }

    public void a(boolean z, Integer num, jb jbVar) {
        this.k = Boolean.valueOf(z);
        this.r = num;
        this.s = jbVar;
    }

    @Override // com.huawei.openalliance.ad.views.h
    public void a(Context context) {
        try {
            View inflate = LayoutInflater.from(context).inflate(getLayoutId(), this);
            if (inflate == null) {
                return;
            }
            this.b = inflate.findViewById(R.id.dom_dsa_view_root);
            this.c = inflate.findViewById(R.id.dsa_scrollview);
            this.n = inflate.findViewById(R.id.splash_feedback_line);
            this.l = (RelativeLayout) inflate.findViewById(R.id.splash_feedback_btn);
            this.m = (TextView) inflate.findViewById(R.id.splash_feedback_tv);
            this.o = (RelativeLayout) inflate.findViewById(R.id.why_this_ad_btn);
            this.p = (TextView) inflate.findViewById(R.id.why_this_ad_tv);
        } catch (Throwable th) {
            ho.c("DomesticDsaView", "initView error, %s", th.getClass().getSimpleName());
        }
    }

    public void a() {
        RelativeLayout relativeLayout = this.o;
        if (relativeLayout == null || !relativeLayout.getGlobalVisibleRect(new Rect())) {
            return;
        }
        this.o.setPressed(true);
    }

    private void h() {
        if (this.c != null) {
            this.c.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            float measuredWidth = this.c.getMeasuredWidth();
            this.d = measuredWidth / d.b(getContext());
            this.f = (int) measuredWidth;
            ho.a("DomesticDsaView", "fitTvViewWidth: viewWidthPercent: %s, mViewWidth: %s", Float.valueOf(this.d), Integer.valueOf(this.f));
        }
    }

    private void g() {
        if (ao.n(getContext())) {
            TextView textView = this.m;
            if (textView != null) {
                textView.setTextSize(1, 28.0f);
            }
            TextView textView2 = this.p;
            if (textView2 != null) {
                textView2.setTextSize(1, 28.0f);
            }
        }
    }

    private void f() {
        String str;
        if (this.n == null || this.l == null) {
            str = "partingLine or splashFeedbackClick view not init";
        } else {
            Boolean bool = this.k;
            if (bool != null && bool.booleanValue()) {
                this.n.setVisibility(0);
                this.l.setVisibility(0);
                this.l.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.dsa.DomesticDsaView.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        new com.huawei.openalliance.ad.analysis.h(DomesticDsaView.this.getContext().getApplicationContext()).a("145", "", "", "");
                        Intent intent = new Intent(DomesticDsaView.this.getContext(), (Class<?>) SplashFeedbackActivity.class);
                        intent.setPackage(DomesticDsaView.this.getContext().getPackageName());
                        intent.putExtra(MapKeyNames.SPLASH_CLICKABLE_TYPE, DomesticDsaView.this.r);
                        if (!(DomesticDsaView.this.getContext() instanceof Activity)) {
                            intent.addFlags(268435456);
                        }
                        dd.a(DomesticDsaView.this.getContext(), intent);
                        if (DomesticDsaView.this.s != null) {
                            DomesticDsaView.this.s.f();
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                if (ao.a(this.q.bc(), this.q.bb())) {
                    return;
                }
                this.o.setVisibility(8);
                this.n.setVisibility(8);
                return;
            }
            str = "not need show splash feedback";
        }
        ho.b("DomesticDsaView", str);
    }

    private void a(final hi hiVar) {
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.dsa.DomesticDsaView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean a2 = ao.a(DomesticDsaView.this.getContext(), DomesticDsaView.this.q);
                ho.a("DomesticDsaView", "jump to dsa page: %s", Boolean.valueOf(a2));
                if (a2) {
                    hi hiVar2 = hiVar;
                    if (hiVar2 != null) {
                        hiVar2.a();
                    }
                    if (DomesticDsaView.this.s != null) {
                        DomesticDsaView.this.s.e();
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public DomesticDsaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public DomesticDsaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DomesticDsaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DomesticDsaView(Context context) {
        super(context);
    }
}
