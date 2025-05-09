package com.huawei.phoneservice.faq.base.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: classes5.dex */
public class FaqHwFrameworkUtil {

    public interface b {
        void a(int i);
    }

    class c implements View.OnApplyWindowInsetsListener {
        final /* synthetic */ Activity b;
        final /* synthetic */ b d;

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            try {
                Class<?> cls = Class.forName("com.huawei.android.view.WindowManagerEx$LayoutParamsEx");
                Class<?> cls2 = Class.forName("com.huawei.android.view.DisplaySideRegionEx");
                Object b = FaqRefectUtils.b(cls, this.b.getWindow().getAttributes());
                if (b != null) {
                    Object invoke = cls.getMethod("getDisplaySideRegion", WindowInsets.class).invoke(b, windowInsets);
                    if (invoke == null) {
                        this.d.a(1);
                    } else {
                        Rect rect = (Rect) cls2.getMethod("getSafeInsets", new Class[0]).invoke(invoke, new Object[0]);
                        if (rect.left > 0 && rect.right > 0) {
                            this.d.a(3);
                        } else if (rect.top > 0) {
                            this.d.a(2);
                        }
                    }
                }
            } catch (Throwable unused) {
                this.d.a(1);
                i.c("setForRing", "Exception");
            }
            return view.onApplyWindowInsets(windowInsets);
        }

        c(Activity activity, b bVar) {
            this.b = activity;
            this.d = bVar;
        }
    }

    public static void cdf_(Activity activity, int i) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            FaqRefectUtils.e("com.huawei.android.view.WindowManagerEx$LayoutParamsEx", "setDisplaySideMode", activity.getWindow().getAttributes(), (Class<?>[]) new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
            i.b("FaqHwFrameworkUtil", "setDisplaySideMode,time:%s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th) {
            i.b("FaqHwFrameworkUtil", th, "setDisplaySideMode error", new Object[0]);
        }
    }

    public static void cde_(Activity activity, b bVar) {
        activity.getWindow().getDecorView().setOnApplyWindowInsetsListener(new c(activity, bVar));
    }
}
