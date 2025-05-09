package com.huawei.operation.js;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.adapter.AchievementShareCallback;
import com.huawei.operation.adapter.CloseWebCallback;
import com.huawei.operation.adapter.JsDataCallback;
import com.huawei.operation.adapter.OnActivityQuitCallback;
import com.huawei.operation.adapter.OnActivityShowButtonCallback;
import com.huawei.operation.adapter.OnCaptureCallback;
import com.huawei.operation.adapter.OnHealthDataCallback;
import com.huawei.operation.adapter.OnLoginCallback;
import com.huawei.operation.adapter.OnVmallCouponCallback;
import com.huawei.operation.adapter.OnWebViewStatusCallback;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.adapter.SendCurrentUrlCallback;
import com.huawei.operation.adapter.SendNoNetMsgCallback;
import com.huawei.operation.adapter.SendServerErrorMsgCallback;
import com.huawei.operation.adapter.SetFullscreenCallback;
import com.huawei.operation.adapter.SetLeftBtnArrowTypeCallback;
import com.huawei.operation.adapter.SetShareDataCallback;
import com.huawei.operation.adapter.SetTitleCallback;
import com.huawei.operation.adapter.ShareCallback;
import com.huawei.operation.adapter.SleepQuestionnaireCallback;
import com.huawei.operation.adapter.SportsStatisticsCallback;
import com.huawei.operation.adapter.StartAppSettingPage;
import com.huawei.operation.adapter.StartFitnessPageCallback;
import com.huawei.operation.adapter.StartGpsTrackPageCallback;
import com.huawei.operation.adapter.StartMiniShopWebPage;
import com.huawei.operation.adapter.StartSportPage;
import com.huawei.operation.adapter.StartWebPage;
import com.huawei.operation.adapter.ToastCallback;
import com.huawei.operation.adapter.TouchSignalCallback;
import com.huawei.operation.adapter.UpdateCustomTitleBarCallback;
import com.huawei.operation.adapter.VmallArgSignCallback;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.operation.myhuawei.MyHuaweiLogin;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.AbilitySetUtils;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.UriConstants;
import com.huawei.operation.utils.Utils;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.up.model.UserInfomation;
import com.huawei.up.utils.Constants;
import defpackage.cun;
import defpackage.dsl;
import defpackage.gmz;
import defpackage.ixq;
import defpackage.ixx;
import defpackage.jah;
import defpackage.jcu;
import defpackage.jei;
import defpackage.jes;
import defpackage.njn;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Sha256;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JsInteraction extends JsModuleBase {
    private static final String BASE64_JPEG_HEAD = "data:image/jpeg;base64,";
    private static final String BASE64_PNG_HEAD = "data:image/png;base64,";
    private static final String BASE64_WEBP_HEAD = "data:image/webp;base64,";
    private static final String COMMON_CONFIG_FEEDBACKSDK_SWITCH = "common_config_feedbacksdk_switch";
    private static final int EMUI = 1;
    private static final String FEEDBACK_SWITCH_OFF = "off";
    private static final String FEEDBACK_SWITCH_ON = "on";
    private static final int HARMONY_OS = 2;
    private static final String LABEL = "label";
    private static final int MAGIC_UI = 0;
    private static final int MAX_LENGTH = 120;
    private static final int MAX_QUALITY = 100;
    private static final String PARAMS_CONNECTOR_SYMBOL = "&";
    private static final String PARAMS_EQUAL_SYMBOL = "=";
    private static final String TAG = "PluginOperation_[Operation Version 1.2]JsInteraction";
    private static String mDeviceId = "";
    private AchievementShareCallback mAchievementShareCallback;
    protected CloseWebCallback mCloseWebCallback;
    private Context mContext;
    private String mHuaweiHost;
    protected JsDataCallback mJsDataCallback;
    private Map<String, String> mMapInfo;
    private OnActivityQuitCallback mOnActivityQuitCallback;
    private OnActivityShowButtonCallback mOnActivityShowButtonCallback;
    protected OnCaptureCallback mOnCaptureCallback;
    private OnHealthDataCallback mOnHealthDataCallback;
    private OnLoginCallback mOnLoginCallback;
    private OnVmallCouponCallback mOnVmallCouponCallback;
    private OnWebViewStatusCallback mOnWebViewStatusCallback;
    protected SendCurrentUrlCallback mSendCurrentUrlCallback;
    private SendNoNetMsgCallback mSendNoNetMsgCallback;
    protected SendServerErrorMsgCallback mSendServerErrorMsgCallback;
    private SetFullscreenCallback mSetFullscreenCallback;
    private SetLeftBtnArrowTypeCallback mSetLeftBtnArrowTypeCallback;
    private SetShareDataCallback mSetShareDataCallback;
    private SetTitleCallback mSetTitleCallback;
    private ShareCallback mShareCallback;
    private SleepQuestionnaireCallback mSleepQuestionnaireCallback;
    private SportsStatisticsCallback mSportsStatisticsCallback;
    protected StartAppSettingPage mStartAppSettingPage;
    private StartFitnessPageCallback mStartFitnessPageCallback;
    private StartGpsTrackPageCallback mStartGpsTrackPageCallback;
    private StartMiniShopWebPage mStartMiniShopWebPage;
    private StartSportPage mStartSportPage;
    private StartWebPage mStartWebPage;
    protected ToastCallback mToastCallback;
    private TouchSignalCallback mTouchSignalCallback;
    private UpdateCustomTitleBarCallback mUpdateCustomTitleBarCallback;
    private VmallArgSignCallback mVmallArgSignCallback;
    protected PluginOperationAdapter mAdapter = null;
    private String mUrlLoginHost = null;

    public JsInteraction() {
    }

    public JsInteraction(Context context) {
        init(context);
    }

    protected final void init(Context context) {
        this.mContext = context;
        PluginBaseAdapter adapter = PluginOperation.getInstance(context).getAdapter();
        if (adapter instanceof PluginOperationAdapter) {
            PluginOperationAdapter pluginOperationAdapter = (PluginOperationAdapter) adapter;
            this.mAdapter = pluginOperationAdapter;
            this.mMapInfo = pluginOperationAdapter.getInfo(new String[]{"getAppInfo", "getDeviceInfo", "getLoginInfo", Constants.METHOD_GET_USER_INFO, "getPhoneInfo"});
        }
        GRSManager.a(this.mContext).e("domainWwwHuawei", new GrsQueryCallback() { // from class: com.huawei.operation.js.JsInteraction.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                JsInteraction.this.mHuaweiHost = str;
                if (TextUtils.isEmpty(JsInteraction.this.mHuaweiHost)) {
                    JsInteraction.this.mHuaweiHost = "https:/";
                    LogUtil.h(JsInteraction.TAG, "JsInteraction mHuaweiHost is empty");
                }
                LogUtil.c(JsInteraction.TAG, "JsInteraction mHuaweiHost = ", JsInteraction.this.mHuaweiHost);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                if (TextUtils.isEmpty(JsInteraction.this.mHuaweiHost)) {
                    JsInteraction.this.mHuaweiHost = "https:/";
                    LogUtil.h(JsInteraction.TAG, "JsInteraction onCallBackFail, mHuaweiHost is empty");
                }
                LogUtil.h(JsInteraction.TAG, "onCallBackFail index = ", Integer.valueOf(i));
            }
        });
    }

    public static void setDeviceId(String str) {
        mDeviceId = str;
        LogUtil.a(TAG, "setDeviceId = ", CommonUtil.l(str));
    }

    public void setShareCallback(ShareCallback shareCallback, AchievementShareCallback achievementShareCallback, OnCaptureCallback onCaptureCallback) {
        this.mShareCallback = shareCallback;
        this.mAchievementShareCallback = achievementShareCallback;
        this.mOnCaptureCallback = onCaptureCallback;
        LogUtil.c(TAG, "mShareCallback: ", shareCallback, " mAchievementShareCallback: ", achievementShareCallback, " mOnCaptureCallback: ", onCaptureCallback);
    }

    public void setTouchCallback(TouchSignalCallback touchSignalCallback, OnLoginCallback onLoginCallback, OnWebViewStatusCallback onWebViewStatusCallback) {
        this.mTouchSignalCallback = touchSignalCallback;
        this.mOnLoginCallback = onLoginCallback;
        this.mOnWebViewStatusCallback = onWebViewStatusCallback;
    }

    public void setWidgetCallback(SetTitleCallback setTitleCallback, ToastCallback toastCallback, JsDataCallback jsDataCallback) {
        this.mSetTitleCallback = setTitleCallback;
        this.mToastCallback = toastCallback;
        this.mJsDataCallback = jsDataCallback;
    }

    public void setTitleBarCallback(UpdateCustomTitleBarCallback updateCustomTitleBarCallback, SetFullscreenCallback setFullscreenCallback, SetLeftBtnArrowTypeCallback setLeftBtnArrowTypeCallback, SetShareDataCallback setShareDataCallback) {
        this.mUpdateCustomTitleBarCallback = updateCustomTitleBarCallback;
        this.mSetFullscreenCallback = setFullscreenCallback;
        this.mSetLeftBtnArrowTypeCallback = setLeftBtnArrowTypeCallback;
        this.mSetShareDataCallback = setShareDataCallback;
    }

    public void setSendCallback(SendServerErrorMsgCallback sendServerErrorMsgCallback, SendNoNetMsgCallback sendNoNetMsgCallback, SendCurrentUrlCallback sendCurrentUrlCallback) {
        this.mSendServerErrorMsgCallback = sendServerErrorMsgCallback;
        this.mSendNoNetMsgCallback = sendNoNetMsgCallback;
        this.mSendCurrentUrlCallback = sendCurrentUrlCallback;
    }

    public void setVmallCallback(OnActivityQuitCallback onActivityQuitCallback, OnVmallCouponCallback onVmallCouponCallback, SleepQuestionnaireCallback sleepQuestionnaireCallback, VmallArgSignCallback vmallArgSignCallback) {
        this.mOnActivityQuitCallback = onActivityQuitCallback;
        this.mOnVmallCouponCallback = onVmallCouponCallback;
        this.mSleepQuestionnaireCallback = sleepQuestionnaireCallback;
        this.mVmallArgSignCallback = vmallArgSignCallback;
    }

    public void setSportsStatisticsCallback(SportsStatisticsCallback sportsStatisticsCallback) {
        this.mSportsStatisticsCallback = sportsStatisticsCallback;
    }

    public void setActivityShareButtonCallback(OnActivityShowButtonCallback onActivityShowButtonCallback) {
        this.mOnActivityShowButtonCallback = onActivityShowButtonCallback;
    }

    public void setStarCallback(StartGpsTrackPageCallback startGpsTrackPageCallback, StartFitnessPageCallback startFitnessPageCallback) {
        this.mStartGpsTrackPageCallback = startGpsTrackPageCallback;
        this.mStartFitnessPageCallback = startFitnessPageCallback;
    }

    public void setStarAndEndCallback(StartMiniShopWebPage startMiniShopWebPage, StartWebPage startWebPage, StartAppSettingPage startAppSettingPage, CloseWebCallback closeWebCallback) {
        this.mStartMiniShopWebPage = startMiniShopWebPage;
        this.mStartWebPage = startWebPage;
        this.mStartAppSettingPage = startAppSettingPage;
        this.mCloseWebCallback = closeWebCallback;
    }

    public void setStartSportPageCallback(StartSportPage startSportPage) {
        this.mStartSportPage = startSportPage;
    }

    public void setOnHealthDataCallback(OnHealthDataCallback onHealthDataCallback) {
        this.mOnHealthDataCallback = onHealthDataCallback;
    }

    @JavascriptInterface
    public void login(String str) {
        String currentUrl = this.mSendCurrentUrlCallback.getCurrentUrl();
        PluginOperation.getInstance(this.mContext).setActivityFlag(true);
        if (currentUrl.startsWith("https://") || currentUrl.startsWith("http://")) {
            LogUtil.a(TAG, "url.startsWith(\"https://\") || url.startsWith(\"http://\")");
            this.mUrlLoginHost = Uri.parse(currentUrl).getHost();
        }
        LogUtil.a(TAG, "login() url=..., huid=..., serviceId=...;function=...");
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1011);
        String serviceIdByUrl = this.mAdapter.getServiceIdByUrl(com.huawei.operation.utils.Constants.HEALTH_LOGIN + currentUrl);
        OnLoginCallback onLoginCallback = this.mOnLoginCallback;
        if (onLoginCallback != null) {
            onLoginCallback.onLogin(accountInfo, serviceIdByUrl, str);
        } else {
            LogUtil.a(TAG, "mOnLoginCallback is null");
        }
    }

    @JavascriptInterface
    public String getFitnessData(String str, String str2) {
        LogUtil.a(TAG, "getFitnessData startTime = ", str, ",endTime = ", str2);
        return JsInteractAddition.getInstance().getFitnessDataForWeb(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str, str2);
    }

    @JavascriptInterface
    public String getFitnessStat(String str, String str2) {
        LogUtil.a(TAG, "getFitnessStat");
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (!checkUrlIsLegal()) {
            LogUtil.a(TAG, "getFitnessStat checkUrlIsLegal return false");
            initJsonDataForH5(jSONObject, jSONArray, ResultCode.ERROR_TS_TIMEOUT, "getFitnessStat JSONException checkUrlIsLegal false");
            return jSONObject.toString();
        }
        if (!JsUtil.checkParamIsLegal(str, str2)) {
            LogUtil.a(TAG, "getFitnessStat params is not legal");
            initJsonDataForH5(jSONObject, jSONArray, "1001", "getFitnessStat JSONException ONE ");
            return jSONObject.toString();
        }
        List<Map<String, Object>> recordsByDateRange = this.mAdapter.getRecordsByDateRange(str, str2);
        if (recordsByDateRange == null || recordsByDateRange.isEmpty()) {
            LogUtil.a(TAG, "getFitnessStat sportListData is null");
            initJsonDataForH5(jSONObject, jSONArray, "0", "getFitnessStat JSONException TWO ");
            return jSONObject.toString();
        }
        putFitnessData(recordsByDateRange, jSONArray);
        initJsonDataForH5(jSONObject, jSONArray, "0", "getFitnessStat JSONException Four");
        return jSONObject.toString();
    }

    private void putFitnessData(List<Map<String, Object>> list, JSONArray jSONArray) {
        LogUtil.a(TAG, "putFitnessData");
        for (Map<String, Object> map : list) {
            JSONObject jSONObject = new JSONObject();
            Object obj = map.get("date");
            Object obj2 = map.get(JsUtil.SUGGESTION_TOTAL_CALORIE);
            Object obj3 = map.get("totalduration");
            if (obj != null && obj2 != null && obj3 != null) {
                try {
                    if (obj instanceof String) {
                        jSONObject.put("date", CommonUtil.h((String) obj));
                    }
                    if (obj2 instanceof String) {
                        jSONObject.put(JsUtil.SUGGESTION_TOTAL_CALORIE, CommonUtil.j((String) obj2));
                    }
                    if (obj3 instanceof String) {
                        jSONObject.put("totalduration", CommonUtil.h((String) obj3));
                    }
                    jSONArray.put(jSONObject);
                } catch (NumberFormatException | JSONException unused) {
                    LogUtil.b(TAG, "putFitnessData: JSONException or NumberFormatException");
                }
            }
        }
    }

    private void initJsonDataForH5(JSONObject jSONObject, JSONArray jSONArray, String str, String str2) {
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("data", jSONArray);
        } catch (JSONException unused) {
            LogUtil.a(TAG, str2);
        }
    }

    @JavascriptInterface
    public String getHuid() {
        return !checkUrlIsLegal() ? "" : LoginInit.getInstance(this.mContext).getAccountInfo(1011);
    }

    @JavascriptInterface
    public String getAccessToken() {
        LogUtil.a(TAG, "getAccessToken()");
        return !JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter) ? "" : LoginInit.getInstance(this.mContext).getAccountInfo(1015);
    }

    @JavascriptInterface
    public String getServiceToken() {
        LogUtil.a(TAG, "getServiceToken()");
        return JsInteractAddition.getInstance().getServerTokenForWeb(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public String getVersion() {
        LogUtil.a(TAG, "getVersion()");
        return JsInteractAddition.getInstance().getVersionForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getAppId() {
        LogUtil.a(TAG, "getAppId()");
        return JsInteractAddition.getInstance().getAppIdForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getAppType() {
        LogUtil.a(TAG, "getAppType()");
        return JsInteractAddition.getInstance().getAppTypeForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getDeviceType() {
        LogUtil.a(TAG, "getDeviceType()");
        return JsInteractAddition.getInstance().getDeviceTypeForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getUpDeviceType() {
        return LoginInit.getInstance(this.mContext).getDeviceType();
    }

    @JavascriptInterface
    public String getDeviceId() {
        LogUtil.a(TAG, "getDeviceId()");
        return JsInteractAddition.getInstance().getDeviceId(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, this.mMapInfo);
    }

    @JavascriptInterface
    public String getSysVersion() {
        LogUtil.a(TAG, "getSysVersion()");
        return JsInteractAddition.getInstance().getSystemVersionForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getBindDeviceType() {
        LogUtil.a(TAG, "getBindDeviceType()");
        return JsInteractAddition.getInstance().getBindDeviceTypeForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getPhoneType() {
        LogUtil.a(TAG, "getPhoneType()");
        return JsInteractAddition.getInstance().getPhoneTypeForWeb();
    }

    @JavascriptInterface
    public String getPhoneModel() {
        LogUtil.a(TAG, "getPhoneModel()");
        String str = Build.MODEL;
        return TextUtils.isEmpty(str) ? "" : str;
    }

    @JavascriptInterface
    public String getLanguage() {
        LogUtil.a(TAG, "getLanguage()");
        return JsInteractAddition.getInstance().getLanguageForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getEnvironment() {
        LogUtil.a(TAG, "getEnvironment()");
        return JsInteractAddition.getInstance().getEnvironmentForWeb(this.mMapInfo);
    }

    @JavascriptInterface
    public String getNickName() {
        LogUtil.a(TAG, "getNickName()");
        return JsInteractAddition.getInstance().getNickName(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, this.mMapInfo);
    }

    @JavascriptInterface
    public String getPhoto() {
        LogUtil.a(TAG, "getPhoto()");
        return JsInteractAddition.getInstance().getUserPhoto(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, this.mMapInfo);
    }

    @JavascriptInterface
    public void setTitle(String str) {
        LogUtil.a(TAG, "setTitle title:", str);
        SetTitleCallback setTitleCallback = this.mSetTitleCallback;
        if (setTitleCallback != null) {
            setTitleCallback.setTitle(str);
        }
    }

    @JavascriptInterface
    public void updateCustomTitleBarVisibility(String str) {
        LogUtil.a(TAG, "updateCustomTitleBarVisibility displayFlag:", str);
        UpdateCustomTitleBarCallback updateCustomTitleBarCallback = this.mUpdateCustomTitleBarCallback;
        if (updateCustomTitleBarCallback != null) {
            updateCustomTitleBarCallback.updateCustomTitleBarVisibility(str);
        }
    }

    @JavascriptInterface
    public void setFullscreen(String str) {
        LogUtil.a(TAG, "setFullscreen displayFlag:", str);
        SetFullscreenCallback setFullscreenCallback = this.mSetFullscreenCallback;
        if (setFullscreenCallback != null) {
            setFullscreenCallback.setFullscreen(str);
        }
    }

    @JavascriptInterface
    public void setTitleRightBlank(boolean z) {
        LogUtil.a(TAG, "setTitleRightBlank isShowBlank:", Boolean.valueOf(z));
        SetTitleCallback setTitleCallback = this.mSetTitleCallback;
        if (setTitleCallback != null) {
            setTitleCallback.setTitleRightBlank(!z);
        }
    }

    @JavascriptInterface
    public void setShareData(String str, String str2, String str3, String str4) {
        SetShareDataCallback setShareDataCallback;
        LogUtil.a(TAG, "interface setShareData");
        if (checkUrlIsLegal() && (setShareDataCallback = this.mSetShareDataCallback) != null) {
            setShareDataCallback.setShareData(str, str2, str3, str4);
        }
    }

    @JavascriptInterface
    public void setLeftBtnArrowType() {
        LogUtil.a(TAG, "interface setLeftBtnArrowType");
        SetLeftBtnArrowTypeCallback setLeftBtnArrowTypeCallback = this.mSetLeftBtnArrowTypeCallback;
        if (setLeftBtnArrowTypeCallback != null) {
            setLeftBtnArrowTypeCallback.setLeftBtnArrowType();
        }
    }

    @JavascriptInterface
    public void setNextH5PageLeftBtnArrowType() {
        LogUtil.a(TAG, "interface setNextH5PageLeftBtnArrowType");
        SetLeftBtnArrowTypeCallback setLeftBtnArrowTypeCallback = this.mSetLeftBtnArrowTypeCallback;
        if (setLeftBtnArrowTypeCallback != null) {
            setLeftBtnArrowTypeCallback.setNextH5PageLeftBtnArrowType();
        }
    }

    @JavascriptInterface
    public void startToSportPage(String str) {
        LogUtil.a(TAG, "startToSportPage pageType:", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "pageType can not empty");
            return;
        }
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            LogUtil.a(TAG, "the currentUrl is not Huawei super White Url");
            return;
        }
        StartSportPage startSportPage = this.mStartSportPage;
        if (startSportPage != null) {
            startSportPage.startToSportPage(str);
        }
    }

    @JavascriptInterface
    public void log(String str, String str2) {
        JsInteractAddition.getInstance().getLogForWeb(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str, str2);
    }

    @JavascriptInterface
    public void achievementShare(String str, String str2) {
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            LogUtil.a(TAG, "the current Url is not Huawei super White Url");
            return;
        }
        AchievementShareCallback achievementShareCallback = this.mAchievementShareCallback;
        if (achievementShareCallback != null) {
            achievementShareCallback.onAchievementShare(str, str2);
        }
    }

    @JavascriptInterface
    public void toast(String str, String str2) {
        LogUtil.a(TAG, "toast content:", str, ", time:", str2);
        ToastCallback toastCallback = this.mToastCallback;
        if (toastCallback != null) {
            toastCallback.onToast(str, str2);
        }
    }

    @JavascriptInterface
    public void share(String str, String str2, String str3) {
        LogUtil.a(TAG, "share activityId:", str, ", shareType:", str2, ", shareChannel:", str3);
        ShareCallback shareCallback = this.mShareCallback;
        if (shareCallback != null) {
            shareCallback.onShare(str, str2);
        }
    }

    @JavascriptInterface
    public void share(String str, String str2, String str3, String str4) {
        ShareCallback shareCallback;
        if (checkUrlIsLegal() && (shareCallback = this.mShareCallback) != null) {
            shareCallback.onShare(str, str2, str3, str4);
        }
    }

    @JavascriptInterface
    public String getApiVersion() {
        LogUtil.a(TAG, "getApiVersion return: ", com.huawei.operation.utils.Constants.JS_API_VERSION);
        return com.huawei.operation.utils.Constants.JS_API_VERSION;
    }

    @JavascriptInterface
    public String getGooglePlayVersion() {
        LogUtil.a(TAG, "getGPlayVersion");
        return "";
    }

    @JavascriptInterface
    public void getUserInfo(String str, String str2) {
        LogUtil.a(TAG, Constants.METHOD_GET_USER_INFO);
        getJsFunctionForWeb(str, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE, JsUtil.ServiceType.DATA, JsUtil.DataFunc.USER_INFO_DATA, str2);
    }

    @JavascriptInterface
    public void getFitnessStatData(String str, String str2) {
        getJsFunctionForWeb(str, this.mHuaweiHost + UriConstants.READ_SPORTS_DATA, JsUtil.ServiceType.DATA, JsUtil.DataFunc.FITNESS_DATA, str2);
    }

    @JavascriptInterface
    public void getHealthStat(String str, String str2) {
        getDataFunction(str, JsUtil.DataFunc.HEALTH_STAT, str2);
    }

    @JavascriptInterface
    public void getHealthData(String str, String str2) {
        LogUtil.a(TAG, BleConstants.GET_HEALTH_DATA);
        getDataFunction(str, JsUtil.DataFunc.HEALTH_DATA, str2);
    }

    @JavascriptInterface
    public void getAnnualReport(String str, String str2) {
        LogUtil.a(TAG, "getAnnualReport functionRes : ", str2);
        getDataFunction(str, JsUtil.DataFunc.ANNUAL_REPORT_DATA, str2);
    }

    private void getDataFunction(String str, String str2, String str3) {
        boolean checkFunctionIsLegal = OperationUtils.getInstance().checkFunctionIsLegal(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str);
        LogUtil.a(TAG, "getDataFunction param : ", str, ",functionResult : ", str3, ",isLegal", Boolean.valueOf(checkFunctionIsLegal));
        JsDataCallback jsDataCallback = this.mJsDataCallback;
        if (jsDataCallback != null) {
            jsDataCallback.callJsServiceFunction(JsUtil.ServiceType.DATA, str2, str, str3, checkFunctionIsLegal);
        }
    }

    private void getJsFunctionForWeb(String str, String str2, String str3, String str4, String str5) {
        String str6;
        LogUtil.a(TAG, "getJsFunctionForWeb functionResult : ", str5);
        try {
            str6 = new JSONObject(str).getString("accessToken");
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getJsFunctionForWeb JSONException param fail");
            str6 = "";
        }
        LogUtil.c(TAG, "getJsFunctionForWeb accessToken");
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str6, str2);
        LogUtil.a(TAG, "getJsFunctionForWeb checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        JsDataCallback jsDataCallback = this.mJsDataCallback;
        if (jsDataCallback != null) {
            jsDataCallback.callJsServiceFunction(str3, str4, str, str5, checkUrlIsLegalNew);
        }
    }

    private void getStressFunction(String str, String str2, String str3, String str4) {
        getJsFunctionForWeb(str, str2, JsUtil.ServiceType.STRESS, str3, str4);
    }

    @JavascriptInterface
    public void getMotionPathData(String str, String str2) {
        String str3;
        boolean checkUrlIsLegalNew;
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getMotionPathData JSONException param fail");
            str3 = "";
        }
        if (TextUtils.isEmpty(str3)) {
            LogUtil.a(TAG, "TextUtils.isEmpty(motionPathAt) and used old check");
            checkUrlIsLegalNew = checkUrlIsLegal();
        } else {
            LogUtil.a(TAG, "motionPathAt is not null,used new check");
            checkUrlIsLegalNew = checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.READ_MOVEMENT_TRACKS);
        }
        boolean z = checkUrlIsLegalNew;
        LogUtil.a(TAG, "getMotionPathData checkUrlIsLegal() return:", Boolean.valueOf(z));
        JsDataCallback jsDataCallback = this.mJsDataCallback;
        if (jsDataCallback != null) {
            jsDataCallback.callJsServiceFunction(JsUtil.ServiceType.DATA, JsUtil.DataFunc.MOTION_PATH_DATA, str, str2, z);
        }
    }

    @JavascriptInterface
    public void getSportData(String str, String str2, String str3) {
        LogUtil.a(TAG, "getSportData");
        boolean checkUrlIsLegal = checkUrlIsLegal();
        LogUtil.a(TAG, "getSportData param : ", str, ",functionRes : ", str2, ",functionErr : ", str3, ",isLegal : ", Boolean.valueOf(checkUrlIsLegal));
        JsDataCallback jsDataCallback = this.mJsDataCallback;
        if (jsDataCallback != null) {
            jsDataCallback.callJsSportDataFunction(str, str2, str3, checkUrlIsLegal);
        }
    }

    @JavascriptInterface
    public void getSportData(String str, String str2) {
        LogUtil.c(TAG, "getSportData functionRes = ", str2);
        getJsFunctionForWeb(str, this.mHuaweiHost + UriConstants.READ_SPORTS_DATA, JsUtil.ServiceType.DATA, JsUtil.DataFunc.SPORT_DATA, str2);
    }

    @JavascriptInterface
    public void registerDataClient(String str, String str2) {
        LogUtil.c(TAG, "registerDataClient param = ", str, ",functionRes = ", str2);
        getJsFunctionForWeb(str, this.mHuaweiHost + UriConstants.READ_WRITE_PERSONAL_PROFILE, JsUtil.ServiceType.DATA, JsUtil.DataFunc.REGISTER_DATA_CLIENT, str2);
    }

    @JavascriptInterface
    public void insertHealthData(String str, String str2) {
        LogUtil.a(TAG, "insertHealthData param = ", str, ",functionRes = ", str2);
        getDataFunction(str, JsUtil.DataFunc.INSERT_HEALTH_DATA, str2);
    }

    @JavascriptInterface
    public String getSportData() {
        LogUtil.a(TAG, "getSportData");
        return JsInteractAddition.getInstance().getSportDataForWeb(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public void startGPSTrailPage(int i, String str, float f) {
        LogUtil.a(TAG, "startGPSTrailPage sportType:", Integer.valueOf(i), " targetType:", str, " targetValue:", Float.valueOf(f));
        StartGpsTrackPageCallback startGpsTrackPageCallback = this.mStartGpsTrackPageCallback;
        if (startGpsTrackPageCallback != null) {
            startGpsTrackPageCallback.onStartGpsTrackPage(this.mContext, i, str, f);
        }
    }

    @JavascriptInterface
    public void startFitnessPage(String str, String str2) {
        LogUtil.a(TAG, "startFitnessPage fitnessCard:", str, " version:", str2);
        StartFitnessPageCallback startFitnessPageCallback = this.mStartFitnessPageCallback;
        if (startFitnessPageCallback != null) {
            startFitnessPageCallback.onStartFitnessPage((Context) new WeakReference(this.mContext).get(), str, str2);
        }
    }

    @JavascriptInterface
    public void startFitnessList() {
        LogUtil.a(TAG, "startFitnessList enter!");
        this.mAdapter.startFitnessList();
    }

    @JavascriptInterface
    public String getAbility() {
        String abilitySet = AbilitySetUtils.getAbilitySet();
        LogUtil.c(TAG, "getAbility()");
        return abilitySet;
    }

    @JavascriptInterface
    public void startMiniShopWebPage(String str, String str2) {
        LogUtil.a(TAG, "startMiniShopWebPage");
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            LogUtil.a(TAG, "the currentUrl is not Huawei super White Url");
            return;
        }
        StartMiniShopWebPage startMiniShopWebPage = this.mStartMiniShopWebPage;
        if (startMiniShopWebPage != null) {
            startMiniShopWebPage.onStartMiniShopWebPage(str, str2);
        }
    }

    @JavascriptInterface
    public void startWebPage(String str) {
        LogUtil.a(TAG, "startWebPage");
        if (TextUtils.isEmpty(str) || !Utils.isHttpOrHttps(str)) {
            LogUtil.a(TAG, "startWebPage empty url");
            return;
        }
        StartWebPage startWebPage = this.mStartWebPage;
        if (startWebPage != null) {
            startWebPage.onStartWebPage(str);
        }
    }

    @JavascriptInterface
    public void startAppSettingPage() {
        LogUtil.a(TAG, "startAppSettingPage");
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            LogUtil.a(TAG, "the currentUrl is not Huawei super White Url");
            return;
        }
        StartAppSettingPage startAppSettingPage = this.mStartAppSettingPage;
        if (startAppSettingPage != null) {
            startAppSettingPage.onStartAppSettingPage();
        }
    }

    @JavascriptInterface
    public void touchSignal(boolean z) {
        LogUtil.c(TAG, "touchSignal is ", Boolean.valueOf(z));
        if (this.mTouchSignalCallback != null) {
            LogUtil.c(TAG, "touchSignal mTouchSignalCallback ", Boolean.valueOf(z));
            this.mTouchSignalCallback.onTouchSignalCallback(z);
        }
    }

    @JavascriptInterface
    public String signInterfaceSHA256(String str, String str2) {
        try {
            return ixq.c(Sha256.e(OperationUtils.getInstance().parseJsonFromShop(str, str2).getBytes("UTF-8")), true);
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b(TAG, "UnsupportedEncodingException");
            return "";
        }
    }

    @JavascriptInterface
    public void closeWeb() {
        LogUtil.a(TAG, "closeWeb");
        CloseWebCallback closeWebCallback = this.mCloseWebCallback;
        if (closeWebCallback != null) {
            closeWebCallback.onCloseWebCallback();
        }
    }

    @JavascriptInterface
    public void goBackToMiniShop() {
        LogUtil.a(TAG, "goBackToMiniShop");
        CloseWebCallback closeWebCallback = this.mCloseWebCallback;
        if (closeWebCallback != null) {
            closeWebCallback.goBackToMiniShop();
        }
    }

    @JavascriptInterface
    public void sendServerErrorMsg() {
        LogUtil.a(TAG, "sendServerErrorMsg");
        if (this.mSendServerErrorMsgCallback != null) {
            LogUtil.a(TAG, "sendServerErrorMsg mSendServerErrorMsgCallback != null");
            this.mSendServerErrorMsgCallback.onSendServerErrorMsgCallback();
        }
    }

    @JavascriptInterface
    public void sendNoNetMsg() {
        LogUtil.a(TAG, "sendNoNetMsg");
        if (this.mSendNoNetMsgCallback != null) {
            LogUtil.a(TAG, "sendNoNetMsg mSendNoNetMsgCallback != null");
            this.mSendNoNetMsgCallback.onSendNoNetMsgCallback();
        }
    }

    @JavascriptInterface
    public boolean isNetworkConnected() {
        LogUtil.a(TAG, "isNetworkConnected");
        return CommonUtil.aa(this.mContext);
    }

    @JavascriptInterface
    public void setBIEvent(String str, String str2, String str3) {
        boolean z = (checkUrlIsLegal() || OperationUtils.getInstance().isMiaoUrl(this.mSendCurrentUrlCallback)) ? false : true;
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter) && z) {
            LogUtil.a(TAG, " setBIEvent checkIsHuaweiWhiteUrl is false");
            return;
        }
        LogUtil.c(TAG, "key = ", str, " mapJson = ", str2, " level = ", str3);
        Map<String, Object> jsonToMap = OperationUtils.getInstance().jsonToMap(str2);
        jsonToMap.put("click", "1");
        String obj = jsonToMap.get(WebViewHelp.BI_KEY_PULL_FROM) != null ? jsonToMap.get(WebViewHelp.BI_KEY_PULL_FROM).toString() : "";
        if ((TextUtils.isEmpty(obj) || "false".equalsIgnoreCase(obj)) && !TextUtils.isEmpty(WebViewHelp.getBiPullFrom())) {
            jsonToMap.put(WebViewHelp.BI_KEY_PULL_FROM, WebViewHelp.getBiPullFrom());
        }
        ixx.d().d(this.mContext, str, jsonToMap, 0);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        for (Map.Entry<String, Object> entry : jsonToMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                linkedHashMap.put(key, value.toString());
            }
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(str, linkedHashMap);
    }

    @JavascriptInterface
    public void setBIEventNew(String str, String str2, String str3, String str4) {
        String str5;
        LogUtil.c(TAG, "setBIEventNew param: ", str, ", key: ", str2, " mapJson : ", str3, " type : ", str4);
        if (health.compact.a.Utils.o()) {
            LogUtil.a(TAG, "setBIEventNew isOversea return");
            return;
        }
        try {
            str5 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "setBIEventNew JSONException param fail exception = ", e.getMessage());
            str5 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str5, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
        boolean checkUrlIsLegalNew2 = checkUrlIsLegalNew(str5, this.mHuaweiHost + UriConstants.BASE_ACCOUNT_SCOPE);
        LogUtil.c(TAG, "setBIEventNew isLegal ", Boolean.valueOf(checkUrlIsLegalNew), " isLegalBase ", Boolean.valueOf(checkUrlIsLegalNew2));
        if (checkUrlIsLegalNew || checkUrlIsLegalNew2) {
            if (TextUtils.isEmpty(str4) || com.huawei.operation.utils.Constants.BI_EVENT.equals(str4)) {
                Map<String, Object> jsonToMap = OperationUtils.getInstance().jsonToMap(str3);
                jsonToMap.put("click", "1");
                String obj = jsonToMap.get(WebViewHelp.BI_KEY_PULL_FROM) != null ? jsonToMap.get(WebViewHelp.BI_KEY_PULL_FROM).toString() : "";
                if ((TextUtils.isEmpty(obj) || "false".equalsIgnoreCase(obj)) && !TextUtils.isEmpty(WebViewHelp.getBiPullFrom())) {
                    jsonToMap.put(WebViewHelp.BI_KEY_PULL_FROM, WebViewHelp.getBiPullFrom());
                }
                ixx.d().d(this.mContext, str2, jsonToMap, 0);
                return;
            }
            if (com.huawei.operation.utils.Constants.ACHIEVE_EVENT.equals(str4)) {
                this.mAdapter.setAchieveEvent(str2, OperationUtils.getInstance().jsonToMap(str3));
            } else {
                LogUtil.c(TAG, "type is unknown.");
            }
        }
    }

    private boolean checkUrlIsLegalNew(String str, String str2) {
        return OperationUtils.getInstance().checkUrlIsLegalNewForWeb(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str, str2);
    }

    private boolean checkUrlIsLegal() {
        return OperationUtils.getInstance().checkUrlIsLegalForWeb(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, this.mUrlLoginHost);
    }

    @JavascriptInterface
    public void stressControl(String str, String str2) {
        LogUtil.c(TAG, "stressControl param:", str, ", functionRes:", str2);
        getStressFunction(str, this.mHuaweiHost + UriConstants.READ_PRESSURE, JsUtil.StressFunc.STRESS_CONTROL, str2);
    }

    @JavascriptInterface
    public void calibrationControl(String str, String str2) {
        LogUtil.c(TAG, "calibrationControl param:", str, ", functionRes:", str2);
        getStressFunction(str, this.mHuaweiHost + UriConstants.READ_PRESSURE, JsUtil.StressFunc.CALIBRATION_CONTROL, str2);
    }

    @JavascriptInterface
    public void checkCalibration(String str, String str2) {
        LogUtil.c(TAG, "checkCalibration param:", str, ", functionRes:", str2);
        getStressFunction(str, this.mHuaweiHost + UriConstants.READ_PRESSURE, JsUtil.StressFunc.CHECK_CALIBRATION, str2);
    }

    @JavascriptInterface
    public void resetCalibration(String str, String str2) {
        LogUtil.c(TAG, "resetCalibration param:", str, ", functionRes:", str2);
        getStressFunction(str, this.mHuaweiHost + UriConstants.READ_PRESSURE, JsUtil.StressFunc.RESET_CALIBRATION, str2);
    }

    @JavascriptInterface
    public void relaxControl(String str, String str2) {
        LogUtil.c(TAG, "relaxControl param:", str, ", functionRes:", str2);
        getStressFunction(str, this.mHuaweiHost + UriConstants.READ_RELAXATION_TRAINING, JsUtil.StressFunc.RELAX_CONTROL, str2);
    }

    @JavascriptInterface
    public void gameControl(String str, String str2) {
        LogUtil.c(TAG, "gameControl param:", str, ", functionRes:", str2);
        getStressFunction(str, this.mHuaweiHost + UriConstants.READ_PRESSURE, JsUtil.StressFunc.GAME_CONTROL, str);
    }

    @JavascriptInterface
    public void checkConnected(String str, String str2) {
        LogUtil.c(TAG, "checkConnected param:", str, ", functionRes:", str2);
        JsDataCallback jsDataCallback = this.mJsDataCallback;
        if (jsDataCallback != null) {
            jsDataCallback.callJsServiceFunction(JsUtil.ServiceType.STRESS, JsUtil.StressFunc.CHECK_CONNECTED, str, str2, true);
        }
    }

    @JavascriptInterface
    public String registerWebViewStatus(String str) {
        LogUtil.a(TAG, "registerWebViewStatus functionRes : ", str);
        OnWebViewStatusCallback onWebViewStatusCallback = this.mOnWebViewStatusCallback;
        if (onWebViewStatusCallback != null) {
            onWebViewStatusCallback.onWebViewStatusCallback(str);
            return String.valueOf(0);
        }
        return String.valueOf(com.huawei.operation.utils.Constants.CODE_UNKNOWN_ERROR);
    }

    @JavascriptInterface
    public String registerActivityQuit(String str, String str2) {
        String str3;
        LogUtil.a(TAG, "registerActivityQuit functionRes : ", str2);
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "getUserInfo JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.READ_WRITE_ACTIVITY_DATA);
        LogUtil.a(TAG, "registerActivityQuit isLegal = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (!checkUrlIsLegalNew) {
            return String.valueOf(1003);
        }
        OnActivityQuitCallback onActivityQuitCallback = this.mOnActivityQuitCallback;
        if (onActivityQuitCallback != null) {
            onActivityQuitCallback.onActivityQuitCallback(str2);
            return String.valueOf(0);
        }
        return String.valueOf(com.huawei.operation.utils.Constants.CODE_UNKNOWN_ERROR);
    }

    @JavascriptInterface
    public void showShareBtnOnTitlebar(String str, String str2) {
        LogUtil.a(TAG, "showShareBtnOnTitlebar  activityId:", str, ", shareType:", str2);
        OnActivityShowButtonCallback onActivityShowButtonCallback = this.mOnActivityShowButtonCallback;
        if (onActivityShowButtonCallback != null) {
            onActivityShowButtonCallback.onShowShareButtonWithId(str, str2);
        } else {
            LogUtil.h(TAG, "showShareBtnOnTitlebar  mOnActivityShareButtonCallback is null");
        }
    }

    @JavascriptInterface
    public void showErrorCodeMsg(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "showErrorCodeMsg errorCode is empty");
        } else {
            LogUtil.a(TAG, "showErrorCodeMsg errorCode:", str);
            JsInteractAddition.getInstance().reportErrorCode(OperationKey.HEALTH_APP_H5_ERROR_CODE_80060003.value(), str);
        }
    }

    @JavascriptInterface
    public void showShareBtnOnTitlebarWithFunc(String str) {
        LogUtil.a(TAG, "registerShareBtn functionRes : ", str);
        OnActivityShowButtonCallback onActivityShowButtonCallback = this.mOnActivityShowButtonCallback;
        if (onActivityShowButtonCallback != null) {
            onActivityShowButtonCallback.onShowShareButtonWithFunc(str);
        } else {
            LogUtil.h(TAG, "showShareBtnOnTitlebarWithFunc  mOnActivityShareButtonCallback is null");
        }
    }

    @JavascriptInterface
    public void showTitlebarRightBtn(int i) {
        LogUtil.a(TAG, "showTitlebarRightBtn type == ", Integer.valueOf(i));
        OnActivityShowButtonCallback onActivityShowButtonCallback = this.mOnActivityShowButtonCallback;
        if (onActivityShowButtonCallback != null) {
            onActivityShowButtonCallback.onShowRightButtonWithType(i);
        } else {
            LogUtil.h(TAG, "showTitlebarRightBtn  mOnActivityShareButtonCallback is null");
        }
    }

    @JavascriptInterface
    public String registerVmallCoupon(String str, String str2) {
        String str3;
        LogUtil.a(TAG, "registerVmallCoupon functionRes : ", str2);
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "getUserInfo JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        LogUtil.c(TAG, "activityAccessToken");
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.READ_WRITE_ACTIVITY_DATA);
        LogUtil.a(TAG, "registerVmallCoupon isLegal = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (!checkUrlIsLegalNew) {
            return String.valueOf(1003);
        }
        OnVmallCouponCallback onVmallCouponCallback = this.mOnVmallCouponCallback;
        if (onVmallCouponCallback != null) {
            onVmallCouponCallback.onVmallCouponCallback(str2);
            return String.valueOf(0);
        }
        return String.valueOf(com.huawei.operation.utils.Constants.CODE_UNKNOWN_ERROR);
    }

    @JavascriptInterface
    public void capture(final String str, final String str2) {
        String str3;
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "capture JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        LogUtil.c(TAG, "capture userInfoAccessToken");
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
        LogUtil.a(TAG, "capture checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (checkUrlIsLegalNew) {
            if (PermissionUtil.c()) {
                LogUtil.a(TAG, "capture: Q");
                dealCapture(str, str2);
            } else {
                PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction((Context) new WeakReference(this.mContext).get()) { // from class: com.huawei.operation.js.JsInteraction.2
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        JsInteraction.this.dealCapture(str, str2);
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onDenied(String str4) {
                        LogUtil.h(JsInteraction.TAG, "permission denied");
                        super.onDenied(str4);
                        JsInteraction.this.dealNoGranted(str2);
                    }

                    @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                        LogUtil.h(JsInteraction.TAG, "permission forever denied, show the guide window");
                        JsInteraction.this.dealNoGranted(str2);
                        super.onForeverDenied(permissionType);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealCapture(String str, final String str2) {
        LogUtil.a(TAG, "capture");
        if (this.mOnCaptureCallback != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.js.JsInteraction.3
                @Override // java.lang.Runnable
                public void run() {
                    JsInteraction.this.mOnCaptureCallback.onCapture(str2);
                }
            });
        } else {
            LogUtil.a(TAG, "mOnCaptureCallback = null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealNoGranted(final String str) {
        LogUtil.a(TAG, "dealNoGranted");
        if (this.mOnCaptureCallback != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.js.JsInteraction.4
                @Override // java.lang.Runnable
                public void run() {
                    JsInteraction.this.mOnCaptureCallback.onNoGranted(str);
                }
            });
        } else {
            LogUtil.a(TAG, "mOnCaptureCallback is null");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void shareByPath(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "activityShareContent"
            java.lang.String r1 = "name"
            java.lang.String r2 = "shareByPath"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "PluginOperation_[Operation Version 1.2]JsInteraction"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            java.lang.String r2 = ""
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L35
            r4.<init>(r9)     // Catch: org.json.JSONException -> L35
            java.lang.String r9 = "accessToken"
            java.lang.String r9 = r4.optString(r9)     // Catch: org.json.JSONException -> L35
            java.lang.String r5 = "moduleId"
            java.lang.String r5 = r4.optString(r5)     // Catch: org.json.JSONException -> L33
            java.lang.String r6 = r4.optString(r1)     // Catch: org.json.JSONException -> L30
            java.lang.String r2 = r4.optString(r0)     // Catch: org.json.JSONException -> L2e
            goto L48
        L2e:
            r4 = move-exception
            goto L3a
        L30:
            r4 = move-exception
            r6 = r2
            goto L3a
        L33:
            r4 = move-exception
            goto L38
        L35:
            r9 = move-exception
            r4 = r9
            r9 = r2
        L38:
            r5 = r2
            r6 = r5
        L3a:
            java.lang.String r7 = "shareByPath JSONException param fail exception = "
            java.lang.String r4 = r4.getMessage()
            java.lang.Object[] r4 = new java.lang.Object[]{r7, r4}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r4)
        L48:
            java.lang.String r4 = "shareByPath userInfoAccessToken"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.c(r3, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = r8.mHuaweiHost
            r4.append(r7)
            java.lang.String r7 = "/health/profile.readonly"
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            boolean r9 = r8.checkUrlIsLegalNew(r9, r4)
            java.lang.String r4 = "shareByPath checkUrlIsLegalNew return = "
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r9)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r4)
            if (r9 == 0) goto La8
            com.huawei.operation.adapter.OnCaptureCallback r9 = r8.mOnCaptureCallback
            boolean r9 = r9 instanceof com.huawei.operation.adapter.OnCaptureExtCallback
            if (r9 == 0) goto L9e
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
            r9.<init>()
            boolean r3 = android.text.TextUtils.isEmpty(r6)
            if (r3 != 0) goto L8d
            r9.put(r1, r6)
        L8d:
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L96
            r9.put(r0, r2)
        L96:
            com.huawei.operation.adapter.OnCaptureCallback r0 = r8.mOnCaptureCallback
            com.huawei.operation.adapter.OnCaptureExtCallback r0 = (com.huawei.operation.adapter.OnCaptureExtCallback) r0
            r0.onShare(r10, r5, r9)
            goto La8
        L9e:
            java.lang.String r9 = "mOnCaptureCallback is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r9)
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.js.JsInteraction.shareByPath(java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    public void shareMultipleImg(String str, String str2, int i) {
        String str3;
        LogUtil.a(TAG, "shareMultipleImg");
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "shareMultipleImg JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        LogUtil.c(TAG, "shareMultipleImg userInfoAccessToken");
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
        LogUtil.a(TAG, "shareMultipleImg checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (checkUrlIsLegalNew) {
            OnCaptureCallback onCaptureCallback = this.mOnCaptureCallback;
            if (onCaptureCallback != null) {
                onCaptureCallback.onShareMultiple(str2, i);
            } else {
                LogUtil.a(TAG, "mOnCaptureCallback is null");
            }
        }
    }

    @JavascriptInterface
    public void shareImageByBase64(String str, final String str2) {
        LogUtil.a(TAG, "shareImageByBase64");
        if (TextUtils.isEmpty(str2)) {
            LogUtil.c(TAG, "shareImageByBase64 buffer is null");
        } else if (!checkCallerIsLegal(str, UriConstants.READ_PERSONAL_PROFILE)) {
            LogUtil.c(TAG, "shareImageByBase64 CheckCallerIsLegal return false");
        } else {
            PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction((Context) new WeakReference(this.mContext).get()) { // from class: com.huawei.operation.js.JsInteraction.5
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    JsInteraction.this.delShareImageByBase64(str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delShareImageByBase64(String str) {
        FileOutputStream fileOutputStream;
        Bitmap.CompressFormat imageFileFormat = getImageFileFormat(str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File file = new File(jcu.f);
                if (!file.exists() && !file.mkdirs()) {
                    LogUtil.c(TAG, "delShareImageByBase64 dir fail");
                    jei.c((OutputStream) null);
                    LogUtil.a(TAG, "delShareImageByBase64 close out");
                    return;
                }
                File file2 = new File(file, "webView_capture." + getFileSuffix(imageFileFormat));
                String canonicalPath = file2.getCanonicalPath();
                if (!CommonUtil.a(file2, canonicalPath)) {
                    LogUtil.c(TAG, "delShareImageByBase64 validateFileName fail");
                    jei.c((OutputStream) null);
                    LogUtil.a(TAG, "delShareImageByBase64 close out");
                    return;
                }
                byte[] decode = Base64.decode(cutBufferHead(str, imageFileFormat), 0);
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                fileOutputStream = new FileOutputStream(canonicalPath);
                try {
                    decodeByteArray.compress(imageFileFormat, 100, fileOutputStream);
                    fileOutputStream.flush();
                    OnCaptureCallback onCaptureCallback = this.mOnCaptureCallback;
                    if (onCaptureCallback != null) {
                        onCaptureCallback.onShare(canonicalPath);
                    } else {
                        LogUtil.a(TAG, "mOnCaptureCallback is null");
                    }
                    jei.c(fileOutputStream);
                    LogUtil.a(TAG, "delShareImageByBase64 close out");
                } catch (IOException | IllegalArgumentException | OutOfMemoryError unused) {
                    fileOutputStream2 = fileOutputStream;
                    LogUtil.b(TAG, "delShareImageByBase64 fail exception");
                    jei.c(fileOutputStream2);
                    LogUtil.a(TAG, "delShareImageByBase64 close out");
                } catch (Throwable th) {
                    th = th;
                    jei.c(fileOutputStream);
                    LogUtil.a(TAG, "delShareImageByBase64 close out");
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (IOException | IllegalArgumentException | OutOfMemoryError unused2) {
        }
    }

    private Bitmap.CompressFormat getImageFileFormat(String str) {
        if (str.startsWith(BASE64_JPEG_HEAD)) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (str.startsWith(BASE64_PNG_HEAD)) {
            return Bitmap.CompressFormat.PNG;
        }
        if (str.startsWith(BASE64_WEBP_HEAD)) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    /* renamed from: com.huawei.operation.js.JsInteraction$13, reason: invalid class name */
    static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$CompressFormat;

        static {
            int[] iArr = new int[Bitmap.CompressFormat.values().length];
            $SwitchMap$android$graphics$Bitmap$CompressFormat = iArr;
            try {
                iArr[Bitmap.CompressFormat.PNG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.WEBP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.JPEG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private String getFileSuffix(Bitmap.CompressFormat compressFormat) {
        int i = AnonymousClass13.$SwitchMap$android$graphics$Bitmap$CompressFormat[compressFormat.ordinal()];
        return i != 1 ? i != 2 ? "jpg" : "webp" : "png";
    }

    private String cutBufferHead(String str, Bitmap.CompressFormat compressFormat) {
        int i = AnonymousClass13.$SwitchMap$android$graphics$Bitmap$CompressFormat[compressFormat.ordinal()];
        if (i == 1) {
            return str.replace(BASE64_PNG_HEAD, "");
        }
        if (i != 2) {
            return i != 3 ? str : str.replace(BASE64_JPEG_HEAD, "");
        }
        return str.replace(BASE64_WEBP_HEAD, "");
    }

    private boolean checkCallerIsLegal(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "checkCallerIsLegal JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        return checkUrlIsLegalNew(str3, this.mHuaweiHost + str2);
    }

    @JavascriptInterface
    public void setCustomUserPrefFromHiHealth(String str, String str2, String str3, String str4) {
        String str5;
        LogUtil.c(TAG, "setCustomUserPrefFromHiHealth key = ", str2, " value = ", str3);
        try {
            str5 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "setCustomUserPrefFromHiHealth JSONException param fail exception = ", e.getMessage());
            str5 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str5, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
        LogUtil.a(TAG, "setCustomUserPrefFromHiHealth checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        boolean z = checkUrlIsLegalNew && getCustomUserPrefKeys().contains(str2);
        boolean z2 = !TextUtils.isEmpty(str3) && str3.length() < 120;
        if (z && z2) {
            OnCaptureCallback onCaptureCallback = this.mOnCaptureCallback;
            if (onCaptureCallback != null) {
                onCaptureCallback.onCustomUserPrefSet(str4, str2, str3);
            } else {
                LogUtil.a(TAG, "mOnCaptureCallback is null");
            }
        }
    }

    @JavascriptInterface
    public String getCustomUserPrefFromHiHealth(String str, String str2) {
        String str3;
        HiUserPreference userPreference;
        LogUtil.c(TAG, "getCustomUserPrefFromHiHealth");
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "getCustomUserPrefFromHiHealth JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
        LogUtil.a(TAG, "getCustomUserPrefFromHiHealth checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (checkUrlIsLegalNew && getCustomUserPrefKeys().contains(str2) && (userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str2)) != null) {
            return userPreference.getValue();
        }
        return null;
    }

    @JavascriptInterface
    public String getSharedPreference(String str, String str2) {
        String str3;
        LogUtil.a(TAG, "getSharedPreference");
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "getSharedPreference JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        if (checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE)) {
            return SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), str2);
        }
        return null;
    }

    @JavascriptInterface
    public void setSharedPreference(String str, String str2, String str3) {
        String str4;
        LogUtil.a(TAG, "setSharedPreference");
        try {
            str4 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "setSharedPreference JSONException param fail exception = ", e.getMessage());
            str4 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str4, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
        LogUtil.a(TAG, "setSharedPreference checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (checkUrlIsLegalNew) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10006), str2, str3, new StorageParams());
        }
    }

    @JavascriptInterface
    public int getUserGender(String str) {
        String str2;
        UserInfomation userInfo;
        LogUtil.a(TAG, "getUserGender");
        try {
            str2 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "checkIsLegal JSONException param fail exception = ", e.getMessage());
            str2 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str2, this.mHuaweiHost + UriConstants.READ_PERSONAL_PROFILE);
        LogUtil.a(TAG, "getUserGender checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        int gender = (checkUrlIsLegalNew && (userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo()) != null && userInfo.isGenderValid()) ? userInfo.getGender() : -1;
        LogUtil.a(TAG, "getUserGender gender : ", Integer.valueOf(gender));
        return gender;
    }

    private List<String> getCustomUserPrefKeys() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(com.huawei.operation.utils.Constants.ANNUAL_CUSTOM_WISH_2018);
        return arrayList;
    }

    @JavascriptInterface
    public String getPersonalPrivacySettingValue(String str, int i) {
        LogUtil.a(TAG, "getPersonalPrivacySettingValue");
        String personalPrivacySettingValue = OperationUtils.getInstance().checkPersonalPrivacySettingsUrlIsLegal(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str) ? this.mAdapter.getPersonalPrivacySettingValue(i) : null;
        LogUtil.c(TAG, "getPersonalPrivacySettingValue result is : ", personalPrivacySettingValue);
        return personalPrivacySettingValue;
    }

    @JavascriptInterface
    public void go2PersonalPrivacySettingsActivity(String str) {
        LogUtil.a(TAG, "go2PersonalPrivacySettingsActivity");
        if (OperationUtils.getInstance().checkPersonalPrivacySettingsUrlIsLegal(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter, str)) {
            this.mAdapter.go2PersonalPrivacySettingsActivity();
        }
    }

    private boolean isValid() {
        if (this.mAdapter == null) {
            LogUtil.c(TAG, "mAdapter is null");
            return false;
        }
        if (JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            return true;
        }
        LogUtil.c(TAG, "whiteUrl check fail");
        return false;
    }

    @JavascriptInterface
    public void uploadSleepQuestionnaireResults(long j, String str, final String str2) {
        LogUtil.c(TAG, "enter uploadSleepQuestionnaireResults");
        if (isValid()) {
            this.mAdapter.uploadSleepQuestionnaireResults(j, str, new IBaseResponseCallback() { // from class: com.huawei.operation.js.JsInteraction.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (JsInteraction.this.mSleepQuestionnaireCallback != null) {
                        JsInteraction.this.mSleepQuestionnaireCallback.callSleepQuestionnaireJsFunction(i, (String) obj, str2);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void querySleepQuestionnaireResults(long j, final String str) {
        LogUtil.c(TAG, "enter querySleepQuestionnaireResults");
        if (isValid()) {
            this.mAdapter.querySleepQuestionnaireResults(j, new IBaseResponseCallback() { // from class: com.huawei.operation.js.JsInteraction.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (JsInteraction.this.mSleepQuestionnaireCallback != null) {
                        JsInteraction.this.mSleepQuestionnaireCallback.callSleepQuestionnaireJsFunction(i, (String) obj, str);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void getFitnessSumData(long j, long j2, String str) {
        LogUtil.a(TAG, "getFitnessSumData startTime = ", Long.valueOf(j), ",endTime = ", Long.valueOf(j2));
        this.mAdapter.getFitnessSumData(j, j2, str, this.mSportsStatisticsCallback);
    }

    @JavascriptInterface
    public void getWorkoutRecordByTimeAndId(long j, long j2, String str, String str2, String str3) {
        LogUtil.a(TAG, "getWorkoutRecordByTimeAndId startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2), "workOutId = ", str, "version = ", str2);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.mAdapter.getWorkoutRecordByTimeAndId(j, j2, str + ":" + str2, str3, this.mSportsStatisticsCallback);
            return;
        }
        LogUtil.h(TAG, "workOutId, version can not empty");
    }

    @JavascriptInterface
    public void vmallAgrSign(final String str) {
        LogUtil.c(TAG, "enter vmallAgrSign");
        if (isValid()) {
            this.mAdapter.vmallAgrSign(new IBaseResponseCallback() { // from class: com.huawei.operation.js.JsInteraction.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (JsInteraction.this.mVmallArgSignCallback != null) {
                        JsInteraction.this.mVmallArgSignCallback.callVmallArgSignJsFunction(i, (String) obj, str);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void vmallAgrQuery(final String str) {
        LogUtil.c(TAG, "enter querySleepQuestionnaireResults");
        if (isValid()) {
            this.mAdapter.vmallAgrQuery(new IBaseResponseCallback() { // from class: com.huawei.operation.js.JsInteraction.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (JsInteraction.this.mVmallArgSignCallback != null) {
                        JsInteraction.this.mVmallArgSignCallback.callVmallArgSignJsFunction(i, (String) obj, str);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void deleteSleepQuestionnaireResult(long j, final String str) {
        LogUtil.c(TAG, "enter deleteSleepQuestionnaireResult");
        if (isValid()) {
            this.mAdapter.deleteSleepQuestionnaireResult(j, new IBaseResponseCallback() { // from class: com.huawei.operation.js.JsInteraction.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (JsInteraction.this.mSleepQuestionnaireCallback == null || !(obj instanceof String)) {
                        return;
                    }
                    JsInteraction.this.mSleepQuestionnaireCallback.callSleepQuestionnaireJsFunction(i, (String) obj, str);
                }
            });
        }
    }

    @JavascriptInterface
    public String getSleepService() {
        return JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter) ? jes.d().a() : "";
    }

    @JavascriptInterface
    public boolean copyContent2Clipboard(String str) {
        Object systemService = BaseApplication.getContext().getSystemService("clipboard");
        if (TextUtils.isEmpty(str) || !(systemService instanceof ClipboardManager)) {
            return false;
        }
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("label", str));
        return true;
    }

    @JavascriptInterface
    public void breatheControl(String str) {
        String str2;
        JsDataCallback jsDataCallback;
        if (this.mContext == null) {
            LogUtil.c(TAG, "breatheControl context is null");
            return;
        }
        LogUtil.c(TAG, "breatheControl ", str);
        try {
            str2 = new JSONObject(str).optString(JsUtil.FUNCTION_RES);
        } catch (JSONException e) {
            LogUtil.b(TAG, "breatheControl JSONException param fail exception = ", e.getMessage());
            str2 = "";
        }
        String str3 = str2;
        boolean checkIsHuaweiWhiteUrl = JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
        LogUtil.a(TAG, "stressControl isLegal = ", Boolean.valueOf(checkIsHuaweiWhiteUrl));
        if (!checkIsHuaweiWhiteUrl || (jsDataCallback = this.mJsDataCallback) == null) {
            return;
        }
        jsDataCallback.callJsServiceFunction(JsUtil.ServiceType.STRESS, JsUtil.StressFunc.BREATHE_CONTROL, str, str3, true);
    }

    @JavascriptInterface
    public boolean checkAppIsInstall(String str, String str2) {
        String str3;
        LogUtil.a(TAG, "checkAppIsInstall");
        if (this.mContext == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a(TAG, "checkAppIsInstall mContext is null ");
            return false;
        }
        try {
            str3 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "capture JSONException param fail exception = ", e.getMessage());
            str3 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str3, this.mHuaweiHost + UriConstants.BASE_ACCOUNT_SCOPE);
        LogUtil.a(TAG, "capture checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        return checkUrlIsLegalNew && CommonUtil.e(this.mContext, str2);
    }

    @JavascriptInterface
    public void startPkgApp(String str, String str2, String str3, String str4) {
        String str5;
        LogUtil.a(TAG, "startPkgApp");
        if (this.mContext == null) {
            LogUtil.a(TAG, "startPkgApp mContext is null");
            return;
        }
        try {
            str5 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "startPkgApp JSONException param fail exception = ", e.getMessage());
            str5 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str5, this.mHuaweiHost + UriConstants.BASE_ACCOUNT_SCOPE);
        LogUtil.a(TAG, "startPkgApp checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (checkUrlIsLegalNew && WebViewUtils.isWhiteThirdPkg(str2)) {
            WebViewUtils.goToPkgAppDialog((Context) new WeakReference(this.mContext).get(), str2, str3, str4);
        }
    }

    @JavascriptInterface
    public void jumpBrowserDownload(String str, String str2, String str3) {
        String str4;
        LogUtil.a(TAG, "jumpBrowserDownload");
        if (this.mContext == null) {
            LogUtil.a(TAG, "jumpBrowserDownload mContext is null");
            return;
        }
        try {
            str4 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "jumpBrowserDownload JSONException param fail exception = ", e.getMessage());
            str4 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str4, this.mHuaweiHost + UriConstants.BASE_ACCOUNT_SCOPE);
        LogUtil.a(TAG, "jumpBrowserDownload checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (checkUrlIsLegalNew && WebViewUtils.isWhiteThirdPkg(str2)) {
            try {
                Uri parse = Uri.parse(str3);
                Intent intent = new Intent();
                intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.setData(parse);
                this.mContext.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.a(TAG, "activity not found exception.");
            }
        }
    }

    @JavascriptInterface
    public boolean isBigCD() {
        return JsInteractAddition.getInstance().isTahitiForWeb(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public String getMcc() {
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            return "";
        }
        LogUtil.a(TAG, "getMcc return:", com.huawei.operation.utils.Constants.DEFAULT_MCC_CODE);
        return com.huawei.operation.utils.Constants.DEFAULT_MCC_CODE;
    }

    @JavascriptInterface
    public void ecgLoginAuthSign(String str) {
        LogUtil.a(TAG, "enter ecgLoginAuthSign:adapter:", this.mAdapter);
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.setSendOpenId(str);
        }
    }

    @JavascriptInterface
    public String getSafeRegionWidth() {
        return JsInteractAddition.getInstance().getSafeRegionWidth(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public String getUserLabels() {
        PluginOperationAdapter pluginOperationAdapter;
        LogUtil.a(TAG, "getUserLabels");
        return (JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter) && (pluginOperationAdapter = this.mAdapter) != null) ? pluginOperationAdapter.getUserLabels() : "";
    }

    @JavascriptInterface
    public boolean isSupportStepCounter() {
        return JsInteractAddition.getInstance().isSupportStepCounter(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter);
    }

    @JavascriptInterface
    public boolean isSupportHarvard() {
        return JsInteractAddition.getInstance().isSupportHarvard();
    }

    @JavascriptInterface
    public String getCountryCode() {
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            return "";
        }
        String commonCountryCode = GRSManager.a(this.mContext).getCommonCountryCode();
        LogUtil.c(TAG, "getCountryCode return:", commonCountryCode);
        return commonCountryCode;
    }

    @JavascriptInterface
    public String getLocale() {
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            LogUtil.a(TAG, " getLocale checkIsHuaweiWhiteUrl is false");
            return "";
        }
        String e = LanguageUtil.e();
        LogUtil.a(TAG, "getLocale return:", e);
        return e;
    }

    @JavascriptInterface
    public String getLocaleCountryCode() {
        String c = CommonUtil.c();
        LogUtil.a(TAG, "getLocaleCountryCode return:", c);
        return c;
    }

    @JavascriptInterface
    public String getLocaleLanguage() {
        String x = CommonUtil.x();
        LogUtil.a(TAG, "getLocaleLanguage return:", x);
        return x;
    }

    @JavascriptInterface
    public boolean getIsOversea() {
        Boolean valueOf = Boolean.valueOf(health.compact.a.Utils.o());
        LogUtil.a(TAG, "getIsOversea: ", valueOf);
        return valueOf.booleanValue();
    }

    @JavascriptInterface
    public boolean getIsBetaVersion() {
        Boolean valueOf = Boolean.valueOf(CommonUtil.as());
        LogUtil.a(TAG, "getIsBetaVersion: ", valueOf);
        return valueOf.booleanValue();
    }

    @JavascriptInterface
    public String getTimeZone() {
        String d = HiDateUtil.d("");
        LogUtil.a(TAG, "getTimezone: ", d);
        return d;
    }

    @JavascriptInterface
    public int getSiteId() {
        if (!JsInteractAddition.getInstance().checkIsHuaweiWhiteUrl(this.mContext, this.mSendCurrentUrlCallback, this.mAdapter)) {
            LogUtil.a(TAG, " getSiteId checkIsHuaweiWhiteUrl is false");
            return 0;
        }
        int parseInt = Integer.parseInt(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        LogUtil.a(TAG, "getSiteId: ", Integer.valueOf(parseInt));
        return parseInt;
    }

    @JavascriptInterface
    public boolean getVmallWrapSwitch() {
        return OperationUtils.isVmallWrapSwitch();
    }

    @JavascriptInterface
    public boolean isShowCustomerService() {
        LogUtil.a(TAG, "isShowCustomerService()");
        return njn.e(this.mContext);
    }

    @JavascriptInterface
    public boolean isShowJapanCustomer() {
        LogUtil.a(TAG, "isShowJapanCustomer()");
        return Utils.isShowJapanCustomer(this.mContext);
    }

    private boolean getPrivacySwitch(int i) {
        return "true".equals(gmz.d().c(i));
    }

    @JavascriptInterface
    public boolean isAllowUploadSportData() {
        boolean privacySwitch = getPrivacySwitch(3);
        LogUtil.a(TAG, "isAllowUploadSportData:", Boolean.valueOf(privacySwitch));
        return privacySwitch;
    }

    @JavascriptInterface
    public boolean isAllowUploadFitnessData() {
        boolean privacySwitch = getPrivacySwitch(7);
        LogUtil.a(TAG, "isAllowUploadFitnessData:", Boolean.valueOf(privacySwitch));
        return privacySwitch;
    }

    @JavascriptInterface
    public boolean isHealthLifeModelBloomToday() {
        int a2 = dsl.a(1, HiDateUtil.c(System.currentTimeMillis()));
        LogUtil.a(TAG, "isHealthLifeModelBloomToday cloverComplete ", Integer.valueOf(a2));
        return a2 > 0;
    }

    @JavascriptInterface
    public void jumpAchieveMedalById(String str) {
        if (this.mContext == null || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "jumpAchieveMedalById medalId is empty");
            return;
        }
        LogUtil.a(TAG, "jumpAchieveMedalById()");
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.jumpAchieveMedalById(this.mContext, str);
        } else {
            LogUtil.h(TAG, "jumpAchieveMedalById mAdapter is null");
        }
    }

    @JavascriptInterface
    public void jumpAchieveKakaPage() {
        if (this.mContext == null) {
            LogUtil.h(TAG, "jumpAchieveKakaPage mContext is null");
            return;
        }
        LogUtil.a(TAG, "jumpAchieveKakaPage()");
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.jumpAchieveKakaPage(this.mContext);
        } else {
            LogUtil.h(TAG, "jumpAchieveKakaPage mAdapter is null");
        }
    }

    @JavascriptInterface
    public void jumpMyAwardPage() {
        if (this.mContext == null) {
            LogUtil.h(TAG, "jumpMyAwardPage mContext is null");
            return;
        }
        LogUtil.a(TAG, "jumpMyAwardPage()");
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.jumpMyAwardPage((Context) new WeakReference(this.mContext).get());
        } else {
            LogUtil.h(TAG, "jumpMyAwardPage mAdapter is null");
        }
    }

    @JavascriptInterface
    public boolean isBindDevice(String str) {
        String str2;
        LogUtil.a(TAG, "isBindDevice");
        try {
            str2 = new JSONObject(str).getString("accessToken");
        } catch (JSONException e) {
            LogUtil.b(TAG, "isBindDevice JSONException accessToken fail exception = ", e.getMessage());
            str2 = "";
        }
        boolean checkUrlIsLegalNew = checkUrlIsLegalNew(str2, this.mHuaweiHost + UriConstants.READ_DEVICE_DATA);
        LogUtil.a(TAG, "isBindDevice checkUrlIsLegalNew return = ", Boolean.valueOf(checkUrlIsLegalNew));
        if (checkUrlIsLegalNew) {
            return this.mAdapter.isBindDevice();
        }
        return false;
    }

    @JavascriptInterface
    public void getHealthDataStatistics(long j, long j2, int i, final String str) {
        this.mAdapter.getHealthDataStatistics(j, j2, i, new IBaseResponseCallback() { // from class: com.huawei.operation.js.JsInteraction.11
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (JsInteraction.this.mOnHealthDataCallback != null) {
                    JsInteraction.this.mOnHealthDataCallback.onHealthDataStatisticsCallback(i2, obj, str);
                } else {
                    LogUtil.c(JsInteraction.TAG, "HealthDataCallback is null");
                }
            }
        });
    }

    @JavascriptInterface
    public boolean isLogined() {
        return LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
    }

    @JavascriptInterface
    public void browsingToLogin() {
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.operation.js.JsInteraction.12
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a(JsInteraction.TAG, "browsingToLogin errorCode: ", Integer.valueOf(i));
            }
        }, "");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0044 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getDeviceInfo() {
        /*
            r4 = this;
            java.lang.String r0 = "getDeviceInfo start"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "PluginOperation_[Operation Version 1.2]JsInteraction"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            android.content.Context r0 = r4.mContext
            r2 = 0
            if (r0 == 0) goto L39
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.uiMode
            r0 = r0 & 48
            r3 = 32
            if (r0 != r3) goto L22
            r0 = 1
            goto L23
        L22:
            r0 = 0
        L23:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L30
            r3.<init>()     // Catch: org.json.JSONException -> L30
            java.lang.String r2 = "isDarkTheme"
            r3.put(r2, r0)     // Catch: org.json.JSONException -> L2f
            r2 = r3
            goto L39
        L2f:
            r2 = r3
        L30:
            java.lang.String r0 = "getDeviceInfo json exception"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        L39:
            java.lang.String r0 = "getDeviceInfo end"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            if (r2 != 0) goto L48
            java.lang.String r0 = "{}"
            goto L4c
        L48:
            java.lang.String r0 = r2.toString()
        L4c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.js.JsInteraction.getDeviceInfo():java.lang.String");
    }

    @JavascriptInterface
    public String getUserInfo(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("client_id");
            String string2 = jSONObject.getString("client_secret");
            String string3 = jSONObject.getString("code");
            String string4 = jSONObject.getString("at_host");
            String string5 = jSONObject.getString("info_host");
            String atFromUp = JsInteractAddition.getInstance().getAtFromUp(string4, string, string2, string3, jSONObject.has(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL) ? jSONObject.getString(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL) : "");
            if (TextUtils.isEmpty(atFromUp)) {
                LogUtil.b(TAG, "getUserInfo accessToken is null");
                return "";
            }
            JSONObject userInfoFromUp = JsInteractAddition.getInstance().getUserInfoFromUp(atFromUp, string5);
            if (userInfoFromUp == null) {
                LogUtil.b(TAG, "getUserInfo info is null");
                return "";
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", userInfoFromUp);
            return jSONObject2.toString();
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getUserInfo JSONException");
            return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @JavascriptInterface
    public boolean isShowFeedback(String str, String str2) {
        char c;
        LogUtil.a(TAG, "isShowFeedback() in isDevice = ", str);
        LogUtil.c(TAG, "isShowFeedback() in deviceId = ", str2);
        if (nsn.ae(this.mContext) && health.compact.a.Utils.o()) {
            return false;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 50:
                if (str.equals("2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (str.equals("3")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 52:
                if (str.equals("4")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                return LoginInit.getInstance(BaseApplication.getContext()).getIsLogined() && isFeedbackSwitchOpen() && !CommonUtil.bp();
            }
            if (c == 2) {
                return true;
            }
            if (c != 3) {
                return false;
            }
        }
        return (TextUtils.isEmpty(str2) || "undefined".equals(str2)) ? false : true;
    }

    private boolean isFeedbackSwitchOpen() {
        String e = jah.c().e(COMMON_CONFIG_FEEDBACKSDK_SWITCH);
        LogUtil.a(TAG, "isFeedbackSwitchClose , COMMON_CONFIG_FEEDBACKSDK_SWITCH = ", e);
        if (health.compact.a.Utils.o()) {
            return FEEDBACK_SWITCH_ON.equalsIgnoreCase(e);
        }
        return !FEEDBACK_SWITCH_OFF.equalsIgnoreCase(e);
    }

    @JavascriptInterface
    public void gotoFeedback(String str, String str2, String str3) {
        LogUtil.c(TAG, "isDevice = ", str, "; deviceId = ", str2, "; isEnhancementMode = ", str3);
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
            return;
        }
        AppRouter.zi_(Uri.parse("huaweischeme://healthapp/router/feedback?isDevice=" + str + "&device_id=" + str2.replace("%3A", ":") + "&enhancement_mode=" + str3)).c((Context) new WeakReference(this.mContext).get());
    }

    @JavascriptInterface
    public boolean isFromHuaweiHealth() {
        return MyHuaweiLogin.isSupportMyHuaweiLogin();
    }

    @JavascriptInterface
    public String getDeviceParam() {
        HwGetDevicesParameter hwGetDevicesParameter;
        LogUtil.a(TAG, "getDeviceParam enter!");
        DeviceInfo deviceInfo = null;
        if (health.compact.a.Utils.o()) {
            LogUtil.h(TAG, "getDeviceParam isOversea return");
            return null;
        }
        String e = CommonUtil.e(this.mContext);
        String marketName = getMarketName();
        if (TextUtils.isEmpty(marketName)) {
            marketName = Build.BRAND;
        }
        int oSBrand = getOSBrand();
        String str = Build.DISPLAY;
        if (!TextUtils.isEmpty(str) && CommonUtil.bd()) {
            String[] split = str.split(" ");
            if (split.length > 1) {
                str = split[1];
            }
        }
        HwGetDevicesMode hwGetDevicesMode = HwGetDevicesMode.CONNECTED_MAIN_DEVICES;
        if (TextUtils.isEmpty(mDeviceId)) {
            hwGetDevicesParameter = null;
        } else {
            hwGetDevicesMode = HwGetDevicesMode.IDENTIFY_DEVICES;
            hwGetDevicesParameter = new HwGetDevicesParameter();
            hwGetDevicesParameter.setDeviceIdentify(mDeviceId);
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(hwGetDevicesMode, hwGetDevicesParameter, TAG);
        if (deviceList == null || deviceList.size() <= 0) {
            deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, hwGetDevicesParameter, TAG);
        }
        if (deviceList != null && deviceList.size() > 0) {
            if (deviceList.size() > 1) {
                deviceList = (List) deviceList.stream().sorted(Comparator.comparingLong(new ToLongFunction() { // from class: com.huawei.operation.js.JsInteraction$$ExternalSyntheticLambda0
                    @Override // java.util.function.ToLongFunction
                    public final long applyAsLong(Object obj) {
                        return ((DeviceInfo) obj).getLastConnectedTime();
                    }
                }).reversed()).collect(Collectors.toList());
            }
            deviceInfo = deviceList.get(0);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appVersion", e);
            jSONObject.put(com.huawei.operation.utils.Constants.MARKET_NAME, marketName);
            jSONObject.put(com.huawei.operation.utils.Constants.OS_BRAND, oSBrand);
            jSONObject.put("osVersion", str);
            String str2 = "";
            jSONObject.put(com.huawei.operation.utils.Constants.BIND_DEVICE_NAME, deviceInfo == null ? "" : deviceInfo.getDeviceName());
            if (deviceInfo != null) {
                str2 = deviceInfo.getSoftVersion();
            }
            jSONObject.put(com.huawei.operation.utils.Constants.BIND_DEVICE_OS_VERSION, str2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getDeviceParam json exception");
        }
        String jSONObject2 = jSONObject.toString();
        LogUtil.a(TAG, "getDeviceParam json: ", jSONObject2);
        return jSONObject2;
    }

    private String getMarketName() {
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            for (String str : com.huawei.operation.utils.Constants.MARKET_NAME_KEYS) {
                Object invoke = method.invoke(null, str);
                if (invoke != null && (invoke instanceof String) && !TextUtils.isEmpty((String) invoke)) {
                    return (String) invoke;
                }
            }
        } catch (Exception e) {
            LogUtil.b(TAG, "getMarketName Exception: ", e.getMessage());
        }
        return "";
    }

    private int getOSBrand() {
        if (CommonUtil.az()) {
            return 2;
        }
        if (CommonUtil.ao()) {
            return 1;
        }
        return CommonUtil.bf() ? 0 : -1;
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.mContext = null;
    }
}
