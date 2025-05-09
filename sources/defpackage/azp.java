package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.healthmodel.bean.BloodPressurePlanResultBean;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class azp {
    private static final AtomicBoolean e = new AtomicBoolean(true);

    public static void e(final String str) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: azo
                @Override // java.lang.Runnable
                public final void run() {
                    azp.e(str);
                }
            });
        } else {
            cbi.d(new ResponseCallback() { // from class: azq
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    azp.e((List<cbk>) obj, str);
                }
            });
        }
    }

    public static void e(List<cbk> list, String str) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "refreshBloodPressureMeasurePlan list ", list);
            return;
        }
        e(String.valueOf(list.size()), str);
        bao.e("bloodPressureMeasurePlan", HiJsonUtil.e(list));
        bao.e("bloodPressureMeasurePlanSaveTime", String.valueOf(System.currentTimeMillis()));
        cbk cbkVar = list.get(0);
        if (cbkVar != null) {
            bao.e("blood_pressure_day_of_week", String.valueOf(cbkVar.d()));
        }
    }

    public static void e(final String str, final String str2) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: azu
                @Override // java.lang.Runnable
                public final void run() {
                    azp.e(str, str2);
                }
            });
            return;
        }
        int b = DateFormatUtil.b(jdl.y(System.currentTimeMillis()));
        HealthLifeBean a2 = aus.a(1, b, str2);
        if (TextUtils.isEmpty(a2.getTarget()) || !a2.getTarget().contains(String.valueOf(12))) {
            LogUtil.a("HealthLife_BloodPressureMeasureUtil", "setBloodPressureTarget cloverBean ", a2);
            return;
        }
        HealthLifeBean a3 = aus.a(12, b, str2);
        if (azi.j(a3) && b == a3.getRecordDay()) {
            a3.setTarget(str);
            aus.a(a3, str2);
        }
    }

    public static void c(final HealthLifeBean healthLifeBean, final ResponseCallback<HealthLifeBean> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getTaskNewestResult listener is null");
            return;
        }
        if (healthLifeBean == null) {
            responseCallback.onResponse(-1, null);
            return;
        }
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: azs
                @Override // java.lang.Runnable
                public final void run() {
                    azp.c(HealthLifeBean.this, (ResponseCallback<HealthLifeBean>) responseCallback);
                }
            });
            return;
        }
        List<cbk> d = d();
        if (koq.b(d) || d.size() < 2) {
            responseCallback.onResponse(-1, healthLifeBean);
            return;
        }
        cbk cbkVar = d.get(0);
        cbk cbkVar2 = d.get(d.size() - 1);
        if (cbkVar == null || cbkVar2 == null) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getTaskNewestResult wokeUpAlarm or sleepAlarm is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        int recordDay = healthLifeBean.getRecordDay();
        long c = azi.c(healthLifeBean);
        long e2 = jdl.e(c, cbkVar.a(), cbkVar.e());
        long e3 = jdl.e(c, cbkVar2.a(), cbkVar2.e());
        long b = azi.b(healthLifeBean);
        final boolean z = e2 > e3;
        if (z) {
            c += 9000000;
            b += 9000000;
        }
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getTaskNewestResult recordDay ", Integer.valueOf(recordDay), " startTime ", Long.valueOf(c), " endTime ", Long.valueOf(b), " isCrossDay ", Boolean.valueOf(z));
        final List<BloodPressurePlanResultBean> b2 = b(d);
        c(c, b, new HiAggregateListener() { // from class: azp.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                azp.b(z, healthLifeBean, list, b2, responseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(boolean z, HealthLifeBean healthLifeBean, List<HiHealthData> list, List<BloodPressurePlanResultBean> list2, ResponseCallback<HealthLifeBean> responseCallback) {
        int b = b();
        int recordDay = healthLifeBean.getRecordDay();
        b(healthLifeBean, b, list2.size());
        int complete = healthLifeBean.getComplete();
        if (b <= recordDay && complete <= 0 && azi.d(azi.b(healthLifeBean))) {
            healthLifeBean.setRest(1);
            d(healthLifeBean);
        }
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "handlePressureDayData dataList is empty");
            healthLifeBean.setExtendInfo(HiJsonUtil.e(list2));
            responseCallback.onResponse(0, healthLifeBean);
            return;
        }
        SparseArray<List<BloodPressurePlanResultBean>> mh_ = mh_(list, list2, z);
        if (mh_.size() == 0) {
            healthLifeBean.setExtendInfo(HiJsonUtil.e(list2));
            responseCallback.onResponse(0, healthLifeBean);
            return;
        }
        List<BloodPressurePlanResultBean> list3 = mh_.get(recordDay);
        int a2 = a(list3);
        boolean d = d(list3.get(0));
        boolean d2 = d(list3.get(list3.size() - 1));
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "handlePressureDayData isCompleteWakeUp=", Boolean.valueOf(d), ",isCompleteSleep=", Boolean.valueOf(d2), ",recordDay=", Integer.valueOf(recordDay), ",count=", Integer.valueOf(a2));
        if (healthLifeBean.getComplete() == 0 && d && d2) {
            d(healthLifeBean);
        }
        healthLifeBean.setResult(String.valueOf(a2));
        healthLifeBean.setExtendInfo(HiJsonUtil.e(list3));
        if (d) {
            responseCallback.onResponse(0, healthLifeBean);
        } else {
            e(list3, list, healthLifeBean, d2, responseCallback);
        }
    }

    private static void e(final List<BloodPressurePlanResultBean> list, final List<HiHealthData> list2, final HealthLifeBean healthLifeBean, final boolean z, final ResponseCallback<HealthLifeBean> responseCallback) {
        final int recordDay = healthLifeBean.getRecordDay();
        long c = azi.c(healthLifeBean) / 1000;
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getSleepDayData recordDay=" + recordDay + ",unitSize=", Integer.valueOf(ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL), ",unitCount=", 2, ",unitType=", 3);
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getSleepDayData : healthDataMgrApi is null.");
            healthLifeBean.setExtendInfo(HiJsonUtil.e(list));
            responseCallback.onResponse(0, healthLifeBean);
            return;
        }
        healthDataMgrApi.getCoreSleepDetail(c, 3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 2, new IBaseResponseCallback() { // from class: azv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                azp.e(HealthLifeBean.this, list, responseCallback, recordDay, list2, z, i, obj);
            }
        });
    }

    static /* synthetic */ void e(HealthLifeBean healthLifeBean, List list, ResponseCallback responseCallback, int i, List list2, boolean z, int i2, Object obj) {
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getSleepDayData getCoreSleepDetail onResponse errorCode=", Integer.valueOf(i2));
        if (!koq.e(obj, SleepTotalData.class)) {
            healthLifeBean.setExtendInfo(HiJsonUtil.e(list));
            responseCallback.onResponse(0, healthLifeBean);
            return;
        }
        SleepTotalData sleepTotalData = (SleepTotalData) ((List) obj).get(0);
        if (!a(sleepTotalData)) {
            LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getSleepDayData sleepTotalDataList checkDataValid false");
            healthLifeBean.setExtendInfo(HiJsonUtil.e(list));
            responseCallback.onResponse(0, healthLifeBean);
            return;
        }
        int wakeUpTime = sleepTotalData.getWakeUpTime();
        if (wakeUpTime == 0) {
            wakeUpTime = sleepTotalData.getCommonWakeUpTime();
        }
        if (sleepTotalData.isPhone()) {
            wakeUpTime = (int) sleepTotalData.getPhoneStartTime();
        }
        int i3 = wakeUpTime + 1200;
        if (i3 > 1440) {
            i3 = wakeUpTime - 240;
        }
        a(z, a((List<HiHealthData>) list2, c(i, i3)), healthLifeBean, list, responseCallback);
    }

    private static void a(boolean z, HiHealthData hiHealthData, HealthLifeBean healthLifeBean, List<BloodPressurePlanResultBean> list, ResponseCallback<HealthLifeBean> responseCallback) {
        if (hiHealthData == null) {
            healthLifeBean.setExtendInfo(HiJsonUtil.e(list));
            responseCallback.onResponse(0, healthLifeBean);
            return;
        }
        ArrayList arrayList = new ArrayList();
        BloodPressurePlanResultBean bloodPressurePlanResultBean = null;
        for (BloodPressurePlanResultBean bloodPressurePlanResultBean2 : list) {
            if (bloodPressurePlanResultBean2 != null) {
                if (bloodPressurePlanResultBean2.getPlanId() == 0) {
                    bloodPressurePlanResultBean = bloodPressurePlanResultBean2;
                } else {
                    arrayList.add(bloodPressurePlanResultBean2);
                }
            }
        }
        if (bloodPressurePlanResultBean != null) {
            bloodPressurePlanResultBean.setPressureTime(hiHealthData.getStartTime());
            bloodPressurePlanResultBean.setBloodPressureSystolic(hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC"));
            bloodPressurePlanResultBean.setBloodPressureDiastolic(hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC"));
            arrayList.add(0, bloodPressurePlanResultBean);
        }
        healthLifeBean.setExtendInfo(HiJsonUtil.e(jdn.a(arrayList)));
        a(healthLifeBean);
        if (z) {
            d(healthLifeBean);
        }
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "refreshDayTask taskBean ", healthLifeBean);
        responseCallback.onResponse(0, healthLifeBean);
    }

    private static void mm_(SparseArray<HealthLifeBean> sparseArray, List<cbk> list) {
        if (koq.b(list)) {
            return;
        }
        int b = b();
        int size = list.size();
        int size2 = sparseArray.size();
        for (int i = 0; i < size2; i++) {
            b(sparseArray.valueAt(i), b, size);
        }
    }

    private static void b(HealthLifeBean healthLifeBean, int i, int i2) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "setTarget lifeBean is null");
        } else if (CommonUtils.h(healthLifeBean.getTarget()) < 2 || (i != 0 && healthLifeBean.getRecordDay() > i)) {
            healthLifeBean.setTarget(String.valueOf(Math.max(i2, 2)));
        }
    }

    private static void mn_(SparseArray<HealthLifeBean> sparseArray, String str) {
        int b = b();
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            HealthLifeBean valueAt = sparseArray.valueAt(i);
            if (valueAt != null) {
                int keyAt = sparseArray.keyAt(i);
                int complete = valueAt.getComplete();
                boolean d = azi.d(azi.c(sparseArray.get(keyAt)));
                if (complete == 0 && d) {
                    valueAt.setRest(1);
                    valueAt.setComplete(1);
                    valueAt.setIsUpdated(true);
                }
                if (CommonUtils.h(valueAt.getTarget()) < 2 || (b != 0 && keyAt > b)) {
                    valueAt.setTarget(str);
                }
                LogUtil.a("HealthLife_BloodPressureMeasureUtil", "updateTaskBean startDay=", Integer.valueOf(keyAt), ",status=", Integer.valueOf(complete), ",isRest=", Boolean.valueOf(d), ",target=", valueAt.getTarget());
            }
        }
    }

    private static void d(HealthLifeBean healthLifeBean) {
        long currentTimeMillis = System.currentTimeMillis();
        if (healthLifeBean.getComplete() <= 0 && healthLifeBean.getRecordDay() == DateFormatUtil.b(currentTimeMillis)) {
            aza.d(healthLifeBean.getId(), 1);
        }
        healthLifeBean.setComplete(1);
        healthLifeBean.setTimestamp(currentTimeMillis);
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        healthLifeBean.setSyncStatus(0);
        healthLifeBean.setIsUpdated(true);
    }

    public static List<cbk> d() {
        ArrayList arrayList = new ArrayList();
        if (DateFormatUtil.b(CommonUtil.g(bao.e("bloodPressureMeasurePlanSaveTime"))) == DateFormatUtil.b(System.currentTimeMillis())) {
            List<cbk> b = azi.b(bao.e("bloodPressureMeasurePlan"));
            LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getAlarmInfo infoList ", b);
            if (koq.c(b)) {
                arrayList.addAll(b);
            }
        }
        if (koq.b(arrayList)) {
            AtomicBoolean atomicBoolean = e;
            if (atomicBoolean.get()) {
                List<cbk> a2 = cbi.a();
                if (koq.c(a2)) {
                    atomicBoolean.set(false);
                    bao.e("bloodPressureMeasurePlan", HiJsonUtil.e(a2));
                }
                arrayList.addAll(a2);
            }
        }
        if (koq.b(arrayList)) {
            List<cbk> b2 = azi.b(bao.e("bloodPressureMeasurePlan"));
            if (koq.b(b2)) {
                b2 = azi.h();
            }
            arrayList.addAll(b2);
        }
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getAlarmInfo list ", arrayList);
        if (koq.c(arrayList)) {
            azi.d(arrayList);
            cbk cbkVar = (cbk) arrayList.get(0);
            if (cbkVar != null) {
                bao.e("blood_pressure_day_of_week", String.valueOf(cbkVar.d()));
            }
        }
        return arrayList;
    }

    public static void mg_(SparseArray<HealthLifeBean> sparseArray, ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getTaskNewestWeekResults listener is null");
            return;
        }
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getTaskNewestWeekResults taskList is empty");
            responseCallback.onResponse(0, sparseArray);
            return;
        }
        List<cbk> d = d();
        if (koq.b(d) || d.size() < 2) {
            LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getTaskNewestWeekResults alarmInfoList error");
            mm_(sparseArray, d);
            responseCallback.onResponse(0, sparseArray);
        } else {
            mn_(sparseArray, String.valueOf(d.size()));
            me_(sparseArray, d, responseCallback);
        }
    }

    private static int a(List<BloodPressurePlanResultBean> list) {
        int i = 0;
        if (koq.b(list)) {
            LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getPlanCompleteCount planResultList is empty");
            return 0;
        }
        for (BloodPressurePlanResultBean bloodPressurePlanResultBean : list) {
            if (bloodPressurePlanResultBean != null && bloodPressurePlanResultBean.getPressureTime() > 0) {
                i++;
            }
        }
        return i;
    }

    private static boolean a(cbk cbkVar, cbk cbkVar2) {
        long currentTimeMillis = System.currentTimeMillis();
        return jdl.e(currentTimeMillis, cbkVar.a(), cbkVar.e()) > jdl.e(currentTimeMillis, cbkVar2.a(), cbkVar2.e());
    }

    private static void me_(final SparseArray<HealthLifeBean> sparseArray, List<cbk> list, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        cbk cbkVar = list.get(0);
        cbk cbkVar2 = list.get(list.size() - 1);
        if (cbkVar == null || cbkVar2 == null) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getPressureWeekData startPlan or endPlan is null");
            responseCallback.onResponse(0, sparseArray);
            return;
        }
        final int keyAt = sparseArray.keyAt(0);
        final int keyAt2 = sparseArray.keyAt(sparseArray.size() - 1);
        long lN_ = azi.lN_(sparseArray);
        long lK_ = azi.lK_(sparseArray);
        final boolean a2 = a(cbkVar, cbkVar2);
        if (a2) {
            lN_ += 9000000;
            lK_ += 9000000;
        }
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getPressureWeekData startTime ", Long.valueOf(lN_), " endTime ", Long.valueOf(lK_), " isCrossDay ", Boolean.valueOf(a2), " startDay ", Integer.valueOf(keyAt), " endDay ", Integer.valueOf(keyAt2));
        final List<BloodPressurePlanResultBean> b = b(list);
        mk_(sparseArray, b);
        c(lN_, lK_, new HiAggregateListener() { // from class: azp.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list2, int i, int i2) {
                if (!koq.b(list2)) {
                    SparseArray mh_ = azp.mh_(list2, b, a2);
                    if (mh_.size() != 0) {
                        azp.ml_(mh_, sparseArray);
                        SparseArray sparseArray2 = sparseArray;
                        int i3 = keyAt;
                        azp.mf_(list2, sparseArray2, i3, jdl.e(DateFormatUtil.c(i3), DateFormatUtil.c(keyAt2)), ResponseCallback.this);
                        return;
                    }
                    ResponseCallback.this.onResponse(0, sparseArray);
                    return;
                }
                LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getPressureWeekData onResult dataList empty");
                ResponseCallback.this.onResponse(0, sparseArray);
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list2, int i2, int i3) {
                LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getPressureWeekData onResultIntent callback");
            }
        });
    }

    private static void mk_(SparseArray<HealthLifeBean> sparseArray, List<BloodPressurePlanResultBean> list) {
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            HealthLifeBean valueAt = sparseArray.valueAt(i);
            if (valueAt != null) {
                valueAt.setExtendInfo(HiJsonUtil.e(jdn.a(list)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SparseArray<List<BloodPressurePlanResultBean>> mh_(List<HiHealthData> list, List<BloodPressurePlanResultBean> list2, boolean z) {
        SparseArray<List<BloodPressurePlanResultBean>> sparseArray = new SparseArray<>();
        for (Map.Entry<Integer, List<HiHealthData>> entry : b(list, z).entrySet()) {
            List<HiHealthData> value = entry.getValue();
            if (!koq.b(value)) {
                sparseArray.put(entry.getKey().intValue(), c(z, (List<BloodPressurePlanResultBean>) jdn.a(list2), value));
            }
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            c(sparseArray.valueAt(i));
        }
        return sparseArray;
    }

    private static List<BloodPressurePlanResultBean> c(boolean z, List<BloodPressurePlanResultBean> list, List<HiHealthData> list2) {
        HiHealthData a2;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "convertPressurePlanData planResultRecord is empty");
            return Collections.emptyList();
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            BloodPressurePlanResultBean bloodPressurePlanResultBean = list.get(i);
            if (bloodPressurePlanResultBean != null && (a2 = a(list2, bloodPressurePlanResultBean, z)) != null) {
                bloodPressurePlanResultBean.setPressureTime(a2.getStartTime());
                bloodPressurePlanResultBean.setBloodPressureSystolic(a2.getDouble("BLOOD_PRESSURE_SYSTOLIC"));
                bloodPressurePlanResultBean.setBloodPressureDiastolic(a2.getDouble("BLOOD_PRESSURE_DIASTOLIC"));
            }
        }
        return list;
    }

    private static HiHealthData a(List<HiHealthData> list, BloodPressurePlanResultBean bloodPressurePlanResultBean, boolean z) {
        long maxTime = bloodPressurePlanResultBean.getMaxTime();
        long minTime = bloodPressurePlanResultBean.getMinTime();
        int size = list.size();
        HiHealthData hiHealthData = null;
        for (int i = 0; i < size; i++) {
            HiHealthData hiHealthData2 = list.get(i);
            if (hiHealthData2 != null) {
                long startTime = hiHealthData2.getStartTime();
                long b = b(startTime, z);
                if (b <= maxTime && b >= minTime && (hiHealthData == null || hiHealthData.getStartTime() < startTime)) {
                    hiHealthData = hiHealthData2;
                }
            }
        }
        return hiHealthData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ml_(SparseArray<List<BloodPressurePlanResultBean>> sparseArray, SparseArray<HealthLifeBean> sparseArray2) {
        long b = b();
        if (b <= 0) {
            b = System.currentTimeMillis();
        }
        int b2 = DateFormatUtil.b(b);
        int size = sparseArray2.size();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray2.keyAt(i);
            HealthLifeBean healthLifeBean = sparseArray2.get(keyAt);
            if (b2 <= keyAt && healthLifeBean.getComplete() == 0 && azi.d(azi.c(healthLifeBean))) {
                healthLifeBean.setRest(1);
                d(healthLifeBean);
            }
            List<BloodPressurePlanResultBean> list = sparseArray.get(keyAt);
            if (koq.b(list)) {
                LogUtil.a("HealthLife_BloodPressureMeasureUtil", "refreshWeekTaskList planResultList is empty", ", recordDay ", Integer.valueOf(keyAt));
            } else {
                int a2 = a(list);
                boolean d = d(list.get(0));
                boolean d2 = d(list.get(list.size() - 1));
                LogUtil.a("HealthLife_BloodPressureMeasureUtil", "refreshWeekTaskList isCompleteWakeUp=", Boolean.valueOf(d), ",isCompleteSleep=", Boolean.valueOf(d2), ",recordDay=", Integer.valueOf(keyAt), ",count=", Integer.valueOf(a2));
                if (healthLifeBean.getComplete() == 0 && d && d2) {
                    d(healthLifeBean);
                }
                healthLifeBean.setResult(String.valueOf(a2));
                healthLifeBean.setExtendInfo(HiJsonUtil.e(list));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void mf_(final List<HiHealthData> list, final SparseArray<HealthLifeBean> sparseArray, int i, int i2, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getSleepWeekDate startDay=", Integer.valueOf(i), ",intervalDays=", Integer.valueOf(i2));
        long c = DateFormatUtil.c(i) / 1000;
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getSleepWeekDate unitSize=", Integer.valueOf(ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL), ",unitCount=", 8, ",unitType=", 3, ",beginTime=", Long.valueOf(c));
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getSleepWeekDate healthDataMgrApi is null.");
            responseCallback.onResponse(0, sparseArray);
        } else {
            healthDataMgrApi.getCoreSleepDetail(c, 3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 8, new IBaseResponseCallback() { // from class: azr
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj) {
                    azp.mi_(list, sparseArray, responseCallback, i3, obj);
                }
            });
        }
    }

    static /* synthetic */ void mi_(List list, SparseArray sparseArray, ResponseCallback responseCallback, int i, Object obj) {
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getSleepWeekDate onResponse errorCode = ", Integer.valueOf(i));
        if (koq.e(obj, SleepTotalData.class)) {
            mj_(list, sparseArray, (List) obj, responseCallback);
        } else {
            responseCallback.onResponse(0, sparseArray);
        }
    }

    private static void mj_(List<HiHealthData> list, SparseArray<HealthLifeBean> sparseArray, List<SleepTotalData> list2, ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        int b;
        HealthLifeBean healthLifeBean;
        List<BloodPressurePlanResultBean> e2;
        BloodPressurePlanResultBean d;
        if (koq.b(list2)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "processWeekFitnessSleepData sleepList is empty");
            responseCallback.onResponse(0, sparseArray);
            return;
        }
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "processWeekFitnessSleepData size=", Integer.valueOf(list2.size()));
        for (SleepTotalData sleepTotalData : list2) {
            if (sleepTotalData != null && (healthLifeBean = sparseArray.get((b = DateFormatUtil.b(sleepTotalData.getSleepDayTime())))) != null && (d = d((e2 = e(healthLifeBean)))) != null && d.getPressureTime() <= 0) {
                int wakeUpTime = sleepTotalData.getWakeUpTime();
                if (wakeUpTime == 0) {
                    wakeUpTime = sleepTotalData.getCommonWakeUpTime();
                }
                if (sleepTotalData.isPhone()) {
                    wakeUpTime = (int) sleepTotalData.getPhoneStartTime();
                }
                int i = wakeUpTime + 1200;
                if (i > 1440) {
                    i = wakeUpTime - 240;
                }
                if (a(sleepTotalData)) {
                    long c = c(b, i);
                    HiHealthData a2 = a(list, c);
                    LogUtil.a("HealthLife_BloodPressureMeasureUtil", "processWeekFitnessSleepData sleepDay ", Integer.valueOf(b), " sleepTime ", Integer.valueOf(i), " wokeUpTime ", Long.valueOf(c), " wokeUp ", a2);
                    if (a2 != null) {
                        ArrayList arrayList = new ArrayList();
                        for (BloodPressurePlanResultBean bloodPressurePlanResultBean : e2) {
                            if (bloodPressurePlanResultBean != null && bloodPressurePlanResultBean.getPlanId() != 0) {
                                arrayList.add(bloodPressurePlanResultBean);
                            }
                        }
                        d.setPressureTime(a2.getStartTime());
                        d.setBloodPressureSystolic(a2.getDouble("BLOOD_PRESSURE_SYSTOLIC"));
                        d.setBloodPressureDiastolic(a2.getDouble("BLOOD_PRESSURE_DIASTOLIC"));
                        arrayList.add(0, d);
                        healthLifeBean.setExtendInfo(HiJsonUtil.e(jdn.a(arrayList)));
                        a(healthLifeBean);
                    }
                }
            }
        }
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "processWeekFitnessSleepData taskList ", sparseArray);
        responseCallback.onResponse(0, sparseArray);
    }

    private static void a(HealthLifeBean healthLifeBean) {
        String extendInfo = healthLifeBean.getExtendInfo();
        if (TextUtils.isEmpty(extendInfo)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "adaptWakeupResult extendInfo is empty");
            return;
        }
        List list = (List) HiJsonUtil.b(extendInfo, new TypeToken<List<BloodPressurePlanResultBean>>() { // from class: azp.1
        }.getType());
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "adaptWakeupResult pressureList is empty");
        } else {
            healthLifeBean.setResult(String.valueOf(a((List<BloodPressurePlanResultBean>) list)));
        }
    }

    private static List<BloodPressurePlanResultBean> e(HealthLifeBean healthLifeBean) {
        String extendInfo = healthLifeBean.getExtendInfo();
        if (TextUtils.isEmpty(extendInfo)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getExtendInfo extendInfo ", extendInfo);
            return Collections.emptyList();
        }
        List<BloodPressurePlanResultBean> list = (List) HiJsonUtil.b(extendInfo, new TypeToken<List<BloodPressurePlanResultBean>>() { // from class: azp.2
        }.getType());
        if (!koq.b(list)) {
            return list;
        }
        LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getExtendInfo planResultList ", list);
        return Collections.emptyList();
    }

    private static BloodPressurePlanResultBean d(List<BloodPressurePlanResultBean> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BloodPressureMeasureUtil", "getWakeUpPressurePlan planResultList is empty");
            return null;
        }
        for (BloodPressurePlanResultBean bloodPressurePlanResultBean : list) {
            if (bloodPressurePlanResultBean != null && bloodPressurePlanResultBean.getPlanId() == 0) {
                return bloodPressurePlanResultBean;
            }
        }
        return list.get(0);
    }

    private static long c(int i, int i2) {
        int i3 = i2 / 60;
        if (i3 >= 24) {
            i = DateFormatUtil.b(jdl.y(DateFormatUtil.c(i)));
            i3 -= 24;
        }
        return jdl.e(DateFormatUtil.c(i), i3, i2 % 60);
    }

    private static HiHealthData a(List<HiHealthData> list, long j) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                long startTime = hiHealthData.getStartTime();
                if (startTime >= j && Math.abs(startTime - j) <= 5400000) {
                    LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getWakeUpPressureTime pressureTime ", Long.valueOf(startTime));
                    return hiHealthData;
                }
            }
        }
        return null;
    }

    private static boolean a(SleepTotalData sleepTotalData) {
        if (!(sleepTotalData.getTotalSleepTime() == 0 && sleepTotalData.getManualBedTime() == 0) && sleepTotalData.getType() == 31) {
            return true;
        }
        if (sleepTotalData.getType() == 30) {
            return (sleepTotalData.getTotalSleepTime() == 0 && sleepTotalData.getShallowSleepTime() == 0 && sleepTotalData.getDeepSleepTime() == 0 && sleepTotalData.getCommonWakeUpTime() == sleepTotalData.getCommonFallTime()) ? false : true;
        }
        return false;
    }

    private static List<BloodPressurePlanResultBean> b(List<cbk> list) {
        ArrayList arrayList = new ArrayList(10);
        for (cbk cbkVar : list) {
            if (cbkVar != null) {
                BloodPressurePlanResultBean bloodPressurePlanResultBean = new BloodPressurePlanResultBean();
                int a2 = cbkVar.a();
                int e2 = cbkVar.e();
                long j = (a2 * 60) + e2;
                if (a2 < 2) {
                    j += 1440;
                }
                bloodPressurePlanResultBean.setMaxTime(j + 30);
                bloodPressurePlanResultBean.setMinTime(j - 30);
                bloodPressurePlanResultBean.setHour(a2);
                bloodPressurePlanResultBean.setMinute(e2);
                bloodPressurePlanResultBean.setPlanId(cbkVar.f());
                arrayList.add(bloodPressurePlanResultBean);
            }
        }
        return arrayList;
    }

    private static long b(long j, boolean z) {
        long m = (jdl.m(j) * 60) + jdl.l(j);
        return (!z || j >= jdl.e(j, 2, 30)) ? m : m + 1440;
    }

    private static void c(List<BloodPressurePlanResultBean> list) {
        int size = list.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            BloodPressurePlanResultBean bloodPressurePlanResultBean = list.get(i);
            if (bloodPressurePlanResultBean != null) {
                long pressureTime = bloodPressurePlanResultBean.getPressureTime();
                if (j == pressureTime) {
                    bloodPressurePlanResultBean.setPressureTime(0L);
                    bloodPressurePlanResultBean.setBloodPressureSystolic(0.0d);
                    bloodPressurePlanResultBean.setBloodPressureDiastolic(0.0d);
                } else {
                    j = pressureTime;
                }
            }
        }
    }

    private static HashMap<Integer, List<HiHealthData>> b(List<HiHealthData> list, boolean z) {
        HashMap<Integer, List<HiHealthData>> hashMap = new HashMap<>();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                double d = hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC");
                double d2 = hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC");
                if (d >= 1.0d || d2 >= 1.0d) {
                    int day = (int) hiHealthData.getDay();
                    if (!z) {
                        e(hashMap, day, hiHealthData);
                    } else {
                        long startTime = hiHealthData.getStartTime();
                        if (startTime < jdl.e(startTime, 2, 30)) {
                            day = DateFormatUtil.b(jdl.v(DateFormatUtil.c(day)));
                        }
                        e(hashMap, day, hiHealthData);
                    }
                }
            }
        }
        return hashMap;
    }

    private static void e(HashMap<Integer, List<HiHealthData>> hashMap, int i, HiHealthData hiHealthData) {
        List<HiHealthData> arrayList;
        if (hashMap.containsKey(Integer.valueOf(i))) {
            arrayList = hashMap.get(Integer.valueOf(i));
        } else {
            arrayList = new ArrayList<>();
        }
        if (arrayList != null) {
            arrayList.add(hiHealthData);
            hashMap.put(Integer.valueOf(i), arrayList);
        }
    }

    private static int b() {
        long g = CommonUtil.g(bao.e("bloodPressureMeasurePlanSaveTime"));
        if (g <= 0) {
            g = System.currentTimeMillis();
        }
        int b = DateFormatUtil.b(g);
        LogUtil.a("HealthLife_BloodPressureMeasureUtil", "getPlanSaveDay saveDay=", Integer.valueOf(b));
        return b;
    }

    private static boolean d(BloodPressurePlanResultBean bloodPressurePlanResultBean) {
        return bloodPressurePlanResultBean != null && bloodPressurePlanResultBean.getPressureTime() > 0;
    }

    private static void c(long j, long j2, HiAggregateListener hiAggregateListener) {
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(e(j, j2), hiAggregateListener);
    }

    public static HiAggregateOption e(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(j, j2);
        hiAggregateOption.setType(new int[]{10002});
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.BLOOD_PRESSURE});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("");
        return hiAggregateOption;
    }
}
