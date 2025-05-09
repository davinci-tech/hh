package health.compact.a;

import defpackage.jdy;

/* loaded from: classes.dex */
public class StorageParams {
    private int c;
    private int d;

    public StorageParams() {
        this.d = 0;
        this.c = 0;
    }

    public StorageParams(int i) {
        this.d = 0;
        this.c = i;
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public void d(int i) {
        this.c = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
