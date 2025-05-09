package com.huawei.phoneservice.feedbackcommon.utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class EmojiFilter implements InputFilter {
    private static final String EMOJI_CHAR = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]|[\\ud83e\\udd00-\\ud83e\\uddff]|[\\u2300-\\u23ff]|[\\u2500-\\u25ff]|[\\u2100-\\u21ff]|[\\u2b00-\\u2bff]|[\\u203c\\ufe0f]|[\\u303d\\ufe0f]|[\\u24c2\\ufe0f]|[\\u2d06]|[\\u3030]";
    private final Pattern emoji = Pattern.compile(EMOJI_CHAR);

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.emoji.matcher(charSequence).find()) {
            return "";
        }
        return null;
    }
}
