package defpackage;

import com.huawei.dynamicanimation.DynamicAnimation;
import com.huawei.dynamicanimation.interpolator.PhysicalInterpolatorBase;

/* loaded from: classes3.dex */
public class bnh extends PhysicalInterpolatorBase<bnh> {
    public bnh(float f, float f2) {
        super(DynamicAnimation.AXIS_X, new bnd(f, f2));
        ((bnd) getModel()).setValueThreshold(getValueThreshold());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.dynamicanimation.interpolator.PhysicalInterpolatorBase
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bnh setValueThreshold(float f) {
        getModel().setValueThreshold(f * 0.75f);
        return this;
    }

    @Override // com.huawei.dynamicanimation.interpolator.PhysicalInterpolatorBase
    public float getDeltaX() {
        return getEndOffset();
    }

    public bni b(float f) {
        float duration = (f * getDuration()) / 1000.0f;
        return new bni(duration, getModel().getPosition(duration), getModel().getVelocity(duration), getModel().getAcceleration(duration));
    }
}
