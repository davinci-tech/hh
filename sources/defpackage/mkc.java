package defpackage;

import com.huawei.pluginachievement.ui.model.BestAchievementBasic;

/* loaded from: classes6.dex */
public class mkc extends BestAchievementBasic {

    /* renamed from: a, reason: collision with root package name */
    private double f15032a;
    private long b;
    private int c;
    private long d;
    private long e;

    public double b() {
        return this.f15032a;
    }

    public void a(double d) {
        this.f15032a = d;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public long a() {
        return this.d;
    }

    public void d(long j) {
        this.d = j;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public long d() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public String toString() {
        return "BestMotionPace{value=" + this.f15032a + ", startTime=" + this.e + ", source=" + this.c + ", deviceCode=" + this.d + '}';
    }
}
