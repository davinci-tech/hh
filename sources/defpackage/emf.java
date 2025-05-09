package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.trackprocess.model.GpsPoint;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class emf implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("punchNum")
    private int f12091a;

    @SerializedName("rotationAngle")
    private double b;

    @SerializedName("cpRelations")
    private List<emg> c;

    @SerializedName("cpGps")
    private List<GpsPoint> d;

    @SerializedName("drawBoard")
    private eml e;

    public List<GpsPoint> e() {
        return this.d;
    }

    public List<emg> a() {
        return this.c;
    }

    public int b() {
        return this.f12091a;
    }

    public eml c() {
        return this.e;
    }

    public String toString() {
        return "CpPoint{cpGps='" + this.d + ", cpRelations=" + this.c + ", rotationAngle=" + this.b + ", drawBoard=" + this.e + ", punchNum=" + this.f12091a + '}';
    }
}
