package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class qvi {

    @SerializedName("targetSettingChanged")
    private int b;

    @SerializedName("fatBurnChoice")
    private int c;

    @SerializedName("targetWeight")
    private double e;

    public void e(double d) {
        this.e = d;
    }

    public void b(int i) {
        this.b = i;
    }

    public void d(int i) {
        this.c = i;
    }

    public String toString() {
        return "Goal{targetWeight=" + this.e + ", targetSettingChanged=" + this.b + ", fatBurnChoice=" + this.c + '}';
    }
}
