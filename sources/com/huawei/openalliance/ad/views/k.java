package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class k extends j {
    protected ImageView f;
    protected ScanningView g;

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        ho.b(getViewTag(), "w=%s, h=%s, oldw=%s, oldh=%s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        this.f.post(new Runnable() { // from class: com.huawei.openalliance.ad.views.k.1
            @Override // java.lang.Runnable
            public void run() {
                ho.b(k.this.getViewTag(), "POST %s %s", Integer.valueOf(k.this.f.getHeight()), Integer.valueOf(k.this.f.getWidth()));
                if (k.this.g.getSrcBitmap() == null) {
                    ScanningView scanningView = k.this.g;
                    k kVar = k.this;
                    scanningView.setSrcBitmap(kVar.a(kVar.f));
                }
                if (k.this.g != null) {
                    k.this.g.a();
                }
            }
        });
    }

    protected String getViewTag() {
        return "PPSSplashSwipeClickView";
    }

    public void b() {
        ScanningView scanningView = this.g;
        if (scanningView != null) {
            scanningView.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap a(View view) {
        if (view == null) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (measuredWidth <= 0 || measuredHeight <= 0 || drawingCache == null) {
            ho.b(getViewTag(), "captureWidget NULL");
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache, 0, 0, measuredWidth, measuredHeight);
        view.destroyDrawingCache();
        return createBitmap;
    }

    public k(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public k(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public k(Context context) {
        super(context);
    }
}
