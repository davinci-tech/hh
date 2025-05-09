package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class frp {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f12623a;
    private LinearLayout b;
    private RecyclerHolder c;
    private LinearLayout d;
    private BaseRecyclerViewAdapter<Motion> e;
    private frq f;
    private Context g;
    private CourseDetailProvider h;
    private List<Motion> i;
    private LinearLayout j;
    private HealthRecycleView k;
    private View l;
    private View m;
    private LinearLayout n;
    private HealthTextView o;
    private RecyclerHolder p;
    private LinearLayout q;

    public frp(Context context, CourseDetailProvider courseDetailProvider) {
        context = context == null ? BaseApplication.e() : context;
        this.g = context;
        this.h = courseDetailProvider;
        this.l = LayoutInflater.from(context).inflate(R.layout.sug_fitness_activity_detail_action, (ViewGroup) null);
        j();
    }

    public void b(frq frqVar) {
        this.f = frqVar;
    }

    private void j() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.l.findViewById(R.id.recv_train_detail);
        this.k = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        BaseActivity.setViewSafeRegion(false, this.k);
        this.n = (LinearLayout) this.l.findViewById(R.id.sug_run_workout_warm_up);
        this.f12623a = (LinearLayout) this.l.findViewById(R.id.sug_run_workout_adjustment);
        this.p = new RecyclerHolder(this.n);
        LinearLayout linearLayout = (LinearLayout) this.l.findViewById(R.id.layout_warm_up);
        this.q = linearLayout;
        BaseActivity.setViewSafeRegion(false, this.n, this.f12623a, linearLayout);
        this.b = (LinearLayout) this.l.findViewById(R.id.layout_action_list);
        this.j = (LinearLayout) this.l.findViewById(R.id.layout_cooldown);
        this.c = new RecyclerHolder(this.f12623a);
        BaseActivity.setViewSafeRegion(false, this.l.findViewById(R.id.sug_run_workout_take_exercise_text), this.l.findViewById(R.id.sug_run_workout_adjustment_text));
        this.k.setLayoutManager(new LinearLayoutManager(this.g));
        this.m = this.l.findViewById(R.id.sug_actions_pay_lock);
        this.o = (HealthTextView) this.l.findViewById(R.id.sug_actions_pay_lock_hint);
        this.d = (LinearLayout) this.l.findViewById(R.id.sug_actions_detail_layout);
    }

    public View aFg_() {
        return this.l;
    }

    public void a() {
        if (this.h.isRunModelCourse()) {
            e();
        } else {
            d();
            i();
        }
        this.k.setAdapter(this.e);
        this.e.notifyDataSetChanged();
    }

    private void i() {
        this.e.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() { // from class: frr
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                frp.this.e(recyclerHolder, i, obj);
            }
        });
    }

    /* synthetic */ void e(RecyclerHolder recyclerHolder, int i, Object obj) {
        BaseRecyclerViewAdapter<Motion> baseRecyclerViewAdapter = this.e;
        if (baseRecyclerViewAdapter == null || baseRecyclerViewAdapter.get(i) == null) {
            BaseRecyclerViewAdapter<Motion> baseRecyclerViewAdapter2 = this.e;
            ReleaseLogUtil.d("Suggestion_CourseActionViewHolder", "action onclick failed with adapter = ", baseRecyclerViewAdapter2, " mAdapter.get(position) = ", baseRecyclerViewAdapter2.get(i));
            return;
        }
        ggr.c(this.e.get(i));
        if (this.e.get(i).getDescription() == null || this.h.isRunModelCourse()) {
            ReleaseLogUtil.d("Suggestion_CourseActionViewHolder", "mAdapter.setOnItemClickListener1: null==mAdapter.get(position).getDescription() = false");
            return;
        }
        frq frqVar = this.f;
        if (frqVar != null) {
            frqVar.a(i, this.i);
        }
    }

    private void e() {
        this.e = new BaseRecyclerViewAdapter<Motion>(new ArrayList(), R.layout.sug_run_rec_train_detail) { // from class: frp.1
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, Motion motion) {
                if (recyclerHolder == null || motion == null) {
                    ReleaseLogUtil.d("Suggestion_CourseActionViewHolder", "holder or itemData is null");
                } else {
                    frp.d(recyclerHolder, motion.acquireName(), ggk.b(frp.this.g, motion.acquireGroups(), motion.acquireMeasurementValue(), motion.acquireMeasurementType()), ffy.b(R.plurals._2130903475_res_0x7f0301b3, motion.acquireGap(), UnitUtil.e(motion.acquireGap(), 1, 0)), !(i == getItemCount() - 1 || getItemCount() <= 1));
                }
            }
        };
    }

    private void d() {
        this.e = new BaseRecyclerViewAdapter<Motion>(new ArrayList(), R.layout.sug_fitness_rec_train_detail) { // from class: frp.3
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, Motion motion) {
                String d;
                if (LanguageUtil.bc(frp.this.g) && !LanguageUtil.bp(frp.this.g)) {
                    d = ffy.d(frp.this.g, R.string._2130848850_res_0x7f022c52, UnitUtil.e(Math.round(motion.acquireDuration() / 1000.0f), 1, 0), UnitUtil.e(motion.acquireGroups(), 1, 0));
                } else {
                    d = ffy.d(frp.this.g, R.string._2130848849_res_0x7f022c51, UnitUtil.e(motion.acquireGroups(), 1, 0), UnitUtil.e(Math.round(motion.acquireDuration() / 1000.0f), 1, 0));
                }
                recyclerHolder.e(R.id.sug_fitness_iv_train_pic, motion.acquirePicUrl(), frp.this.g.getResources().getDimensionPixelSize(R.dimen._2131362361_res_0x7f0a0239)).e(R.id.tv_train_title_adv, motion.acquireName()).e(R.id.tv_train_n_adv, d);
                if (nsn.s()) {
                    recyclerHolder.aCA_(R.id.sug_fitness_rec_gap).setVisibility(8);
                    recyclerHolder.aCA_(R.id.sug_fitness_iv_rest).setVisibility(8);
                    recyclerHolder.aCA_(R.id.three_fold_fonts_view).setVisibility(0);
                    recyclerHolder.e(R.id.sug_fitness_rec_gap_three, ffy.b(R.plurals._2130903475_res_0x7f0301b3, motion.acquireGap(), UnitUtil.e(motion.acquireGap(), 1, 0)));
                } else {
                    recyclerHolder.e(R.id.sug_fitness_rec_gap, ffy.b(R.plurals._2130903475_res_0x7f0301b3, motion.acquireGap(), UnitUtil.e(motion.acquireGap(), 1, 0)));
                }
                if (i == getItemCount() - 1) {
                    recyclerHolder.d(R.id.tv_train_seg_adv, 8);
                }
            }
        };
    }

    public void b(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            ReleaseLogUtil.d("Suggestion_CourseActionViewHolder", "bindRunWorkoutAction with null workout");
            return;
        }
        if (this.h.isFreeCourse()) {
            b();
        }
        if (fitWorkout.isRunModelCourse()) {
            a(ffy.c(fitWorkout.acquireWarmUpRunAction()) ? 0 : 8, ffy.c(fitWorkout.acquireCoolDownRunAction()) ? 0 : 8);
            b(this.p, fitWorkout.acquireWarmUpRunAction());
            b(this.c, fitWorkout.acquireCoolDownRunAction());
        } else {
            LogUtil.a("Suggestion_CourseActionViewHolder", "mIsRunWorkout()==false");
            a(8, 8);
        }
    }

    public void c(List<Motion> list) {
        this.i = list;
        BaseRecyclerViewAdapter<Motion> baseRecyclerViewAdapter = this.e;
        if (baseRecyclerViewAdapter != null) {
            baseRecyclerViewAdapter.refreshDataChange(list);
        }
    }

    public void b(String str) {
        View view = this.m;
        if (view != null) {
            view.setVisibility(0);
        }
        HealthTextView healthTextView = this.o;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        LinearLayout linearLayout = this.d;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    public void b() {
        LinearLayout linearLayout = this.d;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        View view = this.m;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private void b(RecyclerHolder recyclerHolder, WorkoutAction workoutAction) {
        ReleaseLogUtil.e("Suggestion_CourseActionViewHolder", "showAction() enter");
        if (ffy.c(workoutAction)) {
            AtomicAction action = workoutAction.getAction();
            if (action == null) {
                LogUtil.h("Suggestion_CourseActionViewHolder", "bindRunAction action is null.");
                return;
            }
            String name = action.getName();
            int group = workoutAction.getGroup();
            int acquireMeasurementValue = workoutAction.acquireMeasurementValue();
            if (workoutAction.acquireMeasurementType() == 1) {
                acquireMeasurementValue = workoutAction.acquireMeasurementValue() * 1000;
            }
            int acquireMeasurementType = workoutAction.acquireMeasurementType();
            LogUtil.a("Suggestion_CourseActionViewHolder", "groups:", Integer.valueOf(group), "measurementValue:", Integer.valueOf(acquireMeasurementValue), "measurementType", Integer.valueOf(acquireMeasurementType));
            d(recyclerHolder, name, ggk.b(this.l.getContext(), group, acquireMeasurementValue, acquireMeasurementType), ffy.b(R.plurals._2130903475_res_0x7f0301b3, workoutAction.getGap(), UnitUtil.e(workoutAction.getGap(), 1, 0)), false);
        }
    }

    private void a(int i, int i2) {
        this.n.setVisibility(i);
        this.q.setVisibility(i);
        this.j.setVisibility(i2);
        this.f12623a.setVisibility(i2);
        this.b.setVisibility((i2 == 0 || i == 0) ? 0 : 8);
    }

    public static void d(RecyclerHolder recyclerHolder, String str, String str2, String str3, boolean z) {
        if (nsn.s()) {
            recyclerHolder.d(R.id.tv_train_title_adv, 8).d(R.id.tv_train_n_adv, 8).d(R.id.sug_fitness_rec_gap, 8).d(R.id.sug_fitness_iv_rest, 8).d(R.id.sug_run_three, 0);
            recyclerHolder.e(R.id.tv_train_title_adv, str).e(R.id.tv_train_n_adv_three, str2).e(R.id.sug_fitness_rec_gap_three, str3);
        } else {
            recyclerHolder.e(R.id.tv_train_title_adv, str).e(R.id.tv_train_n_adv, str2).e(R.id.sug_fitness_rec_gap, str3);
        }
        if (z) {
            recyclerHolder.d(R.id.tv_train_seg_adv, 0);
        } else {
            recyclerHolder.d(R.id.tv_train_seg_adv, 8);
        }
    }

    public void c(int i) {
        View view = this.l;
        if (view != null) {
            view.setVisibility(i);
        }
    }
}
