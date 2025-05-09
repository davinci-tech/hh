package defpackage;

import android.content.Context;
import com.alipay.sdk.m.n0.d;
import java.util.zip.Adler32;

/* loaded from: classes7.dex */
public class kz {
    public static final Object b = new Object();
    public static lc e;

    public static lc a(Context context) {
        synchronized (kz.class) {
            lc lcVar = e;
            if (lcVar != null) {
                return lcVar;
            }
            if (context == null) {
                return null;
            }
            lc e2 = e(context);
            e = e2;
            return e2;
        }
    }

    public static long d(lc lcVar) {
        if (lcVar == null) {
            return 0L;
        }
        String format = String.format("%s%s%s%s%s", lcVar.d(), lcVar.c(), Long.valueOf(lcVar.b()), lcVar.e(), lcVar.a());
        if (ks.e(format)) {
            return 0L;
        }
        Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(format.getBytes());
        return adler32.getValue();
    }

    public static lc e(Context context) {
        if (context == null) {
            return null;
        }
        synchronized (b) {
            String b2 = d.e(context).b();
            if (ks.e(b2)) {
                return null;
            }
            if (b2.endsWith("\n")) {
                b2 = b2.substring(0, b2.length() - 1);
            }
            lc lcVar = new lc();
            long currentTimeMillis = System.currentTimeMillis();
            String a2 = kq.a(context);
            String b3 = kq.b(context);
            lcVar.d(a2);
            lcVar.e(a2);
            lcVar.c(currentTimeMillis);
            lcVar.b(b3);
            lcVar.c(b2);
            lcVar.d(d(lcVar));
            return lcVar;
        }
    }
}
