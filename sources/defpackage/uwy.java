package defpackage;

import org.ahocorasick.interval.Intervalable;

/* loaded from: classes7.dex */
public class uwy extends uxa implements Intervalable {
    private final String d;

    public uwy(int i, int i2, String str) {
        super(i, i2);
        this.d = str;
    }

    public String c() {
        return this.d;
    }

    @Override // defpackage.uxa
    public String toString() {
        return super.toString() + "=" + this.d;
    }
}
