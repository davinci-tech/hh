package com.huawei.phoneservice.feedback.media.impl.wiget.pictureview;

import android.widget.ImageView;
import androidx.core.view.MotionEventCompat;

/* loaded from: classes9.dex */
class k {
    public static int a(int i) {
        return (i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
    }

    public static boolean a(ImageView imageView) {
        return imageView.getDrawable() != null;
    }

    public static boolean cev_(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        if (scaleType != ImageView.ScaleType.MATRIX) {
            return true;
        }
        throw new IllegalStateException("Matrix scale type is not supported");
    }

    public static void c(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        }
        if (f2 >= f3) {
            throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }
}
