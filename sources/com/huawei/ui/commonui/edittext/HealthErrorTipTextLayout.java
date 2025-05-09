package com.huawei.ui.commonui.edittext;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.TextViewCompat;
import com.huawei.ui.commonui.R$color;
import com.huawei.uikit.hwedittext.R$dimen;
import com.huawei.uikit.hwedittext.widget.HwIconTextLayout;
import com.huawei.uikit.phone.hwedittext.widget.HwErrorTipTextLayout;

/* loaded from: classes6.dex */
public class HealthErrorTipTextLayout extends HwErrorTipTextLayout {
    public HealthErrorTipTextLayout(Context context) {
        super(context);
    }

    public HealthErrorTipTextLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthErrorTipTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setErrorColor(boolean z) {
        if (this.mErrorView != null) {
            this.mErrorView.setTextColor(getResources().getColor(z ? R$color.colorError : R$color.textColorSecondary));
        }
        setBackground(z);
    }

    public void setErrorImmediately(CharSequence charSequence) {
        if (this.mErrorView == null || !isErrorEnabled()) {
            return;
        }
        this.mErrorView.setText(charSequence);
        boolean z = !TextUtils.isEmpty(charSequence);
        setBackground(z);
        this.mErrorView.setVisibility(z ? 0 : 8);
        sendAccessibilityEvent(2048);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwErrorTipTextLayout, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof HwIconTextLayout) {
            setEditText(((HwIconTextLayout) view).getEditText());
            this.mErrorView = new TextView(getContext());
            this.mErrorView.setVisibility(8);
            this.mErrorView.setPaddingRelative(this.mEditText.getPaddingLeft(), getResources().getDimensionPixelSize(R$dimen.hwedittext_dimens_text_margin_fifth), this.mEditText.getPaddingRight(), 0);
            TextViewCompat.setTextAppearance(this.mErrorView, this.mErrorTextAppearance);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(3, view.getId());
            this.mErrorView.setLayoutParams(layoutParams2);
            addView(this.mErrorView);
            super.addView(view, 0, updateEditTextMargin(layoutParams));
            return;
        }
        super.addView(view, i, layoutParams);
    }
}
