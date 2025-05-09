package defpackage;

import org.ahocorasick.interval.Intervalable;

/* loaded from: classes7.dex */
public class uxa implements Intervalable {
    private int b;
    private int d;

    public uxa(int i, int i2) {
        this.d = i;
        this.b = i2;
    }

    @Override // org.ahocorasick.interval.Intervalable
    public int getStart() {
        return this.d;
    }

    @Override // org.ahocorasick.interval.Intervalable
    public int getEnd() {
        return this.b;
    }

    @Override // org.ahocorasick.interval.Intervalable
    public int size() {
        return (this.b - this.d) + 1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Intervalable)) {
            return false;
        }
        Intervalable intervalable = (Intervalable) obj;
        return this.d == intervalable.getStart() && this.b == intervalable.getEnd();
    }

    public int hashCode() {
        return (this.d % 100) + (this.b % 100);
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (!(obj instanceof Intervalable)) {
            return -1;
        }
        Intervalable intervalable = (Intervalable) obj;
        int start = this.d - intervalable.getStart();
        return start != 0 ? start : this.b - intervalable.getEnd();
    }

    public String toString() {
        return this.d + ":" + this.b;
    }
}
