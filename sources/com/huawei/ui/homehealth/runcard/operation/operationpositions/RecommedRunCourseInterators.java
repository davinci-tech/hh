package com.huawei.ui.homehealth.runcard.operation.operationpositions;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.runcard.operation.recommendalgo.model.RecommendSchedule;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes9.dex */
public class RecommedRunCourseInterators {

    public interface UpdateWorkoutsCallback {
        void onUpdateFinish();
    }

    public static RecommendSchedule e(int i) {
        LogUtil.h("Track_RecommedRunCourseInterators", "getCoursesBySportLevel ", Integer.valueOf(i));
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), b(i));
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            return (RecommendSchedule) new Gson().fromJson(b, RecommendSchedule.class);
        } catch (JsonSyntaxException e) {
            LogUtil.h("Track_RecommedRunCourseInterators", "getCoursesBySportLevel ", e.getMessage());
            return null;
        }
    }

    public static void a() {
        for (int i = -1; i <= 5; i++) {
            RecommendSchedule e = e(i);
            if (e != null) {
                e.resetSchedule();
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), b(i), new Gson().toJson(e), new StorageParams());
            }
        }
    }

    private static String b(int i) {
        switch (i) {
            case -1:
                return "KEY_COURSE_LEVEL_NONE";
            case 0:
                return "KEY_COURSE_LEVEL_VERY_POOR";
            case 1:
                return "KEY_COURSE_LEVEL_POOR";
            case 2:
                return "KEY_COURSE_LEVEL_FAIR";
            case 3:
                return "KEY_COURSE_LEVEL_AVERAGE";
            case 4:
                return "KEY_COURSE_LEVEL_GOOD";
            case 5:
                return "KEY_COURSE_LEVEL_VERY_GOOD";
            case 6:
                return "KEY_COURSE_LEVEL_TREADMILL";
            default:
                return "";
        }
    }
}
