package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import com.huawei.openalliance.ad.constant.NotifyMessageNames;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class hg implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<Context> f6911a;
    private final WeakReference<View> b;
    private final int[] c;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        try {
            View view = this.b.get();
            Context context = this.f6911a.get();
            if (view != null && context != null && this.c != null) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                int i = iArr[0];
                if (i == 0 && iArr[1] == 0) {
                    ho.b("CustomOnGlobalLayoutListener", "anchorView onGlobalLayout newLoc[x,y] =0,0");
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    return;
                }
                int[] iArr2 = this.c;
                if ((iArr2[0] == i && iArr2[1] == iArr[1]) || a(iArr2, iArr)) {
                    return;
                }
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ho.b("CustomOnGlobalLayoutListener", "anchorView location change newLoc[x,y] = " + iArr[0] + "," + iArr[1] + "--oldLoc[x,y] = " + this.c[0] + "," + this.c[1]);
                ji.a().a(NotifyMessageNames.FEEDBACK_RECEIVE, new Intent("com.huawei.ads.feedback.action.ANCHOR_LOCATION_CHANGE"));
            }
        } catch (Throwable th) {
            ho.c("CustomOnGlobalLayoutListener", "onGlobalLayout error:" + th.getClass().getSimpleName());
        }
    }

    private boolean a(int[] iArr, int[] iArr2) {
        int max = Math.max(com.huawei.openalliance.ad.utils.ao.h(this.f6911a.get()), com.huawei.openalliance.ad.utils.dd.g(this.f6911a.get()));
        return Math.abs(iArr[0] - iArr2[0]) <= max && Math.abs(iArr[1] - iArr2[1]) <= max;
    }

    public hg(View view, Context context, int[] iArr) {
        this.f6911a = new WeakReference<>(context);
        this.b = new WeakReference<>(view);
        this.c = iArr == null ? null : Arrays.copyOf(iArr, iArr.length);
    }
}
