package com.huawei.health.suggestion.ui.fitness.module;

/* loaded from: classes4.dex */
public interface OnMotionChangeListener {
    void onCustomBeave();

    void onGroupFinish(Motion motion, int i, int i2);

    void onMotionChanged(Motion motion, int i);

    void onMotionContinue(Motion motion, int i);

    void onMotionOver(Motion motion, int i);

    void onMotionPause(Motion motion, int i);

    void onMotionReady(Motion motion, int i);

    void onMotionStart(Motion motion, int i);

    void onTrainOver(boolean z);
}
