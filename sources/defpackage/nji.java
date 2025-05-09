package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/* loaded from: classes6.dex */
public class nji {

    @SerializedName("switch")
    private String b;

    @SerializedName("modify_timestamp")
    private long c;

    public long b() {
        return this.c;
    }

    public void e(long j) {
        this.c = j;
    }

    public String e() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof nji)) {
            return false;
        }
        nji njiVar = (nji) obj;
        return Objects.equals(this.b, njiVar.b) && njiVar.c == this.c;
    }

    public int hashCode() {
        return Objects.hash(this.b, Long.valueOf(this.c));
    }

    public String toString() {
        return "SportSwitchBean{mModifiedTime='" + this.c + "', mSwitch=" + this.b + '}';
    }
}
