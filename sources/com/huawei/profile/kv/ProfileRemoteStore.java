package com.huawei.profile.kv;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.profile.container.ObjectContainer;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.DeviceProfileEx;
import com.huawei.profile.profile.ProfileGeneralException;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.profile.StreamData;
import com.huawei.profile.profile.SyncMode;
import com.huawei.profile.service.GetDeviceInfoListenerEx;
import com.huawei.profile.service.IProfileServiceCall;
import com.huawei.profile.service.PutDeviceExListener;
import com.huawei.profile.service.SyncDeviceInfoExListener;
import com.huawei.profile.service.SyncDeviceInfoListener;
import com.huawei.profile.subscription.deviceinfo.SubscribeInfo;
import com.huawei.profile.subscription.event.EventInfo;
import com.huawei.profile.utils.BaseUtil;
import com.huawei.profile.utils.OpResult;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes6.dex */
public class ProfileRemoteStore extends ProfileStoreExtendProxy {
    private static final ReentrantLock PUT_DEVICE_EX_LOCK = new ReentrantLock();
    private static final String TAG = "ProfileRemoteStore";
    private static final String UNSUBSCRIBE_FAIL_MSG = "unsubscribe device list by id failed, error is ";
    private IProfileServiceCall profileService;

    public ProfileRemoteStore(IProfileServiceCall iProfileServiceCall) {
        this.profileService = iProfileServiceCall;
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putDevice(String str, DeviceProfile deviceProfile) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "Failed to put device, error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.putDevice(str, deviceProfile);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to put device, error:" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "Failed to put service, error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.putServiceOfDevice(str, str2, serviceProfile);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to put service, error:" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "Failed to put character, error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.putServiceCharacteristic(str, str2, str3, serviceCharacteristicProfile);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to put character, error:" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevices(String str, boolean z, int i) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getDevices failed: error: not connected to Profile service.");
            return null;
        }
        try {
            ObjectContainer devices = iProfileServiceCall.getDevices(str, z, i);
            if (devices == null) {
                return null;
            }
            return devices.get();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devices, error:" + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public DeviceProfileEx getHostDevice(String str, List<String> list, List<String> list2) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getHostDevice failed: error: not connected to Profile service.");
            return null;
        }
        try {
            return iProfileServiceCall.getHostDevice(str, list, list2);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devices, error:" + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevicesById(String str, String str2, boolean z, int i) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getDevicesById failed: error: not connected to Profile service.");
            return null;
        }
        try {
            ObjectContainer devicesById = iProfileServiceCall.getDevicesById(str, str2, z, i);
            if (devicesById == null) {
                return null;
            }
            return devicesById.get();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devices by id, error:" + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevicesByType(String str, String str2, boolean z, int i) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getDevicesByType failed: error: not connected to Profile service.");
            return null;
        }
        try {
            ObjectContainer devicesByType = iProfileServiceCall.getDevicesByType(str, str2, z, i);
            if (devicesByType == null) {
                return null;
            }
            return devicesByType.get();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devices by type, error:" + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getServicesOfDevice failed: error: not connected to Profile service.");
            return null;
        }
        try {
            ObjectContainer servicesOfDevice = iProfileServiceCall.getServicesOfDevice(str, str2, z, i);
            if (servicesOfDevice == null) {
                return null;
            }
            return servicesOfDevice.get();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get services, error: " + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i, Bundle bundle) throws ProfileGeneralException {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getServicesOfDevice failed: error: not connected to Profile service.");
            return null;
        }
        try {
            Bundle servicesOfDeviceWithExtraInfo = iProfileServiceCall.getServicesOfDeviceWithExtraInfo(str, str2, z, i, bundle);
            if (servicesOfDeviceWithExtraInfo == null) {
                return null;
            }
            servicesOfDeviceWithExtraInfo.setClassLoader(ObjectContainer.class.getClassLoader());
            int i2 = servicesOfDeviceWithExtraInfo.getInt("retCode");
            if (i2 == 10) {
                throw new ProfileGeneralException(10, "service type of parameter more than 5");
            }
            if (i2 != 0) {
                return null;
            }
            ObjectContainer objectContainer = (ObjectContainer) BaseUtil.cast(servicesOfDeviceWithExtraInfo.getParcelable(OpResult.RESULT_DATA), ObjectContainer.class);
            return objectContainer == null ? Collections.emptyList() : objectContainer.get();
        } catch (RemoteException | ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, "Failed to get services, error: " + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getServiceCharacteristics failed: error: not connected to Profile service.");
            return null;
        }
        try {
            return iProfileServiceCall.getServiceCharacteristics(str, str2, str3, z, i);
        } catch (RemoteException e) {
            Log.e(TAG, "getServiceCharacteristics failed: error is " + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getServiceCharacteristics failed: error: not connected to Profile service.");
            return null;
        }
        try {
            return iProfileServiceCall.getServiceCharacteristicsWithExtraInfo(str, str2, str3, z, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "getServiceCharacteristics failed: error is " + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteDevice(String str, String str2) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "deleteDevice failed: error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.deleteDevice(str, str2);
        } catch (RemoteException e) {
            Log.e(TAG, "deleteDevice failed: error is " + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteServiceOfDevice(String str, String str2, String str3) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "delete service failed: error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.deleteServiceOfDevice(str, str2, str3);
        } catch (RemoteException e) {
            Log.e(TAG, "deleteServiceOfDevice failed: error is " + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle deleteServiceOfDevice(String str, String str2, List<String> list, boolean z, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "delete service failed: error: not connected to Profile service.");
            return bundle2;
        }
        try {
            return iProfileServiceCall.deleteServiceOfDeviceWithExtraInfo(str, str2, list, z, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "deleteServiceOfDevice failed: error is " + e.getMessage());
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "deleteServiceCharacteristic failed: error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.deleteServiceCharacteristic(str, str2, str3, str4);
        } catch (RemoteException e) {
            Log.e(TAG, "deleteServiceCharacteristic failed: error is " + e.getMessage());
            return false;
        } catch (RuntimeException unused) {
            Log.e(TAG, "deleteServiceCharacteristic failed with Unexpected runtimeException");
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean syncDevicesInfos(String str, String str2, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "syncDevicesInfos failed: error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.syncDeviceInfos(str, str2, syncMode, syncDeviceInfoListener, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "syncDevicesInfos failed: error is " + e.getMessage());
            return false;
        } catch (RuntimeException unused) {
            Log.e(TAG, "syncDevicesInfos failed with Unexpected runtimeException");
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean syncDeviceInfoEx(String str, List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "syncDeviceInfoEx failed: error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.syncDeviceInfoEx(str, list, syncMode, syncDeviceInfoExListener, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "syncDeviceInfoEx failed: error is " + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> getDevicesByTypeLocal(String str, String str2, List<String> list) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService is not connected when get local device profiles by device type");
            return Collections.emptyList();
        }
        try {
            ObjectContainer devicesByTypeLocal = iProfileServiceCall.getDevicesByTypeLocal(str, str2, list);
            return devicesByTypeLocal == null ? Collections.emptyList() : devicesByTypeLocal.get();
        } catch (RemoteException e) {
            Log.e(TAG, "GetDevicesByTypeLocal failed: error is " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> getDevicesByTypeLocal(String str, String str2) {
        return getDevicesByTypeLocal(str, str2, null);
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean putDeviceLocal(String str, DeviceProfile deviceProfile) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService is not connected when put device to local database");
            return false;
        }
        try {
            return iProfileServiceCall.putDeviceLocal(str, deviceProfile);
        } catch (RemoteException e) {
            Log.e(TAG, "Put device to local failed: error is " + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean deleteDeviceLocal(String str, String str2) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService is not connected when delete device in local database");
            return false;
        }
        try {
            return iProfileServiceCall.deleteDeviceLocal(str, str2, null);
        } catch (RemoteException e) {
            Log.e(TAG, "Delete device in local failed: error is " + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean deleteDeviceLocal(String str, String str2, String str3) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService is not connected when delete device in local database");
            return false;
        }
        try {
            return iProfileServiceCall.deleteDeviceLocal(str, str2, str3);
        } catch (RemoteException e) {
            Log.e(TAG, "Delete device in local failed: error is " + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfileEx> getDevicesByIdEx(String str, List<String> list, boolean z, int i, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getDevicesByIdEx failed: error: not connected to Profile service.");
            return Collections.emptyList();
        }
        try {
            return iProfileServiceCall.getDevicesByIdEx(str, list, z, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devices by id, error:" + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfileEx> getDevicesByTypeEx(String str, List<String> list, boolean z, int i, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getDevicesByTypeEx failed: error: not connected to Profile service.");
            return Collections.emptyList();
        }
        try {
            return iProfileServiceCall.getDevicesByTypeEx(str, list, z, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devices by type, error:" + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean findDevice(String str, boolean z, String str2, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "findDevice failed: error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.findDevice(str, z, str2, getDeviceInfoListenerEx, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to find device, error:" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean setSwitch(String str, boolean z) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "setSwitch failed: error: not connected to Profile service. ");
            return false;
        }
        try {
            return iProfileServiceCall.setSwitch(str, z);
        } catch (RemoteException e) {
            Log.e(TAG, "setSwitch failed: error is" + e.getClass().getSimpleName() + " " + e.getMessage());
            return false;
        } catch (RuntimeException unused) {
            Log.e(TAG, "setSwitch failed with Unexpected runtimeException");
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle addDeviceList(String str, List<DeviceProfile> list, int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when add deviceList");
            return bundle2;
        }
        try {
            return iProfileServiceCall.addDeviceList(str, list, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "add device list failed, error is " + e.getMessage());
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle deleteDeviceListByType(String str, int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when delete deviceList by type");
            return bundle2;
        }
        try {
            return iProfileServiceCall.deleteDeviceListByType(str, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "delete device list by type failed, error is " + e.getMessage());
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle deleteDeviceListById(String str, List<String> list, int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when delete deviceList by id");
            return bundle2;
        }
        try {
            return iProfileServiceCall.deleteDeviceListById(str, list, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "delete device list by id failed, error is " + e.getMessage());
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> queryDeviceList(String str, int i, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when query deviceList");
            return Collections.emptyList();
        }
        try {
            return iProfileServiceCall.queryDeviceList(str, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "query device list failed, error is " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle subscribeDeviceList(String str, int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when subscribe deviceList");
            return bundle2;
        }
        try {
            return iProfileServiceCall.subscribeDeviceList(str, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "subscribe device list by id failed, error is " + e.getMessage());
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle unsubscribeDeviceList(String str, int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when unsubscribe deviceList");
            return bundle2;
        }
        try {
            return iProfileServiceCall.unsubscribeDeviceList(str, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, UNSUBSCRIBE_FAIL_MSG + e.getMessage());
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfileEx> getDevicesEx(String str, boolean z, int i, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getDevicesEx failed: error: not connected to Profile service.");
            return Collections.emptyList();
        }
        try {
            return iProfileServiceCall.getDevicesEx(str, z, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devicesEx, error:" + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle subscribeDeviceProfileWithListener(String str, List<SubscribeInfo> list, ComponentName componentName, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when subscribe device profile");
            bundle2.putInt("retCode", 5);
            return bundle2;
        }
        try {
            return iProfileServiceCall.subscribeDeviceProfileWithListener(str, list, componentName, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, UNSUBSCRIBE_FAIL_MSG + e.getMessage());
            bundle2.putInt("retCode", 6);
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle unsubscribeDeviceProfileWithListener(String str, List<SubscribeInfo> list, ComponentName componentName, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            bundle2.putInt("retCode", 5);
            Log.e(TAG, "ProfileService not connected when unsubscribe device profile");
            return bundle2;
        }
        try {
            return iProfileServiceCall.unsubscribeDeviceProfileWithListener(str, list, componentName, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, UNSUBSCRIBE_FAIL_MSG + e.getMessage());
            bundle2.putInt("retCode", 6);
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public Bundle updateDeviceListInfo(String str, List<DeviceProfile> list, int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when updateDeviceListInfo");
            return bundle2;
        }
        try {
            return iProfileServiceCall.updateDeviceListInfo(str, list, i, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "update device list failed, error is " + e.getMessage());
            return bundle2;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public String subscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when subscribe device events");
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
        try {
            return iProfileServiceCall.subscribeEvents(str, list, componentName, str2);
        } catch (RemoteException e) {
            Log.e(TAG, "subscribe device events failed, error is " + e.getMessage());
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public String unsubscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "ProfileService not connected when unsubscribe device events");
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
        try {
            return iProfileServiceCall.unsubscribeEvents(str, list, componentName, str2);
        } catch (RemoteException e) {
            Log.e(TAG, "unsubscribe device events failed, error is " + e.getMessage());
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public boolean putDeviceEx(String str, DeviceProfileEx deviceProfileEx, PutDeviceExListener putDeviceExListener) {
        if (this.profileService == null) {
            Log.e(TAG, "Failed to put device ex, error: not connected to Profile service.");
            return false;
        }
        try {
            ReentrantLock reentrantLock = PUT_DEVICE_EX_LOCK;
            reentrantLock.lock();
            try {
                boolean putDeviceEx = this.profileService.putDeviceEx(str, new StreamData(deviceProfileEx), putDeviceExListener);
                reentrantLock.unlock();
                return putDeviceEx;
            } catch (Throwable th) {
                PUT_DEVICE_EX_LOCK.unlock();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to put device ex, error:" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreExtendProxy
    public List<DeviceProfile> getDevices(String str, boolean z, int i, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "getDevices failed: error: not connected to Profile service.");
            return null;
        }
        try {
            ObjectContainer devicesWithExtraInfo = iProfileServiceCall.getDevicesWithExtraInfo(str, z, i, bundle);
            if (devicesWithExtraInfo == null) {
                return null;
            }
            return devicesWithExtraInfo.get();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to get devices, error:" + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putDeviceWithExtraInfo(String str, DeviceProfile deviceProfile, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "Failed to put device with extraInfo, error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.putDeviceWithExtraInfo(str, deviceProfile, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to put device with extraInfo, error:" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceOfDeviceWithExtraInfo(String str, String str2, ServiceProfile serviceProfile, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "Failed to put service with extraInfo, error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.putServiceOfDeviceWithExtraInfo(str, str2, serviceProfile, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to put service with extraInfo, error:" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceCharacteristicWithExtraInfo(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle) {
        IProfileServiceCall iProfileServiceCall = this.profileService;
        if (iProfileServiceCall == null) {
            Log.e(TAG, "Failed to put character with extraInfo, error: not connected to Profile service.");
            return false;
        }
        try {
            return iProfileServiceCall.putServiceCharacteristicWithExtraInfo(str, str2, str3, serviceCharacteristicProfile, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to put character with extraInfo, error:" + e.getMessage());
            return false;
        }
    }
}
