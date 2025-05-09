package com.huawei.profile.client.profile;

import android.os.Bundle;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.DeviceProfileEx;
import com.huawei.profile.profile.ProfileGeneralException;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.profile.SubscribeInfo;
import com.huawei.profile.profile.SyncMode;
import com.huawei.profile.service.AbstractSubscribeProfileListener;
import com.huawei.profile.service.GetDeviceInfoListener;
import com.huawei.profile.service.GetDeviceInfoListenerEx;
import com.huawei.profile.service.PutDeviceExListener;
import com.huawei.profile.service.SyncDeviceInfoExListener;
import com.huawei.profile.service.SyncDeviceInfoListener;
import com.huawei.profile.subscription.event.EventInfo;
import java.util.List;

/* loaded from: classes6.dex */
public interface IProfileClient {
    boolean deleteDevice(String str);

    boolean deleteDeviceLocal(String str);

    boolean deleteDeviceLocal(String str, String str2);

    boolean deleteServiceCharacteristic(String str, String str2, String str3);

    Bundle deleteServiceOfDevice(String str, List<String> list, boolean z, Bundle bundle);

    @Deprecated
    boolean deleteServiceOfDevice(String str, String str2);

    boolean findDevice(boolean z, String str, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle);

    String getApiVersion();

    int getApiVersionCode();

    boolean getAvailableDeviceInfo(boolean z, String str, GetDeviceInfoListener getDeviceInfoListener);

    boolean getAvailableDeviceInfoEx(boolean z, String str, List<String> list, List<String> list2, GetDeviceInfoListenerEx getDeviceInfoListenerEx);

    List<DeviceProfile> getDevices(boolean z, List<String> list);

    List<DeviceProfile> getDevices(boolean z, List<String> list, Bundle bundle);

    List<DeviceProfile> getDevicesById(String str, boolean z, List<String> list);

    List<DeviceProfileEx> getDevicesByIdEx(List<String> list, boolean z, List<String> list2, Bundle bundle);

    List<DeviceProfile> getDevicesByType(String str, boolean z, List<String> list);

    List<DeviceProfileEx> getDevicesByTypeEx(List<String> list, boolean z, List<String> list2, Bundle bundle);

    List<DeviceProfile> getDevicesByTypeLocal(String str);

    List<DeviceProfile> getDevicesByTypeLocal(String str, List<String> list);

    List<DeviceProfileEx> getDevicesEx(boolean z, List<String> list, Bundle bundle);

    DeviceProfileEx getHostDevice(List<String> list, List<String> list2);

    @Deprecated
    ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, boolean z, List<String> list);

    ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, boolean z, List<String> list, Bundle bundle);

    @Deprecated
    List<ServiceProfile> getServicesOfDevice(String str, boolean z, List<String> list);

    List<ServiceProfile> getServicesOfDevice(String str, boolean z, List<String> list, Bundle bundle) throws ProfileGeneralException;

    boolean putDevice(DeviceProfile deviceProfile);

    boolean putDevice(DeviceProfile deviceProfile, Bundle bundle);

    boolean putDeviceEx(DeviceProfileEx deviceProfileEx, PutDeviceExListener putDeviceExListener);

    boolean putDeviceLocal(DeviceProfile deviceProfile);

    boolean putServiceCharacteristic(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile);

    boolean putServiceCharacteristic(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle);

    boolean putServiceOfDevice(String str, ServiceProfile serviceProfile);

    boolean putServiceOfDevice(String str, ServiceProfile serviceProfile, Bundle bundle);

    @Deprecated
    Bundle subscribeDeviceProfile(List<SubscribeInfo> list, Bundle bundle);

    Bundle subscribeDeviceProfile(List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle);

    String subscribeEvents(List<EventInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, String str);

    @Deprecated
    boolean syncDevicesInfo(String str, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle);

    boolean syncDevicesInfo(List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle);

    @Deprecated
    Bundle unsubscribeDeviceProfile(List<SubscribeInfo> list, Bundle bundle);

    Bundle unsubscribeDeviceProfile(List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle);

    String unsubscribeEvents(List<EventInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, String str);
}
