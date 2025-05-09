package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.PositionIdSet;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import health.compact.a.LogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class dms {
    private static volatile dms d;
    private final ConcurrentHashMap<String, Task<Map<Integer, ResourceResultInfo>>> c = new ConcurrentHashMap<>();

    private dms() {
    }

    public static dms e() {
        if (d == null) {
            synchronized (dms.class) {
                if (d == null) {
                    d = new dms();
                }
            }
        }
        return d;
    }

    public void b(String str, Task<Map<Integer, ResourceResultInfo>> task) {
        this.c.put(str, task);
        if (this.c.size() > 10) {
            LogUtil.a("MarketingTaskManager", "MarketingTaskManager size: " + this.c.size());
        }
    }

    public void b(final MarketingOption marketingOption, int i, final int i2) {
        if (this.c.isEmpty()) {
            LogUtil.c("MarketingTaskManager", "mTaskMap is empty. ");
            return;
        }
        Task<Map<Integer, ResourceResultInfo>> task = this.c.get(String.valueOf(i));
        if (task == null) {
            LogUtil.c("MarketingTaskManager", "task is empty. ");
        } else if (task.isSuccessful()) {
            d(dmi.a(PositionIdSet.getPositionIdList(i)), i2, marketingOption);
        } else {
            task.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: dms.4
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(final Map<Integer, ResourceResultInfo> map) {
                    if (map == null) {
                        LogUtil.a("MarketingTaskManager", "result invalid");
                    } else {
                        ThreadPoolManager.d().execute(new Runnable() { // from class: dms.4.2
                            @Override // java.lang.Runnable
                            public void run() {
                                dms.this.d(map, i2, marketingOption);
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<Integer, ResourceResultInfo> map, int i, MarketingOption marketingOption) {
        LogUtil.c("MarketingTaskManager", "doTriggering... eventType: " + i);
        new dml(marketingOption).c(i, dmm.d(map, marketingOption.getTriggerEventParams(), i), marketingOption.getTriggerEventParams());
    }
}
