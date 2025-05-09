package defpackage;

import android.graphics.drawable.Drawable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class eka {
    private final List<GifDrawable> c = new ArrayList();

    public void d(GifDrawable gifDrawable) {
        this.c.add(gifDrawable);
        gifDrawable.setLoopCount(6);
        gifDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() { // from class: eka.5
            @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                LogUtil.d("GifManager", "onAnimationStart for drawable = ", drawable);
            }

            @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                LogUtil.d("GifManager", "onAnimationEnd for drawable = ", drawable);
            }
        });
    }

    public void c() {
        for (GifDrawable gifDrawable : this.c) {
            if (gifDrawable != null) {
                try {
                    gifDrawable.start();
                } catch (NullPointerException e) {
                    LogUtil.e("GifManager", "triggerStart gif failed, exception = ", ExceptionUtils.d(e));
                }
            }
        }
    }

    public void a() {
        for (GifDrawable gifDrawable : this.c) {
            if (gifDrawable != null) {
                gifDrawable.stop();
            }
        }
    }

    private void a(int i) {
        for (GifDrawable gifDrawable : this.c) {
            if (gifDrawable != null) {
                gifDrawable.setLoopCount(i);
            }
        }
    }

    public void b() {
        a(2);
    }

    public void e() {
        a(6);
        c();
    }

    public void d() {
        a(50);
        c();
    }
}
