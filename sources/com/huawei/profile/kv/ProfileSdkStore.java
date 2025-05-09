package com.huawei.profile.kv;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.profile.account.AccountClientSdk;
import com.huawei.profile.kv.impl.ProfileSdkStoreImpl;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ProfileBaseUtils;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.utils.AssetUrlReader;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes6.dex */
public class ProfileSdkStore extends ProfileStoreBaseProxy {
    private static final String ERROR_LOG_MSG = "profileStore == null";
    private static final Object INIT_LOCK = new Object();
    private static final Object OP_LOCK = new Object();
    private static final String TAG = "ProfileSdkStore";
    private volatile boolean isInited;
    private Context mContext;
    private ProfileSdkStoreImpl profileSdkStore;

    public ProfileSdkStore(Context context) {
        this.isInited = false;
        this.mContext = null;
        synchronized (INIT_LOCK) {
            if (this.isInited) {
                return;
            }
            this.mContext = context;
            this.profileSdkStore = ProfileSdkStoreImpl.getInstance(context);
            DsLog.init("ProfileSdk", 1);
            DsLog.dt(TAG, " cloudbase sdk version: " + ProfileUtilsSdk.getCloudBaseVersion(this.mContext), new Object[0]);
            AccountClientSdk.getInstance(context).registerAccountReceiver();
            AssetUrlReader.loadUrls(this.mContext);
            this.isInited = true;
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putDevice(String str, DeviceProfile deviceProfile) {
        DsLog.it(TAG, " putDevice " + str, new Object[0]);
        return putDeviceCore(str, deviceProfile);
    }

    public boolean putDeviceCore(String str, DeviceProfile deviceProfile) {
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.putDevice(str, deviceProfile);
        }
    }

    public boolean putDevicesCore(String str, List<DeviceProfile> list) {
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.putDevices(str, list);
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile) {
        DsLog.it(TAG, " putServiceOfDevice " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.putServiceOfDevice(str, str2, serviceProfile);
        }
    }

    public boolean putServicesOfDevice(String str, String str2, List<ServiceProfile> list) {
        DsLog.it(TAG, " putServiceOfDevice " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.putServicesOfDevice(str, str2, list);
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        DsLog.it(TAG, " putServiceCharacteristic " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.putServiceCharacteristic(str, str2, str3, serviceCharacteristicProfile);
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevices(String str, boolean z, int i) {
        DsLog.it(TAG, " getDevices " + str, new Object[0]);
        ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
        if (profileSdkStoreImpl == null) {
            DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            return Collections.emptyList();
        }
        return profileSdkStoreImpl.getDevices(str, z, i);
    }

    public DeviceProfile getDeviceByDevId(String str, String str2) {
        DsLog.it(TAG, " getDeviceByDevId " + str, new Object[0]);
        ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
        if (profileSdkStoreImpl == null) {
            DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            return null;
        }
        return profileSdkStoreImpl.getDeviceByDevId(str, str2);
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevicesById(String str, String str2, boolean z, int i) {
        DsLog.it(TAG, " getDevicesById " + str, new Object[0]);
        ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
        if (profileSdkStoreImpl == null) {
            DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            return Collections.emptyList();
        }
        return profileSdkStoreImpl.getDevicesById(str, str2, z, i);
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<DeviceProfile> getDevicesByType(String str, String str2, boolean z, int i) {
        DsLog.it(TAG, " getDevicesByType " + str, new Object[0]);
        ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
        if (profileSdkStoreImpl == null) {
            DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            return Collections.emptyList();
        }
        return profileSdkStoreImpl.getDevicesByType(str, str2, z, i);
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i) {
        DsLog.it(TAG, " getServicesOfDevice " + str, new Object[0]);
        ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
        if (profileSdkStoreImpl == null) {
            DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            return Collections.emptyList();
        }
        return profileSdkStoreImpl.getServicesOfDevice(str, str2, z, i);
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i) {
        DsLog.it(TAG, " getServiceCharacteristics " + str, new Object[0]);
        ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
        if (profileSdkStoreImpl == null) {
            DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            return null;
        }
        return profileSdkStoreImpl.getServiceCharacteristics(str, str2, str3, z, i);
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteDevice(String str, String str2) {
        DsLog.it(TAG, " deleteDevice " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.deleteDevice(str, str2);
        }
    }

    public ServiceCharacteristicProfile getServiceCharacteristicsForCloudRequest(String str, String str2, String str3) {
        DsLog.it(TAG, " getServiceCharacteristicsLocal " + str, new Object[0]);
        ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
        if (profileSdkStoreImpl == null) {
            DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            return null;
        }
        return profileSdkStoreImpl.getServiceCharacteristicsInter(str, str2, str3, false);
    }

    public void queryDeleteDeviceHard(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        DsLog.it(TAG, " querydeleteDeviceHard package is " + str + ", deviceId is " + ProfileBaseUtils.anonymousContent(str2), new Object[0]);
        this.profileSdkStore.queryDeleteDeviceHard(str, str2);
    }

    public boolean deleteDeviceHard(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        DsLog.it(TAG, " deleteDeviceHard package is " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.deleteDeviceListHard(str);
        }
    }

    public void deleteServiceAndCharacterHard(String str, String str2, Set<String> set) {
        DsLog.it(TAG, " deleteServiceAndCharacterHard " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
            } else {
                profileSdkStoreImpl.deleteServiceAndCharacterHard(str, str2, set);
            }
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteServiceOfDevice(String str, String str2, String str3) {
        DsLog.it(TAG, " deleteServiceOfDevice " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.deleteServiceOfDevice(str, str2, str3);
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4) {
        DsLog.it(TAG, " deleteServiceCharacteristic " + str, new Object[0]);
        synchronized (OP_LOCK) {
            ProfileSdkStoreImpl profileSdkStoreImpl = this.profileSdkStore;
            if (profileSdkStoreImpl == null) {
                DsLog.et(TAG, ERROR_LOG_MSG, new Object[0]);
                return false;
            }
            return profileSdkStoreImpl.deleteServiceCharacteristic(str, str2, str3, str4);
        }
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putDeviceWithExtraInfo(String str, DeviceProfile deviceProfile, Bundle bundle) {
        return putDevice(str, deviceProfile);
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceOfDeviceWithExtraInfo(String str, String str2, ServiceProfile serviceProfile, Bundle bundle) {
        return putServiceOfDevice(str, str2, serviceProfile);
    }

    @Override // com.huawei.profile.kv.ProfileStoreBaseProxy
    public boolean putServiceCharacteristicWithExtraInfo(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle) {
        return putServiceCharacteristic(str, str2, str3, serviceCharacteristicProfile);
    }
}
