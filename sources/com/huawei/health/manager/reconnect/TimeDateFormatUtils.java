package com.huawei.health.manager.reconnect;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class TimeDateFormatUtils {
    private TimeDateFormatUtils() {
    }

    public static boolean a(String str, String str2) throws ParseException {
        String format = new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS).format(new Date(System.currentTimeMillis()));
        LogUtil.a("TimeDateFormatUtils", "nowTimeStr: ", format);
        boolean a2 = a(new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS).parse(format), new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS).parse(str), new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS).parse(str2));
        LogUtil.a("TimeDateFormatUtils", "isEffectiveDate: ", Boolean.valueOf(a2));
        return a2;
    }

    private static boolean a(Date date, Date date2, Date date3) {
        if (date.getTime() != date2.getTime() && date.getTime() != date3.getTime()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(date3);
            if (!calendar.after(calendar2) || !calendar.before(calendar3)) {
                return false;
            }
        }
        return true;
    }
}
