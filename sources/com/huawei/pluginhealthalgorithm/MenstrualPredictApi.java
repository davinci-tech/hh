package com.huawei.pluginhealthalgorithm;

import com.huawei.hwbasemgr.IBaseResponseCallback;

/* loaded from: classes6.dex */
public interface MenstrualPredictApi {
    void getSwitchState(String str, IBaseResponseCallback iBaseResponseCallback);

    void updateSwitchState(String str, String str2, IBaseResponseCallback iBaseResponseCallback);
}
