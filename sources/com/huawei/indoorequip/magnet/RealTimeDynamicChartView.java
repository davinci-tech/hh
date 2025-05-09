package com.huawei.indoorequip.magnet;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dynamicchart.DynamicChart;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.LogAnonymous;

/* loaded from: classes5.dex */
public class RealTimeDynamicChartView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f6430a;
    private DynamicChart b;
    private int c;
    private boolean d;
    private boolean e;
    private int f;
    private int g;
    private int h;
    private Drawable i;
    private boolean j;
    private Drawable k;
    private int l;
    private ImageView m;
    private int n;
    private float o;
    private HealthTextView p;
    private String q;
    private String r;
    private HealthTextView s;
    private ImageView t;
    private String v;
    private HealthTextView x;

    public RealTimeDynamicChartView(Context context) {
        this(context, null);
        c();
    }

    public RealTimeDynamicChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        c();
    }

    public RealTimeDynamicChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099857_res_0x7f0600d1, R.attr._2131099858_res_0x7f0600d2, R.attr._2131099859_res_0x7f0600d3, R.attr._2131099860_res_0x7f0600d4, R.attr._2131100696_res_0x7f060418, R.attr._2131100697_res_0x7f060419, R.attr._2131100893_res_0x7f0604dd, R.attr._2131101003_res_0x7f06054b, R.attr._2131101039_res_0x7f06056f, R.attr._2131101116_res_0x7f0605bc, R.attr._2131101117_res_0x7f0605bd, R.attr._2131101289_res_0x7f060669, R.attr._2131101290_res_0x7f06066a, R.attr._2131101297_res_0x7f060671, R.attr._2131101338_res_0x7f06069a, R.attr._2131101344_res_0x7f0606a0}, i, R.style.Health_Indoor_RealTimeDataView);
        if (obtainStyledAttributes != null) {
            try {
                this.f6430a = obtainStyledAttributes.getColor(0, getContext().getResources().getColor(R.color._2131298994_res_0x7f090ab2));
                this.g = obtainStyledAttributes.getColor(5, getContext().getResources().getColor(R.color._2131298993_res_0x7f090ab1));
                this.l = obtainStyledAttributes.getColor(6, getContext().getResources().getColor(R.color._2131298998_res_0x7f090ab6));
                this.n = obtainStyledAttributes.getColor(10, getContext().getResources().getColor(R.color._2131299000_res_0x7f090ab8));
                this.k = obtainStyledAttributes.getDrawable(9);
                this.i = obtainStyledAttributes.getDrawable(4);
                this.h = obtainStyledAttributes.getInt(2, 100);
                this.f = obtainStyledAttributes.getInt(3, 0);
                this.c = obtainStyledAttributes.getInt(1, 180);
                this.d = obtainStyledAttributes.getBoolean(7, false);
                this.o = obtainStyledAttributes.getFloat(8, 1.0f);
                this.j = obtainStyledAttributes.getBoolean(12, false);
                this.q = obtainStyledAttributes.getString(13);
                this.v = obtainStyledAttributes.getString(15);
                this.r = obtainStyledAttributes.getString(14);
            } catch (Resources.NotFoundException e) {
                LogUtil.b("BaseRealTimeDataView", "Resources not found ", LogAnonymous.b((Throwable) e));
            }
            obtainStyledAttributes.recycle();
        }
        c();
    }

    private void c() {
        inflate(getContext(), R.layout.layout_real_time_data_magenet, this);
        this.p = (HealthTextView) findViewById(R.id.title);
        this.x = (HealthTextView) findViewById(R.id.value);
        this.s = (HealthTextView) findViewById(R.id.unit);
        this.m = (ImageView) findViewById(R.id.remind_arrow);
        this.t = (ImageView) findViewById(R.id.title_icon);
        this.b = (DynamicChart) findViewById(R.id.dynamic_chart);
        g();
        i();
        setColor();
        if (this.j) {
            this.t.setVisibility(0);
        }
        this.b.setPointCountOneScreen(this.c);
        b();
        this.e = true;
    }

    private void b() {
        HealthTextView healthTextView = this.x;
        healthTextView.setTextSize(0, this.o * healthTextView.getTextSize());
        HealthTextView healthTextView2 = this.s;
        healthTextView2.setTextSize(0, this.o * healthTextView2.getTextSize());
        HealthTextView healthTextView3 = this.p;
        healthTextView3.setTextSize(0, this.o * healthTextView3.getTextSize());
    }

    public void setTitle(int i) {
        this.q = BaseApplication.getContext().getResources().getString(i);
        g();
    }

    private void g() {
        this.p.setText(this.q);
    }

    public void setTitle(String str) {
        this.q = str;
        g();
    }

    public void setValue(String str) {
        this.v = str;
        i();
    }

    private void i() {
        this.x.setText(this.v);
    }

    public void a(float f) {
        int i = this.h;
        float f2 = (i - r1) * 0.005f;
        float f3 = this.f + f2;
        if (f < f3) {
            f = f3;
        }
        float f4 = i - f2;
        if (f > f4) {
            f = f4;
        }
        this.b.a(f);
    }

    public void setScale(float f) {
        if (this.e) {
            this.o = f;
            HealthTextView healthTextView = this.x;
            healthTextView.setTextSize(0, healthTextView.getTextSize() * f);
            HealthTextView healthTextView2 = this.s;
            healthTextView2.setTextSize(0, healthTextView2.getTextSize() * f);
            HealthTextView healthTextView3 = this.p;
            healthTextView3.setTextSize(0, healthTextView3.getTextSize() * f);
            if (this.m.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
                layoutParams.width = (int) (nsn.c(getContext(), 5.0f) * f);
                layoutParams.setMarginStart(((int) f) * layoutParams.getMarginStart());
                this.m.setLayoutParams(layoutParams);
            }
            if (this.t.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.t.getLayoutParams();
                layoutParams2.width = (int) (nsn.c(getContext(), 16.0f) * f);
                layoutParams2.height = (int) (nsn.c(getContext(), 16.0f) * f);
                layoutParams2.setMarginStart(((int) f) * layoutParams2.getMarginStart());
                this.t.setLayoutParams(layoutParams2);
            }
        }
    }

    public void setOrdinateY(int i, int i2) {
        this.f = i;
        this.h = i2;
        this.b.setOrdinateY(i, i2);
    }

    public void setColor() {
        this.b.setColor(this.f6430a);
    }

    public void a() {
        this.m.setVisibility(4);
        this.x.setTextColor(this.l);
        this.s.setTextColor(this.l);
    }

    public void d() {
        this.m.setVisibility(0);
        this.m.setBackground(nrf.cJH_(this.k, this.n));
        this.x.setTextColor(this.n);
        this.s.setTextColor(this.n);
    }

    public void e() {
        this.m.setVisibility(0);
        if (this.d) {
            this.m.setBackground(nrf.cJH_(this.i, this.g));
        } else {
            this.m.setBackground(this.i);
        }
        this.x.setTextColor(this.g);
        this.s.setTextColor(this.g);
    }

    public int getTitleLength() {
        HealthTextView healthTextView = this.p;
        if (healthTextView == null || healthTextView.getText() == null) {
            return 0;
        }
        return this.p.getText().length();
    }

    public void setSmallTextSize() {
        this.p.setTextSize(1, this.o * 12.0f);
        this.s.setTextSize(1, this.o * 9.0f);
        this.x.setTextSize(1, this.o * 20.0f);
        if (this.t.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.t.getLayoutParams();
            layoutParams.width = (int) (this.o * nsn.c(getContext(), 12.0f));
            layoutParams.height = (int) (this.o * nsn.c(getContext(), 12.0f));
            layoutParams.setMarginStart(((int) this.o) * layoutParams.getMarginStart());
            this.t.setLayoutParams(layoutParams);
        }
    }

    public void setNormalTextSize() {
        this.p.setTextSize(1, this.o * 12.0f);
        this.s.setTextSize(1, this.o * 11.0f);
        this.x.setTextSize(1, this.o * 20.0f);
        if (this.t.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.t.getLayoutParams();
            layoutParams.width = (int) (this.o * nsn.c(getContext(), 12.0f));
            layoutParams.height = (int) (this.o * nsn.c(getContext(), 12.0f));
            layoutParams.setMarginStart(((int) this.o) * layoutParams.getMarginStart());
            this.t.setLayoutParams(layoutParams);
        }
    }
}
