package com.huawei.featureuserprofile.route.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.huawei.featureuserprofile.route.adapter.RouteImportAdapter;
import com.huawei.featureuserprofile.route.bean.KomootRouteBean;
import com.huawei.featureuserprofile.route.bean.KomootRouteData;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.btx;
import defpackage.koq;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class RouteImportAdapter extends RecyclerView.Adapter<c> {
    private final CallBack b;
    private final Context d;
    private RecyclerView g;
    private List<KomootRouteData> e = new ArrayList();
    private int j = 5;
    private final Map<Long, KomootRouteBean.Tour> i = new ArrayMap();
    private final Map<Long, KomootRouteBean.Tour> f = new ArrayMap();
    private final Map<Long, KomootRouteBean.Tour> h = new ArrayMap();

    /* renamed from: a, reason: collision with root package name */
    private String f2013a = "All";
    private boolean c = false;

    public interface CallBack {
        void onItemCheckedChanged();

        void onLoadingMore();
    }

    public RouteImportAdapter(Context context, RecyclerView recyclerView, CallBack callBack) {
        this.d = context;
        this.b = callBack;
        this.g = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.featureuserprofile.route.adapter.RouteImportAdapter.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                super.onScrollStateChanged(recyclerView2, i);
                if (i == 0 && RouteImportAdapter.this.j()) {
                    RouteImportAdapter.this.b.onLoadingMore();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                super.onScrolled(recyclerView2, i, i2);
                if (RouteImportAdapter.this.j()) {
                    RouteImportAdapter.this.b();
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: uO_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new c(LayoutInflater.from(this.d).inflate(R.layout.hw_load_more, viewGroup, false), 1);
        }
        return new c(LayoutInflater.from(this.d).inflate(R.layout.item_import_route, viewGroup, false), 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        if (koq.b(this.e) || i >= this.e.size()) {
            return;
        }
        KomootRouteData komootRouteData = this.e.get(i);
        if (komootRouteData.getType() == 1) {
            cVar.d.setVisibility(this.c ? 8 : 0);
            cVar.f2014a.setText(this.c ? this.d.getString(R.string._2130838846_res_0x7f02053e) : this.d.getString(R.string._2130838845_res_0x7f02053d));
            return;
        }
        final KomootRouteBean.Tour data = komootRouteData.getData();
        cVar.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.featureuserprofile.route.adapter.RouteImportAdapter$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RouteImportAdapter.this.uN_(data, compoundButton, z);
            }
        });
        if (!this.h.isEmpty() && this.h.containsKey(Long.valueOf(data.getId()))) {
            this.i.put(Long.valueOf(data.getId()), data);
            this.h.remove(Long.valueOf(data.getId()));
            LogUtil.b("RouteImportAdapter", "ToRestoredTours remove -->" + data.toString());
        }
        if (!this.f.isEmpty() && this.f.size() >= this.j) {
            boolean containsKey = this.i.containsKey(Long.valueOf(data.getId()));
            cVar.b.setVisibility(containsKey ? 0 : 8);
            cVar.e.setVisibility(containsKey ? 8 : 0);
        } else {
            cVar.b.setVisibility(0);
            cVar.e.setVisibility(8);
        }
        if (!this.i.isEmpty()) {
            cVar.b.setChecked(this.i.containsKey(Long.valueOf(data.getId())));
        } else {
            cVar.b.setChecked(false);
        }
        Glide.with(this.d).load(btx.uP_(data.getPreviewSrc(), cVar.c)).placeholder(R.drawable._2131430152_res_0x7f0b0b08).error(R.drawable._2131430152_res_0x7f0b0b08).into(cVar.c);
        cVar.h.setText(data.getName());
        cVar.j.setText(btx.d(this.d, data.getDistance().doubleValue()));
        cVar.f.setText(btx.e(this.d, data.getDuration()));
        cVar.i.setText(btx.d(data.getDate()));
    }

    /* synthetic */ void uN_(KomootRouteBean.Tour tour, CompoundButton compoundButton, boolean z) {
        d(tour, z);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void d(KomootRouteBean.Tour tour, boolean z) {
        if (z) {
            if (!this.i.containsKey(Long.valueOf(tour.getId()))) {
                this.i.put(Long.valueOf(tour.getId()), tour);
                a(tour, true);
            }
        } else {
            this.i.remove(Long.valueOf(tour.getId()));
            a(tour, false);
        }
        h();
        this.b.onItemCheckedChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.e)) {
            return 0;
        }
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.e) || i >= this.e.size()) {
            return 1;
        }
        return this.e.get(i).getType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        if (this.g.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.g.getLayoutManager();
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            if (!this.c && findLastVisibleItemPosition == linearLayoutManager.getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    private void h() {
        if ((this.f.size() == this.j || this.f.size() == this.j - 1) && this.g.getScrollState() == 0 && !this.g.isComputingLayout()) {
            notifyItemRangeChanged(0, this.e.size() - 1);
        }
    }

    public void b(List<KomootRouteData> list, boolean z) {
        if (z) {
            i();
            this.e.clear();
            this.i.clear();
            this.c = false;
        }
        LogUtil.b("RouteImportAdapter", "cache size = " + this.f.size() + ", select type = " + this.f2013a);
        f();
        this.e.addAll(list);
        n();
        notifyDataSetChanged();
    }

    public void e(String str) {
        this.f2013a = str;
    }

    public void b(List<KomootRouteBean.Tour> list) {
        if (koq.b(list)) {
            return;
        }
        for (KomootRouteBean.Tour tour : list) {
            this.h.put(Long.valueOf(tour.getId()), tour);
            LogUtil.b("RouteImportAdapter", "ToRestoredTours put -->" + tour.toString());
        }
        this.f.putAll(this.h);
    }

    private void i() {
        for (Map.Entry<Long, KomootRouteBean.Tour> entry : this.i.entrySet()) {
            LogUtil.b("RouteImportAdapter", "cache selected:" + entry.toString());
            this.f.put(entry.getKey(), entry.getValue());
        }
    }

    private void a(KomootRouteBean.Tour tour, boolean z) {
        if (z) {
            if (this.f.containsKey(Long.valueOf(tour.getId()))) {
                LogUtil.b("RouteImportAdapter", "already cached:" + tour.toString());
                return;
            } else {
                this.f.put(Long.valueOf(tour.getId()), tour);
                LogUtil.b("RouteImportAdapter", "add new cache --> " + tour.toString() + ", current is = " + this.f.size());
                return;
            }
        }
        if (this.f.containsKey(Long.valueOf(tour.getId()))) {
            this.f.remove(Long.valueOf(tour.getId()));
            LogUtil.b("RouteImportAdapter", "remove cache --> " + tour.toString() + ", remain is = " + this.f.size());
        }
    }

    private void n() {
        if (this.f.isEmpty()) {
            return;
        }
        if (this.f2013a.equalsIgnoreCase("All")) {
            LogUtil.b("RouteImportAdapter", "restore all cache selected size:" + this.f.size());
            this.i.putAll(this.f);
            return;
        }
        g();
    }

    private void g() {
        for (Map.Entry<Long, KomootRouteBean.Tour> entry : this.f.entrySet()) {
            if (this.f2013a.equalsIgnoreCase(entry.getValue().getSport())) {
                LogUtil.b("RouteImportAdapter", "restore cache selected:" + entry.toString());
                this.i.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void c(int i) {
        this.j = Math.min(i, 5);
    }

    public void a() {
        this.c = true;
        notifyItemChanged(this.e.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.e.size() > 0) {
            List<KomootRouteData> list = this.e;
            if (list.get(list.size() - 1).getType() == 1) {
                return;
            }
        }
        new Handler().post(new Runnable() { // from class: btr
            @Override // java.lang.Runnable
            public final void run() {
                RouteImportAdapter.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        this.e.add(new KomootRouteData(1));
        notifyItemChanged(this.e.size() - 1);
    }

    private void f() {
        if (this.e.size() != 0) {
            List<KomootRouteData> list = this.e;
            if (list.get(list.size() - 1).getType() != 1) {
                return;
            }
            List<KomootRouteData> list2 = this.e;
            list2.remove(list2.size() - 1);
        }
    }

    public Map<Long, KomootRouteBean.Tour> c() {
        return this.i;
    }

    public Map<Long, KomootRouteBean.Tour> e() {
        return this.f;
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2014a;
        HealthCheckBox b;
        ImageView c;
        HealthProgressBar d;
        View e;
        HealthTextView f;
        HealthTextView h;
        HealthTextView i;
        HealthTextView j;

        c(View view, int i) {
            super(view);
            if (i == 1) {
                this.d = (HealthProgressBar) view.findViewById(R.id.progress_bar_loading);
                this.f2014a = (HealthTextView) view.findViewById(R.id.tv_loading_text);
                return;
            }
            this.c = (ImageView) view.findViewById(R.id.img_thumb);
            this.h = (HealthTextView) view.findViewById(R.id.tv_route_name);
            this.f = (HealthTextView) view.findViewById(R.id.tv_route_duration);
            this.j = (HealthTextView) view.findViewById(R.id.tv_route_distance);
            this.i = (HealthTextView) view.findViewById(R.id.tv_route_date);
            this.b = (HealthCheckBox) view.findViewById(R.id.ck_select);
            this.e = view.findViewById(R.id.view_gray_mask);
        }
    }
}
