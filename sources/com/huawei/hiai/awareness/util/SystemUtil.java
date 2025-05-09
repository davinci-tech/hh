package com.huawei.hiai.awareness.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.huawei.hiai.awareness.service.AwarenessFence;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class SystemUtil {
    private static final String AWARENESS_NAME = "awareness";
    private static final String COLON_KEY = ":";
    private static final int INIT_STRING_BUFFER_SIZE = 128;
    private static final String SEMI_COLON_KEY = ";";
    private static final String TAG = "SystemUtil";
    private static boolean isHiAiEngineInstalled = false;

    private SystemUtil() {
    }

    public static boolean isHiAiEngineApkInstalled(Context context) {
        if (isHiAiEngineInstalled) {
            LogUtil.i(TAG, "isHiAiEngineApkInstalled is true");
            return true;
        }
        if (context == null) {
            LogUtil.e(TAG, "isHiAiEngineApkInstalled context is null");
            return false;
        }
        boolean checkApkExist = Utils.checkApkExist(context, "com.huawei.hiai");
        isHiAiEngineInstalled = checkApkExist;
        return checkApkExist;
    }

    public static String parseAwareness2String(AwarenessFence awarenessFence) {
        if (awarenessFence == null) {
            return "";
        }
        String fenceKey = awarenessFence.getFenceKey();
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("1:1;");
        stringBuffer.append(fenceKey);
        LogUtil.i(TAG, "parseAwareness2String() fenceStr :" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public static boolean isAwarenessPluginInstalled(Context context) {
        LogUtil.i(TAG, "isAwarenessPluginInstalled");
        if (context == null) {
            LogUtil.e(TAG, "isAwarenessPluginInstalled, context is null");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.e(TAG, "isAwarenessPluginInstalled, packageManager is null");
            return false;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.huawei.hiai", 0);
            if (packageInfo == null) {
                LogUtil.e(TAG, "isAwarenessPluginInstalled, packageInfo is null");
                return false;
            }
            String[] strArr = packageInfo.splitNames;
            if (strArr == null) {
                LogUtil.e(TAG, "isAwarenessPluginInstalled, packageInfo.splitNames is null");
                return false;
            }
            return Arrays.asList(strArr).contains(AWARENESS_NAME);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "isAwarenessPluginInstalled, NameNotFoundException: " + e.getMessage());
            return false;
        }
    }
}
