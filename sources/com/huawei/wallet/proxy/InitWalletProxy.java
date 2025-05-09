package com.huawei.wallet.proxy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

/* loaded from: classes9.dex */
public class InitWalletProxy implements InitWalletApi {
    private static volatile InitWalletProxy e;

    /* renamed from: a, reason: collision with root package name */
    private InitWalletApi f10880a = a();

    private InitWalletProxy() throws ClassNotFoundException {
    }

    private InitWalletApi e() {
        try {
            if (this.f10880a == null) {
                this.f10880a = a();
            }
        } catch (ClassNotFoundException unused) {
            this.f10880a = null;
        }
        return this.f10880a;
    }

    private InitWalletApi a() throws ClassNotFoundException {
        return (InitWalletApi) d("com.huawei.nfc.carrera.wear.InitWalletApiImpl");
    }

    private static Object d(String str) throws ClassNotFoundException {
        try {
            return e(str).newInstance();
        } catch (IllegalAccessException | InstantiationException unused) {
            return null;
        }
    }

    private static Class<?> e(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    public static InitWalletProxy c() throws ClassNotFoundException {
        InitWalletProxy initWalletProxy;
        if (e == null) {
            synchronized (InitWalletProxy.class) {
                if (e == null) {
                    e = new InitWalletProxy();
                }
                initWalletProxy = e;
            }
            return initWalletProxy;
        }
        return e;
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public void initBaseConfig(Context context) {
        if (e() != null) {
            this.f10880a.initBaseConfig(context);
        }
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public boolean isInitBaseCommonContext() {
        if (e() != null) {
            return this.f10880a.isInitBaseCommonContext();
        }
        return true;
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public void receiverData(String str, String str2) {
        if (e() != null) {
            this.f10880a.receiverData(str, str2);
        }
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public void initWearWallet(Context context) {
        if (e() != null) {
            this.f10880a.initWearWallet(context);
        }
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public void reverseWalletStartDeepLink(String str) {
        if (e() != null) {
            this.f10880a.reverseWalletStartDeepLink(str);
        }
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public void walletAccountRemove(Context context, Intent intent) {
        if (e() != null) {
            this.f10880a.walletAccountRemove(context, intent);
        }
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public IBinder getServiceBinder(Context context, String str, Intent intent) {
        if (e() != null) {
            return this.f10880a.getServiceBinder(context, str, intent);
        }
        return null;
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public void serviceOnCreate(Context context, String str) {
        if (e() != null) {
            this.f10880a.serviceOnCreate(context, str);
        }
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public void serviceOnDestroy(Context context, String str) {
        if (e() != null) {
            this.f10880a.serviceOnDestroy(context, str);
        }
    }

    @Override // com.huawei.wallet.proxy.InitWalletApi
    public Object getContentProvider(Context context, String str, Uri uri) {
        if (e() != null) {
            return this.f10880a.getContentProvider(context, str, uri);
        }
        return new Object();
    }
}
