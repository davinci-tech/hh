package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class omj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("audioWorkoutList")
    private List<oly> f15785a;

    @SerializedName("audioTopicList")
    private List<Object> b;

    @SerializedName("resultCode")
    private String d;

    @SerializedName("resultDesc")
    private String e;

    public String c() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public List<oly> b() {
        return this.f15785a;
    }

    public String toString() {
        return "UserBehaviorListRsp{resultCode='" + this.d + "', resultDesc='" + this.e + "', audioWorkoutList=" + this.f15785a + ", audioTopicList=" + this.b + '}';
    }
}
