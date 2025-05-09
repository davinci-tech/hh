package defpackage;

import android.os.Bundle;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwbasemgr.IBaseStatusCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwvoiceplaymodel.IVoiceHandler;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes4.dex */
public class fej implements IVoiceHandler {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12470a = new Object();
    private static fej b;
    private IBaseStatusCallback d = new IBaseStatusCallback() { // from class: fej.1
        @Override // com.huawei.hwbasemgr.IBaseStatusCallback
        public void onAvailable() {
            LogUtil.a("Suggestion_PluginSuggestionVoiceHandler", "onAvailable invoke");
            feq.d(0);
        }

        @Override // com.huawei.hwbasemgr.IBaseStatusCallback
        public void onOccupied() {
            LogUtil.a("Suggestion_PluginSuggestionVoiceHandler", "onOccupied invoke");
            feq.d(2);
        }
    };

    private fej() {
    }

    public static fej e() {
        fej fejVar;
        synchronized (f12470a) {
            if (b == null) {
                b = new fej();
            }
            fejVar = b;
        }
        return fejVar;
    }

    public void c() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_PluginSuggestionVoiceHandler", "registerFitnessCourseListen : courseApi is null.");
        } else {
            courseApi.registerFitnessCourseStatusListen(this.d);
        }
    }

    @Override // com.huawei.hwvoiceplaymodel.IVoiceHandler
    public int handleVoiceEvent(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("Suggestion_PluginSuggestionVoiceHandler", "handleVoiceEvent voiceMsg is null");
            return -4;
        }
        String string = bundle.getString("voiceMessage");
        if (string == null) {
            LogUtil.h("Suggestion_PluginSuggestionVoiceHandler", "handleVoiceEvent message is null");
            return -4;
        }
        if (feq.b() != 0) {
            LogUtil.a("Suggestion_PluginSuggestionVoiceHandler", "handleVoiceEvent status conflict");
            if (gso.e().i() == 2) {
                fek.c().d(R.raw._2131886172_res_0x7f12005c);
            }
            if (gso.e().i() != 1) {
                return -2;
            }
            fek.c().d(R.raw._2131886173_res_0x7f12005d);
            return -2;
        }
        int c = c(string);
        LogUtil.a("Suggestion_PluginSuggestionVoiceHandler", "handleVoiceEvent message = ", string, ", result = ", Integer.valueOf(c));
        return c;
    }

    private int c(String str) {
        int i = -1;
        if (!str.endsWith("warmupCourse") && !str.endsWith("stretchCourse")) {
            LogUtil.h("Suggestion_PluginSuggestionVoiceHandler", "dispatchVoiceEvent message.endsWith not ACTION_WARM_UP_COURSE or ACTION_STRETCH_COURSE");
            return -1;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveVersion(null);
        workoutRecord.saveExerciseTime(new Date().getTime());
        String str2 = "";
        workoutRecord.savePlanId("");
        if (str.endsWith("warmupCourse")) {
            feq.c("warmupCourse");
            workoutRecord.saveWorkoutId("R001");
            workoutRecord.saveWorkoutName("跑前热身");
            i = 1;
        }
        if (str.endsWith("stretchCourse")) {
            feq.c("stretchCourse");
            workoutRecord.saveWorkoutId("R002");
            workoutRecord.saveWorkoutName(BaseApplication.getContext().getString(R.string._2130842372_res_0x7f021304));
            str2 = "FitnessCourse_stretch";
            i = 2;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(workoutRecord);
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete() || !Utils.j()) {
            return -3;
        }
        mmp mmpVar = new mmp(workoutRecord.acquireWorkoutId());
        mmpVar.d(str2);
        mmpVar.e(i);
        mmpVar.a(true);
        mod.c(BaseApplication.getContext(), mmpVar, arrayList);
        return 0;
    }
}
