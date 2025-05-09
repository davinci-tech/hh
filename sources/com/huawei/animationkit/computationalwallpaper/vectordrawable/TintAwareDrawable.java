package com.huawei.animationkit.computationalwallpaper.vectordrawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: classes8.dex */
public interface TintAwareDrawable {
    void setTint(int i);

    void setTintList(ColorStateList colorStateList);

    void setTintMode(PorterDuff.Mode mode);
}
