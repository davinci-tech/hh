package com.huawei.operation.h5pro;

import android.app.Activity;
import com.huawei.health.h5pro.ext.hostapp.H5ProHostAppRuntimeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes.dex */
public class H5ProAppRuntimeImpl implements H5ProHostAppRuntimeApi {
    @Override // com.huawei.health.h5pro.ext.hostapp.H5ProHostAppRuntimeApi
    public Activity getTopActivity() {
        return BaseApplication.getActivity();
    }
}
