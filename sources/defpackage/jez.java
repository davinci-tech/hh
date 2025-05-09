package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.callback.BindPhoneServiceCallback;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.EventBusApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonservice.IServiceBinder;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.CustomConfigWatchFaceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.StickerWatchFaceService;
import com.huawei.hwdevice.phoneprocess.binder.SuggestionBinder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IDeviceStateAIDLCallback;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.hwservicesmgr.PhoneService;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.jez;
import health.compact.a.DeviceUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.ProcessUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class jez {

    /* renamed from: a, reason: collision with root package name */
    private static BindPhoneServiceCallback f13778a;
    private static IDeviceStateAIDLCallback b;
    private static IAddDeviceStateAIDLCallback d;
    private static IBinder h;
    private static IWearPhoneServiceAIDL j;
    private static List<IBaseResponseCallback> c = new ArrayList(16);
    private static final List<BindPhoneServiceCallback> f = Collections.synchronizedList(new ArrayList(16));
    private static int g = 0;
    private static ServiceConnection e = new AnonymousClass5();

    /* renamed from: jez$5, reason: invalid class name */
    class AnonymousClass5 implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        private int f13780a = 0;

        AnonymousClass5() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ReleaseLogUtil.e("R_PhoneServiceManager", "ServiceConnection callback");
            if (jez.e() == null) {
                int i = this.f13780a + 1;
                this.f13780a = i;
                LogUtil.a("PhoneServiceManager", "bind phoneservice times count = ", Integer.valueOf(i));
            }
            jez.bGR_(iBinder);
            jhg.a(true);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.a("PhoneServiceManager", "remote onServiceDisconnected");
            HwVersionManager.c(BaseApplication.getContext()).a(false);
            if (jez.c != null) {
                Iterator it = jez.c.iterator();
                while (it.hasNext()) {
                    ((IBaseResponseCallback) it.next()).d(-1, null);
                }
                jez.c.clear();
            }
            jkj.d().h();
            if (jez.d != null) {
                try {
                    jez.d.onAddDeviceState(4);
                } catch (RemoteException e) {
                    LogUtil.b("PhoneServiceManager", "remote exception:", e.getMessage());
                }
                IAddDeviceStateAIDLCallback unused = jez.d = null;
            }
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() { // from class: jfg
                @Override // java.lang.Runnable
                public final void run() {
                    jez.AnonymousClass5.this.b();
                }
            });
            newSingleThreadExecutor.shutdown();
            jhg.a(false);
            Intent intent = new Intent();
            intent.setAction("com.huawei.health.phone_service_dead");
            intent.setPackage(BaseApplication.getContext().getPackageName());
            BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
        }

        /* synthetic */ void b() {
            int i = this.f13780a;
            boolean a2 = jpp.a();
            if (i != this.f13780a) {
                return;
            }
            int g = iyl.d().g();
            LogUtil.a("PhoneServiceManager", "switchState:", Integer.valueOf(g), ";isHasDevice:", Boolean.valueOf(a2));
            if (g != 3 || !a2) {
                jez.d(BaseApplication.getContext());
            }
            jez.b((IWearPhoneServiceAIDL) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bGR_(IBinder iBinder) {
        try {
            b(IWearPhoneServiceAIDL.Stub.asInterface(IServiceBinder.Stub.asInterface(iBinder).getServiceBinder(BaseApplication.getAppPackage(), 0)));
            BindPhoneServiceCallback bindPhoneServiceCallback = f13778a;
            if (bindPhoneServiceCallback != null) {
                bindPhoneServiceCallback.onBind();
            }
            ReleaseLogUtil.e("R_PhoneServiceManager", "sLinkageBinder: ", h);
            h();
            lds.c().initReverseLinkage(lds.a());
            j();
            CustomConfigWatchFaceService.getInstance().registerDeviceSampleListener();
            StickerWatchFaceService.getInstance().registerDeviceSampleListener();
            BaseApplication.getContext().sendBroadcast(new Intent("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS"), LocalBroadcast.c);
            IWearPhoneServiceAIDL iWearPhoneServiceAIDL = j;
            if (iWearPhoneServiceAIDL != null) {
                try {
                    iWearPhoneServiceAIDL.registerDeviceStateCallBack(b);
                    ReleaseLogUtil.e("R_PhoneServiceManager", "processOnServiceConnected is success.");
                } catch (RemoteException e2) {
                    LogUtil.b("PhoneServiceManager", "remote exception:", e2.getMessage());
                }
            }
            l();
            f();
        } catch (RemoteException e3) {
            LogUtil.b("PhoneServiceManager", "system remote exception:", e3.getMessage());
        }
    }

    private static void j() {
        if (ProcessUtil.d()) {
            bgj.c().b();
        }
    }

    private static void l() {
        ThreadPoolManager.d().d("processOnServiceConnected", new Runnable() { // from class: jez.3
            @Override // java.lang.Runnable
            public void run() {
                jfq.c().h();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(IWearPhoneServiceAIDL iWearPhoneServiceAIDL) {
        j = iWearPhoneServiceAIDL;
    }

    private static void f() {
        List<BindPhoneServiceCallback> list = f;
        if (list.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            ((BindPhoneServiceCallback) it.next()).onBind();
        }
    }

    public static void d(BindPhoneServiceCallback bindPhoneServiceCallback) {
        f.add(bindPhoneServiceCallback);
    }

    public static void b(BindPhoneServiceCallback bindPhoneServiceCallback) {
        f.remove(bindPhoneServiceCallback);
    }

    public static void b(IBaseResponseCallback iBaseResponseCallback) {
        c.add(iBaseResponseCallback);
    }

    public static IWearPhoneServiceAIDL e() {
        return j;
    }

    public static void bGS_(IBinder iBinder) {
        ReleaseLogUtil.e("R_PhoneServiceManager", "binder: ", iBinder, ", sLinkageBinder: ", h);
        h = iBinder;
        h();
    }

    public static void b() {
        g();
        IDeviceStateAIDLCallback.Stub a2 = jfm.e().a();
        b = a2;
        IWearPhoneServiceAIDL iWearPhoneServiceAIDL = j;
        if (iWearPhoneServiceAIDL != null) {
            try {
                iWearPhoneServiceAIDL.registerDeviceStateCallBack(a2);
                ReleaseLogUtil.e("R_PhoneServiceManager", "registerDeviceManagerCallback is success");
            } catch (RemoteException e2) {
                LogUtil.b("PhoneServiceManager", "RemoteException = ", e2.getMessage());
            }
        }
    }

    public static void d(IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) {
        d = iAddDeviceStateAIDLCallback;
    }

    public static void a(final Context context) {
        g();
        ((EventBusApi) Services.c("PluginDevice", EventBusApi.class)).subscribeAm16Bind();
        ThreadPoolManager.d().c("PhoneServiceManager", new Runnable() { // from class: com.huawei.hwdevice.mainprocess.PhoneServiceManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                jez.c(context);
            }
        });
    }

    public static /* synthetic */ void c(Context context) {
        if (jpp.a()) {
            b(context);
        } else {
            LogUtil.h("PhoneServiceManager", "no device, do nothing");
        }
    }

    private static void g() {
        if (g < 5 && !ProcessUtil.d()) {
            String d2 = DfxUtils.d(Thread.currentThread(), null);
            ReleaseLogUtil.e("R_PhoneServiceManager", "callPidError stackTraceInfo= ", d2);
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("callPidError", d2);
            g++;
        }
    }

    private static void b(Context context) {
        if (context == null || e() != null) {
            return;
        }
        DeviceUtil.b(context);
        LogUtil.a("PhoneServiceManager", "bindService result is mConnection:", e);
        LogUtil.a("PhoneServiceManager", "bindService result is ", Boolean.valueOf(context.getApplicationContext().bindService(new Intent(context, (Class<?>) PhoneService.class), e, 1)));
    }

    public static void d(Context context, BindPhoneServiceCallback bindPhoneServiceCallback) {
        b(context);
        if (bindPhoneServiceCallback != null) {
            f13778a = bindPhoneServiceCallback;
        }
    }

    public static void d(Context context) {
        if (context == null || e() == null) {
            return;
        }
        try {
            try {
                context.getApplicationContext().unbindService(e);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("PhoneServiceManager", "finish IllegalArgumentException");
            }
        } finally {
            b((IWearPhoneServiceAIDL) null);
        }
    }

    private static void h() {
        final IWearPhoneServiceAIDL e2 = e();
        if (e2 == null) {
            LogUtil.h("PhoneServiceManager", "initLinkageBinder binder is null.");
        } else if (h == null) {
            ReleaseLogUtil.e("R_PhoneServiceManager", "sLinkageBinder is null");
        } else {
            jrq.b("PhoneServiceManager", new Runnable() { // from class: jez.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ReleaseLogUtil.e("R_PhoneServiceManager", "sLinkageBinder: ", jez.h);
                        IWearPhoneServiceAIDL.this.setBinder("PhoneServiceManager", jez.h, new IBaseCallback.Stub() { // from class: jez.4.5
                            @Override // com.huawei.hwservicesmgr.IBaseCallback
                            public void onResponse(int i, String str) throws RemoteException {
                                LogUtil.a("PhoneServiceManager", "setBinder result:", Integer.valueOf(i), "; reason:", str);
                            }
                        });
                    } catch (RemoteException e3) {
                        LogUtil.b("PhoneServiceManager", "initLinkageBinder RemoteException：", ExceptionUtils.d(e3));
                    }
                }
            });
        }
    }

    public static void i() {
        LogUtil.a("PhoneServiceManager", "setSuggestionAidl");
        IWearPhoneServiceAIDL e2 = e();
        if (e2 == null) {
            LogUtil.b("PhoneServiceManager", "phoneServiceBinder == null");
            return;
        }
        try {
            e2.setSuggestionBinder(new SuggestionBinder());
        } catch (RemoteException unused) {
            LogUtil.b("PhoneServiceManager", "action = ", "com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS", ", RemoteException");
        }
    }
}
