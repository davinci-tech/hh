package com.huawei.uikit.hwtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwtextview.widget.HwTextView;

/* loaded from: classes9.dex */
public class HwTimePickerDialogBottomBar extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HwTextView f10770a;
    private float d;
    private HwTextView e;

    public HwTimePickerDialogBottomBar(Context context) {
        super(context);
    }

    private void b() {
        HwTextView hwTextView = this.f10770a;
        if (hwTextView == null || this.e == null) {
            return;
        }
        float min = Math.min(hwTextView.getTextSize(), this.e.getTextSize());
        this.f10770a.setTextSize(0, min);
        this.e.setTextSize(0, min);
    }

    private void c() {
        HwTextView hwTextView = this.f10770a;
        if (hwTextView == null || this.e == null) {
            return;
        }
        hwTextView.setTextSize(this.d);
        this.e.setTextSize(this.d);
    }

    private void e() {
        this.f10770a = (HwTextView) findViewById(R.id.hwtimepicker_negative_btn);
        this.e = (HwTextView) findViewById(R.id.hwtimepicker_positive_btn);
        this.d = getResources().getDimension(R.dimen._2131364501_res_0x7f0a0a95);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        c();
        super.onMeasure(i, i2);
        b();
    }

    public HwTimePickerDialogBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HwTimePickerDialogBottomBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
