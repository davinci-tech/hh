package com.huawei.uikit.phone.hwedittext.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwKeyEventDetector;

/* loaded from: classes7.dex */
public class HwEditText extends com.huawei.uikit.hwedittext.widget.HwEditText {
    private static final String TAG = "HwEditText";

    /* renamed from: a, reason: collision with root package name */
    private static final String f10786a = "ViewState";
    private static final String b = "CursorState";
    private static final String c = "InstanceState";
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 746;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;

    public HwEditText(Context context) {
        super(context);
        this.l = false;
        this.m = 0;
    }

    private boolean a(int i2) {
        return i2 == 57 || i2 == 58;
    }

    private boolean a(boolean z, boolean z2) {
        return (this.j && this.k) != (z && z2);
    }

    private boolean b(int i2) {
        return i2 == 23 || i2 == 62 || i2 == 66 || i2 == 160;
    }

    private boolean c(int i2) {
        switch (i2) {
            case 19:
            case 20:
            case 21:
            case 22:
                return true;
            default:
                return false;
        }
    }

    private boolean d(int i2) {
        return i2 == 59 || i2 == 60;
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent == null) {
            return false;
        }
        if (this.m == 1 && c(keyEvent.getKeyCode())) {
            return false;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwEditText, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.j = hasFocus();
        boolean hasWindowFocus = hasWindowFocus();
        this.k = hasWindowFocus;
        this.l = this.j && hasWindowFocus;
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        if (this.m == 1 && this.l) {
            a(canvas);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (a(z, this.k)) {
            this.l = z;
        }
        this.j = z;
        if (z && this.m == 0) {
            if (isInTouchMode()) {
                this.m = 3;
                setCursorVisible(true);
            } else {
                this.m = 1;
                setCursorVisible(false);
            }
        }
        if (z) {
            return;
        }
        if (this.m != 3) {
            a();
        }
        this.m = 0;
    }

    @Override // com.huawei.uikit.hwedittext.widget.HwEditText, android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
        if (keyEvent == null) {
            return false;
        }
        if (i2 == 4 || i2 == 3 || i2 == 124) {
            return super.onKeyPreIme(i2, keyEvent);
        }
        if (this.m == 3 && i2 != i) {
            this.m = 2;
            a();
        }
        if (this.m != 1) {
            a(i2, keyEvent);
            if (this.m != 2 || i2 != 111) {
                return super.onKeyPreIme(i2, keyEvent);
            }
            this.m = 1;
            setCursorVisible(false);
            return true;
        }
        if (c(i2) || i2 == 111) {
            return false;
        }
        if (i2 == 61 && keyEvent.getAction() == 0) {
            a(keyEvent);
        }
        if (b(i2) && keyEvent.getAction() == 1) {
            this.m = 2;
            setCursorVisible(true);
        }
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        try {
            this.m = bundle.getInt(f10786a);
            setCursorVisible(bundle.getBoolean(b));
            Parcelable parcelable2 = bundle.getParcelable(c);
            if (parcelable2 != null) {
                super.onRestoreInstanceState(parcelable2);
            }
        } catch (BadParcelableException unused) {
            Log.e(TAG, "Parcelable, onRestoreInstanceState error");
        }
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        try {
            bundle.putParcelable(c, super.onSaveInstanceState());
            bundle.putInt(f10786a, this.m);
            bundle.putBoolean(b, isCursorVisible());
        } catch (BadParcelableException unused) {
            Log.e(TAG, "Parcelable, onSaveInstanceState error");
        }
        return bundle;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            setCursorVisible(true);
            this.m = 3;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (a(this.j, z)) {
            this.l = z;
            if (getFocusedDrawable() != null) {
                invalidate();
            }
        }
        this.k = z;
    }

    public void requestFocusInTouchMode() {
        this.m = 3;
        requestFocus();
    }

    private void a(Canvas canvas) {
        Drawable focusedDrawable = getFocusedDrawable();
        if (focusedDrawable != null) {
            canvas.translate(getScrollX(), getScrollY());
            focusedDrawable.setBounds(0, 0, getWidth(), getHeight());
            focusedDrawable.draw(canvas);
            canvas.translate(-getScrollX(), -getScrollY());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwedittext.widget.HwEditText
    public HwKeyEventDetector createKeyEventDetector() {
        return new HwKeyEventDetector(getContext());
    }

    public HwEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = false;
        this.m = 0;
    }

    public HwEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.l = false;
        this.m = 0;
    }

    private void a(int i2, KeyEvent keyEvent) {
        if (this.m == 4 && keyEvent.getAction() == 1 && a(i2)) {
            this.m = 3;
        } else {
            if (!d(i2) || (keyEvent.getMetaState() & 2) == 0) {
                return;
            }
            this.m = 4;
        }
    }

    private void a() {
        Object systemService = getContext().getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
    }

    private void a(KeyEvent keyEvent) {
        View view;
        boolean z = (keyEvent.getMetaState() & 1) != 0;
        FocusFinder focusFinder = FocusFinder.getInstance();
        View rootView = getRootView();
        if (!(rootView instanceof ViewGroup)) {
            view = null;
        } else if (z) {
            view = focusFinder.findNextFocus((ViewGroup) rootView, this, 1);
        } else {
            view = focusFinder.findNextFocus((ViewGroup) rootView, this, 2);
        }
        if (view != null) {
            view.requestFocus();
        }
    }
}
