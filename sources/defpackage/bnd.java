package defpackage;

import android.util.Log;
import com.huawei.dynamicanimation.PhysicalModelBase;

/* loaded from: classes3.dex */
public class bnd extends PhysicalModelBase {

    /* renamed from: a, reason: collision with root package name */
    private float f442a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private boolean i;

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getAcceleration() {
        return 0.0f;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getAcceleration(float f) {
        return 0.0f;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public boolean isAtEquilibrium(float f) {
        return false;
    }

    public bnd(float f, float f2) {
        this(f, f2, 0.75f);
    }

    public bnd(float f, float f2, float f3) {
        this.d = 0.0f;
        this.i = true;
        super.setValueThreshold(f3);
        a(f);
        d(f2);
    }

    private void d() {
        if (this.i) {
            c();
            float log = ((float) (Math.log(this.mVelocityThreshold / this.c) / this.f442a)) * 1000.0f;
            this.e = log;
            float max = Math.max(log, 0.0f);
            this.e = max;
            this.b = getPosition(max / 1000.0f);
            this.i = false;
            Log.i("FlingModelBase", "reset: estimateTime=" + this.e + ",estimateValue=" + this.b);
        }
    }

    public void c() {
        if (bnn.e(this.c)) {
            throw new UnsupportedOperationException("InitVelocity should be set and can not be 0!!");
        }
        if (bnn.e(this.f442a)) {
            throw new UnsupportedOperationException("Friction should be set and can not be 0!!");
        }
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getPosition(float f) {
        this.d = f;
        float f2 = this.f;
        float f3 = this.c;
        float f4 = this.f442a;
        return f2 * ((float) ((f3 / f4) * (Math.exp(f4 * f) - 1.0d)));
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getPosition() {
        return getPosition(this.d);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getVelocity(float f) {
        return this.f * ((float) (this.c * Math.exp(this.f442a * f)));
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getVelocity() {
        return getVelocity(this.d);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public boolean isAtEquilibrium() {
        return this.c < this.mVelocityThreshold;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public boolean isAtEquilibrium(float f, float f2) {
        return Math.abs(f - getEndPosition()) < this.mValueThreshold && Math.abs(f2) < this.mVelocityThreshold;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getEstimatedDuration() {
        d();
        return this.e;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getEndPosition() {
        d();
        return this.b;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public final PhysicalModelBase setValueThreshold(float f) {
        super.setValueThreshold(f);
        this.i = true;
        return this;
    }

    public final <T extends PhysicalModelBase> T a(float f) {
        this.c = Math.abs(f);
        this.f = Math.signum(f);
        this.i = true;
        return this;
    }

    public final <T extends PhysicalModelBase> T d(float f) {
        this.f442a = f * (-4.2f);
        this.i = true;
        return this;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getMaxAbsX() {
        d();
        return this.b;
    }
}
