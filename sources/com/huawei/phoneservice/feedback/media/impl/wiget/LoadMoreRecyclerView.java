package com.huawei.phoneservice.feedback.media.impl.wiget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes9.dex */
public class LoadMoreRecyclerView extends RecyclerView {

    /* renamed from: a, reason: collision with root package name */
    private int f8258a;
    private int b;
    private boolean c;
    private int d;
    private boolean e;
    private b h;
    private a j;

    public interface a {
        void a();
    }

    public interface b {
        void a();

        void b();
    }

    public void setReachBottomRow(int i) {
        if (i < 1) {
            i = 1;
        }
        this.d = i;
    }

    public void setOnRecyclerViewScrollStateListener(b bVar) {
        this.h = bVar;
    }

    public void setOnRecyclerViewPreloadListener(a aVar) {
        this.j = aVar;
    }

    public void setEnabledLoadMore(boolean z) {
        this.e = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0046, code lost:
    
        if (r4 == 0) goto L22;
     */
    @Override // androidx.recyclerview.widget.RecyclerView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onScrolled(int r3, int r4) {
        /*
            r2 = this;
            super.onScrolled(r3, r4)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r2.getLayoutManager()
            if (r3 != 0) goto La
            return
        La:
            r2.setLayoutManagerPosition(r3)
            com.huawei.phoneservice.feedback.media.impl.wiget.LoadMoreRecyclerView$a r0 = r2.j
            if (r0 == 0) goto L4b
            boolean r0 = r2.e
            if (r0 == 0) goto L4b
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = r2.getAdapter()
            if (r0 != 0) goto L1c
            return
        L1c:
            boolean r1 = r3 instanceof androidx.recyclerview.widget.GridLayoutManager
            if (r1 == 0) goto L48
            androidx.recyclerview.widget.GridLayoutManager r3 = (androidx.recyclerview.widget.GridLayoutManager) r3
            int r0 = r0.getItemCount()
            int r1 = r3.getSpanCount()
            int r0 = r0 / r1
            int r1 = r3.findLastVisibleItemPosition()
            int r3 = r3.getSpanCount()
            int r1 = r1 / r3
            int r3 = r2.d
            int r0 = r0 - r3
            if (r1 < r0) goto L48
            boolean r3 = r2.c
            if (r3 != 0) goto L46
            com.huawei.phoneservice.feedback.media.impl.wiget.LoadMoreRecyclerView$a r3 = r2.j
            r3.a()
            if (r4 <= 0) goto L4b
            r3 = 1
            goto L49
        L46:
            if (r4 != 0) goto L4b
        L48:
            r3 = 0
        L49:
            r2.c = r3
        L4b:
            com.huawei.phoneservice.feedback.media.impl.wiget.LoadMoreRecyclerView$b r3 = r2.h
            if (r3 == 0) goto L62
            int r3 = java.lang.Math.abs(r4)
            r4 = 150(0x96, float:2.1E-43)
            if (r3 >= r4) goto L5d
            com.huawei.phoneservice.feedback.media.impl.wiget.LoadMoreRecyclerView$b r3 = r2.h
            r3.a()
            goto L62
        L5d:
            com.huawei.phoneservice.feedback.media.impl.wiget.LoadMoreRecyclerView$b r3 = r2.h
            r3.b()
        L62:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedback.media.impl.wiget.LoadMoreRecyclerView.onScrolled(int, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i) {
        b bVar;
        super.onScrollStateChanged(i);
        if (i == 0 || i == 1) {
            setLayoutManagerPosition(getLayoutManager());
        }
        if (i != 0 || (bVar = this.h) == null) {
            return;
        }
        bVar.a();
    }

    private void setLayoutManagerPosition(RecyclerView.LayoutManager layoutManager) {
        int findLastVisibleItemPosition;
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            this.b = gridLayoutManager.findFirstVisibleItemPosition();
            findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        } else {
            if (!(layoutManager instanceof LinearLayoutManager)) {
                return;
            }
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            this.b = linearLayoutManager.findFirstVisibleItemPosition();
            findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        }
        this.f8258a = findLastVisibleItemPosition;
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.e = false;
        this.d = 1;
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
        this.e = false;
        this.d = 1;
    }

    public LoadMoreRecyclerView(Context context) {
        super(context);
        this.c = false;
        this.e = false;
        this.d = 1;
    }
}
