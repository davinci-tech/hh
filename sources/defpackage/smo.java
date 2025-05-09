package defpackage;

import android.view.animation.AnimationUtils;
import com.huawei.dynamicanimation.SpringModelBase;

/* loaded from: classes7.dex */
public class smo extends SpringModelBase {

    /* renamed from: a, reason: collision with root package name */
    private float f17123a;
    private float b;
    private long d;
    private float e;

    public smo(float f, float f2, float f3, float f4, float f5) {
        super(f, f2, 0.001f);
        this.b = f3;
        this.f17123a = f3;
        this.e = f5;
        setValueThreshold(1.0f);
        e(0.0f);
        c(f4 - this.b, f5, -1L);
        this.d = AnimationUtils.currentAnimationTimeMillis();
    }

    public boolean c() {
        float currentAnimationTimeMillis = (AnimationUtils.currentAnimationTimeMillis() - this.d) / 1000.0f;
        this.e = getVelocity(currentAnimationTimeMillis);
        float position = getPosition(currentAnimationTimeMillis);
        float f = this.b;
        float f2 = position + f;
        this.f17123a = f2;
        if (!isAtEquilibrium(f2 - f, this.e)) {
            return false;
        }
        this.f17123a = getEndPosition() + this.b;
        this.e = 0.0f;
        return true;
    }

    public float e() {
        return this.f17123a;
    }

    public void a(long j) {
        this.d -= j;
    }
}
