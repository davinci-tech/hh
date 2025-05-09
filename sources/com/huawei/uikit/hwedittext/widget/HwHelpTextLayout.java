package com.huawei.uikit.hwedittext.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.TextViewCompat;
import com.huawei.health.R;
import defpackage.smr;

/* loaded from: classes9.dex */
public class HwHelpTextLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private CharSequence f10666a;
    private EditText b;
    private TextView c;
    private HwWidgetStyle d;
    protected HwShapeMode e;
    private int f;
    private CharSequence g;
    private CharSequence i;

    public HwHelpTextLayout(Context context) {
        this(context, null);
    }

    private static Context b(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwEditText);
    }

    public EditText getEditText() {
        return this.b;
    }

    public CharSequence getHelp() {
        return this.c.getText();
    }

    public CharSequence getHint() {
        return this.b.getHint();
    }

    public CharSequence getText() {
        return this.b.getText();
    }

    public TextView getTextView() {
        return this.c;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setHelp(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    public void setHint(CharSequence charSequence) {
        this.b.setHint(charSequence);
    }

    public void setText(CharSequence charSequence) {
        this.b.setText(charSequence);
    }

    public HwHelpTextLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100350_res_0x7f0602be);
    }

    private void edf_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100348_res_0x7f0602bc, R.attr._2131100349_res_0x7f0602bd, R.attr._2131100351_res_0x7f0602bf, R.attr._2131100524_res_0x7f06036c, R.attr._2131100567_res_0x7f060397, R.attr._2131100606_res_0x7f0603be}, i, R.style.Widget_Emui_HwHelpTextLayout);
        this.e = HwShapeMode.values()[obtainStyledAttributes.getInt(3, 0)];
        this.d = HwWidgetStyle.values()[obtainStyledAttributes.getInt(5, 0)];
        this.f10666a = obtainStyledAttributes.getString(2);
        this.i = obtainStyledAttributes.getString(4);
        this.g = obtainStyledAttributes.getString(0);
        this.f = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
    }

    public HwHelpTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(b(context, i), attributeSet, i);
        edf_(super.getContext(), attributeSet, i);
        HwShapeMode hwShapeMode = this.e;
        if (hwShapeMode == HwShapeMode.BUBBLE) {
            d(R.layout.hwedittext_help_text_layout_bubble, R.layout.hwedittext_help_text_layout_bubble_dark, R.layout.hwedittext_help_text_layout_bubble_translucent);
            return;
        }
        if (hwShapeMode == HwShapeMode.LINEAR) {
            d(R.layout.hwedittext_help_text_layout_linear, R.layout.hwedittext_help_text_layout_linear_dark, R.layout.hwedittext_help_text_layout_linear_translucent);
        } else if (hwShapeMode == HwShapeMode.WHITE) {
            d(R.layout.hwedittext_help_text_layout_bubble_white, R.layout.hwedittext_help_text_layout_bubble_white_dark, R.layout.hwedittext_help_text_layout_bubble_white_translucent);
        } else {
            d(R.layout.hwedittext_help_text_layout_space, R.layout.hwedittext_help_text_layout_space_dark, R.layout.hwedittext_help_text_layout_space_translucent);
        }
    }

    private void d(int i, int i2, int i3) {
        HwWidgetStyle hwWidgetStyle = this.d;
        if (hwWidgetStyle == HwWidgetStyle.LIGHT) {
            c(i);
        } else if (hwWidgetStyle == HwWidgetStyle.DARK) {
            c(i2);
        } else {
            c(i3);
        }
    }

    private void c(int i) {
        LinearLayout.inflate(getContext(), i, this);
        EditText editText = (EditText) findViewById(R.id.hwedittext_edit);
        this.b = editText;
        if (editText != null) {
            editText.setHint(this.f10666a);
            this.b.setText(this.i);
        }
        TextView textView = (TextView) findViewById(R.id.hwedittext_text_assist);
        this.c = textView;
        if (textView != null) {
            textView.setText(this.g);
            TextViewCompat.setTextAppearance(this.c, this.f);
        }
    }
}
