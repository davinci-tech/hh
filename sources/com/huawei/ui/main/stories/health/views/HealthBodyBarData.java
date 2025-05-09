package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.HealthBodyBarView;
import defpackage.ntg;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class HealthBodyBarData extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10263a;
    private HealthTextView aa;
    private HealthBodyBarView ab;
    private View ac;
    private HealthTextView ad;
    private View ae;
    private HealthBodyBarView af;
    private HealthTextView b;
    private HealthTextView c;
    private HealthBodyBarView d;
    private HealthBodyBarView e;
    private HealthTextView f;
    private HealthBodyBarView g;
    private View h;
    private HealthTextView i;
    private Context j;
    private int k;
    private HealthBodyBarView l;
    private View m;
    private HealthTextView n;
    private HealthBodyBarView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthBodyBarView r;
    private HealthTextView s;
    private View t;
    private HealthBodyBarView u;
    private HealthBodyBarView v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthTextView y;
    private HealthTextView z;

    public HealthBodyBarData(Context context) {
        this(context, null);
        d(context);
    }

    public HealthBodyBarData(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet == null) {
            LogUtil.h("CommonUI_HealthBodyBarData", "HealthBodyDetailData AttributeSet is null");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099767_res_0x7f060077});
        try {
            this.k = obtainStyledAttributes.getInteger(0, 0);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("CommonUI_HealthBodyBarData", "HealthBodyBarData Resources NotFoundException.");
        }
        obtainStyledAttributes.recycle();
        d(context);
    }

    private void d(Context context) {
        View inflate;
        if (context == null) {
            LogUtil.h("CommonUI_HealthBodyBarData", "initBodyBarView Context is null");
            return;
        }
        this.j = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("CommonUI_HealthBodyBarData", "initBodyBarView LayoutInflater is null");
            return;
        }
        if (this.k == 0) {
            inflate = layoutInflater.inflate(R.layout.health_body_bar_view, this);
        } else {
            inflate = layoutInflater.inflate(R.layout.health_body_bar_view_report, this);
        }
        dIp_(inflate);
    }

    private void dIp_(View view) {
        this.h = view.findViewById(R.id.bmi_include_content_layout);
        this.m = view.findViewById(R.id.fat_include_content_layout);
        this.ae = view.findViewById(R.id.visceral_fat_include_content_layout);
        this.ac = view.findViewById(R.id.muscle_include_content_layout);
        this.t = view.findViewById(R.id.limb_include_content_layout);
        this.d = (HealthBodyBarView) this.h.findViewById(R.id.targetBarView);
        HealthBodyBarView healthBodyBarView = (HealthBodyBarView) this.m.findViewById(R.id.targetBarView);
        this.g = healthBodyBarView;
        healthBodyBarView.setFatData(true);
        this.ab = (HealthBodyBarView) this.ae.findViewById(R.id.targetBarView);
        this.v = (HealthBodyBarView) this.ac.findViewById(R.id.targetBarView);
        this.l = (HealthBodyBarView) this.t.findViewById(R.id.targetBarView);
        this.e = (HealthBodyBarView) this.h.findViewById(R.id.targetBarViewGray);
        HealthBodyBarView healthBodyBarView2 = (HealthBodyBarView) this.m.findViewById(R.id.targetBarViewGray);
        this.o = healthBodyBarView2;
        healthBodyBarView2.setFatData(true);
        this.af = (HealthBodyBarView) this.ae.findViewById(R.id.targetBarViewGray);
        this.u = (HealthBodyBarView) this.ac.findViewById(R.id.targetBarViewGray);
        this.r = (HealthBodyBarView) this.t.findViewById(R.id.targetBarViewGray);
        this.c = (HealthTextView) this.h.findViewById(R.id.indicate_name);
        this.i = (HealthTextView) this.m.findViewById(R.id.indicate_name);
        this.aa = (HealthTextView) this.ae.findViewById(R.id.indicate_name);
        this.x = (HealthTextView) this.ac.findViewById(R.id.indicate_name);
        this.s = (HealthTextView) this.t.findViewById(R.id.indicate_name);
        this.f10263a = (HealthTextView) this.h.findViewById(R.id.indicate_unit);
        this.n = (HealthTextView) this.m.findViewById(R.id.indicate_unit);
        this.ad = (HealthTextView) this.ae.findViewById(R.id.indicate_unit);
        this.w = (HealthTextView) this.ac.findViewById(R.id.indicate_unit);
        this.p = (HealthTextView) this.t.findViewById(R.id.indicate_unit);
        this.b = (HealthTextView) this.h.findViewById(R.id.indicate_content);
        this.f = (HealthTextView) this.m.findViewById(R.id.indicate_content);
        this.z = (HealthTextView) this.ae.findViewById(R.id.indicate_content);
        this.y = (HealthTextView) this.ac.findViewById(R.id.indicate_content);
        this.q = (HealthTextView) this.t.findViewById(R.id.indicate_content);
    }

    public void setBmiBar(ntg ntgVar) {
        if (ntgVar == null) {
            this.h.setVisibility(8);
            return;
        }
        this.d.setBarData(ntgVar, 0);
        this.e.setBarData(ntgVar, 1);
        c(this.c, this.f10263a, ntgVar.i(), ntgVar.f());
        this.b.setText(ntgVar.d());
        this.h.setVisibility(0);
    }

    public void setFatBar(ntg ntgVar) {
        if (ntgVar == null) {
            this.m.setVisibility(8);
            return;
        }
        this.g.setBarData(ntgVar, 0);
        this.o.setBarData(ntgVar, 1);
        if (LanguageUtil.b(this.j)) {
            c(this.i, this.n, ntgVar.i(), "");
        } else {
            c(this.i, this.n, ntgVar.i(), ntgVar.f());
        }
        this.f.setText(ntgVar.d());
        this.m.setVisibility(0);
    }

    public void setVisceralBar(ntg ntgVar) {
        if (ntgVar == null) {
            this.ae.setVisibility(8);
            return;
        }
        this.ab.setBarData(ntgVar, 0);
        this.af.setBarData(ntgVar, 1);
        c(this.aa, this.ad, ntgVar.i(), ntgVar.f());
        this.z.setText(ntgVar.d());
        this.ae.setVisibility(0);
    }

    public void setMuscleBar(ntg ntgVar) {
        if (ntgVar == null) {
            this.ac.setVisibility(8);
            return;
        }
        this.v.setBarData(ntgVar, 0);
        this.u.setBarData(ntgVar, 1);
        c(this.x, this.w, ntgVar.i(), ntgVar.f());
        this.y.setText(ntgVar.d());
        this.ac.setVisibility(0);
    }

    public void setLimbBar(ntg ntgVar) {
        if (ntgVar == null) {
            this.t.setVisibility(8);
            return;
        }
        this.l.setBarData(ntgVar, 0);
        this.r.setBarData(ntgVar, 1);
        c(this.s, this.p, ntgVar.i(), ntgVar.f());
        this.q.setText(ntgVar.d());
        this.t.setVisibility(0);
    }

    private void c(HealthTextView healthTextView, HealthTextView healthTextView2, String str, String str2) {
        if (LanguageUtil.j(BaseApplication.getContext())) {
            healthTextView.setText(dIo_(str, str2));
            healthTextView2.setVisibility(8);
            return;
        }
        healthTextView.setSplittable(true);
        healthTextView.setText(str);
        if (TextUtils.isEmpty(str2)) {
            healthTextView2.setVisibility(8);
        } else {
            healthTextView2.setVisibility(0);
            healthTextView2.setText(str2);
        }
    }

    private SpannableString dIo_(String str, String str2) {
        int i;
        int i2;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (this.k != 0) {
            i = R.style.weight_indicate_name_text_report;
            i2 = R.style.weight_indicate_content_text_report;
        } else {
            i = R.style.weight_indicate_name_text;
            i2 = R.style.weight_indicate_content_text;
        }
        Context context = BaseApplication.getContext();
        SpannableString spannableString = new SpannableString(str + str2);
        spannableString.setSpan(new TextAppearanceSpan(context, i), 0, str.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan(context, i2), str.length(), spannableString.length(), 33);
        return spannableString;
    }
}
