package com.huawei.healthosa;

import android.content.Context;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;

/* loaded from: classes.dex */
public interface HealthOsaApi {
    Class<? extends JsBaseModule> getOsaJsModule(String str);

    void initialize(HealthOsaApi healthOsaApi);

    void startOsaH5(Context context, String str);
}
