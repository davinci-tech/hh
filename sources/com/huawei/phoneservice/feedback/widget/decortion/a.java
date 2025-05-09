package com.huawei.phoneservice.feedback.widget.decortion;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes9.dex */
public class a extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f8305a;
    private int b = Integer.MAX_VALUE;
    private int c;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.top = this.f8305a;
        int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
        int i = this.b;
        if (childLayoutPosition < i) {
            rect.top = 0;
        }
        if (i != Integer.MAX_VALUE) {
            float f = (((i - 1) * r6) * 1.0f) / i;
            float f2 = (childLayoutPosition % i) * (this.c - f);
            rect.left = (int) f2;
            rect.right = (int) (f - f2);
        }
    }

    public void b(int i) {
        this.f8305a = i;
    }

    public void e(int i) {
        this.c = i;
    }

    public void a(int i) {
        this.b = i;
    }
}
