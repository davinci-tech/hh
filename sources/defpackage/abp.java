package defpackage;

import com.huawei.animation.physical2.util.FollowHandRate;

/* loaded from: classes8.dex */
public class abp implements FollowHandRate {

    /* renamed from: a, reason: collision with root package name */
    private float f167a = 0.75f;
    private float b;
    private float d;

    public abp(float f, float f2) {
        this.d = f;
        this.b = f2;
    }

    @Override // com.huawei.animation.physical2.util.FollowHandRate
    public float getRate(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("input can not less than zero!!");
        }
        float f2 = this.d;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f3 = f / f2;
        if (Float.compare(f3, 1.0f) > 0) {
            f3 = 1.0f;
        }
        return (float) Math.exp(-(this.b * f3 * this.f167a));
    }

    public abp b(float f) {
        this.b = f;
        return this;
    }
}
