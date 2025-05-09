package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes4.dex */
public class fhn {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("sportConfigs")
    private List<fhi> f12515a;

    @SerializedName("version")
    private String b;

    @SerializedName("baseConfig")
    private fhi c;

    @SerializedName("template_id")
    private String e;

    public String a() {
        return this.b;
    }

    public fhi e() {
        return this.c;
    }

    public List<fhi> b() {
        return this.f12515a;
    }
}
