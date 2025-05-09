package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gxt;
import defpackage.koq;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SkippingPerformanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final Context f3704a;
    private int b;
    private final List<gxt> c = new ArrayList();
    private View e;

    public SkippingPerformanceAdapter(Context context) {
        this.f3704a = context.getApplicationContext();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.e != null && i == 0) {
            return new d(this.e);
        }
        return new d(LayoutInflater.from(this.f3704a).inflate(R.layout.layout_track_skipping_performance_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 0) {
            return;
        }
        if (!(viewHolder instanceof d)) {
            LogUtil.h("SkippingPerformanceAdapter", "onBindViewHolder holder is not an instance of SkippingPerformanceViewHolder");
            return;
        }
        d dVar = (d) viewHolder;
        int a2 = a(dVar);
        if (a2 == 0) {
            dVar.b.setVisibility(8);
        } else {
            dVar.b.setVisibility(0);
        }
        if (koq.b(this.c, a2)) {
            return;
        }
        dVar.b(a2);
    }

    private int a(d dVar) {
        int layoutPosition = dVar.getLayoutPosition();
        return this.e == null ? layoutPosition : layoutPosition - 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e == null ? this.c.size() : this.c.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (this.e != null && i == 0) ? 0 : 1;
    }

    public void bdG_(View view) {
        this.e = view;
        notifyItemInserted(0);
    }

    public void a(List<gxt> list, int i) {
        this.b = i;
        this.c.clear();
        this.c.addAll(list);
        LogUtil.c("SkippingPerformanceAdapter", "setUpData size = ", Integer.valueOf(this.c.size()));
        notifyDataSetChanged();
    }

    public void c() {
        this.c.clear();
    }

    class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f3705a;
        HealthDivider b;
        HealthTextView c;
        HealthTextView d;
        HealthTextView e;

        d(View view) {
            super(view);
            if (view == SkippingPerformanceAdapter.this.e) {
                return;
            }
            this.b = (HealthDivider) view.findViewById(R.id.skipping_performance_divider);
            this.d = (HealthTextView) view.findViewById(R.id.skipping_performance_category);
            this.f3705a = (HealthTextView) view.findViewById(R.id.skipping_performance_score);
            this.c = (HealthTextView) view.findViewById(R.id.skipping_performance_data_unit);
            this.e = (HealthTextView) view.findViewById(R.id.skipping_performance_rank_score);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        void b(int i) {
            char c;
            gxt gxtVar = (gxt) SkippingPerformanceAdapter.this.c.get(i);
            if (gxtVar == null) {
                LogUtil.a("SkippingPerformanceAdapter", "setData performanceData is null");
                return;
            }
            this.f3705a.setTextColor(SkippingPerformanceAdapter.this.f3704a.getResources().getColor(SkippingPerformanceAdapter.this.b == i ? R.color._2131296651_res_0x7f09018b : R.color._2131299236_res_0x7f090ba4));
            String b = gxtVar.b();
            b.hashCode();
            switch (b.hashCode()) {
                case -2136791786:
                    if (b.equals("enduranceTimeAbility")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 346215715:
                    if (b.equals("enduranceAbility")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 660147460:
                    if (b.equals("maxSkipSpeed")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1571615569:
                    if (b.equals("maxSkippingTimes")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 2147412359:
                    if (b.equals("skipNum")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                d(gxtVar);
                return;
            }
            if (c == 1) {
                d(R.string._2130847664_res_0x7f0227b0, SkippingPerformanceAdapter.this.f3704a.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, ""), gxtVar);
                return;
            }
            if (c == 2) {
                d(R.string._2130847663_res_0x7f0227af, SkippingPerformanceAdapter.this.f3704a.getString(R.string._2130843710_res_0x7f02183e), gxtVar);
                return;
            }
            if (c == 3) {
                d(R.string._2130847666_res_0x7f0227b2, SkippingPerformanceAdapter.this.f3704a.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, ""), gxtVar);
            } else if (c == 4) {
                d(R.string._2130847665_res_0x7f0227b1, SkippingPerformanceAdapter.this.f3704a.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, ""), gxtVar);
            } else {
                LogUtil.a("SkippingPerformanceAdapter", "Unknown type!");
            }
        }

        void d(gxt gxtVar) {
            this.f3705a.setText(UnitUtil.e(gxtVar.a(), 1, 2));
            this.c.setText(SkippingPerformanceAdapter.this.f3704a.getResources().getQuantityString(R.plurals._2130903233_res_0x7f0300c1, 0, ""));
            this.d.setText(R.string._2130846536_res_0x7f022348);
            this.e.setText(UnitUtil.e(gxtVar.c() < 0.0f ? 0.0d : gxtVar.c(), 2, 1));
        }

        void d(int i, String str, gxt gxtVar) {
            this.d.setText(i);
            this.f3705a.setText(UnitUtil.e(gxtVar.a(), 1, 0));
            this.c.setText(str);
            this.e.setText(UnitUtil.e(gxtVar.c() < 0.0f ? 0.0d : gxtVar.c(), 2, 1));
        }
    }
}
