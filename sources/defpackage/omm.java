package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class omm {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("audioWorkoutList")
    private List<oly> f15787a;

    @SerializedName("resultDesc")
    private String c;

    @SerializedName("resultCode")
    private String d;

    public String b() {
        return this.d;
    }

    public String c() {
        return this.c;
    }

    public List<oly> e() {
        return this.f15787a;
    }

    public String toString() {
        return "UserPlayRecordRsp{resultCode='" + this.d + "', resultDesc='" + this.c + "', audioWorkoutList=" + this.f15787a + '}';
    }
}
