package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.CustomConfigWatchFaceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.StickerWatchFaceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchAbility;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceDto;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceParamsResult;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi;
import com.huawei.operation.h5pro.jsmodules.device.DeviceManagerService;
import com.huawei.operation.h5pro.jsmodules.device.FileServiceJsApi;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.WatchFaceApi;
import defpackage.bzs;
import defpackage.cun;
import defpackage.nrh;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = WatchFaceApi.class)
@Singleton
/* loaded from: classes5.dex */
public class WatchFaceApiImpl implements WatchFaceApi {
    private static final String MY_WATCH_FACE = "myWatchFace";
    private static final String TAG_RELEASE = "R_WatchFaceApiManagerImpl";
    private static final String WATCH_FACE_DETAIL = "watchFaceDetail";

    @Override // com.huawei.watchface.WatchFaceApi
    public void openMyWatchFace(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "openMyWatchFace enter");
        startWatchFace(MY_WATCH_FACE, null, iBaseResponseCallback);
    }

    @Override // com.huawei.watchface.WatchFaceApi
    public void openWatchFaceDetail(int i, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "openWatchFaceDetail enter");
        queryWatchFaceDetailInfo(i, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceApiImpl.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == -1) {
                    ReleaseLogUtil.d(WatchFaceApiImpl.TAG_RELEASE, "openWatchFaceDetail error");
                    iBaseResponseCallback.d(-1, obj);
                } else if (!(obj instanceof WatchFaceDto)) {
                    ReleaseLogUtil.d(WatchFaceApiImpl.TAG_RELEASE, "openWatchFaceDetail objectData is not WatchFaceDto");
                    iBaseResponseCallback.d(-1, obj);
                } else {
                    WatchFaceApiImpl.this.startWatchFace(WatchFaceApiImpl.WATCH_FACE_DETAIL, (WatchFaceDto) obj, iBaseResponseCallback);
                }
            }
        });
    }

    private void queryWatchFaceDetailInfo(final int i, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "openMyWatchFace enter");
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG_RELEASE);
        if (deviceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "openMyWatchFace currentDevice is null");
        } else {
            WatchFaceManager.getInstance().getWearWatchInfoFromDevice(deviceInfo.getDeviceIdentify(), WatchFaceManager.WATCH_FACE_LOCAL_LIST_KEY_PREFIX, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceApiImpl$$ExternalSyntheticLambda0
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj) {
                    WatchFaceApiImpl.lambda$queryWatchFaceDetailInfo$0(IBaseResponseCallback.this, i, i2, (List) obj);
                }
            });
        }
    }

    static /* synthetic */ void lambda$queryWatchFaceDetailInfo$0(IBaseResponseCallback iBaseResponseCallback, int i, int i2, List list) {
        if (i2 != 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "openMyWatchFace currentDevice is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WatchFaceDto watchFaceDto = (WatchFaceDto) it.next();
            if (watchFaceDto.getWatchFaceType() == i) {
                iBaseResponseCallback.d(0, watchFaceDto);
                return;
            }
        }
        iBaseResponseCallback.d(-1, null);
    }

    @Override // com.huawei.watchface.WatchFaceApi
    public void openWatchFaceDetail(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        WatchFaceDto watchFaceDto = new WatchFaceDto();
        watchFaceDto.setWatchFaceId(str);
        watchFaceDto.setWatchFaceVersion(str2);
        startWatchFace(WATCH_FACE_DETAIL, watchFaceDto, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWatchFace(final String str, final WatchFaceDto watchFaceDto, final IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG_RELEASE);
        if (deviceInfo == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "openMyWatchFace currentDevice is null");
            sqo.an("entry watch face getDeviceInfo is null");
            return;
        }
        CustomConfigWatchFaceService.getInstance().registerDeviceSampleListener();
        StickerWatchFaceService.getInstance().registerDeviceSampleListener();
        final String deviceIdentify = deviceInfo.getDeviceIdentify();
        final String deviceModel = deviceInfo.getDeviceModel();
        WatchFaceManager.getInstance().queryWatchFaceInfo(deviceIdentify, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceApiImpl$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceApiImpl.lambda$startWatchFace$1(deviceModel, deviceIdentify, watchFaceDto, str, iBaseResponseCallback, i, (WatchFaceParamsResult) obj);
            }
        });
    }

    static /* synthetic */ void lambda$startWatchFace$1(String str, String str2, WatchFaceDto watchFaceDto, String str3, IBaseResponseCallback iBaseResponseCallback, int i, WatchFaceParamsResult watchFaceParamsResult) {
        if (i != 0) {
            nrh.b(BaseApplication.getContext(), R$string.IDS_device_not_support_my_watch_face);
            ReleaseLogUtil.d(TAG_RELEASE, "openMyWatchFace refreshWatchFaceInfo error:", Integer.valueOf(i));
            return;
        }
        List<WatchAbility> convertParamsToAbility = WatchFaceManager.getInstance().convertParamsToAbility(watchFaceParamsResult);
        String supportOneTouch = watchFaceParamsResult.getSupportOneTouch();
        int h = TextUtils.isEmpty(supportOneTouch) ? -1 : CommonUtil.h(supportOneTouch);
        String supportGaussianColor = watchFaceParamsResult.getSupportGaussianColor();
        int h2 = TextUtils.isEmpty(supportGaussianColor) ? -1 : CommonUtil.h(supportGaussianColor);
        if (h2 == 1) {
            h2 = 0;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceType", str);
            jSONObject.put("deviceId", str2);
            jSONObject.put("abilities", GsonUtil.toJson(convertParamsToAbility));
            jSONObject.put("watchFaceHeight", watchFaceParamsResult.getScreenHeight());
            jSONObject.put("watchFaceWidth", watchFaceParamsResult.getScreenWidth());
            jSONObject.put("supportOneTouch", h);
            jSONObject.put("supportGaussColor", h2);
            if (watchFaceDto != null) {
                jSONObject.put("watchFaceId", watchFaceDto.getWatchFaceId());
                jSONObject.put("watchFaceVersion", watchFaceDto.getWatchFaceVersion());
            }
            LogUtil.a(TAG_RELEASE, "watchFace params:", jSONObject);
            String str4 = Constants.H5PRO_PAGE_PREFIX + str3 + "?params=" + jSONObject;
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            bzs e = bzs.e();
            builder.addPath(str4).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("device", e.getCommonJsModule("device")).addCustomizeJsModule("BasePairManager", BasePairManagerJsApi.class).addCustomizeJsModule("WatchFaceCallback", WatchFaceCallback.class).addCustomizeJsModule("FileService", FileServiceJsApi.class).setImmerse().showStatusBar();
            e.initH5Pro();
            H5ProClient.getServiceManager().registerService(WatchFaceJs.class);
            H5ProClient.getServiceManager().registerService(DeviceManagerService.class);
            iBaseResponseCallback.d(0, builder);
        } catch (JSONException unused) {
            ReleaseLogUtil.c(TAG_RELEASE, "openMyWatchFace catch JSONException");
            sqo.an("entry watch face params is JSONException");
        }
    }
}
