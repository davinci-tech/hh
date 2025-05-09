package com.huawei.ui.main.stories.fitness.views.heartrate.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class CardContainerView extends ScrollChartObserverView {

    /* renamed from: a, reason: collision with root package name */
    protected ScrollChartObserverView f9993a;
    protected d b;
    protected List<ScrollChartObserverView> c;
    protected List<HwHealthChartHolder.b> d;
    protected Map<HwHealthChartHolder.b, HwHealthBaseBarLineDataSet> e;
    private b f;
    private e g;
    private int h;
    private List<OnSelectListener> i;
    private HealthRecycleView j;
    private View l;
    private View o;

    public interface OnSelectListener {
        void onSelect(View view, int i);
    }

    public CardContainerView(Context context, ObserveredClassifiedView observeredClassifiedView) {
        super(context, observeredClassifiedView, null, null);
        this.d = new ArrayList(16);
        this.c = new ArrayList(16);
        this.b = new d();
        this.e = new HashMap(16);
        this.f9993a = null;
        this.i = new ArrayList();
        this.h = 0;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void initView(String str, String str2) {
        inflate(getContext(), R.layout.multi_view_data_observer_view, this);
        this.c = new ArrayList();
        this.g = new e();
        this.f = null;
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.observer_view_container);
        this.j = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.j.setAdapter(this.g);
    }

    private void a() {
        this.h = getCardWidth();
        View view = new View(getContext());
        this.o = view;
        view.setMinimumWidth(getGutter());
        this.o.setMinimumHeight(nrr.e(getContext(), 32.0f));
        View view2 = new View(getContext());
        this.l = view2;
        view2.setMinimumWidth(getMargin());
        this.l.setMinimumHeight(nrr.e(getContext(), 32.0f));
    }

    class e extends RecyclerView.Adapter<a> {
        private e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: dxH_, reason: merged with bridge method [inline-methods] */
        public a onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 0) {
                View view = new View(CardContainerView.this.getContext());
                view.setLayoutParams(new RecyclerView.LayoutParams(CardContainerView.this.getGutter(), 1));
                return new a(view);
            }
            if (i == 2) {
                return new a(new View(CardContainerView.this.getContext()));
            }
            View inflate = LayoutInflater.from(CardContainerView.this.getContext()).inflate(R.layout.multi_view_data_observer_view_item, viewGroup, false);
            inflate.setLayoutParams(new LinearLayout.LayoutParams(CardContainerView.this.h, -2));
            return new a(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(a aVar, int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                aVar.itemView.setMinimumWidth(CardContainerView.this.o.getMinimumWidth());
                aVar.itemView.setMinimumHeight(CardContainerView.this.o.getMinimumHeight());
            } else if (itemViewType == 2) {
                aVar.itemView.setMinimumWidth(CardContainerView.this.l.getMinimumWidth() + ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                aVar.itemView.setMinimumHeight(CardContainerView.this.l.getMinimumHeight());
            } else {
                dxG_((LinearLayout) aVar.itemView.findViewById(R.id.observer_view_item_place), CardContainerView.this.c.get(i / 2));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return CardContainerView.this.c.size() + CardContainerView.this.c.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            if (i == 0 || i == getItemCount() - 1) {
                return 2;
            }
            return (i < 0 || i % 2 != 1) ? 0 : 1;
        }

        public void dxG_(ViewGroup viewGroup, ScrollChartObserverView scrollChartObserverView) {
            if (scrollChartObserverView != null && (scrollChartObserverView.getParent() instanceof ViewGroup)) {
                ((ViewGroup) scrollChartObserverView.getParent()).removeView(scrollChartObserverView);
            }
            viewGroup.removeAllViews();
            viewGroup.addView(scrollChartObserverView);
        }
    }

    static class a extends RecyclerView.ViewHolder {
        a(View view) {
            super(view);
        }
    }

    protected class b implements View.OnClickListener {
        protected List<ScrollChartObserverView> e;

        public b(List<ScrollChartObserverView> list) {
            this.e = list;
            Iterator<ScrollChartObserverView> it = list.iterator();
            while (it.hasNext()) {
                it.next().setOnClickListener(this);
            }
        }

        public boolean e() {
            return !CardContainerView.this.b.b || CardContainerView.this.b.d == null;
        }

        public void c(int i) {
            dxI_(this.e.get(i));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            dxI_(view);
            ViewClickInstrumentation.clickOnView(view);
        }

        public void dxI_(View view) {
            if (view instanceof ScrollChartObserverView) {
                for (ScrollChartObserverView scrollChartObserverView : this.e) {
                    scrollChartObserverView.setBackgroundResource(R.drawable._2131427568_res_0x7f0b00f0);
                    scrollChartObserverView.setClickable(true);
                    scrollChartObserverView.setTitleColor(R.color._2131299241_res_0x7f090ba9);
                    scrollChartObserverView.setContentColor(R.color._2131297589_res_0x7f090535);
                }
                view.setBackgroundResource(R.drawable._2131427582_res_0x7f0b00fe);
                CardContainerView.this.f9993a = (ScrollChartObserverView) view;
                CardContainerView.this.f9993a.setTitleColor(R.color._2131299238_res_0x7f090ba6);
                CardContainerView.this.f9993a.setContentColor(R.color._2131299238_res_0x7f090ba6);
                CardContainerView.this.f9993a.setClickable(false);
                if (this.e.contains(view)) {
                    int indexOf = this.e.indexOf(view);
                    if (!CardContainerView.this.f.e()) {
                        CardContainerView.this.f.b();
                    }
                    try {
                        CardContainerView.this.f.a(indexOf);
                    } catch (c unused) {
                        CardContainerView.this.f.b();
                    }
                    Iterator it = CardContainerView.this.i.iterator();
                    while (it.hasNext()) {
                        ((OnSelectListener) it.next()).onSelect(view, indexOf);
                    }
                }
            }
        }

        public void a() {
            for (HwHealthChartHolder.b bVar : CardContainerView.this.d) {
                if (bVar == CardContainerView.this.d.get(0)) {
                    if (CardContainerView.this.mHost.getChart() != null && CardContainerView.this.mHost.getChart().getData() != null && koq.c(CardContainerView.this.mHost.getChart().getData().getDataSets()) && (CardContainerView.this.mHost.getChart().getData().getDataSets().get(0) instanceof HwHealthBaseBarLineDataSet)) {
                        CardContainerView.this.e.put(bVar, (HwHealthBaseBarLineDataSet) CardContainerView.this.mHost.getChart().getData().getDataSets().get(0));
                    }
                } else {
                    HwHealthBaseBarLineDataSet fakeDataLayer = CardContainerView.this.mHost.fakeDataLayer(bVar);
                    CardContainerView.this.e.put(bVar, fakeDataLayer);
                    b(bVar, fakeDataLayer);
                }
            }
        }

        protected void b(HwHealthChartHolder.b bVar, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            IChartLayerHolder acquireChartLayerHolder = CardContainerView.this.mHost.acquireChartLayerHolder();
            if (!(acquireChartLayerHolder instanceof HwHealthScrollChartHolder)) {
                throw new RuntimeException("not support scrollable,init focus now only support scrollable chart!!!");
            }
            CardContainerView.this.mHost.manageDataSetAsProxy(hwHealthBaseBarLineDataSet, ((HwHealthScrollChartHolder) acquireChartLayerHolder).acquireStorageHelper(), CardContainerView.this.mHost.getStepDataType(), bVar);
        }

        public void a(int i) throws c {
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet;
            if (koq.b(CardContainerView.this.d, i)) {
                LogUtil.b("CardContainerView", "overLayout mShowModes, the index is out of bounds");
                return;
            }
            HwHealthChartHolder.b bVar = CardContainerView.this.d.get(i);
            if (bVar == CardContainerView.this.d.get(0)) {
                throw new c();
            }
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet2 = CardContainerView.this.e.get(bVar);
            if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e()) && !CardContainerView.this.e.isEmpty() && (hwHealthBaseBarLineDataSet = CardContainerView.this.e.get(CardContainerView.this.d.get(0))) != null) {
                CardContainerView.this.mHost.removeDataLayer(hwHealthBaseBarLineDataSet);
                b(CardContainerView.this.d.get(0), hwHealthBaseBarLineDataSet);
            }
            CardContainerView.this.mHost.addDataLayer(hwHealthBaseBarLineDataSet2, bVar);
            c(hwHealthBaseBarLineDataSet2);
            if (CardContainerView.this.mHost.getChart() != null) {
                CardContainerView.this.mHost.getChart().animateBorderYAuto();
            }
            CardContainerView.this.b.b = true;
            CardContainerView.this.b.d = new d.C0264d(hwHealthBaseBarLineDataSet2, bVar);
        }

        protected void c(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            CardContainerView.this.mHost.unManageDataSetAsProxy(hwHealthBaseBarLineDataSet);
        }

        public void b() {
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet;
            if (!CardContainerView.this.b.b || CardContainerView.this.b.d == null) {
                return;
            }
            if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(CardContainerView.this.b.d.b.e()) && !CardContainerView.this.e.isEmpty() && koq.d(CardContainerView.this.d, 0) && (hwHealthBaseBarLineDataSet = CardContainerView.this.e.get(CardContainerView.this.d.get(0))) != null) {
                CardContainerView.this.mHost.addDataLayer(hwHealthBaseBarLineDataSet, CardContainerView.this.d.get(0));
                c(hwHealthBaseBarLineDataSet);
            }
            d.C0264d c0264d = CardContainerView.this.b.d;
            CardContainerView.this.mHost.removeDataLayer(c0264d.e);
            b(c0264d.b, c0264d.e);
            if (CardContainerView.this.mHost.getChart() != null) {
                CardContainerView.this.mHost.getChart().animateBorderYAuto();
            }
            CardContainerView.this.b.b = false;
            CardContainerView.this.b.d = null;
        }
    }

    public void setCurrentItem(int i) {
        b bVar = this.f;
        if (bVar != null) {
            bVar.c(i);
        }
    }

    public void c(List<ScrollChartObserverView> list, List<HwHealthChartHolder.b> list2) {
        a();
        this.c.clear();
        this.c.addAll(list);
        this.d.clear();
        this.d.addAll(list2);
        this.g.notifyDataSetChanged();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        Iterator<ScrollChartObserverView> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
        }
    }

    protected static class d {
        boolean b = false;
        C0264d d = null;

        protected d() {
        }

        /* renamed from: com.huawei.ui.main.stories.fitness.views.heartrate.card.CardContainerView$d$d, reason: collision with other inner class name */
        static class C0264d {
            HwHealthChartHolder.b b;
            HwHealthBaseBarLineDataSet e;

            C0264d(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, HwHealthChartHolder.b bVar) {
                this.e = hwHealthBaseBarLineDataSet;
                this.b = bVar;
            }
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void initChartLinkage() {
        b b2 = b(this.c);
        this.f = b2;
        b2.a();
        this.f.c(0);
        d dVar = new d();
        this.b = dVar;
        dVar.b = false;
        this.b.d = null;
    }

    protected b b(List<ScrollChartObserverView> list) {
        return new b(list);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public IFocusObserverItem acquireFocusItem() {
        return (IFocusObserverItem) this.f9993a;
    }

    public void d(OnSelectListener onSelectListener) {
        this.i.add(onSelectListener);
    }

    public static class c extends Exception {
        private static final long serialVersionUID = -1714786535320703698L;

        public c() {
            super("rest show layer");
        }
    }

    public void e() {
        this.h = getCardWidth();
        this.o.setMinimumWidth(getGutter());
        this.l.setMinimumWidth(getMargin());
        e eVar = new e();
        this.g = eVar;
        this.j.setAdapter(eVar);
        this.g.notifyDataSetChanged();
    }

    private int getCardWidth() {
        int margin = getMargin();
        int gutter = getGutter();
        return (nsn.ag(BaseApplication.getContext()) && nsn.l()) ? ((((nsn.n() - (margin * 2)) - (gutter * 7)) / 8) * 2) + gutter : (int) Utils.convertDpToPixel(154.0f);
    }

    private int getMargin() {
        return BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getGutter() {
        Context context = BaseApplication.getContext();
        if (nsn.ag(context)) {
            return nrr.b(context);
        }
        return context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
    }
}
