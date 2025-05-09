package defpackage;

import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.datatype.NotificationData;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.common.base.BaseNotification;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.awarenessdonate.AwarenessDonateApi;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import defpackage.bzn;
import defpackage.plr;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ppy {
    public static void a(final long j, final long j2, final int i) {
        if (jez.e() != null) {
            d(j, j2, i);
        } else {
            ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "pushSleepNotifyMessage binder is null");
            jez.a(BaseApplication.getContext());
            new Timer("CoreSleepEventHandler").schedule(new TimerTask() { // from class: ppy.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "pushSleepNotifyMessage start");
                    ppy.d(j, j2, i);
                }
            }, 2000L);
        }
        ThreadPoolManager.d().d("CoreSleepEventHandler", new Runnable() { // from class: pqc
            @Override // java.lang.Runnable
            public final void run() {
                ppy.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a() {
        bzn.e eVar = new bzn.e(System.currentTimeMillis() + 900000, System.currentTimeMillis() + 4500000);
        ArrayList arrayList = new ArrayList();
        arrayList.add(eVar);
        bzn d = new bzn.b().f("featurePrediction").g(UUID.randomUUID().toString()).a(Build.MODEL).c("phone").b(Build.BRAND + " " + Build.MODEL).a(0).l(TimeZone.getDefault().getDisplayName(false, 0)).a(System.currentTimeMillis()).b(((bzn.e) arrayList.get(0)).c()).c(((bzn.e) arrayList.get(arrayList.size() - 1)).e()).e("com.huawei.ohos.healthservice").o(FaCardAgdsApi.SLEEP_FA_CARD).d("com.huawei.ohos.healthservice.sleep.SleepDataFormAbility").h("sleep_data_mini").i("1*2").j("haveGoodSleep").b(arrayList).d();
        AwarenessDonateApi awarenessDonateApi = (AwarenessDonateApi) Services.a("HWSmartInteractMgr", AwarenessDonateApi.class);
        if (awarenessDonateApi == null || !awarenessDonateApi.isSupportDonate()) {
            return;
        }
        ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "invoking awarenessDonateApi donateFeature Interface");
        awarenessDonateApi.donateFeature(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(long j, long j2, int i) {
        if (VersionControlUtil.isSupportSleepManagement()) {
            e(j, j2);
        } else if (new SecureRandom().nextInt(10) < 7 || i == 0) {
            a(j, j2);
        } else {
            b(j, j2, i);
        }
    }

    private static void b(long j, long j2, int i) {
        LogUtil.a("CoreSleepEventHandler", "pushSleepScoreMsg fallAsleepTime ", Long.valueOf(j), "wakeTime ", Long.valueOf(j2), " score ", Integer.valueOf(i));
        SecureRandom secureRandom = new SecureRandom();
        Resources resources = BaseApplication.getContext().getResources();
        c(j, j2, resources.getString(R$string.IDS_hw_show_notifications_to_sleep_details_title), String.format(Locale.ENGLISH, new String[]{resources.getString(R$string.IDS_notification_sleep_msg_one), resources.getString(R$string.IDS_notification_sleep_msg_two), resources.getString(R$string.IDS_notification_sleep_msg_three)}[secureRandom.nextInt(3)], Integer.valueOf(i)));
    }

    private static void a(final long j, final long j2) {
        final pxd pxdVar = new pxd();
        final Date date = new Date();
        date.setTime(j2);
        String c = scn.c(j);
        String c2 = scn.c(j2);
        LogUtil.a("CoreSleepEventHandler", "pushSleepAdviceMsg fallAsleepTime ", c, " wakeTime ", c2);
        pxdVar.a(c);
        pxdVar.d(c2);
        pxdVar.b(jec.n(date), new CommonUiBaseResponse() { // from class: ppz
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i, Object obj) {
                ppy.e(pxd.this, date, j, j2, i, obj);
            }
        });
    }

    static /* synthetic */ void e(final pxd pxdVar, Date date, final long j, final long j2, int i, Object obj) {
        LogUtil.a("CoreSleepEventHandler", "pushSleepAdviceMsg errorCode ", Integer.valueOf(i));
        if (i != 0) {
            return;
        }
        pxdVar.e(date, 7, new CommonUiBaseResponse() { // from class: pqh
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i2, Object obj2) {
                ppy.e(pxd.this, j, j2, i2, obj2);
            }
        });
    }

    static /* synthetic */ void e(pxd pxdVar, long j, long j2, int i, Object obj) {
        LogUtil.a("CoreSleepEventHandler", "pushSleepAdviceMsg error ", Integer.valueOf(i));
        if (i == 0) {
            c(j, j2, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_notifications_to_sleep_details_title), pxdVar.g() + c());
        }
    }

    private static void e(final long j, final long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.DAILY_SLEEP_PROBLEM.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_INTERPRET.value()});
        hiDataReadOption.setStartTime(ggl.g(System.currentTimeMillis()));
        hiDataReadOption.setEndTime(ggl.e(System.currentTimeMillis()));
        hiDataReadOption.setSortOrder(1);
        ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "readHiHealthDataPro start");
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: ppy.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                JSONObject jSONObject;
                ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "readHiHealthDataPro errorCode is ", Integer.valueOf(i));
                if (i != 0) {
                    return;
                }
                SparseArray sparseArray = new SparseArray();
                if (obj instanceof SparseArray) {
                    sparseArray = (SparseArray) obj;
                }
                List list = (List) sparseArray.get(DicDataTypeUtil.DataType.DAILY_SLEEP_INTERPRET.value());
                List list2 = (List) sparseArray.get(DicDataTypeUtil.DataType.DAILY_SLEEP_PROBLEM.value());
                if (koq.b(list)) {
                    ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "dailySleepInterpretList is null or empty");
                    return;
                }
                int i3 = 0;
                String metaData = ((HiHealthData) list.get(0)).getMetaData();
                LogUtil.a("CoreSleepEventHandler", "interpret is ", metaData);
                try {
                    jSONObject = new JSONObject(metaData);
                } catch (JSONException unused) {
                    ReleaseLogUtil.c("BTSYNC_CoreSleepEventHandler", "interpretJson error");
                    jSONObject = null;
                }
                JSONObject jSONObject2 = jSONObject;
                if (koq.c(list2)) {
                    i3 = ((HiHealthData) list2.get(0)).getIntValue();
                } else {
                    ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "problemList is null or empty");
                }
                ppy.e(j, j2, jSONObject2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final long j, final long j2, final JSONObject jSONObject, final int i) {
        ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "getDescriptionInMainThread enter");
        final int g = g(jSONObject);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: ppy.1
            @Override // java.lang.Runnable
            public void run() {
                String b;
                ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "getDescriptionInMainThread loadResources before");
                AppBundle.b().loadResources(BaseApplication.getContext());
                ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "getDescriptionInMainThread loadResources after");
                if (ppy.j(jSONObject)) {
                    if (new SecureRandom().nextInt(10) <= 7) {
                        b = nsf.b(R$string.IDS_three_parts, ppy.d(g), ppy.e(jSONObject), ppy.a(i));
                    } else {
                        b = nsf.b(R$string.IDS_three_parts, ppy.d(g), ppy.c(jSONObject), ppy.a(i));
                    }
                } else {
                    b = nsf.b(R$string.IDS_three_parts, ppy.d(g), ppy.c(jSONObject), ppy.a(i));
                }
                String replace = b.replace("<b>", "").replace("</b>", "");
                LogUtil.a("CoreSleepEventHandler", "description is ", replace);
                ppy.c(j, j2, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_notifications_to_sleep_details_title), replace);
            }
        });
    }

    private static int g(JSONObject jSONObject) {
        String str;
        if (jSONObject == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "getDailyQualityDesc interpretJson null");
            return -1;
        }
        try {
            str = jSONObject.getString("dailyQualityDesc");
        } catch (JSONException unused) {
            ReleaseLogUtil.c("BTSYNC_CoreSleepEventHandler", "getDailyQualityDescId json error");
            str = null;
        }
        int a2 = pwt.a(str);
        LogUtil.a("CoreSleepEventHandler", "dailyQualityDescIndex is ", str);
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(int i) {
        if (i == -1) {
            ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "getDailyQualityDesc resId is ", Integer.valueOf(i));
            return "";
        }
        String string = BaseApplication.getContext().getString(i);
        LogUtil.a("CoreSleepEventHandler", "getDailyQualityDesc is ", string);
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(JSONObject jSONObject) {
        String str;
        if (jSONObject == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "interpretJson null");
            return "";
        }
        String str2 = null;
        try {
            str = jSONObject.getString("dailyProblemDescInput");
        } catch (JSONException unused) {
            str = null;
        }
        try {
            str2 = jSONObject.getString("dailyProblemDesc");
        } catch (JSONException unused2) {
            ReleaseLogUtil.c("BTSYNC_CoreSleepEventHandler", "getDailyProblemDesc json error");
            String a2 = pwt.a((List<fcm>) moj.b(str, fcm[].class), str2);
            LogUtil.a("CoreSleepEventHandler", "dailyProblemDescInputStr is ", str, ", DailyProblemDesc is ", a2);
            return a2;
        }
        String a22 = pwt.a((List<fcm>) moj.b(str, fcm[].class), str2);
        LogUtil.a("CoreSleepEventHandler", "dailyProblemDescInputStr is ", str, ", DailyProblemDesc is ", a22);
        return a22;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(JSONObject jSONObject) {
        String str;
        if (jSONObject == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "getDailyFactorDesc interpretJson null");
            return "";
        }
        String str2 = null;
        try {
            str = jSONObject.getString("dailyFactorDescInput");
        } catch (JSONException unused) {
            str = null;
        }
        try {
            str2 = jSONObject.getString("dailyFactorDesc");
        } catch (JSONException unused2) {
            ReleaseLogUtil.c("BTSYNC_CoreSleepEventHandler", "getDailyFactorDesc json error");
            return pwt.b((List<fco>) moj.b(str, fco[].class), str2);
        }
        return pwt.b((List<fco>) moj.b(str, fco[].class), str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(int i) {
        LogUtil.a("CoreSleepEventHandler", "dailyProblem is ", Integer.valueOf(i));
        return i != 0 ? BaseApplication.getContext().getResources().getString(R$string.IDS_improvement_detail_in_app) : BaseApplication.getContext().getResources().getString(R$string.IDS_check_detail_in_app);
    }

    private static String c() {
        int nextInt = new SecureRandom().nextInt(3);
        Resources resources = BaseApplication.getContext().getResources();
        return new String[]{resources.getString(R$string.IDS_msg_sleep_advice_one), resources.getString(R$string.IDS_msg_sleep_advice_two), resources.getString(R$string.IDS_msg_sleep_advice_three)}[nextInt];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(long j, long j2, String str, String str2) {
        ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "start sendSleepNotification");
        HashMap hashMap = new HashMap();
        hashMap.put("startTime", String.valueOf(j));
        hashMap.put("endTime", String.valueOf(j2));
        final NotificationData b = jrn.b("sleepDetail", hashMap, str, str2);
        jqi.a().getSwitchSetting("core_sleep_switch_status", new IBaseResponseCallback() { // from class: pqa
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ppy.a(NotificationData.this, i, obj);
            }
        });
    }

    static /* synthetic */ void a(NotificationData notificationData, int i, Object obj) {
        ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "sendSleepNotification errorCode ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof String)) {
            if (jds.c(String.valueOf(obj), 10) == 1) {
                c(notificationData, "huaweischeme://healthapp/router/sleepDetail?from=3", jdh.c());
                return;
            } else {
                LogUtil.a("CoreSleepEventHandler", "sendSleepNotification sleepSwitch closed");
                return;
            }
        }
        c(notificationData, "huaweischeme://healthapp/router/sleepDetail?from=3", jdh.c());
    }

    private static void c(NotificationData notificationData, String str, BaseNotification baseNotification) {
        ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "start notifyMsg");
        ast astVar = new ast();
        astVar.f(notificationData.getTitle());
        astVar.b(notificationData.getDescription());
        Resources resources = BaseApplication.getContext().getResources();
        String string = resources.getString(R$string.IDS_notification_open_app);
        String string2 = resources.getString(R$string.IDS_notification_ignore);
        astVar.c(string);
        astVar.e(string2);
        astVar.d(str);
        astVar.a("huaweischeme://healthapp/router/sleepDetail?from=4");
        asx.e().a(astVar, new AnonymousClass3(notificationData, baseNotification));
    }

    /* renamed from: ppy$3, reason: invalid class name */
    class AnonymousClass3 implements IBaseResponseCallback {
        final /* synthetic */ BaseNotification b;
        final /* synthetic */ NotificationData d;

        AnonymousClass3(NotificationData notificationData, BaseNotification baseNotification) {
            this.d = notificationData;
            this.b = baseNotification;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("BTSYNC_CoreSleepEventHandler", "notifyMsg errorCode:", Integer.valueOf(i));
            if (i != 100) {
                Handler handler = new Handler(Looper.getMainLooper());
                final NotificationData notificationData = this.d;
                final BaseNotification baseNotification = this.b;
                handler.post(new Runnable() { // from class: pqf
                    @Override // java.lang.Runnable
                    public final void run() {
                        jrn.b(BaseApplication.getContext()).d(NotificationData.this, baseNotification);
                    }
                });
            } else {
                Handler handler2 = new Handler(Looper.getMainLooper());
                final NotificationData notificationData2 = this.d;
                final BaseNotification baseNotification2 = this.b;
                handler2.post(new Runnable() { // from class: pqd
                    @Override // java.lang.Runnable
                    public final void run() {
                        jrn.b(BaseApplication.getContext()).a(NotificationData.this, true, baseNotification2);
                    }
                });
            }
            asx.e().a();
        }
    }

    public static void b(plr.b bVar) {
        if (bVar == null) {
            LogUtil.h("CoreSleepEventHandler", "msHolder is null");
        } else {
            c(jrn.c(System.currentTimeMillis(), System.currentTimeMillis(), bVar.b(), bVar.c(), bVar.e()), bVar.a(), jdh.c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean j(JSONObject jSONObject) {
        if (jSONObject == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleepEventHandler", "hasDailyFactorDesc interpretJson null");
            return false;
        }
        if (!jSONObject.has("dailyFactorDesc")) {
            return false;
        }
        try {
            return !TextUtils.isEmpty(jSONObject.getString("dailyFactorDesc"));
        } catch (JSONException unused) {
            ReleaseLogUtil.c("BTSYNC_CoreSleepEventHandler", "hasDailyFactorDesc json error");
            return false;
        }
    }
}
