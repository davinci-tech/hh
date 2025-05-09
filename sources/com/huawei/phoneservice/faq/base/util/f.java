package com.huawei.phoneservice.faq.base.util;

import android.os.Handler;
import android.view.View;

/* loaded from: classes9.dex */
public class f {

    class b implements Runnable {
        final /* synthetic */ View b;

        @Override // java.lang.Runnable
        public void run() {
            View view = this.b;
            if (view != null) {
                view.setEnabled(true);
            }
        }

        b(View view) {
            this.b = view;
        }
    }

    public static void cdi_(View view) {
        if (view != null) {
            view.setEnabled(false);
        }
        new Handler().postDelayed(new b(view), 1000L);
    }
}
