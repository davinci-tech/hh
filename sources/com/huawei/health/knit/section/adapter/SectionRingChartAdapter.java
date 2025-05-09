package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.oval.HealthOval;
import defpackage.eet;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionRingChartAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2586a;
    private Context b;
    private c c;
    private List<String> d;
    private SectionRingChartData e;
    private List<String> g;
    private List<String> h;

    public SectionRingChartAdapter(Context context, SectionRingChartData sectionRingChartData) {
        this.b = context;
        e(sectionRingChartData);
    }

    private void e(SectionRingChartData sectionRingChartData) {
        this.e = sectionRingChartData;
        this.f2586a = sectionRingChartData.d();
        this.h = sectionRingChartData.j();
        this.g = sectionRingChartData.f();
        this.d = sectionRingChartData.b();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aeb_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(this.b).inflate(R.layout.section_ring_chart_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        this.c = cVar;
        b(i);
    }

    private void b(int i) {
        if (eet.b(this.f2586a, i) && this.c.c != null) {
            this.c.c.setFillColor(this.f2586a.get(i).intValue());
        }
        if (eet.b(this.h, i) && this.c.d != null) {
            this.c.d.setText(this.h.get(i));
            if (LanguageUtil.bg(this.b) || LanguageUtil.be(this.b)) {
                this.c.d.setTextSize(2, 11.0f);
                ((LinearLayout.LayoutParams) this.c.d.getLayoutParams()).weight = 6.0f;
            }
        }
        if (eet.b(this.g, i) && this.c.f2587a != null && this.e.i()) {
            this.c.f2587a.setText(this.g.get(i));
            this.c.f2587a.setVisibility(0);
        } else if (this.c.f2587a != null) {
            this.c.f2587a.setVisibility(8);
        }
        if (eet.b(this.d, i) && this.c.e != null && this.e.g()) {
            this.c.e.setText(this.d.get(i));
            this.c.e.setVisibility(0);
        } else if (this.c.e != null) {
            this.c.e.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return eet.d(this.f2586a, this.h, this.g);
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2587a;
        HealthOval c;
        HealthTextView d;
        HealthTextView e;

        public c(View view) {
            super(view);
            this.c = (HealthOval) view.findViewById(R.id.ring_chart_legend_oval);
            this.d = (HealthTextView) view.findViewById(R.id.ring_chart_legend_title);
            this.f2587a = (HealthTextView) view.findViewById(R.id.ring_chart_item_value);
            this.e = (HealthTextView) view.findViewById(R.id.ring_chart_item_percent);
        }
    }
}
