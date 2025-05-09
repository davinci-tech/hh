package com.huawei.login.ui.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.common.OpAnalyticsApi;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class HealthAccessTokenUtil {
    private static final long AWAIT_TIMEOUT = 5000;
    public static final String KEY_AT = "healthAccessToken";
    public static final String KEY_AT_EXPIRE = "hwidATokenExpireTime";
    private static final String LOG_TAG = "HiH_HealthTokenUt";
    private static final int MAX_RANDOM = 8;
    private static final long MINUTE_MILL = 60000;
    private static final int MIN_RANDOM = 2;
    public static final long REFRESH_TIME = 3600000;
    private static final String RELEASE_LOG_TAG = "R_HiH_HealthTokenUt";
    private static final String TAG = "HealthTokenUt";
    private Context mContext;
    private BroadcastReceiver mLoginStatusReceiver;
    private String mUserAt;

    private HealthAccessTokenUtil() {
        this.mLoginStatusReceiver = new BroadcastReceiver() { // from class: com.huawei.login.ui.login.HealthAccessTokenUtil.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.a(HealthAccessTokenUtil.TAG, "enter mLoginStatusReceiver");
                if ("com.huawei.plugin.account.logout".equals(intent.getAction())) {
                    LogUtil.a(HealthAccessTokenUtil.RELEASE_LOG_TAG, "logout");
                    HealthAccessTokenUtil.this.clearAtAndUid();
                }
            }
        };
        this.mContext = BaseApplication.getContext();
        LogUtil.a(TAG, "enter HealthAccessTokenUtil");
    }

    public void registerReceiver() {
        LogUtil.a(TAG, "enter registerReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.logout");
        BroadcastManagerUtil.bFA_(this.mContext, this.mLoginStatusReceiver, intentFilter, LocalBroadcast.c, null);
    }

    public void registerReceiverToGetAt() {
        registerReceiver();
        String e = KeyValDbManager.b(this.mContext).e("key_wether_to_auth");
        LogUtil.a(RELEASE_LOG_TAG, "privacy: ", e);
        if (Boolean.parseBoolean(e)) {
            getAccessToken();
        }
    }

    public void unregisterReceiverToGetAt() {
        LogUtil.a(TAG, "Unregister receiver");
        this.mContext.unregisterReceiver(this.mLoginStatusReceiver);
    }

    public static HealthAccessTokenUtil getAtInstance() {
        return AtInstance.INSTANCE;
    }

    /* loaded from: classes5.dex */
    static class AtInstance {
        private static final HealthAccessTokenUtil INSTANCE = new HealthAccessTokenUtil();

        private AtInstance() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAtAndUid() {
        KeyValDbManager.b(this.mContext).a(KEY_AT, "", new StorageParams(1));
    }

    private void doInBackground() {
        report("doInBackground");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.ui.login.HealthAccessTokenUtil.2
            @Override // java.lang.Runnable
            public void run() {
                HealthAccessTokenUtil.this.refreshAccessToken();
            }
        });
    }

    private void report(String str) {
        OpAnalyticsApi opAnalyticsApi;
        if (CommonUtil.bv()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long timeInMillis2 = calendar.getTimeInMillis();
        if (((currentTimeMillis < timeInMillis || currentTimeMillis > timeInMillis + 300000) && (currentTimeMillis < timeInMillis2 || currentTimeMillis > timeInMillis2 + 59999)) || (opAnalyticsApi = LoginInit.getInstance(BaseApplication.getContext()).getOpAnalyticsApi()) == null) {
            return;
        }
        opAnalyticsApi.onReport(str, DfxUtils.d(Thread.currentThread(), null));
    }

    public String refreshAccessToken() {
        LogUtil.a(RELEASE_LOG_TAG, "refreshAccessToken Sync begin");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ReleaseLogUtil.d(RELEASE_LOG_TAG, "refreshAccessToken sync method can't execute in mainThread");
        }
        return GetAccessTokenSyncHelper.getAccessTokenWithDoubleCheck();
    }

    String refreshAccessTokenInner() {
        if (!CommonUtil.bh()) {
            this.mUserAt = ThirdLoginDataStorageUtil.getAccessTokenFromDb();
        } else {
            this.mUserAt = KeyValDbManager.b(this.mContext).d(KEY_AT, new StorageParams(1));
            silentSignSync();
        }
        return this.mUserAt;
    }

    String getCachedUserAt() {
        return this.mUserAt;
    }

    void refreshAccessTokenAsync(final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.login.ui.login.HealthAccessTokenUtil.3
            @Override // java.lang.Runnable
            public void run() {
                if (CommonUtil.bh()) {
                    HealthAccessTokenUtil.this.silentSignAsync(iBaseResponseCallback);
                } else {
                    ThirdPartyLoginManager.getInstance().refreshAtByRt(new IBaseResponseCallback() { // from class: com.huawei.login.ui.login.HealthAccessTokenUtil.3.1
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (iBaseResponseCallback != null) {
                                iBaseResponseCallback.d(i, obj);
                            }
                        }
                    });
                }
            }
        });
    }

    protected String getAccessToken() {
        LogUtil.a(TAG, "enter getAccessToken");
        if (!CommonUtil.bh()) {
            return ThirdLoginDataStorageUtil.getAccessTokenFromDb();
        }
        if (GetAccessTokenSyncHelper.isCacheAtValid()) {
            ReleaseLogUtil.e(RELEASE_LOG_TAG, "getAccessToken from mmkv");
            return KeyValDbManager.b(this.mContext).d(KEY_AT, new StorageParams(1));
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return refreshAccessToken();
        }
        ReleaseLogUtil.e(RELEASE_LOG_TAG, "Is main thread, enter doInBackground");
        doInBackground();
        return KeyValDbManager.b(this.mContext).d(KEY_AT, new StorageParams(1));
    }

    private void silentSignSync() {
        LogUtil.a(RELEASE_LOG_TAG, "enter silentSignSync");
        if (!CloudUtils.a() || !LoginInit.getInstance(this.mContext).getIsLogined()) {
            LogUtil.a(TAG, "silentSignSync not login");
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        report("silentSignIn");
        HuaweiLoginManager.silentSignIn(new IBaseResponseCallback() { // from class: com.huawei.login.ui.login.HealthAccessTokenUtil.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                HealthAccessTokenUtil.this.saveAccessToken(i, obj);
                countDownLatch.countDown();
            }
        });
        try {
            LogUtil.a(RELEASE_LOG_TAG, "wait silentSignIn countDown result:", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b(RELEASE_LOG_TAG, "InterruptedException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void silentSignAsync(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "enter silentSignAsync");
        if (!CloudUtils.a() || !LoginInit.getInstance(this.mContext).getIsLogined()) {
            LogUtil.a(TAG, "silentSignAsync not login");
            iBaseResponseCallback.d(-1, "");
        } else {
            HuaweiLoginManager.silentSignIn(new IBaseResponseCallback() { // from class: com.huawei.login.ui.login.HealthAccessTokenUtil.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    HealthAccessTokenUtil.this.saveAccessToken(i, obj);
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(i, KeyValDbManager.b(HealthAccessTokenUtil.this.mContext).d(HealthAccessTokenUtil.KEY_AT, new StorageParams(1)));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAccessToken(int i, Object obj) {
        long currentTimeMillis = (System.currentTimeMillis() + 3600000) - getRandomUpdateTime();
        if (i == 0 && (obj instanceof AuthAccount)) {
            AuthAccount authAccount = (AuthAccount) obj;
            if (!TextUtils.isEmpty(authAccount.getUid())) {
                SharedPreferenceUtil.getInstance(BaseApplication.getContext()).setUserID(authAccount.getUid());
            } else {
                ReleaseLogUtil.e(TAG, "saveAccessToken getUid is empty");
            }
            this.mUserAt = authAccount.getAccessToken();
            KeyValDbManager.b(BaseApplication.getContext()).a("server_token", this.mUserAt, new StorageParams(14));
            KeyValDbManager.b(this.mContext).a(KEY_AT, this.mUserAt, new StorageParams(1));
            KeyValDbManager.b(this.mContext).e(KEY_AT_EXPIRE, String.valueOf(currentTimeMillis));
            if (TextUtils.isEmpty(this.mUserAt)) {
                LogUtil.b(RELEASE_LOG_TAG, "success: mUserAT is null");
            } else {
                LogUtil.a(RELEASE_LOG_TAG, "success: mUserAT:", this.mUserAt.substring(r6.length() - 4));
            }
        } else {
            LogUtil.h(RELEASE_LOG_TAG, "sign fail status errorCode: ", Integer.valueOf(i));
        }
        ThirdPartyLoginManager.getInstance().timedRefreshAt(currentTimeMillis);
    }

    public long getRandomUpdateTime() {
        LogUtil.a(TAG, "randomMinute:", Integer.valueOf(new SecureRandom().nextInt(6) + 2));
        return r0 * 60 * 1000;
    }

    /* loaded from: classes5.dex */
    static class GetAccessTokenSyncHelper {
        private static final Object sLock = new Object();

        private GetAccessTokenSyncHelper() {
        }

        static String getAccessTokenWithDoubleCheck() {
            if (!isCacheAtValid()) {
                synchronized (sLock) {
                    if (isCacheAtValid()) {
                        return HealthAccessTokenUtil.getAtInstance().getCachedUserAt();
                    }
                    LogUtil.a(HealthAccessTokenUtil.TAG, "get from the refreshAccessToken, not Cache");
                    return HealthAccessTokenUtil.getAtInstance().refreshAccessTokenInner();
                }
            }
            return HealthAccessTokenUtil.getAtInstance().getCachedUserAt();
        }

        static boolean isCacheAtValid() {
            return System.currentTimeMillis() < CommonUtil.g(KeyValDbManager.b(BaseApplication.getContext()).e(HealthAccessTokenUtil.KEY_AT_EXPIRE));
        }
    }
}
