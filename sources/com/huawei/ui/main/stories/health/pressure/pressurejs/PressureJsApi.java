package com.huawei.ui.main.stories.health.pressure.pressurejs;

import android.app.Activity;
import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback;
import com.huawei.ui.main.stories.fitness.interactors.stressAdvice.PressureScoreMapAdvice;
import defpackage.mcj;

@H5ProService(name = PressureJsApi.TAG, users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class PressureJsApi extends JsBaseModule {
    private static final String TAG = "PressureJsApi";

    private PressureJsApi() {
    }

    private static Context getContext() {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ != null) {
            return wa_;
        }
        LogUtil.h(TAG, "getContext activity is null");
        return BaseApplication.e();
    }

    @H5ProMethod(name = "getPressureAdvice")
    public static void getPressureAdvice(long j, int i, ServiceApiCallback<String> serviceApiCallback) {
        if (serviceApiCallback == null) {
            mcj.b(TAG, "callback is null");
        } else {
            serviceApiCallback.onSuccess(getContext().getString(PressureScoreMapAdvice.a(j, i)));
        }
    }
}
