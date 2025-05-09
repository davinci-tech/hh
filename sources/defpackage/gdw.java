package defpackage;

/* loaded from: classes4.dex */
public class gdw {
    private Object c;
    private int d;
    private int e;

    public gdw(int i) {
        this(-1, i);
    }

    public gdw(int i, int i2) {
        this.e = i;
        this.d = i2;
    }

    public int c() {
        return this.e;
    }

    public int a() {
        return this.d;
    }

    public Object b() {
        return this.c;
    }

    public void b(Object obj) {
        this.c = obj;
    }

    public String toString() {
        return "RecycleItemData{viewId=" + this.e + ", viewType=" + this.d + ", itemData=" + this.c + '}';
    }
}
