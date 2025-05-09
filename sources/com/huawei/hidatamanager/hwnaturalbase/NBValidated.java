package com.huawei.hidatamanager.hwnaturalbase;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.hidatamanager.util.LogUtils;

/* loaded from: classes4.dex */
public class NBValidated {
    private static final String ODMF_PACKAGE_NAME = "com.huawei.nb.service";
    private static final String TAG = "NBValidated";

    public static boolean isOdmfInstalled(Context context) {
        boolean checkApkExist;
        synchronized (NBValidated.class) {
            checkApkExist = checkApkExist(context, ODMF_PACKAGE_NAME);
        }
        return checkApkExist;
    }

    private static boolean checkApkExist(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            try {
                return context.getPackageManager().getApplicationInfo(str, 0) != null;
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtils.w(TAG, "depended package:" + str + " does n't exist!");
            }
        }
        return false;
    }
}
