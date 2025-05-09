package defpackage;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes4.dex */
public class fiu {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("foodId")
    private String f12532a;

    @SerializedName("extensionUnits")
    private List<fjb> b;

    @SerializedName("fat")
    private float c;

    @SerializedName("foodName")
    private String d;

    @SerializedName("carbohydrate")
    private float e;

    @SerializedName("imageUrl")
    private String f;

    @SerializedName("gi")
    private float g;

    @SerializedName("mealType")
    private int h;

    @SerializedName("protein")
    private float i;

    @SerializedName("heat")
    private float j;

    @SerializedName("unit")
    private String l;

    @SerializedName("value")
    private int o;

    public String a() {
        return this.f12532a;
    }

    public String b() {
        return this.d;
    }

    public int o() {
        return this.o;
    }

    public float g() {
        return this.j;
    }

    public String i() {
        return this.f;
    }

    public String l() {
        return this.l;
    }

    public int h() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    public float f() {
        return this.g;
    }

    public float c() {
        return this.e;
    }

    public float e() {
        return this.c;
    }

    public float j() {
        return this.i;
    }

    public List<fjb> d() {
        return this.b;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
