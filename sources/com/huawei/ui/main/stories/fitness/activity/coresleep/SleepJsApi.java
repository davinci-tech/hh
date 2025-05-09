package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.h5pro.service.H5ProServiceLiveTerm;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback;
import com.huawei.ui.commonui.R$string;
import defpackage.nrh;

@H5ProService(liveTerm = H5ProServiceLiveTerm.WEB_VIEW, name = "SleepJsApi")
/* loaded from: classes.dex */
public class SleepJsApi {
    @H5ProMethod(name = "setManualSleepInsertSuccess")
    public static void setManualSleepInsertSuccess(String str, ServiceApiCallback<String> serviceApiCallback) {
        Context e = BaseApplication.e();
        try {
            long parseLong = Long.parseLong(str);
            nrh.d(e, e.getResources().getString(R$string.IDS_manual_sleep_input_success));
            ObserverManagerUtil.c("FINISH_SLEEP_TAG", Long.valueOf(parseLong));
            serviceApiCallback.onSuccess("success");
        } catch (NumberFormatException unused) {
            serviceApiCallback.onFailure(-1, "time is error");
        }
    }
}
