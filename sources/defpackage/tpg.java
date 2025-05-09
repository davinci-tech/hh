package defpackage;

import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.wearengine.MonitorManager;
import com.huawei.wearengine.WearEngineBinderClient;
import com.huawei.wearengine.WearEngineClientInner;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorDataCallback;
import com.huawei.wearengine.monitor.MonitorItem;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class tpg implements MonitorManager, WearEngineBinderClient {

    /* renamed from: a, reason: collision with root package name */
    private final Object f17322a = new Object();
    private IBinder.DeathRecipient d = new IBinder.DeathRecipient() { // from class: tpg.3
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tov.b("MonitorServiceProxy", "binderDied enter");
            if (tpg.this.c != null) {
                tpg.this.c.asBinder().unlinkToDeath(tpg.this.d, 0);
                tpg.this.c = null;
            }
        }
    };
    private MonitorManager c = null;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    public tpg() {
        c();
    }

    private void b() throws RemoteException {
        synchronized (this.f17322a) {
            if (this.c == null) {
                WearEngineClientInner.c().d();
                IBinder fcR_ = WearEngineClientInner.c().fcR_(3);
                if (fcR_ == null) {
                    throw new tnx(2);
                }
                MonitorManager asInterface = MonitorManager.Stub.asInterface(fcR_);
                this.c = asInterface;
                asInterface.asBinder().linkToDeath(this.d, 0);
            }
        }
    }

    @Override // com.huawei.wearengine.MonitorManager
    public int registerListener(Device device, String str, MonitorItem monitorItem, MonitorDataCallback monitorDataCallback, int i) {
        try {
            b();
            if (this.c == null) {
                return 6;
            }
            e(new ArrayList(Collections.singleton(monitorItem)));
            return this.c.registerListener(device, str, monitorItem, monitorDataCallback, i);
        } catch (RemoteException unused) {
            tov.d("MonitorServiceProxy", "registerListener RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.MonitorManager
    public int registerListListener(Device device, String str, List<MonitorItem> list, MonitorDataCallback monitorDataCallback, int i) {
        try {
            b();
            if (this.c == null) {
                return 6;
            }
            e(list);
            return this.c.registerListListener(device, str, list, monitorDataCallback, i);
        } catch (RemoteException unused) {
            tov.d("MonitorServiceProxy", "registerListListener RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.MonitorManager
    public int unregisterListener(MonitorDataCallback monitorDataCallback, int i) {
        try {
            b();
            MonitorManager monitorManager = this.c;
            if (monitorManager != null) {
                return monitorManager.unregisterListener(monitorDataCallback, i);
            }
            return 6;
        } catch (RemoteException unused) {
            tov.d("MonitorServiceProxy", "unregisterListener RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.MonitorManager
    public MonitorData query(Device device, MonitorItem monitorItem) {
        try {
            b();
            if (this.c != null) {
                if (!e(monitorItem)) {
                    tov.d("MonitorServiceProxy", "query Health version is low");
                    throw new tnx(14);
                }
                return this.c.query(device, monitorItem);
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("MonitorServiceProxy", "send RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    private boolean e(MonitorItem monitorItem) {
        if (!monitorItem.getName().equals(MonitorItem.MONITOR_ITEM_POWER_MODE.getName()) || tra.a("powerMode")) {
            return tra.a("monitor_query");
        }
        return false;
    }

    @Override // com.huawei.wearengine.WearEngineBinderClient
    public void clearBinderProxy() {
        this.c = null;
        tov.b("MonitorServiceProxy", "clearBinderProxy");
    }

    private void c() {
        WearEngineClientInner.c().a(new tob(new WeakReference(this)));
    }

    private void e(List<MonitorItem> list) {
        if (list == null || list.isEmpty()) {
            tov.d("MonitorServiceProxy", "checkServiceSupportMonitorStatus monitorItemList == null or monitorItemList.isEmpty()");
            throw new tnx(5);
        }
        for (MonitorItem monitorItem : list) {
            if (!MonitorItem.MONITOR_ITEM_CONNECTION.getName().equals(monitorItem.getName()) && !tra.a(monitorItem.getName())) {
                tov.d("MonitorServiceProxy", "checkServiceSupportMonitorStatus Health version is low");
                throw new tnx(14);
            }
        }
    }
}
