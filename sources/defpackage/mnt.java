package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class mnt implements Serializable, Cloneable {
    private static final long serialVersionUID = 5664805502014025933L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("durationMan")
    private long f15070a;

    @SerializedName("picture")
    private String b;

    @SerializedName("id")
    private String c;

    @SerializedName("name")
    private String d;

    @SerializedName("duration")
    private long e;

    public String b() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.b;
    }

    public long c() {
        if (ggg.a() == 0) {
            long j = this.f15070a;
            if (j != 0) {
                return j;
            }
        }
        return this.e;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
