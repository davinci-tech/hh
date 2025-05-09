package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public class ghx {

    /* renamed from: a, reason: collision with root package name */
    private boolean f12811a;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private float j;
    private int k;
    private float m;
    private int n = 4;
    private int[] o;
    private static final int[] d = {40, -1};
    private static final int[] b = {10, 90, 180, 360, HwExerciseConstants.NINE_MINUTES_PACE, 720, 600};
    private static final int[] c = {10, 90, 180, 360, HwExerciseConstants.NINE_MINUTES_PACE, 720, 600};
    private static final int[] e = {10, 90, 180, 360, HwExerciseConstants.NINE_MINUTES_PACE, 720, 600};

    public ghx(float f, float f2, int[] iArr) {
        e(f, f2, iArr);
    }

    public static final int[] d() {
        return (int[]) d.clone();
    }

    public static final int[] e() {
        return (int[]) b.clone();
    }

    public static final int[] b() {
        return (int[]) c.clone();
    }

    public static final int[] c() {
        return (int[]) e.clone();
    }

    private void e(float f, float f2, int[] iArr) {
        this.j = f;
        this.m = f2;
        this.o = iArr;
        this.n = 4;
        this.k = 0;
        this.g = 0;
        this.h = 0;
        if (iArr.length > 0) {
            this.f = iArr[0];
        }
    }

    public void c(float f) {
        int d2 = d(f);
        LogUtil.b("Suggestion_StatusUtil", "onUpdate mLowerLimit:", Float.valueOf(this.j));
        LogUtil.b("Suggestion_StatusUtil", "onUpdate mUpperLimit:", Float.valueOf(this.m));
        LogUtil.b("Suggestion_StatusUtil", "onUpdate mTime:", Integer.valueOf(this.f));
        LogUtil.b("Suggestion_StatusUtil", "onUpdate mTimeIndex:", Integer.valueOf(this.h));
        int i = this.n;
        if (i == d2 && d2 != 4 && d2 != 3) {
            int i2 = this.k + 1;
            this.k = i2;
            if (i2 >= 30) {
                this.f12811a = true;
            }
        } else if (i == d2 && d2 == 3) {
            int i3 = this.g + 1;
            this.g = i3;
            if (i3 >= 30 && this.f12811a) {
                this.i = true;
                this.f12811a = false;
            }
        } else {
            e(this.j, this.m, this.o);
            if (d2 == 2 || d2 == 1) {
                this.k++;
            }
            if (d2 == 3) {
                this.g++;
            }
        }
        LogUtil.d("Suggestion_StatusUtil", "onUpdate current value:", Float.valueOf(f), " mTypeCount:", Integer.valueOf(this.k), " mNormalTypeCount:", Integer.valueOf(this.g), " old type:", Integer.valueOf(this.n), " new type:", Integer.valueOf(d2));
        this.n = d2;
    }

    private int d(float f) {
        float f2 = this.m;
        if (f > f2) {
            return 2;
        }
        float f3 = this.j;
        if (f < f3) {
            return 1;
        }
        return (f3 > f || f > f2) ? 4 : 3;
    }

    public int a() {
        if (this.n == 3) {
            return i();
        }
        return h();
    }

    private int h() {
        int i = this.k;
        int i2 = this.f;
        if (i != i2 || i2 == -1) {
            return 4;
        }
        int i3 = this.h + 1;
        this.h = i3;
        if (i3 >= this.o.length) {
            this.h = r1.length - 1;
        }
        LogUtil.d("Suggestion_StatusUtil", "getStatus mLowerLimit: ", Float.valueOf(this.j), "mUpperLimit: ", Float.valueOf(this.m), " status:", Integer.valueOf(this.n));
        this.f = this.o[this.h];
        return this.n;
    }

    private int i() {
        if (this.g == 180) {
            LogUtil.d("Suggestion_StatusUtil", "getStatus normal: ", Float.valueOf(this.j), "mUpperLimit: ", Float.valueOf(this.m), " status:", Integer.valueOf(this.n));
            return this.n;
        }
        if (!this.i) {
            return 4;
        }
        LogUtil.d("Suggestion_StatusUtil", "recover normal.status:", Integer.valueOf(this.n));
        this.i = false;
        return this.n;
    }
}
