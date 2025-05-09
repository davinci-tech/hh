package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class fcm implements Serializable {

    @SerializedName("trans")
    private int c;

    @SerializedName("val")
    private int e;

    public void d(int i) {
        this.e = i;
    }

    public int e() {
        return this.e;
    }

    public void b(int i) {
        this.c = i;
    }

    public int d() {
        return this.c;
    }

    public String toString() {
        return "{\"DailyProblemDescInput\": {\"val\":" + this.e + ", \"trans\":" + this.c + "}}";
    }
}
