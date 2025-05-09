package com.huawei.login.huaweilogin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.common.OpAnalyticsApi;
import com.huawei.common.util.Utils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiDataUpdateOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ILoginCallback;
import com.huawei.login.ui.login.util.LoginResult;
import com.huawei.login.ui.login.util.ScopeManager;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.StorageResult;
import health.compact.a.UnitUtil;
import health.compact.a.VersionDbManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class HuaweiLoginManager {
    private static final String ACCOUNT_SDK_API_ERROR_CODE = "80070011";
    private static final int DEFAULT_SCOPES_SIZES = 64;
    private static final Set<Integer> ERROR_CODE_NOT_RETRY_SET;
    private static final String KEY_API_NAME = "apiName";
    private static final String KEY_ERROR_CODE = "errorCode";
    private static final String PROVIDER_CONTENT = "content://com.huawei.hwid.api.provider/has_login";
    private static final String RELEASE_LOG_TAG = "R_PLGLOGIN_HwLoginMgr";
    private static final long SIGN_IN_INTERVAL = 10000;
    private static final String TAG = "PLGLOGIN_HwLoginMgr";
    private static final int TIME_OUT = 3;
    private static volatile boolean hasLogin;
    private static int lastSiteId;
    private static volatile long sSignInTimestamp;
    private static final AtomicInteger CALLBACK_INDEX = new AtomicInteger(-536870912);
    private static final Map<Integer, ILoginCallback> LOGIN_CALLBACK_MAP = new ConcurrentHashMap();

    static {
        HashSet hashSet = new HashSet();
        ERROR_CODE_NOT_RETRY_SET = hashSet;
        hasLogin = false;
        lastSiteId = -123;
        sSignInTimestamp = 0L;
        hashSet.add(2007);
        hashSet.add(2009);
    }

    private HuaweiLoginManager() {
    }

    public static HuaweiLoginManager getInstance() {
        return Instance.INSTANCE;
    }

    public static void informHiDataLoginSuccess(Context context) {
        LogUtil.a(TAG, "ST timeout but has login success again ");
        HiAccountInfo hiAccountInfo = new HiAccountInfo();
        LoginInit loginInit = LoginInit.getInstance(context);
        String accountInfo = loginInit.getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            return;
        }
        hiAccountInfo.setHuid(accountInfo);
        String accountInfo2 = loginInit.getAccountInfo(1015);
        hiAccountInfo.setAccessToken(accountInfo2);
        hiAccountInfo.setServiceToken(accountInfo2);
        hiAccountInfo.setSiteId(CommonUtil.m(context, loginInit.getAccountInfo(1009)));
        HiHealthNativeApi.a(context).hiLogin(hiAccountInfo, new HiCommonListener() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a(HuaweiLoginManager.TAG, "inform HiData login onSuccess.");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h(HuaweiLoginManager.TAG, "hiLogin failed errCode:", Integer.valueOf(i), "errorMsg:", obj);
            }
        });
    }

    public void logout() {
        AccountAuthManager.getService(BaseApplication.getContext(), new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setAuthorizationCode().createParams()).signOut().addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.4
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(Void r2) {
                ReleaseLogUtil.b(HuaweiLoginManager.RELEASE_LOG_TAG, "signOut success");
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.3
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ReleaseLogUtil.b(HuaweiLoginManager.RELEASE_LOG_TAG, "signOut fail");
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.2
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                ReleaseLogUtil.b(HuaweiLoginManager.RELEASE_LOG_TAG, "signOut complete");
            }
        });
    }

    public static boolean checkIsInstallHuaweiAccount(Context context) {
        LogUtil.a(TAG, "Enter checkIsInstallHuaweiAccount");
        if (CommonUtil.bu()) {
            LogUtil.a(TAG, "StoreDemo no check hms");
            return true;
        }
        try {
            boolean z = context.getPackageManager().getApplicationInfo(HMSPackageManager.getInstance(context).getHMSPackageName(), 128) != null;
            LogUtil.a(TAG, "checkIsInstallHuaweiAccount: ", Boolean.valueOf(z));
            return z;
        } catch (PackageManager.NameNotFoundException | SecurityException e) {
            LogUtil.h(TAG, "checkIsInstallHuaweiAccount ", e.getClass().getName());
            return false;
        }
    }

    public static boolean isHasLogin() {
        return hasLogin;
    }

    public static void setHasLogin(boolean z) {
        hasLogin = z;
    }

    public static boolean hasLoginAccount(final Context context) {
        LogUtil.a(TAG, "enter hasLoginAccount():");
        if (!checkIsInstallHuaweiAccount(context)) {
            LogUtil.a(TAG, "huid sdk is not exit ,return false.");
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.5
            /* JADX WARN: Code restructure failed: missing block: B:11:0x0048, code lost:
            
                if (r10 != null) goto L12;
             */
            /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
            
                r10.close();
             */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x0061, code lost:
            
                r2.countDown();
             */
            /* JADX WARN: Code restructure failed: missing block: B:17:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
            
                r2.countDown();
             */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x006a, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
            
                if (0 == 0) goto L33;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r11 = this;
                    java.lang.String r0 = "PLGLOGIN_HwLoginMgr"
                    java.lang.String r1 = "hasLogin"
                    android.content.Context r2 = r1
                    android.content.ContentResolver r3 = r2.getContentResolver()
                    java.lang.String r2 = "content://com.huawei.hwid.api.provider/has_login"
                    android.net.Uri r4 = android.net.Uri.parse(r2)
                    r2 = 0
                    r9 = 1
                    r10 = 0
                    java.lang.String[] r5 = new java.lang.String[r9]     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    r5[r2] = r1     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    r6 = 0
                    r7 = 0
                    r8 = 0
                    android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    if (r10 == 0) goto L48
                    boolean r3 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    if (r3 == 0) goto L48
                    int r1 = r10.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    int r1 = r10.getInt(r1)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    r3 = 2
                    java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    java.lang.String r4 = "result = "
                    r3[r2] = r4     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    r3[r9] = r4     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    com.huawei.hwlogsmodel.LogUtil.a(r0, r3)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    if (r1 != r9) goto L45
                    com.huawei.login.huaweilogin.HuaweiLoginManager.setHasLogin(r9)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                    goto L48
                L45:
                    com.huawei.login.huaweilogin.HuaweiLoginManager.setHasLogin(r2)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52
                L48:
                    if (r10 == 0) goto L4d
                L4a:
                    r10.close()
                L4d:
                    java.util.concurrent.CountDownLatch r0 = r2     // Catch: java.lang.Exception -> L65
                    goto L61
                L50:
                    r0 = move-exception
                    goto L6b
                L52:
                    java.lang.Object[] r1 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L50
                    java.lang.String r3 = "hasLoginAccount, Exception"
                    r1[r2] = r3     // Catch: java.lang.Throwable -> L50
                    com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L50
                    com.huawei.login.huaweilogin.HuaweiLoginManager.setHasLogin(r2)     // Catch: java.lang.Throwable -> L50
                    if (r10 == 0) goto L4d
                    goto L4a
                L61:
                    r0.countDown()     // Catch: java.lang.Exception -> L65
                    goto L6a
                L65:
                    java.util.concurrent.CountDownLatch r0 = r2
                    r0.countDown()
                L6a:
                    return
                L6b:
                    if (r10 == 0) goto L70
                    r10.close()
                L70:
                    java.util.concurrent.CountDownLatch r1 = r2     // Catch: java.lang.Exception -> L76
                    r1.countDown()     // Catch: java.lang.Exception -> L76
                    goto L7b
                L76:
                    java.util.concurrent.CountDownLatch r1 = r2
                    r1.countDown()
                L7b:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.login.huaweilogin.HuaweiLoginManager.AnonymousClass5.run():void");
            }
        });
        handleTimeout(countDownLatch);
        ReleaseLogUtil.b(RELEASE_LOG_TAG, "hasLoginAccount:", Boolean.valueOf(isHasLogin()));
        return isHasLogin() && !SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false);
    }

    private static void handleTimeout(CountDownLatch countDownLatch) {
        boolean z;
        try {
            z = countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LogUtil.b(TAG, "InterruptedException e = ", e.getMessage());
            z = false;
        }
        if (z) {
            return;
        }
        LogUtil.b(TAG, "hasLoginAccount isOutTime:", Boolean.valueOf(z));
        setHasLogin(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginFailed(int i, ILoginCallback iLoginCallback) {
        LogUtil.a(TAG, "loginFailed error, code is:", Integer.valueOf(i));
        if (iLoginCallback != null) {
            iLoginCallback.onLoginFailed(new LoginResult(i));
        }
    }

    public static void updateLoginTypeByCountry(Context context, String str) {
        if (LoginInit.getCloudapi().isOverseaJudgeByCountry(str)) {
            setIsAllowedLoginValueToDB(context, "1");
            LogUtil.a(TAG, "updateLoginTypeByCountry: isAllowedLogin() true");
        } else {
            setIsAllowedLoginValueToDB(context, "0");
            LogUtil.a(TAG, "updateLoginTypeByCountry: isAllowedLogin() false");
        }
    }

    public static void updateAppTypeBySiteID(final int i) {
        LogUtil.a(TAG, "updateAppTypeBySiteID_siteId ");
        LogUtil.c(TAG, "siteId = ", Integer.valueOf(i));
        if (i != 0) {
            LogUtil.a(TAG, "updateAppTypeBySiteID siteId != defaultSiteId");
            LogUtil.c(TAG, "siteId =", Integer.valueOf(i));
            if (lastSiteId == i) {
                LogUtil.a(TAG, "updateAppTypeBySiteID lastSiteId == siteId");
                return;
            }
            if (i == 1 && !CommonUtil.cg()) {
                LogUtil.a(TAG, "updateAppTypeBySiteID: CHINA_SITE");
                setCloudVersion("1", new StorageDataCallback() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.6
                    @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
                    public void onProcessed(StorageResult storageResult) {
                        if (storageResult.d() == 0) {
                            int unused = HuaweiLoginManager.lastSiteId = i;
                            LogUtil.a(HuaweiLoginManager.TAG, "setCloudVersion HAVE_CLOUD, onProcessed success");
                            LogUtil.c(HuaweiLoginManager.TAG, "setCloudVersion HAVE_CLOUD, onProcessed ", "success，lastSiteId =", Integer.valueOf(HuaweiLoginManager.lastSiteId));
                            return;
                        }
                        LogUtil.a(HuaweiLoginManager.TAG, "setCloudVersion HAVE_CLOUD,onProcessed fail");
                    }
                });
            } else {
                LogUtil.a(TAG, "updateAppTypeBySiteID: NOT CHINA_SITE");
                setCloudVersion("0", new StorageDataCallback() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.7
                    @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
                    public void onProcessed(StorageResult storageResult) {
                        if (storageResult.d() == 0) {
                            int unused = HuaweiLoginManager.lastSiteId = i;
                            LogUtil.a(HuaweiLoginManager.TAG, "setCloudVersion NO_CLOUD,, onProcessed success");
                            LogUtil.c(HuaweiLoginManager.TAG, "lastSiteId =", Integer.valueOf(HuaweiLoginManager.lastSiteId));
                            return;
                        }
                        LogUtil.a(HuaweiLoginManager.TAG, "setCloudVersion NO_CLOUD,, onProcessed fail");
                    }
                });
            }
        }
    }

    public static void setCloudVersion(final String str, final StorageDataCallback storageDataCallback) {
        LogUtil.a(TAG, "Health APP setCloudVersion = ", str);
        VersionDbManager.e(BaseApplication.getContext()).a("have_cloud_or_not", str, new StorageDataCallback() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.8
            @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
            public void onProcessed(StorageResult storageResult) {
                if (storageResult.d() == 0) {
                    UnitUtil.e(str);
                    CommonUtil.ab(str);
                    if (BaseApplication.getContext() != null) {
                        LogConfig.a(BaseApplication.getContext());
                        CommonUtil.i(BaseApplication.getContext(), str);
                    }
                }
                StorageDataCallback storageDataCallback2 = storageDataCallback;
                if (storageDataCallback2 != null) {
                    storageDataCallback2.onProcessed(storageResult);
                }
            }
        });
    }

    public static void setIsAllowedLoginValueToDB(Context context, final String str) {
        VersionDbManager.e(context).a("allow_login_or_not", str, new StorageDataCallback() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.9
            @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
            public void onProcessed(StorageResult storageResult) {
                if (storageResult.d() == 0) {
                    CommonUtil.ad(str);
                    if (BaseApplication.getContext() != null) {
                        if ("0".equals(str)) {
                            HiDataUpdateOption hiDataUpdateOption = new HiDataUpdateOption();
                            hiDataUpdateOption.setType(101);
                            HiHealthManager.d(BaseApplication.getContext()).updateHiHealthData(hiDataUpdateOption, new HiDataOperateListener() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.9.1
                                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                                public void onResult(int i, Object obj) {
                                    LogUtil.a(HuaweiLoginManager.TAG, "setIsAllowedLoginValueToDB: default user ", "onResult errorCode = ", Integer.valueOf(i));
                                }
                            });
                        }
                        CommonUtil.l(BaseApplication.getContext(), str);
                        return;
                    }
                    return;
                }
                LogUtil.a(HuaweiLoginManager.TAG, "setValue KEY_UI_ID_IS_ALLOW_LOGIN_OR_NOT faied!! ", Integer.valueOf(storageResult.d()));
            }
        });
    }

    private void saveTokenInfo(AuthAccount authAccount) {
        LogUtil.a(TAG, "saveTokenInfo enter");
        String accessToken = authAccount.getAccessToken();
        if (TextUtils.isEmpty(accessToken)) {
            ReleaseLogUtil.a(RELEASE_LOG_TAG, "saveTokenInfo accessToken is null!");
            return;
        }
        Context context = BaseApplication.getContext();
        LoginInit.getInstance(context).setAccountInfo(1011, authAccount.getUid(), null);
        SharedPreferenceUtil.getInstance(context).setCountryCode(authAccount.getCountryCode());
        KeyValDbManager.b(BaseApplication.getContext()).a("server_token", accessToken, new StorageParams(14));
        KeyValDbManager.b(context).a(HealthAccessTokenUtil.KEY_AT, accessToken, new StorageParams(1));
        long currentTimeMillis = (System.currentTimeMillis() + 3600000) - HealthAccessTokenUtil.getAtInstance().getRandomUpdateTime();
        KeyValDbManager.b(context).e(HealthAccessTokenUtil.KEY_AT_EXPIRE, String.valueOf(currentTimeMillis));
        ThirdPartyLoginManager.getInstance().timedRefreshAt(currentTimeMillis);
        SharedPreferenceUtil.getInstance(context).setUpdateState();
    }

    public void hmsHasLogined(final ILoginCallback iLoginCallback, final boolean z) {
        final int andIncrement = CALLBACK_INDEX.getAndIncrement();
        Map<Integer, ILoginCallback> map = LOGIN_CALLBACK_MAP;
        map.put(Integer.valueOf(andIncrement), iLoginCallback);
        LogUtil.a(TAG, "LOGIN_CALLBACK_MAP size:", Integer.valueOf(map.size()));
        silentSignIn(new IBaseResponseCallback() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof AuthAccount)) {
                    ReleaseLogUtil.b(HuaweiLoginManager.RELEASE_LOG_TAG, "silentSignIn success.");
                    HuaweiLoginManager.this.m664x1ca9b287((AuthAccount) obj, iLoginCallback, andIncrement);
                    return;
                }
                ReleaseLogUtil.a(HuaweiLoginManager.RELEASE_LOG_TAG, "silentSignIn failed. errCode:", Integer.valueOf(i));
                if (z || (i != 2002 && i != 907135003)) {
                    HuaweiLoginManager.this.loginFailed(i, iLoginCallback);
                    HuaweiLoginManager.LOGIN_CALLBACK_MAP.remove(Integer.valueOf(andIncrement));
                } else {
                    HuaweiLoginManager.this.hmsNotLogined(andIncrement);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: queryAndSaveLoginInfo, reason: merged with bridge method [inline-methods] */
    public void m664x1ca9b287(AuthAccount authAccount, final ILoginCallback iLoginCallback, final int i) {
        if (CommonUtil.bh()) {
            saveTokenInfo(authAccount);
            Utils.initGrs(Utils.getCountryCode(), BaseApplication.getContext());
            ThirdPartyLoginManager.getInstance().queryUserInfo(new IBaseResponseCallback() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 != 0) {
                        HuaweiLoginManager.this.loginFailed(i2, iLoginCallback);
                    } else {
                        ILoginCallback iLoginCallback2 = iLoginCallback;
                        if (iLoginCallback2 != null) {
                            iLoginCallback2.onLoginSuccess(0);
                        }
                    }
                    HuaweiLoginManager.LOGIN_CALLBACK_MAP.remove(Integer.valueOf(i));
                }
            }, true);
        } else {
            String authorizationCode = authAccount.getAuthorizationCode();
            SharedPreferenceUtil.getInstance(BaseApplication.getContext()).setAuthCode(authorizationCode);
            ThirdPartyLoginManager.getInstance().getAtByAuthCode(authorizationCode, false, new IBaseResponseCallback() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.12
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 != 0) {
                        HuaweiLoginManager.this.loginFailed(i2, iLoginCallback);
                    } else {
                        SharedPreferenceUtil.getInstance(BaseApplication.getContext()).setUpdateState();
                        ILoginCallback iLoginCallback2 = iLoginCallback;
                        if (iLoginCallback2 != null) {
                            iLoginCallback2.onLoginSuccess(0);
                        }
                    }
                    HuaweiLoginManager.LOGIN_CALLBACK_MAP.remove(Integer.valueOf(i));
                }
            });
        }
    }

    public static void silentSignIn(final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.b(TAG, "silentSignIn enter.");
        AccountAuthService accountAuthService = null;
        if (!NetworkUtil.i() || isSignInInterval()) {
            ReleaseLogUtil.a(TAG, "silentSignIn no network or in interval");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        String[] scopeList = ScopeManager.getScopeList();
        ArrayList arrayList = new ArrayList(64);
        for (String str : scopeList) {
            arrayList.add(new Scope(str));
        }
        AccountAuthParams createParams = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setScopeList(arrayList).setAuthorizationCode().setAccessToken().setUid().setMobileNumber().setProfile().setEmail().setId().createParams();
        ThreadPoolManager d = ThreadPoolManager.d();
        try {
            accountAuthService = AccountAuthManager.getService(BaseApplication.getContext(), createParams);
        } catch (VerifyError e) {
            ReleaseLogUtil.c(TAG, "silentSignIn VerifyError: ", e.getMessage());
        }
        if (accountAuthService == null) {
            ReleaseLogUtil.a(TAG, "AccountAuthService is null ");
            return;
        }
        if (!CommonUtil.bv()) {
            reportErrorCode("silentSignInTotal", 0);
        }
        accountAuthService.silentSignIn().addOnSuccessListener(d, new OnSuccessListener<AuthAccount>() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.14
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(AuthAccount authAccount) {
                ReleaseLogUtil.b(HuaweiLoginManager.TAG, "silentSignIn success!");
                LogUtil.c(HuaweiLoginManager.TAG, "authAccount:", authAccount);
                IBaseResponseCallback.this.d(0, authAccount);
            }
        }).addOnFailureListener(d, new OnFailureListener() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager.13
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                if (exc instanceof ApiException) {
                    int statusCode = ((ApiException) exc).getStatusCode();
                    ReleaseLogUtil.c(HuaweiLoginManager.TAG, "silentSignIn failed status:", Integer.valueOf(statusCode));
                    if (HuaweiLoginManager.ERROR_CODE_NOT_RETRY_SET.contains(Integer.valueOf(statusCode))) {
                        long unused = HuaweiLoginManager.sSignInTimestamp = SystemClock.elapsedRealtime();
                    }
                    IBaseResponseCallback.this.d(statusCode, null);
                    HuaweiLoginManager.reportErrorCode("silentSignIn", statusCode);
                }
            }
        });
    }

    public static void reportErrorCode(String str, int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("apiName", str);
        linkedHashMap.put("errorCode", String.valueOf(i));
        OpAnalyticsApi opAnalyticsApi = LoginInit.getInstance(BaseApplication.getContext()).getOpAnalyticsApi();
        if (opAnalyticsApi != null) {
            opAnalyticsApi.reportErrorCode(ACCOUNT_SDK_API_ERROR_CODE, linkedHashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hmsNotLogined(int i) {
        Context context = BaseApplication.getContext();
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.putExtra("requestCode", 6009);
        intent.putExtra("callbackIndex", i);
        intent.setClassName(context, "com.huawei.health.HuaweiLoginActivity");
        context.startActivity(intent);
    }

    public void handleLoginRequest(Intent intent, final int i) {
        Map<Integer, ILoginCallback> map = LOGIN_CALLBACK_MAP;
        final ILoginCallback iLoginCallback = map.get(Integer.valueOf(i));
        if (intent == null) {
            LogUtil.b(TAG, "handleLoginRequest: data is null!");
            loginFailed(2012, iLoginCallback);
            reportErrorCode("nonSilentSignIn", 2012);
            map.remove(Integer.valueOf(i));
            return;
        }
        Task<AuthAccount> parseAuthResultFromIntent = AccountAuthManager.parseAuthResultFromIntent(intent);
        if (parseAuthResultFromIntent != null && parseAuthResultFromIntent.isSuccessful()) {
            final AuthAccount result = parseAuthResultFromIntent.getResult();
            LogUtil.c(TAG, "handleLoginRequest success! account: ", result);
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.huaweilogin.HuaweiLoginManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HuaweiLoginManager.this.m664x1ca9b287(result, iLoginCallback, i);
                }
            });
            return;
        }
        if (parseAuthResultFromIntent != null && (parseAuthResultFromIntent.getException() instanceof ApiException)) {
            int statusCode = ((ApiException) parseAuthResultFromIntent.getException()).getStatusCode();
            loginFailed(statusCode, iLoginCallback);
            reportErrorCode("nonSilentSignIn", statusCode);
        } else {
            LogUtil.b(TAG, "handleLoginRequest: authAccountTask is null!");
            loginFailed(-1, iLoginCallback);
            reportErrorCode("nonSilentSignIn", -1);
        }
        map.remove(Integer.valueOf(i));
    }

    static class Instance {
        private static final HuaweiLoginManager INSTANCE = new HuaweiLoginManager();

        private Instance() {
        }
    }

    private static boolean isSignInInterval() {
        if (SystemClock.elapsedRealtime() - sSignInTimestamp > 10000) {
            return false;
        }
        LogUtil.a(TAG, "signIn in interval");
        return true;
    }
}
