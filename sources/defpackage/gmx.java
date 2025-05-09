package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes4.dex */
public class gmx implements Serializable {
    private static final long serialVersionUID = 6945617213218402229L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("name")
    private String f12871a;

    @SerializedName("contactVersion")
    private String b;

    @SerializedName("fromSystem")
    private boolean c = true;

    @SerializedName("phoneNumber")
    private String d;

    @SerializedName("uri")
    private String e;

    public String d() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String e() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public boolean b() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public String a() {
        return this.f12871a;
    }

    public void a(String str) {
        this.f12871a = str;
    }

    public String c() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String toString() {
        return "ContactInfo{uri='" + this.e + "', contactVersion='" + this.b + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gmx gmxVar = (gmx) obj;
        return this.b.equals(gmxVar.b) && Objects.equals(this.e, gmxVar.e);
    }

    public int hashCode() {
        return Objects.hash(this.e, this.b);
    }
}
