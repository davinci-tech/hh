package com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager;

import com.huawei.datatype.DeviceCommand;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwbtsdk.btdatatype.callback.IAddDeviceStateCallback;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback;
import com.huawei.wearmgr.phoneprocess.adapterapi.WearMgrAdapterApi;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface HwDeviceMgrInterface {
    void addDevice(DeviceParameter deviceParameter, IAddDeviceStateCallback iAddDeviceStateCallback, String str);

    void connectDevice(DeviceInfo deviceInfo, int i);

    void destroy();

    List<com.huawei.health.devicemgr.business.entity.DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str);

    Map<String, DeviceCapability> queryDeviceCapability(int i, String str, String str2);

    void registerAdapter(WearMgrAdapterApi wearMgrAdapterApi);

    void registerDeviceStateCallback(DeviceStateCallback deviceStateCallback);

    void sendDeviceData(DeviceCommand deviceCommand);

    void switchDevice(List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list, String str);

    void unPair(List<String> list, boolean z);

    void unRegisterDeviceStateCallback(DeviceStateCallback deviceStateCallback);
}
