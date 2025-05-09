package com.huawei.hianalytics.visual.autocollect.instrument;

import android.os.Bundle;
import android.view.View;
import com.huawei.hianalytics.visual.f0;
import com.huawei.hianalytics.visual.p;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes4.dex */
public class FragmentInstrumentation {

    /* renamed from: a, reason: collision with root package name */
    public static final Set<p> f3906a = new CopyOnWriteArraySet();

    public static void addHAFragmentCallbacks(p pVar) {
        f3906a.add(pVar);
    }

    public static void onHiddenChangedByFragment(Object obj, boolean z) {
        if (f0.e(obj)) {
            Iterator<p> it = f3906a.iterator();
            while (it.hasNext()) {
                it.next().b(obj, z);
            }
        }
    }

    public static void onPauseByFragment(Object obj) {
        if (f0.e(obj)) {
            Iterator<p> it = f3906a.iterator();
            while (it.hasNext()) {
                it.next().b(obj);
            }
        }
    }

    public static void onResumeByFragment(Object obj) {
        if (f0.e(obj)) {
            Iterator<p> it = f3906a.iterator();
            while (it.hasNext()) {
                it.next().a(obj);
            }
        }
    }

    public static void onViewCreatedByFragment(Object obj, View view, Bundle bundle) {
        if (f0.e(obj)) {
            Iterator<p> it = f3906a.iterator();
            while (it.hasNext()) {
                it.next().a(obj, view, bundle);
            }
        }
    }

    public static void removeHAFragmentCallbacks(p pVar) {
        f3906a.remove(pVar);
    }

    public static void setUserVisibleHintByFragment(Object obj, boolean z) {
        if (f0.e(obj)) {
            Iterator<p> it = f3906a.iterator();
            while (it.hasNext()) {
                it.next().a(obj, z);
            }
        }
    }
}
