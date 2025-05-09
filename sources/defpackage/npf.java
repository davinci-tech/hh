package defpackage;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.Transformer;

/* loaded from: classes6.dex */
public class npf {
    private Entry c;
    private float g;
    private float i;
    private boolean e = false;
    private boolean d = false;
    private boolean f = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f15420a = false;
    private Object b = null;

    public npf(float f, float f2, Entry entry) {
        a(f, f2, entry);
    }

    public npf(Entry entry) {
        c(entry);
    }

    public npf() {
    }

    public void b(float f, float f2, Entry entry) {
        a(f, f2, entry);
    }

    private void c(Entry entry) {
        this.i = entry.getX();
        this.g = entry.getY();
        this.c = entry;
    }

    private void a(float f, float f2, Entry entry) {
        this.i = f;
        this.g = f2;
        this.c = entry;
    }

    public float b() {
        return this.i;
    }

    public float d() {
        return this.g;
    }

    public void b(float f) {
        this.g = f;
    }

    public Object a() {
        return this.b;
    }

    public void b(Object obj) {
        this.b = obj;
    }

    public void d(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean i() {
        return this.e;
    }

    public boolean g() {
        return this.d;
    }

    public boolean f() {
        return (this.e || this.d) ? false : true;
    }

    public npf c(Transformer transformer) {
        float[] fArr = {b(), d()};
        transformer.pointValuesToPixel(fArr);
        npf npfVar = new npf(fArr[0], fArr[1], this.c);
        npfVar.e(this.f);
        npfVar.a(this.f15420a);
        return npfVar;
    }

    public Entry c() {
        return this.c;
    }

    public boolean j() {
        return this.f;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public boolean e() {
        return this.f15420a;
    }

    public void a(boolean z) {
        this.f15420a = z;
    }
}
