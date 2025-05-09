package com.huawei.animationkit.computationalwallpaper.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.animationkit.computationalwallpaper.generator.DynamicWallpaper;

/* loaded from: classes8.dex */
public class WallpaperView extends View {

    /* renamed from: a, reason: collision with root package name */
    private final Rect f1855a;
    private WallpaperDrawable b;
    private final Drawable.Callback c;

    public WallpaperView(Context context) {
        super(context);
        this.f1855a = new Rect();
        this.c = new Drawable.Callback() { // from class: com.huawei.animationkit.computationalwallpaper.ui.WallpaperView.2
            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                WallpaperView.this.invalidate();
            }
        };
    }

    public WallpaperView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1855a = new Rect();
        this.c = new Drawable.Callback() { // from class: com.huawei.animationkit.computationalwallpaper.ui.WallpaperView.2
            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                WallpaperView.this.invalidate();
            }
        };
    }

    public WallpaperView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1855a = new Rect();
        this.c = new Drawable.Callback() { // from class: com.huawei.animationkit.computationalwallpaper.ui.WallpaperView.2
            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable drawable) {
                WallpaperView.this.invalidate();
            }
        };
    }

    public void setDrawable(WallpaperDrawable wallpaperDrawable) {
        WallpaperDrawable wallpaperDrawable2 = this.b;
        if (wallpaperDrawable2 != null) {
            wallpaperDrawable2.setCallback(null);
            if (this.b.getWallpaper() instanceof DynamicWallpaper) {
                ((DynamicWallpaper) this.b.getWallpaper()).getPattern().releaseBitmap();
            }
        }
        this.b = wallpaperDrawable;
        wallpaperDrawable.setCallback(this.c);
        if (this.f1855a.width() == 0 || this.f1855a.height() == 0) {
            return;
        }
        this.b.setBounds(this.f1855a);
    }

    public WallpaperDrawable getDrawable() {
        return this.b;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f1855a.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        WallpaperDrawable wallpaperDrawable = this.b;
        if (wallpaperDrawable != null) {
            wallpaperDrawable.setBounds(this.f1855a);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        WallpaperDrawable wallpaperDrawable = this.b;
        if (wallpaperDrawable != null) {
            wallpaperDrawable.draw(canvas);
        }
    }
}
