package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.AIActionBundle;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class hpo {
    public static void d(int i, AIActionBundle aIActionBundle, WorkoutRecord workoutRecord) {
        HashMap hashMap = new HashMap(12);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        if (aIActionBundle != null) {
            hashMap.put("action_id", aIActionBundle.getActionId());
            hashMap.put("action_name", aIActionBundle.getActionName());
            hashMap.put("action_type", aIActionBundle.getBodyName());
            hashMap.put(WorkoutRecord.Extend.COURSE_TARGET_TYPE, e(aIActionBundle.getTargetType()));
            hashMap.put(WorkoutRecord.Extend.COURSE_TARGET_VALUE, Float.valueOf(aIActionBundle.getTargetValue()));
        }
        if (workoutRecord != null) {
            hashMap.put("finishRate", Float.valueOf(workoutRecord.acquireFinishRate()));
            hashMap.put("calories", Float.valueOf(workoutRecord.acquireActualCalorie()));
            hashMap.put("totalTime", Integer.valueOf(workoutRecord.getDuration() / 1000));
            hashMap.put("validityTime", Integer.valueOf(workoutRecord.getTrainMeasureValue()));
            hashMap.put("ranking", Integer.valueOf(workoutRecord.putCurRank()));
        }
        gge.e(AnalyticsValue.AI_ACTION_TRAIN_END_EVENT_VALUE.value(), hashMap);
    }

    public static void b(int i, AIActionBundle aIActionBundle) {
        HashMap hashMap = new HashMap(11);
        hashMap.put("event", Integer.valueOf(i));
        if (aIActionBundle != null) {
            hashMap.put("action_id", aIActionBundle.getActionId());
            hashMap.put("action_name", aIActionBundle.getActionName());
            hashMap.put("type", aIActionBundle.getBodyName());
            hashMap.put(WorkoutRecord.Extend.COURSE_TARGET_TYPE, e(aIActionBundle.getTargetType()));
            hashMap.put(WorkoutRecord.Extend.COURSE_TARGET_VALUE, Float.valueOf(aIActionBundle.getTargetValue()));
            hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, aIActionBundle.getPullFrom());
            hashMap.put("resourceId", aIActionBundle.getResourceId());
            hashMap.put("resourceName", aIActionBundle.getResourceName());
            hashMap.put("pullOrder", aIActionBundle.getPullOrder());
        }
        gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_DETAIL_1130029.value(), hashMap);
    }

    public static int a(int i) {
        Context context = BaseApplication.getContext();
        HashMap hashMap = new HashMap(14);
        hashMap.put(context.getString(R.string._2130846343_res_0x7f022287), 1);
        hashMap.put(context.getString(R.string._2130846029_res_0x7f02214d), 2);
        hashMap.put(context.getString(R.string._2130846028_res_0x7f02214c), 3);
        hashMap.put(context.getString(R.string._2130846027_res_0x7f02214b), 4);
        hashMap.put(context.getString(R.string._2130843959_res_0x7f021937), 5);
        hashMap.put(context.getString(R.string._2130846026_res_0x7f02214a), 6);
        hashMap.put(context.getString(R.string._2130846344_res_0x7f022288), 7);
        hashMap.put(context.getString(R.string._2130846025_res_0x7f022149), 8);
        hashMap.put(context.getString(R.string._2130845333_res_0x7f021e95), 9);
        hashMap.put(context.getString(R.string._2130845330_res_0x7f021e92), 10);
        hashMap.put(context.getString(R.string._2130845332_res_0x7f021e94), 10);
        hashMap.put(context.getString(R.string._2130845331_res_0x7f021e93), 11);
        hashMap.put(context.getString(R.string._2130842896_res_0x7f021510), 12);
        hashMap.put(context.getString(R.string._2130842897_res_0x7f021511), 13);
        String string = context.getString(i);
        int intValue = hashMap.containsKey(string) ? ((Integer) hashMap.get(string)).intValue() : 0;
        LogUtil.a("Track_AiSportBiUtil", "shortTip ", string);
        hashMap.clear();
        return intValue;
    }

    private static String e(int i) {
        return i != -1 ? i != 1 ? i != 10 ? "" : "fixed_number" : "timed_mode" : "free_model";
    }
}
