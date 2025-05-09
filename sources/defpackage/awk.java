package defpackage;

import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class awk implements TaskDayDataListener {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final int f261a;
    private final int b;
    private final TaskDayDataListener c;
    private final List<HealthLifeBean> d = Collections.synchronizedList(new ArrayList());
    private final AtomicInteger h = new AtomicInteger();
    private final String f = azi.p();

    public awk(int i, int i2, TaskDayDataListener taskDayDataListener) {
        this.f261a = i;
        this.b = i2;
        this.c = taskDayDataListener;
        LogUtil.a("HealthLife_TaskDayData", "TaskDayData numTotal ", Integer.valueOf(i), " recordDay ", Integer.valueOf(i2), " listener ", taskDayDataListener);
    }

    @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
    public void onAllDataChange(int i, List<HealthLifeBean> list) {
        LogUtil.a("HealthLife_TaskDayData", "onAllDataChange result ", Integer.valueOf(i), " list ", list);
        if (list == null || list.size() < this.f261a) {
            TaskDayDataListener taskDayDataListener = this.c;
            if (taskDayDataListener != null) {
                taskDayDataListener.onAllDataChange(-1, new ArrayList());
                return;
            }
            return;
        }
        e(new CopyOnWriteArrayList<>(list));
    }

    @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
    public void onDataChange(int i, HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            TaskDayDataListener taskDayDataListener = this.c;
            if (taskDayDataListener != null) {
                taskDayDataListener.onDataChange(-1, null);
            }
        } else {
            TaskDayDataListener taskDayDataListener2 = this.c;
            if (taskDayDataListener2 != null) {
                taskDayDataListener2.onDataChange(0, healthLifeBean);
            }
            this.d.add(healthLifeBean);
        }
        synchronized (e) {
            this.h.addAndGet(1);
            LogUtil.a("HealthLife_TaskDayData", "onDataChange mResultNum ", Integer.valueOf(this.h.get()), " mNumTotal ", Integer.valueOf(this.f261a));
            if (this.h.get() >= this.f261a) {
                onAllDataChange(0, this.d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void e(final CopyOnWriteArrayList<HealthLifeBean> copyOnWriteArrayList) {
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelInsertOneDayTask", new Runnable() { // from class: awo
                @Override // java.lang.Runnable
                public final void run() {
                    awk.this.e(copyOnWriteArrayList);
                }
            });
            return;
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder("");
        ArrayList<Integer> b = awq.e().b();
        Iterator<HealthLifeBean> it = copyOnWriteArrayList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            if (next != null) {
                int id = next.getId();
                if ("".equals(sb.toString())) {
                    sb.append(id);
                } else {
                    sb.append(",");
                    sb.append(id);
                }
                if (next.getComplete() >= 1) {
                    i++;
                    if (b.contains(Integer.valueOf(id))) {
                        i2++;
                    }
                }
                if (next.getIsUpdated()) {
                    arrayList.add(next);
                }
            }
        }
        HealthLifeBean a2 = bcm.a(sb.toString(), this.b, i == copyOnWriteArrayList.size() ? 1 : 0, 1, this.f);
        if (copyOnWriteArrayList.size() >= 3) {
            arrayList.add(a2);
        }
        e(i2, sb, copyOnWriteArrayList.size(), arrayList);
        LogUtil.a("HealthLife_TaskDayData", "onAllTaskDataChange mListener ", this.c);
        TaskDayDataListener taskDayDataListener = this.c;
        if (taskDayDataListener != null) {
            taskDayDataListener.onAllDataChange(0, this.d);
            this.d.clear();
        }
    }

    private void e(int i, StringBuilder sb, int i2, List<HealthLifeBean> list) {
        int q = azi.q();
        if (q != 0 && this.b >= q) {
            HealthLifeBean a2 = bcm.a(sb.toString(), this.b, i == 3 ? 1 : 0, 100001, this.f);
            if (i2 >= 3) {
                list.add(a2);
            }
        }
        if (koq.c(list)) {
            LogUtil.a("HealthLife_TaskDayData", "onAllTaskDataChange insertTaskList result is ", Long.valueOf(auz.a(list, this.f)));
        } else {
            LogUtil.a("HealthLife_TaskDayData", "onAllTaskDataChange insertTaskList is empty.");
        }
    }
}
