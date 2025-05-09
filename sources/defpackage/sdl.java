package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes7.dex */
public class sdl {
    public static String d(long j, Context context) {
        String a2;
        LogUtil.a("SocialUtils", "getRelativeTime");
        if (j <= 0) {
            LogUtil.h("SocialUtils", "simpleDateFormat.parse is wrong");
            return "";
        }
        Date date = new Date();
        Calendar.getInstance().setTime(date);
        Date date2 = new Date(j);
        long time = date.getTime() - date2.getTime();
        if (b(date2)) {
            if (time <= 60000 && 0 <= time) {
                a2 = context.getResources().getString(R.string._2130841927_res_0x7f021147);
            } else if (time <= 3600000 && time > 60000) {
                a2 = context.getResources().getQuantityString(R.plurals._2130903222_res_0x7f0300b6, (((int) time) / 1000) / 60, Long.valueOf((time / 1000) / 60));
            } else {
                a2 = new SimpleDateFormat("HH:mm").format(date2);
            }
        } else if (a(date2)) {
            a2 = context.getResources().getString(R.string._2130841752_res_0x7f021098);
        } else {
            a2 = UnitUtil.a(date2, 65552);
        }
        LogUtil.a("SocialUtils", "relativeTime = ", a2);
        return a2;
    }

    public static boolean b(Date date) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar2.get(1) == calendar.get(1) && calendar2.get(6) - calendar.get(6) == 0;
    }

    public static boolean a(Date date) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar2.get(1) == calendar.get(1) && calendar2.get(6) - calendar.get(6) == -1;
    }

    public static boolean d(String str) {
        boolean isEmpty = TextUtils.isEmpty(str);
        return isEmpty ? isEmpty : str.trim().length() <= 0;
    }

    public static int e(int[] iArr, int i) {
        if (iArr == null || iArr.length <= 0) {
            return -1;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public static boolean b() {
        return (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() || Utils.o()) ? false : true;
    }

    public static String b(String str) {
        return str == null ? "" : str.trim();
    }

    public static boolean a(String str) {
        if (d(str)) {
            return false;
        }
        return str.equals(String.valueOf(0)) || str.equals(String.valueOf(1));
    }

    public static String a(mdf mdfVar) {
        boolean z = mdfVar != null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(z);
        if (z) {
            stringBuffer.append(mdfVar.ac()).append(mdfVar.h()).append(mdfVar.e());
        }
        return stringBuffer.toString();
    }

    public static boolean b(long j, long j2) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        return (j == 0 || j2 == 0) ? (j == 0 || j2 != 0) ? j != 0 || j2 == 0 || timeInMillis <= j2 : j <= timeInMillis : j <= timeInMillis && timeInMillis <= j2;
    }

    public static float a(float f, float f2) {
        return new BigDecimal(f).subtract(new BigDecimal(f2)).floatValue();
    }

    public static float d(float f, float f2, int i) {
        return new BigDecimal(f).divide(new BigDecimal(f2), i, 4).floatValue();
    }
}
