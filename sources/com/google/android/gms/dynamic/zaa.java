package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes2.dex */
final class zaa<T> implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        LinkedList linkedList;
        LinkedList linkedList2;
        LifecycleDelegate lifecycleDelegate2;
        this.zarj.zarf = lifecycleDelegate;
        linkedList = this.zarj.zarh;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            DeferredLifecycleHelper.zaa zaaVar = (DeferredLifecycleHelper.zaa) it.next();
            lifecycleDelegate2 = this.zarj.zarf;
            zaaVar.zaa(lifecycleDelegate2);
        }
        linkedList2 = this.zarj.zarh;
        linkedList2.clear();
        DeferredLifecycleHelper.zaa(this.zarj, (Bundle) null);
    }
}
