package defpackage;

import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.common.utils.PowerUtil;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jvi implements ParserInterface {
    private static final Object b = new Object();
    private static jvi c;
    private int ad;
    private int ar;
    private TransferFileInfo at;
    private boolean d;
    private IBaseResponseCallback g;
    private ExtendHandler q;
    private long y;
    private boolean ab = false;
    private SparseArray<ExtendHandler> as = new SparseArray<>();
    private ExtendHandler aa = null;
    private ArrayList u = null;
    private String x = null;
    private MaintenaceInterface al = null;
    private int aq = 0;
    private ArrayList<byte[]> an = new ArrayList<>(16);
    private int j = 2440;
    private int aj = 732;
    private int z = 0;
    private int v = 0;
    private int r = 0;
    private boolean ai = false;

    /* renamed from: a, reason: collision with root package name */
    private String f14120a = "All file:";
    private String s = "Done file:";
    private Date o = new Date();
    private int ax = 5000;
    private kbb am = null;
    private String k = "";
    private String t = "";
    private int l = -1;
    private int ak = 1;
    private int aw = 244;
    private int i = -1;
    private int h = 0;
    private int p = 0;
    private int ao = 0;
    private int af = -1;
    private int ae = 1;
    private int ac = -1;
    private long w = -1;
    private int e = 0;
    private boolean ah = false;
    private boolean ag = false;
    private Map<Integer, byte[]> m = new HashMap(16);
    private BtDeviceResponseCallback f = new BtDeviceResponseCallback() { // from class: jvi.3
        @Override // com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback
        public void onResponse(int i, Object obj) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                kbe kbeVar = new kbe();
                kbeVar.c(0);
                kbeVar.c(bArr);
                if (bArr.length != 8 || bArr[2] != Byte.MAX_VALUE) {
                    jvk.b().c(jvi.this.aa, 15);
                    jvi.this.d(kbeVar, 1);
                    return;
                } else {
                    blt.d("HwFileTransferTaskManager", bArr, "mApplyWearAssetFileCallback() onResponse error: ");
                    return;
                }
            }
            iyv.d();
        }
    };
    private BtDeviceResponseCallback ap = new BtDeviceResponseCallback() { // from class: jvi.5
        @Override // com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback
        public void onResponse(int i, Object obj) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                blt.d("HwFileTransferTaskManager", bArr, "mPeriodRriFileCallBack");
                kbe kbeVar = new kbe();
                kbeVar.c(0);
                kbeVar.c(bArr);
                if (bArr.length != 8 || bArr[2] != Byte.MAX_VALUE) {
                    jvk.b().c(jvi.this.aa, 15);
                    LogUtil.a("HwFileTransferTaskManager", "mPeriodRriFileCallBack applyDataFromDeviceHandle");
                    jvi.this.d(kbeVar, 1);
                    return;
                }
                blt.d("HwFileTransferTaskManager", bArr, "mPeriodRriFileCallBack() onResponse error: ");
            }
        }
    };
    private IBaseResponseCallback n = new IBaseResponseCallback() { // from class: jvi.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr.length < 2) {
                    ReleaseLogUtil.d("Dfx_HwFileTransferTaskManager", "onResponse(), error data, return");
                    return;
                }
                byte b2 = bArr[1];
                LogUtil.a("HwFileTransferTaskManager", "mDeviceResponseCallback onResponse command: ", Integer.valueOf(b2));
                if (b2 == 11) {
                    if (!jvi.this.ai && CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")) {
                        LogUtil.a("HwFileTransferTaskManager", "The current DFX is status is idle, sending a broadcast", "request for processing the report on the board");
                        if (jvi.this.aa == null) {
                            jvi.this.g(1);
                        }
                    } else {
                        LogUtil.a("HwFileTransferTaskManager", "The current DFX status is busy or not supported");
                        return;
                    }
                }
                if (b2 == 12) {
                    jvk.b().c(bArr);
                    return;
                }
                jvh.d(true);
                if (jvk.b().d(b2)) {
                    jvh.b(0);
                }
                if (jvi.this.aa != null) {
                    Message obtain = Message.obtain();
                    obtain.what = b2;
                    obtain.obj = bArr;
                    jvi.this.aa.sendMessage(obtain);
                }
            }
        }
    };
    private kaz av = kaz.c();

    private jvi() {
        ExtendHandler yt_ = HandlerCenter.yt_(new e(), "DFX_AND_SLEEPHwFileTransferTaskManager");
        this.q = yt_;
        this.as.put(1, yt_);
    }

    public static jvi a() {
        jvi jviVar;
        synchronized (b) {
            if (c == null) {
                c = new jvi();
            }
            jviVar = c;
        }
        return jviVar;
    }

    public void e(ExtendHandler extendHandler) {
        this.as.put(0, extendHandler);
    }

    public int q() {
        return this.aq;
    }

    public void i(int i) {
        this.aq = i;
    }

    public kaz w() {
        return this.av;
    }

    public String k() {
        return this.x;
    }

    public void e(String str) {
        this.x = str;
    }

    public int m() {
        return this.ad;
    }

    public void f(int i) {
        this.ad = i;
    }

    public ArrayList<byte[]> n() {
        return this.an;
    }

    public int d() {
        return this.j;
    }

    public void c(int i) {
        this.j = i;
    }

    public int p() {
        return this.aj;
    }

    public void j(int i) {
        this.aj = i;
    }

    public int l() {
        return this.z;
    }

    public void a(int i) {
        this.z = i;
    }

    public int j() {
        return this.v;
    }

    public void b(int i) {
        this.v = i;
    }

    public int g() {
        return this.r;
    }

    public void e(int i) {
        this.r = i;
    }

    public long i() {
        return this.y;
    }

    public void d(long j) {
        this.y = j;
    }

    public String e() {
        return this.f14120a;
    }

    public void c(String str) {
        this.f14120a = str;
    }

    public String f() {
        return this.s;
    }

    public void a(String str) {
        this.s = str;
    }

    public int y() {
        return this.ax;
    }

    public void m(int i) {
        this.ax = i;
    }

    public kbb r() {
        return this.am;
    }

    public void d(kbb kbbVar) {
        this.am = kbbVar;
    }

    public String h() {
        return this.k;
    }

    public int v() {
        return this.aw;
    }

    public void k(int i) {
        this.aw = i;
    }

    public int c() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public IBaseResponseCallback b() {
        return this.g;
    }

    public TransferFileInfo t() {
        return this.at;
    }

    public int o() {
        return this.af;
    }

    public void h(int i) {
        this.af = i;
    }

    public void g(int i) {
        this.aa = this.as.get(i);
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "setStates() state is :", Integer.valueOf(i));
    }

    public void c(TransferFileInfo transferFileInfo, IBaseResponseCallback iBaseResponseCallback) {
        if (this.ai) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "startMainteFile mIsMaintenance, so return");
            return;
        }
        this.g = iBaseResponseCallback;
        if (transferFileInfo == null) {
            return;
        }
        this.at = transferFileInfo;
        jcr.b(transferFileInfo);
        jvk.b().d(this.at);
        this.ac = -1;
        this.w = -1L;
        this.ah = false;
        if (this.at.getIsFromAbout() == 1) {
            this.ab = true;
        } else {
            this.ab = false;
        }
        LogUtil.a("HwFileTransferTaskManager", "mIsFromActivity:", Integer.valueOf(this.at.getIsFromAbout()));
        this.ai = true;
        if (transferFileInfo.getType() == 1) {
            jvm.a().b(transferFileInfo);
        } else if (transferFileInfo.getType() == 0) {
            c(transferFileInfo);
        } else if (transferFileInfo.getType() == 2) {
            g(1);
            this.al = jry.d();
            jss.b(this.f);
            LogUtil.a("HwFileTransferTaskManager", "request sleep transferFileInfo.getStartTime() :", Integer.valueOf(transferFileInfo.getStartTime()), " ,transferFileInfo.getEndTime() :", Integer.valueOf(transferFileInfo.getEndTime()));
            this.al.setMaintRetryResult(true);
            jvk.b().c(transferFileInfo.getStartTime(), transferFileInfo.getEndTime(), this.aa);
        } else if (transferFileInfo.getType() == 4) {
            d(transferFileInfo);
        } else {
            LogUtil.a("HwFileTransferTaskManager", "request type error transferFileInfo.getType() :", Integer.valueOf(transferFileInfo.getType()));
            z();
            ad();
            this.g.d(10001, " mType not found");
        }
        LogUtil.a("HwFileTransferTaskManager", "file modelType is:", Integer.valueOf(transferFileInfo.getType()), ";transferFile size:", Integer.valueOf(this.z));
    }

    private void d(TransferFileInfo transferFileInfo) {
        g(1);
        jsb e2 = jsb.e();
        this.al = e2;
        e2.setMaintRetryResult(true);
        jss.b(this.ap);
        LogUtil.a("HwFileTransferTaskManager", " request Period_rri file transferFileInfo.getStartTime() :", Integer.valueOf(transferFileInfo.getStartTime()), ",transferFileInfo.getEndTime() :", Integer.valueOf(transferFileInfo.getEndTime()));
        jvk.b().a(transferFileInfo.getStartTime(), transferFileInfo.getEndTime(), this.aa);
    }

    private void c(TransferFileInfo transferFileInfo) {
        g(1);
        LogUtil.a("HwFileTransferTaskManager", "request DFX transferFileInfo.getDeviceMac() :", iyl.d().e(transferFileInfo.getDeviceMac()), " , transferFileInfo.getDeviceVersion() :", transferFileInfo.getDeviceVersion());
        jwa.a(BaseApplication.getContext(), transferFileInfo.getDeviceSn());
        jvl.b().a(System.currentTimeMillis());
        int selectedFlag = transferFileInfo.getSelectedFlag();
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "selectedFlag:", Integer.valueOf(selectedFlag));
        if (!CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD")) {
            this.ak = 0;
        } else if (selectedFlag == 1) {
            this.ak = 2;
        } else {
            this.ak = 1;
        }
        e(transferFileInfo);
        if (TextUtils.isEmpty(this.k) || TextUtils.isEmpty(this.t)) {
            b(transferFileInfo);
            return;
        }
        boolean e2 = jvj.e().e(transferFileInfo);
        if (!this.k.equals(jvk.b().e("DEVICE_MACADDRESS")) || e2) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "Device mac address is different from the previous one");
            jvk.b().e(transferFileInfo, this.k);
        }
        jrx d = jrx.d();
        this.al = d;
        d.initName();
        LogUtil.a("HwFileTransferTaskManager", "DFX registered callback");
        if (transferFileInfo.getDeviceType() == 32) {
            LogUtil.a("HwFileTransferTaskManager", "Device is Cassini");
            jvk.b().e(this.at, this.k);
            ad();
            if (this.g != null) {
                jsd.c(transferFileInfo, 10000, jvk.b().a(this.f14120a, this.s), this.g);
                return;
            }
            return;
        }
        jss.b(this.f);
        jvk.b().e(transferFileInfo.getDfxLogType(), this.at, this.aa);
    }

    private void e(TransferFileInfo transferFileInfo) {
        this.k = transferFileInfo.getDeviceSn();
        this.t = transferFileInfo.getDeviceVersion();
        this.l = transferFileInfo.getDeviceType();
    }

    private void b(TransferFileInfo transferFileInfo) {
        ReleaseLogUtil.d("Dfx_HwFileTransferTaskManager", "Request Dfx parameter is error");
        jvk.b().e(transferFileInfo, this.k);
        ad();
        jsd.c(transferFileInfo, 10001, "parameter is error", this.g);
    }

    private void c(ArrayList arrayList) {
        String str;
        try {
            this.u = arrayList;
            if (arrayList == null) {
                if (this.g != null) {
                    ad();
                    this.al.cutFolder(jrx.b, jrx.c);
                    MaintenaceInterface maintenaceInterface = this.al;
                    if (maintenaceInterface instanceof jry) {
                        maintenaceInterface.setMaintRetryResult(true);
                    }
                    jsd.c(this.at, 10001, "error fileList is null", this.g);
                    return;
                }
                return;
            }
            this.ar = arrayList.size();
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle CommonUtil.isReleaseVersion() = " + CommonUtil.bv());
            if (as()) {
                DeviceInfo a2 = jvl.b().a(this.k);
                if (a2 != null) {
                    if (cwi.c(a2, 217) && kbi.b(a2.getDeviceIdentify())) {
                        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle mTransferFileInfo.getTaskType() = " + this.at.getTaskType());
                        if (this.u.size() == 0) {
                            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle fileList() is 0");
                            ad();
                            if (this.g != null) {
                                this.al.cutFolder(jrx.b, jsd.b);
                                jsd.c(this.at, 10000, jvk.b().a(this.f14120a, this.s), this.g);
                                return;
                            } else {
                                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle mCallback == null");
                                return;
                            }
                        }
                        if (!(this.u.get(0) instanceof String)) {
                            str = "";
                        } else {
                            str = (String) this.u.get(0);
                        }
                        a(this.u, str);
                        return;
                    }
                } else {
                    ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle deviceInfo is null");
                }
            }
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "mLogLevel", Integer.valueOf(this.ak));
            au();
            jvj.e().e(this.u, this.at);
            String str2 = this.f14120a + this.u;
            this.f14120a = str2;
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle mAllFileListName:", str2);
            if (this.u.size() == 0) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle fileList() is 0");
                ad();
                jvk.b().e(this.at, this.k);
                if (this.g != null) {
                    this.al.cutFolder(jrx.b, jrx.c);
                    jsd.c(this.at, 10000, jvk.b().a(this.f14120a, this.s), this.g);
                    return;
                }
                return;
            }
            TransferFileInfo transferFileInfo = this.at;
            if (transferFileInfo != null) {
                this.al.initMaintenanceParame(transferFileInfo);
            }
            jvk.b().c(this.al, this.aa);
        } catch (Exception unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle Exception");
        }
    }

    private boolean as() {
        if (CommonUtil.bv() || this.at.getType() != 0) {
            return false;
        }
        if (this.at.getTaskType() != 0 && this.at.getTaskType() != 1) {
            return false;
        }
        sqo.p("HwFileTransferTaskManager, isDfxNeedWifiP2p");
        return true;
    }

    private void au() {
        DeviceInfo a2 = jvl.b().a(this.k);
        if (a2 == null) {
            ReleaseLogUtil.d("Dfx_HwFileTransferTaskManager", "LogTrustlistFiltering deviceInfo is null");
            int i = this.ak;
            if (i != 0) {
                this.u = this.al.filtertFile(this.u, i);
                return;
            }
            return;
        }
        if (!cwi.c(a2, 129)) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "logTrustListFiltering device not support log trust list");
            int i2 = this.ak;
            if (i2 != 0) {
                this.u = this.al.filtertFile(this.u, i2);
            }
        } else {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "logTrustListFiltering device support log trust list");
        }
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxFileNameHandle mFileSleepOrDfxList:", this.u);
    }

    private void c(kbb kbbVar) {
        if (kbbVar != null) {
            try {
                this.am = kbbVar;
                this.ax = kbbVar.b();
                this.aw = this.am.c();
                if (this.al instanceof jrx) {
                    TransferFileInfo transferFileInfo = this.at;
                    if (transferFileInfo != null && transferFileInfo.getDeviceType() >= 34) {
                        this.aj = this.am.e();
                    }
                    this.j = this.aj;
                } else {
                    this.aj = this.am.e();
                }
                ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxParametersHandle() ok, protocalVersion :", this.am.a(), System.lineSeparator(), "mTransferUnitSize:", Integer.valueOf(this.aw), System.lineSeparator(), "mMaxApplyDataSize:", Integer.valueOf(this.aj), System.lineSeparator(), "mWaitTimeout:", Integer.valueOf(this.ax), System.lineSeparator(), "breakPointEnable:", Boolean.valueOf(this.am.d()));
            } catch (Exception unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "getMaintenanceSleepOrDfxParametersHandle Exception");
                ad();
                if (this.g != null) {
                    this.al.cutFolder(jrx.b, jrx.c);
                    jsd.c(this.at, 10000, jvk.b().a(this.f14120a, this.s), this.g);
                    return;
                }
                return;
            }
        }
        if (this.aa != null) {
            this.al.deleteTenDayFile();
            this.aa.sendEmptyMessage(14);
        }
    }

    private void d(String str) {
        ExtendHandler extendHandler = this.aa;
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            this.aa.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        x();
        this.h = 0;
        if (this.at.getType() == 0) {
            jvk.b().b(str, this.k);
        } else {
            jvk.b().b(str, "");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0087 A[Catch: ClassCastException -> 0x008f, TryCatch #0 {ClassCastException -> 0x008f, blocks: (B:18:0x0006, B:3:0x0025, B:5:0x0051, B:8:0x005a, B:9:0x0083, B:11:0x0087, B:14:0x008b, B:16:0x0064), top: B:17:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008b A[Catch: ClassCastException -> 0x008f, TRY_LEAVE, TryCatch #0 {ClassCastException -> 0x008f, blocks: (B:18:0x0006, B:3:0x0025, B:5:0x0051, B:8:0x005a, B:9:0x0083, B:11:0x0087, B:14:0x008b, B:16:0x0064), top: B:17:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(defpackage.kay r11) {
        /*
            r10 = this;
            java.lang.String r0 = "HwFileTransferTaskManager"
            java.lang.String r1 = "Dfx_HwFileTransferTaskManager"
            if (r11 == 0) goto L25
            long r2 = r11.e()     // Catch: java.lang.ClassCastException -> L8f
            int r2 = (int) r2     // Catch: java.lang.ClassCastException -> L8f
            r10.z = r2     // Catch: java.lang.ClassCastException -> L8f
            long r2 = r11.a()     // Catch: java.lang.ClassCastException -> L8f
            r10.y = r2     // Catch: java.lang.ClassCastException -> L8f
            int r2 = r11.b()     // Catch: java.lang.ClassCastException -> L8f
            r10.ac = r2     // Catch: java.lang.ClassCastException -> L8f
            long r2 = r11.c()     // Catch: java.lang.ClassCastException -> L8f
            r10.w = r2     // Catch: java.lang.ClassCastException -> L8f
            int r11 = r11.d()     // Catch: java.lang.ClassCastException -> L8f
            r10.e = r11     // Catch: java.lang.ClassCastException -> L8f
        L25:
            r11 = 0
            r10.ah = r11     // Catch: java.lang.ClassCastException -> L8f
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.ClassCastException -> L8f
            java.lang.String r4 = "querySleepOrDfxFileInformationHandle() ok, mFileTotalSize:"
            r3[r11] = r4     // Catch: java.lang.ClassCastException -> L8f
            int r4 = r10.z     // Catch: java.lang.ClassCastException -> L8f
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.ClassCastException -> L8f
            r5 = 1
            r3[r5] = r4     // Catch: java.lang.ClassCastException -> L8f
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r3)     // Catch: java.lang.ClassCastException -> L8f
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.ClassCastException -> L8f
            java.lang.String r4 = "querySleepOrDfxFileInformationHandle() ok, mFileCrc = "
            r3[r11] = r4     // Catch: java.lang.ClassCastException -> L8f
            long r6 = r10.y     // Catch: java.lang.ClassCastException -> L8f
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch: java.lang.ClassCastException -> L8f
            r3[r5] = r4     // Catch: java.lang.ClassCastException -> L8f
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)     // Catch: java.lang.ClassCastException -> L8f
            int r3 = r10.ac     // Catch: java.lang.ClassCastException -> L8f
            r4 = -1
            if (r3 != r4) goto L64
            long r6 = r10.w     // Catch: java.lang.ClassCastException -> L8f
            r8 = -1
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 == 0) goto L5a
            goto L64
        L5a:
            jvk r11 = defpackage.jvk.b()     // Catch: java.lang.ClassCastException -> L8f
            com.huawei.hwcommonmodel.datatypes.TransferFileInfo r0 = r10.at     // Catch: java.lang.ClassCastException -> L8f
            r11.c(r0)     // Catch: java.lang.ClassCastException -> L8f
            goto L83
        L64:
            r10.ah = r5     // Catch: java.lang.ClassCastException -> L8f
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.ClassCastException -> L8f
            java.lang.String r6 = "querySleepOrDfxFileInformationHandle ok, mFileTransferType:"
            r4[r11] = r6     // Catch: java.lang.ClassCastException -> L8f
            java.lang.Integer r11 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.ClassCastException -> L8f
            r4[r5] = r11     // Catch: java.lang.ClassCastException -> L8f
            java.lang.String r11 = ", mFileCreateTime:"
            r4[r2] = r11     // Catch: java.lang.ClassCastException -> L8f
            long r2 = r10.w     // Catch: java.lang.ClassCastException -> L8f
            java.lang.Long r11 = java.lang.Long.valueOf(r2)     // Catch: java.lang.ClassCastException -> L8f
            r2 = 3
            r4[r2] = r11     // Catch: java.lang.ClassCastException -> L8f
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)     // Catch: java.lang.ClassCastException -> L8f
        L83:
            int r11 = r10.z     // Catch: java.lang.ClassCastException -> L8f
            if (r11 != 0) goto L8b
            r10.al()     // Catch: java.lang.ClassCastException -> L8f
            goto L98
        L8b:
            r10.an()     // Catch: java.lang.ClassCastException -> L8f
            goto L98
        L8f:
            java.lang.String r11 = "querySleepOrDfxFileInformationHandle ClassCastException"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r1, r11)
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jvi.d(kay):void");
    }

    private void al() {
        ArrayList arrayList = this.u;
        if (arrayList != null && !arrayList.isEmpty()) {
            this.u.remove(0);
            if (ap()) {
                jvk.b().a(this.u);
            }
        }
        ExtendHandler extendHandler = this.aa;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(14);
        }
    }

    private void an() {
        int i;
        if ((this.al instanceof jrx) && this.l != 10) {
            this.d = false;
            at();
            if (this.g != null && (i = this.z) != 0 && this.p <= i) {
                ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfx progress mCallback is not null. WearhomeActivity: ", Integer.valueOf(this.ar), " is ", this.u);
                this.g.d(20001, "0&" + this.x + Constants.LEFT_BRACKET_ONLY + ((this.ar - this.u.size()) + 1) + "/" + this.ar + Constants.RIGHT_BRACKET_ONLY);
            }
            if (this.d) {
                ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "fileContent is return dump file");
                return;
            }
        }
        if (jvk.b().c(this.ah, this.at, this.w, this.ac)) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfx progress TRANSMITTED_SIZE mDoneTotalSize = ", jvk.b().e("TRANSMITTED_SIZE"));
            this.v = CommonUtil.m(BaseApplication.getContext(), jvk.b().e("TRANSMITTED_SIZE"));
        }
        this.o = new Date();
        s();
    }

    private void a(String str, int i, int i2) {
        this.af = 1;
        if (ap() && jvh.h()) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "5.10.4 DFX 4mins Timer is arrived");
            ad();
            jvh.c(jvh.d(), jvh.b());
            av();
            return;
        }
        if (jcr.e() != null && jcr.e().getSuspend() == 1) {
            this.at.setSuspend(1);
            LogUtil.a("HwFileTransferTaskManager", "5.10.4 DFX Tasks are interrupted, and the higher priority is ", Integer.valueOf(this.at.getType()));
            ad();
            if (this.g != null) {
                bb();
                return;
            } else {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "IBaseResponseCallback is null");
                return;
            }
        }
        e(str, i, i2);
    }

    private void av() {
        this.al.save2File(new IBaseResponseCallback() { // from class: jvi.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (jvi.this.u()) {
                    jvk.b().c("IS_CONTINUE", "true");
                } else {
                    jvk.b().c("IS_CONTINUE", "false");
                }
                jvi.this.al.cutFolder(jrx.b, jrx.c);
                jsd.c(jvi.this.at, 10000, jvk.b().a(jvi.this.f14120a, jvi.this.s), jvi.this.g);
            }
        }, true);
    }

    private void e(String str, int i, int i2) {
        ExtendHandler extendHandler = this.aa;
        if (extendHandler != null) {
            extendHandler.removeMessages(7);
            this.aa.sendEmptyMessage(7, this.ax);
        }
        this.ao = i;
        if (this.at.getType() == 0) {
            jvk.b().c(str, i, i2, this.am, this.k);
        } else {
            jvk.b().c(str, i, i2, this.am, "");
        }
    }

    private void bb() {
        MaintenaceInterface maintenaceInterface = this.al;
        if (maintenaceInterface instanceof jrx) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "Tasks are interrupted,the higher is", Integer.valueOf(this.at.getTaskType()));
            this.al.save2File(new IBaseResponseCallback() { // from class: jvi.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    jvh.c(jvh.d(), jvh.b());
                    jvk.b().e(jvi.this.at, jvi.this.ac);
                    jvi.this.al.cutFolder(jrx.b, jrx.c);
                    if (jvi.this.aa != null) {
                        jvi.this.aa.postTask(new Runnable() { // from class: jvi.10.2
                            @Override // java.lang.Runnable
                            public void run() {
                                LogUtil.a("HwFileTransferTaskManager", "taskInterrupt callback ERROR_CODE_NUMBER_SUSPEND_ERROR");
                                jvi.this.g.d(104003, jvk.b().a(jvi.this.f14120a, jvi.this.s));
                            }
                        }, 2000L);
                    } else {
                        LogUtil.a("HwFileTransferTaskManager", "taskInterrupt mHandler is null");
                    }
                }
            }, true);
            return;
        }
        maintenaceInterface.cutFolder(jrx.b, jrx.c);
        MaintenaceInterface maintenaceInterface2 = this.al;
        if (maintenaceInterface2 instanceof jry) {
            maintenaceInterface2.setMaintRetryResult(true);
        }
        this.g.d(104003, jvk.b().a(this.f14120a, this.s));
    }

    private void d(int[] iArr) {
        if (iArr != null) {
            try {
                int i = this.ao;
                int i2 = iArr.length > 0 ? iArr[0] : -1;
                int i3 = iArr.length > 1 ? iArr[1] : i;
                if (!(this.al instanceof jrx)) {
                    i = i3;
                }
                ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "applySleepOrDfxDataHandle objectData[0]:", Integer.valueOf(i2), "objectData[1]:", Integer.valueOf(i), " mRequestApplyOffset:", Integer.valueOf(this.ao));
                if (i2 == 100000) {
                    l(i);
                    return;
                }
                ad();
                if (this.g != null) {
                    o(i2);
                }
            } catch (ClassCastException unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "applySleepOrDfxDataHandle errorCodeHandle ClassCastException");
            }
        }
    }

    private void l(int i) {
        if (i == this.ao) {
            this.i = 0;
            this.m.clear();
            this.r = 0;
            this.p = this.v;
            this.an.clear();
            ExtendHandler extendHandler = this.aa;
            if (extendHandler != null) {
                extendHandler.removeMessages(15);
                this.aa.removeMessages(7);
                this.aa.sendEmptyMessage(7, this.ax);
                return;
            }
            return;
        }
        ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "responseApplyOffset is inequal throw error package");
    }

    private void o(int i) {
        MaintenaceInterface maintenaceInterface = this.al;
        if (maintenaceInterface instanceof jrx) {
            jvk.b().a(i, this.g, this.al, this.at, this.ac);
            return;
        }
        maintenaceInterface.cutFolder(jrx.b, jrx.c);
        MaintenaceInterface maintenaceInterface2 = this.al;
        if (maintenaceInterface2 instanceof jry) {
            maintenaceInterface2.setMaintRetryResult(true);
        }
        this.g.d(10002, jvk.b().a(this.f14120a, this.s));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(kbe kbeVar, int i) {
        if (kbeVar != null) {
            try {
                if (i != 1) {
                    jvm.a().b(i, kbeVar);
                } else {
                    b(kbeVar);
                }
            } catch (Exception unused) {
                z();
                if (this.g != null) {
                    jsd.c(this.at, 10002, jvk.b().a(this.f14120a, this.s), this.g);
                }
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "applyDataFromDeviceHandle Exception");
            }
        }
    }

    private void b(kbc kbcVar) {
        if (kbcVar != null) {
            try {
                if (jvt.a().b(kbcVar)) {
                    c(kbcVar);
                } else {
                    ReleaseLogUtil.d("Dfx_HwFileTransferTaskManager", "maintenanceLog is null");
                }
            } catch (Exception unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "queryOtaAllowHandle Exception");
            }
        }
    }

    private void ax() {
        ExtendHandler extendHandler = this.aa;
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            this.aa.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        if (this.at.getType() == 0) {
            jvk.b().a(this.k);
        }
    }

    private void c(kbc kbcVar) {
        int d = kbcVar.d();
        if (d == this.i) {
            byte[] e2 = kbcVar.e();
            this.r += e2.length;
            this.an.add(e2);
            this.p += e2.length;
            this.i++;
            ao();
        } else {
            this.m.put(Integer.valueOf(d), kbcVar.e());
            ReleaseLogUtil.d("Dfx_HwFileTransferTaskManager", "addSleepOrDfxPlayDataToList() lost index = ", Integer.valueOf(d));
        }
        LogUtil.a("HwFileTransferTaskManager", "addSleepOrDfxPlayDataToList() mCurrentApplyDataSize =", Integer.valueOf(this.j), ", mDonePackageSize = ", Integer.valueOf(this.r), ", mCurrentSleepOrDfxFrameNum = ", Integer.valueOf(this.i), ", index = ", Integer.valueOf(d));
        if (this.j == this.r) {
            this.aq = 0;
            this.r = 0;
            ExtendHandler extendHandler = this.aa;
            if (extendHandler != null) {
                extendHandler.removeMessages(7);
                this.v = this.p;
                MaintenaceInterface maintenaceInterface = this.al;
                if (maintenaceInterface != null) {
                    maintenaceInterface.writeLogToFile(this.an, this.x, this.o);
                }
                this.an.clear();
                if (this.z <= 0) {
                    ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "addSleepOrDfxPlayDataToList() lost package error");
                    ad();
                    jvk.b().e(this.g, this.al, this.at, this.ac);
                } else {
                    ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "addSleepOrDfxPlayDataToList() send ok mDoneTotalSize:", Integer.valueOf(this.v), ",mFileTotalSize = ", Integer.valueOf(this.z), ",file name = ", this.x);
                    bc();
                    if (this.v >= this.z) {
                        am();
                    } else {
                        aj();
                    }
                }
            }
        }
    }

    private void bc() {
        int i;
        jvk.b().e(this.x, this.g, this.z, this.v, this.p);
        if (!(this.al instanceof jrx) || this.g == null || (i = this.z) == 0 || this.p > i) {
            return;
        }
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfx progress mCallback is not null");
        IBaseResponseCallback iBaseResponseCallback = this.g;
        iBaseResponseCallback.d(20001, ((this.v * 100) / this.z) + "&" + this.x + Constants.LEFT_BRACKET_ONLY + ((this.ar - this.u.size()) + 1) + "/" + this.ar + Constants.RIGHT_BRACKET_ONLY);
    }

    private void am() {
        this.s += this.x + ",";
        ArrayList arrayList = this.u;
        if (arrayList != null && !arrayList.isEmpty()) {
            this.u.remove(0);
            if (ap()) {
                jvk.b().c(this.u);
            }
        }
        jvk.b().a(this.al, this.aa, this.at, this.ac);
    }

    private void aj() {
        if (this.al instanceof jrx) {
            ExtendHandler extendHandler = this.aa;
            if (extendHandler != null) {
                extendHandler.removeMessages(13);
                this.aa.sendEmptyMessage(13, OpAnalyticsConstants.H5_LOADING_DELAY);
            }
            this.al.save2File(new IBaseResponseCallback() { // from class: jvi.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HwFileTransferTaskManager", "onResponse fileTransfer: ", Integer.valueOf(i));
                    if (jvi.this.aa != null) {
                        jvi.this.aa.removeMessages(13);
                        if (i == 10001) {
                            jvi.this.aa.sendEmptyMessage(12);
                            return;
                        }
                    }
                    if (jvi.this.u()) {
                        jvk.b().c("IS_CONTINUE", "true");
                        jvk.b().c("TRANSMITTED_SIZE", String.valueOf(jvi.this.v));
                    }
                    jvi.this.s();
                }
            }, false);
            return;
        }
        s();
    }

    private void b(kbe kbeVar) {
        int d = kbeVar.d();
        if (d == this.h) {
            byte[] b2 = kbeVar.b();
            if (b2 != null) {
                this.r += b2.length;
                this.ad = 0;
                LogUtil.a("HwFileTransferTaskManager", "addSleepDataFromAndroidWear() mFileType:", 0, "valueByte.length:", Integer.valueOf(b2.length));
                if (this.ad != 1) {
                    this.an.add(b2);
                } else {
                    try {
                        this.an.add(cvx.d(b2).getBytes("UTF-8"));
                    } catch (UnsupportedEncodingException unused) {
                        ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "addSleepDataFromAndroidWear exception is UnsupportedEncodingException");
                    }
                }
                this.v += b2.length;
            } else {
                LogUtil.h("HwFileTransferTaskManager", "addSleepDataFromAndroidWear() byteData is null");
            }
            this.h++;
        } else {
            LogUtil.a("HwFileTransferTaskManager", "addSleepDataFromAndroidWear() lost index", Integer.valueOf(d));
        }
        LogUtil.a("HwFileTransferTaskManager", "mCurrentApplyDataSize:", Integer.valueOf(this.j), ",mDonePackageSize:", Integer.valueOf(this.r), ",mCurrentSleepOrDfxFrameNum:", Integer.valueOf(this.i), ",index:", Integer.valueOf(d));
        if (this.v >= this.z) {
            this.aq = 0;
            this.r = 0;
            if (this.aa != null) {
                ac();
            }
        }
    }

    private void ac() {
        this.aa.removeMessages(7);
        MaintenaceInterface maintenaceInterface = this.al;
        if (maintenaceInterface != null) {
            maintenaceInterface.writeLogToFile(this.an, this.x, this.o);
        }
        this.an.clear();
        int i = this.z;
        if (i <= 0) {
            LogUtil.h("HwFileTransferTaskManager", "addSleepDataFromAndroidWear() lost package error");
            ad();
            jvk.b().d(this.g, this.al);
        } else {
            if (this.v >= i) {
                aq();
                jvk.b().e(this.af, this.aa);
                return;
            }
            MaintenaceInterface maintenaceInterface2 = this.al;
            if (maintenaceInterface2 instanceof jrx) {
                maintenaceInterface2.save2File(new IBaseResponseCallback() { // from class: jvi.9
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        jvi.this.s();
                    }
                }, false);
            } else {
                s();
            }
        }
    }

    private void aq() {
        this.s += this.x + ",";
        ArrayList arrayList = this.u;
        if (arrayList != null && !arrayList.isEmpty()) {
            this.u.remove(0);
        }
        jvk.b().a(this.al, this.aa);
    }

    public void s() {
        int i = this.z - this.v;
        String a2 = this.am.a();
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "Enter getSleepOrDFXApplyDataFromDevice spareSize = ", Integer.valueOf(i), ",mCurrentApplyDataSize = ", Integer.valueOf(this.j), ",mMaxApplyDataSize : ", Integer.valueOf(this.aj), ",version : ", a2);
        boolean z = a2 != null && a2.contains("AW");
        if (i <= this.j || z) {
            this.j = i;
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "requeset final package mCurrentApplyDataSize:", Integer.valueOf(i));
            a(this.x, this.v, this.j);
        } else {
            int i2 = this.aj;
            this.j = i2;
            a(this.x, this.v, i2);
        }
    }

    private void a(int[] iArr) {
        if (iArr != null) {
            try {
                if (iArr.length > 0 && iArr[0] != 100000) {
                    ad();
                    if (this.g != null) {
                        jvk.b().b(iArr, this.g, this.al, this.at, this.ac);
                    }
                }
            } catch (Exception unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "errorCodeHandle Exception");
            }
        }
    }

    public void x() {
        this.j = this.aj;
        this.z = 0;
        this.v = 0;
        this.p = 0;
        this.ao = 0;
        this.r = 0;
        this.y = 0L;
        this.an.clear();
    }

    public void z() {
        LogUtil.a("HwFileTransferTaskManager", "enter resetMaintenance()");
        x();
        this.h = 0;
        this.ai = false;
        this.aq = 0;
        this.f14120a = "All file:";
        this.s = "Done file:";
        jss.b((BtDeviceResponseCallback) null);
        this.ax = 5000;
        this.aj = 732;
        this.aw = 244;
    }

    public void ad() {
        LogUtil.a("HwFileTransferTaskManager", "enter resetSleepOrDfxMaintenance()");
        this.ab = false;
        this.k = "";
        ay();
        jss.b((BtDeviceResponseCallback) null);
    }

    public void ab() {
        LogUtil.a("HwFileTransferTaskManager", "enter resetDfxMaintenance()");
        ay();
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null) {
            LogUtil.h("HwFileTransferTaskManager", "getResult deviceInfo is null");
            this.n.d(-1, bArr);
        } else if (cvt.c(deviceInfo.getProductType())) {
            jvz.e().d(bArr);
        } else {
            this.n.d(0, bArr);
        }
    }

    private void ao() {
        if (this.m.size() <= 0 || this.m.get(Integer.valueOf(this.i)) == null) {
            return;
        }
        byte[] bArr = this.m.get(Integer.valueOf(this.i));
        this.r += bArr.length;
        this.an.add(bArr);
        this.p += bArr.length;
        this.i++;
        ao();
    }

    private void ay() {
        x();
        ExtendHandler extendHandler = this.aa;
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            this.aa.removeMessages(7);
        }
        this.i = -1;
        this.m.clear();
        this.ai = false;
        this.aq = 0;
        this.f14120a = "All file:";
        this.s = "Done file:";
        this.ax = 5000;
        this.aj = 732;
        this.aw = 244;
        this.ag = false;
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 9) {
                if (message.what != 4 && message.what != 5) {
                    ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "DfxAndSleepHandle handleMessage msg: ", Integer.valueOf(message.what));
                } else {
                    LogUtil.a("HwFileTransferTaskManager", "DfxAndSleepHandle handleMessage msg: ", Integer.valueOf(message.what));
                }
            }
            try {
                return bJZ_(message);
            } catch (ClassCastException unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "DfxAndSleepHandle ClassCastException");
                return true;
            } catch (NumberFormatException unused2) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "DfxAndSleepHandle NumberFormatException");
                return true;
            } catch (Exception unused3) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "DfxAndSleepHandle Exception");
                return true;
            }
        }

        private boolean bJZ_(Message message) {
            if (message == null) {
                return false;
            }
            int i = message.what;
            if (i == 1) {
                jvi.this.bJU_(message);
                return true;
            }
            if (i == 2) {
                jvi.this.bJV_(message);
                return true;
            }
            if (i == 3) {
                jvi.this.bJX_(message);
                return true;
            }
            if (i == 4) {
                jvi.this.bJR_(message);
                return true;
            }
            if (i == 5) {
                jvi.this.bJW_(message);
                return true;
            }
            if (i == 7) {
                jvi.this.ag();
                return true;
            }
            if (i != 127) {
                switch (i) {
                    case 10:
                        jvi.this.bJY_(message);
                        return true;
                    case 11:
                        jvi.this.bJS_(message);
                        return true;
                    case 12:
                    case 13:
                        jvi.this.ae();
                        return true;
                    case 14:
                        jvi.this.ah();
                        return true;
                    case 15:
                        jvi.this.af();
                        return true;
                    default:
                        LogUtil.h("HwFileTransferTaskManager", "DfxAndSleepHandle is default");
                        return false;
                }
            }
            jvi.this.bJT_(message);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "save log error");
        ad();
        IBaseResponseCallback iBaseResponseCallback = this.g;
        if (iBaseResponseCallback != null) {
            jsd.c(this.at, 10001, "save log error", iBaseResponseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        jrp.c();
        ArrayList arrayList = this.u;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (this.u.get(0) instanceof String) {
                this.x = (String) this.u.get(0);
            }
            if (jvk.b().b(this.at)) {
                jvk.b().c("CONTINUE_LOG_NAME", this.x);
            }
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "start querySleepOrDfxFileInformation mFileName:", this.x);
            this.i = -1;
            this.m.clear();
            d(this.x);
            return;
        }
        if (CommonUtil.as() && ar()) {
            LogUtil.a("HwFileTransferTaskManager", "start query raw file info info");
            this.ag = true;
            ax();
        } else {
            ad();
            jvk.b().b(this.at, this.g, this.al);
        }
    }

    private boolean ar() {
        LogUtil.a("HwFileTransferTaskManager", "raw file mIsRawFileHandled is : ", Boolean.valueOf(this.ag));
        if (this.ag) {
            return false;
        }
        Object systemService = BaseApplication.getContext().getApplicationContext().getSystemService("wifi");
        if (!(systemService instanceof WifiManager)) {
            LogUtil.a("HwFileTransferTaskManager", "raw file get wifiManager fail");
            return false;
        }
        if (!((WifiManager) systemService).isWifiEnabled()) {
            LogUtil.a("HwFileTransferTaskManager", "raw file wifi is disabled");
            return false;
        }
        boolean c2 = cwi.c(jvl.b().a(this.k), 153);
        LogUtil.a("HwFileTransferTaskManager", "raw file isSuppoerRawFileTransfer is : ", Boolean.valueOf(c2));
        if (!c2) {
            return false;
        }
        if (HwWifiP2pTransferManager.d().i() != null) {
            LogUtil.a("HwFileTransferTaskManager", "raw file transfer channel conflict");
            return false;
        }
        boolean z = this.at.getType() == 0 && this.at.getTaskType() == 0;
        LogUtil.a("HwFileTransferTaskManager", "raw file isLogFeedBack is : ", Boolean.valueOf(z));
        if (!z) {
            return false;
        }
        boolean a2 = PowerUtil.a(BaseApplication.getContext());
        LogUtil.a("HwFileTransferTaskManager", "raw file isCharged is : ", Boolean.valueOf(a2));
        boolean z2 = !CommonUtil.bh();
        LogUtil.a("HwFileTransferTaskManager", "raw file is3rdSystem is : ", Boolean.valueOf(z2));
        return a2 || z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        int i = this.aq + 1;
        this.aq = i;
        if (i == 3) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "More than three failures and mReTransferTime = 3");
            ad();
            if (this.g != null) {
                ai();
            } else {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "maintenanceCallback is null");
            }
            ExtendHandler extendHandler = this.aa;
            if (extendHandler != null) {
                extendHandler.removeMessages(7);
                return;
            }
            return;
        }
        if (this.aa != null) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "lost packet and resend");
            s();
        }
    }

    private void ai() {
        if (this.al instanceof jrx) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "More than three failures and save the collected log locally");
            ak();
        } else {
            jvk.b().b(this.g, this.al, this.f14120a, this.s);
        }
    }

    private void ak() {
        this.al.save2File(new IBaseResponseCallback() { // from class: jvi.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (jvi.this.u()) {
                    jvk.b().c("IS_CONTINUE", "true");
                    jvh.c(jvh.d(), jvh.b());
                }
                jvi.this.al.cutFolder(jrx.b, jrx.c);
                jsd.c(jvi.this.at, 10002, jvk.b().a(jvi.this.f14120a, jvi.this.s), jvi.this.g);
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "data transfer time out");
        if (this.ag) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "raw file transfer time out");
            this.aa.sendEmptyMessage(14);
            return;
        }
        ad();
        if (this.g != null) {
            if (this.al instanceof jrx) {
                ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "Dfx command upload timeout and save the collected log locally");
                jvk.b().a(this.g, this.al, this.at, this.ac);
                return;
            } else {
                jvk.b().b(this.g, this.al);
                return;
            }
        }
        LogUtil.a("HwFileTransferTaskManager", "maintenanceCallback is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJU_(Message message) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfxOrSleepGetFileName 5.10.1 received");
        jvk.b().c(this.aa, 15);
        try {
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    if (jvk.b().b(bArr)) {
                        aa();
                        return;
                    } else {
                        ad();
                        jvk.b().e(bArr, this.g, this.al);
                        return;
                    }
                }
                if (message.obj instanceof byte[]) {
                    c(this.av.c((byte[]) message.obj));
                }
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "GpsHandle dfxOrSleepGetFileName() TlvException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJY_(Message message) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "rawGetFileName 5.10.10 received");
        jvk.b().c(this.aa, 15);
        try {
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                int i = 0;
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    LogUtil.a("HwFileTransferTaskManager", "raw file name get error, reenter normal handler");
                    this.aa.sendEmptyMessage(14);
                    return;
                }
                ArrayList c2 = this.av.c(bArr);
                LogUtil.a("HwFileTransferTaskManager", "raw file size is " + c2.size());
                LogUtil.a("HwFileTransferTaskManager", "raw file name is " + String.join(";", c2));
                if (c2.isEmpty()) {
                    LogUtil.a("HwFileTransferTaskManager", "not exist raw file, reenter normal control");
                    this.aa.sendEmptyMessage(14);
                    return;
                }
                String obj = c2.get(0).toString();
                if (obj.isEmpty()) {
                    LogUtil.a("HwFileTransferTaskManager", "initFileName is Empty, reenter normal control");
                    this.aa.sendEmptyMessage(14);
                    return;
                }
                this.g.d(20001, String.format(Locale.ENGLISH, "0&%s(1%s%d)", obj, File.separator, Integer.valueOf(c2.size())));
                while (i < c2.size()) {
                    String obj2 = c2.get(i).toString();
                    i++;
                    RequestFileInfo requestFileInfo = new RequestFileInfo();
                    requestFileInfo.setFileName(obj2);
                    jrx.d(obj2);
                    requestFileInfo.setSavePath(jrx.d().e());
                    requestFileInfo.setFileType(125);
                    requestFileInfo.setDeviceMac(this.k);
                    b(requestFileInfo, c(c2, obj2, Integer.valueOf(i)));
                }
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "rawGetFileName TlvException");
        }
    }

    private void a(ArrayList arrayList, String str) {
        this.g.d(20001, String.format(Locale.ENGLISH, "0&%s(1%s%d)", str, File.separator, Integer.valueOf(arrayList.size())));
        int i = 0;
        while (i < arrayList.size()) {
            String obj = arrayList.get(i).toString();
            i++;
            RequestFileInfo requestFileInfo = new RequestFileInfo();
            requestFileInfo.setFileName(obj);
            jrx.d(obj);
            String str2 = jrx.b + "/" + obj;
            LogUtil.b("HwFileTransferTaskManager", "targetFilePath isExist = " + new File(str2).exists());
            LogUtil.b("HwFileTransferTaskManager", "targetFilePath isExist final = " + new File(str2).exists());
            LogUtil.b("HwFileTransferTaskManager", "targetFilePath = " + str2);
            requestFileInfo.setSavePath(str2);
            requestFileInfo.setFileType(125);
            requestFileInfo.setDeviceMac(this.k);
            b(requestFileInfo, a(arrayList, obj, Integer.valueOf(i)));
        }
    }

    private ITransferFileCallback a(final ArrayList arrayList, final String str, final Integer num) {
        return new ITransferFileCallback.Stub() { // from class: jvi.8
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str2) throws RemoteException {
                LogUtil.a("HwFileTransferTaskManager", "wifi file onProgress percentage: ", Integer.valueOf(i), ", progressDesc = ", str2, ", currentFileName = ", str);
                jvi.this.g.d(20001, String.format(Locale.ENGLISH, "%d&%s(%d/%d)", Integer.valueOf(i), arrayList.get(num.intValue() - 1).toString(), num, Integer.valueOf(arrayList.size())));
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str2) throws RemoteException {
                ReleaseLogUtil.c("HwFileTransferTaskManager", "wifi file onTransferFailed errorCode = ", Integer.valueOf(i), ", errMsg = ", str2, ", currentFileName = ", str);
                String str3 = jrx.b + "/" + str;
                File file = new File(str3);
                if (file.isFile() && file.exists()) {
                    LogUtil.h("HwFileTransferTaskManager", "wifi file onTransferFailed delete file = ", str3, ", isDelete = ", Boolean.valueOf(file.delete()));
                }
                if (num.intValue() == arrayList.size()) {
                    LogUtil.h("HwFileTransferTaskManager", "wifi file onTransferFailed EVENT_SAVE_LOG_ERROR");
                    jvi.this.ad();
                    jvi.this.aa.sendEmptyMessage(12);
                } else {
                    LogUtil.h("HwFileTransferTaskManager", "wifi file onTransferFailed currentIndex = ", num, ", arrayList.size() = ", Integer.valueOf(arrayList.size()));
                    jvi.this.ad();
                    jvk.b().c(jvi.this.at, jvi.this.g, jvi.this.al);
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str2, String str3) throws RemoteException {
                LogUtil.a("HwFileTransferTaskManager", "wifi file onSuccess = ", Integer.valueOf(i), ", mTransferDataContent = ", str2, ", mTransferStateContent = ", str3, ", currentFileName = ", str);
                if (num.intValue() == arrayList.size()) {
                    jvi.this.ad();
                    jvk.b().c(jvi.this.at, jvi.this.g, jvi.this.al);
                } else {
                    LogUtil.h("HwFileTransferTaskManager", "wifi file onSuccess currentIndex = ", num, ", arrayList.size() = ", Integer.valueOf(arrayList.size()));
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str2) throws RemoteException {
                LogUtil.a("HwFileTransferTaskManager", "wifi file onResponse errorCode = ", Integer.valueOf(i), ", responseMsg = ", str2);
            }
        };
    }

    private ITransferFileCallback c(final ArrayList arrayList, final String str, final Integer num) {
        return new ITransferFileCallback.Stub() { // from class: jvi.4
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str2) throws RemoteException {
                LogUtil.a("HwFileTransferTaskManager", "raw file onFileTransferState percentage:", Integer.valueOf(i), ",des", str2, ",currentFileName", str);
                jvi.this.g.d(20001, String.format(Locale.ENGLISH, "%d&%s(%d/%d)", Integer.valueOf(i), arrayList.get(num.intValue() - 1).toString(), num, Integer.valueOf(arrayList.size())));
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str2) throws RemoteException {
                LogUtil.a("HwFileTransferTaskManager", "raw file onTransferFailed errorCode:", Integer.valueOf(i), ",des", str2, ",currentFileName:", str);
                if (num.intValue() == arrayList.size()) {
                    jvi.this.aa.sendEmptyMessage(14);
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str2, String str3) throws RemoteException {
                LogUtil.a("HwFileTransferTaskManager", "raw file checkResult:", Integer.valueOf(i), ",des", str2);
                if (num.intValue() == arrayList.size()) {
                    jvi.this.aa.sendEmptyMessage(14);
                }
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str2) throws RemoteException {
                LogUtil.a("HwFileTransferTaskManager", "raw file onResponse: ", str2);
            }
        };
    }

    private void b(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwFileTransferTaskManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.a("HwFileTransferTaskManager", "startTransferRawFileMessage no device connected");
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("Dfx_HwFileTransferTaskManager", "startTransferRawFileMessage the device is null");
        } else if (deviceInfo.getDeviceIdentify().equals(requestFileInfo.getDeviceMac())) {
            LogUtil.a("HwFileTransferTaskManager", "startTransferRawFileMessage. ", requestFileInfo.getFileName());
            soa.c().d(requestFileInfo, iTransferFileCallback);
        } else {
            LogUtil.a("HwFileTransferTaskManager", "startTransferRawFileMessage the device is null or not connected");
        }
    }

    private void aa() {
        LogUtil.a("HwFileTransferTaskManager", "is LEO return");
        jvh.d(false);
        this.ae = this.at.getIsUploadAppLog();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "last_trigger_beta_log_time", String.valueOf(0), new StorageParams(0));
        ab();
        ExtendHandler extendHandler = this.aa;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(15, 600000L);
        }
        LogUtil.a("HwFileTransferTaskManager", "getlog from activity:", Boolean.valueOf(this.ab));
        jvk.b().d(this.ab, this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJV_(Message message) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfxOrSleepGetParameter 5.10.2 received");
        jvk.b().c(this.aa, 15);
        try {
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    ad();
                    jvk.b().b(bArr, this.al, this.g);
                }
                c(this.av.g(bArr));
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "GpsHandle dfxOrSleepGetParameter() TlvException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJX_(Message message) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfxOrSleepQueryFileInformation 5.10.3 received");
        jvk.b().c(this.aa, 15);
        try {
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    ad();
                    jvk.b().a(bArr, this.g, this.al);
                } else {
                    d(this.av.i(message.obj instanceof byte[] ? (byte[]) message.obj : null));
                }
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "GpsHandle dfxOrSleepQueryFileInformation() TlvException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJW_(Message message) {
        try {
            if (message.obj instanceof byte[]) {
                byte[] bArr = (byte[]) message.obj;
                if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
                    ad();
                    if (this.al instanceof jrx) {
                        jvk.b().c(bArr, this.g, this.aa);
                        return;
                    }
                }
                b(this.av.e(bArr));
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "GpsHandle dfxOrSleepMaintenanceReportData() TlvException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJS_(Message message) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfxOrSleepAutoMaticGetLog 5.10.11 received");
        jvk.b().c(this.aa, 15);
        if (message.obj instanceof byte[]) {
            e((byte[]) message.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJT_(Message message) {
        try {
            if (message.obj instanceof byte[]) {
                a(this.av.a((byte[]) message.obj));
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "GpsHandle dfxOrSleepErrorCode() TlvException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJR_(Message message) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManager", "dfxOrSleepQueryFileInformation 5.10.4 received");
        try {
            if (message.obj instanceof byte[]) {
                d(this.av.d((byte[]) message.obj));
            }
        } catch (cwg unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManager", "GpsHandle dfxOrSleepApplyData() TlvException");
        }
    }

    private void e(byte[] bArr) {
        LogUtil.a("HwFileTransferTaskManager", "is show collect dialog: ", Boolean.valueOf(this.ab));
        if (this.ab) {
            c(this.at, this.g);
            this.ab = false;
            this.at.setIsFromAbout(0);
            jcr.e().setIsFromAbout(0);
            return;
        }
        jvk.b().c(bArr, this.ae);
    }

    public boolean u() {
        return jvk.b().b(this.at) && this.ac == 1;
    }

    private boolean ap() {
        return jvk.b().b(this.at) && this.ah;
    }

    private void at() {
        ArrayList arrayList;
        if (!CompileParameterUtil.a("RELEASE_EVENT_LOG_UPLOAD") || this.e != 0 || (arrayList = this.u) == null || arrayList.isEmpty()) {
            return;
        }
        aw();
    }

    private void aw() {
        Object obj = this.u.get(0);
        if ((obj instanceof String) && ((String) obj).toLowerCase(Locale.ENGLISH).contains("dump.log")) {
            this.u.remove(0);
            ExtendHandler extendHandler = this.aa;
            if (extendHandler != null) {
                this.d = true;
                extendHandler.sendEmptyMessage(14);
            }
        }
    }
}
