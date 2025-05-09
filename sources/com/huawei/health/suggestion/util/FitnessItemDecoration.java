package com.huawei.health.suggestion.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class FitnessItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f3431a;
    private Context b;
    private int e;

    public FitnessItemDecoration(Context context, int i, int i2) {
        this.b = context;
        this.f3431a = i;
        this.e = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int c;
        if (rect == null || recyclerView == null) {
            LogUtil.h("Suggestion_FitnessItemDecoration", "getItemOffsets, outRect or parent is null");
            return;
        }
        super.getItemOffsets(rect, view, recyclerView, state);
        int i = 0;
        if (recyclerView.getChildAdapterPosition(view) % 2 != 0) {
            i = nsn.c(this.b, this.f3431a / 2.0f);
            c = 0;
        } else {
            c = nsn.c(this.b, this.f3431a / 2.0f);
        }
        if (LanguageUtil.bc(this.b)) {
            int i2 = i;
            i = c;
            c = i2;
        }
        rect.set(i, nsn.c(this.b, this.e / 2.0f), c, nsn.c(this.b, this.e / 2.0f));
    }
}
