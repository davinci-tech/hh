package com.huawei.uikit.hwedittext.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.health.R;
import com.huawei.uikit.hwedittext.R$drawable;
import com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector;
import defpackage.slc;
import defpackage.smr;
import defpackage.sms;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class HwEditText extends EditText {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10664a = "HwEditText";
    private static final int b = 3;
    private int c;
    private HwKeyEventDetector d;
    private final Drawable e;
    private boolean f;
    private boolean g;
    private Drawable h;
    private TextWatcher i;

    class b implements TextWatcher {
        b() {
        }

        private void b(int i, int i2, int i3) {
            int i4 = i2 + i3;
            int i5 = i3 + i4;
            if (i5 <= i) {
                i = i5;
            }
            if (i > i4) {
                HwEditText.this.getText().replace(i4, i, "");
            }
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence == null || !HwEditText.this.f) {
                return;
            }
            if (i >= charSequence.length() || charSequence.charAt(i) != '\n') {
                int length = charSequence.length();
                Object[] spans = HwEditText.this.getText().getSpans(i, i + i3, UnderlineSpan.class);
                if (i2 == 0) {
                    if (spans == null || spans.length == 0) {
                        b(length, i, i3);
                        return;
                    }
                    return;
                }
                if (spans == null || spans.length == 0) {
                    b(length, i, i3);
                }
            }
        }
    }

    public HwEditText(Context context) {
        this(context, null);
    }

    public static HwEditText instantiate(Context context) {
        Object e = sms.e(context, sms.e(context, (Class<?>) HwEditText.class, sms.c(context, 3, 1)), (Class<?>) HwEditText.class);
        if (e instanceof HwEditText) {
            return (HwEditText) e;
        }
        return null;
    }

    private void setValueFromPlume(Context context) {
        Method b2 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b2 == null) {
            setExtendedEditEnabled(true);
            return;
        }
        Object c = slc.c((Object) null, b2, new Object[]{context, this, "insertEnabled", true});
        if (c instanceof Boolean) {
            setExtendedEditEnabled(((Boolean) c).booleanValue());
        } else {
            setExtendedEditEnabled(true);
        }
    }

    protected HwKeyEventDetector createKeyEventDetector() {
        return new HwKeyEventDetector(getContext());
    }

    public Drawable getFocusedDrawable() {
        return this.h;
    }

    public HwKeyEventDetector.OnSearchEventListener getOnSearchEventListener() {
        HwKeyEventDetector hwKeyEventDetector = this.d;
        if (hwKeyEventDetector != null) {
            return hwKeyEventDetector.d();
        }
        return null;
    }

    public boolean isExtendedEditEnabled() {
        return this.g;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.g) {
            addTextChangedListener(this.i);
        } else {
            removeTextChangedListener(this.i);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (keyEvent == null) {
            return false;
        }
        if (i == 124 && keyEvent.getAction() == 0) {
            this.f = !this.f;
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        HwKeyEventDetector hwKeyEventDetector;
        if (keyEvent == null) {
            return false;
        }
        if (keyEvent == null || (hwKeyEventDetector = this.d) == null || !hwKeyEventDetector.eix_(keyEvent.getKeyCode(), keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    public void setExtendedEditEnabled(boolean z) {
        if (!isAttachedToWindow() || this.i == null) {
            return;
        }
        this.g = z;
        if (isAttachedToWindow()) {
            if (z) {
                addTextChangedListener(this.i);
            } else {
                removeTextChangedListener(this.i);
            }
        }
    }

    public void setFocusedDrawable(Drawable drawable) {
        this.h = drawable;
    }

    public void setOnSearchEventListener(HwKeyEventDetector.OnSearchEventListener onSearchEventListener) {
        if (this.d == null) {
            this.d = createKeyEventDetector();
        }
        HwKeyEventDetector hwKeyEventDetector = this.d;
        if (hwKeyEventDetector != null) {
            hwKeyEventDetector.a(onSearchEventListener);
        }
    }

    public void setTextCursorColor(int i) {
        if (i != this.c) {
            this.c = i;
            a();
        }
    }

    public HwEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100290_res_0x7f060282);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwEditText);
    }

    public HwEditText(Context context, AttributeSet attributeSet, int i) {
        super(a(context, i), attributeSet, i);
        this.d = null;
        this.e = ContextCompat.getDrawable(getContext(), R$drawable.hwedittext_cursor);
        this.f = false;
        this.g = false;
        this.i = new b();
        a(super.getContext(), attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100334_res_0x7f0602ae, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100569_res_0x7f060399}, i, R.style.Widget_Emui_HwEditText);
        int color = obtainStyledAttributes.getColor(2, ContextCompat.getColor(context, R.color._2131298340_res_0x7f090824));
        this.h = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        setTextCursorColor(color);
        setValueFromPlume(context);
    }

    private void a() {
        try {
            Object b2 = slc.b(this, "mEditor", (Class<?>) TextView.class);
            Class<?> cls = Class.forName("android.widget.Editor");
            if (Build.VERSION.SDK_INT >= 28) {
                slc.c(b2, "updateCursorPosition", null, null, cls);
                try {
                    Object invoke = TextView.class.getDeclaredMethod("getTextCursorDrawable", new Class[0]).invoke(this, new Object[0]);
                    if (invoke instanceof Drawable) {
                        TextView.class.getDeclaredMethod("setTextCursorDrawable", Drawable.class).invoke(this, a((Drawable) invoke, this.c));
                        return;
                    }
                    return;
                } catch (NoSuchMethodException unused) {
                    Object b3 = slc.b(b2, "mDrawableForCursor", cls);
                    if (b3 instanceof Drawable) {
                        slc.e("mDrawableForCursor", b2, a((Drawable) b3, this.c), cls);
                        return;
                    }
                    return;
                }
            }
            Object b4 = slc.b(b2, "mCursorDrawable", cls);
            if (b4 instanceof Drawable[]) {
                Drawable[] drawableArr = (Drawable[]) b4;
                for (int i = 0; i < drawableArr.length; i++) {
                    drawableArr[i] = a(drawableArr[i], this.c);
                }
                slc.e("mCursorDrawable", b2, drawableArr, cls);
            }
        } catch (ClassNotFoundException unused2) {
            Log.e(f10664a, "class not found");
        } catch (IllegalAccessException unused3) {
            Log.e(f10664a, "illegal access");
        } catch (InvocationTargetException unused4) {
            Log.e(f10664a, "invocation error");
        }
    }

    private Drawable a(Drawable drawable, int i) {
        Drawable drawable2;
        if (drawable == null) {
            if (this.c == 0 || (drawable2 = this.e) == null) {
                return drawable;
            }
            drawable = drawable2.mutate();
        }
        Drawable mutate = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(mutate, i);
        return mutate;
    }
}
