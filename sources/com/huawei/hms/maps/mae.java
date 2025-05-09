package com.huawei.hms.maps;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.huawei.hms.maps.utils.LogM;

/* loaded from: classes4.dex */
public class mae {
    public Drawable a(final View view) {
        return new Drawable() { // from class: com.huawei.hms.maps.mae.1
            private final Paint c;

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -3;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int i) {
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter colorFilter) {
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                int width = view.getWidth();
                int height = view.getHeight();
                LogM.d("MapBackground", "drawBackground: h = " + height + "; w = " + width);
                double d = (double) 96;
                float f = (float) (0.23d * d);
                float f2 = (float) (d * 0.89d);
                float f3 = (float) height;
                float f4 = 96;
                int i = (int) ((f3 - f) / f4);
                for (int i2 = 0; i2 <= i; i2++) {
                    float f5 = (i2 * 96) + f;
                    canvas.drawLine(0.0f, f5, width, f5, this.c);
                }
                int i3 = (int) ((width - f2) / f4);
                for (int i4 = 0; i4 <= i3; i4++) {
                    float f6 = (i4 * 96) + f2;
                    canvas.drawLine(f6, 0.0f, f6, f3, this.c);
                }
            }

            {
                Paint paint = new Paint();
                this.c = paint;
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.MITER);
                paint.setStrokeCap(Paint.Cap.BUTT);
                paint.setColor(-4342596);
                paint.setAntiAlias(true);
            }
        };
    }
}
