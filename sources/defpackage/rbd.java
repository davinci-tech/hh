package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/* loaded from: classes7.dex */
public class rbd {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("huid")
    private long f16692a;

    @SerializedName("createTime")
    private long c;

    @SerializedName("memberStatus")
    private int d;

    public void a(long j) {
        this.f16692a = j;
    }

    public long a() {
        return this.f16692a;
    }

    public void e(int i) {
        this.d = i;
    }

    public int d() {
        return this.d;
    }

    public long c() {
        return this.c;
    }

    public void d(long j) {
        this.c = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rbd)) {
            return false;
        }
        rbd rbdVar = (rbd) obj;
        return a() == rbdVar.a() && d() == rbdVar.d() && c() == rbdVar.c();
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(a()), Integer.valueOf(d()), Long.valueOf(c()));
    }
}
