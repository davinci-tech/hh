package defpackage;

import com.huawei.dynamicanimation.DynamicAnimation;
import com.huawei.dynamicanimation.SpringModelBase;

/* loaded from: classes3.dex */
public class bnf extends SpringModelBase {
    private float b;
    private final DynamicAnimation.c e;

    public bnf(float f, float f2) {
        super(f, f2, c);
        this.b = 0.0f;
        this.e = new DynamicAnimation.c();
    }

    public bnf b() {
        this.b = 0.0f;
        this.e.b = 0.0f;
        this.e.d = 0.0f;
        return this;
    }

    public DynamicAnimation.c c(long j) {
        float f = this.b + j;
        this.b = f;
        float f2 = f / 1000.0f;
        this.e.b = getPosition(f2);
        this.e.d = getVelocity(f2);
        return this.e;
    }
}
