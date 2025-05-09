package com.huawei.openalliance.ad;

import android.view.View;
import android.view.ViewParent;
import com.huawei.openalliance.ad.views.PPSInterstitialView;
import com.huawei.openalliance.ad.views.PPSRewardView;
import com.huawei.openalliance.ad.views.PPSSplashView;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public abstract class tg {

    /* renamed from: a, reason: collision with root package name */
    private static Set<Class<?>> f7533a;

    protected static boolean a(ViewParent viewParent) {
        for (Class<?> cls : f7533a) {
            if (cls != null && cls.isInstance(viewParent)) {
                ho.a("BaseTrackerUtil", "parent instanceof %s", cls.getSimpleName());
                return true;
            }
        }
        return false;
    }

    protected static boolean a(View view) {
        if (view == null) {
            return false;
        }
        ViewParent parent = view.getParent();
        for (int i = 0; i < 5 && parent != null; i++) {
            if (a(parent)) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    static {
        HashSet hashSet = new HashSet();
        f7533a = hashSet;
        hashSet.add(PPSInterstitialView.class);
        f7533a.add(PPSRewardView.class);
        f7533a.add(com.huawei.openalliance.ad.views.ah.class);
        f7533a.add(PPSSplashView.class);
    }
}
