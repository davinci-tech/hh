package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.suggestion.util.FitnessItemDecoration;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Audio;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.TargetConfig;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ggs {
    public static boolean d(int i) {
        return i == 5 || i == 6 || i == 7;
    }

    public static boolean d(int i, int i2) {
        return i == 5 || (i == 7 && i2 != 0);
    }

    public static boolean e(int i, int i2) {
        return i == 6 || (i == 7 && i2 != 0);
    }

    public static int c() {
        return new HealthColumnSystem(BaseApplication.getContext(), 1).f() / 4;
    }

    public static int a(Context context) {
        if (context == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "getWindowWidth() context is null!");
            return 0;
        }
        Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "getWindowWidth, object is not instanceof WindowManager");
            return 0;
        }
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void a(Context context, HealthRecycleView healthRecycleView) {
        if (context == null || healthRecycleView == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "setRecyclerViewLayout(), Param is null!");
        } else {
            e(context, healthRecycleView, new HealthLinearLayoutManager(context), true);
        }
    }

    public static void b(Context context, HealthRecycleView healthRecycleView, LinearLayoutManager linearLayoutManager) {
        e(context, healthRecycleView, linearLayoutManager, true);
    }

    public static void d(Context context, HealthRecycleView healthRecycleView, boolean z) {
        HealthLinearLayoutManager healthLinearLayoutManager;
        if (context == null || healthRecycleView == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "setRecyclerViewLayout(), context or recycleView is null!");
            return;
        }
        if (z) {
            healthLinearLayoutManager = new HealthLinearLayoutManager(context);
        } else {
            healthLinearLayoutManager = new HealthLinearLayoutManager(context) { // from class: ggs.1
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }
        e(context, healthRecycleView, healthLinearLayoutManager, z);
    }

    public static void e(Context context, HealthRecycleView healthRecycleView, LinearLayoutManager linearLayoutManager, boolean z) {
        b(context, healthRecycleView, linearLayoutManager, z, c(context));
    }

    public static void b(Context context, HealthRecycleView healthRecycleView, LinearLayoutManager linearLayoutManager, boolean z, int i) {
        if (context == null || healthRecycleView == null || linearLayoutManager == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "setRecyclerViewLayout(), context or recycleView or linearLayoutManager is null!");
            return;
        }
        boolean z2 = false;
        if (nsn.ag(context)) {
            int a2 = nsn.a(context, nrr.b(context));
            if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
                healthRecycleView.removeItemDecorationAt(0);
            }
            healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, a2, c(context)));
            int i2 = 1;
            int i3 = 2;
            if (z) {
                healthRecycleView.setLayoutManager(new GridLayoutManager(context, 2, 1, false));
                return;
            } else {
                healthRecycleView.setLayoutManager(new GridLayoutManager(context, i3, i2, z2) { // from class: ggs.2
                    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                return;
            }
        }
        if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
            healthRecycleView.removeItemDecorationAt(0);
        }
        healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, 0, i));
        healthRecycleView.setLayoutManager(linearLayoutManager);
    }

    public static void d(Context context, int i, HealthRecycleView healthRecycleView) {
        if (context == null || healthRecycleView == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "setRecyclerViewLayout(), context or recycleView");
            return;
        }
        if (i == 1) {
            d(context, healthRecycleView, true);
            return;
        }
        if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
            healthRecycleView.removeItemDecorationAt(0);
        }
        if (nsn.ag(context)) {
            int i2 = i * 2;
            healthRecycleView.addItemDecoration(c(context, i2));
            healthRecycleView.setLayoutManager(new GridLayoutManager(context, i2, 1, false));
        } else {
            healthRecycleView.addItemDecoration(c(context, i));
            healthRecycleView.setLayoutManager(new GridLayoutManager(context, i, 1, false));
        }
    }

    private static ColumnLayoutItemDecoration c(Context context, int i) {
        return new ColumnLayoutItemDecoration(context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), i, new int[]{0, context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), 0, 0});
    }

    public static void b(Context context, HealthRecycleView healthRecycleView, boolean z, boolean z2) {
        HealthLinearLayoutManager healthLinearLayoutManager;
        if (context == null || healthRecycleView == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "setRecyclerViewLayout(), tahitiPadding, defaultPadding is null!");
            return;
        }
        boolean z3 = false;
        if (nsn.ag(context)) {
            int a2 = nsn.a(context, nrr.b(context));
            if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
                healthRecycleView.removeItemDecorationAt(0);
            }
            healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, a2, 0));
            int i = 1;
            int i2 = 2;
            if (z) {
                healthRecycleView.setLayoutManager(new GridLayoutManager(context, 2, 1, false));
                return;
            } else {
                healthRecycleView.setLayoutManager(new GridLayoutManager(context, i2, i, z3) { // from class: ggs.4
                    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                return;
            }
        }
        if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
            healthRecycleView.removeItemDecorationAt(0);
        }
        if (z2) {
            healthLinearLayoutManager = new HealthLinearLayoutManager(context);
        } else {
            healthLinearLayoutManager = new HealthLinearLayoutManager(context) { // from class: ggs.5
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }
        healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, 0, 0));
        healthRecycleView.setLayoutManager(healthLinearLayoutManager);
    }

    public static void e(Context context, HealthRecycleView healthRecycleView, int i) {
        if (context == null || healthRecycleView == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "setRecyclerViewLayout(), context or recycleView or linearLayoutManager is null!");
            return;
        }
        HealthLinearLayoutManager healthLinearLayoutManager = new HealthLinearLayoutManager(context);
        if (nsn.ag(context)) {
            if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
                healthRecycleView.removeItemDecorationAt(0);
            }
            healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, i, c(context)));
            healthRecycleView.setLayoutManager(new GridLayoutManager(context, 2, 1, false));
            return;
        }
        if (healthRecycleView.getItemDecorationCount() > 0 && healthRecycleView.getItemDecorationAt(0) != null) {
            healthRecycleView.removeItemDecorationAt(0);
        }
        healthRecycleView.addItemDecoration(new FitnessItemDecoration(context, 0, c(context)));
        healthRecycleView.setLayoutManager(healthLinearLayoutManager);
    }

    public static boolean e(Context context) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            return false;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "personalized_recommend");
        LogUtil.a("Suggestion_FitnessCommonUtil", "productRecommend = ", b);
        return "1".equals(b);
    }

    public static List<FitWorkout> d(List<FitWorkout> list, String str) {
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null) {
                if (fitWorkout.getWorkoutType() == 1) {
                    arrayList.add(fitWorkout);
                } else {
                    arrayList2.add(fitWorkout);
                }
            }
        }
        return str.equals("RUNNING_COURSE") ? arrayList2 : arrayList;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0049 -> B:20:0x0056). Please report as a decompilation issue!!! */
    public static Map<String, mmz> a(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            return Collections.EMPTY_MAP;
        }
        Map<String, mmz> d = d(fitWorkout.acquireExtendSeaMap());
        if (d == null || d.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(fitWorkout.acquireNarrowDesc());
                if (jSONObject.has("extendMap") && !TextUtils.isEmpty(jSONObject.optString("extendMap"))) {
                    d = e(jSONObject.optString("extendMap"));
                } else {
                    LogUtil.b("Suggestion_FitnessCommonUtil", "no has JsonObject");
                }
            } catch (JSONException e) {
                LogUtil.b("Suggestion_FitnessCommonUtil", "detailInfo JSONException:", LogAnonymous.b((Throwable) e));
            }
        }
        return d == null ? Collections.EMPTY_MAP : d;
    }

    public static Map<String, mmz> d(String str) {
        if (str == null || str.length() == 0) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "parseWorkoutExtendBean input null or empty");
            return Collections.EMPTY_MAP;
        }
        HashMap hashMap = new HashMap(10);
        try {
            JSONObject jSONObject = new JSONObject(str);
            a(hashMap, jSONObject, "workoutRecommendDec", "courseDesc", HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE);
            JSONObject optJSONObject = jSONObject.optJSONObject("suitPeople");
            if (optJSONObject != null) {
                hashMap.put("suitPeople", new mmz(optJSONObject.optString("title"), optJSONObject.optString(ParamConstants.Param.CONTEXT)));
            }
            a(hashMap, jSONObject, "trainingSuggest", "bannerPeople", "attention");
            return hashMap;
        } catch (JSONException e) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "parseWorkoutExtendBean input json exception ", LogAnonymous.b((Throwable) e));
            return hashMap;
        } catch (Exception e2) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "parseWorkoutExtendBean exception ", LogAnonymous.b((Throwable) e2));
            return hashMap;
        }
    }

    private static void a(Map<String, mmz> map, JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject != null) {
            map.put(str, new mmz(optJSONObject.optString("title"), optJSONObject.optString(ParamConstants.Param.CONTEXT)));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject(str2);
        if (optJSONObject2 != null) {
            map.put(str2, new mmz(optJSONObject2.optString("title"), optJSONObject2.optString(ParamConstants.Param.CONTEXT)));
        }
        JSONObject optJSONObject3 = jSONObject.optJSONObject(str3);
        if (optJSONObject3 != null) {
            map.put(str3, new mmz(optJSONObject3.optString("title"), optJSONObject3.optString(ParamConstants.Param.CONTEXT)));
        }
    }

    public static Map<String, mmz> e(String str) {
        if (StringUtils.g(str)) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "parseWorkoutExtendOld input null or empty");
            return Collections.EMPTY_MAP;
        }
        HashMap hashMap = new HashMap(10);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("课程说明");
            if (StringUtils.i(optString)) {
                hashMap.put("courseDesc", new mmz(BaseApplication.getContext().getResources().getString(R.string._2130837549_res_0x7f02002d), optString));
            }
            String optString2 = jSONObject.optString("训练建议");
            if (StringUtils.i(optString2)) {
                hashMap.put("trainingSuggest", new mmz(BaseApplication.getContext().getResources().getString(R.string._2130837550_res_0x7f02002e), optString2));
            }
            String optString3 = jSONObject.optString("适用人群");
            if (StringUtils.i(optString3)) {
                hashMap.put("suitPeople", new mmz(BaseApplication.getContext().getResources().getString(R.string._2130837551_res_0x7f02002f), optString3));
            }
            String optString4 = jSONObject.optString("禁忌人群");
            if (StringUtils.i(optString4)) {
                hashMap.put("bannerPeople", new mmz(BaseApplication.getContext().getResources().getString(R.string._2130837552_res_0x7f020030), optString4));
            }
            String optString5 = jSONObject.optString("注意事项");
            if (StringUtils.i(optString5)) {
                hashMap.put("attention", new mmz(BaseApplication.getContext().getResources().getString(R.string._2130837553_res_0x7f020031), optString5));
            }
        } catch (JSONException e) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "parseWorkoutExtendOld exception ", LogAnonymous.b((Throwable) e));
        }
        return hashMap;
    }

    public static List<WorkoutAction> e(FitWorkout fitWorkout) {
        ArrayList arrayList = new ArrayList();
        if (fitWorkout != null) {
            WorkoutAction acquireWarmUpRunAction = fitWorkout.acquireWarmUpRunAction();
            if (acquireWarmUpRunAction != null) {
                arrayList.add(acquireWarmUpRunAction);
            }
            List<WorkoutAction> acquireWorkoutActions = fitWorkout.acquireWorkoutActions();
            int acquireRepeatTimes = fitWorkout.getType() == 3 ? fitWorkout.acquireRepeatTimes() : 1;
            if (acquireWorkoutActions != null) {
                for (int i = 0; i < acquireRepeatTimes; i++) {
                    arrayList.addAll(acquireWorkoutActions);
                }
            }
            WorkoutAction acquireCoolDownRunAction = fitWorkout.acquireCoolDownRunAction();
            if (acquireCoolDownRunAction != null) {
                arrayList.add(acquireCoolDownRunAction);
            }
        } else {
            health.compact.a.util.LogUtil.c("Suggestion_FitnessCommonUtil", "getWorkoutActionRunWorkoutAll fitworkout is null");
        }
        return arrayList;
    }

    public static List<ChoreographedSingleAction> c(FitWorkout fitWorkout) {
        ArrayList arrayList = new ArrayList();
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "getChoreographedMultiActionsAll fitWorkout is null");
            return arrayList;
        }
        List<ChoreographedMultiActions> courseActions = fitWorkout.getCourseActions();
        if (koq.b(courseActions)) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "getChoreographedMultiActionsAll workoutActionList is null");
            return arrayList;
        }
        for (ChoreographedMultiActions choreographedMultiActions : courseActions) {
            if (choreographedMultiActions != null) {
                int repeatTimes = choreographedMultiActions.getRepeatTimes();
                for (int i = 0; i < repeatTimes; i++) {
                    arrayList.addAll(choreographedMultiActions.getActionList());
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ChoreographedSingleAction) it.next()).putExtendProperty(MessageConstant.GROUP_MEDAL_TYPE, "1");
        }
        return arrayList;
    }

    public static List<ChoreographedSingleAction> a(List<WorkoutAction> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "convertActionList workoutActionList == null");
            return arrayList;
        }
        for (WorkoutAction workoutAction : list) {
            if (workoutAction == null) {
                LogUtil.b("Suggestion_FitnessCommonUtil", "convertActionList workoutAction null");
            } else {
                ChoreographedSingleAction choreographedSingleAction = new ChoreographedSingleAction();
                a(workoutAction, choreographedSingleAction);
                AtomicAction action = choreographedSingleAction.getAction();
                if (action == null) {
                    LogUtil.h("Suggestion_FitnessCommonUtil", "convertActionList atomicAction is null.");
                } else {
                    action.setExtendProperty(new HashMap());
                    c(action, workoutAction.getAction());
                    action.setId(workoutAction.getActionId());
                    action.putExtendProperty("duration", String.valueOf(workoutAction.getDuration()));
                    if (koq.c(workoutAction.getVideos())) {
                        action.putExtendProperty("actionVideo", new Gson().toJson(workoutAction.getVideos()));
                    }
                    if (workoutAction.getActionMusic() != null) {
                        action.putExtendProperty("actionMusic", new Gson().toJson(workoutAction.getActionMusic()));
                    }
                    action.putExtendProperty("actionStep", String.valueOf(workoutAction.getActionStep()));
                    action.putExtendProperty("introduceLyric", String.valueOf(workoutAction.getIntroduceLyric()));
                    action.putExtendProperty("breath", String.valueOf(workoutAction.getBreath()));
                    action.putExtendProperty("feeling", String.valueOf(workoutAction.getFeeling()));
                    action.putExtendProperty("commonError", String.valueOf(workoutAction.getCommonError()));
                    action.putExtendProperty("calorie", String.valueOf(workoutAction.getCalorie()));
                    if (koq.c(workoutAction.getPictures())) {
                        action.putExtendProperty("pictures", new Gson().toJson(workoutAction.getPictures()));
                    }
                    arrayList.add(choreographedSingleAction);
                }
            }
        }
        return arrayList;
    }

    private static void c(AtomicAction atomicAction, AtomicAction atomicAction2) {
        if (atomicAction2 != null) {
            atomicAction.setName(atomicAction2.getName());
            List extendPropertyList = atomicAction2.getExtendPropertyList("audios", Audio[].class);
            if (koq.c(extendPropertyList)) {
                atomicAction.putExtendProperty("audios", new Gson().toJson(extendPropertyList));
            }
            atomicAction.setActionSportType(atomicAction2.getActionSportType());
            atomicAction.putExtendProperty("frequency", atomicAction2.getExtendPropertyString("frequency"));
            atomicAction.setEquipments(atomicAction2.getEquipments());
            atomicAction.setTrainingPoints(atomicAction2.getTrainingPoints());
            atomicAction.setDifficulty(atomicAction2.getDifficulty());
            atomicAction.setDescription(atomicAction2.getDescription());
            atomicAction.setCovers(atomicAction2.getCovers());
            atomicAction.putExtendProperty("logoImgUrl", atomicAction2.getExtendPropertyString("logoImgUrl"));
        }
    }

    private static void a(WorkoutAction workoutAction, ChoreographedSingleAction choreographedSingleAction) {
        choreographedSingleAction.setCommentaryGap(workoutAction.acquireCommentaryGap());
        choreographedSingleAction.setCommentaryTraining(workoutAction.getCommentaryTraining());
        choreographedSingleAction.setAction(new AtomicAction());
        choreographedSingleAction.setIntensityConfig(new TargetConfig());
        choreographedSingleAction.getIntensityConfig().setId(String.valueOf(workoutAction.acquireIntensityType()));
        d(workoutAction, choreographedSingleAction.getIntensityConfig());
        choreographedSingleAction.setExtendProperty(new HashMap());
        choreographedSingleAction.putExtendProperty(MessageConstant.GROUP_MEDAL_TYPE, String.valueOf(workoutAction.getGroup()));
        choreographedSingleAction.putExtendProperty("gap", String.valueOf(workoutAction.getGap()));
        choreographedSingleAction.putExtendProperty("tabloidPicUrl", workoutAction.acquireTabloidPicUrl());
        choreographedSingleAction.setTargetConfig(new TargetConfig());
        choreographedSingleAction.getTargetConfig().setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
        choreographedSingleAction.getTargetConfig().setValueL(workoutAction.acquireMeasurementValue());
        choreographedSingleAction.getTargetConfig().setId(String.valueOf(workoutAction.acquireMeasurementType()));
    }

    private static void d(WorkoutAction workoutAction, TargetConfig targetConfig) {
        int acquireIntensityType = workoutAction.acquireIntensityType();
        if (acquireIntensityType == 0) {
            targetConfig.setValueL(workoutAction.acquireSpeedL());
            targetConfig.setValueH(workoutAction.acquireSpeedH());
        }
        if (acquireIntensityType == 1) {
            targetConfig.setValueL(workoutAction.acquireRelativeHeartRatePercentL());
            targetConfig.setValueH(workoutAction.acquireRelativeHeartRatePercentH());
            return;
        }
        if (acquireIntensityType == 2) {
            targetConfig.setValueL(CommonUtil.a(workoutAction.acquireAbsoluteHeartRateL()));
            targetConfig.setValueH(CommonUtil.a(workoutAction.acquireAbsoluteHeartRateH()));
            return;
        }
        if (acquireIntensityType == 4) {
            targetConfig.setValueL(workoutAction.acquireRelativeHeartRateRangeL());
            targetConfig.setValueH(workoutAction.acquireRelativeHeartRateRangeH());
            return;
        }
        switch (acquireIntensityType) {
            case 7:
                targetConfig.setValueL(workoutAction.acquireReserveHeartRatePercentL());
                targetConfig.setValueH(workoutAction.acquireReserveHeartRatePercentH());
                break;
            case 8:
                targetConfig.setValueL(workoutAction.acquireReserveHeartRateRangeL());
                targetConfig.setValueH(workoutAction.acquireReserveHeartRateRangeH());
                break;
            case 9:
                targetConfig.setValueL(workoutAction.acquireMaf180HeartRateRange());
                targetConfig.setValueH(workoutAction.acquireMaf180HeartRateBase());
                break;
            case 10:
                targetConfig.setValueL(workoutAction.acquireSpecifiedSpeed());
                targetConfig.setValueH(workoutAction.acquireSpecifiedSpeed());
                break;
            case 11:
                targetConfig.setValueL(workoutAction.acquireSpecifiedSlope());
                targetConfig.setValueH(workoutAction.acquireSpecifiedSlope());
                break;
            case 12:
                targetConfig.setValueL(workoutAction.acquireSpecifiedSpeed());
                targetConfig.setValueH(workoutAction.acquireSpecifiedSlope());
                break;
            default:
                LogUtil.b("Suggestion_FitnessCommonUtil", "setIntensityRangeValue other type:", Integer.valueOf(acquireIntensityType));
                break;
        }
    }

    public static int d(ChoreographedSingleAction choreographedSingleAction) {
        double valueH;
        if (choreographedSingleAction == null || choreographedSingleAction.getTargetConfig() == null) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "getTargetValue workoutAction == null");
            return 0;
        }
        if (TargetConfig.ValueTypes.LOW_VALUE.getValue() == choreographedSingleAction.getTargetConfig().getValueType()) {
            valueH = choreographedSingleAction.getTargetConfig().getValueL();
        } else {
            valueH = choreographedSingleAction.getTargetConfig().getValueH();
        }
        return (int) valueH;
    }

    public static List<RecordAction> e(List<ChoreographedSingleAction> list, int i) {
        ArrayList arrayList = new ArrayList(list.size());
        if (koq.b(list)) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "getRecordActions workoutActions is null");
            return arrayList;
        }
        for (ChoreographedSingleAction choreographedSingleAction : list) {
            if (choreographedSingleAction != null) {
                arrayList.add(c(choreographedSingleAction, i));
            }
        }
        return arrayList;
    }

    private static RecordAction c(ChoreographedSingleAction choreographedSingleAction, int i) {
        if (choreographedSingleAction == null || choreographedSingleAction.getAction() == null) {
            return new RecordAction("", "", 0, 0.0f, kxe.e(""));
        }
        int h = CommonUtils.h(choreographedSingleAction.getTargetConfig().getId());
        if (h == 4 || h == 5) {
            return new RecordAction(choreographedSingleAction.getAction().getId(), choreographedSingleAction.getAction().getName(), 0, Math.round((i * d(choreographedSingleAction)) / 100.0f), h);
        }
        return new RecordAction(choreographedSingleAction.getAction().getId(), choreographedSingleAction.getAction().getName(), 0, d(choreographedSingleAction), h);
    }

    public static boolean d(FitWorkout fitWorkout) {
        List<ChoreographedSingleAction> a2;
        if (fitWorkout == null) {
            LogUtil.b("Suggestion_FitnessCommonUtil", "fitWorkout == null");
            return false;
        }
        if (fitWorkout.isNewRunCourse()) {
            a2 = c(fitWorkout);
        } else {
            a2 = a(e(fitWorkout));
        }
        for (ChoreographedSingleAction choreographedSingleAction : a2) {
            if (choreographedSingleAction != null && c(choreographedSingleAction)) {
                LogUtil.a("Suggestion_FitnessCommonUtil", "Treadmill, intensityType = ", choreographedSingleAction.getIntensityConfig().getId());
                return true;
            }
        }
        return false;
    }

    private static boolean c(ChoreographedSingleAction choreographedSingleAction) {
        int h = choreographedSingleAction.getTargetConfig() != null ? CommonUtils.h(choreographedSingleAction.getTargetConfig().getId()) : -1;
        if (h == 5 || h == 4) {
            return true;
        }
        int h2 = choreographedSingleAction.getIntensityConfig() != null ? CommonUtils.h(choreographedSingleAction.getIntensityConfig().getId()) : 255;
        if (h2 == 17) {
            return true;
        }
        switch (h2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    public static int d() {
        return Utils.o() ? 204 : 4;
    }

    public static int c(Context context) {
        return nsn.a(context, context.getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a() {
        /*
            java.lang.String r0 = "HWUserProfileMgr"
            java.lang.Class<com.huawei.health.userprofilemgr.api.UserProfileMgrApi> r1 = com.huawei.health.userprofilemgr.api.UserProfileMgrApi.class
            java.lang.Object r0 = health.compact.a.Services.a(r0, r1)
            com.huawei.health.userprofilemgr.api.UserProfileMgrApi r0 = (com.huawei.health.userprofilemgr.api.UserProfileMgrApi) r0
            java.lang.String r1 = "Suggestion_FitnessCommonUtil"
            r2 = 1
            if (r0 != 0) goto L19
            java.lang.String r0 = "getUserInfo : userProfileMgrApi is null."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return r2
        L19:
            com.huawei.up.model.UserInfomation r0 = r0.getUserInfo()
            if (r0 == 0) goto L33
            boolean r3 = r0.isGenderValid()
            if (r3 == 0) goto L2a
            int r0 = r0.getGender()
            goto L34
        L2a:
            java.lang.String r0 = "getUserGender !userInfo.isGenderValid()"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
        L33:
            r0 = r2
        L34:
            r1 = 2
            if (r0 != r1) goto L38
            goto L39
        L38:
            r2 = r0
        L39:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ggs.a():int");
    }

    public static void e() {
        ash.d("allFitnessCourses");
        ash.d("workoutMyFitnessCourses");
        ash.d("RECOMMEND_WORKOUT_KEY_COURSE");
        ash.d("RECOMMEND_WORKOUT_CREATE_TIME_COURSE");
        ash.d(f());
        ash.d("allFitnessPkgs");
    }

    private static String f() {
        HashMap hashMap = new HashMap(2);
        hashMap.put(MedalConstants.EVENT_KEY, "getFitnessCourseTopicListKey");
        hashMap.put("pageNum", 0);
        return hashMap.toString();
    }

    public static void e(Context context, FitWorkout fitWorkout, Plan plan, long j, int i) {
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.i(i);
        mmpVar.d("10");
        mmpVar.f(plan.acquireName());
        mmpVar.e(j);
        mmpVar.h(plan.acquireId());
        mmpVar.f(b(plan, j));
        mod.b(context, fitWorkout, mmpVar);
    }

    public static void d(Context context, FitWorkout fitWorkout, Plan plan, long j) {
        e(context, fitWorkout, plan, j, 1);
    }

    public static void c(Context context, FitWorkout fitWorkout, Plan plan, long j) {
        if (context == null || fitWorkout == null || plan == null) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "context == null || fitWorkout == null || currentRunPlan == null");
        } else {
            c(context, fitWorkout, plan, 1, j);
        }
    }

    public static void c(Context context, FitWorkout fitWorkout, Plan plan, int i, long j) {
        if (context == null || fitWorkout == null || plan == null) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "context == null || fitWorkout == null || currentRunPlan == null");
        } else {
            e(context, fitWorkout, plan.acquireId(), b(plan, j), i, j);
        }
    }

    private static int b(Plan plan, long j) {
        if (plan.getPlanCategory() == 0) {
            return a(plan.getStartTime() * 1000, j);
        }
        return ase.e(plan);
    }

    public static void e(Context context, FitWorkout fitWorkout, String str, int i, int i2, long j) {
        if (context == null || fitWorkout == null) {
            LogUtil.h("Suggestion_FitnessCommonUtil", "context == null || fitWorkout == null");
            return;
        }
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.h(str);
        mmpVar.f(i);
        mmpVar.i(i2);
        mmpVar.d("10");
        mmpVar.e(j);
        mod.b(context, fitWorkout, mmpVar);
    }

    public static int a(long j, long j2) {
        return (((int) TimeUnit.MILLISECONDS.toDays(gib.b(j2) - gib.b(j))) / 7) + 1;
    }

    public static void b(int i, boolean z) {
        LogUtil.a("Suggestion_FitnessCommonUtil", "setPersonalPrivacySettingValue... privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        gmz.d().c(i, z, String.valueOf(i), new IBaseResponseCallback() { // from class: ggp
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                ggs.b(i2, obj);
            }
        });
    }

    static /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            LogUtil.a("Suggestion_FitnessCommonUtil", "onResponse setUserPrivacy success ");
        } else {
            LogUtil.b("Suggestion_FitnessCommonUtil", "onResponse setUserPrivacy failure");
        }
    }

    public static boolean b(int i) {
        String c = gmz.d().c(i);
        LogUtil.a("Suggestion_FitnessCommonUtil", "getPersonalPrivacySettingValue... privacyId: ", Integer.valueOf(i), " value: ", c);
        return "true".equals(c);
    }

    public static float b() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            return userInfo.getWeightOrDefaultValue();
        }
        return 0.0f;
    }
}
