package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import defpackage.koq;
import defpackage.qta;
import defpackage.qtb;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes6.dex */
public class BodyAnalysisReportViewAdapter extends RecyclerView.Adapter {
    private List<BodyReportRecycleItem> c;
    private Context e;

    public BodyAnalysisReportViewAdapter(Context context, List<BodyReportRecycleItem> list) {
        this.e = context;
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            return null;
        }
        if (i == 0) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_body_analysis_report_item, viewGroup, false));
        }
        if (i == 1) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_body_analysis_oversea_report_item, viewGroup, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!(viewHolder instanceof a) || koq.b(this.c, i)) {
            return;
        }
        qta c = qtb.c(this.e, this.c.get(i));
        if (c == null) {
            return;
        }
        a aVar = (a) viewHolder;
        aVar.d.setText(c.b());
        aVar.f10117a.setText(c.e());
        View dIW_ = c.dIW_();
        if (dIW_ != null) {
            aVar.e.addView(dIW_);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BodyReportRecycleItem> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (Utils.o() && koq.d(this.c, i) && BodyReportRecycleItem.BodyReportType.BODY_ANALYSIS.equals(this.c.get(i).a())) ? 1 : 0;
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f10117a;
        private HealthTextView d;
        private LinearLayout e;

        a(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.body_report_item_name);
            this.f10117a = (HealthTextView) view.findViewById(R.id.body_report_item_unit);
            this.e = (LinearLayout) view.findViewById(R.id.body_report_item_detail_container);
        }
    }
}
