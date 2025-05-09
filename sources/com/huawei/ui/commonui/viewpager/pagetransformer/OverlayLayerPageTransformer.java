package com.huawei.ui.commonui.viewpager.pagetransformer;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import health.compact.a.LogUtil;

/* loaded from: classes6.dex */
public class OverlayLayerPageTransformer implements ViewPager.PageTransformer {
    private final b c;

    public OverlayLayerPageTransformer(b bVar) {
        if (bVar == null) {
            LogUtil.a("OverlayLayerPageTransformer", "OverlayLayerPageTransformer builder is null");
            this.c = new b();
        } else {
            this.c = bVar;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        float f2;
        float f3;
        int compare = Float.compare(f, 0.0f);
        if (compare == -1 || compare == 0) {
            view.setClickable(true);
            view.setVisibility(0);
            view.setTranslationX(0.0f);
            float abs = 1.0f - (Math.abs(f) * 0.5f);
            view.setAlpha(abs);
            view.setScaleX(abs);
            view.setScaleY(abs);
            return;
        }
        view.setClickable(false);
        int a2 = this.c.a();
        int compare2 = Float.compare(f, a2 - 1);
        int width = view.getWidth();
        float e = this.c.e();
        if (compare2 == 1 && Float.compare(f, a2) == -1) {
            view.setVisibility(0);
            double d = e;
            float floor = (float) (Math.floor(f - 1.0f) * d);
            view.setTranslationX(((-width) * f) + floor + ((1.0f - (f % ((int) f))) * (((float) (Math.floor(f) * d)) - floor)));
            view.setAlpha((float) Math.max(Math.pow(this.c.b(), f * f * f), 0.10000000149011612d));
        } else if (compare2 == 0 || compare2 == -1) {
            view.setVisibility(0);
            view.setTranslationX(((-width) * f) + (e * f));
            view.setAlpha((float) Math.max(Math.pow(this.c.b(), f * f * f), 0.10000000149011612d));
        } else {
            view.setAlpha(0.0f);
        }
        if (width > 0) {
            float f4 = width;
            f2 = (f4 - (this.c.d() * f)) / f4;
        } else {
            f2 = 0.0f;
        }
        int height = view.getHeight();
        if (height > 0) {
            float f5 = height;
            f3 = (f5 - (this.c.c() * f)) / f5;
        } else {
            f3 = 0.0f;
        }
        if (f2 > 0.0f && f3 > 0.0f) {
            view.setScaleX(f2);
            view.setScaleY(f3);
            return;
        }
        if (f2 <= 0.0f) {
            if (f3 <= 0.0f) {
                LogUtil.a("OverlayLayerPageTransformer", "transformPage scaleX ", Float.valueOf(f2), " scaleY ", Float.valueOf(f3), " position ", Float.valueOf(f), " view ", view);
                return;
            }
            f2 = f3;
        }
        view.setScaleX(f2);
        view.setScaleY(f2);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private int f8988a;
        private int b;
        private float c;
        private int d;
        private int e;

        private b() {
            this.e = 3;
            this.b = 100;
            this.d = 100;
            this.f8988a = 100;
            this.c = 0.5f;
        }

        public b(int i) {
            this.b = 100;
            this.d = 100;
            this.f8988a = 100;
            this.c = 0.5f;
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public int e() {
            return this.b;
        }

        public b e(int i) {
            this.b = i;
            return this;
        }

        public int d() {
            return this.d;
        }

        public b b(int i) {
            this.d = i;
            return this;
        }

        public int c() {
            return this.f8988a;
        }

        public b c(int i) {
            this.f8988a = i;
            return this;
        }

        public float b() {
            return this.c;
        }

        public b e(float f) {
            this.c = f;
            return this;
        }
    }
}
