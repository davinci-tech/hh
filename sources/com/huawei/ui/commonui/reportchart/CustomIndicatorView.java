package com.huawei.ui.commonui.reportchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes8.dex */
public class CustomIndicatorView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f8926a;
    private TextView b;
    private String c;
    private int d;
    private String e;
    private int f;
    private TextView g;
    private int h;
    private int i;
    private String j;
    private TextView k;
    private String l;
    private int m;
    private TextView n;
    private int o;

    public CustomIndicatorView(Context context) {
        super(context);
        a(context);
    }

    public CustomIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.customIndicatorView);
        cEP_(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        a(context);
    }

    private void a(Context context) {
        if (context == null) {
            LogUtil.h("CustomIndicatorView", "initView Context is null");
            return;
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("CustomIndicatorView", "initView layoutInflater is null");
            return;
        }
        View inflate = layoutInflater.inflate(R.layout.custom_indicator_view, this);
        this.g = (TextView) inflate.findViewById(R.id.indicator_title_text);
        this.k = (TextView) inflate.findViewById(R.id.indicator_value_text);
        this.n = (TextView) inflate.findViewById(R.id.indicator_unit_text);
        this.b = (TextView) inflate.findViewById(R.id.indicator_desc_text);
        e();
        b();
        a();
        c();
    }

    private void cEP_(TypedArray typedArray) {
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorTitleText)) {
            this.c = typedArray.getString(R$styleable.customIndicatorView_indicatorTitleText);
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorTitleColor)) {
            this.f = typedArray.getColor(R$styleable.customIndicatorView_indicatorTitleColor, getResources().getColor(R$color.textColorSecondary));
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorTitleVisibility)) {
            this.i = typedArray.getInteger(R$styleable.customIndicatorView_indicatorTitleVisibility, 8);
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorValueText)) {
            this.l = typedArray.getString(R$styleable.customIndicatorView_indicatorValueText);
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorValueColor)) {
            this.m = typedArray.getColor(R$styleable.customIndicatorView_indicatorValueColor, getResources().getColor(R$color.textColorPrimary));
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorUnitText)) {
            this.j = typedArray.getString(R$styleable.customIndicatorView_indicatorUnitText);
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorUnitColor)) {
            this.h = typedArray.getColor(R$styleable.customIndicatorView_indicatorUnitColor, getResources().getColor(R$color.textColorSecondary));
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorUnitVisibility)) {
            this.o = typedArray.getInteger(R$styleable.customIndicatorView_indicatorUnitVisibility, 8);
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorDescText)) {
            this.e = typedArray.getString(R$styleable.customIndicatorView_indicatorDescText);
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorDescColor)) {
            this.f8926a = typedArray.getColor(R$styleable.customIndicatorView_indicatorDescColor, getResources().getColor(R$color.textColorSecondary));
        }
        if (typedArray.hasValue(R$styleable.customIndicatorView_indicatorDescVisibility)) {
            this.d = typedArray.getInteger(R$styleable.customIndicatorView_indicatorUnitVisibility, 8);
        }
    }

    private void c() {
        int i = this.d;
        if (i == 0) {
            this.b.setVisibility(0);
            if (TextUtils.isEmpty(this.e)) {
                this.b.setText(this.e);
                cEQ_(this.b, this.f8926a);
                return;
            }
            return;
        }
        if (i == 4) {
            this.b.setVisibility(4);
        } else {
            if (i != 8) {
                return;
            }
            this.b.setVisibility(8);
        }
    }

    private void cEQ_(TextView textView, int i) {
        if (i != 0) {
            textView.setTextColor(i);
        }
    }

    public void setIndicatorTitle(String str) {
        TextView textView = this.g;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setIndicatorValue(String str) {
        TextView textView = this.k;
        if (textView != null) {
            textView.setText(str);
            this.k.setTypeface(Typeface.createFromAsset(BaseApplication.e().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
        }
    }

    public void setIndicatorUnit(String str) {
        TextView textView = this.n;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setIndicatorDesc(String str) {
        TextView textView = this.b;
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void a() {
        int i = this.o;
        if (i == 0) {
            this.n.setVisibility(0);
            if (TextUtils.isEmpty(this.j)) {
                return;
            }
            this.n.setText(this.j);
            cEQ_(this.n, this.h);
            return;
        }
        if (i == 4) {
            this.n.setVisibility(4);
        } else {
            if (i != 8) {
                return;
            }
            this.n.setVisibility(8);
        }
    }

    private void b() {
        if (TextUtils.isEmpty(this.l)) {
            return;
        }
        this.k.setText(this.l);
        cEQ_(this.k, this.m);
        this.k.setTypeface(Typeface.createFromAsset(BaseApplication.e().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
    }

    private void e() {
        int i = this.i;
        if (i == 0) {
            this.g.setVisibility(0);
            if (TextUtils.isEmpty(this.c)) {
                return;
            }
            this.g.setText(this.c);
            cEQ_(this.g, this.f);
            return;
        }
        if (i == 4) {
            this.g.setVisibility(4);
        } else {
            if (i != 8) {
                return;
            }
            this.g.setVisibility(8);
        }
    }
}
