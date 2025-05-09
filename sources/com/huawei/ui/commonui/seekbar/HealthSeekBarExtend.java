package com.huawei.ui.commonui.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.uikit.phone.hwseekbar.widget.HwSeekBar;
import defpackage.nrz;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class HealthSeekBarExtend extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HwSeekBar f8948a;
    private final Context c;
    private ImageView e;

    public HealthSeekBarExtend(Context context) {
        this(context, null);
    }

    public HealthSeekBarExtend(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        Object systemService = context.getSystemService("layout_inflater");
        if (!(systemService instanceof LayoutInflater)) {
            LogUtil.h("CommonUI_HealthSeekBarExtend", "HealthSeekBarExtend service is not instance of LayoutInflater");
            return;
        }
        ((LayoutInflater) systemService).inflate(R.layout.health_seek_bar_extend, this);
        this.f8948a = (HwSeekBar) findViewById(R.id.health_seekbar);
        this.e = (ImageView) findViewById(R.id.health_color_ruler_image);
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.health_seekbar_extend);
        this.f8948a.setMax(obtainStyledAttributes.getInteger(R$styleable.health_seekbar_extend_max, 100));
        Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.health_seekbar_extend_progressDrawable);
        if (drawable != null) {
            this.f8948a.setProgressDrawable(drawable);
        }
        setThumb(obtainStyledAttributes.getDrawable(R$styleable.health_seekbar_extend_thumb));
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R$styleable.health_seekbar_extend_rulerSrc);
        if (drawable2 != null) {
            if (LanguageUtil.bc(context)) {
                BitmapDrawable cKm_ = nrz.cKm_(context, drawable2);
                if (cKm_ != null) {
                    this.e.setImageDrawable(cKm_);
                }
            } else {
                this.e.setImageDrawable(drawable2);
            }
        }
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.health_seekbar_extend_dividerHeight, (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
        ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) layoutParams).topMargin = dimensionPixelOffset;
        }
        obtainStyledAttributes.recycle();
    }

    public void setThumb(Drawable drawable) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth() / 2;
            this.f8948a.setThumb(drawable);
            setThumbOffset(intrinsicWidth);
            setSeekBarPadding(intrinsicWidth, 0, intrinsicWidth, 0);
            this.e.setPadding(intrinsicWidth, 0, intrinsicWidth, 0);
        }
    }

    public void setThumbOffset(int i) {
        this.f8948a.setThumbOffset(i);
    }

    public void setSeekBarPadding(int i, int i2, int i3, int i4) {
        this.f8948a.setPadding(i, i2, i3, i4);
    }

    public void setProgressDrawable(Drawable drawable) {
        if (drawable != null) {
            this.f8948a.setProgressDrawable(drawable);
        }
    }

    public void setRulerSrc(Drawable drawable) {
        if (drawable == null || this.e == null) {
            return;
        }
        if (LanguageUtil.bc(this.c)) {
            BitmapDrawable cKm_ = nrz.cKm_(this.c, drawable);
            if (cKm_ != null) {
                this.e.setImageDrawable(cKm_);
                return;
            }
            return;
        }
        this.e.setImageDrawable(drawable);
    }

    public void setMax(int i) {
        HwSeekBar hwSeekBar = this.f8948a;
        if (hwSeekBar != null) {
            hwSeekBar.setMax(i);
        }
    }

    public void setProgress(int i) {
        if (this.f8948a == null) {
            return;
        }
        if (LanguageUtil.bc(this.c)) {
            HwSeekBar hwSeekBar = this.f8948a;
            hwSeekBar.setProgress(hwSeekBar.getMax() - (i - this.f8948a.getMin()));
        } else {
            this.f8948a.setProgress(i);
        }
    }
}
