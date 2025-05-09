package com.huawei.uikit.hwedittext.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.huawei.health.R;
import com.huawei.uikit.hwedittext.R$dimen;
import defpackage.smr;

/* loaded from: classes9.dex */
public class HwCounterTextLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private EditText f10660a;
    private HwShapeMode b;
    private int c;
    private TextView d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private c l;
    private int m;
    private Animation n;
    private int o;
    private a t;

    class a implements Animation.AnimationListener {

        /* renamed from: a, reason: collision with root package name */
        private boolean f10661a;

        private a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            TextViewCompat.setTextAppearance(HwCounterTextLayout.this.d, HwCounterTextLayout.this.e);
            HwCounterTextLayout.this.setBackground(false);
            this.f10661a = false;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            HwCounterTextLayout.this.d.setTextColor(HwCounterTextLayout.this.c);
            HwCounterTextLayout.this.setBackground(this.f10661a);
        }

        /* synthetic */ a(HwCounterTextLayout hwCounterTextLayout, com.huawei.uikit.hwedittext.widget.a aVar) {
            this();
        }
    }

    class b extends View.AccessibilityDelegate {
        private b() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (view == null || accessibilityEvent == null) {
                return;
            }
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName("HwCounterTextLayout");
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            if (view == null || accessibilityNodeInfo == null) {
                return;
            }
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("HwCounterTextLayout");
            if (HwCounterTextLayout.this.f10660a != null) {
                accessibilityNodeInfo.setLabelFor(HwCounterTextLayout.this.f10660a);
            }
            CharSequence error = HwCounterTextLayout.this.getError();
            if (TextUtils.isEmpty(error)) {
                return;
            }
            AccessibilityNodeInfoCompat.obtain().setContentInvalid(true);
            AccessibilityNodeInfoCompat.obtain().setError(error);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        /* synthetic */ b(HwCounterTextLayout hwCounterTextLayout, com.huawei.uikit.hwedittext.widget.a aVar) {
            this();
        }
    }

    class c implements TextWatcher {
        private c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            HwCounterTextLayout.this.d(charSequence);
        }

        /* synthetic */ c(HwCounterTextLayout hwCounterTextLayout, com.huawei.uikit.hwedittext.widget.a aVar) {
            this();
        }
    }

    public HwCounterTextLayout(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackground(boolean z) {
        HwShapeMode hwShapeMode = this.b;
        if (hwShapeMode == HwShapeMode.BUBBLE) {
            this.f10660a.setBackgroundResource(z ? this.h : this.f);
            c();
        } else if (hwShapeMode == HwShapeMode.LINEAR) {
            this.f10660a.setBackgroundResource(z ? this.o : this.m);
        } else if (hwShapeMode != HwShapeMode.WHITE) {
            this.f10660a.setBackgroundResource(z ? this.o : this.k);
        } else {
            this.f10660a.setBackgroundResource(z ? this.j : this.i);
            c();
        }
    }

    private void setEditText(EditText editText) {
        if (this.f10660a != null) {
            return;
        }
        this.f10660a = editText;
        this.f10660a.setImeOptions(editText.getImeOptions() | 33554432);
        HwShapeMode hwShapeMode = this.b;
        if (hwShapeMode == HwShapeMode.BUBBLE) {
            this.f10660a.setBackgroundResource(this.f);
            return;
        }
        if (hwShapeMode == HwShapeMode.LINEAR) {
            this.f10660a.setBackgroundResource(this.m);
        } else if (hwShapeMode == HwShapeMode.WHITE) {
            this.f10660a.setBackgroundResource(this.i);
        } else {
            this.f10660a.setBackgroundResource(this.k);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText) || layoutParams == null) {
            super.addView(view, i, layoutParams);
            return;
        }
        setEditText((EditText) view);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(view, 0, layoutParams2);
        super.addView(frameLayout, 0, layoutParams2);
        d();
    }

    public EditText getEditText() {
        return this.f10660a;
    }

    public CharSequence getError() {
        TextView textView = this.d;
        if (textView != null) {
            return textView.getText();
        }
        return null;
    }

    public CharSequence getHint() {
        EditText editText = this.f10660a;
        if (editText == null) {
            return null;
        }
        return editText.getHint();
    }

    public int getMaxLength() {
        return this.g;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f10660a == null) {
            return;
        }
        HwShapeMode hwShapeMode = this.b;
        if ((hwShapeMode == HwShapeMode.BUBBLE || hwShapeMode == HwShapeMode.WHITE) && (this.f10660a.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) this.f10660a.getParent();
            this.f10660a.layout(0, 0, viewGroup.getWidth(), viewGroup.getHeight());
        }
        if (this.l == null) {
            c cVar = new c(this, null);
            this.l = cVar;
            this.f10660a.addTextChangedListener(cVar);
            EditText editText = this.f10660a;
            editText.setText(editText.getText());
        }
    }

    public void setError(CharSequence charSequence) {
        if (this.f10660a == null || this.d == null) {
            return;
        }
        boolean z = !TextUtils.isEmpty(charSequence);
        this.d.setText(charSequence);
        this.d.setVisibility(z ? 0 : 8);
        this.d.animate().setInterpolator(new LinearInterpolator()).setDuration(50L).alpha(z ? 1.0f : 0.0f).start();
        HwShapeMode hwShapeMode = this.b;
        if (hwShapeMode == HwShapeMode.BUBBLE || hwShapeMode == HwShapeMode.WHITE) {
            c();
        }
        sendAccessibilityEvent(2048);
    }

    public void setHint(CharSequence charSequence) {
        EditText editText = this.f10660a;
        if (editText == null) {
            return;
        }
        editText.setHint(charSequence);
        sendAccessibilityEvent(2048);
    }

    public void setMaxLength(int i) {
        this.g = i;
    }

    public HwCounterTextLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100269_res_0x7f06026d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            int i = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            if (this.d.getVisibility() == 0) {
                i += this.d.getHeight() + getResources().getDimensionPixelSize(R$dimen.hwedittext_dimens_text_margin_fifth);
            }
            EditText editText = this.f10660a;
            editText.setPaddingRelative(editText.getPaddingStart(), this.f10660a.getPaddingTop(), this.f10660a.getPaddingEnd(), i);
        }
    }

    public HwCounterTextLayout(Context context, AttributeSet attributeSet, int i) {
        super(c(context, i), attributeSet, i);
        a(super.getContext(), attributeSet, i);
    }

    private static Context c(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwEditText);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        int i2;
        setOrientation(1);
        try {
            i2 = ContextCompat.getColor(context, R.color._2131298344_res_0x7f090828);
        } catch (Resources.NotFoundException unused) {
            Log.d("HwCounterTextLayout", "initCounterTextErrorColor: resource error color not found");
            i2 = 0;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100267_res_0x7f06026b, R.attr._2131100268_res_0x7f06026c, R.attr._2131100288_res_0x7f060280, R.attr._2131100289_res_0x7f060281, R.attr._2131100292_res_0x7f060284, R.attr._2131100293_res_0x7f060285, R.attr._2131100294_res_0x7f060286, R.attr._2131100396_res_0x7f0602ec, R.attr._2131100406_res_0x7f0602f6, R.attr._2131100524_res_0x7f06036c, R.attr._2131100533_res_0x7f060375}, i, R.style.Widget_Emui_HwCounterTextLayout);
        this.e = obtainStyledAttributes.getResourceId(0, 0);
        this.c = obtainStyledAttributes.getColor(1, i2);
        this.b = HwShapeMode.values()[obtainStyledAttributes.getInt(9, 0)];
        this.m = obtainStyledAttributes.getResourceId(7, 0);
        this.k = obtainStyledAttributes.getResourceId(10, 0);
        this.h = obtainStyledAttributes.getResourceId(5, 0);
        this.j = obtainStyledAttributes.getResourceId(6, 0);
        this.f = obtainStyledAttributes.getResourceId(2, 0);
        this.i = obtainStyledAttributes.getResourceId(3, 0);
        this.g = obtainStyledAttributes.getInteger(8, -1);
        this.o = obtainStyledAttributes.getResourceId(4, 0);
        obtainStyledAttributes.recycle();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        setAccessibilityDelegate(new b(this, null));
        Resources resources = getResources();
        setPaddingRelative(resources.getDimensionPixelSize(R$dimen.hwedittext_dimens_max_start), 0, resources.getDimensionPixelSize(R$dimen.hwedittext_dimens_max_end), 0);
    }

    private void d() {
        com.huawei.uikit.hwedittext.widget.a aVar = new com.huawei.uikit.hwedittext.widget.a(this, getContext());
        this.d = aVar;
        TextViewCompat.setTextAppearance(aVar, this.e);
        this.d.setGravity(17);
        this.d.setTextDirection(5);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        HwShapeMode hwShapeMode = this.b;
        if (hwShapeMode != HwShapeMode.BUBBLE && hwShapeMode != HwShapeMode.WHITE) {
            layoutParams.gravity = 8388693;
            this.d.setPaddingRelative(0, getResources().getDimensionPixelSize(R$dimen.hwedittext_dimens_text_margin_fifth), 0, 0);
            addView(this.d, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        layoutParams2.gravity = 8388693;
        layoutParams2.setMarginEnd(this.f10660a.getPaddingEnd());
        layoutParams2.bottomMargin = this.f10660a.getPaddingBottom();
        ViewParent parent = this.f10660a.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).addView(this.d, layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(CharSequence charSequence) {
        com.huawei.uikit.hwedittext.widget.a aVar = null;
        if (this.g == -1) {
            setError(null);
            return;
        }
        if (charSequence instanceof Editable) {
            Editable editable = (Editable) charSequence;
            int length = editable.length();
            int i = this.g;
            if (length <= i) {
                if (length >= i * 0.9f) {
                    setError(length + " / " + this.g);
                    return;
                }
                setError(null);
                return;
            }
            int selectionEnd = this.f10660a.getSelectionEnd();
            editable.delete(this.g, editable.length());
            EditText editText = this.f10660a;
            int i2 = this.g;
            if (selectionEnd > i2) {
                selectionEnd = i2;
            }
            editText.setSelection(selectionEnd);
            if (this.n == null) {
                this.n = AnimationUtils.loadAnimation(getContext(), R.anim._2130772045_res_0x7f01004d);
                a aVar2 = new a(this, aVar);
                this.t = aVar2;
                Animation animation = this.n;
                if (animation != null) {
                    animation.setAnimationListener(aVar2);
                }
            }
            this.t.f10661a = true;
            this.f10660a.startAnimation(this.n);
        }
    }
}
