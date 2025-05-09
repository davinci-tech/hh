package com.huawei.ui.commonui.viewpager.carouselsviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.viewpager.widget.ViewPager;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* loaded from: classes6.dex */
public class CarouselsViewPager extends ViewPager {

    /* renamed from: a, reason: collision with root package name */
    private PageScrolledListener f8987a;
    private float b;
    private Handler c;
    private boolean d;
    private float e;
    private int g;
    private int h;
    private int i;
    private int j;

    /* loaded from: classes9.dex */
    public interface OnCarouselsClickListener {
        void onClick(int i);
    }

    public interface PageScrolledListener {
        void pageScrolled(int i);
    }

    public CarouselsViewPager(Context context) {
        this(context, null);
    }

    public CarouselsViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.c = new e();
        this.e = 0.0f;
        this.g = 1500;
        this.h = 2500;
        this.i = 1;
        this.j = -1;
        FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(context, new LinearOutSlowInInterpolator());
        fixedSpeedScroller.e(this.g);
        cNk_(fixedSpeedScroller);
        setRotation(180.0f);
    }

    public final void cNk_(Scroller scroller) {
        Log.i("CarouselsViewPager", "replaceScroller");
        try {
            Field declaredField = Class.forName("androidx.viewpager.widget.ViewPager").getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, scroller);
            declaredField.setAccessible(false);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            Log.i("CarouselsViewPager", "replace scroller fail");
        }
    }

    public void b() {
        d(this.h);
    }

    public void d(int i) {
        Log.i("CarouselsViewPager", "enablePagesAutoLoop");
        Handler handler = this.c;
        if (handler != null) {
            handler.removeMessages(1);
        }
        this.d = false;
        int i2 = this.j;
        if (i2 == -1) {
            b(0);
        } else {
            b(i2);
        }
        this.h = i;
        if (this.i >= 2) {
            int i3 = this.j;
            if (i3 == -1) {
                setCurrentItem(getSurfaceCount() + 2, false);
            } else {
                setCurrentItem(i3, false);
            }
        }
        Handler handler2 = this.c;
        if (handler2 != null) {
            handler2.sendEmptyMessageDelayed(1, i);
        }
    }

    public void c() {
        Log.i("CarouselsViewPager", "disablePagesAutoLoop");
        this.d = true;
        Handler handler = this.c;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Log.i("CarouselsViewPager", "setAutoScrollPager");
        int realCount = getRealCount();
        if (realCount == -1) {
            Log.i("CarouselsViewPager", "acquire pages count fail");
            return;
        }
        int currentItem = getCurrentItem();
        Log.i("CarouselsViewPager", "pagersCount: " + realCount + ", curPageIndex: " + currentItem);
        if (currentItem == getRealCount() - 3) {
            setCurrentItem(d() - 2, false);
            this.c.sendEmptyMessageDelayed(1, 0L);
        } else {
            if (this.d) {
                return;
            }
            Log.i("CarouselsViewPager", "postDelayed, curPageIndex: " + currentItem);
            setCurrentItem(currentItem + 1, true);
            this.c.sendEmptyMessageDelayed(1, 2000L);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        LogUtil.a("CarouselsViewPager", "item: " + i);
        super.setCurrentItem(i, z);
        if (this.j != i) {
            b(i);
        }
        this.j = i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.c.removeMessages(1);
            this.d = true;
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            Log.i("CarouselsViewPager", "MotionEvent: " + motionEvent.getAction());
            this.d = false;
            this.c.sendEmptyMessageDelayed(1, 2000L);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.e = x;
        } else if (action == 1) {
            this.b = 0.0f;
        } else if (action == 2) {
            this.b = x - this.e;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void onPageScrolled(int i, float f, int i2) {
        int i3;
        if (this.b >= 0.0f || i != getRealCount() - 1) {
            i3 = i;
        } else {
            setCurrentItem(d(), false);
            i3 = d();
        }
        float f2 = this.b;
        if (f2 > 0.0f) {
            if (i == getSurfaceCount() + 1) {
                setCurrentItem(e(), false);
                i3 = e();
            }
            if (f > 0.0f && f < 1.0f) {
                b(i3);
            }
        } else if (f2 < 0.0f && f > 0.0f && f < 1.0f) {
            b(i3 + 1);
        }
        this.j = i3;
        super.onPageScrolled(i3, f, i2);
    }

    private void b(int i) {
        PageScrolledListener pageScrolledListener = this.f8987a;
        if (pageScrolledListener == null) {
            Log.i("CarouselsViewPager", "mPageScrolledListener is null");
        } else {
            pageScrolledListener.pageScrolled(getSurfaceCount() == 0 ? 0 : ((i + getSurfaceCount()) - 2) % getSurfaceCount());
        }
    }

    public void setPageScrolledListener(PageScrolledListener pageScrolledListener) {
        this.f8987a = pageScrolledListener;
    }

    public int getCurrentRealItem() {
        if (getSurfaceCount() == 0) {
            LogUtil.a("CarouselsViewPager", "getSurfaceCount is 0");
            return 0;
        }
        return c(this.j);
    }

    public int c(int i) {
        if (getSurfaceCount() == 0) {
            LogUtil.a("CarouselsViewPager", "getSurfaceCount is 0");
            return 0;
        }
        return (i - 2) % getSurfaceCount();
    }

    private int getRealCount() {
        if (getAdapter() == null) {
            Log.i("CarouselsViewPager", "adapter is null");
            return 0;
        }
        return getAdapter().getCount();
    }

    private int getSurfaceCount() {
        if (getAdapter() == null) {
            Log.i("CarouselsViewPager", "adapter is null");
            return 0;
        }
        return getRealCount() / 3;
    }

    public int d() {
        return ((getRealCount() * 2) / 3) - 1;
    }

    public int e() {
        return ((getRealCount() * 2) / 3) + 2;
    }

    public void setQuantityOfShowPages(int i) {
        this.i = i;
    }

    public void setScrollerDuration(int i) {
        this.g = i;
    }

    /* loaded from: classes9.dex */
    public static class FixedSpeedScroller extends Scroller {
        private int e;

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator, false);
            this.e = 1500;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4) {
            startScroll(i, i2, i3, i4, this.e);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, this.e);
        }

        public void e(int i) {
            this.e = i;
        }
    }

    /* loaded from: classes9.dex */
    static class e extends Handler {
        private WeakReference<CarouselsViewPager> c;

        private e(CarouselsViewPager carouselsViewPager) {
            this.c = new WeakReference<>(carouselsViewPager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<CarouselsViewPager> weakReference = this.c;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("CarouselsViewPager", "instance is null");
                return;
            }
            CarouselsViewPager carouselsViewPager = this.c.get();
            if (message.what != 1 || carouselsViewPager.d) {
                return;
            }
            carouselsViewPager.a();
        }
    }
}
