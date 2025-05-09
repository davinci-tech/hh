package com.huawei.operation.h5pro.jsmodules.compact;

import android.app.Activity;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.adapter.CloseWebCallback;

/* loaded from: classes5.dex */
public class CloseWebCallbackCompact implements CloseWebCallback {
    @Override // com.huawei.operation.adapter.CloseWebCallback
    public void goBackToMiniShop() {
    }

    @Override // com.huawei.operation.adapter.CloseWebCallback
    public void onCloseWebCallback() {
        Activity activity = BaseApplication.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
