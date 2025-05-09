package defpackage;

import android.os.Build;
import com.huawei.health.awarenessdonate.AwarenessDonateApi;
import com.huawei.health.awarenessdonate.AwarenessDonateImpl;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import defpackage.bzn;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

/* loaded from: classes7.dex */
public class qtn {
    public static void c() {
        if (!a()) {
            LogUtil.c("CeliaSuggestUtil", "skip donate force, not target user");
            return;
        }
        long b = SharedPreferenceManager.b(AwarenessDonateImpl.AWARENESS_FEATURE_MODULE_ID, "diet_force_donate_time", -1L);
        if (b > 0) {
            LogUtil.c("CeliaSuggestUtil", "skip donate force, already donated in: " + b);
        } else {
            LogUtil.c("CeliaSuggestUtil", "donate force...");
            e(d());
            SharedPreferenceManager.e(AwarenessDonateImpl.AWARENESS_FEATURE_MODULE_ID, "diet_force_donate_time", System.currentTimeMillis());
        }
    }

    public static void b() {
        LogUtil.c("CeliaSuggestUtil", "donate after using diet diary...");
        e(e());
    }

    private static List<bzn.e> d() {
        ArrayList arrayList = new ArrayList();
        long d = d(7, 30);
        long d2 = d(8, 30);
        long d3 = d(11, 30);
        long d4 = d(13, 0);
        long d5 = d(17, 30);
        long d6 = d(19, 0);
        int i = 1;
        for (int i2 = 7; i < i2; i2 = 7) {
            ArrayList arrayList2 = arrayList;
            long j = i * 86400000;
            int i3 = i;
            long j2 = d6;
            long j3 = d;
            bzn.e eVar = new bzn.e(d + j, d2 + j);
            bzn.e eVar2 = new bzn.e(d3 + j, d4 + j);
            bzn.e eVar3 = new bzn.e(d5 + j, j2 + j);
            arrayList2.add(eVar);
            arrayList2.add(eVar2);
            arrayList2.add(eVar3);
            i = i3 + 1;
            arrayList = arrayList2;
            d6 = j2;
            d = j3;
            d2 = d2;
        }
        return arrayList;
    }

    private static long d(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, 0);
        return calendar.getTimeInMillis();
    }

    private static void e(List<bzn.e> list) {
        if (list == null || list.size() < 1) {
            LogUtil.a("CeliaSuggestUtil", "donate skipped, period is empty");
            return;
        }
        bzn d = new bzn.b().f("featurePrediction").g(UUID.randomUUID().toString()).a(Build.MODEL).c("phone").b(Build.BRAND + " " + Build.MODEL).a(0).l(TimeZone.getDefault().getDisplayName(false, 0)).a(System.currentTimeMillis()).b(list.get(0).c()).c(list.get(list.size() - 1).e()).e("com.huawei.ohos.healthservice").o(FaCardAgdsApi.DIET_FA_CARD).d("com.huawei.ohos.healthservice.diet.DietDiaryMiniFormAbility").h("diet_diary_mini").i("1*2").j("dietaryDiary").b(list).d();
        AwarenessDonateApi awarenessDonateApi = (AwarenessDonateApi) Services.c("HWSmartInteractMgr", AwarenessDonateApi.class);
        if (awarenessDonateApi.isSupportDonate()) {
            LogUtil.c("CeliaSuggestUtil", "donate efficient rest");
            awarenessDonateApi.donateFeature(d);
        }
    }

    private static List<bzn.e> e() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < 7; i++) {
            long j = i * 86400000;
            arrayList.add(new bzn.e((currentTimeMillis - 1800000) + j, j + 1800000 + currentTimeMillis2));
        }
        return arrayList;
    }

    private static boolean a() {
        if (!"1".equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005))) {
            LogUtil.c("CeliaSuggestUtil", "isTargetUser false, gender not support");
            return false;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1006);
        if (accountInfo == null) {
            LogUtil.a("CeliaSuggestUtil", "isTargetUser false, birthday is empty");
            return false;
        }
        try {
            Date parse = new SimpleDateFormat("yyyyMMdd").parse(accountInfo);
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar2.setTime(parse);
            if (calendar2.after(calendar)) {
                LogUtil.a("CeliaSuggestUtil", "isTargetUser false, birthday not valid");
                return false;
            }
            int i = calendar.get(1) - calendar2.get(1);
            if (calendar.get(6) < calendar2.get(6)) {
                i--;
            }
            return i >= 18 && i <= 44;
        } catch (ParseException unused) {
            LogUtil.a("CeliaSuggestUtil", "isTargetUser false, birthday parse failed");
            return false;
        }
    }
}
