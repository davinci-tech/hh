package com.huawei.healthosa.location;

import android.content.Context;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.healthosa.HealthOsaApi;
import com.huawei.healthosa.h5.OsaServiceForH5Interaction;
import defpackage.bzs;
import health.compact.a.LogUtil;

/* loaded from: classes8.dex */
public class OsaServiceForLocationInteraction implements HealthOsaApi {
    private static final Object LOCK = new Object();
    private static final String TAG = "OsaServiceForLocationInteraction";
    private static OsaServiceForLocationInteraction mInteraction;

    @Override // com.huawei.healthosa.HealthOsaApi
    public void initialize(HealthOsaApi healthOsaApi) {
    }

    private static OsaServiceForLocationInteraction getInstance() {
        synchronized (LOCK) {
            if (mInteraction == null) {
                mInteraction = new OsaServiceForLocationInteraction();
            }
        }
        return mInteraction;
    }

    @Override // com.huawei.healthosa.HealthOsaApi
    public void startOsaH5(Context context, String str) {
        LogUtil.d(TAG, "startOsaH5");
        bzs e = bzs.e();
        e.initH5Pro();
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.sleep-apnea", new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("healthosa", OsaServiceForH5Interaction.class).addCustomizeJsModule("device", e.getCommonJsModule("device")).addCustomizeJsModule("ecgJsModule", e.getCommonJsModule("ecgJsModule")).addCustomizeJsModule("healthengine", e.getCommonJsModule("healthengine")).setImmerse().showStatusBar().setStatusBarTextBlack(true).setNeedSoftInputAdapter().setForceDarkMode(1).addPath("#/?from=" + str).build());
    }

    @Override // com.huawei.healthosa.HealthOsaApi
    public Class<? extends JsBaseModule> getOsaJsModule(String str) {
        if ("healthosa".equals(str)) {
            return OsaServiceForH5Interaction.class;
        }
        LogUtil.a(TAG, "getOsaJsModule: ", str, " not implemented");
        return JsBaseModule.class;
    }
}
