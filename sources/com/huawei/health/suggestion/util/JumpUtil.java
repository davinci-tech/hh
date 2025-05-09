package com.huawei.health.suggestion.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanPoster;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.model.data.RecordPlanInfo;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.health.suggestion.h5pro.IntelligentPlanH5Repository;
import com.huawei.health.suggestion.ui.FitnessPlanJoinActivity;
import com.huawei.health.suggestion.ui.MyPlanReportActivity;
import com.huawei.health.suggestion.ui.TrainEventActivity;
import com.huawei.health.suggestion.ui.fitness.activity.CoachActivity;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.fitness.callback.JumpFinishCallback;
import com.huawei.health.suggestion.ui.fitness.callback.JumpViewInterface;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.plan.activity.AiPlanActivity;
import com.huawei.health.suggestion.ui.plan.activity.FitnessPlanDetailActivity;
import com.huawei.health.suggestion.ui.run.activity.CustomCourseActivity;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.maps.offlinedata.DeviceTypeConsts;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository;
import com.huawei.pluginoperation.util.DietKakaUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.ary;
import defpackage.ase;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.cun;
import defpackage.ffm;
import defpackage.ffy;
import defpackage.fib;
import defpackage.fis;
import defpackage.fnu;
import defpackage.fot;
import defpackage.fpq;
import defpackage.ftb;
import defpackage.gge;
import defpackage.ggg;
import defpackage.ggr;
import defpackage.ggs;
import defpackage.ghq;
import defpackage.ghy;
import defpackage.gib;
import defpackage.gnm;
import defpackage.gpn;
import defpackage.gpo;
import defpackage.grz;
import defpackage.ixx;
import defpackage.jdm;
import defpackage.jec;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mmw;
import defpackage.mnw;
import defpackage.mod;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class JumpUtil {
    public static void c(Context context, CourseApi courseApi, FitWorkout fitWorkout) {
        new JumpHelper().b(context, courseApi, fitWorkout);
    }

    public static boolean a(CourseApi courseApi, FitWorkout fitWorkout) {
        return new JumpHelper().d(courseApi, fitWorkout);
    }

    public static void c(Context context, FitWorkout fitWorkout) {
        new JumpHelper().c(context, fitWorkout);
    }

    public static void e(Context context, FitWorkout fitWorkout) {
        new JumpHelper().d(context, fitWorkout);
    }

    public static void e(Context context, int i) {
        new JumpHelper().e(context, i);
    }

    public static void c(Context context, int i) {
        new JumpHelper().c(context, i);
    }

    public static void b(Context context, int i, int i2, String str) {
        new JumpHelper().e(context, i, i2, str);
    }

    public static void c(Context context, FitWorkout fitWorkout, Map<String, Object> map) {
        new JumpHelper().e(context, fitWorkout, map);
    }

    public static void c(Context context, mmw mmwVar) {
        new JumpHelper().b(context, mmwVar);
    }

    public static void d(Context context, int i) {
        new JumpHelper().b(context, i);
    }

    public static void b(Context context, int i) {
        new JumpHelper().d(context, i);
    }

    public static void e(Context context) {
        new JumpHelper().e(context);
    }

    public static void d(Plan plan, PlanWorkout planWorkout) {
        new JumpHelper().d(plan, planWorkout);
    }

    public static void d(Context context, Plan plan, FitWorkout fitWorkout) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            return;
        }
        planApi.setPlanType(0);
        ggs.d(context, fitWorkout, plan, System.currentTimeMillis());
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, 1);
        gge.e(AnalyticsValue.HEALTH_HOME_START_TRAIN_BTN_2010037.value(), hashMap);
    }

    public static void i(Context context) {
        new JumpHelper().g(context);
    }

    public static void d(Context context, FitWorkout fitWorkout) {
        new JumpHelper().aMP_(context, fitWorkout, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void aMF_(Context context, FitWorkout fitWorkout, RecordPlanInfo recordPlanInfo, Bundle bundle) {
        JumpHelper jumpHelper = new JumpHelper();
        if (context instanceof JumpFinishCallback) {
            jumpHelper.d((JumpFinishCallback) context);
        }
        jumpHelper.aMP_(context, fitWorkout, recordPlanInfo, bundle);
    }

    public static void e() {
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null) {
            ReleaseLogUtil.d("Suggestion_JumpUtil", "jumpDietaryInstruct api is null");
        } else {
            marketRouterApi.router("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.diet-diary?h5pro=true&urlType=4&pName=com.huawei.health&path=dietary_instruct");
        }
    }

    public static void c(Context context) {
        new JumpHelper().d(context);
    }

    public static void a(Context context) {
        new JumpHelper().b(context);
    }

    public static void d(Context context) {
        new JumpHelper().a(context);
    }

    public static void d(int i, String str, String str2, Context context) {
        new JumpHelper(context).d(i, str, str2);
    }

    public static void b(Context context) {
        new JumpHelper().c(context);
    }

    public static void c(Context context, String str, int i) {
        new JumpHelper().d(context, str, i);
    }

    public static void c(Context context, double d, double d2) {
        new JumpHelper().d(context, d, d2, (String) null);
    }

    public static void e(Context context, double d, double d2, String str) {
        new JumpHelper().d(context, d, d2, str);
    }

    public static void a(Context context, String str, int i) {
        new JumpHelper().e(context, str, i);
    }

    public static void a(int i, Context context, int i2, String str) {
        new JumpHelper(context).c(i, i2, str, 0);
    }

    public static void c(int i, Context context, int i2, String str, int i3) {
        new JumpHelper(context).c(i, i2, str, i3);
    }

    public static void a(Context context, String str) {
        new JumpHelper().a(context, str);
    }

    public static void d(String str, String... strArr) {
        new JumpHelper().c(str, strArr);
    }

    public static void c(long j, int i, int i2) {
        new JumpHelper().c(j, i, i2);
    }

    public static void h(Context context) {
        new JumpHelper().h(context);
    }

    public static void b(Context context, String str, int i) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_JumpUtil", "jumpToCustomCourseEdit failed context ", context, " courseId ", str, " courseEditState ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("Suggestion_JumpUtil", "context ", context, " courseId ", str, " courseEditState ", Integer.valueOf(i));
        Intent intent = new Intent(context, (Class<?>) CustomCourseActivity.class);
        intent.putExtra("CustomCourseFitWorkoutId", str);
        intent.putExtra("CustomCourseState", i);
        intent.setFlags(268435456);
        gnm.aPB_(context, intent);
    }

    public static void c(Context context, String str) {
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("mediaCenter", bzs.e().getCommonJsModule("mediaCenter")).enableOnPauseCallback().setImmerse().showStatusBar().setStatusBarTextBlack(true).addCustomizeArg("workoutPackageId", str).setForceDarkMode(0).build();
        SeriesCourseH5Repository.registerService();
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.course", build);
        ary.a().e("COLLECTION_ADD");
    }

    public static void aMD_(final Context context, final WorkoutRecord workoutRecord, final Bundle bundle) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_JumpUtil", "doViewTrainDetailBi, getCurrentPlan : planApi is null.");
        } else {
            planApi.b(new UiCallback<IntPlan>() { // from class: com.huawei.health.suggestion.util.JumpUtil.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_JumpUtil", "getCurrentPlan fail errCode:", Integer.valueOf(i), ",errInfo:", str);
                    JumpUtil.aME_(context, workoutRecord, 0, bundle, null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IntPlan intPlan) {
                    int i;
                    LogUtil.a("Suggestion_JumpUtil", "jumpToMotionGuidance onSuccess");
                    if (intPlan != null) {
                        i = intPlan.getPlanType().getType();
                        LogUtil.a("Suggestion_JumpUtil", "type: ", Integer.valueOf(i));
                    } else {
                        i = 0;
                    }
                    JumpUtil.aME_(context, workoutRecord, i, bundle, intPlan);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void aME_(Context context, WorkoutRecord workoutRecord, int i, Bundle bundle, IntPlan intPlan) {
        int i2;
        int i3;
        long j;
        int planTrainDate = workoutRecord.getPlanTrainDate();
        if (planTrainDate == 0 || intPlan == null) {
            i2 = 0;
            i3 = 0;
        } else {
            try {
                j = DateFormatUtil.d(String.valueOf(planTrainDate), DateFormatUtil.DateFormatType.DATE_FORMAT_8).getTime();
            } catch (ParseException e) {
                LogUtil.b("Suggestion_JumpUtil", "startMotionGuidance exception", LogAnonymous.b((Throwable) e));
                j = 0;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            i2 = gib.d(calendar.get(7));
            i3 = ase.e(intPlan, j);
        }
        LogUtil.a("Suggestion_JumpUtil", "dayNumber: ", Integer.valueOf(i2), "weekNumber: ", Integer.valueOf(i3));
        bundle.putInt("planType", i);
        bundle.putInt("dayNumber", i2);
        bundle.putInt("weekNumber", i3);
        AppRouter.b("/PluginAiFitnessSportCommon/AIMotionGuidanceActivity").zF_(bundle).c(BaseApplication.getContext());
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public static void d(int i, String str, int i2, Context context) {
        d(i, str, i2, "", "", context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void d(int i, String str, int i2, String str2, String str3, Context context) {
        JumpHelper jumpHelper = new JumpHelper(context);
        if (context instanceof JumpFinishCallback) {
            jumpHelper.d((JumpFinishCallback) context);
        }
        if (context instanceof JumpViewInterface) {
            jumpHelper.e((JumpViewInterface) context);
        }
        jumpHelper.e(i, str, i2, str2, str3);
    }

    public static void b(String str, Context context) {
        new JumpHelper().e(str, context);
    }

    public static void a(mmw mmwVar, String str, Context context, boolean z) {
        new JumpHelper().d(mmwVar, str, context, z);
    }

    public static void d(mmw mmwVar, Context context) {
        new JumpHelper().a(mmwVar, context);
    }

    public static void e(IntPlan intPlan) {
        new JumpHelper().a(intPlan);
    }

    public static void e(int i, String str) {
        new JumpHelper().c(i, str);
    }

    public static void b(FitnessPackageInfo fitnessPackageInfo, String str, Context context) {
        new JumpHelper().e(fitnessPackageInfo, str, context);
    }

    public static void b(int i, Context context, String str) {
        new JumpHelper().b(i, context, str);
    }

    public static void a(Context context, mmw mmwVar) {
        new JumpHelper().c(context, mmwVar);
    }

    public static void b(Context context, Map<String, String> map) {
        new JumpHelper(context).b(map);
    }

    public static void e(Context context, String str, int i) {
        new JumpHelper(context).c(str, i);
    }

    public static class JumpHelper {

        /* renamed from: a, reason: collision with root package name */
        private boolean f3436a;
        private WeakReference<Context> b;
        private WeakReference<JumpViewInterface> c;
        private WeakReference<JumpFinishCallback> e;

        public JumpHelper() {
            this(BaseApplication.getContext());
        }

        public JumpHelper(Context context) {
            this.f3436a = false;
            if (context != null) {
                this.b = new WeakReference<>(context);
            } else {
                this.b = new WeakReference<>(BaseApplication.getContext());
            }
        }

        public void b(final Context context, final CourseApi courseApi, final FitWorkout fitWorkout) {
            if (context == null || courseApi == null || fitWorkout == null) {
                LogUtil.b("JumpUtil", "params are invalid");
            } else if (e(fitWorkout)) {
                courseApi.getCourseLongVideoInfo(fitWorkout.acquireId(), new UiCallback<LongVideoInfo>() { // from class: com.huawei.health.suggestion.util.JumpUtil.JumpHelper.4
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.a("JumpUtil", "getCourseLongVideoInfo failed, errorCode: ", Integer.valueOf(i), ", errorInfo: ", str, ", try to jump course detail");
                        JumpHelper.this.d(context, fitWorkout);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(LongVideoInfo longVideoInfo) {
                        courseApi.postUserCourse(fitWorkout.acquireId(), fitWorkout.accquireVersion(), 0);
                        JumpHelper.this.a(context, longVideoInfo.getUrl(), fitWorkout);
                    }
                });
            }
        }

        public boolean d(CourseApi courseApi, FitWorkout fitWorkout) {
            return (fitWorkout == null || courseApi == null || courseApi.getCourseMediaFilesLength(fitWorkout.acquireId(), fitWorkout.accquireVersion()) != 0) ? false : true;
        }

        public void c(Context context, FitWorkout fitWorkout) {
            if (context == null || fitWorkout == null) {
                LogUtil.b("JumpUtil", "context or workout is invalid");
                return;
            }
            CoachData d = d(fitWorkout, false);
            if (ffy.c(d)) {
                boolean b = b();
                fot.a().a(fitWorkout);
                WorkoutRecord d2 = d(fitWorkout);
                d2.saveExerciseTime(System.currentTimeMillis());
                Intent intent = new Intent(context, (Class<?>) CoachActivity.class);
                intent.putExtra("BASE_AUDIO_TIMBRE", fitWorkout.getTimbre());
                intent.putExtra("islinkedfitness", b);
                intent.putExtra("isafterrun", false);
                intent.putExtra(ContentTemplateRecord.MOTIONS, d);
                intent.putExtra("coachstartposition", 0);
                intent.putExtra("havenexttrain", false);
                intent.putExtra("workoutrecord", d2);
                intent.putExtra("entrance", "DailyMoment");
                intent.putExtra("ISPLANFIT", false);
                gnm.aPB_(context, intent);
            }
        }

        public void d(Context context, FitWorkout fitWorkout) {
            if (fitWorkout == null) {
                return;
            }
            mmp mmpVar = new mmp(fitWorkout.acquireId());
            mmpVar.d("DailyMoment");
            mod.b(context, fitWorkout, mmpVar);
        }

        public void e(Context context, int i) {
            String str;
            LogUtil.a("JumpUtil", "jumpToHistoryPlanList");
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToHistoryPlanList() context is null");
                return;
            }
            if (i == 2) {
                str = "#/my_plan_list?planType=0";
            } else if (i != 3) {
                LogUtil.a("JumpUtil", "jumpToHistoryPlanList all planType");
                str = "#/my_plan_list?planType=";
            } else {
                str = "#/my_plan_list?planType=1";
            }
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (operationUtilsApi != null) {
                operationUtilsApi.initH5pro();
                H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().addPath(str).setStatusBarTextBlack(true).setForceDarkMode(1).build();
                IntelligentPlanH5Repository.registerService();
                H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.ai-weight", build);
            }
        }

        public void c(Context context, int i) {
            e(context, i, CommonUtil.e(SharedPreferenceManager.b(context, Integer.toString(20002), "map_tracking_sport_type"), 258) == 264 ? 5 : 4, "RUNNING_COURSE");
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_SPORT_TAP_ALL_RUNNING_COURSE_1120018.value(), hashMap, 0);
        }

        public void e(Context context, int i, int i2, String str) {
            Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportAllCourseActivity.class);
            intent.putExtra("COURSE_CATEGORY_KEY", i);
            int c = gge.c(str);
            intent.putExtra("SKIP_ALL_COURSE_KEY", str);
            intent.addFlags(268435456);
            gnm.aPB_(context, intent);
            ggr.c(c, i2);
        }

        public void e(Context context, FitWorkout fitWorkout, Map<String, Object> map) {
            gge.e("1130015", map);
            mmp mmpVar = new mmp(fitWorkout.acquireId());
            mmpVar.d(fnu.a(map, "entrance"));
            if (fitWorkout.getCourseDefineType() == 1) {
                mmpVar.g(1);
            }
            mod.b(context, fitWorkout, mmpVar);
        }

        public void b(Context context, mmw mmwVar) {
            if (FitnessExternalUtils.a() && !FitnessExternalUtils.e()) {
                d(context, mmwVar);
            } else {
                d();
            }
        }

        private void d(Context context, mmw mmwVar) {
            Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) RunPlanCreateActivity.class);
            intent.putExtra("runPlanTitle", mmwVar.i());
            intent.putExtra("runPlanType", mmwVar.c());
            intent.setFlags(268435456);
            gnm.aPB_(context, intent);
        }

        private void d() {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("JumpUtil", "bindRunPlanInfo, getCurrentPlan : planApi is null.");
            } else {
                planApi.setPlanType(0);
                planApi.checkAllowCreateOldPlan(new UiCallback<Boolean>() { // from class: com.huawei.health.suggestion.util.JumpUtil.JumpHelper.1
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        ReleaseLogUtil.c("JumpUtil", "checkAllowCreateOldPlan()  failed errorCode=", Integer.valueOf(i));
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Boolean bool) {
                        if (bool != null) {
                            JumpHelper.this.a();
                        } else {
                            ReleaseLogUtil.c("JumpUtil", "checkAllowCreateOldPlan() onSuccess plan is null");
                        }
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(BaseApplication.getContext());
            builder.e(R.string._2130844101_res_0x7f0219c5);
            final NoTitleCustomAlertDialog noTitleCustomAlertDialog = null;
            builder.czC_(R.string._2130841550_res_0x7f020fce, new View.OnClickListener() { // from class: ghk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JumpUtil.JumpHelper.aMI_(NoTitleCustomAlertDialog.this, view);
                }
            });
            builder.czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: ggz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            builder.e().show();
        }

        public static /* synthetic */ void aMI_(NoTitleCustomAlertDialog noTitleCustomAlertDialog, View view) {
            if (jdm.b(BaseApplication.getContext(), "com.huawei.appmarket")) {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + BaseApplication.getAppPackage()));
                intent.addFlags(268435456);
                intent.setPackage("com.huawei.appmarket");
                nsn.cLM_(intent, "com.huawei.appmarket", BaseApplication.getContext(), nsf.h(R$string.IDS_device_fragment_application_market));
            } else {
                nrh.b(BaseApplication.getContext(), R.string._2130841726_res_0x7f02107e);
            }
            noTitleCustomAlertDialog.dismiss();
            ViewClickInstrumentation.clickOnView(view);
        }

        public void b(Context context, int i) {
            Intent intent = new Intent(arx.b(), (Class<?>) MyFitnessCourseActivity.class);
            intent.putExtra("intent_behavior_key", i);
            intent.putExtra("courseCategoryKey", 258);
            intent.putExtra("titleName", arx.b().getString(R.string._2130848513_res_0x7f022b01));
            intent.addFlags(268435456);
            gnm.aPB_(context, intent);
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            ixx.d().d(context, AnalyticsValue.HEALTH_SPORT_TAP_MY_RUNNING_COURSE_1120017.value(), hashMap, 0);
        }

        public void d(Context context, int i) {
            Intent intent = new Intent(arx.b(), (Class<?>) MyFitnessCourseActivity.class);
            intent.putExtra("intent_behavior_key", i);
            intent.putExtra("courseCategoryKey", 257);
            intent.putExtra("titleName", context.getString(R.string._2130848826_res_0x7f022c3a));
            intent.addFlags(268435456);
            gnm.aPB_(context, intent);
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            ixx.d().d(context, AnalyticsValue.HEALTH_SPORT_TAP_MY_RUNNING_COURSE_1120017.value(), hashMap, 0);
        }

        public void e(Context context) {
            Intent intent = new Intent(context, (Class<?>) AiPlanActivity.class);
            intent.putExtra("plantype", 2);
            intent.addFlags(268435456);
            gnm.aPB_(context, intent);
            gge.e("1120019");
        }

        public void d(Plan plan, PlanWorkout planWorkout) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.h("JumpUtil", "startRunPlan : planApi is null.");
                return;
            }
            planApi.setPlanType(0);
            if (fib.e().c()) {
                ffm.e(planWorkout);
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", 1);
                hashMap.put(BleConstants.SPORT_TYPE, 1);
                gge.e(AnalyticsValue.HEALTH_HOME_START_TRAIN_BTN_2010037.value(), hashMap);
            }
        }

        public void g(Context context) {
            if (gpo.b()) {
                LogUtil.a("JumpUtil", "jump to discover.");
                AppRouter.b("/home/main").e("openDiscover", true).c(com.huawei.haf.application.BaseApplication.e());
            } else {
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addPath("#/PrivilegeDetail?functionId=1");
                bzs.e().loadH5ProApp(context, "com.huawei.health.h5.vip", builder);
            }
        }

        public void aMP_(Context context, FitWorkout fitWorkout, RecordPlanInfo recordPlanInfo, Bundle bundle) {
            if (fis.d().axT_(context, fitWorkout, recordPlanInfo, bundle) == 0) {
                e();
            }
        }

        public void d(Context context, String str, int i) {
            bzs.e().initH5Pro();
            StringBuilder sb = new StringBuilder("#/home");
            if (!TextUtils.isEmpty(str)) {
                sb.append("?planTempId=");
                sb.append(str);
                sb.append("&planType=");
                sb.append(i);
            }
            H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).setImmerse().showStatusBar().setStatusBarTextBlack(true).addPath(sb.toString()).setForceDarkMode(0).build();
            IntelligentPlanH5Repository.registerService();
            H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.general-plan", build);
            e();
        }

        private WorkoutRecord d(FitWorkout fitWorkout) {
            WorkoutRecord workoutRecord = new WorkoutRecord();
            if (fitWorkout == null) {
                return workoutRecord;
            }
            workoutRecord.saveWorkoutId(fitWorkout.acquireId());
            workoutRecord.saveVersion(fitWorkout.accquireVersion());
            workoutRecord.saveExerciseTime(System.currentTimeMillis());
            workoutRecord.savePlanId("");
            workoutRecord.saveWorkoutName(fitWorkout.acquireName());
            workoutRecord.saveCalorie(fitWorkout.acquireCalorie());
            workoutRecord.saveCategory(fitWorkout.getCategory());
            workoutRecord.saveHeartRateDataList(new ArrayList());
            workoutRecord.saveInvalidHeartRateList(new ArrayList());
            return workoutRecord;
        }

        private boolean e(FitWorkout fitWorkout) {
            Video b = b(fitWorkout);
            return (b == null || b.getFileId() == null) ? false : true;
        }

        private Video b(FitWorkout fitWorkout) {
            WorkoutAction workoutAction;
            Video video;
            if (fitWorkout == null) {
                return null;
            }
            List<WorkoutAction> acquireWorkoutActions = fitWorkout.acquireWorkoutActions();
            if (koq.b(acquireWorkoutActions, 0) || (workoutAction = acquireWorkoutActions.get(0)) == null || workoutAction.getAction() == null) {
                return null;
            }
            List extendPropertyList = workoutAction.getAction().getExtendPropertyList("actionVideo", Video[].class);
            if (koq.b(extendPropertyList, 0) || (video = (Video) extendPropertyList.get(0)) == null) {
                return null;
            }
            return video;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Context context, String str, FitWorkout fitWorkout) {
            LogUtil.a("JumpUtil", "long video url: " + str);
            CoachData d = d(fitWorkout, true);
            if (!ffy.c(d)) {
                LogUtil.h("JumpUtil", "startLongCoachActivity mCoachData is null");
                return;
            }
            LogUtil.a("JumpUtil", "startLongCoachActivity isSupportDevices:", Integer.valueOf(fitWorkout.acquireIsSupportDevice()));
            cun.c().unregisterDataCallback(0);
            fot.a().a(fitWorkout);
            Intent intent = new Intent(context, (Class<?>) LongCoachActivity.class);
            intent.putExtra("islinkedfitness", b());
            intent.putExtra("isafterrun", false);
            List<Motion> acquireMotions = d.acquireMotions();
            if (koq.c(acquireMotions) && koq.d(acquireMotions, 0)) {
                d(fitWorkout, acquireMotions.get(0));
            }
            intent.putExtra(ContentTemplateRecord.MOTIONS, d);
            intent.putExtra("commodityFlag", fitWorkout.acquireCommodityFlag());
            intent.putExtra("long_coach_type", fitWorkout.getVideoProperty());
            intent.putExtra("long_coach_video_url", str);
            intent.putExtra("long_coach_show_action", fitWorkout.getWorkoutActionProperty());
            intent.putExtra("long_coach_need_calories", fitWorkout.getShowCalories());
            intent.putExtra("long_coach_need_countdown", fitWorkout.getShowCountdown());
            intent.putExtra("long_coach_need_heart_rate", fitWorkout.getShowHeartRate());
            intent.putExtra("havenexttrain", false);
            WorkoutRecord d2 = d(fitWorkout);
            intent.putExtra("coach_detail_name", !TextUtils.isEmpty(d2.acquireWorkoutName()) ? d2.acquireWorkoutName() : fitWorkout.acquireName());
            intent.putExtra("coach_detail_picture", FitWorkout.nullToStr(!TextUtils.isEmpty(fitWorkout.acquireMidPicture()) ? fitWorkout.acquireMidPicture() : fitWorkout.acquirePicture()));
            intent.putExtra("workoutrecord", d2);
            intent.putExtra("bundlekey", "");
            intent.putExtra("topic_name", "");
            intent.putExtra("entrance", "DailyMoment");
            intent.putExtra("ISPLANFIT", false);
            gnm.aPB_(context, intent);
        }

        private CoachData d(FitWorkout fitWorkout, boolean z) {
            CoachData coachData = new CoachData();
            if (fitWorkout == null) {
                return coachData;
            }
            coachData.saveWorkId(fitWorkout.acquireId());
            coachData.savePlanId("");
            coachData.saveDuration(fitWorkout.acquireDuration());
            coachData.saveMotions(c(fitWorkout, z));
            return coachData;
        }

        private List<Motion> c(FitWorkout fitWorkout, boolean z) {
            List<ChoreographedSingleAction> a2 = ggs.a(fitWorkout.acquireWorkoutActions());
            ArrayList arrayList = new ArrayList(10);
            if (z) {
                fpq.c(a2, fitWorkout.acquireId(), (ArrayList<Motion>) arrayList);
            } else {
                fpq.b(a2, ggg.a(), fitWorkout.acquireId(), arrayList);
            }
            return arrayList;
        }

        private void d(FitWorkout fitWorkout, Motion motion) {
            if (motion == null) {
                ReleaseLogUtil.d("JumpUtil", "setVideoSegmentIntoMotion mFitWorkout or motion null");
                return;
            }
            Video b = b(fitWorkout);
            if (b == null) {
                return;
            }
            List<VideoSegment> videoSegmentList = b.getVideoSegmentList();
            if (koq.c(videoSegmentList)) {
                motion.setVideoSegments(videoSegmentList);
            }
        }

        private boolean b() {
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "JumpUtil");
            if (deviceInfo != null) {
                Map<String, DeviceCapability> queryDeviceCapability = cun.c().queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "JumpUtil");
                if (queryDeviceCapability == null || !queryDeviceCapability.containsKey(deviceInfo.getDeviceIdentify())) {
                    LogUtil.h("JumpUtil", "deviceCapabilityHashMaps is empty or does not contain key:", deviceInfo.getDeviceIdentify());
                } else if (queryDeviceCapability.get(deviceInfo.getDeviceIdentify()) != null && queryDeviceCapability.get(deviceInfo.getDeviceIdentify()).isSupportPosture()) {
                    return true;
                }
            }
            return false;
        }

        public void d(Context context) {
            LogUtil.a("JumpUtil", "jumpToPlanTab");
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToPlanTab() context is null");
                return;
            }
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), "com.huawei.health.MainActivity");
            if ((context instanceof Activity) && ((Activity) context).getWindowManager().getDefaultDisplay().getRotation() == 1 && nsn.ae(BaseApplication.getContext())) {
                intent.setFlags(131072);
            } else {
                intent.setFlags(872415232);
            }
            intent.putExtra("isToSportTab", true);
            intent.putExtra(BleConstants.SPORT_TYPE, 2);
            intent.putExtra(Constants.HOME_TAB_NAME, "SPORT");
            gnm.aPB_(context, intent);
        }

        public void b(Context context) {
            LogUtil.a("JumpUtil", "jumpToMyPlanH5");
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToMyPlanH5() context is null");
                return;
            }
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (operationUtilsApi != null) {
                operationUtilsApi.initH5pro();
                H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().addPath("#/my_plan").setStatusBarTextBlack(true).setForceDarkMode(1).build();
                IntelligentPlanH5Repository.registerService();
                H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.ai-weight", build);
            }
        }

        public void a(Context context) {
            LogUtil.a("JumpUtil", "jumpToTrainingDaySettingsH5");
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToTrainingDaySettingsH5() context is null");
                return;
            }
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (operationUtilsApi != null) {
                operationUtilsApi.initH5pro();
                H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().addPath("#/set_training_day").setStatusBarTextBlack(true).setForceDarkMode(1).build();
                IntelligentPlanH5Repository.registerService();
                H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.ai-weight", build);
            }
        }

        public void d(int i, String str, String str2) {
            WeakReference<Context> weakReference = this.b;
            if (weakReference == null) {
                LogUtil.h("JumpUtil", "jumpToCreatePlan() mContextReference is null");
                return;
            }
            Context context = weakReference.get();
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToCreatePlan() context is null");
                e();
                return;
            }
            if (i == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
                e(str, context, false);
                return;
            }
            if (i == IntPlan.PlanType.FIT_PLAN.getType()) {
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (planApi == null) {
                    e();
                    LogUtil.h("JumpUtil", "initData : planApi is null.");
                    return;
                } else {
                    d(planApi.getFitnessPkgInfoByTempId(str), str, context, false);
                    return;
                }
            }
            if (i == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
                c(context, i, str, str2, "");
            } else {
                e();
                LogUtil.h("JumpUtil", "jumpToCreatePlan invalid param");
            }
        }

        public void c(Context context, int i, String str, String str2, String str3) {
            Activity wa_;
            LogUtil.a("JumpUtil", "jumpToCreateAiFitnessPlanH5");
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToCreateAiFitnessPlanH5() context is null");
                e();
                return;
            }
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (operationUtilsApi != null) {
                operationUtilsApi.initH5pro();
                H5ProLaunchOption.Builder forceDarkMode = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("weight", bzs.e().getCommonJsModule("weight")).addCustomizeArg("planType", String.valueOf(i)).addCustomizeArg("planTempId", str).addCustomizeArg("entrySource", str2).setImmerse().showStatusBar().addPath(ase.d() ? "#/recomend_infomation" : "#/plan_introduce").setStatusBarTextBlack(true).setForceDarkMode(1);
                if (!TextUtils.isEmpty(str2)) {
                    forceDarkMode.setStartSource(str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    forceDarkMode.setFromPageTitle(str3);
                }
                if (DeviceTypeConsts.WATCH.equals(str2)) {
                    forceDarkMode.setActivityStartFlag(AppRouterExtras.COLDSTART);
                    if (!(context instanceof Activity) && (wa_ = com.huawei.haf.application.BaseApplication.wa_()) != null) {
                        context = wa_;
                    }
                }
                WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
                if (weightApi != null) {
                    H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
                    H5ProServiceManager.getInstance().registerService(weightApi.getCustomRecipe());
                } else {
                    LogUtil.h("JumpUtil", "jumpToCreateAiFitnessPlanH5 weightApi is null.");
                }
                IntelligentPlanH5Repository.registerService();
                H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.ai-weight", forceDarkMode.build());
            }
            e();
        }

        public void c(Context context) {
            LogUtil.a("JumpUtil", "jumpToPlanDetailH5");
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToPlanDetailH5() context is null");
                return;
            }
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (operationUtilsApi != null) {
                operationUtilsApi.initH5pro();
                H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().addPath("#/program_details").setStatusBarTextBlack(true).setForceDarkMode(1).build();
                WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
                if (weightApi != null) {
                    H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
                } else {
                    LogUtil.h("JumpUtil", "weightApi is null.");
                }
                IntelligentPlanH5Repository.registerService();
                H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.ai-weight", build);
            }
        }

        public void d(Context context, double d, double d2, String str) {
            if (context == null) {
                LogUtil.h("JumpUtil", "joinInputActivity() context is null");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("isShowInput", true);
            bundle.putString("weight", String.valueOf(d));
            bundle.putString("bodyFat", String.valueOf(d2));
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("from", str);
                if (str.equals("/PluginHealthModel/IntelligentPlanView")) {
                    bundle.putBoolean("isFromPlan", true);
                }
            }
            AppRouter.b("/Main/InputWeightActivity").zF_(bundle).a(268435456).c(context);
        }

        public void e(Context context, String str, int i) {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), "com.huawei.health.device.ui.DeviceMainActivity");
            intent.putExtra("kind", str);
            intent.putExtra("view", "MeasureDevice");
            intent.putExtra("type", i);
            gnm.aPB_(context, intent);
        }

        public void c(int i, int i2, String str, int i3) {
            WeakReference<Context> weakReference = this.b;
            if (weakReference == null) {
                LogUtil.h("JumpUtil", "jumpToPlanReport() mContextReference is null");
                return;
            }
            Context context = weakReference.get();
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToPlanReport() context is null");
                return;
            }
            if (i2 == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
                LogUtil.h("JumpUtil", "jumpToPlanReport goto ai fitness");
                d(i, context, i2, str);
                return;
            }
            if (i2 == IntPlan.PlanType.AI_RUN_PLAN.getType() || (i2 == IntPlan.PlanType.FIT_PLAN.getType() && i3 == 2)) {
                Intent intent = new Intent(context, (Class<?>) MyPlanReportActivity.class);
                intent.putExtra("planId", str);
                intent.putExtra("plantype", i2);
                intent.setFlags(268435456);
                gnm.aPB_(context, intent);
                return;
            }
            if (i2 == IntPlan.PlanType.RUN_PLAN.getType()) {
                Intent intent2 = new Intent(context, (Class<?>) TrainEventActivity.class);
                intent2.setFlags(268435456);
                intent2.putExtra("planid", str);
                intent2.putExtra("plantype", 0);
                gnm.aPB_(context, intent2);
                return;
            }
            if (i2 == IntPlan.PlanType.FIT_PLAN.getType()) {
                Intent intent3 = new Intent(context, (Class<?>) TrainEventActivity.class);
                intent3.setFlags(268435456);
                intent3.putExtra("planid", str);
                intent3.putExtra("plantype", 3);
                gnm.aPB_(context, intent3);
                return;
            }
            LogUtil.h("JumpUtil", "jumpToPlanReport invalid param");
        }

        private void d(int i, Context context, int i2, String str) {
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (operationUtilsApi != null) {
                operationUtilsApi.initH5pro();
                H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().addCustomizeArg("reportType", String.valueOf(i)).addCustomizeArg("planType", String.valueOf(i2)).addCustomizeArg("planId", str).addCustomizeArg("from", String.valueOf(2)).addPath("#/weekly_report").setStatusBarTextBlack(true).build();
                IntelligentPlanH5Repository.registerService();
                H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.ai-weight", build);
            }
        }

        public void a(Context context, String str) {
            if (context == null) {
                LogUtil.h("JumpUtil", "context is null");
                return;
            }
            LogUtil.h("JumpUtil", "jumpToWeightDataActivity");
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("from", str);
            }
            grz.aSU_(str, bundle);
        }

        public void c(String str, String... strArr) {
            b("#/diet_recording_tool?from=3&date=" + (jec.e(str + " 00:00:00") / 1000), strArr);
        }

        public void c(long j, int i, int i2) {
            b("#/record_breakfast?date=" + (j / 1000) + "&whichMeal=" + i + "&index=" + i2, "weightControlPlan");
        }

        private void b(String str, String... strArr) {
            LogUtil.a("JumpUtil", "jumpToDietH5Page() path: ", str);
            bzs.e().initH5Pro();
            H5ProLaunchOption.Builder addCustomizeArg = new H5ProLaunchOption.Builder().addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL)).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().setStatusBarTextBlack(true).setNeedSoftInputAdapter().addCustomizeJsModule("DietKakaUtil", DietKakaUtil.class).addPath(str + "&from=3").addCustomizeArg("entrySource", "plan");
            if (strArr != null && strArr.length > 0) {
                addCustomizeArg.addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, strArr[0]);
            }
            WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
            if (weightApi == null) {
                return;
            }
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
            H5ProClient.startH5MiniProgram(com.huawei.haf.application.BaseApplication.e(), "com.huawei.health.h5.diet-diary", addCustomizeArg.build());
        }

        public void h(Context context) {
            if (gpo.b()) {
                LogUtil.a("JumpUtil", "jump to discover.");
                AppRouter.b("/home/main").e("openDiscover", true).c(com.huawei.haf.application.BaseApplication.e());
            } else {
                gpn.c(context, "from=7");
            }
        }

        public void e(int i, String str, int i2, String str2, String str3) {
            WeakReference<Context> weakReference = this.b;
            if (weakReference == null) {
                LogUtil.h("JumpUtil", "jumpToPlanFromDp() mContextReference is null");
                return;
            }
            Context context = weakReference.get();
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToPlanFromDp context == null");
                e();
                return;
            }
            if (TextUtils.isEmpty(str) || i == IntPlan.PlanType.RUN_PLAN.getType() || i == IntPlan.PlanType.NA_PLAN.getType()) {
                LogUtil.a("JumpUtil", "planTempId is empty or planType error ", Integer.valueOf(i), "pullFrom:", str2);
                d(context);
                if (i == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
                    ase.c(ftb.c(str2), 1, 1, 1);
                }
                e();
                return;
            }
            if (i == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
                a(i, str, str2, str3, context);
                return;
            }
            if (i == IntPlan.PlanType.FIT_PLAN.getType()) {
                if (FitnessExternalUtils.b() && i2 == 2) {
                    d(context, str, IntPlan.PlanType.FIT_PLAN.getType());
                    return;
                } else {
                    c(str, context);
                    return;
                }
            }
            if (i == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
                e(str, context);
            } else {
                d(context);
            }
        }

        public void a(IntPlan intPlan) {
            String str;
            String str2;
            String string;
            String string2;
            PlanPoster b = b(intPlan);
            if (b != null) {
                str = b.getMemberEquityPictureForPhone();
                str2 = b.getMemberEquityPictureForPad();
            } else {
                str = null;
                str2 = null;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                LogUtil.a("JumpUtil", "posterUrl:", str, " posterPadUrl:", str2);
                e(str, str2, intPlan.getMetaInfo().getName());
                return;
            }
            if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
                e(BaseApplication.getContext().getResources().getString(R.string._2130851522_res_0x7f0236c2), BaseApplication.getContext().getResources().getString(R.string._2130851521_res_0x7f0236c1), intPlan.getMetaInfo().getName());
                return;
            }
            if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
                if (Utils.o()) {
                    String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
                    string = e(accountInfo, false);
                    string2 = e(accountInfo, true);
                } else {
                    string = BaseApplication.getContext().getResources().getString(R.string._2130851515_res_0x7f0236bb);
                    string2 = BaseApplication.getContext().getResources().getString(R.string._2130851514_res_0x7f0236ba);
                }
                e(string, string2, intPlan.getMetaInfo().getName());
                return;
            }
            h(BaseApplication.getContext());
        }

        private PlanPoster b(IntPlan intPlan) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.h("JumpUtil", "getPlanPoster : planApi is null.");
                return null;
            }
            if (intPlan.getMetaInfo() == null) {
                LogUtil.h("JumpUtil", "getPlanPoster : getMetaInfo is null.");
                return null;
            }
            FitnessPackageInfo fitnessPkgInfoByTempId = planApi.getFitnessPkgInfoByTempId(intPlan.getMetaInfo().getPlanTempId());
            if (fitnessPkgInfoByTempId != null) {
                return fitnessPkgInfoByTempId.getPlanPoster();
            }
            return null;
        }

        private String e(String str, boolean z) {
            if (TextUtils.isEmpty(str)) {
                return BaseApplication.getContext().getResources().getString(R.string._2130851518_res_0x7f0236be);
            }
            if ("de".equals(str.toLowerCase(Locale.ENGLISH))) {
                if (z) {
                    return BaseApplication.getContext().getResources().getString(R.string._2130851516_res_0x7f0236bc);
                }
                return BaseApplication.getContext().getResources().getString(R.string._2130851517_res_0x7f0236bd);
            }
            if (!"it".equals(str.toLowerCase(Locale.ENGLISH))) {
                return BaseApplication.getContext().getResources().getString(R.string._2130851518_res_0x7f0236be);
            }
            if (z) {
                return BaseApplication.getContext().getResources().getString(R.string._2130851519_res_0x7f0236bf);
            }
            return BaseApplication.getContext().getResources().getString(R.string._2130851520_res_0x7f0236c0);
        }

        public void c(int i, String str) {
            if (i == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
                e(BaseApplication.getContext().getResources().getString(R.string._2130851522_res_0x7f0236c2), BaseApplication.getContext().getResources().getString(R.string._2130851521_res_0x7f0236c1), BaseApplication.getContext().getString(R.string._2130848558_res_0x7f022b2e));
                return;
            }
            if (this.b == null) {
                this.b = new WeakReference<>(BaseApplication.getContext());
            }
            d(i, str, "");
        }

        public void e(String str) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("JumpUtil", "jumpToVipBenefit path is empty");
                e();
                return;
            }
            if (gpo.b()) {
                LogUtil.a("JumpUtil", "jump to discover.");
                AppRouter.b("/home/main").e("openDiscover", true).c(com.huawei.haf.application.BaseApplication.e());
            } else {
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addPath(str);
                LogUtil.a("JumpUtil", str);
                bzs.e().loadH5ProApp(com.huawei.haf.application.BaseApplication.e(), "com.huawei.health.h5.vip", builder);
            }
            e();
        }

        public void e(String str, String str2, String str3) {
            e("#/PrivilegeDetail?detailImgUrl=" + str + "&detailBigImgUrl=" + str2 + "&title=" + str3);
        }

        public void d(JumpFinishCallback jumpFinishCallback) {
            this.e = new WeakReference<>(jumpFinishCallback);
        }

        public void e(JumpViewInterface jumpViewInterface) {
            this.c = new WeakReference<>(jumpViewInterface);
        }

        private void c(String str, Context context) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                e();
                LogUtil.h("JumpUtil", "initData : planApi is null.");
            } else {
                e(planApi.getFitnessPkgInfoByTempId(str), str, context);
            }
        }

        public void e(final FitnessPackageInfo fitnessPackageInfo, final String str, final Context context) {
            if (fitnessPackageInfo == null) {
                d(context);
                e();
            } else {
                ghq.d(new JudgeCallback() { // from class: ghh
                    @Override // com.huawei.health.suggestion.callback.JudgeCallback
                    public final void onJudgeCallback(Object obj) {
                        JumpUtil.JumpHelper.this.e(str, context, fitnessPackageInfo, (IntPlan) obj);
                    }
                });
            }
        }

        public /* synthetic */ void e(String str, Context context, FitnessPackageInfo fitnessPackageInfo, IntPlan intPlan) {
            if (intPlan != null && TextUtils.equals(intPlan.getPlanTempId(), str)) {
                d(context);
                e();
            } else {
                d(fitnessPackageInfo, str, context, intPlan != null);
            }
        }

        private void d(final FitnessPackageInfo fitnessPackageInfo, final String str, final Context context, final boolean z) {
            if (fitnessPackageInfo.getCommodityFlag() == 0) {
                c(context, str);
            } else {
                ResourceJudgeUtil.c(2, str, fitnessPackageInfo.getCommodityFlag() == 1 ? 2 : 3, new JudgeCallback() { // from class: ghg
                    @Override // com.huawei.health.suggestion.callback.JudgeCallback
                    public final void onJudgeCallback(Object obj) {
                        JumpUtil.JumpHelper.this.a(z, context, str, fitnessPackageInfo, (Integer) obj);
                    }
                });
            }
        }

        public /* synthetic */ void a(boolean z, Context context, String str, FitnessPackageInfo fitnessPackageInfo, Integer num) {
            LogUtil.b("JumpUtil", "jumpFitPlanWithVipJudge: resourceJudge result is ", num, "hasCurrentPlan = ", Boolean.valueOf(z));
            if (num.intValue() == 0) {
                c(context, str);
                e();
                return;
            }
            if ((num.intValue() == 2 && fitnessPackageInfo.getCommodityFlag() == 2) || num.intValue() == 3) {
                c(ghy.c(fitnessPackageInfo, str));
                e();
                return;
            }
            if (num.intValue() == 2) {
                b(IntPlan.PlanType.FIT_PLAN.getType(), context, str);
            } else if (z) {
                b(IntPlan.PlanType.FIT_PLAN.getType(), context, str);
            } else {
                c(context, str);
            }
            e();
        }

        public void c(String str) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("JumpUtil", "jumpToVipBenefit path is empty");
                e();
                return;
            }
            if (gpo.b()) {
                LogUtil.a("JumpUtil", "jump to member relay.");
                AppRouter.b("/OperationBundle/MemberRelayActivity").c(BaseApplication.getContext());
            } else {
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addPath(str);
                LogUtil.a("JumpUtil", str);
                bzs.e().loadH5ProApp(com.huawei.haf.application.BaseApplication.e(), "com.huawei.health.h5.vip", builder);
            }
            e();
        }

        public void b(Map<String, String> map) {
            WeakReference<Context> weakReference = this.b;
            if (weakReference == null) {
                LogUtil.h("JumpUtil", "jumpToPlanListH5() mContextReference is null");
                return;
            }
            Context context = weakReference.get();
            if (context == null) {
                LogUtil.h("JumpUtil", "jumpToPlanListH5() context is null");
                return;
            }
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (operationUtilsApi != null) {
                operationUtilsApi.initH5pro();
                H5ProLaunchOption a2 = a("#/train_plan_list", map);
                IntelligentPlanH5Repository.registerService();
                WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
                if (weightApi != null) {
                    H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
                } else {
                    LogUtil.h("JumpUtil", "jumpToPlanListH5 weightApi is null.");
                }
                H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.ai-weight", a2);
            }
        }

        public void e(final String str, final Context context) {
            ghq.d(new JudgeCallback() { // from class: ghn
                @Override // com.huawei.health.suggestion.callback.JudgeCallback
                public final void onJudgeCallback(Object obj) {
                    JumpUtil.JumpHelper.this.d(str, context, (IntPlan) obj);
                }
            });
        }

        public /* synthetic */ void d(String str, Context context, IntPlan intPlan) {
            if (intPlan != null && TextUtils.equals(intPlan.getPlanTempId(), str)) {
                d(context);
                e();
            } else {
                e(str, context, intPlan != null);
            }
        }

        private void e(final String str, final Context context, final boolean z) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("JumpUtil", "Jump getPlanData : planApi is null.");
                c(context, (mmw) null);
                e();
                return;
            }
            planApi.getAllPlans(0, new UiCallback<mnw>() { // from class: com.huawei.health.suggestion.util.JumpUtil.JumpHelper.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("JumpUtil", "No current plan now.", Integer.valueOf(i), " ", str2);
                    JumpHelper.this.c(context, (mmw) null);
                    JumpHelper.this.e();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mnw mnwVar) {
                    if (mnwVar == null || koq.b(mnwVar.b())) {
                        JumpHelper.this.c(context, (mmw) null);
                        JumpHelper.this.e();
                    } else {
                        JumpHelper.this.d(ghq.a(str, mnwVar.b()), str, context, z);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(final mmw mmwVar, final String str, final Context context, final boolean z) {
            if (mmwVar == null) {
                c(context, (mmw) null);
                e();
                return;
            }
            if (Utils.o()) {
                if (z) {
                    f(context);
                    return;
                } else {
                    a(mmwVar, context);
                    return;
                }
            }
            if (mmwVar.getCommodityFlag() == 0) {
                a(mmwVar, str, context, z);
                e();
            } else {
                int c = mmwVar.c();
                ResourceJudgeUtil.c(5, String.valueOf(c), 3, new JudgeCallback() { // from class: ghl
                    @Override // com.huawei.health.suggestion.callback.JudgeCallback
                    public final void onJudgeCallback(Object obj) {
                        JumpUtil.JumpHelper.this.d(str, mmwVar, context, z, (Integer) obj);
                    }
                });
            }
        }

        public /* synthetic */ void d(String str, mmw mmwVar, Context context, boolean z, Integer num) {
            if (num.intValue() == 3 || num.intValue() == 2) {
                c(IntPlan.PlanType.AI_RUN_PLAN.getType(), str);
            } else {
                a(mmwVar, str, context, z);
            }
            e();
        }

        private void f(final Context context) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
            builder.e(R.string._2130848610_res_0x7f022b62);
            builder.czC_(R.string._2130848480_res_0x7f022ae0, new View.OnClickListener() { // from class: com.huawei.health.suggestion.util.JumpUtil$JumpHelper$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JumpUtil.JumpHelper.aMG_(context, view);
                }
            }).czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: ghm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: ghj
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    JumpUtil.JumpHelper.this.aMK_(dialogInterface);
                }
            });
            e.show();
            c();
        }

        static /* synthetic */ void aMG_(Context context, View view) {
            JumpUtil.c(context);
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void aMK_(DialogInterface dialogInterface) {
            e();
        }

        public void a(final mmw mmwVar, final Context context) {
            c();
            final String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
            if (TextUtils.isEmpty(SharedPreferenceManager.b(context, Integer.toString(101010), accountInfo + "agree_plan_privacy_key"))) {
                NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(R.string._2130848706_res_0x7f022bc2)).czE_(context.getString(R.string._2130843454_res_0x7f02173e).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: ghi
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        JumpUtil.JumpHelper.this.aMM_(context, accountInfo, mmwVar, view);
                    }
                }).czA_(context.getString(R.string._2130839509_res_0x7f0207d5).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: ghe
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        JumpUtil.JumpHelper.this.aML_(view);
                    }
                }).e();
                e.setCancelable(false);
                e.show();
                return;
            }
            c(mmwVar, context);
        }

        public /* synthetic */ void aMM_(Context context, String str, mmw mmwVar, View view) {
            SharedPreferenceManager.e(context, Integer.toString(101010), str + "agree_plan_privacy_key", "agree_plan_privacy_key", new StorageParams());
            c(mmwVar, context);
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void aML_(View view) {
            e();
            ViewClickInstrumentation.clickOnView(view);
        }

        private void c(final mmw mmwVar, final Context context) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.sug_remind_dialog_view, (ViewGroup) null);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_tv_message);
            healthTextView.setMovementMethod(new ScrollingMovementMethod());
            healthTextView.setText(context.getString(R.string._2130848709_res_0x7f022bc5) + System.lineSeparator() + context.getString(R.string._2130848710_res_0x7f022bc6) + System.lineSeparator() + context.getString(R.string._2130848711_res_0x7f022bc7));
            healthTextView.setHyphenationFrequency(1);
            healthTextView.setBreakStrategy(1);
            CustomViewDialog.Builder czc_ = new CustomViewDialog.Builder(context).d(R.string._2130848708_res_0x7f022bc4).czg_(inflate).cze_(R.string._2130843454_res_0x7f02173e, new View.OnClickListener() { // from class: gha
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JumpUtil.JumpHelper.this.aMN_(context, mmwVar, view);
                }
            }).czc_(R.string._2130841129_res_0x7f020e29, new View.OnClickListener() { // from class: ghf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JumpUtil.JumpHelper.this.aMO_(view);
                }
            });
            ((HealthTextView) inflate.findViewById(R.id.dialog_tv_agree)).setText(context.getResources().getString(R.string._2130848763_res_0x7f022bfb));
            CustomViewDialog e = czc_.e();
            e.setCancelable(false);
            e.show();
        }

        public /* synthetic */ void aMN_(Context context, mmw mmwVar, View view) {
            c(context, mmwVar);
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void aMO_(View view) {
            e();
            ViewClickInstrumentation.clickOnView(view);
        }

        private void a(mmw mmwVar, String str, Context context, boolean z) {
            if (z) {
                b(IntPlan.PlanType.AI_RUN_PLAN.getType(), context, str);
            } else {
                c(context, mmwVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(Context context, mmw mmwVar) {
            Intent intent;
            if (mmwVar == null) {
                intent = new Intent(context, (Class<?>) AiPlanActivity.class);
                intent.putExtra("plantype", 2);
            } else {
                HashMap hashMap = new HashMap(5);
                hashMap.put("click", 1);
                hashMap.put("type", 2);
                hashMap.put("planType", 0);
                hashMap.put("planName", mmwVar.i());
                hashMap.put("enter", "0");
                gge.e(AnalyticsValue.HEALTH_HOME_TRAINING_PROGRAM_PIC_2010036.value(), hashMap);
                Intent intent2 = new Intent(context, (Class<?>) RunPlanCreateActivity.class);
                intent2.putExtra("runPlanTitle", mmwVar.i());
                intent2.putExtra("runPlanType", mmwVar.c());
                intent = intent2;
            }
            intent.setFlags(268435456);
            gnm.aPB_(context, intent);
            e();
        }

        private void c(Context context, String str) {
            if (TextUtils.isEmpty(str)) {
                e();
                LogUtil.b("JumpUtil", "jumpToFitnessPlanCreateView : planTempId is empty");
                return;
            }
            Intent intent = new Intent(context, (Class<?>) FitnessPlanJoinActivity.class);
            intent.putExtra("PLAN_TEMP_ID_KEY", str);
            intent.setFlags(268435456);
            gnm.aPB_(context, intent);
            if (context instanceof Activity) {
                if (LanguageUtil.bc(context)) {
                    ((Activity) context).overridePendingTransition(R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f);
                } else {
                    ((Activity) context).overridePendingTransition(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
                }
            }
            e();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i, Context context, String str) {
            Intent intent = new Intent(context, (Class<?>) FitnessPlanDetailActivity.class);
            intent.putExtra("params_plan_id_key", str);
            intent.putExtra("params_plan_TYPE_key", i);
            intent.setFlags(872415232);
            gnm.aPB_(context, intent);
            e();
        }

        private void a(final int i, final String str, final String str2, final String str3, final Context context) {
            ghq.d(new JudgeCallback() { // from class: ghc
                @Override // com.huawei.health.suggestion.callback.JudgeCallback
                public final void onJudgeCallback(Object obj) {
                    JumpUtil.JumpHelper.this.d(str, context, i, str2, str3, (IntPlan) obj);
                }
            });
        }

        public /* synthetic */ void d(String str, Context context, int i, String str2, String str3, IntPlan intPlan) {
            if (intPlan != null && TextUtils.equals(intPlan.getPlanTempId(), str)) {
                d(context);
                LogUtil.a("JumpUtil", "jumpToAiFitnessPlan jumpToPlanTab.");
                i(context);
            } else {
                c(context, i, str, str2, str3);
                LogUtil.a("JumpUtil", "jumpToAiFitnessPlan jumpToCreateAiFitnessPlanH5.");
            }
            e();
        }

        private void i(Context context) {
            if ("1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "MAIN_VIP_KEY"))) {
                bzw.e().finishKakaTask(context, 20009, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            WeakReference<JumpFinishCallback> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("JumpUtil", "onJumpFinish mJumpFinishCallback is null");
                return;
            }
            JumpFinishCallback jumpFinishCallback = weakReference.get();
            if (jumpFinishCallback == null) {
                LogUtil.h("JumpUtil", "onJumpFinish jumpFinishCallback is null");
            } else {
                jumpFinishCallback.onJumpFinish();
                this.e = null;
            }
        }

        private void c() {
            WeakReference<JumpViewInterface> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.h("JumpUtil", "hideProgressBar: jumpViewController is null");
                return;
            }
            JumpViewInterface jumpViewInterface = weakReference.get();
            if (jumpViewInterface == null) {
                LogUtil.h("JumpUtil", "hideProgressBar: jumpViewController is null");
            } else {
                jumpViewInterface.setProgressBarVisible(false);
            }
        }

        private H5ProLaunchOption a(String str, Map<String, String> map) {
            if (map != null && !map.isEmpty()) {
                StringBuilder sb = new StringBuilder(str + "?");
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append("&");
                }
                str = sb.substring(0, sb.length() - 1);
            }
            H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().addPath(str).setStatusBarTextBlack(true).setForceDarkMode(1).build();
            LogUtil.a("JumpUtil", "buildCommonJumpH5Option option is " + build.getPath());
            return build;
        }

        public void c(String str, int i) {
            LogUtil.a("JumpUtil", "gotoSeriesCourseH5 seriesCourseId ", str, " type ", Integer.valueOf(i));
            bzs.e().initH5Pro();
            H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("mediaCenter", bzs.e().getCommonJsModule("mediaCenter")).enableOnPauseCallback().setImmerse().showStatusBar().setStatusBarTextBlack(true).addPath("#/seriesCourse?id=" + str + "&type=" + i).setForceDarkMode(1).build();
            SeriesCourseH5Repository.registerService();
            H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.sleeping-music", build);
            ary.a().e("COLLECTION_ADD");
        }
    }
}
