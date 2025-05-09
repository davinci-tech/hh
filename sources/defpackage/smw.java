package defpackage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwscrollbarview.widget.HwScrollBind;
import com.huawei.uikit.hwscrollbarview.widget.HwScrollbarView;

/* loaded from: classes7.dex */
public class smw implements HwScrollBind {

    class a implements AbsListView.OnScrollListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ HwScrollbarView f17127a;

        a(HwScrollbarView hwScrollbarView) {
            this.f17127a = hwScrollbarView;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.f17127a.onScrollChanged();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    class d extends RecyclerView.OnScrollListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ HwScrollbarView f17128a;

        d(HwScrollbarView hwScrollbarView) {
            this.f17128a = hwScrollbarView;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            this.f17128a.onScrollChanged();
        }
    }

    @Override // com.huawei.uikit.hwscrollbarview.widget.HwScrollBind
    public void bindListView(AbsListView absListView, HwScrollbarView hwScrollbarView, boolean z) {
        if (absListView == null || hwScrollbarView == null) {
            return;
        }
        hwScrollbarView.setScrollableView(absListView, z);
        absListView.setOnScrollListener(new a(hwScrollbarView));
    }

    @Override // com.huawei.uikit.hwscrollbarview.widget.HwScrollBind
    public void bindRecyclerView(RecyclerView recyclerView, HwScrollbarView hwScrollbarView, boolean z) {
        if (recyclerView == null || hwScrollbarView == null) {
            return;
        }
        hwScrollbarView.setScrollableView(recyclerView, z);
        recyclerView.addOnScrollListener(new d(hwScrollbarView));
    }

    @Override // com.huawei.uikit.hwscrollbarview.widget.HwScrollBind
    public void bindScrollView(ScrollView scrollView, HwScrollbarView hwScrollbarView, boolean z) {
    }

    @Override // com.huawei.uikit.hwscrollbarview.widget.HwScrollBind
    public void onScrollableViewTouchEvent(View view, HwScrollbarView hwScrollbarView, MotionEvent motionEvent) {
        if (view == null || hwScrollbarView == null || motionEvent == null) {
            return;
        }
        hwScrollbarView.onScrollableViewTouchEvent(view, motionEvent);
    }
}
