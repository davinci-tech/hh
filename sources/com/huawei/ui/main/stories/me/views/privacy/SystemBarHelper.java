package com.huawei.ui.main.stories.me.views.privacy;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class SystemBarHelper {

    interface OnDecorViewInstalledListener {
        void onDecorViewInstalled(View view);
    }

    public static void dPf_(Window window) {
        if (window == null) {
            LogUtil.h("SystemBarHelper", "hideSystemBars window is null");
            return;
        }
        dPe_(window, 5634);
        dPc_(window, 5634);
        window.setNavigationBarColor(0);
        window.setStatusBarColor(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dPd_(View view, int i) {
        view.setSystemUiVisibility(i | view.getSystemUiVisibility());
    }

    private static void dPe_(Window window, int i) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.systemUiVisibility = i | attributes.systemUiVisibility;
        window.setAttributes(attributes);
    }

    private static void dPc_(Window window, final int i) {
        dPg_(window, new OnDecorViewInstalledListener() { // from class: com.huawei.ui.main.stories.me.views.privacy.SystemBarHelper.5
            @Override // com.huawei.ui.main.stories.me.views.privacy.SystemBarHelper.OnDecorViewInstalledListener
            public void onDecorViewInstalled(View view) {
                SystemBarHelper.dPd_(view, i);
            }
        });
    }

    private static void dPg_(Window window, OnDecorViewInstalledListener onDecorViewInstalledListener) {
        new e().dPj_(window, onDecorViewInstalledListener, 3);
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private int f10384a;
        private final Handler b;
        private final Runnable c;
        private OnDecorViewInstalledListener d;
        private Window e;

        private e() {
            this.b = new Handler();
            this.c = new Runnable() { // from class: com.huawei.ui.main.stories.me.views.privacy.SystemBarHelper.e.3
                @Override // java.lang.Runnable
                public void run() {
                    View peekDecorView = e.this.e.peekDecorView();
                    if (peekDecorView != null) {
                        e.this.d.onDecorViewInstalled(peekDecorView);
                        return;
                    }
                    e.c(e.this);
                    if (e.this.f10384a >= 0) {
                        e.this.b.post(e.this.c);
                    } else {
                        LogUtil.h("SystemBarHelper", "DecorViewFinder cannot get decor view of mWindow: ", e.this.e);
                    }
                }
            };
        }

        static /* synthetic */ int c(e eVar) {
            int i = eVar.f10384a;
            eVar.f10384a = i - 1;
            return i;
        }

        public void dPj_(Window window, OnDecorViewInstalledListener onDecorViewInstalledListener, int i) {
            this.e = window;
            this.f10384a = i;
            this.d = onDecorViewInstalledListener;
            this.c.run();
        }
    }
}
