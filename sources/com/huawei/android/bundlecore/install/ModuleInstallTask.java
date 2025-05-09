package com.huawei.android.bundlecore.install;

import defpackage.yi;
import defpackage.yu;
import defpackage.zj;
import defpackage.zm;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class ModuleInstallTask implements Runnable {
    private static final String TAG = "Bundle_InstallTask";
    private final yu mInstaller;
    private final boolean mIsStartInstall;
    private final Collection<yi> mUpdateModules;

    public void onInstallCompleted(List<yu.c> list) {
    }

    public void onInstallFailed(List<zj> list) {
    }

    public void onPreInstall() {
    }

    public ModuleInstallTask(yu yuVar, boolean z, Collection<yi> collection) {
        this.mInstaller = yuVar;
        this.mIsStartInstall = z;
        this.mUpdateModules = collection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        onPreInstall();
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(this.mUpdateModules.size());
        ArrayList arrayList3 = new ArrayList();
        Iterator<yi> it = this.mUpdateModules.iterator();
        boolean z = true;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            yi next = it.next();
            zm zmVar = new zm(next.f(), next.i(), next.k());
            try {
                yu.c e = this.mInstaller.e(this.mIsStartInstall, next);
                arrayList2.add(zmVar);
                arrayList.add(e);
            } catch (yu.d e2) {
                arrayList3.add(new zj(zmVar, e2.c(), e2.getCause()));
                if (this.mIsStartInstall) {
                    z = false;
                    break;
                }
                z = false;
            }
        }
        String str = this.mIsStartInstall ? "Start" : "Deferred";
        if (z) {
            onInstallCompleted(arrayList);
            LogUtil.c(TAG, str, " install ", arrayList2.toString(), " success, cost time:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
        } else {
            onInstallFailed(arrayList3);
            LogUtil.a(TAG, str, " install ", arrayList2.toString(), " failed, ", arrayList3.toString(), ", cost time:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
        }
    }
}
