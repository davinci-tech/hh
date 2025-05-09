package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes4.dex */
public class gvn {
    private int f = 0;
    private int h = 0;
    private float[] b = new float[30];
    private float d = 0.0f;
    private long e = 0;
    private float c = 0.0f;

    /* renamed from: a, reason: collision with root package name */
    private Deque<a> f12959a = new LinkedList();

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private long f12960a;
        private int e;

        a(long j, int i) {
            this.f12960a = j;
            this.e = i;
        }
    }

    public void d(int i, long j) {
        Deque<a> deque = this.f12959a;
        if (deque == null) {
            LogUtil.h("Track_CalcPaceManager", "calcSpeedByDistanceChange mCalcPaceArray is null");
            return;
        }
        if (deque.size() < 2) {
            this.f12959a.add(new a(j, i));
        } else {
            this.f12959a.poll();
            this.f12959a.add(new a(j, i));
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.e = currentTimeMillis;
        LogUtil.a("Track_CalcPaceManager", "saveDataByDistanceChange mLastTimestamp = ", Long.valueOf(currentTimeMillis), ", distance = ", Integer.valueOf(i), " time = ", Long.valueOf(j));
        ReleaseLogUtil.e("Track_CalcPaceManager", "saveDataByDistanceChange last time stamp");
    }

    private float d() {
        float f;
        if (System.currentTimeMillis() - this.e >= 15000) {
            return 0.0f;
        }
        Deque<a> deque = this.f12959a;
        if (deque == null || deque.size() != 2) {
            f = 0.0f;
        } else {
            float f2 = (this.f12959a.getLast().f12960a - this.f12959a.getFirst().f12960a) / 1000.0f;
            f = f2 > 0.0f ? (this.f12959a.getLast().e - this.f12959a.getFirst().e) / f2 : 0.0f;
            LogUtil.c("Track_CalcPaceManager", "calcSpeedByDistanceChange speed = ", Float.valueOf(f));
        }
        return f > 0.0f ? 1000.0f / f : f;
    }

    public float e() {
        return this.d;
    }

    public void c() {
        LogUtil.c("Track_CalcPaceManager", "enter calcAverageSpeedBySlidingFrame() startIndex = ", Integer.valueOf(this.f), " endIndex = ", Integer.valueOf(this.h));
        float d = d();
        int i = this.h - this.f;
        int i2 = 0;
        if (i < 0 || a(d, 0.0f)) {
            LogUtil.h("Track_CalcPaceManager", "calcAverageSpeedBySlidingFrame slidingFrameLength < 0 || isEqualFloat(newAddSpeed, 0)");
            this.h = 0;
            this.f = 0;
            this.d = 0.0f;
            this.c = 0.0f;
            return;
        }
        if (i < 2) {
            this.b[i] = d;
            this.h = i + 1;
            return;
        }
        int i3 = this.h;
        if (i3 < 30) {
            this.b[i3] = d;
            this.h = i3 + 1;
        } else {
            d(d);
        }
        d(d, i);
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i4 = this.f; i4 < this.h; i4++) {
            d3 += this.b[i4] * gvo.d.get(i2).doubleValue();
            d2 += gvo.d.get(i2).doubleValue();
            i2++;
        }
        if (d2 > 0.0d) {
            float f = (float) (d3 / d2);
            this.d = f;
            LogUtil.a("Track_CalcPaceManager", "calcAverageSpeedBySlidingFrame mAverageSpeed(s/km) = ", Float.valueOf(f));
            float f2 = 3600.0f / this.d;
            this.d = f2;
            if (b(f2)) {
                this.d = this.c;
            }
            this.c = this.d;
        }
    }

    private void d(float f) {
        int i = 0;
        while (true) {
            float[] fArr = this.b;
            if (i < fArr.length - 1) {
                int i2 = i + 1;
                fArr[i] = fArr[i2];
                i = i2;
            } else {
                fArr[fArr.length - 1] = f;
                return;
            }
        }
    }

    private void d(float f, int i) {
        LogUtil.c("Track_CalcPaceManager", "adjustSlidingFrame() newAddSpeed = ", Float.valueOf(f), " slidingFrameLength = ", Integer.valueOf(i));
        float max = Math.max(Math.max((float) (Math.abs(f - a(r0 - 2)) * 1.0d), (float) (Math.abs(f - a(r0 - 3)) * 0.9d)), this.h - 1 >= 3 ? (float) (Math.abs(f - a(r0 - 4)) * 0.8333333333333334d) : 0.0f);
        List<Integer> e = e(f);
        int i2 = 0;
        while (true) {
            if (i2 >= gvo.f12961a.size()) {
                break;
            }
            if (i2 == gvo.f12961a.size() - 1) {
                this.f = this.h - 2;
                break;
            }
            if (max <= e.get(i2).intValue()) {
                int intValue = i + gvo.f12961a.get(i2).intValue();
                if (intValue < 2) {
                    this.f = this.h - 2;
                } else if (intValue <= 30) {
                    this.f = this.h - intValue;
                } else {
                    this.f = 0;
                }
            } else {
                i2++;
            }
        }
        LogUtil.c("Track_CalcPaceManager", "adjustSlidingFrame() startIndex = ", Integer.valueOf(this.f), " endIndex = ", Integer.valueOf(this.h));
    }

    private List<Integer> e(float f) {
        if (f > 600.0f) {
            return gvo.b;
        }
        if (f > 450.0f) {
            return gvo.e;
        }
        return gvo.c;
    }

    private boolean a(float f, float f2) {
        return ((double) Math.abs(f - f2)) <= 1.0E-6d;
    }

    private float a(int i) {
        float[] fArr = this.b;
        if (fArr == null) {
            return 0.0f;
        }
        if (i >= 0 && i < fArr.length) {
            return fArr[i];
        }
        LogUtil.h("Track_CalcPaceManager", "getSpecifiedElement Error index = ", Integer.valueOf(i));
        return 0.0f;
    }

    private boolean b(float f) {
        if (a(f, 0.0f)) {
            return false;
        }
        int n = gtx.c(BaseApplication.getContext()).n();
        if (n != 259) {
            float f2 = 3600.0f / f;
            if (n == 257) {
                if (f2 <= 180.0f) {
                    LogUtil.h("Track_CalcPaceManager", "convertPace <= CalcPaceConstants.MAX_WALK_PACE");
                    return true;
                }
            } else if (n != 258 && n != 264) {
                LogUtil.c("Track_CalcPaceManager", "isAbnormalPaceData sportType = ", Integer.valueOf(n));
            } else if (f2 <= 150.0f) {
                LogUtil.h("Track_CalcPaceManager", "convertPace <= CalcPaceConstants.MAX_RUN_PACE");
                return true;
            }
        } else if (f >= 55.0f) {
            LogUtil.h("Track_CalcPaceManager", "speed > CalcPaceConstants.MAX_RIDE_SPEED");
            return true;
        }
        return false;
    }
}
