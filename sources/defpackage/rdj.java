package defpackage;

import health.compact.a.UnitUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rdj {
    private long c;
    private Map<String, Double> d;
    private int e;

    public rdj() {
        this.d = new HashMap();
        this.c = 0L;
    }

    public rdj(Map<String, Double> map, int i, long j) {
        new HashMap();
        this.d = map;
        this.e = i;
        this.c = j;
    }

    public void d(Map<String, Double> map) {
        this.d = map;
    }

    public Map<String, Double> d() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public long c() {
        return this.c;
    }

    public void e(long j) {
        this.c = j;
    }

    public String a() {
        return UnitUtil.a(new Date(this.c), 52);
    }

    public boolean e() {
        Map<String, Double> map = this.d;
        return map == null || map.isEmpty();
    }
}
