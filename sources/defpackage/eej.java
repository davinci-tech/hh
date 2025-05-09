package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes3.dex */
public class eej {
    public static String d(double d) {
        return UnitUtil.e(d, 1, 0);
    }

    public static SpannableString agX_(Context context, String str, String str2) {
        return agY_(context, str, str2, R.dimen._2131365076_res_0x7f0a0cd4);
    }

    public static SpannableString agY_(Context context, String str, String str2, int i) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("ActiveUtil", "getSpannableString context is null or part and content is empty");
            return null;
        }
        SpannableString spannableString = new SpannableString(str2);
        int indexOf = str2.indexOf(str);
        if (indexOf == -1) {
            LogUtil.h("ActiveUtil", "getSpannableString content not contains part");
            return spannableString;
        }
        int length = str.length() + indexOf;
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color._2131299236_res_0x7f090ba4)), indexOf, length, 34);
        spannableString.setSpan(new TypefaceSpan(context.getString(R$string.textFontFamilyMedium)), indexOf, length, 34);
        spannableString.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(i)), indexOf, length, 34);
        return spannableString;
    }

    public static SpannableString agZ_(Context context, String str, String str2, int i) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("ActiveUtil", "getTextSpannableString context is null or part and content is empty");
            return null;
        }
        SpannableString spannableString = new SpannableString(str2);
        int lastIndexOf = str2.lastIndexOf(str);
        if (lastIndexOf == -1) {
            LogUtil.h("ActiveUtil", "getSpannableString content not contains part");
            return spannableString;
        }
        int length = str.length() + lastIndexOf;
        if (lastIndexOf >= 0 && lastIndexOf < length && length <= str2.length()) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color._2131299236_res_0x7f090ba4)), lastIndexOf, length, 34);
            spannableString.setSpan(new TypefaceSpan(context.getString(R$string.textFontFamilyMedium)), lastIndexOf, length, 34);
            spannableString.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(i)), lastIndexOf, length, 34);
        }
        return spannableString;
    }

    public static SpannableString agW_(Context context, String str, String str2, int i) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("ActiveUtil", "getColorSpannableString context is null or part and content is empty");
            return null;
        }
        SpannableString spannableString = new SpannableString(str2);
        int lastIndexOf = str2.lastIndexOf(str);
        if (lastIndexOf == -1) {
            LogUtil.h("ActiveUtil", "getColorSpannableString content not contains part");
            return spannableString;
        }
        int length = str.length() + lastIndexOf;
        if (lastIndexOf >= 0 && lastIndexOf < length && length <= str2.length()) {
            spannableString.setSpan(new ForegroundColorSpan(nrn.d(i)), lastIndexOf, length, 34);
            spannableString.setSpan(new TypefaceSpan(context.getString(R$string.textFontFamilyMedium)), lastIndexOf, length, 34);
            spannableString.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6)), lastIndexOf, length, 34);
        }
        return spannableString;
    }

    public static SpannableString agV_(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        int length = str2.length();
        int indexOf = str.indexOf(str2);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(i);
        if (indexOf == -1) {
            length = str.length();
            indexOf = 0;
        }
        spannableString.setSpan(absoluteSizeSpan, indexOf, length + indexOf, 17);
        return spannableString;
    }

    public static boolean c(edr edrVar, edr edrVar2) {
        return edrVar != null && edrVar2 != null && edrVar.t() == edrVar2.t() && edrVar.y() == edrVar2.y() && edrVar.p() == edrVar2.p() && edrVar.i() == edrVar2.i() && edrVar.f() == edrVar2.f() && edrVar.s() == edrVar2.s() && edrVar.q() == edrVar2.q() && edrVar.e() == edrVar2.e() && edrVar.c() == edrVar2.c() && edrVar.g() == edrVar2.g() && edrVar.h() == edrVar2.h();
    }
}
