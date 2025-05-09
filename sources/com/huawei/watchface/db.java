package com.huawei.watchface;

import com.huawei.secure.android.common.encrypt.hash.SHA;
import com.huawei.watchface.utils.HwLog;
import java.util.Locale;

/* loaded from: classes7.dex */
public class db {
    public static String a(String str) {
        HwLog.i("HashCalculator", "getStringHash256 enter");
        if (str == null) {
            HwLog.i("HashCalculator", "getStringHash256 name is null");
            return null;
        }
        return SHA.sha256Encrypt(str).toUpperCase(Locale.ENGLISH);
    }
}
