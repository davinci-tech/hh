package defpackage;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.wifi.interfaces.BaseCallback;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.interfaces.CommBaseCallback;
import com.huawei.health.device.wifi.interfaces.DeviceCallBack;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.device.wifi.manager.DeviceRegisterManager;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class cui {
    private static volatile cui b;
    private static final Object c = new Object();
    private DeviceCallBack d;
    private ctn f;
    private csq g;
    private d h;
    private WeakReference<Context> i;
    private b j;
    private String k;
    private DeviceRegisterManager n;
    private ScanFilter o;
    private cuh p;
    private e q;
    private String r;
    private cum s;
    private String t;
    private int e = 1;

    /* renamed from: a, reason: collision with root package name */
    private int f11481a = 0;
    private int v = 0;
    private DeviceRegisterManager.RegisterMode m = DeviceRegisterManager.RegisterMode.REGISTER_NORFORM;
    private BaseCallbackInterface l = new BaseCallbackInterface() { // from class: cui.2
        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onStatus(int i) {
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onSuccess(Object obj) {
            cui.this.b(obj);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onFailure(int i) {
            cpw.e(false, "AddDeviceManager", "mRegisterCallBack error:", Integer.valueOf(i));
            cui.this.a(i);
        }
    };

    private cui(Context context) {
        this.i = new WeakReference<>(context);
        HandlerThread handlerThread = new HandlerThread("config_network");
        handlerThread.start();
        this.h = new d(this, handlerThread.getLooper());
    }

    public static cui b(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new cui(context);
                }
            }
        }
        return b;
    }

    static class d extends StaticHandler<cui> {
        d(cui cuiVar, Looper looper) {
            super(cuiVar, looper);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: MK_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(cui cuiVar, Message message) {
            if (cuiVar == null) {
                cpw.e(false, "AddDeviceManager", "DeviceHandler object is null");
                return;
            }
            if (message == null) {
                cpw.e(false, "AddDeviceManager", "DeviceHandler msg is null");
                return;
            }
            cpw.a(false, "AddDeviceManager", "handleMessage msg is ", Integer.valueOf(message.what));
            if (message.what < 10) {
                cuiVar.MJ_(message);
            } else if (message.what > 10) {
                cuiVar.MI_(message);
            } else {
                cpw.a(false, "AddDeviceManager", "handleMessage msg is error");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void MJ_(Message message) {
        switch (message.what) {
            case 1:
            case 4:
                n();
                break;
            case 2:
                r();
                break;
            case 3:
                o();
                break;
            case 5:
                this.h.sendEmptyMessage(7);
                break;
            case 6:
                h();
                break;
            case 7:
                f();
                break;
            default:
                cpw.a(false, "AddDeviceManager", "startConfigNetWork what is error：", Integer.valueOf(message.what));
                break;
        }
    }

    private void o() {
        if (k()) {
            q();
        }
    }

    private void e(int i) {
        cpw.a(false, "AddDeviceManager", "initConfigMode configMode：", Integer.valueOf(i));
        if (i < 0 || i > 4) {
            return;
        }
        this.e = i;
    }

    public void d(ctn ctnVar, DeviceCallBack deviceCallBack) {
        b(DeviceRegisterManager.RegisterMode.REGISTER_NORFORM, ctnVar, deviceCallBack);
    }

    public void b(DeviceRegisterManager.RegisterMode registerMode, ctn ctnVar, DeviceCallBack deviceCallBack) {
        this.f = ctnVar;
        e(3);
        synchronized (c) {
            this.d = deviceCallBack;
        }
        d dVar = this.h;
        if (dVar != null) {
            dVar.sendEmptyMessage(this.e);
        }
        this.m = registerMode;
    }

    private boolean k() {
        if (this.f != null) {
            return true;
        }
        a(102);
        return false;
    }

    private boolean a(String str) {
        ctn ctnVar = this.f;
        if (ctnVar == null) {
            cpw.c(false, "AddDeviceManager", "isPatternMatching isPatternMatching is null ");
            return false;
        }
        String i = ctnVar.i();
        cpw.c(false, "AddDeviceManager", "isPatternMatching deviceType : ", i);
        if (TextUtils.equals(str, i)) {
            return true;
        }
        cpw.e(false, "AddDeviceManager", "isPatternMatching deviceType is error : ", i, " mode:", str);
        return false;
    }

    public void a(int i, String str, String str2, ctn ctnVar, DeviceCallBack deviceCallBack) {
        d dVar;
        this.t = str;
        this.r = str2;
        this.f = ctnVar;
        synchronized (c) {
            this.d = deviceCallBack;
        }
        e(i);
        if (!m() || (dVar = this.h) == null) {
            return;
        }
        dVar.sendEmptyMessage(i);
    }

    public void d(String str, String str2, ScanFilter scanFilter, String str3, DeviceCallBack deviceCallBack) {
        d dVar;
        this.t = str;
        this.r = str2;
        this.o = scanFilter;
        this.k = str3;
        synchronized (c) {
            this.d = deviceCallBack;
        }
        e(4);
        if (!m() || (dVar = this.h) == null) {
            return;
        }
        dVar.sendEmptyMessage(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        synchronized (c) {
            DeviceCallBack deviceCallBack = this.d;
            if (deviceCallBack != null) {
                deviceCallBack.onFailure(this.e, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        synchronized (c) {
            DeviceCallBack deviceCallBack = this.d;
            if (deviceCallBack != null) {
                deviceCallBack.onStatus(this.e, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj) {
        synchronized (c) {
            DeviceCallBack deviceCallBack = this.d;
            if (deviceCallBack != null) {
                deviceCallBack.onSuccess(this.e, obj);
            }
        }
    }

    public void c() {
        int i = this.e;
        if (i == 2) {
            g();
        } else if (i == 1 || i == 4) {
            i();
            if (this.v == 3) {
                d();
            }
        } else if (i == 3) {
            d();
        } else {
            cpw.a(false, "AddDeviceManager", "destroy error mode");
        }
        j();
    }

    private void j() {
        this.e = 1;
        this.f11481a = 0;
        this.v = 0;
        synchronized (c) {
            this.d = null;
        }
    }

    private void g() {
        if (this.s != null) {
            b();
            this.s = null;
        }
    }

    private void i() {
        if (this.g != null) {
            this.h.removeCallbacksAndMessages(null);
            a();
            this.g = null;
        }
    }

    private boolean m() {
        int i = this.e;
        if (i != 1 && i != 2 && i != 4) {
            cpw.a(false, "AddDeviceManager", "isStartConfig configMode error ", Integer.valueOf(i));
            a(101);
            return false;
        }
        if (TextUtils.isEmpty(this.t) || this.r == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "isStartConfig wifiName:";
            objArr[1] = Boolean.valueOf(TextUtils.isEmpty(this.t));
            objArr[2] = " wifiPassword:";
            objArr[3] = Boolean.valueOf(this.r == null);
            cpw.a(false, "AddDeviceManager", objArr);
            a(102);
            return false;
        }
        if (this.e == 4) {
            if (this.o == null) {
                cpw.a(false, "AddDeviceManager", "isStartConfig mScanFilter null:");
                a(102);
                return false;
            }
            if (TextUtils.isEmpty(this.k)) {
                cpw.a(false, "AddDeviceManager", "isStartConfig productID is null");
                a(102);
                return false;
            }
        } else if (this.f == null) {
            cpw.a(false, "AddDeviceManager", "isStartConfig mDeviceInfo is null");
            a(102);
            return false;
        }
        if (cub.g(this.i.get())) {
            return true;
        }
        cpw.a(false, "AddDeviceManager", "isStartConfig wifi is not connected");
        a(103);
        return false;
    }

    private void r() {
        cpw.c(false, "AddDeviceManager", "startSoftAp");
        if (a("softap")) {
            this.v = 1;
            if (this.i.get() == null) {
                LogUtil.h("AddDeviceManager", "context is null");
                return;
            }
            cum e2 = cum.e(this.i.get());
            this.s = e2;
            if (e2 == null) {
                LogUtil.h("AddDeviceManager", "mSoftApManager is null");
                return;
            } else {
                e2.d(this.f, this.t, this.r, new a(this));
                return;
            }
        }
        a(101);
    }

    public void b() {
        cum cumVar = this.s;
        if (cumVar != null) {
            cumVar.a();
        }
    }

    static class a extends CommBaseCallback<cui> {
        a(cui cuiVar) {
            super(cuiVar);
        }

        @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(cui cuiVar, int i, String str, Object obj) {
            if (cuiVar != null) {
                if (cuiVar.v == 0) {
                    cpw.e(false, "AddDeviceManager", "AddDeviceManager is null or Config NetWork is not running");
                    return;
                }
                if ("error_code".equals(str)) {
                    cuiVar.c(i, obj);
                    return;
                } else if ("work_flow".equals(str)) {
                    cuiVar.b(i);
                    return;
                } else {
                    cpw.e(false, "AddDeviceManager", "SoftApCallBack error");
                    return;
                }
            }
            cpw.e(false, "AddDeviceManager", "SoftApCallBack obj is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj) {
        switch (i) {
            case 1100:
                b(obj);
                break;
            case 1101:
                cpw.e(false, "AddDeviceManager", "Ap device ssid is null");
                break;
            case 1102:
            case 1103:
            case ExceptionCode.CHECK_FILE_HASH_FAILED /* 1104 */:
                a(i);
                break;
            default:
                cpw.d(false, "AddDeviceManager", "softApErrorCode error is other ", Integer.valueOf(i));
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void MI_(Message message) {
        int i = message.what;
        if (i == 11) {
            l();
        } else if (i == 13) {
            b(message.arg1);
        } else {
            cpw.d(false, "AddDeviceManager", "handleMultMessage msg is error :", Integer.valueOf(message.what));
        }
    }

    private boolean e() {
        if (cst.b(this.i.get())) {
            return true;
        }
        a(110);
        cpw.e(false, "AddDeviceManager", "checkWiFiConfig WiFi config has Configuration information");
        return false;
    }

    private void n() {
        cpw.a(false, "AddDeviceManager", "initMulticast");
        if (e()) {
            this.j = new b(this);
            this.q = new e(this);
            this.p = cuh.e(this.i.get());
            if (this.e == 4) {
                this.h.sendEmptyMessage(6);
                return;
            } else {
                this.h.sendEmptyMessage(7);
                return;
            }
        }
        cpw.d(false, "AddDeviceManager", "initMulticast start multcast fail");
    }

    public void a() {
        csq csqVar = this.g;
        if (csqVar != null) {
            csqVar.b();
            this.p.c();
            Context context = this.i.get();
            if (context != null) {
                cst.c(context);
            }
        }
    }

    private void s() {
        csq csqVar = this.g;
        if (csqVar != null) {
            csqVar.e();
        }
    }

    private void t() {
        csq csqVar = this.g;
        if (csqVar != null) {
            csqVar.b();
        }
    }

    private void h() {
        cpw.a(false, "AddDeviceManager", "doStartHandMulticast");
        ctn ctnVar = new ctn();
        this.f = ctnVar;
        ctnVar.f("Hi11117K5B000000000000000009CB55");
        this.f.i(this.k);
        this.f.j("wifiap");
        this.f.a(csp.c(this.i.get()).b("Hi11117K5B000000000000000009CB55"));
        this.f.d("9CB55");
        f();
    }

    private void f() {
        if (this.f == null) {
            cpw.a(false, "AddDeviceManager", "doStartMulticast mDeviceInfo is null");
            return;
        }
        if (a("wifiap")) {
            this.v = 2;
            this.q.e();
            csu csuVar = new csu();
            csuVar.e(this.t);
            csuVar.b(this.r);
            csuVar.d(this.f.c());
            csuVar.e(this.f.a());
            csuVar.a(this.f.f());
            csuVar.d(this.o);
            csq csqVar = new csq(this.i.get(), csuVar, this.j);
            this.g = csqVar;
            csqVar.d(this.f11481a);
            this.g.c(this.e);
            this.g.c();
            return;
        }
        a(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        cuh cuhVar = this.p;
        if (cuhVar != null) {
            cuhVar.c();
        }
    }

    static final class b extends BaseCallback<cui> {
        b(cui cuiVar) {
            super(cuiVar);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(cui cuiVar, Object obj) {
            if (cuiVar == null || cuiVar.f == null) {
                return;
            }
            cpw.a(false, "AddDeviceManager", "MultCastCallback: onSuccess");
            if (obj != null) {
                cuiVar.f.b(obj.toString());
            }
            cuiVar.y();
            cuiVar.a();
            cuiVar.b(2212);
            cuiVar.h.sendEmptyMessage(3);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onFailure(cui cuiVar, int i) {
            cpw.a(false, "AddDeviceManager", "MultCastCallback: onFailure ", Integer.valueOf(i));
            if (cuiVar != null) {
                cuiVar.y();
                cuiVar.a(2115);
            }
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onStatus(cui cuiVar, int i) {
            cpw.a(false, "AddDeviceManager", "MultCastCallback: onStatus", Integer.valueOf(i));
            if (cuiVar != null) {
                Message obtainMessage = cuiVar.h.obtainMessage();
                obtainMessage.what = 13;
                obtainMessage.arg1 = i;
                cuiVar.h.sendMessage(obtainMessage);
                if (i != 2210 || Build.VERSION.SDK_INT >= 28) {
                    return;
                }
                cuiVar.p();
                cpw.c(false, "AddDeviceManager", "MultCastCallback: onStatus");
                cuiVar.h.sendEmptyMessage(11);
            }
        }
    }

    private void l() {
        cuh cuhVar = this.p;
        if (cuhVar != null) {
            cuhVar.c(this.f, this.q);
        } else {
            cpw.e(false, "AddDeviceManager", "scanSelectDevice mScanManager is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        Context context = this.i.get();
        if (context != null) {
            Object systemService = context.getApplicationContext().getSystemService("wifi");
            if (systemService instanceof WifiManager) {
                ((WifiManager) systemService).startScan();
            } else {
                LogUtil.h("AddDeviceManager", "wifiManagerObj not instanceof WifiManager");
            }
        }
    }

    static final class e extends BaseCallback<cui> {
        int c;

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onStatus(cui cuiVar, int i) {
        }

        e(cui cuiVar) {
            super(cuiVar);
            this.c = 0;
        }

        public void e() {
            this.c = 0;
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(cui cuiVar, Object obj) {
            if (cuiVar == null) {
                return;
            }
            if (obj != null) {
                cpw.a(false, "AddDeviceManager", "ScanSelectDeviceCallback: onSuccess status", obj);
                cuiVar.b(obj.toString());
            }
            int i = this.c + 1;
            this.c = i;
            if (i < 16) {
                return;
            }
            if (obj == null || "".equals(obj)) {
                cpw.d(false, "AddDeviceManager", "ScanSelectDeviceCallback: The specified WiFi is not scanned");
                return;
            }
            cpw.c(false, "AddDeviceManager", "ScanSelectDeviceCallback: Scan to specified WiFi");
            e();
            cuiVar.y();
            cuiVar.a();
            cuiVar.a(2114);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onFailure(cui cuiVar, int i) {
            cpw.a(false, "AddDeviceManager", "ScanSelectDeviceCallback: onFailure");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void b(String str) {
        char c2;
        if (!TextUtils.isEmpty(str)) {
            str.hashCode();
            switch (str.hashCode()) {
                case 71:
                    if (str.equals("G")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 72:
                    if (str.equals("H")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 73:
                    if (str.equals("I")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 74:
                    if (str.equals(RequestOptions.AD_CONTENT_CLASSIFICATION_J)) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                cpw.c(false, "AddDeviceManager", "Status :WiFi Password is error ", str);
                y();
                t();
                a(2109);
                return;
            }
            if (c2 == 1) {
                cpw.c(false, "AddDeviceManager", "Status :The device has received the WiFi name/password and is connecting to the router WiFi ", str);
                s();
                a(2112);
                return;
            } else {
                if (c2 == 2) {
                    cpw.c(false, "AddDeviceManager", "Status :Cannot connect to the router WiFi due to timeout ", str);
                    y();
                    t();
                    a(2110);
                    return;
                }
                if (c2 == 3) {
                    cpw.c(false, "AddDeviceManager", "Status :Unable to connect to the router's WiFi because the router SSID could not be found ", str);
                    y();
                    t();
                    a(2111);
                    return;
                }
                cpw.d(false, "AddDeviceManager", "sendMultStatus: other msg:", str);
                return;
            }
        }
        cpw.d(false, "AddDeviceManager", "sendMultStatus msg is null");
    }

    public void d() {
        DeviceRegisterManager deviceRegisterManager = this.n;
        if (deviceRegisterManager != null) {
            deviceRegisterManager.d();
            this.n = null;
        }
    }

    private void q() {
        this.v = 3;
        DeviceRegisterManager a2 = DeviceRegisterManager.a(this.i.get());
        this.n = a2;
        a2.a(this.f, this.m, this.l);
    }
}
