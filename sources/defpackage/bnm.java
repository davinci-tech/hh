package defpackage;

import android.util.Log;
import com.huawei.dynamicanimation.util.FollowHandRate;

/* loaded from: classes3.dex */
public class bnm implements FollowHandRate {

    /* renamed from: a, reason: collision with root package name */
    private float f446a;
    private float b;
    private float e;

    public bnm(float f, float f2) {
        this.f446a = 0.75f;
        this.e = f;
        this.b = f2;
    }

    public bnm(float f) {
        this(f, 1.848f);
    }

    @Override // com.huawei.dynamicanimation.util.FollowHandRate
    public float getRate(float f) {
        if (Float.compare(f, 0.0f) < 0) {
            throw new IllegalArgumentException("input can not less than zero!!");
        }
        float f2 = this.e;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f3 = f / f2;
        if (Float.compare(f3, 1.0f) > 0) {
            f3 = 1.0f;
        }
        float f4 = f3 * this.f446a;
        float exp = (float) Math.exp(-(this.b * f4));
        Log.d("DynamicCurveRate", "getRate: x=" + f4 + ",rate=" + exp + ",input=" + f);
        return exp;
    }
}
