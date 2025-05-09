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
import com.huawei.profile.utils.OpResult;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class ProfileFalseStore extends ProfileStoreExtendProxy {
    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteDevice(String str, String str2) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean deleteDeviceLocal(String str, String str2) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean deleteDeviceLocal(String str, String str2, String str3) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteServiceOfDevice(String str, String str2, String str3) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean findDevice(String str, boolean z, String str2, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevices(String str, boolean z, int i) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> getDevices(String str, boolean z, int i, Bundle bundle) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevicesById(String str, String str2, boolean z, int i) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevicesByType(String str, String str2, boolean z, int i) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public DeviceProfileEx getHostDevice(String str, List<String> list, List<String> list2) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i, Bundle bundle) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i) {
        return null;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putDevice(String str, DeviceProfile deviceProfile) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean putDeviceEx(String str, DeviceProfileEx deviceProfileEx, PutDeviceExListener putDeviceExListener) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean putDeviceLocal(String str, DeviceProfile deviceProfile) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putDeviceWithExtraInfo(String str, DeviceProfile deviceProfile, Bundle bundle) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceCharacteristicWithExtraInfo(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceOfDeviceWithExtraInfo(String str, String str2, ServiceProfile serviceProfile, Bundle bundle) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean setSwitch(String str, boolean z) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean syncDeviceInfoEx(String str, List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean syncDevicesInfos(String str, String str2, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle) {
        return false;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i, Bundle bundle) throws ProfileGeneralException {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle deleteServiceOfDevice(String str, String str2, List<String> list, boolean z, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> getDevicesByTypeLocal(String str, String str2, List<String> list) {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> getDevicesByTypeLocal(String str, String str2) {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfileEx> getDevicesByIdEx(String str, List<String> list, boolean z, int i, Bundle bundle) {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfileEx> getDevicesByTypeEx(String str, List<String> list, boolean z, int i, Bundle bundle) {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle addDeviceList(String str, List<DeviceProfile> list, int i, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle deleteDeviceListByType(String str, int i, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle deleteDeviceListById(String str, List<String> list, int i, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> queryDeviceList(String str, int i, Bundle bundle) {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle subscribeDeviceList(String str, int i, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle unsubscribeDeviceList(String str, int i, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfileEx> getDevicesEx(String str, boolean z, int i, Bundle bundle) {
        return Collections.emptyList();
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle subscribeDeviceProfileWithListener(String str, List<SubscribeInfo> list, ComponentName componentName, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle unsubscribeDeviceProfileWithListener(String str, List<SubscribeInfo> list, ComponentName componentName, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle updateDeviceListInfo(String str, List<DeviceProfile> list, int i, Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public String unsubscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) {
        return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public String subscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) {
        return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
    }
}
