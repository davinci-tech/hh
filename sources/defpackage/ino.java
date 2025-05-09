package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.ITrackDataForDeveloper;
import com.huawei.health.ITrackManagerForDeveloper;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class ino {

    /* renamed from: a, reason: collision with root package name */
    private static Context f13474a;
    public static final String c = SecurityConstant.d;
    private IBinder b;
    private ICommonCallback d;
    private ITrackManagerForDeveloper e;
    private IBinder.DeathRecipient g;
    private ArrayList<ITrackDataForDeveloper> h;
    private ExecutorService i;
    private boolean j;

    private ino() {
        this.h = new ArrayList<>();
        this.j = false;
        LogUtil.a("KitSportApi", "HiHealthKitAPI construct");
        this.i = Executors.newSingleThreadExecutor();
        this.g = new IBinder.DeathRecipient() { // from class: ino.1
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                ReleaseLogUtil.e("R_KitSportApi", "setBinder(null)");
                ino.this.bBQ_(null);
            }
        };
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final ino f13481a = new ino();
    }

    public static ino b(Context context) {
        LogUtil.a("KitSportApi", "HiHealthKitAPI getInstance");
        f13474a = context;
        return c.f13481a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        return this.e == null;
    }

    public void bBQ_(final IBinder iBinder) {
        this.i.execute(new Runnable() { // from class: ino.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("KitSportApi", "setBinder enter, mApiAidl = ", iBinder);
                if (ino.this.b != null) {
                    ino.this.b.unlinkToDeath(ino.this.g, 0);
                }
                IBinder iBinder2 = iBinder;
                if (iBinder2 != null) {
                    try {
                        iBinder2.linkToDeath(ino.this.g, 0);
                    } catch (RemoteException e) {
                        ReleaseLogUtil.c("R_KitSportApi", "linkToDeath RemoteException = ", e.getMessage());
                    }
                    ino.this.b = iBinder;
                    ino.this.e = ITrackManagerForDeveloper.Stub.asInterface(iBinder);
                    if (ino.this.d != null && !ino.this.j) {
                        try {
                            ino.this.d.onResult(0, ipd.b(0));
                        } catch (RemoteException e2) {
                            ReleaseLogUtil.c("R_KitSportApi", "setBinder callback RemoteException: ", e2.getMessage());
                        }
                    }
                    int size = ino.this.h.size();
                    LogUtil.a("KitSportApi", "setBinder listSize = ", Integer.valueOf(size));
                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            ino inoVar = ino.this;
                            inoVar.a((ITrackDataForDeveloper) inoVar.h.get(i), false);
                        }
                    }
                } else {
                    ino.this.e = null;
                    ino.this.b = null;
                    ino.this.d = null;
                    ino.this.j = false;
                }
                LogUtil.c("KitSportApi", "mApiAidl = ", ino.this.e);
            }
        });
    }

    public void c(ITrackDataForDeveloper iTrackDataForDeveloper) {
        if (iTrackDataForDeveloper != null) {
            a(iTrackDataForDeveloper, d());
        } else {
            ReleaseLogUtil.d("R_KitSportApi", "registerRealTimeSportCallback callback is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final ITrackDataForDeveloper iTrackDataForDeveloper, final boolean z) {
        this.i.execute(new Runnable() { // from class: ino.5
            @Override // java.lang.Runnable
            public void run() {
                if (!ino.this.h.contains(iTrackDataForDeveloper)) {
                    ino.this.h.add(iTrackDataForDeveloper);
                }
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    if (!z) {
                        ino.this.e.registerRealtimeSportCallback(iTrackDataForDeveloper);
                        LogUtil.a("KitSportApi", "registerRealTimeSportCallback cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    } else {
                        ReleaseLogUtil.c("R_KitSportApi", "registerCallback AIDL isNull");
                    }
                } catch (RemoteException e) {
                    ReleaseLogUtil.c("R_KitSportApi", "registerRealTimeSportCallback remoteException = ", e.getMessage());
                } catch (Exception unused) {
                    ReleaseLogUtil.c("R_KitSportApi", "registerRealTimeSportCallback exception");
                }
            }
        });
    }

    public void d(final ITrackDataForDeveloper iTrackDataForDeveloper, final ICommonCallback iCommonCallback) {
        if (iTrackDataForDeveloper == null || iCommonCallback == null) {
            ReleaseLogUtil.c("R_KitSportApi", "unregisterRealTimeSportCallback input illegal");
            if (iCommonCallback != null) {
                try {
                    LogUtil.a("KitSportApi", "callback == null,result= ", 0);
                    iCommonCallback.onResult(0, null);
                    return;
                } catch (RemoteException e) {
                    ReleaseLogUtil.c("R_KitSportApi", "unregisterRealTimeSportCallback RemoteException: ", e.getMessage());
                    return;
                }
            }
            return;
        }
        LogUtil.a("KitSportApi", "unregisterRealTimeSportCallback setBinder listSize = ", Integer.valueOf(this.h.size()));
        this.i.execute(new Runnable() { // from class: ino.8
            @Override // java.lang.Runnable
            public void run() {
                Exception e2;
                int i;
                RemoteException e3;
                boolean remove = ino.this.h.remove(iTrackDataForDeveloper);
                long currentTimeMillis = System.currentTimeMillis();
                try {
                } catch (RemoteException e4) {
                    e3 = e4;
                    i = 1;
                } catch (Exception e5) {
                    e2 = e5;
                    i = 1;
                }
                if (!ino.this.d()) {
                    i = !ino.this.e.unregisterRealtimeSportCallback(iTrackDataForDeveloper) ? 1 : 0;
                    try {
                        try {
                            LogUtil.a("KitSportApi", "unregisterRealTimeSportCallback cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        } catch (RemoteException e6) {
                            e3 = e6;
                            ReleaseLogUtil.c("R_KitSportApi", "unregisterRealTimeSportCallback remoteException = ", e3.getMessage());
                            LogUtil.a("KitSportApi", "result= ", Integer.valueOf(i));
                            iCommonCallback.onResult(i, null);
                            return;
                        } catch (Exception e7) {
                            e2 = e7;
                            ReleaseLogUtil.c("R_KitSportApi", "unregisterRealTimeSportCallback exception: ", e2.getMessage());
                            LogUtil.a("KitSportApi", "result= ", Integer.valueOf(i));
                            iCommonCallback.onResult(i, null);
                            return;
                        }
                        LogUtil.a("KitSportApi", "result= ", Integer.valueOf(i));
                        iCommonCallback.onResult(i, null);
                        return;
                    } catch (RemoteException e8) {
                        ReleaseLogUtil.c("R_KitSportApi", "unregisterRealTimeSportCallback RemoteException: ", e8.getMessage());
                        return;
                    }
                }
                ReleaseLogUtil.c("R_KitSportApi", "unregisster mApiAidl == null");
                if (remove) {
                    iCommonCallback.onResult(0, null);
                } else {
                    iCommonCallback.onResult(1, null);
                }
            }
        });
    }

    public void d(final int i, final IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("R_KitSportApi", "enter start Sport");
        this.i.execute(new Runnable() { // from class: ino.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ino.this.e != null) {
                        ino.this.e.startSport(i, iBaseCommonCallback);
                    } else {
                        ino.this.d = new ICommonCallback.Stub() { // from class: ino.6.3
                            @Override // com.huawei.hihealth.ICommonCallback
                            public void onResult(int i2, String str) throws RemoteException {
                                ino.this.e.startSport(i, iBaseCommonCallback);
                                ino.this.j = true;
                            }
                        };
                        ino.this.b(iBaseCommonCallback);
                    }
                } catch (Exception e) {
                    ReleaseLogUtil.c("R_KitSportApi", "startSport unknown Exception: ", e.getMessage());
                    ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                }
            }
        });
    }

    public void c(final StartSportParam startSportParam, final IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("R_KitSportApi", "enter startSportEnhance");
        if (startSportParam.getInt("isBackground") == CharacteristicConstant.SportModeType.FOREGROUND_SPORT_MODE.getType()) {
            String a2 = iwi.a(f13474a);
            LogUtil.a("KitSportApi", "startSportEnhance callingPackageName:" + a2);
            if (!"com.huawei.health".equals(a2) && !BaseApplication.APP_PACKAGE_GOOGLE_HEALTH.equals(a2)) {
                startSportParam.putBoolean("ExitApp", true);
                ReleaseLogUtil.e("R_KitSportApi", "startSportEnhance KEY_EXIT_APP: true");
            }
        }
        this.i.execute(new Runnable() { // from class: ino.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ino.this.e != null) {
                        ino.this.e.startSportEnhance(startSportParam, iBaseCommonCallback);
                    } else {
                        ino.this.d = new ICommonCallback.Stub() { // from class: ino.10.3
                            @Override // com.huawei.hihealth.ICommonCallback
                            public void onResult(int i, String str) throws RemoteException {
                                ino.this.e.startSportEnhance(startSportParam, iBaseCommonCallback);
                                ino.this.j = true;
                            }
                        };
                        ino.this.b(iBaseCommonCallback);
                    }
                } catch (Exception e) {
                    ReleaseLogUtil.e("R_KitSportApi", "startSportEnhance unknown Exception: ", e.getMessage());
                    ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IBaseCommonCallback iBaseCommonCallback) {
        Context context = f13474a;
        if (context == null) {
            ReleaseLogUtil.d("R_KitSportApi", "sendStartSportBroadcast context is null");
            c(iBaseCommonCallback, 4, ipd.b(4));
            return;
        }
        String packageName = context.getPackageName();
        LogUtil.a("KitSportApi", "sendBroadcast() ", " action == ", "com.huawei.health.track.broadcast");
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction("com.huawei.health.track.broadcast");
        intent.putExtra("command_type", "com.huawei.health.background.start.sport");
        f13474a.sendBroadcast(intent, c);
    }

    public void a(final IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("R_KitSportApi", "enter stop Sport");
        this.i.execute(new Runnable() { // from class: ino.9
            @Override // java.lang.Runnable
            public void run() {
                if (ino.this.e != null) {
                    try {
                        ino.this.e.stopSport(iBaseCommonCallback);
                        return;
                    } catch (Exception e) {
                        ReleaseLogUtil.d("R_KitSportApi", "stopSport aidl Exception: ", e.getMessage());
                        ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                        return;
                    }
                }
                ino.this.d = new ICommonCallback.Stub() { // from class: ino.9.1
                    @Override // com.huawei.hihealth.ICommonCallback
                    public void onResult(int i, String str) throws RemoteException {
                        ino.this.e.stopSport(iBaseCommonCallback);
                        ino.this.j = true;
                    }
                };
                ino.this.b(iBaseCommonCallback);
            }
        });
    }

    public void d(final IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("R_KitSportApi", "enter pause Sport");
        this.i.execute(new Runnable() { // from class: ino.7
            @Override // java.lang.Runnable
            public void run() {
                if (ino.this.e != null) {
                    try {
                        ino.this.e.pauseSport(iBaseCommonCallback);
                        return;
                    } catch (Exception e) {
                        ReleaseLogUtil.d("R_KitSportApi", "pauseSport Exception: ", e.getMessage());
                        ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                        return;
                    }
                }
                ReleaseLogUtil.d("R_KitSportApi", "pauseSport aidl is null");
                ino.this.c(iBaseCommonCallback, 1, ipd.b(1));
            }
        });
    }

    public void c(final IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("R_KitSportApi", "enter resume Sport");
        this.i.execute(new Runnable() { // from class: ino.15
            @Override // java.lang.Runnable
            public void run() {
                if (ino.this.e != null) {
                    try {
                        ino.this.e.resumeSport(iBaseCommonCallback);
                        return;
                    } catch (Exception e) {
                        ReleaseLogUtil.d("R_KitSportApi", "resumeSport Exception: ", e.getMessage());
                        ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                        return;
                    }
                }
                ReleaseLogUtil.d("R_KitSportApi", "resumeSport aidl is null");
                ino.this.c(iBaseCommonCallback, 1, ipd.b(1));
            }
        });
    }

    public void a(final String str, final IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("KitSportApi", "enter sendDeviceControlinstruction");
        this.i.execute(new Runnable() { // from class: ino.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ino.this.e != null) {
                        ino.this.e.sendDeviceControlinstruction(str, iBaseCommonCallback);
                    } else {
                        ReleaseLogUtil.d("R_KitSportApi", "sendDeviceControlinstructionaidl is null");
                        ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                    }
                } catch (Exception e) {
                    ReleaseLogUtil.d("R_KitSportApi", "sendDeviceControlinstruction unknown Exception: ", e.getMessage());
                    ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                }
            }
        });
    }

    public void c(final int i, final IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("R_KitSportApi", "enter connectSportDevice");
        this.i.execute(new Runnable() { // from class: ino.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ino.this.e != null) {
                        ino.this.e.connectSportDevice(i, iBaseCommonCallback);
                    } else {
                        ino.this.d = new ICommonCallback.Stub() { // from class: ino.4.3
                            @Override // com.huawei.hihealth.ICommonCallback
                            public void onResult(int i2, String str) throws RemoteException {
                                ReleaseLogUtil.e("R_KitSportApi", "connectSportDevice code = ", Integer.valueOf(i2), ", message = ", str);
                                ino.this.e.connectSportDevice(i, iBaseCommonCallback);
                                ino.this.j = true;
                            }
                        };
                        ino.this.b(iBaseCommonCallback);
                    }
                } catch (Exception e) {
                    ReleaseLogUtil.d("R_KitSportApi", "connectSportDevice unknown Exception: ", e.getMessage());
                    ino.this.c(iBaseCommonCallback, 4, ipd.b(4));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IBaseCommonCallback iBaseCommonCallback, int i, String str) {
        if (iBaseCommonCallback != null) {
            try {
                iBaseCommonCallback.onResponse(i, str);
            } catch (RemoteException e) {
                ReleaseLogUtil.e("R_KitSportApi", "notifyCallback RemoteException: ", e.getMessage());
            }
        }
    }
}
