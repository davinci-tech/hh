package defpackage;

import com.google.gson.annotations.SerializedName;
import com.tencent.open.SocialConstants;

/* loaded from: classes6.dex */
public class ome {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String f15782a;

    @SerializedName("label")
    private String b;

    @SerializedName("lectureId")
    private String c;

    @SerializedName("headPhoto")
    private String d;

    @SerializedName("name")
    private String e;

    @SerializedName("qualification")
    private String j;

    public String b() {
        return this.e;
    }

    public String c() {
        return this.b;
    }

    public String toString() {
        return "LecturerDaoBean{lectureId='" + this.c + "', name='" + this.e + "', headPhoto='" + this.d + "', label='" + this.b + "', desc='" + this.f15782a + "', qualification='" + this.j + "'}";
    }
}
