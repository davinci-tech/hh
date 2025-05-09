package defpackage;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kwy;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class aww extends avr {
    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(final HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_WorkoutManager", "getRecord listener is null");
            return;
        }
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_WorkoutManager", "getRecord bean is null");
            taskDayDataListener.onDataChange(-1, null);
            return;
        }
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("HealthLife_WorkoutManager", "getRecord api is null");
            taskDayDataListener.onDataChange(-1, healthLifeBean);
        } else {
            recordApi.acquireSummaryFitnessRecord(new kwy.a().a(azi.c(healthLifeBean)).e(azi.b(healthLifeBean)).d(), new IBaseResponseCallback() { // from class: axb
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    aww.b(TaskDayDataListener.this, healthLifeBean, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void b(TaskDayDataListener taskDayDataListener, HealthLifeBean healthLifeBean, int i, Object obj) {
        LogUtil.a("HealthLife_WorkoutManager", "getRecord errorCode ", Integer.valueOf(i), " object ", obj);
        if (!koq.e(obj, FitnessTrackRecord.class)) {
            taskDayDataListener.onDataChange(-1, healthLifeBean);
            return;
        }
        List list = (List) obj;
        if (koq.b(list)) {
            taskDayDataListener.onDataChange(0, healthLifeBean);
            return;
        }
        FitnessTrackRecord fitnessTrackRecord = (FitnessTrackRecord) list.get(0);
        if (fitnessTrackRecord == null) {
            taskDayDataListener.onDataChange(0, healthLifeBean);
            return;
        }
        int acquireSumExerciseTimes = fitnessTrackRecord.acquireSumExerciseTimes();
        if (acquireSumExerciseTimes <= 0) {
            taskDayDataListener.onDataChange(0, healthLifeBean);
            return;
        }
        if (healthLifeBean.getComplete() <= 0) {
            healthLifeBean.setComplete(1);
            healthLifeBean.setRest(0);
        }
        long currentTimeMillis = System.currentTimeMillis();
        healthLifeBean.setIsUpdated(true);
        healthLifeBean.setTimestamp(currentTimeMillis);
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        healthLifeBean.setResult(String.valueOf(acquireSumExerciseTimes));
        healthLifeBean.setSyncStatus(0);
        taskDayDataListener.onDataChange(0, healthLifeBean);
    }

    @Override // defpackage.avr, com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(final SparseArray<HealthLifeBean> sparseArray, final TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_WorkoutManager", "getRecord listener is null");
            return;
        }
        if (sparseArray == null) {
            LogUtil.h("HealthLife_WorkoutManager", "getRecord sparseArray is null");
            taskDataListener.onDataChange(-1, null);
        } else {
            if (sparseArray.size() <= 0) {
                LogUtil.h("HealthLife_WorkoutManager", "getRecord sparseArray ", sparseArray);
                taskDataListener.onDataChange(-1, sparseArray);
                return;
            }
            RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
            if (recordApi == null) {
                LogUtil.h("HealthLife_WorkoutManager", "getRecord api is null");
                taskDataListener.onDataChange(-1, sparseArray);
            } else {
                recordApi.acquireSummaryFitnessRecord(new kwy.a().a(azi.lN_(sparseArray)).e(azi.lK_(sparseArray)).c(3).d(), new IBaseResponseCallback() { // from class: axd
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        aww.kF_(TaskDataListener.this, sparseArray, i, obj);
                    }
                });
            }
        }
    }

    static /* synthetic */ void kF_(TaskDataListener taskDataListener, SparseArray sparseArray, int i, Object obj) {
        List<FitnessTrackRecord> arrayList = new ArrayList();
        if (koq.e(obj, FitnessTrackRecord.class)) {
            arrayList = (List) obj;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        for (FitnessTrackRecord fitnessTrackRecord : arrayList) {
            if (fitnessTrackRecord != null) {
                sparseIntArray.put(CommonUtil.h(fitnessTrackRecord.acquireMonthDate()), fitnessTrackRecord.acquireSumExerciseTimes());
            }
        }
        taskDataListener.onDataChange(0, bdh.nU_(4, sparseArray, sparseIntArray));
    }
}
