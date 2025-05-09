package com.huawei.ui.commonui.activetips;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;
import defpackage.nsy;
import java.util.Objects;

/* loaded from: classes6.dex */
public class ActiveTipsTextView extends HealthTextView {
    private boolean b;

    public ActiveTipsTextView(Context context) {
        super(context);
    }

    public ActiveTipsTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActiveTipsTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActiveTipsTextView);
        this.b = obtainStyledAttributes.getBoolean(R$styleable.ActiveTipsTextView_isNeedAutoFocus, true);
        obtainStyledAttributes.recycle();
    }

    @Override // com.huawei.uikit.hwtextview.widget.HwTextView, android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (Objects.equals(charSequence, getText())) {
            return;
        }
        super.setText(charSequence, bufferType);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.b && (nsy.cMe_(this) || nsn.t());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (z) {
            super.onFocusChanged(true, i, rect);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        if (z) {
            super.onWindowFocusChanged(true);
        }
    }

    public void setAutoFocus(boolean z) {
        this.b = z;
    }
}
