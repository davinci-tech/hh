package com.huawei.health.knit.section.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.chart.HealthTrendBarChart;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionPieChartTrendAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<b> c = new ArrayList();
    private final Context e;

    public SectionPieChartTrendAdapter(Context context) {
        this.e = context;
    }

    public void e(List<b> list) {
        this.c.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ajz_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.e).inflate(R.layout.section_pie_trend_row, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (koq.b(this.c, i)) {
            return;
        }
        viewHolder.e(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTrendBarChart.b f2716a;
        private final HealthTrendBarChart.b c;
        private final String d;
        private final String e;

        public b(String str, String str2, HealthTrendBarChart.b bVar, HealthTrendBarChart.b bVar2) {
            this.e = str;
            this.d = str2;
            this.f2716a = bVar;
            this.c = bVar2;
        }
    }

    protected static final class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTextView f2715a;
        private final HealthTextView b;
        private final HealthTrendBarChart c;

        public ViewHolder(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.title);
            this.f2715a = (HealthTextView) view.findViewById(R.id.subTitle);
            this.c = (HealthTrendBarChart) view.findViewById(R.id.barChart);
        }

        public void e(b bVar) {
            nsy.cMr_(this.b, bVar.e);
            nsy.cMr_(this.f2715a, bVar.d);
            HealthTrendBarChart healthTrendBarChart = this.c;
            if (healthTrendBarChart != null) {
                healthTrendBarChart.setValues(bVar.f2716a, bVar.c);
            }
        }
    }
}
