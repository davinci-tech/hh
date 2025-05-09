package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class omg {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resultCode")
    private String f15783a;

    @SerializedName("workoutHistoryList")
    private List<oma> c;

    @SerializedName("resultDesc")
    private String e;

    public List<oma> c() {
        return this.c;
    }

    public String e() {
        return this.f15783a;
    }

    public String a() {
        return this.e;
    }

    public String toString() {
        return "AudioWorkoutListHistoryRsp{workoutHistoryList=" + this.c + ", resultCode='" + this.f15783a + "', resultDesc='" + this.e + "'}";
    }
}
