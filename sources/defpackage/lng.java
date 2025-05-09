package defpackage;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class lng {
    private static HashMap<String, lne> e = new HashMap<>();

    private lng() {
    }

    static class b {
        private static final lng b = new lng();
    }

    public static lng e() {
        return b.b;
    }

    public lne b(Context context, int i) {
        String b2 = lop.b(context, i);
        loq.c("CarrierConfigController", "getCarrierConfigInfo getSimOperator=" + b2 + ", slotId=" + i);
        lne lneVar = e.get(b2);
        if (lneVar != null) {
            loq.c("CarrierConfigController", "CarrierConfigInfo has the config Info of the simOperator");
            return lneVar;
        }
        lne c = lnf.c(context, null, i);
        e.put(b2, c);
        return c;
    }
}
