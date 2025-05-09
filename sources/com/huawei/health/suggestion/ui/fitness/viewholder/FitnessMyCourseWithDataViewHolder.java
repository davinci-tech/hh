package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessMyCourseWithDataViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.ffy;
import defpackage.gge;
import defpackage.ggm;
import defpackage.mod;
import defpackage.moe;
import defpackage.nrf;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class FitnessMyCourseWithDataViewHolder extends AbsFitnessViewHolder<FitWorkout> {

    /* renamed from: a, reason: collision with root package name */
    private final HealthTextView f3212a;
    private final HealthTextView b;
    private final HealthTextView c;
    private final HealthDivider d;
    private final HealthTextView e;
    private String f;
    private String g;
    private final ImageView h;
    private String i;

    public FitnessMyCourseWithDataViewHolder(View view) {
        super(view);
        this.h = (ImageView) view.findViewById(R.id.recycle_item_picture);
        this.b = (HealthTextView) view.findViewById(R.id.course_title);
        this.f3212a = (HealthTextView) view.findViewById(R.id.fitness_course_trained);
        this.c = (HealthTextView) view.findViewById(R.id.fitness_course_difficulty);
        this.e = (HealthTextView) view.findViewById(R.id.fitness_course_duration);
        this.d = (HealthDivider) view.findViewById(R.id.fitness_course_divider);
        this.f = view.getContext().getString(R.string._2130846126_res_0x7f0221ae);
        this.g = view.getContext().getString(R.string._2130846127_res_0x7f0221af);
        this.i = view.getContext().getString(R.string._2130846128_res_0x7f0221b0);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void init(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_FitnessMyCourseWithDataViewHolder", "FitnessMyCourseWithDataViewHolder init fitWorkout null");
        } else {
            h(fitWorkout);
            d(fitWorkout);
        }
    }

    private void h(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_FitnessMyCourseWithDataViewHolder", "setDataAndRefresh fitWorkout null");
            return;
        }
        LogUtil.a("Suggestion_FitnessMyCourseWithDataViewHolder", "setDataAndRefresh success");
        e(fitWorkout);
        this.b.setText(TextUtils.isEmpty(fitWorkout.acquireName()) ? "" : fitWorkout.acquireName());
        this.c.setText(ggm.d(fitWorkout.acquireDifficulty()));
        this.e.setText(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
        c(fitWorkout);
    }

    private void d(final FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_FitnessMyCourseWithDataViewHolder", "setClickListener fitWorkout null");
        } else {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: fsa
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessMyCourseWithDataViewHolder.this.aFA_(fitWorkout, view);
                }
            });
        }
    }

    public /* synthetic */ void aFA_(FitWorkout fitWorkout, View view) {
        if (!(this.itemView.getParent() instanceof RecyclerView)) {
            ViewClickInstrumentation.clickOnView(view);
        } else if (isFastClick()) {
            LogUtil.h("Suggestion_FitnessMyCourseWithDataViewHolder", "FitnessMyCourseWithDataViewHolder is fast click");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            g(fitWorkout);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void g(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_FitnessMyCourseWithDataViewHolder", "startActivityForTrainDetail fitWorkout null");
        } else {
            a(fitWorkout);
            mod.d(this.itemView.getContext(), fitWorkout);
        }
    }

    private void c(FitWorkout fitWorkout) {
        int intervals = fitWorkout.getIntervals();
        if (intervals == -4) {
            this.f3212a.setVisibility(4);
            return;
        }
        if (intervals == -3) {
            this.f3212a.setText(this.g);
            return;
        }
        if (intervals == -2) {
            this.f3212a.setText(this.i);
        } else {
            if (intervals == 0) {
                this.f3212a.setText(this.f);
                return;
            }
            int intervals2 = fitWorkout.getIntervals();
            this.f3212a.setText(this.itemView.getContext().getResources().getQuantityString(R.plurals._2130903356_res_0x7f03013c, intervals2, Integer.valueOf(intervals2)));
        }
    }

    private void e(FitWorkout fitWorkout) {
        if (!TextUtils.isEmpty(fitWorkout.getTopicPreviewPicUrl())) {
            nrf.cIS_(this.h, fitWorkout.getTopicPreviewPicUrl(), nrf.e, 0, R.drawable._2131431605_res_0x7f0b10b5);
        } else {
            nrf.cIM_(R.drawable._2131431605_res_0x7f0b10b5, this.h, nrf.e);
        }
    }

    private void a(FitWorkout fitWorkout) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("entrance", this.itemView.getContext().getString(R.string._2130848532_res_0x7f022b14));
        hashMap.put("position", 0);
        hashMap.put("type", 10001);
        hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, TextUtils.isEmpty(fitWorkout.acquireId()) ? "" : fitWorkout.acquireId());
        gge.e("1130015", hashMap);
    }

    public HealthDivider d() {
        return this.d;
    }
}
