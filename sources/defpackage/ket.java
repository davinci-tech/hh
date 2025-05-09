package defpackage;

import android.os.Process;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class ket {
    private static ket d;

    /* renamed from: a, reason: collision with root package name */
    private long f14327a;
    private static final Object c = new Object();
    private static final Object b = new Object();
    private List<AbstractSyncTask> g = new LinkedList();
    private boolean e = false;

    private ket() {
        String d2 = CommonUtil.d(Process.myPid());
        if (TextUtils.isEmpty(d2) || bfh.f349a.equals(d2)) {
            return;
        }
        throw new RuntimeException("SyncTaskManager do not init in process process." + d2);
    }

    public static ket a() {
        ket ketVar;
        synchronized (c) {
            if (d == null) {
                d = new ket();
            }
            ketVar = d;
        }
        return ketVar;
    }

    public void e(String str) {
        ReleaseLogUtil.e("R_SyncTaskManager", "putAllSyncTask tag: ", str);
        this.f14327a = System.currentTimeMillis();
        synchronized (b) {
            boolean isEmpty = this.g.isEmpty();
            b(new key(""));
            b(new kfd());
            b(new kfa());
            d();
            b(new asp());
            b(new bdl());
            b(new kfc(""));
            if (isEmpty) {
                i();
            } else {
                LogUtil.h("SyncTaskManager", "task is running. wait queue.");
            }
        }
    }

    public void b(String str) {
        synchronized (b) {
            boolean isEmpty = this.g.isEmpty();
            c(str);
            if (isEmpty) {
                LogUtil.a("SyncTaskManager", "run", str, "task.");
                i();
            } else {
                LogUtil.h("SyncTaskManager", "task", str, " is running. wait queue.");
            }
        }
    }

    public void e(String str, keu keuVar, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            boolean isEmpty = this.g.isEmpty();
            c(str, keuVar, iBaseResponseCallback);
            if (isEmpty) {
                LogUtil.a("SyncTaskManager", "run", str, "task.");
                i();
            } else {
                LogUtil.h("SyncTaskManager", "task", str, " is running. wait queue.");
            }
        }
    }

    private void c(String str) {
        str.hashCode();
        if (str.equals("ECG_ANALY_SYNC_TASK")) {
            c(new kfc(String.valueOf(System.currentTimeMillis())));
        } else if (str.equals("ECG_SYNC_TASK")) {
            c(new key(String.valueOf(System.currentTimeMillis())));
        } else {
            LogUtil.h("SyncTaskManager", "taskTag is not adapter : ", str);
        }
    }

    private boolean c(String str, keu keuVar, IBaseResponseCallback iBaseResponseCallback) {
        str.hashCode();
        if (str.equals("DIC_SYNC_TASK")) {
            c(a(keuVar));
            if (!CommonUtil.as()) {
                return true;
            }
            kex.a().b(System.currentTimeMillis());
            return true;
        }
        if (str.equals("DIC_SEQUENCE_SYNC_TASK")) {
            c(d(keuVar, iBaseResponseCallback));
            if (!CommonUtil.as()) {
                return true;
            }
            kbv.b().d(System.currentTimeMillis());
            return true;
        }
        LogUtil.h("SyncTaskManager", "insertTask taskParams default");
        return true;
    }

    private void i() {
        LogUtil.a("SyncTaskManager", "runTasks enter.");
        synchronized (b) {
            if (this.g.isEmpty()) {
                LogUtil.h("SyncTaskManager", "all tasks is completed.");
                if (CommonUtil.as()) {
                    sqo.d("all tasks is completed, sync duration: " + (System.currentTimeMillis() - this.f14327a));
                }
                this.e = false;
                return;
            }
            final AbstractSyncTask abstractSyncTask = this.g.get(0);
            if (abstractSyncTask != null) {
                this.e = true;
                abstractSyncTask.startTask(new IBaseResponseCallback() { // from class: ket.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        ReleaseLogUtil.e("R_SyncTaskManager", "AbstractSyncTask : ", abstractSyncTask.getTaskTag(), ",errorCode : ", Integer.valueOf(i));
                        ket.this.a(abstractSyncTask, i);
                    }
                });
            } else {
                LogUtil.b("SyncTaskManager", "syncTask is null. check add task.");
                this.g.remove(0);
                i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AbstractSyncTask abstractSyncTask, int i) {
        if (!(abstractSyncTask instanceof key) && !(abstractSyncTask instanceof kfc)) {
            e(abstractSyncTask);
        } else if (i == 100005) {
            LogUtil.a("SyncTaskManager", "ecg file is busy, wait file queue.");
        } else {
            e(abstractSyncTask);
        }
    }

    private void e(AbstractSyncTask abstractSyncTask) {
        f(abstractSyncTask);
        i();
    }

    private void f(AbstractSyncTask abstractSyncTask) {
        synchronized (b) {
            this.g.remove(abstractSyncTask);
        }
    }

    private void b(AbstractSyncTask abstractSyncTask) {
        if (this.g.contains(abstractSyncTask)) {
            LogUtil.h("SyncTaskManager", "task : ", abstractSyncTask.getTaskTag(), ",already in queue.");
        } else if (a(abstractSyncTask) || d(abstractSyncTask)) {
            LogUtil.a("SyncTaskManager", "has ecg or analysis task, tag : ", abstractSyncTask.getTaskTag());
        } else {
            this.g.add(abstractSyncTask);
        }
    }

    private boolean a(AbstractSyncTask abstractSyncTask) {
        for (AbstractSyncTask abstractSyncTask2 : this.g) {
            if ((abstractSyncTask instanceof key) && (abstractSyncTask2 instanceof key)) {
                return true;
            }
        }
        return false;
    }

    private boolean d(AbstractSyncTask abstractSyncTask) {
        for (AbstractSyncTask abstractSyncTask2 : this.g) {
            if ((abstractSyncTask instanceof kfc) && (abstractSyncTask2 instanceof kfc)) {
                return true;
            }
        }
        return false;
    }

    private void c(AbstractSyncTask abstractSyncTask) {
        if (this.g.contains(abstractSyncTask)) {
            LogUtil.h("SyncTaskManager", "task : ", abstractSyncTask.getTaskTag(), ",already in queue.");
            if (this.g.size() == 1) {
                LogUtil.a("SyncTaskManager", "task is running.add task.");
                this.g.add(0, abstractSyncTask);
                return;
            } else {
                LogUtil.a("SyncTaskManager", "task is wait.remove task old index and jump first index.");
                this.g.remove(abstractSyncTask);
                this.g.add(0, abstractSyncTask);
                return;
            }
        }
        LogUtil.a("SyncTaskManager", "jumpTask insert first index.");
        this.g.add(0, abstractSyncTask);
    }

    public int e() {
        return this.e ? 101 : 100;
    }

    public void c() {
        ReleaseLogUtil.e("R_SyncTaskManager", "clearSyncTaskQueue.");
        synchronized (b) {
            this.g.clear();
        }
    }

    public void b() {
        LogUtil.a("SyncTaskManager", "destroy.");
        synchronized (b) {
            this.g.clear();
        }
        h();
    }

    private static void h() {
        synchronized (c) {
            d = null;
        }
    }

    private void d() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "SyncTaskManager");
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null) {
                keu keuVar = new keu();
                keuVar.d(deviceInfo.getDeviceIdentify());
                keuVar.c(-1);
                b(new kfb(deviceInfo, keuVar, System.currentTimeMillis()));
            }
        }
        if (CommonUtil.as()) {
            kex.a().b(System.currentTimeMillis());
        }
        for (DeviceInfo deviceInfo2 : deviceList) {
            if (deviceInfo2 != null) {
                keu keuVar2 = new keu();
                keuVar2.d(deviceInfo2.getDeviceIdentify());
                keuVar2.c(-1);
                b(new kez(deviceInfo2, keuVar2, System.currentTimeMillis(), null));
            }
        }
        if (CommonUtil.as()) {
            kbv.b().d(System.currentTimeMillis());
        }
    }

    private kfb a(keu keuVar) {
        LogUtil.a("SyncTaskManager", "addSingleDeviceTask");
        return new kfb(c(keuVar), keuVar, System.currentTimeMillis());
    }

    private kez d(keu keuVar, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SyncTaskManager", "addSequenceSingleDeviceTask");
        return new kez(c(keuVar), keuVar, System.currentTimeMillis(), iBaseResponseCallback);
    }

    private DeviceInfo c(keu keuVar) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "SyncTaskManager");
        if (deviceList == null || deviceList.isEmpty()) {
            return null;
        }
        return deviceList.get(0);
    }
}
