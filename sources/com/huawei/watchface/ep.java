package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.secure.android.common.anonymization.Anonymizer;

/* loaded from: classes7.dex */
public class ep {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String maskCommonString = Anonymizer.maskCommonString(str, 0, str.length());
        return TextUtils.isEmpty(maskCommonString) ? maskCommonString : maskCommonString.replaceAll("\\*+", "*");
    }

    public static String b(String str) {
        String maskCommonString;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.indexOf("@") > 0) {
            maskCommonString = Anonymizer.maskCommonString(str, str.indexOf("@") + 1, 1);
        } else {
            maskCommonString = Anonymizer.maskCommonString(str, 0, str.length());
        }
        return TextUtils.isEmpty(maskCommonString) ? maskCommonString : maskCommonString.replaceAll("\\*+", "*");
    }
}
