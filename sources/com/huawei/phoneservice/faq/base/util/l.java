package com.huawei.phoneservice.faq.base.util;

import android.text.TextUtils;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class l {
    public static boolean e(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence) || "\"\"".equals(charSequence) || Constants.NULL.equals(charSequence)) {
            return true;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (charAt != ' ' && charAt != '\t' && charAt != '\r' && charAt != '\n') {
                return false;
            }
        }
        return true;
    }
}
