package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.Objects;

/* loaded from: classes6.dex */
public class njc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(ParsedFieldTag.GOAL)
    private int f15323a;

    @SerializedName("modifiedTime")
    private long c;

    public long d() {
        return this.c;
    }

    public void c(long j) {
        this.c = j;
    }

    public int e() {
        return this.f15323a;
    }

    public void e(int i) {
        this.f15323a = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof njc)) {
            return false;
        }
        njc njcVar = (njc) obj;
        return njcVar.f15323a == this.f15323a && njcVar.c == this.c;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f15323a), Long.valueOf(this.c));
    }

    public String toString() {
        return "CircleGoalBean{mModifiedTime='" + this.c + "', mGoalValue=" + this.f15323a + '}';
    }
}
