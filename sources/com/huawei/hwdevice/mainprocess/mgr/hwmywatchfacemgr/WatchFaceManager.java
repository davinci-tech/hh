package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.api.IWatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceCloudService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.InstalledWatchFace;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressCode;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.ProgressState;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.DeviceTypeInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.PackageInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.RecommendWatchFacesResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchAbility;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceDetailResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.WatchFaceVersion;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.CompatibleDialStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceDto;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceIdReportInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceOperateInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceParamsResult;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.StickerWatchFaceManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ProgressListener;
import com.huawei.networkclient.ResultCallback;
import com.huawei.watchface.WatchFaceType;
import defpackage.bmr;
import defpackage.cun;
import defpackage.cvc;
import defpackage.cwf;
import defpackage.cwi;
import defpackage.gnm;
import defpackage.jfu;
import defpackage.jpt;
import defpackage.jqh;
import defpackage.koq;
import defpackage.moj;
import defpackage.msj;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WatchFaceManager implements IWatchFaceManager {
    private static final String DEEP_LINK_PREFIX = "huaweihealth://huaweihealth.app/openwith?type=aar&address=";
    private static final String DETAIL_FROM_TAG = "dialDetail_ecoDial";
    private static final int DEVICE_REPORT_FILE_LIMITED = 140004;
    private static final int DEVICE_REPORT_NO_SPACE = 140009;
    private static final long FETCH_WATCHFACE_PREVIEW_TIME_OUT = 5000;
    private static final String IMAGE_PNG_BASE64_PREFIX = "data:image/png;base64,";
    private static final Set<Integer> SPECIAL_WATCH_FACE_TYPE_SET = Collections.unmodifiableSet(new HashSet<Integer>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.1
        private static final long serialVersionUID = -4145748043555378968L;

        {
            add(Integer.valueOf(WatchFaceType.VIDEO.value()));
            add(Integer.valueOf(WatchFaceType.ALBUM.value()));
            add(Integer.valueOf(WatchFaceType.KALEIDOSCOPE.value()));
            add(Integer.valueOf(WatchFaceType.WEAR.value()));
        }
    });
    private static final String TAG = "WatchFaceManager";
    private static final String WATCHFACE_BETA_PATH = "/sandbox/cch5/health";
    private static final String WATCHFACE_CACHE_KEY_SPLIT = "_";
    private static final String WATCHFACE_DEFAULT_PATH = "/cch5/health";
    private static final String WATCHFACE_DEFAULT_SITE = "watchFace";
    public static final String WATCH_FACE_LOCAL_LIST_KEY_PREFIX = "watchFaceLocalList_";
    private static final String WATCH_FACE_NAME_KEY_PREFIX = "watchFaceName_";
    private static final String WATCH_FACE_PARAMS_KEY_PREFIX = "watchFaceParams_";

    private WatchFaceManager() {
    }

    /* loaded from: classes5.dex */
    static class SingletonHolder {
        private static final WatchFaceManager INSTANCE = new WatchFaceManager();

        private SingletonHolder() {
        }
    }

    public static WatchFaceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void obtainDeviceWatchFace(String str, final ResultCallback<List<InstalledWatchFace>> resultCallback) {
        final String str2;
        LogUtil.a(TAG, "obtainDeviceWatchFace enter params: ", str);
        try {
            str2 = new JSONObject(str).getString("deviceId");
        } catch (JSONException e) {
            LogUtil.b(TAG, "obtainDeviceWatchFace JSONException, params: ", str);
            resultCallback.onFailure(e);
            str2 = "";
        }
        LogUtil.a(TAG, "obtainDeviceWatchFace getWatchFaceLocalList enter");
        getWatchFaceLocalList(str, new ResultCallback<List<WatchFaceDto>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.2
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(final List<WatchFaceDto> list) {
                LogUtil.a(WatchFaceManager.TAG, "obtainDeviceWatchFace getWatchFaceLocalList onSuccess");
                WatchFaceManager.this.getLocalWatchFaceRes(str2, list, new ResultCallback<RecommendWatchFacesResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.2.1
                    @Override // com.huawei.networkclient.ResultCallback
                    public void onSuccess(RecommendWatchFacesResponse recommendWatchFacesResponse) {
                        RecommendWatchFacesResponse.RecommendWatchFace recommendWatchFace;
                        LogUtil.a(WatchFaceManager.TAG, "obtainDeviceWatchFace getLocalWatchFaceRes onSuccess");
                        ArrayList arrayList = new ArrayList(16);
                        for (WatchFaceDto watchFaceDto : list) {
                            InstalledWatchFace installedWatchFace = new InstalledWatchFace();
                            arrayList.add(installedWatchFace);
                            installedWatchFace.setStatus(-1);
                            installedWatchFace.setWatchFaceId(watchFaceDto.getWatchFaceId());
                            installedWatchFace.setWatchFaceVersion(watchFaceDto.getWatchFaceVersion());
                            installedWatchFace.setCurrent(watchFaceDto.isCurrent());
                            installedWatchFace.setSupportDelete(watchFaceDto.isSupportDelete());
                            installedWatchFace.setWatchFaceType(watchFaceDto.getWatchFaceType());
                            installedWatchFace.setOnTrial(watchFaceDto.isOnTrial());
                            Iterator<RecommendWatchFacesResponse.RecommendWatchFace> it = recommendWatchFacesResponse.getWatchFaces().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    recommendWatchFace = null;
                                    break;
                                }
                                recommendWatchFace = it.next();
                                if (recommendWatchFace != null && Objects.equals(recommendWatchFace.getWatchFaceId(), watchFaceDto.getWatchFaceId())) {
                                    break;
                                }
                            }
                            if (recommendWatchFace != null) {
                                LogUtil.a(WatchFaceManager.TAG, "responseWatchFaceMatched:", recommendWatchFace.getName(), ",", recommendWatchFace.getWatchFaceId(), ",jumpUrl:", recommendWatchFace.getJumpUrl());
                                installedWatchFace.setStatus(0);
                                installedWatchFace.setWatchFaceName(recommendWatchFace.getName());
                                installedWatchFace.setLatestVersion(recommendWatchFace.getLatestVersion());
                                if (!WatchFaceManager.SPECIAL_WATCH_FACE_TYPE_SET.contains(Integer.valueOf(installedWatchFace.getWatchFaceType())) && !TextUtils.isEmpty(recommendWatchFace.getJumpUrl())) {
                                    installedWatchFace.setJumpUrl(WatchFaceManager.this.handleJumpUrl(recommendWatchFace.getJumpUrl()));
                                }
                                installedWatchFace.setIconPicture(recommendWatchFace.getIconPicture());
                            }
                        }
                        LogUtil.a(WatchFaceManager.TAG, "obtainDeviceWatchFace success");
                        resultCallback.onSuccess(arrayList);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        resultCallback.onFailure(th);
                    }
                });
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String handleJumpUrl(String str) {
        String format = String.format(Locale.ENGLISH, "%s&from=%s", str, DETAIL_FROM_TAG);
        if (!CommonUtil.as() && !CommonUtil.aq()) {
            return format;
        }
        String replace = format.replace(WATCHFACE_DEFAULT_PATH, WATCHFACE_BETA_PATH);
        String b = SharedPreferenceManager.b(BaseApplication.e(), "APP_WATCHFACE", "app_watchface_change_url");
        LogUtil.a(TAG, "handleJumpUrl watchFaceSite: ", b);
        return !TextUtils.isEmpty(b) ? replace.replace(WATCHFACE_DEFAULT_SITE, b) : replace;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLocalWatchFaceRes(String str, final List<WatchFaceDto> list, final ResultCallback<RecommendWatchFacesResponse> resultCallback) {
        queryWatchAbility(str, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda4
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceManager.this.m652x4336987b(list, resultCallback, i, (List) obj);
            }
        });
    }

    /* renamed from: lambda$getLocalWatchFaceRes$0$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WatchFaceManager, reason: not valid java name */
    /* synthetic */ void m652x4336987b(List list, final ResultCallback resultCallback, int i, final List list2) {
        final ArrayList arrayList = new ArrayList(16);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WatchFaceDto watchFaceDto = (WatchFaceDto) it.next();
            WatchFaceVersion watchFaceVersion = new WatchFaceVersion();
            watchFaceVersion.setVersion(watchFaceDto.getWatchFaceVersion());
            watchFaceVersion.setWatchFaceId(watchFaceDto.getWatchFaceId());
            arrayList.add(watchFaceVersion);
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.3
            @Override // java.lang.Runnable
            public void run() {
                WatchFaceManager.this.pageFetchRecommendedBriefs(arrayList, list2, resultCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pageFetchRecommendedBriefs(ArrayList<WatchFaceVersion> arrayList, List<WatchAbility> list, ResultCallback<RecommendWatchFacesResponse> resultCallback) {
        final CountDownLatch countDownLatch = new CountDownLatch((arrayList.size() / 15) + (arrayList.size() % 15 == 0 ? 0 : 1));
        final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        int i = 0;
        while (i < arrayList.size()) {
            int i2 = i + 15;
            List<WatchFaceVersion> subList = arrayList.subList(i, Math.min(arrayList.size(), i2));
            WatchFaceCloudService.getInstance().fetchRecommendedBriefs(0, subList.size(), list, subList, new ResultCallback<RecommendWatchFacesResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.4
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(RecommendWatchFacesResponse recommendWatchFacesResponse) {
                    copyOnWriteArrayList.addAll(recommendWatchFacesResponse.getWatchFaces());
                    countDownLatch.countDown();
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    LogUtil.b(WatchFaceManager.TAG, "getLocalWatchFaceRes onFailure: ", th.getMessage());
                    countDownLatch.countDown();
                }
            });
            i = i2;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b(TAG, "getLocalWatchFaceRes, interrupted while waiting for fetchRecommendedBriefs");
            resultCallback.onFailure(e);
        }
        RecommendWatchFacesResponse recommendWatchFacesResponse = new RecommendWatchFacesResponse();
        recommendWatchFacesResponse.setTotal(copyOnWriteArrayList.size());
        recommendWatchFacesResponse.setWatchFaces(copyOnWriteArrayList);
        recommendWatchFacesResponse.setResultCode(0);
        resultCallback.onSuccess(recommendWatchFacesResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLocalWatchFaceRes(String str, List<WatchFaceDto> list, int i, int i2, ResultCallback<RecommendWatchFacesResponse> resultCallback) {
        getLocalWatchFaceRes(str, list.subList(i, i2), resultCallback);
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void getWatchFaceLocalList(String str, final ResultCallback<List<WatchFaceDto>> resultCallback) {
        String str2;
        try {
            str2 = new JSONObject(str).getString("deviceId");
        } catch (JSONException e) {
            LogUtil.b(TAG, "getWatchFaceLocalList JSONException, params: ", str);
            resultCallback.onFailure(e);
            str2 = "";
        }
        getWearWatchInfoFromDevice(str2, WATCH_FACE_LOCAL_LIST_KEY_PREFIX, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda10
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceManager.lambda$getWatchFaceLocalList$1(ResultCallback.this, i, (List) obj);
            }
        });
    }

    static /* synthetic */ void lambda$getWatchFaceLocalList$1(ResultCallback resultCallback, int i, List list) {
        if (i != 0) {
            resultCallback.onFailure(new Throwable("getWearWatchInfoFromDevice failed"));
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WatchFaceDto watchFaceDto = (WatchFaceDto) it.next();
            if (watchFaceDto.isCurrent()) {
                WatchFaceWearService.getInstance().setCurWatchFaceId(watchFaceDto.getWatchFaceId());
            }
        }
        resultCallback.onSuccess(list);
    }

    public void getCurrentWatchFaceDetail(final String str, final String str2, final ResultCallback<WatchFaceDetailResponse> resultCallback) {
        getWearWatchInfoFromDevice(str, WATCH_FACE_LOCAL_LIST_KEY_PREFIX, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda1
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceManager.this.m651x906353d5(resultCallback, str, str2, i, (List) obj);
            }
        });
    }

    /* renamed from: lambda$getCurrentWatchFaceDetail$3$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WatchFaceManager, reason: not valid java name */
    /* synthetic */ void m651x906353d5(final ResultCallback resultCallback, String str, final String str2, int i, List list) {
        if (i != 0) {
            resultCallback.onFailure(new Throwable("getWearWatchInfoFromDevice failed"));
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WatchFaceDto watchFaceDto = (WatchFaceDto) it.next();
            if (watchFaceDto.isCurrent()) {
                WatchFaceWearService.getInstance().setCurWatchFaceId(watchFaceDto.getWatchFaceId());
                final String watchFaceId = watchFaceDto.getWatchFaceId();
                final String watchFaceVersion = watchFaceDto.getWatchFaceVersion();
                queryWatchAbility(str, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda6
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj) {
                        WatchFaceCloudService.getInstance().fetchWatchFaceDetail(str2, watchFaceId, (List) obj, watchFaceVersion, resultCallback);
                    }
                });
                return;
            }
        }
        resultCallback.onFailure(new Throwable("no current watch face found"));
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void obtainWatchFaceDetail(String str, final ResultCallback<WatchFaceDetailResponse> resultCallback) {
        String str2;
        String str3;
        String str4;
        LogUtil.a(TAG, "obtainWatchFaceDetail entry");
        String str5 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.getString("deviceType");
            try {
                str3 = jSONObject.getString("deviceId");
                try {
                    str4 = jSONObject.getString("watchFaceId");
                    try {
                        str5 = jSONObject.getString("watchFaceVersion");
                    } catch (JSONException e) {
                        e = e;
                        LogUtil.b(TAG, "obtainDeviceWatchFace JSONException, params: ", str);
                        resultCallback.onFailure(e);
                        final String str6 = str5;
                        final String str7 = str2;
                        final String str8 = str4;
                        queryWatchAbility(str3, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda0
                            @Override // com.huawei.hwbasemgr.ResponseCallback
                            public final void onResponse(int i, Object obj) {
                                WatchFaceManager.this.m654x24da6af4(str7, str8, str6, resultCallback, i, (List) obj);
                            }
                        });
                    }
                } catch (JSONException e2) {
                    e = e2;
                    str4 = "";
                }
            } catch (JSONException e3) {
                e = e3;
                str3 = "";
                str4 = str3;
                LogUtil.b(TAG, "obtainDeviceWatchFace JSONException, params: ", str);
                resultCallback.onFailure(e);
                final String str62 = str5;
                final String str72 = str2;
                final String str82 = str4;
                queryWatchAbility(str3, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda0
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i, Object obj) {
                        WatchFaceManager.this.m654x24da6af4(str72, str82, str62, resultCallback, i, (List) obj);
                    }
                });
            }
        } catch (JSONException e4) {
            e = e4;
            str2 = "";
        }
        final String str622 = str5;
        final String str722 = str2;
        final String str822 = str4;
        queryWatchAbility(str3, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceManager.this.m654x24da6af4(str722, str822, str622, resultCallback, i, (List) obj);
            }
        });
    }

    /* renamed from: lambda$obtainWatchFaceDetail$4$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WatchFaceManager, reason: not valid java name */
    /* synthetic */ void m654x24da6af4(String str, String str2, String str3, final ResultCallback resultCallback, int i, List list) {
        WatchFaceCloudService.getInstance().fetchWatchFaceDetail(str, str2, list, str3, new ResultCallback<WatchFaceDetailResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.5
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(WatchFaceDetailResponse watchFaceDetailResponse) {
                LogUtil.a(WatchFaceManager.TAG, "obtainWatchFaceDetail fetchWatchFaceDetail onSuccess");
                WatchFaceDetailResponse.WatchFaceDetail watchFace = watchFaceDetailResponse.getWatchFace();
                watchFace.setCoverPictureBase64(watchFace.getCoverPicture());
                watchFace.setAodPictureBase64(watchFace.getAodPicture());
                WatchFaceManager.this.setCoverAndAodPictureBase64(watchFace);
                resultCallback.onSuccess(watchFaceDetailResponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(WatchFaceManager.TAG, "obtainWatchFaceDetail fetchWatchFaceDetail onFailure, errMsg: ", th.getMessage());
                resultCallback.onFailure(th);
            }
        });
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void installWatchFace(String str, String str2, String str3, int i) {
        dealFetchPackageInfo(str, str2, str3, i);
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void applyWatchFace(String str, final ResponseCallback<Boolean> responseCallback) {
        final String str2;
        JSONObject jSONObject;
        String str3 = "";
        try {
            jSONObject = new JSONObject(str);
            str2 = jSONObject.getString("watchFaceId");
        } catch (JSONException unused) {
            str2 = "";
        }
        try {
            str3 = jSONObject.getString("version");
        } catch (JSONException unused2) {
            LogUtil.b(TAG, "applyWatchFace JSONException, params: ", str);
            responseCallback.onResponse(0, false);
            WatchFaceWearService.getInstance().displayWatchFace(str2, str3, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    WatchFaceManager.lambda$applyWatchFace$5(ResponseCallback.this, str2, i, obj);
                }
            });
        }
        WatchFaceWearService.getInstance().displayWatchFace(str2, str3, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WatchFaceManager.lambda$applyWatchFace$5(ResponseCallback.this, str2, i, obj);
            }
        });
    }

    static /* synthetic */ void lambda$applyWatchFace$5(ResponseCallback responseCallback, String str, int i, Object obj) {
        if (!(obj instanceof WatchFaceOperateInfo)) {
            LogUtil.a(TAG, "applyWatchFace result is error");
            responseCallback.onResponse(i, false);
            return;
        }
        responseCallback.onResponse(i, Boolean.valueOf(((WatchFaceOperateInfo) obj).getErrorCode() == 0));
        if (i == 0) {
            jqh.a("m3", "ture", str);
        } else {
            jqh.a("m3", "5", str);
        }
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void cancelInstallingWatchFace(String str) {
        WatchFaceWearService.getInstance().cancelInstallingWatchFace();
    }

    private void dealFetchPackageInfo(final String str, String str2, String str3, final int i) {
        fetchPackageInfo(str, str2, str3, new ResultCallback<PackageInfoResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.6
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(PackageInfoResponse packageInfoResponse) {
                PackageInfoResponse.PackageInfo packageInfo = packageInfoResponse.getPackageInfo();
                WatchFaceManager.this.dealDownloadEvent(packageInfo.getPackageUrl(), new File(BaseApplication.e().getFilesDir() + WatchFaceConstant.HWT_SAVE_PATH), str, packageInfo, i);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                WatchFaceCallback.getsProgressRespCallback().onFailure(th);
            }
        });
    }

    private void fetchPackageInfo(final String str, final String str2, String str3, final ResultCallback<PackageInfoResponse> resultCallback) {
        queryWatchAbility(str3, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda3
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceCloudService.getInstance().fetchPackageInfo(str, str2, (List) obj, resultCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealDownloadEvent(String str, final File file, final String str2, final PackageInfoResponse.PackageInfo packageInfo, final int i) {
        WatchFaceCallback.onCall(ProgressCode.SUCCESS, ProgressState.DOWNLOAD, str2);
        WatchFaceCloudService.getInstance().downloadPackage(str, file, new ProgressListener<File>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.7
            private int lastPercent = 0;

            @Override // com.huawei.networkclient.ProgressListener
            public void onFinish(File file2) {
                LogUtil.a(WatchFaceManager.TAG, "dealDownloadEvent onFinish");
                WatchFaceCallback.onCall(ProgressCode.SUCCESS, ProgressState.DOWNLOAD_PER, str2, 100);
                WatchFaceManager.this.dealTransferEvent(str2, packageInfo.getVersion(), file, packageInfo, i);
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                int i2 = (int) (((j * 1.0d) / j2) * 100.0d);
                if (i2 == this.lastPercent) {
                    return;
                }
                LogUtil.a(WatchFaceManager.TAG, "download file totalSize: ", Long.valueOf(j2), ", receiveSize: ", Long.valueOf(j), ", percent: ", Integer.valueOf(i2));
                this.lastPercent = i2;
                WatchFaceCallback.onCall(ProgressCode.SUCCESS, ProgressState.DOWNLOAD_PER, str2, Integer.valueOf(i2));
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                WatchFaceManager.this.handleDownloadError(th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealTransferEvent(String str, String str2, File file, PackageInfoResponse.PackageInfo packageInfo, int i) {
        WatchFaceWearService.getInstance().sendWatchFace(str, str2, file, str + "_" + str2, packageInfo, i, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda8
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                WatchFaceManager.this.m650xdcf47fc2(i2, (String) obj);
            }
        });
    }

    /* renamed from: lambda$dealTransferEvent$7$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WatchFaceManager, reason: not valid java name */
    /* synthetic */ void m650xdcf47fc2(int i, String str) {
        if (i != 0) {
            handleTransferError(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDownloadError(String str) {
        WatchFaceCallback.onCall(CommonUtil.aa(BaseApplication.e()) ? ProgressCode.DOWNLOAD_OTHER_FAILED : ProgressCode.DOWNLOAD_NETWORK_FAILED, str);
    }

    public void handleTransferError(int i, String str) {
        ProgressCode progressCode = ProgressCode.TRANSFER_OTHER_FAILED;
        if (!CommonUtil.aa(BaseApplication.e())) {
            progressCode = ProgressCode.DOWNLOAD_NETWORK_FAILED;
        }
        if (i == DEVICE_REPORT_FILE_LIMITED) {
            LogUtil.a(TAG, "onUpgradeFailed DEVICE_REPORT_FILE_LIMITED");
            progressCode = ProgressCode.FILE_LIMITED;
        } else if (i == DEVICE_REPORT_NO_SPACE) {
            LogUtil.a(TAG, "onUpgradeFailed DEVICE_REPORT_NO_SPACE");
            progressCode = ProgressCode.DEVICE_NO_SPACE;
        }
        LogUtil.a(TAG, "handleTransferError progressCode ", Integer.valueOf(progressCode.value()));
        WatchFaceCallback.onCall(progressCode, str);
    }

    public void deleteWatchFaces(List<WatchFaceVersion> list, ResponseCallback<List<WatchFaceVersion>> responseCallback) {
        final ArrayList arrayList = new ArrayList(16);
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (final WatchFaceVersion watchFaceVersion : list) {
            WatchFaceWearService.getInstance().deleteWatchFace(watchFaceVersion.getWatchFaceId(), watchFaceVersion.getVersion(), new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    WatchFaceManager.lambda$deleteWatchFaces$8(WatchFaceVersion.this, arrayList, countDownLatch, i, obj);
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c(TAG, "obtainWatchFaceDetail, interrupted while waiting for deleteWatchFace");
            sqo.an("deleteWatchFaces CountDownLatch await InterruptedException");
        }
        responseCallback.onResponse(0, arrayList);
    }

    static /* synthetic */ void lambda$deleteWatchFaces$8(WatchFaceVersion watchFaceVersion, List list, CountDownLatch countDownLatch, int i, Object obj) {
        WatchFaceVersion watchFaceVersion2 = new WatchFaceVersion();
        watchFaceVersion2.setWatchFaceId(watchFaceVersion.getWatchFaceId());
        watchFaceVersion2.setVersion(watchFaceVersion.getVersion());
        list.add(watchFaceVersion2);
        countDownLatch.countDown();
        if (i == 0) {
            jqh.a("m4", "ture", watchFaceVersion.getWatchFaceId());
        } else {
            jqh.a("m4", "8", watchFaceVersion.getWatchFaceId());
        }
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void deleteWatchFaces(String str, ResponseCallback<List<WatchFaceVersion>> responseCallback) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                WatchFaceVersion watchFaceVersion = new WatchFaceVersion();
                watchFaceVersion.setWatchFaceId(jSONObject.getString("watchFaceId"));
                watchFaceVersion.setVersion(jSONObject.getString("watchFaceVersion"));
                arrayList.add(watchFaceVersion);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "deleteWatchFaces JSONException, params: ", str);
        }
        deleteWatchFaces(arrayList, responseCallback);
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void obtainOhEntryDefaultImg(String str, ResultCallback<String> resultCallback) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        String str2 = "";
        if (deviceInfo == null) {
            ReleaseLogUtil.d(TAG, "obtainOhEntryDefaultImg, deviceInfo is null");
            resultCallback.onSuccess("");
            return;
        }
        if (cwi.c(deviceInfo, 112)) {
            resultCallback.onSuccess(WatchFaceUtil.bitmapToBase64Encode(BitmapFactory.decodeResource(BaseApplication.e().getResources(), R.drawable._2131432027_res_0x7f0b125b)));
            return;
        }
        String j = jfu.j(deviceInfo.getProductType());
        if (TextUtils.isEmpty(j)) {
            j = jfu.d(deviceInfo.getProductType());
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
        String a2 = cwf.a(pluginInfoByUuid, 2, deviceInfo);
        LogUtil.a(TAG, "HomeImgNew name:", a2);
        if (new File(msj.a().d(pluginInfoByUuid.e()) + File.separator + pluginInfoByUuid.e() + File.separator + "img" + File.separator + a2 + ".png").exists()) {
            Bitmap loadImageByImageName = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, a2);
            if (loadImageByImageName == null) {
                LogUtil.h(TAG, "deviceBitmapTemp is null");
                return;
            }
            str2 = WatchFaceUtil.bitmapToBase64Encode(loadImageByImageName);
        } else {
            LogUtil.b(TAG, "obtainOhEntryDefaultImg file not exists");
        }
        resultCallback.onSuccess(IMAGE_PNG_BASE64_PREFIX + str2);
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void registerIdReportCb(String str, ResponseCallback<WatchFaceIdReportInfo> responseCallback) {
        WatchFaceWearService.getInstance().registerIdReportCb(str, responseCallback);
    }

    public void unregisterIdReportCb(String str) {
        WatchFaceWearService.getInstance().unregisterIdReportCb(str);
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void jumpToThemePage(String str) {
        LogUtil.a(TAG, "jumpToThemePage enter params: ", str);
        try {
            String string = new JSONObject(str).getString("jumpUrl");
            if (TextUtils.isEmpty(string)) {
                LogUtil.h(TAG, "jumpToThemePage jumpUrl is empty");
            } else {
                startDeepLinkActivity(string);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "obtainDeviceWatchFace JSONException, params: ", str);
        }
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void isSupportDualFrame(String str, final ResultCallback<Boolean> resultCallback) {
        WatchFaceCloudService.getInstance().fetchDeviceTypeInfo(str, new ResultCallback<DeviceTypeInfoResponse.DeviceTypeInfo>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.8
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(DeviceTypeInfoResponse.DeviceTypeInfo deviceTypeInfo) {
                resultCallback.onSuccess(Boolean.valueOf(deviceTypeInfo.isSupportDualFrame()));
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void queryWatchAbility(String str, final ResponseCallback<List<WatchAbility>> responseCallback) {
        LogUtil.a(TAG, "queryWatchAbility enter");
        getWearWatchInfoFromSp(str, WATCH_FACE_PARAMS_KEY_PREFIX, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda2
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceManager.this.m655xae41024(responseCallback, i, (WatchFaceParamsResult) obj);
            }
        });
    }

    /* renamed from: lambda$queryWatchAbility$9$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WatchFaceManager, reason: not valid java name */
    /* synthetic */ void m655xae41024(ResponseCallback responseCallback, int i, WatchFaceParamsResult watchFaceParamsResult) {
        responseCallback.onResponse(0, convertParamsToAbility(watchFaceParamsResult));
    }

    public void queryWatchFaceInfo(String str, String str2, final ResponseCallback<WatchFaceParamsResult> responseCallback) {
        LogUtil.a(TAG, "queryWatchFaceInfo enter");
        final String str3 = WATCH_FACE_PARAMS_KEY_PREFIX + str + str2;
        String e = SharedPreferenceManager.e(str3);
        if (StringUtils.g(e)) {
            LogUtil.a(TAG, "queryWatchFaceInfo query from device");
            WatchFaceWearService.getInstance().queryWatchFaceParams(new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda9
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    WatchFaceManager.lambda$queryWatchFaceInfo$10(ResponseCallback.this, str3, i, (WatchFaceParamsResult) obj);
                }
            });
        } else {
            LogUtil.a(TAG, "queryWatchFaceInfo query from sp");
            responseCallback.onResponse(0, (WatchFaceParamsResult) moj.e(e, WatchFaceParamsResult.class));
        }
    }

    static /* synthetic */ void lambda$queryWatchFaceInfo$10(ResponseCallback responseCallback, String str, int i, WatchFaceParamsResult watchFaceParamsResult) {
        if (i != 0) {
            responseCallback.onResponse(-1, null);
        } else {
            SharedPreferenceManager.c(str, moj.e(watchFaceParamsResult));
            responseCallback.onResponse(i, watchFaceParamsResult);
        }
    }

    public void refreshWatchFaceInfo(String str, ResponseCallback<WatchFaceParamsResult> responseCallback) {
        LogUtil.a(TAG, "refreshWatchFaceInfo enter");
        getWearWatchInfoFromDevice(str, WATCH_FACE_PARAMS_KEY_PREFIX, responseCallback);
    }

    public void queryWatchFaceInfo(String str, final ResponseCallback<WatchFaceParamsResult> responseCallback) {
        getWearWatchInfoFromSp(str, WATCH_FACE_PARAMS_KEY_PREFIX, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda15
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ResponseCallback.this.onResponse(0, (WatchFaceParamsResult) obj);
            }
        });
    }

    public List<WatchAbility> convertParamsToAbility(WatchFaceParamsResult watchFaceParamsResult) {
        ArrayList arrayList = new ArrayList(16);
        convertMainAbility(watchFaceParamsResult, arrayList);
        convertCompatibleAbility(watchFaceParamsResult, arrayList);
        LogUtil.a(TAG, "queryWatchAbility result: ", arrayList.toString());
        return arrayList;
    }

    private void convertMainAbility(WatchFaceParamsResult watchFaceParamsResult, List<WatchAbility> list) {
        String str = watchFaceParamsResult.getScreenWidth() + "*" + watchFaceParamsResult.getScreenHeight();
        List<String> asciiNumToStringList = WatchFaceUtil.asciiNumToStringList(watchFaceParamsResult.getMaxFirmware());
        List<String> asciiNumToStringList2 = WatchFaceUtil.asciiNumToStringList(watchFaceParamsResult.getSupportEarlyFirmwareVersion());
        convertAbility(str, asciiNumToStringList, list);
        convertAbility(str, asciiNumToStringList2, list);
    }

    private void convertCompatibleAbility(WatchFaceParamsResult watchFaceParamsResult, List<WatchAbility> list) {
        if (koq.b(watchFaceParamsResult.getCompatibleDialList())) {
            return;
        }
        for (CompatibleDialStruct compatibleDialStruct : watchFaceParamsResult.getCompatibleDialList()) {
            convertAbility(compatibleDialStruct.getDialWidth() + "*" + compatibleDialStruct.getDialHeight(), new ArrayList(Arrays.asList(compatibleDialStruct.getSupportVersion().split(","))), list);
        }
    }

    private void convertAbility(String str, List<String> list, List<WatchAbility> list2) {
        WatchAbility watchAbility;
        if (!WatchFaceConstant.SCREE_MAP.containsKey(str)) {
            LogUtil.h(TAG, "queryWatchAbility, invalid screenKey: ", str);
            return;
        }
        if (koq.b(list)) {
            LogUtil.h(TAG, "empty supportVersions");
            return;
        }
        Iterator<WatchAbility> it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                watchAbility = null;
                break;
            } else {
                watchAbility = it.next();
                if (Objects.equals(watchAbility.getScreenCode(), WatchFaceConstant.SCREE_MAP.get(str))) {
                    break;
                }
            }
        }
        if (watchAbility != null) {
            watchAbility.getAbilityVersions().addAll(list);
            watchAbility.setAbilityVersions((List) watchAbility.getAbilityVersions().stream().distinct().collect(Collectors.toList()));
        } else {
            WatchAbility watchAbility2 = new WatchAbility();
            watchAbility2.setScreenCode(WatchFaceConstant.SCREE_MAP.get(str));
            watchAbility2.setAbilityVersions((List) list.stream().distinct().collect(Collectors.toList()));
            list2.add(watchAbility2);
        }
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void getRecommendedWatchFaceBriefs(String str, String str2, int i, int i2, ResultCallback<RecommendWatchFacesResponse> resultCallback) {
        WatchFaceCloudService.getInstance().updateDeviceInfo(str, str2);
        getCurWatchFaceRes(str, i2, resultCallback);
    }

    private void getCurWatchFaceRes(final String str, int i, final ResultCallback<RecommendWatchFacesResponse> resultCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", str);
        } catch (JSONException e) {
            LogUtil.b(TAG, "getRecommendedWatchFaceBriefs JSONException");
            resultCallback.onFailure(e);
        }
        getWatchFaceLocalList(jSONObject.toString(), new ResultCallback<List<WatchFaceDto>>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.9
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(List<WatchFaceDto> list) {
                WatchFaceManager.this.getLocalWatchFaceRes(str, list, 0, 1, new ResultCallback<RecommendWatchFacesResponse>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.9.1
                    @Override // com.huawei.networkclient.ResultCallback
                    public void onSuccess(RecommendWatchFacesResponse recommendWatchFacesResponse) {
                        WatchFaceManager.this.buildRecommendResponse(recommendWatchFacesResponse, resultCallback);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        resultCallback.onFailure(th);
                    }
                });
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buildRecommendResponse(RecommendWatchFacesResponse recommendWatchFacesResponse, ResultCallback<RecommendWatchFacesResponse> resultCallback) {
        RecommendWatchFacesResponse recommendWatchFacesResponse2 = new RecommendWatchFacesResponse();
        recommendWatchFacesResponse2.setResultCode(0);
        recommendWatchFacesResponse2.setWatchFaces(new ArrayList(16));
        if (recommendWatchFacesResponse != null) {
            recommendWatchFacesResponse2.getWatchFaces().addAll(recommendWatchFacesResponse.getWatchFaces());
        }
        recommendWatchFacesResponse2.setTotal(recommendWatchFacesResponse2.getWatchFaces().size());
        LogUtil.a(TAG, "getRecommendedWatchFaceBriefs finished, watchFaces size: ", Integer.valueOf(recommendWatchFacesResponse2.getWatchFaces().size()));
        resultCallback.onSuccess(recommendWatchFacesResponse2);
    }

    @Override // com.huawei.hwdevice.api.IWatchFaceManager
    public void obtainPreviewCardImage(String str, String str2, String str3, final ResultCallback<Bitmap> resultCallback) {
        WatchFaceCloudService.getInstance().fetchPreviewSource(WatchFaceConstant.ICON_PICTURE_HASH_KEY.replace("{watchFaceId}", str).replace("{watchFaceVersion}", str2), str3, new ResultCallback<byte[]>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.10
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(byte[] bArr) {
                resultCallback.onSuccess(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    public String getWatchFacePath(int i) {
        if (i == 0) {
            return StickerWatchFaceManager.getInstance().getDIYStickerWatchFacePath();
        }
        if (i == 1) {
            return StickerWatchFaceManager.getInstance().getPresetStickerWatchFacePath();
        }
        LogUtil.h(TAG, "getWatchFacePath unsupported feature type");
        return "";
    }

    private <T> void getWearWatchInfoFromSp(final String str, final String str2, final ResponseCallback<T> responseCallback) {
        fetchWearWatchInfoFromSp(str, str2, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda14
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceManager.this.m653xfc75d4c2(str, str2, responseCallback, i, obj);
            }
        });
    }

    /* renamed from: lambda$getWearWatchInfoFromSp$12$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-WatchFaceManager, reason: not valid java name */
    /* synthetic */ void m653xfc75d4c2(String str, String str2, ResponseCallback responseCallback, int i, Object obj) {
        LogUtil.a(TAG, "getWearWatchInfoFromSp status: ", Integer.valueOf(i));
        if (i != 0) {
            fetchWearWatchInfoFromDevice(str, str2, responseCallback);
        } else {
            responseCallback.onResponse(i, obj);
        }
    }

    public void getCurWatchFaceInfo(String str, final ResponseCallback<WatchFaceDto> responseCallback) {
        getWearWatchInfoFromDevice(str, WATCH_FACE_LOCAL_LIST_KEY_PREFIX, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda13
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchFaceManager.lambda$getCurWatchFaceInfo$13(ResponseCallback.this, i, (List) obj);
            }
        });
    }

    static /* synthetic */ void lambda$getCurWatchFaceInfo$13(ResponseCallback responseCallback, int i, List list) {
        if (i != 0) {
            responseCallback.onResponse(-1, null);
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WatchFaceDto watchFaceDto = (WatchFaceDto) it.next();
            if (watchFaceDto.isCurrent()) {
                WatchFaceWearService.getInstance().setCurWatchFaceId(watchFaceDto.getWatchFaceId());
                responseCallback.onResponse(0, watchFaceDto);
                return;
            }
        }
        responseCallback.onResponse(-1, null);
    }

    public <T> void getWearWatchInfoFromDevice(String str, String str2, ResponseCallback<T> responseCallback) {
        getWearWatchInfoFromDevice(str, str2, null, responseCallback);
    }

    private <T> void getWearWatchInfoFromDevice(String str, String str2, Object obj, ResponseCallback<T> responseCallback) {
        DeviceInfo d = jpt.d(TAG);
        if (d != null && d.getDeviceConnectState() == 2) {
            fetchWearWatchInfoFromDevice(str, str2, obj, responseCallback);
        } else {
            fetchWearWatchInfoFromSp(str, str2, responseCallback);
        }
    }

    private <T> void fetchWearWatchInfoFromDevice(String str, String str2, ResponseCallback<T> responseCallback) {
        fetchWearWatchInfoFromDevice(str, str2, null, responseCallback);
    }

    private <T> void fetchWearWatchInfoFromDevice(String str, String str2, Object obj, final ResponseCallback<T> responseCallback) {
        final String generateCacheKey = generateCacheKey(str, str2);
        char c = 65535;
        if (StringUtils.g(generateCacheKey)) {
            LogUtil.a(TAG, "fetchWearWatchInfoFromDevice get cacheKey error");
            responseCallback.onResponse(-1, null);
            return;
        }
        ResponseCallback<WatchFaceInfoResponse> responseCallback2 = new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda12
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj2) {
                WatchFaceManager.lambda$fetchWearWatchInfoFromDevice$14(ResponseCallback.this, generateCacheKey, i, obj2);
            }
        };
        str2.hashCode();
        int hashCode = str2.hashCode();
        if (hashCode != -1184377144) {
            if (hashCode != -55764030) {
                if (hashCode == 1759420973 && str2.equals(WATCH_FACE_PARAMS_KEY_PREFIX)) {
                    c = 2;
                }
            } else if (str2.equals(WATCH_FACE_LOCAL_LIST_KEY_PREFIX)) {
                c = 1;
            }
        } else if (str2.equals(WATCH_FACE_NAME_KEY_PREFIX)) {
            c = 0;
        }
        if (c == 0) {
            WatchFaceWearService.getInstance().obtainWatchFaceInfo((List) obj, responseCallback2);
            return;
        }
        if (c == 1) {
            WatchFaceWearService.getInstance().queryWatchFaceInfoList(responseCallback2);
        } else if (c == 2) {
            WatchFaceWearService.getInstance().queryWatchFaceParams(responseCallback2);
        } else {
            LogUtil.b(TAG, "fetchWearWatchInfoFromDevice, invalid cacheKeyPrefix");
        }
    }

    static /* synthetic */ void lambda$fetchWearWatchInfoFromDevice$14(ResponseCallback responseCallback, String str, int i, Object obj) {
        if (i != 0) {
            responseCallback.onResponse(-1, null);
        } else {
            SharedPreferenceManager.c(str, moj.e(obj));
            responseCallback.onResponse(i, obj);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void fetchWearWatchInfoFromSp(String str, String str2, ResponseCallback<T> responseCallback) {
        String generateCacheKey = generateCacheKey(str, str2);
        char c = 65535;
        Object obj = null;
        if (StringUtils.g(generateCacheKey)) {
            LogUtil.a(TAG, "fetchWearWatchInfoFromDevice get cacheKey error");
            responseCallback.onResponse(-1, null);
            return;
        }
        String e = SharedPreferenceManager.e(generateCacheKey);
        if (StringUtils.g(e)) {
            responseCallback.onResponse(-1, null);
            return;
        }
        str2.hashCode();
        int hashCode = str2.hashCode();
        if (hashCode != -1184377144) {
            if (hashCode != -55764030) {
                if (hashCode == 1759420973 && str2.equals(WATCH_FACE_PARAMS_KEY_PREFIX)) {
                    c = 2;
                }
            } else if (str2.equals(WATCH_FACE_LOCAL_LIST_KEY_PREFIX)) {
                c = 1;
            }
        } else if (str2.equals(WATCH_FACE_NAME_KEY_PREFIX)) {
            c = 0;
        }
        if (c == 0) {
            obj = moj.e(e, WatchFaceInfoResponse.class);
        } else if (c == 1) {
            obj = moj.b(e, WatchFaceDto[].class);
        } else if (c == 2) {
            obj = moj.e(e, WatchFaceParamsResult.class);
        } else {
            LogUtil.b(TAG, "fetchWearWatchInfoFromDevice, invalid cacheKeyPrefix");
        }
        responseCallback.onResponse(0, obj);
    }

    private void urlToBase64(String str, String str2, String str3, String str4, final ResultCallback<String> resultCallback) {
        WatchFaceCloudService.getInstance().fetchPreviewSource(str2.replace("{watchFaceId}", str3).replace("{watchFaceVersion}", str4), str, new ResultCallback<byte[]>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.11
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(byte[] bArr) {
                String encodeToString = Base64.getEncoder().encodeToString(bArr);
                resultCallback.onSuccess(WatchFaceManager.IMAGE_PNG_BASE64_PREFIX + encodeToString);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                resultCallback.onFailure(th);
            }
        });
    }

    private void setNameFromDevice(String str, final List<InstalledWatchFace> list, final CountDownLatch countDownLatch) {
        ArrayList arrayList = new ArrayList(16);
        for (InstalledWatchFace installedWatchFace : list) {
            if (TextUtils.isEmpty(installedWatchFace.getWatchFaceName())) {
                arrayList.add(installedWatchFace.getWatchFaceId());
            }
        }
        if (koq.b(arrayList)) {
            countDownLatch.countDown();
        } else {
            getWearWatchInfoFromDevice(str, WATCH_FACE_NAME_KEY_PREFIX, arrayList, new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager$$ExternalSyntheticLambda5
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    WatchFaceManager.lambda$setNameFromDevice$15(countDownLatch, list, i, (WatchFaceInfoResponse) obj);
                }
            });
        }
    }

    static /* synthetic */ void lambda$setNameFromDevice$15(CountDownLatch countDownLatch, List list, int i, WatchFaceInfoResponse watchFaceInfoResponse) {
        if (watchFaceInfoResponse == null) {
            countDownLatch.countDown();
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            InstalledWatchFace installedWatchFace = (InstalledWatchFace) it.next();
            for (WatchFaceInfoResponse.WatchFaceInfoStruct watchFaceInfoStruct : watchFaceInfoResponse.getWatchFaceList()) {
                if (watchFaceInfoStruct != null && Objects.equals(installedWatchFace.getWatchFaceId(), WatchFaceUtil.asciiNumToString(watchFaceInfoStruct.getWatchFaceId()))) {
                    installedWatchFace.setWatchFaceName(WatchFaceUtil.hexListToName(watchFaceInfoStruct.getWatchFaceName()));
                    installedWatchFace.setStatus(0);
                }
            }
        }
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCoverAndAodPictureBase64(final WatchFaceDetailResponse.WatchFaceDetail watchFaceDetail) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        urlToBase64(watchFaceDetail.getCoverPicture(), WatchFaceConstant.COVER_PICTURE_HASH_KEY, watchFaceDetail.getWatchFaceId(), watchFaceDetail.getVersion(), new ResultCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.12
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str) {
                LogUtil.a(WatchFaceManager.TAG, "obtainWatchFaceDetail urlToBase64 getCoverPicture onSuccess");
                watchFaceDetail.setCoverPictureBase64(str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(WatchFaceManager.TAG, "obtainWatchFaceDetail urlToBase64 getCoverPicture onFailure, errMsg: ", th.getMessage());
                countDownLatch.countDown();
            }
        });
        urlToBase64(watchFaceDetail.getAodPicture(), WatchFaceConstant.AOD_PICTURE_HASH_KEY, watchFaceDetail.getWatchFaceId(), watchFaceDetail.getVersion(), new ResultCallback<String>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.WatchFaceManager.13
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str) {
                LogUtil.a(WatchFaceManager.TAG, "obtainWatchFaceDetail urlToBase64 getAodPicture onSuccess");
                watchFaceDetail.setAodPictureBase64(str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.a(WatchFaceManager.TAG, "obtainWatchFaceDetail urlToBase64 getAodPicture onFailure, errMsg: ", th.getMessage());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LogUtil.b(TAG, "obtainWatchFaceDetail, interrupted while waiting for urlToBase64: ", e.getMessage());
            sqo.an("obtainWatchFaceDetail urlToBase64 countDownLatch await InterruptedException");
        }
    }

    private boolean startDeepLinkActivity(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "startDeepLinkActivity url is empty");
            return false;
        }
        LogUtil.a(TAG, "startDeepLinkActivity url: ", str);
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
        intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
        gnm.aPB_(BaseApplication.e(), intent);
        return true;
    }

    private String generateCacheKey(String str, String str2) {
        DeviceInfo b = jpt.b(str, TAG);
        if (b == null) {
            LogUtil.b(TAG, "generateCacheKey deviceInfo is null");
            return null;
        }
        return bmr.a(String.join("_", str2, str, b.getSoftVersion(), String.valueOf(b.getPowerSaveModel())));
    }
}
