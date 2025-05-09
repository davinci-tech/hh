package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

/* loaded from: classes8.dex */
public class kas {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("compressionAlgorithm")
    private int f14238a;

    @SerializedName("bezierCurvePoint")
    private e b;

    @SerializedName("cutout")
    private int c;
    private final int d = 1;

    @SerializedName("photoHeight")
    private int e;

    @SerializedName("startPointY")
    private int f;

    @SerializedName("photoWidth")
    private int g;

    @SerializedName("radius")
    private float[] h;

    @SerializedName("roundedCutType")
    private int i;

    @SerializedName("startPointX")
    private int j;

    @SerializedName("watchType")
    private int k;

    public int e() {
        return 1;
    }

    public int a() {
        return this.f14238a;
    }

    public int f() {
        return this.j;
    }

    public int h() {
        return this.f;
    }

    public int j() {
        return this.g - (e() * 2);
    }

    public int d() {
        return this.e - (e() * 2);
    }

    public float[] i() {
        return this.h;
    }

    public int k() {
        return this.k;
    }

    public int b() {
        return this.c;
    }

    public e c() {
        return this.b;
    }

    public int g() {
        return this.i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ImageProcessParameters{compressionAlgorithm=");
        sb.append(this.f14238a);
        sb.append(", startPointX=");
        sb.append(this.j);
        sb.append(", startPointY=");
        sb.append(this.f);
        sb.append(", photoWidth=");
        sb.append(this.g);
        sb.append(", photoHeight=");
        sb.append(this.e);
        sb.append(", radian=");
        float[] fArr = this.h;
        sb.append(fArr != null ? Arrays.toString(fArr) : "[]");
        sb.append(", watchType=");
        sb.append(this.k);
        sb.append(", cutout=");
        sb.append(this.c);
        sb.append(", roundedCutType=");
        sb.append(this.i);
        sb.append('}');
        return sb.toString();
    }

    public static class e {

        @SerializedName("outPoint")
        private float[][] c;

        @SerializedName("inPoint")
        private float[][] e;

        public float[][] d() {
            return this.e;
        }

        public float[][] a() {
            return this.c;
        }
    }
}
