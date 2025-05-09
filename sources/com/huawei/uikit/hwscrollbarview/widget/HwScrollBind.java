package com.huawei.uikit.hwscrollbarview.widget;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes7.dex */
public interface HwScrollBind {
    void bindListView(AbsListView absListView, HwScrollbarView hwScrollbarView, boolean z);

    void bindRecyclerView(RecyclerView recyclerView, HwScrollbarView hwScrollbarView, boolean z);

    void bindScrollView(ScrollView scrollView, HwScrollbarView hwScrollbarView, boolean z);

    void onScrollableViewTouchEvent(View view, HwScrollbarView hwScrollbarView, MotionEvent motionEvent);
}
