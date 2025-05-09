package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class pwa {
    public static String a(Context context, long j) {
        if (j <= 0) {
            LogUtil.h("getRelativeTime", "simpleDateFormat.parse is wrong");
            return "";
        }
        Date date = new Date();
        Calendar.getInstance().setTime(date);
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date2 = new Date(j);
        long time = date.getTime() - date2.getTime();
        if (!b(date2)) {
            if (a(date2)) {
                return context.getResources().getString(R$string.IDS_calendar_current_date_yesterday);
            }
            return UnitUtil.a(date2, 65552);
        }
        if (time <= 60000 && time >= 0) {
            return context.getResources().getString(R$string.IDS_social_information_just_now);
        }
        if (time <= 3600000 && time > 60000) {
            return context.getResources().getQuantityString(R.plurals._2130903222_res_0x7f0300b6, (((int) time) / 1000) / 60, Long.valueOf((time / 1000) / 60));
        }
        return simpleDateFormat.format(date2);
    }

    private static boolean b(Date date) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar2.get(1) == calendar.get(1) && calendar2.get(6) - calendar.get(6) == 0;
    }

    private static boolean a(Date date) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar2.get(1) == calendar.get(1) && calendar.get(6) - calendar2.get(6) == 1;
    }
}
