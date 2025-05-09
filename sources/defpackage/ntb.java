package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class ntb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("abnormalContinuousDay")
    private int f15477a;

    @SerializedName("abnormalStepTipsPeriod")
    private int b;

    @SerializedName("abnormalStepSupportType")
    private String c;

    @SerializedName("courseDetailStyle")
    private int d;

    @SerializedName("afterProcessSwitch")
    private int e;

    @SerializedName("sleepManagementReleaseSupportDeviceType")
    private String g;

    @SerializedName("supportShowStepTipDeviceList")
    private List<String> h;

    @SerializedName("sleepManagementSupportVersions")
    private List<String> i;

    @SerializedName("sleepManagementDefaultSupportDeviceType")
    private String j;

    public int b() {
        return this.d;
    }

    public int a() {
        return this.e;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.f15477a;
    }

    public String c() {
        return this.c;
    }

    public List<String> f() {
        return this.h;
    }

    public String toString() {
        return "TemporaryConfigContent{mSupportVersions=" + this.i + ", mReleaseSupportDeviceType='" + this.g + "', mDefaultSupportDeviceType='" + this.j + "', mCourseDetailStyle=" + this.d + ", mAfterProcessSwitch=" + this.e + ", mAbnormalStepTipsSupportDeviceType='" + this.c + "', mAbnormalStepTipsPeriod=" + this.b + ", mAbnormalContinuousDay=" + this.f15477a + ", mSupportShowStepTipDeviceList=" + this.h + '}';
    }
}
