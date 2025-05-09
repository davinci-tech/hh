package defpackage;

import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class ffs implements Serializable, Cloneable {
    private static final long serialVersionUID = -1353400425254333543L;

    /* renamed from: a, reason: collision with root package name */
    private int f12492a;
    private int b;
    private int c;
    private LinkedHashMap<String, String> d;
    private float e;
    private float f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private float l;
    private long m;
    private int n;
    private int o;
    private float p;
    private int q;
    private float s;

    public ffs() {
        this.h = -1;
        this.i = -1;
        this.o = -1;
        this.f12492a = -101;
        this.c = -1;
        this.q = -1;
        this.j = -1;
        this.g = -1;
        this.n = -1;
        this.b = -1;
        this.e = -1.0f;
        this.l = -1.0f;
        this.s = -1.0f;
        this.f = -1.0f;
        this.p = -1.0f;
        this.k = -1;
        this.d = new LinkedHashMap<>();
    }

    public ffs(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.h = -1;
        this.i = -1;
        this.o = -1;
        this.f12492a = -101;
        this.c = -1;
        this.q = -1;
        this.j = -1;
        this.g = -1;
        this.n = -1;
        this.b = -1;
        this.e = -1.0f;
        this.l = -1.0f;
        this.s = -1.0f;
        this.f = -1.0f;
        this.p = -1.0f;
        this.k = -1;
        this.d = new LinkedHashMap<>();
        this.m = j;
        this.h = i;
        this.i = i2;
        this.o = i3;
        this.f12492a = i4;
        this.c = i5;
        this.q = i6;
        this.j = i7;
    }

    public long g() {
        return this.m;
    }

    public void e(long j) {
        this.m = j;
    }

    public int b() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public int e() {
        return this.i;
    }

    public void d(int i) {
        this.i = i;
    }

    public int i() {
        return this.o;
    }

    public void h(int i) {
        this.o = i;
    }

    public int a() {
        return this.f12492a;
    }

    public void c(int i) {
        this.f12492a = i;
    }

    public int d() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int h() {
        return this.q;
    }

    public void f(int i) {
        this.q = i;
    }

    public int c() {
        return this.j;
    }

    public void e(int i) {
        this.j = i;
    }

    public int l() {
        return this.g;
    }

    public void g(int i) {
        this.g = i;
    }

    public int o() {
        return this.n;
    }

    public void i(int i) {
        this.n = i;
    }

    public float f() {
        return this.e;
    }

    public void d(float f) {
        this.e = f;
    }

    public float m() {
        return this.l;
    }

    public void a(float f) {
        this.l = f;
    }

    public float t() {
        return this.s;
    }

    public void e(float f) {
        this.s = f;
    }

    public float n() {
        return this.f;
    }

    public void b(float f) {
        this.f = f;
    }

    public float r() {
        return this.p;
    }

    public void c(float f) {
        this.p = f;
    }

    public void j(int i) {
        this.k = i;
    }

    public int k() {
        return this.k;
    }

    public LinkedHashMap<String, String> j() {
        return this.d;
    }

    public void e(LinkedHashMap<String, String> linkedHashMap) {
        this.d = linkedHashMap;
    }

    public String toString() {
        return "RunningPosture { mTime = " + this.m + ", mGroundContactTime = " + this.h + ", mGroundImpactAcceleration = " + this.i + ", mSwingAngle = " + this.o + ", mEversionExcursion = " + this.f12492a + ", mForeFootStrikePattern = " + this.c + ", mWholeFootStrikePattern = " + this.q + ", mHindFootStrikePattern = " + this.j + ", mHangTime = " + this.g + ", mImpactHangRate = " + this.n + ", mCircleCadence = " + this.b + ", mStepLength = " + this.k + ", mAverageVerticalImpactRate = " + this.e + ", mImpactPeak = " + this.l + " mVerticalOscillation = " + this.s + ", mGcTimeBalance = " + this.f + ", mVerticalRatio = " + this.p + ", mExtraDataMap = " + this.d + "}";
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean s() {
        return ((float) this.h) <= -1.0f && ((float) this.i) <= -1.0f && ((float) this.o) <= -1.0f && this.f12492a <= -101 && ((float) this.c) <= -1.0f && ((float) this.q) <= -1.0f && ((float) this.j) <= -1.0f && ((float) this.g) <= -1.0f && ((float) this.n) <= -1.0f && Float.compare(this.e, -1.0f) <= 0 && Float.compare(this.l, -1.0f) <= 0 && Float.compare(this.s, -1.0f) <= 0 && Float.compare(this.f, -1.0f) <= 0 && Float.compare(this.p, -1.0f) <= 0 && ((float) this.k) <= -1.0f;
    }

    public static boolean a(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            return true;
        }
        int requestAvgGroundContactTime = motionPathSimplify.requestAvgGroundContactTime();
        int requestAverageHangTime = motionPathSimplify.requestAverageHangTime();
        float requestGroundHangTimeRate = motionPathSimplify.requestGroundHangTimeRate();
        int requestAvgGroundImpactAcceleration = motionPathSimplify.requestAvgGroundImpactAcceleration();
        int requestAvgEversionExcursion = motionPathSimplify.requestAvgEversionExcursion();
        int requestAvgSwingAngle = motionPathSimplify.requestAvgSwingAngle();
        float a2 = (float) UnitUtil.a(motionPathSimplify.getExtendDataFloat("avg_gc_tb"), 1);
        float extendDataFloat = motionPathSimplify.getExtendDataFloat("avg_v_osc");
        float extendDataFloat2 = motionPathSimplify.getExtendDataFloat("avg_v_s_r");
        int extendDataInt = motionPathSimplify.getExtendDataInt("avg_i_p");
        float extendDataFloat3 = motionPathSimplify.getExtendDataFloat("avg_v_i_r");
        LogUtil.a("Track_RunningPosture", "isAllDataZero contactTime = ", Integer.valueOf(requestAvgGroundContactTime), " impactAcceleration = ", Integer.valueOf(requestAvgGroundImpactAcceleration), " avgEversionExcursion = ", Integer.valueOf(requestAvgEversionExcursion), " avgSwingAngle = ", Integer.valueOf(requestAvgSwingAngle), " hangTime = ", Integer.valueOf(requestAverageHangTime), " groundHangTimeRate = ", Float.valueOf(requestGroundHangTimeRate), " avgTimeBalance = ", Float.valueOf(a2), " avgVerticalOscillation = ", Float.valueOf(extendDataFloat), " avgVerticalStrideRatio = ", Float.valueOf(extendDataFloat2), " avgImpactPeak = ", Integer.valueOf(extendDataInt), " averageVerticalImpactRate = ", Float.valueOf(extendDataFloat3));
        return requestAvgGroundContactTime <= 0 && requestAvgGroundImpactAcceleration <= 0 && requestAvgEversionExcursion <= 0 && requestAvgSwingAngle <= 0 && requestAverageHangTime <= 0 && Float.compare(requestGroundHangTimeRate, 0.0f) <= 0 && Float.compare(a2, 0.0f) <= 0 && Float.compare(extendDataFloat, 0.0f) <= 0 && Float.compare(extendDataFloat2, 0.0f) <= 0 && extendDataInt <= 0 && Float.compare(extendDataFloat3, 0.0f) <= 0;
    }

    public void c(int i, double d) {
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GROUND_CONTACT_TIME.value()) {
            a((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_SWING_ANGLE.value()) {
            h((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_EVERSION.value()) {
            c((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FORE_STEPS.value()) {
            b((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WHOLE_STEPS.value()) {
            f((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HIND_STEPS.value()) {
            e((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_HANG_TIME.value()) {
            g((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_GROUND_IMPACT_ACCELERATION.value()) {
            d((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FLIGHT_RATIO.value()) {
            i((int) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_LOADING_RATE.value()) {
            d((float) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_ACTIVE_PEAK.value()) {
            a((float) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_VERTICAL_RATIO.value()) {
            c((float) d);
            return;
        }
        if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_STRIDE.value()) {
            j((int) d);
        } else if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_OSCILLATION.value()) {
            e((float) d);
        } else if (i == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GC_TIME_BALANCE.value()) {
            b((float) d);
        }
    }
}
