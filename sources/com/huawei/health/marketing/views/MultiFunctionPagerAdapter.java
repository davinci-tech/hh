package com.huawei.health.marketing.views;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.SingleFunctionGridContent;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import defpackage.eie;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class MultiFunctionPagerAdapter extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private ResourceBriefInfo f2877a;
    private boolean b;
    private Context c;
    private List<SingleFunctionGridContent> d;
    private int e;
    private Pair<Integer, Integer> f = BaseActivity.getSafeRegionWidth();
    private HealthRecycleView g;
    private int j;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public MultiFunctionPagerAdapter(Context context) {
        this.c = context;
    }

    public void b(List<SingleFunctionGridContent> list, ResourceBriefInfo resourceBriefInfo, int i, int i2) {
        this.d = list;
        this.f2877a = resourceBriefInfo;
        this.j = i;
        this.e = i2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return (this.d.size() / 8) + (this.d.size() % 8 > 0 ? 1 : 0);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = View.inflate(this.c, R.layout.pager_multi_function, null);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.multi_function_recycler_view);
        this.g = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        this.g.setHasFixedSize(true);
        this.g.a(false);
        this.g.d(false);
        this.g.setIsScroll(false);
        ColumnLayoutAdapter columnLayoutAdapter = new ColumnLayoutAdapter(this.c, this.j, this.f2877a);
        int i2 = i * 8;
        columnLayoutAdapter.e(this.d.subList(i2, Math.min(i2 + 8, this.d.size())));
        this.g.setAdapter(columnLayoutAdapter);
        a();
        viewGroup.addView(inflate);
        return inflate;
    }

    private void a() {
        e(eie.c(this.e, this.b, this.g.getAdapter() != null ? this.g.getAdapter().getItemCount() : 0), eie.b(this.e, this.b), eie.a(this.e), eie.a(this.e, this.j));
    }

    private void e(int i, int i2, int i3, int[] iArr) {
        LogUtil.a("MultiFunctionPagerAdapter", "enter setRecyclerViewLayout");
        if (this.c == null) {
            LogUtil.h("MultiFunctionPagerAdapter", "setRecyclerViewLayout() mContext is null.");
            return;
        }
        if (i < 1) {
            LogUtil.h("MultiFunctionPagerAdapter", "setRecyclerViewLayout() gridNumber should be at least 1.");
            return;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.c, 2);
        gridLayoutManager.setReverseLayout(false);
        gridLayoutManager.setOrientation(0);
        gridLayoutManager.setReverseLayout(LanguageUtil.bc(BaseApplication.getContext()));
        this.g.setLayoutManager(gridLayoutManager);
        int itemDecorationCount = this.g.getItemDecorationCount();
        if (itemDecorationCount > 0) {
            while (true) {
                itemDecorationCount--;
                if (itemDecorationCount < 0) {
                    break;
                } else {
                    this.g.removeItemDecorationAt(itemDecorationCount);
                }
            }
        }
        this.g.addItemDecoration(new ColumnLayoutItemDecoration(i2, i3, i, iArr, this.e));
        this.g.setHasFixedSize(true);
        this.g.setNestedScrollingEnabled(false);
        this.g.setVisibility(0);
    }

    public void e(boolean z) {
        this.b = z;
    }
}
