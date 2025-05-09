package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;

/* loaded from: classes7.dex */
public class rxu implements HwViewPager.PageTransformer, HwViewPager.OnPageChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private int f16957a;
    private HwViewPager b;
    private Handler c;
    private boolean d;
    private long e;
    private int f;
    private float i;

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }

    public rxu() {
        this(0.9f);
    }

    public rxu(float f) {
        this.d = false;
        this.c = new Handler(Looper.getMainLooper()) { // from class: rxu.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if ((message.what == 0 || message.what == 2) && rxu.this.b != null && rxu.this.f > 0 && rxu.this.b.getChildCount() > 0) {
                    int currentItem = rxu.this.b.getCurrentItem();
                    if (currentItem < 0 || currentItem >= rxu.this.f) {
                        currentItem = 0;
                    }
                    int i = rxu.this.f;
                    int i2 = rxu.this.f;
                    int i3 = rxu.this.f;
                    for (int i4 = 0; i4 < rxu.this.b.getChildCount(); i4++) {
                        Object tag = rxu.this.b.getChildAt(i4).getTag();
                        int intValue = tag instanceof Integer ? ((Integer) tag).intValue() : -1;
                        if (intValue == currentItem) {
                            rxu rxuVar = rxu.this;
                            rxuVar.dTF_(rxuVar.b.getChildAt(i4), 0.0f);
                        } else if (intValue == ((currentItem - 1) + i) % i2) {
                            rxu rxuVar2 = rxu.this;
                            rxuVar2.dTF_(rxuVar2.b.getChildAt(i4), -1.0f);
                        } else if (intValue == (currentItem + 1) % i3) {
                            rxu rxuVar3 = rxu.this;
                            rxuVar3.dTF_(rxuVar3.b.getChildAt(i4), 1.0f);
                        }
                    }
                    if (message.what == 2) {
                        rxu.this.d = false;
                    }
                    if (rxu.this.d) {
                        return;
                    }
                    rxu.this.d = true;
                    if (SystemClock.elapsedRealtime() - rxu.this.e > 1000) {
                        rxu.this.e = 0L;
                    } else {
                        sendEmptyMessageAtTime(2, SystemClock.elapsedRealtime() + 20);
                    }
                }
            }
        };
        this.i = f;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.PageTransformer
    public void transformPage(View view, float f) {
        float dTE_ = dTE_(view, f);
        if (this.f16957a != 0) {
            float max = Math.max(1.0f - Math.abs(Math.min(Math.abs(dTE_), 1.0f) * 0.1f), this.i);
            view.setScaleX(max);
            view.setScaleY(max);
        }
    }

    private float dTE_(View view, float f) {
        HwViewPager hwViewPager;
        if (view == null || (hwViewPager = this.b) == null) {
            return sdl.a(f, 0.2f);
        }
        int measuredWidth = (hwViewPager.getMeasuredWidth() - this.b.getPaddingStart()) - this.b.getPaddingEnd();
        if (measuredWidth <= 0) {
            return sdl.a(f, 0.2f);
        }
        return sdl.d((view.getLeft() - this.b.getPaddingStart()) - this.b.getScrollX(), measuredWidth, 3);
    }

    public void dTF_(View view, float f) {
        if (view != null) {
            float max = Math.max(1.0f - Math.abs(Math.min(Math.abs(f), 1.0f) * 0.1f), this.i);
            view.setScaleX(max);
            view.setScaleY(max);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.f16957a = i;
        if (i == 0) {
            this.e = SystemClock.elapsedRealtime();
            Handler handler = this.c;
            handler.handleMessage(Message.obtain(handler, 0));
        } else {
            this.e = 0L;
            this.c.removeMessages(0);
            this.c.removeMessages(2);
            this.d = false;
        }
    }

    public void c(HwViewPager hwViewPager) {
        this.b = hwViewPager;
    }

    public void d(int i) {
        this.f = i;
    }

    public void b() {
        this.f = 0;
        this.f16957a = 0;
        this.b = null;
    }

    public void d() {
        this.c.removeMessages(0);
        this.c.removeMessages(2);
        this.d = false;
        b();
    }
}
