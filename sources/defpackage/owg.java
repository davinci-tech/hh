package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity;
import com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity;
import com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper;
import com.huawei.ui.main.stories.me.util.StepCounterSupportUtil;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class owg {
    public static int d() {
        if ("threeCircleCard".equals(oun.a())) {
            return 1;
        }
        return "threeLeafCard".equals(oun.a()) ? 2 : 0;
    }

    public static void a(Context context, int i) {
        if (context == null) {
            return;
        }
        String usetId = LoginInit.getInstance(context).getUsetId();
        String e = KeyValDbManager.b(context).e(usetId + "step_sum_dvalue");
        double b = TextUtils.isEmpty(e) ? 0.0d : CommonUtils.b(e, 0.0d);
        double a2 = a(context, b);
        LogUtil.a("TwoModelUtils", "otherDeviceSteps=", Double.valueOf(b), "  totalSteps=", Integer.valueOf(i), "actualOtherDeviceSteps=", Double.valueOf(a2));
        if (i <= 0 || a2 / i < 0.20000000298023224d || a2 < 500.0d) {
            LogUtil.a("TwoModelUtils", "(otherDeviceSteps/(float) steps)=", Double.valueOf(b / i));
        } else if (pwj.e().b() >= 2) {
            e(context);
        }
    }

    private static double a(Context context, double d) {
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "first_day_run_record");
        LogUtil.a("TwoModelUtils", "acquireActualOtherDeviceSteps firstRunText", b);
        if (TextUtils.isEmpty(b)) {
            SharedPreferenceManager.e(context, Integer.toString(10000), "first_day_run_record", "false#" + System.currentTimeMillis() + "#" + d, new StorageParams());
            d = 0.0d;
        } else {
            try {
                String[] split = b.split("#");
                if (split != null && split.length == 3 && "false".equals(split[0]) && jdl.ac(Long.parseLong(split[1]))) {
                    d -= Double.parseDouble(split[2]);
                }
            } catch (Exception e) {
                LogUtil.b("TwoModelUtils", "acquireActualOtherDeviceSteps e", LogAnonymous.b((Throwable) e));
            }
        }
        LogUtil.a("TwoModelUtils", "acquireActualOtherDeviceSteps actualOtherDeviceSteps", Double.valueOf(d));
        return d;
    }

    private static void e(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "show_data_origin_tips_times");
        LogUtil.a("TwoModelUtils", "showOtherDeviceStepsMoreThanTwentyPercentTips storeText=", b);
        if (!TextUtils.isEmpty(b)) {
            String[] split = b.split("#");
            try {
                if (split.length > 1 && "1".equals(split[1]) && jdl.ac(Long.parseLong(split[0]))) {
                    LogUtil.a("TwoModelUtils", "showOtherDeviceStepsMoreThanTwentyPercentTips already tips twice! return.");
                } else {
                    ObserverManagerUtil.c("TIPS_OTHER_DEVICES_SYNC_STEPS", 6);
                }
                return;
            } catch (NumberFormatException unused) {
                LogUtil.b("TwoModelUtils", "showOtherDeviceStepsMoreThanTwentyPercentTips NumberFormatException");
                return;
            }
        }
        ObserverManagerUtil.c("TIPS_OTHER_DEVICES_SYNC_STEPS", 6);
    }

    public static void b(HealthOpenSDK healthOpenSDK, Context context, int i, int i2) {
        pxp.a(1);
        d(healthOpenSDK, context, i, i2);
    }

    public static void d(HealthOpenSDK healthOpenSDK, Context context, int i, int i2) {
        String string;
        e(healthOpenSDK);
        int a2 = StepCounterSupportUtil.a(BaseApplication.getContext());
        if (i <= 0 && a2 == 3) {
            if (EnvironmentInfo.k()) {
                string = context.getString(R.string._2130846131_res_0x7f0221b3);
            } else if (nsn.ae(com.huawei.haf.application.BaseApplication.e())) {
                string = context.getString(R.string._2130844370_res_0x7f021ad2);
            } else {
                string = context.getString(R.string._2130841918_res_0x7f02113e);
            }
            Toast.makeText(context, string, 0).show();
            return;
        }
        StepModuleChartStorageHelper.e(true);
        Intent intent = new Intent(context, (Class<?>) FitnessStepDetailActivity.class);
        intent.putExtra("today_current_steps_total", i);
        intent.putExtra("today_current_distance_total", i2);
        gnm.aPB_(context, intent);
        opj.b(1);
    }

    private static void e(final HealthOpenSDK healthOpenSDK) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: owe
            @Override // java.lang.Runnable
            public final void run() {
                owg.b(HealthOpenSDK.this);
            }
        });
    }

    static /* synthetic */ void b(HealthOpenSDK healthOpenSDK) {
        if (healthOpenSDK != null) {
            healthOpenSDK.a((IFlushResult) null);
        }
    }

    public static void b(HealthOpenSDK healthOpenSDK, Context context, int i) {
        e(healthOpenSDK);
        Intent intent = new Intent(context, (Class<?>) FitnessSportIntensityDetailActivity.class);
        intent.putExtra("today_current_middle_and_high_total", i);
        gnm.aPB_(context, intent);
        opj.b(1);
    }

    public static void a(HealthOpenSDK healthOpenSDK, Context context, int i) {
        e(healthOpenSDK);
        pxp.a();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(context, FitnessCalorieDetailActivity.class);
        intent.putExtra("bundle_key_data", bundle);
        intent.putExtra("today_current_colories_total", i * 1000);
        gnm.aPB_(context, intent);
        opj.b(1);
    }

    public static void a(Context context) {
        b(context);
        KnitActiveHoursActivity.e(context);
        opj.b(1);
    }

    private static void b(Context context) {
        pxp.a(3);
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", "1");
        hashMap.put("from", 1);
        hashMap.put("event", 1);
        ixx.d().d(context, AnalyticsValue.HEALTH_HOME_ACTIVE_HOUR_1040092.value(), hashMap, 0);
    }

    public static void b(Context context, int i, int[] iArr, int[] iArr2, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        LogUtil.a("TwoModelUtils", "clickThreeCircleDetail step:", Integer.valueOf(iArr[3]), " mActive:", Integer.valueOf(iArr[2]));
        Intent intent = new Intent(context, (Class<?>) KnitActiveRecordActivity.class);
        intent.putExtra("current_total_step", iArr[3]);
        intent.putExtra("current_total_intensity", iArr[1]);
        intent.putExtra("current_total_active", iArr[2]);
        intent.putExtra("cur_calorie_goal", iArr2[0] * 1000);
        intent.putExtra("current_total_distance", iArr[4]);
        intent.putExtra("current_total_calorie", iArr[0]);
        intent.putExtra("current_total_calorie", i);
        intent.putExtra("current_total_floor", iArr[5]);
        intent.putExtra("cur_step_goal", iArr2[3]);
        intent.putExtra("cur_intensity_goal", iArr2[1]);
        intent.putExtra("cur_active_goal", iArr2[2]);
        b(hashMap, iArr, iArr2);
        gnm.aPB_(context, intent);
        hashMap.put("from", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040091.value(), hashMap, 0);
    }

    private static void b(Map<String, Object> map, int[] iArr, int[] iArr2) {
        String[] strArr = {"calorie", "MVPA", "activity"};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            int i2 = iArr[i];
            int i3 = iArr2[i];
            if (i2 >= i3 && i3 > 0) {
                arrayList.add(strArr[i]);
            }
        }
        if (arrayList.size() == 3) {
            arrayList.add("perfect");
        }
        map.put("status", arrayList);
    }

    public static void b(int[] iArr) {
        String str = System.currentTimeMillis() + "#" + iArr[0] + "#" + iArr[1] + "#" + iArr[2] + "#" + iArr[3] + "#" + iArr[4] + "#" + iArr[5];
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "three_circle_data", str, (StorageParams) null);
        LogUtil.a("TwoModelUtils", "saveCircleData to sp", str);
    }

    public static void c(int[] iArr) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "three_circle_data");
        if (TextUtils.isEmpty(b)) {
            return;
        }
        LogUtil.a("TwoModelUtils", "getCircleDataFromSp circleVal=", b);
        String[] split = b.split("#");
        if (split.length == 7 && FitnessUtils.c(CommonUtil.g(split[0]), System.currentTimeMillis())) {
            for (int i = 1; i < 7; i++) {
                iArr[i - 1] = CommonUtil.h(split[i]);
            }
        }
    }

    public static void d(int[] iArr) {
        String str = System.currentTimeMillis() + "#" + iArr[0] + "#" + iArr[1] + "#" + iArr[2] + "#" + iArr[3];
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "three_circle_goal_version_2", str, (StorageParams) null);
        LogUtil.a("TwoModelUtils", "goalVal to sp", str);
    }

    public static void a(int[] iArr) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "three_circle_goal_version_2");
        if (TextUtils.isEmpty(b)) {
            return;
        }
        LogUtil.a("TwoModelUtils", "getCircleGoalFromSp goalVal=", b);
        String[] split = b.split("#");
        if (split.length == 5 && FitnessUtils.c(CommonUtil.g(split[0]), System.currentTimeMillis())) {
            for (int i = 1; i < 5; i++) {
                iArr[i - 1] = CommonUtil.h(split[i]);
            }
        }
    }
}
