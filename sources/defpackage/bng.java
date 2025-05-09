package defpackage;

import com.huawei.dynamicanimation.AnimationHandler;
import com.huawei.dynamicanimation.DynamicAnimation;
import com.huawei.dynamicanimation.FloatPropertyCompat;

/* loaded from: classes3.dex */
public class bng extends DynamicAnimation<bng> {

    /* renamed from: a, reason: collision with root package name */
    private bnf f443a;
    private float b;
    private float c;
    private float e;

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    public float getAcceleration(float f, float f2) {
        return 0.0f;
    }

    public <K> bng(K k, FloatPropertyCompat<K> floatPropertyCompat, bnf bnfVar) {
        super(k, floatPropertyCompat);
        this.b = 0.0f;
        this.c = 0.0f;
        this.e = Float.MAX_VALUE;
        this.f443a = bnfVar;
        if (floatPropertyCompat != null) {
            this.b = floatPropertyCompat.getValue(k);
        }
        this.f443a.setValueThreshold(getValueThreshold()).e(0.0f);
    }

    public bng e() {
        this.mTarget = null;
        this.mProperty = null;
        setStartVelocity(0.0f);
        this.c = 0.0f;
        this.b = 0.0f;
        this.f443a.b().e(0.0f).c(1.0f, 0.0f, -1L);
        AnimationHandler.e().e(this);
        return (bng) super.clearListeners();
    }

    public <K> bng e(K k, FloatPropertyCompat<K> floatPropertyCompat, float f, float f2, float f3, float f4) {
        super.setObj(k, floatPropertyCompat);
        setStartVelocity(f4);
        this.c = f3;
        a();
        this.f443a.b().b(f).a(f2).e(0.0f).c(f3 - this.b, f4, -1L);
        return this;
    }

    private void a() {
        if (this.mTarget != null && this.mProperty != null) {
            this.b = this.mProperty.getValue(this.mTarget);
            return;
        }
        if (this.mProperty == null) {
            final bnj bnjVar = new bnj(0.0f);
            this.mProperty = new FloatPropertyCompat("FloatValueHolder") { // from class: bng.3
                @Override // com.huawei.dynamicanimation.FloatPropertyCompat
                public float getValue(Object obj) {
                    return bnjVar.getValue();
                }

                @Override // com.huawei.dynamicanimation.FloatPropertyCompat
                public void setValue(Object obj, float f) {
                    bnjVar.setValue(f);
                }
            };
        } else {
            this.mProperty.setValue(this.mTarget, 0.0f);
        }
        this.b = 0.0f;
    }

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    public boolean updateValueAndVelocity(long j) {
        float f = this.e;
        if (f != Float.MAX_VALUE) {
            this.c = f;
            this.e = Float.MAX_VALUE;
            setStartVelocity(this.mVelocity);
            float value = this.mProperty.getValue(this.mTarget);
            this.b = value;
            this.f443a.b(this.c - value, this.mVelocity);
            DynamicAnimation.c c = this.f443a.c(j / 2);
            this.mValue = c.b + this.b;
            this.mVelocity = c.d;
            return false;
        }
        DynamicAnimation.c c2 = this.f443a.c(j);
        this.mValue = c2.b + this.b;
        this.mVelocity = c2.d;
        if (!isAtEquilibrium(this.mValue - this.b, this.mVelocity)) {
            return false;
        }
        this.mValue = this.f443a.getEndPosition() + this.b;
        this.mVelocity = 0.0f;
        return true;
    }

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    public void setValueThreshold(float f) {
        this.f443a.setValueThreshold(f);
    }

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    public void start() {
        super.start();
    }

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    public boolean isAtEquilibrium(float f, float f2) {
        return this.f443a.isAtEquilibrium(f, f2);
    }

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public bng setStartValue(float f) {
        super.setStartValue(f);
        this.b = f;
        float startVelocity = this.f443a.getStartVelocity();
        this.f443a.e(0.0f);
        this.f443a.c(this.c - this.b, startVelocity, -1L);
        return this;
    }

    public bnf b() {
        return this.f443a;
    }
}
