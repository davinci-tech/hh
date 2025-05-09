package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class dsb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("customTaskListDetails")
    private List<Object> f11814a;

    @SerializedName("dimensionListDetails")
    private List<Object> b;

    @SerializedName("commentInfo")
    private drs c;

    @SerializedName("commentStatus")
    private int d;

    @SerializedName("challengeId")
    private String e;

    @SerializedName("version")
    private String g;

    @SerializedName("mainTargetDetails")
    private List<Object> h;

    @SerializedName("interventionPlan")
    private drw j;

    public String d() {
        return this.e;
    }

    public drs e() {
        return this.c;
    }

    public int a() {
        return this.d;
    }

    public String toString() {
        return "HealthWeekReport{mVersion=" + this.g + ", mChallengeId=" + this.e + ", mInterventionPlan=" + this.j + ", mCommentInfo=" + this.c + ", mCommentStatus=" + this.d + ", mMainTargetSubDataDetailList=" + this.h + ", mDimensionDetailList=" + this.b + ", mCustomTaskDetailList=" + this.f11814a + "}";
    }
}
