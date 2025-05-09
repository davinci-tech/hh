package com.huawei.ui.homehealth.device.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class RecommendItemDecoration extends RecyclerView.ItemDecoration {
    private int c;
    private int d;

    public RecommendItemDecoration(int i, int i2) {
        this.d = i;
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
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        if (layoutParams.getSpanIndex() != -1) {
            cZW_(rect, 0);
            cZV_(rect, 0);
            int itemCount = recyclerView.getAdapter().getItemCount();
            if (itemCount == 1) {
                return;
            }
            if (nsn.ag(BaseApplication.getContext())) {
                if (itemCount > 4) {
                    itemCount = 4;
                }
                cZX_(layoutParams, rect, itemCount);
            } else if (view.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
                GridLayoutManager.LayoutParams layoutParams2 = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    if (layoutParams2.getSpanIndex() % 2 == 0) {
                        cZV_(rect, this.d / 2);
                        return;
                    } else {
                        cZW_(rect, this.d / 2);
                        return;
                    }
                }
                if (layoutParams2.getSpanIndex() % 2 == 0) {
                    cZW_(rect, this.d / 2);
                } else {
                    cZV_(rect, this.d / 2);
                }
            }
        }
    }

    private void cZX_(GridLayoutManager.LayoutParams layoutParams, Rect rect, int i) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            if ((layoutParams.getSpanIndex() + 1) % i == 1) {
                cZV_(rect, this.d / 2);
                return;
            } else if ((layoutParams.getSpanIndex() + 1) % i == 0) {
                cZW_(rect, this.d / 2);
                return;
            } else {
                cZV_(rect, this.d / 2);
                cZW_(rect, this.d / 2);
                return;
            }
        }
        if ((layoutParams.getSpanIndex() + 1) % i == 1) {
            cZW_(rect, this.d / 2);
        } else if ((layoutParams.getSpanIndex() + 1) % i == 0) {
            cZV_(rect, this.d / 2);
        } else {
            cZV_(rect, this.d / 2);
            cZW_(rect, this.d / 2);
        }
    }

    private void cZW_(Rect rect, int i) {
        rect.right = i;
    }

    private void cZV_(Rect rect, int i) {
        rect.left = i;
    }
}
