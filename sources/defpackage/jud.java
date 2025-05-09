package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes5.dex */
public class jud {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("workoutRecordId")
    private int f14089a;

    @SerializedName("bloodOxygenIndex")
    private int b;

    @SerializedName("bloodOxygenStructList")
    private List<jua> d;

    public void d(int i) {
        this.f14089a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void a(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void e(List<jua> list) {
        this.d = (List) jdy.d(list);
    }
}
