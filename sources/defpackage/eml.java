package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.trackprocess.model.GpsPoint;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class eml implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("center")
    private GpsPoint f12095a;

    @SerializedName("ld")
    private GpsPoint b;

    @SerializedName("rd")
    private GpsPoint c;

    @SerializedName("height")
    private double d;

    @SerializedName("lu")
    private GpsPoint e;

    @SerializedName("rotationAngle")
    private double f;

    @SerializedName("width")
    private double i;

    @SerializedName("ru")
    private GpsPoint j;

    public double d() {
        return this.i;
    }

    public double c() {
        return this.f;
    }

    public GpsPoint e() {
        return this.f12095a;
    }

    public String toString() {
        return "DrawBoard{width=" + this.i + ", height=" + this.d + ", rotationAngle=" + this.f + ", center=" + this.f12095a + ", leftUp=" + this.e + ", leftBottom=" + this.b + ", rightUp=" + this.j + ", rightBottom=" + this.c + '}';
    }
}
