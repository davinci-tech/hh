package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes8.dex */
public class dsg {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("unReadMsgCount")
    private int f11817a;

    @SerializedName("messages")
    private List<dru> b;

    @SerializedName("resultDesc")
    private String d;

    @SerializedName("resultCode")
    private int e;

    public int c() {
        return this.e;
    }

    public List<dru> a() {
        return this.b;
    }

    public int b() {
        return this.f11817a;
    }

    public String toString() {
        return "DoctorImInfoResponse{mResultCode=" + this.e + ", mResultDescription=" + this.d + ", mDoctorImInfoMapList=" + this.b + "}";
    }
}
