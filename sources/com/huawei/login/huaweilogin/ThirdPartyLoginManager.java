package com.huawei.login.huaweilogin;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.common.Constant;
import com.huawei.common.OpAnalyticsApi;
import com.huawei.common.util.Utils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.BuildConfig;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwidauth.api.Result;
import com.huawei.hwidauth.api.ResultCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.login.third.ThirdPartyHttpUtils;
import com.huawei.login.third.ThirdPartyLoginInfo;
import com.huawei.login.third.UserAccountInfo;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ContentProviderUtil;
import com.huawei.login.ui.login.util.LoginCache;
import com.huawei.login.ui.login.util.ScopeManager;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.koq;
import defpackage.kqc;
import defpackage.kqg;
import defpackage.kqi;
import defpackage.kqj;
import defpackage.kqk;
import defpackage.kql;
import defpackage.kqm;
import defpackage.kqn;
import defpackage.kqo;
import defpackage.kqq;
import defpackage.kqr;
import defpackage.kqs;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.EzPluginManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/* loaded from: classes.dex */
public class ThirdPartyLoginManager {
    private static final int ACCESS_TOKEN_INVALID_RESULT_CODE = 20020002;
    private static final String DEVICE_UUID = "device_uuid";
    private static final int ERROR_CODE = -1;
    private static final String HEALTH_APP_PROBABILITY_PROBLEM = "85070032";
    private static final String KEY_WORDS = "keyWords";
    private static final Byte[] LOCK = new Byte[1];
    private static final String MESSAGES = "messages";
    private static final String OBTAIN_AT_URL = "/commonAbility/userAccessToken/obtain";
    private static final String OPTIONAL_SCAN_LOGIN = "0";
    private static final String OP_ALARM_PERMISSION_KEY = "opAlarmPermissionKey";
    private static final String QUERY_INFO_URL = "/commonAbility/userAccessToken/query";
    private static final String QUERY_USER_ACCOUNT_URL = "/commonAbility/userAccessToken/queryUserAccount";
    private static final String REFRESH_AT_URL = "/commonAbility/userAccessToken/refresh";
    private static final int REFRESH_TOKEN_INVALID_RESULT_CODE = 20020003;
    private static final String RELEASE_TAG = "R_UIDV_3rdPtyLoginMgr";
    private static final String REQ_CLIENT_TYPE = "7";
    private static final int SUCCESS = 0;
    private static final String TAG = "UIDV_3rdPtyLoginMgr";
    private static volatile ThirdPartyLoginManager sInstance;
    private Context mContext = BaseApplication.getContext();
    private HiUserInfo mUserInfo;

    private ThirdPartyLoginManager() {
    }

    public static ThirdPartyLoginManager getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new ThirdPartyLoginManager();
                }
            }
        }
        return sInstance;
    }

    public void thirdPartyPhoneLogin(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "enter thirdPartPhoneLogin");
        if (!(context instanceof Activity)) {
            LogUtil.b(TAG, "thirdPartyPhoneLogin: context is not instanceof Activity");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        String d = kqj.d("", getDeviceUuid(), "", "");
        String[] scopeList = ScopeManager.getScopeList();
        LogUtil.a(TAG, "start hms lite sdk login");
        Utils.initGrsOld(Utils.getCountryCode(), context);
        try {
            new kql.c((Activity) context).a(String.valueOf(BuildConfig.HMS_APPLICATION_ID)).c("hms://redirect_url").a(scopeList).d(d).b("").a(new ResultCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager$$ExternalSyntheticLambda3
                @Override // com.huawei.hwidauth.api.ResultCallBack
                public final void onResult(Result result) {
                    ThirdPartyLoginManager.this.m665x73b294ab(iBaseResponseCallback, (kqo) result);
                }
            }).c().e(AccountConstants.GRS_APP_NAME);
        } catch (kqk unused) {
            LogUtil.b(TAG, "ParmaInvalidException");
        }
    }

    /* renamed from: lambda$thirdPartyPhoneLogin$0$com-huawei-login-huaweilogin-ThirdPartyLoginManager, reason: not valid java name */
    /* synthetic */ void m665x73b294ab(IBaseResponseCallback iBaseResponseCallback, kqo kqoVar) {
        int i;
        if (kqoVar.getStatus().c()) {
            ReleaseLogUtil.e(RELEASE_TAG, "StatusCode:", Integer.valueOf(kqoVar.getStatus().e()), ", StatusMessage:", kqoVar.getStatus().a());
            String d = kqoVar.d();
            saveAuthCode(d);
            getAtByAuthCode(d, true, iBaseResponseCallback);
            return;
        }
        if (kqoVar.getStatus() != null) {
            i = kqoVar.getStatus().e();
            ReleaseLogUtil.d(RELEASE_TAG, "StatusCode:", Integer.valueOf(i), " StatusMessage:", kqoVar.getStatus().a());
        } else {
            i = -1;
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
        }
    }

    public void getAtByAuthCode(final String str, final boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "enter postToGetAccessToke.");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.1
            @Override // java.lang.Runnable
            public void run() {
                ThirdPartyHttpUtils.postToGetAccessToken(ThirdPartyLoginManager.this.getSyncUrl() + ThirdPartyLoginManager.OBTAIN_AT_URL, str, new ThirdPartyHttpUtils.RequestCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.1.1
                    @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                    public void onSuccess(String str2) {
                        ReleaseLogUtil.e(ThirdPartyLoginManager.TAG, "postToGetAT onSuccess:");
                        if (z) {
                            ThirdLoginDataStorageUtil.setLogoutFlag(false);
                        }
                        ThirdPartyLoginManager.this.saveThirdPartyLoginInfo(str2, false, iBaseResponseCallback);
                    }

                    @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                    public void onFailed(int i) {
                        iBaseResponseCallback.d(-1, null);
                        ReleaseLogUtil.d(ThirdPartyLoginManager.TAG, "postToGetAT onFailed:", Integer.valueOf(i));
                    }
                });
            }
        });
    }

    private String getDeviceUuid() {
        String b = SharedPreferenceManager.b(this.mContext, String.valueOf(20000), DEVICE_UUID);
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String generateDeviceUuid = generateDeviceUuid();
        SharedPreferenceManager.e(this.mContext, String.valueOf(20000), DEVICE_UUID, generateDeviceUuid, new StorageParams(0));
        return generateDeviceUuid;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveThirdPartyLoginInfo(String str, boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        ThirdPartyLoginInfo thirdPartyLoginInfo;
        LogUtil.a(TAG, "enter saveThirdPartLoginInfo");
        try {
            thirdPartyLoginInfo = (ThirdPartyLoginInfo) new Gson().fromJson(CommonUtil.z(str), ThirdPartyLoginInfo.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "JsonSyntaxException:", e.getMessage());
            iBaseResponseCallback.d(-1, null);
            thirdPartyLoginInfo = null;
        }
        if (thirdPartyLoginInfo == null) {
            return;
        }
        int resultCode = thirdPartyLoginInfo.getResultCode();
        LogUtil.a(TAG, "saveThirdPartyLoginInfo resultCode = ", Integer.valueOf(resultCode));
        if (resultCode == 0) {
            if (z) {
                saveFreshTokenData(thirdPartyLoginInfo, iBaseResponseCallback);
                return;
            } else {
                saveFreshTokenData(thirdPartyLoginInfo, null);
                queryUserInfo(new IBaseResponseCallback() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a(ThirdPartyLoginManager.TAG, "queryUserInfo errorCode = ", Integer.valueOf(i));
                        iBaseResponseCallback.d(i, null);
                    }
                }, true);
                return;
            }
        }
        if (resultCode == REFRESH_TOKEN_INVALID_RESULT_CODE) {
            LogUtil.h(TAG, "resultCode == 20020003");
            gotoStTimeActivity();
            iBaseResponseCallback.d(-1, null);
        } else {
            LogUtil.h(TAG, "get accessToken failed");
            iBaseResponseCallback.d(-1, null);
        }
    }

    private void clearTokenInfo() {
        if (getLogoutStatus() && TextUtils.isEmpty(LoginInit.getInstance(this.mContext).getAccountInfo(1011))) {
            clearToken();
        } else {
            LogUtil.a(TAG, "clear tokeninfo logoutstatus is false");
        }
    }

    private void clearToken() {
        LogUtil.a(TAG, "clearToken");
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this.mContext);
        sharedPreferenceUtil.setRefreshTicket(null);
        sharedPreferenceUtil.setAtExpireTime(null);
        sharedPreferenceUtil.setRtExpireTime(null);
        sharedPreferenceUtil.setUserID("");
        ThirdLoginDataStorageUtil.saveAccessTokenToDb(null);
        ThirdLoginDataStorageUtil.saveRefreshTokenToDb(null);
        ThirdLoginDataStorageUtil.saveAccessTokenExpireTimeToDb(null);
        ThirdLoginDataStorageUtil.saveRefreshTokenExpireTimeToDb(null);
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("hmslite", 0).edit();
        edit.putBoolean("use-status", false);
        edit.commit();
    }

    private void saveAuthCode(String str) {
        LogUtil.a(TAG, "enter saveAuthCode");
        SharedPreferenceUtil.getInstance(BaseApplication.getContext()).setAuthCode(str);
        LogUtil.a(TAG, "end saveAuthCode");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSyncUrl() {
        return getSyncUrl(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSyncUrl(boolean z) {
        GrsClient initGrs = Utils.initGrs(Utils.getCountryCode(), this.mContext);
        if (initGrs == null) {
            LogUtil.h(TAG, "get SyncUrl mContext is null");
            return "";
        }
        if (CommonUtil.cc()) {
            return initGrs.synGetGrsUrl(AccountConstants.GRS_APP_NAME, "healthCloudUrl");
        }
        if (z) {
            return initGrs.synGetGrsUrl("com.huawei.health" + AccountConstants.SERVICE_SUFFIX, "domainNewHmsLiteHiCloud");
        }
        return initGrs.synGetGrsUrl("com.huawei.health" + AccountConstants.SERVICE_SUFFIX, "domainHmsLiteHiCloud");
    }

    public void saveUserLoginInfo(ThirdPartyLoginInfo thirdPartyLoginInfo, boolean z) {
        if (!z) {
            if (thirdPartyLoginInfo != null) {
                saveLoginSiteId(this.mContext, String.valueOf(thirdPartyLoginInfo.getSiteId()));
                setAccountType(thirdPartyLoginInfo, SharedPreferenceUtil.getInstance(this.mContext));
                return;
            }
            return;
        }
        LogUtil.a(TAG, "enter saveUserLoginInfo() ProcessName = ", EnvironmentUtils.d());
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this.mContext);
        if (!TextUtils.isEmpty(thirdPartyLoginInfo.getUid())) {
            sharedPreferenceUtil.setUserID(thirdPartyLoginInfo.getUid());
        }
        ifNewSiteId(thirdPartyLoginInfo, sharedPreferenceUtil);
        HuaweiLoginManager.updateAppTypeBySiteID(thirdPartyLoginInfo.getSiteId());
        HuaweiLoginManager.updateLoginTypeByCountry(this.mContext, thirdPartyLoginInfo.getNationalCode());
        sharedPreferenceUtil.setHuaweiAccountLoginFlag("1");
        SharedPreferenceManager.e(this.mContext, Integer.toString(10015), "if_need_set_account_login_entry", "0", new StorageParams(0));
        sharedPreferenceUtil.setCountryCode(thirdPartyLoginInfo.getNationalCode());
        ContentProviderUtil.getInstance(this.mContext).setCountryCode(thirdPartyLoginInfo.getNationalCode(), null);
        queryUserAccount(null);
        saveLoginSiteId(this.mContext, String.valueOf(thirdPartyLoginInfo.getSiteId()));
        LoginInit.getInstance(this.mContext).setIsBrowseModeToPd(this.mContext, false);
        setAccountType(thirdPartyLoginInfo, sharedPreferenceUtil);
        sharedPreferenceUtil.setLoginType(0);
        BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
        if (browsingBiEvent != null) {
            browsingBiEvent.loginSuccessBiEvent();
        }
        LoginInit.getInstance(this.mContext).setIsLogined(true);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1007), "need_relogin", "false", (StorageParams) null);
        HuaweiLoginManager.informHiDataLoginSuccess(this.mContext);
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setAction("com.huawei.plugin.account.login");
        intent.putExtra(Constant.KEY_IS_REBOOT, !SharedPreferenceUtil.isSameCountryCode(thirdPartyLoginInfo.getNationalCode()));
        if (LocalBroadcastManager.getInstance(this.mContext) != null) {
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
        }
        Context context = this.mContext;
        if (context != null) {
            context.sendBroadcast(intent, LocalBroadcast.c);
        }
        ObserverManagerUtil.c("com.huawei.plugin.account.login", "com.huawei.plugin.account.login");
        EzPluginManager.a().c();
    }

    private void saveLoginSiteId(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || !CloudUtils.a()) {
            return;
        }
        LoginInit.getInstance(context).setAccountInfo(1009, str, null);
    }

    private void ifNewSiteId(ThirdPartyLoginInfo thirdPartyLoginInfo, SharedPreferenceUtil sharedPreferenceUtil) {
        if (thirdPartyLoginInfo.getSiteId() != sharedPreferenceUtil.getSiteID()) {
            LogConfig.c(this.mContext);
            SharedPreferenceManager.c(this.mContext, Integer.toString(10000), "grs_config_last_update_time");
            SharedPreferenceManager.e(this.mContext, Integer.toString(10000), "grs_config_delete", "true", (StorageParams) null);
        }
    }

    private void setAccountType(ThirdPartyLoginInfo thirdPartyLoginInfo, SharedPreferenceUtil sharedPreferenceUtil) {
        int ageGroupFlag = thirdPartyLoginInfo.getAgeGroupFlag();
        int b = CommonUtil.b(this.mContext, sharedPreferenceUtil.getAccountType(), -1);
        if (ageGroupFlag != b && (ageGroupFlag == 0 || ageGroupFlag == 1)) {
            LoginInit.getInstance(this.mContext).setAccountInfo(1004, "0", null);
        } else if (ageGroupFlag != b && ageGroupFlag == 2) {
            LoginInit.getInstance(this.mContext).setAccountInfo(1004, "1", null);
        } else {
            LogUtil.h(TAG, "loginInfo AgeGroupFlag unknown");
            LoginInit.getInstance(this.mContext).setAccountInfo(1004, "0", null);
        }
        LoginInit.getInstance(this.mContext).setAccountInfo(1019, String.valueOf(ageGroupFlag), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiUserInfo assembleUserInfo(ThirdPartyLoginInfo thirdPartyLoginInfo) {
        String srvNationalCode = thirdPartyLoginInfo.getSrvNationalCode();
        if (!TextUtils.isEmpty(srvNationalCode) && !srvNationalCode.equalsIgnoreCase(LoginCache.getSrvCountryCode())) {
            LogUtil.a(TAG, "saveUserLoginInfo() srvCountryCode= ", srvNationalCode);
            LoginCache.configSrvCountryCode(srvNationalCode);
            ContentProviderUtil.getInstance(this.mContext).setServiceCountryCode(srvNationalCode, null);
        }
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this.mContext);
        String guardianAccount = sharedPreferenceUtil.getGuardianAccount();
        String guardianAccount2 = thirdPartyLoginInfo.getGuardianAccount();
        if (!TextUtils.isEmpty(guardianAccount2) && !guardianAccount2.equalsIgnoreCase(guardianAccount)) {
            sharedPreferenceUtil.setGuardianAccount(guardianAccount2);
            sharedPreferenceUtil.setGuardianUid(thirdPartyLoginInfo.getGuardianUid());
        }
        HiUserInfo hiUserInfo = new HiUserInfo();
        if (thirdPartyLoginInfo.getGender() == -1) {
            hiUserInfo.setGender(-1);
        } else if (thirdPartyLoginInfo.getGender() == 2) {
            hiUserInfo.setGender(2);
        } else {
            hiUserInfo.setGender(thirdPartyLoginInfo.getGender() == 0 ? 1 : 0);
        }
        sharedPreferenceUtil.setGender(String.valueOf(thirdPartyLoginInfo.getGender()));
        hiUserInfo.setUser(1073741824);
        hiUserInfo.setModifiedIntent(268435456);
        hiUserInfo.setHeadImgUrl(thirdPartyLoginInfo.getHeadPictureUrl());
        hiUserInfo.setHuid(thirdPartyLoginInfo.getUid());
        if (TextUtils.isEmpty(thirdPartyLoginInfo.getNickName())) {
            hiUserInfo.setName(thirdPartyLoginInfo.getLoginUserName());
        } else {
            hiUserInfo.setName(thirdPartyLoginInfo.getNickName());
        }
        String birthDate = thirdPartyLoginInfo.getBirthDate();
        if (!TextUtils.isEmpty(birthDate)) {
            hiUserInfo.setBirthday(CommonUtil.h(birthDate));
            if (LoginInit.isBelowMinAge(birthDate)) {
                SharedPreferenceManager.e(this.mContext, Integer.toString(20000), "key_ui_age_less_minimum", String.valueOf(true), (StorageParams) null);
            }
            LoginInit.getInstance(this.mContext).setAccountInfo(1006, thirdPartyLoginInfo.getBirthDate(), null);
        }
        return hiUserInfo;
    }

    public void saveUserInfo() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.3
                @Override // java.lang.Runnable
                public void run() {
                    ThirdPartyLoginManager.this.saveUserInfo();
                }
            });
            return;
        }
        if (this.mUserInfo == null) {
            LogUtil.h(TAG, "saveUserInfo mUserInfo is null");
            return;
        }
        SharedPreferenceUtil.getInstance(this.mContext).setAccountName(this.mUserInfo.getName());
        KeyValDbManager.b(this.mContext).d("key_user_name", this.mUserInfo.getName(), null);
        Bitmap loadRemoteImage = ThirdLoginDataStorageUtil.loadRemoteImage(this.mUserInfo.getHeadImgUrl());
        KeyValDbManager.b(this.mContext).e("key_user_pic_path", this.mUserInfo.getHeadImgUrl() == null ? "" : this.mUserInfo.getHeadImgUrl());
        String saveProfileImage = ThirdLoginDataStorageUtil.saveProfileImage(this.mContext, this.mUserInfo.getHuid(), loadRemoteImage);
        SharedPreferenceManager.e(this.mContext, String.valueOf(PrebakedEffectId.RT_FLY), "key_user_pic_path", saveProfileImage, new StorageParams(1));
        if (!"1".equalsIgnoreCase(CommonUtil.v())) {
            setUserInfoNoCloud(this.mUserInfo.getName(), saveProfileImage);
            LogUtil.a(TAG, "no cloud, do not use account birthday and gender.");
        } else {
            HiHealthNativeApi.a(this.mContext).setUserData(this.mUserInfo, new HiCommonListener() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.4
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    LogUtil.a(ThirdPartyLoginManager.TAG, "saveUserInfo onSuccess");
                    LocalBroadcastManager.getInstance(ThirdPartyLoginManager.this.mContext).sendBroadcast(new Intent("com.huawei.bone.action.FITNESS_USERINFO_UPDATED"));
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    LogUtil.h(ThirdPartyLoginManager.TAG, "setUserData onFailure:", Integer.valueOf(i), " ", obj);
                }
            });
        }
    }

    private void setUserInfoNoCloud(final String str, final String str2) {
        LogUtil.a(TAG, "setUserNameNoCloud: no cloud, save user info");
        HiHealthNativeApi.a(this.mContext).fetchUserData(new HiCommonListener() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.5
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a(ThirdPartyLoginManager.TAG, "setUserNameNoCloud: get user data success");
                if (obj instanceof List) {
                    List list = (List) obj;
                    if (koq.b(list)) {
                        return;
                    }
                    LogUtil.a(ThirdPartyLoginManager.TAG, "setUserNameNoCloud: user data not null");
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    hiUserInfo.setName(str);
                    hiUserInfo.setHeadImgUrl(str2);
                    hiUserInfo.setUser(1073741824);
                    hiUserInfo.setModifiedIntent(268435456);
                    HiHealthNativeApi.a(ThirdPartyLoginManager.this.mContext).setUserData(hiUserInfo, new HiCommonListener() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.5.1
                        @Override // com.huawei.hihealth.data.listener.HiCommonListener
                        public void onSuccess(int i2, Object obj2) {
                            LogUtil.a(ThirdPartyLoginManager.TAG, "setUserNameNoCloud: setUserName onSuccess");
                            LocalBroadcastManager.getInstance(ThirdPartyLoginManager.this.mContext).sendBroadcast(new Intent("com.huawei.bone.action.FITNESS_USERINFO_UPDATED"));
                        }

                        @Override // com.huawei.hihealth.data.listener.HiCommonListener
                        public void onFailure(int i2, Object obj2) {
                            LogUtil.h(ThirdPartyLoginManager.TAG, "setUserName onFailure:", Integer.valueOf(i2), " ", obj2);
                        }
                    });
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h(ThirdPartyLoginManager.TAG, "get user data errCode = ", Integer.valueOf(i), " errMsg = ", obj);
            }
        });
    }

    public void refreshAtByRt(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "enter refreshAtByRt");
        final String refreshTokenFromDb = ThirdLoginDataStorageUtil.getRefreshTokenFromDb();
        final String e = KeyValDbManager.b(this.mContext).e("user_id");
        if (TextUtils.isEmpty(refreshTokenFromDb) || TextUtils.isEmpty(e)) {
            ReleaseLogUtil.d(TAG, "refreshAtByRt rt= ", Boolean.valueOf(TextUtils.isEmpty(refreshTokenFromDb)), "uid= ", Boolean.valueOf(TextUtils.isEmpty(e)));
            iBaseResponseCallback.d(-1, null);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.6
                @Override // java.lang.Runnable
                public void run() {
                    ThirdPartyHttpUtils.refreshRt(ThirdPartyLoginManager.this.getSyncUrl(true) + ThirdPartyLoginManager.REFRESH_AT_URL, refreshTokenFromDb, e, new ThirdPartyHttpUtils.RequestCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.6.1
                        @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                        public void onSuccess(String str) {
                            ReleaseLogUtil.e(ThirdPartyLoginManager.TAG, "refreshAtByRT onSuccess");
                            ThirdPartyLoginManager.this.saveThirdPartyLoginInfo(str, true, iBaseResponseCallback);
                        }

                        @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                        public void onFailed(int i) {
                            long g = CommonUtil.g(ThirdLoginDataStorageUtil.getAccessTokenExpireTimeFromDb());
                            long g2 = CommonUtil.g(ThirdLoginDataStorageUtil.getServerTimestamp());
                            ThirdPartyLoginManager.this.timedRefreshAt(System.currentTimeMillis() + Math.max((g - g2) - HealthAccessTokenUtil.getAtInstance().getRandomUpdateTime(), 0L));
                            if (iBaseResponseCallback != null) {
                                iBaseResponseCallback.d(i, null);
                            }
                            ReleaseLogUtil.d(ThirdPartyLoginManager.TAG, "refreshAtByRT onFailed:", Integer.valueOf(i));
                        }
                    });
                }
            });
        }
    }

    public void queryUserInfo(final IBaseResponseCallback iBaseResponseCallback, final boolean z) {
        LogUtil.a(TAG, "enter queryUserInfo");
        final String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1015);
        final String userID = SharedPreferenceUtil.getInstance(this.mContext).getUserID();
        if (TextUtils.isEmpty(accountInfo) || TextUtils.isEmpty(userID)) {
            ReleaseLogUtil.d(RELEASE_TAG, "queryUserInfo param is empty", Boolean.valueOf(TextUtils.isEmpty(accountInfo)), Boolean.valueOf(TextUtils.isEmpty(userID)));
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.7
            @Override // java.lang.Runnable
            public void run() {
                ThirdPartyHttpUtils.queryUserData(ThirdPartyLoginManager.this.getSyncUrl() + ThirdPartyLoginManager.QUERY_INFO_URL, accountInfo, userID, new ThirdPartyHttpUtils.RequestCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.7.1
                    @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                    public void onSuccess(String str) {
                        ReleaseLogUtil.e(ThirdPartyLoginManager.RELEASE_TAG, "queryUserInfo onSuccess");
                        LogUtil.c(ThirdPartyLoginManager.TAG, "queryUserInfo result:", str);
                        ThirdPartyLoginInfo thirdPartyLoginInfo = new ThirdPartyLoginInfo();
                        try {
                            thirdPartyLoginInfo = (ThirdPartyLoginInfo) new Gson().fromJson(CommonUtil.z(str), ThirdPartyLoginInfo.class);
                        } catch (JsonSyntaxException e) {
                            LogUtil.b(ThirdPartyLoginManager.TAG, "JsonSyntaxException:", e.getMessage());
                        }
                        if (!ThirdPartyLoginManager.this.isNeedSaveUserInfo(thirdPartyLoginInfo)) {
                            if (iBaseResponseCallback != null) {
                                iBaseResponseCallback.d(-1, null);
                            }
                            ThirdPartyLoginManager.this.refreshAtWhenExpire(thirdPartyLoginInfo);
                            return;
                        }
                        ThirdPartyLoginManager.this.saveUserLoginInfo(thirdPartyLoginInfo, z);
                        ThirdPartyLoginManager.this.mUserInfo = ThirdPartyLoginManager.this.assembleUserInfo(thirdPartyLoginInfo);
                        if (!z) {
                            ThirdPartyLoginManager.this.saveUserInfo();
                        }
                        if (iBaseResponseCallback != null) {
                            iBaseResponseCallback.d(0, ThirdPartyLoginManager.this.mUserInfo);
                        }
                    }

                    @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                    public void onFailed(int i) {
                        ReleaseLogUtil.d(ThirdPartyLoginManager.RELEASE_TAG, "queryUserInfo onFailed");
                        if (iBaseResponseCallback != null) {
                            iBaseResponseCallback.d(i, null);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshAtWhenExpire(ThirdPartyLoginInfo thirdPartyLoginInfo) {
        if (thirdPartyLoginInfo == null || thirdPartyLoginInfo.getResultCode() != ACCESS_TOKEN_INVALID_RESULT_CODE) {
            return;
        }
        ReleaseLogUtil.e(TAG, "refreshAtWhenExpire refresh at");
        LoginInit.getInstance(BaseApplication.getContext()).refreshAccessToken(null);
    }

    public void queryUserAccount(final IBaseResponseCallback iBaseResponseCallback) {
        final String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1015);
        final String userID = SharedPreferenceUtil.getInstance(this.mContext).getUserID();
        if (TextUtils.isEmpty(accountInfo) || TextUtils.isEmpty(userID)) {
            LogUtil.h(TAG, "queryUserAccount accessToken or uid is empty");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.8
            @Override // java.lang.Runnable
            public void run() {
                ThirdPartyHttpUtils.queryUserAccount(ThirdPartyLoginManager.this.getSyncUrl() + ThirdPartyLoginManager.QUERY_USER_ACCOUNT_URL, accountInfo, userID, new ThirdPartyHttpUtils.RequestCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.8.1
                    @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                    public void onSuccess(String str) {
                        ReleaseLogUtil.e(ThirdPartyLoginManager.RELEASE_TAG, "queryUserAccount onSuccess");
                        LogUtil.c(ThirdPartyLoginManager.TAG, "queryUserAccount result：", str);
                        String str2 = "";
                        try {
                            ThirdPartyLoginInfo thirdPartyLoginInfo = (ThirdPartyLoginInfo) new Gson().fromJson(CommonUtil.z(str), ThirdPartyLoginInfo.class);
                            str2 = thirdPartyLoginInfo.getUserAccount();
                            if (!TextUtils.isEmpty(str2)) {
                                LogUtil.a(ThirdPartyLoginManager.TAG, "getPlainTextAccountName success");
                                SharedPreferenceUtil.getInstance(ThirdPartyLoginManager.this.mContext).setPlainTextAccountName(str2);
                            }
                            if (thirdPartyLoginInfo.getUserAccountInfo() != null) {
                                LogUtil.a(ThirdPartyLoginManager.TAG, "getUserAccountInfo success");
                                UserAccountInfo userAccountInfo = thirdPartyLoginInfo.getUserAccountInfo();
                                SharedPreferenceUtil.getInstance(ThirdPartyLoginManager.this.mContext).setMobile(userAccountInfo.getMobile());
                                SharedPreferenceUtil.getInstance(ThirdPartyLoginManager.this.mContext).setEmail(userAccountInfo.getEmail());
                            }
                        } catch (JsonSyntaxException e) {
                            LogUtil.b(ThirdPartyLoginManager.TAG, "JsonSyntaxException:", e.getMessage());
                        }
                        if (iBaseResponseCallback != null) {
                            iBaseResponseCallback.d(0, str2);
                        }
                    }

                    @Override // com.huawei.login.third.ThirdPartyHttpUtils.RequestCallBack
                    public void onFailed(int i) {
                        ReleaseLogUtil.d(ThirdPartyLoginManager.RELEASE_TAG, "queryUserAccount failed: ", Integer.valueOf(i));
                        if (iBaseResponseCallback != null) {
                            iBaseResponseCallback.d(i, null);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNeedSaveUserInfo(ThirdPartyLoginInfo thirdPartyLoginInfo) {
        if (thirdPartyLoginInfo == null) {
            LogUtil.h(TAG, "queryUserInfo onFailed: userLoginInfo is null.");
            return false;
        }
        if (thirdPartyLoginInfo.getResultCode() == 0) {
            return true;
        }
        LogUtil.h(TAG, "queryUserInfo onFailed: resultCode is ", Integer.valueOf(thirdPartyLoginInfo.getResultCode()));
        return false;
    }

    public void gotoStTimeActivity() {
        try {
            LogUtil.a(TAG, "gotoStTimeActivity.");
            if (!CommonUtil.x(this.mContext) && SharedPreferenceUtil.getInstance(this.mContext).getIsLogined()) {
                LoginInit.getInstance(this.mContext).logoutWhenTokenInvalid(null);
                Intent intent = new Intent();
                intent.setFlags(268435456);
                intent.setClassName(this.mContext, "com.huawei.health.StTimeoutActivity");
                this.mContext.startActivity(intent);
                return;
            }
            LogUtil.a(TAG, "gotoStTimeActivity onBackground or logout.");
        } catch (ActivityNotFoundException e) {
            LogUtil.b(TAG, "gotoStTimeActivity:", e.getMessage());
        }
    }

    public void openAccountManager(Activity activity) {
        LogUtil.a(TAG, "enter openAccountManager");
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1015);
        String d = kqj.d("", getDeviceUuid(), "", "");
        Utils.initGrsOld(Utils.getCountryCode(), BaseApplication.getContext());
        kqi.bQi_(activity, AccountConstants.GRS_APP_NAME, accountInfo, d, "7", new ResultCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager$$ExternalSyntheticLambda4
            @Override // com.huawei.hwidauth.api.ResultCallBack
            public final void onResult(Result result) {
                ThirdPartyLoginManager.lambda$openAccountManager$1((kqq) result);
            }
        });
        LogUtil.a(TAG, "end openAccountManager");
    }

    static /* synthetic */ void lambda$openAccountManager$1(kqq kqqVar) {
        if (kqqVar == null || !kqqVar.getStatus().c()) {
            return;
        }
        LogUtil.a(TAG, "SignOutResult:", kqqVar.getStatus().a());
        ThirdLoginDataStorageUtil.setLogoutFlag(true);
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setAction(Action.ACTION_HW_ACCOUNT_LOGOUT);
        BaseApplication.getContext().sendBroadcast(intent, SecurityConstant.b);
    }

    public void openPersonalInfo(Activity activity, final IBaseResponseCallback iBaseResponseCallback) {
        if (activity == null) {
            LogUtil.h(TAG, "openPersonalInfo activity is null");
            return;
        }
        LogUtil.a(TAG, "enter openPersonalInfo.");
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1015);
        String d = kqj.d("", getDeviceUuid(), "", "");
        Utils.initGrsOld(Utils.getCountryCode(), BaseApplication.getContext());
        kqi.bQj_(activity, AccountConstants.GRS_APP_NAME, accountInfo, d, new ResultCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager$$ExternalSyntheticLambda2
            @Override // com.huawei.hwidauth.api.ResultCallBack
            public final void onResult(Result result) {
                ThirdPartyLoginManager.lambda$openPersonalInfo$2(IBaseResponseCallback.this, (kqs) result);
            }
        });
    }

    static /* synthetic */ void lambda$openPersonalInfo$2(IBaseResponseCallback iBaseResponseCallback, kqs kqsVar) {
        if (kqsVar == null || iBaseResponseCallback == null) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        kqm status = kqsVar.getStatus();
        if (status == null) {
            iBaseResponseCallback.d(-1, null);
        } else if (status.c()) {
            iBaseResponseCallback.d(0, null);
        } else {
            LogUtil.a(TAG, "openPersonalInfo:", Integer.valueOf(status.e()));
            iBaseResponseCallback.d(-1, null);
        }
    }

    public void checkUserPassword(Activity activity, String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (activity == null) {
            LogUtil.h(TAG, "chkUserPassword activity is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        LogUtil.a(TAG, "enter checkUserPassword");
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1015);
        String deviceUuid = getDeviceUuid();
        if (TextUtils.isEmpty(accountInfo) || TextUtils.isEmpty(deviceUuid)) {
            LogUtil.h(TAG, "chkUserPassword accessToken is null");
            iBaseResponseCallback.d(-1, null);
        } else {
            String d = kqj.d("", deviceUuid, "", "");
            Utils.initGrsOld(Utils.getCountryCode(), BaseApplication.getContext());
            kqi.bQg_(activity, AccountConstants.GRS_APP_NAME, accountInfo, str, d, new ResultCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager$$ExternalSyntheticLambda1
                @Override // com.huawei.hwidauth.api.ResultCallBack
                public final void onResult(Result result) {
                    ThirdPartyLoginManager.lambda$checkUserPassword$3(IBaseResponseCallback.this, (kqc) result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$checkUserPassword$3(IBaseResponseCallback iBaseResponseCallback, kqc kqcVar) {
        if (kqcVar.getStatus().c()) {
            LogUtil.a(TAG, "check success");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            }
            return;
        }
        int e = kqcVar.getStatus().e();
        LogUtil.h(TAG, "check errorCode:", Integer.valueOf(e), "StatusMessage:", kqcVar.getStatus().a());
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(e, null);
        }
    }

    public void signOut() {
        LogUtil.a(TAG, "enter signOut");
        try {
            Utils.initGrsOld(Utils.getCountryCode(), BaseApplication.getContext());
            kqi.b(this.mContext, new ResultCallBack<kqg>() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.9
                @Override // com.huawei.hwidauth.api.ResultCallBack
                public void onResult(kqg kqgVar) {
                    if (kqgVar == null || !kqgVar.getStatus().c()) {
                        return;
                    }
                    ReleaseLogUtil.e(ThirdPartyLoginManager.TAG, "SignOutResult:", kqgVar.getStatus().a());
                }
            });
        } catch (kqk unused) {
            ReleaseLogUtil.c(TAG, "signOut ParmaInvalidException ");
        }
    }

    private String generateDeviceUuid() {
        return UUID.randomUUID().toString().replace(Constants.LINK, "");
    }

    public void immediatelyFreshAt() {
        LogUtil.a(TAG, "enter immediatelyFreshAt");
        if (TextUtils.isEmpty(KeyValDbManager.b(this.mContext).e("user_id")) || CommonUtil.bh()) {
            LogUtil.h(TAG, "immediatelyFreshAt isLogout");
        } else {
            refreshAtByRt(new IBaseResponseCallback() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a(ThirdPartyLoginManager.TAG, "immediatelyFreshAt errorCode = ", Integer.valueOf(i), " objData = ", obj);
                }
            });
        }
    }

    public void timedRefreshAt(final long j) {
        LogUtil.a(TAG, "enter timedRefreshAt");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager.11
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(KeyValDbManager.b(ThirdPartyLoginManager.this.mContext).e("user_id"))) {
                    LogUtil.h(ThirdPartyLoginManager.TAG, "timedRefreshAt isLogout");
                    return;
                }
                Object systemService = BaseApplication.getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
                if (!(systemService instanceof AlarmManager)) {
                    LogUtil.h(ThirdPartyLoginManager.TAG, "object is not instance of AlarmManager");
                    return;
                }
                AlarmManager alarmManager = (AlarmManager) systemService;
                PendingIntent creatorPendingIntent = ThirdPartyLoginManager.this.creatorPendingIntent();
                alarmManager.cancel(creatorPendingIntent);
                try {
                    if (PermissionUtil.b()) {
                        alarmManager.setExactAndAllowWhileIdle(0, j, creatorPendingIntent);
                    } else {
                        alarmManager.setAndAllowWhileIdle(0, j, creatorPendingIntent);
                    }
                } catch (SecurityException e) {
                    String str = "setExactAndAllowWhileIdle exception " + e.getMessage();
                    ReleaseLogUtil.c(ThirdPartyLoginManager.TAG, "setExactAndAllowWhileIdle exception ", str);
                    ThirdPartyLoginManager.this.reportErrorCode(str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportErrorCode(String str) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("keyWords", "opAlarmPermissionKey");
        linkedHashMap.put("messages", str);
        OpAnalyticsApi opAnalyticsApi = LoginInit.getInstance(BaseApplication.getContext()).getOpAnalyticsApi();
        if (opAnalyticsApi != null) {
            opAnalyticsApi.reportErrorCode(HEALTH_APP_PROBABILITY_PROBLEM, linkedHashMap);
        }
    }

    private void saveFreshTokenData(ThirdPartyLoginInfo thirdPartyLoginInfo, IBaseResponseCallback iBaseResponseCallback) {
        if (thirdPartyLoginInfo == null) {
            LogUtil.h(TAG, "saveFreshTokenData loginInfo is null");
            return;
        }
        LogUtil.a(TAG, "saveFreshTokenData accesstoken= ", thirdPartyLoginInfo.getAccessToken());
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this.mContext);
        if (!TextUtils.isEmpty(thirdPartyLoginInfo.getUid())) {
            sharedPreferenceUtil.setUserID(thirdPartyLoginInfo.getUid());
            ThirdLoginDataStorageUtil.setUserId(thirdPartyLoginInfo.getUid());
        }
        ThirdLoginDataStorageUtil.saveServerTimestamp(String.valueOf(thirdPartyLoginInfo.getTimeStamp()));
        if (!TextUtils.isEmpty(thirdPartyLoginInfo.getAccessToken())) {
            ThirdLoginDataStorageUtil.saveAccessTokenToDb(thirdPartyLoginInfo.getAccessToken());
        }
        if (!TextUtils.isEmpty(thirdPartyLoginInfo.getAccessTokenExpireTime())) {
            sharedPreferenceUtil.setAtExpireTime(thirdPartyLoginInfo.getAccessTokenExpireTime());
            ThirdLoginDataStorageUtil.saveAccessTokenExpireTimeToDb(thirdPartyLoginInfo.getAccessTokenExpireTime());
        }
        long g = CommonUtil.g(thirdPartyLoginInfo.getAccessTokenExpireTime());
        long timeStamp = thirdPartyLoginInfo.getTimeStamp();
        timedRefreshAt(System.currentTimeMillis() + Math.max((g - timeStamp) - HealthAccessTokenUtil.getAtInstance().getRandomUpdateTime(), 0L));
        String refreshTokenFromDb = ThirdLoginDataStorageUtil.getRefreshTokenFromDb();
        LogUtil.a(TAG, "saveFreshTokenData lastRtValue = ", refreshTokenFromDb);
        if (!TextUtils.isEmpty(thirdPartyLoginInfo.getRefreshToken())) {
            sharedPreferenceUtil.setRefreshTicket(thirdPartyLoginInfo.getRefreshToken());
            ThirdLoginDataStorageUtil.saveRefreshTokenToDb(thirdPartyLoginInfo.getRefreshToken());
        }
        if (thirdPartyLoginInfo.getRefreshToken() != null && !thirdPartyLoginInfo.getRefreshToken().equals(refreshTokenFromDb)) {
            long timeStamp2 = thirdPartyLoginInfo.getTimeStamp() + ThirdLoginDataStorageUtil.REFRESH_TOKEN_INTERVAL;
            sharedPreferenceUtil.setRtExpireTime(String.valueOf(timeStamp2));
            ThirdLoginDataStorageUtil.saveRefreshTokenExpireTimeToDb(String.valueOf(timeStamp2));
        }
        clearTokenInfo();
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, thirdPartyLoginInfo.getAccessToken());
        }
        sharedPreferenceUtil.retrySetIsLogined();
    }

    public boolean getLogoutStatus() {
        String logoutFlag = ThirdLoginDataStorageUtil.getLogoutFlag();
        if (TextUtils.isEmpty(logoutFlag)) {
            return true;
        }
        try {
            return Boolean.parseBoolean(logoutFlag);
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, "getLogoutStatus", ExceptionUtils.d(e));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PendingIntent creatorPendingIntent() {
        LogUtil.a(TAG, "enter creatorPendingIntent");
        Intent intent = new Intent();
        Context context = BaseApplication.getContext();
        String packageName = context.getPackageName();
        intent.setPackage(packageName);
        intent.setClassName(packageName, "com.huawei.health.manager.DaemonService");
        intent.setAction(ThirdLoginDataStorageUtil.START_DAEMON_FOR_REFRESH_TOKEN);
        PowerKitManager.e().e(Collections.singletonList(ThirdLoginDataStorageUtil.START_DAEMON_FOR_REFRESH_TOKEN));
        return PendingIntent.getService(context, 0, intent, 201326592);
    }

    public boolean isRefreshTokenOverTime() {
        String rtExpireTime = SharedPreferenceUtil.getInstance(this.mContext).getRtExpireTime();
        return TextUtils.isEmpty(rtExpireTime) || CommonUtil.g(rtExpireTime) <= System.currentTimeMillis();
    }

    public void qrCodeAuthLogin(Activity activity, String str, int i, String str2, final IBaseResponseCallback iBaseResponseCallback) {
        if (activity == null || iBaseResponseCallback == null) {
            LogUtil.b(TAG, "qrCodeAuthLogin currentActivity or callback is null");
            return;
        }
        String accountInfo = LoginInit.getInstance(this.mContext).getAccountInfo(1015);
        LogUtil.a(TAG, "qrCodeAuthLogin accessToken is", Boolean.valueOf(TextUtils.isEmpty(accountInfo)));
        kqr kqrVar = new kqr(str, i, str2);
        String d = kqj.d("", getDeviceUuid(), "", "");
        Utils.initGrsOld(Utils.getCountryCode(), BaseApplication.getContext());
        kqi.bQk_(activity, AccountConstants.GRS_APP_NAME, accountInfo, kqrVar, d, new ResultCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager$$ExternalSyntheticLambda5
            @Override // com.huawei.hwidauth.api.ResultCallBack
            public final void onResult(Result result) {
                ThirdPartyLoginManager.lambda$qrCodeAuthLogin$4(IBaseResponseCallback.this, (kqn) result);
            }
        });
    }

    static /* synthetic */ void lambda$qrCodeAuthLogin$4(IBaseResponseCallback iBaseResponseCallback, kqn kqnVar) {
        if (kqnVar == null || kqnVar.getStatus() == null) {
            LogUtil.h(TAG, "qrCodeAuthLogin result.getStatus() is null");
            iBaseResponseCallback.d(-1, null);
        } else if (kqnVar.getStatus().c()) {
            LogUtil.a(TAG, "qrCodeAuthLogin isSuccess");
            iBaseResponseCallback.d(0, null);
        } else {
            int e = kqnVar.getStatus().e();
            LogUtil.h(TAG, "qrCodeAuthLogin fail:", Integer.valueOf(e));
            iBaseResponseCallback.d(e, null);
        }
    }

    public void qrCodeAuthorize(Activity activity, String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (activity == null || iBaseResponseCallback == null) {
            LogUtil.h(TAG, "qrCodeAuthorize currentActivity or callback is null");
        } else {
            Utils.initGrsOld(Utils.getCountryCode(), BaseApplication.getContext());
            kqi.bQl_(activity, AccountConstants.GRS_APP_NAME, "0", str, new ResultCallBack() { // from class: com.huawei.login.huaweilogin.ThirdPartyLoginManager$$ExternalSyntheticLambda0
                @Override // com.huawei.hwidauth.api.ResultCallBack
                public final void onResult(Result result) {
                    ThirdPartyLoginManager.lambda$qrCodeAuthorize$5(IBaseResponseCallback.this, (kqs) result);
                }
            });
        }
    }

    static /* synthetic */ void lambda$qrCodeAuthorize$5(IBaseResponseCallback iBaseResponseCallback, kqs kqsVar) {
        if (kqsVar == null || kqsVar.getStatus() == null) {
            LogUtil.h(TAG, "qrCodeAuthorize result.getStatus() is null");
            iBaseResponseCallback.d(-1, null);
        } else if (kqsVar.getStatus().c()) {
            LogUtil.a(TAG, "qrCodeAuthorize isSuccess");
            iBaseResponseCallback.d(0, null);
        } else {
            int e = kqsVar.getStatus().e();
            LogUtil.h(TAG, "qrCodeAuthorize fail:", Integer.valueOf(e));
            iBaseResponseCallback.d(e, null);
        }
    }
}
