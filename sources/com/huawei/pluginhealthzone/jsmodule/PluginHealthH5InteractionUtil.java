package com.huawei.pluginhealthzone.jsmodule;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBeanHelper;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.maps.offlinedata.DeviceTypeConsts;
import com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil;
import com.huawei.pluginhealthzone.jsmodule.callback.PushMessageListener;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager;
import com.huawei.utils.FoundationCommonUtil;
import com.tencent.open.SocialConstants;
import defpackage.exg;
import defpackage.exh;
import defpackage.fdu;
import defpackage.gmx;
import defpackage.jaj;
import defpackage.jpt;
import defpackage.mps;
import defpackage.mpx;
import defpackage.mpy;
import defpackage.mqk;
import defpackage.mqq;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class PluginHealthH5InteractionUtil extends JsBaseModule {
    private static final String AUTHORITIES = "com.android.emergency.EmergencyInfoProvider";
    private static final String CLASS_NAME = "com.android.emergency.view.ViewInfoActivity";
    private static final int DEFAULT_STRING_BUILDER_SIZE = 256;
    private static final String EMERGENCY_METADATA = "android.write_emergency_contact";
    private static final String EMRGENCY_CONTACTS = "android.emergency.EMRGENCY_CONTACTS";
    private static final int FOLLOW_SIZE_MAX = 20;
    private static final String METHOD = "WRITE_EMERGENCY_INFO";
    private static final String PACKAGE_NAME = "com.android.emergency";
    private static final String SCREEN_OFF = "1201";
    private static final String USER_INFO_ACTIVITY = "com.huawei.featureuserprofile.me.UserInfoActivity";
    private ScreenBroadcastReceiver mScreenBroadcastReceiver = null;
    private final List<jaj> mPathFileidList = new ArrayList();
    private final List<String> mListImagePath = new ArrayList();
    private final List<String> mListVideoPath = new ArrayList();
    private boolean mIsVideo = false;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        ScreenBroadcastReceiver screenBroadcastReceiver = new ScreenBroadcastReceiver();
        this.mScreenBroadcastReceiver = screenBroadcastReceiver;
        BroadcastManager.wn_(screenBroadcastReceiver);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        LogUtil.a(this.TAG, "onDestroy");
        BroadcastManager.wB_(this.mScreenBroadcastReceiver);
        if (HealthZonePushReceiver.getPushMessageListener() != null) {
            HealthZonePushReceiver.setPushMessageListener(null);
        }
        super.onDestroy();
    }

    public static class ScreenBroadcastReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                LogUtil.h("ScreenBroadcastReceiver", "action == null");
                return;
            }
            LogUtil.a("ScreenBroadcastReceiver", "action:", action);
            if (!action.equals("android.intent.action.SCREEN_OFF") || HealthZonePushReceiver.getPushMessageListener() == null) {
                return;
            }
            HealthZonePushReceiver.getPushMessageListener().onChange(PluginHealthH5InteractionUtil.SCREEN_OFF, "");
        }
    }

    @JavascriptInterface
    public void isBackgroundEnabled(long j) {
        LogUtil.a(this.TAG, "isBackgroundEnabled enter");
        boolean bh = CommonUtil.bh();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", bh);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "isBackgroundEnabled jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void getBaseUrl(long j) {
        LogUtil.a(this.TAG, "getBaseUrl enter");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("baseUrl", GRSManager.a(this.mContext).getUrl("healthCloudUrl"));
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getBaseUrl jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void registerPushListener(final long j) {
        LogUtil.a(this.TAG, "registerPushListener enter");
        HealthZonePushReceiver.setPushMessageListener(new PushMessageListener() { // from class: com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil.4
            @Override // com.huawei.pluginhealthzone.jsmodule.callback.PushMessageListener
            public void onResult(int i) {
                LogUtil.h(PluginHealthH5InteractionUtil.this.TAG, "registerPushListener onResult FAIL");
                PluginHealthH5InteractionUtil.this.onFailureCallback(j, "registerPushListener onResult FAIL", i);
            }

            @Override // com.huawei.pluginhealthzone.jsmodule.callback.PushMessageListener
            public void onChange(String str, String str2) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("code", str);
                    jSONObject.put("value", str2);
                    PluginHealthH5InteractionUtil.this.onSuccessCallback(j, jSONObject);
                } catch (JSONException e) {
                    LogUtil.b(PluginHealthH5InteractionUtil.this.TAG, "registerPushListener jsonException", LogAnonymous.b((Throwable) e));
                    PluginHealthH5InteractionUtil.this.onFailureCallback(j, LogAnonymous.b((Throwable) e));
                }
            }
        });
    }

    @JavascriptInterface
    public void getDocumentations(long j) {
        LogUtil.a(this.TAG, "geDocumentations enter");
        GRSManager a2 = GRSManager.a(this.mContext);
        String noCheckUrl = a2.getNoCheckUrl("domainTipsResDbankcdn", a2.getCommonCountryCode());
        String url = GRSManager.a(this.mContext).getUrl("domainTipsResDbankcdnNew");
        String url2 = GRSManager.a(BaseApplication.getContext()).getUrl("domainTipsResDbankcdnNew");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("backNote", noCheckUrl);
            jSONObject.put("noteUrl", url);
            jSONObject.put("tutorialsUrl", url2);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getDocumentations jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void getShareUrl(long j) {
        LogUtil.a(this.TAG, "getShareUrl enter");
        StringBuilder sb = new StringBuilder(256);
        if (CompileParameterUtil.a("SUPPORT_WIFI_QR_URL_TEST", false)) {
            sb.append(CompileParameterUtil.c("WIFI_QRCODE_URL_PRE_TEST", ""));
        } else {
            sb.append(GRSManager.a(BaseApplication.getContext()).getUrl("domainUrlCloudHuawei"));
        }
        String url = GRSManager.a(this.mContext).getUrl("activityUrl");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", url);
            jSONObject.put("qrUrl", sb);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getShareUrl jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void getAppSettings(long j) {
        LogUtil.a(this.TAG, "getAppSettings enter");
        mqk mqkVar = new mqk();
        mqkVar.b(UnitUtil.d());
        mqkVar.e(UnitUtil.h());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("settings", HiJsonUtil.e(mqkVar));
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getAppSettings jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void getUserAge(long j) {
        LogUtil.a(this.TAG, "getUserAge enter");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("age", LoginInit.getInstance(this.mContext).getAccountInfo(1004));
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getUserAge jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void goSetMyinfo(long j) {
        LogUtil.a(this.TAG, "goSetMyinfo enter");
        Intent intent = new Intent();
        intent.setClassName(this.mContext, "com.huawei.featureuserprofile.me.UserInfoActivity");
        intent.setPackage(this.mContext.getPackageName());
        intent.addFlags(268435456);
        this.mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void searchHmsAccount(final long j, String str) {
        LogUtil.a(this.TAG, "searchHmsAccount enter");
        try {
            HealthZoneUserManager.d().e(0, new JSONObject(str).optString("data"), new UserInfoCallback<exh.b>() { // from class: com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil.1
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void infoCallback(exh.b bVar) {
                    LogUtil.a(PluginHealthH5InteractionUtil.this.TAG, "searchHmsAccount infoCallback");
                    PluginHealthH5InteractionUtil.this.onSuccessCallback(j, bVar.c());
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i) {
                    LogUtil.b(PluginHealthH5InteractionUtil.this.TAG, "errorCallback errorCode is ", Integer.valueOf(i));
                    PluginHealthH5InteractionUtil.this.onFailureCallback(j, "requestFindUserInfo errorCallback FAIL", i);
                }
            });
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "searchHmsAccount jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void getOtherUserInfo(final int i, String str) {
        try {
            ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getOtherUserInfo(new JSONObject(str).optString("data"), new UserInfoCallback<exg>() { // from class: com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil.3
                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void infoCallback(exg exgVar) {
                    LogUtil.a(PluginHealthH5InteractionUtil.this.TAG, "getOtherUserInfo infoCallback");
                    PluginHealthH5InteractionUtil.this.onSuccessCallback(i, exgVar.b().e().a());
                }

                @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
                public void errorCallback(int i2) {
                    LogUtil.b(PluginHealthH5InteractionUtil.this.TAG, "errorCallback errorCode is ", Integer.valueOf(i2));
                    PluginHealthH5InteractionUtil.this.onFailureCallback(i, "getOtherUserInfo errorCallback FAIL", i2);
                }
            });
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getOtherUserInfo jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(i, LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void getMemberNameAndIcon(long j, String str) {
        JSONArray jSONArray;
        LogUtil.a(this.TAG, "getMemberNameAndIcon enter, callbackId:", Long.valueOf(j));
        try {
            jSONArray = new JSONObject(str).optJSONArray("members");
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getMemberNameAndIcon jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
            jSONArray = null;
        }
        List<mqq> synchronizedList = Collections.synchronizedList(new ArrayList(20));
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h(this.TAG, "members == null || members.length() == 0");
            return;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                getUserDataFormWhere(Long.parseLong(jSONArray.getString(i)), synchronizedList, length, j);
            } catch (NumberFormatException | JSONException unused) {
                updateFollowedImage(0L, null, synchronizedList, length, j);
            }
        }
    }

    private void getUserDataFormWhere(final long j, final List<mqq> list, final int i, final long j2) {
        LogUtil.a(this.TAG, "getUserImageAndNicknameFromSocial enter");
        HealthZoneUserManager.d().e(2, String.valueOf(j), new UserInfoCallback<exh.b>() { // from class: com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil.2
            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void infoCallback(exh.b bVar) {
                LogUtil.a(PluginHealthH5InteractionUtil.this.TAG, "getUserImageAndNicknameFromSocial infoCallback");
                PluginHealthH5InteractionUtil.this.updateFollowedImage(j, bVar, list, i, j2);
            }

            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            public void errorCallback(int i2) {
                LogUtil.h(PluginHealthH5InteractionUtil.this.TAG, "getUserImageAndNicknameFromSocial errorCallback errorCode:", Integer.valueOf(i2));
                PluginHealthH5InteractionUtil.this.updateFollowedImage(j, null, list, i, j2);
                PluginHealthH5InteractionUtil.this.onFailureCallback(j2, String.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFollowedImage(long j, exh.b bVar, List<mqq> list, int i, long j2) {
        LogUtil.a(this.TAG, "updateFollowedImage, callbackId:", Long.valueOf(j2));
        mqq mqqVar = new mqq();
        mqqVar.c(j);
        if (bVar != null) {
            mqqVar.d(bVar.e());
            mqqVar.e(bVar.d());
        }
        list.add(mqqVar);
        LogUtil.a(this.TAG, "imageList.size():", Integer.valueOf(list.size()), ", count:", Integer.valueOf(i));
        if (list.size() == i) {
            onSuccessCallback(j2, HiJsonUtil.e(list));
        }
    }

    @JavascriptInterface
    public void getPreDeviceInfo(long j) {
        LogUtil.a(this.TAG, "getPreDeviceInfo enter");
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceUdid(FoundationCommonUtil.getAndroidId(this.mContext));
        deviceInfo.setDeviceName(Build.MANUFACTURER + " " + Build.PRODUCT);
        DeviceInfo a2 = jpt.a(this.TAG);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("phone", HiJsonUtil.e(deviceInfo));
            jSONObject.put(DeviceTypeConsts.WATCH, HiJsonUtil.e(a2));
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getPreDeviceInfo jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    private Bitmap getAppIcon() {
        Drawable loadIcon = BaseApplication.getContext().getApplicationInfo().loadIcon(BaseApplication.getContext().getPackageManager());
        if (loadIcon instanceof BitmapDrawable) {
            return ((BitmapDrawable) loadIcon).getBitmap();
        }
        return null;
    }

    @JavascriptInterface
    public void setMemberListInfo(long j, String str) {
        try {
            LogUtil.a(this.TAG, "setMemberListInfo, param: ", str, ", callbackId: ", Long.valueOf(j));
            FunctionSetBeanHelper.c().d(new JSONObject(str));
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "shareMemberListInfo jsonException", LogAnonymous.b((Throwable) e));
        }
    }

    @JavascriptInterface
    public void shareLink(long j, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("url");
            String optString2 = jSONObject.optString("title");
            String optString3 = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                shareLink(optString, optString2, optString3, jSONObject.optString("iconUrl"), jSONObject.optBoolean("shareFamily", false));
                onSuccessCallback(j);
                return;
            }
            onFailureCallback(j, "param is invalid");
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "shareLink jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
    }

    private void shareLink(String str, String str2, String str3, String str4, boolean z) {
        Bitmap string2Bitmap = TextUtils.isEmpty(str4) ? null : string2Bitmap(str4);
        if (string2Bitmap == null) {
            string2Bitmap = getAppIcon();
        }
        fdu fduVar = new fdu(2);
        fduVar.a(str3);
        fduVar.awp_(string2Bitmap);
        fduVar.f(str);
        fduVar.c(str2);
        fduVar.b(z);
        fduVar.b(AnalyticsValue.SHARE_1140001.value());
        shareContent(BaseApplication.getActivity(), fduVar, false);
    }

    private Bitmap string2Bitmap(String str) {
        byte[] decode = Base64.decode(str.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "").getBytes(Charset.defaultCharset()), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    private static void shareContent(Context context, fdu fduVar, boolean z) {
        SocialShareCenterApi socialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        fduVar.c(z);
        socialShareCenterApi.exeShare(fduVar, context);
    }

    @JavascriptInterface
    public void uploadFiles(long j, String str) {
        LogUtil.a(this.TAG, "uploadFiles:", str);
        JSONArray jSONArray = null;
        try {
            jSONArray = new JSONObject(str).optJSONArray("files");
            LogUtil.a(this.TAG, "uploadFiles files:", jSONArray.toString());
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "uploadFiles jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h(this.TAG, "members == null || members.length() == 0");
            return;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                if (!TextUtils.isEmpty(jSONArray.getString(i)) && jSONArray.getString(i).contains("webp")) {
                    addPath(jSONArray.getString(i));
                    getShareImage(this.mListImagePath, 1, j);
                }
                if (!TextUtils.isEmpty(jSONArray.getString(i)) && jSONArray.getString(i).contains("mp4")) {
                    addPath(jSONArray.getString(i));
                    this.mIsVideo = true;
                    getShareVideo(this.mListVideoPath, 3, j);
                }
            } catch (NumberFormatException | JSONException unused) {
                LogUtil.b(this.TAG, "uploadFiles err");
            }
        }
    }

    private void addPath(String str) {
        if (str.contains(".webp")) {
            this.mListImagePath.add(str);
        } else {
            this.mListVideoPath.add(str);
        }
    }

    private void getShareImage(List<String> list, int i, final long j) {
        HealthZoneUserManager.d().a(i, list, new HealthZoneUserManager.UploadDataFilesCallback() { // from class: com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil.5
            @Override // com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager.UploadDataFilesCallback
            public void infoCallback(List<jaj> list2) {
                PluginHealthH5InteractionUtil.this.updateImageId(list2, j);
            }

            @Override // com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager.UploadDataFilesCallback
            public void errorCallback(int i2) {
                LogUtil.a(PluginHealthH5InteractionUtil.this.TAG, "getShareImage errorCallback errorCode:", Integer.valueOf(i2));
                PluginHealthH5InteractionUtil.this.onFailureCallback(j, String.valueOf(i2));
            }
        });
    }

    private void getShareVideo(List<String> list, int i, final long j) {
        HealthZoneUserManager.d().a(i, list, new HealthZoneUserManager.UploadDataFilesCallback() { // from class: com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil.6
            @Override // com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager.UploadDataFilesCallback
            public void infoCallback(List<jaj> list2) {
                PluginHealthH5InteractionUtil.this.updateVideoId(list2, j);
            }

            @Override // com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager.UploadDataFilesCallback
            public void errorCallback(int i2) {
                LogUtil.a(PluginHealthH5InteractionUtil.this.TAG, "getShareVideo errorCallback errorCode:", Integer.valueOf(i2));
                PluginHealthH5InteractionUtil.this.onFailureCallback(j, String.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateImageId(List<jaj> list, long j) {
        this.mPathFileidList.addAll(list);
        LogUtil.a(this.TAG, "updateImageId:", HiJsonUtil.e(this.mPathFileidList));
        if (!this.mIsVideo) {
            onSuccessCallback(j, HiJsonUtil.e(this.mPathFileidList));
        } else if (this.mPathFileidList.size() > 1) {
            onSuccessCallback(j, HiJsonUtil.e(this.mPathFileidList));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVideoId(List<jaj> list, long j) {
        this.mPathFileidList.addAll(list);
        LogUtil.a(this.TAG, "updateVideoId:", HiJsonUtil.e(this.mPathFileidList));
        if (this.mPathFileidList.size() > 1) {
            onSuccessCallback(j, HiJsonUtil.e(this.mPathFileidList));
        }
    }

    @JavascriptInterface
    public void setFallDetectionAuthorization(final long j) {
        mps mpsVar = new mps();
        mpsVar.c(IEventListener.EVENT_ID_DEVICE_RTSP_CONN);
        mpx mpxVar = new mpx();
        mpxVar.a(mpsVar);
        LogUtil.a(this.TAG, "sendMsgToDevice ResponseMsgBody = ", HiJsonUtil.e(mpxVar));
        mpy.d().a(new DeviceDataListener() { // from class: mql
            @Override // com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener
            public final void onResult(int i, mpx mpxVar2) {
                PluginHealthH5InteractionUtil.this.m772x458a36dc(j, i, mpxVar2);
            }
        }, IEventListener.EVENT_ID_DEVICE_UPDATE);
        mpy.d().activeSendMsgToDevice(0, mpxVar, new DeviceDataListener() { // from class: mqn
            @Override // com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener
            public final void onResult(int i, mpx mpxVar2) {
                PluginHealthH5InteractionUtil.this.m773xe1f8333b(j, i, mpxVar2);
            }
        });
    }

    /* renamed from: lambda$setFallDetectionAuthorization$0$com-huawei-pluginhealthzone-jsmodule-PluginHealthH5InteractionUtil, reason: not valid java name */
    public /* synthetic */ void m772x458a36dc(long j, int i, mpx mpxVar) {
        setResultCode(j, i);
    }

    /* renamed from: lambda$setFallDetectionAuthorization$1$com-huawei-pluginhealthzone-jsmodule-PluginHealthH5InteractionUtil, reason: not valid java name */
    public /* synthetic */ void m773xe1f8333b(long j, int i, mpx mpxVar) {
        LogUtil.a(this.TAG, "sendMsgToDevice resultCode = ", Integer.valueOf(i), "ResponseMsgBody = ");
        if (i == -1) {
            setResultCode(j, i);
        }
    }

    private void setResultCode(long j, int i) {
        LogUtil.a(this.TAG, "setResultCode resultCode = ", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", i);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "setFallDetectionAuthorization jsonException", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
        }
        mpy.d().e(IEventListener.EVENT_ID_DEVICE_UPDATE);
    }

    @JavascriptInterface
    public void stopFallDetectionAuthorization(long j) {
        mps mpsVar = new mps();
        mpsVar.c(3009);
        mpx mpxVar = new mpx();
        mpxVar.a(mpsVar);
        LogUtil.a(this.TAG, "stopFallDetectionAuthorization ResponseMsgBody = ", HiJsonUtil.e(mpxVar));
        mpy.d().activeSendMsgToDevice(0, mpxVar, new DeviceDataListener() { // from class: mqm
            @Override // com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener
            public final void onResult(int i, mpx mpxVar2) {
                PluginHealthH5InteractionUtil.this.m774xb6bcd3a0(i, mpxVar2);
            }
        });
    }

    /* renamed from: lambda$stopFallDetectionAuthorization$2$com-huawei-pluginhealthzone-jsmodule-PluginHealthH5InteractionUtil, reason: not valid java name */
    public /* synthetic */ void m774xb6bcd3a0(int i, mpx mpxVar) {
        LogUtil.a(this.TAG, "stopFallDetectionAuthorization resultCode = ", Integer.valueOf(i));
    }

    @JavascriptInterface
    public void saveEmergencyContacts(long j, String str) {
        LogUtil.a(this.TAG, "saveEmergencyContacts enter");
        if (!TextUtils.isEmpty(str)) {
            try {
                gmx gmxVar = (gmx) new Gson().fromJson(str, gmx.class);
                LogUtil.a(this.TAG, "contactInfo: ", gmxVar.toString());
                boolean saveAppEmergencyContacts = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).saveAppEmergencyContacts(gmxVar);
                LogUtil.a(this.TAG, "isSave: ", Boolean.valueOf(saveAppEmergencyContacts));
                onSuccessCallback(j, Boolean.valueOf(saveAppEmergencyContacts));
                return;
            } catch (JsonSyntaxException e) {
                LogUtil.b(this.TAG, "parse contactInformation error:", e.getMessage());
                onFailureCallback(j, "parse contactInformation error");
                return;
            }
        }
        onFailureCallback(j, "contactInformation is null");
    }

    @JavascriptInterface
    public void getAndroidEmergencyContact(long j) {
        LogUtil.a(this.TAG, "getAndroidEmergencyContact enter");
        List<gmx> emergencyContact = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getEmergencyContact();
        LogUtil.a(this.TAG, "emergencyContactSize: ", Integer.valueOf(emergencyContact.size()));
        onSuccessCallback(j, HiJsonUtil.e(emergencyContact));
    }

    @JavascriptInterface
    public void isHarmony(long j) {
        LogUtil.a(this.TAG, "isHarmony enter");
        boolean hasEmergencyInfoProvider = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).hasEmergencyInfoProvider();
        LogUtil.a(this.TAG, "isHarmony: ", Boolean.valueOf(hasEmergencyInfoProvider));
        onSuccessCallback(j, Boolean.valueOf(hasEmergencyInfoProvider));
    }

    @JavascriptInterface
    public void getHarmonyEmergencyContacts(long j) {
        LogUtil.a(this.TAG, "getHarmonyEmergencyContacts enter");
        List<String> harmonyEmergencyContactsUri = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getHarmonyEmergencyContactsUri();
        LogUtil.a(this.TAG, "emergencyContactsSize: ", Integer.valueOf(harmonyEmergencyContactsUri.size()));
        onSuccessCallback(j, Integer.valueOf(harmonyEmergencyContactsUri.size()));
    }

    @JavascriptInterface
    public void harmonyEmergencyContactsPage(long j) {
        LogUtil.a(this.TAG, "harmonyEmergencyContactsPage enter");
        try {
            if (getMetaDataFromApp() == 0.0f) {
                Intent intent = new Intent();
                intent.setClassName(PACKAGE_NAME, CLASS_NAME);
                this.mContext.startActivity(intent);
            } else {
                this.mContext.startActivity(new Intent(EMRGENCY_CONTACTS));
            }
            onSuccessCallback(j, true);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(this.TAG, "Emui110 activityNotFoundException SOS");
            onFailureCallback(j, "Emui110 activityNotFoundException SOS");
        }
    }

    @JavascriptInterface
    public void androidEmergencyContactsPage(long j) {
        LogUtil.a(this.TAG, "androidEmergencyContactsPage enter");
        AppRouter.b("/HWUserProfileMgr/EditContactActivity").c(BaseApplication.getContext());
        onSuccessCallback(j, true);
    }

    @JavascriptInterface
    public void addContact(final long j, String str) {
        LogUtil.a(this.TAG, "addContact enter");
        if (!TextUtils.isEmpty(str)) {
            try {
                final gmx gmxVar = (gmx) new Gson().fromJson(str, gmx.class);
                if (gmxVar != null && !TextUtils.isEmpty(gmxVar.a()) && !TextUtils.isEmpty(gmxVar.c())) {
                    LogUtil.a(this.TAG, "addContact contactInfo: ", gmxVar.toString());
                    PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.READ_WRITE_CONTACT, new CustomPermissionAction(this.mContext) { // from class: com.huawei.pluginhealthzone.jsmodule.PluginHealthH5InteractionUtil.9
                        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                        public void onGranted() {
                            LogUtil.h(PluginHealthH5InteractionUtil.this.TAG, "addContact permission success");
                            PluginHealthH5InteractionUtil.this.harmonyWriteContacts(j, gmxVar);
                        }

                        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                        public void onDenied(String str2) {
                            LogUtil.h(PluginHealthH5InteractionUtil.this.TAG, "addContact permission denied");
                            super.onDenied(str2);
                            PluginHealthH5InteractionUtil.this.onFailureCallback(j, "addContact permission denied");
                        }

                        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                            LogUtil.h(PluginHealthH5InteractionUtil.this.TAG, "addContact permission forever denied, show the guide window");
                            super.onForeverDenied(permissionType);
                            PluginHealthH5InteractionUtil.this.onFailureCallback(j, "addContact permission forever denied, show the guide window");
                        }
                    });
                    return;
                }
                onFailureCallback(j, "The name or phone number is empty.");
                return;
            } catch (JsonSyntaxException e) {
                LogUtil.b(this.TAG, "addContact parse contactInformation error:", e.getMessage());
                onFailureCallback(j, "addContact parse contactInformation error");
                return;
            }
        }
        onFailureCallback(j, "addContact contactInformation is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void harmonyWriteContacts(long j, gmx gmxVar) {
        ContentProviderClient contentProviderClient;
        Uri addHarmonyContact = addHarmonyContact(gmxVar.a(), gmxVar.c());
        LogUtil.a(this.TAG, "isContacts: ", addHarmonyContact);
        if (addHarmonyContact == null) {
            onSuccessCallback(j, false);
            return;
        }
        if (getMetaDataFromApp() == 0.0f) {
            LogUtil.a(this.TAG, "Harmony not updated");
            onSuccessCallback(j, true);
            return;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (contentResolver == null) {
            LogUtil.h(this.TAG, "harmonyWriteContacts contentResolver is null");
            return;
        }
        LogUtil.a(this.TAG, "The Harmony has been updated.");
        ContentProviderClient contentProviderClient2 = null;
        try {
            try {
                contentProviderClient = contentResolver.acquireUnstableContentProviderClient(AUTHORITIES);
            } catch (IllegalArgumentException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            contentProviderClient = contentProviderClient2;
        }
        try {
            if (contentProviderClient == null) {
                LogUtil.h(this.TAG, "emergencyInfoProviderClient is null");
                releaseResource(contentProviderClient);
                return;
            }
            Bundle call = contentResolver.call(AUTHORITIES, METHOD, addHarmonyContact.toString(), (Bundle) null);
            if (call != null) {
                onSuccessCallback(j, call.get("RESULT"));
                LogUtil.a(this.TAG, "RESULT: ", call.get("RESULT"));
            }
            releaseResource(contentProviderClient);
        } catch (IllegalArgumentException e2) {
            e = e2;
            contentProviderClient2 = contentProviderClient;
            LogUtil.b(this.TAG, "requestEmergencyInfoProvider getContentResolver call ", LogAnonymous.b((Throwable) e));
            onFailureCallback(j, LogAnonymous.b((Throwable) e));
            releaseResource(contentProviderClient2);
        } catch (Throwable th2) {
            th = th2;
            releaseResource(contentProviderClient);
            throw th;
        }
    }

    @JavascriptInterface
    public void getHarmonyEmergencyContact(long j) {
        LogUtil.a(this.TAG, "getHarmonyEmergencyContact enter");
        List<gmx> emergencyContact = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getEmergencyContact();
        LogUtil.a(this.TAG, "filteredEmergencyContacts", Integer.valueOf(emergencyContact.size()));
        onSuccessCallback(j, HiJsonUtil.e(emergencyContact));
    }

    @JavascriptInterface
    public void getHarmonyUpdate(long j) {
        LogUtil.a(this.TAG, "getHarmonyUpdate enter");
        onSuccessCallback(j, Boolean.valueOf(getMetaDataFromApp() != 0.0f));
    }

    public Uri addHarmonyContact(String str, String str2) {
        LogUtil.h(this.TAG, "addHarmonyContact");
        ContentValues contentValues = new ContentValues();
        long parseId = ContentUris.parseId(BaseApplication.getContext().getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues));
        contentValues.clear();
        contentValues.put("raw_contact_id", Long.valueOf(parseId));
        contentValues.put("mimetype", "vnd.android.cursor.item/name");
        contentValues.put("data2", str);
        BaseApplication.getContext().getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contentValues);
        LogUtil.h(this.TAG, "addHarmonyContact success");
        contentValues.clear();
        contentValues.put("raw_contact_id", Long.valueOf(parseId));
        contentValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
        contentValues.put("data1", str2);
        contentValues.put("data2", (Integer) 2);
        Uri insert = BaseApplication.getContext().getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contentValues);
        contentValues.clear();
        LogUtil.h(this.TAG, "addHarmonyContact success");
        return insert;
    }

    private float getMetaDataFromApp() {
        try {
            return BaseApplication.getContext().getPackageManager().getApplicationInfo(PACKAGE_NAME, 128).metaData.getFloat(EMERGENCY_METADATA);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b(this.TAG, "getMetaDataFromApp NameNotFoundException");
            return 0.0f;
        }
    }

    private void releaseResource(ContentProviderClient contentProviderClient) {
        if (contentProviderClient == null) {
            return;
        }
        try {
            contentProviderClient.close();
        } catch (Exception e) {
            LogUtil.b(this.TAG, "release providerClient failed with exception: ", LogAnonymous.b((Throwable) e));
        }
    }
}
