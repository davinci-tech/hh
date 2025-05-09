package com.huawei.pluginsport.helper;

import com.huawei.hwbasemgr.IBaseResponseCallback;

/* loaded from: classes6.dex */
public interface DeviceInterface {
    void getDeviceStepRateAlgorithmEnterprise(IBaseResponseCallback iBaseResponseCallback);

    void getOperator(IBaseResponseCallback iBaseResponseCallback);

    void setBoltWearStatusListener(String str, IBaseResponseCallback iBaseResponseCallback);

    void unregBoltWearStatusListener(String str);
}
