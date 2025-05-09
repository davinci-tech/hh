package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class mlh {
    public static String d(Context context, int i) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getMonthDescByTimestamp: context is null");
            return "";
        }
        switch (i) {
            case 1:
                return context.getResources().getString(R.string._2130840914_res_0x7f020d52);
            case 2:
                return context.getResources().getString(R.string._2130840915_res_0x7f020d53);
            case 3:
                return context.getResources().getString(R.string._2130840916_res_0x7f020d54);
            case 4:
                return context.getResources().getString(R.string._2130840917_res_0x7f020d55);
            case 5:
                return context.getResources().getString(R.string._2130840918_res_0x7f020d56);
            case 6:
                return context.getResources().getString(R.string._2130840919_res_0x7f020d57);
            case 7:
                return context.getResources().getString(R.string._2130840920_res_0x7f020d58);
            case 8:
                return context.getResources().getString(R.string._2130840921_res_0x7f020d59);
            case 9:
                return context.getResources().getString(R.string._2130840922_res_0x7f020d5a);
            case 10:
                return context.getResources().getString(R.string._2130840923_res_0x7f020d5b);
            case 11:
                return context.getResources().getString(R.string._2130840924_res_0x7f020d5c);
            case 12:
                return context.getResources().getString(R.string._2130840925_res_0x7f020d5d);
            default:
                return "";
        }
    }

    public static CharSequence c(Context context, int i) {
        if (context == null || i < 0) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getAvgDailyStepsDesc: context is null");
            return "";
        }
        if (i <= 3000) {
            String string = context.getResources().getString(R.string._2130840932_res_0x7f020d64);
            return LanguageUtil.m(context) ? d(string, "多走走") : string;
        }
        if (i <= 6000) {
            String string2 = context.getResources().getString(R.string._2130840933_res_0x7f020d65);
            return LanguageUtil.m(context) ? d(string2, "脚下的每一步") : string2;
        }
        String string3 = context.getResources().getString(R.string._2130840934_res_0x7f020d66);
        return LanguageUtil.m(context) ? d(string3, "生活的乐趣") : string3;
    }

    public static CharSequence d(String str, String str2) {
        return a(str, str2, BaseApplication.getContext().getResources().getColor(R.color._2131299008_res_0x7f090ac0));
    }

    public static CharSequence a(String str, String str2, int i) {
        return e(str, str2, i, 0, "");
    }

    public static CharSequence e(String str, String str2, int i, int i2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("PLGACHIEVE_ReportDescUtil", "getHighlightCharSequence() content or highlightText is empty.");
            return str;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf < 0) {
            LogUtil.a("PLGACHIEVE_ReportDescUtil", "getHighlightCharSequence() content not contains highlightText.");
            return str;
        }
        int length = str2.length() + indexOf;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), indexOf, length, 34);
        if (i2 > 0) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i2), indexOf, length, 34);
        }
        if (!TextUtils.isEmpty(str3)) {
            spannableStringBuilder.setSpan(new TypefaceSpan(str3), indexOf, length, 34);
        }
        return spannableStringBuilder;
    }

    public static CharSequence e(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("PLGACHIEVE_ReportDescUtil", "getTextSizeSpanString() content or highlightText is empty.");
            return str;
        }
        int indexOf = str.indexOf(str2);
        if (indexOf < 0) {
            LogUtil.a("PLGACHIEVE_ReportDescUtil", "getTextSizeSpanString() content not contains scaleText.");
            return str;
        }
        int length = str2.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i), indexOf, length + indexOf, 34);
        return spannableStringBuilder;
    }

    public static String e(Context context, int i, int i2, int i3) {
        if (context == null) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getComplianceDaysDesc: context is null");
            return "";
        }
        int i4 = i2 - i3;
        String quantityString = context.getResources().getQuantityString(R.plurals._2130903164_res_0x7f03007c, i2, UnitUtil.e(i2, 1, 0));
        String quantityString2 = context.getResources().getQuantityString(R.plurals._2130903164_res_0x7f03007c, Math.abs(i4), UnitUtil.e(Math.abs(i4), 1, 0));
        if (i4 > 0) {
            return context.getResources().getString(i == 0 ? R.string._2130840984_res_0x7f020d98 : R.string._2130840986_res_0x7f020d9a, quantityString, quantityString2);
        }
        if (i4 < 0) {
            return context.getResources().getString(i == 0 ? R.string._2130840983_res_0x7f020d97 : R.string._2130840985_res_0x7f020d99, quantityString, quantityString2);
        }
        return context.getResources().getQuantityString(i == 0 ? R.plurals._2130903190_res_0x7f030096 : R.plurals._2130903191_res_0x7f030097, i2, Integer.valueOf(i2));
    }

    public static CharSequence e(Context context, int i, int i2) {
        String string;
        if (context == null || i2 <= 0) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getMedalTitleDesc: context is null or medalNumbers is ", Integer.valueOf(i2));
            return "";
        }
        if (i2 == 1) {
            String string2 = context.getResources().getString(R.string._2130840926_res_0x7f020d5e);
            return LanguageUtil.m(context) ? d(string2, "努力") : string2;
        }
        if (i2 == 2) {
            String string3 = context.getResources().getString(R.string._2130840927_res_0x7f020d5f);
            return LanguageUtil.m(context) ? d(string3, "突破") : string3;
        }
        if (i == 0) {
            string = context.getResources().getString(R.string._2130840929_res_0x7f020d61);
        } else {
            string = context.getResources().getString(R.string._2130840928_res_0x7f020d60);
        }
        return LanguageUtil.m(context) ? d(string, "荣誉满满") : string;
    }

    public static CharSequence a(Context context, int i, int i2) {
        if (context == null || i2 < 0) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getExerciseDescByDays: context is null or exerciseCount is ", Integer.valueOf(i2));
            return "";
        }
        int i3 = i == 0 ? 4 : 1;
        int i4 = i == 0 ? 16 : 4;
        if (i2 <= i3) {
            String string = context.getResources().getString(R.string._2130840942_res_0x7f020d6e);
            return LanguageUtil.m(context) ? d(string, "开始新的挑战") : string;
        }
        if (i2 <= i4) {
            String string2 = context.getResources().getString(R.string._2130840943_res_0x7f020d6f);
            return LanguageUtil.m(context) ? d(string2, "成就更好的自己") : string2;
        }
        String string3 = context.getResources().getString(R.string._2130840944_res_0x7f020d70);
        return LanguageUtil.m(context) ? d(string3, "变成习惯") : string3;
    }

    public static Drawable cks_(Context context, int i, float f) {
        if (context == null || f < 0.0f) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getDrawableByExerciseTime: context is null or exerciseDays is ", Float.valueOf(f));
            return null;
        }
        int i2 = i == 0 ? 120 : 30;
        int i3 = i == 0 ? 600 : 150;
        if (f - i2 <= 1.0E-6d) {
            return ckt_(ContextCompat.getDrawable(context, R.drawable._2131430681_res_0x7f0b0d19));
        }
        if (f - i3 <= 1.0E-6d) {
            return ckt_(ContextCompat.getDrawable(context, R.drawable._2131430684_res_0x7f0b0d1c));
        }
        return ckt_(ContextCompat.getDrawable(context, R.drawable._2131430686_res_0x7f0b0d1e));
    }

    public static Drawable ckr_(Context context, int i, int i2) {
        if (context == null || i2 < 0) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getDrawableByCalories: context is null or exerciseDays is ", Integer.valueOf(i2));
            return null;
        }
        int i3 = i == 0 ? 5600 : KakaConstants.TASK_RUN_BEHAVIOR;
        int i4 = i == 0 ? 8400 : 2100;
        if (i2 <= i3) {
            return ckt_(ContextCompat.getDrawable(context, R.drawable._2131430682_res_0x7f0b0d1a));
        }
        if (i2 <= i4) {
            return ckt_(ContextCompat.getDrawable(context, R.drawable._2131430683_res_0x7f0b0d1b));
        }
        return ckt_(ContextCompat.getDrawable(context, R.drawable._2131430680_res_0x7f0b0d18));
    }

    private static Drawable ckt_(Drawable drawable) {
        return LanguageUtil.bc(BaseApplication.getContext()) ? nrz.cKm_(BaseApplication.getContext(), drawable) : drawable;
    }

    public static CharSequence e(Context context, int i) {
        if (context == null || i < 0) {
            LogUtil.h("PLGACHIEVE_ReportDescUtil", "getBreakInfoDesc: context is null or breakInfoSize is ", Integer.valueOf(i));
            return "";
        }
        if (i <= 1) {
            String string = context.getResources().getString(R.string._2130840972_res_0x7f020d8c);
            return LanguageUtil.m(context) ? d(string, "潜力无限") : string;
        }
        if (i == 2 || i == 3) {
            String string2 = context.getResources().getString(R.string._2130840973_res_0x7f020d8d, String.valueOf(i));
            return LanguageUtil.m(context) ? d(string2, String.valueOf(i)) : string2;
        }
        String string3 = context.getResources().getString(R.string._2130840974_res_0x7f020d8e);
        return LanguageUtil.m(context) ? d(string3, "更好的自己") : string3;
    }
}
