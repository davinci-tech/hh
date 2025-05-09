package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class PullRecyclerViewGroup extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3423a;
    private int b;
    private List<Rect> c;
    private int d;
    private View e;
    private List<View> g;
    private Rect h;
    private float j;

    public PullRecyclerViewGroup(Context context) {
        this(context, null);
    }

    public PullRecyclerViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PullRecyclerViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new Rect();
        this.g = new ArrayList(16);
        this.c = new ArrayList(16);
        this.f3423a = false;
        c();
    }

    private void c() {
        setVerticalScrollBarEnabled(false);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        for (int i = 0; i < getChildCount(); i++) {
            if ((getChildAt(i) instanceof RecyclerView) || (getChildAt(i) instanceof ListView) || (getChildAt(i) instanceof HealthScrollView)) {
                if (this.e == null) {
                    this.e = getChildAt(i);
                } else {
                    throw new RuntimeException("PullRecyclerViewGroup on have one RecyclerView or ListView or HealthScrollView");
                }
            }
        }
        if (this.e == null) {
            throw new RuntimeException("PullRecyclerViewGroup mChildView is null.");
        }
        super.onFinishInflate();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.h.set(this.e.getLeft(), this.e.getTop(), this.e.getRight(), this.e.getBottom());
        for (int i5 = 0; i5 < this.g.size(); i5++) {
            this.g.get(i5).addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.huawei.health.suggestion.ui.view.PullRecyclerViewGroup.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
                    Rect rect = new Rect();
                    rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                    PullRecyclerViewGroup.this.c.add(rect);
                    view.removeOnLayoutChangeListener(this);
                }
            });
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getY() >= this.h.bottom || motionEvent.getY() <= this.h.top) {
            if (this.f3423a) {
                e();
            }
            motionEvent.setAction(1);
        }
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception e) {
            LogUtil.b("Suggestion_PullRecyclerViewGroup", LogAnonymous.b((Object) e));
            return false;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.j = motionEvent.getY();
        } else if (action != 1) {
            if (action == 2) {
                aMo_(motionEvent);
            }
        } else if (this.f3423a) {
            e();
        }
        return super.onTouchEvent(motionEvent);
    }

    private void aMo_(MotionEvent motionEvent) {
        int y = (int) (motionEvent.getY() - this.j);
        if (!b() && y < 0) {
            int i = (int) (y * 0.5f);
            this.e.layout(this.h.left, this.h.top + i, this.h.right, this.h.bottom + i);
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                if (this.g.get(i2) != null) {
                    this.g.get(i2).layout(this.c.get(i2).left, this.c.get(i2).top + i, this.c.get(i2).right, this.c.get(i2).bottom + i);
                }
            }
            this.f3423a = true;
        } else {
            this.j = motionEvent.getY();
            this.f3423a = false;
            e();
        }
        this.d = (int) motionEvent.getX();
        this.b = (int) motionEvent.getY();
    }

    private void e() {
        if (this.f3423a) {
            for (int i = 0; i < this.g.size(); i++) {
                if (this.c.get(i) != null) {
                    TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, this.g.get(i).getTop(), this.c.get(i).top);
                    translateAnimation.setDuration(400L);
                    this.g.get(i).startAnimation(translateAnimation);
                    this.g.get(i).layout(this.c.get(i).left, this.c.get(i).top, this.c.get(i).right, this.c.get(i).bottom);
                }
            }
            TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, this.e.getTop() - this.h.top, 0.0f);
            translateAnimation2.setDuration(400L);
            this.e.startAnimation(translateAnimation2);
            this.e.layout(this.h.left, this.h.top, this.h.right, this.h.bottom);
            this.f3423a = false;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.j = motionEvent.getY();
            this.b = (int) motionEvent.getY();
            this.d = (int) motionEvent.getX();
        } else if (action != 1 && action == 2) {
            float y = motionEvent.getY();
            float x = motionEvent.getX();
            int i = (int) (y - this.j);
            int i2 = (int) (y - this.b);
            int i3 = (int) (x - this.d);
            if (!b() && i < 0 && Math.abs(i2) > Math.abs(i3)) {
                z = true;
            }
            this.b = (int) motionEvent.getY();
            this.d = (int) motionEvent.getX();
        }
        return z;
    }

    private boolean b() {
        View view = this.e;
        if (view != null) {
            return ViewCompat.canScrollVertically(view, 1);
        }
        return true;
    }
}
