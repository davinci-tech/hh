package defpackage;

import android.content.Context;
import com.huawei.hihealthservice.hihealthkit.hmsauth.HmsCpAuthUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class ipl {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13535a;
    private static Context b = null;
    private static String c = "HMSAuth_HmsAuthUtil";
    private static final String d;
    private static final String e;
    private HmsCpAuthUtil h;
    private ipr i;

    static {
        String str = "HiH_" + c;
        e = str;
        d = "R_" + str;
        f13535a = new Object();
    }

    private ipl(String str) {
        if ("JS".equals(str)) {
            this.i = new ipr();
        }
        if ("CP".equals(str)) {
            this.h = new HmsCpAuthUtil(b);
        }
    }

    public static ipl c(Context context) {
        ipl iplVar;
        synchronized (f13535a) {
            if (context != null) {
                b = context.getApplicationContext();
            }
            iplVar = e.d;
        }
        return iplVar;
    }

    /* loaded from: classes7.dex */
    static class e {
        private static final ipl d = new ipl("JS");
    }

    public int a(String str, String str2, int i, boolean z) {
        ipr iprVar = this.i;
        if (iprVar == null) {
            ReleaseLogUtil.c(d, "HMSJsAuthUtil hasn't been instanced");
            return 4;
        }
        int a2 = iprVar.a(str, str2, i, z, b);
        ReleaseLogUtil.e(d, "jsAuth result is " + a2);
        return a2;
    }

    public static ipl b(Context context) {
        ipl iplVar;
        synchronized (f13535a) {
            if (context != null) {
                b = context.getApplicationContext();
            }
            iplVar = c.e;
        }
        return iplVar;
    }

    static class c {
        private static final ipl e = new ipl("CP");
    }

    public int d(int i, String str, boolean z) {
        HmsCpAuthUtil hmsCpAuthUtil = this.h;
        if (hmsCpAuthUtil == null) {
            ReleaseLogUtil.c(d, "HmsCpAuthUtil hasn't been instanced");
            return 4;
        }
        int c2 = hmsCpAuthUtil.c(i, str, z);
        ReleaseLogUtil.e(d, "cpAuth result is " + c2);
        return c2;
    }

    public int b(String str, int i, boolean z) {
        return this.h.c(str, i, z);
    }
}
