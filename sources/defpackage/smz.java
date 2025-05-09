package defpackage;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import java.util.Locale;

/* loaded from: classes7.dex */
public class smz {

    /* renamed from: a, reason: collision with root package name */
    private float f17132a;
    private float c;
    private HwViewPager d;
    private float b = 0.125f;
    private float e = 0.125f;

    public smz(HwViewPager hwViewPager) {
        this.d = hwViewPager;
    }

    public void a(MotionEvent motionEvent) {
        this.f17132a = motionEvent.getX();
        this.c = motionEvent.getY();
    }

    public void b(float f) {
        this.e = f;
    }

    public void e(float f) {
        this.b = f;
    }

    public void eiC_(MotionEvent motionEvent) {
        if (this.d == null) {
            return;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (!this.d.isPageScrollHorizontal()) {
            c(this.c - y);
            return;
        }
        float f = this.f17132a - x;
        if (b()) {
            f = -f;
        }
        d(f);
    }

    private void d(float f) {
        HwViewPager hwViewPager = this.d;
        if (hwViewPager == null) {
            return;
        }
        int paddingRight = hwViewPager.getPaddingRight();
        int paddingLeft = this.d.getPaddingLeft();
        if (Float.compare(Math.abs(f), ((this.d.getWidth() - paddingLeft) - paddingRight) * this.e) < 0) {
            Log.w("HwPageTurningHelper", "the horizontal condition is not met.");
            return;
        }
        if (f < 0.0f) {
            this.d.prePage(false);
        } else if (f > 0.0f) {
            this.d.nextPage(false, false);
        } else {
            Log.d("HwPageTurningHelper", "the horizontal deltaX is zero.");
        }
    }

    private void c(float f) {
        HwViewPager hwViewPager = this.d;
        if (hwViewPager == null) {
            return;
        }
        int paddingBottom = hwViewPager.getPaddingBottom();
        int paddingTop = this.d.getPaddingTop();
        if (Float.compare(Math.abs(f), ((this.d.getHeight() - paddingTop) - paddingBottom) * this.b) < 0) {
            Log.w("HwPageTurningHelper", "the vertical condition is not met.");
            return;
        }
        if (f < 0.0f) {
            this.d.prePage(false);
        } else if (f > 0.0f) {
            this.d.nextPage(false, false);
        } else {
            Log.d("HwPageTurningHelper", "the vertical deltaY is zero.");
        }
    }

    private boolean b() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
}
