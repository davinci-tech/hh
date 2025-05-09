package com.huawei.hihealthservice.runnable;

import android.os.Process;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.dfx.block.AbstractMonitorHandler;
import com.huawei.haf.common.dfx.block.ThreadMonitorTask;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hmf.services.internal.ApplicationContext;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OperationKey;
import defpackage.ivu;
import defpackage.ivz;
import defpackage.iwe;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: classes7.dex */
public abstract class SingleRunnable implements Runnable {
    private static final String DEADLOCK_TIME_INTERVAL_COUNT = "deadlock_time_interval_count_";
    public static final String FORMAT_FILE_NAME = "_deadlockLog_%d";
    private static boolean IS_SET_OP_EVENT = true;
    private static String LAST_DEADLOCK_EVENT_TIME_RELEASE = "last_deadlock_event_time_release";
    private static final String LOG_TAG = "HiH_SingleRunnable";
    public static final String TASK_TAG = "SingleRunnable";

    protected abstract void doRun();

    @Override // java.lang.Runnable
    public void run() {
        ThreadMonitorTask threadMonitorTask = new ThreadMonitorTask(Thread.currentThread(), new e(TASK_TAG, FORMAT_FILE_NAME));
        try {
            threadMonitorTask.e("");
            doRun();
        } catch (Throwable th) {
            try {
                ReleaseLogUtil.c(LOG_TAG, "Failed to ThreadMonitorTask, cause:", ExceptionUtils.d(th));
                threadMonitorTask.e(th, System.currentTimeMillis());
            } finally {
                threadMonitorTask.e(null, System.currentTimeMillis());
            }
        }
    }

    public static class e extends AbstractMonitorHandler {

        /* renamed from: a, reason: collision with root package name */
        private long f4200a;
        private int d;

        public e(String str, String str2) {
            super(str, str2);
            this.f4200a = 0L;
            this.d = 0;
        }

        @Override // com.huawei.haf.common.dfx.block.MonitorCallback
        public boolean check(Thread thread, long j, long j2) {
            if (j2 <= 0) {
                return false;
            }
            long j3 = this.f4200a + j2;
            this.f4200a = j3;
            this.d++;
            if (a(j3)) {
                String e = SharedPreferenceManager.e("HuaweiHealth", "handleException", "");
                LinkedHashMap<String, String> a2 = a(thread);
                ReleaseLogUtil.c(SingleRunnable.LOG_TAG, "deadlock threadExecTime=", Long.valueOf(this.f4200a), ", errorMsg:", HiJsonUtil.e(a2.get(thread.getName())));
                String e2 = HiJsonUtil.e(a2);
                int i = 0;
                for (int i2 = 5000; i2 < e2.length(); i2 += 5000) {
                    String substring = e2.substring(i, i2);
                    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                    linkedHashMap.put("handleException", e);
                    linkedHashMap.put("ownerThread", ivu.c());
                    linkedHashMap.put("data", substring);
                    linkedHashMap.put(SingleRunnable.DEADLOCK_TIME_INTERVAL_COUNT, String.valueOf(this.d));
                    ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SQL_CIPHER_TIME_OUT_2129019.value(), linkedHashMap, true);
                    i = i2;
                }
                if (i < e2.length() - 1) {
                    String substring2 = e2.substring(i);
                    LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
                    linkedHashMap2.put("handleException", e);
                    linkedHashMap2.put("ownerThread", ivu.c());
                    linkedHashMap2.put("data", substring2);
                    linkedHashMap2.put(SingleRunnable.DEADLOCK_TIME_INTERVAL_COUNT, String.valueOf(this.d));
                    ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SQL_CIPHER_TIME_OUT_2129019.value(), linkedHashMap2, true);
                }
                boolean unused = SingleRunnable.IS_SET_OP_EVENT = false;
                saveDumpStackTraceInfo(thread, false);
            }
            long j4 = this.f4200a;
            if (j4 == 180000 || j4 == 240000 || j4 == 360000 || j4 == 540000) {
                saveDumpStackTraceInfo(thread, false);
            }
            if (this.f4200a > 660000 && !ProcessUtil.d("com.huawei.health")) {
                LinkedHashMap<String, String> linkedHashMap3 = new LinkedHashMap<>();
                linkedHashMap3.put("killDaemon", "1");
                ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SQL_CIPHER_TIME_OUT_2129019.value(), linkedHashMap3, true);
                ReleaseLogUtil.c(SingleRunnable.LOG_TAG, "killDaemon");
                Process.killProcess(Process.myPid());
            }
            return true;
        }

        private boolean a(long j) {
            if (!SingleRunnable.IS_SET_OP_EVENT || j < 600000) {
                return false;
            }
            if (!LogUtil.a()) {
                return true;
            }
            long c = iwe.c(ApplicationContext.getContext(), SingleRunnable.LAST_DEADLOCK_EVENT_TIME_RELEASE, 0, 0L);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - c < 86400000) {
                return false;
            }
            iwe.e(ApplicationContext.getContext(), SingleRunnable.LAST_DEADLOCK_EVENT_TIME_RELEASE, currentTimeMillis, 0);
            return true;
        }

        @Override // com.huawei.haf.common.dfx.block.MonitorCallback
        public void end(Thread thread, Throwable th, long j, long j2) {
            if (j2 <= 0) {
                return;
            }
            long beginTime = j - getBeginTime();
            if (beginTime >= OpAnalyticsConstants.H5_LOADING_DELAY) {
                com.huawei.hwlogsmodel.LogUtil.b(SingleRunnable.LOG_TAG, "ThreadMonitorTask currentThread run end thread=", Long.valueOf(thread.getId()), ", totalTime=", Long.valueOf(beginTime));
            }
        }

        private LinkedHashMap<String, String> a(Thread thread) {
            ThreadGroup e = e();
            Thread[] threadArr = new Thread[e.activeCount() * 2];
            int enumerate = e.enumerate(threadArr);
            Thread[] threadArr2 = new Thread[enumerate];
            System.arraycopy(threadArr, 0, threadArr2, 0, enumerate);
            List list = (List) Arrays.stream(threadArr2).collect(Collectors.toList());
            if (list.isEmpty()) {
                return new LinkedHashMap<>(0);
            }
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("errorCode", Integer.toString(-1));
            linkedHashMap.put(thread.getName(), d(thread));
            for (int i = 0; i < list.size(); i++) {
                Thread thread2 = (Thread) list.get(i);
                if (!thread.getName().equals(thread2.getName())) {
                    linkedHashMap.put(thread2.getName() + "_" + i, d(thread2));
                }
            }
            return linkedHashMap;
        }

        private String d(Thread thread) {
            return "deadlock thread name: " + thread.getName() + ", state: " + thread.getState() + ", id: " + thread.getId() + ": " + System.lineSeparator() + DfxUtils.d(thread, null);
        }

        private ThreadGroup e() {
            ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
            ThreadGroup parent = threadGroup == null ? null : threadGroup.getParent();
            while (true) {
                ThreadGroup threadGroup2 = parent;
                ThreadGroup threadGroup3 = threadGroup;
                threadGroup = threadGroup2;
                if (threadGroup == null) {
                    return threadGroup3;
                }
                parent = threadGroup.getParent();
            }
        }
    }
}
