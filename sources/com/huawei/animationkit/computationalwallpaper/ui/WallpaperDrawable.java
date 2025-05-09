package com.huawei.animationkit.computationalwallpaper.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.generator.Wallpaper;
import defpackage.abo;

/* loaded from: classes8.dex */
public abstract class WallpaperDrawable extends Drawable {
    private static final String TAG = "WallpaperDrawable";
    private Path mClipPath;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    public Wallpaper getWallpaper() {
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void startAnimation() {
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        if (abo.b() == 0.0f) {
            this.mClipPath = null;
            return;
        }
        if (abo.b() != -1.0f) {
            Path path = new Path();
            this.mClipPath = path;
            path.addCircle(getBounds().centerX(), getBounds().centerY(), abo.b(), Path.Direction.CW);
        } else if (getBounds().width() == getBounds().height()) {
            Path path2 = new Path();
            this.mClipPath = path2;
            path2.addCircle(getBounds().centerX(), getBounds().centerY(), getBounds().width() / 2.0f, Path.Direction.CW);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Path path = this.mClipPath;
        if (path != null) {
            canvas.clipPath(path);
        }
    }

    public void goFirstFrame() {
        Log.i(TAG, "goFirstFrame");
    }
}
