package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioGroup;
import com.huawei.pluginfitnessadvice.audio.SleepAudioInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository;
import com.huawei.trade.PayApi;
import com.huawei.ui.main.R$string;
import defpackage.fff;
import defpackage.sbg;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class sbg {

    /* renamed from: a, reason: collision with root package name */
    public static final String f17004a = AnalyticsValue.REMIND_PLAN_AND_COURSE_EVENT.value();
    protected Context b = BaseApplication.e();

    public void dUZ_(final Handler handler) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: sbo
            @Override // java.lang.Runnable
            public final void run() {
                sbg.this.dVa_(handler);
            }
        });
    }

    /* synthetic */ void dVa_(Handler handler) {
        LogUtil.h("PlanDataManager", "getAllData start");
        HashMap hashMap = new HashMap(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        e(countDownLatch, hashMap);
        c(countDownLatch, hashMap);
        a(countDownLatch, hashMap);
        try {
            LogUtil.a("PlanDataManager", "getAllData isReached = ", Boolean.valueOf(countDownLatch.await(5L, TimeUnit.SECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("PlanDataManager", "thread interrupted");
        }
        LogUtil.h("PlanDataManager", "getAllData finish");
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(a.N);
            obtainMessage.obj = hashMap;
            handler.sendMessage(obtainMessage);
        }
    }

    /* renamed from: sbg$5, reason: invalid class name */
    class AnonymousClass5 extends UiCallback<IntPlan> {
        final /* synthetic */ CountDownLatch b;
        final /* synthetic */ Map e;

        AnonymousClass5(CountDownLatch countDownLatch, Map map) {
            this.b = countDownLatch;
            this.e = map;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("PlanDataManager", "getCurrentIntPlan, onFailure, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
            this.b.countDown();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final IntPlan intPlan) {
            CommonUtil.u("PlanDataManager-getCurrentFitnessPlan end");
            ThreadPoolManager d = ThreadPoolManager.d();
            final Map map = this.e;
            final CountDownLatch countDownLatch = this.b;
            d.execute(new Runnable() { // from class: sbv
                @Override // java.lang.Runnable
                public final void run() {
                    sbg.AnonymousClass5.this.d(intPlan, map, countDownLatch);
                }
            });
        }

        /* synthetic */ void d(IntPlan intPlan, Map map, CountDownLatch countDownLatch) {
            sbg.this.e(intPlan, (Map<Integer, List<sch>>) map, countDownLatch);
        }
    }

    private void e(CountDownLatch countDownLatch, Map<Integer, List<sch>> map) {
        ((PlanApi) Services.c("CoursePlanService", PlanApi.class)).b(new AnonymousClass5(countDownLatch, map));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final IntPlan intPlan, final Map<Integer, List<sch>> map, final CountDownLatch countDownLatch) {
        if (intPlan == null) {
            LogUtil.h("PlanDataManager", "resourceAuth mCurrentIntPlan is null");
            countDownLatch.countDown();
            return;
        }
        int i = (intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || intPlan.getPlanType() == IntPlan.PlanType.FIT_PLAN) ? 2 : 4;
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            i = 5;
        }
        PayApi payApi = (PayApi) Services.c("TradeService", PayApi.class);
        LogUtil.a("PlanDataManager", "resType:", Integer.valueOf(i), "PlanTempId:", intPlan.getPlanTempId());
        payApi.resourceAuth(i, intPlan.getPlanTempId(), new IBaseResponseCallback() { // from class: sbr
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                sbg.this.d(intPlan, map, countDownLatch, i2, obj);
            }
        });
    }

    /* synthetic */ void d(IntPlan intPlan, Map map, CountDownLatch countDownLatch, int i, Object obj) {
        LogUtil.a("PlanDataManager", "resourceAuth errorCode: ", Integer.valueOf(i));
        LogUtil.a("PlanDataManager", "resourceAuth objData: ", obj);
        a((i == 0 && (obj instanceof Integer)) ? c(intPlan, ((Integer) obj).intValue()) : false, intPlan, (Map<Integer, List<sch>>) map, countDownLatch);
    }

    private void a(boolean z, IntPlan intPlan, Map<Integer, List<sch>> map, CountDownLatch countDownLatch) {
        sch schVar = new sch();
        schVar.e(intPlan.getMetaInfo().getPicture());
        long createTime = intPlan.getPlanTimeInfo().getCreateTime() * 1000;
        long currentTimeMillis = System.currentTimeMillis();
        long e = jdl.e(System.currentTimeMillis());
        if (currentTimeMillis <= createTime && createTime <= e) {
            createTime = jdl.t(System.currentTimeMillis());
        }
        schVar.e(createTime);
        schVar.a(intPlan.getMetaInfo().getName());
        schVar.dVw_(new View.OnClickListener() { // from class: sbs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                sbg.this.dVf_(view);
            }
        });
        ArrayList arrayList = new ArrayList(10);
        if (z) {
            LogUtil.a("PlanDataManager", "isIntPlanVipExpired true");
            StringBuilder sb = new StringBuilder();
            String string = this.b.getString(R$string.IDS_plan_run_renew_now);
            sb.append(this.b.getString(R$string.IDS_plan_run_renewal_continuation));
            sb.append(" ");
            sb.append(string);
            arrayList.add(sb.toString());
            schVar.dVv_(new View.OnClickListener() { // from class: sbt
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    sbg.this.dVg_(view);
                }
            });
        } else {
            arrayList.add(a(intPlan));
        }
        schVar.c(arrayList);
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(schVar);
        map.put(0, arrayList2);
        e(schVar, intPlan.getPlanId(), countDownLatch);
    }

    /* synthetic */ void dVf_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dVg_(View view) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(this.b, AnalyticsValue.VIP_CENTER_RENEW_BTN_CLICK_EVENT.value(), hashMap, 0);
        LogUtil.a("PlanDataManager", "goto vip renew page");
        AppRouter.b("/OperationBundle/MemberTypeSelectActivity").j();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(final sch schVar, String str, final CountDownLatch countDownLatch) {
        LogUtil.a("PlanDataManager", "getPlanRecordInfo");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.getWorkoutRecords(str, new UiCallback<List<WorkoutRecord>>() { // from class: sbg.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("PlanDataManager", "getPlanRecordInfo getWorkoutRecords error");
                    countDownLatch.countDown();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<WorkoutRecord> list) {
                    if (koq.b(list)) {
                        LogUtil.a("PlanDataManager", "getPlanRecordInfo data is empty");
                        countDownLatch.countDown();
                        return;
                    }
                    LogUtil.a("PlanDataManager", "getPlanRecordInfo success:", Integer.valueOf(list.size()));
                    int size = list.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        }
                        WorkoutRecord workoutRecord = list.get(size);
                        if (workoutRecord.acquireId() != 0) {
                            schVar.e(workoutRecord.acquireExerciseTime());
                            break;
                        }
                        size--;
                    }
                    countDownLatch.countDown();
                }
            });
        }
    }

    private void e() {
        if (nsn.o()) {
            LogUtil.a("PlanDataManager", "onClick, isFastClick");
        } else {
            d(2, 0);
            ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).launchPlanTab();
        }
    }

    private boolean c(IntPlan intPlan, int i) {
        if (intPlan == null) {
            return false;
        }
        if (intPlan.getWeekInfoByIdx(0) == null) {
            return true;
        }
        return (i == 0 || i == 1) ? false : true;
    }

    private String a(IntPlan intPlan) {
        LogUtil.a("PlanDataManager", "getPlanDescription");
        int e = jdl.e(intPlan.getPlanTimeInfo().getBeginDate() * 1000, System.currentTimeMillis());
        LogUtil.a("PlanDataManager", "getTodayActions current day = ", Integer.valueOf(e));
        if (e > intPlan.getMetaInfo().getDayCount()) {
            return this.b.getString(R$string.sug_home_overdueplan);
        }
        if (e <= 0) {
            String formatDateTime = DateUtils.formatDateTime(this.b, intPlan.getPlanTimeInfo().getBeginDate() * 1000, 24);
            LogUtil.a("PlanDataManager", "getTodayActions startDates = ", formatDateTime);
            return this.b.getResources().getString(R$string.IDS_fitness_plan_start_date, formatDateTime);
        }
        IntDayPlan dayInfo = intPlan.getDayInfo(ase.g(intPlan), jdl.n(System.currentTimeMillis()));
        if (dayInfo == null) {
            return this.b.getResources().getString(R$string.IDS_suggest_you_rest_today);
        }
        if (koq.b(b(dayInfo))) {
            return this.b.getResources().getString(R$string.IDS_suggest_you_rest_today);
        }
        String str = ffy.c(this.b, e, intPlan.getMetaInfo().getDayCount(), 0) + " " + ffy.d(this.b, R$string.IDS_plan_finish_percent, UnitUtil.e(((Float) intPlan.getStat("progress").getValue()).floatValue(), 2, 1));
        LogUtil.a("PlanDataManager", "getPlanDescription:", str, " language:", CommonUtil.x());
        return str;
    }

    private List<IntAction> b(IntDayPlan intDayPlan) {
        ArrayList arrayList = new ArrayList(10);
        if (intDayPlan == null) {
            LogUtil.h("PlanDataManager", "getTodayActions dayInfo is null ");
            return arrayList;
        }
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null && inPlanAction.getActionId() != null && (inPlanAction.getActionType() == IntAction.ActionType.RUN || inPlanAction.getActionType() == IntAction.ActionType.WORKOUT)) {
                arrayList.add(inPlanAction);
            }
        }
        return arrayList;
    }

    private void c(final CountDownLatch countDownLatch, final Map<Integer, List<sch>> map) {
        LogUtil.a("PlanDataManager", "getSportCoursesData");
        ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).getUserCourseList(0, 2, 1, "", new UiCallback<List<Workout>>() { // from class: sbg.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("PlanDataManager", "UiCallback onFailure errorInfo = ", str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<Workout> list) {
                LogUtil.a("PlanDataManager", "MyUiCallbackFitnessCourse(", Integer.valueOf(hashCode()), ") onSuccess()");
                sbg.this.c(list, (Map<Integer, List<sch>>) map);
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<Workout> list, Map<Integer, List<sch>> map) {
        if (koq.b(list)) {
            LogUtil.h("PlanDataManager", "reorderList is empty!");
            return;
        }
        LogUtil.a("PlanDataManager", "reorderList size = ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(list.size());
        for (Workout workout : list) {
            if (workout != null) {
                sch schVar = new sch();
                schVar.e(workout.getNewUseWorkoutTime() * 1000);
                FitWorkout a2 = mod.a(workout);
                if (a2 != null) {
                    b(a2, schVar);
                } else {
                    e(workout, schVar);
                }
                arrayList.add(schVar);
            }
        }
        map.put(1, arrayList);
    }

    private void b(final FitWorkout fitWorkout, sch schVar) {
        LogUtil.a("PlanDataManager", "processFitWorkoutData course type = ", Integer.valueOf(fitWorkout.getCourseDefineType()));
        schVar.a(fitWorkout.acquireName());
        schVar.dVw_(new View.OnClickListener() { // from class: sbm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                sbg.this.dVh_(fitWorkout, view);
            }
        });
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(b(fitWorkout));
        if (fitWorkout.getCourseDefineType() == 1) {
            long g = CommonUtils.g(fitWorkout.acquireId()) % 3;
            schVar.e(Integer.valueOf(g == 0 ? R.drawable.pic_custom_yellow_small : g == 1 ? R.drawable.pic_custom_red_small : R.drawable.pic_custom_blue_small));
            arrayList.add(ffy.d(this.b, R$string.IDS_hwh_motiontrack_sport_data_share_date, UnitUtil.a("yyyy/MM/dd", fitWorkout.getPublishDate()), this.b.getString(R$string.sug_plan_create)));
        } else {
            String topicPreviewPicUrl = fitWorkout.getTopicPreviewPicUrl();
            if (TextUtils.isEmpty(topicPreviewPicUrl)) {
                topicPreviewPicUrl = fitWorkout.acquireMidPicture();
            }
            schVar.e(topicPreviewPicUrl);
            arrayList.add(ggm.d(fitWorkout.acquireDifficulty()));
            if (fitWorkout.acquireDuration() != 0) {
                arrayList.add(this.b.getResources().getQuantityString(R.plurals._2130903305_res_0x7f030109, fitWorkout.acquireDuration() / 60, moe.k(fitWorkout.acquireDuration())));
            }
        }
        schVar.c(arrayList);
    }

    /* synthetic */ void dVh_(FitWorkout fitWorkout, View view) {
        c(fitWorkout);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(Workout workout, sch schVar) {
        LogUtil.a("PlanDataManager", "processWorkoutPackageData");
        final WorkoutPackageInfo e = mod.e(workout);
        if (e == null) {
            LogUtil.h("PlanDataManager", "setWorkoutPackageInfo workout is null");
            return;
        }
        schVar.a(e.getWorkoutPackageName());
        e.saveId(e.getWorkoutPackageId());
        String squarePicture = e.getSquarePicture();
        if (TextUtils.isEmpty(squarePicture)) {
            squarePicture = e.getTopicPreviewPicUrl();
        }
        schVar.e(squarePicture);
        List<String> arrayList = new ArrayList<>(10);
        arrayList.add(this.b.getResources().getQuantityString(R.plurals._2130903319_res_0x7f030117, e.getWorkoutFinishCount().intValue(), e.getWorkoutFinishCount(), e.getWorkoutTotalCount()));
        int enableNewUrl = e.getEnableNewUrl();
        LogUtil.a("PlanDataManager", "packageInfo isEnableNewUrl ", Integer.valueOf(enableNewUrl));
        if (enableNewUrl == 1) {
            schVar.dVw_(new View.OnClickListener() { // from class: sbn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    sbg.this.dVi_(e, view);
                }
            });
        } else {
            schVar.dVw_(new View.OnClickListener() { // from class: sbp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    sbg.this.dVj_(e, view);
                }
            });
        }
        schVar.c(arrayList);
    }

    /* synthetic */ void dVi_(WorkoutPackageInfo workoutPackageInfo, View view) {
        e(workoutPackageInfo.getWorkoutPackageId(), 1);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dVj_(WorkoutPackageInfo workoutPackageInfo, View view) {
        c(workoutPackageInfo.getWorkoutPackageId());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(FitWorkout fitWorkout) {
        if (nsn.o()) {
            LogUtil.a("PlanDataManager", "onClick, isFastClick");
            return;
        }
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        if (fitWorkout == null) {
            LogUtil.h("PlanDataManager", "start fitness failed with the null fitWorkout");
            return;
        }
        d(2, 1);
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.c(R.anim._2130772059_res_0x7f01005b);
        mmpVar.d(R.anim._2130771981_res_0x7f01000d);
        if (fitWorkout.getCourseDefineType() == 1) {
            mmpVar.g(1);
        }
        mod.b(this.b, fitWorkout, mmpVar);
    }

    private void c(String str) {
        d(2, 1);
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("mediaCenter", bzs.e().getCommonJsModule("mediaCenter")).enableOnPauseCallback().setImmerse().showStatusBar().setStatusBarTextBlack(true).addCustomizeArg("workoutPackageId", str).setForceDarkMode(0).build();
        SeriesCourseH5Repository.registerService();
        H5ProClient.startH5MiniProgram(this.b, "com.huawei.health.h5.course", build);
        ary.a().e("COLLECTION_ADD");
    }

    private String b(FitWorkout fitWorkout) {
        if (fitWorkout.isLongExplainVideoCourse()) {
            return this.b.getString(R$string.IDS_fitness_advice_explain_courses);
        }
        if (fitWorkout.getIntervals() == -2) {
            return this.b.getString(R$string.IDS_fitness_advice_run_trained_never);
        }
        if (fitWorkout.getIntervals() == -3) {
            return this.b.getString(R$string.IDS_fitness_advice_run_trained_long_ago);
        }
        if (fitWorkout.getIntervals() == 0) {
            return this.b.getString(R$string.IDS_fitness_advice_run_trained_today);
        }
        int intervals = fitWorkout.getIntervals();
        return this.b.getResources().getQuantityString(R.plurals._2130903356_res_0x7f03013c, intervals, Integer.valueOf(intervals));
    }

    private void a(CountDownLatch countDownLatch, Map<Integer, List<sch>> map) {
        ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).getAudioBehaviorList(new fff.e().a(1).b(2).c(0).e(2).d(1).a(), new AnonymousClass1(countDownLatch, map));
    }

    /* renamed from: sbg$1, reason: invalid class name */
    class AnonymousClass1 extends UiCallback<List<SleepAudioSeries>> {
        final /* synthetic */ CountDownLatch c;
        final /* synthetic */ Map e;

        AnonymousClass1(CountDownLatch countDownLatch, Map map) {
            this.c = countDownLatch;
            this.e = map;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("PlanDataManager", "onFailure errorCode =", Integer.valueOf(i), " errorInfo =", str);
            this.c.countDown();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final List<SleepAudioSeries> list) {
            LogUtil.a("PlanDataManager", "onSuccess data = ", list.toString());
            ThreadPoolManager d = ThreadPoolManager.d();
            final Map map = this.e;
            final CountDownLatch countDownLatch = this.c;
            d.execute(new Runnable() { // from class: sbx
                @Override // java.lang.Runnable
                public final void run() {
                    sbg.AnonymousClass1.this.b(list, map, countDownLatch);
                }
            });
        }

        /* synthetic */ void b(List list, Map map, CountDownLatch countDownLatch) {
            sbg.this.e((List<SleepAudioSeries>) list, (Map<Integer, List<sch>>) map, countDownLatch);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<SleepAudioSeries> list, Map<Integer, List<sch>> map, CountDownLatch countDownLatch) {
        if (koq.b(list)) {
            LogUtil.h("PlanDataManager", "handleSuccessData data is empty ");
            countDownLatch.countDown();
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        CountDownLatch countDownLatch2 = new CountDownLatch(list.size());
        HashMap hashMap = new HashMap(list.size());
        String str = "";
        for (final SleepAudioSeries sleepAudioSeries : list) {
            if (sleepAudioSeries == null) {
                countDownLatch2.countDown();
            } else {
                sch schVar = new sch();
                schVar.a(sleepAudioSeries.getName());
                schVar.e(sleepAudioSeries.getSmallImage());
                schVar.dVw_(new View.OnClickListener() { // from class: sbq
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        sbg.this.dVc_(sleepAudioSeries, view);
                    }
                });
                int infoType = sleepAudioSeries.getInfoType();
                LogUtil.a("PlanDataManager", "getHealthCourseProgress type = ", Integer.valueOf(infoType));
                if (infoType == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(TextUtils.isEmpty(str) ? Integer.toString(sleepAudioSeries.getId()) : "," + sleepAudioSeries.getId());
                    str = sb.toString();
                    hashMap.put(Integer.valueOf(sleepAudioSeries.getId()), schVar);
                    int enableNewUrl = sleepAudioSeries.getEnableNewUrl();
                    LogUtil.a("PlanDataManager", "sleepAudioSeries isEnableNewUrl ", Integer.valueOf(enableNewUrl));
                    if (enableNewUrl == 1) {
                        schVar.dVw_(new View.OnClickListener() { // from class: sbu
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                sbg.this.dVd_(sleepAudioSeries, view);
                            }
                        });
                    } else {
                        schVar.dVw_(new View.OnClickListener() { // from class: sbw
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                sbg.this.dVe_(sleepAudioSeries, view);
                            }
                        });
                    }
                } else {
                    ArrayList arrayList2 = new ArrayList(10);
                    arrayList2.add(d(schVar, sleepAudioSeries));
                    schVar.c(arrayList2);
                    schVar.dVw_(new View.OnClickListener() { // from class: sbl
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            sbg.this.dVb_(sleepAudioSeries, view);
                        }
                    });
                    countDownLatch2.countDown();
                }
                arrayList.add(schVar);
            }
        }
        a(str, hashMap, countDownLatch2);
        try {
            LogUtil.a("PlanDataManager", "handleSuccessData isReached = ", Boolean.valueOf(countDownLatch2.await(3L, TimeUnit.SECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("PlanDataManager", "thread interrupted");
        }
        map.put(2, arrayList);
        countDownLatch.countDown();
    }

    /* synthetic */ void dVc_(SleepAudioSeries sleepAudioSeries, View view) {
        c(sleepAudioSeries);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dVd_(SleepAudioSeries sleepAudioSeries, View view) {
        e(String.valueOf(sleepAudioSeries.getId()), 2);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dVe_(SleepAudioSeries sleepAudioSeries, View view) {
        d(sleepAudioSeries);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dVb_(SleepAudioSeries sleepAudioSeries, View view) {
        c(sleepAudioSeries);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(String str, int i) {
        LogUtil.a("PlanDataManager", "gotoSeriesCourseH5 seriesCourseId ", str, " type ", Integer.valueOf(i));
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("mediaCenter", bzs.e().getCommonJsModule("mediaCenter")).enableOnPauseCallback().setImmerse().showStatusBar().setStatusBarTextBlack(true).addPath("#/seriesCourse?id=" + str + "&type=" + i).setForceDarkMode(1).build();
        SeriesCourseH5Repository.registerService();
        H5ProClient.startH5MiniProgram(BaseApplication.e(), "com.huawei.health.h5.sleeping-music", build);
        ary.a().e("COLLECTION_ADD");
    }

    private void d(SleepAudioSeries sleepAudioSeries) {
        d(2, 2);
        Intent intent = new Intent();
        intent.setClassName(this.b, "com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity");
        intent.addFlags(268435456);
        intent.putExtra("id", Integer.toString(sleepAudioSeries.getId()));
        intent.putExtra("from", 1);
        gnm.aPB_(this.b, intent);
    }

    private void a(String str, Map<Integer, sch> map, CountDownLatch countDownLatch) {
        if (map == null || map.size() == 0) {
            LogUtil.h("PlanDataManager", "healthMap is empty");
        } else if (TextUtils.isEmpty(str)) {
            LogUtil.h("PlanDataManager", "no course need get percent");
            a(map, countDownLatch);
        } else {
            ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).queryAudiosPackageBySeriesId(str, new AnonymousClass3(map, countDownLatch));
        }
    }

    /* renamed from: sbg$3, reason: invalid class name */
    class AnonymousClass3 extends UiCallback<List<SleepAudioPackage>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ CountDownLatch f17005a;
        final /* synthetic */ Map b;

        AnonymousClass3(Map map, CountDownLatch countDownLatch) {
            this.b = map;
            this.f17005a = countDownLatch;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("PlanDataManager", "errcode = ", Integer.valueOf(i), ", errorInfo = ", str);
            sbg.this.a((Map<Integer, sch>) this.b, this.f17005a);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final List<SleepAudioPackage> list) {
            if (koq.b(list)) {
                LogUtil.h("PlanDataManager", "onSuccess data is null");
                sbg.this.a((Map<Integer, sch>) this.b, this.f17005a);
            } else {
                ThreadPoolManager d = ThreadPoolManager.d();
                final Map map = this.b;
                final CountDownLatch countDownLatch = this.f17005a;
                d.execute(new Runnable() { // from class: sby
                    @Override // java.lang.Runnable
                    public final void run() {
                        sbg.AnonymousClass3.this.e(list, map, countDownLatch);
                    }
                });
            }
        }

        /* synthetic */ void e(List list, Map map, CountDownLatch countDownLatch) {
            sbg.this.d((List<SleepAudioPackage>) list, (Map<Integer, sch>) map, countDownLatch);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<SleepAudioPackage> list, Map<Integer, sch> map, CountDownLatch countDownLatch) {
        LogUtil.a("PlanDataManager", "dealDataWhenSuccess:");
        for (SleepAudioPackage sleepAudioPackage : list) {
            if (sleepAudioPackage.getSleepAudioSeries() == null) {
                LogUtil.h("PlanDataManager", "sleepAudioSeries is null");
            } else {
                sch schVar = map.get(Integer.valueOf(sleepAudioPackage.getSleepAudioSeries().getId()));
                if (schVar == null) {
                    LogUtil.h("PlanDataManager", "dealDataWhenSuccess no course need get percent");
                    return;
                }
                int displayStyle = sleepAudioPackage.getSleepAudioSeries().getDisplayStyle();
                if (displayStyle == 1) {
                    if (koq.b(sleepAudioPackage.getSleepAudioInfoList())) {
                        LogUtil.h("PlanDataManager", "sleepAudioInfoList is null");
                    } else {
                        a(sleepAudioPackage, c(sleepAudioPackage.getSleepAudioInfoList(), schVar), schVar);
                    }
                } else if (displayStyle != 2) {
                    LogUtil.h("PlanDataManager", "wrong style:", Integer.valueOf(displayStyle));
                } else if (koq.b(sleepAudioPackage.getSleepAudioGroupList())) {
                    LogUtil.h("PlanDataManager", "sleepAudioGroupList is null");
                } else {
                    a(sleepAudioPackage, d(sleepAudioPackage.getSleepAudioGroupList(), schVar), schVar);
                }
            }
        }
        a(map, countDownLatch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<Integer, sch> map, CountDownLatch countDownLatch) {
        for (int size = map.size(); size > 0; size--) {
            countDownLatch.countDown();
        }
    }

    private String c(List<SleepAudioInfo> list, sch schVar) {
        long duration;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (SleepAudioInfo sleepAudioInfo : list) {
            j3 += sleepAudioInfo.getAudioDuration();
            if (sleepAudioInfo.getPlayRecord() != null) {
                j = Math.max(j, sleepAudioInfo.getPlayRecord().getModifyTime());
                if (sleepAudioInfo.getPlayRecord().getCompleteCount() > 0) {
                    duration = sleepAudioInfo.getAudioDuration();
                } else {
                    duration = sleepAudioInfo.getPlayRecord().getDuration();
                }
                j2 += duration;
            }
        }
        schVar.e(j);
        return ffv.e(j2, j3, true);
    }

    private String d(List<SleepAudioGroup> list, sch schVar) {
        long duration;
        Iterator<SleepAudioGroup> it = list.iterator();
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        while (it.hasNext()) {
            List<SleepAudioInfo> sleepAudioInfoList = it.next().getSleepAudioInfoList();
            if (koq.b(sleepAudioInfoList)) {
                LogUtil.b("PlanDataManager", "sleepAudioInfoList is null");
            } else {
                for (SleepAudioInfo sleepAudioInfo : sleepAudioInfoList) {
                    j3 += sleepAudioInfo.getAudioDuration();
                    if (sleepAudioInfo.getPlayRecord() != null) {
                        j = Math.max(j, sleepAudioInfo.getPlayRecord().getModifyTime());
                        if (sleepAudioInfo.getPlayRecord().getCompleteCount() > 0) {
                            duration = sleepAudioInfo.getAudioDuration();
                        } else {
                            duration = sleepAudioInfo.getPlayRecord().getDuration();
                        }
                        j2 += duration;
                    }
                }
            }
        }
        schVar.e(j);
        return ffv.e(j2, j3, true);
    }

    private void a(SleepAudioPackage sleepAudioPackage, String str, sch schVar) {
        String string = this.b.getResources().getString(R$string.IDS_sleep_decompression_not_played);
        ArrayList arrayList = new ArrayList(10);
        if (str.equals(string)) {
            LogUtil.a("PlanDataManager", "AudioDescription = ", sleepAudioPackage.getSleepAudioSeries().getAudioDescription());
            arrayList.add(sleepAudioPackage.getSleepAudioSeries().getAudioDescription());
        } else {
            arrayList.add(str);
            LogUtil.a("PlanDataManager", "setCourseTrainedText courseTrainedText = ", str);
        }
        schVar.c(arrayList);
    }

    private void c(SleepAudioSeries sleepAudioSeries) {
        if (nsn.o()) {
            LogUtil.a("PlanDataManager", "onClick, isFastClick");
            return;
        }
        int id = sleepAudioSeries.getId();
        String str = "com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&statusBarTextBlack&isImmerse&id=" + id;
        if (sleepAudioSeries.getAudioType() != 0) {
            str = "com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&statusBarTextBlack&isImmerse&id=" + id + "&type=sleepAudio";
        }
        if (sleepAudioSeries.getDecompressType() != 0) {
            str = "com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&statusBarTextBlack&isImmerse&id=" + id + "&type=decompressAudio";
        }
        LogUtil.a("PlanDataManager", "gotoAudioCourseH5 url = ", str);
        d(2, 2);
        bzs.e().startOperationWebPage(this.b, str);
    }

    private String d(sch schVar, SleepAudioSeries sleepAudioSeries) {
        if (sleepAudioSeries.getPlayRecord() == null) {
            return this.b.getString(R$string.IDS_fitness_advice_run_trained_never);
        }
        long modifyTime = sleepAudioSeries.getPlayRecord().getModifyTime();
        schVar.e(modifyTime);
        int b = eil.b(modifyTime, System.currentTimeMillis());
        if (b == 0) {
            return this.b.getString(R$string.IDS_fitness_advice_run_trained_today);
        }
        return this.b.getResources().getQuantityString(R.plurals._2130903356_res_0x7f03013c, b, Integer.valueOf(b));
    }

    public static void d(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        if (i == 0) {
            hashMap.put("default", Integer.valueOf(i2));
        } else {
            hashMap.put("tabType", Integer.valueOf(i2));
        }
        ixx.d().d(BaseApplication.e(), f17004a, hashMap, 0);
    }
}
