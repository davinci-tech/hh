package defpackage;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;

/* loaded from: classes9.dex */
public class jam {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("uploadHeaders")
    private JsonObject f13704a;

    @SerializedName("uploadMethod")
    private String b;

    @SerializedName(ContentResource.FILE_NAME)
    private String c;

    @SerializedName("uploadUrl")
    private String d;

    @SerializedName(RecommendConstants.FILE_ID)
    private String e;

    public String a() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public String d() {
        return this.b;
    }

    public JsonObject e() {
        return this.f13704a;
    }
}
