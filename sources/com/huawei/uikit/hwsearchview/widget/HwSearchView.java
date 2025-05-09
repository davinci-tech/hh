package com.huawei.uikit.hwsearchview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.uikit.hwsearchview.R$id;
import defpackage.slc;
import defpackage.smr;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class HwSearchView extends SearchView {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10738a = "HwSearchView";
    private int b;
    private int c;
    private int d;
    private int e;
    private Drawable f;
    private int g;
    private Drawable h;
    private int i;
    private int j;
    private HwSearchAutoComplete k;
    private View n;

    public static class HwSearchAutoComplete extends SearchView.SearchAutoComplete {

        /* renamed from: a, reason: collision with root package name */
        private View f10739a;
        private bzrwd b;
        private View c;
        private View d;
        private int e;
        private int f;
        private int g;
        private TextContextMenuItemListener h;
        private final Drawable i;

        public HwSearchAutoComplete(Context context) {
            super(context);
            this.g = 0;
            this.i = ContextCompat.getDrawable(getContext(), R.drawable._2131429478_res_0x7f0b0866);
        }

        private void a() {
            try {
                TextView.class.getDeclaredMethod("stopTextActionMode", new Class[0]).invoke(this, new Object[0]);
            } catch (IllegalAccessException unused) {
                Log.e(HwSearchView.f10738a, "illegal access stopTextActionMode");
            } catch (NoSuchMethodException unused2) {
                Log.e(HwSearchView.f10738a, "method stopTextActionMode not found");
            } catch (InvocationTargetException unused3) {
                Log.e(HwSearchView.f10738a, "invocation stopTextActionMode error");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCancelButton(View view) {
            this.f10739a = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFocusMode(int i) {
            this.g = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOnDrawableStateChangedListener(bzrwd bzrwdVar) {
            this.b = bzrwdVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSearchPlate(View view) {
            this.d = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTextCursorColor(int i) {
            this.e = i;
            e();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVoiceButton(View view) {
            this.c = view;
            b(0);
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            bzrwd bzrwdVar = this.b;
            if (bzrwdVar != null) {
                bzrwdVar.a(getDrawableState());
            }
        }

        @Override // androidx.appcompat.widget.SearchView.SearchAutoComplete, android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i, Rect rect) {
            if (!z && this.g == 1) {
                Object systemService = getContext().getSystemService("input_method");
                if (systemService instanceof InputMethodManager) {
                    InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                    }
                }
            }
            super.onFocusChanged(z, i, rect);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (this.g == 1) {
                if (i == 21) {
                    if (d()) {
                        return b();
                    }
                    return false;
                }
                if (i == 22) {
                    if (d()) {
                        return false;
                    }
                    return b();
                }
            }
            return super.onKeyDown(i, keyEvent);
        }

        @Override // androidx.appcompat.widget.SearchView.SearchAutoComplete, android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            View view;
            if (this.g == 1) {
                if (i == 111 && (view = this.d) != null) {
                    view.setFocusableInTouchMode(true);
                    this.d.requestFocus();
                    return true;
                }
                if (i == 66) {
                    return true;
                }
            }
            if (i != 4 || keyEvent == null) {
                return false;
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            }
            if (keyEvent.getAction() != 1) {
                return false;
            }
            KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
            if (keyDispatcherState2 != null) {
                keyDispatcherState2.handleUpEvent(keyEvent);
            }
            if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                return false;
            }
            if (isPopupShowing()) {
                dismissDropDown();
            }
            a();
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                return inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
            return false;
        }

        @Override // android.widget.TextView
        protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            super.onTextChanged(charSequence, i, i2, i3);
            e(charSequence);
            b(TextUtils.isEmpty(charSequence) ? this.f : 0);
        }

        @Override // android.widget.EditText, android.widget.TextView
        public boolean onTextContextMenuItem(int i) {
            TextContextMenuItemListener textContextMenuItemListener = this.h;
            if (textContextMenuItemListener == null || !textContextMenuItemListener.onTextContextMenuItemChanged(i)) {
                return super.onTextContextMenuItem(i);
            }
            return true;
        }

        public void setTextContextMenuItemListener(TextContextMenuItemListener textContextMenuItemListener) {
            this.h = textContextMenuItemListener;
        }

        private void e() {
            try {
                Object b = slc.b(this, "mEditor", (Class<?>) TextView.class);
                Class<?> cls = Class.forName("android.widget.Editor");
                if (Build.VERSION.SDK_INT >= 28) {
                    slc.c(b, "updateCursorPosition", null, null, cls);
                    try {
                        Object invoke = TextView.class.getDeclaredMethod("getTextCursorDrawable", new Class[0]).invoke(this, new Object[0]);
                        if (invoke instanceof Drawable) {
                            TextView.class.getDeclaredMethod("setTextCursorDrawable", Drawable.class).invoke(this, egY_((Drawable) invoke, this.e));
                            return;
                        }
                        return;
                    } catch (NoSuchMethodException unused) {
                        Object b2 = slc.b(b, "mDrawableForCursor", cls);
                        if (b2 instanceof Drawable) {
                            slc.e("mDrawableForCursor", b, egY_((Drawable) b2, this.e), cls);
                            return;
                        }
                        return;
                    }
                }
                Object b3 = slc.b(b, "mCursorDrawable", cls);
                if (b3 instanceof Drawable[]) {
                    Drawable[] drawableArr = (Drawable[]) b3;
                    for (int i = 0; i < drawableArr.length; i++) {
                        drawableArr[i] = egY_(drawableArr[i], this.e);
                    }
                    slc.e("mCursorDrawable", b, drawableArr, cls);
                }
            } catch (ClassNotFoundException unused2) {
                Log.e(HwSearchView.f10738a, "class not found");
            } catch (IllegalAccessException unused3) {
                Log.e(HwSearchView.f10738a, "illegal access");
            } catch (InvocationTargetException unused4) {
                Log.e(HwSearchView.f10738a, "invocation error");
            }
        }

        public HwSearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.g = 0;
            this.i = ContextCompat.getDrawable(getContext(), R.drawable._2131429478_res_0x7f0b0866);
        }

        private boolean d() {
            return getLayoutDirection() == 1;
        }

        private boolean b() {
            View view = this.f10739a;
            if (view == null || view.getVisibility() != 0) {
                return false;
            }
            this.f10739a.setFocusableInTouchMode(true);
            this.f10739a.requestFocus();
            return true;
        }

        public HwSearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.g = 0;
            this.i = ContextCompat.getDrawable(getContext(), R.drawable._2131429478_res_0x7f0b0866);
        }

        private void b(int i) {
            View view = this.d;
            if (view == null || i == view.getPaddingEnd()) {
                return;
            }
            View view2 = this.c;
            if (view2 != null && view2.getVisibility() == 0) {
                i = 0;
            }
            View view3 = this.d;
            view3.setPaddingRelative(view3.getPaddingStart(), this.d.getPaddingTop(), i, this.d.getPaddingBottom());
        }

        private void e(CharSequence charSequence) {
            View view = this.c;
            if (view == null) {
                return;
            }
            view.setVisibility(TextUtils.isEmpty(charSequence) ? 0 : 8);
        }

        private Drawable egY_(Drawable drawable, int i) {
            Drawable drawable2;
            if (drawable == null) {
                if (this.e == 0 || (drawable2 = this.i) == null) {
                    return null;
                }
                drawable = drawable2.mutate();
            }
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTint(mutate, i);
            return mutate;
        }
    }

    public interface TextContextMenuItemListener {
        boolean onTextContextMenuItemChanged(int i);
    }

    interface bzrwd {
        void a(int[] iArr);
    }

    class d implements bzrwd {
        d() {
        }

        @Override // com.huawei.uikit.hwsearchview.widget.HwSearchView.bzrwd
        public void a(int[] iArr) {
            Drawable background = HwSearchView.this.n.getBackground();
            if (background != null) {
                background.setState(iArr);
            }
        }
    }

    class e implements View.OnFocusChangeListener {
        e() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (!z && view != null && HwSearchView.this.d == 1) {
                view.setFocusableInTouchMode(false);
            }
            ViewScrollInstrumentation.focusChangeOnView(view, z);
        }
    }

    public HwSearchView(Context context) {
        this(context, null);
    }

    private void setFocusChangeProc(View view) {
        if (view == null) {
            return;
        }
        view.setOnFocusChangeListener(new e());
    }

    private void setSearchButtonTextColorInternal(int i) {
        if (this.e == i) {
            return;
        }
        View findViewById = findViewById(R$id.hwsearchview_search_text_button);
        if (findViewById instanceof TextView) {
            ((TextView) findViewById).setTextColor(i);
            this.e = i;
        }
    }

    protected void e(int i, int i2) {
    }

    public Drawable getCancelButtonDrawable() {
        return this.f;
    }

    public int getFocusMode() {
        return this.d;
    }

    public int getFocusPathColor() {
        return this.b;
    }

    public Drawable getFocusedDrawable() {
        return this.h;
    }

    public int getHintTextColor() {
        return this.i;
    }

    public int getTextColor() {
        return this.j;
    }

    @Override // androidx.appcompat.widget.SearchView, androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i, int i2) {
        if (getOrientation() == 1) {
            b("measureVertical", i, i2);
        } else {
            e(i, i2);
            b("measureHorizontal", i, i2);
        }
    }

    public void setCancelButtonDrawable(Drawable drawable) {
        this.f = drawable;
    }

    public void setFocusMode(int i) {
        this.d = i;
        HwSearchAutoComplete hwSearchAutoComplete = this.k;
        if (hwSearchAutoComplete != null) {
            hwSearchAutoComplete.setFocusMode(i);
        }
    }

    public void setFocusPathColor(int i) {
        this.b = i;
    }

    public void setFocusedDrawable(Drawable drawable) {
        this.h = drawable;
    }

    @Override // android.view.View
    public void setHapticFeedbackEnabled(boolean z) {
        super.setHapticFeedbackEnabled(z);
        HwSearchAutoComplete hwSearchAutoComplete = this.k;
        if (hwSearchAutoComplete != null) {
            hwSearchAutoComplete.setHapticFeedbackEnabled(z);
        }
    }

    public void setHintTextColor(int i) {
        this.i = i;
    }

    public void setSearchButtonTextColor(int i) {
        setSearchButtonTextColorInternal(i);
    }

    public void setTextColor(int i) {
        this.j = i;
    }

    public void setTextCursorColor(int i) {
        if (this.k.e == i) {
            return;
        }
        this.k.setTextCursorColor(i);
    }

    public HwSearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100506_res_0x7f06035a);
    }

    private void d() {
        View findViewById = findViewById(R$id.search_src_text);
        if (findViewById instanceof HwSearchAutoComplete) {
            this.k = (HwSearchAutoComplete) findViewById;
            View findViewById2 = findViewById(R$id.search_plate);
            this.n = findViewById2;
            setFocusChangeProc(findViewById2);
            this.k.setSearchPlate(this.n);
            this.k.setVoiceButton(findViewById(R$id.hwsearchview_voice_button));
            this.k.f = this.g;
            View findViewById3 = findViewById(R$id.search_close_btn);
            this.k.setCancelButton(findViewById3);
            setFocusChangeProc(findViewById3);
            this.k.setTextCursorColor(this.c);
            this.k.setOnDrawableStateChangedListener(new d());
            HwSearchAutoComplete hwSearchAutoComplete = this.k;
            hwSearchAutoComplete.setText(hwSearchAutoComplete.getText());
            this.k.setHapticFeedbackEnabled(isHapticFeedbackEnabled());
            this.k.setFocusMode(this.d);
        }
    }

    public HwSearchView(Context context, AttributeSet attributeSet, int i) {
        super(b(context, i), attributeSet, i);
        this.d = 0;
        egX_(super.getContext(), attributeSet, i);
        d();
    }

    private static Context b(Context context, int i) {
        context.getTheme().applyStyle(R.style.Theme_AppCompat_HwSearchView, false);
        return smr.b(context, i, R.style.Theme_Emui_HwSearchView);
    }

    private void egX_(Context context, AttributeSet attributeSet, int i) {
        int color = ContextCompat.getColor(context, R.color._2131298463_res_0x7f09089f);
        int color2 = ContextCompat.getColor(context, R.color._2131298470_res_0x7f0908a6);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100192_res_0x7f060220, R.attr._2131100197_res_0x7f060225, R.attr._2131100230_res_0x7f060246, R.attr._2131100334_res_0x7f0602ae, R.attr._2131100338_res_0x7f0602b2, R.attr._2131100352_res_0x7f0602c0, R.attr._2131100498_res_0x7f060352, R.attr._2131100499_res_0x7f060353, R.attr._2131100500_res_0x7f060354, R.attr._2131100501_res_0x7f060355, R.attr._2131100502_res_0x7f060356, R.attr._2131100503_res_0x7f060357, R.attr._2131100504_res_0x7f060358, R.attr._2131100505_res_0x7f060359, R.attr._2131100568_res_0x7f060398, R.attr._2131100569_res_0x7f060399}, i, R.style.Widget_Emui_HwSearchView);
        this.c = obtainStyledAttributes.getColor(15, color);
        this.b = obtainStyledAttributes.getColor(4, color2);
        setSearchButtonTextColorInternal(obtainStyledAttributes.getColor(6, 0));
        this.h = obtainStyledAttributes.getDrawable(3);
        this.f = obtainStyledAttributes.getDrawable(2);
        this.i = obtainStyledAttributes.getColor(5, color);
        this.j = obtainStyledAttributes.getColor(14, color);
        this.g = obtainStyledAttributes.getDimensionPixelSize(13, 0);
        obtainStyledAttributes.recycle();
    }

    private void b(String str, int i, int i2) {
        Class cls = Integer.TYPE;
        slc.c(this, str, new Class[]{cls, cls}, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, LinearLayoutCompat.class);
    }
}
