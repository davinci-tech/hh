package defpackage;

import com.huawei.health.R;
import com.huawei.health.courseplanservice.api.DataPlatformApi;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fnt {
    public static void a(RecyclerHolder recyclerHolder, RecordAction recordAction) {
        if (recyclerHolder == null || recordAction == null) {
            LogUtil.h("Suggestion_CoachActivityHelper", "dealDiffType holder == null || itemData == null");
        } else {
            recyclerHolder.e(R.id.sug_coach_tv_finish_actionname, recordAction.getActionName());
            e(recordAction, recyclerHolder);
        }
    }

    private static String c(float f) {
        return UnitUtil.e(f / 60.0f, 1, Math.round((10.0f * f) / 60.0f) % 10 != 0 ? 1 : 0);
    }

    private static void e(RecordAction recordAction, RecyclerHolder recyclerHolder) {
        float finishedAction = recordAction.getFinishedAction();
        float actionTargetValue = recordAction.getActionTargetValue();
        int actionTargetType = recordAction.getActionTargetType();
        if (actionTargetType == 0) {
            e(recyclerHolder, finishedAction, actionTargetValue);
            return;
        }
        if (actionTargetType == 1) {
            c(recyclerHolder, finishedAction, actionTargetValue);
            return;
        }
        if (actionTargetType == 3) {
            b(recyclerHolder, finishedAction, actionTargetValue);
            return;
        }
        if (actionTargetType == 4 || actionTargetType == 5) {
            a(recyclerHolder, finishedAction, actionTargetValue);
            return;
        }
        if (actionTargetType == 10) {
            i(recyclerHolder, finishedAction, actionTargetValue);
            return;
        }
        if (actionTargetType == 101) {
            d(recyclerHolder, finishedAction, actionTargetValue);
        } else if (actionTargetType == 255) {
            c(recyclerHolder, finishedAction);
        } else {
            d(recyclerHolder, finishedAction);
        }
    }

    private static void d(RecyclerHolder recyclerHolder, float f) {
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, UnitUtil.e(f, 1, 2));
        recyclerHolder.d(R.id.sug_coach_tv_unit_, 8);
        recyclerHolder.d(R.id.sug_coach_finish_total, 8);
        recyclerHolder.d(R.id.sug_coach_finish_unit, 8);
    }

    private static void a(RecyclerHolder recyclerHolder, float f, float f2) {
        recyclerHolder.e(R.id.sug_coach_finish_unit, b(R.plurals._2130903224_res_0x7f0300b8, (int) f2, new Object[0]));
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, e(f));
        recyclerHolder.e(R.id.sug_coach_finish_total, e(f2));
    }

    private static void d(RecyclerHolder recyclerHolder, float f, float f2) {
        recyclerHolder.e(R.id.sug_coach_finish_unit, a(R.string._2130851529_res_0x7f0236c9));
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, e(f));
        recyclerHolder.e(R.id.sug_coach_finish_total, e(f2));
    }

    private static void b(RecyclerHolder recyclerHolder, float f, float f2) {
        recyclerHolder.e(R.id.sug_coach_finish_unit, a(R.string._2130848385_res_0x7f022a81));
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, e(f));
        recyclerHolder.e(R.id.sug_coach_finish_total, e(f2));
    }

    private static void i(RecyclerHolder recyclerHolder, float f, float f2) {
        recyclerHolder.e(R.id.sug_coach_finish_unit, a(R.string._2130848397_res_0x7f022a8d));
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, e(f));
        recyclerHolder.e(R.id.sug_coach_finish_total, e(f2));
    }

    private static void c(RecyclerHolder recyclerHolder, float f, float f2) {
        if (f2 >= 60.0f) {
            String c = c(f);
            String c2 = c(f2);
            recyclerHolder.d(R.id.sug_coach_tv_unit_, 8);
            recyclerHolder.e(R.id.sug_coach_finish_unit, BaseApplication.getContext().getResources().getString(R.string._2130840019_res_0x7f0209d3, c, b(R.plurals._2130903200_res_0x7f0300a0, (int) (f2 / 60.0f), c2)));
            return;
        }
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, e(f));
        recyclerHolder.e(R.id.sug_coach_finish_unit, a(R.string._2130848359_res_0x7f022a67));
        recyclerHolder.e(R.id.sug_coach_finish_total, e(f2));
    }

    private static void c(RecyclerHolder recyclerHolder, float f) {
        String b;
        if (f >= 60.0f) {
            b = b(R.plurals._2130903200_res_0x7f0300a0, (int) (f / 60.0f), c(f));
        } else {
            b = b(R.plurals._2130903107_res_0x7f030043, (int) f, e(f));
        }
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, b);
        recyclerHolder.d(R.id.sug_coach_tv_unit_, 8);
        recyclerHolder.d(R.id.sug_coach_finish_total, 8);
        recyclerHolder.d(R.id.sug_coach_finish_unit, 8);
    }

    private static void e(RecyclerHolder recyclerHolder, float f, float f2) {
        int i = (int) f;
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, moe.j(moe.d(i)));
        String a2 = a(R.string._2130851570_res_0x7f0236f2);
        String j = moe.j(moe.d(i));
        String j2 = moe.j(moe.d((int) f2));
        if (UnitUtil.h()) {
            a2 = a(R.string._2130839828_res_0x7f020914);
            j = a(f);
            j2 = a(f2);
        }
        recyclerHolder.e(R.id.sug_coach_finish_unit, a2);
        recyclerHolder.e(R.id.sug_coach_tv_finish_actionmsg, j);
        recyclerHolder.e(R.id.sug_coach_finish_total, j2);
    }

    private static String a(float f) {
        return UnitUtil.e(UnitUtil.e(moe.d((int) f), 3), 1, 2);
    }

    private static String a(int i) {
        return BaseApplication.getContext().getString(i);
    }

    private static String b(int i, int i2, Object... objArr) {
        return BaseApplication.getContext().getResources().getQuantityString(i, i2, objArr);
    }

    private static String e(float f) {
        return UnitUtil.e(f, 1, 0);
    }

    public static void d(Map<Long, Integer> map, long j, long j2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS);
        long j3 = j / 60000;
        long j4 = j3 * 60000;
        long j5 = j2 / 60000;
        long j6 = j5 * 60000;
        int i = (int) (j5 - j3);
        int i2 = (int) ((j2 - j) / 1000);
        LogUtil.a("Suggestion_CoachActivityHelper", "minDuration：", Integer.valueOf(i), ": ", simpleDateFormat.format(Long.valueOf(j)), Constants.LINK, simpleDateFormat.format(Long.valueOf(j2)));
        if (i == 0) {
            LogUtil.a("Suggestion_CoachActivityHelper", "durationSecond:", Integer.valueOf(i2));
            e(map, j4, i2);
            return;
        }
        if (i < 0) {
            LogUtil.b("Suggestion_CoachActivityHelper", "saveFitnessIntensityToMap durationMin < 0 error");
            return;
        }
        for (int i3 = 0; i3 <= i; i3++) {
            if (i3 == 0) {
                e(map, j4, (int) (((j4 + 60000) - j) / 1000));
            } else if (i3 > 0 && i3 < i) {
                map.put(Long.valueOf((i3 * 60000) + j4), 60);
            } else {
                map.put(Long.valueOf(j6), Integer.valueOf((int) ((j2 - j6) / 1000)));
            }
        }
    }

    private static void e(Map<Long, Integer> map, long j, int i) {
        if (map.containsKey(Long.valueOf(j))) {
            i += map.get(Long.valueOf(j)).intValue();
        }
        map.put(Long.valueOf(j), Integer.valueOf(i));
    }

    public static void d(Map<Long, Integer> map, List<HeartRateData> list, int i) {
        LogUtil.a("Suggestion_CoachActivityHelper", "saveFitnessIntensityToMap:", Integer.valueOf(i));
        ArrayList<HeartRateData> arrayList = new ArrayList();
        long acquireTime = list.get(0).acquireTime();
        int acquireHeartRate = list.get(0).acquireHeartRate();
        int i2 = 1;
        for (int i3 = 1; i3 < list.size(); i3++) {
            HeartRateData heartRateData = list.get(i3);
            acquireHeartRate += heartRateData.acquireHeartRate();
            i2++;
            if (heartRateData.acquireTime() - acquireTime > 60000) {
                LogUtil.a("Suggestion_CoachActivityHelper", "minuteHeartRate:", Long.valueOf(heartRateData.acquireTime()), Integer.valueOf(heartRateData.acquireHeartRate()));
                long acquireTime2 = (heartRateData.acquireTime() / 60000) * 60000;
                int round = Math.round(acquireHeartRate / i2);
                LogUtil.a("Suggestion_CoachActivityHelper", "timeMill:", Long.valueOf(acquireTime2), "hearRate:", Integer.valueOf(round));
                arrayList.add(new HeartRateData(acquireTime2, round));
                acquireTime = heartRateData.acquireTime();
                acquireHeartRate = 0;
                i2 = 0;
            }
        }
        if (i2 != 0) {
            long j = (acquireTime / 60000) * 60000;
            int round2 = Math.round(acquireHeartRate / i2);
            LogUtil.a("Suggestion_CoachActivityHelper", "timeMill:", Long.valueOf(j), "hearRate:", Integer.valueOf(round2));
            arrayList.add(new HeartRateData(j, round2));
        }
        for (HeartRateData heartRateData2 : arrayList) {
            if (heartRateData2.acquireHeartRate() >= i && heartRateData2.acquireTime() > 0) {
                map.put(Long.valueOf(heartRateData2.acquireTime()), 60);
            }
        }
    }

    public static void a(Map<Long, Integer> map) {
        if (map == null) {
            LogUtil.h("Suggestion_CoachActivityHelper", "saveMapToFitnessIntensity map is null");
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS);
        StringBuilder sb = new StringBuilder(16);
        int i = 0;
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            int intValue = entry.getValue().intValue();
            if (intValue > 0) {
                arrayList.add(entry.getKey());
                String format = simpleDateFormat.format(entry.getKey());
                i += intValue;
                sb.append("~key:");
                sb.append(format);
                sb.append("-value:");
                sb.append(entry.getValue());
            } else {
                LogUtil.b("Suggestion_CoachActivityHelper", "saveMapToFitnessIntensity key < = 0 error");
            }
        }
        LogUtil.a("Suggestion_CoachActivityHelper", "saveMapToFitnessIntensity sumSecond:", Integer.valueOf(i), "--logStr:", sb);
        DataPlatformApi dataPlatformApi = (DataPlatformApi) Services.a("CoursePlanService", DataPlatformApi.class);
        if (dataPlatformApi == null) {
            LogUtil.h("Suggestion_CoachActivityHelper", "saveMapToFitnessIntensity dataPlatformApi is null.");
        } else {
            dataPlatformApi.insertFitnessIntensityData(BaseApplication.getContext(), arrayList);
            map.clear();
        }
    }
}
