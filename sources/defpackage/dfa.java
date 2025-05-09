package defpackage;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class dfa {
    private static final Set<String> c;

    static {
        HashSet hashSet = new HashSet(2);
        c = hashSet;
        hashSet.add("c015d85d-6e29-4ff6-92e2-83bcddd44e53");
        hashSet.add("44812f21-5712-4ef1-ac74-6e7920442a9a");
    }

    public static boolean c(String str) {
        return c.contains(str);
    }
}
