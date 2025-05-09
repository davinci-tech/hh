package defpackage;

import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class euv {
    public static void c(String str) {
        ash.a("needSendPlanFinish", str);
        String valueOf = String.valueOf(ase.d(System.currentTimeMillis()));
        ash.a("needSendPlanFinishDeadline", valueOf);
        ash.a("hasSendFinishPlanDevices", moj.e(new ArrayList()));
        LogUtil.a("Suggestion_PlanFinishHelper", "setNeedSendFinishPlan, planid:", str, " time:", valueOf);
        PluginSuggestion.getInstance().startSendIntelligentPlan();
    }

    public static String e() {
        return System.currentTimeMillis() <= CommonUtil.g(ash.b("needSendPlanFinishDeadline")) ? ash.b("needSendPlanFinish") : "";
    }

    public static boolean e(String str) {
        if (d()) {
            return !moj.b(ash.b("hasSendFinishPlanDevices"), String[].class).contains(str);
        }
        return false;
    }

    public static void a(String str) {
        ArrayList arrayList = new ArrayList(moj.b(ash.b("hasSendFinishPlanDevices"), String[].class));
        arrayList.add(str);
        ash.a("hasSendFinishPlanDevices", moj.e(arrayList));
    }

    public static boolean d() {
        String b = ash.b("needSendPlanFinish");
        long g = CommonUtil.g(ash.b("needSendPlanFinishDeadline"));
        LogUtil.a("Suggestion_PlanFinishHelper", "isNeedSendFinishPlan, planid:", b, " time:", Long.valueOf(g));
        return System.currentTimeMillis() <= g && StringUtils.i(b);
    }

    public static void d(int i) {
        SharedPreferenceManager.c("MMKV_SUGGEST_MODULE_TAG", "sendFinishPlanType", String.valueOf(i));
    }

    public static int a() {
        return Integer.parseInt(SharedPreferenceManager.e("MMKV_SUGGEST_MODULE_TAG", "sendFinishPlanType", String.valueOf(IntPlan.PlanType.AI_RUN_PLAN.getType())));
    }
}
