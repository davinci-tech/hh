package com.huawei.health.device.kit.wsp.task;

import defpackage.cjq;

/* loaded from: classes3.dex */
public interface ITaskService {
    void disable(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback);

    void enable(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback);

    void write(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback);
}
