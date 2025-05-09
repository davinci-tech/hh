package com.huawei.ui.commonui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class HealthRelativeLayout extends RelativeLayout {
    public HealthRelativeLayout(Context context) {
        super(context);
    }

    public HealthRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        try {
            setMeasuredDimension(i, i2);
            super.onMeasure(i, i2);
        } catch (IllegalStateException e) {
            LogUtil.b("HealthRelativeLayout", "onMeasure() occurs an exception is ", ExceptionUtils.d(e));
        }
    }
}
