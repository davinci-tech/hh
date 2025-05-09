package defpackage;

import android.content.Context;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class iks {

    /* renamed from: a, reason: collision with root package name */
    private static final Context f13416a = BaseApplication.getContext();
    private ikl b;

    private iks() {
        this.b = new ikl();
    }

    static class c {
        private static final iks d = new iks();
    }

    public static iks e() {
        return c.d;
    }

    public List<Integer> a(int i) {
        return c(i);
    }

    private List<Integer> c(int i) {
        if (i <= 0) {
            LogUtil.h("Debug_StatClients", "getUserStatClients who <= 0 ");
            return null;
        }
        String e = ikq.e("0", Integer.toString(i), "0");
        List<Integer> a2 = this.b.a(e);
        if (a2 != null && !a2.isEmpty()) {
            return a2;
        }
        List<Integer> g = iis.d().g(i);
        if (g == null || g.isEmpty()) {
            LogUtil.h("Debug_StatClients", "getUserStatClients clients is null who = ", Integer.valueOf(i));
            return null;
        }
        this.b.d(e, g);
        return g;
    }

    public void d(int i) {
        if (i <= 0) {
            return;
        }
        List<Integer> g = iis.d().g(i);
        if (g == null || g.isEmpty()) {
            LogUtil.h("Debug_StatClients", "setUserStatClients clients is null who = ", Integer.valueOf(i));
        } else {
            this.b.d(ikq.e("0", Integer.toString(i), "0"), g);
        }
    }

    public List<Integer> c(int i, int i2) {
        return f(i, i2);
    }

    private List<Integer> f(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            LogUtil.h("Debug_StatClients", "getUserDeviceStatClients who <= 0 or deviceID <= 0");
            return null;
        }
        String e = ikq.e("0", Integer.toString(i), Integer.toString(i2));
        List<Integer> a2 = this.b.a(e);
        if (a2 != null && !a2.isEmpty()) {
            return a2;
        }
        List<Integer> a3 = iis.d().a(i, i2);
        if (a3 == null || a3.isEmpty()) {
            LogUtil.h("Debug_StatClients", "getUserDeviceStatClients clients is null key = ", e);
            return null;
        }
        this.b.d(e, a3);
        return a3;
    }

    public List<Integer> b(int i, String str) {
        return c(i, str);
    }

    private List<Integer> c(int i, String str) {
        if (i <= 0 || str == null) {
            LogUtil.h("Debug_StatClients", "getUserDeviceStatClients who <= 0 or deviceUUID = null");
            return null;
        }
        String e = ikq.e("0", Integer.toString(i), str);
        List<Integer> a2 = this.b.a(e);
        if (a2 != null && !a2.isEmpty()) {
            return a2;
        }
        int b = ijl.b(f13416a, str);
        if (b <= 0) {
            LogUtil.h("Debug_StatClients", "getUserDeviceStatClients deviceID <= 0 deviceUUID error");
            return null;
        }
        List<Integer> c2 = c(i, b);
        if (c2 == null || c2.isEmpty()) {
            LogUtil.h("Debug_StatClients", "getUserDeviceStatClients clients is null key error ! who is ", Integer.valueOf(i), " deviceID is ", Integer.valueOf(b));
            return null;
        }
        this.b.d(e, c2);
        return c2;
    }

    public void b(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            LogUtil.h("Debug_StatClients", "setUserDeviceStatClients who <= 0 or deviceID <= 0");
            return;
        }
        List<Integer> a2 = iis.d().a(i, i2);
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("Debug_StatClients", "setUserDeviceStatClients clients is null who = ", Integer.valueOf(i), ",deviceID = ", Integer.valueOf(i2));
        } else {
            this.b.d(ikq.e("0", Integer.toString(i), Integer.toString(i2)), a2);
        }
    }

    public void a(int i, String str, List<Integer> list) {
        if (i <= 0 || str == null || list == null || list.isEmpty()) {
            LogUtil.b("Debug_StatClients", "Failed to setUserDeviceStatClients cause: userID=", Integer.valueOf(i), ", deviceUUID=", str, ", clientIs=" + HiJsonUtil.e(list));
            return;
        }
        this.b.d(ikq.e("0", Integer.toString(i), str), list);
        LogUtil.a("Debug_StatClients", "Success setUserDeviceStatClients ");
    }

    public List<Integer> a(int i, int i2) {
        return d(i, i2);
    }

    private List<Integer> d(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            LogUtil.h("Debug_StatClients", "getUserAppStatClients who <= 0 or appID <= 0");
            return null;
        }
        String e = ikq.e(Integer.toString(i), Integer.toString(i2), "0");
        List<Integer> a2 = this.b.a(e);
        if (a2 != null && !a2.isEmpty()) {
            return a2;
        }
        List<Integer> e2 = iis.d().e(i2, i);
        if (e2 == null || e2.isEmpty()) {
            LogUtil.h("Debug_StatClients", "getUserAppStatClients clients is null key = ", e);
            return null;
        }
        this.b.d(e, e2);
        return e2;
    }

    public void e(int i, int i2) {
        if (i2 <= 0 || i <= 0) {
            LogUtil.h("Debug_StatClients", "setUserAppStatClients who <= 0 or appID <= 0");
            return;
        }
        List<Integer> e = iis.d().e(i2, i);
        if (e == null || e.isEmpty()) {
            LogUtil.h("Debug_StatClients", "setUserAppStatClients clients is null who = ", Integer.valueOf(i2), ",appID = ", Integer.valueOf(i));
        } else {
            this.b.d(ikq.e(Integer.toString(i), Integer.toString(i2), "0"), e);
        }
    }

    public void a() {
        this.b.b();
    }
}
