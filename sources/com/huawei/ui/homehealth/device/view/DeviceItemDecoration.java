package com.huawei.ui.homehealth.device.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class DeviceItemDecoration extends RecyclerView.ItemDecoration {
    private int b;
    private int c;

    public DeviceItemDecoration(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.top = this.c;
        rect.bottom = this.c;
        GridLayoutManager.LayoutParams layoutParams = view.getLayoutParams() instanceof GridLayoutManager.LayoutParams ? (GridLayoutManager.LayoutParams) view.getLayoutParams() : null;
        if (layoutParams != null) {
            cZQ_(rect, recyclerView, layoutParams);
        }
    }

    private void cZQ_(Rect rect, RecyclerView recyclerView, GridLayoutManager.LayoutParams layoutParams) {
        if (layoutParams.getSpanIndex() != -1) {
            rect.right = 0;
            rect.left = 0;
            if (recyclerView.getAdapter().getItemCount() == 1) {
                return;
            }
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                if (layoutParams.getSpanIndex() % 2 == 0) {
                    rect.left = this.b / 2;
                    return;
                } else {
                    rect.right = this.b / 2;
                    return;
                }
            }
            if (layoutParams.getSpanIndex() % 2 == 0) {
                rect.right = this.b / 2;
            } else {
                rect.left = this.b / 2;
            }
        }
    }
}
