package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.utils.StringUtils;

/* loaded from: classes3.dex */
public class cat {
    public static boolean e(double d) {
        return d > -1.0E-4d && d < 1.0E-4d;
    }

    public static Drawable Cw_(Context context, Drawable drawable, int i) {
        if (context == null) {
            LogUtil.h("Track_SportHistoryUtils", "getMirrorSportIcon context is null");
            return null;
        }
        if (!LanguageUtil.bc(context)) {
            return null;
        }
        BitmapDrawable cKm_ = nrz.cKm_(context, drawable);
        LogUtil.a("Track_SportHistoryUtils", "decode  ", cKm_);
        return (cKm_ == null || i == 512) ? cKm_ : nrf.cJH_(cKm_, context.getColor(R.color._2131296937_res_0x7f0902a9));
    }

    public static void Cx_(ImageView imageView, Drawable drawable, Drawable drawable2) {
        if (drawable != null) {
            imageView.setBackground(drawable);
        } else {
            imageView.setBackground(drawable2);
        }
    }

    public static String e(Context context) {
        if (context == null) {
            LogUtil.h("Track_SportHistoryUtils", "aquire Run Unit and context is null");
            return "";
        }
        if (UnitUtil.h()) {
            return "/" + context.getResources().getString(R.string._2130841383_res_0x7f020f27);
        }
        return "/" + context.getResources().getString(R.string._2130841382_res_0x7f020f26);
    }

    public static String c(Context context, float f) {
        if (context == null) {
            LogUtil.h("Track_SportHistoryUtils", "getDistanceUnit context is null");
            return "";
        }
        if (UnitUtil.h()) {
            return nsf.a(R.plurals._2130903302_res_0x7f030106, (int) f, "");
        }
        return nsf.a(R.plurals._2130903301_res_0x7f030105, (int) f, "");
    }

    public static String d(double d) {
        return b((d / 1000.0d) / 60.0d, 2);
    }

    public static String b(double d, int i) {
        return UnitUtil.e(d, 1, i);
    }

    public static long d() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(20002), "in_sport_history_activity_time");
        if (StringUtils.i(b) && b.contains("##")) {
            String str = b.split("##")[0];
            if (StringUtils.i(str) && c(str)) {
                return CommonUtil.g(str);
            }
        }
        return 0L;
    }

    public static boolean c(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException unused) {
            LogUtil.b("Track_SportHistoryUtils", "isLong NumberFormatException");
            return false;
        }
    }

    public static String a(int i, Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        if (i == 1) {
            return context.getString(R.string.IDS_recreational_scuba_diving);
        }
        if (i == 2) {
            return context.getString(R.string.IDS_technical_scuba_diving);
        }
        if (i == 3) {
            return context.getString(R.string.IDS_instrument_scuba_diving);
        }
        return context.getString(R.string.IDS_recreational_scuba_diving);
    }

    public static String b(double d) {
        if (d < 60.0d) {
            return UnitUtil.e(d, 1, 0);
        }
        if (d < 3600.0d) {
            int i = (int) d;
            int i2 = i % 60;
            int i3 = i / 60;
            Resources resources = com.huawei.haf.application.BaseApplication.e().getResources();
            return resources.getString(R.string._2130843702_res_0x7f021836, resources.getQuantityString(R.plurals._2130903368_res_0x7f030148, i3, UnitUtil.e(i3, 1, 0)), resources.getQuantityString(R.plurals._2130903369_res_0x7f030149, i2, UnitUtil.e(i2, 1, 0)));
        }
        return UnitUtil.e(d / 3600.0d, 1, 1);
    }

    public static String c(double d) {
        if (d < 60.0d) {
            return nsf.a(R.plurals._2130903369_res_0x7f030149, (int) d, UnitUtil.e(d, 1, 0));
        }
        if (d < 3600.0d) {
            int i = (int) d;
            int i2 = i % 60;
            int i3 = i / 60;
            return nsf.b(R.string._2130843702_res_0x7f021836, nsf.a(R.plurals._2130903368_res_0x7f030148, i3, UnitUtil.e(i3, 1, 0)), nsf.a(R.plurals._2130903369_res_0x7f030149, i2, UnitUtil.e(i2, 1, 0)));
        }
        return nsf.a(R.plurals._2130903199_res_0x7f03009f, (int) d, UnitUtil.e(d / 3600.0d, 1, 1));
    }

    public static String a(double d) {
        if (d < 60000.0d) {
            return com.huawei.haf.application.BaseApplication.e().getResources().getString(R.string._2130843202_res_0x7f021642);
        }
        return d < 3600000.0d ? "" : com.huawei.haf.application.BaseApplication.e().getResources().getString(R.string._2130841434_res_0x7f020f5a);
    }
}
