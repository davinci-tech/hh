package com.huawei.hms.update.download;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.update.download.api.IOtaUpdate;
import com.huawei.hms.update.download.api.IUpdateCallback;
import com.huawei.hms.update.download.api.UpdateInfo;
import com.huawei.hms.utils.Checker;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes9.dex */
public class ThreadWrapper implements IOtaUpdate {
    private static final Executor b = Executors.newSingleThreadExecutor();

    /* renamed from: a, reason: collision with root package name */
    private final IOtaUpdate f6043a;

    public ThreadWrapper(IOtaUpdate iOtaUpdate) {
        Checker.checkNonNull(iOtaUpdate, "update must not be null.");
        this.f6043a = iOtaUpdate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IUpdateCallback b(final IUpdateCallback iUpdateCallback) {
        return new IUpdateCallback() { // from class: com.huawei.hms.update.download.ThreadWrapper.1
            @Override // com.huawei.hms.update.download.api.IUpdateCallback
            public void onCheckUpdate(final int i, final UpdateInfo updateInfo) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hms.update.download.ThreadWrapper.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        IUpdateCallback.this.onCheckUpdate(i, updateInfo);
                    }
                });
            }

            @Override // com.huawei.hms.update.download.api.IUpdateCallback
            public void onDownloadPackage(final int i, final int i2, final int i3, final File file) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hms.update.download.ThreadWrapper.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        IUpdateCallback.this.onDownloadPackage(i, i2, i3, file);
                    }
                });
            }
        };
    }

    @Override // com.huawei.hms.update.download.api.IOtaUpdate
    public void cancel() {
        this.f6043a.cancel();
    }

    @Override // com.huawei.hms.update.download.api.IOtaUpdate
    public void downloadPackage(final IUpdateCallback iUpdateCallback, final UpdateInfo updateInfo) {
        b.execute(new Runnable() { // from class: com.huawei.hms.update.download.ThreadWrapper.2
            @Override // java.lang.Runnable
            public void run() {
                ThreadWrapper.this.f6043a.downloadPackage(ThreadWrapper.b(iUpdateCallback), updateInfo);
            }
        });
    }

    @Override // com.huawei.hms.update.download.api.IOtaUpdate
    public Context getContext() {
        return this.f6043a.getContext();
    }
}
