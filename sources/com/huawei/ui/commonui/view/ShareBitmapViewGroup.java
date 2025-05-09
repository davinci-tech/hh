package com.huawei.ui.commonui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.uikit.phone.hwcolumnlayout.widget.HwColumnLinearLayout;
import java.util.List;

/* loaded from: classes6.dex */
public class ShareBitmapViewGroup extends HwColumnLinearLayout {
    public ShareBitmapViewGroup(Context context) {
        super(context);
    }

    public ShareBitmapViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ShareBitmapViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a(List<Bitmap> list) {
        HealthCardView healthCardView = new HealthCardView(getContext());
        healthCardView.setRadius(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362360_res_0x7f0a0238));
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        int i = 0;
        for (Bitmap bitmap : list) {
            if (bitmap == null) {
                return;
            }
            ImageView imageView = new ImageView(getContext());
            int width = bitmap.getWidth();
            int height = (int) (bitmap.getHeight() * ((r8 - (BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) * 2)) / width));
            i += height;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(getWidth() - (BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) * 2), height));
            imageView.setImageBitmap(bitmap);
            imageView.requestLayout();
            linearLayout.addView(imageView, -1);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, i);
        layoutParams.gravity = 1;
        healthCardView.setLayoutParams(layoutParams);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, i));
        linearLayout.invalidate();
        healthCardView.addView(linearLayout);
        healthCardView.invalidate();
        addView(healthCardView, -1);
        invalidate();
    }

    public void cNa_(Bitmap bitmap) {
        cMZ_(-1, bitmap);
    }

    public void cMZ_(int i, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        ImageView imageView = new ImageView(getContext());
        int width = bitmap.getWidth();
        int width2 = getWidth();
        int height = bitmap.getHeight();
        if (width > width2) {
            height = (int) (bitmap.getHeight() * (width2 / width));
            width = width2;
        }
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        imageView.setImageBitmap(bitmap);
        imageView.requestLayout();
        addView(imageView, i);
        invalidate();
    }

    public Bitmap getHoleViewBitmap() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            i += getChildAt(i2).getHeight();
        }
        if (i == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), i, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(getContext().getColor(R$color.health_chart_extend_background_color));
        draw(canvas);
        removeAllViews();
        return createBitmap;
    }
}
