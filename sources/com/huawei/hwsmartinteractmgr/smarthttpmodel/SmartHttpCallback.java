package com.huawei.hwsmartinteractmgr.smarthttpmodel;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public abstract class SmartHttpCallback<R> {
    private static final String TAG = "SMART_SmartHttpCallback";

    protected abstract void goTrigger(R r);

    protected abstract boolean isHandleResponse(int i, R r);

    protected abstract boolean shouldTrigger(R r);

    public void onResponse(int i, R r) {
        if (isHandleResponse(i, r)) {
            handleResponse(r);
        } else {
            LogUtil.h(TAG, "request response from server failed!");
        }
    }

    protected void handleResponse(R r) {
        if (shouldTrigger(r)) {
            goTrigger(r);
        } else {
            LogUtil.h(TAG, "activities don't satisfied trigger condition");
        }
    }
}
