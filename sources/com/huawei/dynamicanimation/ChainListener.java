package com.huawei.dynamicanimation;

import com.huawei.dynamicanimation.DynamicAnimation;

/* loaded from: classes8.dex */
public abstract class ChainListener implements DynamicAnimation.OnAnimationUpdateListener {
    @Override // com.huawei.dynamicanimation.DynamicAnimation.OnAnimationUpdateListener
    public abstract void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2);
}
