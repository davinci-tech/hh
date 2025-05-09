package com.huawei.uikit.hwdateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwtextview.widget.HwTextView;

/* loaded from: classes9.dex */
public class HwDateAndTimePickerDialogBottomBar extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HwTextView f10632a;
    private HwTextView c;
    private float d;

    public HwDateAndTimePickerDialogBottomBar(Context context) {
        super(context);
    }

    private void b() {
        HwTextView hwTextView = this.c;
        if (hwTextView == null || this.f10632a == null) {
            return;
        }
        hwTextView.setTextSize(this.d);
        this.f10632a.setTextSize(this.d);
    }

    private void c() {
        this.c = (HwTextView) findViewById(R.id.hwdateandtimepicker_dialog_negative_btn);
        this.f10632a = (HwTextView) findViewById(R.id.hwdateandtimepicker_dialog_positive_btn);
        this.d = getResources().getDimension(R.dimen._2131364094_res_0x7f0a08fe);
    }

    private void d() {
        HwTextView hwTextView = this.c;
        if (hwTextView == null || this.f10632a == null) {
            return;
        }
        float min = Math.min(hwTextView.getTextSize(), this.f10632a.getTextSize());
        this.c.setTextSize(0, min);
        this.f10632a.setTextSize(0, min);
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

    public HwDateAndTimePickerDialogBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HwDateAndTimePickerDialogBottomBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
