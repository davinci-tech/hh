package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class qul {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("eatTime")
    private long f16596a;

    @SerializedName("modifiedTime")
    private long b;

    @SerializedName("index")
    private int c;

    @SerializedName("kiloCalorie")
    private float d;

    @SerializedName("foodDescriptors")
    private List<quk> e;

    @SerializedName("whichMeal")
    private int f;

    @SerializedName("setTime")
    private long g;

    @SerializedName("timeZone")
    private String i;

    @SerializedName("sugRange")
    private int[] j;

    public qul(int i, long j, int i2, List<quk> list) {
        this.f = i;
        this.f16596a = j;
        this.d = i2;
        this.e = new ArrayList(list);
    }

    public void c(String str) {
        this.i = str;
    }

    public void l() {
        if (String.valueOf(this.f16596a).matches("\\d{13}")) {
            this.f16596a /= 1000;
        }
        if (koq.b(this.e)) {
            return;
        }
        Iterator<quk> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().l();
        }
    }

    public void m() {
        if (String.valueOf(this.f16596a).matches("\\d{10}")) {
            this.f16596a *= 1000;
        }
        if (koq.b(this.e)) {
            return;
        }
        Iterator<quk> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().k();
        }
    }

    public long g() {
        return this.g;
    }

    public void a(long j) {
        this.g = j;
    }

    public int a() {
        return this.c;
    }

    public int h() {
        return this.f;
    }

    public long d() {
        return this.f16596a;
    }

    public void e(long j) {
        this.f16596a = j;
    }

    public long i() {
        return this.b;
    }

    public void c(long j) {
        this.b = j;
    }

    public float b() {
        return this.d;
    }

    public void c(float f) {
        this.d = f;
    }

    public List<quk> c() {
        return this.e;
    }

    public int[] f() {
        return this.j;
    }

    public void a(int[] iArr) {
        this.j = iArr;
    }

    public float e() {
        float f = 0.0f;
        if (koq.b(this.e)) {
            return 0.0f;
        }
        Iterator<quk> it = this.e.iterator();
        while (it.hasNext()) {
            f += it.next().h();
        }
        return f;
    }

    public String j() {
        return this.i;
    }

    public int hashCode() {
        if (h() != 0) {
            return Integer.valueOf(h()).hashCode() + Integer.valueOf(a()).hashCode();
        }
        return Long.valueOf(d()).hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof qul)) {
            return false;
        }
        qul qulVar = (qul) obj;
        return h() != 0 ? h() == qulVar.h() && a() == qulVar.a() : d() == qulVar.d();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Meal{whichMeal=");
        sb.append(this.f);
        sb.append(", eatTime=");
        sb.append(this.f16596a);
        sb.append(", timeZone =");
        sb.append(this.i);
        sb.append(", setTime=");
        sb.append(this.g);
        sb.append(", index=");
        sb.append(this.c);
        sb.append(", kcal=");
        sb.append(this.d);
        sb.append(", modifiedTime=");
        sb.append(this.b);
        sb.append(", sugRange=");
        sb.append(this.j);
        sb.append(", foodDescriptors=");
        List<quk> list = this.e;
        sb.append(list == null ? new ArrayList() : list.toString());
        sb.append('}');
        return sb.toString();
    }
}
