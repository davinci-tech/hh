package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class svh {
    private Map<String, String> c = new HashMap();
    private String d;
    private String e;

    public svh(String str, String str2) {
        this.d = str;
        this.e = str2;
    }

    public String c() {
        return this.d;
    }

    public String a() {
        return this.e;
    }

    public void a(String str, String str2) {
        this.c.put(str, str2);
    }

    public boolean d(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return this.c.isEmpty();
        }
        if (this.c.isEmpty() || map.size() != this.c.size()) {
            return false;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!this.c.containsKey(entry.getKey()) || !this.c.get(entry.getKey()).equalsIgnoreCase(entry.getValue())) {
                return false;
            }
            stq.c("AddressName", "conditionMatch", false);
        }
        return true;
    }
}
