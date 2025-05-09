package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Objects;

/* loaded from: classes3.dex */
public class awf extends avr {
    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_IntensityManager", "getRecord listener is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_IntensityManager", "getRecord sparseArray is null");
            taskDataListener.onDataChange(-1, null);
        } else if (sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_IntensityManager", "getRecord sparseArray ", sparseArray);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            Objects.requireNonNull(taskDataListener);
            bbm.mJ_(sparseArray, new avt(taskDataListener));
        }
    }
}
