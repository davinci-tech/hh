package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/* loaded from: classes5.dex */
public class kwm {

    @SerializedName("endTime")
    private long b;

    @SerializedName("startTime")
    private long d;

    private static boolean e(long j, long j2) {
        return j / 1000 == j2 / 1000;
    }

    public kwm(long j, long j2) {
        this.d = j;
        this.b = j2;
    }

    public long b() {
        return this.b;
    }

    public boolean c(long j, long j2) {
        return e(j, this.d) && e(j2, this.b);
    }

    public boolean d(long j, long j2) {
        return this.d == j && this.b == j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        kwm kwmVar = (kwm) obj;
        return this.d == kwmVar.d && this.b == kwmVar.b;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.d), Long.valueOf(this.b));
    }
}
