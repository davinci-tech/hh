package com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public interface VectorAnimation {
    int getDuration();

    default long getStartDelay() {
        return 0L;
    }

    default List<b> getTranslateXKeyFrames() {
        return Collections.emptyList();
    }

    default List<b> getTranslateYKeyFrames() {
        return Collections.emptyList();
    }

    default List<b> getScaleKeyFrames() {
        return Collections.emptyList();
    }

    default List<b> getRotateKeyFrame() {
        return Collections.emptyList();
    }

    default Interpolator getInterpolator() {
        return new PathInterpolator(0.3f, 0.0f, 0.3f, 1.0f);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private final float f1854a;
        private final float e;

        public b(float f, float f2) {
            this.f1854a = f;
            this.e = f2;
        }

        public float a() {
            return this.f1854a;
        }

        public float c() {
            return this.e;
        }
    }
}
