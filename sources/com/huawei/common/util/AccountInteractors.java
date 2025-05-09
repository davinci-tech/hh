package com.huawei.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.account.aidl.AccountAidlInfo;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import defpackage.jdq;
import defpackage.jdr;
import defpackage.jfb;
import defpackage.jfd;
import defpackage.ut;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class AccountInteractors {
    private static final int ERROR_CODE = 907127009;
    private static final int QUALITY = 100;
    private static final String TAG = "AccountInteractors";
    private static final int WAIT_TIME = 3;

    private AccountInteractors() {
    }

    public static AccountAidlInfo isWearLogined(Context context) {
        final ut b = ut.b(context);
        final AccountAidlInfo[] accountAidlInfoArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        b.d(new IBaseResponseCallback() { // from class: com.huawei.common.util.AccountInteractors.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    LogUtil.a(AccountInteractors.TAG, "accountmigrate: isWearLogined bindRemoteService = true");
                    accountAidlInfoArr[0] = b.b();
                } else {
                    accountAidlInfoArr[0] = null;
                }
                try {
                    countDownLatch.countDown();
                } catch (UnsupportedOperationException unused) {
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LogUtil.b(TAG, "accountmigrate: 2.0isLogin InterruptedException e = ", e.getMessage());
        }
        AccountAidlInfo accountAidlInfo = accountAidlInfoArr[0];
        if (accountAidlInfo == null || !TextUtils.isEmpty(accountAidlInfo.getServeToken())) {
            return accountAidlInfoArr[0];
        }
        LogUtil.a(TAG, "accountmigrate: accountAidlInfo[0] but st is empty.");
        return null;
    }

    public static String saveImage(Context context, String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        LogUtil.a(TAG, "saveImage");
        if (bitmap == null) {
            LogUtil.b(TAG, "bitmap is null");
            return "";
        }
        String filePath = getFilePath(context, str);
        File file = new File(filePath);
        if (file.exists() && !file.delete()) {
            LogUtil.b(TAG, "delete old file error");
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException | IllegalArgumentException | IllegalStateException unused2) {
        }
        if (!file.createNewFile()) {
            LogUtil.b(TAG, "saveImage createNewFile error");
            return "";
        }
        fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            try {
                try {
                    fileOutputStream.flush();
                } catch (IOException unused3) {
                    LogUtil.b(TAG, "saveImage finally flush IOException");
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused4) {
                        LogUtil.b(TAG, "saveImage finally close IOException");
                    }
                }
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException unused5) {
                    LogUtil.b(TAG, "saveImage finally close IOException");
                }
            }
        } catch (FileNotFoundException unused6) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b(TAG, "saveImage FileNotFoundException");
            if (fileOutputStream2 != null) {
                try {
                    try {
                        fileOutputStream2.flush();
                    } catch (IOException unused7) {
                        LogUtil.b(TAG, "saveImage finally flush IOException");
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException unused8) {
                                LogUtil.b(TAG, "saveImage finally close IOException");
                            }
                        }
                    }
                } catch (Throwable th2) {
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused9) {
                            LogUtil.b(TAG, "saveImage finally close IOException");
                        }
                    }
                    throw th2;
                }
            }
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException unused10) {
                    LogUtil.b(TAG, "saveImage finally close IOException");
                }
            }
            return filePath;
        } catch (IOException | IllegalArgumentException | IllegalStateException unused11) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b(TAG, "saveImage IOException");
            try {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.flush();
                    } catch (IOException unused12) {
                        LogUtil.b(TAG, "saveImage finally flush IOException");
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException unused13) {
                                LogUtil.b(TAG, "saveImage finally close IOException");
                            }
                        }
                    }
                }
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused14) {
                        LogUtil.b(TAG, "saveImage finally close IOException");
                    }
                }
                return filePath;
            } catch (Throwable th3) {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused15) {
                        LogUtil.b(TAG, "saveImage finally close IOException");
                    }
                }
                throw th3;
            }
        } catch (Throwable th4) {
            th = th4;
            try {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.flush();
                    } catch (IOException unused16) {
                        LogUtil.b(TAG, "saveImage finally flush IOException");
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused17) {
                                LogUtil.b(TAG, "saveImage finally close IOException");
                            }
                        }
                        throw th;
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused18) {
                        LogUtil.b(TAG, "saveImage finally close IOException");
                    }
                }
                throw th;
            } catch (Throwable th5) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused19) {
                        LogUtil.b(TAG, "saveImage finally close IOException");
                    }
                }
                throw th5;
            }
        }
        return filePath;
    }

    private static String getFilePath(Context context, String str) {
        File file = new File(context.getFilesDir() + File.separator + "photos" + File.separator + "headimage");
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.b(TAG, "create file error");
        }
        try {
            return file.getCanonicalPath() + File.separator + jdq.e(context, str);
        } catch (IOException unused) {
            LogUtil.b(TAG, "IOException");
            return "";
        }
    }

    public static boolean isInstallHwIdApk(Context context) {
        boolean checkIsInstallHuaweiAccount = HuaweiLoginManager.checkIsInstallHuaweiAccount(context);
        LogUtil.a(TAG, "accountmigrate: isInstallHwIdApk = " + checkIsInstallHuaweiAccount);
        return checkIsInstallHuaweiAccount;
    }

    public static int checkCloudAndLocalDataMigrated(Context context) {
        LogUtil.a(TAG, "accountmigrate: checkCloudAndLocalDataMigrated ");
        ArrayList<jfd> d = jfb.e().d(LoginInit.getInstance(context).getAccountInfo(1011));
        if (d == null) {
            return 0;
        }
        int size = d.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jfd jfdVar = d.get(i2);
            LogUtil.c(TAG, "accountmigrate: checkCloudAndLocalDataMigrated migrateTable" + i2 + " = " + jfdVar.toString());
            if (jfdVar.g() && jfdVar.h()) {
                i++;
            }
        }
        LogUtil.a(TAG, "accountmigrate: checkCloudAndLocalDataMigrated successNum = ", Integer.valueOf(i));
        return i;
    }

    public static void clearCloudAndLocalDataMigratedTag(Context context) {
        LogUtil.a(TAG, "accountmigrate: clearCloudAndLocalDataMigratedTag ");
        ArrayList<jfd> d = jfb.e().d(LoginInit.getInstance(context).getAccountInfo(1011));
        if (d == null) {
            return;
        }
        int size = d.size();
        for (int i = 0; i < size; i++) {
            jfd jfdVar = d.get(i);
            if (jfdVar.g() && jfdVar.h()) {
                jfb.e().a(jfdVar.d(), jfdVar.b());
                jfb.e().b(jfdVar.d(), jfdVar.b());
            }
        }
    }

    public static void clearCloudPushRecevieTag(Context context) {
        LogUtil.a(TAG, "accountmigrate: clearCloudPushRecevieTag ");
        SharedPreferenceManager.e(context, Integer.toString(10015), "is_cloud_push_receiver", "is_cloud_push_receiver_false", new StorageParams(0));
    }

    public static boolean isReceivedCloudPush(Context context) {
        LogUtil.a(TAG, "accountmigrate: isReceivedCloudPush ");
        String b = SharedPreferenceManager.b(context, Integer.toString(10015), "is_cloud_push_receiver");
        LogUtil.a(TAG, "accountmigrate: isReceivedCloudPush isRecevie = ", b);
        return "is_cloud_push_receiver_true".equals(b);
    }

    public static void saveMigrateInfoToDb(String str, String str2, String str3, boolean z) {
        LogUtil.a(TAG, "accountmigrate: saveMigrateInfoToDb");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.a(TAG, "null,return");
            return;
        }
        jfd jfdVar = new jfd();
        jfdVar.d(str3);
        jfdVar.b(str);
        jfdVar.a(str2);
        jfdVar.b(z);
        jfdVar.d(false);
        jfdVar.d(false);
        jfb.e().e(jfdVar);
        jfb.e().d(str3);
        LogUtil.c(TAG, "accountmigrate: saveMigrateInfoToDb migrateTable = ", jfdVar.toString());
    }

    public static void uploadMigrateerrorCode(long j) {
        LogUtil.a(TAG, "accountmigrate: uploadMigrateerrorCode");
        Bundle bundle = new Bundle();
        bundle.putLong("error_code", j);
        LogUtil.bRh_(ERROR_CODE, TAG, bundle, false, "notice cloud migrate account data but return error message." + bundle);
    }

    public static long stringToLong(String str) throws NumberFormatException {
        LogUtil.a(TAG, "accountmigrate: stringToLong");
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            LogUtil.a(TAG, "NumberFormatException Error " + str);
            throw new NumberFormatException(e.getMessage());
        }
    }

    public static void saveAccountInfo(Context context, HiAccountInfo hiAccountInfo) {
        if (context == null) {
            LogUtil.h(TAG, "context == null");
            return;
        }
        if (hiAccountInfo == null) {
            LogUtil.a(TAG, "saveAccountInfo delete result=", Integer.valueOf(SharedPreferenceUtil.getInstance(context).saveAccountInfo(null)));
            return;
        }
        String bFT_ = jdr.bFT_(hiAccountInfo, "1.0");
        int saveAccountInfo = SharedPreferenceUtil.getInstance(context).saveAccountInfo(bFT_);
        Object[] objArr = new Object[8];
        objArr[0] = "saveAccountInfo size=";
        objArr[1] = Integer.valueOf(bFT_.length());
        objArr[2] = ", result=";
        objArr[3] = Integer.valueOf(saveAccountInfo);
        objArr[4] = ", info=";
        objArr[5] = Integer.valueOf(hiAccountInfo.getHuid() != null ? hiAccountInfo.getHuid().length() : 0);
        objArr[6] = ", ver=";
        objArr[7] = "1.0";
        LogUtil.a(TAG, objArr);
    }

    public static HiAccountInfo restoreAccountInfo(Context context) {
        if (context == null) {
            LogUtil.h(TAG, "restoreAccountInfo, context == null");
            return null;
        }
        String restoreAccountInfo = SharedPreferenceUtil.getInstance(context).restoreAccountInfo();
        if (TextUtils.isEmpty(restoreAccountInfo)) {
            LogUtil.a(TAG, "restoreAccountInfo, return null");
            return null;
        }
        HiAccountInfo hiAccountInfo = (HiAccountInfo) jdr.bFW_(restoreAccountInfo, HiAccountInfo.CREATOR, "1.0");
        Object[] objArr = new Object[6];
        int i = 0;
        objArr[0] = "restoreAccountInfo size=";
        objArr[1] = Integer.valueOf(restoreAccountInfo.length());
        objArr[2] = ", info=";
        if (hiAccountInfo != null && hiAccountInfo.getHuid() != null) {
            i = hiAccountInfo.getHuid().length();
        }
        objArr[3] = Integer.valueOf(i);
        objArr[4] = ", ver=";
        objArr[5] = "1.0";
        LogUtil.a(TAG, objArr);
        return hiAccountInfo;
    }
}
