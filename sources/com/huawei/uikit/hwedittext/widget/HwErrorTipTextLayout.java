package com.huawei.uikit.hwedittext.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.TextViewCompat;
import com.huawei.health.R;
import com.huawei.uikit.hwedittext.R$dimen;
import defpackage.smr;
import defpackage.sms;

/* loaded from: classes7.dex */
public class HwErrorTipTextLayout extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10665a = "HwErrorTipTextLayout";
    private static final int b = 3;
    private static final int c = 200;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private HwShapeMode h;
    private int i;
    private int j;
    private int k;
    private int l;
    private LinearLayout m;
    protected EditText mEditText;
    protected int mErrorTextAppearance;
    protected TextView mErrorView;

    class c extends AnimatorListenerAdapter {
        final /* synthetic */ boolean e;

        c(boolean z) {
            this.e = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            HwErrorTipTextLayout.this.setBackground(this.e);
            HwErrorTipTextLayout.this.mErrorView.setAlpha(this.e ? 1.0f : 0.0f);
            HwErrorTipTextLayout.this.mErrorView.setVisibility(this.e ? 0 : 8);
        }
    }

    /* loaded from: classes9.dex */
    class e extends View.AccessibilityDelegate {
        private e() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (view == null || accessibilityEvent == null) {
                return;
            }
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(HwErrorTipTextLayout.f10665a);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        /* synthetic */ e(HwErrorTipTextLayout hwErrorTipTextLayout, c cVar) {
            this();
        }
    }

    public HwErrorTipTextLayout(Context context) {
        this(context, null);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwEditText);
    }

    public static HwErrorTipTextLayout instantiate(Context context) {
        Object e2 = sms.e(context, sms.e(context, (Class<?>) HwErrorTipTextLayout.class, sms.c(context, 3, 1)), (Class<?>) HwErrorTipTextLayout.class);
        if (e2 instanceof HwErrorTipTextLayout) {
            return (HwErrorTipTextLayout) e2;
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i, layoutParams);
        } else {
            setEditText((EditText) view);
            super.addView(view, 0, updateEditTextMargin(layoutParams));
        }
    }

    public EditText getEditText() {
        return this.mEditText;
    }

    public CharSequence getError() {
        TextView textView = this.mErrorView;
        if (textView != null) {
            return textView.getText();
        }
        return null;
    }

    public CharSequence getHint() {
        EditText editText = this.mEditText;
        if (editText == null) {
            return null;
        }
        return editText.getHint();
    }

    protected void initErrorView() {
        TextView textView = new TextView(getContext());
        this.mErrorView = textView;
        textView.setVisibility(8);
        this.mErrorView.setPaddingRelative(this.mEditText.getPaddingLeft(), getResources().getDimensionPixelSize(R$dimen.hwedittext_dimens_text_margin_fifth), this.mEditText.getPaddingRight(), 0);
        TextViewCompat.setTextAppearance(this.mErrorView, this.mErrorTextAppearance);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, this.mEditText.getId());
        this.mErrorView.setLayoutParams(layoutParams);
        addView(this.mErrorView);
    }

    public boolean isErrorEnabled() {
        return this.d;
    }

    protected void setBackground(boolean z) {
        EditText editText = this.mEditText;
        if (editText == null || this.mErrorView == null) {
            return;
        }
        HwShapeMode hwShapeMode = this.h;
        if (hwShapeMode == HwShapeMode.BUBBLE) {
            editText.setBackgroundResource(z ? this.e : this.i);
            return;
        }
        if (hwShapeMode == HwShapeMode.LINEAR) {
            editText.setBackgroundResource(z ? this.g : this.k);
        } else if (hwShapeMode == HwShapeMode.WHITE) {
            editText.setBackgroundResource(z ? this.f : this.j);
        } else {
            editText.setBackgroundResource(z ? this.g : this.l);
        }
    }

    protected void setEditText(EditText editText) {
        if (this.mEditText != null) {
            return;
        }
        this.mEditText = editText;
        this.mEditText.setImeOptions(editText.getImeOptions() | 33554432);
        HwShapeMode hwShapeMode = this.h;
        if (hwShapeMode == HwShapeMode.BUBBLE) {
            this.mEditText.setBackgroundResource(this.i);
        } else if (hwShapeMode == HwShapeMode.LINEAR) {
            this.mEditText.setBackgroundResource(this.k);
        } else if (hwShapeMode == HwShapeMode.WHITE) {
            this.mEditText.setBackgroundResource(this.j);
        } else {
            this.mEditText.setBackgroundResource(this.l);
        }
        initErrorView();
    }

    public void setError(CharSequence charSequence) {
        if (this.mEditText == null || this.mErrorView == null || !this.d) {
            return;
        }
        boolean z = !TextUtils.isEmpty(charSequence);
        this.mErrorView.setText(charSequence);
        this.mErrorView.animate().setInterpolator(new LinearInterpolator()).setDuration(200L).alpha(z ? 1.0f : 0.0f).setListener(new c(z)).start();
        sendAccessibilityEvent(2048);
    }

    public void setErrorEnabled(boolean z) {
        TextView textView;
        if (z == this.d || (textView = this.mErrorView) == null) {
            return;
        }
        textView.setAlpha(z ? 1.0f : 0.0f);
        this.mErrorView.setVisibility(z ? 0 : 8);
        this.d = z;
    }

    public void setHint(CharSequence charSequence) {
        EditText editText = this.mEditText;
        if (editText == null) {
            return;
        }
        editText.setHint(charSequence);
        sendAccessibilityEvent(2048);
    }

    protected ViewGroup.LayoutParams updateEditTextMargin(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null ? new RelativeLayout.LayoutParams(layoutParams) : generateDefaultLayoutParams();
    }

    public HwErrorTipTextLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100296_res_0x7f060288);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        setAddStatesFromChildren(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100288_res_0x7f060280, R.attr._2131100289_res_0x7f060281, R.attr._2131100291_res_0x7f060283, R.attr._2131100292_res_0x7f060284, R.attr._2131100293_res_0x7f060285, R.attr._2131100294_res_0x7f060286, R.attr._2131100295_res_0x7f060287, R.attr._2131100396_res_0x7f0602ec, R.attr._2131100524_res_0x7f06036c, R.attr._2131100533_res_0x7f060375}, i, R.style.Widget_Emui_HwErrorTipTextLayout);
        this.i = obtainStyledAttributes.getResourceId(0, 0);
        this.j = obtainStyledAttributes.getResourceId(1, 0);
        this.k = obtainStyledAttributes.getResourceId(7, 0);
        this.l = obtainStyledAttributes.getResourceId(9, 0);
        this.e = obtainStyledAttributes.getResourceId(4, 0);
        this.f = obtainStyledAttributes.getResourceId(5, 0);
        this.mErrorTextAppearance = obtainStyledAttributes.getResourceId(6, 0);
        this.g = obtainStyledAttributes.getResourceId(3, 0);
        this.d = obtainStyledAttributes.getBoolean(2, true);
        this.h = HwShapeMode.values()[obtainStyledAttributes.getInt(8, 0)];
        obtainStyledAttributes.recycle();
        setAccessibilityDelegate(new e(this, null));
    }

    public HwErrorTipTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(a(context, i), attributeSet, i);
        a(super.getContext(), attributeSet, i);
    }
}
