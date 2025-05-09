package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class emj implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pathTypes")
    private List<eni> f12094a;

    @SerializedName("pathThemes")
    private List<eni> b;

    @SerializedName("pathClasses")
    private List<eni> d;

    @SerializedName("distanceTypes")
    private List<eni> e;

    public List<eni> e() {
        return this.b;
    }

    public List<eni> c() {
        return this.f12094a;
    }

    public List<eni> b() {
        return this.e;
    }

    public String toString() {
        return "CityPathAttribute{pathClasses=" + this.d + ", pathThemes=" + this.b + ", pathTypes=" + this.f12094a + ", distanceTypes=" + this.e + '}';
    }
}
