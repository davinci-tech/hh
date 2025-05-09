package defpackage;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;

/* loaded from: classes7.dex */
public class smg {

    /* renamed from: a, reason: collision with root package name */
    private float f17121a;
    private float b;
    private HwRecyclerView c;
    private float d;
    private float e;
    private Deque<Integer> j;

    public smg(HwRecyclerView hwRecyclerView) {
        if (hwRecyclerView == null) {
            Log.e("HwPageTurningScrollHelper", "The given hwRecyclerView can not be null!");
            return;
        }
        this.c = hwRecyclerView;
        this.j = new LinkedList();
        this.b = 0.125f;
        this.f17121a = 0.125f;
    }

    private void a(RecyclerView.LayoutManager layoutManager, float f) {
        int height = (this.c.getHeight() - this.c.getPaddingTop()) - this.c.getPaddingBottom();
        if (Float.compare(Math.abs(f), height * this.b) < 0) {
            return;
        }
        if (!(layoutManager instanceof LinearLayoutManager)) {
            HwRecyclerView hwRecyclerView = this.c;
            if (((int) f) <= 0) {
                height = -height;
            }
            hwRecyclerView.scrollBy(0, height);
            return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        if (Float.compare(f, 0.0f) <= 0) {
            c(linearLayoutManager);
            return;
        }
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition) == null) {
            Log.e("HwPageTurningScrollHelper", "Page turning scroll vertically error, first visible item view is null.");
            return;
        }
        if (!b(true)) {
            this.j.push(Integer.valueOf(findFirstVisibleItemPosition));
        }
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        View findViewByPosition = linearLayoutManager.findViewByPosition(findLastVisibleItemPosition);
        if (findViewByPosition == null) {
            Log.e("HwPageTurningScrollHelper", "Page turning scroll vertically error, last visible item view is null.");
        } else if (efD_(linearLayoutManager, findViewByPosition, true)) {
            linearLayoutManager.scrollToPositionWithOffset(findLastVisibleItemPosition, 0);
        } else {
            linearLayoutManager.scrollToPositionWithOffset(findLastVisibleItemPosition + 1, 0);
        }
    }

    public void efE_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.e("HwPageTurningScrollHelper", "The given motionEvent can not be null!");
        } else {
            this.d = motionEvent.getX();
            this.e = motionEvent.getY();
        }
    }

    public void efF_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.e("HwPageTurningScrollHelper", "The given motionEvent can not be null!");
            return;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.LayoutManager layoutManager = this.c.getLayoutManager();
        if (layoutManager == null) {
            Log.e("HwPageTurningScrollHelper", "onMotionTouchUp error, the layout manager is null.");
            return;
        }
        boolean canScrollVertically = layoutManager.canScrollVertically();
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        if (canScrollVertically) {
            a(layoutManager, this.e - y);
            return;
        }
        if (!canScrollHorizontally) {
            Log.i("HwPageTurningScrollHelper", "The RecyclerView can neither scroll horizontally nor vertically, do nothing.");
        } else if (d()) {
            e(layoutManager, this.d - x);
        } else {
            b(layoutManager, this.d - x);
        }
    }

    public void a(float f) {
        this.f17121a = f;
    }

    private void c(LinearLayoutManager linearLayoutManager) {
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        Integer peek = this.j.peek();
        while (peek != null && peek.intValue() > findFirstVisibleItemPosition) {
            this.j.pop();
            peek = this.j.peek();
        }
        if (!this.j.isEmpty()) {
            linearLayoutManager.scrollToPositionWithOffset(this.j.pop().intValue(), 0);
        } else {
            linearLayoutManager.scrollToPositionWithOffset(0, 0);
        }
    }

    private boolean efD_(RecyclerView.LayoutManager layoutManager, View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            return false;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (z) {
            return layoutManager.getDecoratedBottom(view) + layoutParams2.bottomMargin > this.c.getHeight() - this.c.getPaddingBottom();
        }
        if (d()) {
            return layoutManager.getDecoratedLeft(view) - layoutParams2.leftMargin < this.c.getPaddingLeft();
        }
        return layoutManager.getDecoratedRight(view) + layoutParams2.rightMargin > this.c.getWidth() - this.c.getPaddingRight();
    }

    public void e(float f) {
        this.b = f;
    }

    private void e(RecyclerView.LayoutManager layoutManager, float f) {
        int width = (this.c.getWidth() - this.c.getPaddingRight()) - this.c.getPaddingLeft();
        if (Float.compare(Math.abs(f), width * this.f17121a) < 0) {
            return;
        }
        if (!(layoutManager instanceof LinearLayoutManager)) {
            HwRecyclerView hwRecyclerView = this.c;
            if (((int) f) > 0) {
                width = -width;
            }
            hwRecyclerView.scrollBy(width, 0);
            return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        if (Float.compare(f, 0.0f) < 0) {
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition) == null) {
                Log.e("HwPageTurningScrollHelper", "Page turning scroll horizontally rtl error, first visible item view is null.");
                return;
            }
            if (!b(false)) {
                this.j.push(Integer.valueOf(findFirstVisibleItemPosition));
            }
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            View findViewByPosition = linearLayoutManager.findViewByPosition(findLastVisibleItemPosition);
            if (findViewByPosition == null) {
                Log.e("HwPageTurningScrollHelper", "Page turning scroll horizontally rtl error, last visible item view is null.");
                return;
            } else if (efD_(linearLayoutManager, findViewByPosition, false)) {
                linearLayoutManager.scrollToPositionWithOffset(findLastVisibleItemPosition, 0);
                return;
            } else {
                linearLayoutManager.scrollToPositionWithOffset(findLastVisibleItemPosition + 1, 0);
                return;
            }
        }
        c(linearLayoutManager);
    }

    private void b(RecyclerView.LayoutManager layoutManager, float f) {
        int width = (this.c.getWidth() - this.c.getPaddingRight()) - this.c.getPaddingLeft();
        if (Float.compare(Math.abs(f), width * this.f17121a) < 0) {
            return;
        }
        if (!(layoutManager instanceof LinearLayoutManager)) {
            HwRecyclerView hwRecyclerView = this.c;
            if (Float.compare(f, 0.0f) <= 0) {
                width = -width;
            }
            hwRecyclerView.scrollBy(width, 0);
            return;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        if (Float.compare(f, 0.0f) > 0) {
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition) == null) {
                Log.e("HwPageTurningScrollHelper", "Page turning scroll horizontally error, first visible item view is null.");
                return;
            }
            if (!b(false)) {
                this.j.push(Integer.valueOf(findFirstVisibleItemPosition));
            }
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            View findViewByPosition = linearLayoutManager.findViewByPosition(findLastVisibleItemPosition);
            if (findViewByPosition == null) {
                Log.e("HwPageTurningScrollHelper", "Page turning scroll horizontally error, last visible item view is null.");
                return;
            } else if (efD_(linearLayoutManager, findViewByPosition, false)) {
                linearLayoutManager.scrollToPositionWithOffset(findLastVisibleItemPosition, 0);
                return;
            } else {
                linearLayoutManager.scrollToPositionWithOffset(findLastVisibleItemPosition + 1, 0);
                return;
            }
        }
        c(linearLayoutManager);
    }

    private boolean d() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    private boolean b(boolean z) {
        if (z) {
            return this.c.computeVerticalScrollOffset() + this.c.computeVerticalScrollExtent() >= this.c.computeVerticalScrollRange();
        }
        return this.c.computeHorizontalScrollOffset() + this.c.computeHorizontalScrollExtent() >= this.c.computeHorizontalScrollRange();
    }
}
