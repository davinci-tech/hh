package com.huawei.hms.opendevice;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class e {

    /* renamed from: a, reason: collision with root package name */
    private static String f5652a;

    private static String a() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            bufferedReader.close();
                            inputStreamReader.close();
                            fileInputStream.close();
                            return "";
                        }
                        String trim = readLine.trim();
                        bufferedReader.close();
                        inputStreamReader.close();
                        fileInputStream.close();
                        return trim;
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            HMSLog.e("CommFun", "get current app processes IOException!");
            return "";
        } catch (Exception e) {
            HMSLog.e("CommFun", "get current app processes exception!" + e.getMessage());
            return "";
        }
    }

    public static boolean b() {
        int i = HwBuildEx.VERSION.EMUI_SDK_INT;
        HMSLog.d("CommFun", "Emui Api Level:" + i);
        return i > 0;
    }

    public static long d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.huawei.android.pushagent", 16384).versionCode;
        } catch (Exception unused) {
            HMSLog.e("CommFun", "get nc versionCode error");
            return -1L;
        }
    }

    public static boolean e(Context context) {
        return b() && HwBuildEx.VERSION.EMUI_SDK_INT < 21 && d(context) < 110001400;
    }

    public static String c(Context context) {
        String str = context.createDeviceProtectedStorageContext().getDataDir() + "";
        if (TextUtils.isEmpty(str)) {
            HMSLog.e("CommFun", "get storage root path of the current user failed.");
        }
        return str;
    }

    private static String b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return "";
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() != 0) {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid && runningAppProcessInfo.processName != null) {
                    HMSLog.i("CommFun", "info.pid -> " + runningAppProcessInfo.pid + ", info.processName -> " + runningAppProcessInfo.processName);
                    return runningAppProcessInfo.processName;
                }
            }
            return "";
        }
        HMSLog.w("CommFun", "get running app processes null!");
        return "";
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f5652a)) {
            return f5652a;
        }
        String b = b(context);
        f5652a = b;
        if (!TextUtils.isEmpty(b)) {
            return f5652a;
        }
        String a2 = a();
        f5652a = a2;
        return a2;
    }
}
