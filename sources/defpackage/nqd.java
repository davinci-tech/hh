package defpackage;

import android.graphics.Paint;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class nqd {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("endX")
    private float f15439a;

    @SerializedName("endY")
    private float b;
    private Paint c;

    @SerializedName("centerY")
    private float d;

    @SerializedName("centerX")
    private float e;

    @SerializedName("pathData")
    private String f;

    @SerializedName("startY")
    private float h;

    @SerializedName("startX")
    private float i;

    public float b() {
        return this.e;
    }

    public float c() {
        return this.d;
    }

    public String d() {
        return this.f;
    }

    public float g() {
        return this.i;
    }

    public float h() {
        return this.h;
    }

    public float e() {
        return this.b;
    }

    public Paint cDP_() {
        return this.c;
    }

    public void cDQ_(Paint paint) {
        this.c = paint;
    }
}
