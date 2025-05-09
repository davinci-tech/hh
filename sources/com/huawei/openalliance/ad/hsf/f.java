package com.huawei.openalliance.ad.hsf;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.hsf.b;
import com.huawei.openalliance.ad.hsf.e;
import com.huawei.openalliance.ad.utils.i;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class f extends e implements ServiceConnection {

    /* renamed from: a, reason: collision with root package name */
    private final Context f6927a;
    private final e.a b;
    private volatile b d;
    private final List<PPSHsfService> c = new ArrayList();
    private AtomicInteger e = new AtomicInteger(1);

    private static int a(int i) {
        if (i != -2) {
            return i != 0 ? 4 : 0;
        }
        return 8;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.e.set(1);
        synchronized (this.c) {
            this.c.clear();
        }
        this.d = null;
        this.b.a(1);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.d = b.a.a(iBinder);
            if (this.d != null && "com.huawei.hsf.internal.CoreService".equals(componentName.getClassName())) {
                int a2 = a(this.d);
                if (a2 == 0) {
                    this.e.set(3);
                    this.b.a();
                    return;
                } else {
                    this.e.set(1);
                    this.b.b(a2);
                    this.f6927a.unbindService(this);
                    return;
                }
            }
            ho.d("PPSHsfApiImpl", "Maybe mCoreService is null or illegal.");
            this.f6927a.unbindService(this);
            this.e.set(1);
            this.b.b(4);
        } catch (Throwable th) {
            ho.c("PPSHsfApiImpl", "PPSHsfApi Service, service error: %s", th.getClass().getSimpleName());
        }
    }

    public final Context c() {
        return this.f6927a;
    }

    @Override // com.huawei.openalliance.ad.hsf.e
    public boolean b() {
        return this.e.get() == 3;
    }

    @Override // com.huawei.openalliance.ad.hsf.e
    public void a() {
        if (this.e.get() != 1) {
            if (this.e.get() == 3) {
                this.b.a();
                return;
            }
            return;
        }
        Context c = c();
        String str = Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE;
        PackageInfo b = i.b(c, Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE);
        if (b == null) {
            Context c2 = c();
            str = Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE_NEW;
            b = i.b(c2, Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE_NEW);
        }
        if (b == null) {
            this.b.b(1);
        } else {
            this.e.set(2);
            b(str);
        }
    }

    @Override // com.huawei.openalliance.ad.hsf.g
    public PPSHsfService a(String str) {
        PPSHsfService pPSHsfService = null;
        if (this.e.get() != 3) {
            return null;
        }
        synchronized (this.c) {
            for (PPSHsfService pPSHsfService2 : this.c) {
                if (str.equals(pPSHsfService2.a())) {
                    pPSHsfService = pPSHsfService2;
                }
            }
        }
        return pPSHsfService;
    }

    private void b(String str) {
        String str2;
        try {
            Intent intent = new Intent("com.huawei.android.hsf.service.CoreService");
            intent.setPackage(str);
            if (!bz.b(this.f6927a)) {
                String e = i.e(this.f6927a, str);
                boolean isEmpty = TextUtils.isEmpty(e);
                ho.b("PPSHsfApiImpl", "is sign empty: %s", Boolean.valueOf(isEmpty));
                if (!isEmpty && !WhiteListPkgList.isTrustApp(this.f6927a, str, e)) {
                    return;
                }
            }
            boolean bindService = this.f6927a.bindService(intent, this, 1);
            ho.b("PPSHsfApiImpl", "bindCoreService: %s", Boolean.valueOf(bindService));
            if (bindService) {
                return;
            }
            this.e.set(1);
            this.b.b(5);
        } catch (SecurityException unused) {
            str2 = "bindService SecurityException";
            ho.c("PPSHsfApiImpl", str2);
            this.e.set(1);
            this.b.b(5);
        } catch (Exception e2) {
            str2 = "bindService " + e2.getClass().getSimpleName();
            ho.c("PPSHsfApiImpl", str2);
            this.e.set(1);
            this.b.b(5);
        }
    }

    private int a(b bVar) {
        int a2;
        if (bVar == null) {
            return 4;
        }
        try {
            synchronized (this.c) {
                this.c.clear();
                a2 = a(bVar.a(this.f6927a.getPackageName(), this.c));
            }
            return a2;
        } catch (RemoteException unused) {
            ho.d("PPSHsfApiImpl", "Failed to call remote interface for querying the HSF services.");
            return 4;
        }
    }

    public f(Context context, e.a aVar) {
        this.f6927a = context;
        this.b = aVar;
    }
}
