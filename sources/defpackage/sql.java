package defpackage;

import com.huawei.exercise.modle.RunPlanRecordInfo;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Map;

/* loaded from: classes7.dex */
public class sql {
    public static void b(int i, int i2, Map<String, Integer> map, RunPlanRecordInfo runPlanRecordInfo) {
        if (map == null || runPlanRecordInfo == null) {
            return;
        }
        map.put("record_id", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_id()));
        map.put("status", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_status()));
        map.put("load_peak", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_load_peak()));
        map.put("etraining_effect", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_etraining_effect()));
        map.put("anaerobic_exercise_etraining_effect", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_anaerobic_exercise_etraining_effect()));
        map.put("extra_poc", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_Epoc()));
        if (i != 264 || i2 == 5) {
            map.put("max_met", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_maxMET()));
        }
        map.put("recovery_time", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_recovery_time()));
        map.put("achieve_percent", Integer.valueOf(runPlanRecordInfo.getRun_plan_record_info_achieve_percent()));
    }

    public static void a(Map<String, Integer> map, MotionPathSimplify motionPathSimplify, kvq kvqVar) {
        if (map == null || motionPathSimplify == null || kvqVar == null) {
            return;
        }
        if (motionPathSimplify.requestEndTime() - kvqVar.m() <= 600000) {
            map.put("etraining_effect", Integer.valueOf(kvqVar.b()));
            map.put("recovery_time", Integer.valueOf(kvqVar.n()));
            LogUtil.a("Track_RunPlanRecordInfoConvert", "The interval time is less than 10 minutes");
        }
        map.put("max_met", Integer.valueOf(kvqVar.y()));
    }
}
