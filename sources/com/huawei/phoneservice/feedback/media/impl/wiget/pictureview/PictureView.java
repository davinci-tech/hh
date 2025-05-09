package com.huawei.phoneservice.feedback.media.impl.wiget.pictureview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;

/* loaded from: classes9.dex */
public class PictureView extends AppCompatImageView {
    private ImageView.ScaleType b;
    private l d;

    public void setZoomable(boolean z) {
        this.d.b(z);
    }

    public void setZoomTransitionDuration(int i) {
        this.d.d(i);
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        l lVar = this.d;
        if (lVar == null) {
            this.b = scaleType;
        } else {
            lVar.cer_(scaleType);
        }
    }

    public void setScale(float f) {
        this.d.j(f);
    }

    public void setRotationTo(float f) {
        this.d.e(f);
    }

    public void setRotationBy(float f) {
        this.d.a(f);
    }

    public void setOnViewTapListener(j jVar) {
        this.d.d(jVar);
    }

    public void setOnViewDragListener(i iVar) {
        this.d.a(iVar);
    }

    public void setOnSingleFlingListener(h hVar) {
        this.d.d(hVar);
    }

    public void setOnScaleChangeListener(g gVar) {
        this.d.b(gVar);
    }

    public void setOnPhotoTapListener(f fVar) {
        this.d.e(fVar);
    }

    public void setOnOutsidePhotoTapListener(e eVar) {
        this.d.c(eVar);
    }

    public void setOnMatrixChangeListener(d dVar) {
        this.d.e(dVar);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.d.ceq_(onLongClickListener);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.d.ceo_(onDoubleTapListener);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.d.cep_(onClickListener);
    }

    public void setMinimumScale(float f) {
        this.d.c(f);
    }

    public void setMediumScale(float f) {
        this.d.d(f);
    }

    public void setMaximumScale(float f) {
        this.d.b(f);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        l lVar = this.d;
        if (lVar != null) {
            lVar.j();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        l lVar = this.d;
        if (lVar != null) {
            lVar.j();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        l lVar = this.d;
        if (lVar != null) {
            lVar.j();
        }
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (frame) {
            this.d.j();
        }
        return frame;
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.d.c(z);
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.d.ceu_();
    }

    public float getScale() {
        return this.d.f();
    }

    public float getMinimumScale() {
        return this.d.b();
    }

    public float getMediumScale() {
        return this.d.d();
    }

    public float getMaximumScale() {
        return this.d.c();
    }

    @Override // android.widget.ImageView
    public Matrix getImageMatrix() {
        return this.d.cet_();
    }

    public RectF getDisplayRect() {
        return this.d.ces_();
    }

    public l getAttaches() {
        return this.d;
    }

    private void a() {
        this.d = new l(this);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        ImageView.ScaleType scaleType = this.b;
        if (scaleType != null) {
            setScaleType(scaleType);
            this.b = null;
        }
    }

    public PictureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public PictureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PictureView(Context context) {
        this(context, null);
    }
}
