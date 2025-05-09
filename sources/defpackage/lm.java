package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.alipay.sdk.m.p0.b;
import defpackage.lm;

/* loaded from: classes7.dex */
public class lm {

    /* renamed from: a, reason: collision with root package name */
    public static Context f14769a = null;
    public static boolean b = false;
    public static volatile lm c;
    public static volatile b d;
    public static String e;
    public static li f;
    public static Object g = new Object();
    public static li h;
    public static li i;
    public static HandlerThread j;
    public static Handler k;
    public static String l;
    public static String m;
    public static String n;
    public static String o;

    public static void b() {
        HandlerThread handlerThread = new HandlerThread("SqlWorkThread");
        j = handlerThread;
        handlerThread.start();
        final Looper looper = j.getLooper();
        k = new Handler(looper) { // from class: com.alipay.sdk.m.p0.c$a
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                b bVar;
                Object obj;
                Object obj2;
                if (message.what != 11) {
                    Log.e("VMS_IDLG_SDK_Client", "message type valid");
                    return;
                }
                int i2 = message.getData().getInt("type");
                String string = message.getData().getString("appid");
                bVar = lm.d;
                String unused = lm.o = bVar.b(i2, string);
                obj = lm.g;
                synchronized (obj) {
                    obj2 = lm.g;
                    obj2.notify();
                }
            }
        };
    }

    public static void c() {
        b = "1".equals(b("persist.sys.identifierid.supported", "0"));
    }

    public static lm d(Context context) {
        if (c == null) {
            synchronized (lm.class) {
                f14769a = context.getApplicationContext();
                c = new lm();
            }
        }
        if (d == null) {
            synchronized (lm.class) {
                f14769a = context.getApplicationContext();
                b();
                d = new b(f14769a);
                c();
            }
        }
        return c;
    }

    public boolean f() {
        return b;
    }

    private void d(int i2, String str) {
        Message obtainMessage = k.obtainMessage();
        obtainMessage.what = 11;
        Bundle bundle = new Bundle();
        bundle.putInt("type", i2);
        if (i2 == 1 || i2 == 2) {
            bundle.putString("appid", str);
        }
        obtainMessage.setData(bundle);
        k.sendMessage(obtainMessage);
    }

    public String e() {
        if (!f()) {
            return null;
        }
        String str = n;
        if (str != null) {
            return str;
        }
        c(0, null);
        if (h == null) {
            e(f14769a, 0, null);
        }
        return n;
    }

    public void c(int i2, String str) {
        synchronized (g) {
            d(i2, str);
            long uptimeMillis = SystemClock.uptimeMillis();
            try {
                g.wait(2000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (SystemClock.uptimeMillis() - uptimeMillis >= 2000) {
                Log.d("VMS_IDLG_SDK_Client", "query timeout");
            } else if (i2 == 0) {
                n = o;
                o = null;
            } else if (i2 != 1) {
                if (i2 == 2) {
                    String str2 = o;
                    if (str2 != null) {
                        m = str2;
                        o = null;
                    } else {
                        Log.e("VMS_IDLG_SDK_Client", "get aaid failed");
                    }
                } else if (i2 != 4) {
                }
                e = o;
                o = null;
            } else {
                String str3 = o;
                if (str3 != null) {
                    l = str3;
                    o = null;
                } else {
                    Log.e("VMS_IDLG_SDK_Client", "get vaid failed");
                }
            }
        }
    }

    public static String b(String str, String str2) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "unknown");
            } catch (Exception e2) {
                e2.printStackTrace();
                return str2;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static void e(Context context, int i2, String str) {
        if (i2 == 0) {
            h = new li(c, 0, null);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), true, h);
            return;
        }
        if (i2 == 1) {
            f = new li(c, 1, str);
            context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/VAID_" + str), false, f);
            return;
        }
        if (i2 != 2) {
            return;
        }
        i = new li(c, 2, str);
        context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/AAID_" + str), false, i);
    }
}
