package com.huawei.thirdloginbasemodule;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jdq;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageParams;
import health.compact.a.ThirdLoginFreshAccessToken;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class ThirdLoginDataStorageUtil {
    private static final long AT_REFRESH_INTERVAL = 30000;
    private static final int AT_TOKEN_TYPE = 2;
    private static final String LITE_ACCESS_TOKEN_EXPIRE_TIME_KEY = "accessTokenExpireTime";
    private static final String LITE_ACCESS_TOKEN_KEY = "lite_access_token";
    private static final String LITE_LOGOUT_KEY = "logout_flag";
    private static final String LITE_REFRESH_TOKEN_EXPIRE_TIME_KEY = "refreshTokenExpireTime";
    private static final String LITE_REFRESH_TOKEN_KEY = "lite_refresh_token";
    private static final String LITE_SERVER_TIME_KEY = "serverTimestamp";
    private static final String LITE_USER_ID_KEY = "lite_uid";
    public static final long REFRESH_TOKEN_INTERVAL = 15552000000L;
    private static final String RELEASE_TAG = "R_3rdLoginDataStrgUt";
    public static final String START_DAEMON_FOR_REFRESH_TOKEN = "REFRESH_TOKEN_START_DAEMON_SERVICE";
    private static final String TAG = "3rdLoginDataStrgUt";
    private static long sClickTimeStamp;

    public static int getTokenTypeValue() {
        return 2;
    }

    private ThirdLoginDataStorageUtil() {
    }

    public static String getAccessTokenFromDb() {
        if (isAccessTokenOverTime() && needRefresh()) {
            ReleaseLogUtil.d(RELEASE_TAG, "isAccessTokenOverTime");
            ThirdLoginFreshAccessToken.d();
        }
        return KeyValDbManager.b(BaseApplication.getContext()).d(LITE_ACCESS_TOKEN_KEY, new StorageParams(1));
    }

    public static void saveAccessTokenToDb(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).e(LITE_ACCESS_TOKEN_KEY, str, new StorageParams(1), null);
    }

    public static int deleteAccessTokenFromDb() {
        return KeyValDbManager.b(BaseApplication.getContext()).c(LITE_ACCESS_TOKEN_KEY);
    }

    public static String getAccessTokenExpireTimeFromDb() {
        return KeyValDbManager.b(BaseApplication.getContext()).d(LITE_ACCESS_TOKEN_EXPIRE_TIME_KEY, new StorageParams(1));
    }

    public static void saveAccessTokenExpireTimeToDb(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).e(LITE_ACCESS_TOKEN_EXPIRE_TIME_KEY, str, new StorageParams(1), null);
    }

    public static String getRefreshTokenFromDb() {
        return KeyValDbManager.b(BaseApplication.getContext()).d(LITE_REFRESH_TOKEN_KEY, new StorageParams(1));
    }

    public static void saveRefreshTokenToDb(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).e(LITE_REFRESH_TOKEN_KEY, str, new StorageParams(1), null);
    }

    public static String getRefreshTokenExpireTimeFromDb() {
        return KeyValDbManager.b(BaseApplication.getContext()).d(LITE_REFRESH_TOKEN_EXPIRE_TIME_KEY, new StorageParams(1));
    }

    public static void saveRefreshTokenExpireTimeToDb(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).e(LITE_REFRESH_TOKEN_EXPIRE_TIME_KEY, str, new StorageParams(1), null);
        LogUtil.a(TAG, "saveRefreshTokenExpireTimeToDb expireTime = ", str);
    }

    public static String getServerTimestamp() {
        return KeyValDbManager.b(BaseApplication.getContext()).d(LITE_SERVER_TIME_KEY, new StorageParams(1));
    }

    public static void saveServerTimestamp(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).e(LITE_SERVER_TIME_KEY, str, new StorageParams(1), null);
    }

    public static void setUserId(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).e(LITE_USER_ID_KEY, str, new StorageParams(1), null);
        LogUtil.c(TAG, "setUserId completed uid = ", str);
    }

    public static String getUserId() {
        LogUtil.c(TAG, "Enter getUID");
        return KeyValDbManager.b(BaseApplication.getContext()).d(LITE_USER_ID_KEY, new StorageParams(1));
    }

    public static void setLogoutFlag(boolean z) {
        KeyValDbManager.b(BaseApplication.getContext()).e(LITE_LOGOUT_KEY, String.valueOf(z), new StorageParams(1), null);
    }

    public static String getLogoutFlag() {
        LogUtil.a(TAG, "Enter getLogoutFlag");
        return KeyValDbManager.b(BaseApplication.getContext()).d(LITE_LOGOUT_KEY, new StorageParams(1));
    }

    public static boolean isAccessTokenOverTime() {
        String accessTokenExpireTimeFromDb = getAccessTokenExpireTimeFromDb();
        LogUtil.c(TAG, "isAccessTokenOverTime oldAtExpireTime = ", accessTokenExpireTimeFromDb, " serverTime = ", Long.valueOf(CommonUtil.g(getServerTimestamp())));
        return TextUtils.isEmpty(accessTokenExpireTimeFromDb) || CommonUtil.g(accessTokenExpireTimeFromDb) <= System.currentTimeMillis();
    }

    public static boolean isRefreshTokenOverTime() {
        String refreshTokenExpireTimeFromDb = getRefreshTokenExpireTimeFromDb();
        LogUtil.a(TAG, "isRefreshTokenOverTime oldRtExpireTime = ", refreshTokenExpireTimeFromDb);
        return TextUtils.isEmpty(refreshTokenExpireTimeFromDb) || CommonUtil.g(refreshTokenExpireTimeFromDb) <= System.currentTimeMillis();
    }

    public static String saveProfileImage(Context context, String str, Bitmap bitmap) {
        String str2;
        FileOutputStream fileOutputStream;
        LogUtil.a(TAG, "saveImage");
        if (context == null || bitmap == null) {
            LogUtil.h(TAG, "context is null or bitmap is null");
            return "";
        }
        File file = new File(context.getFilesDir() + File.separator + "photos" + File.separator + "headimage");
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.h(TAG, "create file error");
        }
        try {
            str2 = file.getCanonicalPath() + File.separator + jdq.e(context, str);
        } catch (IOException unused) {
            LogUtil.b(TAG, "IOException");
            str2 = "";
        }
        File file2 = new File(str2);
        if (file2.exists() && !file2.delete()) {
            LogUtil.h(TAG, "delete old file error");
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused2) {
        } catch (IOException unused3) {
        }
        if (!file2.createNewFile()) {
            LogUtil.b(TAG, "saveImage createNewFile error");
            return "";
        }
        fileOutputStream = new FileOutputStream(file2);
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            try {
                try {
                    fileOutputStream.flush();
                } catch (IOException unused4) {
                    LogUtil.b(TAG, "saveImage finally flush IOException");
                }
            } finally {
                closeResourceObj(fileOutputStream);
            }
        } catch (FileNotFoundException unused5) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b(TAG, "saveImage FileNotFoundException");
            try {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.flush();
                    } catch (IOException unused6) {
                        LogUtil.b(TAG, "saveImage finally flush IOException");
                    }
                }
                return str2;
            } finally {
            }
        } catch (IOException unused7) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b(TAG, "saveImage IOException");
            try {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.flush();
                    } catch (IOException unused8) {
                        LogUtil.b(TAG, "saveImage finally flush IOException");
                    }
                }
                return str2;
            } finally {
            }
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream2 != null) {
                try {
                    try {
                        fileOutputStream2.flush();
                    } catch (IOException unused9) {
                        LogUtil.b(TAG, "saveImage finally flush IOException");
                        throw th;
                    }
                } finally {
                }
            }
            throw th;
        }
        return str2;
    }

    private static void closeResourceObj(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
                LogUtil.b(TAG, "saveImage finally close IOException");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0118 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v19, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v30 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v8, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap loadRemoteImage(java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil.loadRemoteImage(java.lang.String):android.graphics.Bitmap");
    }

    private static boolean needRefresh() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - sClickTimeStamp < 30000) {
            return false;
        }
        LogUtil.a(TAG, "AT needRefresh");
        sClickTimeStamp = elapsedRealtime;
        return true;
    }
}
