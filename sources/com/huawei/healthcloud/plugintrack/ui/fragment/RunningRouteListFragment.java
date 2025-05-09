package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.runningroute.view.RouteAttributeAdapter;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linearlayout.LineWrappingLinearLayout;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import defpackage.emc;
import defpackage.emj;
import defpackage.emz;
import defpackage.ene;
import defpackage.enf;
import defpackage.eni;
import defpackage.enm;
import defpackage.ggs;
import defpackage.gwe;
import defpackage.gyq;
import defpackage.gyu;
import defpackage.gzi;
import defpackage.hjd;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class RunningRouteListFragment extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private ViewGroup f3723a;
    private RouteAttributeAdapter ab;
    private HealthSpinner ad;
    private Context b;
    private LoadMoreRecyclerViewAdapter c;
    private ImageView d;
    private ene f;
    private RouteAttributeAdapter g;
    private HealthSpinner h;
    private TextView i;
    private boolean j;
    private boolean l;
    private View m;
    private HealthRecycleView q;
    private int r;
    private View s;
    private HotPathListCallBack t;
    private HealthSpinner u;
    private HealthSpinner v;
    private RouteAttributeAdapter w;
    private RouteAttributeAdapter y;
    private int e = 1;
    private int k = 1;
    private boolean n = false;
    private boolean o = true;
    private gyq p = new gyq();
    private final Map<Integer, Integer> x = new HashMap();
    private hjd aa = null;

    public interface HotPathListCallBack {
        void onError(int i, String str);

        void onSuccess(emz emzVar);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        n();
        this.b = getActivity();
        return bfa_(layoutInflater, viewGroup);
    }

    private void n() {
        ene eneVar = new ene();
        this.f = eneVar;
        eneVar.c(20);
    }

    private View bfa_(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.s = layoutInflater.inflate(R.layout.layout_runing_route_list, viewGroup, false);
        m();
        o();
        l();
        if (this.j) {
            d();
        }
        return this.s;
    }

    private void o() {
        this.q = (HealthRecycleView) this.s.findViewById(R.id.recyclerView);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        this.q.setItemAnimator(defaultItemAnimator);
        this.q.setNestedScrollingEnabled(false);
        this.q.a(false);
        this.q.addItemDecoration(new ColumnLayoutItemDecoration(0, getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), 1, new int[]{getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e), 0, getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d), 0}));
        ggs.b(this.b, this.q, new HealthLinearLayoutManager(this.b, 1, false));
        c();
        this.q.setAdapter(this.c);
        q();
    }

    private void l() {
        this.f3723a = (ViewGroup) this.s.findViewById(R.id.common_no_data_layout);
        this.d = (ImageView) this.s.findViewById(R.id.common_no_data_img);
        this.i = (TextView) this.s.findViewById(R.id.common_no_data_text);
        this.m = this.s.findViewById(R.id.loading_layout);
    }

    private void m() {
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) this.s.findViewById(R.id.running_route_type_list);
        horizontalScrollView.setHorizontalFadingEdgeEnabled(true);
        horizontalScrollView.setOverScrollMode(1);
    }

    private void d(List<eni> list, final int i) {
        HealthSpinner b = b(i);
        if (b == null) {
            ReleaseLogUtil.c("RunningPathListFragment", "spinner is null with ", Integer.valueOf(i));
            return;
        }
        if (koq.b(list) || list.size() < 2) {
            b.setVisibility(8);
            ReleaseLogUtil.e("RunningPathListFragment", "pathAttributes less than 2.");
            return;
        }
        final RouteAttributeAdapter routeAttributeAdapter = new RouteAttributeAdapter(BaseApplication.e(), R.layout.multiple_line_hwspinner_item, list);
        routeAttributeAdapter.setDropDownViewResource(R.layout.hwspinner_dropdown_item);
        e(i, routeAttributeAdapter);
        x();
        b.setDynamicWidthEnabled(true);
        b.setDropDownHorizontalOffset(getResources().getDimensionPixelSize(R.dimen._2131362906_res_0x7f0a045a));
        b.setAdapter((SpinnerAdapter) routeAttributeAdapter);
        b.setTag(false);
        b.setSelection(a(i), false);
        b.setVisibility(0);
        b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment.5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                if (RunningRouteListFragment.this.a(i) != i2) {
                    RunningRouteListFragment.this.x.put(Integer.valueOf(i), Integer.valueOf(i2));
                    RunningRouteListFragment.this.a(i, routeAttributeAdapter.b(i2));
                    RunningRouteListFragment.this.e();
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                    return;
                }
                ReleaseLogUtil.e("RunningPathListFragment", "spinner not changed");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
            }
        });
    }

    private HealthSpinner b(int i) {
        if (R.id.spinner_pathTheme == i) {
            if (this.u == null) {
                this.u = (HealthSpinner) this.s.findViewById(i);
            }
            return this.u;
        }
        if (R.id.spinner_distance == i) {
            if (this.h == null) {
                this.h = (HealthSpinner) this.s.findViewById(i);
            }
            return this.h;
        }
        if (R.id.spinner_pathType == i) {
            if (this.ad == null) {
                this.ad = (HealthSpinner) this.s.findViewById(i);
            }
            return this.ad;
        }
        if (this.v == null) {
            this.v = (HealthSpinner) this.s.findViewById(i);
        }
        return this.v;
    }

    private void e(int i, RouteAttributeAdapter routeAttributeAdapter) {
        if (R.id.spinner_distance == i) {
            this.g = routeAttributeAdapter;
            return;
        }
        if (R.id.spinner_pathType == i) {
            this.ab = routeAttributeAdapter;
        } else if (R.id.spinner_pathTheme == i) {
            this.w = routeAttributeAdapter;
        } else {
            this.y = routeAttributeAdapter;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i) {
        Integer num;
        if (!this.x.containsKey(Integer.valueOf(i)) || (num = this.x.get(Integer.valueOf(i))) == null) {
            return 0;
        }
        return num.intValue();
    }

    private List<String> e(int i, List<Integer> list) {
        emj a2 = this.p.a();
        if (a2 == null || koq.b(list)) {
            return new ArrayList();
        }
        if (i == 0) {
            return RunningRouteUtils.c(a2.c(), list);
        }
        return RunningRouteUtils.c(a2.e(), list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str) {
        for (Attribute attribute : RunningRouteUtils.e(getActivity())) {
            if (attribute.getId().equals(str)) {
                return attribute.getName();
            }
        }
        return "";
    }

    private void c() {
        LogUtil.d("RunningPathListFragment", "get user current location");
        this.c = new AnonymousClass2(new ArrayList(), this.q, R.layout.list_item_running_route);
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends LoadMoreRecyclerViewAdapter<enf> {
        AnonymousClass2(List list, RecyclerView recyclerView, int i) {
            super(list, recyclerView, i);
        }

        @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void convert(final RecyclerHolder recyclerHolder, int i, enf enfVar) {
            recyclerHolder.b(R.id.title, enfVar.h());
            recyclerHolder.b(R.id.totalDistance, RunningRouteUtils.a(R.plurals._2130903096_res_0x7f030038, R.plurals._2130903095_res_0x7f030037, enfVar.m()));
            RunningRouteListFragment runningRouteListFragment = RunningRouteListFragment.this;
            recyclerHolder.b(R.id.distanceToUser, RunningRouteUtils.b(R.plurals._2130903406_res_0x7f03016e, R.plurals._2130903407_res_0x7f03016f, runningRouteListFragment.a(enfVar, runningRouteListFragment.aa), 2));
            recyclerHolder.b(R.id.people_num_check_in, UnitUtil.e(enfVar.c(), 1, 0));
            recyclerHolder.a(R.id.distanceToUser, RunningRouteListFragment.this.o ? 0 : 8);
            final LineWrappingLinearLayout lineWrappingLinearLayout = (LineWrappingLinearLayout) recyclerHolder.itemView.findViewById(R.id.distanceParent);
            RunningRouteListFragment.this.bfb_(lineWrappingLinearLayout, recyclerHolder.itemView);
            lineWrappingLinearLayout.postDelayed(new Runnable() { // from class: hhx
                @Override // java.lang.Runnable
                public final void run() {
                    RunningRouteListFragment.AnonymousClass2.this.a(lineWrappingLinearLayout, recyclerHolder);
                }
            }, 100L);
            RunningRouteListFragment.this.a(recyclerHolder, enfVar);
            if (!koq.b(enfVar.j())) {
                recyclerHolder.a(R.id.flag, 0);
                recyclerHolder.b(R.id.flag, RunningRouteListFragment.this.d(enfVar.j().get(0)));
            } else {
                recyclerHolder.a(R.id.flag, 8);
            }
            ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.image);
            String str = imageView.getTag() instanceof String ? (String) imageView.getTag() : null;
            enm f = enfVar.f();
            if ((TextUtils.isEmpty(str) || !(str == null || str.equals(f.b()))) && f != null) {
                imageView.setTag(f.b());
                recyclerHolder.b(R.id.image, f.b(), nrf.e);
            }
        }

        public /* synthetic */ void a(LineWrappingLinearLayout lineWrappingLinearLayout, RecyclerHolder recyclerHolder) {
            RunningRouteListFragment.this.bfb_(lineWrappingLinearLayout, recyclerHolder.itemView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(RecyclerHolder recyclerHolder, enf enfVar) {
        LinearLayout linearLayout = (LinearLayout) recyclerHolder.itemView.findViewById(R.id.labelList);
        linearLayout.removeAllViews();
        beX_(e(1, enfVar.k()), linearLayout);
        beX_(e(0, enfVar.l()), linearLayout);
    }

    private void beX_(List<String> list, LinearLayout linearLayout) {
        if (koq.c(list)) {
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    linearLayout.addView(beY_(str), beZ_());
                }
            }
        }
    }

    private View beY_(String str) {
        TextView textView = (TextView) LayoutInflater.from(this.b).inflate(R.layout.list_item_running_route_label, (ViewGroup) null).findViewById(R.id.text1);
        nsy.cMr_(textView, str);
        return textView;
    }

    private LinearLayout.LayoutParams beZ_() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7));
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double a(enf enfVar, hjd hjdVar) {
        if (this.aa == null) {
            this.aa = RunningRouteUtils.a();
            return enfVar.e();
        }
        return RunningRouteUtils.a(hjdVar, RunningRouteUtils.b(enfVar.g()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bfb_(LineWrappingLinearLayout lineWrappingLinearLayout, View view) {
        HealthTextView healthTextView;
        if (lineWrappingLinearLayout.a() && this.o && (healthTextView = (HealthTextView) view.findViewById(R.id.distanceToUser)) != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams.setMarginStart(0);
            healthTextView.setLayoutParams(layoutParams);
        }
    }

    private void q() {
        this.c.setLoadMoreWhenLoadingViewShow(true);
        this.c.setOnMoreListener(new LoadMoreRecyclerViewAdapter.OnMoreListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment.3
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter.OnMoreListener
            public void onLoadingMore() {
                if (RunningRouteListFragment.this.n) {
                    return;
                }
                RunningRouteListFragment.this.n = true;
                RunningRouteListFragment.this.s();
            }
        });
        this.c.setOnItemClickListener(new LoadMoreRecyclerViewAdapter.OnItemClickListener<enf>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment.4
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter.OnItemClickListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, enf enfVar) {
                Intent intent = new Intent(RunningRouteListFragment.this.getActivity(), (Class<?>) ClockingRankActivity.class);
                intent.putExtra("PATH_ID", enfVar.i());
                intent.putExtra("ENTRANCE_ACTIVITY", 1);
                intent.putExtra("pathClass", enfVar.b());
                intent.putExtra("ROUTE_INFO", RunningRouteListFragment.this.p);
                intent.putExtra("pathLocation", gwe.c(enfVar.g()));
                RunningRouteListFragment.this.startActivity(intent);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RecyclerView.LayoutManager layoutManager = this.q.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            ggs.b(this.b, this.q, (LinearLayoutManager) layoutManager);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        int i = this.k + 1;
        this.e = i;
        LogUtil.d("RunningPathListFragment", "up to loadMore currpage:", Integer.valueOf(i), "--->old dataSize: ", Integer.valueOf(this.c.size()));
        this.f.d(this.e);
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        e(false);
        if (this.e == 1) {
            this.c.clear();
        }
        j();
        if (this.c.size() != 0) {
            this.c.loadError();
        }
        this.n = false;
    }

    private void e(boolean z) {
        if (this.e != 1) {
            return;
        }
        if (this.p.j()) {
            LogUtil.d("RunningPathListFragment", "first init list, no bi");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("searchResult", z + "");
        hashMap.put(DistrictSearchQuery.KEYWORDS_CITY, gzi.d() != null ? gzi.d().getCityName() : "");
        hashMap.put("routeStyle", Integer.valueOf(this.f.g()));
        hashMap.put(BleConstants.SPORT_TYPE, 258);
        hashMap.put("orderType", this.f.i() == 1 ? "distance" : "popular");
        hashMap.put(CommonUtil.PAGE_TYPE, 1);
        ixx.d().d(BaseApplication.e(), "1040082", hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<enf> list, int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = true;
        if (this.e == 1) {
            this.c.clear();
        }
        this.k = this.e;
        if (koq.c(list)) {
            LogUtil.d("RunningPathListFragment", "getData Size: ", Integer.valueOf(list.size()), " ,totalSize: ", Integer.valueOf(i), " , adapter data size: ", Integer.valueOf(this.c.getData().size()), " ,mPageFlag: ", Integer.valueOf(this.k));
            if (this.c.getData().size() + list.size() >= i) {
                this.c.noMoreLoad(list);
            } else {
                this.c.enableMoreLoad(list);
            }
        } else {
            this.c.noMoreLoad();
            z = false;
        }
        if (i != 0) {
            e(z);
        }
        this.n = false;
        j();
        RunningRouteUtils.b(1004, SystemClock.elapsedRealtime() - elapsedRealtime);
    }

    private void j() {
        if (this.m.getVisibility() == 0) {
            this.m.setVisibility(8);
        }
    }

    private void r() {
        this.m.setVisibility(0);
    }

    public void d() {
        LogUtil.d("RunningPathListFragment", "refreshFilter");
        if (this.c == null || !isAdded()) {
            ReleaseLogUtil.d("RunningPathListFragment", "onCreateView not finish");
            this.j = true;
            return;
        }
        HotPathCityInfo d = gzi.d();
        String cityId = d != null ? d.getCityId() : "";
        a();
        r();
        hjd a2 = RunningRouteUtils.a(new a(this, SystemClock.elapsedRealtime()));
        if (this.l || a2 == null || Math.abs(a2.b) <= 1.0E-6d || Math.abs(a2.d) <= 1.0E-6d) {
            return;
        }
        this.l = true;
        ene a3 = new ene.a().d(a2.b).e(a2.d).c(cityId).i(this.p.c()).h(this.p.d()).e(this.p.i()).d(this.p.b()).b(1).a();
        a3.g(this.r);
        a(a2, a3);
        this.j = false;
    }

    private void x() {
        RouteAttributeAdapter routeAttributeAdapter = this.ab;
        if (routeAttributeAdapter != null) {
            this.x.put(Integer.valueOf(R.id.spinner_pathType), Integer.valueOf(routeAttributeAdapter.d(this.p.d())));
        }
        RouteAttributeAdapter routeAttributeAdapter2 = this.g;
        if (routeAttributeAdapter2 != null) {
            this.x.put(Integer.valueOf(R.id.spinner_distance), Integer.valueOf(routeAttributeAdapter2.d(this.p.b())));
        }
        RouteAttributeAdapter routeAttributeAdapter3 = this.y;
        if (routeAttributeAdapter3 != null) {
            this.x.put(Integer.valueOf(R.id.spinner_sortType), Integer.valueOf(routeAttributeAdapter3.d(this.p.c())));
        }
        RouteAttributeAdapter routeAttributeAdapter4 = this.w;
        if (routeAttributeAdapter4 != null) {
            this.x.put(Integer.valueOf(R.id.spinner_pathTheme), Integer.valueOf(routeAttributeAdapter4.d(this.p.i())));
        }
    }

    private void g() {
        HealthSpinner healthSpinner = this.v;
        if (healthSpinner != null) {
            healthSpinner.setVisibility(8);
        }
        HealthSpinner healthSpinner2 = this.ad;
        if (healthSpinner2 != null) {
            healthSpinner2.setVisibility(8);
        }
        HealthSpinner healthSpinner3 = this.h;
        if (healthSpinner3 != null) {
            healthSpinner3.setVisibility(8);
        }
        HealthSpinner healthSpinner4 = this.u;
        if (healthSpinner4 != null) {
            healthSpinner4.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        nsy.cMA_(this.f3723a, 0);
        nsy.cMm_(this.d, nsf.cKq_(R.drawable.pic_no_line_available));
        nsy.cMq_(this.i, R.string._2130840113_res_0x7f020a31);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        nsy.cMA_(this.f3723a, 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.d("RunningPathListFragment", "refreshFilter");
        if (this.c == null) {
            return;
        }
        a();
        r();
        h();
    }

    static class a implements GolfGetLocation.GetLocationCallback {
        private final long d;
        private final WeakReference<RunningRouteListFragment> e;

        a(RunningRouteListFragment runningRouteListFragment, long j) {
            this.e = new WeakReference<>(runningRouteListFragment);
            this.d = j;
        }

        @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
        public void onSuccess(hjd hjdVar) {
            RunningRouteListFragment runningRouteListFragment = this.e.get();
            if (runningRouteListFragment != null && runningRouteListFragment.isAdded()) {
                if (runningRouteListFragment.l) {
                    return;
                }
                RunningRouteUtils.b(1002, SystemClock.elapsedRealtime() - this.d);
                runningRouteListFragment.l = true;
                runningRouteListFragment.a(hjdVar, runningRouteListFragment.f);
                return;
            }
            LogUtil.d("RunningPathListFragment", "get location onSuccess fragment == null");
        }

        @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
        public void onFailure(int i, String str) {
            LogUtil.d("RunningPathListFragment", "get location fail, errCode: ", Integer.valueOf(i), ", errMsg: ", str);
        }
    }

    public void a() {
        this.l = false;
        this.e = 1;
        this.k = 1;
        ene eneVar = this.f;
        if (eneVar != null) {
            eneVar.d(1);
        }
        LoadMoreRecyclerViewAdapter loadMoreRecyclerViewAdapter = this.c;
        if (loadMoreRecyclerViewAdapter != null) {
            loadMoreRecyclerViewAdapter.clear();
            this.c.noMoreLoad();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(hjd hjdVar, ene eneVar) {
        if (hjdVar != null) {
            LogUtil.d("RunningPathListFragment", "get location success");
            this.f.c(hjdVar.b);
            this.f.b(hjdVar.d);
        }
        this.f.c(eneVar.b());
        this.f.j(eneVar.i());
        this.f.f(eneVar.g());
        this.f.e(eneVar.d());
        this.f.e(eneVar.c());
        this.f.b(eneVar.e());
        this.f.a(eneVar.h());
        this.f.d(this.e);
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        if (R.id.spinner_pathTheme == i) {
            this.f.a(i2);
            this.p.d(i2);
            return;
        }
        if (R.id.spinner_distance == i) {
            this.f.e(i2);
            this.p.b(i2);
        } else if (R.id.spinner_pathType == i) {
            this.f.f(i2);
            this.p.e(i2);
        } else if (R.id.spinner_sortType == i) {
            this.f.j(i2);
            this.p.a(i2);
        } else {
            ReleaseLogUtil.c("RunningPathListFragment", "refreshFilterParam with error spinner");
        }
    }

    public void e(emj emjVar) {
        t();
        if (emjVar == null) {
            return;
        }
        c(emjVar.c(), R.id.spinner_pathType);
        if (!UnitUtil.h()) {
            c(emjVar.b(), R.id.spinner_distance);
        }
        c(emjVar.e(), R.id.spinner_pathTheme);
    }

    private void c(List<eni> list, int i) {
        if (koq.b(list)) {
            return;
        }
        list.sort(Comparator.comparingInt(new gyu()));
        d(list, i);
    }

    private void t() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new eni(nsf.h(R.string._2130840080_res_0x7f020a10), 1));
        arrayList.add(new eni(nsf.h(R.string._2130840079_res_0x7f020a0f), 4));
        d(arrayList, R.id.spinner_sortType);
    }

    private void h() {
        final boolean f = this.f.f();
        LogUtil.d("RunningPathListFragment", "getHotPathsData", Boolean.valueOf(f));
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        emc.d().getHotPaths(this.f, new UiCallback<emz>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emz emzVar) {
                if (!RunningRouteListFragment.this.isAdded()) {
                    ReleaseLogUtil.d("RunningPathListFragment", "fragment not add");
                    return;
                }
                RunningRouteUtils.b(1003, SystemClock.elapsedRealtime() - elapsedRealtime);
                RunningRouteListFragment.this.k();
                List<enf> e = emzVar.e();
                if (RunningRouteListFragment.this.t != null && RunningRouteListFragment.this.getUserVisibleHint()) {
                    RunningRouteListFragment.this.t.onSuccess(emzVar);
                }
                if (f) {
                    RunningRouteListFragment.this.p.e(emzVar.c());
                }
                RunningRouteListFragment runningRouteListFragment = RunningRouteListFragment.this;
                runningRouteListFragment.e(runningRouteListFragment.p.a());
                RunningRouteListFragment.this.b(e, emzVar.b());
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                if (!RunningRouteListFragment.this.isAdded()) {
                    ReleaseLogUtil.d("RunningPathListFragment", "fragment not add");
                    return;
                }
                LogUtil.e("RunningPathListFragment", "getHotPaths errorCode ", Integer.valueOf(i), " , errorInfo", str);
                if (RunningRouteListFragment.this.e == 1 && RunningRouteListFragment.this.t != null && RunningRouteListFragment.this.getUserVisibleHint()) {
                    RunningRouteListFragment.this.t.onError(i, str);
                }
                RunningRouteListFragment.this.f();
                if (f) {
                    RunningRouteListFragment.this.p();
                    return;
                }
                RunningRouteListFragment.this.k();
                RunningRouteListFragment runningRouteListFragment = RunningRouteListFragment.this;
                runningRouteListFragment.e(runningRouteListFragment.p.a());
            }
        });
    }

    public void c(HotPathListCallBack hotPathListCallBack) {
        this.t = hotPathListCallBack;
    }

    public void e(int i) {
        this.r = i;
        this.p.c(i);
    }

    public gyq b() {
        return this.p;
    }

    public void e(gyq gyqVar) {
        if (gyqVar != null) {
            this.p = gyqVar;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.d("RunningPathListFragment", "onActivityResult");
        if (intent == null) {
            LogUtil.d("RunningPathListFragment", "Intent is null");
            return;
        }
        this.aa = RunningRouteUtils.a();
        if (intent.getBooleanExtra("IS_CITY_CHANGE", false)) {
            ReleaseLogUtil.e("RunningPathListFragment", "city is change, init param");
            e(new gyq(this.r));
            g();
            k();
        }
        Serializable serializableExtra = intent.getSerializableExtra("ROUTE_INFO");
        if (serializableExtra instanceof gyq) {
            gyq gyqVar = (gyq) serializableExtra;
            if (gyqVar.e() == this.r) {
                e(gyqVar);
            }
        }
        d();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
