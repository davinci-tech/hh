package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.gif.GifPlayView;
import com.huawei.openalliance.ad.views.interfaces.INativeWindowImageView;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class NativeWindowImageView extends AutoScaleSizeRelativeLayout implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, az.a, INativeWindowImageView {

    /* renamed from: a, reason: collision with root package name */
    private View f7830a;
    private GifPlayView b;
    private ProgressBar c;
    private INativeAd d;
    private Drawable e;
    private Rect f;
    private Rect g;
    private float h;
    private int i;
    private boolean j;

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeWindowImageView
    public void setNativeAd(INativeAd iNativeAd) {
        this.d = iNativeAd;
        if (iNativeAd != null) {
            Iterator<ImageInfo> it = iNativeAd.getImageInfos().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ImageInfo next = it.next();
                if (next != null) {
                    String url = next.getUrl();
                    if (cz.j(url)) {
                        rt rtVar = new rt();
                        rtVar.c(url);
                        rtVar.b(next.getSha256());
                        rtVar.b(next.isCheckSha256());
                        rtVar.c(next.a());
                        rtVar.a(Constants.NATIVE_WINDOW_SUB_DIR);
                        az.a(getContext(), rtVar, this);
                    } else {
                        az.a(getContext(), url, this);
                    }
                }
            }
            requestLayout();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeWindowImageView
    public void setDisplayView(View view) {
        if (view != null) {
            this.j = false;
            this.f7830a = view;
        }
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        e();
    }

    @Override // com.huawei.openalliance.ad.views.AutoScaleSizeRelativeLayout, android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = getMeasuredWidth();
        this.b.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (measuredWidth * this.h), 1073741824));
    }

    @Override // com.huawei.openalliance.ad.views.AutoScaleSizeRelativeLayout, android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        GifPlayView gifPlayView = this.b;
        gifPlayView.layout(0, 0, gifPlayView.getMeasuredWidth(), this.b.getMeasuredHeight());
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        e();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
    }

    @Override // com.huawei.openalliance.ad.utils.az.a
    public void a(final Drawable drawable) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.NativeWindowImageView.2
            @Override // java.lang.Runnable
            public void run() {
                NativeWindowImageView.this.e = drawable;
                NativeWindowImageView nativeWindowImageView = NativeWindowImageView.this;
                nativeWindowImageView.setImageDrawable(nativeWindowImageView.e);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.utils.az.a
    public void a() {
        ho.d("NativeWindowImageView", "load image fail");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageDrawable(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof com.huawei.openalliance.ad.views.gif.b) {
                ((com.huawei.openalliance.ad.views.gif.b) drawable).a(new com.huawei.openalliance.ad.views.gif.d() { // from class: com.huawei.openalliance.ad.views.NativeWindowImageView.1
                    @Override // com.huawei.openalliance.ad.views.gif.d
                    public void b() {
                    }

                    @Override // com.huawei.openalliance.ad.views.gif.d
                    public void c() {
                    }

                    @Override // com.huawei.openalliance.ad.views.gif.d
                    public void a() {
                        NativeWindowImageView.this.k();
                    }
                });
            } else {
                k();
            }
            this.b.setImageDrawable(drawable);
            this.c.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        int intrinsicWidth = this.e.getIntrinsicWidth();
        int intrinsicHeight = this.e.getIntrinsicHeight();
        if (intrinsicHeight != 0 && intrinsicWidth != 0) {
            this.h = intrinsicHeight / intrinsicWidth;
        }
        e();
    }

    private void j() {
        int i;
        int width = (int) (getWidth() * this.h);
        if (a(width)) {
            int height = (this.f.height() - width) / 2;
            if (this.g.top - this.f.top <= height) {
                i = 0;
            } else {
                if (this.f.bottom - this.g.bottom <= height) {
                    this.i = this.g.height() - width;
                    return;
                }
                i = (this.f.top + height) - this.g.top;
            }
            this.i = i;
        }
    }

    private void i() {
        Rect rect = new Rect();
        getLocalVisibleRect(rect);
        Rect rect2 = new Rect();
        getGlobalVisibleRect(rect2);
        this.g.left = rect2.left - rect.left;
        Rect rect3 = this.g;
        rect3.right = rect3.left + getWidth();
        this.g.top = rect2.top - rect.top;
        Rect rect4 = this.g;
        rect4.bottom = rect4.top + getHeight();
    }

    private void h() {
        Object parent = this.f7830a.getParent();
        if (parent == null) {
            ho.d("NativeWindowImageView", "invalid parent obj");
        } else {
            ((View) parent).getGlobalVisibleRect(this.f);
        }
    }

    private void g() {
        if (this.e == null) {
            return;
        }
        this.b.setScaleType(ImageView.ScaleType.MATRIX);
        int intrinsicWidth = this.e.getIntrinsicWidth();
        float width = intrinsicWidth != 0 ? getWidth() / intrinsicWidth : 1.0f;
        Matrix matrix = new Matrix();
        matrix.setScale(width, width);
        matrix.postTranslate(0.0f, this.i);
        this.b.setImageMatrix(matrix);
        this.b.invalidate();
    }

    private boolean f() {
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        return rect.width() > 0 && rect.height() > 0;
    }

    private void e() {
        if (f()) {
            b();
            h();
            i();
            j();
            g();
        }
    }

    private void d() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
    }

    private void c() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.removeOnGlobalLayoutListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        if (this.j) {
            ViewParent viewParent = this.f7830a.getParent();
            while (viewParent != 0 && !a(viewParent)) {
                viewParent = viewParent.getParent();
            }
            if (a(viewParent)) {
                this.f7830a = (View) viewParent;
            }
        }
    }

    private boolean a(Object obj) {
        return obj instanceof PPSNativeView;
    }

    private boolean a(int i) {
        return this.f.height() >= i;
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_window_image_layout, this);
        this.f7830a = this;
        this.b = (GifPlayView) findViewById(R.id.window_image_content);
        this.c = (ProgressBar) findViewById(R.id.window_image_progress);
        setRatio(Float.valueOf(1.7777778f));
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public NativeWindowImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.g = new Rect();
        this.h = 1.3007812f;
        this.i = 0;
        this.j = true;
        a(context);
    }

    public NativeWindowImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new Rect();
        this.h = 1.3007812f;
        this.i = 0;
        this.j = true;
        a(context);
    }

    public NativeWindowImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new Rect();
        this.h = 1.3007812f;
        this.i = 0;
        this.j = true;
        a(context);
    }

    public NativeWindowImageView(Context context) {
        super(context);
        this.g = new Rect();
        this.h = 1.3007812f;
        this.i = 0;
        this.j = true;
        a(context);
    }
}
