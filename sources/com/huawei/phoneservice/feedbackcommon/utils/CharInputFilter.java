package com.huawei.phoneservice.feedbackcommon.utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class CharInputFilter implements InputFilter {
    private Pattern pattern;

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        Pattern pattern;
        if (charSequence.toString() == null || charSequence.toString().trim().length() <= 0) {
            pattern = this.pattern;
        } else {
            pattern = this.pattern;
            charSequence = charSequence.toString().trim();
        }
        if (pattern.matcher(charSequence).find()) {
            return "";
        }
        return null;
    }

    public CharInputFilter(String str) {
        this.pattern = Pattern.compile(str);
    }

    public CharInputFilter() {
        this("[&<>\"'()]");
    }
}
