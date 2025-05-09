package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.HiAppInfo;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.IDetectCommonCallback;
import com.huawei.health.IKitWearAIDL;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.IReadCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IWriteCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.datatypes.HeartDeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearkit.IDetectCommonCallback;
import com.huawei.wearkit.IWearCommonCallback;
import com.huawei.wearkit.IWearReadCallback;
import com.huawei.wearkit.IWearWriteCallback;
import com.huawei.wearkit.model.HealthLogOption;
import defpackage.ins;
import health.compact.a.DeviceUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ins {
    private static Context b;
    private static final byte[] e = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private volatile IKitWearAIDL f13488a;
    private IBinder.DeathRecipient c;
    private volatile IBinder d;
    private volatile IWriteCallback i;
    private ExecutorService j;

    /* synthetic */ ins(AnonymousClass5 anonymousClass5) {
        this();
    }

    /* renamed from: ins$5, reason: invalid class name */
    class AnonymousClass5 implements IBinder.DeathRecipient {
        AnonymousClass5() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ReleaseLogUtil.e("HiH_WearAPI", "AIDL binderDied.");
            ins.this.bBT_(null);
            if (lcu.e()) {
                ReleaseLogUtil.e("HiH_WearAPI", "PhoneService needs to be started");
                ins.this.j.execute(new Runnable() { // from class: ios
                    @Override // java.lang.Runnable
                    public final void run() {
                        ins.AnonymousClass5.this.b();
                    }
                });
            }
        }

        /* synthetic */ void b() {
            ins.this.d();
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final ins f13502a = new ins(null);
    }

    private ins() {
        this.i = null;
        this.c = new AnonymousClass5();
        LogUtil.a("WearAPI", "HiHealthKitAPI construct");
        this.j = Executors.newSingleThreadExecutor();
    }

    public static ins a(Context context) {
        LogUtil.a("WearAPI", "HiHealthKitAPI getInstance");
        b = context;
        return b.f13502a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        e(null);
    }

    private void e(String str) {
        if (this.f13488a != null) {
            ReleaseLogUtil.c("HiH_WearAPI", "mApiAidl is not null");
            return;
        }
        if (!DeviceUtil.a() && TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("HiH_WearAPI", "no bind device");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package", str);
        LogUtil.a("WearAPI", "start to invoke phone service");
        int i = 0;
        DeviceUtil.fbV_(b, false, bundle);
        while (true) {
            if (i >= 70) {
                break;
            }
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "InterruptedException occour");
            }
            if (this.f13488a != null) {
                LogUtil.a("WearAPI", " mApiAidl not null ,check times:", Integer.valueOf(i));
                break;
            }
            i++;
        }
        ReleaseLogUtil.e("HiH_WearAPI", "get phone service binder success");
    }

    public void b(final IRealTimeDataCallback iRealTimeDataCallback) {
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "getDeviceList:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: ioq
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.a(iRealTimeDataCallback);
                }
            });
        }
    }

    /* synthetic */ void a(final IRealTimeDataCallback iRealTimeDataCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "getDeviceList:mApiAidl is null");
                iRealTimeDataCallback.onChange(0, "[]");
            } else {
                this.f13488a.getDeviceList(new IBaseCommonCallback.Stub() { // from class: ins.12
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "getDeviceList onResponse err_code:", Integer.valueOf(i));
                        iRealTimeDataCallback.onChange(i, str);
                    }
                });
                LogUtil.a("WearAPI", "getDeviceList cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "getDeviceList Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "getDeviceList,RemoteException: ", e2.getMessage());
            }
        }
    }

    public void h(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        ReleaseLogUtil.e("HiH_WearAPI", "sendDeviceCommand enter");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "sendDeviceCommand:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: ioa
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.b(iRealTimeDataCallback, str);
                }
            });
        }
    }

    /* synthetic */ void b(final IRealTimeDataCallback iRealTimeDataCallback, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "sendDeviceCommand:mApiAidl is null");
                iRealTimeDataCallback.onResult(1);
            } else {
                this.f13488a.sendDeviceCommand(str, new IBaseCommonCallback.Stub() { // from class: ins.17
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "sendDeviceCommand onResponse err_code:", Integer.valueOf(i));
                        iRealTimeDataCallback.onChange(i, str2);
                    }
                });
                LogUtil.a("WearAPI", "sendDeviceCommand cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "sendDeviceCommand Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "sendDeviceCommand,RemoteException: ", e2.getMessage());
            }
        }
    }

    public void g(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "startReadingAtrial:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: iny
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.b(str, iRealTimeDataCallback);
                }
            });
        }
    }

    /* synthetic */ void b(String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "startReadingAtrial:mApiAidl is null");
                c(str, 1, iRealTimeDataCallback);
            } else {
                this.f13488a.registerSingleAtrialCallback(new IBaseCommonCallback.Stub() { // from class: ins.23
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "startReadingAtrial onResponse err_code:", Integer.valueOf(i));
                        iRealTimeDataCallback.onChange(i, str2);
                    }
                });
                LogUtil.a("WearAPI", "startReadingAtrial cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "startReadingAtrial Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "startReadingAtrial,RemoteException: ", e2.getMessage());
            }
        }
    }

    public void k(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "stopReadingAtrial:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: iof
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.a(str, iRealTimeDataCallback);
                }
            });
        }
    }

    /* synthetic */ void a(String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "stopReadingAtrial:mApiAidl is null");
                c(str, 1, iRealTimeDataCallback);
            } else {
                this.f13488a.unRegisterSingleAtrialCallback(new IBaseCommonCallback.Stub() { // from class: ins.21
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "stopReadingAtrial onResponse err_code:", Integer.valueOf(i));
                        iRealTimeDataCallback.onResult(i);
                    }
                });
                LogUtil.a("WearAPI", "stopReadingAtrial cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.d("HiH_WearAPI", "stopReadingAtrial Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException e3) {
                ReleaseLogUtil.d("HiH_WearAPI", "stopReadingAtrial,RemoteException: ", e3.getMessage());
            }
        }
    }

    public void j(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        ReleaseLogUtil.e("HiH_WearAPI", "startReadingHeatRate enter");
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "startReadingHeatRate callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: iog
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.e(str, iRealTimeDataCallback);
                }
            });
        }
    }

    /* synthetic */ void e(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "startReadingHeatRate:mApiAidl is null");
                c(str, 1, iRealTimeDataCallback);
            } else {
                this.f13488a.getDeviceList(new IBaseCommonCallback.Stub() { // from class: ins.24
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "startReadingHeatRate getDeviceList onResponse errCode:", Integer.valueOf(i));
                        if (ins.this.b(str, iRealTimeDataCallback, str2, "supportHeartRate")) {
                            ins.this.f13488a.getRealTimeData(str, 1, 1, new IBaseCommonCallback.Stub() { // from class: ins.24.1
                                @Override // com.huawei.health.IBaseCommonCallback
                                public void onResponse(int i2, String str3) throws RemoteException {
                                    ReleaseLogUtil.e("HiH_WearAPI", "startReadingHeatRate onResponse errorCode:", Integer.valueOf(i2), ", reason: ", str3);
                                    ins.this.d(i2, str3, iRealTimeDataCallback);
                                }
                            });
                        } else {
                            ReleaseLogUtil.d("HiH_WearAPI", "device not support real-heart-rate");
                        }
                    }
                });
                LogUtil.a("WearAPI", "startReadingHeatRate cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "startReadingHeatRate Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "startReadingHeatRate,RemoteException: ", e2.getMessage());
            }
        }
    }

    private void c(String str, int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        if (iqz.d(str, 4)) {
            iRealTimeDataCallback.onResult(150);
        } else {
            iRealTimeDataCallback.onResult(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str, IRealTimeDataCallback iRealTimeDataCallback) {
        if (iRealTimeDataCallback != null) {
            try {
                if (!"OpenOrClose state".equals(str) && str != null) {
                    if (new JSONObject(str).getInt("hr_info") == 255) {
                        iRealTimeDataCallback.onChange(5, ipd.b(5));
                    } else {
                        iRealTimeDataCallback.onChange(i, str);
                    }
                }
                iRealTimeDataCallback.onResult(i);
            } catch (RemoteException e2) {
                ReleaseLogUtil.c("HiH_WearAPI", "notifyHeartRateChanged RemoteException = ", e2.getMessage());
            } catch (JSONException e3) {
                ReleaseLogUtil.c("HiH_WearAPI", "notifyHeartRateChanged JSONException = ", e3.getMessage());
            }
        }
    }

    public void m(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "stopReadingHeartRate:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: inw
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.c(str, iRealTimeDataCallback);
                }
            });
        }
    }

    /* synthetic */ void c(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "stopReadingHeartRate:mApiAidl is null");
                c(str, 1, iRealTimeDataCallback);
            } else {
                this.f13488a.getDeviceList(new IBaseCommonCallback.Stub() { // from class: ins.25
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "stopReadingHeartRate getDeviceList onResponse errCode:", Integer.valueOf(i));
                        if (ins.this.b(str, iRealTimeDataCallback, str2, "supportHeartRate")) {
                            ins.this.f13488a.getRealTimeData(str, 1, 2, new IBaseCommonCallback.Stub() { // from class: ins.25.5
                                @Override // com.huawei.health.IBaseCommonCallback
                                public void onResponse(int i2, String str3) throws RemoteException {
                                    ReleaseLogUtil.e("HiH_WearAPI", "stopReadingHeartRate onResponse errorCode:", Integer.valueOf(i2));
                                    iRealTimeDataCallback.onResult(i2);
                                }
                            });
                        } else {
                            ReleaseLogUtil.d("HiH_WearAPI", "device not support real-heart-rate");
                        }
                    }
                });
                LogUtil.a("WearAPI", "stopReadingHeartRate cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.e("HiH_WearAPI", "stopReadingHeartRate Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException e3) {
                ReleaseLogUtil.e("HiH_WearAPI", "stopReadingHeartRate , RemoteException: ", e3.getMessage());
            }
        }
    }

    public void i(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "startReadingRRI:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: ioc
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.d(str, iRealTimeDataCallback);
                }
            });
        }
    }

    /* synthetic */ void d(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "startReadingRRI:mApiAidl is null");
                c(str, 1, iRealTimeDataCallback);
            } else {
                this.f13488a.getDeviceList(new IBaseCommonCallback.Stub() { // from class: ins.22
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "startReadingRRI getDeviceList onResponse errCode:", Integer.valueOf(i));
                        if (ins.this.b(str, iRealTimeDataCallback, str2, "supportRRI")) {
                            ins.this.f13488a.getRealTimeData(str, 1, 3, new IBaseCommonCallback.Stub() { // from class: ins.22.2
                                @Override // com.huawei.health.IBaseCommonCallback
                                public void onResponse(int i2, String str3) throws RemoteException {
                                    ReleaseLogUtil.e("HiH_WearAPI", "startReadingRRI onResponse errorCode:", Integer.valueOf(i2), ", reason: ", str3);
                                    if ("OpenOrClose state".equals(str3) || str3 == null) {
                                        iRealTimeDataCallback.onResult(i2);
                                    } else {
                                        iRealTimeDataCallback.onChange(i2, str3);
                                    }
                                }
                            });
                        } else {
                            ReleaseLogUtil.d("HiH_WearAPI", "device not support RRI");
                        }
                    }
                });
                LogUtil.a("WearAPI", "startReadingRRI cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "startReadingRRI Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException e3) {
                ReleaseLogUtil.c("HiH_WearAPI", "startReadingRRI , RemoteException: ", e3.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str, IRealTimeDataCallback iRealTimeDataCallback, String str2, String str3) throws RemoteException {
        List list = (List) ((List) HiJsonUtil.b(str2, new TypeToken<List<HeartDeviceInfo>>() { // from class: ins.29
        }.getType())).stream().filter(new Predicate() { // from class: inz
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ins.a((HeartDeviceInfo) obj);
            }
        }).collect(Collectors.toList());
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                DeviceCapability deviceCapability = (DeviceCapability) HiJsonUtil.b(((HeartDeviceInfo) it.next()).getDeviceCapability(), new TypeToken<DeviceCapability>() { // from class: ins.4
                }.getType());
                if ("supportRRI".equals(str3) && deviceCapability.isSupportPressAutoMonitor()) {
                    return true;
                }
                if ("supportHeartRate".equals(str3) && deviceCapability.isSupportHeartRateInfo()) {
                    return true;
                }
            }
            LogUtil.h("WearAPI", "isSupportInvoked: device unsupported get real time data");
            iRealTimeDataCallback.onResult(5);
            return false;
        }
        LogUtil.h("WearAPI", "isSupportInvoked: device not connected");
        c(str, 5, iRealTimeDataCallback);
        return false;
    }

    static /* synthetic */ boolean a(HeartDeviceInfo heartDeviceInfo) {
        return heartDeviceInfo.getDeviceConnectState() == 2;
    }

    public void o(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        if (iRealTimeDataCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "stopReadingRRI:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: ioi
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.f(str, iRealTimeDataCallback);
                }
            });
        }
    }

    /* synthetic */ void f(final String str, final IRealTimeDataCallback iRealTimeDataCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "stopReadingRRI:mApiAidl is null");
                c(str, 1, iRealTimeDataCallback);
            } else {
                this.f13488a.getDeviceList(new IBaseCommonCallback.Stub() { // from class: ins.2
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "stopReadingRRI getDeviceList onResponse errCode:", Integer.valueOf(i));
                        if (ins.this.b(str, iRealTimeDataCallback, str2, "supportRRI")) {
                            ins.this.f13488a.getRealTimeData(str, 1, 4, new IBaseCommonCallback.Stub() { // from class: ins.2.4
                                @Override // com.huawei.health.IBaseCommonCallback
                                public void onResponse(int i2, String str3) throws RemoteException {
                                    ReleaseLogUtil.e("HiH_WearAPI", "stopReadingRRI onResponse errorCode:", Integer.valueOf(i2));
                                    iRealTimeDataCallback.onResult(i2);
                                }
                            });
                        } else {
                            ReleaseLogUtil.d("HiH_WearAPI", "device not support RRI");
                        }
                    }
                });
                LogUtil.a("WearAPI", "stopReadingRRI cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "stopReadingRRI Exception: ", e2.getMessage());
            try {
                iRealTimeDataCallback.onResult(1);
            } catch (RemoteException unused) {
                ReleaseLogUtil.d("HiH_WearAPI", "stopReadingRRI , RemoteException: ", e2.getMessage());
            }
        }
    }

    public void e(final String str, final String str2, final byte[] bArr, final String str3, final IWriteCallback iWriteCallback) {
        if (iWriteCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "writeToWear:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: iod
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.e(iWriteCallback, str, str2, bArr, str3);
                }
            });
        }
    }

    /* synthetic */ void e(final IWriteCallback iWriteCallback, String str, String str2, byte[] bArr, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        if (this.f13488a == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "writeToWear:mApiAidl is null");
            c(iWriteCallback, 1, ipd.b(1));
            return;
        }
        try {
            this.f13488a.writeToWear(str, str2, bArr, str3, new IBaseCommonCallback.Stub() { // from class: ins.1
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str4) throws RemoteException {
                    ReleaseLogUtil.e("HiH_WearAPI", "writeToWear onResponse err_code:", Integer.valueOf(i));
                    if (i == 200 && ins.this.i == null) {
                        ins.this.i = iWriteCallback;
                        LogUtil.a("WearAPI", "Put mWriteCallback.");
                    } else if (i == 0 || i == 1 || i == 1025) {
                        ins.this.i = null;
                        LogUtil.a("WearAPI", "Remove mWriteCallback.");
                    } else {
                        LogUtil.a("WearAPI", "ErrorCode = ", Integer.valueOf(i), " no processing.");
                    }
                    iWriteCallback.onResult(i, str4);
                }
            });
            LogUtil.a("WearAPI", "writeToWear cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "writeToWear RemoteException: ", e2.getMessage());
            this.i = null;
            c(iWriteCallback, 1, ipd.b(1));
        }
    }

    private void c(IWriteCallback iWriteCallback, int i, String str) {
        if (iWriteCallback != null) {
            try {
                iWriteCallback.onResult(i, str);
                return;
            } catch (RemoteException e2) {
                ReleaseLogUtil.c("HiH_WearAPI", "notifyWriteCallback RemoteException: ", e2.getMessage());
                return;
            }
        }
        LogUtil.h("WearAPI", "writeCallback is null.");
    }

    public void d(final String str, final String str2, final byte[] bArr, final String str3, final IWearWriteCallback iWearWriteCallback) {
        if (iWearWriteCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "writeToWear:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: iob
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.e(iWearWriteCallback, str, str2, bArr, str3);
                }
            });
        }
    }

    /* synthetic */ void e(final IWearWriteCallback iWearWriteCallback, String str, String str2, byte[] bArr, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "writeToWear:mApiAidl is null");
                iWearWriteCallback.onResult(1, ipd.b(1));
            } else {
                this.f13488a.writeToWear(str, str2, bArr, str3, new IBaseCommonCallback.Stub() { // from class: ins.3
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str4) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "writeToWear onResponse err_code:", Integer.valueOf(i));
                        iWearWriteCallback.onResult(i, str4);
                    }
                });
                LogUtil.a("WearAPI", "writeToWear cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "writeToWear RemoteException: ", e2.getMessage());
            try {
                iWearWriteCallback.onResult(1, ipd.b(1));
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "writeToWear , RemoteException: ", e2.getMessage());
            }
        }
    }

    public void c(final String str, final String str2, final IReadCallback iReadCallback) {
        if (iReadCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "readFromWear:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: ior
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.d(iReadCallback, str, str2);
                }
            });
        }
    }

    /* synthetic */ void d(final IReadCallback iReadCallback, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "readFromWear:mApiAidl is null");
                iReadCallback.onResult(1, ipd.b(1), e);
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.f13488a.readFromWear(str, str2, new IBaseCommonCallback.Stub() { // from class: ins.6
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str3) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "readFromWear onResponse err_code:", Integer.valueOf(i));
                        iReadCallback.onResult(i, str3, ins.e);
                    }
                });
                LogUtil.a("WearAPI", "readFromWear cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                return;
            }
            iReadCallback.onResult(1, "inputType or fileDescriptionString is empty", e);
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "readFromWear Exception: ", e2.getMessage());
            try {
                iReadCallback.onResult(1, ipd.b(1), e);
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "readFromWear , RemoteException: ", e2.getMessage());
            }
        }
    }

    public void b(final String str, final String str2, final IWearReadCallback iWearReadCallback) {
        if (iWearReadCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "readFromWear:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: ioj
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.e(iWearReadCallback, str, str2);
                }
            });
        }
    }

    /* synthetic */ void e(final IWearReadCallback iWearReadCallback, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "readFromWear:mApiAidl is null");
                iWearReadCallback.onResult(1, ipd.b(1), e);
            } else {
                this.f13488a.readFromWear(str, str2, new IBaseCommonCallback.Stub() { // from class: ins.7
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str3) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "readFromWear onResponse err_code:", Integer.valueOf(i));
                        iWearReadCallback.onResult(i, str3, ins.e);
                    }
                });
                LogUtil.a("WearAPI", "readFromWear cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "readFromWear RemoteException: ", e2.getMessage());
            try {
                iWearReadCallback.onResult(1, ipd.b(1), e);
            } catch (RemoteException e3) {
                ReleaseLogUtil.c("HiH_WearAPI", "readFromWear callback, RemoteException: ", e3.getMessage());
            }
        }
    }

    public void d(final HiAppInfo hiAppInfo, final String str, final String str2, final ICommonCallback iCommonCallback) {
        if (iCommonCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "pushMsgToWearable:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: ioh
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.e(iCommonCallback, hiAppInfo, str, str2);
                }
            });
        }
    }

    /* synthetic */ void e(final ICommonCallback iCommonCallback, HiAppInfo hiAppInfo, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "pushMsgToWearable:mApiAidl is null");
                iCommonCallback.onResult(1, ipd.b(1));
            } else {
                this.f13488a.pushMsgToWearable(hiAppInfo, str, str2, new IBaseCommonCallback.Stub() { // from class: ins.10
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str3) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "pushMsgToWearable onResponse err_code:", Integer.valueOf(i));
                        iCommonCallback.onResult(i, str3);
                    }
                });
                LogUtil.a("WearAPI", "pushMsgToWearable cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "pushMsgToWearable RemoteException: ", e2.getMessage());
            try {
                iCommonCallback.onResult(1, ipd.b(1));
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "pushMsgToWearable , RemoteException: ", e2.getMessage());
            }
        }
    }

    public void e(final HiAppInfo hiAppInfo, final String str, final String str2, final IWearCommonCallback iWearCommonCallback) {
        if (iWearCommonCallback == null) {
            ReleaseLogUtil.d("HiH_WearAPI", "pushMsgToWearable:callback is null");
        } else {
            this.j.execute(new Runnable() { // from class: iok
                @Override // java.lang.Runnable
                public final void run() {
                    ins.this.e(iWearCommonCallback, hiAppInfo, str, str2);
                }
            });
        }
    }

    /* synthetic */ void e(final IWearCommonCallback iWearCommonCallback, HiAppInfo hiAppInfo, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "pushMsgToWearable:mApiAidl is null");
                iWearCommonCallback.onResult(1, ipd.b(1));
            } else {
                this.f13488a.pushMsgToWearable(hiAppInfo, str, str2, new IBaseCommonCallback.Stub() { // from class: ins.9
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str3) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "pushMsgToWearable onResponse err_code:", Integer.valueOf(i));
                        iWearCommonCallback.onResult(i, str3);
                    }
                });
                LogUtil.a("WearAPI", "pushMsgToWearable cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "pushMsgToWearable RemoteException: ", e2.getMessage());
            try {
                iWearCommonCallback.onResult(1, ipd.b(1));
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "pushMsgToWearable , RemoteException: ", e2.getMessage());
            }
        }
    }

    public void e(final String str, final ICommonCallback iCommonCallback) {
        this.j.execute(new Runnable() { // from class: ioe
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.b(iCommonCallback, str);
            }
        });
    }

    /* synthetic */ void b(final ICommonCallback iCommonCallback, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "getSwitch:mApiAidl is null");
                iCommonCallback.onResult(1, ipd.b(1));
            } else {
                this.f13488a.getSwitch(str, new IBaseCommonCallback.Stub() { // from class: ins.8
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "getSwitch onResponse err_code:", Integer.valueOf(i));
                        iCommonCallback.onResult(i, str2);
                    }
                });
                LogUtil.a("WearAPI", "getSwitch cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "getSwitch Exception: ", e2.getMessage());
            try {
                iCommonCallback.onResult(1, ipd.b(1));
            } catch (RemoteException unused) {
                ReleaseLogUtil.c("HiH_WearAPI", "getSwitch RemoteException: ", e2.getMessage());
            }
        }
    }

    public void a(final int i, final IDetectCommonCallback iDetectCommonCallback) {
        this.j.execute(new Runnable() { // from class: iol
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.b(iDetectCommonCallback, i);
            }
        });
    }

    /* synthetic */ void b(final IDetectCommonCallback iDetectCommonCallback, int i) {
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "isSwitchOn:mApiAidl is null");
                iDetectCommonCallback.onResult(1, null, ipd.b(1));
            } else {
                this.f13488a.isSwitchOn(i, new IDetectCommonCallback.Stub() { // from class: ins.14
                    @Override // com.huawei.health.IDetectCommonCallback
                    public void onResponse(int i2, List list, String str) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "isSwitchOn onResponse err_code:", Integer.valueOf(i2));
                        iDetectCommonCallback.onResult(i2, list, str);
                    }
                });
            }
        } catch (RemoteException e2) {
            bBS_("isSwitchOn", e2, iDetectCommonCallback);
        }
    }

    public void e(final int i, final com.huawei.wearkit.IDetectCommonCallback iDetectCommonCallback) {
        this.j.execute(new Runnable() { // from class: iou
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.e(iDetectCommonCallback, i);
            }
        });
    }

    /* synthetic */ void e(final com.huawei.wearkit.IDetectCommonCallback iDetectCommonCallback, int i) {
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "getList:mApiAidl is null");
                iDetectCommonCallback.onResult(1, null, ipd.b(1));
            } else {
                this.f13488a.getList(i, new IDetectCommonCallback.Stub() { // from class: ins.11
                    @Override // com.huawei.health.IDetectCommonCallback
                    public void onResponse(int i2, List list, String str) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "getList onResponse err_code:", Integer.valueOf(i2));
                        iDetectCommonCallback.onResult(i2, list, str);
                    }
                });
            }
        } catch (RemoteException e2) {
            bBS_("getList", e2, iDetectCommonCallback);
        }
    }

    public void d(final int i, final com.huawei.wearkit.IDetectCommonCallback iDetectCommonCallback) {
        this.j.execute(new Runnable() { // from class: inx
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.c(iDetectCommonCallback, i);
            }
        });
    }

    /* synthetic */ void c(final com.huawei.wearkit.IDetectCommonCallback iDetectCommonCallback, int i) {
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "isLatestVersion:mApiAidl is null");
                iDetectCommonCallback.onResult(1, null, ipd.b(1));
            } else {
                this.f13488a.isLatestVersion(i, new IDetectCommonCallback.Stub() { // from class: ins.13
                    @Override // com.huawei.health.IDetectCommonCallback
                    public void onResponse(int i2, List list, String str) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "isLatestVersion onResponse err_code:", Integer.valueOf(i2));
                        iDetectCommonCallback.onResult(i2, list, str);
                    }
                });
            }
        } catch (RemoteException e2) {
            bBS_("isLatestVersion", e2, iDetectCommonCallback);
        }
    }

    public void c(final String str, final int i, final com.huawei.wearkit.IDetectCommonCallback iDetectCommonCallback) {
        this.j.execute(new Runnable() { // from class: ion
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.c(iDetectCommonCallback, str, i);
            }
        });
    }

    /* synthetic */ void c(final com.huawei.wearkit.IDetectCommonCallback iDetectCommonCallback, String str, int i) {
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "goIntoPage:mApiAidl is null");
                iDetectCommonCallback.onResult(1, null, ipd.b(1));
            } else {
                this.f13488a.goIntoPage(str, i, new IDetectCommonCallback.Stub() { // from class: ins.15
                    @Override // com.huawei.health.IDetectCommonCallback
                    public void onResponse(int i2, List list, String str2) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "goIntoPage onResponse err_code:", Integer.valueOf(i2));
                        iDetectCommonCallback.onResult(i2, list, str2);
                    }
                });
            }
        } catch (RemoteException e2) {
            bBS_("goIntoPage", e2, iDetectCommonCallback);
        }
    }

    public void bBT_(IBinder iBinder) {
        LogUtil.a("WearAPI", "setBinder enter, binder = ", iBinder, " mApiAidl = ", this.f13488a);
        if (iBinder != null) {
            try {
                if (this.d != null) {
                    this.d.unlinkToDeath(this.c, 0);
                }
                this.d = iBinder;
                this.d.linkToDeath(this.c, 0);
            } catch (RemoteException e2) {
                ReleaseLogUtil.c("HiH_WearAPI", "setBinder RemoteException = ", e2.getMessage());
            }
            this.f13488a = IKitWearAIDL.Stub.asInterface(this.d);
            LogUtil.c("WearAPI", "mApiAidl = ", this.f13488a);
            return;
        }
        this.j.execute(new Runnable() { // from class: iom
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.e();
            }
        });
        if (this.i != null) {
            LogUtil.a("HiH_WearAPI", "notify error");
            c(this.i, 150, ipd.b(150));
            this.i = null;
        }
    }

    /* synthetic */ void e() {
        this.f13488a = null;
    }

    private void bBS_(String str, RemoteException remoteException, com.huawei.wearkit.IDetectCommonCallback iDetectCommonCallback) {
        ReleaseLogUtil.e("HiH_WearAPI", str, " handleRemoteException Exception: ", remoteException.getMessage());
        try {
            iDetectCommonCallback.onResult(1, null, ipd.b(1));
        } catch (RemoteException unused) {
            ReleaseLogUtil.c("HiH_WearAPI", "handleRemoteException RemoteException: ", remoteException.getMessage());
        }
    }

    public void c(final String str, final String str2, final String str3, final int i, final ICommonCallback iCommonCallback) {
        final String a2 = iwi.a(b);
        this.j.execute(new Runnable() { // from class: iop
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.c(a2, iCommonCallback, str, str2, str3, i);
            }
        });
    }

    /* synthetic */ void c(String str, final ICommonCallback iCommonCallback, String str2, String str3, String str4, int i) {
        e(str);
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "bindDevice:mApiAidl is null");
                iCommonCallback.onResult(1, ipd.b(1));
            } else {
                this.f13488a.bindDevice(str2, str3, str4, i, new IBaseCommonCallback.Stub() { // from class: ins.16
                    @Override // com.huawei.health.IBaseCommonCallback
                    public void onResponse(int i2, String str5) throws RemoteException {
                        ReleaseLogUtil.e("HiH_WearAPI", "bindDevice onResponse err_code:", Integer.valueOf(i2));
                        iCommonCallback.onResult(i2, str5);
                    }
                });
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "bindDevice Exception: ", e2.getMessage());
            try {
                iCommonCallback.onResult(1, ipd.b(1));
            } catch (RemoteException e3) {
                ReleaseLogUtil.c("HiH_WearAPI", "bindDevice RemoteException: ", e3.getMessage());
            }
        }
    }

    public void a(final HealthLogOption healthLogOption, final IWearCommonCallback iWearCommonCallback) {
        this.j.execute(new Runnable() { // from class: ioo
            @Override // java.lang.Runnable
            public final void run() {
                ins.this.b(iWearCommonCallback, healthLogOption);
            }
        });
    }

    /* synthetic */ void b(IWearCommonCallback iWearCommonCallback, HealthLogOption healthLogOption) {
        d();
        try {
            if (this.f13488a == null) {
                ReleaseLogUtil.d("HiH_WearAPI", "captureLog:mApiAidl is null");
                iWearCommonCallback.onResult(1, ipd.b(1));
            } else {
                c(healthLogOption, iWearCommonCallback);
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_WearAPI", "captureLog RemoteException: ", e2.getMessage());
            try {
                iWearCommonCallback.onResult(1, ipd.b(1));
            } catch (RemoteException e3) {
                ReleaseLogUtil.c("HiH_WearAPI", "handleWearCommonCallback , RemoteException: ", e3.getMessage());
            }
        }
    }

    private void c(HealthLogOption healthLogOption, final IWearCommonCallback iWearCommonCallback) throws RemoteException {
        LogUtil.a("WearAPI", "enter doCaptureLog.");
        int geActionType = healthLogOption.getActionType().geActionType();
        if (geActionType == HealthLogOption.ActionType.CAPTURE_LOG.geActionType()) {
            this.f13488a.captureLog(healthLogOption.getUuid(), new IBaseCommonCallback.Stub() { // from class: ins.20
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str) throws RemoteException {
                    ReleaseLogUtil.e("HiH_WearAPI", "captureLog onResponse err_code:", Integer.valueOf(i));
                    iWearCommonCallback.onResult(i, str);
                }
            });
            return;
        }
        if (geActionType == HealthLogOption.ActionType.UPLOAD_LOG.geActionType()) {
            this.f13488a.uploadLog(healthLogOption.getUploadType(), healthLogOption.getUuid(), healthLogOption.getFileNameList(), new IBaseCommonCallback.Stub() { // from class: ins.18
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str) throws RemoteException {
                    ReleaseLogUtil.e("HiH_WearAPI", "uploadLog onResponse err_code:", Integer.valueOf(i));
                    iWearCommonCallback.onResult(i, str);
                }
            });
        } else if (geActionType == HealthLogOption.ActionType.REMOVE_LOG.geActionType()) {
            this.f13488a.removeLog(healthLogOption.getFileNameList(), new IBaseCommonCallback.Stub() { // from class: ins.19
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str) throws RemoteException {
                    ReleaseLogUtil.e("HiH_WearAPI", "removeLog onResponse err_code:", Integer.valueOf(i));
                    iWearCommonCallback.onResult(i, str);
                }
            });
        } else {
            iWearCommonCallback.onResult(2, ipd.b(2));
        }
    }
}
