package com.huawei.uikit.hwspinner.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import defpackage.smr;
import defpackage.sms;

/* loaded from: classes7.dex */
public class HwSpinner extends Spinner implements DialogInterface.OnClickListener {
    public static final int MODE_DIALOG = 0;
    public static final int MODE_DROPDOWN = 1;

    /* renamed from: a, reason: collision with root package name */
    private static final int f10746a = -1;
    private static final String b = "HwSpinner";
    private static final int c = 1;
    private static final int d = 20;
    private static final int e = -1;
    private static final float f = -1.0f;
    private static final int g = -1;
    private int A;
    private int B;
    private int C;
    AdapterView.OnItemClickListener h;
    private final Rect i;
    private Context j;
    private HwForwardingListener k;
    private SpinnerAdapter l;
    private akxao m;
    private int n;
    private int o;
    private boolean p;
    private Context q;
    private boolean r;
    private int s;
    private int t;
    private float u;
    private HwColumnSystem v;
    private boolean w;
    private Drawable x;
    private boolean y;
    private boolean z;

    /* loaded from: classes9.dex */
    class a extends HwForwardingListener {
        e c;

        a(View view, e eVar) {
            super(view);
            this.c = eVar;
        }

        @Override // com.huawei.uikit.hwspinner.widget.HwForwardingListener
        public ShowableListMenu getPopup() {
            return this.c;
        }

        @Override // com.huawei.uikit.hwspinner.widget.HwForwardingListener
        public boolean onForwardingStarted() {
            if (HwSpinner.this.m.isShowing()) {
                return true;
            }
            HwSpinner.this.m.show(HwSpinner.this.getTextDirection(), HwSpinner.this.getTextAlignment());
            return true;
        }
    }

    interface akxao {
        void dismiss();

        Drawable getBackground();

        CharSequence getHintText();

        int getHorizontalOffset();

        int getVerticalOffset();

        boolean isShowing();

        void setAdapter(ListAdapter listAdapter);

        void setBackgroundDrawable(Drawable drawable);

        void setHorizontalOffset(int i);

        void setPromptText(CharSequence charSequence);

        void setVerticalOffset(int i);

        void show(int i, int i2);
    }

    static class b implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter c;
        private ListAdapter d;

        b(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.c = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.d = (ListAdapter) spinnerAdapter;
            }
            if (theme == null || !(spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                return;
            }
            ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
            if (themedSpinnerAdapter.getDropDownViewTheme() == null) {
                themedSpinnerAdapter.setDropDownViewTheme(theme);
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.d;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.c;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.c;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.c;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.c;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.c;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.d;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.c;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.c;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    class e extends HwListPopupWindow implements akxao {
        private CharSequence b;
        private ListAdapter d;

        class c implements AdapterView.OnItemClickListener {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ HwSpinner f10747a;

            c(HwSpinner hwSpinner) {
                this.f10747a = hwSpinner;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                HwSpinner.this.setSelection(i);
                e eVar = e.this;
                HwSpinner hwSpinner = HwSpinner.this;
                if (hwSpinner.h != null) {
                    hwSpinner.performItemClick(view, i, eVar.d.getItemId(i));
                }
                e.this.dismiss();
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        }

        e(Context context, AttributeSet attributeSet, int i, int i2) {
            super(context, attributeSet, i, i2);
            ehg_(HwSpinner.this);
            c(true);
            g(0);
            ehi_(new c(HwSpinner.this));
        }

        @Override // com.huawei.uikit.hwspinner.widget.HwSpinner.akxao
        public CharSequence getHintText() {
            return this.b;
        }

        @Override // com.huawei.uikit.hwspinner.widget.HwListPopupWindow, com.huawei.uikit.hwspinner.widget.HwSpinner.akxao
        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.d = listAdapter;
        }

        @Override // com.huawei.uikit.hwspinner.widget.HwSpinner.akxao
        public void setPromptText(CharSequence charSequence) {
            this.b = charSequence;
        }

        @Override // com.huawei.uikit.hwspinner.widget.HwSpinner.akxao
        public void show(int i, int i2) {
            f();
            c(2);
            super.show();
            HwDropDownListView listView = getListView();
            if (HwSpinner.this.o != 0) {
                listView.setTint(HwSpinner.this.o);
            }
            listView.setChoiceMode(1);
            listView.setTextDirection(i);
            listView.setTextAlignment(i2);
            if (HwSpinner.this.x != null) {
                listView.setDivider(HwSpinner.this.x);
                listView.setDividerHeight(HwSpinner.this.q.getResources().getDimensionPixelSize(R.dimen._2131364395_res_0x7f0a0a2b));
            }
            j(HwSpinner.this.getSelectedItemPosition());
        }

        int b(int i, int i2, int i3) {
            int i4 = 0;
            if (this.d instanceof SpinnerAdapter) {
                boolean z = HwSpinner.this.w;
                HwSpinner.this.w = false;
                i4 = HwSpinner.this.a((SpinnerAdapter) this.d, getBackground());
                HwSpinner.this.w = z;
            }
            int i5 = (HwSpinner.this.q.getResources().getDisplayMetrics().widthPixels - HwSpinner.this.i.left) - HwSpinner.this.i.right;
            if (i4 > i5) {
                i4 = i5;
            }
            int i6 = (i - i2) - i3;
            return i4 >= i6 ? i4 : i6;
        }

        void f() {
            int i;
            Drawable background = getBackground();
            if (background != null) {
                background.getPadding(HwSpinner.this.i);
                i = HwSpinner.this.a() ? HwSpinner.this.i.right : -HwSpinner.this.i.left;
            } else {
                HwSpinner.this.i.left = 0;
                HwSpinner.this.i.right = 0;
                i = 0;
            }
            int paddingLeft = HwSpinner.this.getPaddingLeft();
            int paddingRight = HwSpinner.this.getPaddingRight();
            int width = HwSpinner.this.getWidth();
            if (!HwSpinner.this.r) {
                if (HwSpinner.this.n != -2) {
                    if (HwSpinner.this.n != -1) {
                        a(HwSpinner.this.n);
                    } else {
                        a((width - paddingLeft) - paddingRight);
                    }
                } else {
                    a(b(width, paddingLeft, paddingRight));
                }
            } else {
                a(HwSpinner.this.a(b(width, paddingLeft, paddingRight)));
            }
            setHorizontalOffset(HwSpinner.this.a() ? i + ((width - paddingRight) - d()) : i + paddingLeft);
        }
    }

    public HwSpinner(Context context) {
        this(context, (AttributeSet) null);
    }

    public static HwSpinner instantiate(Context context) {
        Object e2 = sms.e(context, sms.e(context, (Class<?>) HwSpinner.class, sms.c(context, 1, 1)), (Class<?>) HwSpinner.class);
        if (e2 instanceof HwSpinner) {
            return (HwSpinner) e2;
        }
        return null;
    }

    public void configureColumn(int i, int i2, float f2) {
        this.s = i;
        this.t = i2;
        this.u = f2;
        if (this.r) {
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (getBackground() != null) {
            getBackground().setState(getDrawableState());
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Spinner.class.getName();
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        return this.m.getHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        return this.m.getVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.n;
    }

    public boolean getListShadowClip() {
        return this.z;
    }

    public int getListShadowColor() {
        return this.C;
    }

    public int getListShadowSize() {
        if (this.y) {
            return this.B;
        }
        return -1;
    }

    public int getListShadowStyle() {
        if (this.y) {
            return this.A;
        }
        return -1;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        return this.m.getBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.j;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        return this.m.getHintText();
    }

    public boolean isColumnEnabled() {
        return this.r;
    }

    public boolean isDynamicWidthEnabled() {
        return this.w;
    }

    public boolean isListShadowEnabled() {
        return this.y;
    }

    public boolean isPopupShowing() {
        akxao akxaoVar = this.m;
        return akxaoVar != null && akxaoVar.isShowing();
    }

    @Override // android.widget.Spinner, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        setSelection(i);
        if (dialogInterface == null) {
            Log.w(b, "onClick: dialog is null");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        } else {
            dialogInterface.dismiss();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.m == null || View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int a2 = a(getAdapter(), getBackground());
        int min = this.w ? Math.min(measuredWidth, a2) : Math.max(measuredWidth, a2);
        if (min > View.MeasureSpec.getSize(i)) {
            min = View.MeasureSpec.getSize(i);
        }
        setMeasuredDimension(min, getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        HwForwardingListener hwForwardingListener = this.k;
        if (hwForwardingListener == null || !hwForwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        if (getAdapter() == null || getAdapter().getCount() < 1) {
            return false;
        }
        if (!this.m.isShowing()) {
            this.m.show(getTextDirection(), getTextAlignment());
        }
        return true;
    }

    public void setColumnEnabled(boolean z) {
        this.r = z;
        requestLayout();
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i) {
        this.m.setHorizontalOffset(i);
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i) {
        this.m.setVerticalOffset(i);
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i) {
        if (this.m instanceof e) {
            this.n = i;
        } else {
            Log.e(b, "Cannot set dropdown width for MODE_DIALOG, ignoring");
        }
    }

    public void setDynamicWidthEnabled(boolean z) {
        this.w = z;
    }

    @Override // android.widget.Spinner, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.p) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).setEnabled(z);
            }
        }
    }

    public void setListShadowClip(boolean z) {
        this.z = z;
        akxao akxaoVar = this.m;
        if (akxaoVar instanceof e) {
            ((e) akxaoVar).a(z);
        }
    }

    public void setListShadowColor(int i) {
        this.C = i;
        akxao akxaoVar = this.m;
        if (akxaoVar instanceof e) {
            ((e) akxaoVar).b(i);
        }
    }

    public void setListShadowEnabled(boolean z) {
        this.y = z;
        akxao akxaoVar = this.m;
        if (akxaoVar instanceof e) {
            ((e) akxaoVar).e(z);
        }
    }

    public void setListShadowSize(int i) {
        this.B = i;
        akxao akxaoVar = this.m;
        if (akxaoVar instanceof e) {
            ((e) akxaoVar).e(i);
        }
    }

    public void setListShadowStyle(int i) {
        this.A = i;
        akxao akxaoVar = this.m;
        if (akxaoVar instanceof e) {
            ((e) akxaoVar).d(i);
        }
    }

    public void setOnItemClickListenerInt(AdapterView.OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        akxao akxaoVar = this.m;
        if (akxaoVar instanceof e) {
            akxaoVar.setBackgroundDrawable(drawable);
        } else {
            Log.e(b, "setPopupBackgroundDrawable: incompatible spinner mode, ignoring.");
        }
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        this.m.setPromptText(charSequence);
    }

    @Override // android.widget.Spinner
    public void setPromptId(int i) {
        setPrompt(getContext().getText(i));
    }

    public void setTint(int i) {
        this.o = i;
    }

    public HwSpinner(Context context, int i) {
        this(context, null, R.attr._2131100534_res_0x7f060376, i);
    }

    @Override // android.widget.Spinner, android.view.ViewGroup, android.view.View
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        return (getPointerIcon() == null && isClickable() && isEnabled()) ? PointerIcon.getSystemIcon(getContext(), 1002) : super.onResolvePointerIcon(motionEvent, i);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (this.m == null) {
            this.l = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.q.getApplicationInfo().targetSdkVersion >= 21 && spinnerAdapter != null && spinnerAdapter.getViewTypeCount() != 1) {
            Log.w(b, "setAdapter: Spinner adapter view type count must be 1");
            return;
        }
        Context context = this.j;
        if (context == null) {
            context = this.q;
        }
        this.m.setAdapter(new b(spinnerAdapter, context.getTheme()));
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(getPopupContext().getDrawable(i));
    }

    public HwSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100534_res_0x7f060376);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwSpinner);
    }

    public HwSpinner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        this.q = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100193_res_0x7f060221, R.attr._2131100265_res_0x7f060269, R.attr._2131100274_res_0x7f060272, R.attr._2131100282_res_0x7f06027a, R.attr._2131100283_res_0x7f06027b, R.attr._2131100284_res_0x7f06027c, R.attr._2131100285_res_0x7f06027d, R.attr._2131100286_res_0x7f06027e, R.attr._2131100287_res_0x7f06027f, R.attr._2131100398_res_0x7f0602ee, R.attr._2131100399_res_0x7f0602ef, R.attr._2131100400_res_0x7f0602f0, R.attr._2131100426_res_0x7f06030a, R.attr._2131100428_res_0x7f06030c, R.attr._2131100430_res_0x7f06030e, R.attr._2131100476_res_0x7f06033c, R.attr._2131100516_res_0x7f060364, R.attr._2131100517_res_0x7f060365, R.attr._2131100518_res_0x7f060366, R.attr._2131100519_res_0x7f060367, R.attr._2131100606_res_0x7f0603be}, i, R.style.Widget_Emui_HwSpinner);
        a(obtainStyledAttributes);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(this.q, R.drawable.hwspinner_selector_background_emui);
        }
        setBackground(drawable);
        if (i2 == -1 || i2 == 0 || i2 == 1) {
            e eVar = new e(this.j, attributeSet, i, R.style.Widget_Emui_HwSpinner);
            TypedArray obtainStyledAttributes2 = this.j.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100193_res_0x7f060221, R.attr._2131100265_res_0x7f060269, R.attr._2131100274_res_0x7f060272, R.attr._2131100282_res_0x7f06027a, R.attr._2131100283_res_0x7f06027b, R.attr._2131100284_res_0x7f06027c, R.attr._2131100285_res_0x7f06027d, R.attr._2131100286_res_0x7f06027e, R.attr._2131100287_res_0x7f06027f, R.attr._2131100398_res_0x7f0602ee, R.attr._2131100399_res_0x7f0602ef, R.attr._2131100400_res_0x7f0602f0, R.attr._2131100426_res_0x7f06030a, R.attr._2131100428_res_0x7f06030c, R.attr._2131100430_res_0x7f06030e, R.attr._2131100476_res_0x7f06033c, R.attr._2131100516_res_0x7f060364, R.attr._2131100517_res_0x7f060365, R.attr._2131100518_res_0x7f060366, R.attr._2131100519_res_0x7f060367, R.attr._2131100606_res_0x7f0603be}, i, R.style.Widget_Emui_HwSpinner);
            this.n = obtainStyledAttributes2.getLayoutDimension(7, -2);
            a(context, eVar, obtainStyledAttributes2);
            eVar.setBackgroundDrawable(obtainStyledAttributes2.getDrawable(12));
            eVar.setPromptText(obtainStyledAttributes.getString(15));
            obtainStyledAttributes2.recycle();
            this.m = eVar;
            this.k = new a(this, eVar);
            setListShadowEnabled(this.y);
            setListShadowStyle(this.A);
            setListShadowSize(this.B);
            setListShadowColor(this.C);
            setListShadowClip(this.z);
        }
        this.p = obtainStyledAttributes.getBoolean(2, false);
        obtainStyledAttributes.recycle();
        SpinnerAdapter spinnerAdapter = this.l;
        if (spinnerAdapter != null) {
            setAdapter(spinnerAdapter);
            this.l = null;
        }
    }

    public HwSpinner(Context context, AttributeSet attributeSet, int i, int i2) {
        super(a(context, i), attributeSet, i);
        this.i = new Rect();
        this.p = false;
        this.r = false;
        this.s = -1;
        this.t = -1;
        this.u = -1.0f;
        a(super.getContext(), attributeSet, i, i2);
        this.v = new HwColumnSystem(this.j);
    }

    private void a(TypedArray typedArray) {
        this.r = typedArray.getBoolean(1, false);
        this.w = typedArray.getBoolean(8, false);
        this.x = typedArray.getDrawable(3);
        this.y = typedArray.getBoolean(10, false);
        this.B = typedArray.getInt(11, 2);
        this.A = typedArray.getInt(20, 0);
        this.C = typedArray.getColor(9, -16777216);
        int resourceId = typedArray.getResourceId(14, R.style.Theme_Emui_HwSpinner);
        if (resourceId != 0) {
            this.j = new ContextThemeWrapper(this.q, resourceId);
        } else {
            this.j = this.q;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0078, code lost:
    
        if (r11 == null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007a, code lost:
    
        r11.getPadding(r9.i);
        r10 = r9.i;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0087, code lost:
    
        return r4 + (r10.left + r10.right);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int a(android.widget.SpinnerAdapter r10, android.graphics.drawable.Drawable r11) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L4
            return r0
        L4:
            int r1 = r9.getSelectedItemPosition()
            if (r1 > 0) goto Lc
            r1 = r0
            goto L10
        Lc:
            int r1 = r9.getSelectedItemPosition()
        L10:
            int r2 = r10.getCount()
            int r3 = r1 + 20
            if (r2 > r3) goto L1c
            int r3 = r10.getCount()
        L1c:
            int r2 = r3 - r1
            int r2 = 20 - r2
            int r1 = r1 - r2
            if (r1 > 0) goto L24
            r1 = r0
        L24:
            r2 = 0
            r4 = r0
            r5 = r4
            r6 = r2
        L28:
            if (r1 >= r3) goto L78
            int r7 = r10.getItemViewType(r1)
            if (r7 == r5) goto L32
            r6 = r2
            r5 = r7
        L32:
            android.view.View r6 = r10.getView(r1, r6, r9)
            if (r6 != 0) goto L39
            return r0
        L39:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            if (r7 != 0) goto L48
            android.view.ViewGroup$LayoutParams r7 = new android.view.ViewGroup$LayoutParams
            r8 = -2
            r7.<init>(r8, r8)
            r6.setLayoutParams(r7)
        L48:
            int r7 = r9.getMeasuredWidth()
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r0)
            int r8 = r9.getMeasuredHeight()
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r0)
            r6.measure(r7, r8)
            boolean r7 = r9.w
            if (r7 == 0) goto L6a
            int r7 = r9.getSelectedItemPosition()
            if (r1 != r7) goto L6a
            int r4 = r6.getMeasuredWidth()
            goto L78
        L6a:
            int r7 = r6.getMeasuredWidth()
            if (r4 <= r7) goto L71
            goto L75
        L71:
            int r4 = r6.getMeasuredWidth()
        L75:
            int r1 = r1 + 1
            goto L28
        L78:
            if (r11 == 0) goto L87
            android.graphics.Rect r10 = r9.i
            r11.getPadding(r10)
            android.graphics.Rect r10 = r9.i
            int r11 = r10.left
            int r10 = r10.right
            int r11 = r11 + r10
            int r4 = r4 + r11
        L87:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwspinner.widget.HwSpinner.a(android.widget.SpinnerAdapter, android.graphics.drawable.Drawable):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int a(int r6) {
        /*
            r5 = this;
            com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem r0 = r5.v
            r1 = 10
            r0.e(r1)
            int r0 = r5.s
            if (r0 <= 0) goto L1e
            int r1 = r5.t
            if (r1 <= 0) goto L1e
            float r2 = r5.u
            r3 = 0
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 <= 0) goto L1e
            com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem r3 = r5.v
            android.content.Context r4 = r5.j
            r3.d(r4, r0, r1, r2)
            goto L25
        L1e:
            com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem r0 = r5.v
            android.content.Context r1 = r5.j
            r0.e(r1)
        L25:
            com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem r0 = r5.v
            int r0 = r0.b()
            com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem r1 = r5.v
            int r1 = r1.j()
            if (r6 <= r0) goto L35
            r6 = r0
            goto L38
        L35:
            if (r6 >= r1) goto L38
            r6 = r1
        L38:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwspinner.widget.HwSpinner.a(int):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return getLayoutDirection() == 1;
    }

    private void a(Context context, e eVar, TypedArray typedArray) {
        Drawable drawable = typedArray.getDrawable(5);
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(context, R.drawable._2131429552_res_0x7f0b08b0);
        }
        eVar.ehh_(drawable);
    }
}
