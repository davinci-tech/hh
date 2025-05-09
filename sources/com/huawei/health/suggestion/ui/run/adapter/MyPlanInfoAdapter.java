package com.huawei.health.suggestion.ui.run.adapter;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.device.model.OperatorStatus;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.asc;
import defpackage.ffm;
import defpackage.ffy;
import defpackage.fib;
import defpackage.fys;
import defpackage.fyw;
import defpackage.gdq;
import defpackage.gdr;
import defpackage.gds;
import defpackage.gge;
import defpackage.ggx;
import defpackage.gib;
import defpackage.jed;
import defpackage.koq;
import defpackage.mod;
import defpackage.mwu;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.qtm;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class MyPlanInfoAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private Handler f3351a;
    private List<Plan> b;
    private final List<IntPlan> c;
    private Plan d;
    private Context e;
    private PlanWorkout h;
    private boolean j;

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f3357a;
        LinearLayout b;
        ImageView c;
        LinearLayout d;
        HealthCardView e;
        HealthTextView f;
        HealthTextView g;
        HealthTextView h;
        HealthTextView i;
        HealthButton j;
        HealthProgressBar k;
        HealthTextView l;
        HealthTextView m;
        View n;
        HealthTextView o;

        c(View view) {
            super(view);
            this.f3357a = (ImageView) view.findViewById(R.id.sug_mylans_item_bg);
            this.i = (HealthTextView) view.findViewById(R.id.sug_mylans_item_name_tv);
            this.h = (HealthTextView) view.findViewById(R.id.sug_mylans_item_complete_tv);
            this.g = (HealthTextView) view.findViewById(R.id.sug_mylans_item_finishrate_tv);
            this.j = (HealthButton) view.findViewById(R.id.sug_mylans_item_button);
            this.o = (HealthTextView) view.findViewById(R.id.sug_mylans_item_describe_tv);
            this.e = (HealthCardView) view.findViewById(R.id.sug_mylans_item_ryt);
            this.k = (HealthProgressBar) view.findViewById(R.id.sug_mylans_item_horizontal);
            this.n = view.findViewById(R.id.sug_my_plans_view);
            this.c = (ImageView) view.findViewById(R.id.corner_img);
            this.l = (HealthTextView) view.findViewById(R.id.sug_mylans_item_summary_tv);
            this.m = (HealthTextView) view.findViewById(R.id.fit_plan_name);
            this.f = (HealthTextView) view.findViewById(R.id.fit_plan_description);
            this.b = (LinearLayout) view.findViewById(R.id.on_going_view);
            this.d = (LinearLayout) view.findViewById(R.id.helper_view);
        }
    }

    public MyPlanInfoAdapter(List<Plan> list, Context context) {
        this(list, new ArrayList(), context);
    }

    public MyPlanInfoAdapter(List<Plan> list, List<IntPlan> list2, Context context) {
        this.j = false;
        this.f3351a = new Handler() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.h("Suggestion_MyPlanInfoAdapter", "msg is null");
                    return;
                }
                super.handleMessage(message);
                int i = message.what;
                if (i == 1) {
                    MyPlanInfoAdapter.this.a();
                } else {
                    if (i != 2) {
                        return;
                    }
                    MyPlanInfoAdapter.this.d();
                }
            }
        };
        this.e = context;
        this.b = list;
        this.c = list2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aKZ_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(BaseApplication.e()).inflate(R.layout.sug_my_plans_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        if (koq.b(this.c, i)) {
            LogUtil.h("Suggestion_MyPlanInfoAdapter", "mCurrentIntPlanList is null");
            return;
        }
        IntPlan intPlan = this.c.get(i);
        if (intPlan != null) {
            if (intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
                e(cVar, intPlan);
            } else if (intPlan.getCompatiblePlan().acquireType() == 0) {
                c(cVar, intPlan);
            }
        }
    }

    private void e(c cVar, IntPlan intPlan) {
        int i;
        boolean z;
        LogUtil.a("Suggestion_MyPlanInfoAdapter", "setDoingFitnessIntPlansData");
        cVar.e.setOnClickListener(new View.OnClickListener() { // from class: gdh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyPlanInfoAdapter.this.aKY_(view);
            }
        });
        nrf.cIS_(cVar.f3357a, intPlan.getMetaInfo().getPicture(), nrf.d, 0, 0);
        cVar.i.setText(intPlan.getMetaInfo().getName());
        List<IntAction> b = fys.b(intPlan);
        IntAction intAction = null;
        if (b.size() > 0) {
            Iterator<IntAction> it = b.iterator();
            i = 0;
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                IntAction next = it.next();
                i++;
                if (next.getPunchFlag() == 0) {
                    intAction = next;
                    break;
                }
            }
        } else {
            i = 0;
            z = false;
        }
        d(cVar, intPlan);
        if (fyw.l(intPlan)) {
            cVar.l.setVisibility(8);
            if (z) {
                if (intAction == null) {
                    String string = this.e.getString(R.string._2130848840_res_0x7f022c48);
                    cVar.o.setText(string);
                    cVar.j.setText(string);
                    cVar.j.setVisibility(8);
                    cVar.n.setVisibility(0);
                    cVar.j.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
                    return;
                }
                d(cVar, intAction.getActionId(), i, intPlan);
                return;
            }
            String string2 = this.e.getString(R.string._2130848447_res_0x7f022abf);
            cVar.o.setText(string2);
            cVar.j.setText(string2);
            cVar.j.setVisibility(8);
            cVar.n.setVisibility(0);
            cVar.j.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
            if (intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                b(cVar);
            }
        }
    }

    public /* synthetic */ void aKY_(View view) {
        JumpUtil.c(this.e);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(c cVar) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(gib.b(System.currentTimeMillis()), System.currentTimeMillis());
        hiAggregateOption.setAggregateType(0);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setCount(1);
        WeakReference weakReference = new WeakReference(cVar);
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            weightApi.getWeightData(hiAggregateOption, new AnonymousClass6(weakReference));
        } else {
            LogUtil.h("Suggestion_MyPlanInfoAdapter", "weightApi is null.");
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter$6, reason: invalid class name */
    public class AnonymousClass6 implements WeightCallback {

        /* renamed from: a, reason: collision with root package name */
        private Handler f3355a = new Handler(Looper.getMainLooper());
        final /* synthetic */ WeakReference c;

        AnonymousClass6(WeakReference weakReference) {
            this.c = weakReference;
        }

        public /* synthetic */ void e(ArrayList arrayList, WeakReference weakReference) {
            MyPlanInfoAdapter.this.d((ArrayList<qtm>) arrayList, (c) weakReference.get());
        }

        @Override // com.huawei.ui.main.stories.health.weight.callback.WeightCallback
        public void getWeight(final ArrayList<qtm> arrayList) {
            Handler handler = this.f3355a;
            final WeakReference weakReference = this.c;
            handler.post(new Runnable() { // from class: gde
                @Override // java.lang.Runnable
                public final void run() {
                    MyPlanInfoAdapter.AnonymousClass6.this.e(arrayList, weakReference);
                }
            });
        }
    }

    private void d(c cVar, IntPlan intPlan) {
        float floatValue = ((Float) intPlan.getStat("progress").getValue()).floatValue();
        cVar.g.setText(ffy.d(this.e, R.string._2130848449_res_0x7f022ac1, jed.b(floatValue, 2, 1)));
        cVar.k.setProgress(Math.round(floatValue));
        int e = fyw.e(intPlan);
        if (e > intPlan.getMetaInfo().getDayCount()) {
            cVar.b.setVisibility(8);
            cVar.d.setVisibility(0);
            cVar.m.setText(R.string._2130846125_res_0x7f0221ad);
            cVar.f.setText(intPlan.getMetaInfo().getName());
            return;
        }
        if (e <= 0) {
            String string = this.e.getResources().getString(R.string._2130846123_res_0x7f0221ab, DateUtils.formatDateTime(this.e, intPlan.getPlanTimeInfo().getBeginDate() * 1000, 24));
            cVar.b.setVisibility(8);
            cVar.d.setVisibility(0);
            cVar.m.setText(string);
            cVar.f.setText(intPlan.getMetaInfo().getName());
            return;
        }
        cVar.b.setVisibility(0);
        cVar.d.setVisibility(8);
        cVar.h.setText(ffy.c(this.e, e, intPlan.getMetaInfo().getDayCount(), 0));
    }

    private void d(c cVar, String str, int i, IntPlan intPlan) {
        cVar.j.setVisibility(0);
        cVar.n.setVisibility(8);
        cVar.j.setText(R.string._2130848439_res_0x7f022ab7);
        cVar.j.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.getCourseById(str, null, null, new AnonymousClass9(cVar, intPlan, i));
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter$9, reason: invalid class name */
    public class AnonymousClass9 extends UiCallback<Workout> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3356a;
        final /* synthetic */ c d;
        final /* synthetic */ IntPlan e;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        AnonymousClass9(c cVar, IntPlan intPlan, int i) {
            this.d = cVar;
            this.e = intPlan;
            this.f3356a = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            final FitWorkout a2 = mod.a(workout);
            if (a2 == null) {
                return;
            }
            this.d.o.setText(a2.acquireName());
            HealthButton healthButton = this.d.j;
            final IntPlan intPlan = this.e;
            final int i = this.f3356a;
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: gdm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyPlanInfoAdapter.AnonymousClass9.this.aLa_(a2, intPlan, i, view);
                }
            });
        }

        public /* synthetic */ void aLa_(FitWorkout fitWorkout, IntPlan intPlan, int i, View view) {
            long currentTimeMillis = System.currentTimeMillis();
            if (fitWorkout.isRunModelCourse()) {
                gds.e(MyPlanInfoAdapter.this.e, fitWorkout, intPlan, currentTimeMillis, i);
            } else {
                gds.d(MyPlanInfoAdapter.this.e, fitWorkout, intPlan, i, currentTimeMillis);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (ggx.a(this.e, BluetoothAdapter.getDefaultAdapter() != null ? BluetoothAdapter.getDefaultAdapter().isEnabled() : false) && ggx.c(this.e)) {
            mwu.d().a(new a(this));
        } else {
            a();
        }
    }

    private void c(c cVar, IntPlan intPlan) {
        this.d = intPlan.getCompatiblePlan();
        cVar.l.setVisibility(8);
        cVar.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == null) {
                    LogUtil.h("Suggestion_MyPlanInfoAdapter", "view == null");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (view.hasWindowFocus()) {
                    JumpUtil.c(MyPlanInfoAdapter.this.e);
                    HashMap hashMap = new HashMap(2);
                    hashMap.put("click", "1");
                    hashMap.put("type", 1);
                    hashMap.put("planType", 0);
                    hashMap.put("planName", MyPlanInfoAdapter.this.d.acquireName());
                    hashMap.put("enter", "2");
                    gge.e(AnalyticsValue.HEALTH_HOME_TRAINING_PROGRAM_PIC_2010036.value(), hashMap);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("Suggestion_MyPlanInfoAdapter", "on click return because window not has focus");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        e(cVar);
        cVar.j.setVisibility(0);
        cVar.j.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
        cVar.k.setVisibility(0);
        cVar.h.setVisibility(0);
        cVar.i.setText(this.d.acquireName());
        d(cVar);
        b(cVar, intPlan);
        nrf.cHI_(mod.b(this.d.getPriceTagBeanList()), cVar.c, nrf.d);
    }

    private void b(c cVar, IntPlan intPlan) {
        if (intPlan.getStat("progress") != null) {
            float floatValue = ((Float) intPlan.getStat("progress").getValue()).floatValue();
            cVar.k.setProgress(Math.round(floatValue));
            cVar.g.setText(ffy.d(this.e, R.string._2130848449_res_0x7f022ac1, jed.b(floatValue, 2, 1)));
        }
    }

    private void d(c cVar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        List<PlanWorkout> b = b(this.d, format);
        if (koq.d(b, 0)) {
            this.h = b.get(0);
        } else {
            this.h = null;
        }
        if (this.h == null) {
            String string = this.e.getString(R.string._2130848447_res_0x7f022abf);
            cVar.o.setText(string);
            cVar.n.setVisibility(0);
            cVar.j.setVisibility(8);
            cVar.j.setText(string);
            cVar.j.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
            cVar.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            cVar.n.setVisibility(8);
            cVar.j.setVisibility(0);
            a(cVar);
            cVar.j.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
            String popName = this.h.popName();
            if (!TextUtils.isEmpty(popName)) {
                cVar.o.setText(popName);
                cVar.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        MyPlanInfoAdapter.this.e();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
        c(cVar, simpleDateFormat, format);
    }

    private void a(final c cVar) {
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.12
            @Override // java.lang.Runnable
            public void run() {
                CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                if (courseApi != null) {
                    final int i = courseApi.hasDoneTodayRunTask(MyPlanInfoAdapter.this.d) ? R.string._2130848482_res_0x7f022ae2 : R.string._2130848439_res_0x7f022ab7;
                    MyPlanInfoAdapter.this.f3351a.post(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.12.4
                        @Override // java.lang.Runnable
                        public void run() {
                            cVar.j.setText(i);
                        }
                    });
                } else {
                    LogUtil.h("Suggestion_MyPlanInfoAdapter", "setRunWorkout, run: courseApi is null.");
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.c r7, java.text.SimpleDateFormat r8, java.lang.String r9) {
        /*
            r6 = this;
            com.huawei.basefitnessadvice.model.Plan r0 = r6.d
            java.lang.String r0 = r0.acquireStartDate()
            r1 = 0
            java.lang.String r2 = "Suggestion_MyPlanInfoAdapter"
            r3 = 1
            if (r9 != 0) goto L17
            java.lang.String r4 = "todayDate == null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r4)
            r4 = r1
            goto L18
        L17:
            r4 = r3
        L18:
            if (r0 != 0) goto L24
            java.lang.String r8 = "startDate == null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r8)
            goto L4b
        L24:
            if (r4 == 0) goto L4b
            java.util.Date r9 = r8.parse(r9)     // Catch: java.text.ParseException -> L3d
            long r4 = r9.getTime()     // Catch: java.text.ParseException -> L3d
            java.util.Date r8 = r8.parse(r0)     // Catch: java.text.ParseException -> L3d
            long r8 = r8.getTime()     // Catch: java.text.ParseException -> L3d
            long r4 = r4 - r8
            r8 = 86400000(0x5265c00, double:4.2687272E-316)
            long r4 = r4 / r8
            int r8 = (int) r4
            goto L4c
        L3d:
            r8 = move-exception
            java.lang.String r9 = "exception = "
            java.lang.String r8 = health.compact.a.LogAnonymous.b(r8)
            java.lang.Object[] r8 = new java.lang.Object[]{r9, r8}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r8)
        L4b:
            r8 = r1
        L4c:
            int r9 = r8 + 1
            com.huawei.basefitnessadvice.model.Plan r0 = r6.d
            int r0 = r0.getDays()
            if (r9 <= r0) goto L86
            com.huawei.ui.commonui.button.HealthButton r8 = r7.j
            r9 = 2130848364(0x7f022a6c, float:1.730199E38)
            r8.setText(r9)
            com.huawei.ui.commonui.healthtextview.HealthTextView r8 = r7.o
            r9 = 2130846125(0x7f0221ad, float:1.729745E38)
            r8.setText(r9)
            com.huawei.ui.commonui.button.HealthButton r8 = r7.j
            android.content.Context r9 = r6.e
            r0 = 2131299386(0x7f090c3a, float:1.8216772E38)
            int r9 = androidx.core.content.ContextCompat.getColor(r9, r0)
            r8.setTextColor(r9)
            com.huawei.ui.commonui.button.HealthButton r8 = r7.j
            com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter$13 r9 = new com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter$13
            r9.<init>()
            r8.setOnClickListener(r9)
            com.huawei.basefitnessadvice.model.Plan r8 = r6.d
            int r8 = r8.getDays()
            int r8 = r8 - r3
            goto L89
        L86:
            if (r9 >= 0) goto L89
            r8 = -1
        L89:
            android.content.Context r9 = r6.e
            com.huawei.basefitnessadvice.model.Plan r0 = r6.d
            int r0 = r0.getDays()
            int r8 = r8 + r3
            java.lang.String r8 = defpackage.ffy.c(r9, r8, r0, r1)
            com.huawei.ui.commonui.healthtextview.HealthTextView r7 = r7.h
            r7.setText(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.c(com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter$c, java.text.SimpleDateFormat, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_MyPlanInfoAdapter", "checkUserInfo : userProfileMgrApi is null.");
        } else {
            userProfileMgrApi.checkUserInfo(this.e, new IBaseResponseCallback() { // from class: gdd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    MyPlanInfoAdapter.this.b(i, obj);
                }
            });
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        if (i != 0) {
            b();
        }
    }

    private void b() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_MyPlanInfoAdapter", "startRunPlan : planApi is null.");
            return;
        }
        planApi.setPlanType(0);
        planApi.cancelTodayPlanRemind(this.d);
        if (fib.e().c()) {
            ffm.e(this.h);
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", 1);
            hashMap.put(BleConstants.SPORT_TYPE, 1);
            gge.e(AnalyticsValue.HEALTH_HOME_START_TRAIN_BTN_2010037.value(), hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
        builder.e(R$string.IDS_hwh_motiontrack_ingnore_link_attention);
        builder.czC_(com.huawei.ui.commonui.R$string.IDS_apphelp_pwindows_continue_button, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_MyPlanInfoAdapter", "ignore link and continue");
                MyPlanInfoAdapter.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(com.huawei.ui.commonui.R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final Plan plan) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
        builder.e(R.string._2130848365_res_0x7f022a6d);
        builder.czC_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (planApi == null) {
                    LogUtil.b("Suggestion_MyPlanInfoAdapter", "finishPlan planApi is null.");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    final String acquireId = plan.acquireId();
                    planApi.setPlanType(gdr.e(acquireId));
                    planApi.finishPlan(1, acquireId, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.5.5
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i, String str) {
                            LogUtil.b("Suggestion_MyPlanInfoAdapter", "error Code = ", Integer.valueOf(i), ";errorInfo = ", str);
                            MyPlanInfoAdapter.this.a(i);
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: e, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(String str) {
                            LogUtil.a("Suggestion_MyPlanInfoAdapter", "data = ", str);
                            gdq.b().c(acquireId);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        builder.czz_(R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.e);
        builder.b(R.string._2130848356_res_0x7f022a64).e(c(i)).cyU_(R.string._2130848401_res_0x7f022a91, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    private List<PlanWorkout> b(Plan plan, String str) {
        List<PlanWorkout> acquireWorkouts = plan.acquireWorkouts();
        ArrayList arrayList = new ArrayList();
        if (koq.b(acquireWorkouts)) {
            LogUtil.h("Suggestion_MyPlanInfoAdapter", "CollectionUtils.isEmpty(planWorkouts)");
            return arrayList;
        }
        for (PlanWorkout planWorkout : acquireWorkouts) {
            if (planWorkout != null && str.equals(planWorkout.popDayInfo().acquireDate())) {
                if (planWorkout.popWorkoutId() != null) {
                    arrayList.add(planWorkout);
                }
                LogUtil.c("Suggestion_MyPlanInfoAdapter", Integer.valueOf(arrayList.size()), "==today have workouts: ", planWorkout.popName());
            }
        }
        return arrayList;
    }

    private void e(c cVar) {
        if (!LanguageUtil.bc(this.e)) {
            nrf.cIP_(cVar.f3357a, R.drawable._2131430940_res_0x7f0b0e1c, nrf.d, 0, 0);
        } else {
            aKW_(cVar.f3357a);
        }
    }

    private void aKW_(ImageView imageView) {
        nrf.cIO_(nrz.cKn_(this.e, R.drawable._2131430940_res_0x7f0b0e1c), imageView, nrf.d);
    }

    private String c(int i) {
        if (i != -404) {
            return this.e.getString(R.string._2130851533_res_0x7f0236cd);
        }
        return this.e.getString(R.string._2130839508_res_0x7f0207d4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.c(this.b)) {
            return this.b.size();
        }
        if (FitnessExternalUtils.a()) {
            return this.c.size();
        }
        return this.b.size();
    }

    static class a implements IBaseResponseCallback {
        private WeakReference<MyPlanInfoAdapter> d;

        a(MyPlanInfoAdapter myPlanInfoAdapter) {
            this.d = new WeakReference<>(myPlanInfoAdapter);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            MyPlanInfoAdapter myPlanInfoAdapter = this.d.get();
            if (i == 100000 && (obj instanceof String)) {
                OperatorStatus operatorStatus = (OperatorStatus) new Gson().fromJson(CommonUtil.z((String) obj), OperatorStatus.class);
                if (myPlanInfoAdapter == null || myPlanInfoAdapter.f3351a == null) {
                    return;
                }
                Handler handler = myPlanInfoAdapter.f3351a;
                if (operatorStatus.getTrainMonitorState() == 0) {
                    LogUtil.a("Suggestion_MyPlanInfoAdapter", "device is not running");
                    handler.sendMessage(handler.obtainMessage(1));
                    return;
                } else {
                    handler.sendMessage(handler.obtainMessage(2));
                    return;
                }
            }
            LogUtil.b("Suggestion_MyPlanInfoAdapter", "onResponse error");
            if (myPlanInfoAdapter == null || myPlanInfoAdapter.f3351a == null) {
                return;
            }
            myPlanInfoAdapter.f3351a.sendMessage(myPlanInfoAdapter.f3351a.obtainMessage(1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ArrayList<qtm> arrayList, c cVar) {
        UserInfomation userInfo;
        if (cVar == null) {
            return;
        }
        if (arrayList.size() == 0) {
            LogUtil.a("Suggestion_MyPlanInfoAdapter", "processLatestWeight has no record");
            cVar.j.setText(this.e.getString(R.string._2130848647_res_0x7f022b87));
            cVar.n.setVisibility(8);
            cVar.j.setVisibility(0);
            cVar.j.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
            UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
            final double weightOrDefaultValue = (userProfileMgrApi == null || (userInfo = userProfileMgrApi.getUserInfo()) == null) ? 65.0d : userInfo.getWeightOrDefaultValue();
            cVar.j.setOnClickListener(new View.OnClickListener() { // from class: gdf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyPlanInfoAdapter.this.aKX_(weightOrDefaultValue, view);
                }
            });
            return;
        }
        qtm qtmVar = arrayList.get(0);
        LogUtil.a("Suggestion_MyPlanInfoAdapter", "processLatestWeight has record ", Double.valueOf(qtmVar.e()));
        cVar.j.setVisibility(8);
        if (qtmVar.e() > 0.0d) {
            cVar.n.setVisibility(8);
            cVar.l.setVisibility(0);
            cVar.l.setText(ffy.d(this.e, R.string._2130849775_res_0x7f022fef, jed.b(qtmVar.e(), 1, 1)));
            cVar.l.setTextColor(ContextCompat.getColor(this.e, R.color._2131299386_res_0x7f090c3a));
        }
    }

    public /* synthetic */ void aKX_(double d, View view) {
        JumpUtil.c(this.e, d, 20.0d);
        ViewClickInstrumentation.clickOnView(view);
    }
}
