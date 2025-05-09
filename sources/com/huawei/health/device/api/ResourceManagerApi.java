package com.huawei.health.device.api;

import com.huawei.health.device.model.HealthDevice;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public interface ResourceManagerApi {
    ArrayList<HealthDevice.HealthDeviceKind> getProductKinds(List<String> list);
}
