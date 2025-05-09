package defpackage;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class npx {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("id")
    private int f15433a;

    @SerializedName("muscleName")
    private String b;

    @SerializedName("muscleFunction")
    private String d;

    public static class b extends TypeToken<ArrayList<npx>> {
    }

    public String c() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public String toString() {
        return "HealthMuscleInfo{id=" + this.f15433a + ", muscleName=" + this.b + ", muscleFunction=" + this.d;
    }
}
