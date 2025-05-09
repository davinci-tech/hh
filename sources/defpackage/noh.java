package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.Calendar;

/* loaded from: classes6.dex */
public final class noh {
    public static String a(Context context, int i) {
        if (i == 0) {
            LogUtil.a("BloodSugarStringHelper", "getDifferenceDescription, The strRes is 0. return null");
            return null;
        }
        if (i == R$string.IDS_hw_show_healthdata_bloodsugar_meal_difference_explanation) {
            return context.getString(i, 2, Float.valueOf(3.3f));
        }
        return context.getString(i);
    }

    public static String b(Context context, int i) {
        if (i == 0) {
            LogUtil.a("BloodSugarStringHelper", "getBloodSugarTimePeriodDescription, The strId is 0. return null");
            return null;
        }
        if (i == R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast_desc) {
            return context.getString(i, 8);
        }
        if (i == R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast_desc || i == R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch_desc || i == R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner_desc) {
            return context.getString(i, 2);
        }
        if (i == R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep_desc) {
            return context.getString(i, e(22, 0));
        }
        if (i == R$string.IDS_hw_show_healthdata_bloodsugar_early_morning_desc) {
            return context.getString(i, e(2, 0), e(3, 0));
        }
        return context.getString(i);
    }

    private static String e(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        return UnitUtil.a("HH:mm", calendar.getTimeInMillis());
    }

    public static String e(float f) {
        return jed.b(new BigDecimal(String.valueOf(Math.abs(f))).doubleValue(), 1, 1);
    }
}
