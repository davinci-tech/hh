package com.huawei.health.suggestion.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.view.AutoFillColorView;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nrf;
import defpackage.nrl;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import huawei.android.hwcolorpicker.HwColorPicker;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class AutoFillColorView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f3412a;
    View b;
    View c;
    private String d;
    ImageView e;
    private int f;
    private Drawable g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int l;

    public AutoFillColorView(Context context) {
        super(context);
        this.h = false;
        this.j = true;
        aMe_(context, null);
    }

    public AutoFillColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = false;
        this.j = true;
        aMe_(context, attributeSet);
    }

    public AutoFillColorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = false;
        this.j = true;
        aMe_(context, attributeSet);
    }

    private void aMe_(Context context, AttributeSet attributeSet) {
        this.f3412a = new WeakReference<>(context);
        View.inflate(context, R.layout.auto_fill_color_view, this);
        this.e = (ImageView) findViewById(R.id.img_content);
        this.b = findViewById(R.id.startView);
        this.c = findViewById(R.id.coverView);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099937_res_0x7f060121, R.attr._2131099938_res_0x7f060122, R.attr._2131099952_res_0x7f060130});
            if (obtainStyledAttributes.getDrawable(1) != null) {
                this.e.setImageDrawable(obtainStyledAttributes.getDrawable(1));
            }
            ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                this.e.setLayoutParams(layoutParams);
            }
            int layoutDimension = obtainStyledAttributes.getLayoutDimension(2, 0);
            int layoutDimension2 = obtainStyledAttributes.getLayoutDimension(0, 0);
            if (layoutDimension != 0) {
                layoutParams.width = layoutDimension;
            }
            if (layoutDimension2 != 0) {
                layoutParams.height = layoutDimension2;
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (getVisibility() != i && i == 0 && !this.j) {
            d();
        }
        super.setVisibility(i);
    }

    private void d() {
        this.e.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.suggestion.ui.view.AutoFillColorView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AutoFillColorView.this.e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                AutoFillColorView.this.c();
            }
        });
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ImageView imageView = this.e;
        if (imageView == null) {
            LogUtil.h("Suggestion_AutoFillColorView", "onConfigurationChanged() failed with mImgContent is null ");
            return;
        }
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        } else {
            layoutParams.width = -1;
        }
        this.e.setLayoutParams(layoutParams);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.h) {
            aMj_(this.g, this.i);
        } else {
            b(this.d, this.i);
        }
    }

    public void aMj_(Drawable drawable, boolean z) {
        WeakReference<Context> weakReference;
        if (this.e == null || (weakReference = this.f3412a) == null || weakReference.get() == null) {
            LogUtil.h("Suggestion_AutoFillColorView", "loadContentImage, The mImageContent or context is null, return.");
            return;
        }
        if (drawable == null) {
            LogUtil.h("Suggestion_AutoFillColorView", "the resourceDrawable is null");
            return;
        }
        this.g = drawable;
        this.i = z;
        this.h = true;
        this.j = false;
        aMi_(drawable, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: aMf_, reason: merged with bridge method [inline-methods] */
    public void aMi_(final Drawable drawable, final boolean z) {
        Context context = this.f3412a.get();
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: gfo
                @Override // java.lang.Runnable
                public final void run() {
                    AutoFillColorView.this.aMi_(drawable, z);
                }
            });
            return;
        }
        if ((context instanceof Activity) && ((Activity) context).isDestroyed()) {
            LogUtil.h("Suggestion_AutoFillColorView", "loadContentImage() activity isDestroyed");
        } else if (z) {
            nrf.cIq_(drawable, new b(this));
        } else {
            nrf.cIp_(drawable, this.e);
        }
    }

    public void b(final String str, final boolean z) {
        WeakReference<Context> weakReference;
        LogUtil.a("Suggestion_AutoFillColorView", "loadContentImage() url = ", str);
        if (this.e == null || (weakReference = this.f3412a) == null || weakReference.get() == null) {
            LogUtil.h("Suggestion_AutoFillColorView", "loadContentImage() mImageContent or context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_AutoFillColorView", "loadContentImage() url is empty");
            return;
        }
        this.d = str;
        this.i = z;
        this.h = false;
        this.j = false;
        final Context context = this.f3412a.get();
        post(new Runnable() { // from class: gfp
            @Override // java.lang.Runnable
            public final void run() {
                AutoFillColorView.this.e(context, z, str);
            }
        });
    }

    public /* synthetic */ void e(Context context, boolean z, String str) {
        if ((context instanceof Activity) && ((Activity) context).isDestroyed()) {
            LogUtil.h("Suggestion_AutoFillColorView", "loadContentImage() activity isDestroyed");
        } else if (z) {
            nrf.d(str, new b(this));
        } else {
            nrf.cIu_(str, this.e);
        }
    }

    static class b extends CustomTarget<Drawable> {
        private WeakReference<AutoFillColorView> c;

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
        }

        b(AutoFillColorView autoFillColorView) {
            this.c = new WeakReference<>(autoFillColorView);
        }

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: aMk_, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
            WeakReference<AutoFillColorView> weakReference = this.c;
            if (weakReference == null) {
                return;
            }
            AutoFillColorView autoFillColorView = weakReference.get();
            if (autoFillColorView == null) {
                LogUtil.h("Suggestion_AutoFillColorView", "autoFillColorView == null");
                return;
            }
            try {
                autoFillColorView.aMh_(drawable);
            } catch (OutOfMemoryError unused) {
                LogUtil.b("Suggestion_AutoFillColorView", "loadContentImage() OutOfMemoryError");
            } catch (RuntimeException e) {
                LogUtil.b("Suggestion_AutoFillColorView", "exception:", LogAnonymous.b((Throwable) e));
            }
        }
    }

    public void aMh_(Drawable drawable) {
        if (drawable == null) {
            LogUtil.h("Suggestion_AutoFillColorView", "fillColorBackground() drawable is null");
            return;
        }
        float aMc_ = aMc_(drawable);
        int height = this.e.getHeight();
        if (height == 0) {
            LogUtil.h("Suggestion_AutoFillColorView", "height == 0");
            if (getVisibility() == 0) {
                d();
                return;
            }
            return;
        }
        this.j = true;
        float width = (this.e.getWidth() * 1.0f) / height;
        int width2 = this.e.getWidth();
        if (aMc_ >= width) {
            this.e.setImageDrawable(drawable);
            LogUtil.a("Suggestion_AutoFillColorView", "fillColorBackground() onGlobalLayout More Long");
            return;
        }
        if (aMc_ < width) {
            ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                layoutParams2.width = (int) (this.e.getHeight() * aMc_);
                this.e.setLayoutParams(layoutParams2);
                width2 = layoutParams2.width;
            }
        } else {
            LogUtil.h("Suggestion_AutoFillColorView", "fillColorBackground is other condition");
        }
        int aMd_ = aMd_(drawable);
        if (this.b.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) this.b.getLayoutParams()).width = getMeasuredWidth() - width2;
        }
        this.b.setBackgroundColor(aMd_);
        if (this.c.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) this.c.getLayoutParams()).width = width2 / 2;
        }
        aMg_(drawable, aMd_);
    }

    private void aMg_(Drawable drawable, int i) {
        GradientDrawable gradientDrawable;
        int argb = Color.argb(0, Color.red(i), Color.green(i), Color.blue(i));
        WeakReference<Context> weakReference = this.f3412a;
        if (weakReference == null || weakReference.get() == null) {
            LogUtil.h("Suggestion_AutoFillColorView", "setGradientDrawable() context is null");
            return;
        }
        if (LanguageUtil.bc(this.f3412a.get())) {
            gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, new int[]{i, argb});
        } else {
            gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{i, argb});
        }
        this.c.setBackground(gradientDrawable);
        this.e.setImageDrawable(drawable);
    }

    private int aMd_(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        HwColorPicker.PickedColor processBitmap = HwColorPicker.processBitmap(createBitmap, HwColorPicker.ClientType.Debug);
        this.f = processBitmap.get(HwColorPicker.ResultType.MergedRgb1);
        this.l = processBitmap.get(HwColorPicker.ResultType.MergedRgb2);
        int i = processBitmap.get(HwColorPicker.ResultType.MergedNum1);
        int i2 = processBitmap.get(HwColorPicker.ResultType.MergedNum2);
        float[] fArr = new float[3];
        float[] fArr2 = new float[3];
        Color.colorToHSV(this.f, fArr);
        Color.colorToHSV(this.l, fArr2);
        return nrl.c(fArr, fArr2, Boolean.valueOf(i - i2 > 5), nrl.a(fArr));
    }

    private float aMc_(Drawable drawable) {
        int intrinsicHeight;
        if (drawable == null || (intrinsicHeight = drawable.getIntrinsicHeight()) == 0) {
            return 0.0f;
        }
        return (drawable.getIntrinsicWidth() * 1.0f) / intrinsicHeight;
    }

    public ImageView getContentImg() {
        ImageView imageView = this.e;
        return imageView != null ? imageView : (ImageView) findViewById(R.id.img_content);
    }
}
