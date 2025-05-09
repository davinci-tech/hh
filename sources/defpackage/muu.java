package defpackage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class muu implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    private int f15187a;
    private int b;
    private int c;
    private int d;
    private HealthScrollView e;

    public muu(HealthScrollView healthScrollView, int i, int i2) {
        this.e = healthScrollView;
        this.b = i;
        this.f15187a = i2;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.e.requestDisallowInterceptTouchEvent(true);
            this.c = (int) motionEvent.getRawX();
            this.d = (int) motionEvent.getRawY();
        } else if (action == 1) {
            cpJ_(view);
        } else if (action == 2) {
            this.e.requestDisallowInterceptTouchEvent(true);
            int rawX = ((int) motionEvent.getRawX()) - this.c;
            int rawY = ((int) motionEvent.getRawY()) - this.d;
            int left = view.getLeft() + rawX;
            int top = view.getTop() + rawY;
            int right = view.getRight() + rawX;
            int bottom = view.getBottom() + rawY;
            if (left < 0) {
                right = view.getWidth();
                left = 0;
            }
            int i = this.b;
            if (right > i) {
                left = i - view.getWidth();
                right = i;
            }
            if (top < 0) {
                bottom = view.getHeight();
                top = 0;
            }
            int i2 = this.f15187a;
            if (bottom > i2) {
                top = i2 - view.getHeight();
                bottom = i2;
            }
            view.layout(left, top, right, bottom);
            this.c = (int) motionEvent.getRawX();
            this.d = (int) motionEvent.getRawY();
        }
        return true;
    }

    private void cpJ_(View view) {
        this.e.requestDisallowInterceptTouchEvent(false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            layoutParams.setMargins(0, view.getTop(), (this.b - view.getWidth()) - view.getLeft(), 0);
        } else {
            layoutParams.setMargins(view.getLeft(), view.getTop(), 0, 0);
        }
        view.setLayoutParams(layoutParams);
    }
}
