package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class oal {

    /* renamed from: a, reason: collision with root package name */
    private static volatile oal f15583a;
    private static final Object e = new Object();
    private Context d;

    private oal(Context context) {
        this.d = context;
    }

    public static oal d(Context context) {
        if (f15583a == null) {
            synchronized (e) {
                if (f15583a == null) {
                    f15583a = new oal(BaseApplication.getContext());
                }
            }
        }
        return f15583a;
    }

    public static String b(Context context, int i) {
        return oan.e(context, i);
    }

    public String b(int i) {
        Context context = this.d;
        return context == null ? "" : oan.e(i, context.getApplicationContext());
    }

    public int c(boolean[] zArr) {
        LogUtil.a("AlarmInteractor", "getRemindWeek()");
        int i = zArr[0] ? 64 : 0;
        if (zArr[6]) {
            i += 32;
        }
        if (zArr[5]) {
            i += 16;
        }
        if (zArr[4]) {
            i += 8;
        }
        if (zArr[3]) {
            i += 4;
        }
        if (zArr[2]) {
            i += 2;
        }
        if (zArr[1]) {
            i++;
        }
        LogUtil.a("AlarmInteractor", "getRemindWeek() week ", Integer.valueOf(i));
        return i;
    }

    public String c(String str, int i) {
        while (str.length() < i) {
            str = "0" + str;
        }
        return str;
    }

    public int d(int i, int i2, int i3) {
        int i4 = i2 - i3;
        if (i4 <= 0) {
            i = (i + 23) % 24;
            i4 += 60;
        }
        return (i * 100) + i4;
    }
}
