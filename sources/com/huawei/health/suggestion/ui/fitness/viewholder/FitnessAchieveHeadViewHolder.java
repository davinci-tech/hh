package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.model.fitness.FitnessAchieveInfoUseCase;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionLibraryActivity;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessAchieveHeadViewHolder;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gge;
import defpackage.nrf;
import health.compact.a.UnitUtil;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class FitnessAchieveHeadViewHolder extends AbsFitnessViewHolder<FitnessAchieveInfoUseCase> implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final HealthButton f3204a;
    private final ConstraintLayout b;
    private final HealthTextView c;
    private final HealthTextView d;
    private final ImageView e;
    private final HealthTextView f;
    private final HealthTextView g;
    private final HealthButton h;
    private final HealthTextView i;

    public static /* synthetic */ void e(int i, Object obj) {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void init(FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        c();
        a();
        a(fitnessAchieveInfoUseCase);
        d();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_FitnessAchieveHeadViewHolder", "view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (isFastClick()) {
            LogUtil.h("Suggestion_FitnessAchieveHeadViewHolder", "FitnessAchieveHeadViewHolder is fast click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!(this.itemView.getParent() instanceof RecyclerView)) {
            LogUtil.h("Suggestion_FitnessAchieveHeadViewHolder", "onClick not on RecyclerView");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.tv_fitness_course_lib) {
            f();
        } else if (id == R.id.cl_fitness_enter_area) {
            if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: fry
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        FitnessAchieveHeadViewHolder.e(i, obj);
                    }
                }, AnalyticsValue.HEALTH_HOME_GPS_HISTORY_2010015.value());
            } else {
                e();
            }
        } else if (id == R.id.tv_fitness_action_lib) {
            b();
        } else {
            LogUtil.h("Suggestion_FitnessAchieveHeadViewHolder", "onClick nothing happen");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        e(fitnessAchieveInfoUseCase);
        d(fitnessAchieveInfoUseCase);
    }

    private void d(FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        if (fitnessAchieveInfoUseCase == null) {
            e(0L, 0, 0, 0);
            LogUtil.h("Suggestion_FitnessAchieveHeadViewHolder", "setDataAndRefresh fitnessAchieveInfoUseCase null");
        } else {
            e(fitnessAchieveInfoUseCase.getSumExerciseTime(), fitnessAchieveInfoUseCase.getTodayTime(), fitnessAchieveInfoUseCase.getSumDays(), fitnessAchieveInfoUseCase.getExerciseCount());
        }
    }

    private void d() {
        this.h.setOnClickListener(this);
        this.f3204a.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    private void a() {
        Typeface createFromAsset = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/hw-digit-bold.otf");
        this.g.setTypeface(Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
        this.i.setTypeface(createFromAsset);
        this.c.setTypeface(createFromAsset);
        this.f.setTypeface(createFromAsset);
    }

    private void e(FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        if (fitnessAchieveInfoUseCase == null) {
            a(0);
            LogUtil.h("Suggestion_FitnessAchieveHeadViewHolder", "setDataAndRefresh fitnessAchieveInfoUseCase null");
        } else {
            a(fitnessAchieveInfoUseCase.getSumDays());
        }
    }

    private void b() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_LIBRARY_1130027.value(), hashMap);
        Intent intent = new Intent(this.itemView.getContext(), (Class<?>) FitnessActionLibraryActivity.class);
        intent.setFlags(268435456);
        this.itemView.getContext().startActivity(intent);
    }

    private void e() {
        FitnessSessionManager.SessionActivityAction c = FitnessSessionManager.e().c();
        if (c == null) {
            LogUtil.h("Suggestion_FitnessAchieveHeadViewHolder", "FitnessSessionManager getSessionForActivityAction null");
        } else {
            c.startSportHistoryActivity(this.itemView.getContext());
        }
    }

    private void f() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("entrance", this.itemView.getContext().getString(R.string._2130845613_res_0x7f021fad));
        hashMap.put("position", 3);
        gge.e("1130015", hashMap);
        Intent intent = new Intent(this.itemView.getContext(), (Class<?>) SportAllCourseActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
        this.itemView.getContext().startActivity(intent);
    }

    private void c() {
        nrf.cIP_(this.e, isTahiti(this.itemView.getContext()) ? R.drawable.pic_fitness_tahiti : R.drawable.pic_fitness, nrf.d, 0, 0);
    }

    private void e(long j, int i, int i2, int i3) {
        this.g.setText(UnitUtil.e(j, 1, 0));
        this.i.setText(UnitUtil.e(i, 1, 0));
        this.c.setText(UnitUtil.e(i2, 1, 0));
        this.f.setText(UnitUtil.e(i3, 1, 0));
    }

    private void a(int i) {
        this.d.setText(this.itemView.getResources().getQuantityString(R.plurals._2130903243_res_0x7f0300cb, i));
    }
}
