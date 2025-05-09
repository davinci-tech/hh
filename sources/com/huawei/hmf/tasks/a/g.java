package com.huawei.hmf.tasks.a;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hmf.tasks.ExecuteResult;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes9.dex */
public class g extends Fragment {
    public static final WeakHashMap<Activity, WeakReference<g>> b = new WeakHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    public final List<WeakReference<ExecuteResult<?>>> f4228a = new ArrayList();

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.app.Activity r6, com.huawei.hmf.tasks.ExecuteResult r7) {
        /*
            java.lang.String r0 = "LifecycleCallbackFrg"
            java.lang.String r1 = "com.huawei.hmf.tasks.lifecycle_fragment_tag"
            java.util.WeakHashMap<android.app.Activity, java.lang.ref.WeakReference<com.huawei.hmf.tasks.a.g>> r2 = com.huawei.hmf.tasks.a.g.b
            java.lang.Object r2 = r2.get(r6)
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2
            if (r2 == 0) goto L1b
            java.lang.Object r3 = r2.get()
            if (r3 == 0) goto L1b
            java.lang.Object r6 = r2.get()
            com.huawei.hmf.tasks.a.g r6 = (com.huawei.hmf.tasks.a.g) r6
            goto L7b
        L1b:
            android.app.FragmentManager r2 = r6.getFragmentManager()
            r3 = 0
            android.app.Fragment r4 = r2.findFragmentByTag(r1)     // Catch: java.lang.ClassCastException -> L63
            com.huawei.hmf.tasks.a.g r4 = (com.huawei.hmf.tasks.a.g) r4     // Catch: java.lang.ClassCastException -> L63
            if (r4 != 0) goto L56
            com.huawei.hmf.tasks.a.g r5 = new com.huawei.hmf.tasks.a.g     // Catch: java.lang.Exception -> L3d
            r5.<init>()     // Catch: java.lang.Exception -> L3d
            android.app.FragmentTransaction r2 = r2.beginTransaction()     // Catch: java.lang.Exception -> L3a
            android.app.FragmentTransaction r1 = r2.add(r5, r1)     // Catch: java.lang.Exception -> L3a
            r1.commitAllowingStateLoss()     // Catch: java.lang.Exception -> L3a
            r3 = r5
            goto L57
        L3a:
            r1 = move-exception
            r3 = r5
            goto L3e
        L3d:
            r1 = move-exception
        L3e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.ClassCastException -> L54
            java.lang.String r5 = "create fragment failed."
            r2.<init>(r5)     // Catch: java.lang.ClassCastException -> L54
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.ClassCastException -> L54
            r2.append(r1)     // Catch: java.lang.ClassCastException -> L54
            java.lang.String r1 = r2.toString()     // Catch: java.lang.ClassCastException -> L54
            android.util.Log.e(r0, r1)     // Catch: java.lang.ClassCastException -> L54
            goto L57
        L54:
            r6 = move-exception
            goto L65
        L56:
            r3 = r4
        L57:
            java.util.WeakHashMap<android.app.Activity, java.lang.ref.WeakReference<com.huawei.hmf.tasks.a.g>> r1 = com.huawei.hmf.tasks.a.g.b     // Catch: java.lang.ClassCastException -> L63
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference     // Catch: java.lang.ClassCastException -> L63
            r2.<init>(r3)     // Catch: java.lang.ClassCastException -> L63
            r1.put(r6, r2)     // Catch: java.lang.ClassCastException -> L63
            r6 = r3
            goto L7b
        L63:
            r6 = move-exception
            r4 = r3
        L65:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "found LifecycleCallbackFragment but the type do not match. "
            r1.<init>(r2)
            java.lang.String r6 = r6.getMessage()
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            android.util.Log.e(r0, r6)
            r6 = r4
        L7b:
            if (r6 == 0) goto L80
            r6.a(r7)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hmf.tasks.a.g.a(android.app.Activity, com.huawei.hmf.tasks.ExecuteResult):void");
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        synchronized (this.f4228a) {
            Iterator<WeakReference<ExecuteResult<?>>> it = this.f4228a.iterator();
            while (it.hasNext()) {
                ExecuteResult<?> executeResult = it.next().get();
                if (executeResult != null) {
                    executeResult.cancel();
                }
            }
            this.f4228a.clear();
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }

    public final void a(ExecuteResult executeResult) {
        synchronized (this.f4228a) {
            this.f4228a.add(new WeakReference<>(executeResult));
        }
    }
}
