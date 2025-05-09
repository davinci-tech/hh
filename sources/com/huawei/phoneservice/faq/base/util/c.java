package com.huawei.phoneservice.faq.base.util;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class c implements InputFilter {
    private final Pattern e = Pattern.compile("[\\u0733\\u0734\\u0736\\u0737]");

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.e.matcher(charSequence).find()) {
            return "";
        }
        return null;
    }
}
