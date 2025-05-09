package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.LanguageUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Deprecated
/* loaded from: classes6.dex */
public class mfn {
    public static long d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.add(14, -(calendar.get(15) + calendar.get(16)));
        return calendar.getTimeInMillis();
    }

    public static long a(String str) {
        Date parse;
        LogUtil.a("DateUtil", "getTime time=", str);
        Date date = new Date();
        if (str != null && !str.equals("")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd ", Locale.ENGLISH);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
            try {
                int length = str.split(":").length;
                if (length >= 3 || length <= 0) {
                    parse = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str);
                } else {
                    if (length == 2) {
                        date = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str + ":00");
                    }
                    if (length == 1) {
                        if (str.endsWith(":")) {
                            parse = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str + "00:00");
                        } else {
                            parse = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str + ":00:00");
                        }
                    }
                }
                date = parse;
            } catch (ParseException e) {
                LogUtil.a("DateUtil", "getTime e=", e.getMessage());
            }
        }
        return date.getTime();
    }

    public static String c(Context context, long j) {
        String str;
        String str2;
        String str3;
        if (LanguageUtil.m(context)) {
            str = "年";
            str3 = "月";
            str2 = "日";
        } else {
            str = Constants.LINK;
            str2 = "";
            str3 = Constants.LINK;
        }
        return new SimpleDateFormat("yyyy" + str + "MM" + str3 + "dd" + str2, Locale.getDefault()).format(new Date(j));
    }

    public static long c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(1, 1);
        return calendar.getTime().getTime();
    }
}
