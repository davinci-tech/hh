package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.view.View;

/* loaded from: classes4.dex */
public abstract class AnimBaseHelper {
    public View mAnimContainer;

    protected abstract void initResource();

    public abstract boolean isAnimRunning();

    public abstract void startAnimation(Object obj);

    public AnimBaseHelper(View view) {
        this.mAnimContainer = view;
    }

    public void startAnimation() {
        initResource();
    }
}
