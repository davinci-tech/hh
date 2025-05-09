package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.uiengine.common.IProgressButton;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes5.dex */
public abstract class a extends RelativeLayout implements View.OnClickListener, IProgressButton, IProgressButton.ProgressButtonResetListener {

    /* renamed from: a, reason: collision with root package name */
    protected final String f8027a;
    protected AppDownloadButtonStyle b;
    protected boolean c;
    private IProgressButton d;
    private ImageView e;
    private RelativeLayout.LayoutParams f;
    private int g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;

    public abstract AppStatus getStatus();

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void updateLayoutHeight() {
        this.d.updateLayoutHeight();
    }

    public void setVisibilityInner(int i) {
        this.d.getProgressBtn().setVisibility(i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setTextSize(float f) {
        this.d.setTextSize(f);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setTextColor(int i) {
        this.d.setTextColor(i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setText(CharSequence charSequence) {
        this.d.setTextInner(charSequence, this.n);
    }

    public void setResetWidth(boolean z) {
        this.i = z;
        this.d.setResetWidth(z);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setProgressDrawable(Drawable drawable, int i) {
        this.d.setProgressDrawable(drawable, i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setProgressDrawable(Drawable drawable) {
        this.d.setProgressDrawable(drawable);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setProgress(int i) {
        this.d.setProgress(i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setPaintTypeface(Typeface typeface, int i) {
        this.d.setPaintTypeface(typeface, i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setPaintTypeface(Typeface typeface) {
        this.d.setPaintTypeface(typeface);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setMinWidth(int i) {
        this.k = i;
        this.d.setMinWidth(i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setMaxWidth(int i) {
        this.j = i;
        this.d.setMaxWidth(i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setMax(int i) {
        this.d.setMax(i);
    }

    public void setLayoutParamsSkipSizeReset(ViewGroup.LayoutParams layoutParams) {
        this.o = true;
        setLayoutParams(layoutParams);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setFontFamily(String str) {
        this.d.setFontFamily(str);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setFixedWidth(boolean z) {
        this.d.setFixedWidth(z);
    }

    public void setCancelBtnClickListener(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton.ProgressButtonResetListener
    public void onSizeReset(int i, int i2) {
        ho.a(this.f8027a, "on size reset: %s, %s", Integer.valueOf(i), Integer.valueOf(i2));
        if (i <= 0 || i2 <= 0) {
            return;
        }
        if (this.o) {
            this.l = i;
            this.o = false;
        } else {
            b(i);
        }
        this.m = i2;
        a();
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        IProgressButton iProgressButton = this.d;
        if (iProgressButton != null && this.h) {
            ViewGroup.LayoutParams layoutParams = iProgressButton.getProgressBtn().getLayoutParams();
            layoutParams.height = View.MeasureSpec.getSize(i2);
            layoutParams.width = View.MeasureSpec.getSize(i);
            int i4 = this.m;
            if (i4 > 0) {
                layoutParams.height = i4;
            }
            if (this.i && (i3 = this.l) > 0) {
                layoutParams.width = i3;
            }
            if (this.j > 0) {
                int i5 = layoutParams.width;
                int i6 = this.j;
                if (i5 > i6) {
                    layoutParams.width = i6;
                }
            }
            if (this.k > 0) {
                int i7 = layoutParams.width;
                int i8 = this.k;
                if (i7 < i8) {
                    layoutParams.width = i8;
                }
            }
            if (layoutParams.width > 0 && layoutParams.height > 0) {
                this.d.getProgressBtn().setLayoutParams(layoutParams);
            }
        }
        super.onMeasure(i, i2);
    }

    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public boolean isFastClick() {
        return this.d.isFastClick();
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public void incrementProgressBy(int i) {
        this.d.incrementProgressBy(i);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public CharSequence getText() {
        return this.d.getText();
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public Rect getPromptRect() {
        return this.d.getPromptRect();
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public Drawable getProgressDrawable() {
        return this.d.getProgressDrawable();
    }

    @Override // com.huawei.hms.ads.uiengine.common.IProgressButton
    public int getProgress() {
        return this.d.getProgress();
    }

    public void b(int i, int i2, int i3, int i4) {
        this.d.getProgressBtn().setPaddingRelative(i, i2, i3, i4);
    }

    protected void a(AppStatus appStatus) {
        if (appStatus == null) {
            return;
        }
        this.n = b(appStatus);
        ho.a(this.f8027a, "configCancelBtn, status: %s", appStatus);
        if (this.n) {
            this.e.setImageDrawable(getCancelBtnDrawable());
            this.d.setShowCancelBtn(this.n);
            int measuredHeight = getMeasuredHeight();
            if (measuredHeight <= 0) {
                post(new Runnable() { // from class: com.huawei.openalliance.ad.views.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ho.a(a.this.f8027a, "post run");
                        a aVar = a.this;
                        aVar.a(aVar.getMeasuredHeight());
                    }
                });
                return;
            } else {
                a(measuredHeight);
                return;
            }
        }
        try {
            if (this.e.getParent() == this) {
                this.d.setShowCancelBtn(this.n);
                removeView(this.e);
            }
        } catch (Throwable th) {
            ho.c(this.f8027a, "remove cancel btn ex: %s", th.getClass().getSimpleName());
        }
    }

    public void a(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
        this.d.getProgressBtn().setOnClickListener(onClickListener);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.d.getProgressBtn().setPadding(i, i2, i3, i4);
    }

    private Drawable getCancelBtnDrawable() {
        AppDownloadButtonStyle appDownloadButtonStyle = this.b;
        return appDownloadButtonStyle == null ? getContext().getResources().getDrawable(R.drawable._2131428496_res_0x7f0b0490) : appDownloadButtonStyle.cancelBtnDrawable;
    }

    private static boolean b(AppStatus appStatus) {
        return AppStatus.PAUSE == appStatus || AppStatus.WAITING_FOR_WIFI == appStatus;
    }

    private void b(int i) {
        if (this.i || this.l <= 0) {
            int i2 = this.j;
            if ((i2 <= 0 || i <= i2) && ((i2 = this.k) <= 0 || i >= i2)) {
                this.l = i;
            } else {
                this.l = i2;
            }
        }
    }

    private void a(Context context, AttributeSet attributeSet, boolean z, IProgressButton iProgressButton) {
        a(context, attributeSet);
        ho.a(this.f8027a, "init, create with attrs: %s", Boolean.valueOf(this.h));
        if (iProgressButton == null || iProgressButton.getProgressBtn() == null) {
            iProgressButton = z ? new ab(context, attributeSet) : new ProgressButton(context, attributeSet);
        } else {
            ho.a(this.f8027a, "init btn for uiengine");
        }
        this.d = iProgressButton;
        ho.b(this.f8027a, "progressBtn: %s", Integer.valueOf(iProgressButton.hashCode()));
        this.d.getProgressBtn().setId(R.id.haid_down_btn_progress);
        setOnClickListener(this);
        this.d.setResetListener(this);
        this.d.getProgressBtn().setOnClickListener(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        addView(this.d.getProgressBtn(), layoutParams);
        this.c = z;
        a(context);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return;
        }
        this.h = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100152_res_0x7f0601f8, R.attr._2131100153_res_0x7f0601f9, R.attr._2131100154_res_0x7f0601fa, R.attr._2131100155_res_0x7f0601fb, R.attr._2131100156_res_0x7f0601fc, R.attr._2131100158_res_0x7f0601fe, R.attr._2131100159_res_0x7f0601ff, R.attr._2131100160_res_0x7f060200, R.attr._2131100161_res_0x7f060201});
        try {
            this.i = obtainStyledAttributes.getBoolean(4, true);
            this.j = obtainStyledAttributes.getDimensionPixelSize(2, 0);
            this.k = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        } finally {
            try {
            } finally {
            }
        }
    }

    private void a(Context context) {
        if (context == null) {
            return;
        }
        this.e = new ImageView(context);
        this.g = ao.a(context, 16.0f);
        this.e.setImageDrawable(context.getResources().getDrawable(R.drawable._2131428496_res_0x7f0b0490));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.f = layoutParams;
        layoutParams.addRule(19, this.d.getProgressBtn().getId());
        this.f.addRule(15);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.g = i < ao.a(getContext(), 40.0f) ? ao.a(getContext(), 12.0f) : ao.a(getContext(), 16.0f);
        int cancelBtnHeight = this.d.getCancelBtnHeight(i);
        ho.a(this.f8027a, "btnHeight: %s, cancelBtnSize: %s, realBtnHeight: %s", Integer.valueOf(i), Integer.valueOf(this.g), Integer.valueOf(cancelBtnHeight));
        this.f.height = cancelBtnHeight;
        this.f.width = cancelBtnHeight;
        int i2 = 0;
        this.f.setMarginEnd(0);
        int i3 = this.g;
        int i4 = (cancelBtnHeight - i3) / 2;
        if (i4 <= 0) {
            this.f.height = i3;
            this.f.width = this.g;
            this.f.setMarginEnd(ao.a(getContext(), 12.0f));
        } else {
            i2 = i4;
        }
        this.e.setPaddingRelative(i2, i2, i2, i2);
        if (this.c) {
            this.d.setCancelBtnHeight(this.g);
            this.e.setBackground(this.b.b().getBackground());
        }
        try {
            if (this.e.getParent() != this) {
                addView(this.e, this.f);
            }
        } catch (Throwable th) {
            ho.c(this.f8027a, "add cancel btn ex: %s", th.getClass().getSimpleName());
        }
    }

    private void a() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = this.m;
            layoutParams.width = this.l;
            setLayoutParams(layoutParams);
        }
        if (this.n) {
            a(this.m);
        }
    }

    public a(Context context, Boolean bool, IProgressButton iProgressButton) {
        super(context);
        this.f8027a = "AppDownBtn_" + hashCode();
        this.h = false;
        this.i = true;
        this.n = false;
        this.o = false;
        a(context, (AttributeSet) null, (bool == null ? true : bool).booleanValue(), iProgressButton);
    }

    public a(Context context, Boolean bool) {
        super(context);
        this.f8027a = "AppDownBtn_" + hashCode();
        this.h = false;
        this.i = true;
        this.n = false;
        this.o = false;
        a(context, (AttributeSet) null, (bool == null ? true : bool).booleanValue(), (IProgressButton) null);
    }

    public a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f8027a = "AppDownBtn_" + hashCode();
        this.h = false;
        this.i = true;
        this.n = false;
        this.o = false;
        a(context, attributeSet, false, (IProgressButton) null);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8027a = "AppDownBtn_" + hashCode();
        this.h = false;
        this.i = true;
        this.n = false;
        this.o = false;
        a(context, attributeSet, false, (IProgressButton) null);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8027a = "AppDownBtn_" + hashCode();
        this.h = false;
        this.i = true;
        this.n = false;
        this.o = false;
        a(context, attributeSet, false, (IProgressButton) null);
    }

    public a(Context context) {
        super(context);
        this.f8027a = "AppDownBtn_" + hashCode();
        this.h = false;
        this.i = true;
        this.n = false;
        this.o = false;
        a(context, (AttributeSet) null, false, (IProgressButton) null);
    }
}
