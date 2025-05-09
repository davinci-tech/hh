package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class pwb implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("deviceName")
    private String f16289a;
    private Object b;

    @SerializedName("deviceUniqueCode")
    private String c;

    @SerializedName("productId")
    private String d;

    @SerializedName("deviceModel")
    private String e;

    @SerializedName("type")
    private int g;

    public Object b() {
        return this.b;
    }

    public void b(Object obj) {
        this.b = obj;
    }

    public int c() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.f16289a;
    }

    public void b(String str) {
        this.f16289a = str;
    }

    public String a() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String e() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String toString() {
        return "DataOriginListItem{mType=" + this.g + ", mProductId='" + this.d + "', mDeviceName='" + this.f16289a + "', mDeviceModel='" + this.e + "', mDeviceUniqueCode='" + this.c + "', mExtraInfo=" + this.b + '}';
    }
}
