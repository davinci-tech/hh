package com.huawei.pluginachievement.ui.views;

import android.view.View;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes6.dex */
public class CardLinearSnapHelper extends LinearSnapHelper {
    private boolean d = true;

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        return !this.d ? new int[]{0, 0} : super.calculateDistanceToFinalSnap(layoutManager, view);
    }

    public void c(boolean z) {
        this.d = z;
    }
}
