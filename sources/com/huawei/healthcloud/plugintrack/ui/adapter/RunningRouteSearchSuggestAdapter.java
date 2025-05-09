package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteLocationSearchActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dpf;
import defpackage.hjd;
import defpackage.koq;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class RunningRouteSearchSuggestAdapter extends RecyclerView.Adapter<a> {

    /* renamed from: a, reason: collision with root package name */
    private String f3702a;
    private List<hjd> c;
    private Context e;
    private List<String> d = new ArrayList();
    private List<String> b = new ArrayList();

    public RunningRouteSearchSuggestAdapter(Context context, List<hjd> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.e = context;
        arrayList.addAll(list);
        this.b.addAll(list2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: bdu_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.e).inflate(R.layout.running_route_search_suggest_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, final int i) {
        nsy.cMA_(aVar.f3703a, i == getItemCount() + (-1) ? 8 : 0);
        if (!koq.d(this.b, i) || aVar.c == null) {
            return;
        }
        aVar.c.setText(dpf.Ym_(this.b.get(i), this.f3702a));
        aVar.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.RunningRouteSearchSuggestAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Activity activity = BaseApplication.getActivity();
                if ((activity instanceof RunningRouteLocationSearchActivity) && koq.d(RunningRouteSearchSuggestAdapter.this.c, i)) {
                    HotPathCityInfo hotPathCityInfo = new HotPathCityInfo();
                    hotPathCityInfo.setCityName((String) RunningRouteSearchSuggestAdapter.this.d.get(i));
                    hjd hjdVar = (hjd) RunningRouteSearchSuggestAdapter.this.c.get(i);
                    hotPathCityInfo.setCityCenter(new GpsPoint(hjdVar.b, hjdVar.d));
                    ((RunningRouteLocationSearchActivity) activity).c(hotPathCityInfo);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void b(String str) {
        this.f3702a = str;
    }

    public void c(List<String> list, List<String> list2, List<hjd> list3) {
        this.d.clear();
        this.b.clear();
        this.c.clear();
        this.d.addAll(list);
        this.b.addAll(list2);
        this.c.addAll(list3);
        notifyDataSetChanged();
    }

    public void d() {
        this.b.clear();
        this.c.clear();
        notifyDataSetChanged();
    }

    public boolean b() {
        return this.b.size() > 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f3703a;
        private HealthTextView c;

        a(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.search_suggest_view);
            this.f3703a = (LinearLayout) view.findViewById(R.id.search_suggest_divider);
        }
    }
}
