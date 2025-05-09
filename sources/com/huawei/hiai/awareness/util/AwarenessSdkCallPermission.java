package com.huawei.hiai.awareness.util;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public final class AwarenessSdkCallPermission {
    private static final String TAG = "AwarenessSdkCallPermission";

    private AwarenessSdkCallPermission() {
    }

    public static boolean checkAppTrust(Context context, int i) {
        if (context == null) {
            LogUtil.e(TAG, "CheckAppTrust failed, context is null");
            return false;
        }
        List<String> callingPkgListByUid = getCallingPkgListByUid(context, i);
        if (!checkIfExistAwarenessPkgName(callingPkgListByUid)) {
            LogUtil.e(TAG, "CheckAppTrust failed, awareness is not running");
            return false;
        }
        if (checkIsSameSignatureOfHiAi(callingPkgListByUid, context)) {
            LogUtil.i(TAG, "CheckAppTrust success, the signature is trust");
            return true;
        }
        LogUtil.e(TAG, "The calling process's packageSignatures are not trust");
        return false;
    }

    private static boolean checkIfExistAwarenessPkgName(List<String> list) {
        return list != null && list.contains("com.huawei.hiai");
    }

    private static boolean checkIsSameSignatureOfHiAi(List<String> list, Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.e(TAG, "packageManager == null");
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (packageManager.checkSignatures("com.huawei.hiai", it.next()) == 0) {
                return true;
            }
        }
        return false;
    }

    private static List<String> getCallingPkgListByUid(Context context, int i) {
        String[] packagesForUid;
        if (context == null) {
            return Collections.emptyList();
        }
        List<String> emptyList = Collections.emptyList();
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || (packagesForUid = packageManager.getPackagesForUid(i)) == null) ? emptyList : Arrays.asList(packagesForUid);
        } catch (RuntimeException unused) {
            LogUtil.e(TAG, "Failed to get pkg list of uid: " + i);
            return emptyList;
        }
    }
}
