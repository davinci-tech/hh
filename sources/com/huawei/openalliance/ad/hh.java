package com.huawei.openalliance.ad;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class hh implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<com.huawei.openalliance.ad.activity.a> f6912a;
    private WeakReference<View> b;
    private int[] c;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        try {
            View view = this.b.get();
            com.huawei.openalliance.ad.activity.a aVar = this.f6912a.get();
            if (view != null && aVar != null && this.c != null) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                int i = iArr[0];
                if (i == 0 && iArr[1] == 0) {
                    ho.b("DialogOnGlobalLayoutListener", "anchorView onGlobalLayout newLoc[x,y] =0,0");
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    return;
                }
                int[] iArr2 = this.c;
                if (iArr2[0] == i && iArr2[1] == iArr[1]) {
                    return;
                }
                if (a(iArr2, iArr)) {
                    ho.b("DialogOnGlobalLayoutListener", "the anchorView's sliding distance is acceptable");
                    return;
                }
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ho.b("DialogOnGlobalLayoutListener", "anchorView location change newLoc[x,y] = " + iArr[0] + "," + iArr[1] + "--oldLoc[x,y] = " + this.c[0] + "," + this.c[1]);
                aVar.finish();
                return;
            }
            ho.b("DialogOnGlobalLayoutListener", "anchorView or activity or location is null.");
        } catch (Throwable th) {
            ho.c("DialogOnGlobalLayoutListener", "onGlobalLayout error:" + th.getClass().getSimpleName());
        }
    }

    private boolean a(int[] iArr, int[] iArr2) {
        int max = Math.max(com.huawei.openalliance.ad.utils.ao.h(this.f6912a.get()), com.huawei.openalliance.ad.utils.dd.g(this.f6912a.get()));
        return Math.abs(iArr[0] - iArr2[0]) <= max && Math.abs(iArr[1] - iArr2[1]) <= max;
    }

    public hh(com.huawei.openalliance.ad.activity.a aVar, View view, int[] iArr) {
        this.f6912a = new WeakReference<>(aVar);
        this.b = new WeakReference<>(view);
        this.c = iArr;
    }
}
