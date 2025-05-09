package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class gsh implements Serializable {
    private static final long serialVersionUID = 7505480085796770578L;

    @SerializedName("dinner")
    private int b;

    @SerializedName("breakfast")
    private int c;

    @SerializedName("lunch")
    private int e;

    public int a() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public void e(int i) {
        this.e = i;
    }

    public void a(int i) {
        this.b = i;
    }

    public String toString() {
        return "OnePointFull{breakfast=" + this.c + ", lunch=" + this.e + ", dinner=" + this.b + '}';
    }
}
