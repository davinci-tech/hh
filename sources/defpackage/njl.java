package defpackage;

/* loaded from: classes6.dex */
public class njl {
    private int d = 1;
    private int j = 60;
    private int b = 170;
    private int c = 20480;
    private int i = 110;

    /* renamed from: a, reason: collision with root package name */
    private int f15331a = 720;
    private int h = 5;
    private int e = 20;

    public boolean d(int i, int i2, int i3) {
        if (i < 0 || i > 2 || i2 < 30 || i2 > 200 || i3 < 100 || i3 > 300) {
            return false;
        }
        this.d = i;
        this.j = i2;
        this.b = i3;
        return true;
    }

    public boolean e(int[] iArr) {
        if (iArr == null || iArr.length != 5) {
            return false;
        }
        for (int i : iArr) {
            if (i <= 0) {
                return false;
            }
        }
        this.c = iArr[0];
        this.i = iArr[1];
        this.f15331a = iArr[2];
        this.h = iArr[3];
        this.e = iArr[4];
        return true;
    }

    public int[] b() {
        return new int[]{this.d, this.j, this.b, this.c, this.i, this.f15331a, this.h, this.e};
    }
}
