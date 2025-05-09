package defpackage;

import java.util.Map;

/* loaded from: classes5.dex */
public class jca {
    private double b;
    private int c;
    private Map<String, Object> e;

    public Map<String, Object> e() {
        return this.e;
    }

    public void e(Map<String, Object> map) {
        this.e = map;
    }

    public void d(double d) {
        this.b = d;
    }

    public double b() {
        return this.b;
    }

    public void b(int i) {
        this.c = i;
    }

    public int d() {
        return this.c;
    }

    public String toString() {
        return "AtmosphereInfo{pressure=" + this.b + ", pressureUnit=" + this.c + ", hagReportBiMap=" + this.e + '}';
    }
}
