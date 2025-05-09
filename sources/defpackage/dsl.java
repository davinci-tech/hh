package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import com.google.gson.JsonObject;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.healthmodel.HealthLifeApi;
import com.huawei.health.healthmodel.HealthModelApi;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeStatistic;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class dsl {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f11820a = {R$string.IDS_settings_steps, R$string.IDS_intensity_new, R$string.IDS_health_model_workout, R$string.IDS_health_model_breath, R$string.IDS_health_model_wake_up, R$string.IDS_health_model_sleep, R$string.IDS_smile, R$string.IDS_mindfulness, R$string.IDS_drink_water, R$string.IDS_medicine, R$string.IDS_health_model_blood_pressure, R$string.IDS_health_model_diet, R$string.IDS_health_model_weight};
    private static SparseIntArray c = new SparseIntArray();

    /* JADX INFO: Access modifiers changed from: private */
    public static HealthLifeApi p() {
        return (HealthLifeApi) Services.a("BasicHealthModel", HealthLifeApi.class);
    }

    public static HealthModelApi c() {
        boolean isInstalledModule = AppBundle.e().isInstalledModule("PluginHealthModel");
        ReleaseLogUtil.b("R_HealthLife_HealthLifeUtil", "getHealthModelApi isInstalledModule ", Boolean.valueOf(isInstalledModule));
        if (isInstalledModule) {
            return (HealthModelApi) Services.a("PluginHealthModel", HealthModelApi.class);
        }
        s();
        boolean isInstalledModule2 = AppBundle.e().isInstalledModule("PluginHealthModel");
        ReleaseLogUtil.b("R_HealthLife_HealthLifeUtil", "getHealthModelApi initHealthModelApi isInstalledModule ", Boolean.valueOf(isInstalledModule2));
        if (isInstalledModule2) {
            return (HealthModelApi) Services.a("PluginHealthModel", HealthModelApi.class);
        }
        return null;
    }

    private static void s() {
        HandlerExecutor.e(new Runnable() { // from class: dsr
            @Override // java.lang.Runnable
            public final void run() {
                dsl.l();
            }
        });
    }

    static /* synthetic */ void l() {
        Intent intent = new Intent();
        intent.putExtra("moduleName", "PluginHealthModel");
        AppBundle.e().launchActivity(BaseApplication.e(), intent, new AppBundleLauncher.InstallCallback() { // from class: dsp
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent2) {
                return dsl.ZM_(context, intent2);
            }
        });
    }

    static /* synthetic */ boolean ZM_(Context context, Intent intent) {
        ReleaseLogUtil.b("R_HealthLife_HealthLifeUtil", "initHealthModelApi context ", context, " newIntent ", intent);
        return false;
    }

    public static void k() {
        m();
    }

    public static void m() {
        HealthLifeApi p = p();
        LogUtil.a("HealthLife_HealthLifeUtil", "initHealthLife api ", p);
        if (p == null) {
            return;
        }
        p.initHealthLife();
    }

    public static void r() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "setBinder api is null");
        } else {
            p.setBinder();
        }
    }

    public static void g() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "initDeviceManager api is null");
        } else {
            p.initDeviceManager();
        }
    }

    public static void o() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "registerH5ProService api is null");
        } else {
            p.registerH5ProService();
        }
    }

    public static void q() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "resetCache api is null");
        } else {
            p.resetCache();
        }
    }

    public static boolean n() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "isJoinHealthModel api is null");
            return false;
        }
        return p.isJoinHealthModel();
    }

    public static ArrayList<Integer> b() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getBloodPressurePlanIdList api is null");
            return new ArrayList<>();
        }
        return p.getBloodPressurePlanIdList();
    }

    public static List<cbk> d() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getBloodPressureAlarmInfoList api is null");
            return Collections.emptyList();
        }
        return p.getBloodPressureAlarmInfoList();
    }

    public static void c(String str, ResponseCallback<dso> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "queryInterventionPlanInfo callback is null");
            return;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "queryInterventionPlanInfo api is null");
            responseCallback.onResponse(-1, new dso());
        } else {
            p.queryInterventionPlanInfo(str, responseCallback);
        }
    }

    public static void b(String str, ResponseCallback<drx> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getHealthLifeChallenge callback is null");
            return;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getHealthLifeChallenge api is null");
            responseCallback.onResponse(-1, new drx());
        } else {
            p.getHealthLifeChallenge(str, responseCallback);
        }
    }

    public static void ZK_(Context context, Uri uri) {
        HealthModelApi c2 = c();
        if (c2 == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "gotoHealthModel healthModelApi is null");
            Intent intent = new Intent();
            intent.setPackage(BaseApplication.d());
            intent.setClassName(BaseApplication.e(), "com.huawei.health.MainActivity");
            intent.setFlags(131072);
            intent.putExtra(Constants.HOME_TAB_NAME, Constants.HOME);
            gnm.aPB_(context, intent);
            return;
        }
        c2.gotoHealthModel(context, uri);
    }

    public static void ZL_(Context context, Uri uri) {
        HealthModelApi c2 = c();
        if (c2 == null) {
            HealthModelApi c3 = c();
            if (c3 != null) {
                c3.gotoHealthModel(context, uri);
                return;
            }
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "gotoHealthModelByLink healthModelApi is null");
            Intent intent = new Intent();
            intent.setPackage(BaseApplication.d());
            intent.setClassName(BaseApplication.e(), "com.huawei.health.MainActivity");
            intent.setFlags(131072);
            intent.putExtra(Constants.HOME_TAB_NAME, Constants.HOME);
            gnm.aPB_(context, intent);
            return;
        }
        c2.gotoHealthModel(context, uri);
    }

    public static void e(List<HealthLifeBean> list) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "refreshLastDayRecordListCache api is null");
        } else {
            p.refreshLastDayRecordListCache(list);
        }
    }

    public static List<HealthLifeTaskBean> f() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getLastDayRecordListCache api is null");
            return Collections.emptyList();
        }
        return p.getLastDayRecordListCache();
    }

    public static List<HealthLifeTaskBean> e() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getDayRecordListCache api is null");
            return Collections.emptyList();
        }
        return p.getDayRecordListCache();
    }

    public static void a(int i, ResponseCallback<List<HealthLifeTaskBean>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getDayRecordList callback is null");
            return;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getDayRecordList api is null");
            responseCallback.onResponse(-1, Collections.emptyList());
        } else {
            p.getDayRecordList(i, responseCallback);
        }
    }

    public static void a(int i) {
        if (i == 5 || i == 7) {
            HealthLifeApi p = p();
            if (p == null) {
                ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "refreshTask api is null");
                return;
            } else {
                p.refreshTask(i);
                return;
            }
        }
        ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "refreshTask id ", Integer.valueOf(i));
    }

    public static void d(Context context, HealthLifeTaskBean healthLifeTaskBean, String str, ResponseCallback<HealthLifeTaskBean> responseCallback) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "showTaskDialog api is null");
        } else {
            p.showTaskDialog(context, healthLifeTaskBean, str, responseCallback);
        }
    }

    public static SparseIntArray ZJ_() {
        SparseIntArray sparseIntArray = c;
        if (sparseIntArray == null || sparseIntArray.size() <= 0) {
            c = ZI_(f11820a);
        }
        return c;
    }

    public static SparseIntArray ZI_(int[] iArr) {
        if (koq.e(iArr)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskArray array ", iArr);
            return new SparseIntArray();
        }
        ArrayList<Integer> j = j();
        int size = j.size();
        int length = iArr.length;
        SparseIntArray sparseIntArray = new SparseIntArray();
        Iterator<Integer> it = j.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            int min = Math.min(Math.max(intValue - 2, 0), size - 2);
            if (min < 0 || min >= length) {
                ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskArray index ", Integer.valueOf(min));
            } else {
                sparseIntArray.append(intValue, iArr[min]);
            }
        }
        return sparseIntArray;
    }

    public static ArrayList<Integer> j() {
        return d(R.array._2130968709_res_0x7f040085);
    }

    public static ArrayList<Integer> d(int i) {
        String[] stringArray;
        int i2;
        ArrayList<Integer> arrayList = new ArrayList<>(16);
        try {
            stringArray = BaseApplication.e().getResources().getStringArray(i);
        } catch (Resources.NotFoundException e) {
            ReleaseLogUtil.c("R_HealthLife_HealthLifeUtil", "getCapabilitySet exception ", LogAnonymous.b((Throwable) e));
        }
        if (koq.b(stringArray, 0)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getCapabilitySet capabilitySetArray is Array Index Out Of Bound");
            return arrayList;
        }
        for (String str : stringArray) {
            arrayList.add(Integer.valueOf(CommonUtil.h(str)));
        }
        return arrayList;
    }

    public static boolean b(HealthLifeBean healthLifeBean) {
        eyw eywVar;
        String extendInfo = healthLifeBean.getExtendInfo();
        return (TextUtils.isEmpty(extendInfo) || (eywVar = (eyw) HiJsonUtil.e(extendInfo, eyw.class)) == null || eywVar.a() == 0) ? false : true;
    }

    public static int b(int i) {
        Context e = BaseApplication.e();
        if (i == 1) {
            return ContextCompat.getColor(e, R$color.clover_relax_pressure_mood);
        }
        if (i == 2) {
            return ContextCompat.getColor(e, R$color.clover_mood_pressure_normal);
        }
        if (i == 3) {
            return ContextCompat.getColor(e, R$color.clover_mood_pressure_medium);
        }
        if (i == 4) {
            return ContextCompat.getColor(e, R$color.clover_mood_pressure_high);
        }
        return ContextCompat.getColor(e, R$color.clover_relax_pressure_mood);
    }

    public static String d(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str) || String.valueOf(0).equals(str)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTimeText result is ", str);
            return "";
        }
        String[] split = str.split(" ");
        int length = split.length;
        if (length > 1) {
            str3 = split[0];
            str2 = split[1];
        } else if (length == 1) {
            str2 = split[0];
            str3 = "";
        } else {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTimeText other");
            str2 = "";
            str3 = str2;
        }
        if ((TextUtils.isEmpty(str3) || CommonUtils.h(str3) > 0) && !TextUtils.isEmpty(str2)) {
            String[] split2 = str2.split(":");
            if (split2.length >= 1) {
                String str4 = split2[0];
                int h = CommonUtils.h(str4);
                if (h >= 0 && h < 24) {
                    return str2;
                }
                ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTimeText hour ", Integer.valueOf(h), " hourText ", str4, " result ", str);
            }
        }
        return "";
    }

    public static void e(List<String> list, final ResponseCallback<List<HealthLifeBean>> responseCallback) {
        Context e = BaseApplication.e();
        boolean aa = CommonUtil.aa(e);
        boolean a2 = niv.a(e);
        if (!aa || a2) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "syncUpdateHealthGoal isNetworkConnected ", Boolean.valueOf(aa), " isStepCard ", Boolean.valueOf(a2));
            if (responseCallback != null) {
                responseCallback.onResponse(-1, Collections.emptyList());
                return;
            }
            return;
        }
        final HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "syncUpdateHealthGoal api is null");
            if (responseCallback != null) {
                responseCallback.onResponse(-1, Collections.emptyList());
                return;
            }
            return;
        }
        nip.a(list, new IBaseResponseCallback() { // from class: dsq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dsl.c(HealthLifeApi.this, responseCallback, i, obj);
            }
        });
    }

    static /* synthetic */ void c(HealthLifeApi healthLifeApi, ResponseCallback responseCallback, int i, Object obj) {
        if (!(obj instanceof Map)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "syncUpdateHealthGoal onResponse object instanceof Map false");
            return;
        }
        HashMap hashMap = (HashMap) obj;
        int e = nip.e(hashMap, "900200006", 0);
        int e2 = nip.e(hashMap, "900200008", 0);
        LogUtil.a("HealthLife_HealthLifeUtil", "syncUpdateHealthGoal onResponse stepGoal ", Integer.valueOf(e), " intensityGoal ", Integer.valueOf(e2));
        ArrayList arrayList = new ArrayList(2);
        if (e > 0) {
            HealthLifeBean healthLifeBean = new HealthLifeBean();
            healthLifeBean.setId(2);
            healthLifeBean.setTarget(String.valueOf(e));
            arrayList.add(healthLifeBean);
        }
        if (e2 > 0) {
            HealthLifeBean healthLifeBean2 = new HealthLifeBean();
            healthLifeBean2.setId(3);
            healthLifeBean2.setTarget(String.valueOf(e2));
            arrayList.add(healthLifeBean2);
        }
        if (koq.c(arrayList)) {
            healthLifeApi.syncUpdateHealthGoal(arrayList, responseCallback, "UPDATE_GOAL");
        }
    }

    public static int a(int i, int i2) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getCloverComplete api is null cloverId ", Integer.valueOf(i), " date ", Integer.valueOf(i2));
            return 0;
        }
        return p.getCloverComplete(i, i2);
    }

    public static List<HealthLifeBean> a(long j, long j2) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getCloverComplete api is null.");
            return new ArrayList();
        }
        return p.queryHealthLifeBeanList(j, j2);
    }

    public static Object a(Context context, Object obj) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getCardReader api is null context ", context, " cardConfig ", obj);
            return null;
        }
        return p.getCardReader(context, obj);
    }

    public static String e(int i) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getSubscriptionTaskData api is null date ", Integer.valueOf(i));
            return "";
        }
        return p.getSubscriptionData(i);
    }

    public static void e(int i, ResponseCallback<dsb> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "queryWeekHealthReport callback is null");
            return;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "queryWeekHealthReport api is null startDay ", Integer.valueOf(i), " callback ", responseCallback);
        } else {
            p.queryWeekHealthReport(i, responseCallback);
        }
    }

    public static void j(List<cbk> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "updateBloodPressureTarget list ", list);
            return;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "updateBloodPressureTarget api is null list ", list);
        } else {
            p.updateBloodPressureTarget(list);
        }
    }

    public static List<HealthLifeTaskBean> c(List<Integer> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskDayRecordList idList ", list);
            return Collections.emptyList();
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskDayRecordList api is null idList ", list);
            return Collections.emptyList();
        }
        return p.getTaskDayRecordList(list);
    }

    public static float d(List<HealthLifeBean> list, int i) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getCloverScale list ", list, " type ", Integer.valueOf(i));
            return 0.0f;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getCloverScale api is null list ", list);
            return 0.0f;
        }
        return p.getCloverScale(list, i);
    }

    public static boolean d(List<HealthLifeBean> list, float f, float f2, float f3) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "isPerfectClover list ", list, " top ", Float.valueOf(f), " left ", Float.valueOf(f2), " right ", Float.valueOf(f3));
            return false;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "isPerfectClover api is null list ", list, " top ", Float.valueOf(f), " left ", Float.valueOf(f2), " right ", Float.valueOf(f3));
            return false;
        }
        return p.isPerfectClover(list, f, f2, f3);
    }

    public static int b(List<HealthLifeBean> list, int i) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getActiveTaskId list ", list, " type ", Integer.valueOf(i));
            return 0;
        }
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getActiveTaskId api is null list ", list);
            return 0;
        }
        return p.getActiveTaskId(list, i);
    }

    public static List<ImageBean> e(String str) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskDialogImageList api is null");
            return Collections.emptyList();
        }
        return p.getTaskDialogImageList(str);
    }

    public static void a(int i, Context context) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "setAboutEvent api is null");
        } else {
            p.setAboutEvent(i, context);
        }
    }

    public static void ZN_(final int i, final int i2, final Bitmap bitmap) {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "topActivity is null");
        } else {
            PermissionUtil.b(wa_, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(wa_) { // from class: dsl.1
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    HealthLifeApi p = dsl.p();
                    if (p == null) {
                        ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "shareSleepTask api is null");
                    } else {
                        p.shareSleepTask(i, i2, bitmap);
                    }
                }
            });
        }
    }

    public static void t() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "sendDeviceTaskData api is null");
        } else {
            p.sendDeviceTaskData();
        }
    }

    public static int h() {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getPlayType api is null");
            return 0;
        }
        return p.getPlayType();
    }

    public static Pair<Integer, String> b(List<HealthLifeBean> list) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getRemindTipMap api is null");
            return new Pair<>(-1, "");
        }
        return p.getRemindTipMap(list);
    }

    public static void c(int i, ResponseCallback<dsa> responseCallback) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getHealthLifeConDays api is null date ", Integer.valueOf(i), " callback ", responseCallback);
            responseCallback.onResponse(-1, new dsa());
        } else {
            p.getHealthLifeConDays(i, responseCallback);
        }
    }

    public static void a(dsa dsaVar) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "setTaskConsInfoCache api is null");
        } else {
            p.setHealthLifeTaskConsInfoCache(dsaVar);
        }
    }

    public static void a(List<HealthLifeTaskBean> list, dsa dsaVar) {
        if (dsaVar == null || koq.b(dsaVar.e()) || koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "HealthTaskConsInfo is null");
            return;
        }
        boolean z = false;
        for (HealthLifeStatistic healthLifeStatistic : dsaVar.e()) {
            Iterator<HealthLifeTaskBean> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    HealthLifeTaskBean next = it.next();
                    if (healthLifeStatistic.getId() == next.getId() && !healthLifeStatistic.hasToday() && next.getComplete() > 0) {
                        z = true;
                        healthLifeStatistic.setHasToday(true);
                        healthLifeStatistic.addConDays();
                        LogUtil.a("HealthLife_HealthLifeUtil", "update task id ", Integer.valueOf(next.getId()), " consDays is ", Integer.valueOf(healthLifeStatistic.getConDays()));
                        break;
                    }
                }
            }
        }
        if (z) {
            a(dsaVar);
        }
    }

    public static void c(List<HealthLifeBean> list, dsa dsaVar) {
        if (dsaVar == null || koq.b(dsaVar.e()) || koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "HealthTaskConsInfo is null");
            return;
        }
        boolean z = false;
        for (HealthLifeStatistic healthLifeStatistic : dsaVar.e()) {
            Iterator<HealthLifeBean> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    HealthLifeBean next = it.next();
                    if (healthLifeStatistic.getId() == next.getId() && next.getComplete() > 0 && !healthLifeStatistic.hasToday()) {
                        healthLifeStatistic.addConDays();
                        z = true;
                        healthLifeStatistic.setHasToday(true);
                        LogUtil.a("HealthLife_HealthLifeUtil", "update task id ", Integer.valueOf(next.getId()), " consDays is ", Integer.valueOf(healthLifeStatistic.getConDays()));
                        break;
                    }
                }
            }
        }
        if (z) {
            a(dsaVar);
        }
    }

    public static List<Integer> c(int i) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskConsInfoCache api is null");
            return new ArrayList();
        }
        return p.getSmallShownIdsCache(i);
    }

    public static void a(List<Integer> list) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskConsInfoCache api is null");
        } else {
            p.setSmallShownIdsCache(list);
        }
    }

    public static List<Integer> d(List<HealthLifeTaskBean> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<HealthLifeTaskBean> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().getId()));
        }
        return arrayList;
    }

    public static void e(String str, boolean z) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskConsInfoCache api is null");
        } else {
            p.setHealthLifeSwitch(str, z);
        }
    }

    public static boolean c(String str, boolean z) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskConsInfoCache api is null");
            return true;
        }
        return p.getHealthLifeSwitch(str, z);
    }

    public static String e(String str, Object obj) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str, obj);
        } catch (JSONException unused) {
            LogUtil.a("HealthLife_HealthLifeUtil", "JSONException");
        }
        LogUtil.a("HealthLife_HealthLifeUtil", "configdata is ", jSONObject.toString());
        return jSONObject.toString();
    }

    public static String c(String str, String str2) {
        JsonObject jsonObject = (JsonObject) HiJsonUtil.e(str, JsonObject.class);
        if (jsonObject == null || jsonObject.isJsonNull() || !jsonObject.isJsonObject() || jsonObject.get(str2) == null) {
            LogUtil.h("HealthLife_HealthLifeUtil", "configData fromJson is null");
            return null;
        }
        return jsonObject.get(str2).getAsString();
    }

    public static Calendar c(String str) {
        HealthLifeApi p = p();
        if (p == null) {
            ReleaseLogUtil.a("R_HealthLife_HealthLifeUtil", "getTaskConsInfoCache api is null");
            return null;
        }
        return p.getHourAndMinute(str);
    }
}
