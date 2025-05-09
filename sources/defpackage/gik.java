package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes8.dex */
public class gik {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("defaultClearIndex")
    private Integer f12816a;

    @SerializedName("clearMap")
    private Map<String, String> b;

    @SerializedName("screenType")
    private int c;

    @SerializedName("isClears")
    private Boolean d;

    @SerializedName("isLoop")
    private Boolean e;

    @SerializedName("videoName")
    private String h;

    @SerializedName("url")
    private String i;

    public Integer a() {
        return Integer.valueOf(this.c);
    }

    public String e() {
        return this.i;
    }

    public String d() {
        return this.h;
    }

    public Boolean g() {
        return this.e;
    }

    public Boolean j() {
        return this.d;
    }

    public Map<String, String> b() {
        return this.b;
    }

    public Integer c() {
        return this.f12816a;
    }
}
