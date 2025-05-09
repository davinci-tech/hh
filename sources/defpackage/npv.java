package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class npv {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("cityCenterLatLng")
    npq f15432a;

    @SerializedName("cityName")
    String b;

    @SerializedName("detailAddress")
    String e;

    public String b() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String c() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public npq d() {
        return this.f15432a;
    }

    public void a(npq npqVar) {
        this.f15432a = npqVar;
    }
}
