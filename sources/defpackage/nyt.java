package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.util.List;

/* loaded from: classes6.dex */
public class nyt {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(FaqConstants.FAQ_MODELTYPE)
    private String f15561a;

    @SerializedName("endVersion")
    private String b;

    @SerializedName("featureId")
    private String c;

    @SerializedName("modelId")
    private String d;

    @SerializedName("startVersion")
    private String e;

    @SerializedName("versionMap")
    private List<nza> f;

    public String c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String a() {
        return this.b;
    }

    public List<nza> e() {
        return this.f;
    }
}
