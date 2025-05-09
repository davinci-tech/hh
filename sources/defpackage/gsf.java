package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.knit.ui.KnitFragment;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class gsf implements Serializable {
    private static final long serialVersionUID = 7505480085796770549L;

    @SerializedName("dinner")
    private int b;

    @SerializedName("lunch")
    private int c;

    @SerializedName(KnitFragment.KEY_EXTRA)
    private int d;

    @SerializedName("breakfast")
    private int e;

    public void c(int i) {
        this.e = i;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public void e(int i) {
        this.d = i;
    }

    public String toString() {
        return "ProportionMeal{mBreakfast=" + this.e + ", mLunch=" + this.c + ", mDinner=" + this.b + ", mExtra=" + this.d + '}';
    }
}
