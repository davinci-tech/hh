package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.basichealthmodel.listener.TaskInterface;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class avr implements TaskInterface {
    @Override // com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_BaseManager", "getRecord listener is null bean ", healthLifeBean);
        } else if (healthLifeBean == null) {
            ReleaseLogUtil.a("HealthLife_BaseManager", "getRecord bean is null listener ", taskDayDataListener);
            taskDayDataListener.onDataChange(-1, null);
        } else {
            taskDayDataListener.onDataChange(0, healthLifeBean);
        }
    }

    @Override // com.huawei.basichealthmodel.listener.TaskInterface
    public void getRecord(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener) {
        if (taskDataListener == null) {
            LogUtil.h("HealthLife_BaseManager", "getRecord listener is null sparseArray ", sparseArray);
            return;
        }
        if (sparseArray == null) {
            ReleaseLogUtil.a("HealthLife_BaseManager", "getRecord sparseArray is null listener ", taskDataListener);
            taskDataListener.onDataChange(-1, null);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_BaseManager", "getRecord size ", Integer.valueOf(size), " sparseArray ", sparseArray, " listener ", taskDataListener);
            taskDataListener.onDataChange(-1, sparseArray);
        } else {
            taskDataListener.onDataChange(0, sparseArray);
        }
    }

    @Override // com.huawei.basichealthmodel.listener.TaskInterface
    /* renamed from: saveRecord, reason: merged with bridge method [inline-methods] */
    public void c(final int i, final String str, final HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_BaseManager", "saveRecord listener is null.");
            return;
        }
        if (healthLifeBean == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_BaseManager", "saveRecord null or no new change.");
            taskDayDataListener.onDataChange(-1, healthLifeBean);
        } else {
            if (HandlerExecutor.c()) {
                azi.b(ThreadPoolManager.d(), "HealthModelSaveTaskRecord", new Runnable() { // from class: avq
                    @Override // java.lang.Runnable
                    public final void run() {
                        avr.this.c(i, str, healthLifeBean, taskDayDataListener);
                    }
                });
                return;
            }
            int complete = healthLifeBean.getComplete();
            c(i, str, healthLifeBean);
            int complete2 = healthLifeBean.getComplete();
            if (complete2 > 0 && complete <= 0) {
                aza.d(healthLifeBean.getId(), complete2);
            }
            taskDayDataListener.onDataChange(auz.a(healthLifeBean, azi.p()) >= 0 ? 0 : -1, healthLifeBean);
        }
    }

    private void c(int i, String str, HealthLifeBean healthLifeBean) {
        int b = b(i, healthLifeBean);
        if (b == -1) {
            return;
        }
        int id = healthLifeBean.getId();
        int recordDay = healthLifeBean.getRecordDay();
        if (id == 6) {
            int a2 = bcm.a(recordDay, str);
            int d = bcm.d(recordDay, healthLifeBean.getTarget());
            if (healthLifeBean.getComplete() <= 0 && Math.abs(d - a2) <= 60) {
                healthLifeBean.setComplete(b);
            }
        } else if (id == 7) {
            int a3 = bcm.a(recordDay, str);
            int d2 = bcm.d(recordDay, healthLifeBean.getTarget());
            if (healthLifeBean.getComplete() <= 0 && a3 <= d2 + 60) {
                healthLifeBean.setComplete(b);
            }
        } else if (id == 12) {
            LogUtil.a("HealthLife_BaseManager", "refreshBean type ", Integer.valueOf(i), " result ", str, " bean ", healthLifeBean);
        } else if (healthLifeBean.getComplete() <= 0 && CommonUtil.h(str) >= CommonUtil.h(healthLifeBean.getTarget())) {
            healthLifeBean.setComplete(b);
        }
        long currentTimeMillis = System.currentTimeMillis();
        healthLifeBean.setResult(str);
        healthLifeBean.setTimestamp(currentTimeMillis);
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        healthLifeBean.setSyncStatus(0);
    }

    private int b(int i, HealthLifeBean healthLifeBean) {
        int complete = healthLifeBean.getComplete();
        if (complete == 4) {
            return 4;
        }
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    return -1;
                }
                if (complete <= 0) {
                    healthLifeBean.setComplete(3);
                }
                return 3;
            }
            if (complete <= 0) {
                healthLifeBean.setComplete(2);
            }
            healthLifeBean.setRest(1);
        }
        return 2;
    }
}
