package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class gyo {

    @SerializedName("mPace")
    private float b;

    @SerializedName("mRecordNum")
    private int d;

    public void e(int i) {
        this.d = i;
    }

    public void d(float f) {
        this.b = f;
    }

    public String toString() {
        return "HistoryBestResult{mRecordNum=" + this.d + ", mPace=" + this.b + '}';
    }
}
