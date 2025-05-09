package defpackage;

import android.graphics.Color;
import java.util.Locale;

/* loaded from: classes8.dex */
public class acf {

    /* renamed from: a, reason: collision with root package name */
    private final int f169a;
    private int b;
    private final float[] d;

    public acf(int i, int i2) {
        float[] fArr = new float[3];
        this.d = fArr;
        this.f169a = i;
        Color.colorToHSV(i, fArr);
        this.b = i2;
    }

    public acf(float f, float f2, float f3, int i) {
        float[] fArr = {f, f2, f3};
        this.d = fArr;
        this.f169a = Color.HSVToColor(fArr);
        this.b = i;
    }

    public int c() {
        return this.f169a;
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public float e() {
        return this.d[0];
    }

    public float d() {
        return this.d[1];
    }

    public float b() {
        return this.d[2];
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "{rgb=#%06X, hsv=[%.2f, %.2f, %.2f], population=%d}", Integer.valueOf(16777215 & this.f169a), Float.valueOf(this.d[0]), Float.valueOf(this.d[1]), Float.valueOf(this.d[2]), Integer.valueOf(this.b));
    }
}
