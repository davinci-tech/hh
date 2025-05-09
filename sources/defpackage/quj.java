package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class quj {

    @SerializedName("initialWeight")
    private float b = 0.0f;

    @SerializedName("targetWeight")
    private float c = 0.0f;

    @SerializedName("fulfilledTime")
    private long e = 0;

    public void d(float f) {
        this.b = f;
    }

    public void a(float f) {
        this.c = f;
    }

    public String toString() {
        return "WeightControlSetting{mInitialWeight=" + this.b + ", mTargetWeight=" + this.c + ", mFulfilledTime=" + this.e + '}';
    }
}
