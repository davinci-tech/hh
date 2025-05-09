package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class svp {
    private String b;
    private Map<String, svo> d = new HashMap(16);
    private String e;

    public void d(String str) {
        this.b = str;
    }

    public void d(String str, svo svoVar) {
        this.d.put(str, svoVar);
    }

    public svo e(String str) {
        return this.d.get(str);
    }

    public void a(String str) {
        this.e = str;
    }
}
