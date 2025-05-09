package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.main.stories.health.views.WeightTipsImageView;
import com.huawei.uikit.hwbubblelayout.widget.HwBubbleLayout;
import defpackage.nsn;

/* loaded from: classes9.dex */
public class WeightTipsImageView extends HealthImageView {
    private String e;

    /* renamed from: a, reason: collision with root package name */
    private static final int f10266a = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
    private static final int c = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131365217_res_0x7f0a0d61);
    private static final int b = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
    private static final int d = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131363067_res_0x7f0a04fb);

    public WeightTipsImageView(Context context) {
        super(context);
        a();
    }

    public WeightTipsImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public WeightTipsImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void setTips(String str) {
        this.e = str;
    }

    protected void a() {
        setOnClickListener(new View.OnClickListener() { // from class: qst
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightTipsImageView.this.dIs_(view);
            }
        });
    }

    public /* synthetic */ void dIs_(View view) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.weight_tips_bubble_layout, (ViewGroup) null);
        if (!(inflate instanceof HwBubbleLayout)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HwBubbleLayout hwBubbleLayout = (HwBubbleLayout) inflate;
        PopupWindow popupWindow = new PopupWindow(getContext());
        View findViewById = hwBubbleLayout.findViewById(R.id.tips_content);
        if ((findViewById instanceof HealthTextView) && !TextUtils.isEmpty(this.e)) {
            ((HealthTextView) findViewById).setText(this.e);
        }
        popupWindow.setContentView(hwBubbleLayout);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);
        hwBubbleLayout.measure(0, 0);
        int measuredWidth = hwBubbleLayout.getMeasuredWidth();
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        hwBubbleLayout.setArrowDirection(HwBubbleLayout.ArrowDirection.TOP);
        hwBubbleLayout.setArrowPositionCenter(false);
        if (iArr[0] < nsn.n() / 2) {
            int i = b;
            hwBubbleLayout.setArrowPosition(i);
            int i2 = (-getWidth()) / 2;
            popupWindow.showAsDropDown(this, (i2 - i) - c, f10266a);
        } else {
            int i3 = c;
            int width = getWidth() / 2;
            int i4 = d;
            hwBubbleLayout.setArrowPosition(((measuredWidth - i3) - width) - i4);
            popupWindow.showAsDropDown(this, (-measuredWidth) + i4, f10266a);
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
