package defpackage;

import java.util.List;

/* loaded from: classes5.dex */
public class kek {

    /* renamed from: a, reason: collision with root package name */
    private int f14318a;
    private long b;
    private int c;
    private long d;
    private int e;
    private List<kel> g;

    public long d() {
        return this.d;
    }

    public void e(long j) {
        this.d = j;
    }

    public long b() {
        return this.b;
    }

    public void c(long j) {
        this.b = j;
    }

    public void c(int i) {
        this.f14318a = i;
    }

    public void a(int i) {
        this.c = i;
    }

    public List<kel> c() {
        return this.g;
    }

    public void e(List<kel> list) {
        this.g = list;
    }

    public int e() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public String toString() {
        return "BloodOxygenDownRemindStruct{mBloodOxygenDownRemindStartTime=" + this.d + ", mBloodOxygenDownRemindEndTime=" + this.b + ", mBloodOxygenDownRemindMinNumber=" + this.f14318a + ", mBloodOxygenDownRemindMaxNumber=" + this.c + ", mBloodOxygenDownValueStructs=" + this.g + ", mBloodOxygenDownThresholdNumber=" + this.e + '}';
    }
}
