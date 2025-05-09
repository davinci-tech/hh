package defpackage;

import android.os.Process;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.hwappdfxmgr.threads.ThreadsCallBack;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/* loaded from: classes.dex */
public class ixl extends DfxBaseHandler implements ThreadsCallBack {
    private static int b;
    private static long e;

    public ixl() {
        super("HAF_HealthThreadsHandler", "_threads");
    }

    @Override // com.huawei.hwappdfxmgr.threads.ThreadsCallBack
    public void dumpThreadsMessage(String str, long j) {
        File saveFile;
        if (CommonUtil.bv() || !c(j) || (saveFile = getSaveFile()) == null) {
            return;
        }
        ThreadGroup e2 = e();
        Thread[] threadArr = new Thread[e2.activeCount() * 2];
        int enumerate = e2.enumerate(threadArr);
        Thread[] threadArr2 = new Thread[enumerate];
        System.arraycopy(threadArr, 0, threadArr2, 0, enumerate);
        StringBuilder sb = new StringBuilder(" start dumpThreadsMessage; current process:");
        sb.append(ProcessUtil.b());
        sb.append("; the situation: ");
        sb.append(str);
        sb.append("; threadCount: ");
        sb.append(e2.activeCount());
        LogUtil.c("HAF_HealthThreadsHandler", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) sb);
        sb2.append(System.lineSeparator());
        for (int i = 0; i < enumerate; i++) {
            c(threadArr2[i], sb2);
        }
        if (b(sb2.toString(), saveFile)) {
            return;
        }
        LogUtil.a("HAF_HealthThreadsHandler", "save Threads Message fail.");
    }

    @Override // com.huawei.hwappdfxmgr.threads.ThreadsCallBack
    public int getAllThreadCount() {
        ThreadGroup e2 = e();
        if (e2 != null) {
            return e2.activeCount();
        }
        return 0;
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

    private int a() {
        return (EnvironmentUtils.b() || EnvironmentUtils.e()) ? 100 : 200;
    }

    private void c(Thread thread, StringBuilder sb) {
        sb.append("SystemInfo:");
        SystemInfo.a(sb);
        sb.append(System.lineSeparator());
        sb.append("ProcessName:");
        sb.append(ProcessUtil.b());
        sb.append(", pid=");
        sb.append(Process.myPid());
        sb.append(System.lineSeparator());
        sb.append("ThreadName:");
        sb.append(thread.getName());
        sb.append(", tid=");
        sb.append(thread.getId());
        sb.append(System.lineSeparator());
        sb.append("--------------------------------");
        sb.append(System.lineSeparator());
    }

    private boolean c(long j) {
        if (j - e < OpAnalyticsConstants.H5_LOADING_DELAY) {
            return false;
        }
        int allThreadCount = getAllThreadCount();
        if (allThreadCount - b <= 5 || allThreadCount <= a()) {
            return false;
        }
        e = j;
        b = allThreadCount;
        return true;
    }

    private boolean b(String str, File file) {
        boolean z = false;
        if (file != null && file.getParentFile().exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    PrintStream printStream = new PrintStream((OutputStream) fileOutputStream, false, "UTF-8");
                    try {
                        printStream.println(str);
                        z = true;
                        printStream.close();
                        fileOutputStream.close();
                    } finally {
                    }
                } finally {
                }
            } catch (IOException e2) {
                LogUtil.e("HAF_HealthThreadsHandler", "saveInfo ex=", LogUtil.a(e2));
            }
        }
        return z;
    }
}
