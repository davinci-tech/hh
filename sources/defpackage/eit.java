package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.marketing.views.ColumnLayoutAdapter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.util.List;

/* loaded from: classes3.dex */
public class eit extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private int f12040a = 36;
    private boolean b;
    private List<List<SingleGridContent>> c;
    private int d;
    private Context e;
    private ResourceBriefInfo g;
    private HealthViewPager j;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public eit(List<List<SingleGridContent>> list, HealthViewPager healthViewPager, Context context, int i) {
        this.b = false;
        this.d = i;
        this.b = nsn.ag(BaseApplication.getActivity());
        this.c = list;
        this.j = healthViewPager;
        this.e = context;
    }

    public void d(List<List<SingleGridContent>> list, ResourceBriefInfo resourceBriefInfo) {
        if (koq.b(list)) {
            LogUtil.h("AppTurnPageAdapter", "list is empty");
        } else {
            this.c = list;
            this.g = resourceBriefInfo;
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<List<SingleGridContent>> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.c, i)) {
            LogUtil.h("AppTurnPageAdapter", "isOutOfBounds");
            return LayoutInflater.from(this.e).inflate(R.layout.item_app_turn_page, (ViewGroup) null);
        }
        View ama_ = ama_(this.c.get(i), this.j);
        if (viewGroup instanceof HealthViewPager) {
            ((HealthViewPager) viewGroup).addView(ama_);
        }
        return ama_;
    }

    private View ama_(List<SingleGridContent> list, HealthViewPager healthViewPager) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.item_app_turn_page, (ViewGroup) null);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.app_turn_page_recycler_view);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        ColumnLayoutAdapter columnLayoutAdapter = new ColumnLayoutAdapter(this.e, this.d, this.g);
        columnLayoutAdapter.b(list);
        healthRecycleView.setAdapter(columnLayoutAdapter);
        a(healthRecycleView, list != null ? list.size() : 0);
        return inflate;
    }

    private void a(HealthRecycleView healthRecycleView, int i) {
        b(healthRecycleView, eie.c(this.f12040a, this.b, i), eie.b(this.f12040a, this.b), eie.a(this.f12040a), eie.a(this.f12040a, this.d));
    }

    private void b(HealthRecycleView healthRecycleView, int i, int i2, int i3, int[] iArr) {
        if (this.e == null) {
            LogUtil.h("AppTurnPageAdapter", "setRecyclerViewLayout() mContext is null.");
            return;
        }
        if (i < 1) {
            LogUtil.h("AppTurnPageAdapter", "setRecyclerViewLayout() gridNumber should be at least 1.");
            return;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.e, i);
        gridLayoutManager.setOrientation(1);
        healthRecycleView.setLayoutManager(gridLayoutManager);
        if (healthRecycleView.getItemDecorationCount() > 0) {
            healthRecycleView.removeItemDecorationAt(0);
        }
        healthRecycleView.addItemDecoration(new ColumnLayoutItemDecoration(i2, i3, i, iArr));
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.setVisibility(0);
    }
}
