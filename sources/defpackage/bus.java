package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class bus {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("completeValue")
    private double f516a;

    @SerializedName("id")
    private long c;

    @SerializedName("recordDays")
    private List<Long> d;

    @SerializedName("status")
    private int e;

    public void a(long j) {
        this.c = j;
    }

    public void c(double d) {
        this.f516a = d;
    }

    public void a(List<Long> list) {
        this.d = list;
    }

    public void b(int i) {
        this.e = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("UpdateGoalBean{id=");
        stringBuffer.append(this.c);
        stringBuffer.append(", completeValue=").append(this.f516a);
        stringBuffer.append(", recordDays=").append(this.d);
        stringBuffer.append(", status=").append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
