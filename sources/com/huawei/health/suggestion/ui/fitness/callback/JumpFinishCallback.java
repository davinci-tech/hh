package com.huawei.health.suggestion.ui.fitness.callback;

/* loaded from: classes.dex */
public interface JumpFinishCallback {
    default void onJumpFail() {
    }

    void onJumpFinish();
}
