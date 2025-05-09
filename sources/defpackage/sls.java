package defpackage;

import android.graphics.Canvas;
import android.util.Log;
import android.widget.ImageView;
import com.huawei.uikit.hwimageview.widget.HwParallaxStyle;

/* loaded from: classes9.dex */
public class sls implements HwParallaxStyle {

    /* renamed from: a, reason: collision with root package name */
    private int f17111a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean i = false;

    private boolean b(ImageView imageView) {
        if (!this.i) {
            edL_(imageView);
        }
        if (imageView.getScaleType() != ImageView.ScaleType.CENTER_CROP) {
            Log.e("HwVerticalOffsetStyle", "isSupportable: Unsupported ImageView Scale Type, parallax only support CENTER_CROP.");
        }
        if (this.c <= 0 || this.d <= 0) {
            Log.e("HwVerticalOffsetStyle", "isSupportable: Unsupported ImagView: ImageView's height and width should greater than 0.");
        }
        return this.c * this.b < this.d * this.f17111a;
    }

    private void edL_(ImageView imageView) {
        this.c = imageView.getDrawable().getIntrinsicWidth();
        this.d = imageView.getDrawable().getIntrinsicHeight();
        this.f17111a = (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
        this.b = (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
        this.i = true;
    }

    @Override // com.huawei.uikit.hwimageview.widget.HwParallaxStyle
    public void onAttachedToImageView(ImageView imageView) {
    }

    @Override // com.huawei.uikit.hwimageview.widget.HwParallaxStyle
    public void onDetachedFromImageView(ImageView imageView) {
    }

    @Override // com.huawei.uikit.hwimageview.widget.HwParallaxStyle
    public void transform(ImageView imageView, Canvas canvas, int[] iArr, int[] iArr2) {
        if (imageView == null || canvas == null || iArr == null || iArr2 == null) {
            Log.w("HwVerticalOffsetStyle", "transform: input params contains null");
            return;
        }
        if (iArr.length <= 1 || iArr2.length <= 1) {
            return;
        }
        int i = iArr[1];
        this.e = iArr2[1];
        if (b(imageView)) {
            if (i < 0) {
                i = 0;
            } else {
                int i2 = this.e - this.b;
                if (i > i2) {
                    i = i2;
                } else {
                    Log.w("HwVerticalOffsetStyle", "transform: do not handle");
                }
            }
            int i3 = this.c;
            float abs = Math.abs(((this.d * (i3 == 0 ? 1.0f : this.f17111a / i3)) - this.b) * 0.5f);
            int i4 = this.b - this.e;
            if (i4 != 0) {
                canvas.translate(0.0f, (abs * ((i * 2) - (r0 - r7))) / i4);
            }
        }
    }
}
