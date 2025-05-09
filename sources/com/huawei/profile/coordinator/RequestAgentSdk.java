package com.huawei.profile.coordinator;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.huawei.profile.coordinator.impl.RegisterDeviceToCloudSdk;
import com.huawei.profile.coordinator.task.ProfileTaskPoolSdk;
import com.huawei.profile.kv.ProfileValue;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ProfileBaseUtils;
import com.huawei.profile.profile.ProfileUtilsSdk;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.scheduler.thread.SdkThread;
import com.huawei.profile.utils.logger.DsLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes6.dex */
public class RequestAgentSdk {
    private static final int COUNT_DOWN_TIME = 1;
    private static final int EXPECTED_LENGTH = 2;
    private static final String MESSAGE = " message: ";
    private static final String TAG = "ProfileRequestAgent";
    private static final int UPLOAD_TIMEOUT = 2000;
    private ProfileUtilsSdk profileUtilsSdk;
    private static final ReentrantReadWriteLock DEVICE_LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock SERVICE_LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock CHARACTERISTICS_LOCK = new ReentrantReadWriteLock();

    public void putDeviceToCloud(final Context context, final String str, long j) {
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        DsLog.dt(TAG, "putDeviceToCloud thread start", new Object[0]);
        new SdkThread("PutDeviceToCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.1
            @Override // java.lang.Runnable
            public void run() {
                RequestAgentSdk.this.putDeviceToCloudRequest(context, str, countDownLatch);
            }
        }).start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putDeviceToCloudRequest(final Context context, final String str, final CountDownLatch countDownLatch) {
        ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
        profileRequestInputParams.setLocalDevId(str);
        profileRequestInputParams.setCloudDevId(this.profileUtilsSdk.getCloudDevId(str));
        final ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.PUT_DEVICE_TO_CLOUD, profileRequestInputParams);
        if (requestFactorySdk == null) {
            DsLog.et(TAG, "put device to cloud instance is null", new Object[0]);
            countDownLatch.countDown();
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = DEVICE_LOCK;
        reentrantReadWriteLock.writeLock().lock();
        try {
            requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.2
                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onSuccess(ProfileRequestResult profileRequestResult) {
                    if (profileRequestResult == null) {
                        DsLog.et(RequestAgentSdk.TAG, "PutDeviceToCloud requestResult is null", new Object[0]);
                        countDownLatch.countDown();
                        return;
                    }
                    if (requestFactorySdk instanceof RegisterDeviceToCloudSdk) {
                        RequestAgentSdk.this.profileUtilsSdk.saveCloudDevId(str, RequestUtilsSdk.parseCloudDevIdFromJson(profileRequestResult.getJsonResult()));
                    }
                    RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("devices", str);
                    countDownLatch.countDown();
                }

                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onFailure(int i, String str2) {
                    DsLog.et(RequestAgentSdk.TAG, "Failed to put device to cloud, errorCode: " + i + RequestAgentSdk.MESSAGE + str2, new Object[0]);
                    if (i == 404) {
                        ProfileBaseUtils.deleteCloudDevId(context, str);
                    } else if (RequestUtilsSdk.isNeedResend(context, i, str2, str)) {
                        RequestAgentSdk.this.profileUtilsSdk.saveResendIndex("devices", str, "putDevice");
                    } else {
                        RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("devices", str);
                    }
                    countDownLatch.countDown();
                }
            });
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            DEVICE_LOCK.writeLock().unlock();
            throw th;
        }
    }

    public void putServicesToCloud(final Context context, final String str, long j) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SdkThread sdkThread = new SdkThread("PutServicesToCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.3
            @Override // java.lang.Runnable
            public void run() {
                ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
                profileRequestInputParams.setLocalDevId(str);
                RequestAgentSdk.this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
                ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.PUT_SERVICES_TO_CLOUD, profileRequestInputParams);
                if (requestFactorySdk != null) {
                    RequestAgentSdk.SERVICE_LOCK.writeLock().lock();
                    try {
                        requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.3.1
                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onSuccess(ProfileRequestResult profileRequestResult) {
                                RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("services", str);
                                countDownLatch.countDown();
                            }

                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onFailure(int i, String str2) {
                                DsLog.et(RequestAgentSdk.TAG, "Failed to put services to cloud, errorCode: " + i + RequestAgentSdk.MESSAGE + str2, new Object[0]);
                                if (i == 404) {
                                    ProfileBaseUtils.deleteCloudDevId(context, str);
                                } else if (RequestUtilsSdk.isNeedResend(context, i, str2, str)) {
                                    RequestAgentSdk.this.profileUtilsSdk.saveResendIndex("services", str, "putService");
                                } else {
                                    RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("services", str);
                                }
                                countDownLatch.countDown();
                            }
                        });
                        return;
                    } finally {
                        RequestAgentSdk.SERVICE_LOCK.writeLock().unlock();
                    }
                }
                DsLog.et(RequestAgentSdk.TAG, "put service to cloud instance is null", new Object[0]);
                countDownLatch.countDown();
            }
        });
        DsLog.dt(TAG, "putServicesToCloud thread start", new Object[0]);
        sdkThread.start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    public void putServiceCharacteristicToCloud(final Context context, final String str, final String str2, long j) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
        SdkThread sdkThread = new SdkThread("PutServiceCharacteristicToCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.4
            @Override // java.lang.Runnable
            public void run() {
                ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
                profileRequestInputParams.setLocalDevId(str);
                profileRequestInputParams.setSid(str2);
                ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.PUT_SERVICE_CHARACTERISTIC_TO_CLOUD, profileRequestInputParams);
                if (requestFactorySdk != null) {
                    RequestAgentSdk.CHARACTERISTICS_LOCK.writeLock().lock();
                    try {
                        requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.4.1
                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onSuccess(ProfileRequestResult profileRequestResult) {
                                RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("characteristics", str + "/" + str2);
                                countDownLatch.countDown();
                            }

                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onFailure(int i, String str3) {
                                if (i == 404) {
                                    ProfileBaseUtils.deleteCloudDevId(context, str);
                                } else if (RequestUtilsSdk.isNeedResend(context, i, str3, str)) {
                                    RequestAgentSdk.this.profileUtilsSdk.saveResendIndex("characteristics", str + "/" + str2, "putCharacter");
                                } else {
                                    RequestAgentSdk.this.profileUtilsSdk.removeResendIndexOfCharacter(str, str2);
                                }
                                countDownLatch.countDown();
                            }
                        });
                        return;
                    } finally {
                        RequestAgentSdk.CHARACTERISTICS_LOCK.writeLock().unlock();
                    }
                }
                DsLog.et(RequestAgentSdk.TAG, "put service's characteristic to cloud instance is null", new Object[0]);
                countDownLatch.countDown();
            }
        });
        DsLog.dt(TAG, "putServiceCharacteristicToCloud thread start", new Object[0]);
        sdkThread.start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    public void getDevicesFromCloud(final Context context, final String str, final String str2, final String str3, long j) {
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SdkThread sdkThread = new SdkThread("GetDevicesFromCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.5
            @Override // java.lang.Runnable
            public void run() {
                DsLog.dt(RequestAgentSdk.TAG, "start to request devices", new Object[0]);
                if (RequestAgentSdk.this.isUploadSuccess(context)) {
                    RequestAgentSdk.this.getDevicesFromCloudRequest(context, str, str2, str3, countDownLatch);
                } else {
                    countDownLatch.countDown();
                    DsLog.wt(RequestAgentSdk.TAG, "Failed to get devices from cloud.", new Object[0]);
                }
            }
        });
        DsLog.dt(TAG, "getDevicesFromCloud thread start", new Object[0]);
        sdkThread.start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDevicesFromCloudRequest(final Context context, final String str, String str2, final String str3, final CountDownLatch countDownLatch) {
        ProfileRequestType profileRequestType = ProfileRequestType.GET_DEVICES_FROM_CLOUD;
        ProfileRequestInputParams requestInputParams = getRequestInputParams(str, str2, str3);
        ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(profileRequestType, requestInputParams);
        if (requestFactorySdk == null) {
            DsLog.et(TAG, "get devices from cloud instance is null", new Object[0]);
            countDownLatch.countDown();
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = DEVICE_LOCK;
        reentrantReadWriteLock.readLock().lock();
        try {
            requestFactorySdk.request(context, requestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.6
                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onSuccess(ProfileRequestResult profileRequestResult) {
                    if (profileRequestResult == null) {
                        DsLog.et(RequestAgentSdk.TAG, "GetDevicesFromCloud requestResult is null", new Object[0]);
                        countDownLatch.countDown();
                        return;
                    }
                    List<DeviceProfile> parseCloudResponseToDeviceProfiles = RequestUtilsSdk.parseCloudResponseToDeviceProfiles(context, profileRequestResult.getJsonResult());
                    DsLog.it(RequestAgentSdk.TAG, "device list size = " + parseCloudResponseToDeviceProfiles.size(), new Object[0]);
                    RequestAgentSdk.this.deleteRedundantDevice(context, parseCloudResponseToDeviceProfiles, str, str3);
                    DsLog.it(RequestAgentSdk.TAG, " BatchPutDevice started", new Object[0]);
                    RequestAgentSdk.this.batchSaveDevices(parseCloudResponseToDeviceProfiles);
                    DsLog.it(RequestAgentSdk.TAG, " BatchPutDevice finished", new Object[0]);
                    countDownLatch.countDown();
                }

                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onFailure(int i, String str4) {
                    DsLog.et(RequestAgentSdk.TAG, "Failed to get devices, errorCode: " + i + RequestAgentSdk.MESSAGE + str4, new Object[0]);
                    if (i == 401) {
                        RequestAgentSdk.this.profileUtilsSdk.resetAccessTokenTime();
                    }
                    countDownLatch.countDown();
                }
            });
            reentrantReadWriteLock.readLock().unlock();
        } catch (Throwable th) {
            DEVICE_LOCK.readLock().unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void batchSaveDevices(List<DeviceProfile> list) {
        DsLog.it(TAG, " putDevices count:" + list.size(), new Object[0]);
        if (this.profileUtilsSdk.putDevicesInter(list)) {
            ArrayList arrayList = new ArrayList();
            Iterator<DeviceProfile> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getId());
            }
            this.profileUtilsSdk.saveNeedCloudDevices(arrayList);
        }
    }

    private ProfileRequestInputParams getRequestInputParams(String str, String str2, String str3) {
        ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
        if (!TextUtils.isEmpty(str)) {
            profileRequestInputParams.setLocalDevId(str);
        } else if (!TextUtils.isEmpty(str2)) {
            profileRequestInputParams.setCloudDevId(str2);
        } else if (!TextUtils.isEmpty(str3)) {
            profileRequestInputParams.setDevType(str3);
        } else {
            DsLog.et(TAG, "Failed to get request input params", new Object[0]);
        }
        return profileRequestInputParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteRedundantDevice(Context context, List<DeviceProfile> list, String str, String str2) {
        Map<String, ProfileValue> putProfilesToMap = this.profileUtilsSdk.putProfilesToMap(list);
        List<DeviceProfile> allDevicesInter = this.profileUtilsSdk.getAllDevicesInter();
        if (ProfileUtilsSdk.isNull(allDevicesInter)) {
            DsLog.et(TAG, "local device is null", new Object[0]);
            return;
        }
        List<DeviceProfile> filterLocalDevices = getFilterLocalDevices(allDevicesInter, str, str2);
        JsonObject queryNeedCloudDevice = ProfileBaseUtils.queryNeedCloudDevice(context);
        deleteDevices(context, putProfilesToMap, filterLocalDevices, queryNeedCloudDevice);
        deleteNeedCloudDeviceRecord(context, putProfilesToMap, allDevicesInter, queryNeedCloudDevice);
    }

    private void deleteDevices(Context context, Map<String, ProfileValue> map, List<DeviceProfile> list, JsonObject jsonObject) {
        HashSet hashSet = new HashSet();
        for (DeviceProfile deviceProfile : list) {
            if (jsonObject.has(deviceProfile.getId()) && !map.containsKey(deviceProfile.getId()) && !ProfileBaseUtils.isResendIndexHasPutDevice(context, deviceProfile.getId())) {
                this.profileUtilsSdk.queryDeleteDeviceHard(deviceProfile.getId());
                hashSet.add(deviceProfile.getId());
            }
        }
        this.profileUtilsSdk.deleteDeviceHard();
        ProfileBaseUtils.clearCloudDeviceRecordSet(context, hashSet);
    }

    private List<DeviceProfile> getFilterLocalDevices(List<DeviceProfile> list, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return !TextUtils.isEmpty(str2) ? traversalListByDevType(list, str2) : list;
        }
        return traversalListByDevId(list, str);
    }

    private List<DeviceProfile> traversalListByDevId(List<DeviceProfile> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (DeviceProfile deviceProfile : list) {
            if (deviceProfile.getId().equals(str)) {
                arrayList.add(deviceProfile);
            }
        }
        return arrayList;
    }

    private List<DeviceProfile> traversalListByDevType(List<DeviceProfile> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (DeviceProfile deviceProfile : list) {
            if (deviceProfile.getType().equals(str)) {
                arrayList.add(deviceProfile);
            }
        }
        return arrayList;
    }

    private void deleteNeedCloudDeviceRecord(Context context, Map<String, ProfileValue> map, List<DeviceProfile> list, JsonObject jsonObject) {
        Set<String> localDevIdSet = getLocalDevIdSet(list);
        ArrayList arrayList = new ArrayList();
        for (String str : jsonObject.keySet()) {
            if (!localDevIdSet.contains(str) && !map.containsKey(str)) {
                arrayList.add(str);
            }
        }
        ProfileBaseUtils.deleteNeedCloudDevices(context, arrayList);
    }

    private Set<String> getLocalDevIdSet(List<DeviceProfile> list) {
        HashSet hashSet = new HashSet();
        Iterator<DeviceProfile> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getId());
        }
        return hashSet;
    }

    public void getServicesOfDeviceFromCloud(final Context context, final String str, long j) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SdkThread sdkThread = new SdkThread("GetServiceFromCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.7
            @Override // java.lang.Runnable
            public void run() {
                if (RequestAgentSdk.this.isUploadSuccess(context)) {
                    RequestAgentSdk.this.getServicesFromCloudRequest(context, str, countDownLatch);
                } else {
                    countDownLatch.countDown();
                    DsLog.wt(RequestAgentSdk.TAG, "Failed to get services from cloud.", new Object[0]);
                }
            }
        });
        DsLog.dt(TAG, "getServicesOfDeviceFromCloud thread start", new Object[0]);
        sdkThread.start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getServicesFromCloudRequest(final Context context, final String str, final CountDownLatch countDownLatch) {
        ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
        profileRequestInputParams.setLocalDevId(str);
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
        ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.GET_SERVICES_FROM_CLOUD, profileRequestInputParams);
        if (requestFactorySdk == null) {
            DsLog.et(TAG, "get services from cloud instance is null", new Object[0]);
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = SERVICE_LOCK;
        reentrantReadWriteLock.readLock().lock();
        try {
            requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.8
                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onSuccess(ProfileRequestResult profileRequestResult) {
                    if (profileRequestResult == null) {
                        DsLog.et(RequestAgentSdk.TAG, "GetServiceFromCloud requestResult is null", new Object[0]);
                        countDownLatch.countDown();
                        return;
                    }
                    List<ServiceProfile> parseCloudResponseToServiceProfiles = RequestUtilsSdk.parseCloudResponseToServiceProfiles(str, profileRequestResult.getJsonResult());
                    DsLog.it(RequestAgentSdk.TAG, "service list size = " + parseCloudResponseToServiceProfiles.size(), new Object[0]);
                    RequestAgentSdk.this.deleteRedundantService(context, parseCloudResponseToServiceProfiles, str);
                    countDownLatch.countDown();
                }

                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onFailure(int i, String str2) {
                    DsLog.et(RequestAgentSdk.TAG, "Failed to get services from cloud, errorCode: " + i + RequestAgentSdk.MESSAGE + str2, new Object[0]);
                    if (i == 401) {
                        RequestAgentSdk.this.profileUtilsSdk.resetAccessTokenTime();
                    }
                    countDownLatch.countDown();
                }
            });
            reentrantReadWriteLock.readLock().unlock();
        } catch (Throwable th) {
            SERVICE_LOCK.readLock().unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteRedundantService(Context context, List<ServiceProfile> list, String str) {
        Map<String, ProfileValue> putProfilesToMap = this.profileUtilsSdk.putProfilesToMap(list);
        List<ServiceProfile> servicesInter = this.profileUtilsSdk.getServicesInter(str);
        if (ProfileUtilsSdk.isNull(servicesInter)) {
            DsLog.et(TAG, "local service is null", new Object[0]);
            return;
        }
        HashSet hashSet = new HashSet();
        for (ServiceProfile serviceProfile : servicesInter) {
            if (!putProfilesToMap.containsKey(serviceProfile.getId()) && !ProfileBaseUtils.isResendIndexHasPutService(context, str)) {
                hashSet.add(serviceProfile.getId());
            }
        }
        this.profileUtilsSdk.deleteServiceHard(str, hashSet);
        DsLog.it(TAG, " BatchPutService started", new Object[0]);
        this.profileUtilsSdk.putServicesInter(str, list);
        DsLog.it(TAG, " BatchPutService finished", new Object[0]);
    }

    public void getServiceCharacteristicsFromCloud(final Context context, final String str, final String str2, long j) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SdkThread sdkThread = new SdkThread("GetServiceCharacteristicsFromCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.9
            @Override // java.lang.Runnable
            public void run() {
                if (RequestAgentSdk.this.isUploadSuccess(context)) {
                    RequestAgentSdk.this.getServiceCharacteristicsFromCloudRequest(context, str, str2, countDownLatch);
                } else {
                    countDownLatch.countDown();
                    DsLog.wt(RequestAgentSdk.TAG, "Failed to get characteristic from cloud.", new Object[0]);
                }
            }
        });
        DsLog.dt(TAG, "getServiceCharacteristicsFromCloud thread start", new Object[0]);
        sdkThread.start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getServiceCharacteristicsFromCloudRequest(Context context, final String str, final String str2, final CountDownLatch countDownLatch) {
        ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
        profileRequestInputParams.setLocalDevId(str);
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
        ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.GET_SERVICES_CHARACTERISTIC_FROM_CLOUD, profileRequestInputParams);
        if (requestFactorySdk == null) {
            DsLog.et(TAG, "get characteristic from cloud instance is null", new Object[0]);
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = CHARACTERISTICS_LOCK;
        reentrantReadWriteLock.readLock().lock();
        try {
            requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.10
                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onSuccess(ProfileRequestResult profileRequestResult) {
                    if (profileRequestResult == null) {
                        DsLog.et(RequestAgentSdk.TAG, "GetServiceCharacteristicsFromCloud requestResult is null", new Object[0]);
                        countDownLatch.countDown();
                    } else {
                        RequestAgentSdk.this.profileUtilsSdk.putServicesCharacteristicInter(str, str2, RequestUtilsSdk.parseCloudResponseToCharacteristicProfile(profileRequestResult.getJsonResult(), str, str2));
                        countDownLatch.countDown();
                    }
                }

                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onFailure(int i, String str3) {
                    DsLog.et(RequestAgentSdk.TAG, "Failed to get characteristic from cloud, errorCode: " + i + RequestAgentSdk.MESSAGE + str3, new Object[0]);
                    if (i == 401) {
                        RequestAgentSdk.this.profileUtilsSdk.resetAccessTokenTime();
                    }
                    countDownLatch.countDown();
                }
            });
            reentrantReadWriteLock.readLock().unlock();
        } catch (Throwable th) {
            CHARACTERISTICS_LOCK.readLock().unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUploadSuccess(Context context) {
        return !RequestUtilsSdk.isNeedUpload(context) || ProfileTaskPoolSdk.getInstance().executeIfNetworkValid(context, 1, 2000L, 2);
    }

    public void deleteDeviceFromCloud(final Context context, final String str, long j) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SdkThread sdkThread = new SdkThread("DeleteDeviceFromCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.11
            @Override // java.lang.Runnable
            public void run() {
                ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
                profileRequestInputParams.setLocalDevId(str);
                RequestAgentSdk.this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
                ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.DELETE_DEVICE_FROM_CLOUD, profileRequestInputParams);
                if (requestFactorySdk != null) {
                    RequestAgentSdk.DEVICE_LOCK.writeLock().lock();
                    try {
                        requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.11.1
                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onSuccess(ProfileRequestResult profileRequestResult) {
                                RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("devices", str);
                                ProfileBaseUtils.deleteCloudDevId(context, str);
                                countDownLatch.countDown();
                            }

                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onFailure(int i, String str2) {
                                DsLog.et(RequestAgentSdk.TAG, "Failed to delete device from cloud, errorCode: " + i + RequestAgentSdk.MESSAGE + str2, new Object[0]);
                                if (i == 404) {
                                    ProfileBaseUtils.notFoundDeviceWhenDelete(context, str);
                                } else if (RequestUtilsSdk.isNeedResend(context, i, str2, str)) {
                                    RequestAgentSdk.this.profileUtilsSdk.saveResendIndex("devices", str, "deleteDevice");
                                } else {
                                    RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("devices", str);
                                }
                                countDownLatch.countDown();
                            }
                        });
                        return;
                    } finally {
                        RequestAgentSdk.DEVICE_LOCK.writeLock().unlock();
                    }
                }
                DsLog.et(RequestAgentSdk.TAG, "delete device from cloud instance is null", new Object[0]);
                countDownLatch.countDown();
            }
        });
        DsLog.dt(TAG, "deleteDeviceFromCloud thread start", new Object[0]);
        sdkThread.start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    public void deleteServiceFromCloud(final Context context, final String str, final String str2, long j) {
        final String str3 = "deleteService/" + str2;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SdkThread sdkThread = new SdkThread("DeleteServiceFromCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.12
            @Override // java.lang.Runnable
            public void run() {
                ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
                profileRequestInputParams.setLocalDevId(str);
                profileRequestInputParams.setTimestamp(str2);
                RequestAgentSdk.this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
                ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.DELETE_SERVICE_FROM_CLOUD, profileRequestInputParams);
                if (requestFactorySdk != null) {
                    RequestAgentSdk.SERVICE_LOCK.writeLock().lock();
                    try {
                        requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.12.1
                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onSuccess(ProfileRequestResult profileRequestResult) {
                                RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("services", str);
                                countDownLatch.countDown();
                            }

                            @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                            public void onFailure(int i, String str4) {
                                DsLog.et(RequestAgentSdk.TAG, "Failed to delete services from cloud, errorCode: " + i + RequestAgentSdk.MESSAGE + str4, new Object[0]);
                                if (i == 404) {
                                    ProfileBaseUtils.notFoundDeviceWhenDelete(context, str);
                                } else if (RequestUtilsSdk.isNeedResend(context, i, str4, str)) {
                                    RequestAgentSdk.this.profileUtilsSdk.saveResendIndex("services", str, str3);
                                } else {
                                    RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("services", str);
                                }
                                countDownLatch.countDown();
                            }
                        });
                        return;
                    } finally {
                        RequestAgentSdk.SERVICE_LOCK.writeLock().unlock();
                    }
                }
                DsLog.et(RequestAgentSdk.TAG, "delete service from cloud instance is null", new Object[0]);
                countDownLatch.countDown();
            }
        });
        DsLog.dt(TAG, "deleteServiceFromCloud thread start", new Object[0]);
        sdkThread.start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    public void deleteCharacteristicFromCloud(final Context context, final String str, final String str2, long j) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String[] split = str.split("/");
        if (split.length != 2) {
            DsLog.et(TAG, "Characteristic key is invalid!", new Object[0]);
        }
        new SdkThread("DeleteCharacteristicFromCloud", new Runnable() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.13
            @Override // java.lang.Runnable
            public void run() {
                RequestAgentSdk.this.deleteCharacteristicFromCloudRequest(context, split, str2, countDownLatch, str);
            }
        }).start();
        ProfileUtilsSdk.waitForCallback(j, countDownLatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteCharacteristicFromCloudRequest(final Context context, String[] strArr, String str, final CountDownLatch countDownLatch, final String str2) {
        final String str3 = strArr[0];
        String str4 = strArr[1];
        final String str5 = "deleteCharacter/" + str;
        ProfileRequestInputParams profileRequestInputParams = new ProfileRequestInputParams();
        profileRequestInputParams.setLocalDevId(str3);
        profileRequestInputParams.setSid(str4);
        profileRequestInputParams.setTimestamp(str);
        this.profileUtilsSdk = ProfileUtilsSdk.getInstance(context);
        ProfileRequestInterface requestFactorySdk = RequestFactorySdk.getInstance(ProfileRequestType.DELETE_CHARACTERISTIC_FROM_CLOUD, profileRequestInputParams);
        if (requestFactorySdk == null) {
            DsLog.et(TAG, "delete characteristic from cloud instance is null", new Object[0]);
            countDownLatch.countDown();
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = CHARACTERISTICS_LOCK;
        reentrantReadWriteLock.writeLock().lock();
        try {
            requestFactorySdk.request(context, profileRequestInputParams, new ProfileRequestCallback() { // from class: com.huawei.profile.coordinator.RequestAgentSdk.14
                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onSuccess(ProfileRequestResult profileRequestResult) {
                    RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("characteristics", str2);
                    countDownLatch.countDown();
                }

                @Override // com.huawei.profile.coordinator.ProfileRequestCallback
                public void onFailure(int i, String str6) {
                    DsLog.et(RequestAgentSdk.TAG, "Failed to delete characteristic code: " + i + RequestAgentSdk.MESSAGE + str6, new Object[0]);
                    if (i == 404) {
                        ProfileBaseUtils.notFoundDeviceWhenDelete(context, str3);
                    } else if (RequestUtilsSdk.isNeedResend(context, i, str6, str3)) {
                        RequestAgentSdk.this.profileUtilsSdk.saveResendIndex("characteristics", str2, str5);
                    } else {
                        RequestAgentSdk.this.profileUtilsSdk.removeResendIndex("characteristics", str2);
                    }
                    countDownLatch.countDown();
                }
            });
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            CHARACTERISTICS_LOCK.writeLock().unlock();
            throw th;
        }
    }
}
