package defpackage;

import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.coap.CoAP;

/* loaded from: classes7.dex */
public final class uxl {
    public static String e(uxi uxiVar) {
        return a(uxiVar.b());
    }

    public static String a(uxr uxrVar) {
        String a2 = vcb.a();
        StringBuilder sb = new StringBuilder("==[ CoAP Response ]============================================");
        sb.append(a2);
        sb.append(String.format("MID    : %d%n", Integer.valueOf(uxrVar.getMID())));
        sb.append(String.format("Token  : %s%n", uxrVar.getTokenString()));
        sb.append(String.format("Type   : %s%n", uxrVar.getType()));
        CoAP.ResponseCode a3 = uxrVar.a();
        sb.append(String.format("Status : %s - %s%n", a3, a3.name()));
        Long d = uxrVar.d();
        if (uxrVar.getOffloadMode() != null) {
            if (d != null) {
                sb.append(String.format("RTT    : %d ms%n", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(d.longValue()))));
            }
            sb.append("(offloaded)");
            sb.append(a2);
        } else {
            sb.append(String.format("Options: %s%n", uxrVar.getOptions()));
            if (d != null) {
                sb.append(String.format("RTT    : %d ms%n", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(d.longValue()))));
            }
            sb.append(String.format("Payload: %d Bytes%n", Integer.valueOf(uxrVar.getPayloadSize())));
            if (uxrVar.getPayloadSize() > 0 && uxm.e(uxrVar.getOptions().h())) {
                sb.append("---------------------------------------------------------------");
                sb.append(a2);
                sb.append(uxrVar.getPayloadString());
                sb.append(a2);
            }
        }
        sb.append("===============================================================");
        return sb.toString();
    }
}
