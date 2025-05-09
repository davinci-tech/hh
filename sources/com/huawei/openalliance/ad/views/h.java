package com.huawei.openalliance.ad.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.hk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dd;
import java.util.Arrays;

/* loaded from: classes5.dex */
public abstract class h extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    protected View f8087a;
    protected View b;
    protected View c;
    protected float d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    protected Boolean i;
    protected ViewTreeObserver.OnGlobalLayoutListener j;
    private int[] k;
    private int[] l;

    public interface a {
        void l();
    }

    protected abstract void a(Context context);

    protected abstract void b(Context context);

    protected abstract void c();

    public void e() {
    }

    public void setAdContent(ContentRecord contentRecord) {
    }

    public void setFeedbackListener(com.huawei.openalliance.ad.views.feedback.b bVar) {
    }

    public void setScreenLockCallBack(a aVar) {
    }

    public void setViewClickListener(hk hkVar) {
    }

    public void setShowWhyThisAd(boolean z) {
        this.i = Boolean.valueOf(z);
    }

    public void setPaddingStart(int i) {
        if (dd.c()) {
            this.g = 0;
            this.h = i;
        } else {
            this.g = i;
            this.h = 0;
        }
        c();
    }

    public int getViewWith() {
        return this.f;
    }

    public float getViewWidthPercent() {
        return this.d;
    }

    protected boolean d() {
        return (this.l == null || this.k == null) ? false : true;
    }

    public void d(Context context) {
        int i;
        int i2;
        if (this.c != null) {
            int b = com.huawei.openalliance.ad.utils.d.b(context);
            int a2 = com.huawei.openalliance.ad.utils.d.a(context);
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= 30) {
                    Activity activity = (Activity) context;
                    i = activity.getWindowManager().getCurrentWindowMetrics().getBounds().width();
                    i2 = activity.getWindowManager().getCurrentWindowMetrics().getBounds().height();
                } else {
                    Point point = new Point();
                    ((Activity) context).getWindowManager().getDefaultDisplay().getSize(point);
                    i = point.x;
                    i2 = point.y;
                }
                int i3 = i;
                a2 = i2;
                b = i3;
            }
            ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
            int min = (int) ((dd.k(context) == 1 ? b : Math.min(b, a2)) * this.d);
            this.f = min;
            layoutParams.width = min;
            this.c.setLayoutParams(layoutParams);
        }
    }

    protected void c(Context context) {
        this.d = (com.huawei.openalliance.ad.utils.x.n(context) || (com.huawei.openalliance.ad.utils.x.q(context) && com.huawei.openalliance.ad.utils.x.r(context))) ? 0.6f : 0.86f;
    }

    public void a(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr2 == null) {
            return;
        }
        this.k = Arrays.copyOf(iArr, iArr.length);
        this.l = Arrays.copyOf(iArr2, iArr2.length);
    }

    public void a(int i) {
        int i2 = this.g;
        if (i2 > i) {
            this.g = i2 - i;
        }
        int i3 = this.h;
        if (i3 > i) {
            this.h = i3 - i;
        }
        c();
    }

    private void e(Context context) {
        try {
            a(context);
            c(context);
            d(context);
            b(context);
            c();
        } catch (Throwable th) {
            ho.c("PPSBaseDialogContentView", "init ex: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(View view, int i) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    public h(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = (int) (com.huawei.openalliance.ad.utils.d.a(getContext()) * 0.8f);
        this.j = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.openalliance.ad.views.h.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                try {
                    if (h.this.b == null) {
                        return;
                    }
                    h.this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int measuredHeight = h.this.b.getMeasuredHeight();
                    h hVar = h.this;
                    hVar.a(hVar.b, Math.min(measuredHeight, h.this.e));
                } catch (Throwable th) {
                    ho.c("PPSBaseDialogContentView", "onGlobalLayout error: %s", th.getClass().getSimpleName());
                }
            }
        };
        e(context);
    }

    public h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = (int) (com.huawei.openalliance.ad.utils.d.a(getContext()) * 0.8f);
        this.j = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.openalliance.ad.views.h.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                try {
                    if (h.this.b == null) {
                        return;
                    }
                    h.this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int measuredHeight = h.this.b.getMeasuredHeight();
                    h hVar = h.this;
                    hVar.a(hVar.b, Math.min(measuredHeight, h.this.e));
                } catch (Throwable th) {
                    ho.c("PPSBaseDialogContentView", "onGlobalLayout error: %s", th.getClass().getSimpleName());
                }
            }
        };
        e(context);
    }

    public h(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = (int) (com.huawei.openalliance.ad.utils.d.a(getContext()) * 0.8f);
        this.j = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.openalliance.ad.views.h.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                try {
                    if (h.this.b == null) {
                        return;
                    }
                    h.this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int measuredHeight = h.this.b.getMeasuredHeight();
                    h hVar = h.this;
                    hVar.a(hVar.b, Math.min(measuredHeight, h.this.e));
                } catch (Throwable th) {
                    ho.c("PPSBaseDialogContentView", "onGlobalLayout error: %s", th.getClass().getSimpleName());
                }
            }
        };
        e(context);
    }

    public h(Context context) {
        super(context);
        this.e = (int) (com.huawei.openalliance.ad.utils.d.a(getContext()) * 0.8f);
        this.j = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.openalliance.ad.views.h.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                try {
                    if (h.this.b == null) {
                        return;
                    }
                    h.this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int measuredHeight = h.this.b.getMeasuredHeight();
                    h hVar = h.this;
                    hVar.a(hVar.b, Math.min(measuredHeight, h.this.e));
                } catch (Throwable th) {
                    ho.c("PPSBaseDialogContentView", "onGlobalLayout error: %s", th.getClass().getSimpleName());
                }
            }
        };
        e(context);
    }
}
