package com.huawei.ui.main.stories.fitness.views.heartrate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class MultiViewHorizontalDataObserverView extends LinearLayout implements IScrollChartOuterObserver {
    public static final HwHealthChartHolder.b d = new HwHealthChartHolder.b();

    /* renamed from: a, reason: collision with root package name */
    protected boolean f9987a;
    protected Map<HwHealthChartHolder.b, HwHealthBaseBarLineDataSet> b;
    protected ObserveredClassifiedView c;
    protected ScrollChartHorizontalObserverHRView e;
    private b f;
    protected List<ScrollChartHorizontalObserverHRView> g;
    private List<OnSelectListener> h;
    protected List<HwHealthChartHolder.b> i;
    protected c j;
    private HealthRecycleView k;
    private List<Integer> l;
    private List<Integer> m;
    private h o;

    public interface OnSelectListener {
        void onSelect(View view, int i);
    }

    public MultiViewHorizontalDataObserverView(Context context) {
        super(context);
        this.i = new ArrayList(10);
        this.g = new ArrayList(10);
        this.j = new c();
        this.b = new HashMap(10);
        this.f9987a = false;
        this.e = null;
        this.l = new ArrayList(10);
        this.m = new ArrayList(10);
        this.h = new ArrayList(10);
        c();
        a();
    }

    public MultiViewHorizontalDataObserverView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new ArrayList(10);
        this.g = new ArrayList(10);
        this.j = new c();
        this.b = new HashMap(10);
        this.f9987a = false;
        this.e = null;
        this.l = new ArrayList(10);
        this.m = new ArrayList(10);
        this.h = new ArrayList(10);
        c();
        a();
    }

    public MultiViewHorizontalDataObserverView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new ArrayList(10);
        this.g = new ArrayList(10);
        this.j = new c();
        this.b = new HashMap(10);
        this.f9987a = false;
        this.e = null;
        this.l = new ArrayList(10);
        this.m = new ArrayList(10);
        this.h = new ArrayList(10);
        c();
        a();
    }

    private void c() {
        this.l.clear();
        this.l.add(Integer.valueOf(R.drawable._2131431408_res_0x7f0b0ff0));
        List<Integer> list = this.l;
        Integer valueOf = Integer.valueOf(R.drawable._2131431405_res_0x7f0b0fed);
        list.add(valueOf);
        this.l.add(valueOf);
        this.l.add(Integer.valueOf(R.drawable._2131431403_res_0x7f0b0feb));
        this.m.clear();
        this.m.add(Integer.valueOf(R.drawable._2131431409_res_0x7f0b0ff1));
        List<Integer> list2 = this.m;
        Integer valueOf2 = Integer.valueOf(R.drawable._2131431406_res_0x7f0b0fee);
        list2.add(valueOf2);
        this.m.add(valueOf2);
        this.m.add(Integer.valueOf(R.drawable._2131431404_res_0x7f0b0fec));
    }

    public void setHost(ObserveredClassifiedView observeredClassifiedView) {
        this.c = observeredClassifiedView;
    }

    private void a() {
        inflate(getContext(), R.layout.multi_view_data_observer_view, this);
        this.f = new b();
        this.o = null;
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.observer_view_container);
        this.k = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.k.setAdapter(this.f);
    }

    class b extends RecyclerView.Adapter {
        private b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 0) {
                return new d(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.multi_view_data_observer_view_divider, (ViewGroup) null));
            }
            return new e(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.multi_view_horizontal_data_observer_view_item, (ViewGroup) null));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (i < 0 || i >= getItemCount()) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "onBindViewHolder position is wrong");
            } else {
                if (getItemViewType(i) == 0 || !(viewHolder instanceof e)) {
                    return;
                }
                ((e) viewHolder).b(MultiViewHorizontalDataObserverView.this.g.get(i / 2));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return (MultiViewHorizontalDataObserverView.this.g.size() * 2) - 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return i % 2 == 0 ? 1 : 0;
        }
    }

    static class e extends RecyclerView.ViewHolder {
        private LinearLayout c;

        e(View view) {
            super(view);
            this.c = (LinearLayout) view.findViewById(R.id.observer_view_item_place);
        }

        public void b(ScrollChartHorizontalObserverHRView scrollChartHorizontalObserverHRView) {
            if (scrollChartHorizontalObserverHRView == null) {
                return;
            }
            ViewParent parent = scrollChartHorizontalObserverHRView.getParent();
            if (parent instanceof ViewGroup) {
                LogUtil.h("Health_MultiViewHorizontalDataObserverView", "specifyObserverView():parent removeView");
                ((ViewGroup) parent).removeView(scrollChartHorizontalObserverHRView);
            }
            this.c.removeAllViews();
            this.c.addView(scrollChartHorizontalObserverHRView);
        }
    }

    static class d extends RecyclerView.ViewHolder {
        d(View view) {
            super(view);
        }
    }

    protected class h implements View.OnClickListener {
        private OnSelectListener c;
        protected List<? extends View> d;

        h(List<? extends View> list) {
            if (list == null) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "init SingleSelectViewsMgr with wrong views,system error");
                return;
            }
            this.d = list;
            Iterator<? extends View> it = list.iterator();
            while (it.hasNext()) {
                it.next().setOnClickListener(this);
            }
        }

        public boolean e() {
            return !MultiViewHorizontalDataObserverView.this.j.b || MultiViewHorizontalDataObserverView.this.j.c == null;
        }

        public void a() {
            MultiViewHorizontalDataObserverView.this.j.b = false;
            MultiViewHorizontalDataObserverView.this.j.c = null;
        }

        public void d(int i) {
            if (koq.b(this.d, i)) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "set current item with wrong index, pls check.");
            }
            dxC_(this.d.get(i));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            dxC_(view);
            ViewClickInstrumentation.clickOnView(view);
        }

        public void dxC_(View view) {
            if (!(view instanceof ScrollChartHorizontalObserverHRView)) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "the select view is wrong type, pls check");
                return;
            }
            for (int i = 0; i < this.d.size(); i++) {
                View view2 = this.d.get(i);
                if (koq.d(MultiViewHorizontalDataObserverView.this.l, i)) {
                    view2.setBackgroundResource(((Integer) MultiViewHorizontalDataObserverView.this.l.get(i)).intValue());
                }
                view2.setClickable(true);
            }
            if (!this.d.contains(view)) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "The input viewArg is not in the view set.");
                return;
            }
            int indexOf = this.d.indexOf(view);
            if (koq.d(MultiViewHorizontalDataObserverView.this.m, indexOf)) {
                view.setBackgroundResource(((Integer) MultiViewHorizontalDataObserverView.this.m.get(indexOf)).intValue());
            }
            MultiViewHorizontalDataObserverView.this.e = (ScrollChartHorizontalObserverHRView) view;
            this.c.onSelect(view, indexOf);
        }

        public void b() {
            for (HwHealthChartHolder.b bVar : MultiViewHorizontalDataObserverView.this.i) {
                HwHealthBaseBarLineDataSet fakeDataLayer = MultiViewHorizontalDataObserverView.this.c.fakeDataLayer(bVar);
                if (bVar != MultiViewHorizontalDataObserverView.d) {
                    c(bVar, fakeDataLayer);
                    MultiViewHorizontalDataObserverView.this.b.put(bVar, fakeDataLayer);
                }
            }
        }

        protected void c(HwHealthChartHolder.b bVar, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            if (MultiViewHorizontalDataObserverView.this.c == null || bVar == null || hwHealthBaseBarLineDataSet == null) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "the host view has not attached, pls attached first");
                return;
            }
            IChartLayerHolder acquireChartLayerHolder = MultiViewHorizontalDataObserverView.this.c.acquireChartLayerHolder();
            if (!(acquireChartLayerHolder instanceof HwHealthScrollChartHolder)) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "not support scrollable,init focus now only support scrollable chart!!!");
            } else {
                MultiViewHorizontalDataObserverView.this.c.manageDataSetAsProxy(hwHealthBaseBarLineDataSet, ((HwHealthScrollChartHolder) acquireChartLayerHolder).acquireStorageHelper(), MultiViewHorizontalDataObserverView.this.c.getStepDataType(), bVar);
            }
        }

        protected void d(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            if (MultiViewHorizontalDataObserverView.this.c == null || hwHealthBaseBarLineDataSet == null) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "the host view has not attached, pls attached first");
            } else {
                MultiViewHorizontalDataObserverView.this.c.unManageDataSetAsProxy(hwHealthBaseBarLineDataSet);
            }
        }

        public void b(OnSelectListener onSelectListener) {
            this.c = onSelectListener;
        }

        public void b(int i) throws a {
            if (koq.b(MultiViewHorizontalDataObserverView.this.i, i)) {
                throw new RuntimeException("Health_MultiViewHorizontalDataObserverViewThe show mode index is out of the bounds");
            }
            HwHealthChartHolder.b bVar = MultiViewHorizontalDataObserverView.this.i.get(i);
            if (bVar == MultiViewHorizontalDataObserverView.d) {
                throw new a();
            }
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet = MultiViewHorizontalDataObserverView.this.b.get(bVar);
            MultiViewHorizontalDataObserverView.this.c.addDataLayer(hwHealthBaseBarLineDataSet, bVar);
            d(hwHealthBaseBarLineDataSet);
            MultiViewHorizontalDataObserverView.this.j.b(new c.d(hwHealthBaseBarLineDataSet, bVar));
            MultiViewHorizontalDataObserverView.this.j.c(true);
        }

        public void d() {
            if (e()) {
                return;
            }
            c.d dVar = MultiViewHorizontalDataObserverView.this.j.c;
            MultiViewHorizontalDataObserverView.this.c.removeDataLayer(dVar.e);
            c(dVar.f9988a, dVar.e);
            a();
        }
    }

    public void a(List<ScrollChartHorizontalObserverHRView> list, List<HwHealthChartHolder.b> list2, boolean z) {
        if (koq.b(list) || koq.b(list2)) {
            LogUtil.b("Health_MultiViewHorizontalDataObserverView", "observerViews or showModes is empty.");
            return;
        }
        int i = 0;
        for (ScrollChartHorizontalObserverHRView scrollChartHorizontalObserverHRView : list) {
            if (!(scrollChartHorizontalObserverHRView instanceof IFocusObserverItem)) {
                LogUtil.b("Health_MultiViewHorizontalDataObserverView", "enableObserverView give to multiView need impls IFocusObserverItem");
                return;
            } else {
                int e2 = e(scrollChartHorizontalObserverHRView.getTitle());
                if (e2 > i) {
                    i = e2;
                }
            }
        }
        if (i > 0) {
            for (ScrollChartHorizontalObserverHRView scrollChartHorizontalObserverHRView2 : list) {
                if (scrollChartHorizontalObserverHRView2.getTitle() != null) {
                    scrollChartHorizontalObserverHRView2.getTitle().setMinimumWidth(i);
                }
            }
        }
        this.g.clear();
        this.g.addAll(list);
        this.i.clear();
        this.i.addAll(list2);
        this.f.notifyDataSetChanged();
        e(z);
    }

    public void setCurrentItem(int i) {
        h hVar = this.o;
        if (hVar != null) {
            hVar.d(i);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        Iterator<ScrollChartHorizontalObserverHRView> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().onRangeShow(hwHealthBaseScrollBarLineChart, i, i2);
        }
    }

    protected static class c {
        private boolean b = false;
        private d c = null;

        protected c() {
        }

        public boolean d() {
            return this.b;
        }

        public void c(boolean z) {
            this.b = z;
        }

        public d c() {
            return this.c;
        }

        public void b(d dVar) {
            this.c = dVar;
        }

        static class d {

            /* renamed from: a, reason: collision with root package name */
            private HwHealthChartHolder.b f9988a;
            private HwHealthBaseBarLineDataSet e;

            d(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, HwHealthChartHolder.b bVar) {
                this.e = hwHealthBaseBarLineDataSet;
                this.f9988a = bVar;
            }

            public HwHealthChartHolder.b e() {
                return this.f9988a;
            }
        }
    }

    private void e(boolean z) {
        this.f9987a = z;
    }

    private void b() {
        if (this.f9987a) {
            h d2 = d(this.g);
            this.o = d2;
            d2.b();
            this.o.b(new OnSelectListener() { // from class: com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView.4
                @Override // com.huawei.ui.main.stories.fitness.views.heartrate.MultiViewHorizontalDataObserverView.OnSelectListener
                public void onSelect(View view, int i) {
                    if (!MultiViewHorizontalDataObserverView.this.o.e()) {
                        MultiViewHorizontalDataObserverView.this.o.d();
                    }
                    try {
                        MultiViewHorizontalDataObserverView.this.o.b(i);
                    } catch (a unused) {
                        MultiViewHorizontalDataObserverView.this.o.d();
                    }
                    Iterator it = MultiViewHorizontalDataObserverView.this.h.iterator();
                    while (it.hasNext()) {
                        ((OnSelectListener) it.next()).onSelect(view, i);
                    }
                }
            });
            this.o.d(0);
            this.o.a();
        }
    }

    protected h d(List<ScrollChartHorizontalObserverHRView> list) {
        return new h(list);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public void initChartLinkage() {
        b();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public IFocusObserverItem acquireFocusItem() {
        ViewParent viewParent = this.e;
        if (!(viewParent instanceof IFocusObserverItem)) {
            LogUtil.b("Health_MultiViewHorizontalDataObserverView", "the focus observer view is not instance of IFocusObserverItem");
            return null;
        }
        return (IFocusObserverItem) viewParent;
    }

    public static class a extends Exception {
        private static final long serialVersionUID = 8885490331373485667L;

        a() {
            super("rest show layer");
        }
    }

    private int e(HealthTextView healthTextView) {
        if (healthTextView == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        healthTextView.measure(makeMeasureSpec, makeMeasureSpec);
        return healthTextView.getMeasuredWidth();
    }
}
