package com.huawei.health.dailyactivity.impl;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.threecircle.api.ThreeCircleApi;
import defpackage.njb;
import defpackage.nje;
import defpackage.njg;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;

@ApiDefine(uri = ThreeCircleApi.class)
@Singleton
/* loaded from: classes3.dex */
public class ThreeCircleImpl implements ThreeCircleApi {
    private static final String TAG = "ThreeCircleImpl";

    @Override // com.huawei.threecircle.api.ThreeCircleApi
    public void querySummary(final String str, final int i, final UiCallback<njb> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c(TAG, "querySummary(), callback == null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ceh
                @Override // java.lang.Runnable
                public final void run() {
                    ced.e(str, i, uiCallback);
                }
            });
        }
    }

    @Override // com.huawei.threecircle.api.ThreeCircleApi
    public void queryEncourage(final String str, final int i, final UiCallback<nje> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b(TAG, "queryEncourage(), callback == null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: cee
                @Override // java.lang.Runnable
                public final void run() {
                    ced.b(str, i, uiCallback);
                }
            });
        }
    }

    @Override // com.huawei.threecircle.api.ThreeCircleApi
    public njg queryRules(String str, String str2) {
        return ThreeCircleCloudResourceHelper.getInstance().queryRules(str, str2);
    }

    @Override // com.huawei.threecircle.api.ThreeCircleApi
    public String getPromptMessage(njg njgVar, Map<String, String> map) {
        return ThreeCircleCloudResourceHelper.getInstance().getPromptMessage(njgVar, map);
    }

    @Override // com.huawei.threecircle.api.ThreeCircleApi
    public void checkUpdateForThreeCircle() {
        ThreeCircleCloudResourceHelper.getInstance().checkUpdateForThreeCircle();
    }
}
