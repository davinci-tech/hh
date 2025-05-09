package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;

/* loaded from: classes6.dex */
public class mqp {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("clientTime")
    private Long f15115a;

    @SerializedName("altitude")
    private Double b;

    @SerializedName(JsbMapKeyNames.H5_LOC_LON)
    private Double c;

    @SerializedName("coordinate")
    private String d;

    @SerializedName(JsbMapKeyNames.H5_LOC_LAT)
    private Double e;

    @SerializedName("serverTime")
    private Long g;

    @SerializedName("type")
    private int j;

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public Double a() {
        return this.c;
    }

    public void d(Double d) {
        this.c = d;
    }

    public Double e() {
        return this.e;
    }

    public void c(Double d) {
        this.e = d;
    }

    public Double c() {
        return this.b;
    }

    public void b(Double d) {
        this.b = d;
    }

    public Long d() {
        return this.f15115a;
    }

    public void c(Long l) {
        this.f15115a = l;
    }

    public int j() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }
}
