package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes7.dex */
public class sde {
    public static boolean c(long j, long j2) {
        if (j2 == -1 || j == -1) {
            LogUtil.c("Track_RemindReceiverUtil", "isSameDay return false");
            return false;
        }
        Date date = new Date(j);
        Date date2 = new Date(j2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        return simpleDateFormat.format(date).equals(simpleDateFormat.format(date2));
    }
}
