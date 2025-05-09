package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class awl implements TaskDataListener {

    /* renamed from: a, reason: collision with root package name */
    private final int f262a;
    private final int c;
    private final TaskDataListener e;
    private final String j;
    private final SparseArray<List<HealthLifeBean>> d = new SparseArray<>();
    private final List<SparseArray<HealthLifeBean>> b = Collections.synchronizedList(new ArrayList());
    private final AtomicInteger f = new AtomicInteger();

    public awl(String str, int i, TaskDataListener taskDataListener, int i2) {
        this.e = taskDataListener;
        this.c = i2;
        this.f262a = i;
        this.j = str;
    }

    @Override // com.huawei.basichealthmodel.listener.TaskDataListener
    public void onAllDataChange(int i, SparseArray<List<HealthLifeBean>> sparseArray) {
        if (sparseArray == null || sparseArray.size() == 0) {
            TaskDataListener taskDataListener = this.e;
            if (taskDataListener != null) {
                taskDataListener.onAllDataChange(-1, sparseArray);
            }
            LogUtil.h("HealthLife_TaskData", "WeekTaskManagerListener OneDayTaskManagerListener healthTaskBeanList is null.");
            return;
        }
        LogUtil.a("HealthLife_TaskData", "WeekTaskManagerListener WeekTaskManagerListener onAllTaskDataChange healthTaskBeanList", Integer.valueOf(sparseArray.size()));
        kp_(sparseArray);
    }

    @Override // com.huawei.basichealthmodel.listener.TaskDataListener
    public void onDataChange(int i, SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null) {
            TaskDataListener taskDataListener = this.e;
            if (taskDataListener != null) {
                taskDataListener.onDataChange(-1, null);
            }
            LogUtil.h("HealthLife_TaskData", "onWeekOneTaskDataChange healthTaskBeanList is null");
            sparseArray = new SparseArray<>();
        }
        this.b.add(sparseArray);
        if (this.f.addAndGet(1) >= this.c) {
            c();
            onAllDataChange(0, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ko_, reason: merged with bridge method [inline-methods] */
    public void kp_(final SparseArray<List<HealthLifeBean>> sparseArray) {
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelInsertWeekTask", new Runnable() { // from class: awi
                @Override // java.lang.Runnable
                public final void run() {
                    awl.this.kp_(sparseArray);
                }
            });
            return;
        }
        List<HealthLifeBean> kn_ = kn_(sparseArray);
        LogUtil.a("HealthLife_TaskData", "insertBeanList result ", Long.valueOf(koq.c(kn_) ? auz.a(kn_, this.j) : -1L));
        TaskDataListener taskDataListener = this.e;
        if (taskDataListener != null) {
            taskDataListener.onAllDataChange(0, sparseArray);
            this.d.clear();
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - bao.d("health_life_last_sync_time_millis")) < 180000) {
            return;
        }
        awj.a().b(this.j, DateFormatUtil.b(jdl.c(DateFormatUtil.c(this.f262a), 1, 0)), DateFormatUtil.b(currentTimeMillis), 4);
        bao.c("health_life_last_sync_time_millis", currentTimeMillis);
    }

    private List<HealthLifeBean> kn_(SparseArray<List<HealthLifeBean>> sparseArray) {
        boolean z;
        int id;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            List<HealthLifeBean> list = sparseArray.get(keyAt);
            if (!koq.b(list) && list.size() >= 3) {
                Iterator<HealthLifeBean> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    HealthLifeBean next = it.next();
                    if (next != null && next.getTaskType() == 1) {
                        LogUtil.a("HealthLife_TaskData", "getInsertDbBeanList isBasicType bean ", next);
                        z = true;
                        break;
                    }
                }
                ArrayList<Integer> e = azi.e(list);
                StringBuilder sb = new StringBuilder();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                for (HealthLifeBean healthLifeBean : list) {
                    if (healthLifeBean != null && (id = healthLifeBean.getId()) != 1 && id != 100001) {
                        i3++;
                        if (TextUtils.isEmpty(sb.toString())) {
                            sb.append(id);
                        } else {
                            sb.append(",");
                            sb.append(id);
                        }
                        if (healthLifeBean.getComplete() > 0) {
                            i2++;
                            if (!z ? e.contains(Integer.valueOf(id)) : healthLifeBean.getTaskType() == 1) {
                                i4++;
                            }
                        }
                        if (healthLifeBean.getIsUpdated()) {
                            arrayList.add(healthLifeBean);
                        }
                    }
                }
                arrayList.add(bcm.a(sb.toString(), keyAt, i2 == i3 ? 1 : 0, 1, this.j));
                int q = azi.q();
                if (q > 0 && keyAt >= q) {
                    arrayList.add(bcm.a(sb.toString(), keyAt, i4 != 3 ? 0 : 1, 100001, this.j));
                }
            }
        }
        return arrayList;
    }

    private void c() {
        LogUtil.a("HealthLife_TaskData", "mAllTasksList size ", Integer.valueOf(this.b.size()));
        for (SparseArray<HealthLifeBean> sparseArray : this.b) {
            for (int i = 0; i < sparseArray.size(); i++) {
                int keyAt = sparseArray.keyAt(i);
                List<HealthLifeBean> list = this.d.get(keyAt);
                if (koq.b(list)) {
                    list = new ArrayList<>();
                }
                list.add(sparseArray.get(keyAt));
                this.d.put(keyAt, list);
            }
        }
    }
}
