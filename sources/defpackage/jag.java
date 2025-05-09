package defpackage;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class jag {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("uploadUrl")
    private String f13699a;

    @SerializedName("uploadHeaders")
    private JsonObject b;

    @SerializedName("uploadMethod")
    private String d;

    @SerializedName("filePartId")
    private String e;

    public String b() {
        return this.f13699a;
    }

    public String c() {
        return this.d;
    }

    public JsonObject a() {
        return this.b;
    }

    public void e(String str) {
        this.f13699a = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(JsonObject jsonObject) {
        this.b = jsonObject;
    }
}
