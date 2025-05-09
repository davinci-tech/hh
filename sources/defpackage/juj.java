package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.btproxy.BtProxyNetworkChangeReceiver;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class juj {
    private static final Object b = new Object();
    private static volatile juj d;
    private static Handler e;

    /* renamed from: a, reason: collision with root package name */
    private HandlerThread f14093a;
    private juh c;
    private long f = 0;

    private juj() {
        LogUtil.a("HwBtProxyReceiveDataFromDevice", "new HwBtProxyReceiveDataFromDevice:");
        this.c = new juh();
        HandlerThread handlerThread = new HandlerThread("HwBtProxyReceiveDataFromDevice");
        this.f14093a = handlerThread;
        handlerThread.start();
        e = new a(this.f14093a.getLooper());
    }

    public static juj d() {
        if (d == null) {
            synchronized (b) {
                LogUtil.a("HwBtProxyReceiveDataFromDevice", "getInstance()");
                if (d == null) {
                    d = new juj();
                }
            }
        }
        return d;
    }

    static class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            LogUtil.a("HwBtProxyReceiveDataFromDevice", "msg is ", message);
            super.handleMessage(message);
            LogUtil.a("HwBtProxyReceiveDataFromDevice", "msg.what is ", Integer.valueOf(message.what));
            if (message.what == 0) {
                boolean aa = CommonUtil.aa(BaseApplication.getContext());
                if (aa) {
                    LogUtil.a("HwBtProxyReceiveDataFromDevice", "netConnectStatus is available: ", Boolean.valueOf(aa));
                    BtProxyNetworkChangeReceiver.c().e();
                    juj.e.removeMessages(0);
                    return;
                } else {
                    LogUtil.h("HwBtProxyReceiveDataFromDevice", "netConnectStatus is unavailable: ", Boolean.valueOf(aa));
                    juj.e.sendEmptyMessageDelayed(0, PreConnectManager.CONNECT_INTERNAL);
                    return;
                }
            }
            LogUtil.h("HwBtProxyReceiveDataFromDevice", "do nothing.");
        }
    }

    public void c(int i, int i2) {
        this.c.startProxyService(i, i2);
    }

    public void a() {
        this.c.stopProxyService();
        f();
    }

    public static void e() {
        if (d == null) {
            LogUtil.h("HwBtProxyReceiveDataFromDevice", "current instance is null");
        } else {
            d = null;
        }
    }

    private void f() {
        HandlerThread handlerThread = this.f14093a;
        if (handlerThread != null) {
            handlerThread.quit();
            this.f14093a = null;
        }
    }

    public void a(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            LogUtil.h("HwBtProxyReceiveDataFromDevice", "data illegal");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f;
        LogUtil.a("HwBtProxyReceiveDataFromDevice", "network differTime milliseconds: ", Long.valueOf(currentTimeMillis), " ,origin networkConnectStatus: ", Boolean.valueOf(CommonUtil.aa(BaseApplication.getContext())));
        if (currentTimeMillis > 60000 || !CommonUtil.aa(BaseApplication.getContext())) {
            b();
            long currentTimeMillis2 = System.currentTimeMillis();
            this.f = currentTimeMillis2;
            LogUtil.a("HwBtProxyReceiveDataFromDevice", "mPowerKitLastStartupTime: ", Long.valueOf(currentTimeMillis2), " NetworkConnectStatus is ", Boolean.valueOf(CommonUtil.aa(BaseApplication.getContext())));
        }
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            BtProxyNetworkChangeReceiver.c().d();
            e.sendEmptyMessageDelayed(0, PreConnectManager.CONNECT_INTERNAL);
            LogUtil.h("HwBtProxyReceiveDataFromDevice", "wearNode proxy not closed, current network is unavailable");
            return;
        }
        jug jugVar = new jug();
        blt.d("HwBtProxyReceiveDataFromDevice", bArr, "getResult(): ");
        c(bArr, jugVar, deviceInfo);
        if (b(jugVar)) {
            return;
        }
        juh juhVar = this.c;
        if (juhVar != null) {
            juhVar.sendMessage(jugVar);
        } else {
            LogUtil.h("HwBtProxyReceiveDataFromDevice", "DistributionNetworkPhoneProxy is null");
        }
    }

    private void b() {
        PowerKitManager.e().d("HwBtProxyReceiveDataFromDevice", 512, 75000L, "user-BTProxy");
        LogUtil.a("HwBtProxyReceiveDataFromDevice", "user sleeping, apply network resource.");
    }

    private boolean b(jug jugVar) {
        if (jugVar.a() == 1) {
            LogUtil.a("HwBtProxyReceiveDataFromDevice", "operateProxyService: start");
            c(jugVar.b(), jugVar.i());
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_OPEN_BT_PROXY_TIMES_2060059.value(), new HashMap(1), 0);
            return true;
        }
        LogUtil.a("HwBtProxyReceiveDataFromDevice", "networkIfo");
        return false;
    }

    private void c(byte[] bArr, jug jugVar, DeviceInfo deviceInfo) {
        try {
            List<bmu> d2 = new bmt().b(bArr).d();
            if (d2 != null && !d2.isEmpty()) {
                for (bmu bmuVar : d2) {
                    b(bmuVar.a(), bmuVar.c(), jugVar, deviceInfo);
                }
                return;
            }
            LogUtil.h("HwBtProxyReceiveDataFromDevice", "handleBtProxyMessage tlv error");
        } catch (bmk unused) {
            LogUtil.b("HwBtProxyReceiveDataFromDevice", "handleBtProxyMessage error");
        }
    }

    private void b(int i, byte[] bArr, jug jugVar, DeviceInfo deviceInfo) {
        LogUtil.a("HwBtProxyReceiveDataFromDevice", "handleSecondLevelMessageTlv type is ", Integer.valueOf(i), "value is ", bArr);
        if (i == 13) {
            jugVar.b(cvx.c(bArr, -1));
            return;
        }
        if (i != 14) {
            switch (i) {
                case 1:
                    jugVar.i(cvx.c(bArr, -1));
                    break;
                case 2:
                    jugVar.b(new String(bArr, StandardCharsets.UTF_8));
                    break;
                case 3:
                    jugVar.a(new String(bArr, StandardCharsets.UTF_8));
                    break;
                case 4:
                    jugVar.b(bArr);
                    break;
                case 5:
                    jugVar.f(cvx.c(bArr, -1));
                    break;
                case 6:
                    jugVar.e(bArr);
                    break;
                case 7:
                    jugVar.e(cvx.c(bArr, -1));
                    break;
                case 8:
                    jugVar.c(cvx.c(bArr, -1));
                    break;
                case 9:
                    jugVar.d(bArr);
                    break;
                default:
                    LogUtil.h("HwBtProxyReceiveDataFromDevice", "default type: ", Integer.valueOf(i));
                    break;
            }
            return;
        }
        boolean b2 = BtProxyNetworkChangeReceiver.c().b();
        LogUtil.a("HwBtProxyReceiveDataFromDevice", "get isSupportBtProxy:", Boolean.valueOf(b2));
        if (b2) {
            int c = cvx.c(bArr, -1);
            LogUtil.a("HwBtProxyReceiveDataFromDevice", "get mProxyDataMaxsize:", Integer.valueOf(c));
            int max = Math.max(1, c) * 1024;
            jugVar.d(max);
            jugVar.a(max);
        }
    }
}
