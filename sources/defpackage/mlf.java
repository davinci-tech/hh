package defpackage;

import android.content.Context;
import com.huawei.health.R;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes9.dex */
public class mlf {
    public static String a(Context context, int i) {
        return context == null ? "" : context.getResources().getQuantityString(R.plurals._2130903167_res_0x7f03007f, i, UnitUtil.e(i, 1, 0));
    }

    public static String b(double d, Context context) {
        if (Utils.o()) {
            return e(d, context);
        }
        return e(mlg.c(d / 1000.0d), context);
    }

    public static String d(double d, int i, Context context) {
        switch (i) {
            case 12:
            case 13:
                return context.getResources().getQuantityString(R.plurals._2130903186_res_0x7f030092, (int) d, UnitUtil.e(d, 1, 0));
            case 14:
                return context.getResources().getQuantityString(R.plurals._2130903187_res_0x7f030093, (int) d, UnitUtil.e(d, 1, 0));
            case 15:
                return context.getResources().getQuantityString(R.plurals._2130903188_res_0x7f030094, (int) d, UnitUtil.e(d, 1, 0));
            default:
                return "";
        }
    }

    public static String e(double d, Context context) {
        if (context == null) {
            return "";
        }
        if (UnitUtil.h()) {
            return context.getString(R.string._2130841422_res_0x7f020f4e, UnitUtil.e(UnitUtil.e(d, 3), 1, 2));
        }
        return context.getString(R.string._2130840711_res_0x7f020c87, UnitUtil.e(d, 1, 2));
    }

    public static String d(double d) {
        double c = mlg.c(d / 1000.0d);
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(c, 3), 1, 2);
        }
        return UnitUtil.e(c, 1, 2);
    }
}
