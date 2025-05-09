package com.huawei.ui.commonui.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class HealthViewPager extends HwViewPager {
    private Context c;
    private float d;
    private float e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private float m;
    private boolean n;
    private float o;
    private OnViewPagerTouchEvent p;
    private int r;
    private float s;

    /* loaded from: classes.dex */
    public interface OnPageChangeListener extends HwViewPager.OnPageChangeListener {
    }

    public interface OnViewPagerTouchEvent {
        void onTouchDown(MotionEvent motionEvent);

        void onTouchUp(MotionEvent motionEvent);

        void setIsViewTouch(Boolean bool);
    }

    public interface PageTransformer extends HwViewPager.PageTransformer {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    public HealthViewPager(Context context) {
        super(context);
        this.h = true;
        this.k = true;
        this.n = true;
        this.l = false;
        this.g = false;
        this.f = false;
        this.j = false;
        this.e = 0.0f;
        this.d = 0.0f;
        this.m = 0.0f;
        this.s = 0.0f;
        this.o = 0.0f;
        this.r = 0;
        this.i = 0;
        this.c = context;
    }

    public HealthViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = true;
        this.k = true;
        this.n = true;
        this.l = false;
        this.g = false;
        this.f = false;
        this.j = false;
        this.e = 0.0f;
        this.d = 0.0f;
        this.m = 0.0f;
        this.s = 0.0f;
        this.o = 0.0f;
        this.r = 0;
        this.i = 0;
        this.c = context;
    }

    public void setIsScroll(boolean z) {
        this.h = z;
    }

    public void setIsScrollToLeft(boolean z) {
        this.k = z;
    }

    public void setIsScrollToRight(boolean z) {
        this.n = z;
    }

    public void setIsCompatibleScrollView(boolean z) {
        this.l = z;
    }

    public void setIsAutoHeight(boolean z) {
        this.g = z;
    }

    public void setIsAutoCurrentItemHeight(boolean z) {
        this.f = z;
    }

    public void setIsAutoWidthAndHeight(boolean z) {
        this.j = z;
    }

    public void setScrollHeightArea(int i) {
        this.i = nsn.c(this.c, i);
    }

    public void setOnViewPagerTouchEventListener(OnViewPagerTouchEvent onViewPagerTouchEvent) {
        this.p = onViewPagerTouchEvent;
    }

    public void setYScrollMoveThreshold(int i) {
        this.r = i;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = 0;
        if (this.g) {
            int i4 = 0;
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                childAt.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i4) {
                    i4 = measuredHeight;
                }
            }
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
            return;
        }
        if (this.f) {
            int currentItem = getCurrentItem();
            HwPagerAdapter adapter = getAdapter();
            if (adapter == null) {
                return;
            }
            Object instantiateItem = adapter.instantiateItem(this, currentItem);
            if (instantiateItem instanceof View) {
                View view = (View) instantiateItem;
                view.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
                i3 = view.getMeasuredHeight();
                adapter.destroyItem(this, currentItem, instantiateItem);
            }
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
            return;
        }
        if (this.j) {
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < getChildCount(); i8++) {
                View childAt2 = getChildAt(i8);
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                i6 = Math.max(i6, childAt2.getMeasuredWidth());
                i7 = Math.max(i7, childAt2.getMeasuredHeight());
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i6, 1073741824), View.MeasureSpec.makeMeasureSpec(i7, 1073741824));
            return;
        }
        setMeasuredDimension(ViewGroup.getDefaultSize(0, i), ViewGroup.getDefaultSize(0, i2));
        super.onMeasure(i, i2);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.h) {
            return false;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null && this.h && motionEvent.getY() > this.i) {
            try {
                return super.onInterceptTouchEvent(motionEvent);
            } catch (IllegalArgumentException unused) {
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        cNg_(motionEvent);
        if (this.l) {
            int action = motionEvent.getAction();
            if (action == 0) {
                getParent().requestDisallowInterceptTouchEvent(true);
                this.e = motionEvent.getY();
                this.d = motionEvent.getX();
            } else if (action == 2) {
                this.m = motionEvent.getX();
                this.s = motionEvent.getY();
                if ((Math.abs(this.m - this.d) >= Math.abs(this.s - this.e) && nsn.c(this.c, 180.0f) >= this.s) || nsn.c(this.c, 180.0f) < this.s || nsn.c(this.c, this.r) > Math.abs(this.m - this.d)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        }
        if (!this.k && cNh_(motionEvent)) {
            return true;
        }
        if (this.n || !cNi_(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    private boolean cNh_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.o = motionEvent.getX();
        } else if (action == 2) {
            float x = motionEvent.getX() - this.o;
            boolean bc = LanguageUtil.bc(getContext());
            if (!bc && x < 0.0f) {
                return true;
            }
            if (bc && x > 0.0f) {
                return true;
            }
        }
        return false;
    }

    private boolean cNi_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.o = motionEvent.getX();
        } else if (action == 2) {
            float x = motionEvent.getX() - this.o;
            boolean bc = LanguageUtil.bc(getContext());
            if (!bc && x > 0.0f) {
                return true;
            }
            if (bc && x < 0.0f) {
                return true;
            }
        }
        return false;
    }

    private void cNg_(MotionEvent motionEvent) {
        if (this.p == null) {
            return;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.p.onTouchDown(motionEvent);
            return;
        }
        if (action == 1) {
            this.p.onTouchUp(motionEvent);
            return;
        }
        if (action != 2) {
            return;
        }
        this.m = motionEvent.getX();
        this.s = motionEvent.getY();
        if ((Math.abs(this.m - this.d) >= Math.abs(this.s - this.e) && nsn.c(this.c, 180.0f) >= this.s) || nsn.c(this.c, 180.0f) < this.s || nsn.c(this.c, this.r) > Math.abs(this.m - this.d)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }
}
