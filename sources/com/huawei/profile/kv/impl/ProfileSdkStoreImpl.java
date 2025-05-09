package com.huawei.profile.kv.impl;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.profile.account.AccountClientSdk;
import com.huawei.profile.coordinator.RequestAgentSdk;
import com.huawei.profile.coordinator.task.ProfileTaskPoolSdk;
import com.huawei.profile.datamanager.DatabaseFactory;
import com.huawei.profile.kv.DBEntity;
import com.huawei.profile.kv.ProfileJson;
import com.huawei.profile.kv.ProfileValue;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.utils.ClassUtil;
import com.huawei.profile.utils.JsonUtils;
import com.huawei.profile.utils.logger.DsLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class ProfileSdkStoreImpl {
    private static final int CALLBACK_COUNT = 1;
    private static final int CLOUD_TIME_OUT = 3000;
    private static final long DAY_TIME_IN_MS = 86400000;
    private static final String ERROR_CODE = " Failed to get account, error code:";
    private static final int GET_ACCOUNT_TIME_OUT = 2000;
    private static final Object LOCK = new Object();
    private static final int MAX_DEVICE_NUMBER = 500;
    private static final int MAX_PROFILE_NUMBER = 200;
    private static final String PARAMS_NULL = "Some input params may be null, check package name, device id.";
    private static final String RESULTS_FROM_DATABASE = "query from database, not to download.";
    private static final String TAG = "ProfileSdkStoreImpl";
    private static final String WISE_DEVICE_ID_KEY = "wiseDeviceId";
    private static ProfileSdkStoreImpl singleton;
    private Context mContext;
    private ProfileUtilsSdk profileUtilsSdk;
    private List<String> devices = new ArrayList();
    private List<String> services = new ArrayList();
    private List<String> characters = new ArrayList();

    private ProfileSdkStoreImpl(Context context) {
        this.mContext = null;
        this.mContext = context;
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
    }

    public static ProfileSdkStoreImpl getInstance(Context context) {
        ProfileSdkStoreImpl profileSdkStoreImpl;
        synchronized (LOCK) {
            if (singleton == null) {
                singleton = new ProfileSdkStoreImpl(context);
            }
            profileSdkStoreImpl = singleton;
        }
        return profileSdkStoreImpl;
    }

    private boolean isDeviceExist(String str, String str2) {
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/devices");
        return (ProfileUtilsSdk.isNull(profileNew) || profileNew.isEmpty() || !profileNew.containsKey(str2)) ? false : true;
    }

    private boolean isServiceExist(String str, String str2, String str3) {
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/" + str2 + "/services");
        return (ProfileUtilsSdk.isNull(profileNew) || profileNew.isEmpty() || !profileNew.containsKey(str3)) ? false : true;
    }

    public boolean putDevice(String str, DeviceProfile deviceProfile) {
        boolean isNeedCloud = deviceProfile.getIsNeedCloud();
        if (!isNeedCloud) {
            DsLog.dt(TAG, " need cloud: " + isNeedCloud, new Object[0]);
        }
        return putDeviceInter(str, deviceProfile, isNeedCloud);
    }

    public boolean putDevices(String str, List<DeviceProfile> list) {
        if (str == null || list == null || list.isEmpty()) {
            DsLog.et(TAG, "Some input params may be null, check package name, device profile list", new Object[0]);
            return false;
        }
        return batchPutProfile(str, "all_profiles/devices", list);
    }

    public boolean putDeviceInter(String str, DeviceProfile deviceProfile, boolean z) {
        if (str == null || deviceProfile == null) {
            DsLog.et(TAG, "Some input params may be null, check package name, device profile", new Object[0]);
            return false;
        }
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/devices");
        if (!ProfileUtilsSdk.isNull(profileNew) && !profileNew.isEmpty() && profileNew.size() >= 500 && !profileNew.containsKey(deviceProfile.getId())) {
            DsLog.et(TAG, "putDevice failed, reason is the number of deviceProfile is more than 500", new Object[0]);
            return false;
        }
        boolean putProfile = putProfile(str, "all_profiles/devices", deviceProfile);
        if (z && putProfile) {
            this.profileUtilsSdk.saveResendIndex("devices", deviceProfile.getId(), "putDevice");
            this.profileUtilsSdk.saveNeedCloudDevice(deviceProfile.getId());
            ProfileTaskPoolSdk.getInstance().uploadLater(this.mContext);
        }
        return putProfile;
    }

    public boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile) {
        boolean isNeedCloud = serviceProfile.getIsNeedCloud();
        if (!isNeedCloud) {
            DsLog.dt(TAG, " need cloud:" + isNeedCloud, new Object[0]);
        }
        return putServiceOfDeviceInter(str, str2, serviceProfile, isNeedCloud);
    }

    public boolean putServicesOfDevice(String str, String str2, List<ServiceProfile> list) {
        if (str == null || str2 == null || list == null || list.isEmpty()) {
            DsLog.et(TAG, "Some input params may be null, check package name, device id", new Object[0]);
            return false;
        }
        if (!isDeviceExist(str, str2)) {
            DsLog.et(TAG, "putServicesOfDevice failed, reason is devId does not exist", new Object[0]);
            return false;
        }
        return batchPutProfile(str, "all_profiles/" + str2 + "/services", list);
    }

    public boolean putServiceOfDeviceInter(String str, String str2, ServiceProfile serviceProfile, boolean z) {
        if (str == null || str2 == null || serviceProfile == null) {
            DsLog.et(TAG, "Some input params may be null, check package name, device id and service profile", new Object[0]);
            return false;
        }
        if (!isDeviceExist(str, str2)) {
            DsLog.et(TAG, "putServiceOfDeviceInter failed, reason is devId does not exist", new Object[0]);
            return false;
        }
        String str3 = "all_profiles/" + str2 + "/services";
        Map<String, Object> profileNew = getProfileNew(str, str3);
        if (!ProfileUtilsSdk.isNull(profileNew) && !profileNew.isEmpty() && profileNew.size() >= 200 && !profileNew.containsKey(serviceProfile.getId())) {
            DsLog.et(TAG, "putServiceOfDeviceInter failed, reason is the number of serviceProfile is more than 200.", new Object[0]);
            return false;
        }
        boolean putProfile = putProfile(str, str3, serviceProfile);
        if (z && putProfile) {
            this.profileUtilsSdk.saveResendIndex("services", str2, "putService");
            ProfileTaskPoolSdk.getInstance().uploadLater(this.mContext);
        }
        return putProfile;
    }

    public boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        boolean isNeedCloud = serviceCharacteristicProfile.getIsNeedCloud();
        if (!isNeedCloud) {
            DsLog.it(TAG, " need cloud:" + isNeedCloud, new Object[0]);
        }
        return putServiceCharacteristicInter(str, str2, str3, serviceCharacteristicProfile, isNeedCloud);
    }

    public boolean putServiceCharacteristicInter(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, boolean z) {
        if (str == null || str2 == null || str3 == null || serviceCharacteristicProfile == null) {
            DsLog.et(TAG, "Some input params may be null, check pkgName, device id, service id and character", new Object[0]);
            return false;
        }
        if (!isDeviceExist(str, str2)) {
            DsLog.et(TAG, "putServiceCharacteristicInter failed, reason is devId does not exist.", new Object[0]);
            return false;
        }
        if (!isServiceExist(str, str2, str3)) {
            DsLog.et(TAG, "putServiceCharacteristicInter failed, reason is ServiceProfile of devId does not exist.", new Object[0]);
            return false;
        }
        if (!isValidServiceCharacteristic(serviceCharacteristicProfile.getProfile())) {
            DsLog.et(TAG, "putServiceCharacteristicInter failed, ServiceCharacteristicProfile is invalid.", new Object[0]);
            return false;
        }
        String str4 = "all_profiles/" + str2 + "/" + str3 + "/characteristics";
        Map<String, Object> profileNew = getProfileNew(str, str4);
        if (!ProfileUtilsSdk.isNull(profileNew) && !profileNew.isEmpty() && profileNew.size() >= 200) {
            DsLog.et(TAG, "putServiceCharacteristicInter failed, reason is the number of characteristicProfile is more than 200 .", new Object[0]);
            return false;
        }
        boolean putProfile = putProfile(str, str4, serviceCharacteristicProfile);
        if (z && putProfile) {
            this.profileUtilsSdk.saveResendIndex("characteristics", str2 + "/" + str3, "putCharacter");
            ProfileTaskPoolSdk.getInstance().uploadLater(this.mContext);
        }
        return putProfile;
    }

    private boolean isValidServiceCharacteristic(Map<String, Object> map) {
        if (!isTimestampValid(map)) {
            DsLog.et(TAG, "putServiceCharacteristicInter failed, reason is timestamp is invalid.", new Object[0]);
            return false;
        }
        if (!isValueEmptyOrAnyNull(map)) {
            return true;
        }
        DsLog.et(TAG, "characterMap contains null value or characterMap contains timestamp only.", new Object[0]);
        return false;
    }

    private boolean isTimestampValid(Map<String, Object> map) {
        Object obj = map.get("timestamp");
        if (obj == null) {
            return false;
        }
        if (obj instanceof Long) {
            return ((Long) obj).longValue() < System.currentTimeMillis() + 86400000;
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong(obj.toString()) < System.currentTimeMillis() + 86400000;
            } catch (NumberFormatException unused) {
                DsLog.et(TAG, "putServiceCharacteristicInter failed, reason is the timestamp is invalid.", new Object[0]);
            }
        }
        return false;
    }

    private boolean isValueEmptyOrAnyNull(Map<String, Object> map) {
        return (map.containsKey("timestamp") && map.size() == 1) || map.containsValue(null);
    }

    public List<DeviceProfile> getDevices(String str, boolean z, int i) {
        return getDevicesInter(str, z);
    }

    public DeviceProfile getDeviceByDevId(String str, String str2) {
        DsLog.it(TAG, "getDeviceById start", new Object[0]);
        if (str == null || str2 == null || str2.isEmpty()) {
            return null;
        }
        List<DeviceProfile> devicesInter = getDevicesInter("com.huawei.profile", false);
        if (ProfileUtilsSdk.isNull(devicesInter) || devicesInter.isEmpty()) {
            return null;
        }
        for (DeviceProfile deviceProfile : devicesInter) {
            if (deviceProfile.getId().equals(str2)) {
                return deviceProfile;
            }
        }
        return null;
    }

    public List<DeviceProfile> getDevicesInter(String str, boolean z) {
        if (str == null) {
            DsLog.et(TAG, "package name is null", new Object[0]);
            return ProfileUtilsSdk.nullList();
        }
        if (!z) {
            DsLog.dt(TAG, RESULTS_FROM_DATABASE, new Object[0]);
            return queryDevicesFromDatabase(str);
        }
        updateExpiredAccount();
        new RequestAgentSdk().getDevicesFromCloud(this.mContext, "", "", "", 3000L);
        return queryDevicesFromDatabase(str);
    }

    private void updateExpiredAccount() {
        AccountClientSdk.getInstance(this.mContext).updateExpiredAccount();
    }

    private List<DeviceProfile> queryDevicesFromDatabase(String str) {
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/devices");
        ArrayList arrayList = new ArrayList();
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.it(TAG, "getDevicesInter profileStrMap is null", new Object[0]);
            return ProfileUtilsSdk.nullList();
        }
        if (profileNew.isEmpty()) {
            DsLog.it(TAG, "getDevicesInter profileStrMap is empty", new Object[0]);
            return arrayList;
        }
        for (Map.Entry<String, Object> entry : profileNew.entrySet()) {
            Map<String, Object> castMap = ClassUtil.castMap(entry.getValue(), String.class, Object.class);
            if (!castMap.isEmpty()) {
                putWiseDeviceIdToEntity(castMap, entry.getKey());
                DeviceProfile deviceProfile = new DeviceProfile();
                deviceProfile.setId(entry.getKey());
                deviceProfile.addEntities(castMap);
                arrayList.add(deviceProfile);
            }
        }
        return arrayList;
    }

    public List<DeviceProfile> getDevicesById(String str, String str2, boolean z, int i) {
        return getDevicesByIdInter(str, str2, true, z, i);
    }

    public List<DeviceProfile> getDevicesByIdInter(String str, String str2, boolean z, boolean z2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            DsLog.et(TAG, "package name or devId is invalid", new Object[0]);
            return ProfileUtilsSdk.nullList();
        }
        if (z) {
            new RequestAgentSdk().getDevicesFromCloud(this.mContext, str2, "", "", 3000L);
        }
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/devices");
        ArrayList arrayList = new ArrayList();
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.it(TAG, "getDevicesByIdInter profileStrMap is null", new Object[0]);
            return ProfileUtilsSdk.nullList();
        }
        if (profileNew.isEmpty()) {
            DsLog.it(TAG, "getDevicesByIdInter profileStrMap is empty", new Object[0]);
            return arrayList;
        }
        Map<String, Object> castMap = ClassUtil.castMap(profileNew.get(str2), String.class, Object.class);
        if (!castMap.isEmpty()) {
            putWiseDeviceIdToEntity(castMap, str2);
            DeviceProfile deviceProfile = new DeviceProfile();
            deviceProfile.setId(str2);
            deviceProfile.addEntities(castMap);
            arrayList.add(deviceProfile);
        }
        DsLog.it(TAG, "getDevicesByIdInter list size = " + arrayList.size(), new Object[0]);
        return arrayList;
    }

    public List<DeviceProfile> getDevicesByType(String str, String str2, boolean z, int i) {
        return getDevicesByTypeInter(str, str2, true, z, i);
    }

    public List<DeviceProfile> getDevicesByTypeInter(String str, String str2, boolean z, boolean z2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            DsLog.et(TAG, "package name or devType is invalid", new Object[0]);
            return ProfileUtilsSdk.nullList();
        }
        if (z) {
            new RequestAgentSdk().getDevicesFromCloud(this.mContext, "", "", str2, 3000L);
        }
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/devices");
        ArrayList arrayList = new ArrayList();
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.it(TAG, "getDevicesByTypeInter profileStrMap is null", new Object[0]);
            return ProfileUtilsSdk.nullList();
        }
        if (profileNew.isEmpty()) {
            DsLog.it(TAG, "getDevicesByTypeInter profileStrMap is empty", new Object[0]);
            return arrayList;
        }
        for (Map.Entry<String, Object> entry : profileNew.entrySet()) {
            Map<String, Object> castMap = ClassUtil.castMap(entry.getValue(), String.class, Object.class);
            if (!castMap.isEmpty() && str2.equals(castMap.get("devType"))) {
                putWiseDeviceIdToEntity(castMap, entry.getKey());
                DeviceProfile deviceProfile = new DeviceProfile();
                deviceProfile.setId(entry.getKey());
                deviceProfile.addEntities(castMap);
                arrayList.add(deviceProfile);
            }
        }
        return arrayList;
    }

    public List<ServiceProfile> getServicesOfDevice(String str, String str2, boolean z, int i) {
        return getServicesOfDeviceInter(str, str2, z);
    }

    public List<ServiceProfile> getServicesOfDeviceInter(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            DsLog.et(TAG, PARAMS_NULL, new Object[0]);
        }
        if (!z) {
            DsLog.dt(TAG, RESULTS_FROM_DATABASE, new Object[0]);
            return queryServicesFromDatabase(str, str2);
        }
        updateExpiredAccount();
        new RequestAgentSdk().getServicesOfDeviceFromCloud(this.mContext, str2, 3000L);
        return queryServicesFromDatabase(str, str2);
    }

    private List<ServiceProfile> queryServicesFromDatabase(String str, String str2) {
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/" + str2 + "/services");
        ArrayList arrayList = new ArrayList();
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.wt(TAG, "getServicesOfDeviceInter profileStrMap is null", new Object[0]);
            return ProfileUtilsSdk.nullList();
        }
        if (profileNew.isEmpty()) {
            DsLog.wt(TAG, "getServicesOfDeviceInter profileStrMap is empty", new Object[0]);
            return arrayList;
        }
        for (Map.Entry<String, Object> entry : profileNew.entrySet()) {
            Map<String, Object> castMap = ClassUtil.castMap(entry.getValue(), String.class, Object.class);
            if (!castMap.isEmpty()) {
                ServiceProfile serviceProfile = new ServiceProfile();
                Object obj = castMap.get("type");
                serviceProfile.setType(obj != null ? obj.toString() : "");
                serviceProfile.setId(entry.getKey());
                serviceProfile.setType(castMap.get("type").toString());
                serviceProfile.setDeviceId(str2);
                serviceProfile.addEntities(castMap);
                arrayList.add(serviceProfile);
            }
        }
        return arrayList;
    }

    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            DsLog.et(TAG, PARAMS_NULL, new Object[0]);
            return null;
        }
        if (!isServiceExist(str, str2, str3)) {
            DsLog.et(TAG, "getServiceCharacteristics failed, reason is ServiceProfile of devId does not exist.", new Object[0]);
            return new ServiceCharacteristicProfile();
        }
        return getServiceCharacteristicsInter(str, str2, str3, z);
    }

    public ServiceCharacteristicProfile getServiceCharacteristicsInter(String str, String str2, String str3, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            DsLog.et(TAG, PARAMS_NULL, new Object[0]);
            return null;
        }
        if (!z) {
            DsLog.dt(TAG, RESULTS_FROM_DATABASE, new Object[0]);
            return queryCharacterFromDatabase(str, str2, str3);
        }
        updateExpiredAccount();
        new RequestAgentSdk().getServiceCharacteristicsFromCloud(this.mContext, str2, str3, 3000L);
        return queryCharacterFromDatabase(str, str2, str3);
    }

    private ServiceCharacteristicProfile queryCharacterFromDatabase(String str, String str2, String str3) {
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/" + str2 + "/" + str3 + "/characteristics");
        ServiceCharacteristicProfile serviceCharacteristicProfile = new ServiceCharacteristicProfile();
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.wt(TAG, "getServiceCharacteristicsInter profileStrMap is null", new Object[0]);
            return null;
        }
        if (profileNew.isEmpty()) {
            DsLog.wt(TAG, "getServiceCharacteristicsInter profileStrMap is empty", new Object[0]);
            return serviceCharacteristicProfile;
        }
        serviceCharacteristicProfile.setDeviceId(str2);
        serviceCharacteristicProfile.setServiceId(str3);
        serviceCharacteristicProfile.addEntities(profileNew);
        return serviceCharacteristicProfile;
    }

    public boolean deleteDevice(String str, String str2) {
        if (str == null || str2 == null) {
            DsLog.et(TAG, PARAMS_NULL, new Object[0]);
            return false;
        }
        Map<String, Object> profileNew = getProfileNew(str, "all_profiles/" + str2 + "/services");
        if (ProfileUtilsSdk.isNull(profileNew) || !profileNew.isEmpty()) {
            DsLog.et(TAG, "deleteDevice failed, reason is serviceProfileStrMap is null or not empty.", new Object[0]);
            return false;
        }
        Map<String, ?> profileNew2 = getProfileNew(str, "all_profiles/devices");
        if (profileNew2 == null) {
            DsLog.et(TAG, "deleteDevice failed, reason is devProfileStrMap is null", new Object[0]);
            return false;
        }
        if (profileNew2.isEmpty() || !profileNew2.containsKey(str2)) {
            DsLog.it(TAG, "deleteDevice success, reason is devProfileStrMap is empty or does not have devId", new Object[0]);
            return true;
        }
        profileNew2.remove(str2);
        if (profileNew2.isEmpty()) {
            DatabaseFactory.generateDb(this.mContext).remove(str, "all_profiles/devices");
        }
        boolean put = DatabaseFactory.generateDb(this.mContext).put(str, new DBEntity("all_profiles/devices", new ProfileJson().toJson(profileNew2)));
        String cloudDevId = this.profileUtilsSdk.getCloudDevId(str2);
        if (put && !TextUtils.isEmpty(cloudDevId)) {
            this.profileUtilsSdk.saveResendIndex("devices", str2, "deleteDevice");
            ProfileTaskPoolSdk.getInstance().uploadLater(this.mContext);
        } else {
            StringBuilder sb = new StringBuilder(" Failed to delete device from cloud, error: cloud device id is empty: ");
            sb.append(!TextUtils.isEmpty(cloudDevId));
            sb.append(" delete service result: ");
            sb.append(put);
            DsLog.et(TAG, sb.toString(), new Object[0]);
        }
        return put;
    }

    public void queryDeleteDeviceHard(String str, String str2) {
        if (str == null || str2 == null) {
            DsLog.wt(TAG, " pkgName or devId is null", new Object[0]);
        }
        this.devices.add(str2);
        String str3 = "all_profiles/" + str2 + "/services";
        this.services.add(str3);
        Map<String, Object> profileNew = getProfileNew(str, str3);
        Set<String> keySet = !ProfileUtilsSdk.isNull(profileNew) ? profileNew.keySet() : null;
        if (keySet == null || keySet.isEmpty()) {
            DsLog.et(TAG, "serviceIdList is null or empty", new Object[0]);
            return;
        }
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            this.characters.add("all_profiles/" + str2 + "/" + it.next() + "/characteristics");
        }
    }

    public boolean deleteDeviceListHard(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        DsLog.it(TAG, "delete all profile of device", new Object[0]);
        List<String> list = this.characters;
        if (list != null && !list.isEmpty() && !DatabaseFactory.generateDb(this.mContext).removeList(str, this.characters)) {
            DsLog.wt(TAG, "delete all character hard failed", new Object[0]);
        }
        List<String> list2 = this.services;
        if (list2 != null && !list2.isEmpty() && !DatabaseFactory.generateDb(this.mContext).removeList(str, this.services)) {
            DsLog.wt(TAG, "delete all service hard failed", new Object[0]);
        }
        if (this.devices.isEmpty()) {
            return false;
        }
        Map<String, ?> profileNew = getProfileNew(str, "all_profiles/devices");
        if (profileNew == null) {
            DsLog.wt(TAG, " devices are null, not to delete device profile", new Object[0]);
            return false;
        }
        for (String str2 : this.devices) {
            if (profileNew.isEmpty()) {
                break;
            }
            if (TextUtils.isEmpty(str2) || !profileNew.containsKey(str2)) {
                DsLog.it(TAG, " devices are empty or don't have matched devId", new Object[0]);
            } else {
                profileNew.remove(str2);
            }
        }
        if (profileNew.isEmpty()) {
            DsLog.it(TAG, "delete all_profile", new Object[0]);
            DatabaseFactory.generateDb(this.mContext).remove(str, "all_profiles/devices");
        }
        DatabaseFactory.generateDb(this.mContext).put(str, new DBEntity("all_profiles/devices", new ProfileJson().toJson(profileNew)));
        this.devices.clear();
        this.services.clear();
        this.characters.clear();
        return true;
    }

    public void deleteServiceAndCharacterHard(String str, String str2, Set<String> set) {
        if (set == null || set.isEmpty()) {
            DsLog.et(TAG, "serviceIdList is null or empty, not to delete", new Object[0]);
            return;
        }
        if (!DatabaseFactory.generateDb(this.mContext).remove(str, "all_profiles/" + str2 + "/services")) {
            DsLog.et(TAG, "delete all service hard failed", new Object[0]);
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            if (!DatabaseFactory.generateDb(this.mContext).remove(str, "all_profiles/" + str2 + "/" + it.next() + "/characteristics")) {
                DsLog.et(TAG, "delete all character hard failed", new Object[0]);
            }
        }
    }

    public boolean deleteServiceOfDevice(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            DsLog.et(TAG, "Some input params may be null, check package name, device id and service id.", new Object[0]);
            return false;
        }
        if (!checkCharacterProfileMap(str, "all_profiles/" + str2 + "/" + str3 + "/characteristics")) {
            DsLog.et(TAG, " CharacterProfileMap is invalid", new Object[0]);
            return false;
        }
        String str4 = "all_profiles/" + str2 + "/services";
        Map<String, ?> profileNew = getProfileNew(str, str4);
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.wt(TAG, "deleteServiceOfDevice failed, reason is serviceProfileStrMap is null", new Object[0]);
            return false;
        }
        if (profileNew.isEmpty() || !profileNew.containsKey(str3)) {
            DsLog.it(TAG, "deleteServiceOfDevice success, reason is map is empty or does not have sid ", new Object[0]);
            return true;
        }
        profileNew.remove(str3);
        if (profileNew.isEmpty()) {
            HashSet hashSet = new HashSet();
            hashSet.add(str3);
            deleteServiceAndCharacterHard(str, str2, hashSet);
        }
        DBEntity dBEntity = new DBEntity(str4, new ProfileJson().toJson(profileNew));
        String cloudDevId = this.profileUtilsSdk.getCloudDevId(str2);
        boolean put = DatabaseFactory.generateDb(this.mContext).put(str, dBEntity);
        if (put && !TextUtils.isEmpty(cloudDevId)) {
            this.profileUtilsSdk.saveResendIndex("services", str2, "deleteService/" + System.currentTimeMillis());
            ProfileTaskPoolSdk.getInstance().uploadLater(this.mContext);
        } else {
            DsLog.et(TAG, "Failed to delete service from cloud, error: cloud device id is empty: " + TextUtils.isEmpty(cloudDevId) + " delete character result: " + put, new Object[0]);
        }
        return put;
    }

    private boolean checkCharacterProfileMap(String str, String str2) {
        Map<String, Object> profileNew = getProfileNew(str, str2);
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.wt(TAG, "deleteServiceOfDevice failed, reason is serviceCharaProfileStrMap is null", new Object[0]);
            return false;
        }
        if (profileNew.isEmpty()) {
            return true;
        }
        Iterator<Object> it = profileNew.values().iterator();
        while (it.hasNext()) {
            if (!"".equals(it.next())) {
                DsLog.et(TAG, "deleteServiceOfDevice failed, reason is serviceCharacteristic is not empty", new Object[0]);
                return false;
            }
        }
        return true;
    }

    public boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || str3 == null || str4 == null) {
            DsLog.et(TAG, "Some input params may be null, check package name, device id, service id and charkey", new Object[0]);
            return false;
        }
        String str5 = "all_profiles/" + str2 + "/" + str3 + "/characteristics";
        Map<String, ?> profileNew = getProfileNew(str, str5);
        if (ProfileUtilsSdk.isNull(profileNew)) {
            DsLog.wt(TAG, "deleteServiceCharacteristic failed, reason is parameters exception", new Object[0]);
            return false;
        }
        if (profileNew.isEmpty() || !profileNew.containsKey(str4) || "".equals(profileNew.get(str4))) {
            DsLog.it(TAG, "deleteServiceCharacteristic success, reason is characteristicProfileStrMap is empty or does not has charId or value of charId is empty", new Object[0]);
            return true;
        }
        profileNew.put(str4, "");
        if (profileNew.isEmpty()) {
            DatabaseFactory.generateDb(this.mContext).remove(str, str5);
        }
        boolean put = DatabaseFactory.generateDb(this.mContext).put(str, new DBEntity(str5, new ProfileJson().toJson(profileNew)));
        String cloudDevId = this.profileUtilsSdk.getCloudDevId(str2);
        if (put && !TextUtils.isEmpty(cloudDevId)) {
            this.profileUtilsSdk.saveResendIndex("characteristics", str2 + "/" + str3, "deleteCharacter/" + System.currentTimeMillis());
            ProfileTaskPoolSdk.getInstance().uploadLater(this.mContext);
        } else {
            StringBuilder sb = new StringBuilder("Failed to delete characteristic from cloud, error: cloud device id is empty: ");
            sb.append(!TextUtils.isEmpty(cloudDevId));
            sb.append(" delete service result: ");
            sb.append(put);
            DsLog.et(TAG, sb.toString(), new Object[0]);
        }
        return put;
    }

    private boolean putProfile(String str, String str2, ProfileValue profileValue) {
        DBEntity newProfileRecord;
        DsLog.dt(TAG, "putProfile", new Object[0]);
        if (str == null || str2 == null) {
            DsLog.et(TAG, "putProfileNew failed, reason is invalid pkgName or dbKey", new Object[0]);
            return false;
        }
        if (profileValue == null || !profileValue.verify()) {
            DsLog.et(TAG, "putProfile failed: null or invalid json value.", new Object[0]);
            return false;
        }
        String str3 = DatabaseFactory.generateDb(this.mContext).get(str, str2);
        if (profileValue instanceof ServiceCharacteristicProfile) {
            newProfileRecord = newCharacterProfileRecord(str3, profileValue, str2);
        } else {
            newProfileRecord = newProfileRecord(str3, profileValue, str2);
        }
        if (newProfileRecord == null || !newProfileRecord.verify()) {
            DsLog.et(TAG, "putProfile failed: null or invalid DBEntity", new Object[0]);
            return false;
        }
        boolean put = DatabaseFactory.generateDb(this.mContext).put(str, newProfileRecord);
        if (!put) {
            DsLog.et(TAG, "putProfile failed, reason is putInDatabase failed.", new Object[0]);
        }
        return put;
    }

    private boolean batchPutProfile(String str, String str2, List<? extends ProfileValue> list) {
        DsLog.dt(TAG, "batchPutProfile", new Object[0]);
        if (str == null || str2 == null || list == null || list.isEmpty()) {
            DsLog.et(TAG, "batchPutProfile failed, reason is invalid pkgName or dbKey or profileValueList", new Object[0]);
            return false;
        }
        for (ProfileValue profileValue : list) {
            if (profileValue == null || !profileValue.verify()) {
                DsLog.et(TAG, "batchPutProfile failed: null or invalid json value.", new Object[0]);
                return false;
            }
        }
        DBEntity newProfileRecordFromBatch = newProfileRecordFromBatch(DatabaseFactory.generateDb(this.mContext).get(str, str2), list, str2);
        if (newProfileRecordFromBatch == null || !newProfileRecordFromBatch.verify()) {
            DsLog.et(TAG, "batchPutProfile failed: null or invalid DBEntity", new Object[0]);
            return false;
        }
        boolean put = DatabaseFactory.generateDb(this.mContext).put(str, newProfileRecordFromBatch);
        if (!put) {
            DsLog.et(TAG, "batchPutProfile failed, reason is putInDatabase failed.", new Object[0]);
        }
        return put;
    }

    private Map<String, Object> getProfileNew(String str, String str2) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str2)) {
            return ProfileUtilsSdk.nullMap();
        }
        String str3 = DatabaseFactory.generateDb(this.mContext).get(str, str2);
        if (str3 == null) {
            return ProfileUtilsSdk.nullMap();
        }
        return TextUtils.isEmpty(str3) ? hashMap : new ProfileJson().fromJson(JsonUtils.sanitize(str3));
    }

    private JsonObject getProfileJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return new JsonObject();
        }
        return JsonParser.parseString(JsonUtils.sanitize(str)).getAsJsonObject();
    }

    private DBEntity newProfileRecord(String str, ProfileValue profileValue, String str2) {
        DsLog.dt(TAG, "newProfileRecord start", new Object[0]);
        if (profileValue.getId() == null) {
            DsLog.et(TAG, "newProfileRecord failed, reason is profileId is null", new Object[0]);
            return null;
        }
        Map<String, Object> profile = profileValue.getProfile();
        if (profile == null || profile.isEmpty()) {
            DsLog.et(TAG, "newProfileRecord failed, reason is parameter failed", new Object[0]);
            return null;
        }
        DBEntity dBEntity = new DBEntity();
        dBEntity.setEntityKey(str2);
        dBEntity.setEntityValue(newProfileRecord(getProfileJson(str), profileValue).toString());
        return dBEntity;
    }

    private JsonObject newProfileRecord(JsonObject jsonObject, ProfileValue profileValue) {
        String id = profileValue.getId();
        if (id == null) {
            DsLog.et(TAG, "newProfileRecord failed, reason is profile id null", new Object[0]);
            return jsonObject;
        }
        Map<String, ?> profile = profileValue.getProfile();
        if (profile == null || profile.isEmpty()) {
            DsLog.et(TAG, "newProfileRecord failed, reason is parameter failed", new Object[0]);
            return jsonObject;
        }
        JsonObject asJsonObject = JsonParser.parseString(JsonUtils.sanitize(new ProfileJson().toJson(profile))).getAsJsonObject();
        if (jsonObject.has(id)) {
            Map<String, ?> fromJson = new ProfileJson().fromJson(JsonUtils.sanitize(jsonObject.get(id).getAsJsonObject().toString()));
            if (!profile.isEmpty()) {
                fromJson.putAll(profile);
                JsonObject asJsonObject2 = JsonParser.parseString(JsonUtils.sanitize(new ProfileJson().toJson(fromJson))).getAsJsonObject();
                jsonObject.remove(id);
                jsonObject.add(id, asJsonObject2);
            }
        } else {
            jsonObject.add(id, asJsonObject);
        }
        return jsonObject;
    }

    private DBEntity newProfileRecordFromBatch(String str, List<? extends ProfileValue> list, String str2) {
        DsLog.dt(TAG, "newProfileRecordFromBatch start", new Object[0]);
        JsonObject profileJson = getProfileJson(str);
        Iterator<? extends ProfileValue> it = list.iterator();
        while (it.hasNext()) {
            profileJson = newProfileRecord(profileJson, it.next());
        }
        DBEntity dBEntity = new DBEntity();
        dBEntity.setEntityKey(str2);
        dBEntity.setEntityValue(profileJson.toString());
        return dBEntity;
    }

    private DBEntity newCharacterProfileRecord(String str, ProfileValue profileValue, String str2) {
        Map<String, ?> profile = profileValue.getProfile();
        if (profile == null || profile.isEmpty()) {
            DsLog.et(TAG, "newCharacterProfileRecord failed, reason is parameter failed", new Object[0]);
            return null;
        }
        JsonObject asJsonObject = JsonParser.parseString(JsonUtils.sanitize(new ProfileJson().toJson(profile))).getAsJsonObject();
        DBEntity dBEntity = new DBEntity();
        dBEntity.setEntityKey(str2);
        if (TextUtils.isEmpty(str)) {
            dBEntity.setEntityValue(asJsonObject.toString());
            return dBEntity;
        }
        JsonObject asJsonObject2 = JsonParser.parseString(JsonUtils.sanitize(str)).getAsJsonObject();
        for (String str3 : profile.keySet()) {
            if (asJsonObject2.has(str3)) {
                asJsonObject2.remove(str3);
                asJsonObject2.add(str3, asJsonObject.get(str3));
            } else {
                asJsonObject2.add(str3, asJsonObject.get(str3));
            }
        }
        dBEntity.setEntityValue(asJsonObject2.toString());
        return dBEntity;
    }

    private void putWiseDeviceIdToEntity(Map<String, Object> map, String str) {
        String cloudDevId = this.profileUtilsSdk.getCloudDevId(str);
        if (TextUtils.isEmpty(cloudDevId)) {
            return;
        }
        map.put(WISE_DEVICE_ID_KEY, cloudDevId);
    }
}
