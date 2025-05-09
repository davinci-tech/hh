package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;

/* loaded from: classes6.dex */
public class mkw {
    public static int b(double d) {
        int i = 0;
        while (d < 1.0d) {
            i++;
            d *= 10.0d;
        }
        return i;
    }

    public static double a(double d, int i) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMaximumFractionDigits(i);
        numberFormat.setMinimumFractionDigits(i);
        try {
            return numberFormat.parse(numberFormat.format(d)).doubleValue();
        } catch (ParseException e) {
            LogUtil.b("PLGACHIEVE_BaseUtil", e.getMessage());
            return 0.0d;
        }
    }

    public static int e(int i) {
        int i2 = 0;
        while (i >= 1) {
            i2++;
            i /= 10;
        }
        return i2;
    }

    public static boolean e() {
        return "1".equals(SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "MAIN_VIP_KEY"));
    }
}
