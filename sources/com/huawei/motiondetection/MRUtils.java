package com.huawei.motiondetection;

import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import com.huawei.hms.network.embedded.k;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class MRUtils {
    private static final Uri HWMOTIONS_CONTENT_URI = Uri.parse("content://com.huawei.providers.motions/hwmotions");
    public static final int MOTION_DETECTION_VERSION_CODE = 81301;
    public static final String MOTION_DETECTION_VERSION_NAME = "8.1.301";
    private static final int MOTION_SERVICE_CONFIG_VERSION_CODE = 81301;

    public static int getMotionDetectionVersionCode() {
        return 81301;
    }

    private MRUtils() {
    }

    public static String localProcess(String str) {
        return str == null ? "" : str.toLowerCase(Locale.getDefault()).trim();
    }

    public static void setMotionEnableState(Context context, String str, int i) {
        if (isMotionServiceConfigurable(context)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(k.g, Integer.valueOf(i));
            context.getContentResolver().update(HWMOTIONS_CONTENT_URI, contentValues, "name='" + str + "'", null);
            return;
        }
        Settings.System.putInt(context.getContentResolver(), str, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0069 A[Catch: Exception -> 0x006d, TRY_LEAVE, TryCatch #0 {Exception -> 0x006d, blocks: (B:8:0x000b, B:10:0x0031, B:15:0x0069, B:18:0x003a, B:19:0x003d, B:21:0x005f), top: B:7:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getMotionEnableState(android.content.Context r12, java.lang.String r13) {
        /*
            java.lang.String r0 = "'"
            java.lang.String r1 = "name"
            boolean r2 = isMotionServiceConfigurable(r12)
            r3 = -1
            if (r2 == 0) goto L72
            android.content.ContentResolver r4 = r12.getContentResolver()     // Catch: java.lang.Exception -> L6d
            android.net.Uri r2 = com.huawei.motiondetection.MRUtils.HWMOTIONS_CONTENT_URI     // Catch: java.lang.Exception -> L6d
            r10 = 1
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch: java.lang.Exception -> L6d
            r11 = 0
            r6[r11] = r1     // Catch: java.lang.Exception -> L6d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L6d
            java.lang.String r7 = "enable=1 and name='"
            r5.<init>(r7)     // Catch: java.lang.Exception -> L6d
            r5.append(r13)     // Catch: java.lang.Exception -> L6d
            r5.append(r0)     // Catch: java.lang.Exception -> L6d
            java.lang.String r7 = r5.toString()     // Catch: java.lang.Exception -> L6d
            r8 = 0
            r9 = 0
            r5 = r2
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L6d
            if (r4 == 0) goto L38
            int r5 = r4.getCount()     // Catch: java.lang.Exception -> L6d
            if (r5 <= 0) goto L38
            goto L66
        L38:
            if (r4 == 0) goto L3d
            r4.close()     // Catch: java.lang.Exception -> L6d
        L3d:
            android.content.ContentResolver r5 = r12.getContentResolver()     // Catch: java.lang.Exception -> L6d
            java.lang.String[] r7 = new java.lang.String[r10]     // Catch: java.lang.Exception -> L6d
            r7[r11] = r1     // Catch: java.lang.Exception -> L6d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L6d
            java.lang.String r1 = "enable=0 and name='"
            r12.<init>(r1)     // Catch: java.lang.Exception -> L6d
            r12.append(r13)     // Catch: java.lang.Exception -> L6d
            r12.append(r0)     // Catch: java.lang.Exception -> L6d
            java.lang.String r8 = r12.toString()     // Catch: java.lang.Exception -> L6d
            r9 = 0
            r10 = 0
            r6 = r2
            android.database.Cursor r4 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L6d
            if (r4 == 0) goto L67
            int r12 = r4.getCount()     // Catch: java.lang.Exception -> L6d
            if (r12 <= 0) goto L67
            r10 = r11
        L66:
            r3 = r10
        L67:
            if (r4 == 0) goto L7a
            r4.close()     // Catch: java.lang.Exception -> L6d
            goto L7a
        L6d:
            r12 = move-exception
            r12.printStackTrace()
            goto L7a
        L72:
            android.content.ContentResolver r12 = r12.getContentResolver()
            int r3 = android.provider.Settings.System.getInt(r12, r13, r3)
        L7a:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "getMotionEnableState enabled: "
            r12.<init>(r13)
            r12.append(r3)
            java.lang.String r12 = r12.toString()
            java.lang.String r13 = "MRUtils"
            com.huawei.motiondetection.MRLog.d(r13, r12)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.motiondetection.MRUtils.getMotionEnableState(android.content.Context, java.lang.String):int");
    }

    public static boolean isMotionConfigSupported(Context context, String str) {
        try {
            Cursor query = context.getContentResolver().query(HWMOTIONS_CONTENT_URI, new String[]{"name"}, "support=1 and name='" + str + "'", null, null);
            if (query != null) {
                r0 = query.getCount() > 0;
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r0;
    }

    public static boolean isServiceRunning(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null) {
            MRLog.d("MRUtils", "isServiceRunning serviceList == null");
            return false;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            if (runningServiceInfo.process.equals(str) && runningServiceInfo.pid > 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMotionServiceConfigurable(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(MotionConfig.MOTION_SERVICE_PROCESS, 1);
            if (packageInfo != null) {
                return packageInfo.versionCode >= 81301;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getMotionDetectionVersionName() {
        return MOTION_DETECTION_VERSION_NAME;
    }
}
