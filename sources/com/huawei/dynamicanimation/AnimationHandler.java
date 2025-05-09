package com.huawei.dynamicanimation;

import android.os.SystemClock;
import android.view.Choreographer;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class AnimationHandler {

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<AnimationHandler> f1961a = new ThreadLocal<>();
    private AnimationFrameCallbackProvider i;
    private final HashMap<AnimationFrameCallback, Long> c = new HashMap<>();
    private final ArrayList<AnimationFrameCallback> d = new ArrayList<>();
    private final a b = new a();
    private long e = 0;
    private boolean g = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface AnimationFrameCallback {
        boolean doAnimationFrame(long j);
    }

    AnimationHandler() {
    }

    class a {
        a() {
        }

        void e() {
            AnimationHandler.this.e = SystemClock.uptimeMillis();
            AnimationHandler animationHandler = AnimationHandler.this;
            animationHandler.c(animationHandler.e);
            if (AnimationHandler.this.d.size() > 0) {
                AnimationHandler.this.b().postFrameCallback();
            }
        }
    }

    public static AnimationHandler e() {
        ThreadLocal<AnimationHandler> threadLocal = f1961a;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler());
        }
        return threadLocal.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnimationFrameCallbackProvider b() {
        if (this.i == null) {
            this.i = new c(this.b);
        }
        return this.i;
    }

    public void c(AnimationFrameCallback animationFrameCallback, long j) {
        if (this.d.size() == 0) {
            b().postFrameCallback();
        }
        if (!this.d.contains(animationFrameCallback)) {
            this.d.add(animationFrameCallback);
        }
        if (j > 0) {
            this.c.put(animationFrameCallback, Long.valueOf(SystemClock.uptimeMillis() + j));
        }
    }

    public void e(AnimationFrameCallback animationFrameCallback) {
        this.c.remove(animationFrameCallback);
        int indexOf = this.d.indexOf(animationFrameCallback);
        if (indexOf >= 0) {
            this.d.set(indexOf, null);
            this.g = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j) {
        long uptimeMillis = SystemClock.uptimeMillis();
        for (int i = 0; i < this.d.size(); i++) {
            AnimationFrameCallback animationFrameCallback = this.d.get(i);
            if (animationFrameCallback != null && b(animationFrameCallback, uptimeMillis)) {
                animationFrameCallback.doAnimationFrame(j);
            }
        }
        a();
    }

    private boolean b(AnimationFrameCallback animationFrameCallback, long j) {
        if (this.c.get(animationFrameCallback) == null) {
            return true;
        }
        if (this.c.get(animationFrameCallback).longValue() >= j) {
            return false;
        }
        this.c.remove(animationFrameCallback);
        return true;
    }

    private void a() {
        if (this.g) {
            for (int size = this.d.size() - 1; size >= 0; size--) {
                if (this.d.get(size) == null) {
                    this.d.remove(size);
                }
            }
            this.g = false;
        }
    }

    static class c extends AnimationFrameCallbackProvider {
        private final Choreographer d;
        private final Choreographer.FrameCallback e;

        c(a aVar) {
            super(aVar);
            this.d = Choreographer.getInstance();
            this.e = new Choreographer.FrameCallback() { // from class: com.huawei.dynamicanimation.AnimationHandler.c.2
                @Override // android.view.Choreographer.FrameCallback
                public void doFrame(long j) {
                    c.this.mDispatcher.e();
                }
            };
        }

        @Override // com.huawei.dynamicanimation.AnimationHandler.AnimationFrameCallbackProvider
        void postFrameCallback() {
            this.d.postFrameCallback(this.e);
        }
    }

    static abstract class AnimationFrameCallbackProvider {
        final a mDispatcher;

        abstract void postFrameCallback();

        AnimationFrameCallbackProvider(a aVar) {
            this.mDispatcher = aVar;
        }
    }
}
