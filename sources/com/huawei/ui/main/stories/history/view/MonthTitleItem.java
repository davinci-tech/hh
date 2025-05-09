package com.huawei.ui.main.stories.history.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;

/* loaded from: classes7.dex */
public class MonthTitleItem extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10319a;
    private Context b;
    private int c;
    private LinearLayout.LayoutParams d;
    private LinearLayout.LayoutParams e;
    private LinearLayout.LayoutParams f;
    private HealthTextView g;

    public MonthTitleItem(Context context) {
        super(context);
        this.c = 64;
        this.f = new LinearLayout.LayoutParams(-2, -2);
        this.e = new LinearLayout.LayoutParams(-2, -2);
        this.d = new LinearLayout.LayoutParams(-2, -2);
        this.b = context;
        this.g = new HealthTextView(context);
        this.f10319a = new HealthTextView(context);
        a();
        Resources resources = context.getResources();
        setGravity(1);
        this.g.setTextAppearance(R.style.track_history_unit_style);
        this.g.setAutoTextSize(0, resources.getDimension(R.dimen._2131365063_res_0x7f0a0cc7));
        if (nsn.r()) {
            this.g.setAutoTextSize(0, resources.getDimension(R.dimen._2131362709_res_0x7f0a0395));
        }
        this.g.setAutoTextInfo(9, 1, 1);
        this.g.setEllipsize(TextUtils.TruncateAt.END);
        this.g.setGravity(1);
        this.f10319a.setAutoTextSize(0, resources.getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        this.f10319a.setAutoTextInfo(9, 1, 1);
        this.f10319a.setMaxLines(1);
        this.f10319a.setTextSize(0, resources.getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        this.f10319a.setTextColor(resources.getColor(R.color._2131299236_res_0x7f090ba4));
        this.f10319a.setTypeface(Typeface.create(getResources().getString(R$string.emui_text_font_family_medium), 0));
        this.f10319a.setGravity(1);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.e.gravity = 1;
        this.e.setMargins(resources.getDimensionPixelOffset(R.dimen._2131363706_res_0x7f0a077a), 0, resources.getDimensionPixelOffset(R.dimen._2131363706_res_0x7f0a077a), 0);
        this.f.gravity = 1;
        this.f.setMargins(0, resources.getDimensionPixelOffset(R.dimen._2131363702_res_0x7f0a0776), 0, 0);
        linearLayout.addView(this.f10319a, this.e);
        linearLayout.addView(this.g, this.f);
        addView(linearLayout);
        this.d.width = 360;
        setLayoutParams(this.d);
    }

    private void a() {
        if (nsn.t()) {
            nsn.b(this.f10319a);
            nsn.b(this.g);
        }
    }

    public MonthTitleItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 64;
        this.f = new LinearLayout.LayoutParams(-2, -2);
        this.e = new LinearLayout.LayoutParams(-2, -2);
        this.d = new LinearLayout.LayoutParams(-2, -2);
    }

    public MonthTitleItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 64;
        this.f = new LinearLayout.LayoutParams(-2, -2);
        this.e = new LinearLayout.LayoutParams(-2, -2);
        this.d = new LinearLayout.LayoutParams(-2, -2);
    }

    public void setTextColor(int i) {
        this.f10319a.setTextColor(i);
        this.g.setTextColor(i);
    }

    public void setValueAutoTextSize(int i) {
        this.f10319a.setAutoTextSize(0, this.b.getResources().getDimension(i));
    }

    public void setValueUnitMaxLine(int i) {
        if (i <= 1) {
            this.g.setSingleLine();
        } else {
            this.g.setLines(i);
        }
    }

    public void setValueTypeFace(Typeface typeface) {
        this.f10319a.setTypeface(typeface);
    }

    public void setValueAutoTextInfo(int i, int i2) {
        this.f10319a.setAutoTextInfo(i, i2, 1);
    }

    public int c(int i, int i2) {
        this.f10319a.measure(i, i2);
        return this.f10319a.getMeasuredHeight();
    }

    public void setValueHeight(int i) {
        this.f10319a.setHeight(i);
    }

    public void setValueGravity(int i) {
        this.f10319a.setGravity(i);
    }

    public void setValueLineSpacing(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f10319a.setFallbackLineSpacing(z);
        }
    }

    public void setDefaultHeight(int i) {
        this.c = i;
    }

    public void setGroupSize(int i, int i2) {
        this.d.width = i;
        this.d.height = i2;
        setLayoutParams(this.d);
    }

    public void setLinearLayoutParams(LinearLayout.LayoutParams layoutParams) {
        setLayoutParams(layoutParams);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(e(i, true), e(i2, false));
    }

    private int e(int i, boolean z) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int c = (z || this.b == null) ? 360 : nsn.c(this.b, this.c * Math.max(nsn.c(), 1.0f));
        return mode == Integer.MIN_VALUE ? Math.min(c, size) : c;
    }

    public void setItemView(e eVar) {
        if (eVar == null) {
            LogUtil.h("Track_MonthTitleItem", "setItemView data is null");
        } else {
            this.f10319a.setText(eVar.e());
            this.g.setText(eVar.b());
        }
    }

    public float getValueUnitTextWidth() {
        return this.g.getTextWidth();
    }

    public static class e {
        private String b;
        private String d;

        public e(String str, String str2) {
            this.b = str;
            this.d = str2;
        }

        public String e() {
            return this.b;
        }

        public String b() {
            return this.d;
        }
    }
}
