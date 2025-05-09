package com.huawei.operation.h5pro.preload;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.preload.IH5PreloadStrategy;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.H5proUtil;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class H5ProPkgPreloadSyncTask {
    private static final String TAG = "H5ProPkgPreloadSyncTask";

    private H5ProPkgPreloadSyncTask() {
    }

    public static void startTask(final Context context, final String str, final IH5PreloadStrategy iH5PreloadStrategy) {
        ThreadPoolManager.d().d(TAG, new Runnable() { // from class: com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask.1
            @Override // java.lang.Runnable
            public void run() {
                H5ProPkgPreloadSyncTask.preload(str, context, iH5PreloadStrategy);
            }
        });
    }

    public static void startTaskWithDelay(final Context context, final String[] strArr, final IH5PreloadStrategy iH5PreloadStrategy, long j) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        new Timer(TAG).schedule(new TimerTask() { // from class: com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                for (String str : strArr) {
                    H5ProPkgPreloadSyncTask.preload(str, context, iH5PreloadStrategy);
                }
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void preload(String str, Context context, IH5PreloadStrategy iH5PreloadStrategy) {
        LogUtil.a(TAG, "start task " + str);
        if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            LogUtil.a(TAG, "huid is empty, return. " + str);
        } else {
            H5proUtil.initH5pro();
            H5ProClient.preInstallH5MiniProgram(context, str, iH5PreloadStrategy);
            LogUtil.a(TAG, "preInstallH5MiniProgram end " + str);
        }
    }
}
