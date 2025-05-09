package com.huawei.health.suggestion.ui.fitness.mvp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface ScreenController {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ScreenControlType {
    }

    void setScreenOrientation(int i);
}
