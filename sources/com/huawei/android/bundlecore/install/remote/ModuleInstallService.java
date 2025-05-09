package com.huawei.android.bundlecore.install.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallService;
import com.huawei.android.bundlecore.install.protocol.ISplitInstallServiceCallback;
import com.huawei.haf.handler.HandlerCenter;
import defpackage.yy;
import defpackage.yz;
import defpackage.za;
import defpackage.zb;
import defpackage.zc;
import defpackage.zd;
import java.util.List;

/* loaded from: classes8.dex */
public final class ModuleInstallService extends Service {
    private static final HandlerCenter e = HandlerCenter.a("bundle_");

    /* renamed from: a, reason: collision with root package name */
    private ISplitInstallService.Stub f1827a;

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        stopSelf(i2);
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.f1827a == null) {
            this.f1827a = new b();
        }
        return this.f1827a;
    }

    static void e(String str, Runnable runnable) {
        e.d(runnable, str);
    }

    static class b extends ISplitInstallService.Stub {
        private b() {
        }

        @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
        public void startInstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
            ModuleInstallService.e(str, new zc(iSplitInstallServiceCallback, list));
        }

        @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
        public void cancelInstall(String str, int i, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
            ModuleInstallService.e(str, new yy(iSplitInstallServiceCallback, i));
        }

        @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
        public void getSessionState(String str, int i, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
            ModuleInstallService.e(str, new yz(iSplitInstallServiceCallback, i));
        }

        @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
        public void getSessionStates(String str, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
            ModuleInstallService.e(str, new zb(iSplitInstallServiceCallback));
        }

        @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
        public void deferredInstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
            ModuleInstallService.e(str, new za(iSplitInstallServiceCallback, list));
        }

        @Override // com.huawei.android.bundlecore.install.protocol.ISplitInstallService
        public void deferredUninstall(String str, List<Bundle> list, Bundle bundle, ISplitInstallServiceCallback iSplitInstallServiceCallback) {
            ModuleInstallService.e(str, new zd(iSplitInstallServiceCallback, list));
        }
    }
}
