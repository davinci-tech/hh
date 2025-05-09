package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class avp extends avr {
    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(final HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_BloodPressureManager", "getRecord listener is null");
        } else if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BloodPressureManager", "getRecord bean is null");
            taskDayDataListener.onDataChange(-1, null);
        } else {
            azp.c(healthLifeBean, (ResponseCallback<HealthLifeBean>) new ResponseCallback() { // from class: avn
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    avp.c(TaskDayDataListener.this, healthLifeBean, i, (HealthLifeBean) obj);
                }
            });
        }
    }

    static /* synthetic */ void c(TaskDayDataListener taskDayDataListener, HealthLifeBean healthLifeBean, int i, HealthLifeBean healthLifeBean2) {
        if (i == -1 || healthLifeBean2 == null) {
            LogUtil.h("HealthLife_BloodPressureManager", "getRecord resultCode ", Integer.valueOf(i), " result ", healthLifeBean2);
            taskDayDataListener.onDataChange(-1, healthLifeBean);
        } else {
            taskDayDataListener.onDataChange(0, healthLifeBean2);
        }
    }

    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(final SparseArray<HealthLifeBean> sparseArray, final TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_BloodPressureManager", "getRecord listener is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_BloodPressureManager", "getRecord sparseArray is null");
            taskDataListener.onDataChange(-1, null);
        } else if (sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_BloodPressureManager", "getRecord sparseArray ", sparseArray);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            azp.mg_(sparseArray, new ResponseCallback() { // from class: avw
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    avp.jZ_(TaskDataListener.this, sparseArray, i, (SparseArray) obj);
                }
            });
        }
    }

    static /* synthetic */ void jZ_(TaskDataListener taskDataListener, SparseArray sparseArray, int i, SparseArray sparseArray2) {
        if (i == -1 || sparseArray2 == null) {
            LogUtil.h("HealthLife_BloodPressureManager", "getRecord resultCode ", Integer.valueOf(i), " result ", sparseArray2);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            taskDataListener.onDataChange(0, sparseArray2);
        }
    }
}
