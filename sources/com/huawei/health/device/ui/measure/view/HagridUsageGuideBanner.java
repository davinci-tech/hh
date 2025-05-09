package com.huawei.health.device.ui.measure.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolderCreator;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.cmr;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class HagridUsageGuideBanner<T> extends FrameLayout implements HealthViewPager.OnPageChangeListener, View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthDotsPageIndicator f2260a;
    private int b;
    private Handler c;
    private int d;
    private boolean e;
    private HealthViewPager f;
    private Runnable g;
    private View h;
    private List<T> i;
    private int j;

    static /* synthetic */ int a(HagridUsageGuideBanner hagridUsageGuideBanner) {
        int i = hagridUsageGuideBanner.d;
        hagridUsageGuideBanner.d = i + 1;
        return i;
    }

    public HagridUsageGuideBanner(Context context) {
        this(context, null);
        a(context);
    }

    public HagridUsageGuideBanner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        a(context);
    }

    public HagridUsageGuideBanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.c = new Handler();
        this.b = 0;
        this.j = 0;
        this.g = new Runnable() { // from class: com.huawei.health.device.ui.measure.view.HagridUsageGuideBanner.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HagridUsageGuideBanner", "Runnable mViewPager:", Integer.valueOf(HagridUsageGuideBanner.this.d));
                if (HagridUsageGuideBanner.this.f == null || HagridUsageGuideBanner.this.f.getChildCount() <= 1) {
                    HagridUsageGuideBanner.this.e = false;
                    if (HagridUsageGuideBanner.this.f != null) {
                        LogUtil.a("HagridUsageGuideBanner", "Runnable mViewPager count is :", Integer.valueOf(HagridUsageGuideBanner.this.f.getChildCount()));
                        return;
                    } else {
                        LogUtil.h("HagridUsageGuideBanner", "Runnable mViewPager is null");
                        return;
                    }
                }
                HagridUsageGuideBanner.this.e = true;
                HagridUsageGuideBanner.this.c.postDelayed(this, 5000L);
                HagridUsageGuideBanner.a(HagridUsageGuideBanner.this);
                HagridUsageGuideBanner.this.f.setCurrentItem(HagridUsageGuideBanner.this.d, true);
            }
        };
        a(context);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        View findViewWithTag = findViewWithTag(Integer.valueOf(this.f.getCurrentItem()));
        if (findViewWithTag != null) {
            findViewWithTag.measure(i, View.MeasureSpec.makeMeasureSpec(0, 0));
            this.b = findViewWithTag.getMeasuredHeight();
            this.j = findViewWithTag.getMeasuredWidth();
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.b, 1073741824));
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_hagrid_usage_guide_banner_view, this);
        this.h = inflate;
        this.f = (HealthViewPager) inflate.findViewById(R.id.guide_view_pager);
        this.f2260a = (HealthDotsPageIndicator) this.h.findViewById(R.id.guide_dots);
        this.i = new ArrayList();
    }

    public void setViewPagerData(List<T> list, HealthViewPagerHolderCreator healthViewPagerHolderCreator) {
        b(list);
        c(healthViewPagerHolderCreator);
    }

    private void b(List<T> list) {
        if (list.size() == 0) {
            this.h.setVisibility(8);
        } else {
            this.i.addAll(list);
        }
        if (this.i.size() > 1) {
            this.f2260a.setVisibility(0);
        } else {
            this.f2260a.setVisibility(8);
        }
    }

    private void c(HealthViewPagerHolderCreator healthViewPagerHolderCreator) {
        this.f.setAdapter(new cmr(this.i, healthViewPagerHolderCreator));
        this.f.setCurrentItem(this.d);
        this.f.setOffscreenPageLimit(this.i.size());
        this.f2260a.setViewPager(this.f);
        this.f.addOnPageChangeListener(this);
        this.f.setOnTouchListener(this);
        d();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.h("HagridUsageGuideBanner", "OnTouchListener onTouch event is null");
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        LogUtil.h("HagridUsageGuideBanner", "OnTouchListener onTouch default");
                        return false;
                    }
                }
            }
            this.e = false;
            d();
            return false;
        }
        this.e = true;
        b();
        return false;
    }

    public void d() {
        Object[] objArr = new Object[4];
        objArr[0] = "startLoop isLoop:";
        objArr[1] = Boolean.valueOf(this.e);
        objArr[2] = ",mViewPager is null?";
        objArr[3] = Boolean.valueOf(this.f == null);
        LogUtil.a("HagridUsageGuideBanner", objArr);
        if (this.e || this.f == null) {
            return;
        }
        this.c.postDelayed(this.g, 5000L);
        this.e = true;
    }

    public void b() {
        Object[] objArr = new Object[4];
        objArr[0] = "stopLoop isLoop:";
        objArr[1] = Boolean.valueOf(this.e);
        objArr[2] = ",mViewPager is null?";
        objArr[3] = Boolean.valueOf(this.f == null);
        LogUtil.a("HagridUsageGuideBanner", objArr);
        if (!this.e || this.f == null) {
            return;
        }
        this.c.removeCallbacks(this.g);
        this.e = false;
    }

    public boolean e() {
        return this.e;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.d == i) {
            ViewGroup.LayoutParams layoutParams = this.h.getLayoutParams();
            layoutParams.height = this.b;
            layoutParams.width = this.j;
            this.h.setLayoutParams(layoutParams);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        requestLayout();
        this.d = i;
        if (i == this.i.size() - 1) {
            b();
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            this.f.setCurrentItem(this.d, false);
        }
    }
}
