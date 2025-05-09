package com.huawei.health.suggestion.ui.run.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.plan.activity.AiPlanActivity;
import com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter;
import com.huawei.health.suggestion.ui.run.listener.RunPlanRefreshCallback;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.asc;
import defpackage.ase;
import defpackage.bzs;
import defpackage.ffm;
import defpackage.ffy;
import defpackage.fib;
import defpackage.fzn;
import defpackage.gge;
import defpackage.ggs;
import defpackage.gib;
import defpackage.gnm;
import defpackage.jed;
import defpackage.koq;
import defpackage.mnu;
import defpackage.mod;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class RunPlanningAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<Plan> f3360a;
    private boolean b;
    private Handler c;
    private Context e;
    private RunPlanRefreshCallback f;
    private boolean j;
    private CustomViewDialog d = null;
    private boolean g = true;
    private PlanWorkout i = null;

    public RunPlanningAdapter(Context context, List<Plan> list) {
        this.f3360a = new ArrayList();
        this.c = null;
        this.e = context;
        this.c = new b(this.e);
        if (koq.b(list)) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "RunPlanningAdapter planList is null");
        } else {
            this.f3360a = list;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aLk_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.e).inflate(R.layout.sug_item_run_planning, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (koq.b(this.f3360a, 0)) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "mCurrentPlansList is null");
        } else {
            this.b = false;
            c(viewHolder, this.f3360a.get(0));
        }
    }

    private void c(ViewHolder viewHolder, Plan plan) {
        if (plan == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "onBindViewHolder plan is invalid.");
            return;
        }
        viewHolder.f3366a.setVisibility(0);
        viewHolder.c.setVisibility(0);
        viewHolder.f.setVisibility(0);
        viewHolder.b.setVisibility(0);
        viewHolder.d.setVisibility(0);
        d(plan);
        a(viewHolder, plan);
        d(plan, viewHolder);
        b(plan, viewHolder);
        if (plan.getPlanCategory() == 0) {
            e(viewHolder, plan);
        } else if (plan.getTransactionStatus() == 3) {
            a(viewHolder);
        } else {
            d(viewHolder, plan);
        }
    }

    private void a(ViewHolder viewHolder, Plan plan) {
        if (plan.acquireType() == 3) {
            e(plan, viewHolder);
            aLf_(viewHolder.e);
        } else if (plan.getPlanCategory() == 0 && plan.acquireType() == 0) {
            b(viewHolder);
            aLg_(viewHolder.e);
        } else {
            e(plan, viewHolder);
            aLf_(viewHolder.e);
        }
    }

    private void aLg_(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    if (RunPlanningAdapter.this.e instanceof Activity) {
                        ((Activity) RunPlanningAdapter.this.e).runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.3.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (RunPlanningAdapter.this.d != null) {
                                    RunPlanningAdapter.this.d.show();
                                }
                            }
                        });
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    } else {
                        LogUtil.b("Suggestion_RunPlanningAdapter", "mContext is not instanceof Activity");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                LogUtil.b("Suggestion_RunPlanningAdapter", " isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void aLf_(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == null) {
                    LogUtil.h("Suggestion_RunPlanningAdapter", "mBackgroundPlanning view is null.");
                    ViewClickInstrumentation.clickOnView(view);
                } else if (!nsn.o()) {
                    JumpUtil.c(RunPlanningAdapter.this.e);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.b("Suggestion_RunPlanningAdapter", " isFastClick");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void e(Plan plan, ViewHolder viewHolder) {
        nrf.cJh_(plan.getPicture(), viewHolder.e, nrf.d);
    }

    private void b(ViewHolder viewHolder) {
        if (!LanguageUtil.bc(this.e)) {
            nrf.cIP_(viewHolder.e, R.drawable._2131430940_res_0x7f0b0e1c, nrf.d, 0, 0);
        } else {
            aLh_(viewHolder.e);
        }
    }

    private void aLh_(ImageView imageView) {
        nrf.cIO_(nrz.cKn_(this.e, R.drawable._2131430940_res_0x7f0b0e1c), imageView, nrf.d);
    }

    private void d(final Plan plan) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.run_plan_tips_layout, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.run_plan_feature1)).setText(this.e.getResources().getString(R.string._2130844818_res_0x7f021c92));
        ((HealthTextView) inflate.findViewById(R.id.run_plan_feature2)).setText(this.e.getResources().getString(R.string._2130844819_res_0x7f021c93));
        ((HealthTextView) inflate.findViewById(R.id.run_plan_join)).setText(this.e.getResources().getString(R.string._2130844968_res_0x7f021d28));
        this.d = new CustomViewDialog.Builder(this.e).a(this.e.getResources().getString(R.string._2130844970_res_0x7f021d2a)).czg_(inflate).czf_(this.e.getResources().getString(R.string._2130844969_res_0x7f021d29), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RunPlanningAdapter.this.a(plan);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czd_(this.e.getResources().getString(R.string._2130844820_res_0x7f021c94), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setFlags(268435456);
                intent.setClassName(RunPlanningAdapter.this.e, "com.huawei.health.suggestion.ui.run.activity.ShowPlanActivity");
                intent.putExtra("planDetail", new Gson().toJson(plan));
                LogUtil.c("Suggestion_RunPlanningAdapter", "initDialog ShowPlanActivity.");
                RunPlanningAdapter.this.aLi_(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aLi_(Intent intent) {
        if (intent == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "startActivity intent is null.");
            return;
        }
        try {
            this.e.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Suggestion_RunPlanningAdapter", " ActivityNotFoundException.");
        }
    }

    public void a(Plan plan) {
        LogUtil.a("Suggestion_RunPlanningAdapter", "finish_plan");
        if (plan == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "finishPlan currentPlan is null");
            return;
        }
        String acquireId = plan.acquireId();
        if (acquireId != null) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("Suggestion_RunPlanningAdapter", "finishPlan, getCurrentPlan : planApi is null.");
                return;
            } else {
                planApi.setPlanType(0);
                planApi.finishPlan(1, acquireId, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.8
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("Suggestion_RunPlanningAdapter", "finish  plan failed  ", str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str) {
                        LogUtil.a("Suggestion_RunPlanningAdapter", "finish  plan onSuccess  ", str);
                        Intent intent = new Intent(RunPlanningAdapter.this.e, (Class<?>) AiPlanActivity.class);
                        intent.putExtra("plantype", 2);
                        gnm.aPB_(RunPlanningAdapter.this.e, intent);
                        RunPlanningAdapter.this.e();
                    }
                });
                return;
            }
        }
        LogUtil.a("Suggestion_RunPlanningAdapter", "finish  plan  id is null ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("enter", "3");
        gge.e("1120019", hashMap);
    }

    private void e(final ViewHolder viewHolder, final Plan plan) {
        List<PlanWorkout> e = e(plan, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (koq.d(e, 0)) {
            this.i = e.get(0);
        } else {
            this.i = null;
        }
        if (this.i == null) {
            viewHolder.g.setText(this.e.getString(R.string._2130848447_res_0x7f022abf));
            viewHolder.h.setText(plan.acquireName());
            viewHolder.f.setVisibility(8);
            viewHolder.b.setVisibility(8);
            viewHolder.d.setVisibility(8);
            return;
        }
        viewHolder.f.setVisibility(0);
        viewHolder.b.setVisibility(0);
        viewHolder.d.setVisibility(0);
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.7
            @Override // java.lang.Runnable
            public void run() {
                RunPlanningAdapter.this.a(plan, viewHolder);
            }
        });
        String popName = this.i.popName();
        if (!TextUtils.isEmpty(popName)) {
            viewHolder.h.setText(popName);
        }
        viewHolder.g.setText(plan.acquireName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Plan plan, final ViewHolder viewHolder) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "setRunWorkout, run: courseApi is null.");
            return;
        }
        final int i = courseApi.hasDoneTodayRunTask(plan) ? R.string._2130848482_res_0x7f022ae2 : R.string._2130848439_res_0x7f022ab7;
        Handler handler = this.c;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.6
                @Override // java.lang.Runnable
                public void run() {
                    viewHolder.b.setText(RunPlanningAdapter.this.e.getResources().getString(i));
                }
            });
        }
        viewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    RunPlanningAdapter.this.b(plan);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.b("Suggestion_RunPlanningAdapter", "mBtnPlanning isFastClick");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Plan plan) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "startRunPlan : planApi is null.");
            return;
        }
        planApi.setPlanType(0);
        if (fib.e().c()) {
            ffm.e(this.i);
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", 1);
            hashMap.put(BleConstants.SPORT_TYPE, 1);
            gge.e(AnalyticsValue.HEALTH_HOME_START_TRAIN_BTN_2010037.value(), hashMap);
        }
    }

    private List<PlanWorkout> e(Plan plan, String str) {
        List<PlanWorkout> acquireWorkouts = plan.acquireWorkouts();
        ArrayList arrayList = new ArrayList();
        if (koq.b(acquireWorkouts)) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "CollectionUtils.isEmpty(planWorkouts)");
            return arrayList;
        }
        for (PlanWorkout planWorkout : acquireWorkouts) {
            if (planWorkout != null && str.equals(planWorkout.popDayInfo().acquireDate())) {
                if (planWorkout.popWorkoutId() != null) {
                    arrayList.add(planWorkout);
                }
                LogUtil.c("Suggestion_RunPlanningAdapter", Integer.valueOf(arrayList.size()), "==today have workouts: ", planWorkout.popName());
            }
        }
        return arrayList;
    }

    private void d(ViewHolder viewHolder, Plan plan) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        int i = calendar.get(7);
        int d2 = gib.d(i);
        int e = ase.e(plan);
        LogUtil.c("Suggestion_RunPlanningAdapter", "PLAN = ", plan.toString());
        List<CourseDataBean> b2 = fzn.b(plan, e, d2);
        mnu d3 = ase.d(plan, d2, e);
        d(d3, b2, viewHolder, plan);
        List<CourseDataBean> a2 = d3.a();
        if (ase.b(plan, e, i) && koq.b(a2)) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "isLastPlanDay true && courseList is empty");
            c(viewHolder, plan, e);
        } else if (koq.b(a2)) {
            e(viewHolder, plan, e, false);
            LogUtil.h("Suggestion_RunPlanningAdapter", "courseDataBeanList is null");
        } else {
            c(viewHolder, plan, a2, e);
        }
    }

    private void e(ViewHolder viewHolder, Plan plan, int i, boolean z) {
        if (i > 1) {
            viewHolder.b.setVisibility(0);
            viewHolder.d.setVisibility(0);
            viewHolder.b.setText(R.string._2130844994_res_0x7f021d42);
            b(viewHolder, plan);
        } else {
            viewHolder.b.setVisibility(4);
            viewHolder.d.setVisibility(4);
        }
        viewHolder.h.setText(plan.acquireName());
        if (z) {
            viewHolder.g.setText(R.string._2130848765_res_0x7f022bfd);
        } else if (this.g) {
            viewHolder.g.setText(R.string._2130848447_res_0x7f022abf);
        }
        if (this.j) {
            viewHolder.b.setVisibility(8);
            viewHolder.d.setVisibility(8);
        }
    }

    private void c(ViewHolder viewHolder, Plan plan, int i) {
        if (viewHolder == null || viewHolder.g == null || viewHolder.b == null || viewHolder.d == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "showRaceView view null");
        } else {
            e(viewHolder, plan, i, true);
        }
    }

    private void c(final ViewHolder viewHolder, final Plan plan, List<CourseDataBean> list, int i) {
        Iterator<CourseDataBean> it = list.iterator();
        String str = "";
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CourseDataBean next = it.next();
            if (next != null) {
                if (!next.d() && TextUtils.isEmpty(str)) {
                    str = next.a();
                }
                if (next.d()) {
                    z2 = true;
                }
                if ("Race".equals(str)) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            c(viewHolder, plan, i);
            return;
        }
        if (z2) {
            viewHolder.b.setText(this.e.getResources().getString(R.string._2130844993_res_0x7f021d41));
        } else {
            viewHolder.b.setText(this.e.getResources().getString(R.string._2130848439_res_0x7f022ab7));
        }
        ((CourseApi) Services.a("CoursePlanService", CourseApi.class)).getCourseById(str, null, null, new UiCallback<Workout>() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.14
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                LogUtil.h("Suggestion_RunPlanningAdapter", "getCourseById", str2, Integer.valueOf(i2));
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Workout workout) {
                LogUtil.a("Suggestion_RunPlanningAdapter", "getCourseById onSuccess");
                if (workout == null) {
                    return;
                }
                RunPlanningAdapter.this.b(workout, viewHolder, plan);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Workout workout, ViewHolder viewHolder, final Plan plan) {
        final FitWorkout a2 = mod.a(workout);
        viewHolder.b.setVisibility(0);
        viewHolder.d.setVisibility(0);
        viewHolder.g.setText(a2.acquireName());
        viewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RunPlanningAdapter.this.b) {
                    RunPlanningAdapter.this.d();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (planApi == null) {
                    LogUtil.h("Suggestion_RunPlanningAdapter", "startRunPlan : planApi is null.");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                planApi.setPlanType(0);
                ggs.d(RunPlanningAdapter.this.e, a2, plan, System.currentTimeMillis());
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", 1);
                hashMap.put(BleConstants.SPORT_TYPE, 1);
                gge.e(AnalyticsValue.HEALTH_HOME_START_TRAIN_BTN_2010037.value(), hashMap);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(ViewHolder viewHolder, final Plan plan) {
        viewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.b("Suggestion_RunPlanningAdapter", "setBtnClickListener is click fast ");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    JumpUtil.a(1, RunPlanningAdapter.this.e, IntPlan.PlanType.AI_RUN_PLAN.getType(), plan.acquireId());
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void a(ViewHolder viewHolder) {
        RunPlanRefreshCallback runPlanRefreshCallback = this.f;
        if (runPlanRefreshCallback != null) {
            runPlanRefreshCallback.setRefreshState();
        }
        viewHolder.f3366a.setVisibility(8);
        viewHolder.c.setVisibility(8);
        viewHolder.f.setVisibility(8);
        viewHolder.g.setVisibility(0);
        viewHolder.g.setText(this.e.getResources().getString(R.string._2130848620_res_0x7f022b6c));
        viewHolder.h.setVisibility(0);
        viewHolder.h.setText(this.e.getResources().getString(R.string._2130848621_res_0x7f022b6d));
        viewHolder.b.setVisibility(0);
        viewHolder.d.setVisibility(0);
        viewHolder.b.setPaddingRelative((int) this.e.getResources().getDimension(R.dimen._2131362640_res_0x7f0a0350), 0, (int) this.e.getResources().getDimension(R.dimen._2131362640_res_0x7f0a0350), 0);
        viewHolder.b.setText(this.e.getResources().getString(R.string._2130844867_res_0x7f021cc3));
        this.b = true;
        viewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: gdl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunPlanningAdapter.this.aLj_(view);
            }
        });
    }

    public /* synthetic */ void aLj_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "mBtnPlanning view is null.");
            ViewClickInstrumentation.clickOnView(view);
        } else if (nsn.o()) {
            LogUtil.b("Suggestion_RunPlanningAdapter", " isFastClick");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            d();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/PrivilegeDetail?functionId=1");
        bzs.e().loadH5ProApp(this.e, "com.huawei.health.h5.vip", builder);
    }

    private void b(final Plan plan, final ViewHolder viewHolder) {
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "getCurrentPlanRecord planApi is null");
        } else {
            final UiCallback<PlanRecord> uiCallback = new UiCallback<PlanRecord>() { // from class: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_RunPlanningAdapter", "getCurrentPlanRecord fail errCode:", Integer.valueOf(i), " errorInfo:", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(PlanRecord planRecord) {
                    if (planRecord == null) {
                        LogUtil.h("Suggestion_RunPlanningAdapter", "getCurrentPlanRecord success but data is null");
                        return;
                    }
                    LogUtil.a("Suggestion_RunPlanningAdapter", "finishRate is ", Float.valueOf(planRecord.acquireFinishRate()));
                    viewHolder.f.setProgress(Math.round(planRecord.acquireFinishRate()));
                    viewHolder.c.setText(ffy.d(RunPlanningAdapter.this.e, R.string._2130844992_res_0x7f021d40, jed.b(planRecord.acquireFinishRate(), 2, 1)));
                }
            };
            asc.e().b(new Runnable() { // from class: gdj
                @Override // java.lang.Runnable
                public final void run() {
                    RunPlanningAdapter.a(PlanApi.this, plan, uiCallback);
                }
            });
        }
    }

    public static /* synthetic */ void a(PlanApi planApi, Plan plan, UiCallback uiCallback) {
        planApi.setPlanType(0);
        if (plan != null) {
            planApi.getPlanProgress(plan.acquireId(), (UiCallback<PlanRecord>) uiCallback);
        } else {
            LogUtil.b("Suggestion_RunPlanningAdapter", "getCurrentPlanRecord have no current plan");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(com.huawei.basefitnessadvice.model.Plan r11, com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.ViewHolder r12) {
        /*
            r10 = this;
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String r1 = "yyyy-MM-dd"
            r0.<init>(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 86400000(0x5265c00, double:4.2687272E-316)
            long r1 = r1 / r3
            java.lang.String r5 = r11.acquireStartDate()
            java.lang.String r6 = r11.getEndDate()
            java.lang.String r7 = "Suggestion_RunPlanningAdapter"
            r8 = 1
            r9 = 0
            if (r5 == 0) goto L22
            if (r6 != 0) goto L20
            goto L22
        L20:
            r6 = r8
            goto L2c
        L22:
            java.lang.String r6 = "startDate or endDate == null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r7, r6)
            r6 = r9
        L2c:
            if (r6 == 0) goto L46
            java.util.Date r0 = r0.parse(r5)     // Catch: java.text.ParseException -> L38
            long r5 = r0.getTime()     // Catch: java.text.ParseException -> L38
            long r5 = r5 / r3
            goto L48
        L38:
            r0 = move-exception
            java.lang.String r3 = "exception = "
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0}
            com.huawei.hwlogsmodel.LogUtil.b(r7, r0)
        L46:
            r5 = 0
        L48:
            int r0 = r11.getDays()
            long r1 = r1 - r5
            int r1 = (int) r1
            if (r1 <= r0) goto L71
            com.huawei.ui.commonui.button.HealthButton r0 = r12.b
            r1 = 8
            r0.setVisibility(r1)
            com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout r0 = r12.d
            r0.setVisibility(r1)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r12.g
            r1 = 2130846125(0x7f0221ad, float:1.729745E38)
            r0.setText(r1)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r12.h
            java.lang.String r11 = r11.acquireName()
            r0.setText(r11)
            r10.d(r12, r8, r9)
            goto Lb3
        L71:
            if (r1 > 0) goto L9c
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r12.h
            java.lang.String r1 = r11.acquireName()
            r0.setText(r1)
            android.content.Context r0 = r10.e
            java.lang.String r11 = defpackage.ase.d(r0, r11)
            android.content.Context r0 = r10.e
            android.content.res.Resources r0 = r0.getResources()
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            r1 = 2130846123(0x7f0221ab, float:1.7297445E38)
            java.lang.String r11 = r0.getString(r1, r11)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r12.g
            r0.setText(r11)
            r10.d(r12, r8, r9)
            goto Lb3
        L9c:
            com.huawei.ui.commonui.healthtextview.HealthTextView r2 = r12.h
            java.lang.String r11 = r11.acquireName()
            r2.setText(r11)
            android.content.Context r11 = r10.e
            java.lang.String r11 = defpackage.ffy.c(r11, r1, r0, r9)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r12.f3366a
            r0.setText(r11)
            r10.d(r12, r9, r8)
        Lb3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter.d(com.huawei.basefitnessadvice.model.Plan, com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter$ViewHolder):void");
    }

    private void d(ViewHolder viewHolder, boolean z, boolean z2) {
        if (z) {
            viewHolder.i.setVisibility(0);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder.i.getLayoutParams();
            layoutParams.weight = 0.0f;
            layoutParams.topMargin = this.e.getResources().getDimensionPixelSize(R.dimen._2131363015_res_0x7f0a04c7);
            viewHolder.i.setLayoutParams(layoutParams);
        } else {
            viewHolder.i.setVisibility(8);
        }
        if (z2) {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.topMargin = (int) this.e.getResources().getDimension(R.dimen._2131362566_res_0x7f0a0306);
            viewHolder.j.setLayoutParams(layoutParams2);
            viewHolder.f3366a.setVisibility(0);
            viewHolder.c.setVisibility(0);
            viewHolder.f.setVisibility(0);
            String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1014);
            if (accountInfo == null || !accountInfo.equals("RU")) {
                return;
            }
            viewHolder.j.setOrientation(1);
            return;
        }
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = (int) this.e.getResources().getDimension(R.dimen._2131365055_res_0x7f0a0cbf);
        viewHolder.j.setLayoutParams(layoutParams3);
        viewHolder.f3366a.setVisibility(8);
        viewHolder.c.setVisibility(8);
        viewHolder.f.setVisibility(8);
        this.j = true;
        this.g = false;
    }

    private void d(mnu mnuVar, List<CourseDataBean> list, ViewHolder viewHolder, Plan plan) {
        if (mnuVar.e()) {
            viewHolder.g.setText(this.e.getResources().getText(R.string._2130844995_res_0x7f021d43));
            viewHolder.h.setText(plan.acquireName());
            viewHolder.b.setVisibility(8);
            viewHolder.d.setVisibility(8);
            return;
        }
        if (koq.b(list)) {
            LogUtil.b("Suggestion_RunPlanningAdapter", "courseDataBeanList is empty");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        for (CourseDataBean courseDataBean : list) {
            if (courseDataBean == null) {
                LogUtil.b("Suggestion_RunPlanningAdapter", "planCourseData is null");
                return;
            } else if (!courseDataBean.d() && courseDataBean.c() == 1) {
                planApi.getWorkoutById(courseDataBean.a(), CommonUtil.e(BaseApplication.e()), Locale.getDefault().getCountry(), new d(viewHolder));
                return;
            }
        }
        for (CourseDataBean courseDataBean2 : list) {
            if (courseDataBean2 == null) {
                LogUtil.b("Suggestion_RunPlanningAdapter", "planCourseData is null");
                return;
            } else if (!courseDataBean2.d() && courseDataBean2.c() == 2) {
                planApi.getWorkoutById(courseDataBean2.a(), CommonUtil.e(BaseApplication.e()), Locale.getDefault().getCountry(), new d(viewHolder));
                viewHolder.b.setVisibility(0);
                viewHolder.d.setVisibility(0);
                viewHolder.b.setText(R.string._2130844993_res_0x7f021d41);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f3360a.size();
    }

    public void c(List<Plan> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_RunPlanningAdapter", "clearAndAddAll planList is null");
            return;
        }
        this.f3360a.clear();
        this.f3360a.addAll(list);
        notifyDataSetChanged();
        LogUtil.a("Suggestion_RunPlanningAdapter", "clearAndAddAll()");
    }

    public void b(RunPlanRefreshCallback runPlanRefreshCallback) {
        LogUtil.a("Suggestion_RunPlanningAdapter", "RunPlan setDataRefreshCallback");
        this.f = runPlanRefreshCallback;
    }

    class d extends UiCallback<FitWorkout> {
        WeakReference<ViewHolder> d;

        d(ViewHolder viewHolder) {
            this.d = new WeakReference<>(viewHolder);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_RunPlanningAdapter", "FitWorkoutUiCallBack errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(FitWorkout fitWorkout) {
            if (fitWorkout == null) {
                LogUtil.b("Suggestion_RunPlanningAdapter", "FitWorkoutUiCallBack onSuccess data is null.");
            } else {
                LogUtil.a("Suggestion_RunPlanningAdapter", "data = ", fitWorkout.toString());
                RunPlanningAdapter.this.d(this.d, fitWorkout);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(WeakReference<ViewHolder> weakReference, FitWorkout fitWorkout) {
        ViewHolder viewHolder = weakReference.get();
        if (viewHolder == null) {
            LogUtil.b("Suggestion_RunPlanningAdapter", "ViewHolder = null");
            return;
        }
        if (StringUtils.g(fitWorkout.acquireName())) {
            viewHolder.g.setVisibility(8);
        } else if ("Race".equals(fitWorkout.acquireId())) {
            viewHolder.g.setText(R.string._2130848765_res_0x7f022bfd);
        } else {
            viewHolder.g.setText(fitWorkout.acquireName());
        }
    }

    public static class b extends BaseHandler {
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(Object obj, Message message) {
        }

        public b(Object obj) {
            super(obj);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f3366a;
        HealthButton b;
        HealthTextView c;
        HealthColumnRelativeLayout d;
        ImageView e;
        HealthProgressBar f;
        HealthTextView g;
        HealthTextView h;
        Space i;
        HealthColumnLinearLayout j;

        ViewHolder(View view) {
            super(view);
            this.e = (ImageView) view.findViewById(R.id.run_planning_bg);
            this.g = (HealthTextView) view.findViewById(R.id.run_planning_name);
            this.h = (HealthTextView) view.findViewById(R.id.description_run_planning);
            this.f3366a = (HealthTextView) view.findViewById(R.id.run_planning_completion);
            this.c = (HealthTextView) view.findViewById(R.id.run_planning_completion_rate);
            this.b = (HealthButton) view.findViewById(R.id.btn_start_training);
            this.f = (HealthProgressBar) view.findViewById(R.id.progressbar_run_planning);
            this.i = (Space) view.findViewById(R.id.space_top);
            this.j = (HealthColumnLinearLayout) view.findViewById(R.id.desc_layout);
            this.d = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_btn);
        }
    }
}
