package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class awy extends avr {
    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_WakeUpManager", "getRecord listener is null");
        } else if (healthLifeBean == null) {
            LogUtil.h("HealthLife_WakeUpManager", "getRecord bean is null");
            taskDayDataListener.onDataChange(-1, null);
        } else {
            bcg.b(healthLifeBean, taskDayDataListener);
        }
    }

    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_WakeUpManager", "getRecord listener is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_WakeUpManager", "getRecord sparseArray is null");
            taskDataListener.onDataChange(-1, null);
        } else if (sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_WakeUpManager", "getRecord sparseArray ", sparseArray);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            bcg.nb_(sparseArray, taskDataListener);
        }
    }
}
