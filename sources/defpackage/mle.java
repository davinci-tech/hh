package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mle {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f15051a = {"2855670", "2934914", "2624136", "2787804", "3120668", "3279864", "3368659", "3428891", "4018400", "4142595", "4267785", "4304180", "4414136", "4587804"};
    private static final String[] e = {"3509119", "3658543", "3793654", "3835521", "3904161"};
    private static final String[] c = {"2624136", "2787804"};
    private static final String[] b = {"1000", "1100", AgdConstant.INSTALL_TYPE_MINI_MANUAL, AgdConstant.INSTALL_TYPE_MINI_AUTO, "1300", "1400", "1500", "1600", "1700", "1900"};
    private static final String[] g = {"1100", AgdConstant.INSTALL_TYPE_MINI_MANUAL, "1900", "130010", "210020", "2160030", "430140", "540550", "651060", "760070", "800110", "910010", "1005020", "1110030", "1260040", "1350050", "1400110", "1510010", "1605020", "1710030", "1860040", "1950050", "2010050", "3076638"};
    private static final String[] d = {ResultCode.CODE_UNKNOWN_ERROR, "10002", "10003", "10004", ResultCode.CODE_AUTH_FAIL, "20001", "20002", "20003", "20004", "20005", "20006", "20007", "20008", "20009", "30001", "30002", "30003", "30004", "30005", "30006", "30007", "30008", "30009", "30010", "30011", "30012", "30013", "30014", "30015", "30016", "30017", "30018", "40001", "40002", "40003", "40004", "40005", "40006", "40007", "40008", "40009", "40010", "40011", "40012", "40013", "40014"};

    public static boolean a(int i) {
        return i == 30008;
    }

    public static boolean c(int i) {
        return i == 40006 || i == 40007 || i == 40008 || i == 30013 || i == 30014 || i == 40009;
    }

    public static boolean d(int i) {
        return i == 10003 || i == 10002 || i == 10004 || i == 10001 || i == 10005;
    }

    public static boolean e(long j, long j2) {
        return j2 - j > 120000;
    }

    public static boolean a(long j, long j2) {
        if (j2 < j) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "afterTimestamps is invalid");
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        return (calendar.get(5) == calendar2.get(5) && calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1)) ? false : true;
    }

    public static boolean d(long j, long j2) {
        if (j2 < j) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "afterTimestamps is invalid");
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.setFirstDayOfWeek(2);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        calendar2.setFirstDayOfWeek(2);
        return calendar.get(3) != calendar2.get(3);
    }

    public static boolean c(long j, long j2) {
        if (j2 < j) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "afterTimestamps is invalid");
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        return (calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1)) ? false : true;
    }

    public static long a(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "getLastSyncDate syncDate=", str);
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e2) {
                LogUtil.b("PLGACHIEVE_KakaUtil", "NumberFormatException", e2.getMessage());
            }
        }
        return 0L;
    }

    public static String e() {
        StringBuilder sb = new StringBuilder(10);
        String format = new SimpleDateFormat("Z", Locale.ENGLISH).format(Calendar.getInstance().getTime());
        if (!TextUtils.isEmpty(format)) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "getNewTimezone gmtStr =", format);
            sb.append(format);
        } else {
            sb.append("+0800");
        }
        return sb.toString();
    }

    public static boolean a(Context context) {
        if (context == null) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "isRTLLanguage() context is null");
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return "ar".equalsIgnoreCase(language) || "iw".equalsIgnoreCase(language) || "fa".equalsIgnoreCase(language) || Constants.URDU_LANG.equalsIgnoreCase(language) || "ug".equalsIgnoreCase(language);
    }

    public static boolean a(long j) {
        if (0 == j) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        return j >= d(true, currentTimeMillis) && j <= d(false, currentTimeMillis);
    }

    public static boolean l(String str) {
        return String.valueOf(1100).equals(str) || String.valueOf(1200).equals(str) || String.valueOf(KakaConstants.TASK_ENTER_TODAY_WEIGHT).equals(str);
    }

    public static boolean d(String str) {
        return String.valueOf(1200).equals(str) || String.valueOf(KakaConstants.TASK_ENTER_TODAY_WEIGHT).equals(str);
    }

    public static boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return String.valueOf(1900).equals(str) || String.valueOf(KakaConstants.TASK_SEC_EXCHANGE_INSURANCE).equals(str) || String.valueOf(KakaConstants.TASK_HEALTH_JUMP).equals(str);
    }

    public static boolean f(String str) {
        return (String.valueOf(3).equals(str) || String.valueOf(2).equals(str)) ? false : true;
    }

    public static boolean m(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "judgeTaskIDByVersion key is null");
            return false;
        }
        if (!mlg.b(b, str)) {
            return false;
        }
        LogUtil.a("PLGACHIEVE_KakaUtil", "judgeTaskIDByVersion true");
        return true;
    }

    public static boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "judgeTaskIDByVersionTwo key is null");
            return false;
        }
        if (!mlg.b(g, str) && !mlg.b(d, str)) {
            return false;
        }
        LogUtil.c("PLGACHIEVE_KakaUtil", "judgeTaskIDByVersionTwo true taskId=", str);
        return true;
    }

    public static boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_KakaUtil", "isTaskIdInHealthLife taskId is null");
            return false;
        }
        if (!mlg.b(f15051a, str)) {
            return false;
        }
        LogUtil.c("PLGACHIEVE_KakaUtil", "isTaskIdInHealthLife true taskId=", str);
        return true;
    }

    public static boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_KakaUtil", "isTaskIdInHealthLife taskId is null");
            return false;
        }
        if (!mlg.b(e, str)) {
            return false;
        }
        LogUtil.c("PLGACHIEVE_KakaUtil", "isTaskIdInHealthLife true taskId=", str);
        return true;
    }

    public static boolean j(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_KakaUtil", "isTaskIdInHealthLife taskId is null");
            return false;
        }
        if (!mlg.b(c, str)) {
            return false;
        }
        LogUtil.c("PLGACHIEVE_KakaUtil", "isTaskIdInHealthLife true taskId=", str);
        return true;
    }

    public static long d(boolean z, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (z) {
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
        } else {
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
        }
        return calendar.getTimeInMillis();
    }

    public static void ckk_(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                LogUtil.h("PLGACHIEVE_KakaUtil", "avoidImplicitProblem: packageManager is null ");
                return;
            }
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (resolveActivity == null) {
                LogUtil.h("PLGACHIEVE_KakaUtil", "avoidImplicitProblem: resolveInfo is null ");
                return;
            }
            intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("PLGACHIEVE_KakaUtil", "avoidImplicitProblem: exception -> " + e2.getMessage());
        }
    }

    public static long e(String str) {
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(str).getTime();
        } catch (ParseException unused) {
            LogUtil.b("PLGACHIEVE_KakaUtil", "getDateZeroTimeStamp parseException");
            return 0L;
        }
    }

    public static int[] e(long j) {
        int i = (int) (j / 86400000);
        long j2 = j - (i * 86400000);
        int i2 = (int) (j2 / 3600000);
        long j3 = j2 - (i2 * 3600000);
        int i3 = (int) (j3 / 60000);
        return new int[]{i, i2, i3, (int) ((j3 - (i3 * 60000)) / 1000)};
    }

    public static String e(int i) {
        String e2 = UnitUtil.e(i, 1, 0);
        String e3 = UnitUtil.e(0.0d, 1, 0);
        if (String.valueOf(i).length() != 1) {
            return e2;
        }
        return e3 + e2;
    }

    public static boolean c(mdf mdfVar) {
        if (mdfVar == null) {
            LogUtil.h("PLGACHIEVE_KakaUtil", "isKakaTaskFinished oldTaskInfo is null");
            return false;
        }
        if (a(a(mct.b(BaseApplication.getContext(), "kakaSyncDate")), System.currentTimeMillis())) {
            return false;
        }
        String p = mdfVar.p();
        LogUtil.a("PLGACHIEVE_KakaUtil", "isKakaTaskFinished taskStatus = ", p);
        return String.valueOf(1).equals(p) || String.valueOf(2).equals(p) || String.valueOf(3).equals(p);
    }

    public static List<mdf> b(int[] iArr, List<mdf> list) {
        ArrayList arrayList = new ArrayList(2);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_KakaUtil", "getTaskListsByRules taskLists is empty");
            return arrayList;
        }
        if (iArr == null || iArr.length == 0) {
            LogUtil.h("PLGACHIEVE_KakaUtil", "getTaskListsByRules taskRules is empty");
            return list;
        }
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        for (mdf mdfVar : list) {
            if (hashSet.contains(Integer.valueOf(mdfVar.ag()))) {
                arrayList.add(mdfVar);
            }
        }
        LogUtil.a("PLGACHIEVE_KakaUtil", "getTaskListsByRules taskLists size = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public static void b(int i, int i2, int i3) {
        LogUtil.a("PLGACHIEVE_KakaUtil", "kakaPageClickBiEvent type ", Integer.valueOf(i), " result ", Integer.valueOf(i2), " value ", Integer.valueOf(i3));
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("status", Integer.valueOf(i3));
        if (i == 6) {
            hashMap.put("result", Integer.valueOf(i2));
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_KAKA_1100006.value(), hashMap, 0);
    }

    public static void b(CountDownLatch countDownLatch) {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public static void c() {
        long e2 = nsj.e(System.currentTimeMillis(), 0) + 72000000;
        LogUtil.a("PLGACHIEVE_KakaUtil", "registerKakaReminder requestCode ", 20210603, ", time ", Long.valueOf(e2));
        Context context = BaseApplication.getContext();
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.pluginachievement.ui.kakatask.KakaReminderReceiver");
        intent.putExtra("reminderHuid", LoginInit.getInstance(context).getAccountInfo(1011));
        intent.putExtra("reminderTime", e2);
        sqa.ekC_(20210603, intent, 201326592, 0, e2, 86400000L);
    }

    public static void a() {
        LogUtil.a("PLGACHIEVE_KakaUtil", "cancelKakaReminder requestCode ", 20210603);
        Context context = BaseApplication.getContext();
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.pluginachievement.ui.kakatask.KakaReminderReceiver");
        intent.setPackage(BaseApplication.getAppPackage());
        sqa.ekn_(20210603, intent, 201326592);
    }

    public static void b(mdf mdfVar) {
        if (mdfVar.ag() == 10005) {
            LogUtil.a("PLGACHIEVE_KakaUtil", "save vip taskId to sp");
            mct.b(BaseApplication.getContext(), "needReloadTaskId", mdfVar.h());
        }
    }

    public static void c(String str) {
        String e2 = mct.e(BaseApplication.getContext(), "needReloadTaskId", "");
        LogUtil.a("PLGACHIEVE_KakaUtil", "clearVipTaskStatusToSp start");
        if (TextUtils.isEmpty(e2) || !e2.equals(str)) {
            return;
        }
        LogUtil.c("PLGACHIEVE_KakaUtil", "clearVipTaskStatusToSp vipTaskId = ", e2);
        mct.b(BaseApplication.getContext(), "needReloadTaskId", "");
    }

    public static void b() {
        String e2 = mct.e(BaseApplication.getContext(), "needReloadTaskId", "");
        if (TextUtils.isEmpty(e2)) {
            return;
        }
        LogUtil.a("PLGACHIEVE_KakaUtil", "vip task need refresh to cloud");
        mdf mdfVar = new mdf();
        mdfVar.e(e2);
        mdfVar.q(10005);
        mdfVar.n(0);
        mer.b(BaseApplication.getContext()).a(mdfVar, 1);
    }

    public static boolean e(mdf mdfVar) {
        if (mdfVar.ag() != 10005) {
            return true;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "MAIN_VIP_KEY");
        LogUtil.a("PLGACHIEVE_KakaUtil", "parseKakaTaskInfo vip status = :", b2);
        LogUtil.a("PLGACHIEVE_KakaUtil", "parseKakaTaskInfo vip task status = :", mdfVar.p());
        if (!"0".equals(b2) && !String.valueOf(1).equals(mdfVar.p())) {
            return false;
        }
        LogUtil.a("PLGACHIEVE_KakaUtil", "parseKakaTaskInfo show vip task ");
        return true;
    }

    public static void c(Context context, int i) {
        if (gpo.b()) {
            AppRouter.b("/OperationBundle/MemberRelayActivity").c(context);
            return;
        }
        LogUtil.a("PLGACHIEVE_KakaUtil", "gotoVipPage vip status = :", SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "MAIN_VIP_KEY"));
        gpn.c(context, "from=" + i);
    }

    public static String b(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        return UnitUtil.a("HH:mm", calendar.getTimeInMillis());
    }

    public static void e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_KakaUtil", "jumpToH5OrSchemePage context or pagePath is empty");
            return;
        }
        if (str.startsWith("http") || str.startsWith(ProxyConfig.MATCH_HTTPS)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            Intent createWebViewIntent = bzs.e().createWebViewIntent(context, bundle, 268435456);
            if (createWebViewIntent != null) {
                context.startActivity(createWebViewIntent);
                LogUtil.a("PLGACHIEVE_KakaUtil", "jumpToH5OrSchemePage jump to webviewActivity");
                return;
            } else {
                LogUtil.h("PLGACHIEVE_KakaUtil", "jumpToH5OrSchemePage h5 intent is null");
                return;
            }
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
        intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
        jdw.bGh_(intent, context);
        LogUtil.a("PLGACHIEVE_KakaUtil", "jumpToH5OrSchemePage jump to scheme page");
    }

    public static Map<String, Object> a(ArrayList<mkg> arrayList, int i) {
        HashMap hashMap = new HashMap(2);
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<mkg> it = arrayList.iterator();
            int i2 = 0;
            int i3 = 0;
            while (it.hasNext()) {
                mkj b2 = it.next().b();
                if (b2 != null && (i != 1 || !d(b2.n()))) {
                    i2++;
                    if ("2".equals(b2.f())) {
                        i3++;
                    }
                }
            }
            LogUtil.a("PLGACHIEVE_KakaUtil", "aquireKakaTaskInfo taskSize ", Integer.valueOf(i2), " finishCount ", Integer.valueOf(i3), " from ", Integer.valueOf(i));
            hashMap.put("task_size", Integer.valueOf(i2));
            hashMap.put("finish_count", Integer.valueOf(i3));
        }
        return hashMap;
    }

    public static ArrayList<mkg> d() {
        ArrayList<mkg> arrayList = new ArrayList<>(0);
        HashMap hashMap = new HashMap(2);
        hashMap.put(ParsedFieldTag.KAKA_TASK_SCENARIO, String.valueOf(0));
        List<mcz> b2 = meh.c(BaseApplication.getContext()).b(12, hashMap);
        if (b2 == null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(b2.size());
        mdf mdfVar = null;
        boolean z = true;
        for (mcz mczVar : b2) {
            if (mczVar instanceof mdf) {
                mdf mdfVar2 = (mdf) mczVar;
                int e2 = nsn.e(mdfVar2.p());
                if (mdfVar2.ag() == 10004 && e2 < 1) {
                    z = false;
                }
                if (mdfVar2.ag() == 30008) {
                    mdfVar = mdfVar2;
                }
                arrayList2.add(mdfVar2);
            }
        }
        if (mdfVar != null && !z) {
            arrayList2.remove(mdfVar);
        }
        return mji.c((List<mdf>) arrayList2);
    }

    public static void a(int i, int i2) {
        LogUtil.a("PLGACHIEVE_KakaUtil", "setKakaDelayEventBi event ", Integer.valueOf(i));
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("from", Integer.valueOf(i2));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_KAKA_1100082.value(), hashMap, 0);
    }

    public static boolean a(UserAchieveWrapper userAchieveWrapper) {
        if (userAchieveWrapper == null) {
            return false;
        }
        return userAchieveWrapper.getContentType() == 11 || userAchieveWrapper.getContentType() == 19 || userAchieveWrapper.getContentType() == 20 || userAchieveWrapper.getContentType() == 15;
    }

    public static void b(String str) {
        if (o("com.vmall.client")) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("vmall://com.vmall.client/home/activity?isShowLayout=false&pn=pointsmall" + str));
            intent.setPackage("com.vmall.client");
            intent.setFlags(268435456);
            ckl_(intent);
            return;
        }
        n(str);
        LogUtil.a("PLGACHIEVE_KakaUtil", "gotoVmall : VMALL APP is not installed");
    }

    private static boolean o(String str) {
        if (jdm.b(BaseApplication.getContext(), str)) {
            return true;
        }
        LogUtil.h("PLGACHIEVE_KakaUtil", "isInstallVmallApp VMALL APP is not installed");
        return false;
    }

    private static void n(final String str) {
        GRSManager.a(BaseApplication.getContext()).e("domainMVmall", new GrsQueryCallback() { // from class: mle.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    LogUtil.h("PLGACHIEVE_KakaUtil", "gotoVmallWeb vmallHost is empty");
                    return;
                }
                Intent intent = new Intent();
                intent.setClassName(com.huawei.haf.application.BaseApplication.d(), "com.huawei.operation.activity.WebViewActivity");
                intent.putExtra("url", str2 + "/portal/activity/index.html?isShowLayout=false&pn=pointsmall" + str);
                mle.ckk_(BaseApplication.getContext(), intent);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("PLGACHIEVE_KakaUtil", "onCallBackFail index = ", Integer.valueOf(i));
            }
        });
    }

    private static void ckl_(Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
        intent.setComponent(componentName);
        nsn.cLM_(intent, componentName.getPackageName(), BaseApplication.getContext(), nsf.h(R.string._2130842624_res_0x7f021400));
    }
}
