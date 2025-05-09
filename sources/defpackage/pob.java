package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sleep.SleepApi;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwbasemgr.SleepMonthlyProcessResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepJobService;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class pob {
    private static String b;

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f16197a = {DicDataTypeUtil.DataType.DAILY_SLEEP_PROBLEM.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_DEGREE.value(), DicDataTypeUtil.DataType.DAILY_TARGET_PROBLEM.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_INTERPRET.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_TASKS.value()};
    public static final int[] d = {DicDataTypeUtil.DataType.MONTHLY_SLEEP.value()};
    public static final int[] c = {DicDataTypeUtil.DataType.MONTHLY_SLEEP_PROBLEM.value()};

    public static void e(final SleepJobService.e eVar, final long j, final long j2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pob.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SleepManagementResultUtil", "TryRequestMonthResult");
                pob.d(new IBaseResponseCallback() { // from class: pob.1.4
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("SleepManagementResultUtil", "TryRequestMonthResult objData: ", obj);
                        if (!(obj instanceof Integer)) {
                            LogUtil.a("SleepManagementResultUtil", "objData invalid");
                            pob.b(SleepJobService.e.this, j, j2);
                        } else if (SleepJobService.e.this != null) {
                            SleepJobService.e.this.d(0, true);
                        }
                    }
                }, j, j2);
            }
        });
    }

    public static void d(final SleepJobService.e eVar, final long j, final long j2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pob.6
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SleepManagementResultUtil", "requestMonthResult, startTime: ", Long.valueOf(j), ", endTime: ", Long.valueOf(j2));
                pob.b(eVar, j, j2);
            }
        });
    }

    public static void d(final IBaseResponseCallback iBaseResponseCallback, long j, long j2) {
        e(new HiDataReadResultListener() { // from class: pob.10
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("SleepManagementResultUtil", "queryMonthResult onResult, data: ", obj);
                if (i != 0) {
                    LogUtil.h("SleepManagementResultUtil", "errorCode: ", Integer.valueOf(i));
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("SleepManagementResultUtil", "map.size() <= 0");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                int drh_ = pob.drh_(sparseArray, DicDataTypeUtil.DataType.MONTHLY_SLEEP_PROBLEM.value());
                LogUtil.a("SleepManagementResultUtil", "queryMonthProcessResult success, sleepProblem: ", Integer.valueOf(drh_));
                if (drh_ == -1) {
                    LogUtil.a("SleepManagementResultUtil", "get sleep problem failed");
                    IBaseResponseCallback.this.d(-1, null);
                } else {
                    IBaseResponseCallback.this.d(0, Integer.valueOf(drh_));
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("SleepManagementResultUtil", "queryMonthProcessResult intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
            }
        }, j, j2);
    }

    public static void b(int i, Object obj, long j, long j2, int i2) {
        LogUtil.a("SleepManagementResultUtil", "handleMonthResult, objData: ", obj, ", startTime: ", Long.valueOf(j), ", curTime: ", Long.valueOf(j2), ", generateType: ", Integer.valueOf(i2));
        if (!(obj instanceof fdh)) {
            LogUtil.b("SleepManagementResultUtil", "objData error");
            return;
        }
        fdh fdhVar = (fdh) obj;
        e(fdhVar);
        e(new HiDataOperateListener() { // from class: pob.8
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i3, Object obj2) {
                LogUtil.a("SleepManagementResultUtil", "handleMonthResult errorCode: ", Integer.valueOf(i3), "object: ", obj2);
                ObserverManagerUtil.c("MonthProblemRefresh", new Object[0]);
                pob.g();
            }
        }, j, j2, fdhVar, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(DicDataTypeUtil.DataType.MONTHLY_SLEEP.value());
        hiSyncOption.setSyncMethod(2);
        HiHealthManager.d(BaseApplication.getContext()).synCloud(hiSyncOption, new HiCommonListener() { // from class: pob.14
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("SleepManagementResultUtil", "syncCloud onSuccess");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("SleepManagementResultUtil", "syncCloud onFailure errorCode: ", Integer.valueOf(i), ", errorMessage: ", obj);
            }
        });
    }

    public static void d(fda fdaVar, long j, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback, boolean z) {
        LogUtil.a("SleepManagementResultUtil", "bean: ", fdaVar.toString());
        if (z) {
            if (sleepDailyProcessResultCallback == null) {
                return;
            }
            LogUtil.a("SleepManagementResultUtil", "handleDailyResult onResponse first.");
            sleepDailyProcessResultCallback.onResponse(0, fdaVar);
        }
        a(new AnonymousClass13(z, sleepDailyProcessResultCallback, fdaVar), j, fdaVar);
    }

    /* renamed from: pob$13, reason: invalid class name */
    class AnonymousClass13 implements HiDataOperateListener {
        final /* synthetic */ SleepDailyProcessResultCallback b;
        final /* synthetic */ boolean c;
        final /* synthetic */ fda e;

        AnonymousClass13(boolean z, SleepDailyProcessResultCallback sleepDailyProcessResultCallback, fda fdaVar) {
            this.c = z;
            this.b = sleepDailyProcessResultCallback;
            this.e = fdaVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a("SleepManagementResultUtil", "handleDailyResult errorCode: ", Integer.valueOf(i), "object: ", obj);
            ThreadPoolManager.d().execute(new Runnable() { // from class: poc
                @Override // java.lang.Runnable
                public final void run() {
                    pob.i();
                }
            });
            if (this.c || this.b == null) {
                return;
            }
            LogUtil.a("SleepManagementResultUtil", "handleDailyResult onResponse second.");
            this.b.onResponse(i, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(DicDataTypeUtil.DataType.SLEEP_RECORD.value());
        hiSyncOption.setSyncMethod(2);
        HiHealthManager.d(BaseApplication.getContext()).synCloud(hiSyncOption, new HiCommonListener() { // from class: pob.11
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("SleepManagementResultUtil", "syncDailyHiHealthData onSuccess");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("SleepManagementResultUtil", "syncDailyHiHealthData onFailure errorCode: ", Integer.valueOf(i), ", errorMessage: ", obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(fda fdaVar, long j) {
        if (!jdl.ac(j)) {
            LogUtil.h("SleepManagementResultUtil", "time is not today, time: ", Long.valueOf(j));
        } else {
            c(fdaVar);
        }
    }

    private static void e(fdh fdhVar) {
        ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).initMonthlySleepLabel(fdhVar);
    }

    public static void c(fda fdaVar) {
        ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).initDailySleepLabel(fdaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(SleepJobService.e eVar, long j, long j2) {
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        if (sleepApi != null) {
            sleepApi.requestMonthlyProcessResult(null, null, j, j2, new a(eVar, j, System.currentTimeMillis()));
            return;
        }
        LogUtil.a("SleepManagementResultUtil", "sleepServiceApi is null");
        if (eVar != null) {
            eVar.d(0, true);
        }
    }

    public static void c(HiDataReadResultListener hiDataReadResultListener, long j) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jdl.t(j), jdl.e(j));
        hiDataReadOption.setType(f16197a);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, hiDataReadResultListener);
    }

    public static void a(HiDataOperateListener hiDataOperateListener, long j, fda fdaVar) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(DicDataTypeUtil.DataType.SLEEP_RECORD.value());
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setStartTime(jdl.t(j));
        hiHealthData.setEndTime(jdl.e(j));
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.DAILY_SLEEP_PROBLEM.value()), Integer.valueOf(fdaVar.h()));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.DAILY_SLEEP_DEGREE.value()), Integer.valueOf(fdaVar.j()));
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.DAILY_TARGET_PROBLEM.value()), j(fdaVar.c()));
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.DAILY_SLEEP_INTERPRET.value()), b(fdaVar));
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.DAILY_SLEEP_TASKS.value()), d(fdaVar, j));
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, hiDataOperateListener);
    }

    private static String j(List<Integer> list) {
        JSONArray jSONArray = new JSONArray();
        if (koq.b(list)) {
            LogUtil.b("SleepManagementResultUtil", "factor is null");
            return jSONArray.toString();
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray.toString();
    }

    private static List<Integer> d(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(Integer.valueOf(jSONArray.optInt(i)));
            }
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return arrayList;
    }

    public static void d(HiDataReadResultListener hiDataReadResultListener, long j, boolean z) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (z) {
            hiDataReadOption.setTimeInterval(0L, j + 86400000);
            hiDataReadOption.setSortOrder(1);
        } else {
            hiDataReadOption.setTimeInterval(jdl.s(j) - 86400000, jdl.a(j) + 86400000);
        }
        hiDataReadOption.setType(d);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, hiDataReadResultListener);
    }

    public static void e(HiDataReadResultListener hiDataReadResultListener, long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(c);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, hiDataReadResultListener);
    }

    public static void e(HiDataOperateListener hiDataOperateListener, long j, long j2, fdh fdhVar, int i) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(DicDataTypeUtil.DataType.MONTHLY_SLEEP.value());
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setStartTime(jdl.s(j));
        hiHealthData.setEndTime(jdl.a(j));
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.RHYTHM_TYPE.value()), Integer.valueOf(fdhVar.o()));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MONTHLY_SLEEP_PROBLEM.value()), Integer.valueOf(fdhVar.f()));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MONTHLY_SLEEP_DEGREE.value()), Integer.valueOf(fdhVar.g()));
        int value = DicDataTypeUtil.DataType.MONTHLY_SLEEP_TASKS.value();
        hashMap2.put(Integer.valueOf(value), b(fdhVar, j2, i));
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.MONTHLY_SLEEP_INTERPRET.value()), d(fdhVar));
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, hiDataOperateListener);
    }

    public static Map<String, Object> b(fda fdaVar, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("dailySleepProblem", Integer.valueOf(fdaVar.h()));
        hashMap.put("dailySleepDegree", Integer.valueOf(fdaVar.j()));
        hashMap.put("dailyTargetProblem", j(fdaVar.c()));
        hashMap.put("dailySleepInterpretParam", b(fdaVar));
        hashMap.put("dailySleepTasks", d(fdaVar, j));
        return hashMap;
    }

    public static Map<String, Object> c(fdh fdhVar, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("rhythmType", Integer.valueOf(fdhVar.o()));
        hashMap.put("monthlySleepProblem", Integer.valueOf(fdhVar.f()));
        hashMap.put("monthlySleepDegree", Integer.valueOf(fdhVar.g()));
        hashMap.put("monthlySleepInterpretParam", d(fdhVar));
        hashMap.put("monthlySleepTasks", b(fdhVar, j));
        return hashMap;
    }

    private static String b(fdh fdhVar, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("generateTime", j);
            jSONObject.put("monthlyTasks", g(fdhVar.i()));
            jSONObject.put("monthlyDailyTasks", a(fdhVar, j));
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject.toString();
    }

    private static String b(fdh fdhVar, long j, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("generateTime", j);
            jSONObject.put("generateType", i);
            jSONObject.put("monthlyTasks", g(fdhVar.i()));
            jSONObject.put("monthlyDailyTasks", a(fdhVar, j));
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject.toString();
    }

    private static JSONArray a(fdh fdhVar, long j) {
        JSONArray jSONArray = new JSONArray();
        List<List<fcv>> b2 = fdhVar.b();
        if (koq.b(b2)) {
            LogUtil.b("SleepManagementResultUtil", "tasks is null");
            return jSONArray;
        }
        for (List<fcv> list : b2) {
            if (!koq.b(list)) {
                jSONArray.put(d(list));
            }
        }
        return jSONArray;
    }

    private static JSONArray d(List<fcv> list) {
        JSONArray jSONArray = new JSONArray();
        if (koq.b(list)) {
            LogUtil.b("SleepManagementResultUtil", "task is null");
            return jSONArray;
        }
        for (fcv fcvVar : list) {
            if (fcvVar != null) {
                jSONArray.put(b(fcvVar));
            }
        }
        return jSONArray;
    }

    private static JSONObject b(fcv fcvVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("taskId", fcvVar.a());
            jSONObject.put("taskCode", fcvVar.e());
            jSONObject.put(ParsedFieldTag.TASK_TYPE, fcvVar.c());
            jSONObject.put("dailyTaskCard", a(fcvVar.d()));
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject;
    }

    private static String d(fdh fdhVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("monthlyProbDesc", fdhVar.e());
            jSONObject.put("monthlyProbVal", g(fdhVar.h()));
            jSONObject.put("monthlyMajorReason", g(fdhVar.a()));
            jSONObject.put("monthlyImpactDesc", fdhVar.d());
            jSONObject.put("monthlyRecomd", d(fdhVar.j()));
            jSONObject.put("monthlyDailyAdvices", c(fdhVar.c()));
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject.toString();
    }

    private static JSONArray g(List<Integer> list) {
        JSONArray jSONArray = new JSONArray();
        if (koq.b(list)) {
            return jSONArray;
        }
        for (Integer num : list) {
            if (num != null) {
                jSONArray.put(num);
            }
        }
        return jSONArray;
    }

    private static JSONArray c(List<fct> list) {
        JSONArray jSONArray = new JSONArray();
        if (koq.b(list)) {
            return jSONArray;
        }
        Iterator<fct> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(d(it.next()));
        }
        return jSONArray;
    }

    private static JSONObject d(fct fctVar) {
        JSONObject jSONObject = new JSONObject();
        if (fctVar == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("adviceId", fctVar.d());
            jSONObject.put("adviceCode", fctVar.c());
            jSONObject.put("adviceType", fctVar.b());
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject;
    }

    private static JSONObject d(fcu fcuVar) {
        JSONObject jSONObject = new JSONObject();
        if (fcuVar == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("recomdAsleepTime", fcuVar.e());
            jSONObject.put("recomdAwakeTime", fcuVar.d());
            jSONObject.put("recomdSleepDur", fcuVar.c());
            fca d2 = d();
            if (d2 == null) {
                LogUtil.b("SleepManagementResultUtil", "curAlarm is null");
            } else {
                jSONObject.put("fallAsleepTime", c(d2));
                jSONObject.put("wakeupTime", e(d2));
            }
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject;
    }

    private static long e(fca fcaVar) {
        return ((fcaVar.a() * 60) + fcaVar.e()) * 60000;
    }

    private static long c(fca fcaVar) {
        return ((fcaVar.f() * 60) + fcaVar.l()) * 60000;
    }

    private static fca d() {
        Set<fca> alarms = new fce().getAlarms();
        LogUtil.a("SleepManagementResultUtil", "alarmInfos: ", Integer.valueOf(alarms.size()));
        for (fca fcaVar : alarms) {
            if (fcaVar.b() == 1000) {
                return fcaVar;
            }
        }
        return null;
    }

    private static JSONObject j() {
        JSONObject jSONObject = new JSONObject();
        try {
            fca d2 = d();
            if (d2 == null) {
                LogUtil.b("SleepManagementResultUtil", "getDailyRecomd alarmInfo is null");
            } else {
                jSONObject.put("fallAsleepTime", c(d2));
                jSONObject.put("wakeupTime", e(d2));
            }
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject;
    }

    private static String d(fda fdaVar, long j) {
        List<fcn> l;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("generateTime", j);
            l = fdaVar.l();
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        if (koq.b(l)) {
            return jSONObject.toString();
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<fcn> it = l.iterator();
        while (it.hasNext()) {
            jSONArray.put(e(it.next()));
        }
        jSONObject.put("dailyTask", jSONArray);
        return jSONObject.toString();
    }

    private static JSONObject e(fcn fcnVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("taskId", fcnVar.e());
            jSONObject.put("taskCode", fcnVar.c());
            jSONObject.put(ParsedFieldTag.TASK_TYPE, fcnVar.a());
            jSONObject.put("dailyTaskCard", a(fcnVar.d()));
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject;
    }

    private static JSONObject a(fcr fcrVar) {
        JSONObject jSONObject = new JSONObject();
        if (fcrVar == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("taskNum", fcrVar.a());
            jSONObject.put("taskTime", fcrVar.b());
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject;
    }

    private static String b(fda fdaVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dailyQualityDesc", fdaVar.n());
            jSONObject.put("dailyProblemDesc", fdaVar.g());
            jSONObject.put("dailyProblemImpact", fdaVar.f());
            jSONObject.put("dailyFactorDesc", fdaVar.d());
            jSONObject.put("dailyRecomd", j());
            jSONObject.put("dailyProblemDescInput", e(fdaVar.i()));
            jSONObject.put("dailyFactorDescInput", b(fdaVar.b()));
            jSONObject.put("dailyAdvice", e(fdaVar));
            jSONObject.put("dailyProblemPhysio", fdaVar.m());
            jSONObject.put("dailyFactorPhysio", fdaVar.a());
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        return jSONObject.toString();
    }

    private static JSONArray b(List<fco> list) {
        JSONArray jSONArray = new JSONArray();
        try {
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        if (koq.b(list)) {
            return jSONArray;
        }
        for (fco fcoVar : list) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("val", fcoVar.c());
            jSONObject.put("trans", fcoVar.a());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private static JSONArray e(List<fcm> list) {
        JSONArray jSONArray = new JSONArray();
        try {
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        if (koq.b(list)) {
            return jSONArray;
        }
        for (fcm fcmVar : list) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("val", fcmVar.e());
            jSONObject.put("trans", fcmVar.d());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private static JSONArray e(fda fdaVar) {
        List<fci> e;
        JSONArray jSONArray = new JSONArray();
        try {
            e = fdaVar.e();
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "JSONException");
        }
        if (koq.b(e)) {
            return jSONArray;
        }
        for (fci fciVar : e) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("adviceId", fciVar.a());
            jSONObject.put("adviceCode", fciVar.c());
            jSONObject.put("adviceType", fciVar.e());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public static Map<String, Object> drj_(SparseArray<Object> sparseArray) {
        HashMap hashMap = new HashMap();
        if (sparseArray == null) {
            LogUtil.b("SleepManagementResultUtil", "resultMap is null");
            return hashMap;
        }
        hashMap.put("dailySleepProblem", Integer.valueOf(drh_(sparseArray, DicDataTypeUtil.DataType.DAILY_SLEEP_PROBLEM.value())));
        hashMap.put("dailySleepDegree", Integer.valueOf(drh_(sparseArray, DicDataTypeUtil.DataType.DAILY_SLEEP_DEGREE.value())));
        hashMap.put("dailyTargetProblem", dri_(sparseArray, DicDataTypeUtil.DataType.DAILY_TARGET_PROBLEM.value()));
        hashMap.put("dailySleepInterpretParam", dri_(sparseArray, DicDataTypeUtil.DataType.DAILY_SLEEP_INTERPRET.value()));
        hashMap.put("dailySleepTasks", dri_(sparseArray, DicDataTypeUtil.DataType.DAILY_SLEEP_TASKS.value()));
        return hashMap;
    }

    public static Map<String, Object> drk_(SparseArray<Object> sparseArray, long j, boolean z) {
        HashMap hashMap = new HashMap();
        if (sparseArray == null) {
            LogUtil.b("SleepManagementResultUtil", "resultMap is null");
            return hashMap;
        }
        Object obj = sparseArray.get(DicDataTypeUtil.DataType.MONTHLY_SLEEP.value());
        if (!(obj instanceof List)) {
            LogUtil.b("SleepManagementResultUtil", "data is invalid");
            return hashMap;
        }
        List list = (List) obj;
        if (koq.b(list)) {
            LogUtil.b("SleepManagementResultUtil", "dataList is invalid");
            return hashMap;
        }
        List<HiHealthData> a2 = a(e((List<HiHealthData>) list, j, z));
        LogUtil.a("SleepManagementResultUtil", "result: ", a2.toString());
        if (a2.size() != 5) {
            LogUtil.b("SleepManagementResultUtil", "result map is not 5");
            return hashMap;
        }
        hashMap.put("rhythmType", Integer.valueOf(a(a2, DicDataTypeUtil.DataType.RHYTHM_TYPE.value())));
        hashMap.put("monthlySleepProblem", Integer.valueOf(a(a2, DicDataTypeUtil.DataType.MONTHLY_SLEEP_PROBLEM.value())));
        hashMap.put("monthlySleepDegree", Integer.valueOf(a(a2, DicDataTypeUtil.DataType.MONTHLY_SLEEP_DEGREE.value())));
        hashMap.put("monthlySleepInterpretParam", b(a2, DicDataTypeUtil.DataType.MONTHLY_SLEEP_INTERPRET.value()));
        hashMap.put("monthlySleepTasks", b(a2, DicDataTypeUtil.DataType.MONTHLY_SLEEP_TASKS.value()));
        return hashMap;
    }

    private static List<HiHealthData> e(List<HiHealthData> list, long j, boolean z) {
        if (z) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (Math.abs(jdl.e(hiHealthData.getEndTime(), jdl.a(j))) <= 2) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private static List<HiHealthData> a(List<HiHealthData> list) {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && hiHealthData.getType() == DicDataTypeUtil.DataType.MONTHLY_SLEEP_TASKS.value()) {
                try {
                    long j4 = new JSONObject(hiHealthData.getMetaData()).getLong("generateTime");
                    if (j4 > j) {
                        try {
                            j2 = hiHealthData.getEndTime();
                            j = j4;
                        } catch (JSONException unused) {
                            j = j4;
                            LogUtil.b("SleepManagementResultUtil", "JSONException");
                        }
                    }
                    long endTime = hiHealthData.getEndTime();
                    if (endTime > j3) {
                        j3 = endTime;
                    }
                } catch (JSONException unused2) {
                }
            }
        }
        LogUtil.a("SleepManagementResultUtil", "latestGenerateTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2), " latestEndTime:", Long.valueOf(j3));
        long b2 = b(j2, j3);
        ArrayList arrayList = new ArrayList(5);
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 != null && hiHealthData2.getEndTime() == b2) {
                arrayList.add(hiHealthData2);
            }
        }
        return arrayList;
    }

    private static long b(long j, long j2) {
        if (j2 - j > 1296000000) {
            j = j2;
        }
        LogUtil.a("SleepManagementResultUtil", "resultEndTime: ", Long.valueOf(j));
        return j;
    }

    public static int drh_(SparseArray<Object> sparseArray, int i) {
        HiHealthData hiHealthData;
        Object obj = sparseArray.get(i);
        if (!(obj instanceof List)) {
            return -1;
        }
        List list = (List) obj;
        if (koq.b(list) || (hiHealthData = (HiHealthData) list.get(0)) == null) {
            return -1;
        }
        return (int) hiHealthData.getValue();
    }

    private static int a(List<HiHealthData> list, int i) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && hiHealthData.getType() == i) {
                return (int) hiHealthData.getValue();
            }
        }
        return -1;
    }

    public static String dri_(SparseArray<Object> sparseArray, int i) {
        HiHealthData hiHealthData;
        Object obj = sparseArray.get(i);
        if (!(obj instanceof List)) {
            return "";
        }
        List list = (List) obj;
        return (koq.b(list) || (hiHealthData = (HiHealthData) list.get(0)) == null) ? "" : hiHealthData.getMetaData();
    }

    private static String b(List<HiHealthData> list, int i) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && hiHealthData.getType() == i) {
                return hiHealthData.getMetaData();
            }
        }
        return "";
    }

    public static boolean b(fda fdaVar, Map<String, Object> map) {
        if (fdaVar == null || map == null || fdaVar.h() != ((Integer) map.get("dailySleepProblem")).intValue() || fdaVar.j() != ((Integer) map.get("dailySleepDegree")).intValue()) {
            return false;
        }
        if (!d(j(fdaVar.c()), (String) map.get("dailyTargetProblem"))) {
            LogUtil.a("SleepManagementResultUtil", "has different target problem");
            return false;
        }
        if (!a(fdaVar, map)) {
            LogUtil.a("SleepManagementResultUtil", "has different factor string");
            return false;
        }
        if (e(fdaVar, map)) {
            return true;
        }
        LogUtil.a("SleepManagementResultUtil", "has different problem string");
        return false;
    }

    private static boolean a(fda fdaVar, Map<String, Object> map) {
        try {
            return c(new JSONObject((String) map.get("dailySleepInterpretParam")).optJSONArray("dailyFactorDescInput"), b(fdaVar.b()));
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "isSameFactorString JSONException");
            return false;
        }
    }

    private static boolean e(fda fdaVar, Map<String, Object> map) {
        try {
            JSONArray e = e(fdaVar.i());
            JSONObject jSONObject = new JSONObject((String) map.get("dailySleepInterpretParam"));
            if (c(jSONObject.optJSONArray("dailyProblemDescInput"), e) && jSONObject.optString("dailyQualityDesc").equals(fdaVar.n()) && jSONObject.optString("dailyProblemDesc").equals(fdaVar.g()) && jSONObject.optString("dailyFactorDesc").equals(fdaVar.d()) && jSONObject.optString("dailyProblemImpact").equals(fdaVar.f()) && jSONObject.optString("dailyProblemPhysio").equals(fdaVar.f())) {
                return jSONObject.optString("dailyFactorPhysio").equals(fdaVar.f());
            }
            return false;
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "isSameProblemString JSONException");
            return false;
        }
    }

    private static boolean c(JSONArray jSONArray, JSONArray jSONArray2) {
        if (e(jSONArray) && e(jSONArray2)) {
            return true;
        }
        if (e(jSONArray) || e(jSONArray2)) {
            return false;
        }
        if (d(jSONArray2.toString(), jSONArray.toString())) {
            return true;
        }
        LogUtil.a("SleepManagementResultUtil", "different array string");
        return false;
    }

    private static boolean e(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    private static boolean d(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return true;
        }
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.equals(str2)) ? false : true;
    }

    public static void d(boolean z) {
        b(z);
        qrp.b("9003", "900300013", Boolean.toString(z), new HiDataOperateListener() { // from class: pob.12
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("SleepManagementResultUtil", "setSleepManagementState errorCode: ", Integer.valueOf(i), ", object: ", obj);
            }
        });
    }

    public static void b(long j, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback) {
        synchronized (pob.class) {
            LogUtil.a("SleepManagementResultUtil", "requestAndUpdateDailyResult");
            if (!VersionControlUtil.isSupportSleepManagement()) {
                LogUtil.h("SleepManagementResultUtil", "not support sleep management");
            } else {
                e(j, sleepDailyProcessResultCallback, false, null);
            }
        }
    }

    public static void b(Date date, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback) {
        LogUtil.a("SleepManagementResultUtil", "syncRequestAndUpdateDailyResult");
        if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h("SleepManagementResultUtil", "not support sleep management");
            return;
        }
        fda b2 = fch.b(date);
        if (b2 != null) {
            sleepDailyProcessResultCallback.onResponse(0, b2);
        }
        e(date.getTime(), sleepDailyProcessResultCallback, true, b2);
    }

    private static void e(final long j, final SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback, final boolean z, final fda fdaVar) {
        c(j, new SleepManagementCallback<fcl>() { // from class: pob.2
            @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(fcl fclVar) {
                LogUtil.a("SleepManagementResultUtil", "requestAndUpdateDailyResult request success");
                fck d2 = fclVar.d();
                fda e = fclVar.e();
                if (!d2.e()) {
                    fda c2 = pob.c(d2.c());
                    c2.e(j);
                    LogUtil.a("SleepManagementResultUtil", "requestAndUpdateDailyResult not Updated, return dailyResultBean: ", c2.toString());
                    fda fdaVar2 = fdaVar;
                    LogUtil.a("SleepManagementResultUtil", "bean from sp is ", fdaVar2, "isEqual ", Boolean.valueOf(c2.equals(fdaVar2)));
                    fda fdaVar3 = fdaVar;
                    if (fdaVar3 == null || fch.e(Collections.singletonList(fdaVar3), Collections.singletonList(c2))) {
                        LogUtil.a("SleepManagementResultUtil", "callback new bean");
                        fch.d(new Date(j), c2);
                        sleepDailyProcessResultCallback.onResponse(0, c2);
                    }
                } else {
                    LogUtil.a("SleepManagementResultUtil", "requestAndUpdateDailyResult has Updated");
                    e.d(d2.e());
                    e.e(j);
                    fch.d(new Date(j), e);
                    pob.d(e, j, sleepDailyProcessResultCallback, z);
                }
                pob.c(e, j);
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
            public void onFailure(int i, String str) {
                LogUtil.b("SleepManagementResultUtil", "errCode: ", Integer.valueOf(i), ", errMsg: ", str);
                sleepDailyProcessResultCallback.onResponse(1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static fda c(Map<String, Object> map) {
        fda fdaVar = new fda();
        if (map == null) {
            LogUtil.b("SleepManagementResultUtil", "resultMap is null");
            return fdaVar;
        }
        fdaVar.b(nru.d((Map) map, "dailySleepProblem", 0));
        fdaVar.c(nru.d((Map) map, "dailySleepDegree", 0));
        fdaVar.a(d(nru.b(map, "dailyTargetProblem", "")));
        try {
            JSONObject jSONObject = new JSONObject(nru.b(map, "dailySleepInterpretParam", ""));
            fdaVar.b(jSONObject.optString("dailyQualityDesc"));
            fdaVar.c(jSONObject.optString("dailyProblemDesc"));
            fdaVar.d(jSONObject.optString("dailyProblemImpact"));
            fdaVar.a(jSONObject.optString("dailyFactorDesc"));
            fdaVar.e(c(jSONObject.optJSONArray("dailyProblemDescInput")));
            fdaVar.d(d(jSONObject.optJSONArray("dailyFactorDescInput")));
            fdaVar.c(a(jSONObject.optJSONArray("dailyAdvice")));
            fdaVar.b(b(new JSONObject(nru.b(map, "dailySleepTasks", "")).optJSONArray("dailyTask")));
        } catch (JSONException unused) {
            LogUtil.b("SleepManagementResultUtil", "dailyMapToBean JSONException");
        }
        return fdaVar;
    }

    private static List<fcn> b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    fcn fcnVar = new fcn();
                    fcnVar.c(jSONObject.optInt("taskId"));
                    fcnVar.e(jSONObject.optString("taskCode"));
                    fcnVar.b(jSONObject.optInt(ParsedFieldTag.TASK_TYPE));
                    fcnVar.c(b(jSONObject.optJSONObject("dailyTaskCard")));
                    arrayList.add(fcnVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("SleepManagementResultUtil", "transDailyTask JSONException");
            }
        }
        return arrayList;
    }

    private static fcr b(JSONObject jSONObject) {
        fcr fcrVar = new fcr();
        if (jSONObject == null) {
            return fcrVar;
        }
        fcrVar.a(jSONObject.optInt("taskNum"));
        fcrVar.b(jSONObject.optInt("taskTime"));
        return fcrVar;
    }

    private static List<fco> d(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    fco fcoVar = new fco();
                    fcoVar.d(jSONObject.optInt("val"));
                    fcoVar.b(jSONObject.optInt("trans"));
                    arrayList.add(fcoVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("SleepManagementResultUtil", "JSONException");
            }
        }
        return arrayList;
    }

    private static List<fcm> c(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    fcm fcmVar = new fcm();
                    fcmVar.d(jSONObject.optInt("val"));
                    fcmVar.b(jSONObject.optInt("trans"));
                    arrayList.add(fcmVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("SleepManagementResultUtil", "JSONException");
            }
        }
        return arrayList;
    }

    private static List<fci> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    fci fciVar = new fci();
                    fciVar.c(jSONObject.optInt("adviceId"));
                    fciVar.d(jSONObject.optString("adviceCode"));
                    fciVar.e(jSONObject.optInt("adviceType"));
                    arrayList.add(fciVar);
                }
            } catch (JSONException unused) {
                LogUtil.b("SleepManagementResultUtil", "JSONException");
            }
        }
        return arrayList;
    }

    public static void a(long j, final SleepManagementCallback<Map<String, Object>> sleepManagementCallback) {
        c(new HiDataReadResultListener() { // from class: pob.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (i != 0) {
                    LogUtil.h("SleepManagementResultUtil", "errorCode: ", Integer.valueOf(i));
                    return;
                }
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() <= 0) {
                        LogUtil.h("SleepManagementResultUtil", "map.size() <= 0");
                    } else {
                        LogUtil.a("SleepManagementResultUtil", "queryDailyResult success");
                        SleepManagementCallback.this.onSuccess(pob.drj_(sparseArray));
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("SleepManagementResultUtil", "readCardData onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
            }
        }, j);
    }

    public static void b(final long j, final SleepManagementCallback<fck> sleepManagementCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pob.3
            @Override // java.lang.Runnable
            public void run() {
                pob.c(j, new SleepManagementCallback<fcl>() { // from class: pob.3.5
                    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(fcl fclVar) {
                        LogUtil.a("SleepManagementResultUtil", "getDailyProcessResult request success");
                        fck d2 = fclVar.d();
                        fda e = fclVar.e();
                        if (d2.e()) {
                            pob.d(e, j, null, true);
                            fck fckVar = new fck();
                            fckVar.b(true);
                            fckVar.c(pob.b(e, fclVar.b()));
                            sleepManagementCallback.onSuccess(fckVar);
                            return;
                        }
                        sleepManagementCallback.onSuccess(d2);
                    }

                    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("SleepManagementResultUtil", "errCode: ", Integer.valueOf(i), ", errMsg: ", str);
                        sleepManagementCallback.onFailure(1, null);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(long j, final SleepManagementCallback<fcl> sleepManagementCallback) {
        LogUtil.a("SleepManagementResultUtil", "requestDailyResultInner, time: ", Long.valueOf(j));
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final fda[] fdaVarArr = new fda[1];
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        long currentTimeMillis = System.currentTimeMillis();
        if (sleepApi != null) {
            sleepApi.requestDailyProcessResult(jdl.t(j), new SleepDailyProcessResultCallback() { // from class: pob.4
                @Override // com.huawei.hwbasemgr.SleepDailyProcessResultCallback
                public void onResponse(int i, Object obj) {
                    if (!(obj instanceof fda)) {
                        LogUtil.b("SleepManagementResultUtil", "objData is null");
                        SleepManagementCallback.this.onFailure(0, "requestDailyProcessResult objData is null");
                        countDownLatch.countDown();
                    } else {
                        fda fdaVar = (fda) obj;
                        fdaVarArr[0] = fdaVar;
                        LogUtil.a("SleepManagementResultUtil", "requestDailyProcessResult onResponse, resultBean: ", fdaVar.toString());
                        countDownLatch.countDown();
                    }
                }
            });
        } else {
            LogUtil.a("SleepManagementResultUtil", "sleepServiceApi is null");
            countDownLatch.countDown();
        }
        final HashMap hashMap = new HashMap();
        c(new HiDataReadResultListener() { // from class: pob.9
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("SleepManagementResultUtil", "queryDailyResult data: ", obj);
                if (i != 0) {
                    LogUtil.h("SleepManagementResultUtil", "errorCode: ", Integer.valueOf(i));
                    countDownLatch.countDown();
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    countDownLatch.countDown();
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("SleepManagementResultUtil", "map.size() <= 0");
                    countDownLatch.countDown();
                } else {
                    LogUtil.a("SleepManagementResultUtil", "queryDailyResult success");
                    hashMap.putAll(pob.drj_(sparseArray));
                    countDownLatch.countDown();
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("SleepManagementResultUtil", "readCardData onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
            }
        }, j);
        try {
            countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS);
            LogUtil.a("SleepManagementResultUtil", "resultMap: ", hashMap.toString());
            if (fdaVarArr[0] == null) {
                sleepManagementCallback.onFailure(0, "calculate data is null");
                return;
            }
            fck fckVar = new fck();
            if (hashMap.size() == 0) {
                fckVar.b(true);
            } else if (b(fdaVarArr[0], hashMap)) {
                fckVar.b(false);
                fckVar.c(hashMap);
            } else {
                fckVar.b(true);
            }
            fcl fclVar = new fcl();
            fclVar.d(fckVar);
            fclVar.e(fdaVarArr[0]);
            fclVar.b(currentTimeMillis);
            sleepManagementCallback.onSuccess(fclVar);
        } catch (InterruptedException unused) {
            LogUtil.b("SleepManagementResultUtil", "InterruptedException");
            sleepManagementCallback.onFailure(2, "timeout");
        }
    }

    public static void b(Boolean bool) {
        boolean c2 = pmt.d().c();
        LogUtil.a("SleepManagementResultUtil", "sleep job service is running: ", Boolean.valueOf(c2));
        if (bool == null) {
            return;
        }
        if (bool.booleanValue() && !c2) {
            pmt.d().a();
        } else {
            if (bool.booleanValue() || !c2) {
                return;
            }
            pmt.d().e();
        }
    }

    public static void d(final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pob.7
            @Override // java.lang.Runnable
            public void run() {
                if (IBaseResponseCallback.this == null) {
                    LogUtil.h("SleepManagementResultUtil", "callback is null");
                    return;
                }
                if (!Utils.i()) {
                    LogUtil.h("SleepManagementResultUtil", "fetch HEALTH_CLOUD from no_cloud user login");
                    IBaseResponseCallback.this.d(5001, "no_cloud user login");
                    return;
                }
                if (!CommonUtil.aa(BaseApplication.getContext())) {
                    LogUtil.b("SleepManagementResultUtil", "no network,please check");
                    IBaseResponseCallback.this.d(5001, "no network");
                    return;
                }
                JSONObject optJSONObject = eaf.a().optJSONObject("publicArgs");
                if (optJSONObject == null) {
                    LogUtil.h("SleepManagementResultUtil", "requestBody is null");
                    IBaseResponseCallback.this.d(5001, "requestBody is null");
                    return;
                }
                try {
                    optJSONObject.put("category", 0);
                    optJSONObject.put("subCategory", 1);
                    if (pob.b == null) {
                        String unused = pob.b = pob.f();
                    }
                    LogUtil.a("SleepManagementResultUtil", "mUrl: " + pob.b + ", requestBody: " + optJSONObject);
                    final long elapsedRealtime = SystemClock.elapsedRealtime();
                    Map<String, String> e = eaf.e();
                    LogUtil.a("SleepManagementResultUtil", "headers: ", e.toString());
                    lqi.d().b(pob.b, e, optJSONObject.toString(), String.class, new ResultCallback<String>() { // from class: pob.7.1
                        @Override // com.huawei.networkclient.ResultCallback
                        /* renamed from: d, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(String str) {
                            LogUtil.a("SleepManagementResultUtil", "getPlanState onSuccess, post time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                            try {
                                if (str == null) {
                                    IBaseResponseCallback.this.d(5001, "data is null");
                                    return;
                                }
                                JSONObject jSONObject = new JSONObject(str);
                                LogUtil.a("SleepManagementResultUtil", "response: ", jSONObject);
                                int optInt = jSONObject.optInt("resultCode");
                                if (optInt == 0) {
                                    pob.b(IBaseResponseCallback.this, jSONObject);
                                } else {
                                    IBaseResponseCallback.this.d(optInt, jSONObject.optString("resultDesc"));
                                    pob.a(optInt, jSONObject.optString("resultDesc"));
                                }
                            } catch (JSONException unused2) {
                                IBaseResponseCallback.this.d(5002, "JSONException");
                            }
                        }

                        @Override // com.huawei.networkclient.ResultCallback
                        public void onFailure(Throwable th) {
                            LogUtil.a("SleepManagementResultUtil", "getPlanState onFail, post time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                            IBaseResponseCallback.this.d(FitnessStatusCodes.DATA_TYPE_NOT_FOUND, th.getMessage());
                        }
                    });
                } catch (JSONException unused2) {
                    LogUtil.b("SleepManagementResultUtil", "JSONException");
                    IBaseResponseCallback.this.d(5001, "JSONException");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(int i, Object obj) {
        boolean z;
        LogUtil.a("SleepManagementResultUtil", "resCode:", Integer.valueOf(i));
        if (obj instanceof Boolean) {
            z = ((Boolean) obj).booleanValue();
        } else if (i != 23030) {
            return;
        } else {
            z = false;
        }
        b(z);
    }

    private static void b(boolean z) {
        String format = String.format(Locale.ROOT, "%d,%b", Long.valueOf(System.currentTimeMillis()), Boolean.valueOf(z));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "intelSleepTimeStatus", format, (StorageParams) null);
        LogUtil.a("SleepManagementResultUtil", "setStatusToSp success:", format);
    }

    public static Boolean a(Boolean bool) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_COIN_DROP), "intelSleepTimeStatus");
        if (!TextUtils.isEmpty(b2)) {
            String[] split = b2.split(",");
            if (split.length == 2 && c(CommonUtil.g(split[0]))) {
                bool = Boolean.valueOf(Boolean.parseBoolean(split[1]));
            }
        }
        LogUtil.a("SleepManagementResultUtil", "timeAndStatus:", b2);
        return bool;
    }

    private static boolean c(long j) {
        return System.currentTimeMillis() - j <= 86400000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(IBaseResponseCallback iBaseResponseCallback, JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("planDetail");
        if (optJSONObject == null) {
            LogUtil.h("SleepManagementResultUtil", "planDetail is null");
            iBaseResponseCallback.d(5002, "planDetail is null");
            return;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("publicInfo");
        if (optJSONObject2 == null) {
            LogUtil.h("SleepManagementResultUtil", "planDetail is null");
            iBaseResponseCallback.d(5002, "publicInfo is null");
            return;
        }
        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("planMetaInfo");
        if (optJSONObject3 == null) {
            LogUtil.h("SleepManagementResultUtil", "planDetail is null");
            iBaseResponseCallback.d(5002, "planMetaInfo is null");
            return;
        }
        int optInt = optJSONObject3.optInt("status");
        int optInt2 = optJSONObject3.optInt("transactionStatus");
        LogUtil.a("SleepManagementResultUtil", "parsePlanDetail: status: ", Integer.valueOf(optInt), ", transactionStatus: ", Integer.valueOf(optInt2));
        iBaseResponseCallback.d(0, Boolean.valueOf(optInt == 1 && optInt2 == 1));
        a(0, Boolean.valueOf(optInt == 1 && optInt2 == 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("domainHealthdataHicloud") + "/healthExpansion/app/plan/v1/getPlanDetail";
    }

    /* loaded from: classes6.dex */
    public static class a implements SleepMonthlyProcessResultCallback<fdh> {

        /* renamed from: a, reason: collision with root package name */
        private long f16201a;
        private long b;
        private int d = 0;
        private SleepJobService.e e;

        public a(SleepJobService.e eVar, long j, long j2) {
            this.e = eVar;
            this.f16201a = j;
            this.b = j2;
        }

        @Override // com.huawei.hwbasemgr.SleepMonthlyProcessResultCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, fdh fdhVar) {
            LogUtil.a("SleepManagementResultUtil", "handle from MonthlyProcessResultCallback");
            try {
                pob.b(i, fdhVar, this.f16201a, this.b, this.d);
            } catch (NoSuchMethodError e) {
                ReleaseLogUtil.c("R_SleepManagementResultUtil", "MonthlyProcessResultCallback error ", ExceptionUtils.d(e));
            }
            SleepJobService.e eVar = this.e;
            if (eVar != null) {
                eVar.d(0, true);
            }
        }
    }
}
