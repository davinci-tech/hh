package com.huawei.health.h5pro.core;

import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class H5ProBridgeCbkExecutorImpl implements H5ProBridgeCbkExecutor {
    public final WeakReference<H5ProAppLoader> d;

    @Override // com.huawei.health.h5pro.core.H5ProBridgeCbkExecutor
    public void execute(String str) {
        H5ProAppLoader h5ProAppLoader = (H5ProAppLoader) GeneralUtil.getReferent(this.d);
        if (h5ProAppLoader == null) {
            LogUtil.w("H5PRO_H5ProBridgeCbkExecutorImpl", "onJsBridgeCbk: h5ProAppLoader is null");
        } else {
            h5ProAppLoader.execJs(str, null);
        }
    }

    public H5ProBridgeCbkExecutorImpl(H5ProAppLoader h5ProAppLoader) {
        this.d = new WeakReference<>(h5ProAppLoader);
    }
}
