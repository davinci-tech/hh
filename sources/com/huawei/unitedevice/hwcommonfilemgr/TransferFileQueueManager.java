package com.huawei.unitedevice.hwcommonfilemgr;

import android.text.TextUtils;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import defpackage.snz;
import defpackage.sol;
import health.compact.a.BuildConfigProperties;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/* loaded from: classes7.dex */
public class TransferFileQueueManager {
    private static final Object b = new Object();
    private static volatile TransferFileQueueManager e;

    /* renamed from: a, reason: collision with root package name */
    private LinkedList<sol> f10794a = new LinkedList<>();
    private Semaphore c = new Semaphore(1);

    public enum TaskMode {
        ENGINE,
        APP
    }

    private TransferFileQueueManager() {
        if (BuildConfigProperties.e("IS_RELEASE_VERSION", false)) {
            return;
        }
        String b2 = ProcessUtil.b();
        if (TextUtils.isEmpty(b2) || ProcessUtil.b(":PhoneService").equals(b2)) {
            return;
        }
        throw new RuntimeException("TransferFileQueueManager do not init in phoneService process." + b2);
    }

    public static TransferFileQueueManager d() {
        TransferFileQueueManager transferFileQueueManager;
        synchronized (b) {
            if (e == null) {
                e = new TransferFileQueueManager();
            }
            transferFileQueueManager = e;
        }
        return transferFileQueueManager;
    }

    public boolean a(sol solVar, String str) {
        LogUtil.c("TransferFileQueueManager", "ready add queue.", str);
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "addQueueToHead acquire exception.");
        }
        LogUtil.c("TransferFileQueueManager", "mQueueMap.size() ", Integer.valueOf(this.f10794a.size()));
        this.f10794a.add(solVar);
        boolean z = this.f10794a.size() == 1;
        this.c.release();
        if (solVar != null) {
            ReleaseLogUtil.b("R_TransferFileQueueManager", "add queue success.", str, " result : ", Integer.valueOf(this.f10794a.size()), "file type : ", Integer.valueOf(solVar.u()));
        }
        return z;
    }

    public void h(String str) {
        LogUtil.c("TransferFileQueueManager", "ready remove head task by queue.", str);
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "removeHeadTask acquire exception.");
        }
        if (this.f10794a.isEmpty()) {
            LogUtil.a("TransferFileQueueManager", "wrong, no task in queue, please check your code.");
        } else {
            sol remove = this.f10794a.remove(0);
            LogUtil.c("TransferFileQueueManager", "remove task : ", remove.m(), " id ： ", Integer.valueOf(remove.l()));
        }
        this.c.release();
        LogUtil.c("TransferFileQueueManager", "ready remove head task by queue success.", str);
    }

    public List<sol> e(String str) {
        LogUtil.c("TransferFileQueueManager", "ready getAllTask.", str);
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "getAllTask acquire exception.");
        }
        LinkedList linkedList = new LinkedList(this.f10794a);
        this.c.release();
        LogUtil.c("TransferFileQueueManager", "getAllTask success.", str);
        return linkedList;
    }

    public List<sol> d(String str) {
        LinkedList linkedList;
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "getTaskNoHeader acquire exception.");
        }
        if (this.f10794a.isEmpty() || this.f10794a.size() == 1) {
            linkedList = new LinkedList();
        } else {
            linkedList = new LinkedList(this.f10794a);
            linkedList.remove(0);
        }
        this.c.release();
        return linkedList;
    }

    public sol a(String str, int i, String str2) {
        LogUtil.c("TransferFileQueueManager", "ready stopInQueue queue.", str);
        sol solVar = null;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "stopInQueue acquire exception.");
        }
        int i2 = 1;
        if (this.f10794a.size() > 1) {
            while (true) {
                if (i2 >= this.f10794a.size()) {
                    break;
                }
                sol solVar2 = this.f10794a.get(i2);
                int u = solVar2.u();
                String m = solVar2.m();
                if (i == u && str2.equalsIgnoreCase(m)) {
                    this.f10794a.remove(i2);
                    solVar = solVar2;
                    break;
                }
                i2++;
            }
        }
        this.c.release();
        LogUtil.c("TransferFileQueueManager", "stopInQueue queue success.", str);
        return solVar;
    }

    public void b(String str) {
        sol solVar;
        LogUtil.c("TransferFileQueueManager", "ready nextTaskFromQueue.", str);
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "nextTaskFromQueue acquire exception.");
        }
        sol solVar2 = null;
        if (this.f10794a.isEmpty()) {
            LogUtil.a("TransferFileQueueManager", "wrong, no task in queue, please check your code.");
            solVar = null;
        } else {
            sol remove = this.f10794a.remove(0);
            if (this.f10794a.isEmpty()) {
                LogUtil.c("TransferFileQueueManager", "no next task");
            } else {
                solVar2 = this.f10794a.get(0);
            }
            sol solVar3 = solVar2;
            solVar2 = remove;
            solVar = solVar3;
        }
        this.c.release();
        LogUtil.c("TransferFileQueueManager", "nextTaskFromQueue success.", str);
        if (solVar2 != null) {
            HwWifiP2pTransferManager.d().f(solVar2.w());
            ReleaseLogUtil.b("R_TransferFileQueueManager", "file type : ", Integer.valueOf(solVar2.u()), " finish");
        }
        if (solVar == null) {
            return;
        }
        TaskMode an = solVar.an();
        int i = AnonymousClass5.e[an.ordinal()];
        if (i == 1) {
            LogUtil.c("TransferFileQueueManager", "next task is app mode.");
            snz.a().a(solVar);
        } else if (i == 2) {
            LogUtil.c("TransferFileQueueManager", "next task is engine mode.");
            snz.a().e(solVar.i(), solVar);
        } else {
            LogUtil.a("TransferFileQueueManager", "no adapter mode : ", an);
        }
    }

    /* renamed from: com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[TaskMode.values().length];
            e = iArr;
            try {
                iArr[TaskMode.APP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[TaskMode.ENGINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public sol c(String str) {
        LogUtil.c("TransferFileQueueManager", "getHeadTask : ", str);
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "getHeadTask acquire exception.");
        }
        sol peek = this.f10794a.peek();
        this.c.release();
        return peek;
    }

    public void a(String str) {
        LogUtil.c("TransferFileQueueManager", "ready clearAllTask queue.", str);
        try {
            this.c.acquire();
        } catch (InterruptedException unused) {
            LogUtil.e("TransferFileQueueManager", "clearAllTask acquire exception.");
        }
        this.f10794a.clear();
        this.c.release();
        LogUtil.c("TransferFileQueueManager", "add clearAllTask success.", str);
    }
}
