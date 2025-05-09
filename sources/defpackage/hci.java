package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;

/* loaded from: classes4.dex */
public class hci {
    public static SpannableString aZU_(Context context, String str, int i) {
        SpannableString spannableString = new SpannableString(str);
        aZT_(context, spannableString, i);
        return spannableString;
    }

    private static hcg e(Context context, int i, int i2) {
        Drawable drawable = context.getResources().getDrawable(i);
        int i3 = (i2 * 13) / 10;
        drawable.setBounds(0, 0, i3, i3);
        return new hcg(drawable);
    }

    private static void aZT_(Context context, SpannableString spannableString, int i) {
        for (int i2 = 0; i2 < spannableString.length(); i2++) {
            if (spannableString.charAt(i2) == '[') {
                int i3 = i2 + 4;
                if (i3 > spannableString.length()) {
                    return;
                }
                int c = hch.c(spannableString.subSequence(i2, i3).toString());
                if (c > 0) {
                    spannableString.setSpan(e(context, c, i), i2, i3, 33);
                }
            }
        }
    }
}
