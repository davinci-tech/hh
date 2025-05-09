package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes5.dex */
public class jib {

    /* renamed from: a, reason: collision with root package name */
    private long f13861a;
    private DeviceInfo d;
    private int i;
    private int g = 0;
    private int c = 0;
    private int e = 0;
    private int b = 0;

    public int h() {
        return this.i;
    }

    public void f(int i) {
        this.i = i;
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public void j(int i) {
        this.c = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void c(int i) {
        this.c += i;
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.e))).intValue();
    }

    public void a(int i) {
        this.e = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void d(int i) {
        this.e += i;
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void e(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void b(int i) {
        this.b += i;
    }

    public long d() {
        return ((Long) jdy.d(Long.valueOf(this.f13861a))).longValue();
    }

    public void e(long j) {
        this.f13861a = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public DeviceInfo a() {
        return this.d;
    }

    public void a(DeviceInfo deviceInfo) {
        this.d = deviceInfo;
    }

    public String toString() {
        return "LastTotalValue{sportType=" + this.g + ", lastTotalSteps=" + this.c + ", lastTotalCalories=" + this.e + ", lastTotalDistance=" + this.b + ", lastTimeStamp=" + this.f13861a + '}';
    }
}
