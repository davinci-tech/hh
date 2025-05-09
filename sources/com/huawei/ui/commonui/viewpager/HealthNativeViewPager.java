package com.huawei.ui.commonui.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.huawei.ui.commonui.R$styleable;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;

/* loaded from: classes6.dex */
public class HealthNativeViewPager extends ViewPager {

    /* renamed from: a, reason: collision with root package name */
    private int f8985a;
    private int b;
    private boolean c;
    private boolean d;
    private ViewPager.OnPageChangeListener e;
    private int g;

    public HealthNativeViewPager(Context context) {
        super(context);
        this.f8985a = 0;
        this.c = false;
    }

    public HealthNativeViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8985a = 0;
        this.c = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthNativeViewPager);
            this.c = obtainStyledAttributes.getBoolean(R$styleable.HealthNativeViewPager_isAutoMaxHeight, false);
            this.g = obtainStyledAttributes.getInteger(R$styleable.HealthNativeViewPager_slideDuration, 0);
            obtainStyledAttributes.recycle();
        }
        c(context, this.g);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.c) {
            int i3 = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredHeight > i3) {
                    i3 = measuredHeight;
                }
            }
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
            return;
        }
        PagerAdapter adapter = getAdapter();
        if (this.d && (adapter instanceof FragmentStatePagerAdapter)) {
            FragmentStatePagerAdapter fragmentStatePagerAdapter = (FragmentStatePagerAdapter) adapter;
            int count = getAdapter().getCount();
            int i5 = this.b;
            if (count > i5) {
                View view = fragmentStatePagerAdapter.getItem(i5).getView();
                if (view == null) {
                    super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.f8985a, 1073741824));
                    return;
                } else {
                    view.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
                    this.f8985a = view.getMeasuredHeight();
                }
            }
            i2 = View.MeasureSpec.makeMeasureSpec(this.f8985a, 1073741824);
        } else {
            setMeasuredDimension(ViewGroup.getDefaultSize(0, i), ViewGroup.getDefaultSize(0, i2));
        }
        super.onMeasure(i, i2);
    }

    public void setSlideDuration(int i) {
        this.g = i;
    }

    public void setIsAutoMaxHeight(boolean z) {
        this.c = z;
    }

    public void setInitItem(int i) {
        ReflectionUtils.c(this, "mCurItem", Integer.valueOf(i));
    }

    public void setIsAutoAdjustItemHeight(boolean z) {
        this.d = z;
        if (this.e == null) {
            this.e = new ViewPager.OnPageChangeListener() { // from class: com.huawei.ui.commonui.viewpager.HealthNativeViewPager.2
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i, float f, int i2) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i) {
                    HealthNativeViewPager.this.b(i);
                }
            };
        }
        removeOnPageChangeListener(this.e);
        addOnPageChangeListener(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.b = i;
        if (getAdapter() == null) {
            LogUtil.e("HealthNativeViewPager", "adapter is null in resetHeight");
        } else {
            if (i >= getAdapter().getCount() || !(getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                return;
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            layoutParams.height = this.f8985a;
            setLayoutParams(layoutParams);
        }
    }

    private void c(Context context, int i) {
        if (context == null || i <= 0) {
            return;
        }
        a aVar = new a(context);
        aVar.b(i);
        ReflectionUtils.c(this, "mScroller", aVar);
    }

    /* loaded from: classes9.dex */
    static class a extends Scroller {
        private int e;

        private a(Context context) {
            super(context);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4) {
            startScroll(i, i2, i3, i4, this.e);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, this.e);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i) {
            this.e = i;
        }
    }
}
