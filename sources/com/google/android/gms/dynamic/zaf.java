package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* loaded from: classes2.dex */
final class zaf implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    zaf(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 4;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.zarj.zarf;
        lifecycleDelegate2.onStart();
    }
}
