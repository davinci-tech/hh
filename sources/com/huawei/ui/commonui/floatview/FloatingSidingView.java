package com.huawei.ui.commonui.floatview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hmf.tasks.OnCompleteListener;
import defpackage.nrr;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class FloatingSidingView extends FrameLayout {
    private View.OnClickListener b;
    private Context c;
    private e d;
    private long e;

    public FloatingSidingView(Context context) {
        this(context, null);
    }

    public FloatingSidingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingSidingView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, 0, 0);
    }

    public FloatingSidingView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = 0L;
        this.c = context;
        d();
    }

    private void d() {
        this.d = new e();
        setClickable(true);
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public void b() {
        this.d.b(nsn.h(BaseApplication.e()), getY());
    }

    public void e() {
        this.d.b(nsn.h(BaseApplication.e()) - nrr.e(this.c, 80.0f), getY());
    }

    public void setCompleteListener(OnCompleteListener onCompleteListener) {
        e eVar = this.d;
        if (eVar != null) {
            eVar.b(onCompleteListener);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getAction() == 1 && !c()) {
            a();
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a() {
        View.OnClickListener onClickListener = this.b;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    private boolean c() {
        boolean z = SystemClock.elapsedRealtime() - this.e < 150;
        if (!z) {
            this.e = SystemClock.elapsedRealtime();
        }
        return z;
    }

    public class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private float f8830a;
        private float e;
        private long f;
        private OnCompleteListener j;
        private Handler d = new Handler(Looper.getMainLooper());
        private int b = 500;

        public e() {
        }

        public void b(float f, float f2) {
            this.e = f;
            this.f8830a = f2;
            this.f = System.currentTimeMillis();
            this.d.post(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(OnCompleteListener onCompleteListener) {
            this.j = onCompleteListener;
        }

        public void a() {
            Handler handler = this.d;
            if (handler == null) {
                return;
            }
            handler.removeCallbacks(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FloatingSidingView.this.getRootView() == null || FloatingSidingView.this.getRootView().getParent() == null) {
                return;
            }
            float min = Math.min(1.0f, (System.currentTimeMillis() - this.f) / (this.b * 1.0f));
            FloatingSidingView.this.d((this.e - FloatingSidingView.this.getX()) * min, (this.f8830a - FloatingSidingView.this.getY()) * min);
            if (min < 1.0f) {
                this.d.post(this);
                return;
            }
            OnCompleteListener onCompleteListener = this.j;
            if (onCompleteListener != null) {
                onCompleteListener.onComplete(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(float f, float f2) {
        setX(getX() + f);
        setY(getY() + f2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        e eVar = this.d;
        if (eVar != null) {
            eVar.a();
        }
        super.onDetachedFromWindow();
    }
}
