package defpackage;

import java.util.Map;

/* loaded from: classes3.dex */
public class cyk {
    private Map<String, Object> c;
    private long d;
    private String e;

    public Map<String, Object> d() {
        return this.c;
    }

    public void c(Map<String, Object> map) {
        this.c = map;
    }

    public long e() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public String a() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String toString() {
        return "BiAnalyticsInfo{mTime=" + this.d + ", mEventId='" + this.e + "', mBiMap=" + this.c + '}';
    }
}
