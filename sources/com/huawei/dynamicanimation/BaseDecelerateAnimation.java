package com.huawei.dynamicanimation;

import com.huawei.dynamicanimation.PhysicalModelBase;

/* loaded from: classes8.dex */
public abstract class BaseDecelerateAnimation<T extends PhysicalModelBase> extends DynamicAnimation<BaseDecelerateAnimation<T>> {
    private static final int ONE_SECOND = 1000;
    private long mFrameTime;
    private float mLastValue;
    protected T mModel;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.dynamicanimation.DynamicAnimation
    protected float getAcceleration(float f, float f2) {
        return 0.0f;
    }

    abstract void sanityCheck();

    <K> BaseDecelerateAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat, T t) {
        super(k, floatPropertyCompat);
        this.mLastValue = 0.0f;
        this.mModel = t;
        t.setValueThreshold(getValueThreshold());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:6:0x004b  */
    @Override // com.huawei.dynamicanimation.DynamicAnimation
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean updateValueAndVelocity(long r4) {
        /*
            r3 = this;
            long r0 = r3.mFrameTime
            long r0 = r0 + r4
            r3.mFrameTime = r0
            T extends com.huawei.dynamicanimation.PhysicalModelBase r4 = r3.mModel
            float r5 = (float) r0
            r0 = 1148846080(0x447a0000, float:1000.0)
            float r5 = r5 / r0
            float r4 = r4.getPosition(r5)
            float r5 = r3.mValue
            float r1 = r3.mLastValue
            float r1 = r4 - r1
            float r5 = r5 + r1
            r3.mValue = r5
            r3.mLastValue = r4
            T extends com.huawei.dynamicanimation.PhysicalModelBase r4 = r3.mModel
            long r1 = r3.mFrameTime
            float r5 = (float) r1
            float r5 = r5 / r0
            float r4 = r4.getVelocity(r5)
            r3.mVelocity = r4
            float r4 = r3.mValue
            float r5 = r3.mMinValue
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 >= 0) goto L33
            float r4 = r3.mMinValue
            r3.mValue = r4
            goto L3f
        L33:
            float r4 = r3.mValue
            float r5 = r3.mMaxValue
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L41
            float r4 = r3.mMaxValue
            r3.mValue = r4
        L3f:
            r4 = 1
            goto L49
        L41:
            float r4 = r3.mValue
            float r5 = r3.mVelocity
            boolean r4 = r3.isAtEquilibrium(r4, r5)
        L49:
            if (r4 == 0) goto L4e
            r3.reset()
        L4e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.dynamicanimation.BaseDecelerateAnimation.updateValueAndVelocity(long):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.dynamicanimation.DynamicAnimation
    protected boolean isAtEquilibrium(float f, float f2) {
        return this.mModel.isAtEquilibrium(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.dynamicanimation.DynamicAnimation
    protected void setValueThreshold(float f) {
        this.mModel.setValueThreshold(f);
    }

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    public void cancel() {
        super.cancel();
        reset();
    }

    public void reset() {
        this.mFrameTime = 0L;
        this.mLastValue = 0.0f;
    }

    public T getmModel() {
        return this.mModel;
    }

    @Override // com.huawei.dynamicanimation.DynamicAnimation
    public void start() {
        if (this.mModel == null) {
            throw new UnsupportedOperationException("Incomplete Animation: Physical Model should be set!");
        }
        sanityCheck();
        this.mModel.setValueThreshold(getValueThreshold());
        super.start();
    }
}
