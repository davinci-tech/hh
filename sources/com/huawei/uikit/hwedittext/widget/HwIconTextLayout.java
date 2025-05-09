package com.huawei.uikit.hwedittext.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwedittext.R$drawable;
import defpackage.smr;

/* loaded from: classes7.dex */
public class HwIconTextLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HwWidgetStyle f10667a;
    private View.OnClickListener b;
    private EditText c;
    protected HwShapeMode d;
    private AppCompatImageView e;
    private OnPasswordVisibleChangedListener f;
    private CharSequence g;
    private boolean h;
    private View i;
    private Drawable j;
    private CharSequence k;
    private int l;
    private Drawable m;
    private int n;
    private int o;
    private Drawable p;

    /* loaded from: classes9.dex */
    public interface OnPasswordVisibleChangedListener {
        void onPasswordVisibleChanged(ImageView imageView, boolean z);
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HwIconTextLayout.this.h) {
                HwIconTextLayout.this.b();
                if (HwIconTextLayout.this.f != null) {
                    HwIconTextLayout.this.f.onPasswordVisibleChanged(HwIconTextLayout.this.e, HwIconTextLayout.this.a());
                }
            }
            if (HwIconTextLayout.this.b != null) {
                HwIconTextLayout.this.b.onClick(HwIconTextLayout.this.e);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public HwIconTextLayout(Context context) {
        this(context, null);
    }

    private void getEyeId() {
        HwWidgetStyle hwWidgetStyle = this.f10667a;
        if (hwWidgetStyle == HwWidgetStyle.LIGHT) {
            this.o = R$drawable.hwedittext_ic_visibility_password;
            this.n = R$drawable.hwedittext_ic_visibility_off_password;
        } else if (hwWidgetStyle == HwWidgetStyle.DARK) {
            this.o = R$drawable.hwedittext_ic_visibility_password_dark;
            this.n = R$drawable.hwedittext_ic_visibility_off_password_dark;
        } else {
            this.o = R$drawable.hwedittext_ic_visibility_password_translucent;
            this.n = R$drawable.hwedittext_ic_visibility_off_password_translucent;
        }
    }

    public EditText getEditText() {
        return this.c;
    }

    public CharSequence getHint() {
        return this.c.getHint();
    }

    public Drawable getIcon() {
        return this.e.getDrawable();
    }

    public Drawable getIconBackground() {
        return this.i.getBackground();
    }

    public ImageView getImageView() {
        return this.e;
    }

    public View.OnClickListener getOnIconClickListener() {
        return this.b;
    }

    public OnPasswordVisibleChangedListener getOnPasswordVisibleChangedListener() {
        return this.f;
    }

    public CharSequence getText() {
        return this.c.getText();
    }

    public void setHint(CharSequence charSequence) {
        this.c.setHint(charSequence);
    }

    public void setIcon(Drawable drawable) {
        this.e.setImageDrawable(drawable);
    }

    public void setIconBackground(Drawable drawable) {
        this.i.setBackground(drawable);
        this.j = drawable;
    }

    public void setOnIconClickListener(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public void setOnPasswordVisibleChangedListener(OnPasswordVisibleChangedListener onPasswordVisibleChangedListener) {
        this.f = onPasswordVisibleChangedListener;
        boolean z = onPasswordVisibleChangedListener != null;
        if (this.h != z) {
            this.h = z;
            d();
        }
    }

    public void setPasswordType(boolean z) {
        if (this.h != z) {
            this.h = z;
            d();
        }
    }

    public void setText(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    public HwIconTextLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100369_res_0x7f0602d1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        int selectionStart = this.c.getSelectionStart();
        if (a() || !e()) {
            this.c.setInputType(129);
            this.e.setImageDrawable(AppCompatResources.getDrawable(getContext(), this.n));
        } else {
            this.c.setInputType(145);
            this.e.setImageDrawable(AppCompatResources.getDrawable(getContext(), this.o));
        }
        this.c.setSelection(selectionStart);
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwEditText);
    }

    private void d() {
        if (!this.h) {
            this.i.setBackground(this.j);
            return;
        }
        this.c.setTextDirection(5);
        this.c.setTextAlignment(5);
        this.i.setBackground(this.j);
        b();
        c();
    }

    private void d(int i) {
        LinearLayout.inflate(getContext(), i, this);
        EditText editText = (EditText) findViewById(R.id.hwedittext_edit);
        this.c = editText;
        if (editText != null) {
            editText.setHint(this.g);
            this.c.setText(this.k);
        }
        AppCompatImageView appCompatImageView = (AppCompatImageView) findViewById(R.id.hwedittext_icon);
        this.e = appCompatImageView;
        if (appCompatImageView != null) {
            if (appCompatImageView.getParent() instanceof View) {
                this.i = (View) this.e.getParent();
            }
            if (this.i == null) {
                return;
            }
            int i2 = this.l;
            if (i2 > 0) {
                this.e.setImageDrawable(edg_(i2));
            }
            HwShapeMode hwShapeMode = this.d;
            setIconBackground((hwShapeMode == HwShapeMode.BUBBLE || hwShapeMode == HwShapeMode.WHITE) ? this.m : this.p);
            d();
            this.i.setOnClickListener(new b());
        }
    }

    public HwIconTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        this.l = 0;
        this.o = 0;
        this.n = 0;
        edh_(super.getContext(), attributeSet, i);
        HwShapeMode hwShapeMode = this.d;
        if (hwShapeMode == HwShapeMode.BUBBLE) {
            c(R.layout.hwedittext_icon_text_layout_bubble, R.layout.hwedittext_icon_text_layout_bubble_dark, R.layout.hwedittext_icon_text_layout_bubble_translucent);
            return;
        }
        if (hwShapeMode == HwShapeMode.LINEAR) {
            c(R.layout.hwedittext_icon_text_layout_linear, R.layout.hwedittext_icon_text_layout_linear_dark, R.layout.hwedittext_icon_text_layout_linear_translucent);
        } else if (hwShapeMode == HwShapeMode.WHITE) {
            c(R.layout.hwedittext_icon_text_layout_bubble_white, R.layout.hwedittext_icon_text_layout_bubble_white_dark, R.layout.hwedittext_icon_text_layout_bubble_white_translucent);
        } else {
            c(R.layout.hwedittext_icon_text_layout_space, R.layout.hwedittext_icon_text_layout_space_dark, R.layout.hwedittext_icon_text_layout_space_translucent);
        }
    }

    private void c() {
        this.c.setAutofillHints(new String[]{"password"});
    }

    private void edh_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100218_res_0x7f06023a, R.attr._2131100351_res_0x7f0602bf, R.attr._2131100363_res_0x7f0602cb, R.attr._2131100387_res_0x7f0602e3, R.attr._2131100397_res_0x7f0602ed, R.attr._2131100524_res_0x7f06036c, R.attr._2131100567_res_0x7f060397, R.attr._2131100606_res_0x7f0603be}, i, R.style.Widget_Emui_HwIconTextLayout);
        int i2 = obtainStyledAttributes.getInt(5, 0);
        if (i2 >= 0 && i2 < HwShapeMode.values().length) {
            this.d = HwShapeMode.values()[i2];
        }
        this.f10667a = HwWidgetStyle.values()[obtainStyledAttributes.getInt(7, 0)];
        this.g = obtainStyledAttributes.getString(1);
        this.k = obtainStyledAttributes.getString(6);
        this.h = obtainStyledAttributes.getBoolean(3, false);
        if (obtainStyledAttributes.hasValue(2)) {
            this.l = obtainStyledAttributes.getResourceId(2, 0);
        }
        this.p = obtainStyledAttributes.getDrawable(4);
        this.m = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        getEyeId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return (this.c.getInputType() & 4095) == 145;
    }

    private void c(int i, int i2, int i3) {
        HwWidgetStyle hwWidgetStyle = this.f10667a;
        if (hwWidgetStyle == HwWidgetStyle.LIGHT) {
            d(i);
        } else if (hwWidgetStyle == HwWidgetStyle.DARK) {
            d(i2);
        } else {
            d(i3);
        }
    }

    private boolean e() {
        int inputType = this.c.getInputType() & 4095;
        return (inputType == 129) | (inputType == 225) | (inputType == 18);
    }

    private Drawable edg_(int i) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), i);
        if (drawable != null) {
            DrawableCompat.setAutoMirrored(drawable, true);
        }
        return drawable;
    }
}
