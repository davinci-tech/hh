package com.huawei.health.healthlinkage.api;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.List;

/* loaded from: classes3.dex */
public interface HWhealthLinkageApi {
    List<DeviceInfo> getLinkedDevice(int i);

    boolean isMediatorExist();

    boolean isMediatorInit();
}
