package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Objects;

/* loaded from: classes3.dex */
public class avv extends avr {
    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(final HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_BreathManager", "getRecord listener is null");
        } else if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BreathManager", "getRecord bean is null");
            taskDayDataListener.onDataChange(-1, null);
        } else {
            bbr.e(healthLifeBean, new ResponseCallback() { // from class: avs
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    TaskDayDataListener.this.onDataChange(0, healthLifeBean);
                }
            });
        }
    }

    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_BreathManager", "getRecord listener is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_BreathManager", "getRecord sparseArray is null");
            taskDataListener.onDataChange(-1, null);
        } else if (sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_BreathManager", "getRecord sparseArray ", sparseArray);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            Objects.requireNonNull(taskDataListener);
            azt.mp_(sparseArray, new avt(taskDataListener));
        }
    }
}
