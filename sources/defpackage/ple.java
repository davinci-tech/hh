package defpackage;

import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.sleep.SleepApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.Services;

/* loaded from: classes6.dex */
public class ple {
    public static void d(String str) {
        H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.sleep-management", new H5ProLaunchOption.Builder().setImmerse().showStatusBar().setNeedSoftInputAdapter().setStatusBarTextBlack(true).setForceDarkMode(1).addPath(str).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("SleepManagementApi", ((SleepApi) Services.c("Main", SleepApi.class)).getSleepManagementH5Bridge()).build());
    }
}
