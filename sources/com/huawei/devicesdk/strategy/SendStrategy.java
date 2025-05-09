package com.huawei.devicesdk.strategy;

import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import defpackage.bim;
import defpackage.bir;
import defpackage.biu;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class SendStrategy {
    public abstract void destroy(String str);

    public abstract ArrayList<bim> getSendFrames(bir birVar, DeviceInfo deviceInfo);

    public abstract void parseReceiveFrames(DeviceInfo deviceInfo, biu biuVar, MessageReceiveCallback messageReceiveCallback);
}
