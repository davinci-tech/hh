package com.huawei.ui.commonui.woundplastadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import defpackage.nrf;

/* loaded from: classes6.dex */
public class WoundPlast extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8994a;
    private View b;
    private ImageView c;
    private ImageSizeMode d;

    public enum ImageSizeMode {
        SMALL_IMAGE,
        NORMAL_IMAGE
    }

    public WoundPlast(Context context) {
        super(context);
        this.d = ImageSizeMode.SMALL_IMAGE;
        b();
    }

    public WoundPlast(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = ImageSizeMode.SMALL_IMAGE;
        b();
    }

    public WoundPlast(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = ImageSizeMode.SMALL_IMAGE;
        b();
    }

    private void b() {
        this.b = View.inflate(getContext(), R.layout.woundplast_ad_view, this);
        this.c = (ImageView) findViewById(R.id.ad_image);
        ImageView imageView = (ImageView) findViewById(R.id.ad_close);
        this.f8994a = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.woundplastadview.WoundPlast.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WoundPlast.this.b.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c();
    }

    public void setAdClickListener(View.OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
    }

    public void setAdImage(int i) {
        this.c.setImageResource(i);
    }

    public void setAdImage(String str) {
        nrf.cIv_(str, RequestOptions.overrideOf(Integer.MIN_VALUE, Integer.MIN_VALUE), this.c);
    }

    public void setImageSizeMode(ImageSizeMode imageSizeMode) {
        if (this.d != imageSizeMode) {
            this.d = imageSizeMode;
            c();
        }
    }

    public ImageSizeMode getImageSizeMode() {
        return this.d;
    }

    public void setCloseBtnClickListener(View.OnClickListener onClickListener) {
        this.f8994a.setOnClickListener(onClickListener);
    }

    private void e(int i, int i2) {
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.width = getResources().getDimensionPixelSize(i);
            layoutParams2.height = getResources().getDimensionPixelSize(i2);
            this.c.setLayoutParams(layoutParams2);
        }
    }

    private void setCloseBtnMargin(int i) {
        int dimensionPixelSize = i != 0 ? getResources().getDimensionPixelSize(i) : 0;
        ViewGroup.LayoutParams layoutParams = this.f8994a.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginEnd(dimensionPixelSize);
            layoutParams2.topMargin = dimensionPixelSize;
            this.f8994a.setLayoutParams(layoutParams2);
        }
    }

    /* renamed from: com.huawei.ui.commonui.woundplastadview.WoundPlast$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[ImageSizeMode.values().length];
            e = iArr;
            try {
                iArr[ImageSizeMode.SMALL_IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[ImageSizeMode.NORMAL_IMAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void c() {
        int i = AnonymousClass4.e[this.d.ordinal()];
        if (i == 1) {
            setCloseBtnMargin(0);
            e(R.dimen._2131363074_res_0x7f0a0502, R.dimen._2131363006_res_0x7f0a04be);
        } else {
            if (i != 2) {
                return;
            }
            setCloseBtnMargin(R.dimen._2131363122_res_0x7f0a0532);
            e(R.dimen._2131362941_res_0x7f0a047d, R.dimen._2131362872_res_0x7f0a0438);
        }
    }
}
