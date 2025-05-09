package com.huawei.featureuserprofile.target.js;

import com.huawei.featureuserprofile.target.cloud.ActiveTargetManager;
import com.huawei.health.h5pro.service.anotation.H5ProCallback;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import defpackage.buq;

@H5ProService(name = "ActiveTarget", users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class ActiveTargetService {
    private static final String TAG = "ActiveTargetService";

    @H5ProCallback
    public interface ActiveTargetsCallback {
        void onFailure(int i, String str);

        void onSuccess(Object obj);
    }

    private ActiveTargetService() {
    }

    @H5ProMethod
    public static void getActiveTargets(final ActiveTargetsCallback activeTargetsCallback) {
        LogUtil.a(TAG, "enter getActiveTargets");
        ActiveTargetManager.e().a(new ResultCallback<buq>() { // from class: com.huawei.featureuserprofile.target.js.ActiveTargetService.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(buq buqVar) {
                LogUtil.a(ActiveTargetService.TAG, "data is ", buqVar);
                ActiveTargetsCallback.this.onSuccess(buqVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b(ActiveTargetService.TAG, "throwable is ", th.getMessage());
            }
        });
    }

    @H5ProMethod
    public static void getLastYearData(int i, ActiveTargetsCallback activeTargetsCallback) {
        LogUtil.a(TAG, "enter getLastYearData");
        ActiveTargetManager.e().c(i, activeTargetsCallback);
    }

    @H5ProMethod
    public static void updateActiveTarget(int i, double d, double d2, double d3) {
        LogUtil.a(TAG, "enter updateActiveTarget, type = ", Integer.valueOf(i), ", goalValue = ", Double.valueOf(d), ", completeValue = ", Double.valueOf(d2), ", initialWeight = ", Double.valueOf(d3));
        ActiveTargetManager.e().b(i, d, d2, d3);
    }
}
