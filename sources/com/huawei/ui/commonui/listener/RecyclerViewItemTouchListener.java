package com.huawei.ui.commonui.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public abstract class RecyclerViewItemTouchListener implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "RecyclerViewItemTouchListener";
    private GestureDetectorCompat mGestureDetectorCompat;

    public abstract void onItemTouchDown(RecyclerView.ViewHolder viewHolder);

    public RecyclerViewItemTouchListener(RecyclerView recyclerView) {
        this.mGestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new b(recyclerView, this));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mGestureDetectorCompat.onTouchEvent(motionEvent);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        LogUtil.a(TAG, "onTouchEvent:", Integer.valueOf(motionEvent.getAction()));
        this.mGestureDetectorCompat.onTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
        LogUtil.a(TAG, "onRequestDisallowInterceptTouchEvent");
    }

    static class b extends GestureDetector.SimpleOnGestureListener {
        private WeakReference<RecyclerView> c;
        private RecyclerViewItemTouchListener e;

        b(RecyclerView recyclerView, RecyclerViewItemTouchListener recyclerViewItemTouchListener) {
            this.c = new WeakReference<>(recyclerView);
            this.e = recyclerViewItemTouchListener;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            WeakReference<RecyclerView> weakReference = this.c;
            if (weakReference == null || weakReference.get() == null) {
                return false;
            }
            RecyclerView recyclerView = this.c.get();
            View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder == null || this.e == null) {
                return true;
            }
            this.e.onItemTouchDown(recyclerView.getChildViewHolder(findChildViewUnder));
            return true;
        }
    }
}
