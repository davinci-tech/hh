package com.huawei.ui.commonui.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.operation.utils.Constants;
import com.huawei.uikit.phone.hwrecyclerview.widget.HwRecyclerView;
import defpackage.nsf;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class HealthRecycleView extends HwRecyclerView {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8924a;
    private boolean b;
    private boolean c;
    private boolean e;
    private boolean f;
    private boolean g;
    private int j;

    public HealthRecycleView(Context context) {
        super(context);
        this.f = true;
        this.b = false;
        this.e = false;
        this.c = false;
        this.f8924a = false;
        d();
    }

    public HealthRecycleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = true;
        this.b = false;
        this.e = false;
        this.c = false;
        this.f8924a = false;
        d();
    }

    public HealthRecycleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = true;
        this.b = false;
        this.e = false;
        this.c = false;
        this.f8924a = false;
        d();
    }

    private void d() {
        setOverScrollMode(2);
    }

    public void setIsScroll(boolean z) {
        this.f = z;
    }

    public void setIsIntercept(boolean z) {
        this.b = z;
    }

    public void setIsConsumption(boolean z) {
        this.e = z;
    }

    public void setIsConsumptionMoving(boolean z) {
        this.c = z;
    }

    public void setIsFling(boolean z) {
        this.f8924a = z;
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.g) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            this.j = (int) motionEvent.getY();
        } else if (action == 2 && this.g && cEJ_(motionEvent)) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean cEJ_(MotionEvent motionEvent) {
        return (v() && ((motionEvent.getY() - ((float) this.j)) > 0.0f ? 1 : ((motionEvent.getY() - ((float) this.j)) == 0.0f ? 0 : -1)) > 0) || (e() && ((motionEvent.getY() - ((float) this.j)) > 0.0f ? 1 : ((motionEvent.getY() - ((float) this.j)) == 0.0f ? 0 : -1)) < 0);
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.b) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.e) {
            return true;
        }
        if (this.c && motionEvent.getAction() == 2) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchWindowFocusChanged(boolean z) {
        super.dispatchWindowFocusChanged(z);
        setScrollTopEnable(z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        try {
            if (!this.f) {
                super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
            } else {
                super.onMeasure(i, i2);
            }
        } catch (Exception e) {
            int id = getId();
            String f = nsf.f(id);
            StringBuilder sb = new StringBuilder("on measure recycler view exception happened,");
            sb.append(e);
            sb.append(", id = ");
            sb.append(id);
            sb.append(" resourceName ");
            sb.append(f);
            sb.append(", adapter = ");
            sb.append(getAdapter() != null ? getAdapter() : Constants.NULL);
            String sb2 = sb.toString();
            LogUtil.e("HealthRecycleView", sb2);
            throw new RuntimeException(sb2 + System.lineSeparator() + DfxUtils.d(Thread.currentThread(), e));
        }
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i, int i2) {
        if (this.f8924a) {
            if (Math.abs(i) >= 60) {
                return super.fling(i, i2);
            }
            if (i < 0) {
                return super.fling(-600, i2);
            }
            return super.fling(600, i2);
        }
        return super.fling(i, i2);
    }

    public void setIsPriorityScroll(boolean z) {
        this.g = z;
    }

    private boolean e() {
        return computeVerticalScrollExtent() + computeVerticalScrollOffset() >= computeVerticalScrollRange();
    }

    private boolean v() {
        return computeVerticalScrollOffset() <= 0;
    }
}
