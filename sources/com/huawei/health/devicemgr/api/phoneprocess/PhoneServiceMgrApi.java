package com.huawei.health.devicemgr.api.phoneprocess;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import java.util.List;

/* loaded from: classes.dex */
public interface PhoneServiceMgrApi {
    List<DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str);

    String getDeviceRelation(String str);
}
