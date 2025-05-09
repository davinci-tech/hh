package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.google.gson.JsonObject;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.DiyThrowable;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressCode;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressResp;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransferProgressResp;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.TransmitWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.AlbumWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.KaleidoscopeWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceIdReportInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WearWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import defpackage.bkz;
import defpackage.jpt;
import defpackage.jqh;
import defpackage.trk;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class WatchFaceCallback extends JsBaseModule {
    private static final String TAG = "WatchFaceCallback";
    private static final String TAG_RELEASE = "DEVMGR_WatchFaceCallback";
    private static JSONObject sCurWatchFaceInstallParams;
    private static ProgressResp sLastResp;
    private static ResultCallback<ProgressResp> sProgressRespCallback;
    private static long sProgressRespCbId;
    private Map<String, List<Long>> queryCustomConfigCallbacks;

    @JavascriptInterface
    public void queryCustomConfig(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("deviceId");
            String string2 = jSONObject.getString("marketId");
            LogUtil.a(TAG, "queryCustomConfig:", CommonUtil.l(string), " ", string2);
            LogUtil.a(TAG, "queryCustomConfig callbackId:", Long.valueOf(j), " marketId:", string2);
            registerQueryCustomConfigCallback(string, string2, j);
            CustomConfigWatchFaceManager.getInstance().queryCustomConfig(string, string2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "queryCustomConfig catch JSONException, params: ", str);
            onFailureCallback(j, "params error");
        }
    }

    @JavascriptInterface
    public void installWatchFace(long j, String str) {
        String str2;
        int i;
        String str3;
        String str4;
        String str5;
        String str6 = "";
        int i2 = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str5 = jSONObject.getString("watchFaceId");
            try {
                str4 = jSONObject.getString("deviceType");
                try {
                    str6 = jSONObject.getString("deviceId");
                    i2 = jSONObject.getInt("operate");
                    sCurWatchFaceInstallParams = jSONObject;
                } catch (JSONException unused) {
                    str2 = str6;
                    str6 = str4;
                    int i3 = i2;
                    str3 = str6;
                    str6 = str5;
                    i = i3;
                    LogUtil.b(TAG, "installWatchFace catch JSONException, params: ", str);
                    sCurWatchFaceInstallParams = null;
                    str4 = str3;
                    i2 = i;
                    str5 = str6;
                    str6 = str2;
                    sProgressRespCbId = j;
                    WatchFaceManager.getInstance().installWatchFace(str5, str4, str6, i2);
                }
            } catch (JSONException unused2) {
                str2 = "";
            }
        } catch (JSONException unused3) {
            str2 = "";
            i = 0;
            str3 = str2;
        }
        sProgressRespCbId = j;
        WatchFaceManager.getInstance().installWatchFace(str5, str4, str6, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void installAndApplyWatchFaceBiEvent(int i, ProgressResp.Data data, int i2, String str) {
        if (i == ProgressCode.SUCCESS.value()) {
            if (data != null) {
                if (data.getState() == ProgressState.APPLIED.value() || data.getState() == ProgressState.INSTALLED.value()) {
                    LogUtil.a(TAG, "installAndApplyWatchFaceBiEvent resultCode is success");
                    jqh.e(i2, "true", str);
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.a(TAG, "installAndApplyWatchFaceBiEvent resultCode is ", Integer.valueOf(i));
        if (i == ProgressCode.DOWNLOAD_NETWORK_FAILED.value()) {
            jqh.e(i2, "1", str);
            return;
        }
        if (i == ProgressCode.DOWNLOAD_OTHER_FAILED.value()) {
            jqh.e(i2, "2", str);
            return;
        }
        if (i == ProgressCode.TRANSFER_OTHER_FAILED.value()) {
            DeviceInfo d = jpt.d(TAG);
            if (d != null && d.getDeviceConnectState() == 2) {
                jqh.e(i2, "4", str);
                return;
            } else {
                LogUtil.a(TAG, "installAndApplyWatchFaceBiEvent device disConnect");
                jqh.e(i2, "7", str);
                return;
            }
        }
        LogUtil.a(TAG, "installAndApplyWatchFaceBiEvent resultCode is other");
    }

    @JavascriptInterface
    public void queryState(long j, String str) {
        LogUtil.a(TAG, "queryState: ", Long.valueOf(j), " ", str, " ", sLastResp);
        sProgressRespCbId = j;
        ProgressResp progressResp = sLastResp;
        if (progressResp != null) {
            sProgressRespCallback.onSuccess(progressResp);
        }
    }

    public static void onCall(ProgressCode progressCode, ProgressState progressState, String str, Integer num) {
        ProgressResp progressResp = new ProgressResp();
        progressResp.setResultCode(progressCode.value());
        ProgressResp.Data data = new ProgressResp.Data();
        data.setState(progressState.value());
        data.setWatchFaceId(str);
        if (num != null) {
            data.setProgress(num.intValue());
        }
        progressResp.setResultData(data);
        sLastResp = progressResp;
        sProgressRespCallback.onSuccess(progressResp);
    }

    public static void onCall(ProgressCode progressCode, ProgressState progressState, String str) {
        onCall(progressCode, progressState, str, null);
    }

    public static void onCall(ProgressCode progressCode, String str) {
        ProgressResp progressResp = new ProgressResp();
        progressResp.setResultCode(progressCode.value());
        progressResp.setResultDesc(str);
        sProgressRespCallback.onSuccess(progressResp);
        sLastResp = null;
    }

    @JavascriptInterface
    public void registerIdReportCb(final long j) {
        WatchFaceManager.getInstance().registerIdReportCb(TAG, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceCallback.this.m649x9411e08f(j, i, (WatchFaceIdReportInfo) obj);
            }
        });
    }

    /* renamed from: lambda$registerIdReportCb$0$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WatchFaceCallback, reason: not valid java name */
    /* synthetic */ void m649x9411e08f(long j, int i, WatchFaceIdReportInfo watchFaceIdReportInfo) {
        onSuccessCallback(j, watchFaceIdReportInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFailureResult(Throwable th, long j) {
        if (th instanceof DiyThrowable) {
            DiyThrowable diyThrowable = (DiyThrowable) th;
            onFailureCallback(j, diyThrowable.getMessage(), diyThrowable.getErrorCode());
        }
    }

    @JavascriptInterface
    public void getAlbumWatchFaceInfo(final long j) {
        AlbumWatchFaceManger.getInstance().getDeviceWatchFaceInfo(new ResultCallback<TransmitWatchFace<AlbumWatchFaceInfo>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.1
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(TransmitWatchFace<AlbumWatchFaceInfo> transmitWatchFace) {
                LogUtil.a(WatchFaceCallback.TAG, "getAlbumWatchFaceInfo callback response: ", HiJsonUtil.e(transmitWatchFace));
                WatchFaceCallback.this.onSuccessCallback(j, transmitWatchFace);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                WatchFaceCallback.this.onFailureResult(th, j);
            }
        });
    }

    @JavascriptInterface
    public void setAlbumWatchFaceInfo(final long j, String str) {
        String str2;
        try {
            str2 = new JSONObject(str).getString("albumWatchFaceInfo");
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "setAlbumWatchFaceInfo JSONException: ", ExceptionUtils.d(e));
            str2 = "";
        }
        AlbumWatchFaceManger.getInstance().sendWatchFaceInfoToDevice(str2, new ResultCallback<TransferProgressResp>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.2
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(TransferProgressResp transferProgressResp) {
                LogUtil.a(WatchFaceCallback.TAG, "setAlbumWatchFaceInfo callback response: ", HiJsonUtil.e(transferProgressResp));
                WatchFaceCallback.this.onSuccessCallback(j, transferProgressResp);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                WatchFaceCallback.this.onFailureResult(th, j);
            }
        });
    }

    @JavascriptInterface
    public void getWearWatchFaceInfo(final long j, String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getWearWatchFaceInfo params is empty");
        } else {
            WearWatchFaceManager.getInstance().getWearWatchFaceInfo(str, new ResultCallback<TransmitWatchFace<WearWatchFaceInfo>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.3
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(TransmitWatchFace<WearWatchFaceInfo> transmitWatchFace) {
                    LogUtil.a(WatchFaceCallback.TAG, "getWearWatchFaceInfo callback response: ", HiJsonUtil.e(transmitWatchFace));
                    WatchFaceCallback.this.onSuccessCallback(j, transmitWatchFace);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    WatchFaceCallback.this.onFailureResult(th, j);
                }
            });
        }
    }

    @JavascriptInterface
    public void setWearWatchFaceInfo(final long j, String str) {
        String str2;
        try {
            str2 = new JSONObject(str).getString("wearWatchFaceInfo");
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "setWearWatchFaceInfo JSONException: ", ExceptionUtils.d(e));
            str2 = "";
        }
        WearWatchFaceManager.getInstance().setWearWatchFaceInfo(str2, new ResultCallback<TransferProgressResp>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.4
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(TransferProgressResp transferProgressResp) {
                LogUtil.a(WatchFaceCallback.TAG, "setWearWatchFaceInfo callback response: ", HiJsonUtil.e(transferProgressResp));
                WatchFaceCallback.this.onSuccessCallback(j, transferProgressResp);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                WatchFaceCallback.this.onFailureResult(th, j);
            }
        });
    }

    @JavascriptInterface
    public void getKaleidoscopeWatchFaceInfo(final long j, String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG_RELEASE, "getKaleidoscopeWatchFaceInfo params is empty");
        } else {
            KaleidoscopeWatchFaceManger.getInstance().getKaleidoscopeWatchFaceInfo(str, new ResultCallback<TransmitWatchFace<KaleidoscopeWatchFaceInfo>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.5
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(TransmitWatchFace<KaleidoscopeWatchFaceInfo> transmitWatchFace) {
                    LogUtil.a(WatchFaceCallback.TAG, "getKaleidoscopeWatchFaceInfo callback response: ", HiJsonUtil.e(transmitWatchFace));
                    WatchFaceCallback.this.onSuccessCallback(j, transmitWatchFace);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    WatchFaceCallback.this.onFailureResult(th, j);
                }
            });
        }
    }

    @JavascriptInterface
    public void setKaleidoscopeWatchFaceInfo(final long j, String str) {
        String str2;
        try {
            str2 = new JSONObject(str).getString("kaleidoscopeWatchFaceInfo");
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "setKaleidoscopeWatchFaceInfo JSONException: ", ExceptionUtils.d(e));
            str2 = "";
        }
        KaleidoscopeWatchFaceManger.getInstance().setKaleidoscopeWatchFaceInfo(str2, new ResultCallback<TransferProgressResp>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.6
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(TransferProgressResp transferProgressResp) {
                LogUtil.a(WatchFaceCallback.TAG, "setKaleidoscopeWatchFaceInfo callback response: ", HiJsonUtil.e(transferProgressResp));
                WatchFaceCallback.this.onSuccessCallback(j, transferProgressResp);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                WatchFaceCallback.this.onFailureResult(th, j);
            }
        });
    }

    @JavascriptInterface
    public void setStickerWatchFaceInfo(final long j, String str) {
        ReleaseLogUtil.e(TAG_RELEASE, "setStickerWatchFaceInfo enter");
        try {
            String string = new JSONObject(str).getString("stickerWatchFaceInfo");
            LogUtil.a(TAG, "setStickerWatchFaceInfo raw:", string);
            StickerWatchFaceManager.getInstance().setStickerWatchFaceInfo(string, new ResultCallback<TransferProgressResp>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.7
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(TransferProgressResp transferProgressResp) {
                    LogUtil.a(WatchFaceCallback.TAG, String.format(Locale.ENGLISH, "setStickerWatchFaceInfo callback resultCode:%s, progress: %d", Integer.valueOf(transferProgressResp.getResultCode()), Integer.valueOf(transferProgressResp.getCurrentProgress())));
                    WatchFaceCallback.this.onSuccessCallback(j, transferProgressResp);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.c("setStickerWatchFaceInfo error:", ExceptionUtils.d(th));
                    WatchFaceCallback.this.onFailureResult(th, j);
                }
            });
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "setStickerWatchFaceInfo JSONException: ", ExceptionUtils.d(e));
            onFailureCallback(j, "params error");
        }
    }

    @JavascriptInterface
    public void getStickerWatchFaceInfo(final long j, String str) {
        ReleaseLogUtil.e(TAG_RELEASE, "getStickerWatchFaceInfo enter");
        try {
            String string = new JSONObject(str).getString("deviceId");
            LogUtil.a(TAG, "getStickerWatchFaceInfo:", CommonUtil.l(string));
            StickerWatchFaceManager.getInstance().getStickerWatchFaceInfo(string, new ResultCallback<TransmitWatchFace<String>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.8
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(TransmitWatchFace<String> transmitWatchFace) {
                    LogUtil.a(WatchFaceCallback.TAG, String.format(Locale.ENGLISH, "getStickerWatchFaceInfo callback resultCode:%s, progress: %d", Integer.valueOf(transmitWatchFace.getResultCode()), Integer.valueOf(transmitWatchFace.getCurrentProgress())));
                    TransmitWatchFace transmitWatchFace2 = new TransmitWatchFace();
                    transmitWatchFace2.setResultCode(transmitWatchFace.getResultCode());
                    transmitWatchFace2.setCurrentProgress(transmitWatchFace.getCurrentProgress());
                    if (!TextUtils.isEmpty(transmitWatchFace.getWatchFaceInfo())) {
                        transmitWatchFace2.setWatchFaceInfo((JsonObject) trk.b(transmitWatchFace.getWatchFaceInfo(), JsonObject.class));
                    }
                    WatchFaceCallback.this.onSuccessCallback(j, transmitWatchFace2);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    ReleaseLogUtil.d(WatchFaceCallback.TAG_RELEASE, "getStickerWatchFaceInfo:", ExceptionUtils.d(th));
                    WatchFaceCallback.this.onFailureResult(th, j);
                }
            });
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getStickerWatchFaceInfo catch JSONException, params: ", str);
            onFailureCallback(j, "params error");
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        LogUtil.a(TAG, "onCreate invoke");
        this.queryCustomConfigCallbacks = new ConcurrentHashMap();
        setsProgressRespCallback(buildWatchFaceInstallProcessChangedCallback());
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "onDestroy invoke");
        this.queryCustomConfigCallbacks.clear();
        CustomConfigWatchFaceManager.getInstance().destroyWatchFace();
        WatchFaceManager.getInstance().unregisterIdReportCb(TAG);
        WatchFaceCloudService.getInstance().cleanResponseDataCache();
    }

    public static JSONObject getCurWatchFaceInstallParams() {
        return sCurWatchFaceInstallParams;
    }

    public static ResultCallback<ProgressResp> getsProgressRespCallback() {
        return sProgressRespCallback;
    }

    public static void setsProgressRespCallback(ResultCallback<ProgressResp> resultCallback) {
        sProgressRespCallback = resultCallback;
    }

    public static void resetState() {
        LogUtil.a(TAG, "resetState");
        sProgressRespCbId = 0L;
        sLastResp = null;
        sCurWatchFaceInstallParams = null;
    }

    /* renamed from: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback$9, reason: invalid class name */
    class AnonymousClass9 implements ResultCallback<ProgressResp> {
        static /* synthetic */ void lambda$onSuccess$0(int i, Object obj) {
        }

        AnonymousClass9() {
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x006f A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
        @Override // com.huawei.networkclient.ResultCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onSuccess(com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressResp r10) {
            /*
                r9 = this;
                java.lang.String r0 = "WatchFaceCallback"
                java.lang.String r1 = ""
                org.json.JSONObject r2 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$100()
                if (r2 != 0) goto Lb
                return
            Lb:
                org.json.JSONObject r2 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$100()     // Catch: org.json.JSONException -> L37
                java.lang.String r3 = "watchFaceId"
                java.lang.String r2 = r2.getString(r3)     // Catch: org.json.JSONException -> L37
                org.json.JSONObject r3 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$100()     // Catch: org.json.JSONException -> L35
                java.lang.String r4 = "deviceId"
                java.lang.String r3 = r3.getString(r4)     // Catch: org.json.JSONException -> L35
                org.json.JSONObject r4 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$100()     // Catch: org.json.JSONException -> L39
                java.lang.String r5 = "operate"
                int r4 = r4.getInt(r5)     // Catch: org.json.JSONException -> L39
                org.json.JSONObject r5 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$100()     // Catch: org.json.JSONException -> L3a
                java.lang.String r6 = "customConfig"
                java.lang.String r1 = r5.optString(r6)     // Catch: org.json.JSONException -> L3a
                goto L4b
            L35:
                r3 = r1
                goto L39
            L37:
                r2 = r1
                r3 = r2
            L39:
                r4 = 0
            L3a:
                java.lang.String r5 = "onInstallWatchFace process catch JSONException, params: "
                org.json.JSONObject r6 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$100()
                java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
                health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r5)
                r5 = 0
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$102(r5)
            L4b:
                java.lang.String r5 = "installWatchFace callback response: "
                java.lang.String r6 = r10.toString()
                java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
                com.huawei.hwlogsmodel.LogUtil.a(r0, r5)
                int r0 = r10.getResultCode()
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressResp$Data r5 = r10.getResultData()
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback r6 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.this
                long r7 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$200()
                r6.onSuccessCallback(r7, r10)
                boolean r10 = android.text.TextUtils.isEmpty(r1)
                if (r10 != 0) goto Lb5
                if (r5 == 0) goto Lb5
                int r10 = r5.getState()
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState r6 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState.APPLIED
                int r6 = r6.value()
                if (r10 == r6) goto L89
                int r10 = r5.getState()
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState r6 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState.INSTALLED
                int r6 = r6.value()
                if (r10 != r6) goto Lb5
            L89:
                java.lang.Class<com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig> r10 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig.class
                java.lang.Object r10 = defpackage.trk.b(r1, r10)
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig r10 = (com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig) r10
                if (r10 != 0) goto La2
                java.lang.Object[] r10 = new java.lang.Object[]{r1}
                java.lang.String r0 = "installWatchFace apply custom config fail:"
                com.huawei.hwlogsmodel.LogUtil.b(r0, r10)
                java.lang.String r10 = "6"
                defpackage.jqh.e(r4, r10, r2)
                goto Lb8
            La2:
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.CustomConfigWatchFaceManager r0 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.CustomConfigWatchFaceManager.getInstance()
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback$9$$ExternalSyntheticLambda0 r1 = new com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback$9$$ExternalSyntheticLambda0
                r1.<init>()
                r0.applyCustomConfig(r3, r10, r1)
                java.lang.String r10 = "true"
                defpackage.jqh.e(r4, r10, r2)
                goto Lb8
            Lb5:
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.access$300(r0, r5, r4, r2)
            Lb8:
                if (r5 == 0) goto Ld5
                int r10 = r5.getState()
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState r0 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState.APPLIED
                int r0 = r0.value()
                if (r10 == r0) goto Ld2
                int r10 = r5.getState()
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState r0 = com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState.INSTALLED
                int r0 = r0.value()
                if (r10 != r0) goto Ld5
            Ld2:
                com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.resetState()
            Ld5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback.AnonymousClass9.onSuccess(com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressResp):void");
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            int i;
            LogUtil.a("installWatchFace callback onFailure：", th.getMessage());
            WatchFaceCallback.this.onFailureCallback(WatchFaceCallback.sProgressRespCbId, th.getMessage());
            String str = "";
            try {
                str = WatchFaceCallback.sCurWatchFaceInstallParams.getString("watchFaceId");
                i = WatchFaceCallback.sCurWatchFaceInstallParams.getInt("operate");
            } catch (JSONException unused) {
                ReleaseLogUtil.c(WatchFaceCallback.TAG, "onInstallWatchFace process catch JSONException, params: ", WatchFaceCallback.sCurWatchFaceInstallParams);
                i = 0;
            }
            WatchFaceCallback.resetState();
            if (CommonUtil.aa(BaseApplication.e())) {
                jqh.e(i, "2", str);
            } else {
                jqh.e(i, "1", str);
            }
        }
    }

    private ResultCallback<ProgressResp> buildWatchFaceInstallProcessChangedCallback() {
        return new AnonymousClass9();
    }

    private void registerQueryCustomConfigCallback(String str, String str2, long j) {
        if (str == null || str2 == null) {
            return;
        }
        String callbackIdKey = getCallbackIdKey(str, str2);
        if (!this.queryCustomConfigCallbacks.containsKey(callbackIdKey)) {
            this.queryCustomConfigCallbacks.put(callbackIdKey, new ArrayList());
        }
        List<Long> list = this.queryCustomConfigCallbacks.get(callbackIdKey);
        if (list == null) {
            LogUtil.h(TAG, "callbackIdList is null");
        } else {
            list.add(Long.valueOf(j));
            CustomConfigWatchFaceManager.getInstance().registerCustomConfigListener(TAG, new Consumer() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceCallback$$ExternalSyntheticLambda0
                @Override // com.huawei.framework.servicemgr.Consumer
                public final void accept(Object obj) {
                    WatchFaceCallback.this.onCustomConfigReceived((CustomConfig) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCustomConfigReceived(CustomConfig customConfig) {
        DeviceInfo d = jpt.d(TAG);
        if (d == null) {
            LogUtil.h(TAG, "deviceInfo is null");
            return;
        }
        String callbackIdKey = getCallbackIdKey(d.getDeviceIdentify(), customConfig.getMarketId());
        if (this.queryCustomConfigCallbacks.containsKey(callbackIdKey)) {
            List<Long> list = this.queryCustomConfigCallbacks.get(callbackIdKey);
            if (bkz.e(list)) {
                return;
            }
            Iterator<Long> it = list.iterator();
            while (it.hasNext()) {
                onSuccessCallback(it.next().longValue(), customConfig);
            }
        }
    }

    private String getCallbackIdKey(String str, String str2) {
        return String.format(Locale.ENGLISH, "%s#%s", str, str2);
    }
}
