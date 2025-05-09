package defpackage;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes8.dex */
public class aaz<k, t> implements Serializable {
    private static final long serialVersionUID = 1;
    private Map<k, t> b;

    public Map<k, t> b() {
        return this.b;
    }

    public void e(Map<k, t> map) {
        this.b = map;
    }
}
