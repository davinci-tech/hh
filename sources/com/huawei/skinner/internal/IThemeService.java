package com.huawei.skinner.internal;

import android.graphics.drawable.Drawable;
import defpackage.nct;

/* loaded from: classes9.dex */
public interface IThemeService {
    int getCurrentAccentColor();

    int getCustomThemeColor();

    nct getThemeInfo();

    Drawable themeBackground();

    void updateThemeInfo(nct nctVar);
}
