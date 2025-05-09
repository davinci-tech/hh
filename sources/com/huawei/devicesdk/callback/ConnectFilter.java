package com.huawei.devicesdk.callback;

import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bir;

/* loaded from: classes3.dex */
public interface ConnectFilter {
    int onFilter(UniteDevice uniteDevice, String str, bir birVar);

    String preProcess(UniteDevice uniteDevice, String str);
}
