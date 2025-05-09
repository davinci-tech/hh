package com.huawei.updatesdk.service.otaupdate;

import android.content.Intent;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<CheckUpdateCallBack> f10874a;

    public void b(Intent intent) {
        String str;
        WeakReference<CheckUpdateCallBack> weakReference = this.f10874a;
        if (weakReference != null) {
            CheckUpdateCallBack checkUpdateCallBack = weakReference.get();
            if (checkUpdateCallBack != null) {
                checkUpdateCallBack.onUpdateInfo(intent);
                return;
            }
            str = "setUpdateCallBackMsg callBack is null";
        } else {
            str = "setUpdateCallBackMsg updateCallBack is null";
        }
        com.huawei.updatesdk.a.a.a.c("CallbackManager", str);
    }

    public void a(CheckUpdateCallBack checkUpdateCallBack) {
        this.f10874a = new WeakReference<>(checkUpdateCallBack);
    }

    public void a(Intent intent) {
        CheckUpdateCallBack checkUpdateCallBack;
        WeakReference<CheckUpdateCallBack> weakReference = this.f10874a;
        if (weakReference == null || (checkUpdateCallBack = weakReference.get()) == null) {
            return;
        }
        checkUpdateCallBack.onMarketInstallInfo(intent);
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final d f10875a = new d();
    }

    public void a(int i) {
        String str;
        WeakReference<CheckUpdateCallBack> weakReference = this.f10874a;
        if (weakReference != null) {
            CheckUpdateCallBack checkUpdateCallBack = weakReference.get();
            if (checkUpdateCallBack != null) {
                checkUpdateCallBack.onMarketStoreError(i);
                return;
            }
            str = "setGetMarketInfoCallBack callBack is null";
        } else {
            str = "setGetMarketInfoCallBack updateCallBack is null";
        }
        com.huawei.updatesdk.a.a.a.c("CallbackManager", str);
    }

    public static d a() {
        return a.f10875a;
    }
}
