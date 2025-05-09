package defpackage;

import android.os.Build;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener;
import com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView;
import com.huawei.uikit.hwscrollbarview.widget.HwOverScrollProxy;
import com.huawei.uikit.hwscrollbarview.widget.HwScrollbarView;
import huawei.android.widget.HwSafeInsetsShareable;

/* loaded from: classes7.dex */
public class smx {

    static final class a implements HwOverScrollProxy {
        final /* synthetic */ ScrollView e;

        a(ScrollView scrollView) {
            this.e = scrollView;
        }

        private int a() {
            View childAt = this.e.getChildAt(0);
            if (childAt == null) {
                return 0;
            }
            int height = childAt.getHeight();
            int height2 = this.e.getHeight();
            return this.e.getPaddingBottom() + (height - height2) + this.e.getPaddingTop();
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwOverScrollProxy
        public int getOverScrollOffset() {
            int scrollY = this.e.getScrollY();
            if (scrollY <= 0) {
                return scrollY;
            }
            int a2 = a();
            if (scrollY > a2) {
                return scrollY - a2;
            }
            return 0;
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwOverScrollProxy
        public boolean isOverScroll() {
            int a2 = a();
            int scrollY = this.e.getScrollY();
            return scrollY > a2 || scrollY < 0;
        }
    }

    static final class b implements HwScrollbarView.OnFastScrollListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ RecyclerView f17129a;

        b(RecyclerView recyclerView) {
            this.f17129a = recyclerView;
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwScrollbarView.OnFastScrollListener
        public void onFastScrollChanged(int i, int i2, float f) {
            if (Math.abs(i2) < smx.egN_(this.f17129a)) {
                this.f17129a.scrollBy(i, i2);
            } else {
                smx.b(this.f17129a, i, i2, f);
            }
        }
    }

    static final class c implements HwOverScrollProxy {
        final /* synthetic */ AbsListView c;

        c(AbsListView absListView) {
            this.c = absListView;
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwOverScrollProxy
        public int getOverScrollOffset() {
            return this.c.getScrollY();
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwOverScrollProxy
        public boolean isOverScroll() {
            return this.c.getScrollY() != 0;
        }
    }

    static final class d implements HwScrollbarView.OnFastScrollListener {
        final /* synthetic */ AbsListView c;

        d(AbsListView absListView) {
            this.c = absListView;
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwScrollbarView.OnFastScrollListener
        public void onFastScrollChanged(int i, int i2, float f) {
            smx.b(this.c, i, i2, f);
        }
    }

    static final class e implements HwScrollbarView.OnFastScrollListener {
        final /* synthetic */ ScrollView b;

        e(ScrollView scrollView) {
            this.b = scrollView;
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwScrollbarView.OnFastScrollListener
        public void onFastScrollChanged(int i, int i2, float f) {
            this.b.smoothScrollBy(i, i2);
        }
    }

    static final class g implements HwOverScrollProxy {
        final /* synthetic */ RecyclerView c;

        g(RecyclerView recyclerView) {
            this.c = recyclerView;
        }

        private int e() {
            return (int) this.c.getTranslationY();
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwOverScrollProxy
        public int getOverScrollOffset() {
            return -e();
        }

        @Override // com.huawei.uikit.hwscrollbarview.widget.HwOverScrollProxy
        public boolean isOverScroll() {
            return e() != 0;
        }
    }

    static final class j implements HwOnOverScrollListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ HwScrollbarView f17130a;

        j(HwScrollbarView hwScrollbarView) {
            this.f17130a = hwScrollbarView;
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener
        public void onOverScrollEnd() {
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener
        public void onOverScrollStart() {
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwOnOverScrollListener
        public void onOverScrolled(float f) {
            this.f17130a.onScrollChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(AbsListView absListView, int i, int i2, float f) {
        Adapter adapter;
        if (Float.compare(f, 0.0f) == 0) {
            absListView.setSelection(0);
            return;
        }
        if (Float.compare(f, 1.0f) != 0 || (adapter = absListView.getAdapter()) == null || adapter.getCount() <= 0) {
            if (Math.abs(i2) < egN_(absListView)) {
                absListView.scrollListBy(i2);
                return;
            } else {
                egT_(absListView, i, i2, f);
                return;
            }
        }
        View childAt = absListView.getChildAt(absListView.getChildCount() - 1);
        if (childAt == null) {
            return;
        }
        int height = ((absListView.getHeight() - absListView.getPaddingTop()) - absListView.getPaddingBottom()) - childAt.getHeight();
        int count = adapter.getCount() - 1;
        if (height >= 0) {
            absListView.setSelection(count);
        } else {
            egK_(absListView, count, height);
        }
    }

    public static boolean c(RecyclerView recyclerView, HwScrollbarView hwScrollbarView) {
        return b(recyclerView, hwScrollbarView, true);
    }

    public static boolean egP_(AbsListView absListView, HwScrollbarView hwScrollbarView) {
        return egQ_(absListView, hwScrollbarView, true);
    }

    public static boolean egR_(ScrollView scrollView, HwScrollbarView hwScrollbarView) {
        return egS_(scrollView, hwScrollbarView, true);
    }

    private static void egT_(AbsListView absListView, int i, int i2, float f) {
        int i3;
        int count;
        int firstVisiblePosition = absListView.getFirstVisiblePosition();
        int lastVisiblePosition = absListView.getLastVisiblePosition();
        int height = absListView.getHeight();
        int paddingTop = absListView.getPaddingTop();
        int paddingBottom = absListView.getPaddingBottom();
        if (lastVisiblePosition > firstVisiblePosition && (i3 = ((height - paddingTop) - paddingBottom) / (lastVisiblePosition - firstVisiblePosition)) > 0) {
            int i4 = firstVisiblePosition + (i2 / i3);
            int i5 = i2 % i3;
            if (i4 <= 0) {
                i4 = 0;
                i5 = 0;
            } else {
                if (((ListAdapter) absListView.getAdapter()) != null && i4 > r6.getCount() - 1) {
                    i4 = count;
                }
            }
            egK_(absListView, i4, i5);
        }
    }

    public static boolean b(RecyclerView recyclerView, HwScrollbarView hwScrollbarView, boolean z) {
        if (!egM_(recyclerView, hwScrollbarView)) {
            return false;
        }
        HwScrollbarView.getHwScrollBindImpl().bindRecyclerView(recyclerView, hwScrollbarView, z);
        hwScrollbarView.setOnFastScrollListener(new b(recyclerView));
        hwScrollbarView.setHwOverScrollProxy(new g(recyclerView));
        if (recyclerView instanceof HwRecyclerView) {
            ((HwRecyclerView) recyclerView).b(new j(hwScrollbarView));
        }
        egO_(recyclerView, hwScrollbarView);
        return true;
    }

    public static boolean egQ_(AbsListView absListView, HwScrollbarView hwScrollbarView, boolean z) {
        if (!egM_(absListView, hwScrollbarView)) {
            return false;
        }
        HwScrollbarView.getHwScrollBindImpl().bindListView(absListView, hwScrollbarView, z);
        hwScrollbarView.setOnFastScrollListener(new d(absListView));
        hwScrollbarView.setHwOverScrollProxy(new c(absListView));
        egO_(absListView, hwScrollbarView);
        return true;
    }

    public static boolean egS_(ScrollView scrollView, HwScrollbarView hwScrollbarView, boolean z) {
        if (!egM_(scrollView, hwScrollbarView)) {
            return false;
        }
        HwScrollbarView.getHwScrollBindImpl().bindScrollView(scrollView, hwScrollbarView, z);
        hwScrollbarView.setOnFastScrollListener(new e(scrollView));
        hwScrollbarView.setHwOverScrollProxy(new a(scrollView));
        egO_(scrollView, hwScrollbarView);
        return true;
    }

    private static void egK_(AbsListView absListView, int i, int i2) {
        absListView.setSelectionFromTop(i, i2);
    }

    private static boolean egM_(View view, HwScrollbarView hwScrollbarView) {
        return (view == null || hwScrollbarView == null || hwScrollbarView.getScrollableView() != null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int egN_(View view) {
        if (view == null) {
            return 0;
        }
        int measuredHeight = view.getMeasuredHeight();
        View rootView = view.getRootView();
        return rootView != null ? rootView.getMeasuredHeight() : measuredHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(RecyclerView recyclerView, int i, int i2, float f) {
        int itemCount;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || layoutManager.getItemCount() <= 0 || (itemCount = layoutManager.getItemCount() - layoutManager.getChildCount()) < 0) {
            return;
        }
        int i3 = (int) (f * itemCount);
        if (i2 > 0 && (i3 = i3 + (layoutManager.getItemCount() - itemCount)) >= layoutManager.getItemCount()) {
            i3 = layoutManager.getItemCount() - 1;
        }
        recyclerView.scrollToPosition(i3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void egO_(View view, HwScrollbarView hwScrollbarView) {
        if (Build.VERSION.SDK_INT < 28 || !(view instanceof HwSafeInsetsShareable)) {
            return;
        }
        ((HwSafeInsetsShareable) view).addSharedView(hwScrollbarView, 1);
    }
}
