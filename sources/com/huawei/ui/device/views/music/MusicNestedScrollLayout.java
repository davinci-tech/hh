package com.huawei.ui.device.views.music;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.StatusBarClickedListener;
import defpackage.oby;

/* loaded from: classes6.dex */
public class MusicNestedScrollLayout extends NestedScrollView {

    /* renamed from: a, reason: collision with root package name */
    private StatusBarClickedListener f9322a;
    private ViewGroup b;
    private int c;
    private ViewGroup d;
    private boolean e;
    private int j;

    static /* synthetic */ int b(MusicNestedScrollLayout musicNestedScrollLayout, int i) {
        int i2 = musicNestedScrollLayout.c + i;
        musicNestedScrollLayout.c = i2;
        return i2;
    }

    public MusicNestedScrollLayout(Context context) {
        this(context, null);
    }

    public MusicNestedScrollLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MusicNestedScrollLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.e = false;
        e();
    }

    private void e() {
        setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.huawei.ui.device.views.music.MusicNestedScrollLayout.1
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                if (MusicNestedScrollLayout.this.e) {
                    MusicNestedScrollLayout.this.c = 0;
                    MusicNestedScrollLayout.this.e = false;
                }
                if (i2 == MusicNestedScrollLayout.this.getChildAt(0).getMeasuredHeight() - view.getMeasuredHeight()) {
                    LogUtil.c("MusicNestedScrollLayout", "bottom scroll");
                    MusicNestedScrollLayout.this.a();
                }
                MusicNestedScrollLayout.b(MusicNestedScrollLayout.this, i2 - i4);
                ViewScrollInstrumentation.scrollChangeOnView(view, i, i2, i3, i4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int i = this.j;
        if (i != 0) {
            Double valueOf = Double.valueOf(oby.a(i));
            if (valueOf.doubleValue() > this.c) {
                c(oby.d(valueOf.doubleValue() - Double.valueOf(this.c).doubleValue()));
            }
        }
        this.c = 0;
        this.j = 0;
    }

    private void c(int i) {
        RecyclerView cVC_ = cVC_(this.b);
        if (cVC_ != null) {
            cVC_.fling(0, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("MusicNestedScrollLayout", "enter, scrollToTop");
        if (getScrollY() != 0) {
            smoothScrollTo(0, 0);
            RecyclerView cVC_ = cVC_(this.b);
            if (cVC_ != null) {
                cVC_.scrollToPosition(0);
            }
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildAt(0) instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            if (viewGroup.getChildAt(0) instanceof ViewGroup) {
                this.d = (ViewGroup) viewGroup.getChildAt(0);
            }
            if (viewGroup.getChildAt(1) instanceof ViewGroup) {
                this.b = (ViewGroup) viewGroup.getChildAt(1);
            }
        }
    }

    @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
        layoutParams.height = getMeasuredHeight();
        this.b.setLayoutParams(layoutParams);
    }

    @Override // androidx.core.widget.NestedScrollView, androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        if (i2 <= 0 || getScrollY() >= this.d.getMeasuredHeight()) {
            return;
        }
        scrollBy(0, i2);
        iArr[1] = i2;
    }

    @Override // androidx.core.widget.NestedScrollView
    public void fling(int i) {
        super.fling(i);
        if (i <= 0) {
            this.j = 0;
        } else {
            this.e = true;
            this.j = i;
        }
    }

    private RecyclerView cVC_(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof RecyclerView) {
                return (RecyclerView) childAt;
            }
            if (childAt instanceof ViewGroup) {
                RecyclerView cVC_ = cVC_((ViewGroup) childAt);
                if (cVC_ != null) {
                    return cVC_;
                }
            } else {
                LogUtil.h("MusicNestedScrollLayout", "getChildRecyclerView, default.");
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.a("MusicNestedScrollLayout", "onDetachedFromWindow");
        StatusBarClickedListener statusBarClickedListener = this.f9322a;
        if (statusBarClickedListener != null) {
            statusBarClickedListener.unregister();
            this.f9322a = null;
        }
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.a("MusicNestedScrollLayout", "onAttachedToWindow");
        if (this.f9322a == null) {
            this.f9322a = new StatusBarClickedListener() { // from class: com.huawei.ui.device.views.music.MusicNestedScrollLayout.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    MusicNestedScrollLayout.this.b();
                }
            };
        }
    }
}
