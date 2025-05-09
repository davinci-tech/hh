package defpackage;

import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ui.commonui.utils.HarmonyOsTypefaceSpan;
import health.compact.a.LogUtil;

/* loaded from: classes6.dex */
public class nsi {
    public static void cKH_(SpannableString spannableString, String str, CharacterStyle characterStyle) {
        if (spannableString == null || TextUtils.isEmpty(str)) {
            LogUtil.a("R_SpannableStringUtils", "setSpan source ", spannableString, " key ", str);
            return;
        }
        String spannableString2 = spannableString.toString();
        if (TextUtils.isEmpty(spannableString2)) {
            LogUtil.a("R_SpannableStringUtils", "setSpan sourceText ", spannableString2);
            return;
        }
        int indexOf = spannableString2.indexOf(str);
        if (indexOf < 0) {
            LogUtil.a("R_SpannableStringUtils", "setSpan start ", Integer.valueOf(indexOf));
        } else {
            spannableString.setSpan(characterStyle, indexOf, str.length() + indexOf, 17);
        }
    }

    public static int cKD_(SpannableString spannableString, String str) {
        if (spannableString == null || TextUtils.isEmpty(str)) {
            LogUtil.a("R_SpannableStringUtils", "setSpan source ", spannableString, " key ", str);
            return -1;
        }
        String spannableString2 = spannableString.toString();
        if (TextUtils.isEmpty(spannableString2)) {
            LogUtil.a("R_SpannableStringUtils", "setSpan sourceText ", spannableString2);
            return -1;
        }
        int indexOf = spannableString2.indexOf(str);
        if (indexOf >= 0) {
            return indexOf;
        }
        LogUtil.a("R_SpannableStringUtils", "setSpan start ", Integer.valueOf(indexOf));
        return -1;
    }

    public static void cKG_(SpannableString spannableString, int i, int i2, CharacterStyle characterStyle) {
        if (spannableString == null || i < 0 || i2 < i || i2 > spannableString.length()) {
            LogUtil.a("R_SpannableStringUtils", "setSpan source:", spannableString, " start：", Integer.valueOf(i), " end:", Integer.valueOf(i2));
            return;
        }
        String spannableString2 = spannableString.toString();
        if (TextUtils.isEmpty(spannableString2)) {
            LogUtil.a("R_SpannableStringUtils", "setSpan sourceText ", spannableString2);
        } else {
            spannableString.setSpan(characterStyle, i, i2, 17);
        }
    }

    public static void cKI_(SpannableString spannableString, String str, int i) {
        cKH_(spannableString, str, new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.e(), i)));
    }

    public static void cKJ_(SpannableString spannableString, String str, int i) {
        cKH_(spannableString, str, new AbsoluteSizeSpan(i));
    }

    public static void cKK_(SpannableString spannableString, String str, int i) {
        cKH_(spannableString, str, new AbsoluteSizeSpan(i, true));
    }

    public static void cKL_(SpannableString spannableString, String str, int i) {
        cKH_(spannableString, str, new TypefaceSpan(nsf.h(i)));
    }

    public static void cKF_(SpannableString spannableString, String str) {
        if (Build.VERSION.SDK_INT >= 28) {
            cKH_(spannableString, str, new HarmonyOsTypefaceSpan(nsk.cKN_()));
        } else {
            cKH_(spannableString, str, new nmj(nsk.cKN_()));
        }
    }

    public static void cKE_(SpannableString spannableString, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            cKG_(spannableString, i, i2, new HarmonyOsTypefaceSpan(nsk.cKN_()));
        } else {
            cKG_(spannableString, i, i2, new nmj(nsk.cKN_()));
        }
    }
}
