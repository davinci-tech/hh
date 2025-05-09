package defpackage;

import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealthmodel.medicine.MedicineBean;
import com.huawei.basichealthmodel.medicine.MedicineRecord;
import com.huawei.basichealthmodel.medicine.MedicinesRule;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.axf;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class axf {
    private static final int b = DicDataTypeUtil.DataType.MEDICATION_RULE.value();
    private static final int i = DicDataTypeUtil.DataType.MEDICATION_RULE_SOURCE.value();
    private static final int d = DicDataTypeUtil.DataType.MEDICATION_RULE_DRUG.value();
    private static final int g = DicDataTypeUtil.DataType.MEDICATION_RULE_EXPECTED_TIME.value();
    private static final int j = DicDataTypeUtil.DataType.MEDICATION_RULE_ID.value();
    private static final int o = DicDataTypeUtil.DataType.MEDICATION_RULE_TYPE.value();
    private static final int k = DicDataTypeUtil.DataType.MEDICATION_RULE_START_TIME.value();

    /* renamed from: a, reason: collision with root package name */
    private static final int f272a = DicDataTypeUtil.DataType.MEDICATION_RULE_END_TIME.value();
    private static final int f = DicDataTypeUtil.DataType.MEDICATION_RULE_NAME.value();
    private static final int h = DicDataTypeUtil.DataType.MEDICATION_RULE_MARK.value();
    private static final String[] e = {"punchSource", "punchTaskId", "punchStatus", "punchDrug", "punchExpectedTime", "punchTime"};
    private static final int[] c = {DicDataTypeUtil.DataType.MEDICATION_PUNCH_SOURCE.value(), DicDataTypeUtil.DataType.MEDICATION_PUNCH_TASK_ID.value(), DicDataTypeUtil.DataType.MEDICATION_PUNCH_STATUS.value(), DicDataTypeUtil.DataType.MEDICATION_PUNCH_DRUG.value(), DicDataTypeUtil.DataType.MEDICATION_PUNCH_EXPECTED_TIME.value(), DicDataTypeUtil.DataType.MEDICATION_PUNCH_TIME.value()};
    private static final Map<String, Long> l = new HashMap(16);

    public static void d(List<MedicineRecord> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HealthLife_MedicationManager", "insertMedicineRecord callback is null");
            return;
        }
        if (koq.b(list)) {
            iBaseResponseCallback.d(-1, "");
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(c(list));
        LogUtil.a("HealthLife_MedicationManager", "insertMedicineRecord list ", list.toString(), " option ", hiDataInsertOption);
        HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: axi
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                axf.d(IBaseResponseCallback.this, i2, obj);
            }
        });
    }

    static /* synthetic */ void d(IBaseResponseCallback iBaseResponseCallback, int i2, Object obj) {
        iBaseResponseCallback.d(i2, obj);
        LogUtil.a("HealthLife_MedicationManager", "insertMedicineRecord errorCode ", Integer.valueOf(i2), " object ", obj);
    }

    public static void a(final List<MedicinesRule> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: axk
                @Override // java.lang.Runnable
                public final void run() {
                    axf.a((List<MedicinesRule>) list, iBaseResponseCallback);
                }
            });
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        e eVar = new e(1);
        e(list, countDownLatch, eVar);
        azi.d(countDownLatch, "addMedicineInfo");
        if (eVar.d()) {
            iBaseResponseCallback.d(0, "");
        } else {
            iBaseResponseCallback.d(-1, "addMedicineInfo error");
        }
    }

    private static void f(List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HealthLife_MedicationManager", "updateMedicineRule healthDataList ", list, " callback ", iBaseResponseCallback);
        final HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(list);
        HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: axn
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                axf.d(HiDataInsertOption.this, iBaseResponseCallback, i2, obj);
            }
        });
    }

    static /* synthetic */ void d(HiDataInsertOption hiDataInsertOption, IBaseResponseCallback iBaseResponseCallback, int i2, Object obj) {
        LogUtil.a("HealthLife_MedicationManager", "updateMedicineRule insertOption ", hiDataInsertOption, " type ", Integer.valueOf(i2), " object ", obj);
        iBaseResponseCallback.d(i2, "");
    }

    private static void c(List<HiHealthData> list, final CountDownLatch countDownLatch, final e eVar) {
        LogUtil.a("HealthLife_MedicationManager", "updateMedicineRule healthDataList ", list, " countDownLatch ", countDownLatch, " countFlag ", eVar);
        if (koq.b(list)) {
            LogUtil.a("HealthLife_MedicationManager", "updateMedicineRule healthDataList isEmpty");
            eVar.b();
            countDownLatch.countDown();
        } else {
            final HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(list);
            HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: axj
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i2, Object obj) {
                    axf.e(axf.e.this, countDownLatch, hiDataInsertOption, i2, obj);
                }
            });
        }
    }

    static /* synthetic */ void e(e eVar, CountDownLatch countDownLatch, HiDataInsertOption hiDataInsertOption, int i2, Object obj) {
        if (i2 == 0) {
            eVar.b();
        }
        countDownLatch.countDown();
        LogUtil.a("HealthLife_MedicationManager", "updateMedicineRule insertOption ", hiDataInsertOption, " type ", Integer.valueOf(i2), " object ", obj);
    }

    private static void e(List<MedicinesRule> list, final CountDownLatch countDownLatch, final e eVar) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_MedicationManager", "addMedicineRule medicinesRuleBeans is empty");
            eVar.b();
            countDownLatch.countDown();
        } else {
            final HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(b(list, false));
            HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: axe
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i2, Object obj) {
                    axf.c(axf.e.this, countDownLatch, hiDataInsertOption, i2, obj);
                }
            });
        }
    }

    static /* synthetic */ void c(e eVar, CountDownLatch countDownLatch, HiDataInsertOption hiDataInsertOption, int i2, Object obj) {
        if (i2 == 0) {
            eVar.b();
        }
        countDownLatch.countDown();
        LogUtil.a("HealthLife_MedicationManager", "addMedicineRule insertOption ", hiDataInsertOption, " type ", Integer.valueOf(i2), " object ", obj);
    }

    public static void a(List<MedicinesRule> list, final List<MedicinesRule> list2, final IBaseResponseCallback iBaseResponseCallback) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_MedicationManager", "updateSaveMedicinesRule medicinesRuleBeans is empty");
            if (koq.b(list2)) {
                iBaseResponseCallback.d(0, "");
                return;
            } else {
                f(b(list2, true), iBaseResponseCallback);
                return;
            }
        }
        final HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(b(list, false));
        HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: axl
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                axf.a(list2, iBaseResponseCallback, hiDataInsertOption, i2, obj);
            }
        });
    }

    static /* synthetic */ void a(List list, IBaseResponseCallback iBaseResponseCallback, HiDataInsertOption hiDataInsertOption, int i2, Object obj) {
        if (i2 == 0) {
            if (koq.b(list)) {
                iBaseResponseCallback.d(0, "");
            } else {
                f(b((List<MedicinesRule>) list, true), iBaseResponseCallback);
            }
        } else {
            iBaseResponseCallback.d(-1, "");
        }
        LogUtil.a("HealthLife_MedicationManager", "updateSaveMedicinesRule insertOption ", hiDataInsertOption, " type ", Integer.valueOf(i2), " object ", obj);
    }

    private static List<HiHealthData> b(List<MedicinesRule> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < list.size(); i2++) {
            MedicinesRule medicinesRule = list.get(i2);
            if (medicinesRule != null) {
                long j2 = i2 + currentTimeMillis;
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setType(b);
                HashMap hashMap = new HashMap();
                String name = medicinesRule.getName();
                String mark = medicinesRule.getMark();
                int ruleId = medicinesRule.getRuleId();
                if (ruleId <= 0 && !TextUtils.isEmpty(name)) {
                    ruleId = axq.b(name, mark, j2);
                    medicinesRule.setRuleId(ruleId);
                }
                hashMap.put(Integer.valueOf(j), Integer.valueOf(ruleId));
                hashMap.put(Integer.valueOf(d), Integer.valueOf(ruleId));
                int source = medicinesRule.getSource();
                if (source <= 0) {
                    source = 1;
                }
                hashMap.put(Integer.valueOf(i), Integer.valueOf(source));
                hashMap.put(Integer.valueOf(g), Integer.valueOf(medicinesRule.getMedicineTime()));
                int ruleType = medicinesRule.getRuleType();
                hashMap.put(Integer.valueOf(o), Integer.valueOf(ruleType > 0 ? ruleType : 1));
                long startTime = medicinesRule.getStartTime();
                if (startTime <= 0) {
                    startTime = jdl.t(j2);
                }
                hashMap.put(Integer.valueOf(k), Long.valueOf(startTime));
                HashMap hashMap2 = new HashMap();
                hashMap2.put(Integer.valueOf(f), name);
                hashMap2.put(Integer.valueOf(h), mark);
                hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
                if (z) {
                    hashMap.put(Integer.valueOf(f272a), Long.valueOf(jdl.t(j2)));
                    Map<String, Long> map = l;
                    long longValue = map.containsKey(axq.e(ruleId)) ? map.get(axq.e(ruleId)).longValue() : j2;
                    if (map.containsKey(axq.b(ruleId))) {
                        j2 = map.get(axq.b(ruleId)).longValue();
                    }
                    hiHealthData.setStartTime(longValue);
                    hiHealthData.setEndTime(j2);
                } else {
                    hashMap.put(Integer.valueOf(f272a), Long.valueOf(medicinesRule.getEndTime()));
                    hiHealthData.setEndTime(j2);
                    hiHealthData.setStartTime(j2);
                    b(ruleId, hiHealthData);
                }
                hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
                hiHealthData.setDeviceUuid(String.valueOf(-1));
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.c("HealthLife_MedicationManager", "generatedHiHealthRuleData sStartAndEndTime is", l.toString());
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<HiHealthData> d(List<MedicinesRule> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (int i2 = 0; i2 < list.size(); i2++) {
            MedicinesRule medicinesRule = list.get(i2);
            if (medicinesRule != null) {
                long j2 = i2 + currentTimeMillis;
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setType(b);
                HashMap hashMap = new HashMap();
                String name = medicinesRule.getName();
                String mark = medicinesRule.getMark();
                int ruleId = medicinesRule.getRuleId();
                if (ruleId <= 0 && !TextUtils.isEmpty(name)) {
                    ruleId = axq.b(name, mark, j2);
                }
                hashMap.put(Integer.valueOf(j), Integer.valueOf(ruleId));
                hashMap.put(Integer.valueOf(d), Integer.valueOf(ruleId));
                int source = medicinesRule.getSource();
                if (source <= 0) {
                    source = 1;
                }
                hashMap.put(Integer.valueOf(i), Integer.valueOf(source));
                hashMap.put(Integer.valueOf(g), Integer.valueOf(medicinesRule.getMedicineTime()));
                int ruleType = medicinesRule.getRuleType();
                hashMap.put(Integer.valueOf(o), Integer.valueOf(ruleType > 0 ? ruleType : 1));
                long startTime = medicinesRule.getStartTime();
                if (startTime <= 0) {
                    startTime = jdl.t(j2);
                }
                hashMap.put(Integer.valueOf(k), Long.valueOf(startTime));
                HashMap hashMap2 = new HashMap();
                hashMap2.put(Integer.valueOf(f), name);
                hashMap2.put(Integer.valueOf(h), mark);
                hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
                Map<String, Long> map = l;
                long longValue = map.containsKey(axq.e(ruleId)) ? map.get(axq.e(ruleId)).longValue() : j2;
                if (map.containsKey(axq.b(ruleId))) {
                    j2 = map.get(axq.b(ruleId)).longValue();
                }
                hiHealthData.setStartTime(longValue);
                hiHealthData.setEndTime(j2);
                hashMap.put(Integer.valueOf(f272a), Long.valueOf(medicinesRule.getEndTime()));
                hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
                hiHealthData.setDeviceUuid(String.valueOf(-1));
                arrayList.add(hiHealthData);
                LogUtil.a("HealthLife_MedicationManager", "generatedHiHealthRuleData healthDataList ", hiHealthData.toString());
            }
        }
        return arrayList;
    }

    private static List<HiHealthData> c(List<MedicineRecord> list) {
        ArrayList arrayList = new ArrayList();
        for (MedicineRecord medicineRecord : list) {
            if (medicineRecord != null) {
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setType(DicDataTypeUtil.DataType.MEDICATION_PUNCHING.value());
                HashMap hashMap = new HashMap();
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICATION_PUNCH_SOURCE.value()), Integer.valueOf(medicineRecord.getSource()));
                int recordId = medicineRecord.getRecordId();
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICATION_PUNCH_TASK_ID.value()), Integer.valueOf(recordId));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICATION_PUNCH_STATUS.value()), Integer.valueOf(medicineRecord.getStatus()));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICATION_PUNCH_EXPECTED_TIME.value()), Integer.valueOf(medicineRecord.getMedicineTime()));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICATION_PUNCH_TIME.value()), Long.valueOf(medicineRecord.getRecordTime()));
                int drug = medicineRecord.getDrug();
                if (drug > 0) {
                    recordId = drug;
                }
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MEDICATION_PUNCH_DRUG.value()), Integer.valueOf(recordId));
                hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
                hiHealthData.setDeviceUuid(String.valueOf(-1));
                long currentTimeMillis = System.currentTimeMillis();
                long startTime = medicineRecord.getStartTime();
                long endTime = medicineRecord.getEndTime();
                if (startTime <= 0 || endTime <= 0) {
                    hiHealthData.setStartTime(currentTimeMillis);
                    hiHealthData.setEndTime(currentTimeMillis);
                } else {
                    hiHealthData.setStartTime(startTime);
                    hiHealthData.setEndTime(endTime);
                }
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    public static void e(List<MedicinesRule> list, IBaseResponseCallback iBaseResponseCallback) {
        if (koq.b(list)) {
            LogUtil.b("HealthLife_MedicationManager", "deleteMedicineRule is empty");
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.b("HealthLife_MedicationManager", "deleteMedicineRule callback is null");
            return;
        }
        List<HiHealthData> b2 = b(list, true);
        if (koq.b(b2)) {
            LogUtil.b("HealthLife_MedicationManager", "changeHiHealData is empty");
        } else {
            f(b2, iBaseResponseCallback);
        }
    }

    public static void a(final int i2, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HealthLife_MedicationManager", "getMedicineRulesInfo callback is null endDate ", Integer.valueOf(i2));
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(String.valueOf(20140101), String.valueOf(i2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(new int[]{b});
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).b(true).e(), new HiDataReadResultListener() { // from class: axf.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                LogUtil.a("HealthLife_MedicationManager", "getMedicineRulesInfo object ", obj, " errorCode ", Integer.valueOf(i3), " anchor ", Integer.valueOf(i4));
                if (obj instanceof SparseArray) {
                    Object obj2 = ((SparseArray) obj).get(axf.b);
                    if (koq.e(obj2, HiHealthData.class)) {
                        List b2 = axf.b((List<HiHealthData>) obj2, i2);
                        LogUtil.a("HealthLife_MedicationManager", "getMedicineRulesInfo ruleList ", b2);
                        if (!koq.b(b2)) {
                            axf.g(b2, IBaseResponseCallback.this);
                            return;
                        } else {
                            IBaseResponseCallback.this.d(-1, new HashMap());
                            return;
                        }
                    }
                    IBaseResponseCallback.this.d(-1, new HashMap());
                    return;
                }
                IBaseResponseCallback.this.d(-1, new HashMap());
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
                LogUtil.a("HealthLife_MedicationManager", "getMedicineRulesInfo intentType ", Integer.valueOf(i3), " object ", obj, " errorCode ", Integer.valueOf(i4), " anchor ", Integer.valueOf(i5));
                IBaseResponseCallback.this.d(-1, new HashMap());
            }
        });
    }

    private static void a(List<HiHealthData> list) {
        if (koq.b(list)) {
            return;
        }
        Collections.sort(list, new Comparator() { // from class: axg
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(r4 != null ? ((HiHealthData) obj).getStartTime() : 0L, r5 != null ? ((HiHealthData) obj2).getStartTime() : 0L);
                return compare;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<MedicinesRule> b(List<HiHealthData> list, int i2) {
        long e2;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_MedicationManager", "getRuleList list ", list);
            return new ArrayList();
        }
        a(list);
        ArrayList arrayList = new ArrayList();
        l.clear();
        long c2 = DateFormatUtil.c(i2);
        if (jdl.ac(c2)) {
            e2 = System.currentTimeMillis();
        } else {
            e2 = jdl.e(c2);
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                MedicinesRule b2 = b(hiHealthData);
                if (axq.e(b2)) {
                    long endTime = b2.getEndTime();
                    if (endTime == 0 || endTime >= e2) {
                        arrayList.add(b2);
                        b(b2.getRuleId(), hiHealthData);
                    }
                }
            }
        }
        return arrayList;
    }

    private static Map<String, Object> c(Object obj) {
        if (obj instanceof String) {
            try {
                Map<String, Object> map = (Map) HiJsonUtil.b((String) obj, new TypeToken<HashMap<String, Object>>() { // from class: axf.5
                }.getType());
                if (map != null) {
                    return map;
                }
                LogUtil.h("HealthLife_MedicationManager", "getMap map is null");
                return new HashMap();
            } catch (JsonParseException | IllegalStateException e2) {
                LogUtil.b("HealthLife_MedicationManager", "getMap exception ", LogAnonymous.b(e2));
                return new HashMap();
            }
        }
        return new HashMap();
    }

    private static int d(Object obj) {
        Object obj2 = c(obj).get("point_value");
        if (obj2 instanceof Double) {
            return Double.valueOf(((Double) obj2).doubleValue()).intValue();
        }
        LogUtil.h("HealthLife_MedicationManager", "getInt object ", obj, " pointValue ", obj2);
        return -1;
    }

    private static long e(Object obj) {
        Object obj2 = c(obj).get("point_value");
        if (obj2 instanceof Double) {
            return Double.valueOf(((Double) obj2).doubleValue()).longValue();
        }
        LogUtil.h("HealthLife_MedicationManager", "getLong object ", obj, " pointValue ", obj2);
        return -1L;
    }

    private static String b(Object obj) {
        Object obj2 = c(obj).get("metadata");
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        LogUtil.h("HealthLife_MedicationManager", "getString object ", obj, " metadata ", obj2);
        return "";
    }

    private static MedicinesRule b(HiHealthData hiHealthData) {
        MedicinesRule medicinesRule = new MedicinesRule();
        String b2 = b(hiHealthData.get(String.valueOf(f)));
        if (!TextUtils.isEmpty(b2)) {
            medicinesRule.setName(b2);
        }
        String b3 = b(hiHealthData.get(String.valueOf(h)));
        if (!TextUtils.isEmpty(b3)) {
            medicinesRule.setMark(b3);
        }
        medicinesRule.setDrug(d(hiHealthData.get(String.valueOf(d))));
        medicinesRule.setRuleId(d(hiHealthData.get(String.valueOf(j))));
        medicinesRule.setSource(d(hiHealthData.get(String.valueOf(i))));
        medicinesRule.setRuleType(d(hiHealthData.get(String.valueOf(o))));
        medicinesRule.setEndTime(e(hiHealthData.get(String.valueOf(f272a))));
        medicinesRule.setStartTime(e(hiHealthData.get(String.valueOf(k))));
        medicinesRule.setMedicineTime(d(hiHealthData.get(String.valueOf(g))));
        return medicinesRule;
    }

    public static SparseArray<ArrayList<MedicineRecord>> kG_(List<MedicineRecord> list) {
        int medicineTime;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_MedicationManager", "convertSparseArray beanList isEmpty");
            return new SparseArray<>();
        }
        SparseArray<ArrayList<MedicineRecord>> sparseArray = new SparseArray<>();
        for (MedicineRecord medicineRecord : list) {
            if (medicineRecord != null && (medicineTime = medicineRecord.getMedicineTime()) >= 0) {
                if (sparseArray.indexOfKey(medicineTime) >= 0) {
                    ArrayList<MedicineRecord> arrayList = sparseArray.get(medicineTime);
                    if (arrayList != null) {
                        arrayList.add(medicineRecord);
                    }
                } else {
                    ArrayList<MedicineRecord> arrayList2 = new ArrayList<>();
                    arrayList2.add(medicineRecord);
                    sparseArray.append(medicineTime, arrayList2);
                }
            }
        }
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<MedicineRecord> valueAt = sparseArray.valueAt(i2);
            if (!koq.b(valueAt)) {
                Collections.sort(valueAt, new Comparator() { // from class: axh
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return axf.b((MedicineRecord) obj, (MedicineRecord) obj2);
                    }
                });
            }
        }
        return sparseArray;
    }

    static /* synthetic */ int b(MedicineRecord medicineRecord, MedicineRecord medicineRecord2) {
        return (int) (medicineRecord.getStartTime() - medicineRecord2.getStartTime());
    }

    public static List<MedicineRecord> e(Map<Integer, MedicineRecord> map) {
        ArrayList arrayList = new ArrayList(map.values());
        Collections.sort(arrayList, new Comparator() { // from class: axo
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return axf.a((MedicineRecord) obj, (MedicineRecord) obj2);
            }
        });
        LogUtil.c("HealthLife_MedicationManager", "groupRecordData ", arrayList.toString());
        return arrayList;
    }

    static /* synthetic */ int a(MedicineRecord medicineRecord, MedicineRecord medicineRecord2) {
        int medicineTime = medicineRecord.getMedicineTime() - medicineRecord2.getMedicineTime();
        if (medicineTime > 0) {
            return 1;
        }
        return medicineTime < 0 ? -1 : 0;
    }

    private static void b(int i2, HiHealthData hiHealthData) {
        Map<String, Long> map = l;
        map.put(axq.e(i2), Long.valueOf(hiHealthData.getStartTime()));
        map.put(axq.b(i2), Long.valueOf(hiHealthData.getEndTime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(List<HiHealthData> list, List<MedicineRecord> list2) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                MedicineRecord medicineRecord = new MedicineRecord();
                medicineRecord.setDrug(hiHealthData.getInt("punchDrug"));
                medicineRecord.setMedicineTime(hiHealthData.getInt("punchExpectedTime"));
                medicineRecord.setRecordId(hiHealthData.getInt("punchTaskId"));
                medicineRecord.setRecordTime(hiHealthData.getLong("punchTime"));
                int i2 = hiHealthData.getInt("punchSource");
                if (i2 != 1) {
                    i2 = 1;
                }
                medicineRecord.setSource(i2);
                medicineRecord.setStatus(hiHealthData.getInt("punchStatus"));
                medicineRecord.setStartTime(hiHealthData.getStartTime());
                medicineRecord.setEndTime(hiHealthData.getEndTime());
                list2.add(medicineRecord);
            }
        }
    }

    public static void a(int i2, int i3, final List<MedicineRecord> list, final CountDownLatch countDownLatch) {
        if (list == null) {
            return;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(String.valueOf(i2), String.valueOf(i3), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(c);
        hiAggregateOption.setConstantsKey(e);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(0);
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: axf.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list2, int i4, int i5) {
                if (koq.b(list2)) {
                    LogUtil.b("HealthLife_MedicationManager", "data is empty");
                    countDownLatch.countDown();
                } else {
                    LogUtil.a("HealthLife_MedicationManager", "getMedicineRecord datas", list2.toString());
                    axf.d(list2, (List<MedicineRecord>) list);
                    countDownLatch.countDown();
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i4, List<HiHealthData> list2, int i5, int i6) {
                LogUtil.h("HealthLife_MedicationManager", "getMedicineRecord errorCode:" + i5);
                countDownLatch.countDown();
            }
        });
    }

    public static void b(List<MedicinesRule> list, List<MedicinesRule> list2, List<Integer> list3, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.b("HealthLife_MedicationManager", "updateMedicine callback is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        c(list, list2, arrayList, list3, arrayList2);
        e eVar = new e(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        c(d((List<MedicinesRule>) arrayList), countDownLatch, eVar);
        azi.d(countDownLatch, "updateMedicine");
        if (!eVar.d()) {
            iBaseResponseCallback.d(-1, "updateMedicine recordChangeRule is err");
            return;
        }
        if (z) {
            LogUtil.b("HealthLife_MedicationManager", "updateMedicine changeTime is true updateSaveMedicinesRule");
            long currentTimeMillis = System.currentTimeMillis();
            for (MedicinesRule medicinesRule : list) {
                if (medicinesRule != null) {
                    currentTimeMillis++;
                    medicinesRule.setRuleId(axq.b(medicinesRule.getName(), medicinesRule.getMark(), currentTimeMillis));
                }
            }
            a(list, list2, iBaseResponseCallback);
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        b(list, list2, arrayList3);
        a(arrayList2, arrayList3, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(final List<MedicinesRule> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: axm
                @Override // java.lang.Runnable
                public final void run() {
                    axf.g(list, iBaseResponseCallback);
                }
            });
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (MedicinesRule medicinesRule : list) {
            if (medicinesRule != null) {
                if (TextUtils.isEmpty(medicinesRule.getName())) {
                    String c2 = axq.c(medicinesRule.getDrug());
                    HiUserPreference c3 = azi.c(c2);
                    if (c3 == null || TextUtils.isEmpty(c3.getValue())) {
                        LogUtil.h("HealthLife_MedicationManager", "getMedicine hiUserPreference medicineId ", c2);
                    } else {
                        try {
                            MedicineBean medicineBean = (MedicineBean) HiJsonUtil.e(c3.getValue(), MedicineBean.class);
                            medicinesRule.setName(medicineBean.getName());
                            medicinesRule.setMark(medicineBean.getRemarks());
                            medicinesRule.setDrug(medicineBean.getId());
                            medicinesRule.setSource(medicineBean.getSource());
                        } catch (JsonParseException e2) {
                            LogUtil.b("HealthLife_MedicationManager", "getMedicine exception ", LogAnonymous.b((Throwable) e2));
                        }
                    }
                }
                if (!TextUtils.isEmpty(medicinesRule.getName())) {
                    ArrayList arrayList = new ArrayList();
                    int medicineTime = medicinesRule.getMedicineTime();
                    if (linkedHashMap.containsKey(Integer.valueOf(medicineTime))) {
                        List list2 = (List) linkedHashMap.get(Integer.valueOf(medicineTime));
                        if (list2 != null) {
                            list2.add(medicinesRule);
                        }
                    } else {
                        arrayList.add(medicinesRule);
                        linkedHashMap.put(Integer.valueOf(medicineTime), arrayList);
                    }
                }
            }
        }
        if (koq.b(linkedHashMap.entrySet())) {
            iBaseResponseCallback.d(-1, null);
        } else {
            iBaseResponseCallback.d(0, a(linkedHashMap));
        }
    }

    public static Map<Integer, List<MedicinesRule>> a(Map<Integer, List<MedicinesRule>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (map != null && !koq.b(map.entrySet())) {
            if (map.entrySet().size() == 1) {
                return map;
            }
            ArrayList<Map.Entry> arrayList = new ArrayList(map.entrySet());
            Collections.sort(arrayList, new Comparator() { // from class: axc
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compareTo;
                    compareTo = ((Integer) ((Map.Entry) obj).getKey()).compareTo((Integer) ((Map.Entry) obj2).getKey());
                    return compareTo;
                }
            });
            for (Map.Entry entry : arrayList) {
                linkedHashMap.put((Integer) entry.getKey(), (List) entry.getValue());
            }
        }
        return linkedHashMap;
    }

    public static void c(List<MedicinesRule> list, List<MedicinesRule> list2, List<MedicinesRule> list3, List<Integer> list4, List<MedicinesRule> list5) {
        for (MedicinesRule medicinesRule : list) {
            if (medicinesRule != null) {
                if (list4.contains(Integer.valueOf(medicinesRule.getRuleId()))) {
                    MedicinesRule medicinesRule2 = list2.get(list4.indexOf(Integer.valueOf(medicinesRule.getRuleId())));
                    if (medicinesRule2 != null && b(medicinesRule, medicinesRule2)) {
                        medicinesRule.setRuleId(medicinesRule2.getRuleId());
                        list3.add(medicinesRule);
                    }
                } else {
                    list5.add(medicinesRule);
                }
            }
        }
    }

    public static void b(List<MedicinesRule> list, List<MedicinesRule> list2, List<MedicinesRule> list3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MedicinesRule> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().getRuleId()));
        }
        for (MedicinesRule medicinesRule : list2) {
            if (!arrayList.contains(Integer.valueOf(medicinesRule.getRuleId()))) {
                list3.add(medicinesRule);
            }
        }
    }

    private static boolean b(MedicinesRule medicinesRule, MedicinesRule medicinesRule2) {
        if (medicinesRule == null || medicinesRule2 == null) {
            return false;
        }
        if (!medicinesRule.getName().equals(medicinesRule2.getName())) {
            return true;
        }
        String mark = medicinesRule.getMark();
        if (TextUtils.isEmpty(mark)) {
            mark = "";
        }
        return !mark.equals(TextUtils.isEmpty(medicinesRule2.getMark()) ? "" : r4);
    }

    public static List<Calendar> d(Map<Integer, List<MedicinesRule>> map, boolean z, Map<Integer, ayh> map2) {
        ArrayList arrayList = new ArrayList();
        if (map == null || map.size() == 0) {
            LogUtil.h("HealthLife_MedicationManager", "getMedicinesCalendars medicationMap null");
            return arrayList;
        }
        if (map2 == null || map2.size() == 0) {
            LogUtil.h("HealthLife_MedicationManager", "getMedicinesCalendars medicationMap null");
            return arrayList;
        }
        long t = jdl.t(System.currentTimeMillis());
        ArrayList<Map.Entry> arrayList2 = new ArrayList(map.entrySet());
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry : arrayList2) {
            Calendar calendar = Calendar.getInstance();
            wq wqVar = new wq();
            calendar.setTimeInMillis((((Integer) entry.getKey()).intValue() * 1000) + t);
            wqVar.a(calendar.get(11));
            wqVar.c(calendar.get(12));
            wqVar.b(127);
            arrayList.add(calendar);
            arrayList3.add(wqVar);
        }
        LogUtil.a("HealthLife_MedicationManager", "getMedicinesCalendars calendars ", arrayList);
        if (z) {
            ayh c2 = bby.c(map2, 11);
            c2.d(arrayList3);
            map2.put(11, c2);
            bby.d(map2);
        }
        return arrayList;
    }

    /* loaded from: classes8.dex */
    static final class e {
        private int c;
        private int d = 0;

        e(int i) {
            this.c = i;
        }

        public void b() {
            this.d++;
        }

        public boolean d() {
            return this.c == this.d;
        }
    }

    public static void e() {
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(String.valueOf(20140101), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(new int[]{b});
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).b(true).e(), new AnonymousClass4(b2));
    }

    /* renamed from: axf$4, reason: invalid class name */
    class AnonymousClass4 implements HiDataReadResultListener {
        final /* synthetic */ int c;

        AnonymousClass4(int i) {
            this.c = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("HealthLife_MedicationManager", "refreshDoctorRule object ", obj, " errorCode ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
            if (obj instanceof SparseArray) {
                Object obj2 = ((SparseArray) obj).get(axf.b);
                if (koq.e(obj2, HiHealthData.class)) {
                    List<MedicinesRule> b = axf.b((List<HiHealthData>) obj2, this.c);
                    LogUtil.a("HealthLife_MedicationManager", "refreshDoctorRule ruleList ", b);
                    if (koq.b(b)) {
                        return;
                    }
                    for (MedicinesRule medicinesRule : b) {
                        if (medicinesRule != null && medicinesRule.getSource() == 3) {
                            medicinesRule.setSource(1);
                        }
                    }
                    final HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
                    hiDataInsertOption.setDatas(axf.d((List<MedicinesRule>) b));
                    HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: axp
                        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                        public final void onResult(int i3, Object obj3) {
                            LogUtil.a("HealthLife_MedicationManager", "refreshDoctorRule insertOption ", HiDataInsertOption.this, " type ", Integer.valueOf(i3), " resultObject ", obj3);
                        }
                    });
                }
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("HealthLife_MedicationManager", "refreshDoctorRule intentType ", Integer.valueOf(i), " object ", obj, " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3));
        }
    }
}
