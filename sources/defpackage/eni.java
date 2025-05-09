package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class eni implements Serializable {

    @SerializedName("id")
    private int b;

    @SerializedName("name")
    private String c;

    public eni(String str, int i) {
        this.c = str;
        this.b = i;
    }

    public String b() {
        String str = this.c;
        return str == null ? "" : str;
    }

    public int d() {
        return this.b;
    }

    public String toString() {
        return this.c;
    }
}
