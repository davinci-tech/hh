package com.huawei.uikit.hwfloatingactionbutton.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huawei.health.R;
import com.huawei.hwfabengine.FloatingActionButtonAnimationListener;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.kns;
import defpackage.slo;
import defpackage.smr;
import defpackage.sms;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class HwFloatingActionButton extends FloatingActionButton {

    /* renamed from: a, reason: collision with root package name */
    private int[] f10671a;
    private boolean b;
    private slo c;
    private int[] d;
    private int[] e;
    private FloatingActionButtonAnimationListener f;
    private float g;
    private String h;
    private float i;
    private kns j;
    private final FloatingActionButtonAnimationListener o;

    class b implements FloatingActionButtonAnimationListener {
        b() {
        }

        @Override // com.huawei.hwfabengine.FloatingActionButtonAnimationListener
        public void onAnimationEnd() {
            if (HwFloatingActionButton.this.f != null) {
                HwFloatingActionButton.this.f.onAnimationEnd();
            }
        }

        @Override // com.huawei.hwfabengine.FloatingActionButtonAnimationListener
        public void onAnimationUpdate(float f) {
            if (HwFloatingActionButton.this.f != null) {
                HwFloatingActionButton.this.f.onAnimationUpdate(f);
            }
            HwFloatingActionButton.this.setAnimatorValue(f);
        }
    }

    public HwFloatingActionButton(Context context) {
        this(context, null);
    }

    public static HwFloatingActionButton c(Context context) {
        Object e = sms.e(context, sms.e(context, (Class<?>) HwFloatingActionButton.class, sms.c(context, 1, 1)), (Class<?>) HwFloatingActionButton.class);
        if (e instanceof HwFloatingActionButton) {
            return (HwFloatingActionButton) e;
        }
        return null;
    }

    private HwTextView getLabelView() {
        if (getTag(R.id.hwfab_label) instanceof HwTextView) {
            return (HwTextView) getTag(R.id.hwfab_label);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimatorValue(float f) {
        this.i = f;
        invalidate();
    }

    public String getTitle() {
        return this.h;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        edw_(getParent());
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        edv_(canvas);
        super.onDraw(canvas);
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (this.j == null) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.j.e();
        } else if (action == 1 || action == 3) {
            this.j.b();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        b(z);
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        if (drawable2 instanceof kns) {
            ((kns) drawable2).b(null);
        }
        if (drawable instanceof kns) {
            kns knsVar = (kns) drawable;
            this.j = knsVar;
            this.f = knsVar.d();
            this.j.b(this.o);
        }
        super.setImageDrawable(drawable);
    }

    public void setShadowColors(int[] iArr) {
        if (iArr == null || iArr.length != this.d.length) {
            return;
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        this.d = copyOf;
        if (copyOf[0] == 0 || copyOf[1] == 0) {
            return;
        }
        this.b = true;
        c(copyOf);
    }

    public void setTitle(String str) {
        this.h = str;
        HwTextView labelView = getLabelView();
        if (labelView != null) {
            labelView.setText(this.h);
            labelView.setAutoTextInfo(9, 1, 2);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return this.c == drawable || super.verifyDrawable(drawable);
    }

    public HwFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100326_res_0x7f0602a6);
    }

    public HwFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(e(context, i), attributeSet, i);
        this.b = false;
        this.d = new int[]{0, 0};
        this.e = new int[]{0, 0};
        this.f10671a = new int[]{0, 0};
        this.g = 0.0f;
        this.i = 1.0f;
        this.o = new b();
        edt_(super.getContext(), attributeSet, i);
    }

    private static Context e(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwFloatingActionButton);
    }

    private void edt_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100307_res_0x7f060293, R.attr._2131100308_res_0x7f060294, R.attr._2131100319_res_0x7f06029f, R.attr._2131100320_res_0x7f0602a0, R.attr._2131100321_res_0x7f0602a1, R.attr._2131100322_res_0x7f0602a2}, i, R.style.Widget_Emui_HwFloatingActionButton);
        this.h = obtainStyledAttributes.getString(5);
        edu_(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private void edu_(TypedArray typedArray) {
        this.d[0] = typedArray.getColor(4, 0);
        this.d[1] = typedArray.getColor(3, 0);
        int[] iArr = this.d;
        if (iArr[0] == 0 || iArr[1] == 0) {
            return;
        }
        this.b = true;
        c(iArr);
        this.e[0] = typedArray.getColor(1, 0);
        this.e[1] = typedArray.getColor(0, 0);
    }

    private void c(int[] iArr) {
        int i = iArr[0];
        int[] iArr2 = this.f10671a;
        if (i == iArr2[0] || iArr[1] == iArr2[1]) {
            return;
        }
        this.c = new slo(iArr, 4, 19);
        this.f10671a = Arrays.copyOf(iArr, iArr.length);
        int customSize = (int) ((getCustomSize() + 38) * this.i);
        this.c.setBounds(new Rect(0, 0, customSize, customSize));
    }

    private void edv_(Canvas canvas) {
        int i;
        int i2;
        if (this.b) {
            int customSize = (int) ((getCustomSize() + 38) * this.i);
            Bitmap eds_ = eds_(this.c, customSize, customSize);
            int width = getWidth() >> 1;
            int height = getHeight() >> 1;
            if (this.g != 0.0f) {
                canvas.translate(width, height);
                canvas.rotate(-this.g);
                i = width;
                i2 = height;
            } else {
                i = 0;
                i2 = 0;
            }
            int i3 = (int) (((this.i - 1.0f) * customSize) / 2.0f);
            canvas.drawBitmap(eds_, ((0 - ((int) (r8 * 19.0f))) - i3) - i, (0 - i3) - i2, (Paint) null);
            float f = this.g;
            if (f != 0.0f) {
                canvas.rotate(f);
                canvas.translate(-width, -height);
            }
        }
    }

    private Bitmap eds_(Drawable drawable, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i2, i);
        drawable.draw(canvas);
        return createBitmap;
    }

    private void edw_(ViewParent viewParent) {
        if (viewParent != null && (viewParent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
        }
    }

    private void b(boolean z) {
        if (this.b) {
            if (z) {
                c(this.d);
            } else {
                c(this.e);
            }
        }
    }
}
