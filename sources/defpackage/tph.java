package defpackage;

import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorDataCallback;
import com.huawei.wearengine.monitor.MonitorItem;
import com.huawei.wearengine.monitor.MonitorListener;
import java.util.concurrent.Callable;

/* loaded from: classes7.dex */
public final class tph {
    private tpg c;

    private tph() {
        this.c = new tpg();
    }

    static class a {
        private static final tph d = new tph();
    }

    public static tph c() {
        return a.d;
    }

    public Task<Void> b(final Device device, final MonitorItem monitorItem, final MonitorListener monitorListener) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tph.3
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(device, "Device can not be null!");
                top.a(monitorListener, "register single monitor, monitorListener can not be null!");
                MonitorDataCallback a2 = tph.this.a(monitorListener);
                int registerListener = tph.this.c.registerListener(device, trr.c().getPackageName(), monitorItem, a2, System.identityHashCode(monitorListener));
                if (registerListener == 0) {
                    return null;
                }
                throw new tnx(registerListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MonitorDataCallback a(final MonitorListener monitorListener) {
        return new MonitorDataCallback.Stub() { // from class: com.huawei.wearengine.monitor.MonitorClient$3
            @Override // com.huawei.wearengine.monitor.MonitorDataCallback
            public void onChanged(int i, MonitorItem monitorItem, MonitorData monitorData) {
                MonitorListener monitorListener2 = monitorListener;
                if (monitorListener2 != null) {
                    monitorListener2.onChanged(i, monitorItem, monitorData);
                }
            }
        };
    }

    public Task<Void> c(final MonitorListener monitorListener) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: tph.4
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void call() {
                top.a(monitorListener, "Unregister monitorListener can not be null!");
                int unregisterListener = tph.this.c.unregisterListener(tph.this.a(null), System.identityHashCode(monitorListener));
                if (unregisterListener == 0) {
                    return null;
                }
                throw new tnx(unregisterListener);
            }
        });
    }

    public Task<MonitorData> c(final Device device, final MonitorItem monitorItem) {
        return Tasks.callInBackground(new Callable<MonitorData>() { // from class: tph.5
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public MonitorData call() {
                top.a(device, "Device can not be null!");
                top.a(monitorItem, "MonitorItem can not be null!");
                MonitorData query = tph.this.c.query(device, monitorItem);
                if (query != null) {
                    return query;
                }
                throw new tnx(12);
            }
        });
    }
}
