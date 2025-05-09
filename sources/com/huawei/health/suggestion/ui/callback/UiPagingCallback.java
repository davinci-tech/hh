package com.huawei.health.suggestion.ui.callback;

import com.huawei.basefitnessadvice.callback.UiCallback;

/* loaded from: classes4.dex */
public abstract class UiPagingCallback<T> extends UiCallback<T> {
    private boolean mHasCallback = false;

    protected abstract void onFailureFirst(int i, String str);

    protected abstract void onSuccessFirst(T t);

    @Override // com.huawei.basefitnessadvice.callback.UiCallback
    public void onSuccess(T t) {
        if (this.mHasCallback) {
            return;
        }
        this.mHasCallback = true;
        onSuccessFirst(t);
    }

    @Override // com.huawei.basefitnessadvice.callback.UiCallback
    public void onFailure(int i, String str) {
        if (this.mHasCallback) {
            return;
        }
        this.mHasCallback = true;
        onFailureFirst(i, str);
    }
}
