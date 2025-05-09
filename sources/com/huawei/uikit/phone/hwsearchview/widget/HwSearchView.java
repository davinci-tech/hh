package com.huawei.uikit.phone.hwsearchview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SearchView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwsearchview.R$id;
import com.huawei.uikit.hwsearchview.widget.HwSearchView;
import defpackage.slc;

/* loaded from: classes7.dex */
public class HwSearchView extends com.huawei.uikit.hwsearchview.widget.HwSearchView {

    /* renamed from: a, reason: collision with root package name */
    private AppCompatImageView f10788a;
    private final View.OnClickListener b;
    private int c;
    private Drawable d;
    private Drawable e;
    private final View.OnClickListener f;
    private View g;
    private HwPhoneSearchAutoComplete h;
    private int i;
    private View j;

    /* loaded from: classes9.dex */
    public static class HwPhoneSearchAutoComplete extends HwSearchView.HwSearchAutoComplete {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f10789a;
        private boolean b;
        private View c;
        private boolean d;
        private boolean e;
        private View f;
        private int i;

        public HwPhoneSearchAutoComplete(Context context) {
            super(context);
            this.e = false;
            this.i = 0;
        }

        private boolean a(int i) {
            switch (i) {
                case 19:
                case 20:
                case 21:
                case 22:
                    return true;
                default:
                    return false;
            }
        }

        private boolean b(int i) {
            return i == 57 || i == 58;
        }

        private void c(CharSequence charSequence) {
            View view = this.f;
            if (view == null) {
                return;
            }
            view.setVisibility(TextUtils.isEmpty(charSequence) ? 0 : 8);
        }

        private boolean c(int i) {
            return i == 23 || i == 62 || i == 66 || i == 160;
        }

        private boolean d(int i) {
            return i == 59 || i == 60;
        }

        private void e(int i) {
            View view = this.c;
            if (view == null || i == view.getPaddingEnd()) {
                return;
            }
            View view2 = this.f;
            if (view2 != null && view2.getVisibility() == 0) {
                i = 0;
            }
            View view3 = this.c;
            view3.setPaddingRelative(view3.getPaddingStart(), this.c.getPaddingTop(), i, this.c.getPaddingBottom());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSearchPlate(View view) {
            this.c = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVoiceButton(View view) {
            this.f = view;
            e(0);
        }

        @Override // android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (keyEvent == null) {
                return false;
            }
            if (this.i == 1 && a(keyEvent.getKeyCode())) {
                return false;
            }
            return super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.d = hasFocus();
            boolean hasWindowFocus = hasWindowFocus();
            this.b = hasWindowFocus;
            this.e = this.d && hasWindowFocus;
        }

        @Override // android.widget.TextView, android.view.View
        protected void onDraw(Canvas canvas) {
            if (canvas == null) {
                return;
            }
            super.onDraw(canvas);
            if (this.i == 1 && this.e) {
                a(canvas);
            }
        }

        @Override // com.huawei.uikit.hwsearchview.widget.HwSearchView.HwSearchAutoComplete, androidx.appcompat.widget.SearchView.SearchAutoComplete, android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            if (a(z, this.b)) {
                this.e = z;
            }
            this.d = z;
            if (z && this.i == 0) {
                if (isInTouchMode()) {
                    this.i = 3;
                    setCursorVisible(true);
                } else {
                    this.i = 1;
                    setCursorVisible(false);
                }
            }
            if (z) {
                return;
            }
            if (this.i != 3) {
                b();
            }
            this.i = 0;
        }

        @Override // com.huawei.uikit.hwsearchview.widget.HwSearchView.HwSearchAutoComplete, androidx.appcompat.widget.SearchView.SearchAutoComplete, android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (keyEvent == null) {
                return false;
            }
            if (i == 4 || i == 3) {
                return super.onKeyPreIme(i, keyEvent);
            }
            if (this.i == 3 && i != 746) {
                this.i = 2;
                b();
            }
            if (this.i != 1) {
                ejq_(i, keyEvent);
                if (this.i != 2 || i != 111) {
                    return super.onKeyPreIme(i, keyEvent);
                }
                this.i = 1;
                setCursorVisible(false);
                return true;
            }
            if (a(i) || i == 111) {
                return false;
            }
            if (i == 61 && keyEvent.getAction() == 0) {
                ejr_(keyEvent);
            }
            if (c(i) && keyEvent.getAction() == 1) {
                this.i = 2;
                setCursorVisible(true);
            }
            return true;
        }

        @Override // android.widget.TextView, android.view.View
        public void onRestoreInstanceState(Parcelable parcelable) {
            Parcelable parcelable2;
            if (!(parcelable instanceof Bundle)) {
                super.onRestoreInstanceState(parcelable);
                return;
            }
            Bundle bundle = (Bundle) parcelable;
            try {
                if (bundle.containsKey("ViewState")) {
                    this.i = bundle.getInt("ViewState");
                }
                if (bundle.containsKey("CursorState")) {
                    setCursorVisible(bundle.getBoolean("CursorState"));
                }
                if (bundle.containsKey("InstanceState") && (parcelable2 = bundle.getParcelable("InstanceState")) != null) {
                    super.onRestoreInstanceState(parcelable2);
                }
            } catch (BadParcelableException unused) {
                Log.e("HwSearchView", "Parcelable, onRestoreInstanceState error");
            }
        }

        @Override // android.widget.TextView, android.view.View
        public Parcelable onSaveInstanceState() {
            Bundle bundle = new Bundle();
            try {
                bundle.putParcelable("InstanceState", super.onSaveInstanceState());
                bundle.putInt("ViewState", this.i);
                bundle.putBoolean("CursorState", isCursorVisible());
            } catch (BadParcelableException unused) {
                Log.e("HwSearchView", "Parcelable, onSaveInstanceState error");
            }
            return bundle;
        }

        @Override // com.huawei.uikit.hwsearchview.widget.HwSearchView.HwSearchAutoComplete, android.widget.TextView
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            c(charSequence);
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent == null) {
                return false;
            }
            if (motionEvent.getAction() == 0) {
                setCursorVisible(true);
                this.i = 3;
            }
            return super.onTouchEvent(motionEvent);
        }

        @Override // androidx.appcompat.widget.SearchView.SearchAutoComplete, android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (a(this.d, z)) {
                this.e = z;
                if (this.f10789a != null) {
                    invalidate();
                }
            }
            this.b = z;
        }

        public HwPhoneSearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = false;
            this.i = 0;
        }

        private boolean a(boolean z, boolean z2) {
            return (this.d && this.b) != (z && z2);
        }

        private void a(Canvas canvas) {
            if (this.f10789a == null) {
                return;
            }
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) parent;
                Rect rect = new Rect(0, 0, viewGroup.getWidth(), viewGroup.getHeight());
                rect.offset(getScrollX() - getLeft(), getScrollY() - getTop());
                this.f10789a.setBounds(rect);
                this.f10789a.draw(canvas);
            }
        }

        public HwPhoneSearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.e = false;
            this.i = 0;
        }

        private void ejq_(int i, KeyEvent keyEvent) {
            if (this.i == 4 && keyEvent.getAction() == 1 && b(i)) {
                this.i = 3;
            } else {
                if (!d(i) || (keyEvent.getMetaState() & 2) == 0) {
                    return;
                }
                this.i = 4;
            }
        }

        private void b() {
            if (Build.VERSION.SDK_INT > 29) {
                return;
            }
            Object systemService = getContext().getSystemService("input_method");
            if (systemService instanceof InputMethodManager) {
                InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                }
            }
        }

        private void ejr_(KeyEvent keyEvent) {
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

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!TextUtils.isEmpty(HwSearchView.this.h.getText())) {
                HwSearchView.this.h.setText("");
                HwSearchView.this.h.requestFocus();
            } else if (HwSearchView.this.b != null) {
                HwSearchView.this.b.onClick(view);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public HwSearchView(Context context) {
        this(context, null);
    }

    private void d() {
        this.e = getFocusedDrawable();
        this.d = getCancelButtonDrawable();
        this.c = getHintTextColor();
        this.i = getTextColor();
        View findViewById = findViewById(R$id.search_src_text);
        if (findViewById instanceof HwPhoneSearchAutoComplete) {
            this.h = (HwPhoneSearchAutoComplete) findViewById;
        }
        AppCompatImageView appCompatImageView = (AppCompatImageView) findViewById(R$id.search_close_btn);
        this.f10788a = appCompatImageView;
        if (appCompatImageView != null) {
            appCompatImageView.setBackground(this.d);
            this.f10788a.setOnClickListener(this.f);
        }
        this.j = findViewById(R$id.search_plate);
        this.g = findViewById(R$id.hwsearchview_voice_button);
        HwPhoneSearchAutoComplete hwPhoneSearchAutoComplete = this.h;
        if (hwPhoneSearchAutoComplete != null) {
            hwPhoneSearchAutoComplete.f10789a = this.e;
            this.h.setHintTextColor(this.c);
            this.h.setTextColor(this.i);
            this.h.setSearchPlate(this.j);
            this.h.setVoiceButton(this.g);
            if (Float.compare(getResources().getConfiguration().fontScale, 1.75f) >= 0) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131364329_res_0x7f0a09e9);
                HwPhoneSearchAutoComplete hwPhoneSearchAutoComplete2 = this.h;
                hwPhoneSearchAutoComplete2.setPaddingRelative(hwPhoneSearchAutoComplete2.getPaddingStart(), dimensionPixelSize, this.h.getPaddingEnd(), dimensionPixelSize);
            }
        }
    }

    private boolean ejo_(View view) {
        return (view == null || view.getVisibility() == 8) ? false : true;
    }

    @Override // com.huawei.uikit.hwsearchview.widget.HwSearchView
    public void e(int i, int i2) {
        int i3;
        TextView textView = (TextView) findViewById(R$id.hwsearchview_start_title);
        if (ejo_(textView)) {
            int size = View.MeasureSpec.getSize(i);
            View findViewById = findViewById(R$id.search_plate);
            if (!ejo_(findViewById) || findViewById.getLayoutParams() == null) {
                i3 = 0;
            } else {
                int i4 = findViewById.getLayoutParams().width;
                if (i4 <= 0) {
                    i4 = findViewById.getMinimumWidth();
                }
                i3 = i4 + ejm_(findViewById);
            }
            int ejk_ = ((size - i3) - ejk_(findViewById(R$id.hwsearchview_barcode_button))) - ejk_(findViewById(R$id.hwsearchview_intelligent_button));
            View findViewById2 = findViewById(R$id.hwsearchview_search_bar);
            if (ejo_(findViewById2)) {
                ejk_ -= findViewById2.getPaddingEnd() + findViewById2.getPaddingStart();
            }
            int ejm_ = ejk_ - ejm_(textView);
            if (textView.getMaxWidth() == ejm_ || ejm_ <= 0) {
                return;
            }
            textView.setMaxWidth(ejm_);
        }
    }

    @Override // com.huawei.uikit.hwsearchview.widget.HwSearchView
    public void setFocusMode(int i) {
    }

    public HwSearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100506_res_0x7f06035a);
    }

    private int ejk_(View view) {
        ViewGroup.LayoutParams layoutParams;
        int ejm_;
        if (!ejo_(view) || (layoutParams = view.getLayoutParams()) == null) {
            return 0;
        }
        int i = layoutParams.width;
        if (i < 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            i = view.getMeasuredWidth();
            ejm_ = ejm_(view);
        } else {
            ejm_ = ejm_(view);
        }
        return i + ejm_;
    }

    private int ejm_(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
    }

    public HwSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = ejl_(this, "mOnClickListener", SearchView.class);
        this.f = new b();
        d();
    }

    private View.OnClickListener ejl_(Object obj, String str, Class<?> cls) {
        Object b2 = slc.b(obj, str, cls);
        if (b2 instanceof View.OnClickListener) {
            return (View.OnClickListener) b2;
        }
        return null;
    }
}
