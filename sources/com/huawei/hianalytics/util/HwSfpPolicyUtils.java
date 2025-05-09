package com.huawei.hianalytics.util;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.fileprotect.HwSfpPolicyManager;
import com.huawei.hianalytics.core.log.HiLog;
import java.io.File;

/* loaded from: classes4.dex */
public class HwSfpPolicyUtils {
    public static void setPublicProtectionLevel(Context context, String str, int i) {
        if (context != null && context.isDeviceProtectedStorage()) {
            b(context, str, "S0", i);
            HiLog.i("HWPU", "haJsSdk file LABEL_VALUE :S0");
        }
    }

    public static void b(Context context, String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        try {
            HwSfpPolicyManager hwSfpPolicyManager = HwSfpPolicyManager.getDefault();
            if (hwSfpPolicyManager == null) {
                return;
            }
            String label = hwSfpPolicyManager.getLabel(context, str, "SecurityLevel");
            if (TextUtils.isEmpty(label)) {
                int label2 = hwSfpPolicyManager.setLabel(context, str, "SecurityLevel", str2, i);
                if (label2 != 0) {
                    HiLog.i("HWPU", "setProtectionLabel failed. result:" + label2);
                    return;
                }
                return;
            }
            if (TextUtils.equals(label, str2)) {
                return;
            }
            HiLog.i("HWPU", "setProtectionLabel failed, currentLabel: " + label + ", expectedLabel: " + str2);
        } catch (Throwable th) {
            HiLog.i("HWPU", "setProtectionLabel failed: " + th.getMessage());
        }
    }

    public static void a(Context context, String str, String str2, int i) {
        File databasePath = context.getDatabasePath(str);
        if (databasePath == null || !databasePath.exists()) {
            HiLog.e("HWPU", "file does not exist");
        } else {
            b(context, databasePath.getPath(), str2, i);
        }
    }
}
