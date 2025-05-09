package com.huawei.health.suggestion.videoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class ResizeTextureView extends TextureView {

    /* renamed from: a, reason: collision with root package name */
    private int f3442a;
    private int e;

    public ResizeTextureView(Context context) {
        super(context);
        this.f3442a = 0;
        this.e = 0;
    }

    public ResizeTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3442a = 0;
        this.e = 0;
    }

    public void setVideoSize(int i, int i2) {
        if (this.f3442a == i && this.e == i2) {
            return;
        }
        this.f3442a = i;
        this.e = i2;
        requestLayout();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        if (f != getRotation()) {
            super.setRotation(f);
            requestLayout();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int rotation = (int) getRotation();
        int i8 = this.f3442a;
        int i9 = this.e;
        if (getParent() instanceof View) {
            View view = (View) getParent();
            i4 = view.getMeasuredHeight();
            i3 = view.getMeasuredWidth();
        } else {
            i3 = 0;
            i4 = 0;
        }
        if (b(i3, i4, i8, i9) && IngotsVideoPlayer.getVideoImageDisplayType() == 1) {
            if (rotation != 90 && rotation != 270) {
                int i10 = i4;
                i4 = i3;
                i3 = i10;
            }
            i5 = (i8 * i3) / i4;
            int i11 = i4;
            i4 = i3;
            i3 = i11;
        } else {
            i5 = i9;
        }
        if (rotation == 90 || rotation == 270) {
            i6 = i;
            i7 = i2;
        } else {
            i7 = i;
            i6 = i2;
        }
        int defaultSize = getDefaultSize(i8, i);
        int defaultSize2 = getDefaultSize(i5, i2);
        if (i8 > 0 && i5 > 0) {
            int[] c = c(i7, i6, i8, i5);
            defaultSize = c[0];
            defaultSize2 = c[1];
        }
        if (b(i3, i4, i8, i5) && IngotsVideoPlayer.getVideoImageDisplayType() == 3) {
            defaultSize = i8;
            defaultSize2 = i5;
        }
        if (b(i3, i4, i8, i5) && IngotsVideoPlayer.getVideoImageDisplayType() == 2) {
            int[] a2 = a(i3, i4, i8, i5, a(i8, i5, i, i2));
            defaultSize = a2[0];
            defaultSize2 = a2[1];
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    private int[] a(int i, int i2, int i3, int i4, int[] iArr) {
        int rotation = (int) getRotation();
        int i5 = iArr[0];
        int i6 = iArr[1];
        if (rotation != 90 && rotation != 270) {
            i2 = i;
            i = i2;
        }
        double d = i4 / i3;
        double d2 = i;
        double d3 = i2;
        double d4 = d2 / d3;
        if (d > d4) {
            i6 = (int) ((d3 / i5) * i6);
            i5 = i2;
        }
        if (d < d4) {
            i5 = (int) ((d2 / i6) * i5);
        } else {
            i = i6;
        }
        return e(i5, i);
    }

    private int[] c(int i, int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int defaultSize = getDefaultSize(i3, i);
        int defaultSize2 = getDefaultSize(i4, i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            int i5 = i3 * size2;
            if (i5 < size * i4) {
                size = i5 / i4;
            }
            int i6 = i4 * size;
            if (i5 > i6) {
                size2 = i6 / i3;
            }
            return e(size, size2);
        }
        if (mode == 1073741824) {
            int i7 = (size * i4) / i3;
            if (mode2 != Integer.MIN_VALUE || i7 <= size2) {
                size2 = i7;
            } else {
                size = (i3 * size2) / i4;
            }
            return e(size, size2);
        }
        if (mode2 == 1073741824) {
            int i8 = (size2 * i3) / i4;
            if (mode != Integer.MIN_VALUE || i8 <= size) {
                size = i8;
            } else {
                size2 = (i4 * size) / i3;
            }
            return e(size, size2);
        }
        return e(defaultSize, defaultSize2);
    }

    private int[] a(int i, int i2, int i3, int i4) {
        return e(getDefaultSize(i, i3), getDefaultSize(i2, i4));
    }

    private boolean b(int i, int i2, int i3, int i4) {
        if (i != 0 && i2 != 0 && i3 != 0 && i4 != 0) {
            return true;
        }
        LogUtil.h("Ingots_ResizeTextureView", "isCalculateDimens() width or height is 0");
        return false;
    }

    private int[] e(int i, int i2) {
        return new int[]{i, i2};
    }
}
