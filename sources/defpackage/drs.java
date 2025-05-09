package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes3.dex */
public class drs {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("summaryComment")
    private String f11809a;

    @SerializedName("detailComment")
    private String c;

    @SerializedName("doctorInfo")
    private String d;

    @SerializedName("dimensionComments")
    private Map<String, String> e;

    public String c() {
        return this.f11809a;
    }

    public String toString() {
        return "CommentInfo{mDoctorInfo=" + this.d + ", mSummaryComment=" + this.f11809a + ", mDetailComment=" + this.c + ", mDimensionComments=" + this.e + "}";
    }
}
