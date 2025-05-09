package com.huawei.hwcommonmodel.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.android.iaware.IAwareSdkEx;
import com.huawei.android.os.ServiceManagerEx;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.api.HwWatchFaceApi;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.List;

/* loaded from: classes5.dex */
public class ThermalCallback extends Binder implements IBinder.DeathRecipient {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f6270a;
    private static final Object b = new Object();
    private static int c;
    private static ThermalCallback d;
    private static ExtendHandler e;
    private static int g;
    private static IBinder h;
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.hwcommonmodel.utils.ThermalCallback.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                LogUtil.a("ThermalCallback", "mTemperatureControlServiceStartReceiver() action: ", intent.getAction());
                if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                    ThermalCallback.o();
                }
            }
        }
    };
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: com.huawei.hwcommonmodel.utils.ThermalCallback.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(intent.getAction())) {
                return;
            }
            boolean unused = ThermalCallback.f6270a = intent.getBooleanExtra("isForeground", false);
            LogUtil.a("ThermalCallback", "mForegroundStatusChangeReceiver() sIsForeground: ", Boolean.valueOf(ThermalCallback.f6270a));
        }
    };

    public void controlThreadLevel(List<Integer> list, int i) {
    }

    static /* synthetic */ int e() {
        int i = g;
        g = i + 1;
        return i;
    }

    private ThermalCallback() {
        h();
        BaseApplication.getContext().registerReceiver(this.f, new IntentFilter("android.intent.action.BOOT_COMPLETED"), LocalBroadcast.c, null);
        BroadcastManager.wj_(this.i);
        f6270a = CommonUtil.s(BaseApplication.getContext());
    }

    public static ThermalCallback getInstance() {
        ThermalCallback thermalCallback;
        LogUtil.a("ThermalCallback", "getInstance ThermalCallback.");
        synchronized (b) {
            if (d == null) {
                d = new ThermalCallback();
                o();
            }
            thermalCallback = d;
        }
        return thermalCallback;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        LogUtil.h("ThermalCallback", "binderDied sdkService is died.");
        g();
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            parcel.enforceInterface("com.huawei.iaware.sdk.ThermalCallback");
            c(parcel.readInt());
            LogUtil.a("ThermalCallback", "Thermal onTransact level:", Integer.valueOf(c));
            parcel2.writeNoException();
            f();
            return true;
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    private static void i() {
        if (h != null) {
            return;
        }
        try {
            IBinder service = ServiceManagerEx.getService("IAwareSdkService");
            h = service;
            if (service != null) {
                service.linkToDeath(d, 0);
            } else {
                LogUtil.h("ThermalCallback", "failed to get IAwareSdkService.");
            }
        } catch (RemoteException e2) {
            LogUtil.b("ThermalCallback", "RemoteException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o() {
        LogUtil.a("ThermalCallback", "registerService.");
        if (!CommonUtil.bh() || (CommonUtil.ao() && !CommonUtil.ar())) {
            LogUtil.h("ThermalCallback", "registerService only support HuaweiSystem.");
            return;
        }
        i();
        IAwareSdkEx.registerCallback(3034, BaseApplication.getAppPackage(), d);
        e.sendEmptyMessage(1, PreConnectManager.CONNECT_INTERNAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        g = 0;
        e.quit(false);
    }

    private static void j() {
        synchronized (b) {
            d = null;
        }
    }

    private static void c(int i) {
        c = i;
    }

    private static void g() {
        h = null;
        e.sendEmptyMessage(1, PreConnectManager.CONNECT_INTERNAL);
    }

    private static void h() {
        e = HandlerCenter.yt_(new d(), "ThermalCallback");
    }

    public boolean isTriggerTask() {
        LogUtil.a("ThermalCallback", "isTriggerTask sIsForeground:", Boolean.valueOf(f6270a), ", sLevel:", Integer.valueOf(c));
        return f6270a || c < 1;
    }

    public boolean isTriggerOtaTask() {
        LogUtil.a("ThermalCallback", "isTriggerOtaTask sIsForeground:", Boolean.valueOf(f6270a), ", sLevel:", Integer.valueOf(c));
        return f6270a || c < 2;
    }

    public int getThermalLevel() {
        return c;
    }

    public void destroy() {
        BaseApplication.getContext().unregisterReceiver(this.f);
        BroadcastManager.wx_(this.i);
        j();
    }

    static class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ThermalCallback.e();
                ThermalCallback.o();
                if (ThermalCallback.g == 3) {
                    ThermalCallback.f();
                }
            } else {
                LogUtil.h("ThermalCallback", "sHandler error msg.");
            }
            return true;
        }
    }
}
