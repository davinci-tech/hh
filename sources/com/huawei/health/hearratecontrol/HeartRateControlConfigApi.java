package com.huawei.health.hearratecontrol;

import com.huawei.health.hearratecontrol.callback.ConfigCallBack;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.dsy;

/* loaded from: classes3.dex */
public interface HeartRateControlConfigApi {
    void getCourseInfoByCourseId(int i, String str, ConfigCallBack<FitWorkout> configCallBack);

    void requestGlobalConfigByType(int i, ConfigCallBack<dsy> configCallBack);
}
