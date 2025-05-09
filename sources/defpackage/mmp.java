package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;

/* loaded from: classes6.dex */
public class mmp {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("algId")
    private String f15060a;

    @SerializedName("topicName")
    private String aa;

    @SerializedName("resourceID")
    private String ab;

    @SerializedName("resourceName")
    private String ac;

    @SerializedName("subtitleUrl")
    private String ad;

    @SerializedName("trackTargetValue")
    private float ae;

    @SerializedName("version")
    private String af;

    @SerializedName("trackType")
    private int ag;

    @SerializedName("trackTarget")
    private int ah;

    @SerializedName("userDefinedType")
    private int ai;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String ak;

    @SerializedName("workoutPackageId")
    private String am;

    @SerializedName("weekNumber")
    private int an;

    @SerializedName("hasNextTrain")
    private boolean b;

    @SerializedName("abInfo")
    private String c;

    @SerializedName("entrance")
    private String d;

    @SerializedName("isAfterRun")
    private boolean f;

    @SerializedName("isDeviceTraining")
    private boolean h;

    @SerializedName("entranceType")
    private String j;

    @SerializedName("isNeedExecuteDown")
    private boolean k;

    @SerializedName("isFromScheme")
    private boolean l;

    @SerializedName("isImmediateStart")
    private boolean m;

    @SerializedName("isPlanFit")
    private boolean n;

    @SerializedName("isSendCourseDevice")
    private boolean o;

    @SerializedName("moveTaskToBack")
    private boolean p;

    @SerializedName("planExecuteTime")
    private long q;

    @SerializedName("longCoachUrl")
    private String r;

    @SerializedName("itemId")
    private String s;

    @SerializedName("isShowButton")
    private boolean t;

    @SerializedName("planTempId")
    private String u;

    @SerializedName("pullOrder")
    private String v;

    @SerializedName("pullFrom")
    private String w;

    @SerializedName("promoteTag")
    private String x;

    @SerializedName("planId")
    private String y;

    @SerializedName("resourceType")
    private String z;

    @SerializedName("workoutOrder")
    private int al = 1;

    @SerializedName("executeImmediateErrorType")
    private int i = -1;

    @SerializedName("enterAnim")
    private int e = -1;

    @SerializedName("exitAnim")
    private int g = -1;

    public mmp(String str) {
        this.ak = str;
    }

    public boolean ah() {
        return this.l;
    }

    public void c(boolean z) {
        this.l = z;
    }

    public String ab() {
        return this.ak;
    }

    public void s(String str) {
        this.ak = str;
    }

    public String v() {
        return this.af;
    }

    public void r(String str) {
        this.af = str;
    }

    public String c() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public String a() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String ad() {
        return this.am;
    }

    public void q(String str) {
        this.am = str;
    }

    public String p() {
        return this.z;
    }

    public String l() {
        return this.u;
    }

    public void g(String str) {
        this.u = str;
    }

    public String k() {
        return this.w;
    }

    public void j(String str) {
        this.w = str;
    }

    public String r() {
        return this.ab;
    }

    public void o(String str) {
        this.ab = str;
    }

    public String t() {
        return this.ac;
    }

    public void k(String str) {
        this.ac = str;
    }

    public String o() {
        return this.v;
    }

    public void l(String str) {
        this.v = str;
    }

    public String b() {
        return this.f15060a;
    }

    public void b(String str) {
        this.f15060a = str;
    }

    public String n() {
        return this.x;
    }

    public void f(String str) {
        this.x = str;
    }

    public String g() {
        return this.s;
    }

    public void e(String str) {
        this.s = str;
    }

    public String m() {
        return this.y;
    }

    public void h(String str) {
        this.y = str;
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public int x() {
        return this.ai;
    }

    public void g(int i) {
        this.ai = i;
    }

    public boolean an() {
        return this.o;
    }

    public void i(boolean z) {
        this.o = z;
    }

    public boolean ag() {
        return this.p;
    }

    public void f(boolean z) {
        this.p = z;
    }

    public long f() {
        return this.q;
    }

    public void e(long j) {
        this.q = j;
    }

    public String i() {
        return this.r;
    }

    public void i(String str) {
        this.r = str;
    }

    public String s() {
        return this.ad;
    }

    public void m(String str) {
        this.ad = str;
    }

    public int z() {
        return this.al;
    }

    public void i(int i) {
        this.al = i;
    }

    public int ac() {
        return this.an;
    }

    public void f(int i) {
        this.an = i;
    }

    public boolean am() {
        return this.n;
    }

    public void h(boolean z) {
        this.n = z;
    }

    public boolean aa() {
        return this.f;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public int j() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public boolean aj() {
        return this.k;
    }

    public void g(boolean z) {
        this.k = z;
    }

    public boolean al() {
        return this.t;
    }

    public void j(boolean z) {
        this.t = z;
    }

    public int w() {
        return this.ag;
    }

    public void b(int i) {
        this.ag = i;
    }

    public int y() {
        return this.ah;
    }

    public void a(int i) {
        this.ah = i;
    }

    public float u() {
        return this.ae;
    }

    public void a(float f) {
        this.ae = f;
    }

    public boolean af() {
        return this.m;
    }

    public void a(boolean z) {
        this.m = z;
    }

    public int e() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public int h() {
        return this.g;
    }

    public void d(int i) {
        this.g = i;
    }

    public boolean ae() {
        return this.h;
    }

    public void e(boolean z) {
        this.h = z;
    }

    public String q() {
        return this.aa;
    }

    public void n(String str) {
        this.aa = str;
    }

    public boolean ai() {
        return this.b;
    }

    public void d(boolean z) {
        this.b = z;
    }

    public String toString() {
        return "CourseJumpParameter{mIsFromScheme=" + this.l + ", mWorkoutId='" + this.ak + "', mVersion='" + this.af + "', mEntranceType='" + this.j + "', mEntrance='" + this.d + "', mWorkoutPackageId='" + this.am + "', mResourceType='" + this.z + "', mPlanTempId='" + this.u + "', mPullFrom='" + this.w + "', mResourceID='" + this.ab + "', mResourceName='" + this.ac + "', mPullOrder='" + this.v + "', mAlgId='" + this.f15060a + "', mPromoteTag='" + this.x + "', mItemId='" + this.s + "', mPlanId='" + this.y + "', mAbInfo='" + this.c + "', mUserDefinedType=" + this.ai + ", mIsSendCourseDevice=" + this.o + ", mMoveTaskToBack=" + this.p + ", mPlanExecuteTime=" + this.q + ", mLongCoachUrl='" + this.r + "', mSubtitleUrl='" + this.ad + "', mWorkoutOrder=" + this.al + ", mWeekNumber=" + this.an + ", mIsPlanFit=" + this.n + ", mIsAfterRun=" + this.f + ", mIsNeedExecuteDown=" + this.k + ", mExecuteImmediateErrorType=" + this.i + ", mIsShowButton=" + this.t + ", mTrackType=" + this.ag + ", mTrackTarget=" + this.ah + ", mTrackTargetValue=" + this.ae + ", mIsImmediateStart=" + this.m + ", mEnterAnim=" + this.e + ", mExitAnim=" + this.g + ", mIsDeviceTraining=" + this.h + ", mTopicName='" + this.aa + "', hasNextTrain=" + this.b + '}';
    }
}
