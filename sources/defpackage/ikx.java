package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.IHealthDataOpenInterface;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class ikx {
    private static Context b;
    private static final String e = SecurityConstant.d;

    /* renamed from: a, reason: collision with root package name */
    private IBinder f13420a;
    private ICommonCallback c;
    private IHealthDataOpenInterface d;
    private boolean f;
    private IBinder.DeathRecipient g;
    private ExecutorService j;

    private ikx() {
        this.f = false;
        LogUtil.a("HlthDevApi", "HealthDeveloperApi construct");
        this.j = Executors.newSingleThreadExecutor();
        this.g = new IBinder.DeathRecipient() { // from class: ikx.3
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                ReleaseLogUtil.e("HiH_HlthDevApi", "binderDied setBinder(null)");
                ikx.this.bBA_(null);
            }
        };
    }

    static class a {
        private static final ikx d = new ikx();
    }

    public static ikx a(Context context) {
        LogUtil.a("HlthDevApi", "HealthDeveloperApi getInstance");
        b = context;
        return a.d;
    }

    public void bBA_(final IBinder iBinder) {
        this.j.execute(new Runnable() { // from class: ikx.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("HlthDevApi", "setBinder enter, mApiAidl = ", iBinder);
                if (ikx.this.f13420a != null) {
                    ikx.this.f13420a.unlinkToDeath(ikx.this.g, 0);
                }
                IBinder iBinder2 = iBinder;
                if (iBinder2 != null) {
                    try {
                        iBinder2.linkToDeath(ikx.this.g, 0);
                    } catch (RemoteException e2) {
                        ReleaseLogUtil.c("HiH_HlthDevApi", "setBinder RemoteException = ", e2.getMessage());
                    }
                    ikx.this.f13420a = iBinder;
                    ikx.this.d = IHealthDataOpenInterface.Stub.asInterface(iBinder);
                    if (ikx.this.c != null && !ikx.this.f) {
                        try {
                            ikx.this.c.onResult(0, ipd.b(0));
                        } catch (RemoteException e3) {
                            ReleaseLogUtil.c("HiH_HlthDevApi", "aidl callback remote Exception", LogAnonymous.b((Throwable) e3));
                        }
                    }
                } else {
                    ikx.this.d = null;
                    ikx.this.f13420a = null;
                    ikx.this.c = null;
                    ikx.this.f = false;
                }
                LogUtil.c("HlthDevApi", "mApiAidl = ", ikx.this.d);
            }
        });
    }

    public void d(final long j, final long j2, final IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("HiH_HlthDevApi", "enter getHealthData");
        this.j.execute(new Runnable() { // from class: ikx.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ikx.this.d != null) {
                        ikx.this.d.getHealthData(j, j2, iBaseCommonCallback);
                    } else {
                        ikx.this.c = new ICommonCallback.Stub() { // from class: ikx.5.4
                            @Override // com.huawei.hihealth.ICommonCallback
                            public void onResult(int i, String str) throws RemoteException {
                                ikx.this.d.getHealthData(j, j2, iBaseCommonCallback);
                                ikx.this.f = true;
                            }
                        };
                        ikx.this.b(iBaseCommonCallback);
                    }
                } catch (Exception e2) {
                    ReleaseLogUtil.c("HiH_HlthDevApi", "getHealthData unknown Exception", LogAnonymous.b((Throwable) e2));
                    ikx.this.a(iBaseCommonCallback, 4, ipd.b(4));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IBaseCommonCallback iBaseCommonCallback) {
        Context context = b;
        if (context == null) {
            LogUtil.h("HlthDevApi", "context is null");
            a(iBaseCommonCallback, 4, ipd.b(4));
            return;
        }
        String packageName = context.getPackageName();
        LogUtil.a("HlthDevApi", "sendBroadcast() ", " action == ", "com.huawei.health.open.healthModel.broadcast");
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction("com.huawei.health.open.healthModel.broadcast");
        b.sendBroadcast(intent, e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IBaseCommonCallback iBaseCommonCallback, int i, String str) {
        if (iBaseCommonCallback != null) {
            try {
                iBaseCommonCallback.onResponse(i, str);
            } catch (RemoteException e2) {
                ReleaseLogUtil.c("HiH_HlthDevApi", "notifyCallback RemoteException: ", e2.getMessage());
            }
        }
    }
}
