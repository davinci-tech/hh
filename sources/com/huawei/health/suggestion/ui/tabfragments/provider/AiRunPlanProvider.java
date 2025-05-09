package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider;
import com.huawei.health.suggestion.util.ClickEventUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ary;
import defpackage.asc;
import defpackage.ase;
import defpackage.ffy;
import defpackage.fzn;
import defpackage.gge;
import defpackage.ggf;
import defpackage.gib;
import defpackage.jed;
import defpackage.koq;
import defpackage.mmw;
import defpackage.mnu;
import defpackage.mod;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
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
import java.util.Map;

/* loaded from: classes8.dex */
public class AiRunPlanProvider extends FitnessEntranceProvider<List<Object>> {
    private static final int c = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296937_res_0x7f0902a9);
    private static final int e = ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296995_res_0x7f0902e3);
    private FitWorkout d;
    private Observer i;
    private PlanWorkout m;
    private String n = BaseApplication.getContext().getString(R.string._2130851561_res_0x7f0236e9);
    private String g = BaseApplication.getContext().getString(R.string._2130851561_res_0x7f0236e9);
    private String p = BaseApplication.getContext().getString(R.string._2130851561_res_0x7f0236e9);
    private Float b = Float.valueOf(0.0f);
    private String k = BaseApplication.getContext().getString(R.string._2130848439_res_0x7f022ab7);
    private int q = c;
    private boolean j = true;
    private boolean h = false;
    private boolean f = false;
    private WeakReference<SectionBean> o = new WeakReference<>(null);
    private OnFitnessStatusChangeCallback l = new OnFitnessStatusChangeCallback() { // from class: ged
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public final void onUpdate() {
            AiRunPlanProvider.this.d();
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final Handler f3379a = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                if (koq.e(message.obj, Plan.class)) {
                    AiRunPlanProvider.this.c(message.obj);
                    Plan plan = (Plan) ((List) message.obj).get(0);
                    AiRunPlanProvider.this.e(plan);
                    AiRunPlanProvider.this.b(plan);
                    if (plan.getPlanCategory() == 0) {
                        AiRunPlanProvider.this.a(plan);
                        return;
                    } else if (plan.getTransactionStatus() == 3) {
                        AiRunPlanProvider.this.g();
                        return;
                    } else {
                        AiRunPlanProvider.this.c(plan);
                        return;
                    }
                }
                return;
            }
            if (i == 1 && koq.e(message.obj, PlanInfo.class)) {
                AiRunPlanProvider.this.c(message.obj);
            }
        }
    };

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 258;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 4;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return null;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 0;
    }

    public /* synthetic */ void d() {
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "OnFitnessStatusChangeCallback, mPlanRefreshCallback");
        f();
    }

    public AiRunPlanProvider() {
        ary.a().e(this.l, "PLAN_DELETE");
        ary.a().e(this.l, "PLAN_CREATE");
        ary.a().e(this.l, "PLAN_UPDATE");
        ary.a().e(this.l, "PLAN_SWITCH");
        Observer observer = new Observer() { // from class: geg
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str, Object[] objArr) {
                AiRunPlanProvider.this.e(str, objArr);
            }
        };
        this.i = observer;
        ObserverManagerUtil.d(observer, "RUN_PLAN_UPDATE_HELLO_TIME");
    }

    public /* synthetic */ void e(String str, Object[] objArr) {
        a("AiRunPlanProvider");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        SectionBean sectionBean = this.o.get();
        if (sectionBean == null) {
            LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "setSectionBean sectionBean == null");
        } else {
            sectionBean.e(obj);
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return LoginInit.getInstance(context).getIsLogined() && FitnessExternalUtils.a() && !FitnessExternalUtils.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        SectionBean sectionBean = this.o.get();
        if (sectionBean == null) {
            ReleaseLogUtil.d("Track_Provider_Track_AiRunPlanProvider", "setSectionBeanData sectionBean == null tag:", str);
        } else {
            sectionBean.e(sectionBean.e());
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "loadData");
        this.o = new WeakReference<>(sectionBean);
        this.h = false;
        if (isActive(context)) {
            f();
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<Object> list) {
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "parseParams");
        c(hashMap, list);
    }

    private void c(HashMap<String, Object> hashMap, List<Object> list) {
        hashMap.put("TITLE", a());
        if (koq.e((Object) list, Plan.class)) {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", false);
            d((List<Plan>) list, (Map<String, Object>) hashMap);
            c((List<Plan>) list, (Map<String, Object>) hashMap);
        } else if (koq.e((Object) list, mmw.class)) {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", true);
            d(hashMap);
            a((List<mmw>) list, (Map<String, Object>) hashMap);
        }
    }

    protected String a() {
        return BaseApplication.getContext().getResources().getString(R.string._2130844966_res_0x7f021d26);
    }

    private void d(List<Plan> list, Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        for (Plan plan : list) {
            if (plan.getPlanCategory() == 0) {
                arrayList.add(Integer.valueOf(R.drawable._2131430940_res_0x7f0b0e1c));
            } else {
                arrayList.add(plan.getPicture());
            }
            arrayList2.add(this.n);
            String str = this.g;
            if (str != null) {
                arrayList3.add(str);
            }
            String str2 = this.p;
            if (str2 != null) {
                arrayList4.add(str2);
            }
            String str3 = this.k;
            if (str3 != null) {
                arrayList6.add(str3);
                arrayList7.add(Integer.valueOf(this.q));
            }
            if (this.j && this.b != null) {
                arrayList5.add(ffy.d(BaseApplication.getContext(), R.string._2130844992_res_0x7f021d40, jed.b(this.b.floatValue(), 2, 1)));
                arrayList8.add(Integer.valueOf(this.b.intValue()));
            }
        }
        map.put("BACKGROUND_IMAGE", arrayList);
        map.put("NAME", arrayList2);
        map.put("COMPLETE_SITUATION", arrayList3);
        map.put("TODAY_TASK", arrayList4);
        map.put("FINISH_RATE", arrayList5);
        map.put("BUTTON_TEXT", arrayList6);
        map.put("BUTTON_TEXT_COLOR", arrayList7);
        map.put("PROGRESS_VALUE", arrayList8);
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", map);
    }

    private void c(final List<Plan> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.2
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                AiRunPlanProvider.this.d(str, (List<Plan>) list);
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                AiRunPlanProvider.this.e(i, (List<Plan>) list);
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                AiRunPlanProvider.this.d(i, str, list);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, List<Plan> list) {
        Plan plan;
        if ("MORE_CLICK_EVENT".equals(str)) {
            if (koq.d(list, 0) && (plan = list.get(0)) != null && plan.getPlanCategory() == 0) {
                g(plan);
            } else {
                ClickEventUtils.e(ClickEventUtils.ClickEventType.AI_RUN_COURSE, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, List<Plan> list) {
        if (koq.b(list, i)) {
            LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "position is out of bounds");
            return;
        }
        if (nsn.o()) {
            LogUtil.b("Track_Provider_Track_AiRunPlanProvider", " isFastClick");
            return;
        }
        Plan plan = list.get(i);
        if (plan == null) {
            LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "runPlanInfo is null");
        } else if (plan.getPlanCategory() == 0) {
            g(plan);
        } else {
            ClickEventUtils.e(ClickEventUtils.ClickEventType.PLAN_TAB, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str, List<Plan> list) {
        if ("BUTTON_CLICK_EVENT".equals(str)) {
            if (nsn.o()) {
                LogUtil.b("Track_Provider_Track_AiRunPlanProvider", " isFastClick");
                return;
            }
            if (this.h) {
                ClickEventUtils.e(ClickEventUtils.ClickEventType.JUMP_VIP, null);
                return;
            }
            if (koq.b(list, i)) {
                LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "position is out of bounds");
                return;
            }
            if (this.f) {
                HashMap hashMap = new HashMap();
                hashMap.put("currentPlan", list.get(i));
                ClickEventUtils.e(ClickEventUtils.ClickEventType.AI_RUN_PLAN_REPORT, hashMap);
                return;
            }
            if (this.m != null) {
                Plan plan = list.get(i);
                if (plan == null) {
                    LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "runPlanInfo is null");
                    return;
                } else {
                    if (plan.getPlanCategory() == 0) {
                        g(plan);
                        return;
                    }
                    return;
                }
            }
            if (this.d != null) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("currentPlan", list.get(i));
                hashMap2.put("planWorkout", this.d);
                ClickEventUtils.e(ClickEventUtils.ClickEventType.START_AI_RUN_PLAN, hashMap2);
            }
        }
    }

    private void d(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (Utils.o()) {
            arrayList.add(Integer.valueOf(R.drawable._2131431069_res_0x7f0b0e9d));
        } else {
            arrayList.add(Integer.valueOf(R.drawable._2131431068_res_0x7f0b0e9c));
        }
        arrayList2.add(ggf.e());
        arrayList3.add(ggf.d());
        arrayList4.add(BaseApplication.getContext().getString(R.string._2130844861_res_0x7f021cbd));
        map.put("BACKGROUND_IMAGE", arrayList);
        map.put("NAME", arrayList2);
        map.put("DESCRIPTION", arrayList3);
        map.put("BUTTON_TEXT", arrayList4);
    }

    private void a(final List<mmw> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.3
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("MORE_CLICK_EVENT".equals(str)) {
                    ClickEventUtils.e(ClickEventUtils.ClickEventType.AI_RUN_COURSE, null);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "position is out of bounds");
                    return;
                }
                if (nsn.o()) {
                    LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "mRunPlanTitle click too fast");
                } else if (((mmw) list.get(i)) == null) {
                    LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "runPlanInfo is null");
                } else {
                    ClickEventUtils.e(ClickEventUtils.ClickEventType.AI_RUN_COURSE, null);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                if ("BUTTON_CLICK_EVENT".equals(str)) {
                    ClickEventUtils.e(ClickEventUtils.ClickEventType.AI_RUN_COURSE, null);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(com.huawei.basefitnessadvice.model.Plan r10) {
        /*
            r9 = this;
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String r1 = "yyyy-MM-dd"
            r0.<init>(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 86400000(0x5265c00, double:4.2687272E-316)
            long r1 = r1 / r3
            java.lang.String r5 = r10.acquireStartDate()
            java.lang.String r6 = r10.getEndDate()
            java.lang.String r7 = "Track_Provider_Track_AiRunPlanProvider"
            r8 = 0
            if (r5 == 0) goto L21
            if (r6 != 0) goto L1f
            goto L21
        L1f:
            r6 = 1
            goto L2b
        L21:
            java.lang.String r6 = "startDate or endDate == null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.huawei.hwlogsmodel.LogUtil.h(r7, r6)
            r6 = r8
        L2b:
            if (r6 == 0) goto L45
            java.util.Date r0 = r0.parse(r5)     // Catch: java.text.ParseException -> L37
            long r5 = r0.getTime()     // Catch: java.text.ParseException -> L37
            long r5 = r5 / r3
            goto L47
        L37:
            r0 = move-exception
            java.lang.String r3 = "exception = "
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0}
            com.huawei.hwlogsmodel.LogUtil.b(r7, r0)
        L45:
            r5 = 0
        L47:
            int r0 = r10.getDays()
            long r1 = r1 - r5
            int r1 = (int) r1
            r2 = 0
            if (r1 <= r0) goto L72
            r9.k = r2
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            r2 = 2130846125(0x7f0221ad, float:1.729745E38)
            java.lang.String r1 = r1.getString(r2)
            r9.p = r1
            java.lang.String r10 = r10.acquireName()
            r9.n = r10
            android.content.Context r10 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            java.lang.String r10 = defpackage.ffy.c(r10, r0, r0, r8)
            r9.g = r10
            r9.j = r8
            goto Lac
        L72:
            if (r1 > 0) goto L9c
            r9.g = r2
            java.lang.String r0 = r10.acquireName()
            r9.n = r0
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            java.lang.String r10 = defpackage.ase.d(r0, r10)
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.res.Resources r0 = r0.getResources()
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            r1 = 2130846123(0x7f0221ab, float:1.7297445E38)
            java.lang.String r10 = r0.getString(r1, r10)
            r9.p = r10
            r9.j = r8
            goto Lac
        L9c:
            java.lang.String r10 = r10.acquireName()
            r9.n = r10
            android.content.Context r10 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            java.lang.String r10 = defpackage.ffy.c(r10, r1, r0, r8)
            r9.g = r10
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.e(com.huawei.basefitnessadvice.model.Plan):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Plan plan) {
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "getCurrentPlanRecord");
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "getCurrentPlanRecord planApi is null");
        } else {
            final UiCallback<PlanRecord> uiCallback = new UiCallback<PlanRecord>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "getCurrentPlanRecord fail errCode:", Integer.valueOf(i), " errorInfo:", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(PlanRecord planRecord) {
                    if (planRecord == null) {
                        LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "getCurrentPlanRecord success but data is null");
                        return;
                    }
                    LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "finishRate is ", Float.valueOf(planRecord.acquireFinishRate()));
                    AiRunPlanProvider.this.b = Float.valueOf(planRecord.acquireFinishRate());
                    AiRunPlanProvider.this.a("getCurrentPlanRecord");
                }
            };
            asc.e().b(new Runnable() { // from class: gel
                @Override // java.lang.Runnable
                public final void run() {
                    AiRunPlanProvider.e(PlanApi.this, plan, uiCallback);
                }
            });
        }
    }

    public static /* synthetic */ void e(PlanApi planApi, Plan plan, UiCallback uiCallback) {
        planApi.setPlanType(0);
        if (plan != null) {
            planApi.getPlanProgress(plan.acquireId(), (UiCallback<PlanRecord>) uiCallback);
        } else {
            LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "getCurrentPlanRecord have no current plan");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Plan plan) {
        List<PlanWorkout> d2 = d(plan, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (koq.d(d2, 0)) {
            this.m = d2.get(0);
        } else {
            this.m = null;
        }
        if (this.m == null) {
            this.p = BaseApplication.getContext().getString(R.string._2130848447_res_0x7f022abf);
            this.n = plan.acquireName();
            this.b = null;
            this.k = null;
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "getRunWorkout, run: courseApi is null.");
            return;
        }
        this.k = BaseApplication.getContext().getResources().getString(courseApi.hasDoneTodayRunTask(plan) ? R.string._2130848482_res_0x7f022ae2 : R.string._2130848439_res_0x7f022ab7);
        this.q = c;
        String popName = this.m.popName();
        if (!TextUtils.isEmpty(popName)) {
            this.n = popName;
        }
        this.p = plan.acquireName();
    }

    private List<PlanWorkout> d(Plan plan, String str) {
        List<PlanWorkout> acquireWorkouts = plan.acquireWorkouts();
        ArrayList arrayList = new ArrayList();
        if (koq.b(acquireWorkouts)) {
            LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "CollectionUtils.isEmpty(planWorkouts)");
            return arrayList;
        }
        for (PlanWorkout planWorkout : acquireWorkouts) {
            if (planWorkout != null && str.equals(planWorkout.popDayInfo().acquireDate())) {
                if (planWorkout.popWorkoutId() != null) {
                    arrayList.add(planWorkout);
                }
                LogUtil.c("Track_Provider_Track_AiRunPlanProvider", Integer.valueOf(arrayList.size()), "==today have workouts: ", planWorkout.popName());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.h = true;
        this.p = BaseApplication.getContext().getResources().getString(R.string._2130848620_res_0x7f022b6c);
        this.n = BaseApplication.getContext().getResources().getString(R.string._2130848621_res_0x7f022b6d);
        this.k = BaseApplication.getContext().getResources().getString(R.string._2130844867_res_0x7f021cc3);
        this.q = c;
        a("showVipExpiredView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Plan plan) {
        String str;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        int i = calendar.get(7);
        int d2 = gib.d(i);
        int e2 = ase.e(plan);
        LogUtil.c("Track_Provider_Track_AiRunPlanProvider", "PLAN = ", plan.toString());
        List<CourseDataBean> b = fzn.b(plan, e2, d2);
        mnu d3 = ase.d(plan, d2, e2);
        b(d3, b, plan);
        List<CourseDataBean> a2 = d3.a();
        boolean z = false;
        boolean z2 = ase.b(plan, e2, i) && koq.b(a2);
        if (koq.b(a2)) {
            this.n = plan.acquireName();
            d(e2, z2);
            return;
        }
        for (CourseDataBean courseDataBean : a2) {
            if (courseDataBean != null && courseDataBean.d()) {
                z = true;
            }
        }
        Iterator<CourseDataBean> it = a2.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            CourseDataBean next = it.next();
            if (next != null && !next.d()) {
                str = next.a();
                break;
            }
        }
        if ("Race".equals(str)) {
            d(e2);
        } else {
            c(str, z);
        }
    }

    private void d(int i) {
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "raceDayView");
        this.k = null;
        this.p = BaseApplication.getContext().getString(R.string._2130848765_res_0x7f022bfd);
        e(i);
    }

    private void d(int i, boolean z) {
        this.k = null;
        e(i);
        if (z) {
            this.p = BaseApplication.getContext().getString(R.string._2130848765_res_0x7f022bfd);
        } else if (this.j) {
            this.p = BaseApplication.getContext().getString(R.string._2130848447_res_0x7f022abf);
        }
        LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "courseDataBeanList is null isShowCompletionView：", Boolean.valueOf(z));
    }

    private void e(int i) {
        if (i > 1) {
            this.f = true;
            this.k = BaseApplication.getContext().getResources().getString(R.string._2130844994_res_0x7f021d42);
            this.q = c;
        }
    }

    private void c(String str, final boolean z) {
        ((CourseApi) Services.a("CoursePlanService", CourseApi.class)).getCourseById(str, null, null, new UiCallback<Workout>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "getCourseById", str2, Integer.valueOf(i));
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Workout workout) {
                LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "getCourseById onSuccess");
                if (workout == null) {
                    return;
                }
                if (z) {
                    AiRunPlanProvider.this.k = BaseApplication.getContext().getResources().getString(R.string._2130844993_res_0x7f021d41);
                } else {
                    AiRunPlanProvider.this.k = BaseApplication.getContext().getResources().getString(R.string._2130848439_res_0x7f022ab7);
                }
                AiRunPlanProvider.this.q = AiRunPlanProvider.c;
                AiRunPlanProvider.this.d = mod.a(workout);
                AiRunPlanProvider aiRunPlanProvider = AiRunPlanProvider.this;
                aiRunPlanProvider.p = aiRunPlanProvider.d.acquireName();
            }
        });
    }

    private void b(mnu mnuVar, List<CourseDataBean> list, Plan plan) {
        if (mnuVar.e()) {
            this.p = BaseApplication.getContext().getString(R.string._2130844995_res_0x7f021d43);
            this.n = plan.acquireName();
            this.k = BaseApplication.getContext().getString(R.string._2130844734_res_0x7f021c3e);
            this.q = e;
            return;
        }
        if (koq.b(list)) {
            LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "courseDataBeanList is empty");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        for (CourseDataBean courseDataBean : list) {
            if (courseDataBean == null) {
                LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "planCourseData is null");
                return;
            } else if (!courseDataBean.d() && courseDataBean.c() == 1) {
                planApi.getWorkoutById(courseDataBean.a(), CommonUtil.e(com.huawei.haf.application.BaseApplication.e()), Locale.getDefault().getCountry(), new d(this));
                return;
            }
        }
        for (CourseDataBean courseDataBean2 : list) {
            if (courseDataBean2 == null) {
                LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "planCourseData is null");
                return;
            } else if (!courseDataBean2.d() && courseDataBean2.c() == 2) {
                planApi.getWorkoutById(courseDataBean2.a(), CommonUtil.e(com.huawei.haf.application.BaseApplication.e()), Locale.getDefault().getCountry(), new d(this));
                return;
            }
        }
    }

    private void g(final Plan plan) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(BaseApplication.getActivity());
        builder.e(R.string._2130845216_res_0x7f021e20);
        builder.czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: gef
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiRunPlanProvider.this.aLI_(plan, view);
            }
        });
        builder.czC_(R.string._2130844969_res_0x7f021d29, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "showOfflineDialog start ai run plan");
                AiRunPlanProvider.this.d(plan);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        final NoTitleCustomAlertDialog e2 = builder.e();
        BaseApplication.getActivity().runOnUiThread(new Runnable() { // from class: gec
            @Override // java.lang.Runnable
            public final void run() {
                AiRunPlanProvider.b(NoTitleCustomAlertDialog.this);
            }
        });
    }

    public /* synthetic */ void aLI_(Plan plan, View view) {
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "showOfflineDialog ok");
        d(plan);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void b(NoTitleCustomAlertDialog noTitleCustomAlertDialog) {
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.show();
        }
    }

    public void d(Plan plan) {
        if (plan == null) {
            LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "finishPlan currentPlan is null");
            return;
        }
        String acquireId = plan.acquireId();
        if (acquireId != null) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "finishPlan, getCurrentPlan : planApi is null.");
            } else {
                planApi.setPlanType(0);
                planApi.finishPlan(0, acquireId, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiRunPlanProvider.8
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "finish  plan failed  ", str);
                        nrh.d(BaseApplication.getActivity(), str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str) {
                        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", "finish  plan onSuccess  ", str);
                        ClickEventUtils.e(ClickEventUtils.ClickEventType.AI_RUN_COURSE, null);
                        AiRunPlanProvider.this.b();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("enter", "3");
        gge.e("1120019", hashMap);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        ary.a().c(this.l, "PLAN_DELETE");
        ary.a().c(this.l, "PLAN_CREATE");
        ary.a().c(this.l, "PLAN_UPDATE");
        ary.a().c(this.l, "PLAN_SWITCH");
        ObserverManagerUtil.e(this.i, "RUN_PLAN_UPDATE_HELLO_TIME");
    }

    private void f() {
        Object[] objArr = new Object[2];
        objArr[0] = "getPlanInfo sectionBean == null:";
        objArr[1] = Boolean.valueOf(this.o.get() == null);
        LogUtil.a("Track_Provider_Track_AiRunPlanProvider", objArr);
        if (isActive(BaseApplication.getContext())) {
            asc.e().b(new Runnable() { // from class: gej
                @Override // java.lang.Runnable
                public final void run() {
                    AiRunPlanProvider.this.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "getMyPlanInfo : planApi is null.");
        } else {
            planApi.b(new a(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Plan> list) {
        if (!koq.b(list)) {
            c(list, 0);
        } else {
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new mmw());
        c(arrayList, 1);
    }

    private void c(Object obj, int i) {
        Message obtainMessage = this.f3379a.obtainMessage(i);
        obtainMessage.obj = obj;
        this.f3379a.sendMessage(obtainMessage);
    }

    static class a extends UiCallback<IntPlan> {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<AiRunPlanProvider> f3382a;
        AiRunPlanProvider e;

        a(AiRunPlanProvider aiRunPlanProvider) {
            WeakReference<AiRunPlanProvider> weakReference = new WeakReference<>(aiRunPlanProvider);
            this.f3382a = weakReference;
            this.e = weakReference.get();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
            AiRunPlanProvider aiRunPlanProvider = this.e;
            if (aiRunPlanProvider != null) {
                aiRunPlanProvider.h();
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(IntPlan intPlan) {
            if (this.e == null) {
                LogUtil.h("Track_Provider_Track_AiRunPlanProvider", "mRunPlanProvider == null");
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (intPlan != null && intPlan.getCompatiblePlan() != null && (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN))) {
                arrayList.add(intPlan.getCompatiblePlan());
            }
            this.e.b(arrayList);
        }
    }

    static class d extends UiCallback<FitWorkout> {
        AiRunPlanProvider b;
        WeakReference<AiRunPlanProvider> e;

        d(AiRunPlanProvider aiRunPlanProvider) {
            WeakReference<AiRunPlanProvider> weakReference = new WeakReference<>(aiRunPlanProvider);
            this.e = weakReference;
            this.b = weakReference.get();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_Provider_Track_AiRunPlanProvider", "FitWorkoutUiCallBack errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(FitWorkout fitWorkout) {
            if (fitWorkout == null || this.b == null) {
                Object[] objArr = new Object[2];
                objArr[0] = "FitWorkoutUiCallBack onSuccess data is null. mAiRunPlanProvider:";
                objArr[1] = Boolean.valueOf(this.b == null);
                LogUtil.b("Track_Provider_Track_AiRunPlanProvider", objArr);
                return;
            }
            if (StringUtils.g(fitWorkout.acquireName())) {
                this.b.p = null;
            } else if ("Race".equals(fitWorkout.acquireId())) {
                this.b.p = BaseApplication.getContext().getString(R.string._2130848765_res_0x7f022bfd);
            } else {
                this.b.p = fitWorkout.acquireName();
            }
            SectionBean sectionBean = (SectionBean) this.b.o.get();
            if (sectionBean != null) {
                sectionBean.e(sectionBean.e());
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_AiRunPlanProvider";
    }
}
