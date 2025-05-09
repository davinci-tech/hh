package com.huawei.uikit.hwdatepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwtextview.widget.HwTextView;

/* loaded from: classes9.dex */
public class HwDatePickerDialogBottomBar extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HwTextView f10637a;
    private float b;
    private HwTextView c;

    public HwDatePickerDialogBottomBar(Context context) {
        super(context);
    }

    private void b() {
        HwTextView hwTextView = this.c;
        if (hwTextView == null || this.f10637a == null) {
            return;
        }
        hwTextView.setTextSize(this.b);
        this.f10637a.setTextSize(this.b);
    }

    private void c() {
        this.c = (HwTextView) findViewById(R.id.hwdatepicker_dialog_negative_btn);
        this.f10637a = (HwTextView) findViewById(R.id.hwdatepicker_dialog_positive_btn);
        this.b = getResources().getDimension(R.dimen._2131364126_res_0x7f0a091e);
    }

    private void d() {
        HwTextView hwTextView = this.c;
        if (hwTextView == null || this.f10637a == null) {
            return;
        }
        float min = Math.min(hwTextView.getTextSize(), this.f10637a.getTextSize());
        this.c.setTextSize(0, min);
        this.f10637a.setTextSize(0, min);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        b();
        super.onMeasure(i, i2);
        d();
    }

    public HwDatePickerDialogBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HwDatePickerDialogBottomBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
