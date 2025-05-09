package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class avu extends avr {
    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_DietManager", "getRecord listener is null");
            return;
        }
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_DietManager", "getRecord bean is null");
            taskDayDataListener.onDataChange(-1, null);
        } else {
            final int recordDay = healthLifeBean.getRecordDay();
            final SparseArray<HealthLifeBean> sparseArray = new SparseArray<>();
            sparseArray.append(recordDay, healthLifeBean);
            kc_(recordDay, recordDay, sparseArray, new ResponseCallback() { // from class: awa
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    TaskDayDataListener.this.onDataChange(i, (HealthLifeBean) sparseArray.get(recordDay));
                }
            });
        }
    }

    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(final SparseArray<HealthLifeBean> sparseArray, final TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_DietManager", "getRecord listener is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_DietManager", "getRecord sparseArray is null");
            taskDataListener.onDataChange(-1, null);
        } else if (sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_DietManager", "getRecord sparseArray ", sparseArray);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            kc_(sparseArray.keyAt(0), sparseArray.keyAt(sparseArray.size() - 1), sparseArray, new ResponseCallback() { // from class: avx
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    TaskDataListener.this.onDataChange(i, sparseArray);
                }
            });
        }
    }

    private void kc_(int i, int i2, final SparseArray<HealthLifeBean> sparseArray, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        grz.e(i, i2, (ResponseCallback<List<quh>>) new ResponseCallback() { // from class: avy
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i3, Object obj) {
                avu.this.ke_(sparseArray, responseCallback, i3, (List) obj);
            }
        });
    }

    /* synthetic */ void ke_(SparseArray sparseArray, ResponseCallback responseCallback, int i, List list) {
        if (koq.c(list)) {
            HashMap hashMap = new HashMap();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                quh quhVar = (quh) it.next();
                if (quhVar != null) {
                    hashMap.put(Integer.valueOf(quhVar.c()), quhVar);
                }
            }
            if (hashMap.size() > 0) {
                kd_(hashMap, sparseArray);
            }
        }
        responseCallback.onResponse(i, sparseArray);
    }

    private void kd_(Map<Integer, quh> map, SparseArray<HealthLifeBean> sparseArray) {
        quh quhVar;
        int h;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            HealthLifeBean healthLifeBean = sparseArray.get(keyAt);
            if (healthLifeBean != null && map.containsKey(Integer.valueOf(keyAt)) && (quhVar = map.get(Integer.valueOf(keyAt))) != null) {
                List<qul> a2 = quhVar.a();
                if (!koq.b(a2)) {
                    int i2 = 0;
                    for (qul qulVar : a2) {
                        if (qulVar != null && ((h = qulVar.h()) == 10 || h == 20 || h == 30)) {
                            if (qulVar.b() > 0.0f) {
                                i2++;
                            }
                        }
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if (healthLifeBean.getComplete() <= 0 && i2 >= 3) {
                        healthLifeBean.setComplete(1);
                        if (healthLifeBean.getRecordDay() == DateFormatUtil.b(currentTimeMillis)) {
                            aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
                        }
                    }
                    healthLifeBean.setIsUpdated(true);
                    healthLifeBean.setResult(String.valueOf(i2));
                    healthLifeBean.setTimestamp(currentTimeMillis);
                    healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
                    healthLifeBean.setSyncStatus(0);
                }
            }
        }
    }
}
