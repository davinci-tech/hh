package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.huawei.health.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    int activePointerId;
    private BottomSheetCallback callback;
    int collapsedOffset;
    private final ViewDragHelper.Callback dragCallback;
    private boolean fitToContents;
    int fitToContentsOffset;
    int halfExpandedOffset;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int lastNestedScrollDy;
    private int lastPeekHeight;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightMin;
    private boolean skipCollapsed;
    int state;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    public static abstract class BottomSheetCallback {
        public abstract void onSlide(View view, float f);

        public abstract void onStateChanged(View view, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface State {
    }

    public BottomSheetBehavior() {
        this.fitToContents = true;
        this.state = 4;
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                View view2;
                if (BottomSheetBehavior.this.state == 1 || BottomSheetBehavior.this.touchingScrollingChild) {
                    return false;
                }
                return ((BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == i && (view2 = BottomSheetBehavior.this.nestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) || BottomSheetBehavior.this.viewRef == null || BottomSheetBehavior.this.viewRef.get() != view) ? false : true;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                BottomSheetBehavior.this.dispatchOnSlide(i2);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x00ec  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x00db  */
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onViewReleased(android.view.View r6, float r7, float r8) {
                /*
                    Method dump skipped, instructions count: 242
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass2.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                return MathUtils.clamp(i, BottomSheetBehavior.this.getExpandedOffset(), BottomSheetBehavior.this.hideable ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                if (BottomSheetBehavior.this.hideable) {
                    return BottomSheetBehavior.this.parentHeight;
                }
                return BottomSheetBehavior.this.collapsedOffset;
            }
        };
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.fitToContents = true;
        this.state = 4;
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                View view2;
                if (BottomSheetBehavior.this.state == 1 || BottomSheetBehavior.this.touchingScrollingChild) {
                    return false;
                }
                return ((BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == i && (view2 = BottomSheetBehavior.this.nestedScrollingChildRef.get()) != null && view2.canScrollVertically(-1)) || BottomSheetBehavior.this.viewRef == null || BottomSheetBehavior.this.viewRef.get() != view) ? false : true;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                BottomSheetBehavior.this.dispatchOnSlide(i2);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                /*
                    Method dump skipped, instructions count: 242
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass2.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                return MathUtils.clamp(i, BottomSheetBehavior.this.getExpandedOffset(), BottomSheetBehavior.this.hideable ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                if (BottomSheetBehavior.this.hideable) {
                    return BottomSheetBehavior.this.parentHeight;
                }
                return BottomSheetBehavior.this.collapsedOffset;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099772_res_0x7f06007c, R.attr._2131099773_res_0x7f06007d, R.attr._2131099775_res_0x7f06007f, R.attr._2131099776_res_0x7f060080});
        TypedValue peekValue = obtainStyledAttributes.peekValue(2);
        if (peekValue != null && peekValue.data == -1) {
            setPeekHeight(peekValue.data);
        } else {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(2, -1));
        }
        setHideable(obtainStyledAttributes.getBoolean(1, false));
        setFitToContents(obtainStyledAttributes.getBoolean(0, true));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(3, false));
        obtainStyledAttributes.recycle();
        this.maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), this.state);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        if (savedState.state == 1 || savedState.state == 2) {
            this.state = 4;
        } else {
            this.state = savedState.state;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.parentHeight = coordinatorLayout.getHeight();
        if (this.peekHeightAuto) {
            if (this.peekHeightMin == 0) {
                this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen._2131362392_res_0x7f0a0258);
            }
            this.lastPeekHeight = Math.max(this.peekHeightMin, this.parentHeight - ((coordinatorLayout.getWidth() * 9) / 16));
        } else {
            this.lastPeekHeight = this.peekHeight;
        }
        this.fitToContentsOffset = Math.max(0, this.parentHeight - v.getHeight());
        this.halfExpandedOffset = this.parentHeight / 2;
        calculateCollapsedOffset();
        int i2 = this.state;
        if (i2 == 3) {
            ViewCompat.offsetTopAndBottom(v, getExpandedOffset());
        } else if (i2 == 6) {
            ViewCompat.offsetTopAndBottom(v, this.halfExpandedOffset);
        } else if (this.hideable && i2 == 5) {
            ViewCompat.offsetTopAndBottom(v, this.parentHeight);
        } else if (i2 == 4) {
            ViewCompat.offsetTopAndBottom(v, this.collapsedOffset);
        } else if (i2 == 1 || i2 == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        this.viewRef = new WeakReference<>(v);
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        if (!v.isShown()) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.initialY = (int) motionEvent.getY();
            WeakReference<View> weakReference = this.nestedScrollingChildRef;
            View view = weakReference != null ? weakReference.get() : null;
            if (view != null && coordinatorLayout.isPointInChildBounds(view, x, this.initialY)) {
                this.activePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                this.touchingScrollingChild = true;
            }
            this.ignoreEvents = this.activePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.initialY);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.touchingScrollingChild = false;
            this.activePointerId = -1;
            if (this.ignoreEvents) {
                this.ignoreEvents = false;
                return false;
            }
        }
        if (!this.ignoreEvents && (viewDragHelper = this.viewDragHelper) != null && viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.nestedScrollingChildRef;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.ignoreEvents || this.state == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.viewDragHelper == null || Math.abs(((float) this.initialY) - motionEvent.getY()) <= ((float) this.viewDragHelper.getTouchSlop())) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 2 && !this.ignoreEvents && Math.abs(this.initialY - motionEvent.getY()) > this.viewDragHelper.getTouchSlop()) {
            this.viewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (i & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        if (i3 != 1 && view == this.nestedScrollingChildRef.get()) {
            int top = v.getTop();
            int i4 = top - i2;
            if (i2 > 0) {
                if (i4 < getExpandedOffset()) {
                    int expandedOffset = top - getExpandedOffset();
                    iArr[1] = expandedOffset;
                    ViewCompat.offsetTopAndBottom(v, -expandedOffset);
                    setStateInternal(3);
                } else {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                }
            } else if (i2 < 0 && !view.canScrollVertically(-1)) {
                int i5 = this.collapsedOffset;
                if (i4 <= i5 || this.hideable) {
                    iArr[1] = i2;
                    ViewCompat.offsetTopAndBottom(v, -i2);
                    setStateInternal(1);
                } else {
                    int i6 = top - i5;
                    iArr[1] = i6;
                    ViewCompat.offsetTopAndBottom(v, -i6);
                    setStateInternal(4);
                }
            }
            dispatchOnSlide(v.getTop());
            this.lastNestedScrollDy = i2;
            this.nestedScrolled = true;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        int i2;
        int i3 = 3;
        if (v.getTop() == getExpandedOffset()) {
            setStateInternal(3);
            return;
        }
        if (view == this.nestedScrollingChildRef.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
                i2 = getExpandedOffset();
            } else if (this.hideable && shouldHide(v, getYVelocity())) {
                i2 = this.parentHeight;
                i3 = 5;
            } else {
                if (this.lastNestedScrollDy == 0) {
                    int top = v.getTop();
                    if (this.fitToContents) {
                        if (Math.abs(top - this.fitToContentsOffset) < Math.abs(top - this.collapsedOffset)) {
                            i2 = this.fitToContentsOffset;
                        } else {
                            i2 = this.collapsedOffset;
                        }
                    } else {
                        int i4 = this.halfExpandedOffset;
                        if (top < i4) {
                            if (top < Math.abs(top - this.collapsedOffset)) {
                                i2 = 0;
                            } else {
                                i2 = this.halfExpandedOffset;
                            }
                        } else if (Math.abs(top - i4) < Math.abs(top - this.collapsedOffset)) {
                            i2 = this.halfExpandedOffset;
                        } else {
                            i2 = this.collapsedOffset;
                        }
                        i3 = 6;
                    }
                } else {
                    i2 = this.collapsedOffset;
                }
                i3 = 4;
            }
            if (this.viewDragHelper.smoothSlideViewTo(v, v.getLeft(), i2)) {
                setStateInternal(2);
                ViewCompat.postOnAnimation(v, new SettleRunnable(v, i3));
            } else {
                setStateInternal(i3);
            }
            this.nestedScrolled = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        return view == this.nestedScrollingChildRef.get() && (this.state != 3 || super.onNestedPreFling(coordinatorLayout, v, view, f, f2));
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public void setFitToContents(boolean z) {
        if (this.fitToContents == z) {
            return;
        }
        this.fitToContents = z;
        if (this.viewRef != null) {
            calculateCollapsedOffset();
        }
        setStateInternal((this.fitToContents && this.state == 6) ? 3 : this.state);
    }

    public final void setPeekHeight(int i) {
        WeakReference<V> weakReference;
        V v;
        if (i == -1) {
            if (this.peekHeightAuto) {
                return;
            } else {
                this.peekHeightAuto = true;
            }
        } else {
            if (!this.peekHeightAuto && this.peekHeight == i) {
                return;
            }
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, i);
            this.collapsedOffset = this.parentHeight - i;
        }
        if (this.state != 4 || (weakReference = this.viewRef) == null || (v = weakReference.get()) == null) {
            return;
        }
        v.requestLayout();
    }

    public final int getPeekHeight() {
        if (this.peekHeightAuto) {
            return -1;
        }
        return this.peekHeight;
    }

    public void setHideable(boolean z) {
        this.hideable = z;
    }

    public boolean isHideable() {
        return this.hideable;
    }

    public void setSkipCollapsed(boolean z) {
        this.skipCollapsed = z;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callback = bottomSheetCallback;
    }

    public final void setState(final int i) {
        if (i == this.state) {
            return;
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null) {
            if (i == 4 || i == 3 || i == 6 || (this.hideable && i == 5)) {
                this.state = i;
                return;
            }
            return;
        }
        final V v = weakReference.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(v)) {
            v.post(new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.1
                @Override // java.lang.Runnable
                public void run() {
                    BottomSheetBehavior.this.startSettlingAnimation(v, i);
                }
            });
        } else {
            startSettlingAnimation(v, i);
        }
    }

    public final int getState() {
        return this.state;
    }

    void setStateInternal(int i) {
        BottomSheetCallback bottomSheetCallback;
        if (this.state == i) {
            return;
        }
        this.state = i;
        if (i == 6 || i == 3) {
            updateImportantForAccessibility(true);
        } else if (i == 5 || i == 4) {
            updateImportantForAccessibility(false);
        }
        V v = this.viewRef.get();
        if (v == null || (bottomSheetCallback = this.callback) == null) {
            return;
        }
        bottomSheetCallback.onStateChanged(v, i);
    }

    private void calculateCollapsedOffset() {
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - this.lastPeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - this.lastPeekHeight;
        }
    }

    private void reset() {
        this.activePointerId = -1;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    boolean shouldHide(View view, float f) {
        if (this.skipCollapsed) {
            return true;
        }
        return view.getTop() >= this.collapsedOffset && Math.abs((((float) view.getTop()) + (f * 0.1f)) - ((float) this.collapsedOffset)) / ((float) this.peekHeight) > 0.5f;
    }

    View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
            if (findScrollingChild != null) {
                return findScrollingChild;
            }
        }
        return null;
    }

    private float getYVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getExpandedOffset() {
        if (this.fitToContents) {
            return this.fitToContentsOffset;
        }
        return 0;
    }

    void startSettlingAnimation(View view, int i) {
        int i2;
        int i3;
        if (i == 4) {
            i2 = this.collapsedOffset;
        } else if (i == 6) {
            i2 = this.halfExpandedOffset;
            if (this.fitToContents && i2 <= (i3 = this.fitToContentsOffset)) {
                i = 3;
                i2 = i3;
            }
        } else if (i == 3) {
            i2 = getExpandedOffset();
        } else if (this.hideable && i == 5) {
            i2 = this.parentHeight;
        } else {
            throw new IllegalArgumentException("Illegal state argument: " + i);
        }
        if (this.viewDragHelper.smoothSlideViewTo(view, view.getLeft(), i2)) {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
        } else {
            setStateInternal(i);
        }
    }

    void dispatchOnSlide(int i) {
        BottomSheetCallback bottomSheetCallback;
        V v = this.viewRef.get();
        if (v == null || (bottomSheetCallback = this.callback) == null) {
            return;
        }
        if (i > this.collapsedOffset) {
            bottomSheetCallback.onSlide(v, (r2 - i) / (this.parentHeight - r2));
        } else {
            bottomSheetCallback.onSlide(v, (r2 - i) / (r2 - getExpandedOffset()));
        }
    }

    int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    class SettleRunnable implements Runnable {
        private final int targetState;
        private final View view;

        SettleRunnable(View view, int i) {
            this.view = view;
            this.targetState = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BottomSheetBehavior.this.viewDragHelper != null && BottomSheetBehavior.this.viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.view, this);
            } else {
                BottomSheetBehavior.this.setStateInternal(this.targetState);
            }
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final int state;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.state = i;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (!(behavior instanceof BottomSheetBehavior)) {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        return (BottomSheetBehavior) behavior;
    }

    private void updateImportantForAccessibility(boolean z) {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.importantForAccessibilityMap != null) {
                    return;
                } else {
                    this.importantForAccessibilityMap = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.viewRef.get()) {
                    if (!z) {
                        Map<View, Integer> map = this.importantForAccessibilityMap;
                        if (map != null && map.containsKey(childAt)) {
                            ViewCompat.setImportantForAccessibility(childAt, this.importantForAccessibilityMap.get(childAt).intValue());
                        }
                    } else {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        ViewCompat.setImportantForAccessibility(childAt, 4);
                    }
                }
            }
            if (z) {
                return;
            }
            this.importantForAccessibilityMap = null;
        }
    }
}
