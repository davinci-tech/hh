package defpackage;

import com.huawei.hihealth.ResultUtils;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ecu implements Serializable {
    public long b;
    public long d;
    public long f;
    private long k;
    private long n;
    public int h = -1;

    /* renamed from: a, reason: collision with root package name */
    public int f11956a = -1;
    public int g = -1;
    public int i = -1;
    public int j = -1;
    public int c = -1;
    public int e = -1;

    public void d(int i) {
        this.h = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int c() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.h))).intValue();
    }

    public void a(int i) {
        this.f11956a = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int a() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.f11956a))).intValue();
    }

    public void e(int i) {
        this.g = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int d() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.g))).intValue();
    }

    public long e() {
        return ((Long) ResultUtils.a(Long.valueOf(this.n))).longValue();
    }

    public void e(long j) {
        this.n = ((Long) ResultUtils.a(Long.valueOf(j))).longValue();
    }

    public long b() {
        return ((Long) ResultUtils.a(Long.valueOf(this.k))).longValue();
    }

    public void b(long j) {
        this.k = ((Long) ResultUtils.a(Long.valueOf(j))).longValue();
    }

    public String toString() {
        return " FitnessHistogramData mHistogramHeight = " + this.g + "  mStartPoint = " + this.h + "  mEndPoint = " + this.f11956a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ecu ecuVar = (ecu) obj;
        return this.h == ecuVar.h && this.f11956a == ecuVar.f11956a && this.g == ecuVar.g && this.i == ecuVar.i && this.j == ecuVar.j && this.c == ecuVar.c && this.e == ecuVar.e && this.f == ecuVar.f && this.b == ecuVar.b && this.d == ecuVar.d;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.h), Integer.valueOf(this.f11956a), Integer.valueOf(this.g), Integer.valueOf(this.i), Integer.valueOf(this.j), Integer.valueOf(this.c), Integer.valueOf(this.e), Long.valueOf(this.f), Long.valueOf(this.b), Long.valueOf(this.d));
    }
}
