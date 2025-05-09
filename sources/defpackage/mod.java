package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.HealthWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import defpackage.ffl;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mod {

    /* renamed from: a, reason: collision with root package name */
    public static String f15079a = "courseParameter";

    public static List<Workout> j(List<FitWorkout> list) {
        if (list == null) {
            return null;
        }
        return new ArrayList(list);
    }

    public static List<FitWorkout> a(List<Workout> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            LogUtil.h("Suggestion_CourseUtil", "toFitWorkoutList workouts is null.");
            return null;
        }
        if (!koq.e((Object) list, FitWorkout.class)) {
            LogUtil.h("Suggestion_CourseUtil", "toFitWorkoutList: workouts element type is not FitWorkout.");
            return arrayList;
        }
        Iterator<Workout> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((FitWorkout) it.next());
        }
        return arrayList;
    }

    public static List<RunWorkout> c(List<Workout> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            LogUtil.h("Suggestion_CourseUtil", "toRunWorkoutList: workouts is null.");
            return null;
        }
        if (!koq.e((Object) list, RunWorkout.class)) {
            LogUtil.h("Suggestion_CourseUtil", "toRunWorkoutList: workouts element type is not RunWorkout.");
            return arrayList;
        }
        Iterator<Workout> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((RunWorkout) it.next());
        }
        return arrayList;
    }

    public static List<HealthWorkout> e(List<Workout> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            LogUtil.h("Suggestion_CourseUtil", "toFitWorkoutList workouts is null.");
            return null;
        }
        if (!koq.e((Object) list, HealthWorkout.class)) {
            LogUtil.h("Suggestion_CourseUtil", "toFitWorkoutList: workouts element type is not FitWorkout.");
            return arrayList;
        }
        Iterator<Workout> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((HealthWorkout) it.next());
        }
        return arrayList;
    }

    public static FitWorkout a(Workout workout) {
        if (workout == null) {
            LogUtil.h("Suggestion_CourseUtil", "toFitWorkout: workout is null.");
            return null;
        }
        if (!(workout instanceof FitWorkout)) {
            LogUtil.h("Suggestion_CourseUtil", "toFitWorkout: workout real type is not FitWorkout.");
            return null;
        }
        return (FitWorkout) workout;
    }

    public static WorkoutPackageInfo e(Workout workout) {
        if (workout == null) {
            LogUtil.h("Suggestion_CourseUtil", "toWorkoutPackageInfo: workout is null.");
            return null;
        }
        if (!(workout instanceof WorkoutPackageInfo)) {
            LogUtil.h("Suggestion_CourseUtil", "toWorkoutPackageInfo: workout real type is not WorkoutPackageInfo.");
            return null;
        }
        return (WorkoutPackageInfo) workout;
    }

    public static String b(List<PriceTagBean> list) {
        String str = "";
        if (list == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = 0;
        int i = 0;
        for (PriceTagBean priceTagBean : list) {
            if (priceTagBean == null) {
                LogUtil.b("Suggestion_CourseUtil", "invalid price tag");
            } else {
                long priceStartTime = priceTagBean.getPriceStartTime();
                long priceEndTime = priceTagBean.getPriceEndTime();
                int weight = priceTagBean.getWeight();
                if (priceEndTime >= currentTimeMillis && priceStartTime <= currentTimeMillis && (priceStartTime > j || (priceStartTime == j && weight > i))) {
                    str = priceTagBean.getUrl();
                    j = priceStartTime;
                    i = weight;
                }
            }
        }
        return str;
    }

    public static float c(String str) {
        String b = ash.b("workoutid_" + str);
        if (TextUtils.isEmpty(b)) {
            ReleaseLogUtil.d("Suggestion_CourseUtil", "getDownloadedProgress oldData == null");
            return 0.0f;
        }
        String[] split = b.split("_");
        if (split.length == 0) {
            LogUtil.h("Suggestion_CourseUtil", "getDownloadedProgress split.length <= 0");
            return 0.0f;
        }
        String str2 = split[0];
        int e = split.length >= 2 ? gic.e((Object) split[1]) : 0;
        int e2 = gic.e((Object) ash.b("clear_version"));
        float floatValue = e >= e2 ? gic.b((Object) str2).floatValue() : 0.0f;
        LogUtil.a("Suggestion_CourseUtil", "getCourseDownloadedProgress courseId: ", str, " oldClearVersion:", Integer.valueOf(e), "- newClearVersion:", Integer.valueOf(e2), "-invalidProgress:", Float.valueOf(floatValue));
        return floatValue;
    }

    public static void d(String str, float f) {
        String str2 = "workoutid_" + str;
        ash.a(str2, String.valueOf(f + "_" + gic.e((Object) ash.b("clear_version"))));
    }

    public static List<String> b(FitWorkout fitWorkout) {
        JSONObject jSONObject;
        if (fitWorkout == null) {
            return Collections.emptyList();
        }
        List<String> acquireListRelativeWorkouts = fitWorkout.acquireListRelativeWorkouts();
        if (koq.c(acquireListRelativeWorkouts)) {
            return acquireListRelativeWorkouts;
        }
        ReleaseLogUtil.d("Suggestion_CourseUtil", "getRelativeCourses user old extendmap");
        ArrayList arrayList = new ArrayList();
        try {
            jSONObject = new JSONObject(fitWorkout.acquireNarrowDesc());
        } catch (JSONException e) {
            LogUtil.h("Suggestion_CourseUtil", "getRelativeCourses ", LogAnonymous.b((Throwable) e));
        }
        if (jSONObject.has("recommendCourses")) {
            return Collections.emptyList();
        }
        JSONArray jSONArray = new JSONArray(jSONObject.optString("recommendCourses"));
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    public static List<ffl> d(List<String> list) {
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ffl.d(it.next()).b());
        }
        return arrayList;
    }

    public static float c(long j, int i, float f, float f2) {
        return moe.b(CommonUtil.h(moe.f(j)) * f * f2 * i);
    }

    public static float a(float f, long j, float f2) {
        return moe.b(f * j * f2);
    }

    public static void d(Context context, FitWorkout fitWorkout) {
        b(context, fitWorkout, null);
    }

    public static void b(Context context, FitWorkout fitWorkout, mmp mmpVar) {
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_CourseUtil", "fitWorkout == null");
            return;
        }
        if (mmpVar == null) {
            mmpVar = new mmp(fitWorkout.acquireId());
            mmpVar.r(fitWorkout.accquireVersion());
        }
        if (b(fitWorkout.isCustomCourse())) {
            e(context, mmpVar);
        } else {
            d(context, fitWorkout, mmpVar);
        }
    }

    public static void c(Context context, mmp mmpVar, ArrayList<WorkoutRecord> arrayList) {
        if (CollectionUtils.d(arrayList) || mmpVar == null) {
            ReleaseLogUtil.d("Suggestion_CourseUtil", "jumpTrainDetail workoutRecords null || parameter == null");
            return;
        }
        if (b(mmpVar.x() == 1)) {
            e(context, mmpVar);
            return;
        }
        Bundle bundle = new Bundle();
        cmy_(mmpVar, bundle);
        bundle.putParcelableArrayList("workoutrecord", arrayList);
        AppRouter.b("/PluginFitnessAdvice/TrainDetail").zF_(bundle).b(mmpVar.e(), mmpVar.h()).a(268435456).c(context);
    }

    public static void c(Context context, mmp mmpVar) {
        if (mmpVar == null) {
            LogUtil.h("Suggestion_CourseUtil", "jumpTrainDetail parameter == null");
            return;
        }
        if (TextUtils.isEmpty(mmpVar.ab())) {
            ReleaseLogUtil.d("Suggestion_CourseUtil", "jumpTrainDetail getWorkoutId == null");
            return;
        }
        if (b(mmpVar.x() == 1)) {
            e(context, mmpVar);
            return;
        }
        Bundle bundle = new Bundle();
        cmy_(mmpVar, bundle);
        AppRouter.b("/PluginFitnessAdvice/TrainDetail").zF_(bundle).a(268435456).c(context);
    }

    private static void d(Context context, FitWorkout fitWorkout, mmp mmpVar) {
        WorkoutRecord c = c(fitWorkout, mmpVar);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(1);
        arrayList.add(c);
        Bundle bundle = new Bundle();
        cmy_(mmpVar, bundle);
        bundle.putParcelableArrayList("workoutrecord", arrayList);
        AppRouter.b("/PluginFitnessAdvice/TrainDetail").zF_(bundle).b(mmpVar.e(), mmpVar.h()).a(268435456).c(context);
        ReleaseLogUtil.e("Suggestion_CourseUtil", "jumpToDetailH5 gotoTrainDetailNativeStyle");
    }

    public static WorkoutRecord c(FitWorkout fitWorkout, mmp mmpVar) {
        if (fitWorkout == null) {
            ReleaseLogUtil.d("Suggestion_CourseUtil", "getWorkoutRecord null");
            return new WorkoutRecord();
        }
        if (mmpVar == null) {
            mmpVar = new mmp(fitWorkout.acquireId());
            mmpVar.r(fitWorkout.accquireVersion());
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveVersion(fitWorkout.accquireVersion());
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId(fitWorkout.acquireId());
        workoutRecord.saveCalorie(fitWorkout.acquireCalorie());
        workoutRecord.saveCategory(fitWorkout.getCategory());
        workoutRecord.saveWeekNum(mmpVar.ac());
        workoutRecord.saveWorkoutOrder(mmpVar.z());
        workoutRecord.savePlanId(mmpVar.m());
        workoutRecord.setAiWorkout(fitWorkout.getIsAi());
        workoutRecord.saveWorkoutDate(DateFormatUtil.b(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD));
        workoutRecord.saveWorkoutName(fitWorkout.acquireName());
        return workoutRecord;
    }

    private static void cmy_(mmp mmpVar, Bundle bundle) {
        if (mmpVar != null) {
            cmv_(mmpVar, bundle);
            cmw_(mmpVar, bundle);
            cmx_(mmpVar, bundle);
            bundle.putBundle("bundlekey", cmz_(mmpVar));
            ReleaseLogUtil.e("Suggestion_CourseUtil", "buildParameter parameter", new Gson().toJson(mmpVar));
        }
    }

    private static void cmx_(mmp mmpVar, Bundle bundle) {
        bundle.putBoolean("isSendCourseDevice", mmpVar.an());
        bundle.putString("entrance_type", mmpVar.c());
        bundle.putBoolean("ISFROMSCHEME", mmpVar.ah());
        bundle.putBoolean("ISPLANFIT", mmpVar.am());
        bundle.putBoolean("isafterrun", mmpVar.aa());
        bundle.putBoolean("moveTaskToBack", mmpVar.ag());
        bundle.putBoolean("IS_START_DOWNLOAD_WORKOUT", mmpVar.aj());
        bundle.putInt("executImmediateErrorType", mmpVar.j());
        bundle.putBoolean("isNeedExecutImmediate", mmpVar.af());
        bundle.putBoolean("havenexttrain", mmpVar.ai());
        bundle.putBoolean("deviceTraining", mmpVar.ae());
    }

    private static void cmv_(mmp mmpVar, Bundle bundle) {
        bundle.putString("workoutid", mmpVar.ab());
        bundle.putLong("plan_execute_time", mmpVar.f());
        bundle.putInt("userDefinedType", mmpVar.x());
        bundle.putString("version", mmpVar.v());
        bundle.putString("workout_package_id", mmpVar.ad());
        bundle.putString("planId", mmpVar.m());
    }

    private static void cmw_(mmp mmpVar, Bundle bundle) {
        bundle.putString("entrance", mmpVar.a());
        bundle.putString("resource_type", mmpVar.p());
        bundle.putString("planTempId", mmpVar.l());
        bundle.putString(WebViewHelp.BI_KEY_PULL_FROM, mmpVar.k());
        bundle.putString("resourceId", mmpVar.r());
        bundle.putString("resourceName", mmpVar.t());
        bundle.putString("pullOrder", mmpVar.o());
        bundle.putString("algId", mmpVar.b());
        bundle.putString("promoteTag", mmpVar.n());
        bundle.putString("itemId", mmpVar.g());
        bundle.putString(x2.AB_INFO, mmpVar.d());
        bundle.putString("topic_name", mmpVar.q());
    }

    public static Bundle cmz_(mmp mmpVar) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isshowbutton", mmpVar.al());
        bundle.putInt("track_type", mmpVar.w());
        bundle.putInt("track_target", mmpVar.y());
        bundle.putFloat("track_targetvalue", mmpVar.u());
        return bundle;
    }

    public static boolean b(boolean z) {
        if (z) {
            return false;
        }
        if (CommonUtil.as()) {
            return true;
        }
        return ntf.b().i();
    }

    private static void e(Context context, mmp mmpVar) {
        ReleaseLogUtil.e("Suggestion_CourseUtil", "jumpToDetailH5 parameter", mmpVar);
        Context d = d(context);
        if (d == null || mmpVar == null) {
            ReleaseLogUtil.d("Suggestion_CourseUtil", "jumpToTrainingDaySettingsH5() context is null");
            return;
        }
        boolean isBrowseMode = LoginInit.getInstance(d).isBrowseMode();
        ReleaseLogUtil.e("Suggestion_CourseUtil", "jumpToDetailH5 isBrowseMode", Boolean.valueOf(isBrowseMode));
        if (isBrowseMode) {
            LoginInit.getInstance(d).browsingToLogin(new IBaseResponseCallback() { // from class: mod.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("Suggestion_CourseUtil", "jumpToDetailH5 browsingToLogin", Integer.valueOf(i));
                }
            }, "");
            return;
        }
        String ab = mmpVar.ab();
        String json = new Gson().toJson(mmpVar);
        bzs.e().initH5Pro();
        bzs e = bzs.e();
        H5ProLaunchOption.Builder addGlobalBiParam = new H5ProLaunchOption.Builder().addPath("#/CourseDetail?id=" + ab).addCustomizeArg(f15079a, json).addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, mmpVar.k()).addGlobalBiParam("resourceId", mmpVar.r()).addGlobalBiParam("resourceName", mmpVar.t()).addGlobalBiParam("pullOrder", mmpVar.o());
        SeriesCourseH5Repository.registerService();
        e.loadH5ProApp(d, "com.huawei.health.h5.course", addGlobalBiParam);
        ReleaseLogUtil.e("Suggestion_CourseUtil", "jumpToDetailH5 success");
        ary.a().e("COLLECTION_ADD");
    }

    private static Context d(Context context) {
        if (context != null) {
            return context;
        }
        Activity activity = BaseApplication.getActivity();
        return activity == null ? BaseApplication.getContext() : activity;
    }
}
