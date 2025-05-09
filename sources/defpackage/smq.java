package defpackage;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.uikit.hwscrollbarview.widget.HwScrollbarView;

/* loaded from: classes7.dex */
public class smq extends smw {

    class e implements View.OnScrollChangeListener {
        final /* synthetic */ HwScrollbarView d;

        e(HwScrollbarView hwScrollbarView) {
            this.d = hwScrollbarView;
        }

        @Override // android.view.View.OnScrollChangeListener
        public void onScrollChange(View view, int i, int i2, int i3, int i4) {
            this.d.onScrollChanged();
            ViewScrollInstrumentation.scrollChangeOnView(view, i, i2, i3, i4);
        }
    }

    private void egD_(View view, HwScrollbarView hwScrollbarView, boolean z) {
        if (view == null || hwScrollbarView == null) {
            return;
        }
        hwScrollbarView.setScrollableView(view, z);
        view.setOnScrollChangeListener(new e(hwScrollbarView));
    }

    @Override // defpackage.smw, com.huawei.uikit.hwscrollbarview.widget.HwScrollBind
    public void bindListView(AbsListView absListView, HwScrollbarView hwScrollbarView, boolean z) {
        egD_(absListView, hwScrollbarView, z);
    }

    @Override // defpackage.smw, com.huawei.uikit.hwscrollbarview.widget.HwScrollBind
    public void bindRecyclerView(RecyclerView recyclerView, HwScrollbarView hwScrollbarView, boolean z) {
        egD_(recyclerView, hwScrollbarView, z);
    }

    @Override // defpackage.smw, com.huawei.uikit.hwscrollbarview.widget.HwScrollBind
    public void bindScrollView(ScrollView scrollView, HwScrollbarView hwScrollbarView, boolean z) {
        egD_(scrollView, hwScrollbarView, z);
    }
}
