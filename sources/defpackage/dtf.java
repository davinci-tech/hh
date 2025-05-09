package defpackage;

import androidx.core.util.Pair;
import com.huawei.health.heartratecontrol.algorithm.BaseHeartRateControlModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class dtf extends BaseHeartRateControlModel {

    /* renamed from: a, reason: collision with root package name */
    private float f11826a = -1.0f;
    private List<Integer> b = new ArrayList();
    private float c;
    private float d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private float j;
    private float l;
    private int o;

    @Override // com.huawei.health.heartratecontrol.algorithm.BaseHeartRateControlModel
    public void initParas() {
        dtk b = dtk.b();
        this.j = b.c();
        this.e = b.a();
        LogUtil.a("HRControl_TreadmillHeartRateControlModel", "mRunLevel = ", Float.valueOf(this.j), " mAge = ", Integer.valueOf(this.e));
    }

    @Override // com.huawei.health.heartratecontrol.algorithm.BaseHeartRateControlModel
    public void setCourseStageInfo(int i, ChoreographedSingleAction choreographedSingleAction) {
        super.setCourseStageInfo(i, choreographedSingleAction);
        a(i, choreographedSingleAction);
        b();
    }

    public void c(int i, int i2, float f) {
        if (i <= 0) {
            LogUtil.a("HRControl_TreadmillHeartRateControlModel", "time less than 0");
            return;
        }
        if (this.mIsCourseFinished) {
            LogUtil.a("HRControl_TreadmillHeartRateControlModel", "the course is finished");
            return;
        }
        if (this.mLockStatus != 0) {
            LogUtil.c("HRControl_TreadmillHeartRateControlModel", "mLockStatus is ", Integer.valueOf(this.mLockStatus));
            this.f11826a = f;
        } else if (e(f)) {
            this.mLockStatus = 1;
            sendHeartRateModelCallback("HEART_RATE_MODEL_STATUS", 1009);
        } else {
            d(i, i2, f);
        }
    }

    private void d(int i, int i2, float f) {
        this.mSportTime = i;
        LogUtil.c("HRControl_TreadmillHeartRateControlModel", "speedAdjustModel: time = ", Integer.valueOf(i), " heartRate = ", Integer.valueOf(i2), " speed = ", Float.valueOf(f));
        if (c(i2, f) && this.g == 1 && this.f == 0) {
            this.g = 2;
        }
        int i3 = this.g;
        if (i3 == 1) {
            LogUtil.c("HRControl_TreadmillHeartRateControlModel", "stage change status");
            int i4 = this.f;
            if (i4 > 0) {
                LogUtil.c("HRControl_TreadmillHeartRateControlModel", "the course is in stage buffer, mStageBufferTime = ", Integer.valueOf(i4));
                this.f--;
                if (this.mCourseStageNo == 0 && this.f == 0) {
                    LogUtil.c("HRControl_TreadmillHeartRateControlModel", "change to heart rate adjust directly in first stage");
                    this.g = 2;
                    return;
                }
                return;
            }
            dtg c = c(f, i2, this.j, this.e);
            this.f = c.d();
            d(f, c.c(), c.d());
            return;
        }
        if (i3 == 2) {
            LogUtil.c("HRControl_TreadmillHeartRateControlModel", "rate adjust status");
            int i5 = this.h;
            if (i5 > 0) {
                int i6 = i5 - 1;
                this.h = i6;
                LogUtil.c("HRControl_TreadmillHeartRateControlModel", "the course is in rate buffer, mRateAdjustBufferTime = ", Integer.valueOf(i6));
                return;
            } else {
                if (this.b.size() > 0) {
                    this.b.add(Integer.valueOf(i2));
                    LogUtil.c("HRControl_TreadmillHeartRateControlModel", "the course is in rate monitor, mHeartRateCheckList = ", Integer.valueOf(this.b.size()));
                    b(f);
                    return;
                }
                c(i2);
                return;
            }
        }
        LogUtil.c("HRControl_TreadmillHeartRateControlModel", "error status");
    }

    private boolean c(int i, float f) {
        return i > this.mTargetHeartRate ? f <= this.l : i >= this.mTargetHeartRate || f >= this.l;
    }

    private void a(int i, ChoreographedSingleAction choreographedSingleAction) {
        if (choreographedSingleAction == null) {
            LogUtil.h("HRControl_TreadmillHeartRateControlModel", "initStageData: action == null");
            return;
        }
        this.o = (int) ((choreographedSingleAction.getTargetConfig() == null || choreographedSingleAction.getTargetConfig().getValueType() != 1) ? 0.0d : choreographedSingleAction.getTargetConfig().getValueL());
        try {
            this.l = Float.parseFloat(choreographedSingleAction.getExtendProperty("target_speed"));
            this.c = Float.parseFloat(choreographedSingleAction.getExtendProperty("target_speed_min"));
            this.d = Float.parseFloat(choreographedSingleAction.getExtendProperty("target_speed_max"));
        } catch (NumberFormatException unused) {
            LogUtil.h("HRControl_TreadmillHeartRateControlModel", "NumberFormatException");
        }
        this.l = dth.d(this.l, 1);
        this.c = dth.d(this.c, 1);
        this.d = dth.d(this.d, 1);
        ReleaseLogUtil.e("HRControl_TreadmillHeartRateControlModel", "initStageData mTargetDuration = ", Integer.valueOf(this.o), " mTargetSpeed = ", Float.valueOf(this.l), " mMinTargetSpeed = ", Float.valueOf(this.c), " mMaxTargetSpeed = ", Float.valueOf(this.d));
    }

    private void b() {
        this.g = 1;
        this.f = 0;
        this.h = 0;
        List<Integer> list = this.b;
        if (list != null) {
            list.clear();
        }
        if (this.mLockStatus == 1) {
            this.mLockStatus = 0;
        }
    }

    private void c(int i) {
        if (!dth.e(i)) {
            LogUtil.c("HRControl_TreadmillHeartRateControlModel", "mIsWear = ", Boolean.valueOf(this.mIsWear));
            if (this.mIsWear) {
                checkWearStatus();
                return;
            }
            return;
        }
        this.mIsWear = true;
        if (i >= this.mCourseStageHeartRateMin && i <= this.mCourseStageHeartRateMax) {
            LogUtil.c("HRControl_TreadmillHeartRateControlModel", "in heart rate adjust, heartRate in scope");
            return;
        }
        this.i = this.mSpeedControlConfigInfo != null ? this.mSpeedControlConfigInfo.getCheckSustainTime() : 30;
        this.b.add(Integer.valueOf(i));
        LogUtil.c("HRControl_TreadmillHeartRateControlModel", "in heart rate adjust, start heart rate monitor");
    }

    private void b(float f) {
        List<Integer> list = this.b;
        if (list == null || list.size() < this.i) {
            return;
        }
        if (dth.e(this.b) >= (this.mSpeedControlConfigInfo != null ? this.mSpeedControlConfigInfo.getAbnormalHeartRateRatio() : 0.5f)) {
            sendHeartRateModelCallback("HEART_RATE_MODEL_STATUS", 1002);
            this.b.clear();
            return;
        }
        int c = (int) dth.c(this.b);
        if (c >= this.mCourseStageHeartRateMin && c <= this.mCourseStageHeartRateMax) {
            this.b.clear();
            return;
        }
        dtg e = e(f, dth.d(this.b), c, this.j, this.e);
        if (e == null) {
            LogUtil.h("HRControl_TreadmillHeartRateControlModel", "heartRateDecisionProcess: result == null");
            this.b.clear();
        } else {
            this.h = e.d();
            d(f, e.c(), e.d());
            this.b.clear();
        }
    }

    private dtg c(float f, int i, float f2, int i2) {
        float b;
        dtg dtgVar;
        ReleaseLogUtil.e("HRControl_TreadmillHeartRateControlModel", "stageChangeSpeedCalculator, input: speed = ", Float.valueOf(f), " heartRate = ", Integer.valueOf(i), " runLevel = ", Float.valueOf(f2), " age = ", Integer.valueOf(i2));
        float b2 = dte.b(dth.b(i), f2, i2);
        if (this.mCourseStageNo == 0) {
            dtgVar = dth.e(b2, this.l);
            b = dtgVar.c();
        } else {
            dtg c = dth.c(b2, this.l);
            b = dth.b(f, c.c(), b2, new Pair(Float.valueOf(this.c), Float.valueOf(this.d)), this.l);
            dtgVar = c;
        }
        dtgVar.d(dth.d(b, 1));
        dtgVar.d(dth.b(f, dtgVar.c()));
        ReleaseLogUtil.e("HRControl_TreadmillHeartRateControlModel", "mCourseStageNo = ", Integer.valueOf(this.mCourseStageNo), " stageChangeSpeedCalculator, output: getAdjustSpeed = ", Float.valueOf(dtgVar.c()), " getAdjustDuration = ", Integer.valueOf(dtgVar.d()));
        return dtgVar;
    }

    private dtg e(float f, int i, int i2, float f2, int i3) {
        dtg e;
        ReleaseLogUtil.e("HRControl_TreadmillHeartRateControlModel", "heartRateAdjustSpeedCalculator, input: speed = ", Float.valueOf(f), " heartRate = ", Integer.valueOf(i), " runLevel = ", Float.valueOf(f2), " age = ", Integer.valueOf(i3));
        int a2 = dth.a(i, i2);
        float b = dte.b(a2, f2, i3);
        int adjustMode = this.mSpeedControlConfigInfo != null ? this.mSpeedControlConfigInfo.getAdjustMode() : 0;
        LogUtil.c("HRControl_TreadmillHeartRateControlModel", "adjustMode is ", Integer.valueOf(adjustMode));
        if (adjustMode == 0) {
            e = dth.b(b, a2, new Pair(Integer.valueOf(this.mCourseStageHeartRateMin), Integer.valueOf(this.mCourseStageHeartRateMax)));
            e.d(dth.d(dth.e(f, e.c(), b, new Pair(Float.valueOf(this.c), Float.valueOf(this.d))), 1));
        } else {
            e = dth.e(f, i, new Pair(Integer.valueOf(this.mCourseStageHeartRateMin), Integer.valueOf(this.mCourseStageHeartRateMax)), this.mSpeedControlConfigInfo != null ? this.mSpeedControlConfigInfo.getAdjustSpeedMax() : 0.0f, this.mSpeedControlConfigInfo != null ? this.mSpeedControlConfigInfo.getHeartRateStableTime() : 0);
            e.d(dth.d(e.c(), 1));
        }
        e.d(dth.b(f, e.c()));
        ReleaseLogUtil.e("HRControl_TreadmillHeartRateControlModel", "heartRateAdjustSpeedCalculator, output: getAdjustSpeed = ", Float.valueOf(e.c()), " getAdjustDuration = ", Integer.valueOf(e.d()));
        return e;
    }

    private void d(float f, float f2, int i) {
        if (this.mCourseStageEndTime - this.mSportTime < i) {
            LogUtil.a("HRControl_TreadmillHeartRateControlModel", "remain duration less than bufferTime");
            return;
        }
        this.f11826a = f2;
        HashMap hashMap = new HashMap();
        hashMap.put("MODEL_SPEED_CHANGE", Float.valueOf(f2));
        sendHeartRateModelCallback("HEART_RATE_MODEL_RESULT", hashMap);
        if (Float.compare(f, f2) < 0) {
            sendHeartRateModelCallback("HEART_RATE_MODEL_STATUS", Integer.valueOf(this.g == 1 ? 1005 : 1007));
        }
        if (Float.compare(f, f2) > 0) {
            sendHeartRateModelCallback("HEART_RATE_MODEL_STATUS", Integer.valueOf(this.g == 1 ? 1006 : 1008));
        }
    }

    private boolean e(float f) {
        LogUtil.c("HRControl_TreadmillHeartRateControlModel", "judgeSpeedChangeByUser: mLastTimeSpeed = ", Float.valueOf(this.f11826a), " speed = ", Float.valueOf(f));
        if (Float.compare(this.f11826a, -1.0f) == 0) {
            this.f11826a = f;
            return false;
        }
        if (this.f <= 1 && this.h <= 1) {
            if (Float.compare(this.f11826a, f) != 0) {
                ReleaseLogUtil.e("HRControl_TreadmillHeartRateControlModel", "judgeSpeedChangeByUser: mLastTimeSpeed != speed");
                this.f11826a = f;
                return true;
            }
            this.f11826a = f;
        }
        return false;
    }
}
