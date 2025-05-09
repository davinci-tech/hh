package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepCounterSupportUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public class gnj {
    public static boolean c(long j, Context context) {
        if (j != -1 || !StepCounterSupportUtils.d(context)) {
            return false;
        }
        ReleaseLogUtil.e("R_StepBiUtils", "reportNotStep");
        SharedPerferenceUtils.ah(context);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(3);
        c(context, linkedHashMap);
        linkedHashMap.put("errorCode", String.valueOf(1005));
        linkedHashMap.put("reportNum", String.valueOf(SharedPerferenceUtils.o(context)));
        linkedHashMap.put("isAllowStep", String.valueOf(StepCounterSupportUtils.e(context)));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), linkedHashMap);
        return true;
    }

    public static boolean e(int i, int i2, Context context) {
        if (i != i2) {
            return false;
        }
        ReleaseLogUtil.e("R_StepBiUtils", "reportSendSameStep startStep:", Integer.valueOf(i));
        SharedPerferenceUtils.ai(context);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        c(context, linkedHashMap);
        linkedHashMap.put("errorCode", String.valueOf(1006));
        linkedHashMap.put("reportNum", String.valueOf(SharedPerferenceUtils.n(context)));
        linkedHashMap.put("startStep", String.valueOf(i));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), linkedHashMap);
        return true;
    }

    private static void c(Context context, LinkedHashMap<String, String> linkedHashMap) {
        if (CommonUtil.bh()) {
            String str = Build.DISPLAY;
            if (TextUtils.isEmpty(str) || !str.contains("log")) {
                return;
            }
            String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
            if (TextUtils.isEmpty(accountInfo) || HuaweiHealth.b().equals(accountInfo) || accountInfo.length() <= 4) {
                return;
            }
            int length = accountInfo.length();
            int i = length / 3;
            String substring = accountInfo.substring(0, i);
            int i2 = length / 2;
            String substring2 = accountInfo.substring(i, i2);
            String substring3 = accountInfo.substring(i2, length);
            linkedHashMap.put("HF", substring);
            linkedHashMap.put("HS", substring2);
            linkedHashMap.put("HT", substring3);
        }
    }

    public static void h() {
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), 1009);
    }

    public static void c(List<String> list) {
        if (bkz.e(list)) {
            LogUtil.h("R_StepBiUtils", "reportHWPhoneServiceBeKilled thirdMobileMgrList empty");
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put("errorCode", String.valueOf(1010));
        linkedHashMap.put("thirdMobileMgrList", new Gson().toJson(list));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), linkedHashMap);
    }

    public static void d() {
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), 1011);
    }

    public static void f() {
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), d(1012));
    }

    public static void j() {
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), d(1013));
    }

    public static void b() {
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), d(1014));
    }

    public static void a() {
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), d(1015));
    }

    private static LinkedHashMap<String, String> d(int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
        linkedHashMap.put("errorCode", String.valueOf(i));
        linkedHashMap.put("model", Build.MODEL);
        linkedHashMap.put("thermalLevel", String.valueOf(ThermalCallback.getInstance().getThermalLevel()));
        linkedHashMap.put("isKeepStepAlive", String.valueOf(lcu.b()));
        return linkedHashMap;
    }

    public static void e() {
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), a(1016));
    }

    public static void c() {
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), a(1017));
    }

    public static void g() {
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), a(1018));
    }

    private static LinkedHashMap<String, String> a(int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("errorCode", String.valueOf(i));
        linkedHashMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        linkedHashMap.put("serialNumber", bkx.c());
        linkedHashMap.put("devBoard", Build.BOARD);
        linkedHashMap.put("devModel", Build.MODEL);
        linkedHashMap.put("androidOS", String.valueOf(Build.VERSION.SDK_INT));
        linkedHashMap.put("appVersion", String.valueOf(CommonUtil.d(BaseApplication.getContext())));
        return linkedHashMap;
    }

    public static void e(long j, int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
        linkedHashMap.put("errorCode", String.valueOf(1019));
        linkedHashMap.put("currentStep", String.valueOf(i));
        linkedHashMap.put("recoverDuration", String.valueOf(System.currentTimeMillis() - j));
        linkedHashMap.put("clickTime", String.valueOf(j));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), linkedHashMap);
    }
}
