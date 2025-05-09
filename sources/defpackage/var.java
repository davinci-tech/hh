package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class var {
    private static final Map<String, Object> e = new HashMap(0);
    private final Map<String, Object> b;

    private var(Map<String, Object> map) {
        if (map == null) {
            this.b = e;
        } else {
            this.b = new HashMap(map);
        }
    }

    public static var c() {
        return new var(null);
    }
}
