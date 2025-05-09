package com.huawei.skinner.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: classes9.dex */
public interface TintAwareDrawable {
    void setTint(int i);

    void setTintList(ColorStateList colorStateList);

    void setTintMode(PorterDuff.Mode mode);
}
