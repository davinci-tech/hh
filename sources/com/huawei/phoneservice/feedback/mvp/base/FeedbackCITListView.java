package com.huawei.phoneservice.feedback.mvp.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;

/* loaded from: classes5.dex */
public class FeedbackCITListView extends RecyclerView {
    private RecyclerView.ItemDecoration b;
    private boolean d;

    public void setmDisableScroll(boolean z) {
        this.d = z;
    }

    public void setColumnCount(int i) {
        RecyclerView.ItemDecoration itemDecoration = this.b;
        if (itemDecoration instanceof com.huawei.phoneservice.feedback.widget.decortion.a) {
            ((com.huawei.phoneservice.feedback.widget.decortion.a) itemDecoration).a(i);
        }
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanCount(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (!isInEditMode() && !(adapter instanceof com.huawei.phoneservice.feedback.widget.b)) {
            throw new IllegalArgumentException("MUST use CITListAdapter!");
        }
        super.setAdapter(adapter);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.d) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.d = getLayoutParams().height == -2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public com.huawei.phoneservice.feedback.widget.b getAdapter() {
        return (com.huawei.phoneservice.feedback.widget.b) super.getAdapter();
    }

    public void e(int i, int i2) {
        RecyclerView.ItemDecoration itemDecoration = this.b;
        if (itemDecoration instanceof com.huawei.phoneservice.feedback.widget.decortion.a) {
            ((com.huawei.phoneservice.feedback.widget.decortion.a) itemDecoration).b(i);
            ((com.huawei.phoneservice.feedback.widget.decortion.a) this.b).e(i2);
        }
    }

    private void cew_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100067_res_0x7f0601a3, R.attr._2131100068_res_0x7f0601a4, R.attr._2131100069_res_0x7f0601a5, R.attr._2131100072_res_0x7f0601a8}, i, 0);
        if (obtainStyledAttributes.getInteger(2, 0) != 1) {
            setLayoutManager(new LinearLayoutManager(context));
        } else {
            this.b = new com.huawei.phoneservice.feedback.widget.decortion.a();
            int integer = obtainStyledAttributes.getInteger(0, 2);
            setLayoutManager(new GridLayoutManager(context, integer));
            addItemDecoration(this.b);
            setColumnCount(integer);
            e(obtainStyledAttributes.getDimensionPixelOffset(3, 0), obtainStyledAttributes.getDimensionPixelOffset(1, 0));
        }
        obtainStyledAttributes.recycle();
    }

    public FeedbackCITListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.b = null;
        cew_(context, attributeSet, i);
    }

    public FeedbackCITListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.b = null;
        cew_(context, attributeSet, 0);
    }

    public FeedbackCITListView(Context context) {
        super(context);
        this.d = false;
        this.b = null;
        cew_(context, null, 0);
    }
}
