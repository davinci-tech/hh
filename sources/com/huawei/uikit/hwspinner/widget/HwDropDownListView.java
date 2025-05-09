package com.huawei.uikit.hwspinner.widget;

import android.R;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.core.widget.ListViewAutoScrollHelper;
import defpackage.slc;

/* loaded from: classes7.dex */
public class HwDropDownListView extends ListView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10741a;
    private int b;
    private ListViewAutoScrollHelper c;
    private boolean d;
    private boolean e;
    private int g;

    public HwDropDownListView(Context context, boolean z) {
        this(context, z, R.attr.dropDownListViewStyle);
    }

    private void c() {
        slc.c(this, "setShowHoverEnabled", new Class[]{Boolean.TYPE}, new Object[]{false}, AbsListView.class);
    }

    private void e() {
        setPressed(false);
        slc.c(this, "updateSelectorState", null, null, AbsListView.class);
        Object b = slc.b(this, "mMotionPosition", (Class<?>) AbsListView.class);
        if (b instanceof Integer) {
            this.b = ((Integer) b).intValue();
        }
        View childAt = getChildAt(this.b - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
    }

    protected int d(int i, int i2) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        ListAdapter adapter = getAdapter();
        int i3 = listPaddingTop + listPaddingBottom;
        if (adapter == null) {
            return i3;
        }
        int count = adapter.getCount();
        View view = null;
        int i4 = 0;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = adapter.getItemViewType(i5);
            if (itemViewType != i4) {
                view = null;
                i4 = itemViewType;
            }
            view = adapter.getView(i5, view, this);
            if (view != null) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = generateDefaultLayoutParams();
                    view.setLayoutParams(layoutParams);
                }
                int i6 = layoutParams.height;
                view.measure(i, i6 <= 0 ? View.MeasureSpec.makeMeasureSpec(0, 0) : View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
                view.forceLayout();
                int dividerHeight = (getDividerHeight() <= 0 || getDivider() == null) ? 0 : getDividerHeight();
                if (i5 > 0) {
                    i3 += dividerHeight;
                }
                i3 += view.getMeasuredHeight();
                if (i3 >= i2) {
                    return i2;
                }
            }
        }
        return i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
    
        if (r0 != 3) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004a A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean ehc_(android.view.MotionEvent r9, int r10) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L11
            r3 = 2
            if (r0 == r3) goto Lf
            r10 = 3
            if (r0 == r10) goto L18
            goto L46
        Lf:
            r3 = r2
            goto L12
        L11:
            r3 = r1
        L12:
            int r10 = r9.findPointerIndex(r10)
            if (r10 >= 0) goto L1b
        L18:
            r10 = r1
            r3 = r10
            goto L48
        L1b:
            float r4 = r9.getX(r10)
            int r4 = (int) r4
            float r10 = r9.getY(r10)
            int r10 = (int) r10
            int r5 = r8.pointToPosition(r4, r10)
            r6 = -1
            if (r5 != r6) goto L2e
            r10 = r2
            goto L48
        L2e:
            int r3 = r8.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r8.getChildAt(r3)
            float r4 = (float) r4
            float r10 = (float) r10
            r8.ehb_(r3, r5, r4, r10)
            if (r0 != r2) goto L46
            long r6 = r8.getItemIdAtPosition(r5)
            r8.performItemClick(r3, r5, r6)
        L46:
            r10 = r1
            r3 = r2
        L48:
            if (r3 == 0) goto L4c
            if (r10 == 0) goto L4f
        L4c:
            r8.e()
        L4f:
            if (r3 == 0) goto L66
            androidx.core.widget.ListViewAutoScrollHelper r10 = r8.c
            if (r10 != 0) goto L5c
            androidx.core.widget.ListViewAutoScrollHelper r10 = new androidx.core.widget.ListViewAutoScrollHelper
            r10.<init>(r8)
            r8.c = r10
        L5c:
            androidx.core.widget.ListViewAutoScrollHelper r10 = r8.c
            r10.setEnabled(r2)
            androidx.core.widget.ListViewAutoScrollHelper r10 = r8.c
            r10.onTouch(r8, r9)
        L66:
            androidx.core.widget.ListViewAutoScrollHelper r9 = r8.c
            if (r9 == 0) goto L6d
            r9.setEnabled(r1)
        L6d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwspinner.widget.HwDropDownListView.ehc_(android.view.MotionEvent, int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f10741a || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f10741a || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f10741a || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        if (isHovered() && isFocused()) {
            return false;
        }
        return (this.f10741a && this.d) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    protected void layoutChildren() {
        super.layoutChildren();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        return true;
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setListSelectionHidden(boolean z) {
        this.d = z;
    }

    public void setTint(int i) {
        if (i != 0) {
            this.g = i;
        }
    }

    public HwDropDownListView(Context context, boolean z, int i) {
        super(context, null, i);
        this.d = false;
        this.b = 0;
        this.f10741a = z;
        setDefaultFocusHighlightEnabled(false);
        c();
        setCacheColorHint(0);
    }

    private void ehb_(View view, int i, float f, float f2) {
        drawableHotspotChanged(f, f2);
        if (!isPressed()) {
            setPressed(true);
        }
        Object b = slc.b(this, "mDataChanged", (Class<?>) AdapterView.class);
        if (b instanceof Boolean) {
            this.e = ((Boolean) b).booleanValue();
        }
        if (this.e) {
            layoutChildren();
        }
        Object b2 = slc.b(this, "mMotionPosition", (Class<?>) AbsListView.class);
        if (b2 instanceof Integer) {
            this.b = ((Integer) b2).intValue();
        }
        View childAt = getChildAt(this.b - getFirstVisiblePosition());
        if (childAt != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.b = i;
        view.drawableHotspotChanged(f - view.getLeft(), f2 - view.getTop());
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        slc.c(this, "setSelectedPositionInt", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)}, AdapterView.class);
        Class cls = Integer.TYPE;
        Class cls2 = Float.TYPE;
        slc.c(this, "positionSelectorLikeTouch", new Class[]{cls, View.class, cls2, cls2}, new Object[]{Integer.valueOf(i), view, Float.valueOf(f), Float.valueOf(f2)}, AbsListView.class);
        refreshDrawableState();
    }
}
