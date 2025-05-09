package defpackage;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class gsg {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("activityCalories")
    private int f12905a;

    @SerializedName("caloriesGap")
    private int b;

    @SerializedName(TypedValues.CycleType.S_WAVE_PERIOD)
    private int d;

    public int d() {
        return this.f12905a;
    }

    public int a() {
        return this.b;
    }

    public int e() {
        return this.d;
    }

    public String toString() {
        return "SimpleFatInfo{activityCalories=" + this.f12905a + ", caloriesGap=" + this.b + ", period=" + this.d + '}';
    }
}
