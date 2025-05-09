package defpackage;

import com.huawei.health.heartratecontrol.algorithm.BaseHeartRateControlModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes8.dex */
public class dti extends BaseHeartRateControlModel {
    private float g;
    private boolean l;
    private int q;
    private float t;
    private int o = 50;
    private int r = 3;
    private int n = 30;
    private float f = 0.5f;
    private int e = -1;
    private int d = -1;
    private int c = -1;
    private final List<Integer> m = new ArrayList();
    private final List<Integer> i = new ArrayList();
    private final List<Integer> j = new ArrayList();
    private final List<Integer> b = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private dtc f11828a = new dtc();
    private int k = 0;
    private AtomicBoolean h = new AtomicBoolean(false);
    private int s = 0;
    private int p = 0;

    @Override // com.huawei.health.heartratecontrol.algorithm.BaseHeartRateControlModel
    public void initParas() {
        int h = dtk.b().h();
        float g = dtk.b().g();
        this.g = dtb.a(h, g);
        this.t = dtb.b(h, g);
        LogUtil.a("HRControl_BikeHeartRateControlModel", "initParas vo2Max = ", Integer.valueOf(h), ", weight = ", Float.valueOf(g), ", mHeartRatePowerFactor = ", Float.valueOf(this.g), ", mRpmPowerFactor = ", Float.valueOf(this.t));
    }

    @Override // com.huawei.health.heartratecontrol.algorithm.BaseHeartRateControlModel
    public void setCourseConfigInfo(dsy dsyVar) {
        super.setCourseConfigInfo(dsyVar);
        LogUtil.a("HRControl_BikeHeartRateControlModel", "setCourseConfigInfo configInfo = ", dsyVar);
        if (dsyVar != null) {
            this.q = dsyVar.getMaxPower();
            this.o = dsyVar.getLowerLimitRpm();
            this.r = dsyVar.getMaxChangedNumber();
            this.n = dsyVar.getCheckSustainTime();
            this.f = dsyVar.getAbnormalHeartRateRatio();
        }
        LogUtil.a("HRControl_BikeHeartRateControlModel", "setCourseConfigInfo mMaxPower = ", Integer.valueOf(this.q), ", mLowerRpmLimit = ", Integer.valueOf(this.o), ", mMaxChangeNum = ", Integer.valueOf(this.r), ", mJudgeSustainTime = ", Integer.valueOf(this.n), ", mInvalidHeartRateRatioLimit = ", Float.valueOf(this.f));
    }

    @Override // com.huawei.health.heartratecontrol.algorithm.BaseHeartRateControlModel
    public void setCourseStageInfo(int i, ChoreographedSingleAction choreographedSingleAction) {
        super.setCourseStageInfo(i, choreographedSingleAction);
        if (choreographedSingleAction != null) {
            h();
        }
    }

    public void d(int i, int i2, int i3, int i4, int i5) {
        LogUtil.a("HRControl_BikeHeartRateControlModel", "onDeviceDataChanged time = ", Integer.valueOf(i), ", heartRate = ", Integer.valueOf(i2), ", power = ", Integer.valueOf(i3), ", rpm = ", Integer.valueOf(i4), ", resistance = ", Integer.valueOf(i5));
        if (this.mIsRestart) {
            ReleaseLogUtil.e("R_HRControl_BikeHeartRateControlModel", "onDeviceDataChanged restart not support control");
            return;
        }
        if (this.mSpeedControlConfigInfo != null) {
            LogUtil.a("HRControl_BikeHeartRateControlModel", "onDeviceDataChanged mIsCourseFinished = ", Boolean.valueOf(this.mIsCourseFinished));
            if (this.mIsCourseFinished) {
                return;
            }
            this.mSportTime = i;
            this.e = i2;
            this.d = i3;
            this.c = i4;
            d(i4);
            LogUtil.a("HRControl_BikeHeartRateControlModel", "onDeviceDataChanged mLockStatus = ", Integer.valueOf(this.mLockStatus));
            if (this.mLockStatus == 0 && !c()) {
                c(i5, i2);
                LogUtil.a("HRControl_BikeHeartRateControlModel", "onDeviceDataChanged mAdjustResult.getStableTime = ", Integer.valueOf(this.f11828a.c()));
                if (this.f11828a.c() > 0) {
                    this.f11828a.a(r11.c() - 1);
                    return;
                } else {
                    b(i2);
                    return;
                }
            }
            return;
        }
        ReleaseLogUtil.e("R_HRControl_BikeHeartRateControlModel", "onDeviceDataChanged CourseConfigInfo is null");
    }

    private void c(int i, int i2) {
        if (i2 == 0 || i2 == 255) {
            return;
        }
        if ((i2 < this.mCourseStageHeartRateMin || i2 > this.mCourseStageHeartRateMax) && this.h.get()) {
            if (this.k == 0) {
                this.k = i;
            }
            int i3 = this.s + 1;
            this.s = i3;
            if (i3 < 3) {
                return;
            }
            this.s = 0;
            if (this.k != i) {
                this.k = i;
                g();
                LogUtil.a("HRControl_BikeHeartRateControlModel", "upperPowerAccordResistance resistance has changed, no need to adjust ");
            } else {
                int i4 = this.p;
                if (i4 > 7) {
                    LogUtil.a("HRControl_BikeHeartRateControlModel", "upperPowerAccordResistance has reach limit ");
                } else {
                    this.p = i4 + 1;
                    e();
                }
            }
        }
    }

    private void g() {
        this.p = 0;
        this.h.set(false);
    }

    private void d(int i) {
        LogUtil.a("HRControl_BikeHeartRateControlModel", "lowerRmpRemindJudge rpm = ", Integer.valueOf(i), ", mLowerRpmLimit = ", Integer.valueOf(this.o), ", mIsLowerRpmOfLast = ", Boolean.valueOf(this.l), ", mLowerRpmList.size = ", Integer.valueOf(this.m.size()));
        if (koq.b(this.m)) {
            if (i >= this.o) {
                this.l = false;
                return;
            } else if (this.l) {
                return;
            }
        }
        this.m.add(Integer.valueOf(i));
        if (this.m.size() >= 20) {
            int d = d(this.m);
            LogUtil.a("HRControl_BikeHeartRateControlModel", "lowerRmpRemindJudge avgRpm = ", Integer.valueOf(d));
            if (d < this.o) {
                this.l = true;
                callbackAdjustStatus(1004);
            }
            this.m.clear();
        }
    }

    private void b(int i) {
        LogUtil.a("HRControl_BikeHeartRateControlModel", "heartRateJudge heartRate = ", Integer.valueOf(i), ", mHeartRateList.size = ", Integer.valueOf(this.i.size()));
        if (koq.b(this.i)) {
            if (i == 0 || i == 255) {
                LogUtil.a("HRControl_BikeHeartRateControlModel", "heartRateJudge mIsWear = ", Boolean.valueOf(this.mIsWear));
                if (this.mIsWear) {
                    checkWearStatus();
                    return;
                }
                return;
            }
            this.mIsWear = true;
            LogUtil.a("HRControl_BikeHeartRateControlModel", "heartRateJudge mCourseStageHeartRateMin = ", Integer.valueOf(this.mCourseStageHeartRateMin), ", mCourseStageHeartRateMax = ", Integer.valueOf(this.mCourseStageHeartRateMax));
            if (i >= this.mCourseStageHeartRateMin && i <= this.mCourseStageHeartRateMax) {
                return;
            }
        }
        if (i == 0 || i == 255) {
            this.i.add(Integer.valueOf(i));
            this.b.add(Integer.valueOf(i));
        } else {
            this.i.add(Integer.valueOf(i));
            this.j.add(Integer.valueOf(i));
        }
        LogUtil.a("HRControl_BikeHeartRateControlModel", "heartRateJudge mHeartRateList.size = ", Integer.valueOf(this.i.size()), ",mJudgeSustainTime = ", Integer.valueOf(this.n));
        if (this.i.size() >= this.n) {
            d();
        }
    }

    private void d() {
        LogUtil.a("HRControl_BikeHeartRateControlModel", "computeAndAdjust mCurrentRpm = ", Integer.valueOf(this.c), ", mLowerRpmLimit = ", Integer.valueOf(this.o), ", mCurrentPower = ", Integer.valueOf(this.d));
        int i = this.c;
        if (i < this.o && this.d >= dtb.b(this.t, i)) {
            this.f11828a.b(0);
            this.f11828a.a(30);
        } else {
            float floatValue = new BigDecimal(this.b.size()).divide(new BigDecimal(this.i.size()), 1, 4).floatValue();
            LogUtil.a("HRControl_BikeHeartRateControlModel", "computeAndAdjust invalidHeartRateRatio = ", Float.valueOf(floatValue), ", mInvalidHeartRateRatioLimit = ", Float.valueOf(this.f));
            if (Float.compare(floatValue, this.f) > 0) {
                callbackAdjustStatus(1002);
                b();
                return;
            }
            int d = d(this.j);
            LogUtil.a("HRControl_BikeHeartRateControlModel", "computeAndAdjust avgHeartRate = ", Integer.valueOf(d), ", mCourseStageHeartRateMin = ", Integer.valueOf(this.mCourseStageHeartRateMin), ", mCourseStageHeartRateMax = ", Integer.valueOf(this.mCourseStageHeartRateMax));
            if (d >= this.mCourseStageHeartRateMin && d <= this.mCourseStageHeartRateMax) {
                b();
                return;
            }
            List<Integer> list = this.j;
            int intValue = list.get(list.size() - 1).intValue();
            this.f11828a = dtb.b(this.f11828a, intValue, this.mTargetHeartRate, dtb.e(intValue, this.mCourseStageHeartRateMin, this.mCourseStageHeartRateMax), this.g);
        }
        a();
        b();
    }

    private void b() {
        this.i.clear();
        this.b.clear();
        this.j.clear();
    }

    private void h() {
        if (this.mIsRestart) {
            ReleaseLogUtil.e("R_HRControl_BikeHeartRateControlModel", "onCourseStageChanged restart not support control");
            return;
        }
        if (this.mSportTime < 0 || this.e < 0 || this.d < 0 || this.c < 0) {
            return;
        }
        LogUtil.a("HRControl_BikeHeartRateControlModel", "onCourseStageChanged mSpeedControlConfigInfo = ", this.mSpeedControlConfigInfo, ", mLockStatus = ", Integer.valueOf(this.mLockStatus), ", mCourseStageNo = ", Integer.valueOf(this.mCourseStageNo));
        if (this.mSpeedControlConfigInfo == null || this.mLockStatus == 2) {
            return;
        }
        b();
        if (this.mLockStatus == 1) {
            this.mLockStatus = 0;
        }
        if (this.mCourseStageNo == 0) {
            this.c = 60;
        }
        g();
        LogUtil.a("HRControl_BikeHeartRateControlModel", "onCourseStageChanged mCurrentRpm = ", Integer.valueOf(this.c), ", mLowerRpmLimit = ", Integer.valueOf(this.o), ", mCurrentPower = ", Integer.valueOf(this.d));
        int i = this.c;
        if (i < this.o && this.d >= dtb.b(this.t, i)) {
            dtc dtcVar = new dtc();
            this.f11828a = dtcVar;
            dtcVar.b(0);
            this.f11828a.a(30);
        } else {
            this.f11828a = dtb.d(new dtc(), this.e, this.mTargetHeartRate, dtb.e(this.e, this.mCourseStageHeartRateMin, this.mCourseStageHeartRateMax), this.g);
        }
        a();
    }

    private int d(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        return new BigDecimal(i).divide(new BigDecimal(list.size()), RoundingMode.HALF_UP).intValue();
    }

    private boolean c() {
        LogUtil.a("HRControl_BikeHeartRateControlModel", "isReachedLimit changedNum = ", Integer.valueOf(this.f11828a.b()), ", mMaxChangeNum = ", Integer.valueOf(this.r), ", changedPowerTotal = ", Integer.valueOf(this.f11828a.e()), ", CHANGE_POWER_LIMIT = ", 150);
        return this.f11828a.b() >= this.r || this.f11828a.e() > 150;
    }

    private void a() {
        LogUtil.a("HRControl_BikeHeartRateControlModel", "callbackAdjustResult mCourseStageEndTime = ", Integer.valueOf(this.mCourseStageEndTime), ", mSportTime = ", Integer.valueOf(this.mSportTime));
        if (this.mCourseStageEndTime - this.mSportTime < this.f11828a.c()) {
            return;
        }
        LogUtil.a("HRControl_BikeHeartRateControlModel", "callbackAdjustResult adjustOrientation = ", Integer.valueOf(this.f11828a.d()));
        if (this.f11828a.d() == 0) {
            return;
        }
        e();
    }

    private void e() {
        if (this.mCourseStageEndTime - this.mSportTime < this.f11828a.c()) {
            return;
        }
        int a2 = this.d + ((this.p + 1) * this.f11828a.a());
        LogUtil.a("HRControl_BikeHeartRateControlModel", "callbackAdjustResult resultPower = ", Integer.valueOf(a2), " curPower ", Integer.valueOf(this.d), " changeNum ", Integer.valueOf(this.p), "  changePower ", Integer.valueOf(this.f11828a.a()), ", mMaxPower = ", Integer.valueOf(this.q));
        if (a2 > this.q) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("RESULT_POWER", Integer.valueOf(a2));
        hashMap.put("RESULT_RPM", Integer.valueOf(this.c));
        if (this.mCallback != null) {
            this.mCallback.onDataUpdate("HEART_RATE_MODEL_RESULT", hashMap);
            this.h.set(true);
        }
    }
}
