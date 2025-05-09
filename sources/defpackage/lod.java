package defpackage;

import android.content.Context;
import com.huawei.multisimsdk.cardpartmanager.simauth.SimAuthController;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;

/* loaded from: classes5.dex */
public class lod {

    /* renamed from: a, reason: collision with root package name */
    private static int f14799a = -1;
    private static final String c = "CardPartManager";
    private static Context d;
    private static final Object e = new Object();

    public static void a(Context context) {
        e(context, null);
    }

    public static void e(Context context, AuthParam authParam) {
        synchronized (e) {
            if (context != null) {
                try {
                    d = context.getApplicationContext();
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (authParam != null) {
                f14799a = authParam.getSlotId();
                loq.c(c, "initCardPart slotId" + f14799a);
            }
        }
    }

    static String d() {
        lne b = lng.e().b(d, f14799a);
        if (b == null) {
            return null;
        }
        loq.c(c, "mCarrierConfigInfo.getCDMUrl()");
        return b.d();
    }

    public static String c() {
        lne b = lng.e().b(d, f14799a);
        if (b == null) {
            return "";
        }
        loq.c(c, "mCarrierConfigInfo.getESUrl()");
        return b.h();
    }

    public static String e() {
        lne b = lng.e().b(d, f14799a);
        if (b == null) {
            return "";
        }
        loq.c(c, "mCarrierConfigInfo.getBSFUrl()");
        return b.c();
    }

    public static String a() {
        lne b = lng.e().b(d, f14799a);
        if (b == null) {
            return "";
        }
        String g = b.g();
        loq.c(c, "mCarrierConfigInfo.getSupportedProtocol is " + g);
        return g;
    }

    public static String c(String str, String str2) {
        String a2 = "MSISDN".equals(str) ? loj.d().a(d, str2) : null;
        if (a2 == null) {
            loq.c(c, "simOperator is null");
            return null;
        }
        lne b = lnf.b(d, a2);
        if (b != null) {
            return b.d();
        }
        return null;
    }

    static String c(String str) {
        return new SimAuthController(d).b(str);
    }

    public static void b() {
        f14799a = -1;
        d = null;
    }
}
