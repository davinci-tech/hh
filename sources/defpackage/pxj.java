package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.up.model.UserInfomation;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class pxj {

    /* renamed from: a, reason: collision with root package name */
    private static Context f16327a;
    private static pxj d;
    private static final Object e = new Object();
    private ExecutorService j = Executors.newSingleThreadExecutor();
    private HeartZoneConf c = new HeartZoneConf();
    private IntentFilter g = new IntentFilter();
    private List<Integer> b = new ArrayList(16);
    private CountDownLatch f = new CountDownLatch(1);
    private boolean i = false;
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: pxj.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            LogUtil.a("Common_HWOldHealthDataManager", "receive broadcastReceiver");
            if (intent == null || (action = intent.getAction()) == null || !action.equals("com.huawei.health.heart_zone_conf_migrate_finish")) {
                return;
            }
            LogUtil.a("Common_HWOldHealthDataManager", "receive com.huawei.health.heart_zone_conf_migrate_finish");
            pxj.this.a();
        }
    };
    private BroadcastReceiver m = new BroadcastReceiver() { // from class: pxj.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("Common_HWOldHealthDataManager", "receive broadcastReceiver");
            if (intent == null || !"com.huawei.bone.action.FITNESS_USERINFO_UPDATED".equals(intent.getAction())) {
                return;
            }
            LogUtil.a("Common_HWOldHealthDataManager", "userinfo save finished then update heartzonethreshold");
            pxj.this.d();
        }
    };
    private HiSubscribeListener k = new HiSubscribeListener() { // from class: pxj.1
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            LogUtil.a("Common_HWOldHealthDataManager", "subscribeHiHealthData onResult");
            if (list == null || list.isEmpty()) {
                return;
            }
            LogUtil.a("Common_HWOldHealthDataManager", "subscribeHiHealthData success");
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("Common_HWOldHealthDataManager", "subscribeHiHealthData onChange type = ", Integer.valueOf(i), ",changeType = ", str);
            if (i == 102 && "HiSyncUserData".equals(str)) {
                LogUtil.a("Common_HWOldHealthDataManager", "subscribeHiHealthData receive userinfo update Msg");
                pxj.this.d();
                pxj.this.e();
            }
        }
    };

    private pxj() {
        f16327a = BaseApplication.getContext();
        d();
        LocalBroadcastManager.getInstance(f16327a).registerReceiver(this.m, new IntentFilter("com.huawei.bone.action.FITNESS_USERINFO_UPDATED"));
        this.g.addAction("com.huawei.health.heart_zone_conf_migrate_finish");
        BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.h, this.g, "com.huawei.health.heartZonePermission", null);
        this.b.clear();
        this.b.add(102);
        HiHealthManager.d(f16327a).subscribeHiHealthData(this.b, this.k);
    }

    public static pxj b() {
        pxj pxjVar;
        synchronized (e) {
            if (d == null) {
                d = new pxj();
            }
            pxjVar = d;
        }
        return pxjVar;
    }

    public void a(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HWOldHealthDataManager", "HWOldHealthDataManager getCoreSleepDetail()");
        pxl.e().b(j, i, i2, i3, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.j.execute(new Runnable() { // from class: pxj.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Common_HWOldHealthDataManager", "updateHeartRateThroshold enter");
                UserInfomation c = pxj.this.c();
                pxj.this.i = c.getAgeOrDefaultValue() != 0;
                if (pxj.this.i) {
                    pxj.this.e(c);
                    return;
                }
                LogUtil.h("Common_HWOldHealthDataManager", "get user info fail.");
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put("actiontype", Integer.toString(1));
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEART_RATE_ZONE_80040005.value(), linkedHashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(UserInfomation userInfomation) {
        HeartZoneConf heartZoneConf = new HeartZoneConf(userInfomation.getAgeOrDefaultValue());
        heartZoneConf.setRestHeartRateDefault(this.c.getRestHeartRateDefault());
        heartZoneConf.resetHeartZoneConf(userInfomation.getAgeOrDefaultValue());
        Context context = BaseApplication.getContext();
        HiUserPreference userPreference = HiHealthManager.d(context).getUserPreference("custom.UserPreference_HeartRate_Classify_Method");
        int h = userPreference != null ? CommonUtil.h(userPreference.getValue()) : 0;
        heartZoneConf.setClassifyMethod(h);
        int restHeartRateDefault = this.c.getRestHeartRateDefault();
        HiUserPreference userPreference2 = HiHealthManager.d(context).getUserPreference("custom.UserPreference_Rest_HeartRate");
        if (userPreference2 != null) {
            restHeartRateDefault = CommonUtil.e(userPreference2.getValue(), 60);
        }
        heartZoneConf.setRestHeartRate(restHeartRateDefault);
        HiUserPreference userPreference3 = HiHealthManager.d(context).getUserPreference("custom.UserPreference_HeartRate_Limit_Flag");
        HiUserPreference userPreference4 = HiHealthManager.d(context).getUserPreference("custom.UserPreference_HeartRate_Flag");
        d(heartZoneConf, userPreference3, userPreference4);
        c(heartZoneConf, userPreference3, userPreference4);
        LogUtil.c("Common_HWOldHealthDataManager", "getHeartZoneConf ", heartZoneConf);
        this.c.setClassifyMethod(h);
        this.c.setRestHeartRate(restHeartRateDefault);
        this.c.setHeartZoneConf(heartZoneConf);
        this.c.setHRRHeartZoneConf(heartZoneConf);
        kox.e().b(heartZoneConf, userInfomation);
        this.f.countDown();
    }

    private void d(HeartZoneConf heartZoneConf, HiUserPreference hiUserPreference, HiUserPreference hiUserPreference2) {
        if (heartZoneConf == null) {
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_HeartZone_Config");
        int warningLimitHR = heartZoneConf.getWarningLimitHR();
        int maxThreshold = heartZoneConf.getMaxThreshold();
        if (userPreference == null || userPreference.getValue() == null) {
            LogUtil.h("Common_HWOldHealthDataManager", "updateMaxHeartRate userPreferenceHeartValue error or getValue error");
            return;
        }
        String[] split = userPreference.getValue().split(",");
        if (split.length != 2) {
            return;
        }
        heartZoneConf.setHRZoneConf(split[0]);
        heartZoneConf.setThreshold(split[1]);
        if (hiUserPreference != null) {
            int h = CommonUtil.h(hiUserPreference.getValue());
            if (h == 0) {
                heartZoneConf.setWarningLimitHR(warningLimitHR);
            }
            kox.e().a(h != 0);
        }
        if (hiUserPreference2 != null) {
            int h2 = CommonUtil.h(hiUserPreference2.getValue());
            if (h2 == 0) {
                heartZoneConf.setMaxThreshold(maxThreshold);
            }
            kox.e().e(h2 != 0);
        }
    }

    private void c(HeartZoneConf heartZoneConf, HiUserPreference hiUserPreference, HiUserPreference hiUserPreference2) {
        if (heartZoneConf == null) {
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_HRRHeartZone_Config");
        int warningLimitHRHRR = heartZoneConf.getWarningLimitHRHRR();
        int hrrMaxThreshold = heartZoneConf.getHrrMaxThreshold();
        if (userPreference == null || userPreference.getValue() == null) {
            LogUtil.h("Common_HWOldHealthDataManager", "updateReserveHeartRate userPreferenceValue2 error or getValue error");
            return;
        }
        String[] split = userPreference.getValue().split(",");
        if (split.length != 2) {
            return;
        }
        heartZoneConf.setHRRHRZoneConf(split[0]);
        heartZoneConf.setHrrThreshold(split[1]);
        if (hiUserPreference != null && CommonUtil.h(hiUserPreference.getValue()) == 0) {
            heartZoneConf.setWarningLimitHRHRR(warningLimitHRHRR);
        }
        if (hiUserPreference2 == null || CommonUtil.h(hiUserPreference2.getValue()) != 0) {
            return;
        }
        heartZoneConf.setHrrMaxThreshold(hrrMaxThreshold);
    }

    public void a(HeartZoneConf heartZoneConf) {
        LogUtil.a("Common_HWOldHealthDataManager", "setHeartRateThrosholdConf enter");
        this.c.setHeartZoneConf(heartZoneConf);
    }

    public void d(HeartZoneConf heartZoneConf) {
        LogUtil.a("Common_HWOldHealthDataManager", "setHRRHeartRateThrosholdConf enter");
        this.c.setHRRHeartZoneConf(heartZoneConf);
    }

    public void a(int i) {
        this.c.setClassifyMethod(i);
    }

    public void e(int i) {
        this.c.setRestHeartRate(i);
    }

    public void e() {
        this.j.execute(new Runnable() { // from class: pxj.7
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.metric_imperial_unit");
                HiUserPreference userPreference2 = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.temperature_unit");
                pxj.this.e(userPreference);
                pxj.this.c(userPreference2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0015, code lost:
    
        if (defpackage.nsn.e(r2.getValue()) == 1) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(com.huawei.hihealth.HiUserPreference r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L18
            java.lang.String r0 = r2.getValue()
            boolean r0 = r1.d(r0)
            if (r0 == 0) goto L18
            java.lang.String r2 = r2.getValue()
            int r2 = defpackage.nsn.e(r2)
            r0 = 1
            if (r2 != r0) goto L18
            goto L19
        L18:
            r0 = 0
        L19:
            health.compact.a.UnitUtil.c(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pxj.e(com.huawei.hihealth.HiUserPreference):void");
    }

    private boolean d(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HiUserPreference hiUserPreference) {
        UnitUtil.a(!"false".equals(hiUserPreference != null ? hiUserPreference.getValue() : ""));
    }

    public UserInfomation c() {
        UserInfomation userInfoFromDbSync = LoginInit.getInstance(f16327a).getUserInfoFromDbSync();
        if (userInfoFromDbSync != null) {
            return userInfoFromDbSync;
        }
        UserInfomation userInfomation = new UserInfomation();
        LogUtil.a("Common_HWOldHealthDataManager", "getLocalUserinfo new userInfomation");
        return userInfomation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.j.execute(new Runnable() { // from class: pxj.6
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Common_HWOldHealthDataManager", "MigrateHeartZoneConf enter");
                HeartZoneConf heartZoneConf = new HeartZoneConf(pxj.this.c().getAgeOrDefaultValue());
                pxj.this.e("custom.UserPreference_HeartRate_Classify_Method");
                pxj.this.e("custom.UserPreference_Rest_HeartRate");
                pxj.this.c(heartZoneConf, "custom.UserPreference_HeartZone_Config");
                pxj.this.c(heartZoneConf, "custom.UserPreference_HRRHeartZone_Config");
                LogUtil.a("Common_HWOldHealthDataManager", "MigrateHeartZoneConf exit");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
        if (userPreference == null) {
            return;
        }
        int h = CommonUtil.h(userPreference.getValue());
        if (str.equals("custom.UserPreference_HeartRate_Classify_Method")) {
            a(h);
        } else if (str.equals("custom.UserPreference_Rest_HeartRate")) {
            e(h);
        } else {
            LogUtil.h("Common_HWOldHealthDataManager", "setHeartRateConf1 flag is wrong");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HeartZoneConf heartZoneConf, String str) {
        LogUtil.c("Common_HWOldHealthDataManager", "getHeartZoneConf ", heartZoneConf);
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
        if (userPreference == null || userPreference.getValue() == null) {
            return;
        }
        String[] split = userPreference.getValue().split(",");
        if (split.length != 2) {
            return;
        }
        str.hashCode();
        if (str.equals("custom.UserPreference_HeartZone_Config")) {
            this.c.setThreshold(split[0]);
            a(this.c);
        } else if (str.equals("custom.UserPreference_HRRHeartZone_Config")) {
            this.c.setHrrThreshold(split[0]);
            d(this.c);
        } else {
            LogUtil.h("Common_HWOldHealthDataManager", "setHeartRateConf2 flag is wrong");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{46018, 46020});
        hiAggregateOption.setConstantsKey(new String[]{"HR_REST", "HR_SLEEP_REST"});
        hiAggregateOption.setStartTime(jdl.d(currentTimeMillis, -30));
        hiAggregateOption.setEndTime(currentTimeMillis);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setGroupUnitType(3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: pxj.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                if (sparseArray != null && sparseArray.size() != 0) {
                    pxj pxjVar = pxj.this;
                    float c = pxjVar.c((List<HiHealthData>) pxjVar.duR_(sparseArray)) + 0.0f;
                    if (c != 0.0f) {
                        pxj.this.c.setRestHeartRateDefault(Math.round(c));
                    } else {
                        LogUtil.h("Common_HWOldHealthDataManager", "there is average rest heart rate is zero");
                        pxj.this.c.setRestHeartRateDefault(60);
                    }
                    kox.e().e(c != 0.0f ? Math.round(c) : 60);
                } else {
                    LogUtil.h("Common_HWOldHealthDataManager", "there is no average rest heart rate!", " and ", " errorCode :", Integer.valueOf(i));
                    pxj.this.c.setRestHeartRateDefault(60);
                    kox.e().e(60);
                }
                pxj.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> duR_(SparseArray<List<HiHealthData>> sparseArray) {
        ArrayList arrayList = new ArrayList();
        if (!koq.b(sparseArray.get(1))) {
            List<HiHealthData> list = sparseArray.get(1);
            LogUtil.a("Common_HWOldHealthDataManager", "average rest heart rate newValue = ", list.toString());
            arrayList.addAll(list);
        }
        if (!koq.b(sparseArray.get(0))) {
            List<HiHealthData> list2 = sparseArray.get(0);
            LogUtil.a("Common_HWOldHealthDataManager", "average rest heart rate oldValue = ", list2.toString());
            arrayList.addAll(list2);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float c(List<HiHealthData> list) {
        float f;
        float f2;
        int i = 0;
        if (koq.b(list)) {
            f = 0.0f;
        } else {
            f = 0.0f;
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getFloat("HR_SLEEP_REST") > 0.0f) {
                    f2 = hiHealthData.getFloat("HR_SLEEP_REST");
                } else {
                    f2 = hiHealthData.getFloat("HR_REST");
                }
                f += f2;
                i++;
            }
        }
        if (i != 0) {
            return f / i;
        }
        LogUtil.h("Common_HWOldHealthDataManager", "count IS  ZERO");
        return 0.0f;
    }
}
