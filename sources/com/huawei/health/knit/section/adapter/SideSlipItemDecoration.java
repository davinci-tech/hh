package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import defpackage.nrr;
import java.util.List;

/* loaded from: classes8.dex */
public class SideSlipItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f2594a;
    private List<String> b;
    private Context d;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.left = this.f2594a;
        rect.right = this.f2594a;
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            rect.left = nrr.e(this.d, R.dimen._2131362009_res_0x7f0a00d9);
        }
        if (recyclerView.getChildAdapterPosition(view) == this.b.size() - 1) {
            rect.right = nrr.e(this.d, R.dimen._2131362007_res_0x7f0a00d7);
        }
    }
}
