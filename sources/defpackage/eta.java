package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class eta {
    public static String ad() {
        return b(cp() + "/getFilters");
    }

    public static String d() {
        return b(cn() + "/getAggregatePageByType");
    }

    public static String an() {
        return b(cj() + "/getWorkoutList");
    }

    public static String aj() {
        return b(cj() + "/getWorkoutInfo");
    }

    public static String ap() {
        return b(cj() + "/getWorkoutPackageInfo");
    }

    public static String ar() {
        return b(cj() + "/getWorkoutsInfo");
    }

    public static String t() {
        return b(cj() + "/getActionList");
    }

    public static String aa() {
        return b(cj() + "/getFitnessActionInfo");
    }

    public static String ab() {
        return b(cj() + "/getFitnessPackage");
    }

    public static String h() {
        return cj() + "/createFitnessPlan";
    }

    public static String bh() {
        return cj() + "/postRunPlan";
    }

    public static String as() {
        return b(cj() + "/getWorkoutUrl");
    }

    public static String ay() {
        return cj() + "/postBhavior";
    }

    public static String ag() {
        return cj() + "/getMyPlan";
    }

    public static String m() {
        return cj() + "/finishExercise";
    }

    public static String ah() {
        return cj() + "/getPlanProgress";
    }

    public static String p() {
        return cj() + "/finishPlan";
    }

    public static String bo() {
        return cj() + "/queryPlanStatistics";
    }

    public static String bt() {
        return cj() + "/queryTrainStatistics";
    }

    public static String bp() {
        return cj() + "/queryTrainCount";
    }

    public static String bn() {
        return cj() + "/queryTrainCountByWorkoutId";
    }

    public static String al() {
        return cj() + "/getUserBestRecords";
    }

    public static String bb() {
        return cj() + "/postBestRecord";
    }

    public static String be() {
        return cj() + "/postTrainBestRecords";
    }

    public static String af() {
        return b(cj() + "/getMultiLanguage");
    }

    public static String bq() {
        return b(cj() + "/queryTopicList");
    }

    public static String aq() {
        return b(cj() + "/getWorkoutsByTopicId");
    }

    public static String f() {
        return cj() + "/collectBehavior";
    }

    public static String g() {
        return cj() + "/deleteBehavior";
    }

    public static String x() {
        return cj() + "/getBehaviorList";
    }

    public static String l() {
        return cj() + "/deleteUserWorkout";
    }

    public static String n() {
        return cj() + "/deleteLogs";
    }

    public static String ak() {
        return b(cj() + "/getRunRecoWorkout");
    }

    public static String bk() {
        return cj() + "/queryFitnessPlanSummary";
    }

    public static String bm() {
        return cj() + "/queryFitnessPlanDetail";
    }

    public static String bl() {
        return cj() + "/queryMyDoingFitnessPlan";
    }

    public static String ba() {
        return cj() + "/myFinishPlans";
    }

    public static String am() {
        return b(cj() + "/getRecommendWorkout");
    }

    public static String ai() {
        return cj() + "/getRecWorkoutByCourseTypeAnon";
    }

    public static String br() {
        return cj() + "/queryTrainList";
    }

    public static String bf() {
        return cj() + "/postPlanRemind";
    }

    public static String av() {
        return cj() + "/modifyPlanName";
    }

    public static String bv() {
        return cj() + "/updateFitnessPlan";
    }

    public static String e() {
        return ch() + "/allowCreateOldPlan";
    }

    public static String bj() {
        return cj() + "/queryOperationPage";
    }

    public static String ao() {
        return b(cj() + "/getWorkoutsByType");
    }

    public static String q() {
        return b(cj() + "/getActionInfo");
    }

    public static String s() {
        return b(ch() + "/getAllPlans");
    }

    public static String c() {
        return ch() + "/achievementForecast";
    }

    public static String j() {
        return ch() + "/createPlan";
    }

    public static String bg() {
        return ch() + "/postFeedback";
    }

    public static String ae() {
        return ch() + "/getPlanReport";
    }

    public static String u() {
        return ch() + "/getCoachAdvice";
    }

    public static String y() {
        return ch() + "/getCurrentReportIndex";
    }

    public static String cg() {
        return ch() + "/updatePlan";
    }

    public static String ca() {
        return ch() + "/updatePlanDate";
    }

    public static String cf() {
        return ch() + "/updatePlanReport";
    }

    public static String cc() {
        return cj() + "/workoutCompletionPunch";
    }

    private static String b(String str) {
        if (!LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            return str;
        }
        return str + "Anon";
    }

    private static String cj() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl");
    }

    private static String ch() {
        return cj() + "/intRunningPlan";
    }

    public static String bs() {
        return GRSManager.a(BaseApplication.e()).getUrl("activityUrl") + "/activity/setEvent";
    }

    private static String ci() {
        return cj() + "/intPlan";
    }

    public static String ax() {
        return ci() + "/updateCustomAction";
    }

    public static String at() {
        return ci() + "/postFeedback";
    }

    public static String v() {
        return ci() + "/getDoingPlan";
    }

    public static String i() {
        return ci() + "/createPlan";
    }

    public static String bu() {
        return ci() + "/stopPlan";
    }

    public static String by() {
        return ci() + "/updatePlan";
    }

    public static String bd() {
        return ci() + "/postPlanReport";
    }

    public static String z() {
        return ci() + "/getPlanReport";
    }

    public static String r() {
        return ci() + "/generateReport";
    }

    public static String cb() {
        return ci() + "/updatePlanProperty";
    }

    public static String bx() {
        return ci() + "/updatePlanDayRecord";
    }

    public static String k() {
        return ci() + "/deletePlanExerciseRecord";
    }

    public static String bw() {
        return ci() + "/replacePlanSchedule";
    }

    public static String aw() {
        return ci() + "/v1/leavePlan";
    }

    public static String b() {
        return ci() + "/v1/cancelLeave";
    }

    public static String bz() {
        return ci() + "/v1/updatePlanCalendar";
    }

    public static String a() {
        return cj() + "/addUserDefinedWorkout";
    }

    public static String au() {
        return cj() + "/modifyUserDefinedWorkout";
    }

    public static String cd() {
        return cj() + "/deleteUserDefinedWorkout";
    }

    public static String ce() {
        return cp() + "/getFilterTab";
    }

    private static String cn() {
        return cj() + "/v1";
    }

    private static String cp() {
        return cj() + "/v2";
    }

    private static String ck() {
        return GRSManager.a(BaseApplication.e()).getUrl("messageCenterUrl") + "/messageCenter";
    }

    private static String a(String str, boolean z) {
        if (LoginInit.getInstance(BaseApplication.e()).isBrowseMode() && z) {
            return str;
        }
        return "/user" + str;
    }

    public static String bi() {
        return ck() + a("/queryAudiosPackageBySeriesId", true);
    }

    public static String w() {
        return ck() + a("/getBehaviorList", false);
    }

    public static String az() {
        return ck() + "/operateFavoriteAudio";
    }

    public static String o() {
        return ck() + a("/deletePlayRecord", false);
    }

    public static String bc() {
        return cj() + "/v2" + a("/getNavigationCourseList", true);
    }

    public static String ac() {
        return cj() + "/getSnapShotAndAlgorithmAnon";
    }

    public static String cl() {
        return cj() + "/queryMaxScore";
    }
}
