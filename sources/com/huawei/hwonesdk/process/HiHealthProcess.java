package com.huawei.hwonesdk.process;

import com.huawei.hwbasemgr.IBaseResponseCallback;

/* loaded from: classes9.dex */
public interface HiHealthProcess {
    public static final String TAG = "HiHealthProcess_";

    void doAction(String str, IBaseResponseCallback iBaseResponseCallback);

    String postProcess(Object obj, Object obj2);

    Object preProcess(String str);
}
