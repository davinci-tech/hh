package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class dsc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("doctorDepartment")
    private String f11815a;

    @SerializedName("doctorLevel")
    private String b;

    @SerializedName("doctorHospital")
    private String c;

    @SerializedName("doctorId")
    private String d;

    @SerializedName("doctorHeadIcon")
    private String e;

    @SerializedName("resultDesc")
    private String f;

    @SerializedName("doctorName")
    private String g;

    @SerializedName("resultCode")
    private int j;

    public int h() {
        return this.j;
    }

    public String b() {
        return this.d;
    }

    public String a() {
        return this.g;
    }

    public String e() {
        return this.e;
    }

    public String c() {
        return this.f11815a;
    }

    public String d() {
        return this.b;
    }

    public String toString() {
        return "DoctorBasicInfoResponse{mResultCode=" + this.j + ", mResultDescription=" + this.f + ", mDoctorId=" + this.d + ", mDoctorName=" + this.g + ", mDoctorHeadIcon=" + this.e + ", mDoctorDepartment=" + this.f11815a + ", mDoctorLevel=" + this.b + ", mDoctorHospital=" + this.c + "}";
    }
}
