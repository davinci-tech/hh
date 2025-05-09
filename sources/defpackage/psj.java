package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class psj {
    private final int b;
    private final int c;

    psj(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public static long e(long j, int i) {
        if (i == 0) {
            return j;
        }
        if (i == 3) {
            return c(j);
        }
        if (i == 5) {
            return b(j);
        }
        if (i == 6) {
            return a(j);
        }
        LogUtil.h("QueryExtension", "correctTimeStamp not support uniteType:", Integer.valueOf(i));
        return -1L;
    }

    private static long a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        calendar.set(5, 1);
        calendar.set(2, 5);
        return calendar.getTimeInMillis();
    }

    private static long b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        calendar.set(5, 15);
        return calendar.getTimeInMillis();
    }

    private static long c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 12);
        return calendar.getTimeInMillis();
    }

    public int d() {
        return this.b;
    }

    public int b() {
        return this.c;
    }
}
