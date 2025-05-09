package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.DeviceTypeInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.PackageInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.RecommendWatchFacesResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchAbility;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceDetailRequest;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceDetailResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceVersion;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.beb;
import defpackage.cun;
import defpackage.eil;
import defpackage.lqg;
import defpackage.lqi;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class WatchFaceCloudService {
    private static final String TAG = "WatchFaceCloudService";
    private String mDeviceId;
    private String mDeviceType;
    private final beb mParamsFactory;
    private final Map<String, byte[]> previewSourceMap;

    private WatchFaceCloudService() {
        this.previewSourceMap = new ConcurrentHashMap();
        this.mParamsFactory = new beb();
    }

    static class SingletonHolder {
        private static final WatchFaceCloudService INSTANCE = new WatchFaceCloudService();

        private SingletonHolder() {
        }
    }

    public static WatchFaceCloudService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void fetchRecommendedBriefs(int i, int i2, List<WatchAbility> list, final List<WatchFaceVersion> list2, final ResultCallback<RecommendWatchFacesResponse> resultCallback) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo == null) {
            resultCallback.onFailure(new Throwable("fetchRecommendedBriefs, deviceInfo is null"));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType", deviceInfo.getDeviceModel());
        hashMap.put("lang", CommonUtil.x());
        hashMap.put("pageStart", String.valueOf(i));
        hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, String.valueOf(i2));
        hashMap.put("countryCode", GRSManager.a(BaseApplication.e()).getCountryCode());
        hashMap.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        hashMap.put("abilities", GsonUtil.toJson(list));
        if (list2 != null) {
            hashMap.put("watchFaces", GsonUtil.toJson(list2));
        }
        callHttpGet(WatchFaceUtil.joinUrl(GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl"), WatchFaceConstant.RECOMMEND_REQUEST_URL), createRequestHeader(), hashMap, RecommendWatchFacesResponse.class, new ResultCallback<RecommendWatchFacesResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService.1
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(RecommendWatchFacesResponse recommendWatchFacesResponse) {
                if (recommendWatchFacesResponse.getResultCode() == 0) {
                    calculateFields(recommendWatchFacesResponse);
                    resultCallback.onSuccess(recommendWatchFacesResponse);
                } else {
                    LogUtil.b(WatchFaceCloudService.TAG, "invalid response, status: ", Integer.valueOf(recommendWatchFacesResponse.getResultCode()), " msg: ", recommendWatchFacesResponse.getResultDesc());
                    resultCallback.onFailure(new Throwable("invalid response"));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }

            private void calculateFields(RecommendWatchFacesResponse recommendWatchFacesResponse) {
                if (list2 == null) {
                    return;
                }
                for (RecommendWatchFacesResponse.RecommendWatchFace recommendWatchFace : recommendWatchFacesResponse.getWatchFaces()) {
                    if (TextUtils.isEmpty(recommendWatchFace.getLatestVersion())) {
                        recommendWatchFace.setUpdate(false);
                    } else {
                        Iterator it = list2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                WatchFaceVersion watchFaceVersion = (WatchFaceVersion) it.next();
                                if (Objects.equals(recommendWatchFace.getWatchFaceId(), watchFaceVersion.getWatchFaceId()) && WatchFaceUtil.checkForUpdate(watchFaceVersion.getVersion(), recommendWatchFace.getLatestVersion())) {
                                    recommendWatchFace.setUpdate(true);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void fetchWatchFaceDetail(String str, String str2, List<WatchAbility> list, String str3, final ResultCallback<WatchFaceDetailResponse> resultCallback) {
        new WatchFaceDetailRequest();
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType", str);
        hashMap.put("lang", CommonUtil.x());
        hashMap.put("countryCode", GRSManager.a(BaseApplication.e()).getCountryCode());
        hashMap.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        hashMap.put("abilities", GsonUtil.toJson(list));
        hashMap.put("version", str3);
        callHttpGet(WatchFaceUtil.joinUrl(GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl"), WatchFaceConstant.DETAIL_REQUEST_URL).replace("{watchFaceId}", str2), createRequestHeader(), hashMap, WatchFaceDetailResponse.class, new ResultCallback<WatchFaceDetailResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService.2
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(WatchFaceDetailResponse watchFaceDetailResponse) {
                if (watchFaceDetailResponse.getResultCode() == 0) {
                    resultCallback.onSuccess(watchFaceDetailResponse);
                } else {
                    LogUtil.b(WatchFaceCloudService.TAG, "invalid response, status: ", Integer.valueOf(watchFaceDetailResponse.getResultCode()), " msg: ", watchFaceDetailResponse.getResultDesc());
                    resultCallback.onFailure(new Throwable("invalid response"));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    public void updateDeviceInfo(String str, String str2) {
        this.mDeviceId = str;
        this.mDeviceType = str2;
    }

    public void fetchPreviewSource(final String str, String str2, final ResultCallback<byte[]> resultCallback) {
        LogUtil.a(TAG, "fetchPreviewSource enter, cacheKey: ", str, ", sourceUrl: ", str2);
        if (this.previewSourceMap.containsKey(str)) {
            LogUtil.a(TAG, "fetchPreviewSource load from cache");
            resultCallback.onSuccess(this.previewSourceMap.get(str));
        } else {
            callHttpGet(str2, createRequestHeader(), new HashMap(), byte[].class, new ResultCallback<byte[]>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService.3
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(byte[] bArr) {
                    LogUtil.a(WatchFaceCloudService.TAG, "fetchPreviewSource load from network");
                    WatchFaceCloudService.this.previewSourceMap.put(str, bArr);
                    resultCallback.onSuccess(bArr);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    resultCallback.onFailure(th);
                }
            });
        }
    }

    public void fetchDeviceTypeInfo(String str, final ResultCallback<DeviceTypeInfoResponse.DeviceTypeInfo> resultCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType", str);
        hashMap.put("lang", CommonUtil.x());
        hashMap.put("countryCode", GRSManager.a(BaseApplication.e()).getCountryCode());
        hashMap.put(CloudParamKeys.CLIENT_TYPE, "1");
        callHttpGet(WatchFaceUtil.joinUrl(GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl"), WatchFaceConstant.DEVICE_TYPE_INFO_REQUEST_URL.replace("{deviceType}", str)), createRequestHeader(), hashMap, DeviceTypeInfoResponse.class, new ResultCallback<DeviceTypeInfoResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService.4
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(DeviceTypeInfoResponse deviceTypeInfoResponse) {
                if (deviceTypeInfoResponse.getResultCode() == 0) {
                    resultCallback.onSuccess(deviceTypeInfoResponse.getDeviceTypeInfo());
                } else {
                    LogUtil.b(WatchFaceCloudService.TAG, "invalid response, status: ", Integer.valueOf(deviceTypeInfoResponse.getResultCode()), " msg: ", deviceTypeInfoResponse.getResultDesc());
                    resultCallback.onFailure(new Throwable("invalid response"));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    public void fetchPackageInfo(String str, String str2, List<WatchAbility> list, final ResultCallback<PackageInfoResponse> resultCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType", str2);
        hashMap.put("watchFaceId", str);
        hashMap.put("lang", CommonUtil.x());
        hashMap.put("countryCode", GRSManager.a(BaseApplication.e()).getCountryCode());
        hashMap.put(CloudParamKeys.CLIENT_TYPE, "1");
        hashMap.put("abilities", GsonUtil.toJson(list));
        callHttpGet(WatchFaceUtil.joinUrl(GRSManager.a(BaseApplication.e()).getUrl("healthOperationUrl"), WatchFaceConstant.PACKAGE_REQUEST_URL.replace("{watchFaceId}", str)), createRequestHeader(), hashMap, PackageInfoResponse.class, new ResultCallback<PackageInfoResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService.5
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(PackageInfoResponse packageInfoResponse) {
                if (packageInfoResponse.getResultCode() == 0) {
                    resultCallback.onSuccess(packageInfoResponse);
                } else {
                    LogUtil.b(WatchFaceCloudService.TAG, "fetchPackageInfo, invalid response, status: ", Integer.valueOf(packageInfoResponse.getResultCode()), " msg: ", packageInfoResponse.getResultDesc());
                    resultCallback.onFailure(new Throwable("invalid response"));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    public void downloadPackage(String str, File file, final ProgressListener<File> progressListener) {
        if (file.exists()) {
            boolean delete = file.delete();
            Object[] objArr = new Object[2];
            objArr[0] = "downloadPackage, DeleteFile : ";
            objArr[1] = delete ? "Success" : "Failed";
            LogUtil.a(TAG, objArr);
        }
        boolean mkdirs = file.mkdirs();
        Object[] objArr2 = new Object[2];
        objArr2[0] = "CreateFile : ";
        objArr2[1] = mkdirs ? "Success" : "Failed";
        LogUtil.a(TAG, objArr2);
        if (!mkdirs) {
            progressListener.onFail(new Throwable("file.mkdirs failed"));
        }
        PowerKitManager.e().a(TAG, 512, WatchFaceConstant.APPLY_POWER_WATCHFACE_KIT_CHECK_REASON);
        lqi.d().d(new lqg(str, null, file, new ProgressListener<File>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService.6
            @Override // com.huawei.networkclient.ProgressListener
            public void onFinish(File file2) {
                PowerKitManager.e().e(WatchFaceCloudService.TAG, 512, WatchFaceConstant.APPLY_POWER_WATCHFACE_KIT_CHECK_REASON);
                progressListener.onFinish(file2);
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                progressListener.onProgress(j, j2, z);
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                PowerKitManager.e().e(WatchFaceCloudService.TAG, 512, WatchFaceConstant.APPLY_POWER_WATCHFACE_KIT_CHECK_REASON);
                progressListener.onFail(th);
            }
        }));
    }

    public void cleanResponseDataCache() {
        this.previewSourceMap.clear();
    }

    private Map<String, String> createRequestHeader() {
        Map<String, String> headers = this.mParamsFactory.getHeaders();
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.e());
        String accountInfo = loginInit.getAccountInfo(1008);
        headers.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        headers.put(CloudParamKeys.X_TOKEN, accountInfo);
        headers.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accountInfo);
        headers.put(CloudParamKeys.X_APP_ID, BaseApplication.d());
        headers.put(CloudParamKeys.X_SITE_ID, String.valueOf(loginInit.getAccountInfo(1009)));
        headers.put(CloudParamKeys.X_CLIENT_VERSION, CommonUtil.c(BaseApplication.e()));
        return headers;
    }

    private <Rsp> void callHttpGet(String str, Map<String, String> map, Map<String, String> map2, Class<Rsp> cls, ResultCallback<Rsp> resultCallback) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c(TAG, "callHttpGet, invalid url");
            resultCallback.onFailure(new Throwable("callHttpGet, invalid url"));
        } else {
            lqi.d().c(str, map, map2, cls, resultCallback);
        }
    }
}
