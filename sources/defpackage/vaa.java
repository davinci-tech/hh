package defpackage;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.elements.EndpointContext;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class vaa {
    private static final Logger c;
    private static final vbw e;

    static {
        Logger a2 = vha.a((Class<?>) vaa.class);
        c = a2;
        e = new vbw(a2, 3L, TimeUnit.SECONDS.toNanos(10L));
    }

    public static boolean b(String str, vac<uzx<?>> vacVar, EndpointContext endpointContext, EndpointContext endpointContext2) {
        Logger logger = c;
        boolean isWarnEnabled = logger.isWarnEnabled();
        boolean isTraceEnabled = logger.isTraceEnabled();
        Iterator<uzx<?>> it = vacVar.iterator();
        while (true) {
            boolean z = true;
            while (it.hasNext()) {
                uzx<?> next = it.next();
                Object obj = endpointContext.get(next);
                Object obj2 = endpointContext2.get(next);
                boolean z2 = obj == obj2 || (obj != null && obj.equals(obj2));
                if (!z2 && !isWarnEnabled) {
                    return false;
                }
                if (!z2) {
                    e.d("{}, {}: \"{}\" != \"{}\"", str, next, obj, obj2);
                } else if (isTraceEnabled) {
                    c.trace("{}, {}: \"{}\" == \"{}\"", str, next, obj, obj2);
                }
                if (!z || !z2) {
                    z = false;
                }
            }
            return z;
        }
    }

    public static EndpointContext b(EndpointContext endpointContext, EndpointContext endpointContext2) {
        String string = endpointContext.getString(uzz.f);
        return (string == null || !string.equals("none")) ? endpointContext2 : vab.a(endpointContext2, uzz.f17626a);
    }
}
