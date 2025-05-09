package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.mmp;
import defpackage.mod;
import defpackage.nrf;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class WarmUpAndStretchViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3221a;
    private Bundle b;
    private CourseApi c;
    private WarmUpAndStretchViewHolder d;
    private Context e;
    private String f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthButton j;

    private WarmUpAndStretchViewHolder(View view) {
        super(view);
        this.f3221a = (ImageView) view.findViewById(R.id.sug_img_item_pic);
        this.g = (HealthTextView) view.findViewById(R.id.tv_fe_name);
        this.h = (HealthTextView) view.findViewById(R.id.tv_plan_peoples_num);
        this.j = (HealthButton) view.findViewById(R.id.start_training_button);
    }

    public WarmUpAndStretchViewHolder(View view, Bundle bundle) {
        this(view);
        this.b = bundle;
        this.d = this;
        LogUtil.a("Suggestion_WarmUpAndStretchViewHolder", "mBundle = ", bundle);
        this.c = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
    }

    public void e(final FitWorkout fitWorkout, String str, Context context) {
        if (fitWorkout == null) {
            return;
        }
        this.e = context;
        this.f = str;
        this.h.setText(ffy.awT_(BaseApplication.getContext(), "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)), R.style.sug_reco_train_num, R.style.sug_reco_train_desc).toString());
        this.g.setText(fitWorkout.acquireName());
        nrf.cIS_(this.f3221a, fitWorkout.acquirePicture(), nrf.d, 0, R.drawable._2131431605_res_0x7f0b10b5);
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.WarmUpAndStretchViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WarmUpAndStretchViewHolder.this.c == null) {
                    LogUtil.h("Suggestion_WarmUpAndStretchViewHolder", "setDataAndRefresh, onClick : mCourseApi is null.");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    if (fitWorkout.acquireWorkoutActions() != null) {
                        WarmUpAndStretchViewHolder.this.e(fitWorkout);
                    } else {
                        WarmUpAndStretchViewHolder.this.c.getCourseById(fitWorkout.acquireId(), fitWorkout.accquireVersion(), null, new e(WarmUpAndStretchViewHolder.this.d));
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.WarmUpAndStretchViewHolder.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WarmUpAndStretchViewHolder.this.a(fitWorkout, false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(FitWorkout fitWorkout, boolean z) {
        boolean equals = "R002".equals(fitWorkout.acquireId());
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d(this.f);
        mmpVar.g(z);
        mmpVar.j(!equals);
        mod.b(this.e, fitWorkout, mmpVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(FitWorkout fitWorkout) {
        a(fitWorkout, !a(fitWorkout));
    }

    private boolean a(FitWorkout fitWorkout) {
        return fitWorkout != null && this.c.getCourseMediaFilesLength(fitWorkout.acquireId(), fitWorkout.accquireVersion()) == 0;
    }

    static class e extends UiCallback<Workout> {
        private WeakReference<WarmUpAndStretchViewHolder> c;

        e(WarmUpAndStretchViewHolder warmUpAndStretchViewHolder) {
            this.c = null;
            this.c = new WeakReference<>(warmUpAndStretchViewHolder);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_WarmUpAndStretchViewHolder", "errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            WeakReference<WarmUpAndStretchViewHolder> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.h("Suggestion_WarmUpAndStretchViewHolder", "mWeakPreference == null");
                return;
            }
            if (workout == null) {
                LogUtil.h("Suggestion_WarmUpAndStretchViewHolder", "GetWorkoutCallback workout is null");
                return;
            }
            WarmUpAndStretchViewHolder warmUpAndStretchViewHolder = weakReference.get();
            if (warmUpAndStretchViewHolder != null) {
                warmUpAndStretchViewHolder.e(mod.a(workout));
            } else {
                LogUtil.h("Suggestion_WarmUpAndStretchViewHolder", "holder is null");
            }
        }
    }
}
