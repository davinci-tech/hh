package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes9.dex */
public class AutoSizeTextView extends TextView {
    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int lineCount = getLineCount();
        int d = ao.d(getContext(), getTextSize());
        if (lineCount <= 1 || d <= 10) {
            return;
        }
        setTextSize(1, d - 1);
    }

    public AutoSizeTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public AutoSizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AutoSizeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AutoSizeTextView(Context context) {
        super(context);
    }
}
