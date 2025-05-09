package com.huawei.health.suggestion.h5pro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.model.data.RecordPlanInfo;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.h5pro.CourseControlManager;
import com.huawei.health.suggestion.ui.fitness.activity.CoachActivity;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.health.suggestion.ui.fitness.callback.TrainDetailConnectCallback;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.tencent.connect.share.QzonePublish;
import defpackage.eme;
import defpackage.ffy;
import defpackage.fnq;
import defpackage.fny;
import defpackage.fop;
import defpackage.fot;
import defpackage.fpq;
import defpackage.fqw;
import defpackage.fqy;
import defpackage.ggg;
import defpackage.ggr;
import defpackage.gnm;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.mxb;
import defpackage.mxc;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class CourseControlManager {
    public static final int COMMON_COURSE_TYPE = 0;
    private static final int COURSE_TYPE = -1;
    private static final int HISTORY_ENTRY = 1;
    public static final int RESULT_SUCCESS = 0;
    public static final int SERIES_COURSE_TYPE = 1;
    private static final String TAG = "Suggestion_CourseControlManager";
    public static final int THE_FIRST_OF_LIST_NUMBER = 0;
    private static final int WORKOUT_PAGE = 2;

    public static void startLongCoachActivity(Context context, FitWorkout fitWorkout, mmp mmpVar) {
        if (fitWorkout == null || mmpVar == null || TextUtils.isEmpty(mmpVar.i()) || context == null) {
            ReleaseLogUtil.d(TAG, "startLongCoachActivity fitWorkout == null || courseJumpParameter == null, context:", context);
            return;
        }
        ReleaseLogUtil.e(TAG, "startLongCoachActivity");
        eme.b().pourInterfaceToKitSportApi();
        WorkoutRecord c = mod.c(fitWorkout, mmpVar);
        CoachData coachData = getCoachData(fitWorkout, 1, mmpVar.m());
        startLongCoachPreAction(context, fitWorkout, c, coachData, mmpVar.ae());
        Intent intent = new Intent(context, (Class<?>) LongCoachActivity.class);
        intent.putExtra("isafterrun", mmpVar.aa());
        LogUtil.a(TAG, "startLongCoachActivity is screen recording = ", Integer.valueOf(fitWorkout.getAntiScreenRecording()));
        intent.putExtra("IS_SUPPORT_RECORD", fitWorkout.getAntiScreenRecording());
        intent.putExtra("equipment_course", fitWorkout.isEquipmentCourse());
        intent.putExtra(ContentTemplateRecord.MOTIONS, coachData);
        intent.putExtra("commodityFlag", fitWorkout.acquireCommodityFlag());
        intent.putExtra("workout_package_id", mmpVar.ad());
        intent.putExtra("course_belong_type", getCourseBelongType(mmpVar.ad()));
        intent.putExtra("workoutrecord", c);
        intent.putExtra("long_coach_type", fitWorkout.getVideoProperty());
        intent.putExtra("long_coach_video_url", mmpVar.i());
        String extendPropertyString = fitWorkout.getExtendPropertyString("resourceType");
        LogUtil.a(TAG, "resourceType is ", extendPropertyString);
        if (!TextUtils.isEmpty(extendPropertyString)) {
            intent.putExtra("resource_type", extendPropertyString);
        }
        intent.putExtra("havenexttrain", mmpVar.ai());
        intent.putExtra(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, fitWorkout.acquireDuration());
        intent.putExtra("long_coach_subtitle_url", mmpVar.s());
        intent.putExtra("long_coach_show_action", fitWorkout.getWorkoutActionProperty());
        intent.putExtra("long_coach_need_calories", fitWorkout.getShowCalories());
        intent.putExtra("long_coach_need_countdown", fitWorkout.getShowCountdown());
        intent.putExtra("long_coach_need_heart_rate", fitWorkout.getShowHeartRate());
        intent.putExtra("plan_execute_time", mmpVar.f());
        intent.putExtra("coach_detail_name", fitWorkout.acquireName());
        intent.putExtra("coach_detail_picture", StringUtils.c((Object) (!TextUtils.isEmpty(fitWorkout.acquireMidPicture()) ? fitWorkout.acquireMidPicture() : fitWorkout.acquirePicture())));
        intent.putExtra("BI_INTENT_COURSE", (Serializable) getIntentCourseBiMap(fitWorkout, mmpVar));
        intent.putExtra("moveTaskToBack", mmpVar.ag());
        recordTrainEvent(4, fitWorkout, mmpVar.ad());
        putEntranceData(intent, mmpVar);
        startLongCoachAfterAction(fitWorkout, mmpVar);
        gnm.aPB_(context, intent);
    }

    public static CoachData getCoachData(FitWorkout fitWorkout, int i, String str) {
        ArrayList arrayList = new ArrayList(fpq.d(fitWorkout, i));
        CoachData coachData = new CoachData();
        coachData.saveWorkId(fitWorkout.acquireId());
        coachData.savePlanId(str);
        coachData.saveMotions(arrayList);
        coachData.saveDuration(fitWorkout.acquireDuration());
        coachData.setBackgroundMusicUrl(fitWorkout.getBackgroundMusic());
        coachData.setCalorieStartTime(fitWorkout.getCalorieStartTime());
        coachData.setPrimaryClassify(fitWorkout.getPrimaryClassify());
        return coachData;
    }

    public static int getCourseBelongType(String str) {
        return TextUtils.isEmpty(str) ? 0 : 1;
    }

    public static int getRecordType(String str) {
        return TextUtils.isEmpty(str) ? 0 : 1;
    }

    private static Map<String, Object> getIntentCourseBiMap(FitWorkout fitWorkout, mmp mmpVar) {
        HashMap hashMap = new HashMap(getBiMapData(fitWorkout, mmpVar));
        if (fitWorkout != null) {
            String a2 = ffy.a(fitWorkout.acquireClasses());
            if (TextUtils.isEmpty(a2)) {
                a2 = ffy.c(fitWorkout.getPrimaryClassify());
            }
            hashMap.put("course_type", a2);
            hashMap.put("body_position", ffy.e(fitWorkout.acquireTrainingpoints()));
            hashMap.put("course_rate", String.valueOf(fitWorkout.acquireDifficulty()));
            hashMap.put("workout_name", fitWorkout.acquireName());
            hashMap.put("workout_id", fitWorkout.acquireId());
        }
        return hashMap;
    }

    public static Map<String, Object> getBiMapData(FitWorkout fitWorkout, mmp mmpVar) {
        if (fitWorkout == null || mmpVar == null) {
            LogUtil.a(TAG, "getBiMapData fitWorkout == null || courseJumpParameter == null");
            return new HashMap();
        }
        HashMap hashMap = new HashMap(getBiMap(mmpVar));
        hashMap.put("resourceType", Integer.valueOf(fpq.b(fitWorkout)));
        hashMap.put("entrance", mmpVar.a());
        hashMap.put("supportDevice", Integer.valueOf(CourseEquipmentConnectionTipsUtil.c(CourseEquipmentConnectionTipsUtil.d(fitWorkout, mmpVar.c()))));
        return hashMap;
    }

    public static Map<String, Object> getBiMap(mmp mmpVar) {
        if (mmpVar == null) {
            LogUtil.h(TAG, "getBiMap courseJumpParameter == null");
            return new HashMap();
        }
        HashMap hashMap = new HashMap(6);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, mmpVar.k());
        hashMap.put("resourceId", mmpVar.r());
        hashMap.put("resourceName", mmpVar.t());
        hashMap.put("pullOrder", mmpVar.o());
        hashMap.put("algId", mmpVar.b());
        hashMap.put("promoteTag", mmpVar.n());
        hashMap.put(x2.AB_INFO, mmpVar.d());
        hashMap.put("itemId", mmpVar.g());
        return hashMap;
    }

    public static void startLongCoachPreAction(Context context, FitWorkout fitWorkout, WorkoutRecord workoutRecord, CoachData coachData, boolean z) {
        fot.a().a(fitWorkout);
        if (fitWorkout.isLongExplainVideoCourse()) {
            CoachController.d().e(false);
        } else {
            getCourseDetailDeviceHelper(context, fitWorkout, workoutRecord, z).b(true);
        }
        workoutRecord.saveExerciseTime(System.currentTimeMillis());
        workoutRecord.saveCategory(fitWorkout.getCategory());
        List<Motion> acquireMotions = coachData.acquireMotions();
        if (!fitWorkout.isEquipmentCourse() && koq.d(acquireMotions, 0)) {
            setVideoSegmentIntoMotion(fitWorkout, acquireMotions.get(0));
        }
        if (fitWorkout.isRealSceneCourse()) {
            fop.b(fitWorkout.getRoadBookList());
        }
        LogUtil.a(TAG, "startLongCoachPreAction isRealSceneCourse:", Boolean.valueOf(fitWorkout.isRealSceneCourse()), " isEquipmentCourse:", Boolean.valueOf(fitWorkout.isEquipmentCourse()), " isLongExplainVideoCourse:", Boolean.valueOf(fitWorkout.isLongExplainVideoCourse()));
    }

    private static fny getCourseDetailDeviceHelper(Context context, FitWorkout fitWorkout, WorkoutRecord workoutRecord, boolean z) {
        fny fnyVar = new fny(new Handler(), context);
        fnyVar.c(fitWorkout);
        fnyVar.c(workoutRecord);
        ReleaseLogUtil.e(TAG, "startCoach activity isDeviceTrain:", Boolean.valueOf(z));
        fnyVar.d(z);
        return fnyVar;
    }

    private static void setVideoSegmentIntoMotion(FitWorkout fitWorkout, Motion motion) {
        WorkoutAction workoutAction;
        Video video;
        if (fitWorkout == null || motion == null) {
            ReleaseLogUtil.d(TAG, "setVideoSegmentIntoMotion mFitWorkout or motion null");
            return;
        }
        List<WorkoutAction> acquireWorkoutActions = fitWorkout.acquireWorkoutActions();
        if (koq.b(acquireWorkoutActions, 0) || (workoutAction = acquireWorkoutActions.get(0)) == null || workoutAction.getAction() == null) {
            return;
        }
        List extendPropertyList = workoutAction.getAction().getExtendPropertyList("actionVideo", Video[].class);
        if (koq.b(extendPropertyList, 0) || (video = (Video) extendPropertyList.get(0)) == null) {
            return;
        }
        List<VideoSegment> videoSegmentList = video.getVideoSegmentList();
        if (koq.c(videoSegmentList)) {
            motion.setVideoSegments(videoSegmentList);
        }
    }

    private static void putEntranceData(Intent intent, mmp mmpVar) {
        if (mmpVar != null) {
            try {
                intent.putExtra("bundlekey", mod.cmz_(mmpVar));
                intent.putExtra("topic_name", mmpVar.q());
                intent.putExtra("entrance", mmpVar.a());
                intent.putExtra("ISPLANFIT", mmpVar.am());
            } catch (BadParcelableException e) {
                LogUtil.b(TAG, "startCoachActivity putEntranceData", LogAnonymous.b((Throwable) e));
            }
        }
    }

    public static void recordTrainEvent(int i, FitWorkout fitWorkout, String str) {
        CourseApi courseApi = (CourseApi) Services.c("CoursePlanService", CourseApi.class);
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveWorkoutId(fitWorkout.acquireId());
        workoutRecord.saveVersion(fitWorkout.accquireVersion());
        workoutRecord.saveCourseBelongType(getCourseBelongType(str));
        workoutRecord.saveWorkoutPackageId(str);
        if (i == 0) {
            courseApi.postUserCourse(workoutRecord, 0);
            return;
        }
        if (!Utils.o()) {
            courseApi.postUserCourse(workoutRecord, 4);
        }
        if (fitWorkout.isLongExplainVideoCourse()) {
            courseApi.postUserCourse(workoutRecord, 1);
        }
    }

    public static void startCoachActivity(Context context, final FitWorkout fitWorkout, final mmp mmpVar, boolean z) {
        ReleaseLogUtil.e(TAG, "startCoachActivity");
        if (fitWorkout == null || mmpVar == null || context == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "startCoachActivity fitWorkout == null || courseJumpParameter == null, context:";
            objArr[1] = Boolean.valueOf(context == null);
            ReleaseLogUtil.d(TAG, objArr);
            return;
        }
        WorkoutRecord c = mod.c(fitWorkout, mmpVar);
        int gender = getGender(fitWorkout);
        CoachData coachData = getCoachData(fitWorkout, gender, mmpVar.m());
        if (koq.b(coachData.acquireMotions())) {
            ReleaseLogUtil.d(TAG, "startCoachActivity mCoachData.acquireMotions() empty gender", Integer.valueOf(gender));
            return;
        }
        eme.b().pourInterfaceToKitSportApi();
        fot.a().a(fitWorkout);
        getCourseDetailDeviceHelper(context, fitWorkout, c, mmpVar.ae()).b(false);
        Intent intent = new Intent(context, (Class<?>) CoachActivity.class);
        LogUtil.a(TAG, "startCoachActivity is screen recording = ", Integer.valueOf(fitWorkout.getAntiScreenRecording()));
        intent.putExtra("IS_SUPPORT_RECORD", fitWorkout.getAntiScreenRecording());
        intent.putExtra("BASE_AUDIO_TIMBRE", fitWorkout.getTimbre());
        intent.putExtra("isafterrun", mmpVar.aa());
        intent.putExtra(ContentTemplateRecord.MOTIONS, coachData);
        intent.putExtra("coachstartposition", 0);
        intent.putExtra("workout_package_id", mmpVar.ad());
        intent.putExtra("course_belong_type", getCourseBelongType(mmpVar.ad()));
        intent.putExtra("havenexttrain", mmpVar.ai());
        intent.putExtra("workoutrecord", c);
        intent.putExtra("plan_execute_time", mmpVar.f());
        intent.putExtra("BI_INTENT_COURSE", (Serializable) getIntentCourseBiMap(fitWorkout, mmpVar));
        intent.putExtra("moveTaskToBack", mmpVar.ag());
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", z ? CourseEquipmentConnectionTipsUtil.d(fitWorkout, mmpVar.c()) : 0);
        intent.putExtra("commodityFlag", fitWorkout.acquireCommodityFlag());
        intent.putExtra("BI_INTENT_COURSE", (Serializable) getIntentCourseBiMap(fitWorkout, mmpVar));
        putEntranceData(intent, mmpVar);
        gnm.aPB_(context, intent);
        ggr.e(fitWorkout);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.suggestion.h5pro.CourseControlManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CourseControlManager.lambda$startCoachActivity$0(FitWorkout.this, mmpVar);
            }
        });
        fnq.a(fitWorkout);
    }

    static /* synthetic */ void lambda$startCoachActivity$0(FitWorkout fitWorkout, mmp mmpVar) {
        recordTrainEvent(4, fitWorkout, mmpVar.ad());
        ggr.e(fitWorkout, getBiMapData(fitWorkout, mmpVar), false);
    }

    private static int getGender(FitWorkout fitWorkout) {
        int a2 = ggg.a();
        LogUtil.a(TAG, "getGender: ", Integer.valueOf(a2));
        return fitWorkout.getCourseAttr() != 2 ? fitWorkout.getCourseAttr() : a2;
    }

    public static mmp buildCommonParameter(fqy fqyVar, String str, boolean z) {
        if (fqyVar == null) {
            ReleaseLogUtil.d(TAG, "buildCommonParameter trainDetailIntentData == null || courseJumpParameter == null");
            return new mmp("");
        }
        mmp mmpVar = new mmp(fqyVar.w());
        mmpVar.h(str);
        mmpVar.e(z);
        mmpVar.b(fqyVar.aa());
        mmpVar.q(fqyVar.x());
        mmpVar.e(fqyVar.j());
        mmpVar.j(fqyVar.k());
        mmpVar.o(fqyVar.o());
        mmpVar.k(fqyVar.r());
        mmpVar.l(fqyVar.n());
        mmpVar.b(fqyVar.b());
        mmpVar.f(fqyVar.l());
        mmpVar.c(fqyVar.e());
        mmpVar.e(fqyVar.h());
        mmpVar.f(fqyVar.ad());
        mmpVar.d(fqyVar.a());
        mmpVar.a(fqyVar.i());
        mmpVar.r(fqyVar.y());
        mmpVar.h(fqyVar.ac());
        mmpVar.n(fqyVar.q());
        mmpVar.j(fqyVar.ai());
        mmpVar.b(fqyVar.s());
        mmpVar.a(fqyVar.t());
        mmpVar.a(fqyVar.u());
        return mmpVar;
    }

    public static void devicesConnectDialogPositive(Context context, FitWorkout fitWorkout, mmp mmpVar, ArrayList<WorkoutRecord> arrayList) {
        if (fitWorkout == null || mmpVar == null) {
            ReleaseLogUtil.d(TAG, "devicesConnectDialogPositive fitWorkout == null || courseJumpParameter == nul");
            return;
        }
        WorkoutRecord c = mod.c(fitWorkout, mmpVar);
        CoachData coachData = getCoachData(fitWorkout, 1, mmpVar.m());
        startLongCoachPreAction(context, fitWorkout, c, coachData, mmpVar.ae());
        fqw fqwVar = new fqw();
        CourseEquipmentConnectionTipsUtil.e(mmpVar, fqwVar, fitWorkout, coachData, 0);
        fqwVar.d(mmpVar.ad());
        fqwVar.a(getCourseBelongType(mmpVar.ad()));
        if (arrayList == null) {
            arrayList = new ArrayList<>(1);
            arrayList.add(c);
        }
        CourseEquipmentConnectionTipsUtil.a(fqwVar, fitWorkout.getWorkoutActionProperty(), mmpVar.i(), arrayList, c);
    }

    public static void startAiCourse(Context context, FitWorkout fitWorkout, mmp mmpVar, boolean z) {
        if (fitWorkout == null || mmpVar == null) {
            ReleaseLogUtil.d(TAG, "startAiCourse fitWorkout == null || courseJumpParameter == null");
            return;
        }
        Bundle bundle = new Bundle();
        IntPlan currentIntPlan = ((PlanApi) Services.c("CoursePlanService", PlanApi.class)).getCurrentIntPlan();
        WorkoutRecord c = mod.c(fitWorkout, mmpVar);
        if (!TextUtils.isEmpty(mmpVar.m())) {
            c.setPlanTrainDate(DateFormatUtil.b(mmpVar.f()));
        }
        bundle.putString("plan_name", fpq.b(currentIntPlan, mmpVar.m()) ? currentIntPlan.getMetaInfo().getName() : "");
        bundle.putInt("type", fpq.b(currentIntPlan, mmpVar.m()) ? currentIntPlan.getPlanType().getType() : -1);
        if ("FitnessResult".equals(mmpVar.a())) {
            bundle.putInt("pullFrom", 1);
        } else {
            bundle.putInt("pullFrom", 2);
        }
        bundle.putParcelable(NotificationCompat.CATEGORY_WORKOUT, fitWorkout);
        bundle.putString("workid", fitWorkout.acquireId());
        bundle.putString("planid", mmpVar.m());
        bundle.putLong("planstarttime", mmpVar.f());
        bundle.putString("entrance", mmpVar.a());
        bundle.putString("biPullfrom", mmpVar.k());
        bundle.putString("promoteTag", mmpVar.n());
        bundle.putInt("resourceType", fpq.b(fitWorkout));
        bundle.putBoolean("isFirstDownload", z);
        LogUtil.a(TAG, "isFirstDownload:", Boolean.valueOf(z));
        recordTrainEvent(4, fitWorkout, mmpVar.ad());
        JumpUtil.aMD_(context, c, bundle);
        fny fnyVar = new fny(new Handler(), context);
        fnyVar.c(fitWorkout);
        fnyVar.c(c);
        fnyVar.c(false);
    }

    public static void checkMultilingualAudio(Context context, FitWorkout fitWorkout, final UiCallback<Integer> uiCallback) {
        if (fitWorkout == null) {
            ReleaseLogUtil.d(TAG, "checkMultilingualAudio fitWorkout == null");
            return;
        }
        if (!Utils.o()) {
            uiCallback.onSuccess(0);
            return;
        }
        AudioResProviderInterface audioResProviderInterface = (AudioResProviderInterface) AppRouter.e(AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_DOWNLOAD, AudioResProviderInterface.class);
        boolean c = mxb.a().c(context);
        String str = fitWorkout.isRunModelCourse() ? "Sport" : SingleDailyMomentContent.COURSE_TYPE;
        Object[] objArr = new Object[4];
        objArr[0] = "service = ";
        objArr[1] = Boolean.valueOf(audioResProviderInterface != null);
        objArr[2] = "isEnableCurLang = ";
        objArr[3] = Boolean.valueOf(c);
        ReleaseLogUtil.e(TAG, objArr);
        if (audioResProviderInterface != null && c && !fitWorkout.isLongVideoCourse()) {
            final boolean[] zArr = {false};
            audioResProviderInterface.queryOrDownAudioResource(BaseApplication.getActivity(), mxc.a(context, str), context.getResources().getString(R$string.IDS_hwh_base_model_multilingualaudio), new AudioResDownloadListener() { // from class: com.huawei.health.suggestion.h5pro.CourseControlManager$$ExternalSyntheticLambda2
                @Override // com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener
                public final void onResult(int i) {
                    CourseControlManager.lambda$checkMultilingualAudio$2(zArr, uiCallback, i);
                }
            });
        } else {
            uiCallback.onSuccess(0);
        }
    }

    static /* synthetic */ void lambda$checkMultilingualAudio$2(final boolean[] zArr, final UiCallback uiCallback, int i) {
        ReleaseLogUtil.e(TAG, "result = ", Integer.valueOf(i));
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.health.suggestion.h5pro.CourseControlManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CourseControlManager.lambda$checkMultilingualAudio$1(zArr, uiCallback);
            }
        }, 150L);
    }

    static /* synthetic */ void lambda$checkMultilingualAudio$1(boolean[] zArr, UiCallback uiCallback) {
        if (!zArr[0]) {
            uiCallback.onSuccess(0);
        }
        zArr[0] = true;
    }

    public static void startTrackActivity(Context context, FitWorkout fitWorkout, mmp mmpVar) {
        RecordPlanInfo recordPlanInfo;
        ReleaseLogUtil.e(TAG, "startTrackActivity");
        if (fitWorkout == null || mmpVar == null) {
            LogUtil.b(TAG, "mFitWorkout is null.");
            return;
        }
        WorkoutRecord c = mod.c(fitWorkout, mmpVar);
        int acquireWorkoutOrder = c.acquireWorkoutOrder();
        String acquirePlanId = c.acquirePlanId();
        ReleaseLogUtil.e(TAG, "CustomPermissionAction onGranted. run workout:", fitWorkout.acquireName(), Integer.valueOf(fitWorkout.getMusicRunFlag()), "planId:", acquirePlanId);
        if (TextUtils.isEmpty(acquirePlanId)) {
            recordPlanInfo = null;
        } else {
            recordPlanInfo = new RecordPlanInfo(acquirePlanId, DateFormatUtil.b(mmpVar.f()), acquireWorkoutOrder, mmpVar.ac());
            IntPlan currentIntPlan = ((PlanApi) Services.c("CoursePlanService", PlanApi.class)).getCurrentIntPlan();
            if (currentIntPlan != null) {
                recordPlanInfo.setPlanType(currentIntPlan.getPlanType().getType());
            }
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("BI_INTENT_COURSE", (Serializable) getIntentCourseBiMap(fitWorkout, mmpVar));
        bundle.putString("workout_package_id", mmpVar.ad());
        bundle.putInt("course_belong_type", getCourseBelongType(mmpVar.ad()));
        JumpUtil.aMF_(context, fitWorkout, recordPlanInfo, bundle);
        recordTrainEvent(4, fitWorkout, mmpVar.ad());
        ggr.e(fitWorkout, getBiMapData(fitWorkout, mmpVar), false);
    }

    public static void startLongCoachAfterAction(FitWorkout fitWorkout, mmp mmpVar) {
        if (fitWorkout == null || mmpVar == null) {
            ReleaseLogUtil.d(TAG, "startLongCoachAfterAction fitWorkout == null || courseJumpParameter == null");
            return;
        }
        ggr.e(fitWorkout, getBiMapData(fitWorkout, mmpVar), false);
        ggr.a();
        ggr.e(fitWorkout);
    }

    public static void initFa(Context context, FitWorkout fitWorkout, String str, long j, UiCallback<Integer> uiCallback) {
        LogUtil.a(TAG, "start to initFA");
        if (context == null || fitWorkout == null || uiCallback == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "context == null || fitWorkout == null || uiCallback == null";
            objArr[1] = Boolean.valueOf(fitWorkout == null);
            ReleaseLogUtil.d(TAG, objArr);
            return;
        }
        PermissionUtil.b(context, PermissionUtil.PermissionType.DISTRIBUTED_DATASYNC, new WeekDistributed(context, fitWorkout, str, j, uiCallback));
    }

    static class WeekDistributed extends CustomPermissionAction {
        private final WeakReference<Context> mContext;
        private final long mCoursePlanTime;
        private final FitWorkout mFitWorkout;
        private final String mPlanId;
        private final UiCallback<Integer> mUiCallback;

        public WeekDistributed(Context context, FitWorkout fitWorkout, String str, long j, UiCallback<Integer> uiCallback) {
            super(context);
            this.mContext = new WeakReference<>(context);
            this.mFitWorkout = fitWorkout;
            this.mPlanId = str;
            this.mCoursePlanTime = j;
            this.mUiCallback = uiCallback;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a(CourseControlManager.TAG, "initFA get the DISTRIBUTED_DATASYNC permission");
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.suggestion.h5pro.CourseControlManager$WeekDistributed$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CourseControlManager.WeekDistributed.this.m501xd8c831dc();
                }
            });
        }

        /* renamed from: lambda$onGranted$0$com-huawei-health-suggestion-h5pro-CourseControlManager$WeekDistributed, reason: not valid java name */
        /* synthetic */ void m501xd8c831dc() {
            Context context = this.mContext.get();
            if (context == null) {
                LogUtil.h(CourseControlManager.TAG, "initFA context == null");
                return;
            }
            DistributedApi distributedApi = (DistributedApi) Services.c("DistributedService", DistributedApi.class);
            if (distributedApi.isHopEnabled(context)) {
                TrainDetailConnectCallback trainDetailConnectCallback = new TrainDetailConnectCallback(this.mFitWorkout, this.mPlanId, this.mCoursePlanTime, this.mUiCallback);
                int register = distributedApi.register(context.getPackageName(), CourseControlManager.getRegisterToken(context, null), CourseControlManager.createExtraParams(this.mFitWorkout.acquireName(), this.mFitWorkout.getSmartScreeFlag()), trainDetailConnectCallback);
                trainDetailConnectCallback.setToken(register);
                ReleaseLogUtil.e(CourseControlManager.TAG, "the faToken is: ", Integer.valueOf(register));
                if (register > 0) {
                    distributedApi.showDeviceList(register, CourseControlManager.createExtraParams(this.mFitWorkout.acquireName(), this.mFitWorkout.getSmartScreeFlag()));
                    return;
                } else {
                    this.mUiCallback.onFailure(TrainDetailConnectCallback.b, "faToken < 0");
                    return;
                }
            }
            ReleaseLogUtil.e(CourseControlManager.TAG, "initFA onFailure isHopEnable false");
            this.mUiCallback.onFailure(TrainDetailConnectCallback.b, "isHopEnable is false");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IBinder getRegisterToken(Context context, IBinder iBinder) {
        Activity wa_;
        try {
            if (!(context instanceof Activity) && (wa_ = com.huawei.haf.application.BaseApplication.wa_()) != null) {
                context = wa_;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            ReleaseLogUtil.c(TAG, "getRegisterToken appear exception, eeror Message:", LogAnonymous.b(e));
        }
        if (context == null) {
            ReleaseLogUtil.d(TAG, "getRegisterToken validContext == null");
            return iBinder;
        }
        Object invoke = Activity.class.getMethod("getActivityToken", new Class[0]).invoke(context, new Object[0]);
        if (invoke instanceof IBinder) {
            return (IBinder) invoke;
        }
        return iBinder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ExtraParams createExtraParams(String str, int i) {
        DistributedApi distributedApi = (DistributedApi) Services.c("DistributedService", DistributedApi.class);
        String createExFilterParamsWithSameAccount = distributedApi.createExFilterParamsWithSameAccount(null);
        LogUtil.a(TAG, "FA mJsonParams is: ", createExFilterParamsWithSameAccount);
        LogUtil.a(TAG, "fitWorkout name: ", str, " view: ", Integer.valueOf(i));
        return distributedApi.createExtraParams(i == 0 ? new String[0] : new String[]{"09C"}, "com.huawei.ohos.healthtv.hmservice", str, createExFilterParamsWithSameAccount);
    }

    public static boolean isSupportFaController(int i) {
        boolean az = CommonUtil.az();
        int ah = CommonUtil.ah();
        boolean isSupportCenterController = ((DistributedApi) Services.c("DistributedService", DistributedApi.class)).isSupportCenterController(com.huawei.haf.application.BaseApplication.e());
        ReleaseLogUtil.e(TAG, "the phone can't use FA,isHarmony: ", Boolean.valueOf(az), " systemVersionCode: ", Integer.valueOf(ah), " isSupportCenterController: ", Boolean.valueOf(isSupportCenterController));
        return !SportSupportUtil.f() && !EnvironmentInfo.k() && i == 1 && isSupportCenterController;
    }
}
