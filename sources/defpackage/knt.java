package defpackage;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.huawei.hwfabengine.FloatingActionButtonAnimationListener;

/* loaded from: classes9.dex */
class knt {
    /* JADX WARN: Multi-variable type inference failed */
    static void bPw_(Drawable drawable, FloatingActionButtonAnimationListener floatingActionButtonAnimationListener, FloatingActionButtonAnimationListener floatingActionButtonAnimationListener2) {
        try {
            if (drawable instanceof Animatable2) {
                Log.i("FloatingActionButtonAnimationUtils", "It starts to do animation.");
                Animatable2 animatable2 = (Animatable2) drawable;
                animatable2.start();
                bPv_(animatable2, floatingActionButtonAnimationListener, floatingActionButtonAnimationListener2);
            }
        } catch (NoClassDefFoundError unused) {
            Log.e("FloatingActionButtonAnimationUtils", "It can not find the class Animatable2.");
        }
    }

    private static void bPv_(final Animatable2 animatable2, final FloatingActionButtonAnimationListener floatingActionButtonAnimationListener, final FloatingActionButtonAnimationListener floatingActionButtonAnimationListener2) {
        if (floatingActionButtonAnimationListener != null) {
            try {
                animatable2.registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: knt.1
                    @Override // android.graphics.drawable.Animatable2.AnimationCallback
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        FloatingActionButtonAnimationListener.this.onAnimationEnd();
                        FloatingActionButtonAnimationListener floatingActionButtonAnimationListener3 = floatingActionButtonAnimationListener2;
                        if (floatingActionButtonAnimationListener3 != null) {
                            floatingActionButtonAnimationListener3.onAnimationEnd();
                        }
                        animatable2.unregisterAnimationCallback(this);
                    }
                });
            } catch (NoClassDefFoundError unused) {
                Log.e("FloatingActionButtonAnimationUtils", "It can not find the class Animatable2.");
            }
        }
    }
}
