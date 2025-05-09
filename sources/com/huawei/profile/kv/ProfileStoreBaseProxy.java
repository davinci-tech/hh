package com.huawei.profile.kv;

import android.os.Bundle;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class ProfileStoreBaseProxy {
    public abstract boolean deleteDevice(String str, String str2);

    public abstract boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4);

    public abstract boolean deleteServiceOfDevice(String str, String str2, String str3);

    public abstract List<DeviceProfile> getDevices(String str, boolean z, int i);

    public abstract List<DeviceProfile> getDevicesById(String str, String str2, boolean z, int i);

    public abstract List<DeviceProfile> getDevicesByType(String str, String str2, boolean z, int i);

    public abstract ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i);

    public abstract List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i);

    public abstract boolean putDevice(String str, DeviceProfile deviceProfile);

    public abstract boolean putDeviceWithExtraInfo(String str, DeviceProfile deviceProfile, Bundle bundle);

    public abstract boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile);

    public abstract boolean putServiceCharacteristicWithExtraInfo(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle);

    public abstract boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile);

    public abstract boolean putServiceOfDeviceWithExtraInfo(String str, String str2, ServiceProfile serviceProfile, Bundle bundle);
}
