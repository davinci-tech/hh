package defpackage;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigDecimal;
import java.util.Locale;

/* loaded from: classes9.dex */
public class mln {
    public static SpannableString cky_(String str, String str2, int i, int i2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginAchievement_TextViewUtil", "acquireSpannableStrUtil: content/contentStr empty");
            return new SpannableString("");
        }
        SpannableString spannableString = new SpannableString(str2);
        int indexOf = str2.indexOf(str);
        if (indexOf < 0) {
            return spannableString;
        }
        spannableString.setSpan(new ForegroundColorSpan(i2), indexOf, str.length() + indexOf, 18);
        spannableString.setSpan(new AbsoluteSizeSpan(i), indexOf, str.length() + indexOf, 18);
        return spannableString;
    }

    public static String d(double d) {
        LogUtil.a("PluginAchievement_TextViewUtil", "enter getGreatWallPercent() :");
        double d2 = d / 21196.18d;
        if (d2 >= 1.0d) {
            return String.format(Locale.ROOT, "%.2f", Double.valueOf(d2));
        }
        String plainString = new BigDecimal(String.valueOf(d2)).toPlainString();
        int[] e = e(plainString.substring(plainString.indexOf(".") + 1));
        int i = e[0];
        int i2 = e[1];
        if (i2 == 0 || i == 0) {
            return "0/21196";
        }
        double d3 = i2;
        int e2 = e(i, (int) Math.pow(10.0d, d3));
        double pow = Math.pow(10.0d, d3) / e2;
        if (pow > 21196.0d && d > 0.0d) {
            return "1/" + ((int) (21196.18d / d));
        }
        String str = (i / e2) + "/" + ((int) pow);
        LogUtil.c("PluginAchievement_TextViewUtil", "percent = ", str);
        return str;
    }

    private static int e(int i, int i2) {
        int i3 = i % i2;
        return i3 == 0 ? i2 : e(i2, i3);
    }

    private static int[] e(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length() && str.startsWith("0"); i2++) {
            try {
                str = str.substring(1);
                i++;
            } catch (NumberFormatException e) {
                LogUtil.b("PluginAchievement_TextViewUtil", e.getMessage());
            }
        }
        LogUtil.a("PluginAchievement_TextViewUtil", "value = ", str, ",count = ", Integer.valueOf(i));
        int i3 = 0;
        int i4 = 0;
        while (i3 < str.length()) {
            int parseInt = Integer.parseInt(String.valueOf(str.charAt(i3)));
            if (i4 != 0) {
                return new int[]{(i4 * 10) + parseInt, i3 + 1 + i};
            }
            i3++;
            i4 = parseInt;
        }
        return new int[]{0, 0};
    }
}
