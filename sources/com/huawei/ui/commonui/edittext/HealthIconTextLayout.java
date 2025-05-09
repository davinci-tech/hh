package com.huawei.ui.commonui.edittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.uikit.hwedittext.widget.HwIconTextLayout;

/* loaded from: classes6.dex */
public class HealthIconTextLayout extends HwIconTextLayout {
    public HealthIconTextLayout(Context context) {
        super(context);
    }

    public HealthIconTextLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HealthIconTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public ImageView getImageView() {
        return (ImageView) findViewById(R.id.hwedittext_icon);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public void setText(CharSequence charSequence) {
        super.setText(charSequence);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public void setHint(CharSequence charSequence) {
        super.setHint(charSequence);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public void setIcon(Drawable drawable) {
        super.setIcon(drawable);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public void setIconBackground(Drawable drawable) {
        super.setIconBackground(drawable);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public void setOnIconClickListener(View.OnClickListener onClickListener) {
        super.setOnIconClickListener(onClickListener);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public void setOnPasswordVisibleChangedListener(HwIconTextLayout.OnPasswordVisibleChangedListener onPasswordVisibleChangedListener) {
        super.setOnPasswordVisibleChangedListener(onPasswordVisibleChangedListener);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwIconTextLayout
    public void setPasswordType(boolean z) {
        super.setPasswordType(z);
    }
}
