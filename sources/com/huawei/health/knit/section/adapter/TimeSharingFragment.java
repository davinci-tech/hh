package com.huawei.health.knit.section.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionPieChartBasicAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.nkz;
import defpackage.nld;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class TimeSharingFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private List<SectionPieChartBasicAdapter.b> f2595a;
    private HealthRecycleView b;
    private String c;
    private SectionPieChartBasicAdapter d;
    private HealthCardView e;
    private HealthRingChartAdapter f;
    private List<nkz> g;
    private View h;
    private HealthTextView i;
    private HealthRingChart j;
    private HealthViewPager o;

    public static TimeSharingFragment b() {
        return new TimeSharingFragment();
    }

    public void b(List<nkz> list, String str, List<SectionPieChartBasicAdapter.b> list2) {
        this.g = list;
        this.c = str;
        this.f2595a = list2;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("TimeSharingFragment", "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.time_sharing_fragment_layout, viewGroup, false);
        this.h = inflate;
        this.b = (HealthRecycleView) inflate.findViewById(R.id.time_sharing_basic_recycler_view);
        this.e = (HealthCardView) this.h.findViewById(R.id.time_sharing_basic_recycler_view_cardview);
        this.i = (HealthTextView) this.h.findViewById(R.id.time_sharing_middle_title);
        this.j = (HealthRingChart) this.h.findViewById(R.id.time_sharing_piechart);
        e();
        this.e.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.adapter.TimeSharingFragment.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LogUtil.a("TimeSharingFragment", "onGlobalLayout");
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) TimeSharingFragment.this.o.getLayoutParams();
                layoutParams.height = TimeSharingFragment.this.j.getMeasuredHeight() + TimeSharingFragment.this.b.getMeasuredHeight();
                TimeSharingFragment.this.o.setLayoutParams(layoutParams);
                TimeSharingFragment.this.e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return this.h;
    }

    public void a(HealthViewPager healthViewPager) {
        this.o = healthViewPager;
    }

    public void e() {
        LogUtil.a("TimeSharingFragment", "initView");
        if (this.g != null) {
            HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(getContext(), new nld(false, true));
            this.f = healthRingChartAdapter;
            healthRingChartAdapter.b(new HealthRingChartAdapter.DataFormatter() { // from class: ecb
                @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
                public final String format(nkz nkzVar) {
                    String e;
                    e = UnitUtil.e(nkzVar.e() * 100.0f, 2, 1);
                    return e;
                }
            });
            this.j.setAdapter(this.f);
            this.f.c(this.g);
        }
        nsy.cMr_(this.i, this.c);
        this.b.setLayoutManager(new LinearLayoutManager(getContext()));
        SectionPieChartBasicAdapter sectionPieChartBasicAdapter = new SectionPieChartBasicAdapter(getContext());
        this.d = sectionPieChartBasicAdapter;
        this.b.setAdapter(sectionPieChartBasicAdapter);
        List<SectionPieChartBasicAdapter.b> list = this.f2595a;
        if (list != null) {
            this.d.d(list);
        }
    }
}
