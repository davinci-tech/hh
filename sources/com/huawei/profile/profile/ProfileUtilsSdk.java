package com.huawei.profile.profile;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.profile.account.AccountClientSdk;
import com.huawei.profile.datamanager.AbstractDatabase;
import com.huawei.profile.datamanager.DatabaseFactory;
import com.huawei.profile.kv.DBEntity;
import com.huawei.profile.kv.ProfileSdkStore;
import com.huawei.profile.kv.ProfileValue;
import com.huawei.profile.utils.JsonUtils;
import com.huawei.profile.utils.logger.DsLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public final class ProfileUtilsSdk {
    public static final String CHARACTER_KEY = "characteristics";
    private static final int DEFAULT_DATA_SOURCE = 1;
    private static final String DEFAULT_WRONG_CLOUD_BASE_VERSION_MESSAGE = "get cloud base version failed";
    public static final String DEVICES_KEY = "devices";
    public static final String DEVICE_ID = "deviceId";
    private static final String DEVICE_PROFILE_CLOUD_BASE_VERSION = "device_profile_cloud_base_version";
    public static final String DEV_ID_AND_CLOUD_INDEX = "com.huawei.profile/localDevidIntoCloudDevid";
    public static final String DEV_TYPE = "devType";
    public static final String EMPTY_STR = "";
    public static final String HW_TIMESTAMP = "timestamp";
    public static final boolean IS_NOT_NEED_CLOUD = false;
    public static final String NEED_CLOUD_DEVICE = "needCloudDevice";
    public static final String PROFILE_PACKAGE_NAME = "com.huawei.profile";
    public static final String PROFILE_TAG = "all_profiles";
    public static final String RESEND_DELETE_CHARACTER = "deleteCharacter";
    public static final String RESEND_DELETE_DEVICE = "deleteDevice";
    public static final String RESEND_DELETE_SERVICE = "deleteService";
    public static final String RESEND_INDEX_KEY = "com.huawei.profile/reSendIndexKey";
    public static final String RESEND_PUT_CHARACTER = "putCharacter";
    public static final String RESEND_PUT_DEVICE = "putDevice";
    public static final String RESEND_PUT_SERVICE = "putService";
    public static final String SEPARATOR = "/";
    public static final String SERVICES_KEY = "services";
    public static final String TAG = "ProfileUtilsSdk";
    public static final String TYPE = "type";
    private static ProfileSdkStore profileSdkStore;
    private Context mContext;
    public static final List<?> NULL_LIST = new ArrayList();
    public static final Map<?, ?> NULL_MAP = new HashMap();
    private static final Object LOCK = new Object();
    private static ProfileUtilsSdk singleton = null;

    private ProfileUtilsSdk(Context context) {
        this.mContext = context;
    }

    public static ProfileUtilsSdk getInstance(Context context) {
        ProfileUtilsSdk profileUtilsSdk;
        synchronized (LOCK) {
            if (singleton == null) {
                singleton = new ProfileUtilsSdk(context);
                profileSdkStore = new ProfileSdkStore(context);
            }
            profileUtilsSdk = singleton;
        }
        return profileUtilsSdk;
    }

    public Map<String, ProfileValue> putProfilesToMap(List list) {
        HashMap hashMap = new HashMap(list.size());
        for (Object obj : list) {
            if (obj instanceof ProfileValue) {
                ProfileValue profileValue = (ProfileValue) obj;
                hashMap.put(profileValue.getId(), profileValue);
            }
        }
        return hashMap;
    }

    public List<DeviceProfile> getAllDevicesInter() {
        return profileSdkStore.getDevices("com.huawei.profile", false, 1);
    }

    public DeviceProfile getDeviceInter(String str) {
        return profileSdkStore.getDeviceByDevId("com.huawei.profile", str);
    }

    public List<ServiceProfile> getServicesInter(String str) {
        return profileSdkStore.getServicesOfDevice("com.huawei.profile", str, false, 1);
    }

    public ServiceCharacteristicProfile getCharacterInter(String str, String str2) {
        return profileSdkStore.getServiceCharacteristicsForCloudRequest("com.huawei.profile", str, str2);
    }

    public boolean putDeviceInter(DeviceProfile deviceProfile) {
        if (deviceProfile == null) {
            DsLog.et(TAG, "Some input params may be null, check device profile", new Object[0]);
            return false;
        }
        deviceProfile.setIsNeedCloud(false);
        return profileSdkStore.putDeviceCore("com.huawei.profile", deviceProfile);
    }

    public boolean putDevicesInter(List<DeviceProfile> list) {
        Iterator<DeviceProfile> it = list.iterator();
        while (it.hasNext()) {
            it.next().setIsNeedCloud(false);
        }
        return profileSdkStore.putDevicesCore("com.huawei.profile", list);
    }

    public void putServicesInter(String str, List<ServiceProfile> list) {
        if (list == null || list.isEmpty()) {
            DsLog.et(TAG, "Some input params may be null, check service profile list", new Object[0]);
            return;
        }
        Iterator<ServiceProfile> it = list.iterator();
        while (it.hasNext()) {
            it.next().setIsNeedCloud(false);
        }
        profileSdkStore.putServicesOfDevice("com.huawei.profile", str, list);
    }

    public void putServicesCharacteristicInter(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        if (serviceCharacteristicProfile == null) {
            DsLog.et(TAG, "Some input params may be null, check character profile", new Object[0]);
        } else {
            serviceCharacteristicProfile.setIsNeedCloud(false);
            profileSdkStore.putServiceCharacteristic("com.huawei.profile", str, str2, serviceCharacteristicProfile);
        }
    }

    public void deleteServiceHard(String str, Set<String> set) {
        profileSdkStore.deleteServiceAndCharacterHard("com.huawei.profile", str, set);
    }

    public void saveCloudDevId(String str, String str2) {
        ProfileBaseUtils.saveCloudDevId(this.mContext, str, str2);
    }

    public void saveCloudDevIdList(List<String> list, List<String> list2) {
        if (list == null || list2 == null) {
            return;
        }
        ProfileBaseUtils.saveCloudDevIdList(this.mContext, list, list2);
    }

    public String getCloudDevId(String str) {
        String str2 = DatabaseFactory.generateDb(this.mContext).get("com.huawei.profile", "com.huawei.profile/localDevidIntoCloudDevid");
        if (str2 == null || str2.isEmpty()) {
            DsLog.et(TAG, "getCloudDevId failed, reason is dbVal is null", new Object[0]);
            return "";
        }
        try {
            JsonObject asJsonObject = JsonParser.parseString(JsonUtils.sanitize(str2)).getAsJsonObject();
            if (asJsonObject.has(str)) {
                return asJsonObject.get(str).getAsString();
            }
        } catch (IllegalStateException | UnsupportedOperationException unused) {
            DsLog.et(TAG, "an error happens when get string value from json.", new Object[0]);
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0066 A[Catch: all -> 0x00b4, TryCatch #0 {, blocks: (B:9:0x0004, B:12:0x000c, B:15:0x0014, B:17:0x001c, B:19:0x0024, B:21:0x002c, B:24:0x0037, B:26:0x0047, B:29:0x004e, B:30:0x0060, B:32:0x0066, B:34:0x0072, B:35:0x0075, B:38:0x006b, B:39:0x005b, B:4:0x00a9), top: B:8:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0072 A[Catch: all -> 0x00b4, TryCatch #0 {, blocks: (B:9:0x0004, B:12:0x000c, B:15:0x0014, B:17:0x001c, B:19:0x0024, B:21:0x002c, B:24:0x0037, B:26:0x0047, B:29:0x004e, B:30:0x0060, B:32:0x0066, B:34:0x0072, B:35:0x0075, B:38:0x006b, B:39:0x005b, B:4:0x00a9), top: B:8:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006b A[Catch: all -> 0x00b4, TryCatch #0 {, blocks: (B:9:0x0004, B:12:0x000c, B:15:0x0014, B:17:0x001c, B:19:0x0024, B:21:0x002c, B:24:0x0037, B:26:0x0047, B:29:0x004e, B:30:0x0060, B:32:0x0066, B:34:0x0072, B:35:0x0075, B:38:0x006b, B:39:0x005b, B:4:0x00a9), top: B:8:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void saveResendIndex(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            if (r6 == 0) goto La9
            boolean r1 = r6.isEmpty()     // Catch: java.lang.Throwable -> Lb4
            if (r1 != 0) goto La9
            if (r7 == 0) goto La9
            boolean r1 = r7.isEmpty()     // Catch: java.lang.Throwable -> Lb4
            if (r1 == 0) goto L14
            goto La9
        L14:
            java.lang.String r1 = "devices"
            boolean r1 = r5.equals(r1)     // Catch: java.lang.Throwable -> Lb4
            if (r1 != 0) goto L37
            java.lang.String r1 = "services"
            boolean r1 = r5.equals(r1)     // Catch: java.lang.Throwable -> Lb4
            if (r1 != 0) goto L37
            java.lang.String r1 = "characteristics"
            boolean r1 = r5.equals(r1)     // Catch: java.lang.Throwable -> Lb4
            if (r1 != 0) goto L37
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r6 = "ProfileUtilsSdk"
            java.lang.String r7 = "saveDevResentIndex failed, reason is indexSort is invalid"
            com.huawei.profile.utils.logger.DsLog.et(r6, r7, r5)     // Catch: java.lang.Throwable -> Lb4
            monitor-exit(r4)
            return
        L37:
            android.content.Context r1 = r4.mContext     // Catch: java.lang.Throwable -> Lb4
            com.huawei.profile.datamanager.AbstractDatabase r1 = com.huawei.profile.datamanager.DatabaseFactory.generateDb(r1)     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = "com.huawei.profile"
            java.lang.String r3 = "com.huawei.profile/reSendIndexKey"
            java.lang.String r1 = r1.get(r2, r3)     // Catch: java.lang.Throwable -> Lb4
            if (r1 == 0) goto L5b
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lb4
            if (r2 == 0) goto L4e
            goto L5b
        L4e:
            java.lang.String r1 = com.huawei.profile.utils.JsonUtils.sanitize(r1)     // Catch: java.lang.Throwable -> Lb4
            com.google.gson.JsonElement r1 = com.google.gson.JsonParser.parseString(r1)     // Catch: java.lang.Throwable -> Lb4
            com.google.gson.JsonObject r1 = r1.getAsJsonObject()     // Catch: java.lang.Throwable -> Lb4
            goto L60
        L5b:
            com.google.gson.JsonObject r1 = new com.google.gson.JsonObject     // Catch: java.lang.Throwable -> Lb4
            r1.<init>()     // Catch: java.lang.Throwable -> Lb4
        L60:
            boolean r2 = r1.has(r5)     // Catch: java.lang.Throwable -> Lb4
            if (r2 == 0) goto L6b
            com.google.gson.JsonObject r2 = r1.getAsJsonObject(r5)     // Catch: java.lang.Throwable -> Lb4
            goto L70
        L6b:
            com.google.gson.JsonObject r2 = new com.google.gson.JsonObject     // Catch: java.lang.Throwable -> Lb4
            r2.<init>()     // Catch: java.lang.Throwable -> Lb4
        L70:
            if (r2 == 0) goto L75
            r2.addProperty(r6, r7)     // Catch: java.lang.Throwable -> Lb4
        L75:
            r1.add(r5, r2)     // Catch: java.lang.Throwable -> Lb4
            com.huawei.profile.kv.DBEntity r5 = new com.huawei.profile.kv.DBEntity     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r6 = "com.huawei.profile/reSendIndexKey"
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Throwable -> Lb4
            r5.<init>(r6, r7)     // Catch: java.lang.Throwable -> Lb4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r7 = "dbEntity key is "
            r6.<init>(r7)     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r7 = "ProfileUtilsSdk"
            java.lang.String r1 = r5.getEntityKey()     // Catch: java.lang.Throwable -> Lb4
            r6.append(r1)     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lb4
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> Lb4
            com.huawei.profile.utils.logger.DsLog.it(r7, r6, r0)     // Catch: java.lang.Throwable -> Lb4
            android.content.Context r6 = r4.mContext     // Catch: java.lang.Throwable -> Lb4
            com.huawei.profile.datamanager.AbstractDatabase r6 = com.huawei.profile.datamanager.DatabaseFactory.generateDb(r6)     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r7 = "com.huawei.profile"
            r6.put(r7, r5)     // Catch: java.lang.Throwable -> Lb4
            monitor-exit(r4)
            return
        La9:
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r6 = "ProfileUtilsSdk"
            java.lang.String r7 = "saveDevResentIndex failed, reason is parameter is invalid"
            com.huawei.profile.utils.logger.DsLog.et(r6, r7, r5)     // Catch: java.lang.Throwable -> Lb4
            monitor-exit(r4)
            return
        Lb4:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.profile.profile.ProfileUtilsSdk.saveResendIndex(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public Map<String, Map<String, String>> getAllResendIndex() {
        synchronized (this) {
            String str = DatabaseFactory.generateDb(this.mContext).get("com.huawei.profile", "com.huawei.profile/reSendIndexKey");
            if (str != null && !str.isEmpty()) {
                JsonObject asJsonObject = JsonParser.parseString(JsonUtils.sanitize(str)).getAsJsonObject();
                JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("devices");
                JsonObject asJsonObject3 = asJsonObject.getAsJsonObject("services");
                JsonObject asJsonObject4 = asJsonObject.getAsJsonObject("characteristics");
                HashMap hashMap = new HashMap();
                Gson gson = new Gson();
                if (asJsonObject2 != null) {
                    hashMap.put("devices", (Map) gson.fromJson(JsonUtils.sanitize(asJsonObject2.toString()), Map.class));
                }
                if (asJsonObject3 != null) {
                    hashMap.put("services", (Map) gson.fromJson(JsonUtils.sanitize(asJsonObject3.toString()), Map.class));
                }
                if (asJsonObject4 != null) {
                    hashMap.put("characteristics", (Map) gson.fromJson(JsonUtils.sanitize(asJsonObject4.toString()), Map.class));
                }
                return hashMap;
            }
            DsLog.wt(TAG, "getAllResendIndex failed, reason is dbVal is invalid", new Object[0]);
            return Collections.emptyMap();
        }
    }

    public boolean removeResendIndex(String str, String str2) {
        synchronized (this) {
            if (str2 != null) {
                if (!str2.isEmpty()) {
                    if (!str.equals("devices") && !str.equals("services") && !str.equals("characteristics")) {
                        DsLog.et(TAG, "saveDevResentIndex failed, reason is indexSort is invalid", new Object[0]);
                        return false;
                    }
                    String str3 = DatabaseFactory.generateDb(this.mContext).get("com.huawei.profile", "com.huawei.profile/reSendIndexKey");
                    if (str3 != null && !str3.isEmpty()) {
                        JsonObject asJsonObject = JsonParser.parseString(JsonUtils.sanitize(str3)).getAsJsonObject();
                        if (asJsonObject.has(str)) {
                            JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(str);
                            if (asJsonObject2 != null) {
                                asJsonObject2.remove(str2);
                            }
                            asJsonObject.add(str, asJsonObject2);
                            return DatabaseFactory.generateDb(this.mContext).put("com.huawei.profile", new DBEntity("com.huawei.profile/reSendIndexKey", asJsonObject.toString()));
                        }
                        DsLog.it(TAG, "removeResendIndex success, reason is detailIndex is not exist", new Object[0]);
                        return true;
                    }
                    DsLog.it(TAG, "removeResendIndex success, reason is index is not exist", new Object[0]);
                    return true;
                }
            }
            DsLog.et(TAG, "removeResendIndex failed, reason is parameter is invalid", new Object[0]);
            return false;
        }
    }

    public void removeResendIndexOfCharacter(String str, String str2) {
        synchronized (this) {
            Map<String, Map<String, String>> allResendIndex = getAllResendIndex();
            if (allResendIndex != null && !allResendIndex.isEmpty()) {
                Map<String, String> map = allResendIndex.get("characteristics");
                if (map != null) {
                    if (map.get(str + "/" + str2).indexOf("putCharacter") >= 0) {
                        removeResendIndex("characteristics", str + "/" + str2);
                    }
                }
                return;
            }
            DsLog.dt(TAG, "removeResendIndexOfCharacter, allResendIndex is null or empty", new Object[0]);
        }
    }

    public static boolean waitForCallback(long j, CountDownLatch countDownLatch) {
        try {
            return countDownLatch.await(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            DsLog.et(TAG, "Waiting for call back is interrupted!", new Object[0]);
            return false;
        }
    }

    public void resetAccessTokenTime() {
        AccountClientSdk.getInstance(this.mContext).setAccountInvalid();
    }

    public void saveNeedCloudDevice(String str) {
        ProfileBaseUtils.saveNeedCloudDevice(this.mContext, str);
    }

    public void saveNeedCloudDevices(List<String> list) {
        ProfileBaseUtils.saveNeedCloudDevices(this.mContext, list);
    }

    public void queryDeleteDeviceHard(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        profileSdkStore.queryDeleteDeviceHard("com.huawei.profile", str);
    }

    public void deleteDeviceHard() {
        profileSdkStore.deleteDeviceHard("com.huawei.profile");
    }

    public void deleteAccountData() {
        boolean z;
        DsLog.it(TAG, "deleteAccountData start", new Object[0]);
        HashSet hashSet = new HashSet();
        JsonObject queryNeedCloudDevice = ProfileBaseUtils.queryNeedCloudDevice(this.mContext);
        if (queryNeedCloudDevice != null) {
            Iterator<String> it = queryNeedCloudDevice.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next());
            }
        }
        AbstractDatabase generateDb = DatabaseFactory.generateDb(this.mContext);
        String str = generateDb.get("com.huawei.profile", "com.huawei.profile/localDevidIntoCloudDevid");
        JsonObject asJsonObject = !TextUtils.isEmpty(str) ? JsonParser.parseString(str).getAsJsonObject() : null;
        if (asJsonObject != null) {
            Iterator<String> it2 = asJsonObject.keySet().iterator();
            while (it2.hasNext()) {
                hashSet.add(it2.next());
            }
        }
        Iterator it3 = hashSet.iterator();
        while (it3.hasNext()) {
            queryDeleteDeviceHard((String) it3.next());
        }
        profileSdkStore.deleteDeviceHard("com.huawei.profile");
        ProfileBaseUtils.clearCloudDeviceRecordSet(this.mContext, hashSet);
        DsLog.dt(TAG, "finish clear cloud", new Object[0]);
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.huawei.profile/reSendIndexKey");
        arrayList.add("com.huawei.profile/localDevidIntoCloudDevid");
        arrayList.add("needCloudDevice");
        Iterator it4 = arrayList.iterator();
        while (true) {
            while (it4.hasNext()) {
                z = z && generateDb.remove("com.huawei.profile", (String) it4.next());
            }
            DsLog.it(TAG, "delete account info result is " + z, new Object[0]);
            return;
        }
    }

    public static <T> List<T> nullList() {
        return (List<T>) NULL_LIST;
    }

    public static <K, V> Map<K, V> nullMap() {
        return (Map<K, V>) NULL_MAP;
    }

    public static boolean isNull(List<?> list) {
        return list == null || list == NULL_LIST;
    }

    public static boolean isNull(Map<?, ?> map) {
        return map == null || map == NULL_MAP;
    }

    public static String getCloudBaseVersion(Context context) {
        ApplicationInfo applicationInfo;
        if (context == null) {
            DsLog.et(TAG, "getCloudBaseVersion failed context is null", new Object[0]);
            return DEFAULT_WRONG_CLOUD_BASE_VERSION_MESSAGE;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            Log.e(TAG, "Failed to get packageManager.");
            return DEFAULT_WRONG_CLOUD_BASE_VERSION_MESSAGE;
        }
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "name not found: " + e.getMessage());
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            Log.e(TAG, "Failed to get applicationInfo.");
            return DEFAULT_WRONG_CLOUD_BASE_VERSION_MESSAGE;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null) {
            Log.e(TAG, "Failed to get bundle.");
            return DEFAULT_WRONG_CLOUD_BASE_VERSION_MESSAGE;
        }
        return bundle.getString(DEVICE_PROFILE_CLOUD_BASE_VERSION);
    }
}
