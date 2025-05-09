package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes5.dex */
public class jvz {
    private static final Object b = new Object();
    private static jvz d;
    private TransferFileInfo aa;
    private long n;
    private IBaseResponseCallback y;
    private boolean s = false;
    private ArrayList t = null;
    private String o = null;
    private MaintenaceInterface w = null;
    private int z = 0;
    private ArrayList<byte[]> x = new ArrayList<>(16);

    /* renamed from: a, reason: collision with root package name */
    private int f14141a = 2440;
    private int v = 732;
    private int p = 0;
    private int l = 0;
    private int m = 0;
    private boolean r = false;
    private String e = "All file:";
    private String k = "Done file:";
    private Date g = null;
    private int ae = 5000;
    private kbb u = null;
    private String i = "";
    private String h = "";
    private int q = 1;
    private int ad = 244;
    private int c = -1;
    private int f = 0;
    private int af = 1;
    private IBaseResponseCallback j = new IBaseResponseCallback() { // from class: jvz.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            byte[] bArr = obj instanceof byte[] ? (byte[]) obj : null;
            if (bArr == null || bArr.length < 2) {
                LogUtil.h("HwFileTransferTaskAw70Manager", "onResponse error data is null return");
                return;
            }
            byte b2 = bArr[1];
            if (b2 == 11) {
                if (!jvz.this.r && CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")) {
                    LogUtil.a("HwFileTransferTaskAw70Manager", "Dfx is ok, not busy");
                } else {
                    LogUtil.h("HwFileTransferTaskAw70Manager", "is busy or is not support");
                    return;
                }
            }
            if (jvz.this.ac != null) {
                Message obtainMessage = jvz.this.ac.obtainMessage();
                obtainMessage.what = b2;
                obtainMessage.obj = bArr;
                jvz.this.ac.sendMessage(obtainMessage);
            }
        }
    };
    private kaz ab = kaz.c();
    private Handler ac = new d(Looper.getMainLooper());

    private jvz() {
    }

    public static jvz e() {
        jvz jvzVar;
        synchronized (b) {
            if (d == null) {
                d = new jvz();
            }
            jvzVar = d;
        }
        return jvzVar;
    }

    public void a(TransferFileInfo transferFileInfo, IBaseResponseCallback iBaseResponseCallback) {
        if (this.r) {
            LogUtil.h("HwFileTransferTaskAw70Manager", "startMainFile mIsMaintaining, so return!");
            return;
        }
        this.y = iBaseResponseCallback;
        this.aa = transferFileInfo;
        if (transferFileInfo.getIsFromAbout() == 1) {
            this.s = true;
        } else {
            this.s = false;
        }
        LogUtil.a("HwFileTransferTaskAw70Manager", "isFromAbout = ", Integer.valueOf(this.aa.getIsFromAbout()));
        this.r = true;
        if (transferFileInfo.getType() == 0) {
            jwa.a(BaseApplication.getContext(), transferFileInfo.getDeviceSn());
            jwf.d().a(System.currentTimeMillis());
            LogUtil.a("HwFileTransferTaskAw70Manager", "transferFileInfo.getDeviceMac() = ", iyl.d().e(transferFileInfo.getDeviceMac()), " , transferFileInfo.getDeviceVersion() = ", transferFileInfo.getDeviceVersion());
            if (CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) {
                this.q = 1;
            } else {
                this.q = 0;
            }
            this.i = transferFileInfo.getDeviceSn();
            this.h = transferFileInfo.getDeviceVersion();
            if (TextUtils.isEmpty(this.i) || TextUtils.isEmpty(this.h)) {
                LogUtil.b("HwFileTransferTaskAw70Manager", "parameter is error!!!");
                c();
                jsd.c(this.aa, 10001, "parameter is error", this.y);
                return;
            } else {
                this.w = jrx.d();
                a(transferFileInfo.getDfxLogType());
                return;
            }
        }
        LogUtil.a("HwFileTransferTaskAw70Manager", "transferFileInfo.getType() = ", Integer.valueOf(transferFileInfo.getType()));
        b();
        c();
        jsd.c(this.aa, 10001, "type not found", this.y);
    }

    private void a(ArrayList arrayList) {
        try {
            this.t = arrayList;
            if (arrayList == null) {
                if (this.y != null) {
                    c();
                    this.w.cutFolder(jrx.b, jrx.c);
                    jsd.c(this.aa, 10001, "error file list is null", this.y);
                    return;
                }
                return;
            }
            int i = this.q;
            if (i != 0) {
                this.t = this.w.filtertFile(arrayList, i);
            }
            String str = this.e + this.t;
            this.e = str;
            LogUtil.a("HwFileTransferTaskAw70Manager", "getMainSleepOrDfxFileNameHandle() mAllFileListName ", str);
            if (this.t.size() == 0) {
                LogUtil.h("HwFileTransferTaskAw70Manager", "getMainSleepOrDfxFileNameHandle fileList() size is 0");
                c();
                if (this.y != null) {
                    this.w.cutFolder(jrx.b, jrx.c);
                    jsd.c(this.aa, 10000, l(), this.y);
                    return;
                }
                return;
            }
            h();
        } catch (Exception unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "getMainSleepOrDfxFileNameHandle Exception");
        }
    }

    private void a(int i) {
        Handler handler = this.ac;
        if (handler != null) {
            handler.removeMessages(15);
            this.ac.sendEmptyMessageDelayed(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(1);
        String g = g();
        if (!TextUtils.isEmpty(g)) {
            deviceCommand.setmIdentify(g);
        }
        if (i != 0) {
            String str = cvx.e(15) + cvx.e(2) + cvx.e(i);
            deviceCommand.setDataContent(cvx.a(str));
            deviceCommand.setDataLen(cvx.a(str).length);
        }
        a(deviceCommand);
    }

    private void h() {
        Handler handler = this.ac;
        if (handler != null) {
            handler.removeMessages(15);
            this.ac.sendEmptyMessageDelayed(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        MaintenaceInterface maintenaceInterface = this.w;
        if (maintenaceInterface != null) {
            DeviceCommand maintParametersCommand = maintenaceInterface.maintParametersCommand();
            String g = g();
            if (!TextUtils.isEmpty(g)) {
                maintParametersCommand.setmIdentify(g);
            }
            a(maintParametersCommand);
        }
    }

    private void b(kbb kbbVar) {
        try {
            this.u = kbbVar;
            if (kbbVar == null) {
                return;
            }
            this.ae = kbbVar.b();
            this.ad = this.u.c();
            int e = this.u.e();
            this.v = e;
            if (e == 0) {
                this.v = 732;
            }
            this.f14141a = this.v;
            LogUtil.a("HwFileTransferTaskAw70Manager", " getSleepOrDfxParametersHandle() ok, protocalVersion :", this.u.a(), ", mTransferUnitSize:", Integer.valueOf(this.ad), ", mMaxApplyDataSize:", Integer.valueOf(this.v), ", mWaitTimeout:", Integer.valueOf(this.ae), ", breakPointEnable:", Boolean.valueOf(this.u.d()));
            if (this.ac != null) {
                this.w.deleteTenDayFile();
                this.w.initMaintenanceParame(this.aa);
                this.ac.sendEmptyMessage(14);
            }
        } catch (Exception unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "getSleepOrDfxParametersHandle Exception");
            c();
            if (this.y != null) {
                this.w.cutFolder(jrx.b, jrx.c);
                jsd.c(this.aa, 10000, l(), this.y);
            }
        }
    }

    private void d(String str) {
        Handler handler = this.ac;
        if (handler != null) {
            handler.removeMessages(15);
            this.ac.sendEmptyMessageDelayed(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        f();
        String c = cvx.c(str);
        String str2 = cvx.e(1) + cvx.e(c.length() / 2) + c;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        String g = g();
        if (!TextUtils.isEmpty(g)) {
            deviceCommand.setmIdentify(g);
        }
        a(deviceCommand);
    }

    private void e(kay kayVar) {
        try {
            this.p = (int) kayVar.e();
            this.n = kayVar.a();
            LogUtil.a("HwFileTransferTaskAw70Manager", " querySleepOrDfxFileInformationHandle() ok, mFileTotalSize = ", Integer.valueOf(this.p), ", mFileCrc = ", Long.valueOf(this.n));
            if (this.p == 0) {
                ArrayList arrayList = this.t;
                if (arrayList != null && arrayList.size() > 0) {
                    this.t.remove(0);
                }
                Handler handler = this.ac;
                if (handler != null) {
                    handler.sendEmptyMessage(14);
                    return;
                }
                return;
            }
            this.g = new Date();
            a();
        } catch (Exception unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "querySleepOrDfxFileInformationHandle Exception");
        }
    }

    private void b(String str, int i, int i2) {
        Handler handler = this.ac;
        if (handler != null) {
            handler.removeMessages(15);
            this.ac.sendEmptyMessageDelayed(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        String c = cvx.c(str);
        String str2 = cvx.e(1) + cvx.e(c.length() / 2) + c;
        String str3 = cvx.e(2) + cvx.e(4) + cvx.b(i);
        String str4 = cvx.e(3) + cvx.e(4) + cvx.b(i2);
        kbb kbbVar = this.u;
        if (kbbVar == null) {
            return;
        }
        String a2 = kbbVar.a();
        if (a2 != null) {
            if (!a2.contains("AW")) {
                str2 = str2 + str3 + str4;
            }
        } else {
            str2 = str2 + str3 + str4;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(4);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        String g = g();
        if (!TextUtils.isEmpty(g)) {
            deviceCommand.setmIdentify(g);
        }
        a(deviceCommand);
    }

    private void d(int[] iArr) {
        try {
            if (iArr.length > 0 && iArr[0] == 100000) {
                this.c = 0;
                this.m = 0;
                this.f = this.l;
                this.x.clear();
                Handler handler = this.ac;
                if (handler != null) {
                    handler.removeMessages(15);
                    this.ac.removeMessages(7);
                    this.ac.sendEmptyMessageDelayed(7, this.ae);
                }
            } else {
                c();
                if (this.y != null) {
                    this.w.cutFolder(jrx.b, jrx.c);
                    jsd.c(this.aa, 10002, l(), this.y);
                }
            }
        } catch (Exception unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "applySleepOrDFXDataHandle errorCodeHandle Exception");
        }
    }

    private void e(kbc kbcVar) {
        try {
            if (kbcVar != null) {
                a(kbcVar);
            } else {
                LogUtil.h("HwFileTransferTaskAw70Manager", "maintenanceLog is null");
            }
        } catch (Exception unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "queryOtaAllowHandle Exception");
        }
    }

    private void a(kbc kbcVar) {
        int d2 = kbcVar.d();
        if (d2 == this.c) {
            byte[] e = kbcVar.e();
            this.m += e.length;
            this.x.add(e);
            this.f += e.length;
            this.c++;
        } else {
            LogUtil.a("HwFileTransferTaskAw70Manager", "addSleepOrDfxPlayDataToList() lost index:", Integer.valueOf(d2));
        }
        LogUtil.c("HwFileTransferTaskAw70Manager", "addSleepOrDfxPlayDataToList() mCurrentApplyDataSize:", Integer.valueOf(this.f14141a), ",mDonePackageSize:", Integer.valueOf(this.m), ",mCurrentSleepOrDfxFrameNum:", Integer.valueOf(this.c), ",index:", Integer.valueOf(d2));
        if (this.f14141a == this.m) {
            j();
        }
    }

    private void j() {
        this.z = 0;
        this.m = 0;
        Handler handler = this.ac;
        if (handler == null) {
            return;
        }
        handler.removeMessages(7);
        this.l = this.f;
        MaintenaceInterface maintenaceInterface = this.w;
        if (maintenaceInterface != null) {
            maintenaceInterface.writeLogToFile(this.x, this.o, this.g);
        }
        this.x.clear();
        if (this.p <= 0) {
            i();
            return;
        }
        LogUtil.a("HwFileTransferTaskAw70Manager", "addSleepOrDFXPlayData2List() send ok mDoneTotalSize is ", Integer.valueOf(this.l), ", mFileTotalSize is ", Integer.valueOf(this.p));
        if (this.l >= this.p) {
            this.k += this.o + ",";
            ArrayList arrayList = this.t;
            if (arrayList != null && arrayList.size() > 0) {
                this.t.remove(0);
            }
            MaintenaceInterface maintenaceInterface2 = this.w;
            DeviceCommand transferFileEndProcess = maintenaceInterface2 != null ? maintenaceInterface2.transferFileEndProcess() : null;
            if (transferFileEndProcess != null) {
                LogUtil.a("HwFileTransferTaskAw70Manager", "addSleepOrDFXPlayData2List transferFileEndProcess sendDeviceData");
                a(transferFileEndProcess);
            }
            MaintenaceInterface maintenaceInterface3 = this.w;
            if (maintenaceInterface3 != null) {
                maintenaceInterface3.save2File(new IBaseResponseCallback() { // from class: jvz.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        jvz.this.ac.sendEmptyMessage(14);
                    }
                }, true);
                return;
            }
            return;
        }
        MaintenaceInterface maintenaceInterface4 = this.w;
        if (maintenaceInterface4 != null) {
            maintenaceInterface4.save2File(new IBaseResponseCallback() { // from class: jvz.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    jvz.this.a();
                }
            }, false);
        }
    }

    private void i() {
        LogUtil.h("HwFileTransferTaskAw70Manager", "addSleepOrDfxPlayData2List() lost package error");
        c();
        if (this.y == null) {
            return;
        }
        MaintenaceInterface maintenaceInterface = this.w;
        if (maintenaceInterface != null) {
            maintenaceInterface.cutFolder(jrx.b, jrx.c);
        }
        jsd.c(this.aa, 10002, "package error", this.y);
    }

    public void a() {
        boolean z;
        int i = this.p - this.l;
        LogUtil.a("HwFileTransferTaskAw70Manager", "Enter getSleepOrDfxApplyDataFromDevice spareSize is ", Integer.valueOf(i), ", mCurrentApplyDataSize is ", Integer.valueOf(this.f14141a));
        kbb kbbVar = this.u;
        if (kbbVar == null) {
            return;
        }
        String a2 = kbbVar.a();
        if (a2 != null) {
            LogUtil.a("HwFileTransferTaskAw70Manager", "getSleepOrDfxApplyDataFromDevice version : ", a2);
            if (a2.contains("AW")) {
                z = true;
                if (i > this.f14141a || z) {
                    this.f14141a = i;
                    LogUtil.a("HwFileTransferTaskAw70Manager", "getSleepOrDfxApplyDataFromDevice request final package mCurrentApplyDataSize:", Integer.valueOf(i));
                    b(this.o, this.l, this.f14141a);
                } else {
                    int i2 = this.v;
                    this.f14141a = i2;
                    LogUtil.a("HwFileTransferTaskAw70Manager", "getSleepOrDfxApplyDataFromDevice request next package mCurrentApplyDataSize:", Integer.valueOf(i2));
                    b(this.o, this.l, this.f14141a);
                    return;
                }
            }
        }
        z = false;
        if (i > this.f14141a) {
        }
        this.f14141a = i;
        LogUtil.a("HwFileTransferTaskAw70Manager", "getSleepOrDfxApplyDataFromDevice request final package mCurrentApplyDataSize:", Integer.valueOf(i));
        b(this.o, this.l, this.f14141a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int[] iArr) {
        try {
            if (iArr.length <= 0 || iArr[0] == 100000) {
                return;
            }
            c();
            if (this.y != null) {
                this.w.cutFolder(jrx.b, jrx.c);
                jsd.c(this.aa, iArr[0], jvk.b().c(iArr[0]), this.y);
            }
        } catch (Exception unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "errorCodeHandle Exception");
        }
    }

    private void f() {
        this.f14141a = this.v;
        this.p = 0;
        this.l = 0;
        this.f = 0;
        this.m = 0;
        this.n = 0L;
        this.x.clear();
    }

    public void b() {
        LogUtil.a("HwFileTransferTaskAw70Manager", "enter resetMaintenance ");
        f();
        this.r = false;
        this.z = 0;
        this.e = "All file:";
        this.k = "Done file:";
        jss.b((BtDeviceResponseCallback) null);
        this.ae = 5000;
        this.v = 732;
        this.ad = 244;
    }

    public void c() {
        LogUtil.a("HwFileTransferTaskAw70Manager", "enter resetSleepOrDFXMaintenance ");
        d();
        jss.b((BtDeviceResponseCallback) null);
    }

    public void d() {
        LogUtil.a("HwFileTransferTaskAw70Manager", "enter resetDFXMaintenance ");
        f();
        Handler handler = this.ac;
        if (handler != null) {
            handler.removeMessages(15);
        }
        this.c = -1;
        this.r = false;
        this.z = 0;
        this.e = "All file:";
        this.k = "Done file:";
        this.ae = 5000;
        this.v = 732;
        this.ad = 244;
    }

    private String l() {
        String str = this.e + ";" + this.k;
        LogUtil.a("HwFileTransferTaskAw70Manager", "reportStatusFileList result:", str);
        return str;
    }

    private void a(DeviceCommand deviceCommand) {
        blt.d("HwFileTransferTaskAw70Manager", deviceCommand.getDataContent(), "sendCommand deviceCommand: ");
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public void d(byte[] bArr) {
        this.j.d(0, bArr);
    }

    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                int i = message.what;
                if (i == 1) {
                    jvz.this.bKB_(message);
                } else if (i == 7) {
                    jvz.this.k();
                } else if (i == 127) {
                    int[] a2 = message.obj instanceof byte[] ? jvz.this.ab.a((byte[]) message.obj) : null;
                    if (a2 instanceof int[]) {
                        jvz.this.e(a2);
                    }
                } else if (i == 14) {
                    jvz.this.o();
                } else if (i == 15) {
                    jvz.this.m();
                } else {
                    LogUtil.c("HwFileTransferTaskAw70Manager", "DfxAndSleepHandle handleMessage default");
                }
            } catch (cwg unused) {
                LogUtil.b("HwFileTransferTaskAw70Manager", "DfxAndSleepHandle tlvException");
            }
            jvz.this.bKC_(message);
            jvz.this.bKD_(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        ArrayList arrayList = this.t;
        if (arrayList != null && arrayList.size() > 0) {
            if (this.t.get(0) instanceof String) {
                this.o = (String) this.t.get(0);
            }
            LogUtil.a("HwFileTransferTaskAw70Manager", "start querySleepOrDfxFileInformation fileName = ", this.o);
            this.c = -1;
            d(this.o);
            return;
        }
        IBaseResponseCallback iBaseResponseCallback = this.y;
        c();
        this.w.onDestroyMaintenance();
        if (iBaseResponseCallback != null) {
            this.w.cutFolder(jrx.b, jrx.c);
            LogUtil.a("HwFileTransferTaskAw70Manager", "dataPath is ", this.w.getmTransferDataContentPath());
            jvo jvoVar = new jvo();
            jvoVar.b(this.w.getmTransferDataContentPath());
            jvoVar.a(this.w.getmTransferStateContentPath());
            jsd.c(this.aa, 10000, jvoVar, this.y);
        } else {
            LogUtil.h("HwFileTransferTaskAw70Manager", "callback is null");
        }
        LogUtil.a("HwFileTransferTaskAw70Manager", "maintenance success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        int i = this.z + 1;
        this.z = i;
        if (i == 3) {
            LogUtil.a("HwFileTransferTaskAw70Manager", "mRetryTimeTransform is already 3");
            c();
            if (this.y != null) {
                this.w.cutFolder(jrx.b, jrx.c);
                jsd.c(this.aa, 10002, l(), this.y);
            } else {
                LogUtil.h("HwFileTransferTaskAw70Manager", "maintenanceCallback is null!");
            }
            Handler handler = this.ac;
            if (handler != null) {
                handler.removeMessages(7);
                return;
            }
            return;
        }
        if (this.ac != null) {
            LogUtil.a("HwFileTransferTaskAw70Manager", "Packet loss repeat");
            a();
            this.ac.sendEmptyMessageDelayed(7, this.ae);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogUtil.a("HwFileTransferTaskAw70Manager", "data transfer time out");
        c();
        if (this.y != null) {
            this.w.cutFolder(jrx.b, jrx.c);
            jsd.c(this.aa, 10001, "30s timeout", this.y);
        } else {
            LogUtil.h("HwFileTransferTaskAw70Manager", "maintenanceCallback is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKD_(Message message) {
        try {
            int i = message.what;
            if (i == 4) {
                int[] d2 = message.obj instanceof byte[] ? this.ab.d((byte[]) message.obj) : null;
                if (d2 instanceof int[]) {
                    d(d2);
                    return;
                }
                return;
            }
            if (i == 5) {
                kbc e = message.obj instanceof byte[] ? this.ab.e((byte[]) message.obj) : null;
                kbc kbcVar = e;
                e(e);
            } else {
                if (i == 11) {
                    Handler handler = this.ac;
                    if (handler != null) {
                        handler.removeMessages(15);
                    }
                    e(message.obj instanceof byte[] ? (byte[]) message.obj : null);
                    return;
                }
                LogUtil.a("HwFileTransferTaskAw70Manager", "processHandleMessageCommandData default");
            }
        } catch (cwg unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "processHandleMessageCommandData handleMessage TlvException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKC_(Message message) {
        Object obj = new Object();
        try {
            int i = message.what;
            if (i == 2) {
                Handler handler = this.ac;
                if (handler != null) {
                    handler.removeMessages(15);
                }
                if (message.obj instanceof byte[]) {
                    obj = this.ab.g((byte[]) message.obj);
                }
                if (obj instanceof kbb) {
                    b((kbb) obj);
                    return;
                }
                return;
            }
            if (i == 3) {
                Handler handler2 = this.ac;
                if (handler2 != null) {
                    handler2.removeMessages(15);
                }
                byte[] bArr = new byte[0];
                if (message.obj instanceof byte[]) {
                    bArr = (byte[]) message.obj;
                }
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    c();
                    if (this.y != null) {
                        this.w.onDestroyMaintenance();
                        jsd.c(this.aa, 10002, cvx.d(bArr), this.y);
                        return;
                    }
                    return;
                }
                if (message.obj instanceof byte[]) {
                    obj = this.ab.i((byte[]) message.obj);
                }
                if (obj instanceof kay) {
                    e((kay) obj);
                    return;
                }
                return;
            }
            LogUtil.a("HwFileTransferTaskAw70Manager", "processHandleMessageCommand default");
        } catch (cwg unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "processHandleMessageCommand handleMessage TlvException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKB_(Message message) {
        Handler handler = this.ac;
        if (handler != null) {
            handler.removeMessages(15);
        }
        if (message.obj instanceof byte[]) {
            byte[] bArr = (byte[]) message.obj;
            try {
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    if (a(bArr)) {
                        this.af = this.aa.getIsUploadAppLog();
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "last_trigger_beta_log_time", String.valueOf(0), new StorageParams(0));
                        d();
                        Handler handler2 = this.ac;
                        if (handler2 != null) {
                            handler2.sendEmptyMessageDelayed(15, 600000L);
                        }
                        if (this.s || this.y == null) {
                            return;
                        }
                        LogUtil.a("HwFileTransferTaskAw70Manager", "is LEO return callback");
                        this.y.d(110002, null);
                        return;
                    }
                    c();
                    if (this.y != null) {
                        this.w.onDestroyMaintenance();
                        jsd.c(this.aa, 10002, cvx.d(bArr), this.y);
                        return;
                    }
                    return;
                }
                a(this.ab.c(bArr));
            } catch (cwg unused) {
                LogUtil.b("HwFileTransferTaskAw70Manager", "handleMessage COMMAND_ID_MAINT_GET_FILE_NAME TlvException");
            }
        }
    }

    private void e(byte[] bArr) {
        LogUtil.a("HwFileTransferTaskAw70Manager", "log from about activity:", Boolean.valueOf(this.s));
        if (this.s) {
            a(this.aa, this.y);
            this.s = false;
            this.aa.setIsFromAbout(0);
            return;
        }
        if (CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") && !jsf.c()) {
            if (bArr == null || bArr.length <= 3 || bArr[2] != 1) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            try {
                String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "last_trigger_beta_log_time");
                LogUtil.a("HwFileTransferTaskAw70Manager", "DeviceTriggerDFXReceiver startMainteFile: sharedPreference is ", b2);
                long parseLong = !TextUtils.isEmpty(b2) ? Long.parseLong(b2) : 0L;
                LogUtil.a("HwFileTransferTaskAw70Manager", "DeviceTriggerDFXReceiver startMainteFile: curTime:", Long.valueOf(currentTimeMillis), ", lastTime:", Long.valueOf(parseLong));
                if (currentTimeMillis - parseLong < AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL) {
                    LogUtil.h("HwFileTransferTaskAw70Manager", "two hour wait");
                    return;
                }
            } catch (NumberFormatException e) {
                LogUtil.b("HwFileTransferTaskAw70Manager", "DeviceDFXReceiver NumberFormatException", e.getMessage());
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "last_trigger_beta_log_time", String.valueOf(currentTimeMillis), new StorageParams(0));
            jvd.d(BaseApplication.getContext()).d();
            return;
        }
        LogUtil.h("HwFileTransferTaskAw70Manager", "the version is not beta. do not support 11");
    }

    private boolean a(byte[] bArr) {
        try {
            return Integer.parseInt(cvx.d(bArr).substring(8), 16) == 110002;
        } catch (NumberFormatException unused) {
            LogUtil.b("HwFileTransferTaskAw70Manager", "value is not number");
            return false;
        }
    }

    private String g() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HwFileTransferTaskAw70Manager");
        DeviceInfo deviceInfo = deviceList.size() != 0 ? deviceList.get(0) : null;
        return deviceInfo != null ? deviceInfo.getDeviceIdentify() : "";
    }
}
