package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class enm implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("thumbnailForDevice")
    private String f12111a;

    @SerializedName("thumbnailBackground")
    private String b;

    @SerializedName("thumbnailForDeviceSmall")
    private String c;

    @SerializedName("landscapeImages")
    private List<String> d;

    @SerializedName("alternateDomainName")
    private List<String> e;

    @SerializedName("thumbnailImage")
    private String h;

    public String b() {
        return (!koq.b(this.e) ? this.e.get(0) : "") + "/" + this.h;
    }

    public String d() {
        if (TextUtils.isEmpty(this.f12111a)) {
            return "";
        }
        return (koq.b(this.e) ? "" : this.e.get(0)) + "/" + this.f12111a;
    }

    public String a() {
        if (TextUtils.isEmpty(this.c)) {
            return "";
        }
        return (koq.b(this.e) ? "" : this.e.get(0)) + "/" + this.c;
    }

    public String c() {
        if (TextUtils.isEmpty(this.b)) {
            return "";
        }
        return (koq.b(this.e) ? "" : this.e.get(0)) + "/" + this.b;
    }

    public List<String> e() {
        return this.d;
    }

    public String toString() {
        return "PathImageInfo{thumbnailImage='" + this.h + "', thumbnailForDevice=" + this.f12111a + ", thumbnailForDeviceSmall=" + this.c + ", landscapeImage=" + this.d + ", alternateDomainName=" + this.e + ", thumbnailBackground=" + this.b + '}';
    }
}
