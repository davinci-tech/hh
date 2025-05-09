package defpackage;

import android.graphics.Rect;

/* loaded from: classes6.dex */
public class nmk {

    /* renamed from: a, reason: collision with root package name */
    private boolean f15390a;
    private int b;
    private String c;
    private int j;
    private Rect i = new Rect();
    private int d = -1;
    private int e = -1;

    public nmk() {
    }

    public nmk(int i, String str, int i2) {
        this.b = i;
        this.c = str;
        this.j = i2;
    }

    public int a() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public int j() {
        return this.j;
    }

    public Rect cAD_() {
        return this.i;
    }

    public boolean g() {
        return this.f15390a;
    }

    public void d(boolean z) {
        this.f15390a = z;
    }

    public int c() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int b() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public String toString() {
        return "TagInfo{itemId = " + this.b + "'itemName = " + this.c + "'}";
    }
}
