package com.huawei.ui.commonui.tablewidget;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import androidx.core.view.GestureDetectorCompat;

/* loaded from: classes6.dex */
public class ScrollHelper implements GestureDetector.OnGestureListener {
    private ScrollHelperListener c;
    private final GestureDetectorCompat e;

    public interface ScrollHelperListener {
        boolean onActionUp(MotionEvent motionEvent);

        boolean onDown(MotionEvent motionEvent);

        boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        void onLongPress(MotionEvent motionEvent);

        boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        boolean onSingleTapUp(MotionEvent motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    public ScrollHelper(Context context) {
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(context, this);
        this.e = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(true);
    }

    public void b(ScrollHelperListener scrollHelperListener) {
        this.c = scrollHelperListener;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        ScrollHelperListener scrollHelperListener = this.c;
        return scrollHelperListener == null || scrollHelperListener.onDown(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        ScrollHelperListener scrollHelperListener = this.c;
        return scrollHelperListener != null && scrollHelperListener.onSingleTapUp(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        ScrollHelperListener scrollHelperListener = this.c;
        return scrollHelperListener != null && scrollHelperListener.onScroll(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        ScrollHelperListener scrollHelperListener = this.c;
        if (scrollHelperListener != null) {
            scrollHelperListener.onLongPress(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        ScrollHelperListener scrollHelperListener = this.c;
        return scrollHelperListener == null || scrollHelperListener.onFling(motionEvent, motionEvent2, f, f2);
    }

    public boolean cGG_(MotionEvent motionEvent) {
        ScrollHelperListener scrollHelperListener;
        if (motionEvent.getAction() == 1 && (scrollHelperListener = this.c) != null) {
            scrollHelperListener.onActionUp(motionEvent);
        }
        return this.e.onTouchEvent(motionEvent);
    }

    public static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Scroller f8958a;
        private int c;
        private final View d;
        private int e;

        a(View view) {
            this.d = view;
            this.f8958a = new Scroller(view.getContext());
        }

        void b(int i, int i2, int i3, int i4, int i5, int i6) {
            this.f8958a.fling(i, i2, i3, i4, 0, i5, 0, i6);
            this.c = i;
            this.e = i2;
            this.d.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f8958a.isFinished()) {
                return;
            }
            boolean computeScrollOffset = this.f8958a.computeScrollOffset();
            int currX = this.f8958a.getCurrX();
            int currY = this.f8958a.getCurrY();
            int i = this.c - currX;
            int i2 = this.e - currY;
            if (i != 0 || i2 != 0) {
                this.d.scrollBy(i, i2);
                this.c = currX;
                this.e = currY;
            }
            if (computeScrollOffset) {
                this.d.post(this);
            }
        }

        boolean a() {
            return this.f8958a.isFinished();
        }

        void d() {
            if (this.f8958a.isFinished()) {
                return;
            }
            this.f8958a.forceFinished(true);
        }
    }
}
