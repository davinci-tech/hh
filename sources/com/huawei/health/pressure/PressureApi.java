package com.huawei.health.pressure;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwbasemgr.IBaseResponseCallback;

/* loaded from: classes.dex */
public interface PressureApi {
    Class<? extends JsBaseModule> getPressureJsApi();

    Class<? extends JsBaseModule> getPressureNewJsApi();

    void getSupportPressureDevice(IBaseResponseCallback iBaseResponseCallback);

    void gotoPressure(Context context, Bundle bundle);
}
