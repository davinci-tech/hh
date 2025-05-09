package com.huawei.health.knit.section.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ccq;

/* loaded from: classes3.dex */
public class FitnessItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private Context f2612a;
    private int b;
    private int e;

    public FitnessItemDecoration(Context context, int i, int i2) {
        this.f2612a = context;
        this.e = i;
        this.b = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (rect == null || recyclerView == null) {
            LogUtil.h("Section_FitnessItemDecoration", "getItemOffsets, outRect or parent is null");
            return;
        }
        super.getItemOffsets(rect, view, recyclerView, state);
        Context context = this.f2612a;
        int i = this.e;
        ccq.Db_(rect, view, recyclerView, 2, context, i, i, 2, false);
    }
}
