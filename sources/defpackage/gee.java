package defpackage;

import com.google.gson.annotations.Expose;
import java.util.Objects;

/* loaded from: classes8.dex */
public class gee {

    @Expose
    private long b;

    @Expose
    private int d;

    public int e() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public long a() {
        return this.b;
    }

    public void e(long j) {
        this.b = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gee geeVar = (gee) obj;
        return this.b == geeVar.b && this.d == geeVar.d;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.b), Integer.valueOf(this.d));
    }

    public String toString() {
        return "SportData{mGolfTotalTimes=" + this.b + ", mGolfTotalScore=" + this.d + '}';
    }
}
