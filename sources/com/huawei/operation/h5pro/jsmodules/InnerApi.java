package com.huawei.operation.h5pro.jsmodules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.json.JsonSanitizer;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.huawei.agconnect.apms.Agent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.security.SecurityCertUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProActivityResultCallback;
import com.huawei.health.h5pro.core.H5ProBridgeManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProReverseControlExecutor;
import com.huawei.health.h5pro.core.H5ProWebViewActivity;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.jsbridge.ErrorEnum;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.HiZipUtil;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudjs.JsClientApi;
import com.huawei.hwcloudjs.api.IAccessToken;
import com.huawei.hwcloudjs.api.TaskCallBack;
import com.huawei.hwcloudjs.service.hms.HmsCoreApi;
import com.huawei.hwcloudjs.service.hms.HmsLiteCoreApi;
import com.huawei.hwcloudjs.service.hms.bean.AccessTokenInfo;
import com.huawei.hwcloudjs.service.hms.bean.Status;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ILoginCallback;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.operation.OperationKey;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.DataTransferInterceptorImpl;
import com.huawei.operation.h5pro.TrustListCheckerImpl;
import com.huawei.operation.h5pro.jsmodules.InnerApi;
import com.huawei.operation.js.JsInteractAddition;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.HelpCustomerOperate;
import com.huawei.operation.utils.NetworkStatusUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.stories.nps.interactors.mode.QuestionSurveyRequestParam;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.up.utils.Constants;
import com.huawei.utils.FoundationCommonUtil;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.bzs;
import defpackage.cwb;
import defpackage.cwi;
import defpackage.eii;
import defpackage.eil;
import defpackage.glz;
import defpackage.gmc;
import defpackage.gnm;
import defpackage.gpo;
import defpackage.jdx;
import defpackage.jeg;
import defpackage.jfa;
import defpackage.koq;
import defpackage.lcu;
import defpackage.moj;
import defpackage.njn;
import defpackage.nrh;
import defpackage.nrv;
import defpackage.nsj;
import health.compact.a.Base64;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class InnerApi extends JsBaseModule {
    private static final String COMMON_PARAM_KEY = "key";
    private static final String COMMON_PARAM_PATH = "path";
    private static final String COMMON_PARAM_VALUE = "value";
    private static final int DEFAULT_SHARED_DATA_MAP_SIZE = 1;
    private static final int FAIL = -1;
    private static final String FUNC_AUTH_FAIL_DESC = "request functionAuth fail.";
    private static final long INIT_CALLBACK_ID = -1;
    private static final int REQUEST_CODE_LOAD_H5 = 10001;
    private static final int REQUEST_CODE_PAY = 10000;
    public static final int REQUEST_CODE_ROUTE_RESULT = 7667713;
    private static final int REQUEST_IGNORE_BATTERY_CODE = 10002;
    private static final String RES_AUTH_FAIL_DESC = "request resourceAuth fail.";
    private static final String ROUTE_TO_PARAM_IS_FORE_RESULT = "isForResult";
    private static final String ROUTE_TO_PARAM_PATH = "path";
    private static final Map<String, String> SHARED_DATA_MAP = new ConcurrentHashMap(1);
    private static final String STRATEGY_NAME = "strategyNames";
    private H5PhoneStateListener mH5PhoneStateListener;
    private HealthCloudOperate mHealthCloudOperate;
    private List<Integer> mSuccessList;
    private SyncCloudDataListener mSyncCloudDataListener;
    private long mCallbackIdOfLoadH5ProApp = -1;
    private long mCallbackIdOfPhoneListener = 0;
    private long mCallBackRequestIgnore = -1;
    private long mCallBackIdOfRouteToNativePage = -1;

    /* loaded from: classes9.dex */
    static class SyncParam {

        @SerializedName("dataType")
        int dataType = 20000;

        @SerializedName("action")
        int action = 5;

        private SyncParam() {
        }
    }

    @JavascriptInterface
    public void syncCloud(long j, String str) {
        LogUtil.a(this.TAG, "syncCloudData start");
        try {
            SyncParam syncParam = (SyncParam) new Gson().fromJson(str, SyncParam.class);
            HiSyncOption hiSyncOption = new HiSyncOption();
            hiSyncOption.setSyncAction(syncParam.action);
            hiSyncOption.setSyncDataType(syncParam.dataType);
            hiSyncOption.setSyncModel(2);
            hiSyncOption.setSyncMethod(2);
            hiSyncOption.setSyncScope(1);
            HiHealthManager.d(BaseApplication.e()).synCloud(hiSyncOption, null);
            onSuccessCallback(j, "");
            LogUtil.a(this.TAG, "syncCloudData end");
        } catch (JsonSyntaxException e) {
            onFailureCallback(j, e.getMessage());
            LogUtil.b(this.TAG, "syncCloud error ", e.getMessage());
        }
    }

    @JavascriptInterface
    public void syncCloudTypeList(long j, String str) {
        LogUtil.a(this.TAG, "syncCloudTypeList param ", str);
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("syncDataType");
            if (optJSONArray == null) {
                ReleaseLogUtil.d(this.TAG, "syncCloudTypeList jsonArray is null");
                onFailureCallback(j, "syncCloudTypeList jsonArray is null");
                return;
            }
            ArrayList arrayList = new ArrayList();
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(Integer.valueOf(optJSONObject.optInt("id")));
                }
            }
            if (CollectionUtils.d(arrayList)) {
                String str2 = "syncCloudTypeList syncDataTypeList " + arrayList;
                ReleaseLogUtil.d(this.TAG, str2);
                onFailureCallback(j, str2);
                return;
            }
            HiSyncOption hiSyncOption = new HiSyncOption();
            hiSyncOption.setForceSync(true);
            hiSyncOption.setSyncDataTypes(arrayList);
            hiSyncOption.setSyncId(System.currentTimeMillis());
            hiSyncOption.setSyncMethod(2);
            hiSyncOption.setSyncScope(1);
            hiSyncOption.setSyncModel(2);
            hiSyncOption.setSyncAction(0);
            HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
            onSuccessCallback(j, arrayList);
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c(this.TAG, "syncCloudTypeList exception ", d);
            onFailureCallback(j, d);
        }
    }

    @JavascriptInterface
    public void routeTo(long j, String str) {
        LogUtil.a(this.TAG, "routeTo param = " + str);
        if (!TrustListCheckerImpl.isUseSpecialJsApi(this.mH5ProInstance)) {
            onFailureCallback(j, ErrorEnum.NO_PERMISSION.getMsg());
            return;
        }
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "routeTo invalid param");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(BleConstants.KEY_PATH);
            boolean optBoolean = jSONObject.optBoolean(ROUTE_TO_PARAM_IS_FORE_RESULT, false);
            Bundle assembleRouterExtra = assembleRouterExtra(jSONObject);
            if (checkRouteToKnownParams(j, optString, optBoolean)) {
                if (!InnerApiPermissionManager.checkAccessToTargetPage(optString)) {
                    onFailureCallback(j, "The target page is not allowed to be accessed.");
                } else {
                    routeToNativePage(j, optString, optBoolean, assembleRouterExtra);
                }
            }
        } catch (JSONException unused) {
            onFailureCallback(j, "routeTo fail to parse param");
        }
    }

    private void routeToNativePage(final long j, String str, final boolean z, Bundle bundle) {
        int i = z ? REQUEST_CODE_ROUTE_RESULT : -1;
        Activity activity = z ? (Activity) this.mContext : null;
        this.mCallBackIdOfRouteToNativePage = j;
        AppRouter.b(str).zF_(bundle).zE_(activity, i, new NaviCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.1
            @Override // com.huawei.haf.router.NaviCallback
            public void onFound(Guidepost guidepost) {
                LogUtil.a(InnerApi.this.TAG, "routeTo onFound");
            }

            @Override // com.huawei.haf.router.NaviCallback
            public void onLost(Guidepost guidepost) {
                LogUtil.a(InnerApi.this.TAG, "routeTo onLost");
                InnerApi.this.onFailureCallback(j, "onLost");
            }

            @Override // com.huawei.haf.router.NaviCallback
            public void onArrival(Guidepost guidepost) {
                LogUtil.a(InnerApi.this.TAG, "routeTo onArrival");
                if (z) {
                    return;
                }
                InnerApi.this.onSuccessCallback(j, "success");
            }

            @Override // com.huawei.haf.router.NaviCallback
            public void onInterrupt(Guidepost guidepost) {
                LogUtil.a(InnerApi.this.TAG, "routeTo onInterrupt");
            }
        });
    }

    private boolean checkRouteToKnownParams(long j, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "routeTo param invalid");
            return false;
        }
        if (!z || (this.mContext instanceof Activity)) {
            return true;
        }
        onFailureCallback(j, "not support");
        return false;
    }

    private Bundle assembleRouterExtra(JSONObject jSONObject) throws JSONException {
        List asList = Arrays.asList(BleConstants.KEY_PATH);
        Bundle bundle = new Bundle();
        if (jSONObject.length() > asList.size()) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!asList.contains(next)) {
                    putRouterExtraPer(bundle, next, jSONObject.get(next));
                }
            }
        }
        return bundle;
    }

    private void putRouterExtraPer(Bundle bundle, String str, Object obj) {
        if (obj instanceof String) {
            bundle.putString(str, (String) obj);
            return;
        }
        if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof JSONArray) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(moj.b(obj.toString(), String[].class));
            bundle.putStringArrayList(str, arrayList);
        } else {
            LogUtil.a(this.TAG, "routeTo putRouterExtraPer: param unknown type -> " + obj);
        }
    }

    @JavascriptInterface
    public void notifyObserver(long j, String str) {
        LogUtil.a(this.TAG, "notifyObserver callbackId ", Long.valueOf(j), " param ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("argumentList");
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        HashMap hashMap = new HashMap();
                        Iterator<String> keys = optJSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            hashMap.put(next, optJSONObject.optString(next));
                        }
                        if (!CollectionUtils.e(hashMap)) {
                            arrayList.add(hashMap);
                        }
                    }
                }
            }
            String optString = jSONObject.optString("label");
            if (CollectionUtils.d(arrayList)) {
                ObserverManagerUtil.c(optString, new Object[0]);
            } else {
                ObserverManagerUtil.c(optString, arrayList.toArray());
            }
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c("R_" + this.TAG, "notifyObserver exception ", d);
            onFailureCallback(j, d);
        }
    }

    @JavascriptInterface
    public void jumpToHome(long j, String str) {
        LogUtil.a(this.TAG, "jumpToDeviceList param = " + str);
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "invalid param");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("name");
            String optString2 = jSONObject.optString("value");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                onFailureCallback(j, "param invalid");
                return;
            }
            Context e = BaseApplication.e();
            Intent intent = new Intent();
            intent.setClassName(e, "com.huawei.health.MainActivity");
            intent.setFlags(AppRouterExtras.COLDSTART);
            intent.putExtra(optString, optString2);
            intent.putExtra("SHORTCUT", "SC_DEVICE");
            gnm.aPB_(e, intent);
            onSuccessCallback(j, "success");
        } catch (JSONException unused) {
            onFailureCallback(j, "fail to parse param");
        }
    }

    @JavascriptInterface
    public void getUrl(final long j, String str) {
        LogUtil.a(this.TAG, "getUrl start");
        if (this.mContext == null) {
            LogUtil.h(this.TAG, "getUrl context is null");
            return;
        }
        Context e = BaseApplication.e();
        if (!LoginInit.getInstance(e).getIsLogined()) {
            String noCheckUrl = GRSManager.a(e).getNoCheckUrl(str, GRSManager.a(e).getCommonCountryCode());
            if (TextUtils.isEmpty(noCheckUrl)) {
                onFailureCallback(j, "no login: URL is empty");
                return;
            } else {
                onSuccessCallback(j, noCheckUrl);
                return;
            }
        }
        GRSManager.a(e).e(str, new GrsQueryCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    InnerApi.this.onFailureCallback(j, "login: URL is empty");
                } else {
                    InnerApi.this.onSuccessCallback(j, str2);
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                InnerApi.this.onFailureCallback(j, "login: failed to obtain the URL", i);
            }
        });
    }

    @JavascriptInterface
    public void getCountryCode(long j) {
        LogUtil.a(this.TAG, "getCountryCode start");
        if (this.mContext != null) {
            String commonCountryCode = GRSManager.a(this.mContext).getCommonCountryCode();
            if (TextUtils.isEmpty(commonCountryCode)) {
                onFailureCallback(j, "The country code is empty.");
            } else {
                onSuccessCallback(j, commonCountryCode);
            }
            LogUtil.a(this.TAG, "getCountryCode end");
        }
    }

    @JavascriptInterface
    public void getDeviceInfoSync(long j) {
        LogUtil.a(this.TAG, "getDeviceInfoSync start");
        HashMap hashMap = new HashMap();
        hashMap.put("osVersionName", Build.ID);
        hashMap.put("device", Build.DEVICE);
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put(JsbMapKeyNames.H5_BRAND, Build.BRAND);
        hashMap.put("isHarmony", String.valueOf(CommonUtil.az()));
        hashMap.put("isFoldable", String.valueOf(EnvironmentInfo.e()));
        onSuccessCallback(j, hashMap);
        LogUtil.a(this.TAG, "getDeviceInfoSync end");
    }

    @JavascriptInterface
    public void getABTestStrategyNames(long j) {
        LogUtil.a(this.TAG, "getABTestStrategyNames");
        if (this.mContext == null) {
            LogUtil.b(this.TAG, "getABTestStrategyNames mContext == null");
            return;
        }
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add("[VIP]");
        if (gpo.b()) {
            hashMap.put(STRATEGY_NAME, arrayList);
        } else {
            hashMap.put(STRATEGY_NAME, arrayList2);
        }
        LogUtil.a(this.TAG, STRATEGY_NAME, hashMap);
        onSuccessCallback(j, hashMap);
    }

    private List<String> isBlockAbMemberTab(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                if (!"[VIP]".equals(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    @JavascriptInterface
    public void getNetworkStatus(long j) {
        LogUtil.a(this.TAG, "getNetworkStatus start");
        if (this.mContext != null) {
            onSuccessCallback(j, NetworkStatusUtils.getsInstance().getNetworkStatus(this.mContext));
            LogUtil.a(this.TAG, "getNetworkStatus end");
        }
    }

    @JavascriptInterface
    public void updateAuthInfo() {
        LogUtil.a(this.TAG, "updateAuthInfo");
        LoginInit.login(this.mContext, new ILoginCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.3
            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginSuccess(Object obj) {
                LogUtil.a(InnerApi.this.TAG, "updateAuthInfo success");
            }

            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginFailed(Object obj) {
                LogUtil.h(InnerApi.this.TAG, "updateAuthInfo fail");
            }
        });
    }

    @JavascriptInterface
    public void getAuthInfo(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            LoginInit loginInit = LoginInit.getInstance(this.mContext);
            jSONObject.put("language", getLanguage());
            jSONObject.put(CardMgrSdkConst.CardInfoDesc.PARAMS_LANGUAGE_TAG, getLanguageTag());
            jSONObject.put(SyncDataConstant.BI_KEY_ACCOUNT_TYPE, loginInit.getAccountInfo(1004));
            jSONObject.put("appId", BaseApplication.d());
            jSONObject.put("appType", "1");
            jSONObject.put("deviceType", loginInit.getDeviceType());
            jSONObject.put("sysVersion", Build.VERSION.RELEASE);
            jSONObject.put("timeZone", HiDateUtil.d(""));
            jSONObject.put("appVersion", CommonUtil.c(BaseApplication.e()));
            jSONObject.put("siteId", loginInit.getAccountInfo(1009));
            jSONObject.put("countryCode", GRSManager.a(this.mContext).getCommonCountryCode());
            jSONObject.put(CloudParamKeys.CLIENT_TYPE, eil.a());
            jSONObject.put("phoneDeviceType", PhoneInfoUtils.getDeviceModel());
            jSONObject.put("isMobileAppEngine", EnvironmentInfo.k());
            jSONObject.put(FaqConstants.FAQ_EMUIVERSION, CommonUtil.r());
            jSONObject.put("firmware", Build.DISPLAY);
            jSONObject.put("model", Build.MODEL);
            jSONObject.put(QuestionSurveyRequestParam.CVERISON, CommonUtil.d(BaseApplication.e()));
            jSONObject.put("os", Agent.OS_NAME + Build.VERSION.RELEASE);
            jSONObject.put("isHuaweiSystem", CommonUtil.bh());
            putSensitiveData(jSONObject);
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b("H5PRO_InnerApi", "JSON error " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    private void putSensitiveData(JSONObject jSONObject) throws JSONException {
        if (TrustListCheckerImpl.isUseSpecialJsApi(this.mH5ProInstance)) {
            LoginInit loginInit = LoginInit.getInstance(this.mContext);
            String accountInfo = loginInit.getAccountInfo(1015);
            if (TextUtils.isEmpty(accountInfo)) {
                jSONObject.put("token", DataTransferInterceptorImpl.TOKEN_PLACEHOLDER);
                jSONObject.put("tokenType", Integer.parseInt("1"));
                LogUtil.a(this.TAG, "putSensitiveData: at is empty");
                JsInteractAddition.getInstance().reportErrorCode(OperationKey.HEALTH_APP_H5_ERROR_CODE_80060003.value(), JsInteractAddition.BI_ERROR_CODE_INVALID_AT);
            } else {
                jSONObject.put("token", accountInfo);
                jSONObject.put("accessToken", accountInfo);
                jSONObject.put("tokenType", Integer.parseInt("2"));
            }
            jSONObject.put("huid", loginInit.getAccountInfo(1011));
            jSONObject.put("accountName", loginInit.getAccountInfo(1001));
            jSONObject.put("deviceSN", CommonUtil.g());
            jSONObject.put("deviceId", loginInit.getDeviceId());
        }
    }

    public static String getLanguage() {
        Locale locale = BaseApplication.e().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if ("ZH".equalsIgnoreCase(language)) {
            String script = locale.getScript();
            if ("HANS".equalsIgnoreCase(script)) {
                return "zh-CN";
            }
            if ("HANT".equalsIgnoreCase(script) && "CN".equalsIgnoreCase(country)) {
                return "zh-HK";
            }
        }
        return TextUtils.concat(language, "_", country).toString();
    }

    private String getLanguageTag() {
        return this.mContext.getResources().getConfiguration().locale.toLanguageTag();
    }

    private boolean isInScope(long j) {
        if (TrustListCheckerImpl.isUseSpecialJsApi(this.mH5ProInstance)) {
            return true;
        }
        LogUtil.h(this.TAG, "authentication failed");
        onFailureCallback(j, "authentication failed");
        return false;
    }

    @JavascriptInterface
    public void getUserInfo(long j) {
        LogUtil.a(this.TAG, Constants.METHOD_GET_USER_INFO);
        if (isInScope(j)) {
            LoginInit loginInit = LoginInit.getInstance(this.mContext);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(JsbMapKeyNames.H5_USER_ID, loginInit.getAccountInfo(1011));
                jSONObject.put("accountName", loginInit.getAccountInfo(1000));
                jSONObject.put("accountPlaintextName", loginInit.getAccountInfo(1001));
                jSONObject.put(HwPayConstant.KEY_USER_NAME, loginInit.getAccountInfo(1002));
                jSONObject.put("userPic", loginInit.getAccountInfo(1003));
                jSONObject.put(CommonConstant.KEY_GENDER, loginInit.getAccountInfo(1005));
                jSONObject.put("birthDate", loginInit.getAccountInfo(1006));
                onSuccessCallback(j, jSONObject);
            } catch (JSONException e) {
                onFailureCallback(j, e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void browsingToLogin(final long j) {
        Log.i(this.TAG, "start browsingToLogin");
        LoginInit.getInstance(this.mContext).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                InnerApi.this.onSuccessCallback(j, Integer.valueOf(i));
            }
        }, "");
    }

    @JavascriptInterface
    public void getVipInfo(final long j) {
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h(this.TAG, "vipApi is null.");
        } else {
            vipApi.getVipInfo(new VipCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.5
                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    LogUtil.a(InnerApi.this.TAG, "getVipInfo on Success.");
                    InnerApi.this.onSuccessCallback(j, obj);
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i, String str) {
                    LogUtil.h(InnerApi.this.TAG, "getVipInfo on failure.");
                    InnerApi.this.onFailureCallback(j, str, i);
                }
            });
        }
    }

    @JavascriptInterface
    public void getProductState(final long j, String str) {
        LogUtil.c(this.TAG, "productId:", str);
        LogUtil.a(this.TAG, "getProductState callbackId:", Long.valueOf(j));
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h(this.TAG, "getProductState payApi is null.");
            onFailureCallback(j, "payApi is null.");
        } else {
            payApi.getProductState(str, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("R_" + InnerApi.this.TAG, "getProductState onResponse.");
                    if (i == 0) {
                        InnerApi.this.onSuccessCallback(j, obj);
                    } else {
                        InnerApi.this.onFailureCallback(j, "getProductState error.", i);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void buyByProductId(final long j, String str) {
        LogUtil.a(this.TAG, "productId:", str);
        LogUtil.a(this.TAG, "buyByProductId callbackId:", Long.valueOf(j));
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h(this.TAG, "buyByProductId payApi is null.");
            onFailureCallback(j, "payApi is null.");
            return;
        }
        H5ProBridgeManager.getInstance().registerActivityResultCallback(10000, new H5ProActivityResultCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.7
            @Override // com.huawei.health.h5pro.core.H5ProActivityResultCallback
            public void onResult(Intent intent) {
                LogUtil.a(InnerApi.this.TAG, "buyByProductId finish callbackId:", Long.valueOf(j));
                int i = -1;
                if (intent != null) {
                    i = intent.getIntExtra("shoppingResult", -1);
                } else {
                    LogUtil.h(InnerApi.this.TAG, "intent is null.");
                }
                LogUtil.a(InnerApi.this.TAG, "shoppingResult:", Integer.valueOf(i));
                InnerApi.this.onSuccessCallback(j, Integer.valueOf(i));
            }
        });
        Map<String, String> globalParams = Analyzer.getGlobalParams();
        if (globalParams != null) {
            ProductInfo productInfo = (ProductInfo) nrv.b(str, ProductInfo.class);
            if (productInfo != null) {
                productInfo.setBiParams(globalParams);
            }
            ReleaseLogUtil.e(this.TAG, "buyByProductId Analyzer.getGlobalParams() = ", globalParams.toString());
            str = nrv.a(productInfo);
        } else {
            LogUtil.h(this.TAG, "Analyzer.getGlobalParams() is empty");
        }
        if (this.mContext instanceof Activity) {
            payApi.buyByProductId((Activity) this.mContext, 10000, str);
        } else {
            LogUtil.h(this.TAG, "context is not activity.");
            payApi.buyByProductId(BaseApplication.wa_(), 10000, str);
        }
    }

    @JavascriptInterface
    public void getAccessToken(long j) {
        LogUtil.a(this.TAG, "getAccessToken");
        if (isInScope(j)) {
            String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1015);
            if (!TextUtils.isEmpty(accountInfo)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("at", accountInfo);
                    onSuccessCallback(j, jSONObject);
                    return;
                } catch (JSONException e) {
                    onFailureCallback(j, e.getMessage());
                    return;
                }
            }
            onFailureCallback(j, "AT is empty");
        }
    }

    @JavascriptInterface
    public void loadH5ProApp(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "loadH5ProApp: invalid param");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(SdkCfgSha256Record.PKGNAME);
            String optString2 = jSONObject.optString(BleConstants.KEY_PATH);
            boolean optBoolean = jSONObject.optBoolean(ROUTE_TO_PARAM_IS_FORE_RESULT, false);
            boolean optBoolean2 = jSONObject.optBoolean("isNewSingleInstance", false);
            int optInt = jSONObject.optInt("startMode", 0);
            String optString3 = jSONObject.optString("customizeArgs", "");
            Map<String, Object> parseMapJson = !TextUtils.isEmpty(optString3) ? GsonUtil.parseMapJson(optString3) : null;
            if (TextUtils.isEmpty(optString)) {
                onFailureCallback(j, "pkgName is empty");
                return;
            }
            this.mCallbackIdOfLoadH5ProApp = j;
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addPath(optString2);
            builder.setIsForResult(optBoolean);
            if (optBoolean) {
                builder.setRequestCode(10001);
            }
            if (optBoolean2) {
                builder.setActivityStartFlag(AppRouterExtras.COLDSTART);
            }
            if (parseMapJson != null && !parseMapJson.isEmpty()) {
                for (String str2 : parseMapJson.keySet()) {
                    Object obj = parseMapJson.get(str2);
                    if (!TextUtils.isEmpty(str2) && (obj instanceof String)) {
                        builder.addCustomizeArg(str2, String.valueOf(obj));
                    } else {
                        LogUtil.a(this.TAG, "Not supported: key = " + str2 + " value = " + obj);
                    }
                }
            }
            LogUtil.a(this.TAG, "loadH5ProApp pkgName = " + optString + ", path=" + optString2);
            if (optInt == 2) {
                Activity wa_ = BaseApplication.wa_();
                if ((wa_ instanceof H5ProWebViewActivity) && optString.equalsIgnoreCase(wa_.getIntent().getStringExtra("h5Flag"))) {
                    wa_.finish();
                }
            } else {
                builder.setStartMode(optInt);
            }
            bzs.e().loadH5ProApp(this.mContext, optString, builder);
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:param invalid");
        }
    }

    @JavascriptInterface
    public void loadH5Compact(long j, String str) {
        LogUtil.a(this.TAG, "loadH5Compact");
        Intent intent = new Intent(this.mContext, (Class<?>) WebViewActivity.class);
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                intent.putExtra(next, jSONObject.optString(next));
            }
            this.mContext.startActivity(intent);
            onSuccessCallback(j);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "loadH5Compact param invalid:", e.getMessage());
            onFailureCallback(j, "loadH5Compact: invalid param");
        }
    }

    @JavascriptInterface
    public void resourceAuth(final long j, int i, String str) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h(this.TAG, "payApi is null in resourceAuth.");
            onFailureCallback(j, RES_AUTH_FAIL_DESC);
        } else {
            payApi.resourceAuth(i, str, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 == 0) {
                        InnerApi.this.onSuccessCallback(j, obj);
                    } else {
                        LogUtil.h(InnerApi.this.TAG, "resourceAuth errorCode: ", Integer.valueOf(i2));
                        InnerApi.this.onFailureCallback(j, InnerApi.RES_AUTH_FAIL_DESC, i2);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void initJsAt(long j) {
        if (CommonUtil.z(BaseApplication.e())) {
            LogUtil.a(this.TAG, "initJsAt registerJsApi HmsLiteCoreApi");
            JsClientApi.registerJsApi(HmsLiteCoreApi.class);
            JsClientApi.registerIAccessTokenClass(new IAccessToken() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.9
                @Override // com.huawei.hwcloudjs.api.IAccessToken
                public void getAccessToken(TaskCallBack taskCallBack) {
                    String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1008);
                    Status status = new Status(0, "success");
                    status.setSuccessFlag(true);
                    taskCallBack.onResult(new AccessTokenInfo(accountInfo, status));
                }
            });
        } else {
            JsClientApi.registerJsApi(HmsCoreApi.class);
            LogUtil.a(this.TAG, "registerJsApi HmsCoreApi");
        }
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void functionAuth(final long j, String str) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h(this.TAG, "payApi is null in functionAuth.");
            onFailureCallback(j, FUNC_AUTH_FAIL_DESC);
        } else {
            payApi.functionAuth(str, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        InnerApi.this.onSuccessCallback(j, obj);
                    } else {
                        LogUtil.h(InnerApi.this.TAG, "functionAuth errorCode: ", Integer.valueOf(i));
                        InnerApi.this.onFailureCallback(j, InnerApi.FUNC_AUTH_FAIL_DESC, i);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void isMatchUserLabel(long j, String str) {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            onFailureCallback(j, "isMatchUserLabel get marketingApi fail");
        } else {
            onSuccessCallback(j, Boolean.valueOf(marketingApi.isValidUserLabels(str)));
        }
    }

    @JavascriptInterface
    public void getHelpCustomerUrl(final long j, final String str) {
        LogUtil.a(this.TAG, "getHelpCustomerUrl");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(this.TAG, "param is null");
        } else {
            GRSManager.a(this.mContext).e("helpCustomerUrl", new GrsQueryCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.11
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str2) {
                    if (TextUtils.isEmpty(str2)) {
                        LogUtil.h(InnerApi.this.TAG, "getHelpCustomerUrl: urlDomain is empty");
                        onCallBackFail(-1);
                        return;
                    }
                    try {
                        InnerApi.this.onSuccessCallback(j, HelpCustomerOperate.getHelpCustomerUrl(str2, new JSONObject(str).optString(BleConstants.KEY_PATH, "")));
                    } catch (JSONException e) {
                        LogUtil.b(InnerApi.this.TAG, "getHelpCustomerUrl: JSONException");
                        InnerApi.this.onFailureCallback(j, e.getMessage());
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.h(InnerApi.this.TAG, "getHelpCustomerUrl: onCallBackFail -> " + i);
                    InnerApi.this.onFailureCallback(j, "Failed to obtain the URL domain", i);
                }
            });
        }
    }

    @JavascriptInterface
    public void writeSampleConfigList(final long j, String str) {
        LogUtil.a(this.TAG, "writeSampleConfigList callbackId ", Long.valueOf(j), " param ", str);
        if (!isInScope(j)) {
            ReleaseLogUtil.d("R_" + this.TAG, "writeSampleConfigList isInScope false");
            return;
        }
        try {
            List<HiSampleConfig> hiSampleConfigList = getHiSampleConfigList(new JSONArray(str));
            if (CollectionUtils.d(hiSampleConfigList)) {
                String str2 = "writeSampleConfigList hiSampleConfigList " + hiSampleConfigList;
                ReleaseLogUtil.d("R_" + this.TAG, str2);
                onFailureCallback(j, str2);
                return;
            }
            LogUtil.a(this.TAG, "writeSampleConfigList hiSampleConfigList ", hiSampleConfigList);
            HiHealthManager.d(BaseApplication.e()).setSampleConfig(hiSampleConfigList, new HiDataOperateListener() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi$$ExternalSyntheticLambda4
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i, Object obj) {
                    InnerApi.this.m722x33b6cc14(j, i, obj);
                }
            });
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c("R_" + this.TAG, "writeSampleConfigList exception ", d);
            onFailureCallback(j, d);
        }
    }

    /* renamed from: lambda$writeSampleConfigList$0$com-huawei-operation-h5pro-jsmodules-InnerApi, reason: not valid java name */
    /* synthetic */ void m722x33b6cc14(long j, int i, Object obj) {
        String str = "writeSampleConfigList onResult errorCode " + i + " object " + obj;
        ReleaseLogUtil.e("R_" + this.TAG, str);
        if (i == 0) {
            onSuccessCallback(j, obj);
        } else {
            onFailureCallback(j, str);
        }
    }

    private List<HiSampleConfig> getHiSampleConfigList(JSONArray jSONArray) {
        String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.e());
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                ReleaseLogUtil.d("R_" + this.TAG, "getHiSampleConfigList jsonObject is null");
            } else {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("dataType");
                if (optJSONObject2 == null) {
                    ReleaseLogUtil.d("R_" + this.TAG, "getHiSampleConfigList dataTypeJsonObject is null");
                } else {
                    JSONObject optJSONObject3 = optJSONObject.optJSONObject("configSubType");
                    if (optJSONObject3 == null) {
                        ReleaseLogUtil.d("R_" + this.TAG, "getHiSampleConfigList configSubTypeJsonObject is null");
                    } else {
                        HiSampleConfig.Builder builder = new HiSampleConfig.Builder();
                        builder.j(String.valueOf(optJSONObject2.optInt("id")));
                        builder.d(String.valueOf(optJSONObject3.optInt("id")));
                        builder.b(optJSONObject.optString("configData"));
                        builder.h(optJSONObject.optString("scopeApp", String.valueOf(0)));
                        builder.g(optJSONObject.optString("scopeDeviceType", String.valueOf(0)));
                        builder.a(optJSONObject.optString("configDescription"));
                        builder.c(optJSONObject.optLong("modifiedTime"));
                        builder.c(androidId);
                        arrayList.add(new HiSampleConfig(builder));
                    }
                }
            }
        }
        return arrayList;
    }

    @JavascriptInterface
    public void readSampleConfigList(final long j, String str) {
        LogUtil.a(this.TAG, "readSampleConfigList callbackId ", Long.valueOf(j), " param ", str);
        if (!isInScope(j)) {
            ReleaseLogUtil.d("R_" + this.TAG, "readSampleConfigList isInScope false");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            List<String> sampleConfigDataTypeList = getSampleConfigDataTypeList(jSONObject);
            if (CollectionUtils.d(sampleConfigDataTypeList)) {
                String str2 = "readSampleConfigList typeList " + sampleConfigDataTypeList;
                ReleaseLogUtil.d("R_" + this.TAG, str2);
                onFailureCallback(j, str2);
                return;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("subDataType");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                String str3 = "readSampleConfigList subDataType " + optJSONArray;
                ReleaseLogUtil.d("R_" + this.TAG, str3);
                onFailureCallback(j, str3);
                return;
            }
            HiSampleConfigProcessOption hiSampleConfigProcessOption = getHiSampleConfigProcessOption(optJSONArray, sampleConfigDataTypeList);
            LogUtil.a(this.TAG, "readSampleConfigList hiSampleConfigProcessOption ", hiSampleConfigProcessOption);
            HiHealthManager.d(BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, new HiDataReadResultListener() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.12
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    String str4 = "readSampleConfigList onResult errorCode " + i + " anchor " + i2 + " object " + obj;
                    LogUtil.a(InnerApi.this.TAG, str4);
                    if (i == 0) {
                        InnerApi.this.processSampleConfigList(j, obj);
                        return;
                    }
                    ReleaseLogUtil.d("R_" + InnerApi.this.TAG, "readSampleConfigList onResult errorCode ", Integer.valueOf(i));
                    InnerApi.this.onFailureCallback(j, str4);
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                    LogUtil.a(InnerApi.this.TAG, "readSampleConfigList onResultIntent intentType ", Integer.valueOf(i), " anchor ", Integer.valueOf(i3), " errorCode ", Integer.valueOf(i2), " object ", obj);
                }
            });
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c("R_" + this.TAG, "readSampleConfigList exception ", d);
            onFailureCallback(j, d);
        }
    }

    private List<String> getSampleConfigDataTypeList(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("sampleConfigDataType");
        if (optJSONArray == null) {
            ReleaseLogUtil.d("R_" + this.TAG, "getSampleConfigDataTypeList sampleConfigDataType is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(String.valueOf(optJSONObject.optInt("id")));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSampleConfigList(long j, Object obj) {
        if (!koq.e(obj, HiSampleConfig.class)) {
            ReleaseLogUtil.d("R_" + this.TAG, "processSampleConfigList object ", obj);
            onSuccessCallback(j, Collections.emptyList());
            return;
        }
        List<HiSampleConfig> list = (List) obj;
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.d("R_" + this.TAG, "processSampleConfigList list ", list);
            onSuccessCallback(j, list);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (HiSampleConfig hiSampleConfig : list) {
            if (hiSampleConfig != null) {
                JsonObject jsonObject = new JsonObject();
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("id", Integer.valueOf(CommonUtils.h(hiSampleConfig.getType())));
                jsonObject.add("dataType", jsonObject2);
                JsonObject jsonObject3 = new JsonObject();
                jsonObject3.addProperty("id", Integer.valueOf(CommonUtils.h(hiSampleConfig.getConfigId())));
                jsonObject.add("configSubType", jsonObject3);
                jsonObject.addProperty("scopeApp", hiSampleConfig.getScopeApp());
                jsonObject.addProperty("configData", hiSampleConfig.getConfigData());
                jsonObject.addProperty("modifiedTime", Long.valueOf(hiSampleConfig.getModifiedTime()));
                jsonObject.addProperty("scopeDeviceType", hiSampleConfig.getScopeDeviceType());
                jsonObject.addProperty("configDescription", hiSampleConfig.getConfigDescription());
                arrayList.add(jsonObject);
            }
        }
        LogUtil.a(this.TAG, "processSampleConfigList resultList ", arrayList);
        onSuccessCallback(j, arrayList);
    }

    private HiSampleConfigProcessOption getHiSampleConfigProcessOption(JSONArray jSONArray, List<String> list) {
        HashMap hashMap = new HashMap();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String valueOf = String.valueOf(optJSONObject.optInt("id"));
                if (!hashMap.containsKey(valueOf)) {
                    for (String str : list) {
                        if (valueOf.startsWith(str)) {
                            hashMap.put(valueOf, str);
                        }
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry != null) {
                HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
                builder.b((String) entry.getValue());
                builder.d((String) entry.getKey());
                builder.e(String.valueOf(0));
                builder.a(String.valueOf(0));
                arrayList.add(new HiSampleConfigKey(builder));
            }
        }
        HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
        hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
        return hiSampleConfigProcessOption;
    }

    @JavascriptInterface
    public void writeSampleConfig(final long j, String str) {
        LogUtil.a(this.TAG, "writeSampleConfig callbackId ", Long.valueOf(j), " param ", str);
        if (!isInScope(j)) {
            ReleaseLogUtil.d(this.TAG, "writeSampleConfig isInScope false");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("configId");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                String str2 = "writeSampleConfig type " + optString + " configId " + optString2;
                ReleaseLogUtil.d(this.TAG, str2);
                onFailureCallback(j, str2);
                return;
            }
            HiSampleConfig.Builder builder = new HiSampleConfig.Builder();
            builder.j(optString);
            builder.d(optString2);
            builder.b(jSONObject.optString("configData"));
            long g = CommonUtils.g(jSONObject.optString("modifiedTime"));
            String optString3 = jSONObject.optString("scopeApp");
            String optString4 = jSONObject.optString("deviceUniqueId");
            String optString5 = jSONObject.optString("scopeDeviceType");
            if (g <= 0) {
                g = System.currentTimeMillis();
            }
            builder.c(g);
            if (TextUtils.isEmpty(optString3)) {
                optString3 = String.valueOf(0);
            }
            builder.h(optString3);
            if (TextUtils.isEmpty(optString4)) {
                optString4 = String.valueOf(-1);
            }
            builder.c(optString4);
            if (TextUtils.isEmpty(optString5)) {
                optString5 = String.valueOf(0);
            }
            builder.g(optString5);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new HiSampleConfig(builder));
            HiHealthManager.d(BaseApplication.e()).setSampleConfig(arrayList, new HiDataOperateListener() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi$$ExternalSyntheticLambda0
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i, Object obj) {
                    InnerApi.this.m721xb89be897(j, i, obj);
                }
            });
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c(this.TAG, "writeSampleConfig exception ", d);
            onFailureCallback(j, d);
        }
    }

    /* renamed from: lambda$writeSampleConfig$1$com-huawei-operation-h5pro-jsmodules-InnerApi, reason: not valid java name */
    /* synthetic */ void m721xb89be897(long j, int i, Object obj) {
        String str = "writeSampleConfig onResult errorCode " + i + " object " + obj;
        ReleaseLogUtil.e(this.TAG, str);
        if (i == 0) {
            onSuccessCallback(j, obj);
        } else {
            onFailureCallback(j, str);
        }
    }

    @JavascriptInterface
    public void readSampleConfig(final long j, String str) {
        LogUtil.a(this.TAG, "readSampleConfig callbackId ", Long.valueOf(j), " param ", str);
        if (!isInScope(j)) {
            ReleaseLogUtil.d(this.TAG, "readSampleConfig isInScope false");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("configId");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                String str2 = "readSampleConfig type " + optString + " configId " + optString2;
                ReleaseLogUtil.d(this.TAG, str2);
                onFailureCallback(j, str2);
                return;
            }
            HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
            builder.b(optString);
            builder.d(optString2);
            String optString3 = jSONObject.optString("scopeApp");
            String optString4 = jSONObject.optString("scopeDeviceType");
            if (TextUtils.isEmpty(optString3)) {
                optString3 = String.valueOf(0);
            }
            builder.e(optString3);
            if (TextUtils.isEmpty(optString4)) {
                optString4 = String.valueOf(0);
            }
            builder.a(optString4);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new HiSampleConfigKey(builder));
            HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
            hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
            HiHealthManager.d(BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, new HiDataReadResultListener() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.13
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    String str3 = "readSampleConfig onResult errorCode " + i + " anchor " + i2 + " object " + obj;
                    LogUtil.a(InnerApi.this.TAG, str3);
                    if (i == 0) {
                        InnerApi.this.onSuccessCallback(j, obj);
                    } else {
                        InnerApi.this.onFailureCallback(j, str3);
                    }
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                    LogUtil.a(InnerApi.this.TAG, "readSampleConfig onResultIntent intentType ", Integer.valueOf(i), " anchor ", Integer.valueOf(i3), " errorCode ", Integer.valueOf(i2), " object ", obj);
                }
            });
        } catch (JSONException e) {
            String d = ExceptionUtils.d(e);
            ReleaseLogUtil.c(this.TAG, "readSampleConfig exception ", d);
            onFailureCallback(j, d);
        }
    }

    @JavascriptInterface
    public void writeCustomData(long j, String str) {
        LogUtil.a(this.TAG, "writeCustomData");
        if (isInScope(j)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                HiHealthManager.d(this.mContext).setUserPreference(new HiUserPreference(jSONObject.getString("key"), jSONObject.getString("value")), true);
                onSuccessCallback(j, "write success");
            } catch (JSONException unused) {
                LogUtil.b(this.TAG, "writeCustomData param invalid");
                onFailureCallback(j, "writeCustomData: invalid param");
            }
        }
    }

    @JavascriptInterface
    public void readCustomData(final long j, String str) {
        LogUtil.a(this.TAG, "readCustomData");
        if (isInScope(j)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                final String string = jSONObject.getString("key");
                final boolean optBoolean = jSONObject.optBoolean("decode", false);
                jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.14
                    @Override // java.lang.Runnable
                    public void run() {
                        HiUserPreference userPreference = HiHealthManager.d(InnerApi.this.mContext).getUserPreference(string);
                        if (userPreference != null && userPreference.getValue() != null) {
                            try {
                                InnerApi.this.onSuccessCallback(j, optBoolean ? HiZipUtil.a(userPreference.getValue()) : userPreference.getValue());
                                return;
                            } catch (IOException e) {
                                LogUtil.b(InnerApi.this.TAG, "readCustomData result uncompress fail, " + e.getMessage());
                                InnerApi.this.onFailureCallback(j, "readCustomData result uncompress fail");
                                return;
                            }
                        }
                        InnerApi.this.onSuccessCallback(j, "");
                    }
                });
            } catch (JSONException unused) {
                LogUtil.b(this.TAG, "readCustomData param invalid");
                onFailureCallback(j, "readCustomData: invalid param");
            }
        }
    }

    @JavascriptInterface
    public void writeSharedPreference(long j, String str) {
        LogUtil.a(this.TAG, "writeSharedPreference");
        if (isInScope(j)) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h(this.TAG, "writeSharedPreference param ", str);
                onFailureCallback(j, "writeSharedPreference param is empty");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("module");
                String optString2 = jSONObject.optString("key");
                onSuccessCallback(j, Integer.valueOf(SharedPreferenceManager.e(BaseApplication.e(), optString, jfa.e(optString2), jSONObject.optString("value"), (StorageParams) null)));
            } catch (JSONException e) {
                LogUtil.b(this.TAG, "writeSharedPreference jsonException ", LogAnonymous.b((Throwable) e));
                onFailureCallback(j, "writeSharedPreference jsonException");
            }
        }
    }

    @JavascriptInterface
    public void readSharedPreference(long j, String str) {
        LogUtil.a(this.TAG, "readSharedPreference");
        if (isInScope(j)) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h(this.TAG, "readSharedPreference param ", str);
                onFailureCallback(j, "readSharedPreference param is empty");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                onSuccessCallback(j, SharedPreferenceManager.b(BaseApplication.e(), jSONObject.optString("module"), jfa.e(jSONObject.optString("key"))));
            } catch (JSONException e) {
                LogUtil.b(this.TAG, "readSharedPreference jsonException ", LogAnonymous.b((Throwable) e));
                onFailureCallback(j, "readSharedPreference jsonException");
            }
        }
    }

    @JavascriptInterface
    public void getCertificateChain(long j, String str) {
        LogUtil.a(this.TAG, "getCertificateChain");
        if (isInScope(j)) {
            if (!SecurityCertUtils.e()) {
                onFailureCallback(j, "initKeyStoreProvider failed");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = jSONObject.getJSONArray("idTypes");
                String string = jSONObject.getString("challenge");
                int length = jSONArray.length();
                int[] iArr = new int[length];
                for (int i = 0; i < length; i++) {
                    Object obj = jSONArray.get(i);
                    if (obj instanceof String) {
                        iArr[i] = Integer.parseInt((String) obj);
                    }
                }
                X509Certificate[] c = SecurityCertUtils.c(this.mContext, iArr, string.getBytes(StandardCharsets.UTF_8));
                if (c != null && c.length == 4) {
                    byte[] bArr = new byte[0];
                    for (X509Certificate x509Certificate : c) {
                        bArr = mergeBytes(bArr, x509Certificate.getEncoded());
                    }
                    onSuccessCallback(j, new Gson().toJson(Base64.a(bArr)));
                    return;
                }
                onFailureCallback(j, "certChain is wrong");
            } catch (NumberFormatException | CertificateEncodingException | JSONException e) {
                onFailureCallback(j, e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void getDeviceSnFromCert(long j) {
        LogUtil.a(this.TAG, "getDeviceSnFromCert");
        if (isInScope(j)) {
            String a2 = njn.a(BaseApplication.e());
            if (!TextUtils.isEmpty(a2)) {
                onSuccessCallback(j, new Gson().toJson(a2));
            } else {
                onFailureCallback(j, "getDeviceSnFromCert: getSn failed");
            }
        }
    }

    private byte[] mergeBytes(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @JavascriptInterface
    public void getDeviceBenefits(final long j, final String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(this.TAG, "getDeviceBenefits devBenefitQueryParam is empty");
        } else {
            LogUtil.a(this.TAG, "getDeviceBenefits start devBenefitQueryParam = ", str);
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    InnerApi.this.m718xa5b8cc36(j, str);
                }
            });
        }
    }

    /* renamed from: lambda$getDeviceBenefits$3$com-huawei-operation-h5pro-jsmodules-InnerApi, reason: not valid java name */
    /* synthetic */ void m718xa5b8cc36(final long j, String str) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h(this.TAG, "getDeviceBenefits payApi is null");
            onFailureCallback(j, "payApi is null");
            return;
        }
        try {
            DeviceBenefitQueryParam deviceBenefitQueryParam = (DeviceBenefitQueryParam) new Gson().fromJson(JsonSanitizer.sanitize(str), DeviceBenefitQueryParam.class);
            if (deviceBenefitQueryParam == null) {
                LogUtil.h(this.TAG, "getDeviceBenefits queryParam is null");
                onFailureCallback(j, "queryParam is null");
            } else {
                payApi.getDeviceBenefits(deviceBenefitQueryParam, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi$$ExternalSyntheticLambda3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        InnerApi.this.m717x303ea5f5(j, i, obj);
                    }
                });
            }
        } catch (JsonSyntaxException unused) {
            onFailureCallback(j, "queryParam error");
            LogUtil.b(this.TAG, "getDeviceBenefits JsonSyntaxException");
        }
    }

    /* renamed from: lambda$getDeviceBenefits$2$com-huawei-operation-h5pro-jsmodules-InnerApi, reason: not valid java name */
    /* synthetic */ void m717x303ea5f5(long j, int i, Object obj) {
        if (obj instanceof DeviceBenefits) {
            String e = HiJsonUtil.e((DeviceBenefits) obj);
            LogUtil.a(this.TAG, "getDeviceBenefits resultJson = ", e);
            onSuccessCallback(j, e);
        } else {
            LogUtil.a(this.TAG, "getDeviceBenefits objData = ", obj);
            onFailureCallback(j, String.valueOf(obj), i);
        }
    }

    @JavascriptInterface
    public void healthCloudRequest(final long j, String str) {
        LogUtil.a(this.TAG, "healthCloudRequest");
        if (isInScope(j)) {
            if (this.mHealthCloudOperate == null) {
                this.mHealthCloudOperate = new HealthCloudOperate();
            }
            this.mHealthCloudOperate.request(str, new ResultCallback<String>() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.15
                @Override // com.huawei.networkclient.ResultCallback
                public void onSuccess(String str2) {
                    LogUtil.c(InnerApi.this.TAG, "healthCloudRequest onSuccess -> " + str2);
                    InnerApi.this.onSuccessCallback(j, str2);
                }

                @Override // com.huawei.networkclient.ResultCallback
                public void onFailure(Throwable th) {
                    String message = th == null ? "request failed" : th.getMessage();
                    LogUtil.c(InnerApi.this.TAG, "healthCloudRequest onFailure -> " + message);
                    InnerApi.this.onFailureCallback(j, message);
                }
            });
        }
    }

    @JavascriptInterface
    public void isEmuiFor12Hour(long j) {
        onSuccessCallback(j, Boolean.valueOf(nsj.b(BaseApplication.e())));
    }

    @JavascriptInterface
    public void is12Hour(long j) {
        onSuccessCallback(j, Boolean.valueOf(!DateFormat.is24HourFormat(BaseApplication.e())));
    }

    @JavascriptInterface
    public void getDeviceCertificateChain(final long j, String str) {
        LogUtil.a(this.TAG, "getDeviceCertificateChain");
        if (isInScope(j)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = jSONObject.getJSONArray("idTypes");
                String string = jSONObject.getString("challenge");
                DeviceInfo deviceInfo = (DeviceInfo) new Gson().fromJson(jSONObject.getString("deviceInfo"), DeviceInfo.class);
                if (deviceInfo == null) {
                    LogUtil.h(this.TAG, "deviceInfo is null");
                    onFailureCallback(j, "param deviceInfo is null");
                }
                if (!cwi.c(deviceInfo, 125)) {
                    LogUtil.h(this.TAG, "device not support certificate chain authentication");
                    onFailureCallback(j, "device not support certificate chain authentication");
                    return;
                }
                int length = jSONArray.length();
                int[] iArr = new int[length];
                for (int i = 0; i < length; i++) {
                    Object obj = jSONArray.get(i);
                    if (obj instanceof String) {
                        iArr[i] = Integer.parseInt((String) obj);
                    }
                }
                cwb.d().b(deviceInfo, iArr, string, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi$$ExternalSyntheticLambda5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj2) {
                        InnerApi.this.m719xcced8c25(j, i2, obj2);
                    }
                });
            } catch (NumberFormatException | JSONException e) {
                String message = e.getMessage();
                LogUtil.b(this.TAG, "getDeviceCertificateChain exception : ", message);
                onFailureCallback(j, message);
            }
        }
    }

    /* renamed from: lambda$getDeviceCertificateChain$4$com-huawei-operation-h5pro-jsmodules-InnerApi, reason: not valid java name */
    /* synthetic */ void m719xcced8c25(long j, int i, Object obj) {
        LogUtil.a(this.TAG, "getDeviceCertificateChain errorCode = ", Integer.valueOf(i));
        if (i == -1 || !(obj instanceof String)) {
            onFailureCallback(j, "get device cert failure");
            return;
        }
        String str = (String) obj;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(this.TAG, "getDeviceCertificateChain cert is null");
            onFailureCallback(j, "get device cert failure, cert is null");
        } else {
            onSuccessCallback(j, new Gson().toJson(str));
        }
    }

    @JavascriptInterface
    public int getShowWeightUnit() {
        return UnitUtil.a();
    }

    @JavascriptInterface
    public void refreshAppRt(final long j) {
        LogUtil.a(this.TAG, "refreshAppRt");
        if (isInScope(j)) {
            if (CommonUtil.z(this.mContext)) {
                ThirdPartyLoginManager.getInstance().thirdPartyPhoneLogin(this.mContext, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.16
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a(InnerApi.this.TAG, "come in to reTradePayfresh RT");
                        if (i == -1) {
                            InnerApi.this.onFailureCallback(j, "refresh rt failed", i);
                        } else {
                            InnerApi.this.onSuccessCallback(j, obj);
                        }
                    }
                });
            } else {
                LogUtil.a(this.TAG, "come to get AT again on hms core");
            }
        }
    }

    @JavascriptInterface
    public void changePersonalInfo(long j) {
        LogUtil.a(this.TAG, "changePersonalInfo");
        if (isInScope(j)) {
            if (!(this.mContext instanceof Activity)) {
                onFailureCallback(j, "mContext instanceof Activity failed");
                LogUtil.h(this.TAG, "mContext instanceof Activity failed");
            } else if (glz.e()) {
                ThirdPartyLoginManager.getInstance().openPersonalInfo((Activity) this.mContext, null);
            } else {
                gmc.aPf_((Activity) this.mContext);
            }
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a(this.TAG, "onActivityResult: requestCode=" + i + " resultCode=" + i2);
        super.onActivityResult(i, i, intent);
        if (i == 10001) {
            long j = this.mCallbackIdOfLoadH5ProApp;
            if (j >= 0 && intent != null) {
                returnActivityResult(i2, intent, j);
                this.mCallbackIdOfLoadH5ProApp = -1L;
                return;
            }
        }
        if (i == 10002) {
            long j2 = this.mCallBackRequestIgnore;
            if (j2 >= 0) {
                onSuccessCallback(j2, Boolean.valueOf(isAgreeWhite()));
                this.mCallbackIdOfLoadH5ProApp = -1L;
                return;
            }
        }
        if (i == 7667713) {
            long j3 = this.mCallBackIdOfRouteToNativePage;
            if (j3 >= 0) {
                returnActivityResult(i2, intent, j3);
                this.mCallBackIdOfRouteToNativePage = -1L;
            }
        }
    }

    private void returnActivityResult(int i, Intent intent, long j) {
        if (i == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("result");
            if (intent.hasExtra("KEY_SUG_COURSE_IDS_RESULT")) {
                try {
                    onSuccessCallback(j, JsonParser.parseString(stringExtra).getAsJsonObject());
                    return;
                } catch (JsonSyntaxException | IllegalStateException e) {
                    ReleaseLogUtil.c(this.TAG, "returnActivityResult exception ", ExceptionUtils.d(e));
                    onFailureCallback(j, stringExtra);
                    return;
                }
            }
            onSuccessCallback(j, stringExtra);
            return;
        }
        onFailureCallback(j, "no result");
    }

    @JavascriptInterface
    public void registerPhoneStateListener(long j) {
        this.mCallbackIdOfPhoneListener = j;
        if (!PermissionUtil.g()) {
            setPhoneStateListener(this.mContext, true);
        } else {
            PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.PHONE_STATE, new CustomPermissionAction(this.mContext) { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.17
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a(InnerApi.this.TAG, "registerPhoneStateListener: onGranted");
                    InnerApi innerApi = InnerApi.this;
                    innerApi.setPhoneStateListener(innerApi.mContext, true);
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    super.onDenied(str);
                    LogUtil.a(InnerApi.this.TAG, "registerPhoneStateListener: onDenied");
                    InnerApi innerApi = InnerApi.this;
                    innerApi.onFailureCallback(innerApi.mCallbackIdOfPhoneListener, "denied");
                    InnerApi innerApi2 = InnerApi.this;
                    innerApi2.onCompleteCallback(innerApi2.mCallbackIdOfPhoneListener, "complete", 0);
                }
            });
        }
    }

    @JavascriptInterface
    public void unRegisterPhoneStateListener(long j) {
        setPhoneStateListener(this.mContext, false);
        onSuccessCallback(j);
    }

    /* loaded from: classes9.dex */
    class H5PhoneStateListener extends PhoneStateListener {
        private H5PhoneStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            LogUtil.a(InnerApi.this.TAG, "onCallStateChanged state = " + i);
            if (i == 0 || i == 2 || i == 1) {
                InnerApi innerApi = InnerApi.this;
                innerApi.onSuccessCallback(innerApi.mCallbackIdOfPhoneListener, Integer.valueOf(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPhoneStateListener(Context context, boolean z) {
        if (context == null) {
            LogUtil.h(this.TAG, "setPhoneStateListener context is null");
            return;
        }
        LogUtil.a(this.TAG, "setPhoneStateListener isListen = " + z);
        Object systemService = context.getSystemService("phone");
        if (systemService instanceof TelephonyManager) {
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            if (z) {
                if (this.mH5PhoneStateListener == null) {
                    H5PhoneStateListener h5PhoneStateListener = new H5PhoneStateListener();
                    this.mH5PhoneStateListener = h5PhoneStateListener;
                    telephonyManager.listen(h5PhoneStateListener, 32);
                    return;
                }
                return;
            }
            H5PhoneStateListener h5PhoneStateListener2 = this.mH5PhoneStateListener;
            if (h5PhoneStateListener2 != null) {
                telephonyManager.listen(h5PhoneStateListener2, 0);
                this.mH5PhoneStateListener = null;
            }
            long j = this.mCallbackIdOfPhoneListener;
            if (j > 0) {
                onCompleteCallback(j, "complete", 0);
                this.mCallbackIdOfPhoneListener = 0L;
            }
        }
    }

    @JavascriptInterface
    public void goSettingSelfStart() {
        LogUtil.a(this.TAG, "goSettingSelfStart");
        if (this.mContext instanceof Activity) {
            lcu.c(this.mContext);
        }
    }

    @JavascriptInterface
    public void goSettingBackRun() {
        LogUtil.a(this.TAG, "goSettingBackRun");
        if (this.mContext instanceof Activity) {
            lcu.e(this.mContext);
        }
    }

    @JavascriptInterface
    public boolean isAgreeWhite() {
        boolean d = lcu.d(this.mContext);
        LogUtil.a(this.TAG, "isAgreeWhite: ", Boolean.valueOf(d));
        return d;
    }

    @JavascriptInterface
    public void requestWhiteSet(long j) {
        LogUtil.a(this.TAG, "requestWhiteSet");
        if (this.mContext instanceof Activity) {
            this.mCallBackRequestIgnore = j;
            LogUtil.a(this.TAG, "mCallBackRequestIgnore: ", Long.valueOf(this.mCallBackRequestIgnore));
            lcu.d(this.mContext, 10002);
        }
    }

    @JavascriptInterface
    public void startAssociationStart() {
        lcu.a(this.mContext);
    }

    @JavascriptInterface
    public boolean isSupportBatteryOptimizations() {
        LogUtil.a(this.TAG, "isSupportBatteryOptimizations");
        if (!(this.mContext instanceof Activity)) {
            return false;
        }
        LogUtil.a(this.TAG, "mContext is instanceof Activity");
        boolean b = lcu.b(this.mContext);
        LogUtil.a(this.TAG, "isSupportBatteryOptimizations,isSupport: ", Boolean.valueOf(b));
        return b;
    }

    @JavascriptInterface
    public void writeSharedData(long j, String str) {
        LogUtil.a(this.TAG, "writeSharedData enter: " + str);
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "param is empty");
            return;
        }
        if (!TrustListCheckerImpl.isUseSpecialJsApi(this.mH5ProInstance)) {
            onFailureCallback(j, ErrorEnum.NO_PERMISSION.getMsg());
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                SHARED_DATA_MAP.put(next, jSONObject.getString(next));
            }
            onSuccessCallback(j);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "writeSharedData JSONException: " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void readSharedData(long j, String str) {
        LogUtil.a(this.TAG, "readSharedData enter: " + str);
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, ErrorEnum.REQ_PARAM_EMPTY.getMsg());
            return;
        }
        if (!TrustListCheckerImpl.isUseSpecialJsApi(this.mH5ProInstance)) {
            onFailureCallback(j, ErrorEnum.NO_PERMISSION.getMsg());
            return;
        }
        try {
            String string = new JSONObject(str).getString("key");
            if (TextUtils.isEmpty(string)) {
                onFailureCallback(j, ErrorEnum.REQ_PARAM_EMPTY.getMsg());
                return;
            }
            String str2 = SHARED_DATA_MAP.get(string);
            if (str2 == null) {
                onFailureCallback(j, "no data");
            } else {
                onSuccessCallback(j, str2);
            }
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "readSharedData JSONException: " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void clearSharedData(long j) {
        LogUtil.a(this.TAG, "clearSharedData enter");
        if (!TrustListCheckerImpl.isUseSpecialJsApi(this.mH5ProInstance)) {
            onFailureCallback(j, ErrorEnum.NO_PERMISSION.getMsg());
        } else {
            SHARED_DATA_MAP.clear();
            onSuccessCallback(j);
        }
    }

    @JavascriptInterface
    public void registerSyncCloudDataListener(long j) {
        if (this.mSyncCloudDataListener != null) {
            LogUtil.h(this.TAG, "registerSyncCloudDataListener: registered listener");
            return;
        }
        SyncCloudDataListener syncCloudDataListener = new SyncCloudDataListener();
        this.mSyncCloudDataListener = syncCloudDataListener;
        syncCloudDataListener.registerSyncCloudDataListener(this, j);
    }

    @JavascriptInterface
    public void unRegisterSyncCloudDataListener(long j) {
        SyncCloudDataListener syncCloudDataListener = this.mSyncCloudDataListener;
        if (syncCloudDataListener != null) {
            syncCloudDataListener.unRegisterSyncCloudDataListener(this, j);
            this.mSyncCloudDataListener = null;
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        SyncCloudDataListener syncCloudDataListener = this.mSyncCloudDataListener;
        if (syncCloudDataListener != null) {
            syncCloudDataListener.unRegisterSyncCloudDataListener();
            this.mSyncCloudDataListener = null;
        }
        unSubscribeHiHealthData();
    }

    @JavascriptInterface
    public void subscribeHiHealthData(long j, String str) {
        JSONObject jSONObject;
        LogUtil.a(this.TAG, "subscribeHiHealthData mSuccessList ", this.mSuccessList, " param ", str);
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(this.TAG, "subscribeHiHealthData exception ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.d(this.TAG, "subscribeHiHealthData jsonObject is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("typeList");
        if (optJSONArray == null) {
            ReleaseLogUtil.d(this.TAG, "subscribeHiHealthData jsonArray is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(Integer.valueOf(optJSONArray.optInt(i)));
        }
        String optString = jSONObject.optString("h5PackageName");
        if (TextUtils.isEmpty(optString)) {
            ReleaseLogUtil.d(this.TAG, "subscribeHiHealthData h5PackageName ", optString);
        } else {
            HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, new AnonymousClass18(j, optString));
        }
    }

    /* renamed from: com.huawei.operation.h5pro.jsmodules.InnerApi$18, reason: invalid class name */
    class AnonymousClass18 implements HiSubscribeListener {
        final /* synthetic */ long val$callbackId;
        final /* synthetic */ String val$h5PackageName;

        AnonymousClass18(long j, String str) {
            this.val$callbackId = j;
            this.val$h5PackageName = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            ReleaseLogUtil.e(InnerApi.this.TAG, "subscribeHiHealthData onResult successList ", list);
            if (!CollectionUtils.d(list)) {
                InnerApi.this.mSuccessList = list;
                InnerApi.this.onSuccessCallback(this.val$callbackId, list);
            } else {
                InnerApi.this.onFailureCallback(this.val$callbackId, "");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            ReleaseLogUtil.e(InnerApi.this.TAG, "subscribeHiHealthData onChange type ", Integer.valueOf(i), " changeKey ", str);
            HashMap hashMap = new HashMap(5);
            hashMap.put("time", Long.valueOf(j));
            hashMap.put("type", Integer.valueOf(i));
            hashMap.put("changeKey", str);
            HashMap hashMap2 = new HashMap(2);
            hashMap2.put("data", hashMap);
            hashMap2.put("commandName", "subscribeHiHealthData");
            String e = HiJsonUtil.e(hashMap2);
            LogUtil.a(InnerApi.this.TAG, "subscribeHiHealthData onChange json ", e, " h5PackageName ", this.val$h5PackageName);
            H5ProClient.sendCommandToJs(this.val$h5PackageName, e, new H5ProReverseControlExecutor.OnReverseControlCallback() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi$18$$ExternalSyntheticLambda0
                @Override // com.huawei.health.h5pro.core.H5ProReverseControlExecutor.OnReverseControlCallback
                public final void onCallback(int i2, String str2) {
                    InnerApi.AnonymousClass18.this.m723x3d145337(i2, str2);
                }
            });
        }

        /* renamed from: lambda$onChange$0$com-huawei-operation-h5pro-jsmodules-InnerApi$18, reason: not valid java name */
        /* synthetic */ void m723x3d145337(int i, String str) {
            ReleaseLogUtil.e(InnerApi.this.TAG, "subscribeHiHealthData onChange onCallback  code ", Integer.valueOf(i), " callbackData ", str);
        }
    }

    @JavascriptInterface
    public void unSubscribeHiHealthData() {
        ReleaseLogUtil.e(this.TAG, "unSubscribeHiHealthData mSuccessList ", this.mSuccessList);
        if (CollectionUtils.d(this.mSuccessList)) {
            return;
        }
        HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(this.mSuccessList, new HiUnSubscribeListener() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi$$ExternalSyntheticLambda1
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public final void onResult(boolean z) {
                InnerApi.this.m720xd5b53ba8(z);
            }
        });
    }

    /* renamed from: lambda$unSubscribeHiHealthData$5$com-huawei-operation-h5pro-jsmodules-InnerApi, reason: not valid java name */
    /* synthetic */ void m720xd5b53ba8(boolean z) {
        ReleaseLogUtil.e(this.TAG, "unSubscribeHiHealthData onResult isSuccess ", Boolean.valueOf(z));
    }

    @JavascriptInterface
    public void ifAllowLogin(long j) {
        onSuccessCallback(j, Boolean.valueOf(Utils.i()));
    }

    @JavascriptInterface
    public void setUserData(final long j, String str) {
        if (!LoginInit.getInstance(this.mContext).getIsLogined()) {
            logErrOnFailureCallback(j, "current not login");
        } else {
            final HiUserInfo hiUserInfo = (HiUserInfo) HiJsonUtil.e(str, HiUserInfo.class);
            HiHealthNativeApi.a(this.mContext).fetchUserData(new HiCommonListener() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.19
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    if (!koq.e(obj, HiUserInfo.class)) {
                        InnerApi.this.logErrOnFailureCallback(j, "fetchUserData failed: receive type error");
                        return;
                    }
                    HiUserInfo hiUserInfo2 = (HiUserInfo) ((List) obj).get(0);
                    InnerApi.this.updateUser(hiUserInfo2, hiUserInfo);
                    HiHealthNativeApi.a(InnerApi.this.mContext).setUserData(hiUserInfo2, new HiCommonListener() { // from class: com.huawei.operation.h5pro.jsmodules.InnerApi.19.1
                        @Override // com.huawei.hihealth.data.listener.HiCommonListener
                        public void onSuccess(int i2, Object obj2) {
                            LogUtil.a(InnerApi.this.TAG, "setUserData success");
                            InnerApi.this.onSuccessCallback(j);
                        }

                        @Override // com.huawei.hihealth.data.listener.HiCommonListener
                        public void onFailure(int i2, Object obj2) {
                            InnerApi.this.logErrOnFailureCallback(j, "setUserData failed: errCode=" + i2 + ", errMsg=" + obj2);
                        }
                    });
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    InnerApi.this.logErrOnFailureCallback(j, "fetchUserData failed: errCode=" + i + ", errMsg=" + obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logErrOnFailureCallback(long j, String str) {
        LogUtil.b(this.TAG, str);
        onFailureCallback(j, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006c, code lost:
    
        if (r1 != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateUser(com.huawei.hihealth.HiUserInfo r4, com.huawei.hihealth.HiUserInfo r5) {
        /*
            r3 = this;
            boolean r0 = r5.isWeightValid()
            r1 = 1
            if (r0 == 0) goto L1c
            java.lang.String r0 = r3.TAG
            java.lang.String r2 = "updateUser weight"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            float r0 = r5.getWeight()
            r4.setWeight(r0)
            r0 = r1
            goto L1d
        L1c:
            r0 = 0
        L1d:
            boolean r2 = r5.isHeightValid()
            if (r2 == 0) goto L37
            java.lang.String r0 = r3.TAG
            java.lang.String r2 = "updateUser height"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            int r0 = r5.getHeight()
            r4.setHeight(r0)
            r0 = r1
        L37:
            boolean r2 = r5.isGenderValid()
            if (r2 == 0) goto L51
            java.lang.String r0 = r3.TAG
            java.lang.String r2 = "updateUser gender"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            int r0 = r5.getGender()
            r4.setGender(r0)
            goto L52
        L51:
            r1 = r0
        L52:
            boolean r0 = r5.isBirthdayValid()
            if (r0 == 0) goto L6c
            java.lang.String r0 = r3.TAG
            java.lang.String r1 = "updateUser birthday"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)
            int r0 = r5.getBirthday()
            r4.setBirthday(r0)
            goto L6e
        L6c:
            if (r1 == 0) goto L78
        L6e:
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r4.setModifiedIntent(r0)
            r0 = 1073741824(0x40000000, float:2.0)
            r4.setUser(r0)
        L78:
            java.lang.String r0 = r3.TAG
            java.lang.String r4 = com.huawei.hihealth.util.HiJsonUtil.e(r4)
            java.lang.String r1 = ", new="
            java.lang.String r5 = com.huawei.hihealth.util.HiJsonUtil.e(r5)
            java.lang.String r2 = "afterUpdate old="
            java.lang.Object[] r4 = new java.lang.Object[]{r2, r4, r1, r5}
            com.huawei.hwlogsmodel.LogUtil.c(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.h5pro.jsmodules.InnerApi.updateUser(com.huawei.hihealth.HiUserInfo, com.huawei.hihealth.HiUserInfo):void");
    }

    @JavascriptInterface
    public void getShareConfig(long j) {
        LogUtil.a(this.TAG, "getShareConfig");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isShowUserInfo", ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).isShowUserInfo());
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getShareConfig: exception -> " + e.getMessage());
        }
        onSuccessCallback(j, jSONObject);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        jeg.d().d(strArr, iArr);
    }

    @JavascriptInterface
    public void getAppClientInfo(long j) {
        LogUtil.a(this.TAG, "getAppClientInfo");
        JSONObject jSONObject = new JSONObject();
        try {
            LoginInit loginInit = LoginInit.getInstance(this.mContext);
            jSONObject.put("language", getLanguage());
            jSONObject.put(CardMgrSdkConst.CardInfoDesc.PARAMS_LANGUAGE_TAG, getLanguageTag());
            jSONObject.put("appId", BaseApplication.d());
            jSONObject.put("appType", "1");
            jSONObject.put("sysVersion", Build.VERSION.RELEASE);
            jSONObject.put("timeZone", HiDateUtil.d(""));
            jSONObject.put(FaqConstants.FAQ_EMUIVERSION, CommonUtil.r());
            jSONObject.put("firmware", Build.DISPLAY);
            jSONObject.put("model", Build.MODEL);
            jSONObject.put("deviceId", loginInit.getDeviceId());
            jSONObject.put("appVersion", CommonUtil.c(BaseApplication.e()));
            jSONObject.put(CloudParamKeys.CLIENT_TYPE, eil.a());
            jSONObject.put(QuestionSurveyRequestParam.CVERISON, CommonUtil.d(BaseApplication.e()));
            jSONObject.put("os", Agent.OS_NAME + Build.VERSION.RELEASE);
            jSONObject.put("isMobileAppEngine", EnvironmentInfo.k());
            onSuccessCallback(j, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(this.TAG, "getAppClientInfo: exception -> " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void showToast(long j, String str) {
        LogUtil.a(this.TAG, "showToast enter: " + str);
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, ErrorEnum.REQ_PARAM_EMPTY.getMsg());
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(Constant.TEXT, "");
            boolean optBoolean = jSONObject.optBoolean("isShowLong", false);
            if (TextUtils.isEmpty(optString)) {
                onFailureCallback(j, "text is null");
                return;
            }
            if (optBoolean) {
                nrh.c(BaseApplication.e(), optString);
            } else {
                nrh.d(BaseApplication.e(), optString);
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("result", 0);
                onSuccessCallback(j, jSONObject2);
            } catch (JSONException e) {
                onFailureCallback(j, ExceptionUtils.d(e));
            }
        } catch (JSONException e2) {
            String d = ExceptionUtils.d(e2);
            ReleaseLogUtil.c(this.TAG, "showToast exception ", d);
            onFailureCallback(j, d);
        }
    }

    @JavascriptInterface
    public void userVisitLastestTime(long j, String str) {
        LogUtil.a(this.TAG, "userVisitLatestTime enter");
        eii.i(str);
        onSuccessCallback(j, "success");
    }
}
