package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.basichealthmodel.medicine.MedicineRecord;
import com.huawei.basichealthmodel.medicine.MedicinesRule;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class awe extends avr {
    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    /* renamed from: getRecord, reason: merged with bridge method [inline-methods] */
    public void d(final HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getRecord listener is null");
            return;
        }
        if (healthLifeBean == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getRecord bean is null");
            taskDayDataListener.onDataChange(-1, null);
        } else {
            if (HandlerExecutor.c()) {
                azi.d(ThreadPoolManager.d(), new Runnable() { // from class: awd
                    @Override // java.lang.Runnable
                    public final void run() {
                        awe.this.d(healthLifeBean, taskDayDataListener);
                    }
                });
                return;
            }
            int recordDay = healthLifeBean.getRecordDay();
            SparseArray<HealthLifeBean> sparseArray = new SparseArray<>();
            sparseArray.put(recordDay, healthLifeBean);
            kl_(recordDay, recordDay, sparseArray);
            taskDayDataListener.onDataChange(0, sparseArray.get(recordDay));
        }
    }

    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getRecord listener is null");
            return;
        }
        if (sparseArray == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getRecord sparseArray is null");
            taskDataListener.onDataChange(-1, null);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getRecord sparseArray ", sparseArray);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            kl_(sparseArray.keyAt(0), sparseArray.keyAt(size - 1), sparseArray);
            taskDataListener.onDataChange(0, sparseArray);
        }
    }

    private void kl_(int i, int i2, SparseArray<HealthLifeBean> sparseArray) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        int b = DateFormatUtil.b(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        axf.a(i, b, arrayList, countDownLatch);
        SparseArray<Map<Integer, List<MedicinesRule>>> sparseArray2 = new SparseArray<>();
        kj_(i2, countDownLatch, sparseArray, sparseArray2);
        azi.d(countDownLatch, "R_HealthLife_MedicineManager");
        SparseArray<Map<Integer, List<MedicineRecord>>> ki_ = ki_(arrayList);
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            int keyAt = sparseArray.keyAt(i3);
            Map<Integer, List<MedicinesRule>> map = sparseArray2.get(keyAt);
            Map<Integer, List<MedicineRecord>> map2 = ki_.get(keyAt);
            int size = map == null ? 0 : map.size();
            int b2 = b(map, map2);
            HealthLifeBean healthLifeBean = sparseArray.get(keyAt);
            healthLifeBean.setTarget(String.valueOf(size));
            healthLifeBean.setResult(String.valueOf(b2));
            if (healthLifeBean.getComplete() <= 0 && size > 0) {
                healthLifeBean.setComplete(b2 >= size ? 1 : 0);
                if (healthLifeBean.getComplete() == 1 && healthLifeBean.getRecordDay() == b) {
                    aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
                }
            }
            healthLifeBean.setExtendInfo(a(map, map2));
        }
    }

    private String a(Map<Integer, List<MedicinesRule>> map, Map<Integer, List<MedicineRecord>> map2) {
        MedicineRecord medicineRecord;
        List<MedicineRecord> d = d(map);
        if (koq.b(d)) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getExtendInfo initRecordList ", d);
            return "";
        }
        List<MedicineRecord> e = e(map2);
        if (koq.b(e)) {
            return HiJsonUtil.e(d);
        }
        HashMap hashMap = new HashMap();
        for (MedicineRecord medicineRecord2 : d) {
            if (medicineRecord2 != null) {
                hashMap.put(Integer.valueOf(medicineRecord2.getRecordId()), medicineRecord2);
            }
        }
        for (MedicineRecord medicineRecord3 : e) {
            if (medicineRecord3 != null) {
                int recordId = medicineRecord3.getRecordId();
                if (hashMap.containsKey(Integer.valueOf(recordId)) && (medicineRecord = (MedicineRecord) hashMap.get(Integer.valueOf(recordId))) != null) {
                    medicineRecord3.setDrug(medicineRecord.getDrug());
                    medicineRecord3.setName(medicineRecord.getName());
                    medicineRecord3.setMark(medicineRecord.getMark());
                    hashMap.put(Integer.valueOf(recordId), medicineRecord3);
                }
            }
        }
        List<MedicineRecord> e2 = axf.e((Map<Integer, MedicineRecord>) hashMap);
        if (koq.b(e2)) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getExtendInfo list ", e2);
            return HiJsonUtil.e(d);
        }
        return HiJsonUtil.e(e2);
    }

    private List<MedicineRecord> d(Map<Integer, List<MedicinesRule>> map) {
        MedicineRecord b;
        if (map == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getInitRecordList map is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, List<MedicinesRule>> entry : map.entrySet()) {
            if (entry != null) {
                List<MedicinesRule> value = entry.getValue();
                if (!koq.b(value)) {
                    ArrayList arrayList2 = new ArrayList();
                    for (MedicinesRule medicinesRule : value) {
                        if (medicinesRule != null && (b = axq.b(medicinesRule)) != null) {
                            arrayList2.add(b);
                        }
                    }
                    List<MedicineRecord> c = axq.c(arrayList2);
                    if (!koq.b(c)) {
                        for (MedicineRecord medicineRecord : c) {
                            if (medicineRecord != null) {
                                arrayList.add(medicineRecord);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private List<MedicineRecord> e(Map<Integer, List<MedicineRecord>> map) {
        if (map == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineManager", "getRecordList map is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, List<MedicineRecord>> entry : map.entrySet()) {
            if (entry != null) {
                List<MedicineRecord> value = entry.getValue();
                if (!koq.b(value)) {
                    for (MedicineRecord medicineRecord : value) {
                        if (medicineRecord != null) {
                            arrayList.add(medicineRecord);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private void kj_(int i, final CountDownLatch countDownLatch, final SparseArray<HealthLifeBean> sparseArray, final SparseArray<Map<Integer, List<MedicinesRule>>> sparseArray2) {
        axf.a(i, new IBaseResponseCallback() { // from class: awc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                awe.this.km_(sparseArray, sparseArray2, countDownLatch, i2, obj);
            }
        });
    }

    /* synthetic */ void km_(SparseArray sparseArray, SparseArray sparseArray2, CountDownLatch countDownLatch, int i, Object obj) {
        if (i == 0 && (obj instanceof Map)) {
            Map<Integer, List<MedicinesRule>> map = (Map) obj;
            int size = sparseArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                int keyAt = sparseArray.keyAt(i2);
                Map<Integer, List<MedicinesRule>> d = d(keyAt, map);
                if (d.size() > 0) {
                    sparseArray2.put(keyAt, d);
                }
            }
        }
        countDownLatch.countDown();
    }

    private SparseArray<Map<Integer, List<MedicineRecord>>> ki_(List<MedicineRecord> list) {
        SparseArray<Map<Integer, List<MedicineRecord>>> sparseArray = new SparseArray<>();
        for (MedicineRecord medicineRecord : list) {
            if (medicineRecord != null) {
                int b = DateFormatUtil.b(medicineRecord.getStartTime());
                Map<Integer, List<MedicineRecord>> map = sparseArray.get(b);
                if (map == null) {
                    map = new LinkedHashMap<>();
                }
                List<MedicineRecord> list2 = map.get(Integer.valueOf(medicineRecord.getMedicineTime()));
                if (list2 == null) {
                    list2 = new ArrayList<>();
                }
                list2.add(medicineRecord);
                map.put(Integer.valueOf(medicineRecord.getMedicineTime()), list2);
                sparseArray.put(b, map);
            }
        }
        return sparseArray;
    }

    private int b(Map<Integer, List<MedicinesRule>> map, Map<Integer, List<MedicineRecord>> map2) {
        int i = 0;
        if (map != null && map2 != null) {
            for (Map.Entry<Integer, List<MedicinesRule>> entry : map.entrySet()) {
                if (entry != null) {
                    List<MedicinesRule> value = entry.getValue();
                    List<MedicineRecord> list = map2.get(entry.getKey());
                    if (!koq.b(value) && !koq.b(list) && kk_(value, kh_(list))) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    private boolean kk_(List<MedicinesRule> list, SparseArray<MedicineRecord> sparseArray) {
        for (MedicinesRule medicinesRule : list) {
            if (medicinesRule != null) {
                int ruleId = medicinesRule.getRuleId();
                MedicineRecord medicineRecord = sparseArray.get(ruleId);
                if (medicineRecord == null) {
                    medicineRecord = sparseArray.get(ruleId + medicinesRule.getMedicineTime());
                }
                if (medicineRecord == null || medicineRecord.getStatus() <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private SparseArray<MedicineRecord> kh_(List<MedicineRecord> list) {
        SparseArray<MedicineRecord> sparseArray = new SparseArray<>();
        for (MedicineRecord medicineRecord : list) {
            if (medicineRecord != null) {
                sparseArray.put(medicineRecord.getRecordId(), medicineRecord);
            }
        }
        return sparseArray;
    }

    private Map<Integer, List<MedicinesRule>> d(int i, Map<Integer, List<MedicinesRule>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : new ArrayList(map.entrySet())) {
            if (entry != null) {
                List<MedicinesRule> list = (List) entry.getValue();
                ArrayList arrayList = new ArrayList();
                for (MedicinesRule medicinesRule : list) {
                    if (medicinesRule != null && (medicinesRule.getEndTime() <= 0 || medicinesRule.getEndTime() > DateFormatUtil.c(i))) {
                        if (!TextUtils.isEmpty(medicinesRule.getName())) {
                            arrayList.add(medicinesRule);
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    linkedHashMap.put((Integer) entry.getKey(), arrayList);
                }
            }
        }
        return linkedHashMap;
    }
}
