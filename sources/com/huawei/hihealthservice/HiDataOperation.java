package com.huawei.hihealthservice;

import android.content.Context;
import com.huawei.haf.common.dfx.block.ThreadMonitorTask;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hihealthservice.runnable.SingleRunnable;
import defpackage.ifq;
import defpackage.ifr;

/* loaded from: classes7.dex */
public abstract class HiDataOperation implements Runnable {
    public static final int DATA_TYPE_GENERAL = 1;
    public static final int DATA_TYPE_REALTIME = 0;
    public static final int DATA_TYPE_SECE = 2;
    private static final String LOG_TAG = "HiH_HiDataOp";
    protected final Context mContext;
    protected final long mStartTime = System.currentTimeMillis();

    public abstract void execute(ifq ifqVar);

    public int getDataType() {
        return 1;
    }

    public boolean isMonitorThread() {
        return false;
    }

    public HiDataOperation(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!isMonitorThread()) {
            ifr.d().d(this);
            return;
        }
        ThreadMonitorTask threadMonitorTask = new ThreadMonitorTask(Thread.currentThread(), new SingleRunnable.e(SingleRunnable.TASK_TAG, SingleRunnable.FORMAT_FILE_NAME));
        try {
            threadMonitorTask.e("");
            ifr.d().d(this);
        } catch (Throwable th) {
            try {
                ReleaseLogUtil.c(LOG_TAG, "Failed to monitorThread, cause: ", ExceptionUtils.d(th));
                threadMonitorTask.e(th, System.currentTimeMillis());
            } finally {
                threadMonitorTask.e(null, System.currentTimeMillis());
            }
        }
    }
}
