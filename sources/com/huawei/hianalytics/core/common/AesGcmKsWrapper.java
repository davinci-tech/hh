package com.huawei.hianalytics.core.common;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesGcmKS;

/* loaded from: classes4.dex */
public class AesGcmKsWrapper {
    public static final String TAG = "AesGcmKsWrapper";

    public static String encrypt(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return AesGcmKS.encrypt(str, str2);
        } catch (Exception e) {
            HiLog.e(TAG, "encrypt failed: " + e.getMessage());
            return "";
        }
    }

    public static String decrypt(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return AesGcmKS.decrypt(str, str2);
        } catch (Exception e) {
            HiLog.e(TAG, "decrypt failed: " + e.getMessage());
            return "";
        }
    }
}
