package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class gwz {
    public static String d(Context context, int i) {
        if (i > 0) {
            return UnitUtil.e(i, 1, 0);
        }
        return context.getString(R.string._2130850262_res_0x7f0231d6);
    }

    public static String a(Context context, String str) {
        return TextUtils.isEmpty(str) ? context.getString(R.string._2130850262_res_0x7f0231d6) : str;
    }

    public static String d(Context context) {
        if (UnitUtil.h()) {
            return context.getResources().getString(R.string._2130844079_res_0x7f0219af);
        }
        return context.getResources().getString(R.string._2130844382_res_0x7f021ade);
    }

    public static String e(Context context, int i) {
        if (i > 0) {
            return hji.c(i);
        }
        return context.getString(R.string._2130850262_res_0x7f0231d6);
    }

    public static String b(Context context, int i) {
        if (i == 274) {
            if (UnitUtil.h()) {
                return String.format(context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100), new Object[0]);
            }
            return String.format(String.format(context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500), new Object[0]), new Object[0]);
        }
        if (i == 266 || i == 262) {
            return UnitUtil.h() ? context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100) : context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
        }
        if (UnitUtil.h()) {
            return "/" + context.getString(R.string._2130844081_res_0x7f0219b1);
        }
        return "/" + context.getString(R.string._2130844082_res_0x7f0219b2);
    }

    public static String c(Context context, int i) {
        if (i == 274) {
            if (UnitUtil.h()) {
                return context.getString(R.string._2130839842_res_0x7f020922, 100);
            }
            return context.getString(R.string._2130839841_res_0x7f020921, 500);
        }
        if (i == 266 || i == 262) {
            if (UnitUtil.h()) {
                return context.getString(R.string._2130839842_res_0x7f020922, 100);
            }
            return context.getString(R.string._2130839841_res_0x7f020921, 100);
        }
        if (UnitUtil.h()) {
            return context.getString(R.string._2130839823_res_0x7f02090f);
        }
        return context.getString(R.string._2130839824_res_0x7f020910);
    }

    public static String a(Context context, int i) {
        if (i == 266 || i == 262) {
            if (UnitUtil.h()) {
                return context.getString(R.string._2130843185_res_0x7f021631);
            }
            return context.getString(R.string._2130843184_res_0x7f021630);
        }
        if (UnitUtil.h()) {
            return context.getResources().getString(R.string._2130842416_res_0x7f021330);
        }
        return context.getResources().getString(R.string._2130842415_res_0x7f02132f);
    }

    public static float b(float f, int i) {
        double d;
        if (i == 262 || i == 266) {
            d = UnitUtil.d(f, 2);
        } else {
            d = UnitUtil.d(f, 3);
        }
        return (float) d;
    }

    public static String e(Integer num, Context context) {
        if (num == null) {
            return context.getString(R.string._2130850262_res_0x7f0231d6);
        }
        int intValue = num.intValue();
        if (intValue == 1) {
            return context.getResources().getString(R.string._2130839815_res_0x7f020907);
        }
        if (intValue == 3) {
            return context.getResources().getString(R.string._2130839816_res_0x7f020908);
        }
        if (intValue == 4) {
            return context.getResources().getString(R.string._2130839814_res_0x7f020906);
        }
        if (intValue == 5) {
            return context.getResources().getString(R.string._2130839817_res_0x7f020909);
        }
        return context.getResources().getString(R.string._2130839813_res_0x7f020905);
    }
}
