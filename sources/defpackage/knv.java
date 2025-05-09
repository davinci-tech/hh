package defpackage;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class knv implements Serializable {
    private static final long serialVersionUID = 8124564522666712928L;
    private int b;
    private long e;

    public knv() {
    }

    public knv(long j, int i) {
        this.e = j;
        this.b = i;
    }

    public long b() {
        return this.e;
    }

    public int d() {
        return this.b;
    }

    public String toString() {
        return this.e + "," + this.b;
    }
}
