package defpackage;

import android.content.Context;
import android.text.format.DateFormat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* loaded from: classes6.dex */
public class oan {
    public static String e(int i, Context context) {
        return e(d(c("", context, i), context, i), context, i);
    }

    private static String c(String str, Context context, int i) {
        if ((i & 64) == 64) {
            str = context.getResources().getString(R.string._2130841537_res_0x7f020fc1);
        }
        if ((i & 1) == 1) {
            if (!"".equals(str)) {
                str = str + " ";
            }
            str = str + context.getResources().getString(R.string._2130841437_res_0x7f020f5d);
        }
        if ((i & 2) != 2) {
            return str;
        }
        if (!"".equals(str)) {
            str = str + " ";
        }
        return str + context.getResources().getString(R.string._2130841539_res_0x7f020fc3);
    }

    private static String d(String str, Context context, int i) {
        if ((i & 4) == 4) {
            if (!"".equals(str)) {
                str = str + " ";
            }
            str = str + context.getResources().getString(R.string._2130841558_res_0x7f020fd6);
        }
        if ((i & 8) == 8) {
            if (!"".equals(str)) {
                str = str + " ";
            }
            str = str + context.getResources().getString(R.string._2130841538_res_0x7f020fc2);
        }
        if ((i & 16) == 16) {
            if (!"".equals(str)) {
                str = str + " ";
            }
            str = str + context.getResources().getString(R.string._2130841414_res_0x7f020f46);
        }
        if ((i & 32) != 32) {
            return str;
        }
        if (!"".equals(str)) {
            str = str + " ";
        }
        return str + context.getResources().getString(R.string._2130841468_res_0x7f020f7c);
    }

    private static String e(String str, Context context, int i) {
        if (i == 127) {
            if (!"".equals(str)) {
                str = "";
            }
            str = str + context.getResources().getString(R.string._2130841734_res_0x7f021086);
        }
        if (i == 31) {
            if (!"".equals(str)) {
                str = "";
            }
            str = str + context.getResources().getString(R.string._2130841735_res_0x7f021087);
        }
        if (i != 0) {
            return str;
        }
        return ("".equals(str) ? str : "") + context.getResources().getString(R.string._2130841879_res_0x7f021117);
    }

    public static String e(Context context, int i) {
        String str = (i / 100) + ":" + (i % 100);
        try {
            str = DateFormat.getTimeFormat(context.getApplicationContext()).format(Long.valueOf(new SimpleDateFormat("HH:mm").parse(str).getTime()));
            LogUtil.a("AlarmUtil", "getOffsetTimeStr dateFormat offsetStr ", str);
            return str;
        } catch (ParseException e) {
            LogUtil.a("AlarmUtil", "parseException.getMessage() ", e.getMessage());
            return str;
        }
    }
}
