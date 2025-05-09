package defpackage;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class vy {

    /* renamed from: a, reason: collision with root package name */
    private static volatile vy f17721a;
    private static final Object b = new Object();
    private Map<String, wa> c = new ConcurrentHashMap();

    public void a(wa waVar) {
        if (waVar == null) {
            return;
        }
        waVar.d();
    }

    public void e() {
        for (Map.Entry<String, wa> entry : this.c.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue().e()) {
                this.c.remove(key);
            }
        }
    }

    public wa c(Context context, String str) {
        wa waVar = this.c.get(str);
        if (waVar == null) {
            synchronized (b) {
                waVar = this.c.get(str);
                if (waVar == null) {
                    waVar = new wa(context, str, 60000L);
                    this.c.put(str, waVar);
                }
            }
        }
        waVar.a();
        return waVar;
    }

    public static vy b() {
        if (f17721a == null) {
            synchronized (b) {
                if (f17721a == null) {
                    f17721a = new vy();
                }
            }
        }
        return f17721a;
    }

    private vy() {
    }
}
