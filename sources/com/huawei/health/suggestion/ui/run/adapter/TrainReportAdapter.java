package com.huawei.health.suggestion.ui.run.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.health.R;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.run.holder.BaseReportHolder;
import com.huawei.health.suggestion.ui.run.holder.ReportBestHolder;
import com.huawei.health.suggestion.ui.run.holder.ReportChartHolder;
import com.huawei.health.suggestion.ui.run.holder.ReportFootHolder;
import com.huawei.health.suggestion.ui.run.holder.ReportHeaderHolder;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TrainReportAdapter extends RecyclerView.Adapter<BaseReportHolder> {
    private List<Pair<Integer, Object>> c = new ArrayList(10);
    private final LayoutInflater e;

    public TrainReportAdapter(Context context, Plan plan, PlanStat planStat, PlanRecord planRecord, List<WorkoutRecord> list) {
        this.e = LayoutInflater.from(context);
        e(1, ReportHeaderHolder.c(context, plan, planRecord));
        e(2, ReportChartHolder.b(context, plan, list).toArray());
        e(3, ReportBestHolder.d(context, plan, planStat).toArray());
        if (plan.acquireType() != 3) {
            e(4, ReportFootHolder.d(context, plan, planStat, planRecord));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aLl_, reason: merged with bridge method [inline-methods] */
    public BaseReportHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new ReportHeaderHolder(this.e.inflate(R.layout.sug_report_rv_item_header, viewGroup, false));
        }
        if (i == 2) {
            return new ReportChartHolder(this.e.inflate(R.layout.sug_item_report_chart, viewGroup, false));
        }
        if (i == 3) {
            return new ReportBestHolder(this.e.inflate(R.layout.sug_item_report_best, viewGroup, false));
        }
        return new ReportFootHolder(this.e.inflate(R.layout.sug_item_report_footer, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(BaseReportHolder baseReportHolder, int i) {
        if (baseReportHolder == null) {
            LogUtil.h("Suggestion_TrainReportAdapter", "onBindViewHolder holder == null");
        } else if (koq.d(this.c, i)) {
            baseReportHolder.bindDataToView(this.c.get(i).second);
        } else {
            LogUtil.h("Suggestion_TrainReportAdapter", "out of position");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.d(this.c, i)) {
            return ((Integer) this.c.get(i).first).intValue();
        }
        LogUtil.h("Suggestion_TrainReportAdapter", "getItemViewType out of position");
        return 0;
    }

    private void e(int i, Object... objArr) {
        for (Object obj : objArr) {
            this.c.add(new Pair<>(Integer.valueOf(i), obj));
        }
    }
}
