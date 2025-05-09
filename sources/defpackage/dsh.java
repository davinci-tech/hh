package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class dsh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resultCode")
    private int f11818a;

    @SerializedName("infoList")
    private List<dry> e;

    public int b() {
        return this.f11818a;
    }

    public List<dry> e() {
        return this.e;
    }

    public String toString() {
        return "GetUserSampleConfigResponse{mResultCode=" + this.f11818a + ", mGetUserSampleConfigResultList=" + this.e + "}";
    }
}
