package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Deprecated
/* loaded from: classes6.dex */
public class mrk {
    public static long b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.add(14, -(calendar.get(15) + calendar.get(16)));
        return calendar.getTimeInMillis();
    }

    public static long b(String str) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!TextUtils.isEmpty(str)) {
            try {
                date = simpleDateFormat.parse(str);
            } catch (ParseException e) {
                LogUtil.b("DateUtil", "dateStringToLong ParseException :", e.getMessage());
            }
        }
        return date.getTime();
    }

    public static long d(String str) {
        Date parse;
        LogUtil.a("DateUtil", "getTime:", str);
        Date date = new Date();
        if (!TextUtils.isEmpty(str)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                int length = str.split(":").length;
                if (length == 2) {
                    parse = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str + ":00");
                } else if (length == 1) {
                    if (str.endsWith(":")) {
                        parse = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str + "00:00");
                    } else {
                        parse = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str + ":00:00");
                    }
                } else {
                    parse = simpleDateFormat2.parse(simpleDateFormat.format(new Date()) + str);
                }
                date = parse;
            } catch (ParseException e) {
                LogUtil.b("DateUtil", "ParseException=", e.getMessage());
            }
        }
        return date.getTime();
    }
}
