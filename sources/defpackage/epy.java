package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.cloud.CloudApi;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.plan.model.intplan.LeavePlanCalendarBean;
import com.huawei.health.plan.model.intplan.ReplacePlanBean;
import com.huawei.health.plan.model.intplan.UserProfileBean;
import com.huawei.health.plan.model.model.BestRecordFitStat;
import com.huawei.health.plan.model.model.CreatePlanBean;
import com.huawei.health.plan.model.model.FinishPlanBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import defpackage.eqc;
import defpackage.eqd;
import defpackage.eqe;
import defpackage.eqf;
import defpackage.eqg;
import defpackage.eqh;
import defpackage.eqi;
import defpackage.eqj;
import defpackage.eqk;
import defpackage.eql;
import defpackage.eqm;
import defpackage.eqn;
import defpackage.eqo;
import defpackage.eqp;
import defpackage.eqq;
import defpackage.eqr;
import defpackage.eqs;
import defpackage.eqt;
import defpackage.equ;
import defpackage.eqv;
import defpackage.eqw;
import defpackage.eqy;
import defpackage.eqz;
import defpackage.erb;
import defpackage.erd;
import defpackage.ere;
import defpackage.erf;
import defpackage.erg;
import defpackage.erh;
import defpackage.eri;
import defpackage.erj;
import defpackage.erk;
import defpackage.erl;
import defpackage.erm;
import defpackage.ern;
import defpackage.ero;
import defpackage.erp;
import defpackage.erq;
import defpackage.err;
import defpackage.ers;
import defpackage.ert;
import defpackage.eru;
import defpackage.erv;
import defpackage.erw;
import defpackage.erx;
import defpackage.ery;
import defpackage.erz;
import defpackage.esa;
import defpackage.esb;
import defpackage.esc;
import defpackage.esd;
import defpackage.ese;
import defpackage.esf;
import defpackage.esg;
import defpackage.esh;
import defpackage.esi;
import defpackage.esj;
import defpackage.esk;
import defpackage.esm;
import defpackage.esn;
import defpackage.eso;
import defpackage.esp;
import defpackage.esq;
import defpackage.esr;
import defpackage.ess;
import defpackage.est;
import defpackage.esu;
import defpackage.esv;
import defpackage.esy;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class epy implements CloudApi {
    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutList(WorkoutListBean workoutListBean, DataCallback dataCallback) {
        erp.e c = new erp.e().e(Integer.valueOf(workoutListBean.getPageStart())).c(Integer.valueOf(workoutListBean.getPageSize())).c(etd.a());
        int supportWear = workoutListBean.getSupportWear();
        if (supportWear == 0 || supportWear == 1) {
            c.h(Integer.valueOf(supportWear));
        }
        Integer primaryClassifyId = workoutListBean.getPrimaryClassifyId();
        if (primaryClassifyId != null && primaryClassifyId.intValue() > 0) {
            c.f(primaryClassifyId);
        }
        Integer secondClassifyId = workoutListBean.getSecondClassifyId();
        if (secondClassifyId != null && secondClassifyId.intValue() > 0) {
            c.g(secondClassifyId);
        }
        Integer userDefinedType = workoutListBean.getUserDefinedType();
        if (userDefinedType != null) {
            c.j(userDefinedType);
        }
        Integer[] classList = workoutListBean.getClassList();
        if (classList != null && classList.length > 0) {
            c.a(Arrays.asList(classList));
        }
        Integer[] secondClassifyList = workoutListBean.getSecondClassifyList();
        if (secondClassifyList != null && secondClassifyList.length > 0) {
            c.e(Arrays.asList(secondClassifyList));
        }
        if (ggg.a() == 0) {
            c.b((Integer) 1);
        } else {
            c.b((Integer) 0);
        }
        Integer[] trainingPoints = workoutListBean.getTrainingPoints();
        if (trainingPoints != null && trainingPoints.length > 0) {
            c.d(Arrays.asList(trainingPoints));
        }
        d(workoutListBean, c);
        c.e(workoutListBean.getSearchCondition());
        etc.a().d(c.d(), workoutListBean.getUseCache(), dataCallback);
    }

    private void d(WorkoutListBean workoutListBean, erp.e eVar) {
        Integer[] difficulty = workoutListBean.getDifficulty();
        if (difficulty != null && difficulty.length > 0) {
            eVar.b(Arrays.asList(difficulty));
        }
        Integer[] equipments = workoutListBean.getEquipments();
        if (equipments != null && equipments.length > 0) {
            eVar.c(Arrays.asList(equipments));
        }
        int my = workoutListBean.getMy();
        if (my == 1) {
            eVar.a(Integer.valueOf(my));
        }
        Integer workoutRank = workoutListBean.getWorkoutRank();
        if (workoutRank != null && workoutRank.intValue() != 0) {
            eVar.i(workoutRank);
        }
        Integer[] workoutType = workoutListBean.getWorkoutType();
        if (workoutType != null && workoutType.length > 0) {
            eVar.i(Arrays.asList(workoutType));
        }
        if (workoutListBean.getCommodityFlag() != -1) {
            eVar.d(Integer.valueOf(workoutListBean.getCommodityFlag()));
        }
        String replacedWorkoutId = workoutListBean.getReplacedWorkoutId();
        if (TextUtils.isEmpty(replacedWorkoutId)) {
            return;
        }
        eVar.d(replacedWorkoutId);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void searchWorkoutList(int i, int i2, String str, List<Integer> list, DataCallback dataCallback) {
        erp.e c = new erp.e().e(Integer.valueOf(i)).c(Integer.valueOf(i2));
        if (koq.c(list)) {
            c.i(list);
        }
        c.b(str);
        etc.a().c(c.d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getNavigationCourseList(WorkoutListBean workoutListBean, DataCallback dataCallback) {
        erf.d d = new erf.d().c((workoutListBean.getPageStart() / workoutListBean.getPageSize()) + 1).e(workoutListBean.getPageSize()).a(workoutListBean.getCourseType()).d(workoutListBean.getSearchCondition());
        Integer workoutRank = workoutListBean.getWorkoutRank();
        if (workoutRank != null && workoutRank.intValue() != 0) {
            d.b(workoutRank);
        }
        etc.a().c(d.b(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutInfo(ffl fflVar, DataCallback dataCallback) {
        erm.c d = new erm.c().d(fflVar.h()).e(fflVar.e()).b(fflVar.d()).d(Integer.valueOf(fflVar.f()));
        if (!TextUtils.isEmpty(fflVar.g())) {
            d.c(fflVar.g());
        }
        etc.a().d(d.a(), fflVar.c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutsInfo(List<ffl> list, boolean z, DataCallback dataCallback) {
        etc.a().d(new erq.d().e(list).d(Boolean.valueOf(z)).a(), false, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void createPlan(CreatePlanBean createPlanBean, DataCallback dataCallback) {
        int goal = createPlanBean.getGoal();
        int difficulty = createPlanBean.getDifficulty();
        int movementTimes = createPlanBean.getMovementTimes();
        String excludedDate = createPlanBean.getExcludedDate();
        etc.a().c(new eqg.d().e(Long.valueOf(ghz.d(createPlanBean.getBeginDate()))).c(Integer.valueOf(goal)).d(Integer.valueOf(difficulty)).e(Integer.valueOf(movementTimes)).a(excludedDate).e(Float.valueOf(createPlanBean.getWeight())).c(), dataCallback);
        if (goal == 1) {
            gge.b(difficulty, movementTimes, excludedDate);
        } else {
            gge.a(difficulty, movementTimes, excludedDate);
        }
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postRunPlan(Plan plan, DataCallback dataCallback) {
        ery.d a2 = new ery.d().b(plan.acquireId()).a(plan.acquireName()).i(Integer.valueOf(plan.acquireType())).c(Integer.valueOf(plan.getDifficulty())).j(Integer.valueOf(plan.getWeekCount())).b(Float.valueOf(plan.getCalorie())).d(Integer.valueOf(moe.h(plan.getDistance()))).e(plan.getPicture()).d(plan.acquireVersion()).e(Long.valueOf(ghz.a(plan.acquireStartDate(), "yyyy-MM-dd"))).c(Long.valueOf(ghz.a(plan.getEndDate(), "yyyy-MM-dd"))).c(ffm.a(plan.acquireExcludedDates())).e(Integer.valueOf(plan.acquireWeekTimes())).b(Integer.valueOf(plan.acquireGoal())).a(Integer.valueOf(plan.getRemindTime()));
        ArrayList arrayList = new ArrayList();
        int i = -1;
        ArrayList arrayList2 = null;
        for (PlanWorkout planWorkout : plan.acquireWorkouts()) {
            int acquireOrder = planWorkout.popWeekInfo().acquireOrder();
            if (i != acquireOrder) {
                ArrayList arrayList3 = new ArrayList();
                arrayList.add(new ery.c.d().c(Integer.valueOf(acquireOrder)).d(planWorkout.popWeekInfo().getStage() + "").e(planWorkout.popWeekInfo().getSentence()).b(planWorkout.popWeekInfo().getWeekName()).e(arrayList3).c());
                arrayList2 = arrayList3;
                i = acquireOrder;
            }
            if (arrayList2 != null) {
                arrayList2.add(new ery.e.b().a(Long.valueOf(ghz.a(planWorkout.popDayInfo().acquireDate(), "yyyy-MM-dd"))).d(planWorkout.popWorkoutId()).a(planWorkout.popName()).e(planWorkout.popDescription()).b(Integer.valueOf(planWorkout.popDayInfo().getSinglesCount())).b(planWorkout.popExtendParams()).c(planWorkout.popVersion()).d(Integer.valueOf(planWorkout.popWeekInfo().getDuration())).e(Integer.valueOf(planWorkout.popWeekInfo().getDuration() == 0 ? 0 : 1)).c());
            }
        }
        a2.c(arrayList);
        etc.a().c(a2.d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void finishExercise(int i, WorkoutRecord workoutRecord, DataCallback dataCallback) {
        if (!b()) {
            ReleaseLogUtil.d("Suggestion_CloudImpl", "finishExercise failed since switch-off");
            dataCallback.onFailure(ResultUtil.ResultCode.PRIVACY_POST_OFF, "post switch-off");
            return;
        }
        if (TextUtils.isEmpty(workoutRecord.acquireVersion())) {
            workoutRecord.saveVersion("1.0");
        }
        eqo.d a2 = a(i, workoutRecord);
        List<HeartRateData> acquireHeartRateDataList = workoutRecord.acquireHeartRateDataList();
        if (koq.c(acquireHeartRateDataList)) {
            ArrayList arrayList = new ArrayList();
            for (HeartRateData heartRateData : acquireHeartRateDataList) {
                arrayList.add(new eqo.c.C0303c().d(Integer.valueOf(heartRateData.acquireHeartRate())).a(Long.valueOf(heartRateData.acquireTime())).c());
            }
            a2.e(arrayList);
        }
        List<HeartRateData> acquireInvalidHeartRateList = workoutRecord.acquireInvalidHeartRateList();
        if (koq.c(acquireInvalidHeartRateList)) {
            ArrayList arrayList2 = new ArrayList();
            for (HeartRateData heartRateData2 : acquireInvalidHeartRateList) {
                arrayList2.add(new eqo.c.C0303c().d(Integer.valueOf(heartRateData2.acquireHeartRate())).a(Long.valueOf(heartRateData2.acquireTime())).c());
            }
            a2.a(arrayList2);
        }
        etc.a().c(a2.a(), dataCallback);
    }

    private eqo.d a(int i, WorkoutRecord workoutRecord) {
        return new eqo.d().g(Integer.valueOf(workoutRecord.acquireId())).a(Integer.valueOf(i)).d(workoutRecord.acquirePlanId()).o(Integer.valueOf(workoutRecord.acquireWeekNum())).b(Long.valueOf(ghz.a(workoutRecord.acquireWorkoutDate(), "yyyy-MM-dd"))).g(workoutRecord.acquireWorkoutId()).e(workoutRecord.acquireWorkoutName()).d(Integer.valueOf(workoutRecord.acquireWorkoutOrder())).e(Integer.valueOf(moe.j(workoutRecord.acquireDistance()))).e(Float.valueOf(workoutRecord.acquireCalorie())).b(Integer.valueOf(workoutRecord.acquireActualDistance() > 0.0f ? moe.j(workoutRecord.acquireActualDistance()) : moe.j(workoutRecord.acquireDistance()))).c(Float.valueOf(workoutRecord.acquireActualCalorie())).d(Float.valueOf(moe.a(workoutRecord.acquireFinishRate()))).f(Integer.valueOf(workoutRecord.getDuration())).h(Integer.valueOf(workoutRecord.acquireUpperHeartRate())).i(Integer.valueOf(workoutRecord.acquireLowerHeartRate())).c(Integer.valueOf(workoutRecord.acquireBestPace())).c(Long.valueOf(ghz.d(workoutRecord.acquireExerciseTime()))).b(Double.valueOf(workoutRecord.acquireOxygen())).m(Integer.valueOf(workoutRecord.acquireTrainingLoadPeak())).a(workoutRecord.acquireTrajectoryId()).c(workoutRecord.acquireActionSummary()).f(workoutRecord.acquireVersion()).n(Integer.valueOf(workoutRecord.acquireWearType())).e(Long.valueOf(workoutRecord.getTotalScore())).j(Integer.valueOf(workoutRecord.getRecordModeType())).b(workoutRecord.acquireExtend());
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getPlanProgress(String str, DataCallback dataCallback) {
        etc.a().c(new eri.e().c(str).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void finishPlan(FinishPlanBean finishPlanBean, DataCallback dataCallback) {
        LogUtil.a("Suggestion_CloudImpl", "actualDistance:", Integer.valueOf(moe.h(finishPlanBean.getActualDistance())), "actualDuration:", Long.valueOf((long) finishPlanBean.getActualDuration()), "actualCalorie:", Float.valueOf(finishPlanBean.getActualCalorie()));
        etc.a().c(new eqn.b().b(finishPlanBean.getPlanId()).e(Float.valueOf(moe.a(finishPlanBean.getCompletionRate()))).a(Integer.valueOf(moe.h(finishPlanBean.getActualDistance()))).d(Float.valueOf(finishPlanBean.getActualCalorie())).c(Long.valueOf((long) finishPlanBean.getActualDuration())).b(Long.valueOf(ghz.d(finishPlanBean.getFinishTime()))).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFinishedPlans(int i, int i2, DataCallback dataCallback) {
        etc.a().c(new eru.e().d(Integer.valueOf(i)).c(Integer.valueOf(i2)).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postBehavior(WorkoutRecord workoutRecord, int i, DataCallback dataCallback) {
        etc.a().c(new erx.a().c(workoutRecord.acquireWorkoutId()).a(workoutRecord.acquireVersion()).e(workoutRecord.acquireWorkoutPackageId()).c(Integer.valueOf(workoutRecord.acquireCourseBelongType())).b(Integer.valueOf(i)).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void checkAllowCreateOldPlan(DataCallback dataCallback) {
        etc.a().b((Object) null, eta.e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getCurrentPlan(DataCallback dataCallback) {
        etc.a().a(null, eta.ag(), false, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updatePlanName(String str, String str2, DataCallback dataCallback) {
        etc.a().c(new err.d().e(str).a(str2).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postPlanRemind(String str, int i, DataCallback dataCallback) {
        etc.a().c(new erv.a().c(str).c(Integer.valueOf(i)).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postBestRecord(String str, PlanStat planStat, DataCallback dataCallback) {
        if (!b()) {
            ReleaseLogUtil.d("Suggestion_CloudImpl", "postBestRecord failed since switch-off");
            return;
        }
        erw.c d = new erw.c().d(str);
        erw.e d2 = new erw.e.c().e(Integer.valueOf(planStat.getBestRecordForFirstOneKm())).j(Integer.valueOf(moe.j(planStat.getFarthestRunning()))).h(Integer.valueOf(planStat.getLongestRunning())).b(Integer.valueOf(planStat.getBestRecordForOneKm())).a(Integer.valueOf(planStat.getBestRecordForFiveKm())).f(Integer.valueOf(planStat.getBestRecordForTenKm())).d(Integer.valueOf(planStat.getBestRecordForHalfMarathon())).c(Integer.valueOf(planStat.getBestRecordForMarathon())).i(Integer.valueOf(planStat.getLongestTimePerWeek())).a(Float.valueOf(planStat.getMostCaloriePerWeek())).g(Integer.valueOf(planStat.getMostWorkoutTimes())).c(planStat.getMostWorkoutName()).e(Float.valueOf(planStat.getHighestCompleteRate())).d();
        d.d(d2).c(new erw.d.b().e(Integer.valueOf(planStat.getBestRecordForAllFiveKm())).b(Integer.valueOf(planStat.getBestRecordForAllTenKm())).a(Integer.valueOf(planStat.getBestRecordForAllHalfMarathon())).d(Integer.valueOf(planStat.getBestRecordForAllMarathon())).d());
        etc.a().c(d.a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postTrainBestRecords(BestRecordFitStat bestRecordFitStat, DataCallback dataCallback) {
        if (!b()) {
            ReleaseLogUtil.d("Suggestion_CloudImpl", "postTrainBestRecords failed since switch-off");
        } else {
            etc.a().c(new ese.b().a(bestRecordFitStat.acquireBestRecords()).d(bestRecordFitStat.acquireId()).a(Long.valueOf(bestRecordFitStat.acquireCompleteTime())).b(), dataCallback);
        }
    }

    private boolean b() {
        return "true".equals(gmz.d().c(3));
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getUserBestRecords(DataCallback dataCallback) {
        etc.a().b((Object) null, eta.al(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTrainCountByWorkoutId(String str, DataCallback dataCallback) {
        etc.a().d(new esh.e().a(str).b(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTopicList(int i, DataCallback dataCallback) {
        queryTopicList(i, etd.a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTopicList(int i, String str, DataCallback dataCallback) {
        queryTopicList(0, i, str, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTopicList(int i, int i2, DataCallback dataCallback) {
        queryTopicList(i, i2, etd.a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTopicList(int i, int i2, String str, DataCallback dataCallback) {
        etc.a().d(new esj.b().a(Integer.valueOf(i)).c(Integer.valueOf(i2)).a(str).d(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getMultiLanguage(long j, String str, DataCallback dataCallback) {
        ere.e d = new ere.e().d(Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            d.c(str);
        }
        etc.a().c(d.e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFitnessPkgInfo(int i, int i2, int i3, DataCallback dataCallback) {
        eqy.c b = new eqy.c().d((Integer) 0).a(50).b(Integer.valueOf(i3));
        if (eve.b) {
            b.e((Integer) 1);
        }
        etc.a().d(b.c(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void createFitnessPackagePlan(UserFitnessPlanInfo userFitnessPlanInfo, DataCallback dataCallback) {
        etc.a().c(new eqg.d().a(userFitnessPlanInfo).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updateFitnessPackagePlan(UserFitnessPlanInfo userFitnessPlanInfo, DataCallback dataCallback) {
        etc.a().c(new esn.a().b(userFitnessPlanInfo).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryFitnessPlanSummary(DataCallback dataCallback) {
        etc.a().a(null, eta.bk(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryFitnessPkgPlan(String str, DataCallback dataCallback) {
        etc.a().d(new esb.a().c(str).c(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryMyDoingFitnessPlan(DataCallback dataCallback) {
        etc.a().a(null, eta.bl(), false, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryOperationPage(int i, DataCallback dataCallback) {
        etc.a().c(new esd.d().d(Integer.valueOf(i)).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkOutsByTopicId(int i, int i2, DataCallback dataCallback) {
        getWorkOutsByTopicId(i, i2, etd.a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkOutsByTopicId(int i, int i2, String str, DataCallback dataCallback) {
        etc.a().d(new ero.c().d(Integer.valueOf(i)).e(Integer.valueOf(i2)).a(str).b(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutsByType(int i, int i2, DataCallback dataCallback) {
        getWorkoutsByType(i, i2, etd.a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutsByType(int i, int i2, String str, DataCallback dataCallback) {
        etc.a().d(new ert.b().e(Integer.valueOf(i)).b(Integer.valueOf(i2)).a(str).c(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void setEvent(float f, int i, long j, DataCallback dataCallback) {
        if (!b()) {
            ReleaseLogUtil.d("Suggestion_CloudImpl", "setEvent failed since switch-off");
            dataCallback.onFailure(ResultUtil.ResultCode.PRIVACY_POST_OFF, "post switch-off");
        } else {
            etc.a().c(new esk.c().a("EXERCISE_FINISH").c(MedalConstants.COMPLEX).e(Long.valueOf(j)).e(new esk.e.b().e(Float.valueOf(f)).e(Integer.valueOf(i)).c()).d(), dataCallback);
        }
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postDeleteUserWorkout(List<Workout> list, DataCallback dataCallback) {
        ArrayList arrayList = new ArrayList();
        for (Workout workout : list) {
            arrayList.add(new eqm.d.c().c(workout.acquireId()).d(workout.accquireVersion()).b(Integer.valueOf(workout.getCourseBelongType())).b());
        }
        etc.a().c(new eqm.c().c(arrayList).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTrainStatistics(int i, DataCallback dataCallback) {
        etc.a().c(new esf.b().a(Integer.valueOf(i)).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryPlanStatistics(int i, DataCallback dataCallback) {
        etc.a().c(new esa.e().d(Integer.valueOf(i)).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void collectBehavior(String str, DataCallback dataCallback) {
        etc.a().c(new eqe.d().d(str).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void collectBehavior(String str, int i, DataCallback dataCallback) {
        etc.a().c(new eqe.d().d(str).e(i).c(str).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void deleteBehavior(String str, int i, DataCallback dataCallback) {
        etc.a().c(new eql.a().c(str).a((Integer) 2).c(Integer.valueOf(i)).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getBehaviorList(int i, int i2, int i3, DataCallback dataCallback) {
        etc.a().c(new equ.c().c(Integer.valueOf(i)).a(Integer.valueOf(i2)).e(Integer.valueOf(i3)).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void deleteUserWorkoutRecords(int i, int i2, DataCallback dataCallback) {
        etc.a().c(new eqk.b().d(Integer.valueOf(i)).e(Integer.valueOf(i2)).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void downloadFile(String str, String str2, DataCallback dataCallback) {
        downloadFile(str, str2, dataCallback, false);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void downloadFile(String str, String str2, DataCallback dataCallback, boolean z) {
        File file = new File(str2);
        if (!file.exists() || z) {
            eqb.c(str, str2, dataCallback);
            return;
        }
        dataCallback.onSuccess(new JSONObject());
        if (file.isDirectory()) {
            LogUtil.h("Suggestion_CloudImpl", "downloadFile as directory, url=", str, ", path=", str2, ", override=", Boolean.valueOf(z));
        }
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void cancelDownloadFile(String str) {
        lqi.d().d(str);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getTrainActions(List<String> list, int i, DataCallback dataCallback) {
        etc.a().d(new eqq.a().b(list).e(i).e(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTrainCount(DataCallback dataCallback) {
        etc.a().b((Object) null, eta.bp(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutFilters(List<Integer> list, DataCallback dataCallback) {
        erj.e c = new erj.e().c(etd.a());
        if (koq.c(list)) {
            c.a(list);
        }
        etc.a().d(c.a(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getAggregatePageByType(int i, DataCallback dataCallback) {
        etc.a().d(new erb.e().d(i).d(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutList(int i, int i2, int i3, int i4, DataCallback dataCallback) {
        erp.e c = new erp.e().e(Integer.valueOf(i)).c(Integer.valueOf(i2)).j(Integer.valueOf(i4)).c(etd.a());
        if (i3 != 0) {
            c.g(Integer.valueOf(i3));
        }
        etc.a().d(c.d(), true, dataCallback);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getBehaviorList(int i, int i2, int i3, String str, DataCallback dataCallback) {
        char c;
        equ.c e = new equ.c().c(Integer.valueOf(i)).a(Integer.valueOf(i2)).e(Integer.valueOf(i3));
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList(1);
            str.hashCode();
            switch (str.hashCode()) {
                case -1114043190:
                    if (str.equals("YOGA_COURSE")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -152700479:
                    if (str.equals("WALKING_COURSE")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -35721541:
                    if (str.equals("RUNNING_COURSE")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1513983602:
                    if (str.equals("FITNESS_COURSE")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                arrayList.add(1);
                e.a(arrayList).b((Integer) 137);
            } else if (c == 1) {
                arrayList.add(1);
                e.a(arrayList).b((Integer) 257);
            } else if (c == 2) {
                arrayList.add(2);
                e.a(arrayList);
            } else if (c == 3) {
                arrayList.add(1);
                e.a(arrayList).b((Integer) 0);
            } else {
                dataCallback.onFailure(-5, ResultUtil.d(-5));
                return;
            }
        }
        etc.a().c(e.c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFitnessWorkoutRecommendList(String str, DataCallback dataCallback) {
        etc.a().d(new erg.c().d(str).c(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFitnessRecWorkoutByCourseTypeList(int i, DataCallback dataCallback) {
        etc.a().d(new erh.d().a(Integer.valueOf(i)).d(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getActionList(int i, int i2, int i3, int i4, DataCallback dataCallback) {
        etc.a().d(new eqs.a().a(Integer.valueOf(i)).d(Integer.valueOf(i2)).c(Integer.valueOf(i3)).e(Integer.valueOf(i4)).c(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void searchActionList(int i, int i2, int i3, String str, DataCallback dataCallback) {
        eqs.a c = new eqs.a().a(Integer.valueOf(i)).d(Integer.valueOf(i2)).c(str);
        if (i3 > 0) {
            c.e(Integer.valueOf(i3));
        }
        etc.a().c(c.c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFitnessActionInfo(String str, String str2, DataCallback dataCallback) {
        etc.a().d(new eqz.b().b(str).e(str2).d(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getLongVideoInfo(ffl fflVar, DataCallback dataCallback) {
        String h = fflVar.h();
        FitWorkout a2 = ett.i().l().a(h);
        etc.a().c(new ern.b().c(h).d(a2 == null ? "" : a2.accquireVersion()).e(fflVar.e()).b(Integer.valueOf(fflVar.b())).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getRunRecommendCourse(DataCallback dataCallback) {
        etc.a().a(null, eta.ak(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryTrainList(long j, long j2, int i, DataCallback dataCallback) {
        etc.a().c(new esg.b().e(Long.valueOf(j)).d(Long.valueOf(j2)).d(Integer.valueOf(i)).c((Integer) 1).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getAllPlans(int i, DataCallback dataCallback) {
        etc.a().d(new eqt.e().c(Integer.valueOf(i)).b(etd.a()).d(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getAchievementForecast(int i, int i2, int i3, int i4, DataCallback dataCallback) {
        etc.a().c(new eqc.b().a(Integer.valueOf(i)).b(Integer.valueOf(i2)).d(Integer.valueOf(i3)).e(Integer.valueOf(i4)).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void createPlan(CreateRunPlanParams createRunPlanParams, DataCallback dataCallback) {
        createRunPlanParams.getPlanInfoBean().setLanguage(etd.a());
        etc.a().b(createRunPlanParams, eta.j(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postFeedback(mof mofVar, DataCallback dataCallback) {
        etc.a().b(mofVar, eta.bg(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getTrainingReport(String str, DataCallback dataCallback) {
        etc.a().c(new erk.d().d(str).a(etd.a()).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getCoachAdvice(String str, int i, DataCallback dataCallback) {
        etc.a().c(new eqr.e().b(str).e(Integer.valueOf(i)).d(Boolean.valueOf(CommonUtil.as())).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getCurrentReportIndex(DataCallback dataCallback) {
        etc.a().b((Object) null, eta.y(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updatePlan(UserInfoBean userInfoBean, int i, DataCallback dataCallback) {
        etc.a().c(new esu.c().d(userInfoBean).e(Integer.valueOf(i)).d(Boolean.valueOf(CommonUtil.as())).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updatePlanDate(String str, List<Integer> list, List<Integer> list2, DataCallback dataCallback) {
        etc.a().c(new ess.e().b(str).d(list).b(list2).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updatePlanReport(String str, String str2, DataCallback dataCallback) {
        etc.a().c(new esp.e().e(str).a(str2).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updateClockTimes(int i, String str, int i2, int i3, String str2, DataCallback dataCallback) {
        etc.a().c(new esy.a().d(Integer.valueOf(i)).d(str).e(Integer.valueOf(i2)).a(Integer.valueOf(i3)).a(str2).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getDoingIntPlan(DataCallback dataCallback) {
        etc.a().a(null, eta.v(), false, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void createIntPlan(IntPlanBean intPlanBean, DataCallback dataCallback) {
        etc.a().c(new eqf.e().e(intPlanBean).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updateAction(moc mocVar, DataCallback dataCallback) {
        etc.a().b(mocVar, eta.ax(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void stopIntPlan(esl eslVar, DataCallback dataCallback) {
        etc.a().c(eslVar, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updateIntPlan(UserProfileBean userProfileBean, String str, int i, DataCallback dataCallback) {
        etc.a().c(new esq.e().b(userProfileBean).e(str).e(i).a(0).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updateIntPlanReport(String str, int i, int i2, String str2, long j, DataCallback dataCallback) {
        etc.a().c(new est.a().d(str).a(i).b(i2).e(str2).c(j).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getIntPlanReport(String str, int i, int i2, DataCallback dataCallback) {
        etc.a().c(new erd.a().d(str).c(i).b(i2).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updatePlanInfo(String str, int i, String str2, String str3, DataCallback dataCallback) {
        etc.a().c(new esm.b().d(str).a(i).a(str2).b(str3).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void addUserDefinedWorkout(String str, String str2, int i, List<ChoreographedMultiActions> list, DataCallback dataCallback) {
        etc.a().b(new esv.a().a(str).e(str2).b(i).d(d(list)).c(), eta.a(), dataCallback);
    }

    private List<esv.b> d(List<ChoreographedMultiActions> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (ChoreographedMultiActions choreographedMultiActions : list) {
                esv.b.e eVar = new esv.b.e();
                eVar.e(choreographedMultiActions.getRepeatTimes());
                if (choreographedMultiActions.getActionList() != null && choreographedMultiActions.getActionList().size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (ChoreographedSingleAction choreographedSingleAction : choreographedMultiActions.getActionList()) {
                        esv.c.d dVar = new esv.c.d();
                        if (choreographedSingleAction.getAction() != null) {
                            dVar.c(choreographedSingleAction.getAction().getId()).b(choreographedSingleAction.getAction().getName());
                        }
                        dVar.b(choreographedSingleAction.getTargetConfig()).d(choreographedSingleAction.getIntensityConfig());
                        arrayList2.add(dVar.a());
                    }
                    eVar.d(arrayList2);
                }
                arrayList.add(eVar.c());
            }
        }
        return arrayList;
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void modifyUserDefinedWorkout(FitWorkout fitWorkout, DataCallback dataCallback) {
        etc.a().b(new esv.a().d(fitWorkout.acquireId()).a(fitWorkout.acquireName()).e(fitWorkout.acquireDescription()).b(fitWorkout.getCourseDefineType()).d(d(fitWorkout.getCourseActions())).c(), eta.au(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void deleteUserDefinedWorkout(String str, int i, DataCallback dataCallback) {
        etc.a().c(new eqh.e().c(str).d(i).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void postIntFeedback(mof mofVar, DataCallback dataCallback) {
        etc.a().b(mofVar, eta.at(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void generateReport(int i, IntPlanBean intPlanBean, DataCallback dataCallback) {
        etc.a().c(new eqp.a().a(i).c(intPlanBean).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updateDayRecord(int i, String str, int i2, int i3, mob mobVar, DataCallback dataCallback) {
        if (mobVar.m() < 0) {
            ReleaseLogUtil.d("Suggestion_CloudImpl", "updateDayRecord() Bean TrainPoint lower than 0, reset to 0");
            mobVar.d(0);
        }
        etc.a().c(new eso.a().a(Integer.valueOf(i)).d(str).b(Integer.valueOf(i2)).e(Integer.valueOf(i3)).d(mobVar).b(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void deletePlanExerciseRecord(int i, String str, String str2, DataCallback dataCallback) {
        etc.a().c(new eqj.e().c(Integer.valueOf(i)).c(str).e(str2).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void replacePlanSchedule(ReplacePlanBean replacePlanBean, DataCallback dataCallback) {
        etc.a().c(new esi.c().b(replacePlanBean.getPlanId()).e(Integer.valueOf(replacePlanBean.getPlanType())).a(Integer.valueOf(replacePlanBean.getWeekNo())).c(Integer.valueOf(replacePlanBean.getDayNo())).a(replacePlanBean.getPropensityWorkoutId()).d(replacePlanBean.getReplacedWorkoutId()).b(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void leavePlan(LeavePlanCalendarBean leavePlanCalendarBean, DataCallback dataCallback) {
        etc.a().c(new ers.c().d(leavePlanCalendarBean.getPlanId()).e(leavePlanCalendarBean.getCategory()).b(leavePlanCalendarBean.getSubCategory()).c(leavePlanCalendarBean.getLeaveInfo()).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void cancelLeave(LeavePlanCalendarBean leavePlanCalendarBean, DataCallback dataCallback) {
        etc.a().c(new eqd.b().b(leavePlanCalendarBean.getPlanId()).a(leavePlanCalendarBean.getCategory()).e(leavePlanCalendarBean.getSubCategory()).b(leavePlanCalendarBean.getLeaveInfo()).d(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void updatePlanCalendar(LeavePlanCalendarBean leavePlanCalendarBean, DataCallback dataCallback) {
        etc.a().c(new esr.c().b(leavePlanCalendarBean.getPlanId()).c(leavePlanCalendarBean.getCategory()).d(leavePlanCalendarBean.getSubCategory()).b(leavePlanCalendarBean.getOperationType()).a(leavePlanCalendarBean.getDays()).a(leavePlanCalendarBean.getExercisePlan()).c(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFilterTab(String str, DataCallback dataCallback) {
        etc.a().c(new eqw.d().a(str).a(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void queryAudiosPackageBySeriesId(String str, DataCallback dataCallback) {
        etc.a().c(new esc.e().a(str).b(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getAudioBehaviorList(int i, int i2, int i3, int i4, DataCallback dataCallback) {
        etc.a().c(new eqv.b().e(Integer.valueOf(i)).b(Integer.valueOf(i2)).d(Integer.valueOf(i3)).c(Integer.valueOf(i4)).b(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getAudioBehaviorList(fff fffVar, DataCallback dataCallback) {
        etc.a().b(fffVar, eta.w(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void operateFavoriteAudio(int i, int i2, DataCallback dataCallback) {
        etc.a().c(new erz.e().c(Integer.valueOf(i)).b(Integer.valueOf(i2)).b(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void deletePlayRecord(List<Integer> list, DataCallback dataCallback) {
        etc.a().c(new eqi.e().c(list).e(), dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getWorkoutPackage(ffk ffkVar, DataCallback dataCallback) {
        etc.a().d(new erl.b().b(ffkVar.e()).a(ffkVar.d()).b(), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFitnessActionTemplate(String str, DataCallback dataCallback) {
        etc.a().d(new eqx(str), true, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getFitnessMaxScore(String str, int i, DataCallback dataCallback) {
        etc.a().d(new era(str, i), false, dataCallback);
    }

    @Override // com.huawei.health.plan.model.cloud.CloudApi
    public void getUserRank(erc ercVar, final DataCallback dataCallback) {
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        lqi.d().b(ercVar.getUrl(), healthDataCloudFactory.getHeaders(), lql.b(healthDataCloudFactory.getBody(ercVar)), String.class, new ResultCallback<String>() { // from class: epy.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                etc.a().b(str, dataCallback, elapsedRealtime);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                etc.a().a(th, dataCallback, elapsedRealtime);
            }
        });
    }
}
