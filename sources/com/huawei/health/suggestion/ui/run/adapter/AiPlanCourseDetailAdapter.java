package com.huawei.health.suggestion.ui.run.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.gds;
import defpackage.ggs;
import defpackage.gic;
import defpackage.koq;
import defpackage.mnv;
import java.util.List;

/* loaded from: classes8.dex */
public class AiPlanCourseDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private long f3346a;
    private List<mnv> b;
    private Plan c;
    private boolean d;
    private Context e;
    private int g;

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f3348a;
        HealthTextView b;
        ImageView c;
        ImageView d;
        View e;
        HealthButton j;

        d(View view) {
            super(view);
            this.c = (ImageView) view.findViewById(R.id.plan_course_item_background);
            this.f3348a = (HealthTextView) view.findViewById(R.id.course_name);
            this.b = (HealthTextView) view.findViewById(R.id.course_duration);
            this.j = (HealthButton) view.findViewById(R.id.start_training_button);
            this.e = view.findViewById(R.id.course_item_interval);
            this.d = (ImageView) view.findViewById(R.id.course_lock);
        }
    }

    static class a extends RecyclerView.ViewHolder {
        a(View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("Suggestion_AiPlanCourseDetailAdapter", "onCreateViewHolder viewType:", Integer.valueOf(i));
        if (i == 2) {
            return new d(LayoutInflater.from(this.e).inflate(R.layout.ai_plan_course_card_view, viewGroup, false));
        }
        return new a(new View(this.e));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.c(this.b)) {
            LogUtil.a("Suggestion_AiPlanCourseDetailAdapter", "getItemCount not empty:", Integer.valueOf(this.b.size()));
            return this.b.size();
        }
        LogUtil.a("Suggestion_AiPlanCourseDetailAdapter", "getItemCount empty:", 0);
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("Suggestion_AiPlanCourseDetailAdapter", "onBindViewHolder position:", Integer.valueOf(i));
        if (koq.b(this.b) || koq.b(this.b, i) || this.b.get(i) == null) {
            LogUtil.b("Suggestion_AiPlanCourseDetailAdapter", "onBindViewHolder mCourseDataModelList empty");
            return;
        }
        mnv mnvVar = this.b.get(i);
        boolean c = mnvVar.c();
        if (viewHolder instanceof d) {
            d dVar = (d) viewHolder;
            int i2 = this.g;
            if (i2 == 0) {
                dVar.d.setVisibility(8);
                dVar.j.setVisibility(0);
                dVar.j.setBackground(this.e.getResources().getDrawable(R.drawable._2131431685_res_0x7f0b1105));
                if (c) {
                    dVar.j.setText(R.string._2130844734_res_0x7f021c3e);
                } else {
                    dVar.j.setText(R.string._2130846125_res_0x7f0221ad);
                }
            } else if (i2 == 1) {
                dVar.d.setVisibility(8);
                dVar.j.setVisibility(0);
                if (c) {
                    dVar.j.setText(R.string._2130844734_res_0x7f021c3e);
                    dVar.j.setBackground(this.e.getResources().getDrawable(R.drawable._2131431685_res_0x7f0b1105));
                } else {
                    dVar.j.setText(R.string._2130841873_res_0x7f021111);
                    dVar.j.setBackground(this.e.getResources().getDrawable(R.drawable._2131431684_res_0x7f0b1104));
                }
            } else {
                dVar.d.setVisibility(0);
                dVar.j.setVisibility(8);
            }
            c(dVar, mnvVar, i);
            ViewGroup.LayoutParams layoutParams = dVar.e.getLayoutParams();
            if (i == this.b.size() - 1 && !this.d) {
                dVar.e.setVisibility(8);
            } else {
                dVar.e.setVisibility(0);
            }
            dVar.e.setLayoutParams(layoutParams);
        }
    }

    private void c(d dVar, mnv mnvVar, final int i) {
        final FitWorkout b = mnvVar.b();
        if (b != null) {
            String acquireName = b.acquireName();
            long acquireDuration = b.acquireDuration();
            if (this.g == 3) {
                dVar.f3348a.setTextColor(this.e.getResources().getColor(R$color.textColorTertiary));
                dVar.b.setTextColor(this.e.getResources().getColor(R$color.textColorTertiary));
                dVar.j.setOnClickListener(null);
            } else {
                dVar.f3348a.setTextColor(this.e.getResources().getColor(R$color.textColorPrimary));
                dVar.b.setTextColor(this.e.getResources().getColor(R$color.textColorPrimary));
                dVar.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.AiPlanCourseDetailAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (gds.c(b)) {
                            ggs.e(AiPlanCourseDetailAdapter.this.e, b, AiPlanCourseDetailAdapter.this.c, AiPlanCourseDetailAdapter.this.f3346a, i + 1);
                        } else {
                            Context context = AiPlanCourseDetailAdapter.this.e;
                            FitWorkout fitWorkout = b;
                            Plan plan = AiPlanCourseDetailAdapter.this.c;
                            int i2 = i;
                            ggs.c(context, fitWorkout, plan, i2 + 1, AiPlanCourseDetailAdapter.this.f3346a);
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            dVar.f3348a.setText(acquireName);
            dVar.b.setText(gic.d(arx.b(), R.string._2130837534_res_0x7f02001e, gic.e(acquireDuration)));
            dVar.c.setBackgroundResource(R.drawable.ic_pic_course_fitness_background);
            this.f3346a = this.b.get(0).e();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return koq.c(this.b) ? 2 : 1;
    }
}
