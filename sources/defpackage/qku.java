package defpackage;

import com.huawei.hihealth.HiHealthData;
import health.compact.a.util.LogUtil;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes6.dex */
public class qku implements Serializable {
    private static final long serialVersionUID = 7048918815967562789L;

    /* renamed from: a, reason: collision with root package name */
    private double f16464a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    private double j;
    private double l;
    private double m;
    private long o;

    public qku(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.e("AiBodyShapeBean", "hiHealthData is null, return");
            return;
        }
        this.f16464a = hiHealthData.getDouble("bustGirth");
        this.m = hiHealthData.getDouble("waistGirth");
        this.f = hiHealthData.getDouble("hipline");
        this.g = hiHealthData.getDouble("thighGirth");
        this.d = hiHealthData.getDouble("calves");
        this.e = hiHealthData.getDouble("armCircumference");
        this.i = hiHealthData.getDouble("headCircumference");
        this.j = hiHealthData.getDouble("legLength");
        this.c = hiHealthData.getDouble("armLength");
        this.h = hiHealthData.getDouble("shoulderWidth");
        this.l = hiHealthData.getDouble("waistHipRatio");
        this.b = hiHealthData.getDouble("bodyForm");
        this.o = hiHealthData.getStartTime();
    }

    public double c() {
        return this.b;
    }

    public double h() {
        return this.l;
    }

    public double a() {
        return this.e;
    }

    public double b() {
        return this.d;
    }

    public double j() {
        return this.g;
    }

    public double d() {
        return this.f;
    }

    public double i() {
        return this.m;
    }

    public double e() {
        return this.f16464a;
    }

    public long g() {
        return this.o;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        qku qkuVar = (qku) obj;
        return this.o == qkuVar.o && Double.compare(qkuVar.f16464a, this.f16464a) == 0 && Double.compare(qkuVar.m, this.m) == 0 && Double.compare(qkuVar.f, this.f) == 0 && Double.compare(qkuVar.g, this.g) == 0 && Double.compare(qkuVar.d, this.d) == 0 && Double.compare(qkuVar.e, this.e) == 0 && Double.compare(qkuVar.i, this.i) == 0 && Double.compare(qkuVar.j, this.j) == 0 && Double.compare(qkuVar.c, this.c) == 0 && Double.compare(qkuVar.h, this.h) == 0 && Double.compare(qkuVar.l, this.l) == 0 && Double.compare(qkuVar.b, this.b) == 0;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.o), Double.valueOf(this.f16464a), Double.valueOf(this.m), Double.valueOf(this.f), Double.valueOf(this.g), Double.valueOf(this.d), Double.valueOf(this.e), Double.valueOf(this.i), Double.valueOf(this.j), Double.valueOf(this.c), Double.valueOf(this.h), Double.valueOf(this.l), Double.valueOf(this.b));
    }
}
