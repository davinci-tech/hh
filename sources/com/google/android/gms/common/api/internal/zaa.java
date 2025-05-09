package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0030zaa> zacl;

    public zaa(Activity activity) {
        this(C0030zaa.zaa(activity));
    }

    private zaa(C0030zaa c0030zaa) {
        this.zacl = new WeakReference<>(c0030zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0030zaa c0030zaa = this.zacl.get();
        if (c0030zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0030zaa.zaa(runnable);
        return this;
    }

    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    static class C0030zaa extends LifecycleCallback {
        private List<Runnable> zacm;

        /* JADX INFO: Access modifiers changed from: private */
        public static C0030zaa zaa(Activity activity) {
            C0030zaa c0030zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0030zaa = (C0030zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0030zaa.class);
                if (c0030zaa == null) {
                    c0030zaa = new C0030zaa(fragment);
                }
            }
            return c0030zaa;
        }

        private C0030zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacm = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zaa(Runnable runnable) {
            synchronized (this) {
                this.zacm.add(runnable);
            }
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacm;
                this.zacm = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }
}
