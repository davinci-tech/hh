package com.huawei.operation.h5pro.versionmanage;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor;
import com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BuildTypeConfig;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class H5ProVersionManager {
    private static final String H5_VERSION_MANAGE_AGC_BETA_KEY = "H5_Version_Summary_Beta";
    private static final String H5_VERSION_MANAGE_AGC_KEY = "H5_Version_Summary";
    private static final String SHARED_PREFERENCES_FILE_NAME = "H5_Version_Manage_SharedPreference";
    private static final String SHARED_PREFERENCES_FILE_NAME_BETA = "H5_Version_Manage_Beta_SharedPreference";
    private static final String TAG = "H5PRO_H5ProVersionManager";
    private static final String TAG_RELEASE = "R_H5PRO_H5ProVersionManager";

    private H5ProVersionManager() {
    }

    public static void syncH5VersionSummary() {
        synchronized (H5ProVersionManager.class) {
            if (BuildTypeConfig.a() || BuildTypeConfig.e() || BuildTypeConfig.c()) {
                String str = BuildTypeConfig.a() ? H5_VERSION_MANAGE_AGC_KEY : H5_VERSION_MANAGE_AGC_BETA_KEY;
                ReleaseLogUtil.e(TAG_RELEASE, "syncH5VersionSummary enter: ".concat(str));
                RemoteConfigUtils.d(str, new AnonymousClass1());
            }
        }
    }

    /* renamed from: com.huawei.operation.h5pro.versionmanage.H5ProVersionManager$1, reason: invalid class name */
    class AnonymousClass1 implements RemoteConfigUtils.ConfigCallback {
        AnonymousClass1() {
        }

        @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
        public void onSuccess(final String str) {
            ReleaseLogUtil.e(H5ProVersionManager.TAG_RELEASE, "syncH5VersionSummary onSuccess");
            LogUtil.c(H5ProVersionManager.TAG, "syncH5VersionSummary onSuccess: " + str);
            if (!H5ProVersionManager.isUiThread()) {
                H5ProVersionManager.doRefreshSp(str);
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.versionmanage.H5ProVersionManager$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        H5ProVersionManager.doRefreshSp(str);
                    }
                });
            }
        }

        @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
        public void onFailure(Exception exc, String str) {
            ReleaseLogUtil.d(H5ProVersionManager.TAG_RELEASE, "RemoteConfig, fetch onFailure: " + exc.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doRefreshSp(String str) {
        if (TextUtils.isEmpty(str)) {
            SharedPreferenceManager.j(BaseApplication.getContext(), getSpName());
            ReleaseLogUtil.e(TAG_RELEASE, "syncH5VersionSummary onSuccess clean.");
            return;
        }
        try {
            H5ProVersionInfo[] h5ProVersionInfoArr = (H5ProVersionInfo[]) new Gson().fromJson(str, H5ProVersionInfo[].class);
            if (h5ProVersionInfoArr == null || h5ProVersionInfoArr.length == 0) {
                return;
            }
            for (H5ProVersionInfo h5ProVersionInfo : h5ProVersionInfoArr) {
                doRefreshSpPerH5(BaseApplication.getContext(), h5ProVersionInfo);
            }
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c(TAG, "syncH5VersionSummary, JsonSyntaxException, " + e.getMessage());
        }
    }

    private static String getSpName() {
        return BuildTypeConfig.a() ? SHARED_PREFERENCES_FILE_NAME : SHARED_PREFERENCES_FILE_NAME_BETA;
    }

    private static void doRefreshSpPerH5(Context context, H5ProVersionInfo h5ProVersionInfo) {
        if (h5ProVersionInfo == null || !h5ProVersionInfo.isIllegal()) {
            return;
        }
        SharedPreferenceManager.e(context, getSpName(), h5ProVersionInfo.getPkgName(), new Gson().toJson(h5ProVersionInfo), new StorageParams(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void checkH5Upgrade(String str, H5ProInterceptor.InterceptCallback interceptCallback) {
        LogUtil.a(TAG, "checkH5Upgrade enter: " + str);
        if (interceptCallback == null) {
            LogUtil.b(TAG, "checkH5Upgrade, interceptCallback is null. ");
            return;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), getSpName(), str);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h(TAG, "checkH5Upgrade, sp version, empty. ");
            interceptCallback.onNotIntercepted();
            return;
        }
        try {
            H5ProVersionInfo h5ProVersionInfo = (H5ProVersionInfo) new Gson().fromJson(b, H5ProVersionInfo.class);
            if ("1".equals(h5ProVersionInfo.getGraySwitch())) {
                checkH5GrayVersion(str, h5ProVersionInfo, interceptCallback);
            } else {
                interceptCallback.onIntercepted(h5ProVersionInfo.getVersion());
            }
        } catch (JsonSyntaxException e) {
            LogUtil.b(TAG, "getLatestVersion, JsonSyntaxException, " + e.getMessage());
            interceptCallback.onNotIntercepted();
        }
    }

    private static void checkH5GrayVersion(String str, final H5ProVersionInfo h5ProVersionInfo, final H5ProInterceptor.InterceptCallback interceptCallback) {
        String replaceAll = str.replaceAll("[\\.-]", "_");
        if (!BuildTypeConfig.a()) {
            replaceAll = replaceAll + "_BETA";
        }
        RemoteConfigUtils.d(replaceAll, new RemoteConfigUtils.ConfigCallback() { // from class: com.huawei.operation.h5pro.versionmanage.H5ProVersionManager.2
            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onSuccess(String str2) {
                LogUtil.a(H5ProVersionManager.TAG, "checkH5GrayVersion getRemoteConfigValue, getValue is: ", str2);
                H5ProVersionManager.handleGrayResultInSubThread(str2, H5ProInterceptor.InterceptCallback.this, h5ProVersionInfo);
            }

            @Override // com.huawei.health.healthcloudconfig.utils.RemoteConfigUtils.ConfigCallback
            public void onFailure(Exception exc, String str2) {
                LogUtil.h(H5ProVersionManager.TAG, "checkH5GrayVersion RemoteConfig, fetch onFailure: " + exc.getMessage());
                H5ProVersionManager.handleGrayResultInSubThread(str2, H5ProInterceptor.InterceptCallback.this, h5ProVersionInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleGrayResultInSubThread(final String str, final H5ProInterceptor.InterceptCallback interceptCallback, final H5ProVersionInfo h5ProVersionInfo) {
        if (isUiThread()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.versionmanage.H5ProVersionManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    H5ProVersionManager.handleGrayResult(str, interceptCallback, h5ProVersionInfo);
                }
            });
        } else {
            handleGrayResult(str, interceptCallback, h5ProVersionInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleGrayResult(String str, H5ProInterceptor.InterceptCallback interceptCallback, H5ProVersionInfo h5ProVersionInfo) {
        String version;
        if (TextUtils.isEmpty(str)) {
            version = h5ProVersionInfo.getVersion();
        } else {
            H5ProVersionGrayInfo h5ProVersionGrayInfo = (H5ProVersionGrayInfo) new Gson().fromJson(str, H5ProVersionGrayInfo.class);
            if (h5ProVersionGrayInfo != null && !TextUtils.isEmpty(h5ProVersionGrayInfo.getVersion())) {
                version = h5ProVersionGrayInfo.getVersion();
            } else {
                version = h5ProVersionInfo.getVersion();
            }
        }
        interceptCallback.onIntercepted(version);
    }
}
