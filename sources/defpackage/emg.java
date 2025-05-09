package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class emg implements Serializable {

    @SerializedName("end")
    private int b;

    @SerializedName("start")
    private int e;

    public int e() {
        return this.e;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return "CpRelation{start='" + this.e + ", end=" + this.b + '}';
    }
}
