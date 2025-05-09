package defpackage;

import android.content.Context;
import com.huawei.hms.network.embedded.r1;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class twp {
    public boolean d(Context context) {
        long b = twi.b(c(), 0L, context);
        twb.a("LocalCDNFile", "lastQueryTime is " + b, new Object[0]);
        return System.currentTimeMillis() - b > 432000000;
    }

    public Map a(Context context) {
        HashMap hashMap = new HashMap();
        String b = twi.b("ETag_ucscomponent", "", context);
        String b2 = twi.b("Last-Modified_ucscomponent", "", context);
        hashMap.put("ETag", b);
        hashMap.put(r1.b.o, b2);
        return hashMap;
    }

    public String c() {
        return "Last-Query-Time_ucscomponent_ucscomponent.jws";
    }
}
