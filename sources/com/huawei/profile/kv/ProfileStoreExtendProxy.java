package com.huawei.profile.kv;

import android.content.ComponentName;
import android.os.Bundle;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.DeviceProfileEx;
import com.huawei.profile.profile.ProfileGeneralException;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.profile.SyncMode;
import com.huawei.profile.service.GetDeviceInfoListenerEx;
import com.huawei.profile.service.PutDeviceExListener;
import com.huawei.profile.service.SyncDeviceInfoExListener;
import com.huawei.profile.service.SyncDeviceInfoListener;
import com.huawei.profile.subscription.deviceinfo.SubscribeInfo;
import com.huawei.profile.subscription.event.EventInfo;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class ProfileStoreExtendProxy extends ProfileStoreBaseProxy {
    public abstract Bundle addDeviceList(String str, List<DeviceProfile> list, int i, Bundle bundle);

    public abstract Bundle deleteDeviceListById(String str, List<String> list, int i, Bundle bundle);

    public abstract Bundle deleteDeviceListByType(String str, int i, Bundle bundle);

    public abstract boolean deleteDeviceLocal(String str, String str2);

    public abstract boolean deleteDeviceLocal(String str, String str2, String str3);

    public abstract Bundle deleteServiceOfDevice(String str, String str2, List<String> list, boolean z, Bundle bundle);

    public abstract boolean findDevice(String str, boolean z, String str2, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle);

    public abstract List<DeviceProfile> getDevices(String str, boolean z, int i, Bundle bundle);

    public abstract List<DeviceProfileEx> getDevicesByIdEx(String str, List<String> list, boolean z, int i, Bundle bundle);

    public abstract List<DeviceProfileEx> getDevicesByTypeEx(String str, List<String> list, boolean z, int i, Bundle bundle);

    public abstract List<DeviceProfile> getDevicesByTypeLocal(String str, String str2);

    public abstract List<DeviceProfile> getDevicesByTypeLocal(String str, String str2, List<String> list);

    public abstract List<DeviceProfileEx> getDevicesEx(String str, boolean z, int i, Bundle bundle);

    public abstract DeviceProfileEx getHostDevice(String str, List<String> list, List<String> list2);

    public abstract ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i, Bundle bundle);

    public abstract List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i, Bundle bundle) throws ProfileGeneralException;

    public abstract boolean putDeviceEx(String str, DeviceProfileEx deviceProfileEx, PutDeviceExListener putDeviceExListener);

    public abstract boolean putDeviceLocal(String str, DeviceProfile deviceProfile);

    public abstract List<DeviceProfile> queryDeviceList(String str, int i, Bundle bundle);

    public abstract boolean setSwitch(String str, boolean z);

    public abstract Bundle subscribeDeviceList(String str, int i, Bundle bundle);

    public abstract Bundle subscribeDeviceProfileWithListener(String str, List<SubscribeInfo> list, ComponentName componentName, Bundle bundle);

    public abstract String subscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2);

    public abstract boolean syncDeviceInfoEx(String str, List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle);

    public abstract boolean syncDevicesInfos(String str, String str2, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle);

    public abstract Bundle unsubscribeDeviceList(String str, int i, Bundle bundle);

    public abstract Bundle unsubscribeDeviceProfileWithListener(String str, List<SubscribeInfo> list, ComponentName componentName, Bundle bundle);

    public abstract String unsubscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2);

    public abstract Bundle updateDeviceListInfo(String str, List<DeviceProfile> list, int i, Bundle bundle);
}
