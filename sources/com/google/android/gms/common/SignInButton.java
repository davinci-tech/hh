package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.SignInButtonCreator;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.dynamic.RemoteCreator;
import com.huawei.health.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes8.dex */
public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zaas;
    private View.OnClickListener zaat;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, null);
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zaat = null;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099817_res_0x7f0600a9, R.attr._2131099921_res_0x7f060111, R.attr._2131101069_res_0x7f06058d}, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(0, 0);
            this.mColor = obtainStyledAttributes.getInt(1, 2);
            obtainStyledAttributes.recycle();
            setStyle(this.mSize, this.mColor);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void setSize(int i) {
        setStyle(i, this.mColor);
    }

    public final void setColorScheme(int i) {
        setStyle(this.mSize, i);
    }

    @Deprecated
    public final void setScopes(Scope[] scopeArr) {
        setStyle(this.mSize, this.mColor);
    }

    public final void setStyle(int i, int i2) {
        this.mSize = i;
        this.mColor = i2;
        Context context = getContext();
        View view = this.zaas;
        if (view != null) {
            removeView(view);
        }
        try {
            this.zaas = SignInButtonCreator.createView(context, this.mSize, this.mColor);
        } catch (RemoteCreator.RemoteCreatorException unused) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            int i3 = this.mSize;
            int i4 = this.mColor;
            SignInButtonImpl signInButtonImpl = new SignInButtonImpl(context);
            signInButtonImpl.configure(context.getResources(), i3, i4);
            this.zaas = signInButtonImpl;
        }
        addView(this.zaas);
        this.zaas.setEnabled(isEnabled());
        this.zaas.setOnClickListener(this);
    }

    @Deprecated
    public final void setStyle(int i, int i2, Scope[] scopeArr) {
        setStyle(i, i2);
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zaat = onClickListener;
        View view = this.zaas;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.zaas.setEnabled(z);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View.OnClickListener onClickListener = this.zaat;
        if (onClickListener == null || view != this.zaas) {
            return;
        }
        onClickListener.onClick(this);
    }
}
