package com.huawei.ui.main.stories.fitness.views.fitnessdata;

import android.content.Context;
import android.text.TextUtils;
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
import com.huawei.ui.main.R$string;
import defpackage.pwe;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class StepSourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private OnStepSourceItemClickListener f9984a;
    private List<pwe> b;
    private Context d;

    /* loaded from: classes.dex */
    public interface OnStepSourceItemClickListener {
        void onItemClick(int i);
    }

    public StepSourceAdapter(Context context, List<pwe> list) {
        this.d = context;
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new StepSourceViewHolder(LayoutInflater.from(this.d).inflate(R.layout.step_source_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder == null) {
            LogUtil.h("SCUI_StepSourceAdapter", "onBindViewHolder holder is null");
            return;
        }
        if (!(viewHolder instanceof StepSourceViewHolder)) {
            LogUtil.h("SCUI_StepSourceAdapter", "onBindViewHolder holder is not instanceof StepSourceViewHolder");
            return;
        }
        StepSourceViewHolder stepSourceViewHolder = (StepSourceViewHolder) viewHolder;
        stepSourceViewHolder.f9985a.setBackgroundResource(this.b.get(i).e());
        stepSourceViewHolder.c.setText(this.b.get(i).d());
        String c = this.b.get(i).c();
        if (TextUtils.isEmpty(c) || !this.b.get(i).b()) {
            stepSourceViewHolder.d.setVisibility(8);
        } else {
            stepSourceViewHolder.d.setVisibility(0);
            if (LanguageUtil.bc(this.d)) {
                stepSourceViewHolder.d.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            } else {
                stepSourceViewHolder.d.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
            }
        }
        if (TextUtils.isEmpty(c)) {
            stepSourceViewHolder.i.setText(this.d.getString(R$string.IDS_hwh_privacy_no_details));
        } else {
            stepSourceViewHolder.i.setText(c);
        }
        if (this.b.get(i).h()) {
            stepSourceViewHolder.e.setVisibility(0);
        } else {
            stepSourceViewHolder.e.setVisibility(8);
        }
        if (i == this.b.size() - 1) {
            stepSourceViewHolder.b.setVisibility(8);
        } else {
            stepSourceViewHolder.b.setVisibility(0);
        }
        stepSourceViewHolder.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.fitnessdata.StepSourceAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (StepSourceAdapter.this.f9984a != null) {
                    StepSourceAdapter.this.f9984a.onItemClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<pwe> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class StepSourceViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthImageView f9985a;
        private HealthDivider b;
        private HealthTextView c;
        private HealthImageView d;
        private HealthTextView e;
        private ConstraintLayout f;
        private HealthTextView i;

        public StepSourceViewHolder(View view) {
            super(view);
            this.f = (ConstraintLayout) view.findViewById(R.id.cl_step_source_item_container);
            this.f9985a = (HealthImageView) view.findViewById(R.id.hiv_device_icon);
            this.c = (HealthTextView) view.findViewById(R.id.htv_device_name);
            this.e = (HealthTextView) view.findViewById(R.id.htv_current_device);
            this.i = (HealthTextView) view.findViewById(R.id.htv_step_count);
            this.d = (HealthImageView) view.findViewById(R.id.hiv_guide_arrow);
            this.b = (HealthDivider) view.findViewById(R.id.hd_step_source_divider);
        }
    }

    public void a(OnStepSourceItemClickListener onStepSourceItemClickListener) {
        this.f9984a = onStepSourceItemClickListener;
    }
}
