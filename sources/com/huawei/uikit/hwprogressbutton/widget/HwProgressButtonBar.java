package com.huawei.uikit.hwprogressbutton.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.health.R;
import com.huawei.uikit.hwbutton.widget.HwButton;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.smr;

/* loaded from: classes9.dex */
public class HwProgressButtonBar extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HwButton f10682a;
    private HwTextView b;
    private HwTextView c;
    private HwButton d;
    private HwProgressButton e;
    private int f;
    private int g;
    private LinearLayout h;
    private LinearLayout i;
    private LinearLayout j;
    private ColorStateList k;
    private ColorStateList l;
    private ColorStateList m;
    private ColorStateList n;
    private int o;

    public HwProgressButtonBar(Context context) {
        this(context, null);
    }

    private static Context c(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwProgressButton);
    }

    private ColorStateList getSmartIconColor() {
        return this.l;
    }

    private ColorStateList getSmartTitleColor() {
        return this.k;
    }

    private void setIconBounds(Drawable drawable) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.f;
            if (intrinsicWidth > i) {
                if (intrinsicWidth != 0) {
                    intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                }
                intrinsicWidth = i;
            }
            if (intrinsicHeight <= i) {
                i = intrinsicHeight;
            } else if (intrinsicHeight != 0) {
                intrinsicWidth = (int) (intrinsicWidth * (i / intrinsicHeight));
            }
            drawable.setBounds(0, 0, intrinsicWidth, i);
        }
    }

    public HwButton getApplyButton() {
        return this.d;
    }

    public int getApplyButtonId() {
        return R.id.hwprogressbutton_bar_apply_button;
    }

    public ViewGroup getEndContainer() {
        return this.i;
    }

    public int getEndItemId() {
        return R.id.hwprogressbutton_bar_end_container;
    }

    public HwProgressButton getHwProgressButton() {
        return this.e;
    }

    public int getHwProgressButtonId() {
        return R.id.hwprogressbutton_bar_progressbutton;
    }

    public ViewGroup getStartContainer() {
        return this.h;
    }

    public int getStartItemId() {
        return R.id.hwprogressbutton_bar_start_container;
    }

    public int getStyleMode() {
        return this.g;
    }

    public HwButton getUpdateButton() {
        return this.f10682a;
    }

    public int getUpdateButtonId() {
        return R.id.hwprogressbutton_bar_update_button;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        HwTextView hwTextView = this.c;
        int lineCount = hwTextView != null ? hwTextView.getLineCount() : 0;
        HwTextView hwTextView2 = this.b;
        int lineCount2 = hwTextView2 != null ? hwTextView2.getLineCount() : 0;
        if (this.b != null && this.c != null) {
            if (lineCount > 1 || lineCount2 > 1) {
                int i3 = this.o;
                setPadding(0, i3, 0, i3);
                this.h.setGravity(49);
                this.i.setGravity(49);
            } else {
                setPadding(0, 0, 0, 0);
                this.h.setGravity(17);
                this.i.setGravity(17);
            }
        }
        super.onMeasure(i, i2);
    }

    public void setApplyButtonClickListener(View.OnClickListener onClickListener) {
        HwButton hwButton = this.d;
        if (hwButton == null) {
            Log.w("HwProgressButtonBar", "primary button is null ");
        } else {
            hwButton.setOnClickListener(onClickListener);
        }
    }

    public void setButtonsText(CharSequence charSequence, CharSequence charSequence2) {
        e(charSequence, this.d);
        e(charSequence2, this.f10682a);
    }

    public void setEndItem(Drawable drawable, CharSequence charSequence) {
        a(this.i, this.b, drawable, charSequence);
    }

    public void setEndItemClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.i;
        if (linearLayout == null) {
            Log.w("HwProgressButtonBar", "endItem is null");
        } else {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setSmartIconColor(ColorStateList colorStateList) {
        this.l = colorStateList;
    }

    public void setSmartTitleColor(ColorStateList colorStateList) {
        this.k = colorStateList;
    }

    public void setStartAndEndItem(Drawable drawable, CharSequence charSequence, Drawable drawable2, CharSequence charSequence2) {
        a(this.h, this.c, drawable, charSequence);
        a(this.i, this.b, drawable2, charSequence2);
    }

    public void setStartItem(Drawable drawable, CharSequence charSequence) {
        a(this.h, this.c, drawable, charSequence);
    }

    public void setStartItemClickListener(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.h;
        if (linearLayout == null) {
            Log.w("HwProgressButtonBar", "startItem is null");
        } else {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void setStyleMode(int i) {
        this.g = i;
        if (i == 0) {
            this.j.setVisibility(8);
            this.e.setVisibility(0);
        } else {
            if (i != 1) {
                return;
            }
            this.j.setVisibility(0);
            this.e.setVisibility(8);
        }
    }

    public void setUpdateButtonClickListener(View.OnClickListener onClickListener) {
        HwButton hwButton = this.f10682a;
        if (hwButton == null) {
            Log.w("HwProgressButtonBar", "primary button is null ");
        } else {
            hwButton.setOnClickListener(onClickListener);
        }
    }

    public HwProgressButtonBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100461_res_0x7f06032d);
    }

    private void edZ_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes;
        LayoutInflater.from(getContext()).inflate(i, (ViewGroup) this, true);
        this.c = (HwTextView) findViewById(R.id.hwprogressbutton_bar_start_text_view);
        this.b = (HwTextView) findViewById(R.id.hwprogressbutton_bar_end_text_view);
        this.d = (HwButton) findViewById(R.id.hwprogressbutton_bar_apply_button);
        this.f10682a = (HwButton) findViewById(R.id.hwprogressbutton_bar_update_button);
        this.e = (HwProgressButton) findViewById(R.id.hwprogressbutton_bar_progressbutton);
        this.j = (LinearLayout) findViewById(R.id.hwprogressbutton_bar_button_layout);
        this.h = (LinearLayout) findViewById(R.id.hwprogressbutton_bar_start_container);
        this.i = (LinearLayout) findViewById(R.id.hwprogressbutton_bar_end_container);
        this.o = getResources().getDimensionPixelSize(R.dimen._2131364277_res_0x7f0a09b5);
        this.f = getResources().getDimensionPixelSize(R.dimen._2131364274_res_0x7f0a09b2);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable._2131429281_res_0x7f0b07a1);
        Drawable drawable2 = ContextCompat.getDrawable(context, R.drawable._2131429280_res_0x7f0b07a0);
        Drawable drawable3 = ContextCompat.getDrawable(context, R.drawable._2131429281_res_0x7f0b07a1);
        Drawable mutate = DrawableCompat.wrap(drawable2).mutate();
        DrawableCompat.setTint(mutate, ContextCompat.getColor(context, R.color.emui_accent));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{mutate, drawable});
        LayerDrawable layerDrawable2 = new LayerDrawable(new Drawable[]{mutate, drawable3});
        this.d.setBackground(layerDrawable);
        this.f10682a.setBackground(layerDrawable2);
        Resources.Theme theme = context.getTheme();
        if (theme != null && (obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100334_res_0x7f0602ae, R.attr._2131100335_res_0x7f0602af, R.attr._2131100336_res_0x7f0602b0, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100339_res_0x7f0602b3, R.attr._2131100340_res_0x7f0602b4, R.attr._2131100341_res_0x7f0602b5, R.attr._2131100362_res_0x7f0602ca, R.attr._2131100455_res_0x7f060327, R.attr._2131100456_res_0x7f060328, R.attr._2131100457_res_0x7f060329, R.attr._2131100458_res_0x7f06032a, R.attr._2131100459_res_0x7f06032b, R.attr._2131100460_res_0x7f06032c, R.attr._2131100462_res_0x7f06032e, R.attr._2131100463_res_0x7f06032f, R.attr._2131100464_res_0x7f060330, R.attr._2131100465_res_0x7f060331, R.attr._2131100466_res_0x7f060332, R.attr._2131100467_res_0x7f060333}, R.attr._2131100459_res_0x7f06032b, R.style.Widget_Emui_HwProgressButtonBar_Button_Small)) != null) {
            this.n = obtainStyledAttributes.getColorStateList(13);
            this.m = obtainStyledAttributes.getColorStateList(11);
            obtainStyledAttributes.recycle();
        }
        setStyleMode(0);
    }

    public void setEndItem(int i, int i2) {
        a(this.i, this.b, ContextCompat.getDrawable(getContext(), i), getContext().getResources().getText(i2));
    }

    public void setStartItem(int i, int i2) {
        a(this.h, this.c, ContextCompat.getDrawable(getContext(), i), getContext().getResources().getText(i2));
    }

    public HwProgressButtonBar(Context context, AttributeSet attributeSet, int i) {
        super(c(context, i), attributeSet, i);
        edZ_(context, attributeSet, R.layout.hwprogressbuttonbar_layout);
    }

    public void setButtonsText(int i, int i2) {
        e(getContext().getResources().getText(i), this.d);
        e(getContext().getResources().getText(i2), this.f10682a);
    }

    public void setStartAndEndItem(int i, int i2, int i3, int i4) {
        Resources resources = getContext().getResources();
        a(this.h, this.c, ContextCompat.getDrawable(getContext(), i), resources.getText(i2));
        a(this.i, this.b, ContextCompat.getDrawable(getContext(), i3), resources.getText(i4));
    }

    private void a(LinearLayout linearLayout, HwTextView hwTextView, Drawable drawable, CharSequence charSequence) {
        if (hwTextView != null && linearLayout != null) {
            hwTextView.setText(charSequence);
            if (drawable == null && !a(hwTextView)) {
                linearLayout.setVisibility(8);
                return;
            }
            setIconBounds(drawable);
            if (a(hwTextView)) {
                hwTextView.setBackground(null);
                hwTextView.setCompoundDrawables(null, drawable, null, null);
            } else {
                hwTextView.setCompoundDrawables(null, null, null, null);
                hwTextView.setBackground(drawable);
            }
            ColorStateList smartIconColor = getSmartIconColor();
            ColorStateList smartTitleColor = getSmartTitleColor();
            Drawable mutate = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            if (smartIconColor != null && smartTitleColor != null) {
                if (mutate != null) {
                    DrawableCompat.setTintList(mutate, smartIconColor);
                }
                hwTextView.setTextColor(smartTitleColor);
                return;
            }
            ColorStateList colorStateList = this.m;
            if (colorStateList != null && mutate != null) {
                DrawableCompat.setTintList(mutate, colorStateList);
            }
            ColorStateList colorStateList2 = this.n;
            if (colorStateList2 != null) {
                hwTextView.setTextColor(colorStateList2);
                return;
            }
            return;
        }
        Log.w("HwProgressButtonBar", "item layout initialization failed");
    }

    private boolean a(HwTextView hwTextView) {
        return !TextUtils.isEmpty(hwTextView.getText());
    }

    private void e(CharSequence charSequence, HwButton hwButton) {
        if (hwButton == null) {
            Log.w("HwProgressButtonBar", "button layout initialization failed");
        } else {
            hwButton.setText(charSequence);
        }
    }
}
