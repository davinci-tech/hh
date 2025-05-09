package com.huawei.phoneservice.feedback.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.faq.base.util.i;

/* loaded from: classes9.dex */
public class SubmitButton extends Button {
    private Context b;

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        StringBuilder sb;
        Configuration configuration = getResources().getConfiguration();
        i.c("widthMeasureSpec", i + "<<<<<<qian");
        WindowManager windowManager = (WindowManager) this.b.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i3 = displayMetrics.widthPixels;
        if (2 != configuration.orientation || !com.huawei.phoneservice.faq.base.util.b.c()) {
            if (1 == configuration.orientation && com.huawei.phoneservice.faq.base.util.b.c()) {
                i = View.MeasureSpec.makeMeasureSpec(i3 / 2, View.MeasureSpec.getMode(i));
                sb = new StringBuilder();
            }
            super.onMeasure(i, i2);
        }
        i = View.MeasureSpec.makeMeasureSpec(i3 / 3, View.MeasureSpec.getMode(i));
        sb = new StringBuilder();
        sb.append(i);
        sb.append("<<<<<<<<hou");
        i.c("widthMeasureSpec", sb.toString());
        super.onMeasure(i, i2);
    }

    public SubmitButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    public SubmitButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SubmitButton(Context context) {
        this(context, null);
    }
}
