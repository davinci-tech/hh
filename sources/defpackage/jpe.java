package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.settings.sendswitchcommandtask.SendSwitchTask;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class jpe {
    private static jpe b;
    private LinkedList<SendSwitchTask> c = new LinkedList<>();
    private static final Object e = new Object();
    private static final Object d = new Object();

    private jpe() {
    }

    public static jpe b() {
        jpe jpeVar;
        synchronized (e) {
            if (b == null) {
                b = new jpe();
            }
            jpeVar = b;
        }
        return jpeVar;
    }

    public void c(SendSwitchTask sendSwitchTask) {
        if (sendSwitchTask == null) {
            LogUtil.h("SendSwitchTaskHandler", "addTask task is null.");
            return;
        }
        LogUtil.h("SendSwitchTaskHandler", "add task tag : ", sendSwitchTask.getTaskTag(), "type : ", Integer.valueOf(sendSwitchTask.getTaskType()));
        synchronized (d) {
            if (!sendSwitchTask.isWillRunTask()) {
                LogUtil.h("SendSwitchTaskHandler", "task will not run. no add queue.");
                return;
            }
            if (!this.c.contains(sendSwitchTask)) {
                LogUtil.a("SendSwitchTaskHandler", "add task : ", Boolean.valueOf(this.c.add(sendSwitchTask)));
            } else if (sendSwitchTask.getTaskType() == 2) {
                boolean remove = this.c.remove(sendSwitchTask);
                this.c.addFirst(sendSwitchTask);
                LogUtil.a("SendSwitchTaskHandler", "isRemoved : ", Boolean.valueOf(remove), "add high task");
            } else {
                LogUtil.h("SendSwitchTaskHandler", "low task, not run.");
            }
        }
    }

    public void e(final int i) {
        ThreadPoolManager.d().d("SendSwitchTaskHandler", new Runnable() { // from class: jpe.5
            @Override // java.lang.Runnable
            public void run() {
                synchronized (jpe.d) {
                    LogUtil.a("SendSwitchTaskHandler", "runTask");
                    if (!jpe.this.c.isEmpty()) {
                        Iterator it = jpe.this.c.iterator();
                        while (it.hasNext()) {
                            SendSwitchTask sendSwitchTask = (SendSwitchTask) it.next();
                            if (sendSwitchTask.getTaskType() == 2) {
                                LogUtil.a("SendSwitchTaskHandler", "immediately task run and remove");
                                sendSwitchTask.run();
                                it.remove();
                            } else {
                                LogUtil.a("SendSwitchTaskHandler", "wait task, not run now.");
                            }
                        }
                        if ((i & 1) == 1 && !jpe.this.c.isEmpty()) {
                            new Timer("SendSwitchTaskHandler").schedule(new TimerTask() { // from class: jpe.5.4
                                @Override // java.util.TimerTask, java.lang.Runnable
                                public void run() {
                                    LogUtil.a("SendSwitchTaskHandler", "run RUN_TASK_RUN_WAIT");
                                    jpe.this.c((IBaseResponseCallback) null);
                                }
                            }, 5000L);
                        }
                        if ((i & 2) == 2) {
                            LogUtil.a("SendSwitchTaskHandler", "run RUN_IMMEDIATELY_TASK");
                            jpe.this.c.clear();
                        }
                        return;
                    }
                    LogUtil.h("SendSwitchTaskHandler", "runTask is empty.");
                }
            }
        });
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().d("SendSwitchTaskHandler", new Runnable() { // from class: jpe.3
            @Override // java.lang.Runnable
            public void run() {
                LinkedList linkedList = new LinkedList();
                synchronized (jpe.d) {
                    LogUtil.a("SendSwitchTaskHandler", "runAllTask");
                    if (!jpe.this.c.isEmpty()) {
                        Iterator it = jpe.this.c.iterator();
                        while (it.hasNext()) {
                            SendSwitchTask sendSwitchTask = (SendSwitchTask) it.next();
                            sendSwitchTask.run();
                            if (iBaseResponseCallback == null) {
                                it.remove();
                            } else {
                                LogUtil.a("SendSwitchTaskHandler", "removeTaskList add task");
                                linkedList.add(sendSwitchTask);
                            }
                        }
                        if (iBaseResponseCallback != null) {
                            LogUtil.a("SendSwitchTaskHandler", "all task runned, response success.");
                            iBaseResponseCallback.onResponse(0, linkedList);
                            return;
                        }
                        return;
                    }
                    LogUtil.h("SendSwitchTaskHandler", "runAllTask is empty.");
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.onResponse(0, null);
                    }
                }
            }
        });
    }

    public void e() {
        synchronized (d) {
            if (this.c.isEmpty()) {
                LogUtil.a("SendSwitchTaskHandler", "runImmediatelyTask task is empty.");
            } else {
                ThreadPoolManager.d().d("SendSwitchTaskHandler", new AnonymousClass1());
            }
        }
    }

    /* renamed from: jpe$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            jpe.this.c(new IBaseResponseCallback() { // from class: jpe.1.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                public void onResponse(int i, Object obj) {
                    final List list = obj instanceof List ? (List) obj : null;
                    new Timer("SendSwitchTaskHandler").schedule(new TimerTask() { // from class: jpe.1.2.5
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            LogUtil.a("SendSwitchTaskHandler", "isRunningImmediately set false.");
                            List list2 = list;
                            if (list2 == null || list2.isEmpty()) {
                                return;
                            }
                            synchronized (jpe.d) {
                                LogUtil.a("SendSwitchTaskHandler", "remove removeTaskList");
                                jpe.this.c.removeAll(list);
                            }
                        }
                    }, 5000L);
                }
            });
        }
    }
}
