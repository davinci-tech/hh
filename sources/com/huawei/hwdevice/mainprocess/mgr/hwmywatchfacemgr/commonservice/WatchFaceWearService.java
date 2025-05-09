package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.WatchFaceDeviceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressCode;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.PackageInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceDto;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceIdReportInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceList;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceOperateInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceParamsResult;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.watchface.WatchFaceType;
import defpackage.jfq;
import defpackage.jpt;
import defpackage.jqj;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class WatchFaceWearService {
    private static final int DEFAULT_STORAGE_SPACE = -1;
    private static final int DEVICE_REPORT_FILE_LIMITED = 140004;
    private static final int DEVICE_REPORT_NO_SPACE = 140009;
    private static final String KEY_MY_WATCHFACE_APPLY_TIME = "my_watchface_apply_time";
    private static final int MEGABYTES_CONVERT = 1048576;
    private static final int REPORT_STATUS_WATCHFACE_TRANSFER_SUCCESS = 2;
    private static final String TAG = "WatchFaceWearService";
    private static final String TAG_RELEASE = "R_WatchFaceWearService";
    private String mCurWatchFaceId;
    private jqj mTransFileInfo;

    private boolean getWatchOnTrial(int i) {
        return (i & 64) == 64;
    }

    private WatchFaceWearService() {
        registerIdReportCb(TAG, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda7
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceWearService.this.handleWatchFaceReportId(i, (WatchFaceIdReportInfo) obj);
            }
        });
    }

    static class SingletonHolder {
        private static final WatchFaceWearService INSTANCE = new WatchFaceWearService();

        private SingletonHolder() {
        }
    }

    public static WatchFaceWearService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void queryWatchFaceParams(final ResponseCallback<WatchFaceParamsResult> responseCallback) {
        WatchFaceDeviceService.getInstance().queryWatchFaceParams(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WatchFaceWearService.lambda$queryWatchFaceParams$0(ResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void lambda$queryWatchFaceParams$0(ResponseCallback responseCallback, int i, Object obj) {
        if (i != 0 || !(obj instanceof WatchFaceParamsResult)) {
            responseCallback.onResponse(i, null);
        } else {
            responseCallback.onResponse(0, (WatchFaceParamsResult) obj);
        }
    }

    public void obtainWatchFaceInfo(List<String> list, final ResponseCallback<WatchFaceInfoResponse> responseCallback) {
        WatchFaceDeviceService.getInstance().obtainWatchFaceInfo(list, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ResponseCallback.this.onResponse(i, (WatchFaceInfoResponse) obj);
            }
        });
    }

    public void queryWatchFaceInfoList(final ResponseCallback<List<WatchFaceDto>> responseCallback) {
        WatchFaceDeviceService.getInstance().queryWatchFaceInfoList(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WatchFaceWearService.this.m658x110d9cf0(responseCallback, i, obj);
            }
        });
    }

    /* renamed from: lambda$queryWatchFaceInfoList$2$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-commonservice-WatchFaceWearService, reason: not valid java name */
    /* synthetic */ void m658x110d9cf0(ResponseCallback responseCallback, int i, Object obj) {
        ArrayList arrayList = new ArrayList(16);
        for (WatchFaceList.WatchFace watchFace : ((WatchFaceList) obj).getWatchFaceList()) {
            String asciiNumToString = WatchFaceUtil.asciiNumToString(watchFace.getWatchFaceId());
            boolean z = (watchFace.getWatchFaceType() & 1) != 0;
            WatchFaceDto watchFaceDto = new WatchFaceDto();
            watchFaceDto.setWatchFaceId(asciiNumToString);
            watchFaceDto.setWatchFaceVersion(WatchFaceUtil.asciiNumToString(watchFace.getWatchFaceVersion()));
            watchFaceDto.setCurrent(z);
            watchFaceDto.setSupportDelete((watchFace.getWatchFaceType() & 4) != 0);
            watchFaceDto.setWatchFaceType(getWatchType(watchFace.getWatchFaceType(), watchFace.getWatchFaceExpandType()));
            watchFaceDto.setOnTrial(getWatchOnTrial(watchFace.getWatchFaceType()));
            if (z) {
                arrayList.add(0, watchFaceDto);
                getInstance().setCurWatchFaceId(asciiNumToString);
            } else {
                arrayList.add(watchFaceDto);
            }
        }
        responseCallback.onResponse(0, arrayList);
    }

    private int getWatchType(int i, int i2) {
        int value = ((i & 16) == 16 || 1 == i2) ? WatchFaceType.VIDEO.value() : 0;
        if ((i & 32) == 32 || 2 == i2) {
            value = WatchFaceType.ALBUM.value();
        }
        if ((i & 128) == 128 || 3 == i2) {
            value = WatchFaceType.KALEIDOSCOPE.value();
        }
        if (4 == i2) {
            value = WatchFaceType.WEAR.value();
        }
        return 5 == i2 ? WatchFaceType.STICKER.value() : value;
    }

    public String getCurWatchFaceId() {
        return this.mCurWatchFaceId;
    }

    public void setCurWatchFaceId(String str) {
        this.mCurWatchFaceId = str;
    }

    public void displayWatchFace(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        refreshMyWatchFaceApplyTime();
        WatchFaceDeviceService.getInstance().applyWatchFace(str, str2, false, iBaseResponseCallback);
    }

    public void deleteWatchFace(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        WatchFaceDeviceService.getInstance().deleteWatchFace(str, str2, iBaseResponseCallback);
    }

    public void sendWatchFace(final String str, final String str2, final File file, final String str3, final PackageInfoResponse.PackageInfo packageInfo, final int i, final ResponseCallback<String> responseCallback) {
        final DeviceInfo d = jpt.d(TAG);
        WatchFaceManager.getInstance().queryWatchFaceInfo(d.getDeviceIdentify(), new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda6
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                WatchFaceWearService.this.m661x25b34a8d(responseCallback, file, packageInfo, d, str, str2, str3, i, i2, (WatchFaceParamsResult) obj);
            }
        });
    }

    /* renamed from: lambda$sendWatchFace$5$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-commonservice-WatchFaceWearService, reason: not valid java name */
    /* synthetic */ void m661x25b34a8d(final ResponseCallback responseCallback, File file, final PackageInfoResponse.PackageInfo packageInfo, DeviceInfo deviceInfo, final String str, final String str2, final String str3, final int i, int i2, final WatchFaceParamsResult watchFaceParamsResult) {
        if (i2 != 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "sendWatchFace get watchface info error");
            responseCallback.onResponse(-1, null);
        } else {
            final String filePathToBeSent = getFilePathToBeSent(file, watchFaceParamsResult, packageInfo, deviceInfo);
            final long fileSize = WatchFaceUtil.getFileSize(filePathToBeSent);
            LogUtil.a(TAG, "sendWatchFace file:", filePathToBeSent, ", fileSize: ", Long.valueOf(fileSize));
            WatchFaceDeviceService.getInstance().queryWatchStatus(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj) {
                    WatchFaceWearService.this.m660x3cab858c(responseCallback, fileSize, watchFaceParamsResult, packageInfo, str, str2, str3, filePathToBeSent, i, i3, obj);
                }
            });
        }
    }

    /* renamed from: lambda$sendWatchFace$4$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-commonservice-WatchFaceWearService, reason: not valid java name */
    /* synthetic */ void m660x3cab858c(final ResponseCallback responseCallback, long j, WatchFaceParamsResult watchFaceParamsResult, final PackageInfoResponse.PackageInfo packageInfo, String str, String str2, final String str3, final String str4, final int i, int i2, Object obj) {
        if (!(obj instanceof WatchFaceList)) {
            ReleaseLogUtil.d(TAG_RELEASE, "sendWatchFace watchStatusObj error");
            responseCallback.onResponse(-1, null);
            return;
        }
        WatchFaceList watchFaceList = (WatchFaceList) obj;
        if (watchFaceList.getIsWatchfaceFull()) {
            LogUtil.h(TAG, "Watchface quantity reach maximum, cant send file.");
            WatchFaceManager.getInstance().handleTransferError(DEVICE_REPORT_FILE_LIMITED, "");
        } else if (watchFaceList.getLeftSpace() != -1 && watchFaceList.getLeftSpace() < (j * 1.0d) / 1048576.0d) {
            LogUtil.h(TAG, "No enough space left for transferring current watch face.");
            WatchFaceManager.getInstance().handleTransferError(DEVICE_REPORT_NO_SPACE, "");
        } else {
            WatchFaceDeviceService.getInstance().applyWatchFace(str, str2, WatchFaceUtil.isNeedSyncWatchFace(watchFaceParamsResult, packageInfo), new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj2) {
                    WatchFaceWearService.this.m659x53a3c08b(responseCallback, str3, str4, packageInfo, i, i3, obj2);
                }
            });
        }
    }

    /* renamed from: lambda$sendWatchFace$3$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-commonservice-WatchFaceWearService, reason: not valid java name */
    /* synthetic */ void m659x53a3c08b(ResponseCallback responseCallback, String str, String str2, PackageInfoResponse.PackageInfo packageInfo, int i, int i2, Object obj) {
        if (!(obj instanceof WatchFaceOperateInfo)) {
            ReleaseLogUtil.d(TAG_RELEASE, "sendWatchFace objeData error");
            responseCallback.onResponse(-1, null);
        } else if (((WatchFaceOperateInfo) obj).getNeedReceive() == 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "sendWatchFace skip send watchface");
            responseCallback.onResponse(-1, null);
        } else {
            sendWatchFaceDetail(str, str2, packageInfo, i, responseCallback);
        }
    }

    private String getFilePathToBeSent(File file, WatchFaceParamsResult watchFaceParamsResult, PackageInfoResponse.PackageInfo packageInfo, DeviceInfo deviceInfo) {
        if (!WatchFaceUtil.isNeedSyncWatchFace(watchFaceParamsResult, packageInfo)) {
            return WatchFaceUtil.generateFileToSend(deviceInfo, file);
        }
        return WatchFaceUtil.generateSyncPackage(file, packageInfo);
    }

    private void sendWatchFaceDetail(final String str, String str2, PackageInfoResponse.PackageInfo packageInfo, final int i, final ResponseCallback<String> responseCallback) {
        final String str3 = str.split("_")[0];
        DeviceInfo d = jpt.d(TAG);
        jqj jqjVar = new jqj();
        jqjVar.c(TransferFileReqType.APP_DELIVERY);
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        jqjVar.e(arrayList);
        jqjVar.a(str);
        jqjVar.d(1);
        jqjVar.f(str2);
        if (d != null) {
            jqjVar.c(d.getDeviceIdentify());
        }
        jqjVar.e(WatchFaceUtil.getFileSize(str2));
        if (d == null || d.getDeviceConnectState() != 2) {
            LogUtil.a(TAG, "sendWatchFaceDetail device not connected");
            responseCallback.onResponse(-1, "device not connected");
        } else {
            this.mTransFileInfo = jqjVar;
            WatchFaceCallback.onCall(ProgressCode.SUCCESS, ProgressState.INSTALLING, str3);
            jfq.c().d(TransferBusinessType.FIVE_FORTY, jqjVar, new IAppTransferFileResultAIDLCallback.Stub() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService.1
                private int lastProgress = 0;

                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onFileTransferState(int i2, String str4) {
                    int i3 = this.lastProgress;
                    if (i2 == i3) {
                        return;
                    }
                    if (i2 >= i3 || i2 == 0) {
                        this.lastProgress = i2;
                        if (i2 % 5 == 0) {
                            LogUtil.a(WatchFaceWearService.TAG, "sendWatchFaceDetail onFileTransferState, progress: ", Integer.valueOf(i2), " fileName: ", str);
                        }
                        WatchFaceCallback.onCall(ProgressCode.SUCCESS, ProgressState.INSTALL_PER, str3, Integer.valueOf(i2));
                    }
                }

                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onUpgradeFailed(int i2, String str4) {
                    LogUtil.a(WatchFaceWearService.TAG, "onUpgradeFailed");
                    responseCallback.onResponse(i2, str4);
                }

                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onFileRespond(int i2, String str4) {
                    LogUtil.a(WatchFaceWearService.TAG, "onFileRespond");
                    int i3 = i;
                    if (i3 != 1 && (i3 != 4 || !Objects.equals(WatchFaceWearService.this.getCurWatchFaceId(), str3))) {
                        WatchFaceCallback.onCall(ProgressCode.SUCCESS, ProgressState.INSTALLED, str3);
                    }
                    responseCallback.onResponse(0, str4);
                }
            });
        }
    }

    public void cancelInstallingWatchFace() {
        jfq.c().d(this.mTransFileInfo.j(), this.mTransFileInfo.n(), new IBaseCallback.Stub() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService.2
            @Override // com.huawei.hwservicesmgr.IBaseCallback
            public void onResponse(int i, String str) {
                if (i == 20003) {
                    WatchFaceCallback.onCall(ProgressCode.CANCELED, "canceled");
                    WatchFaceCallback.resetState();
                } else {
                    WatchFaceCallback.onCall(ProgressCode.CANCEL_FAIL, "cancel fail");
                }
            }
        });
    }

    public void registerIdReportCb(String str, ResponseCallback<WatchFaceIdReportInfo> responseCallback) {
        WatchFaceDeviceService.getInstance().registerIdReportCb(str, responseCallback);
    }

    public void unregisterIdReportCb(String str) {
        WatchFaceDeviceService.getInstance().unregisterIdReportCb(str);
    }

    public void refreshMyWatchFaceApplyTime() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a(TAG, "setMyWatchFaceApplyTime ", Long.valueOf(currentTimeMillis));
        SharedPreferenceManager.c(KEY_MY_WATCHFACE_APPLY_TIME, String.valueOf(currentTimeMillis));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWatchFaceReportId(int i, WatchFaceIdReportInfo watchFaceIdReportInfo) {
        LogUtil.a(TAG, "handleWatchFaceReportId enter");
        if (i != 0 || watchFaceIdReportInfo == null) {
            LogUtil.a(TAG, "handleWatchFaceReportId error :", Integer.valueOf(i));
            return;
        }
        int reportStatus = watchFaceIdReportInfo.getReportStatus();
        String watchFaceId = watchFaceIdReportInfo.getWatchFaceId();
        String watchFaceVersion = watchFaceIdReportInfo.getWatchFaceVersion();
        if (TextUtils.isEmpty(watchFaceId)) {
            LogUtil.a(TAG, "handleWatchFaceReportId error report watchFaceId is empty");
            return;
        }
        if (reportStatus == 2) {
            LogUtil.a(TAG, "handleWatchFaceReportId transfer success");
            JSONObject curWatchFaceInstallParams = WatchFaceCallback.getCurWatchFaceInstallParams();
            if (curWatchFaceInstallParams == null) {
                LogUtil.a(TAG, "handleWatchFaceReportId installParams is null");
                return;
            }
            final String optString = curWatchFaceInstallParams.optString("watchFaceId");
            int optInt = curWatchFaceInstallParams.optInt("operate");
            if (!Objects.equals(watchFaceId, optString)) {
                LogUtil.a(TAG, "handleWatchFaceReportId reportWatchFaceId not equals to watchFaceId");
            } else if (optInt == 1 || (optInt == 4 && Objects.equals(getCurWatchFaceId(), optString))) {
                LogUtil.a(TAG, "handleWatchFaceReportId apply watchface");
                displayWatchFace(optString, watchFaceVersion, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService$$ExternalSyntheticLambda0
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        WatchFaceCallback.onCall(ProgressCode.SUCCESS, ProgressState.APPLIED, optString);
                    }
                });
            }
        }
    }
}
