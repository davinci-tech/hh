package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import com.huawei.health.R;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final class ggk {
    public static SpannableString aMr_(Context context, double d) {
        double d2 = d / 1000.0d;
        return ffy.awT_(context, "\\d|[/]", ffy.b(R.plurals._2130903108_res_0x7f030044, (int) d2, UnitUtil.e(d2, 1, 0)), R.style.sug_detail_bigsize, R.style.sug_detail_smasize);
    }

    public static SpannableString aMs_(Context context, double d) {
        double d2 = d / 60.0d;
        return ffy.awT_(context, "\\d|[/]", ffy.b(R.plurals._2130903305_res_0x7f030109, (int) d2, UnitUtil.e(d2, 1, 0)), R.style.sug_detail_bigsize, R.style.sug_detail_smasize);
    }

    public static SpannableString aMt_(Context context, double d) {
        double d2 = d / 60.0d;
        return ffy.awT_(context, "\\d|[/]", ffy.b(R.plurals._2130903305_res_0x7f030109, (int) d2, UnitUtil.e(d2, 1, 0)), R.style.sug_detail_bigsize_no_finished_course, R.style.sug_detail_smasize_no_finished_course);
    }

    public static String b(Context context, int i, int i2, int i3) {
        if (LanguageUtil.bc(context)) {
            return ffy.d(context, R.string._2130848850_res_0x7f022c52, UnitUtil.e(Math.round((i2 * 1.0f) / 1000.0f), 1, 0), UnitUtil.e(i, 1, 0));
        }
        if (i3 == 0) {
            if (i2 < 1000) {
                return ffy.d(context, R.string._2130851540_res_0x7f0236d4, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0));
            }
            return ffy.d(context, R.string._2130851539_res_0x7f0236d3, UnitUtil.e(i, 1, 0), UnitUtil.e((i2 * 1.0f) / 1000.0f, 1, 1));
        }
        if (i3 == 1) {
            int round = Math.round((i2 * 1.0f) / 1000.0f);
            int i4 = round / 60;
            if (i4 >= 1) {
                return ffy.d(context, R.string._2130851538_res_0x7f0236d2, UnitUtil.e(i, 1, 0), UnitUtil.e(i4, 1, 0), UnitUtil.e(round % 60, 1, 0));
            }
            return ffy.d(context, R.string._2130848849_res_0x7f022c51, UnitUtil.e(i, 1, 0), UnitUtil.e(round, 1, 0));
        }
        return ffy.d(context, R.string._2130848849_res_0x7f022c51, UnitUtil.e(i, 1, 0), UnitUtil.e(Math.round((i2 * 1.0f) / 1000.0f), 1, 0));
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = Pattern.compile("\\d").matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }
}
