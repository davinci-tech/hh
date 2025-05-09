package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* loaded from: classes2.dex */
final class zag implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    zag(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 5;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.zarj.zarf;
        lifecycleDelegate2.onResume();
    }
}
