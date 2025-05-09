package com.huawei.health.recognizekit.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class TabItemDecoration extends RecyclerView.ItemDecoration {
    private int d = 10;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int c;
        int c2;
        if (!(recyclerView.getAdapter() instanceof RecognizeModeRecyclerAdapter)) {
            LogUtil.c("TabItemDecoration", "adapter invalid");
            return;
        }
        Context e = BaseApplication.e();
        RecognizeModeRecyclerAdapter recognizeModeRecyclerAdapter = (RecognizeModeRecyclerAdapter) recyclerView.getAdapter();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = recognizeModeRecyclerAdapter.getItemCount();
        int h = nsn.h(e);
        if (childAdapterPosition == 0) {
            c = (int) ((h - recognizeModeRecyclerAdapter.d(childAdapterPosition)) / 2.0f);
        } else {
            c = nsn.c(e, this.d);
        }
        LogUtil.d("TabItemDecoration", "screenWidth:", Integer.valueOf(h), ",view.width:", Float.valueOf(recognizeModeRecyclerAdapter.d(childAdapterPosition)), "leftMargin:", Integer.valueOf(c));
        if (childAdapterPosition == itemCount - 1) {
            c2 = (int) ((h - recognizeModeRecyclerAdapter.d(childAdapterPosition)) / 2.0f);
        } else {
            c2 = nsn.c(e, this.d);
        }
        LogUtil.d("TabItemDecoration", "screenWidth:", Integer.valueOf(h), ",view.width:", Float.valueOf(recognizeModeRecyclerAdapter.d(childAdapterPosition)), "rightMargin:", Integer.valueOf(c2));
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (LanguageUtil.bc(BaseApplication.e())) {
            layoutParams.setMargins(c2, 0, c, 0);
        } else {
            layoutParams.setMargins(c, 0, c2, 0);
        }
        view.setLayoutParams(layoutParams);
        super.getItemOffsets(rect, view, recyclerView, state);
    }
}
