package com.huawei.openalliance.ad;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import com.huawei.openalliance.ad.views.linkscroll.LinkScrollView;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class hj implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<LinkScrollView> f6913a;
    private final WeakReference<View> b;
    private int c = 0;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        try {
            View view = this.b.get();
            LinkScrollView linkScrollView = this.f6913a.get();
            if (view != null && linkScrollView != null) {
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                int height = view.getHeight();
                int i = height - rect.bottom;
                ho.a("InputOnGlobalLayoutList", "keyboard height is %s, view height is %s.", Integer.valueOf(i), Integer.valueOf(height));
                if (i > height * 0.25f) {
                    linkScrollView.a(i - this.c, true);
                    return;
                }
                linkScrollView.a(i, false);
                if (i != 0) {
                    this.c = i;
                    return;
                }
                return;
            }
            ho.c("InputOnGlobalLayoutList", "view is null.");
        } catch (Throwable th) {
            ho.c("InputOnGlobalLayoutList", "listen input err: %s", th.getClass().getSimpleName());
        }
    }

    public hj(LinkScrollView linkScrollView, View view) {
        this.f6913a = new WeakReference<>(linkScrollView);
        this.b = new WeakReference<>(view);
    }
}
