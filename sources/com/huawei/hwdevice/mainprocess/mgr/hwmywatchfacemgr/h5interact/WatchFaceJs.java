package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.AlbumWatchFaceManger;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.CustomConfigWatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.KaleidoscopeWatchFaceManger;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WearWatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.InstalledWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.KaleidoscopePreview;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.WearFilterPreview;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceDetailResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceVersion;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceDto;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import defpackage.jqh;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = "WatchFaceManager", users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class WatchFaceJs {
    private static final String TAG = "WatchFaceJs";

    @H5ProCallback
    interface IH5ProCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t);
    }

    @H5ProMethod(name = "applyCustomConfig")
    public static void applyCustomConfig(String str, CustomConfig customConfig, final IH5ProCallback<Boolean> iH5ProCallback) {
        CustomConfigWatchFaceManager.getInstance().applyCustomConfig(str, customConfig, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                String str2 = obj instanceof String ? (String) obj : "";
                if (Objects.equals(Integer.valueOf(i), 0)) {
                    jqh.a("m5", "true", str2);
                    IH5ProCallback.this.onSuccess(true);
                } else {
                    IH5ProCallback.this.onFailure(i, "applyCustomConfig err");
                    jqh.a("m5", "6", str2);
                }
            }
        });
    }

    @H5ProMethod(name = "jumpToThemePage")
    public static void jumpToThemePage(String str, IH5ProCallback<Void> iH5ProCallback) {
        WatchFaceManager.getInstance().jumpToThemePage(str);
    }

    @H5ProMethod(name = "obtainDeviceWatchFace")
    public static void obtainDeviceWatchFace(String str, final IH5ProCallback<List<InstalledWatchFace>> iH5ProCallback) {
        WatchFaceManager.getInstance().obtainDeviceWatchFace(str, new ResultCallback<List<InstalledWatchFace>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.2
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(List<InstalledWatchFace> list) {
                IH5ProCallback.this.onSuccess(list);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "obtainWatchFaceDetail")
    public static void obtainWatchFaceDetail(String str, final IH5ProCallback<WatchFaceDetailResponse> iH5ProCallback) {
        WatchFaceManager.getInstance().obtainWatchFaceDetail(str, new ResultCallback<WatchFaceDetailResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.3
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(WatchFaceDetailResponse watchFaceDetailResponse) {
                IH5ProCallback.this.onSuccess(watchFaceDetailResponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "getWatchFaceLocalList")
    public static void getWatchFaceLocalList(String str, final IH5ProCallback<List<WatchFaceDto>> iH5ProCallback) {
        WatchFaceManager.getInstance().getWatchFaceLocalList(str, new ResultCallback<List<WatchFaceDto>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.4
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(List<WatchFaceDto> list) {
                IH5ProCallback.this.onSuccess(list);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "obtainOhEntryDefaultImg")
    public static void obtainOhEntryDefaultImg(String str, final IH5ProCallback<String> iH5ProCallback) {
        WatchFaceManager.getInstance().obtainOhEntryDefaultImg(str, new ResultCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.5
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str2) {
                IH5ProCallback.this.onSuccess(str2);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "obtainPreviewSource")
    public static void obtainPreviewSource(String str, IH5ProCallback<String> iH5ProCallback) {
        iH5ProCallback.onSuccess("");
    }

    @H5ProMethod(name = "deleteWatchFace")
    public static void deleteWatchFace(String str, final IH5ProCallback<List<WatchFaceVersion>> iH5ProCallback) {
        ArrayList arrayList = new ArrayList(1);
        try {
            JSONObject jSONObject = new JSONObject(str);
            WatchFaceVersion watchFaceVersion = new WatchFaceVersion();
            watchFaceVersion.setWatchFaceId(jSONObject.getString("watchFaceId"));
            watchFaceVersion.setVersion(jSONObject.getString("watchFaceVersion"));
            arrayList.add(watchFaceVersion);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "deleteWatchFace JSONException, params: ", str);
        }
        WatchFaceManager.getInstance().deleteWatchFaces(arrayList, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceJs.IH5ProCallback.this.onSuccess((List) obj);
            }
        });
    }

    @H5ProMethod(name = "deleteWatchFaces")
    public static void deleteWatchFaces(String str, final IH5ProCallback<List<WatchFaceVersion>> iH5ProCallback) {
        WatchFaceManager.getInstance().deleteWatchFaces(str, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceJs.IH5ProCallback.this.onSuccess((List) obj);
            }
        });
    }

    @H5ProMethod(name = "cancelInstallingWatchFace")
    public static void cancelInstallingWatchFace(String str, IH5ProCallback<Boolean> iH5ProCallback) {
        WatchFaceManager.getInstance().cancelInstallingWatchFace(str);
    }

    @H5ProMethod(name = "applyWatchFace")
    public static void applyWatchFace(String str, final IH5ProCallback<Boolean> iH5ProCallback) {
        WatchFaceManager.getInstance().applyWatchFace(str, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs$$ExternalSyntheticLambda2
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceJs.IH5ProCallback.this.onSuccess((Boolean) obj);
            }
        });
    }

    @H5ProMethod(name = "getAlbumWatchFacePath")
    public static void getAlbumWatchFacePath(int i, final IH5ProCallback<String> iH5ProCallback) {
        AlbumWatchFaceManger.getInstance().getAlbumWatchFacePath(i, new ResponseCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.6
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i2, String str) {
                IH5ProCallback.this.onSuccess(str);
            }
        });
    }

    @H5ProMethod(name = "getColorPictures")
    public static void getColorPictures(String str, final IH5ProCallback<String> iH5ProCallback) {
        AlbumWatchFaceManger.getInstance().getColorPictures(str, new ResultCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.7
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str2) {
                IH5ProCallback.this.onSuccess(str2);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "getSmartPictureList")
    public static void getSmartPictureList(String str, final IH5ProCallback<String> iH5ProCallback) {
        AlbumWatchFaceManger.getInstance().getSmartPictureList(str, new ResultCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.8
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str2) {
                IH5ProCallback.this.onSuccess(str2);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "createWearWatchFace")
    public static void createWearWatchFace(String str, final IH5ProCallback<List<WearFilterPreview>> iH5ProCallback) {
        WearWatchFaceManager.getInstance().createWearWatchFace(str, new ResultCallback<List<WearFilterPreview>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.9
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(List<WearFilterPreview> list) {
                IH5ProCallback.this.onSuccess(list);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "getWearWatchFacePath")
    public static void getWearWatchFacePath(int i, final IH5ProCallback<String> iH5ProCallback) {
        WearWatchFaceManager.getInstance().getWearWatchFacePath(i, new ResponseCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.10
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i2, String str) {
                IH5ProCallback.this.onSuccess(str);
            }
        });
    }

    @H5ProMethod(name = "deleteRedundantWearWatchFace")
    public static void deleteRedundantWearWatchFace(String str, final IH5ProCallback<String> iH5ProCallback) {
        WearWatchFaceManager.getInstance().deleteRedundantWearWatchFace(str, new ResultCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.11
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str2) {
                IH5ProCallback.this.onSuccess(str2);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "checkLocalResourceExist")
    public static void checkLocalResourceExist(String str, final IH5ProCallback<Boolean> iH5ProCallback) {
        WearWatchFaceManager.getInstance().checkLocalResourceExist(str, new ResultCallback<Boolean>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.12
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(Boolean bool) {
                IH5ProCallback.this.onSuccess(bool);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "getKaleidoscopeTypeInfo")
    public static void getKaleidoscopeTypeInfo(String str, final IH5ProCallback<List<KaleidoscopePreview>> iH5ProCallback) {
        KaleidoscopeWatchFaceManger.getInstance().getKaleidoscopeTypeInfo(str, new ResultCallback<List<KaleidoscopePreview>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.13
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(List<KaleidoscopePreview> list) {
                IH5ProCallback.this.onSuccess(list);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                IH5ProCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }

    @H5ProMethod(name = "getKaleidoscopeWatchFacePath")
    public static void getKaleidoscopeWatchFacePath(final IH5ProCallback<String> iH5ProCallback) {
        KaleidoscopeWatchFaceManger.getInstance().getKaleidoscopeWatchFacePath(new ResponseCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.h5interact.WatchFaceJs.14
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public void onResponse(int i, String str) {
                IH5ProCallback.this.onSuccess(str);
            }
        });
    }

    @H5ProMethod(name = "isSupportWatchFaceMarket")
    public static void isSupportWatchFaceMarket(IH5ProCallback<Boolean> iH5ProCallback) {
        LogUtil.a(TAG, "isSupportWatchFaceMarket enter");
        if (iH5ProCallback == null) {
            LogUtil.h(TAG, "isSupportWatchFaceMarket callback is null");
        } else {
            iH5ProCallback.onSuccess(Boolean.valueOf(WatchFaceUtil.isSupportWatchFaceMarket()));
        }
    }

    @H5ProMethod(name = "getWatchFaceMarketSite")
    public static void getWatchFaceMarketSite(IH5ProCallback<String> iH5ProCallback) {
        LogUtil.a(TAG, "getWatchFaceMarketSite enter");
        if (iH5ProCallback == null) {
            LogUtil.h(TAG, "getWatchFaceMarketSite callback is null");
            return;
        }
        String b = (CommonUtil.as() || CommonUtil.aq()) ? SharedPreferenceManager.b(BaseApplication.getContext(), "APP_WATCHFACE", "app_watchface_change_url") : "";
        LogUtil.a(TAG, "getWatchFaceMarketSite site:", b);
        iH5ProCallback.onSuccess(b);
    }

    @H5ProMethod(name = "getWatchFacePath")
    public static void getWatchFacePath(int i, IH5ProCallback<String> iH5ProCallback) {
        iH5ProCallback.onSuccess(WatchFaceManager.getInstance().getWatchFacePath(i));
    }
}
