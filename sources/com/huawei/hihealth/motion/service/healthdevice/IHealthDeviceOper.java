package com.huawei.hihealth.motion.service.healthdevice;

import android.content.Context;
import com.huawei.hihealth.motion.service.stepcounter.Releasable;

/* loaded from: classes.dex */
public interface IHealthDeviceOper extends Releasable {
    void holdDevice(String str);

    void init(Context context);

    void releaseDevice(String str);
}
