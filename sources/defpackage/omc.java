package defpackage;

import com.google.gson.annotations.SerializedName;
import com.tencent.open.SocialConstants;
import java.util.List;

/* loaded from: classes9.dex */
public class omc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String f15780a;

    @SerializedName("id")
    private long b;

    @SerializedName("headPhoto")
    private String c;

    @SerializedName("label")
    private String d;

    @SerializedName("lecturerId")
    private String e;

    @SerializedName("lecturerWorks")
    private List<oly> f;

    @SerializedName("resultCode")
    private String g;

    @SerializedName("resultDesc")
    private String h;

    @SerializedName("qualification")
    private String i;

    @SerializedName("name")
    private String j;

    public String a() {
        return this.g;
    }

    public String c() {
        return this.h;
    }

    public String d() {
        return this.j;
    }

    public String e() {
        return this.d;
    }

    public List<oly> b() {
        return this.f;
    }

    public String toString() {
        return "AudioWorkoutLecturerInfoRsp{resultCode='" + this.g + "', resultDesc='" + this.h + "', id=" + this.b + ", lecturerId='" + this.e + "', name='" + this.j + "', headPhoto='" + this.c + "', label='" + this.d + "', qualification='" + this.i + "', desc='" + this.f15780a + "', lecturerWorks=" + this.f + '}';
    }
}
