package com.huawei.ui.main.stories.fitness.views.fitnessdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.pwc;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class DayStepSourceItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9982a;
    private OnDayStepSourceItemClickListener c;
    private List<pwc> e;

    public interface OnDayStepSourceItemClickListener {
        void onItemClick(int i);
    }

    public DayStepSourceItemAdapter(Context context, List<pwc> list) {
        this.f9982a = context;
        this.e = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new StepSourceViewHolder(LayoutInflater.from(this.f9982a).inflate(R.layout.day_step_source_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder == null) {
            LogUtil.h("SCUI_DayStepSourceItemAdapter", "onBindViewHolder holder is null");
            return;
        }
        if (viewHolder instanceof StepSourceViewHolder) {
            StepSourceViewHolder stepSourceViewHolder = (StepSourceViewHolder) viewHolder;
            stepSourceViewHolder.b.setText(this.e.get(i).e());
            stepSourceViewHolder.c.setText(this.e.get(i).b());
            if (i == this.e.size() - 1) {
                stepSourceViewHolder.f9983a.setVisibility(8);
            } else {
                stepSourceViewHolder.f9983a.setVisibility(0);
            }
            if (LanguageUtil.bc(this.f9982a)) {
                stepSourceViewHolder.d.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            } else {
                stepSourceViewHolder.d.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
            }
            stepSourceViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.fitnessdata.DayStepSourceItemAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DayStepSourceItemAdapter.this.c != null) {
                        DayStepSourceItemAdapter.this.c.onItemClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        LogUtil.h("SCUI_DayStepSourceItemAdapter", "onBindViewHolder holder is not instanceof StepSourceViewHolder");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<pwc> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class StepSourceViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f9983a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthImageView d;
        private ConstraintLayout e;

        public StepSourceViewHolder(View view) {
            super(view);
            this.e = (ConstraintLayout) view.findViewById(R.id.cl_step_source_item_container);
            this.b = (HealthTextView) view.findViewById(R.id.htv_step_count);
            this.c = (HealthTextView) view.findViewById(R.id.htv_time_range);
            this.d = (HealthImageView) view.findViewById(R.id.hiv_guide_arrow);
            this.f9983a = (HealthDivider) view.findViewById(R.id.hd_step_source_divider);
        }
    }

    public void e(OnDayStepSourceItemClickListener onDayStepSourceItemClickListener) {
        this.c = onDayStepSourceItemClickListener;
    }
}
