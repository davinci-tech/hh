package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.devicesdk.callback.StatusCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.FileRequestBaseManager;
import com.huawei.unitedevice.hwcommonfilemgr.ParserInterface;
import com.huawei.unitedevice.hwcommonfilemgr.bean.Priority;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import defpackage.bir;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes9.dex */
public class snw extends FileRequestBaseManager implements ParserInterface {
    private static volatile snw c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private long f17167a;
    private Context b;
    private int e;
    private boolean f;
    private int g;
    private soi h;
    private ExtendHandler i;
    private StatusCallback.Stub j;
    private long k;
    private soj l;
    private bmn n;

    private snw(Context context) {
        super(context);
        this.l = soj.a();
        this.h = new soi();
        this.n = new bmn();
        this.f = false;
        this.k = 0L;
        this.f17167a = 0L;
        this.g = 0;
        this.e = 0;
        this.j = new StatusCallback.Stub() { // from class: snw.1
            @Override // com.huawei.devicesdk.callback.StatusCallback
            public void onStatusChanged(int i, UniteDevice uniteDevice, int i2) {
                if (uniteDevice == null) {
                    LogUtil.e("Unite_CommonFileRequestManager", "onStatusChanged error. UniteDevice is null");
                    return;
                }
                DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
                if (deviceInfo == null || deviceInfo.getDeviceBtType() <= 0) {
                    Object[] objArr = new Object[1];
                    objArr[0] = Boolean.valueOf(deviceInfo == null);
                    LogUtil.c("onStatusChanged. deviceInfo is", objArr);
                } else if (i2 == 2) {
                    LogUtil.c("Unite_CommonFileRequestManager", "device connected");
                } else if (i2 == 3) {
                    snw.this.c(uniteDevice);
                } else {
                    LogUtil.d("Unite_CommonFileRequestManager", "mConnectStateChangedReceiver() default");
                }
            }
        };
        this.b = context;
        this.i = HandlerCenter.yt_(new b(), "Unite_CommonFileRequestManager");
        sob.e().d(this.j);
        e();
    }

    private void j() {
        LogUtil.c("Unite_CommonFileRequestManager", "enter remove kit , size:", Integer.valueOf(this.l.b().size()));
        if (this.l.b().size() <= 0 || !this.l.b().get(0).at()) {
            return;
        }
        LogUtil.c("Unite_CommonFileRequestManager", "remove kit callback");
        this.l.b().remove(0);
    }

    public void a() {
        sob.e().e(this.j);
        f();
    }

    private static void f() {
        synchronized (d) {
            c = null;
        }
    }

    public static snw d() {
        snw snwVar;
        synchronized (d) {
            if (c == null) {
                c = new snw(BaseApplication.e());
            }
            snwVar = c;
        }
        return snwVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(UniteDevice uniteDevice) {
        LogUtil.c("Unite_CommonFileRequestManager", "disconnected identify:", blt.a(sov.a(uniteDevice)));
        CopyOnWriteArrayList<sol> b2 = this.l.b();
        if (b2.isEmpty()) {
            return;
        }
        Iterator<sol> it = b2.iterator();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        AtomicBoolean atomicBoolean = null;
        while (it.hasNext()) {
            sol next = it.next();
            String b3 = sov.b(next);
            if (b3 != null && !b3.equals(sov.a(uniteDevice))) {
                LogUtil.c("Unite_CommonFileRequestManager", "other device disconnect, no remove task.");
                if (atomicBoolean == null) {
                    atomicBoolean = new AtomicBoolean(false);
                }
            } else {
                if (atomicBoolean == null) {
                    atomicBoolean = new AtomicBoolean(true);
                    this.i.removeTasksAndMessages();
                }
                copyOnWriteArrayList.add(next);
                a(next);
            }
        }
        LogUtil.c("Unite_CommonFileRequestManager", "remove disconnect device task size : " + copyOnWriteArrayList.size());
        this.l.b().removeAll(copyOnWriteArrayList);
        if (atomicBoolean == null || !atomicBoolean.get()) {
            return;
        }
        m();
    }

    private void a(sol solVar) {
        ITransferFileCallback p = solVar.p();
        if (p == null) {
            return;
        }
        try {
            if (solVar.at()) {
                p.onFailure(300004, "");
            } else {
                p.onFailure(30004, "");
            }
        } catch (Exception unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "disconnected RemoteException");
        }
        sov.e(this.b, solVar);
    }

    @Override // com.huawei.unitedevice.hwcommonfilemgr.ParserInterface
    public boolean getResult(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("Unite_CommonFileRequestManager", bArr, "getResult message:");
        if (deviceInfo == null) {
            return false;
        }
        bix h = bjx.a().h(deviceInfo.getDeviceMac());
        ConnectMode b2 = h == null ? null : h.b();
        if (b2 != null) {
            if (b2.value() == ConnectMode.SIMPLE.value()) {
                return true;
            }
        } else {
            LogUtil.a("Unite_CommonFileRequestManager", "getResult connectMode is null");
        }
        if (bArr == null || bArr.length <= 2 || d(bArr)) {
            LogUtil.a("Unite_CommonFileRequestManager", "getResult() dataBytes is error.");
            return false;
        }
        byte b3 = bArr[1];
        if (b3 == 1) {
            d(bArr, deviceInfo);
        } else if (b3 == 2) {
            a(bArr, deviceInfo);
        } else if (b3 == 3) {
            e(bArr, deviceInfo);
        } else if (b3 == 5) {
            b(bArr, deviceInfo);
        } else if (b3 == 6) {
            c(bArr, deviceInfo);
        } else {
            LogUtil.d("Unite_CommonFileRequestManager", "getResult() default");
            return true;
        }
        return false;
    }

    private boolean d(byte[] bArr) {
        if (bArr[1] == 5) {
            return false;
        }
        bmt bmtVar = new bmt();
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        try {
            bmtVar.b(bArr2);
            return false;
        } catch (bmk unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "device send app wrong data");
            return true;
        }
    }

    private boolean d(sol solVar, long j) {
        LogUtil.c("Unite_CommonFileRequestManager", "prepare putCommonFileInfo to queue");
        Iterator<sol> it = this.l.b().iterator();
        boolean z = false;
        while (it.hasNext()) {
            sol next = it.next();
            if ((next.am() != 0 && j - next.am() > 86400000) || (next.aj() != 0 && j - next.aj() > 3600000)) {
                LogUtil.c("Unite_CommonFileRequestManager", "putCommonFileInfo check task is not effective");
                this.l.b().remove(next);
            } else if (!next.equals(solVar)) {
                continue;
            } else {
                if (solVar.ao()) {
                    LogUtil.c("Unite_CommonFileRequestManager", "cancel line up success:", Integer.valueOf(solVar.l()));
                    d(solVar, 3);
                    this.l.b().remove(next);
                    return false;
                }
                z = true;
            }
        }
        if (z) {
            LogUtil.a("Unite_CommonFileRequestManager", "putCommonFileInfo sameTask exist");
            d(solVar, "same task, can not put in queue.", 140008);
            return false;
        }
        if (solVar.ao()) {
            LogUtil.c("Unite_CommonFileRequestManager", "cancel line up fail:", Integer.valueOf(solVar.l()));
            d(solVar, 4);
            return false;
        }
        solVar.d(j);
        this.l.b().add(solVar);
        return true;
    }

    private void d(sol solVar, String str, int i) {
        ITransferFileCallback p = solVar.p();
        if (p == null) {
            return;
        }
        try {
            p.onFailure(i, str);
        } catch (Exception unused) {
            LogUtil.e("Unite_CommonFileRequestManager", str + " : RemoteException");
        }
    }

    private void f(sol solVar) {
        if (this.f) {
            LogUtil.c("Unite_CommonFileRequestManager", "send 5.44.1 task insert, requestNextTask");
            m();
        } else {
            bir e = soq.e(solVar);
            e(solVar.u(), sov.b(solVar));
            c(e, sov.d(solVar));
            l();
        }
    }

    public void a(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        int i;
        int i2;
        if (requestFileInfo == null) {
            return;
        }
        ReleaseLogUtil.b("R_Unite_CommonFileRequestManager", "enter startRequestFile isIskitWear: ", Boolean.valueOf(requestFileInfo.isKit()), "file type : ", Integer.valueOf(requestFileInfo.getFileType()));
        int[] times = requestFileInfo.getTimes();
        if (times == null || times.length != 2) {
            i = -1;
            i2 = -1;
        } else {
            int i3 = times[0];
            i2 = times[1];
            i = i3;
        }
        sol d2 = d(requestFileInfo, iTransferFileCallback);
        if (d2.i() == null) {
            try {
                iTransferFileCallback.onFailure(30004, "");
                return;
            } catch (RemoteException unused) {
                LogUtil.e("Unite_CommonFileRequestManager", "startRequestFile remote exception");
                return;
            }
        }
        d2.p(i);
        d2.d(i2);
        d2.b(Priority.DEFAULT);
        long currentTimeMillis = System.currentTimeMillis();
        if (requestFileInfo.getFileType() == 10) {
            d2.h(requestFileInfo.getSourcePackageName());
            d2.f(requestFileInfo.getSourceCertificate());
            d2.d(requestFileInfo.getDestinationPackageName());
            d2.b(requestFileInfo.getDestinationCertificate());
            d2.c(requestFileInfo.getDescription());
        }
        if (d2.ax()) {
            c(d2);
        } else {
            b(requestFileInfo, i, i2, d2, currentTimeMillis);
        }
    }

    private void e() {
        sov.b(new File(this.b.getFilesDir().getPath() + File.separator + "commonFileRequest"));
        try {
            String d2 = bky.d(this.b.getFilesDir().getCanonicalPath() + File.separator + "commonFileRequest");
            if (d2 != null) {
                sov.b(new File(d2));
            }
        } catch (IOException unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "deleteMacDir wrong");
        }
    }

    private void b(RequestFileInfo requestFileInfo, int i, int i2, sol solVar, long j) {
        if (this.l.b().size() > 0) {
            LogUtil.c("Unite_CommonFileRequestManager", "mCacheFiles size:", Integer.valueOf(this.l.b().size()), ", fileInfo name:", solVar.m(), ", type:", Integer.valueOf(solVar.u()));
            if (requestFileInfo.isKit()) {
                try {
                    ITransferFileCallback p = solVar.p();
                    if (p != null) {
                        p.onFailure(AdapterWithBottomView.TYPE_BOTTOM, "");
                    }
                } catch (RemoteException unused) {
                    LogUtil.e("Unite_CommonFileRequestManager", "toKitFailureCode remote exception");
                }
            }
            if (d(solVar, j)) {
                h();
                return;
            }
            return;
        }
        b(solVar, j, requestFileInfo, i, i2);
    }

    private void b(sol solVar, long j, RequestFileInfo requestFileInfo, int i, int i2) {
        solVar.a(j);
        solVar.d(j);
        if (!b(solVar)) {
            this.l.b().add(solVar);
        }
        LogUtil.c("Unite_CommonFileRequestManager", "sendOneCommonFileInfo enter isCacheListContains :", Boolean.valueOf(b(solVar)), "send 5.44.1 deviceMac is :", blt.a(requestFileInfo.getDeviceMac()));
        i(solVar);
    }

    private void c(sol solVar) {
        if (this.l.b().size() > 0) {
            if (!solVar.ao()) {
                d(solVar, 2);
            }
            if (d(solVar, System.currentTimeMillis())) {
                h();
                return;
            }
            return;
        }
        this.l.b().add(solVar);
        d(solVar, 0);
        c(solVar.l(), solVar);
    }

    private sol d(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        sol solVar = new sol();
        solVar.m(requestFileInfo.getFileType());
        if (!TextUtils.isEmpty(requestFileInfo.getFileName())) {
            solVar.a(requestFileInfo.getFileName());
        }
        solVar.b(requestFileInfo.isNeedVerify());
        solVar.a(requestFileInfo.isKit());
        solVar.a(iTransferFileCallback);
        solVar.d(requestFileInfo.isDeviceReport());
        if (!TextUtils.isEmpty(requestFileInfo.getDeviceMac())) {
            solVar.d(sov.e(requestFileInfo.getDeviceMac()));
        } else {
            LogUtil.c("Unite_CommonFileRequestManager", "deviceMac is null ");
        }
        LogUtil.c("Unite_CommonFileRequestManager", "DictTypeId : ", Integer.valueOf(requestFileInfo.getDictTypeId()));
        solVar.b(requestFileInfo.getDictTypeId());
        if (bky.e()) {
            this.e = requestFileInfo.getDictTypeId();
            this.g = requestFileInfo.getFileType();
        }
        if (requestFileInfo.isDeviceReport()) {
            solVar.c(requestFileInfo.getFileId());
            solVar.i(requestFileInfo.getFileSize());
            solVar.c(requestFileInfo.getDescription());
            solVar.h(requestFileInfo.getSourcePackageName());
            solVar.d(requestFileInfo.getDestinationPackageName());
            solVar.f(requestFileInfo.getSourceCertificate());
            solVar.b(requestFileInfo.getDestinationCertificate());
            solVar.c(requestFileInfo.isCancelTransmission());
        }
        return solVar;
    }

    private void h() {
        LogUtil.c("Unite_CommonFileRequestManager", "doubleCheckQueue CacheFiles size is :", Integer.valueOf(this.l.b().size()));
        if (this.l.b().size() == 1) {
            m();
        }
    }

    private void m() {
        this.f = false;
        if (this.l.b().size() > 0) {
            sol solVar = this.l.b().get(0);
            LogUtil.c("Unite_CommonFileRequestManager", "request next task:", Integer.valueOf(solVar.u()));
            solVar.a(System.currentTimeMillis());
            if (solVar.ax()) {
                LogUtil.c("Unite_CommonFileRequestManager", "request next task:", Integer.valueOf(solVar.l()));
                c(solVar.l(), solVar);
                return;
            } else if (solVar.u() == 1) {
                b(solVar.m(), solVar.u(), sov.d(solVar), solVar.f());
                return;
            } else if (solVar.u() >= 10 && solVar.u() <= 13) {
                f(solVar);
                return;
            } else {
                i(solVar);
                return;
            }
        }
        LogUtil.a("Unite_CommonFileRequestManager", "task is empty.");
    }

    public void e(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        if (requestFileInfo == null) {
            return;
        }
        for (Map.Entry<String, sol> entry : this.l.c().entrySet()) {
            if (TextUtils.equals(entry.getValue().m(), requestFileInfo.getFileName()) && requestFileInfo.getFileType() == entry.getValue().u() && TextUtils.equals(sov.b(entry.getValue()), requestFileInfo.getDeviceMac())) {
                LogUtil.c("Unite_CommonFileRequestManager", "stopRequestFile fileId:", entry.getKey());
                entry.getValue().b(iTransferFileCallback);
                b(entry.getValue().l(), 4, sov.d(entry.getValue()));
            }
        }
    }

    private boolean b(sol solVar) {
        Iterator<sol> it = this.l.b().iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (solVar.equals(it.next())) {
                z = true;
            }
        }
        return z;
    }

    private void i(sol solVar) {
        byte[] a2;
        LogUtil.c("Unite_CommonFileRequestManager", "sendFileInfo 5.44.1 fileName : ", solVar.m(), ", fileType: ", Integer.valueOf(solVar.u()));
        if (solVar.u() == 10) {
            a2 = d(solVar);
        } else {
            a2 = a(solVar.m(), solVar.u(), solVar.ak(), solVar.k(), solVar.f());
        }
        e(solVar.u(), sov.b(solVar));
        c(soq.e(a2, 44, 1), sov.d(solVar));
        e(solVar.u());
    }

    private void e(int i, String str) {
        this.l.d().put(sov.e(str, i), new sok());
    }

    private void b(String str, int i, DeviceInfo deviceInfo, int i2) {
        byte[] a2 = a(str, i, -1, -1, i2);
        e(i, deviceInfo.getDeviceMac());
        c(soq.e(a2, 44, 1), deviceInfo);
    }

    private void e(int i) {
        if (this.i == null) {
            this.i = HandlerCenter.yt_(new b(), "Unite_CommonFileRequestManager");
        }
        Message obtain = Message.obtain();
        obtain.arg1 = 500;
        obtain.what = i;
        this.i.sendMessage(obtain, 35000L);
    }

    private void i() {
        LogUtil.c("Unite_CommonFileRequestManager", "enter removeFileInfoTimeout");
        Iterator<sol> it = this.l.b().iterator();
        if (it.hasNext()) {
            a(it.next().u());
        }
    }

    private void d(byte[] bArr, DeviceInfo deviceInfo) {
        g();
        if (this.f) {
            LogUtil.c("Unite_CommonFileRequestManager", "receive 5.44.1 task insert, requestNextTask");
            m();
            return;
        }
        i();
        String d2 = blq.d(bArr);
        LogUtil.c("Unite_CommonFileRequestManager", "5.44.1 handleRequest: ", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.a("Unite_CommonFileRequestManager", "handleRequest dataBytes is error");
        } else {
            d(d2.substring(4), deviceInfo);
        }
    }

    private void d(String str, DeviceInfo deviceInfo) {
        LogUtil.a("Unite_CommonFileRequestManager", "handleRequestTlv entry");
        sol solVar = new sol();
        int i = 0;
        try {
            List<bmi> b2 = this.n.c(str).b();
            if (b2 != null && b2.size() > 0) {
                for (bmi bmiVar : b2) {
                    int d2 = bli.d(bmiVar.e(), 16);
                    String c2 = bmiVar.c();
                    int b3 = soq.b(d2, solVar, c2);
                    if (b3 != 0) {
                        i = b3;
                    }
                    soq.d(d2, solVar, c2);
                }
            } else {
                LogUtil.a("Unite_CommonFileRequestManager", "handleRequest tlv list error");
            }
        } catch (bmk unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "handleRequest error");
        }
        LogUtil.a("Unite_CommonFileRequestManager", "deviceMac:", blt.a(deviceInfo.getDeviceMac()));
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setDeviceInfo(deviceInfo);
        solVar.d(uniteDevice);
        if (bky.e()) {
            this.f17167a = solVar.v();
        }
        solVar.k(sov.d(sov.e(deviceInfo.getDeviceMac(), solVar.l())));
        a(solVar, i);
    }

    private void a(sol solVar, int i) {
        LogUtil.c("Unite_CommonFileRequestManager", "handleRequestFunc entry");
        Iterator<sol> it = this.l.b().iterator();
        if (it.hasNext()) {
            sol next = it.next();
            LogUtil.c("Unite_CommonFileRequestManager", "name :", next.m(), ", cache type:", Integer.valueOf(next.u()));
            solVar.a(next.p());
            solVar.d(next.ax());
            if (e(next.m(), solVar.m()) && (next.u() == solVar.u() || solVar.u() == 0)) {
                solVar.a(next.at());
                solVar.b(next.aw());
                solVar.g(next.t());
                solVar.e(next.c());
                solVar.b(next.f());
                if (solVar.u() == 0) {
                    solVar.a(next.m());
                    solVar.m(next.u());
                }
            } else {
                LogUtil.c("Unite_CommonFileRequestManager", "5.44.1 device report error.");
                if (solVar.m() == null && next.m() == null && i != 100000) {
                    solVar.a(true);
                }
                solVar.a(next.m());
                solVar.m(next.u());
                if (i == 100000) {
                    i = 144001;
                }
            }
            if (i != 100000) {
                if (solVar.p() != null) {
                    try {
                        e(solVar);
                        solVar.p().onFailure(i, "");
                        LogUtil.c("Unite_CommonFileRequestManager", "onUpgradeFailed errorCode:", Integer.valueOf(i));
                        return;
                    } catch (RemoteException | NullPointerException unused) {
                        LogUtil.e("Unite_CommonFileRequestManager", "commonFileInfo.getFileRequestCallBack RemoteException");
                        return;
                    }
                }
                LogUtil.a("Unite_CommonFileRequestManager", "file callback is null");
                c(i);
                return;
            }
            c(solVar.l(), solVar);
        }
    }

    private void c(int i, sol solVar) {
        Object[] objArr = new Object[3];
        objArr[0] = "device support transfer file, file type: ";
        objArr[1] = Integer.valueOf(solVar.u());
        objArr[2] = solVar.u() == 0 ? ", no task deal with this tlv." : "";
        LogUtil.c("Unite_CommonFileRequestManager", objArr);
        if (solVar.u() == 0) {
            LogUtil.a("Unite_CommonFileRequestManager", "commonFileInfo fileType is 0");
            return;
        }
        solVar.c(i);
        String e = sov.e(sov.b(solVar), solVar.l());
        this.l.c().put(e, solVar);
        this.l.d().put(e, new sok());
        LogUtil.c("Unite_CommonFileRequestManager", "reset info:file id: ", Integer.valueOf(solVar.l()), ", fileType: ", Integer.valueOf(solVar.u()), ", filename: ", solVar.m(), ", NeedVerify: ", Boolean.valueOf(solVar.aw()));
        if (bky.e()) {
            this.k = System.currentTimeMillis();
        }
        if (solVar.aw()) {
            a(solVar.l(), 1, sov.d(solVar));
        } else {
            e(solVar.l(), sov.d(solVar));
        }
    }

    private boolean e(String str, String str2) {
        return TextUtils.equals(str, str2) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str);
    }

    private void a(int i, int i2, DeviceInfo deviceInfo) {
        if (this.f) {
            LogUtil.c("Unite_CommonFileRequestManager", "send 5.44.2 task insert, requestNextTask");
            m();
        } else {
            c(soq.e(a(i, i2), 44, 2), deviceInfo);
            l();
        }
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo) {
        g();
        if (this.f) {
            LogUtil.c("Unite_CommonFileRequestManager", "receive 5.44.2 task insert, requestNextTask");
            m();
            return;
        }
        String d2 = blq.d(bArr);
        LogUtil.c("Unite_CommonFileRequestManager", "5.44.2 handleRequestHash: ", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.a("Unite_CommonFileRequestManager", "handleRequestHash dataBytes is error");
            return;
        }
        try {
            List<bmi> b2 = this.n.c(d2.substring(4)).b();
            if (b2 != null && b2.size() > 0) {
                String str = "";
                int i = 0;
                for (bmi bmiVar : b2) {
                    int d3 = bli.d(bmiVar.e(), 16);
                    String c2 = bmiVar.c();
                    if (d3 == 1) {
                        i = bli.d(c2, 16);
                        LogUtil.c("Unite_CommonFileRequestManager", "handleRequestHash file id: ", Integer.valueOf(i));
                    } else if (d3 != 3) {
                        LogUtil.d("Unite_CommonFileRequestManager", "handleRequestHash default");
                    } else {
                        str = c2;
                    }
                }
                e(i, str, deviceInfo);
                return;
            }
            LogUtil.a("Unite_CommonFileRequestManager", "handleRequestHash tlv list error");
        } catch (bmk unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "handleRequestHash error");
        }
    }

    private void e(int i, String str, DeviceInfo deviceInfo) {
        String e = sov.e(deviceInfo.getDeviceMac(), i);
        if (this.l.c().get(e) != null) {
            this.l.c().get(e).i(str);
            e(i, deviceInfo);
        } else {
            LogUtil.c("Unite_CommonFileRequestManager", "saveFileHash error");
        }
    }

    private void e(int i, DeviceInfo deviceInfo) {
        if (this.f) {
            LogUtil.c("Unite_CommonFileRequestManager", "send 5.44.3 task insert, requestNextTask");
            m();
        } else {
            c(soq.e(b(i), 44, 3), deviceInfo);
            l();
        }
    }

    private void e(byte[] bArr, DeviceInfo deviceInfo) {
        g();
        if (this.f) {
            LogUtil.c("Unite_CommonFileRequestManager", "receive 5.44.3 task insert, requestNextTask");
            m();
            return;
        }
        String d2 = blq.d(bArr);
        LogUtil.c("Unite_CommonFileRequestManager", "5.44.3 handleConsult:", d2);
        String substring = d2.substring(4);
        soo sooVar = new soo();
        try {
            List<bmi> b2 = this.n.c(substring).b();
            if (b2 != null && b2.size() > 0) {
                for (bmi bmiVar : b2) {
                    d(sooVar, bli.d(bmiVar.e(), 16), bmiVar.c(), deviceInfo);
                }
                if (sooVar.e() == 0) {
                    LogUtil.a("Unite_CommonFileRequestManager", "xxx / 0 will error, check this info.");
                    return;
                }
                LogUtil.c("Unite_CommonFileRequestManager", "5.44.3 handleConsult fileId: ", Integer.valueOf(sooVar.b()));
                int d3 = d(sooVar);
                sooVar.c(d3);
                LogUtil.c("Unite_CommonFileRequestManager", "5.44.3 handleConsult psnMax: ", Integer.valueOf(d3));
                String e = sov.e(deviceInfo.getDeviceMac(), sooVar.b());
                this.l.e().put(e, sooVar);
                if (this.l.c().get(e) != null) {
                    d(sooVar.b(), deviceInfo);
                    this.h.e(sooVar, this.l.c().get(e));
                    return;
                }
                return;
            }
            LogUtil.a("Unite_CommonFileRequestManager", "handleConsult tlv list error");
        } catch (bmk unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "handleConsult error");
        }
    }

    private int d(soo sooVar) {
        if (sooVar.c() % sooVar.e() == 0) {
            return (sooVar.c() / sooVar.e()) - 1;
        }
        return sooVar.c() / sooVar.e();
    }

    private void d(soo sooVar, int i, String str, DeviceInfo deviceInfo) {
        if (i == 1) {
            sooVar.d(bli.d(str, 16));
            LogUtil.c("Unite_CommonFileRequestManager", "handleParamTlv file id: ", Integer.valueOf(sooVar.b()));
            return;
        }
        if (i == 2) {
            sooVar.b(bli.d(str, 16));
            LogUtil.c("Unite_CommonFileRequestManager", "handleParamTlv device wait timeout: ", Integer.valueOf(sooVar.a()));
            return;
        }
        if (i == 3) {
            sooVar.h(bli.d(str, 16));
            LogUtil.c("Unite_CommonFileRequestManager", "handleConsult unit size: ", Integer.valueOf(sooVar.e()));
        } else if (i == 4) {
            sooVar.a(bli.d(str, 16));
            LogUtil.c("Unite_CommonFileRequestManager", "handleConsult max apply: ", Integer.valueOf(sooVar.c()));
        } else if (i == 5) {
            sooVar.a(bli.e(str) == 1);
            LogUtil.c("Unite_CommonFileRequestManager", "handleConsult not need encrypt: ", Boolean.valueOf(sooVar.j()));
        } else {
            LogUtil.d("Unite_CommonFileRequestManager", "handleParamTlv default");
        }
    }

    private void d(int i, DeviceInfo deviceInfo) {
        LogUtil.c("Unite_CommonFileRequestManager", "startRequest fileId:", Integer.valueOf(i));
        String e = sov.e(deviceInfo.getDeviceMac(), i);
        sol solVar = this.l.c().get(e);
        if (solVar == null) {
            LogUtil.e("Unite_CommonFileRequestManager", "startRequest, commonFileInfo is null");
            return;
        }
        solVar.b(sov.c(this.b, solVar));
        LogUtil.c("Unite_CommonFileRequestManager", "startRequest offset:", Integer.valueOf(solVar.t()));
        a(i, solVar.t(), false, e);
    }

    private void c(int i, int i2, boolean z, String str) {
        sok sokVar = this.l.d().get(str);
        if (sokVar == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "setRetryInfo info is null.");
            return;
        }
        sokVar.e(i2);
        if (z) {
            sokVar.d(-1);
        }
        this.l.d().put(str, sokVar);
    }

    private void a(int i, int i2, boolean z, String str) {
        LogUtil.c("Unite_CommonFileRequestManager", "doRequest fileId: ", Integer.valueOf(i), " offset: ", Integer.valueOf(i2));
        sol solVar = this.l.c().get(str);
        if (solVar == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "doRequest fileId not in list");
            return;
        }
        soo sooVar = this.l.e().get(str);
        if (sooVar == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "fileTransferParameter is null");
            return;
        }
        solVar.g(i2);
        solVar.h(0);
        c(i, i2, !z, str);
        if (sooVar.c() <= solVar.v() - solVar.t()) {
            LogUtil.c("Unite_CommonFileRequestManager", "doRequest request max: ", Integer.valueOf(sooVar.c()));
            solVar.j(sooVar.c());
            solVar.f(sooVar.d());
            solVar.e(ByteBuffer.allocate(sooVar.c()));
            b(solVar, sooVar.c(), sov.d(solVar));
        } else {
            int v = solVar.v() - solVar.t();
            LogUtil.c("Unite_CommonFileRequestManager", "doRequest request not max: ", Integer.valueOf(v));
            if (sooVar.e() == 0 || v < 0) {
                LogUtil.e("Unite_CommonFileRequestManager", "fileSpare / 0 will error, check this info.");
                return;
            }
            if (v % sooVar.e() == 0) {
                solVar.f((v / sooVar.e()) - 1);
            } else {
                solVar.f(v / sooVar.e());
            }
            solVar.j(v);
            solVar.e(ByteBuffer.allocate(v));
            b(solVar, solVar.o(), sov.d(solVar));
        }
        if (!z) {
            LogUtil.c("Unite_CommonFileRequestManager", "doRequest first");
            b(i, sooVar.a(), solVar.y(), str);
            solVar.l(0);
        }
        e(i, i2, sooVar.a() / 3, solVar.y(), str);
    }

    private void b(sol solVar, int i, DeviceInfo deviceInfo) {
        if (this.f) {
            LogUtil.c("Unite_CommonFileRequestManager", "send 5.44.4 task insert, requestNextTask");
            m();
            return;
        }
        bir e = soq.e(a(solVar.l(), solVar.t(), i, solVar.u(), solVar.f()), 44, 4);
        String e2 = sov.e(deviceInfo.getDeviceMac(), solVar.l());
        if (this.l.e().get(e2) != null && this.l.e().get(e2).j()) {
            LogUtil.c("Unite_CommonFileRequestManager", "builder command");
            bir.a aVar = new bir.a();
            aVar.c(false);
            c(aVar.b(e), deviceInfo);
            return;
        }
        c(e, deviceInfo);
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        if (!i(bArr, deviceInfo)) {
            LogUtil.a("Unite_CommonFileRequestManager", "data is too big, please watch or band check 5.44.5, might lost data.");
            return;
        }
        g();
        if (bArr == null || bArr.length < 8) {
            Object[] objArr = new Object[3];
            objArr[0] = "handleDeviceDataReceived dataBytes is error, mIsCurrentTaskStop: ";
            objArr[1] = Boolean.valueOf(this.f);
            objArr[2] = this.f ? "empty tlv 5.44.5 task insert, requestNextTask" : "";
            LogUtil.a("Unite_CommonFileRequestManager", objArr);
            if (this.f) {
                m();
                return;
            }
            return;
        }
        int d2 = blq.d(Arrays.copyOfRange(bArr, 2, 3), 0);
        int d3 = blq.d(Arrays.copyOfRange(bArr, 3, 7), 0);
        int d4 = blq.d(Arrays.copyOfRange(bArr, 7, 8), 0);
        LogUtil.c("Unite_CommonFileRequestManager", "5.44.5 handleDeviceDataReceived fileID: ", Integer.valueOf(d2), ", offset: ", Integer.valueOf(d3), ", psn: ", Integer.valueOf(d4), " dataBytes ", Integer.valueOf(bArr.length));
        String e = sov.e(deviceInfo.getDeviceMac(), d2);
        sol solVar = this.l.c().get(e);
        if (solVar != null) {
            if (solVar.t() != d3 || solVar.r() != d4) {
                Object[] objArr2 = new Object[3];
                objArr2[0] = "handleDeviceDataReceived fileOffset or psn error, mIsCurrentTaskStop:";
                objArr2[1] = Boolean.valueOf(this.f);
                objArr2[2] = this.f ? ", receive 5.44.5 task insert, requestNextTask" : "";
                LogUtil.c("Unite_CommonFileRequestManager", objArr2);
                if (this.f) {
                    m();
                    return;
                }
                return;
            }
            d(bArr, d2, d4, solVar, e);
            if (solVar.v() == 0) {
                LogUtil.a("Unite_CommonFileRequestManager", "getFileSize is zero");
                return;
            }
            int v = (d3 * 100) / solVar.v();
            LogUtil.c("Unite_CommonFileRequestManager", "reportProgressForUi progress:", Integer.valueOf(v));
            b(e, v);
            return;
        }
        b();
    }

    private void b() {
        Object[] objArr = new Object[3];
        objArr[0] = "handleDeviceDataReceived fileID error, mIsCurrentTaskStop: ";
        objArr[1] = Boolean.valueOf(this.f);
        objArr[2] = this.f ? ", receive 5.44.5 task insert, requestNextTask" : "";
        LogUtil.a("Unite_CommonFileRequestManager", objArr);
        if (this.f) {
            m();
        }
    }

    private boolean i(byte[] bArr, DeviceInfo deviceInfo) {
        ConcurrentHashMap<String, soo> e = this.l.e();
        if (e == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "fileTypeTransferInfos is null");
            return true;
        }
        if (bArr.length < 8) {
            LogUtil.a("Unite_CommonFileRequestManager", "length < 8, please check 5.44.5");
            return false;
        }
        soo sooVar = e.get(sov.e(deviceInfo.getDeviceMac(), bArr[2]));
        if (sooVar != null) {
            return bArr.length - 8 <= sooVar.e();
        }
        LogUtil.a("Unite_CommonFileRequestManager", "fileTransferParameter is null");
        return true;
    }

    private void d(byte[] bArr, int i, int i2, sol solVar, String str) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 8, bArr.length);
        if (solVar.b() != null) {
            if (solVar.b().capacity() >= solVar.b().position() + copyOfRange.length) {
                solVar.b().put(copyOfRange);
                LogUtil.c("Unite_CommonFileRequestManager", "handleRequest dataValue:", Integer.valueOf(solVar.b().toString().length()));
                e(solVar.y(), solVar);
                this.h.a(solVar, copyOfRange, i, i2);
                if (i2 < solVar.q()) {
                    LogUtil.c("Unite_CommonFileRequestManager", "handleDeviceDataReceived keep wait");
                    soo sooVar = this.l.e().get(str);
                    if (sooVar != null) {
                        b(i, sooVar.a(), solVar.y(), str);
                        sok sokVar = this.l.d().get(str);
                        if (sokVar != null) {
                            e(i, sokVar.c(), sooVar.a() / 3, solVar.y(), str);
                        }
                    }
                    solVar.h(i2 + 1);
                    solVar.g(solVar.t() + copyOfRange.length);
                    return;
                }
                LogUtil.c("Unite_CommonFileRequestManager", "handleDeviceDataReceived one unit all received");
                ByteBuffer b2 = solVar.b();
                if (b2 != null) {
                    b2.clear();
                } else {
                    LogUtil.a("Unite_CommonFileRequestManager", "byteUnit is null.");
                }
                d(solVar, copyOfRange, i, str);
                return;
            }
            LogUtil.a("Unite_CommonFileRequestManager", "device return info is error. too big");
            return;
        }
        LogUtil.a("Unite_CommonFileRequestManager", "getByteUnit is null");
    }

    private void d(sol solVar, byte[] bArr, int i, String str) {
        LogUtil.c("Unite_CommonFileRequestManager", "dealCommonFileInfoNext isSaveSuccess: ", Boolean.valueOf(sov.b(this.b, solVar, solVar.z())));
        int t = solVar.t() + bArr.length;
        if (t < solVar.v()) {
            Object[] objArr = new Object[3];
            objArr[0] = "mIsCurrentTaskStop:";
            objArr[1] = Boolean.valueOf(this.f);
            objArr[2] = this.f ? "handleRequest next, task insert, requestNextTask" : "start request next";
            LogUtil.c("Unite_CommonFileRequestManager", objArr);
            if (this.f) {
                b(solVar, t);
                m();
                return;
            } else {
                a(i, t, false, str);
                return;
            }
        }
        solVar.g(t);
        a(i, str);
    }

    private void b(sol solVar, int i) {
        Iterator<sol> it = this.l.b().iterator();
        while (it.hasNext()) {
            sol next = it.next();
            if (next.equals(solVar)) {
                LogUtil.c("Unite_CommonFileRequestManager", "saveThisTask save cut point");
                int c2 = solVar.c() + 1;
                solVar.e(c2);
                next.e(c2);
                next.g(i);
                return;
            }
        }
    }

    private void a(int i, String str) {
        sol solVar = this.l.c().get(str);
        int i2 = 0;
        if (solVar == null) {
            Object[] objArr = new Object[3];
            objArr[0] = "handleRequestOver commonFileInfo is null, mIsCurrentTaskStop:";
            objArr[1] = Boolean.valueOf(this.f);
            objArr[2] = this.f ? "handleRequest next, task insert, requestNextTask" : "";
            LogUtil.a("Unite_CommonFileRequestManager", objArr);
            if (this.f) {
                m();
                return;
            }
            return;
        }
        try {
            if (solVar.aw()) {
                String a2 = sov.a(solVar, 0, 16384, sov.d(this.b, solVar));
                LogUtil.c("Unite_CommonFileRequestManager", "handleRequestOver hashValue end: ", a2, ", device hashValue: ", solVar.x());
                if (a2 != null && a2.equalsIgnoreCase(solVar.x())) {
                    h(solVar);
                    b(i, 1, sov.d(solVar));
                } else {
                    sov.e(this.b, solVar);
                    b(i, 2, sov.d(solVar));
                    i2 = 1;
                }
            } else {
                h(solVar);
                b(i, 1, sov.d(solVar));
            }
            j();
            if (solVar.at()) {
                solVar.p().onFailure(30005, "");
            }
            solVar.t(i2);
        } catch (RemoteException unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "handleRequestOver RemoteException");
        }
    }

    private void b(int i, sol solVar) throws IOException, RemoteException {
        LogUtil.c("Unite_CommonFileRequestManager", "sendSuccessToCallBack transferResult is ", Integer.valueOf(i));
        if (i == 0) {
            if (solVar.p() != null) {
                String b2 = sov.b(this.b, solVar.m(), solVar.l(), sov.b(solVar));
                LogUtil.a("Unite_CommonFileRequestManager", "sendSuccessToCallBack path is : ", b2);
                if (b2 == null) {
                    LogUtil.a("Unite_CommonFileRequestManager", "sendSuccessToCallBack path is null");
                    solVar.p().onFailure(30007, "createFileWithByte path is null");
                    return;
                } else {
                    solVar.p().onSuccess(30000, b2, "");
                    return;
                }
            }
            return;
        }
        if ((i & 1) == 1) {
            if (solVar.p() != null) {
                solVar.p().onFailure(30001, "");
                return;
            } else {
                LogUtil.a("Unite_CommonFileRequestManager", "check failed, fileRequestCallBack is null");
                return;
            }
        }
        if ((i & 2) == 2) {
            if (solVar.p() != null) {
                solVar.p().onFailure(30007, "");
                return;
            } else {
                LogUtil.a("Unite_CommonFileRequestManager", "save failed, fileRequestCallBack is null");
                return;
            }
        }
        LogUtil.a("Unite_CommonFileRequestManager", "transferResult is other status.");
    }

    private void b(String str, int i) {
        sol solVar = this.l.c().get(str);
        if (solVar == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "reportProgressForUi commonFileInfo is null");
        } else if (solVar.p() != null) {
            try {
                solVar.p().onProgress(i, "");
            } catch (RemoteException unused) {
                LogUtil.e("Unite_CommonFileRequestManager", "reportProgressForUi RemoteException");
            }
        }
    }

    private void b(int i, int i2, DeviceInfo deviceInfo) {
        c(soq.e(e(i, i2), 44, 6), deviceInfo);
        if (bky.e()) {
            iyv.c("DataSyncSpeed", "5.44 transfer file size: " + this.f17167a + ", fileType: " + this.g + ", dictType: " + this.e + ", duration: " + (System.currentTimeMillis() - this.k) + ", transferSpeed: " + (((this.f17167a * 1.0d) / (r5 * 1024)) * 1000.0d));
        }
        l();
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        g();
        String d2 = blq.d(bArr);
        LogUtil.c("Unite_CommonFileRequestManager", "5.44.6 handleDeviceStatusReport:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.a("Unite_CommonFileRequestManager", "handleDeviceStatusReport dataBytes is error");
            return;
        }
        try {
            List<bmi> b2 = this.n.c(d2.substring(4)).b();
            if (b2 != null && b2.size() > 0) {
                int i = 0;
                int i2 = 0;
                for (bmi bmiVar : b2) {
                    int d3 = bli.d(bmiVar.e(), 16);
                    String c2 = bmiVar.c();
                    if (d3 == 1) {
                        i = bli.d(c2, 16);
                        LogUtil.c("Unite_CommonFileRequestManager", "handleDeviceStatusReport file_id:", Integer.valueOf(i));
                    } else if (d3 == 127) {
                        i2 = bli.d(c2, 16);
                        LogUtil.c("Unite_CommonFileRequestManager", "handleDeviceStatusReport status:", Integer.valueOf(i2));
                    } else {
                        LogUtil.d("Unite_CommonFileRequestManager", "handleDeviceStatusReport default");
                    }
                }
                c(i, i2, deviceInfo);
                return;
            }
            LogUtil.a("Unite_CommonFileRequestManager", "handleDeviceStatusReport tlv list error");
        } catch (bmk unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "handleDeviceStatusReport error");
        }
    }

    private void c(int i, int i2, DeviceInfo deviceInfo) {
        sol solVar = this.l.c().get(sov.e(deviceInfo.getDeviceMac(), i));
        if (solVar == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "reportCancelResult commonFileInfo is null");
            return;
        }
        try {
            Object[] objArr = new Object[1];
            objArr[0] = i2 == 100000 ? "handleCancelReply success" : "handleCancelReply failed";
            LogUtil.c("Unite_CommonFileRequestManager", objArr);
            if (i2 == 100000) {
                if (solVar.d() != null) {
                    solVar.d().onResponse(30005, "");
                    if (solVar.at()) {
                        solVar.p().onFailure(30005, "");
                    }
                }
                a(solVar.y());
            } else if (solVar.d() != null) {
                solVar.d().onResponse(30006, "");
                if (solVar.at()) {
                    solVar.p().onFailure(30006, "");
                }
            }
        } catch (RemoteException unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "handleCancelReply RemoteException");
        }
        e(solVar);
        try {
            b(solVar.as(), solVar);
        } catch (RemoteException | IOException unused2) {
            LogUtil.e("Unite_CommonFileRequestManager", "reportCancelResult RemoteException | IOException");
        }
    }

    private void h(sol solVar) {
        if (solVar.ax()) {
            try {
                solVar.e(bky.d(this.b.getFilesDir().getCanonicalPath() + File.separator + "common_file_request" + File.separator + sov.a(sov.b(solVar)) + File.separator + solVar.m()));
            } catch (IOException unused) {
                LogUtil.e("Unite_CommonFileRequestManager", "noticeHiWearFileObtain IOException");
            }
            LogUtil.c("Unite_CommonFileRequestManager", "noticeHiWearFileObtain isDeviceReport");
            CommonFileInfoParcel commonFileInfoParcel = new CommonFileInfoParcel();
            commonFileInfoParcel.setFileName(solVar.m());
            commonFileInfoParcel.setFileType(solVar.u());
            commonFileInfoParcel.setFilePath(solVar.s());
            commonFileInfoParcel.setSourcePackageName(solVar.ae());
            commonFileInfoParcel.setSourceCertificate(solVar.ah());
            commonFileInfoParcel.setDestinationPackageName(solVar.g());
            commonFileInfoParcel.setDestinationCertificate(solVar.h());
            commonFileInfoParcel.setDescription(solVar.j());
            commonFileInfoParcel.setSha256Result(solVar.ai());
            try {
                solVar.p().onResponse(0, new Gson().toJson(commonFileInfoParcel));
            } catch (RemoteException unused2) {
                LogUtil.e("Unite_CommonFileRequestManager", "IBaseFileResponseCallback RemoteException");
            }
        }
    }

    private void d(sol solVar, int i) {
        LogUtil.c("Unite_CommonFileRequestManager", "confirmDeviceFileReport,errorCode:", Integer.valueOf(i));
        e(soq.e(c(solVar, i), 44, 7), sov.d(solVar), solVar.u(), solVar.g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        ExtendHandler extendHandler = this.i;
        if (extendHandler != null) {
            extendHandler.removeMessages(i);
        }
    }

    private void e(int i, sol solVar) {
        ExtendHandler extendHandler = this.i;
        if (extendHandler != null) {
            extendHandler.removeMessages(i);
        }
    }

    private void b(int i, int i2, int i3, String str) {
        LogUtil.c("Unite_CommonFileRequestManager", "startWait handlerWhat is :", Integer.valueOf(i3));
        if (this.i != null) {
            if (i2 != 0) {
                Message obtain = Message.obtain();
                obtain.what = i3;
                obtain.arg1 = 100;
                this.i.sendMessage(obtain, i2 * 1000);
                return;
            }
            return;
        }
        this.i = HandlerCenter.yt_(new b(), "Unite_CommonFileRequestManager");
        if (i2 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = i3;
            obtain2.arg1 = 100;
            obtain2.obj = str;
            this.i.sendMessage(obtain2, i2 * 1000);
        }
    }

    private void e(int i, int i2, int i3, int i4, String str) {
        LogUtil.c("Unite_CommonFileRequestManager", "startRetry handlerWhat is :", Integer.valueOf(i4));
        if (this.i != null) {
            if (i3 != 0) {
                Message obtain = Message.obtain();
                obtain.what = i4;
                obtain.arg1 = 200;
                obtain.arg2 = i2;
                obtain.obj = str;
                this.i.sendMessage(obtain, i3 * 1000);
                return;
            }
            return;
        }
        this.i = HandlerCenter.yt_(new b(), "Unite_CommonFileRequestManager");
        if (i3 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = i4;
            obtain2.arg1 = 200;
            obtain2.arg2 = i2;
            obtain2.obj = str;
            this.i.sendMessage(obtain2, i3 * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(sol solVar) {
        if (solVar == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "handleRequestEnd error, file info is null");
            return;
        }
        ReleaseLogUtil.b("R_Unite_CommonFileRequestManager", "finish file : ", Integer.valueOf(solVar.u()));
        sov.a(this.b, solVar);
        blv.d(solVar.z());
        int i = 0;
        while (true) {
            if (i >= this.l.b().size()) {
                break;
            }
            sol solVar2 = this.l.b().get(i);
            LogUtil.c("Unite_CommonFileRequestManager", "handleRequestEnd has cache file name:", solVar2.m(), ", type:", Integer.valueOf(solVar2.u()));
            if ((TextUtils.equals(solVar2.m(), solVar.m()) || TextUtils.isEmpty(solVar2.m())) && solVar2.u() == solVar.u()) {
                LogUtil.c("Unite_CommonFileRequestManager", "delete commonFileInfo.name:", solVar2.m());
                this.l.b().remove(solVar2);
                break;
            }
            i++;
        }
        String e = sov.e(sov.b(solVar), solVar.l());
        if (this.l.c().get(e) != null) {
            LogUtil.c("Unite_CommonFileRequestManager", "handleRequestEnd has mTransferringFileList file id:", Integer.valueOf(solVar.l()));
            this.l.c().remove(e);
        }
        if (this.l.e().get(e) != null) {
            LogUtil.c("Unite_CommonFileRequestManager", "handleRequestEnd has mFileTypeTransferInfos file id:", Integer.valueOf(solVar.l()));
            this.l.e().remove(e);
        }
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i) {
        sol solVar = this.l.c().get(str);
        if (solVar == null) {
            LogUtil.a("Unite_CommonFileRequestManager", "reportFailedForUi commonFileInfo is null");
            return;
        }
        try {
            if (solVar.p() != null) {
                solVar.p().onFailure(i, "");
                LogUtil.c("Unite_CommonFileRequestManager", "reportFailedForUi fileId:", Integer.valueOf(sov.b(str)), "errorCode:", Integer.valueOf(i));
            }
        } catch (RemoteException unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "reportFailedForUi RemoteException");
        }
    }

    class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.arg1;
            if (i != 100) {
                if (i == 200) {
                    snw.this.ejH_(message);
                    return true;
                }
                if (i == 500) {
                    LogUtil.c("Unite_CommonFileRequestManager", "kit NO_MANAGER_CALLBACK");
                    snw.this.d(message.what);
                    return true;
                }
                if (i == 544) {
                    snw.this.c();
                    return true;
                }
                LogUtil.d("Unite_CommonFileRequestManager", "handleMessage default");
                return false;
            }
            LogUtil.d("Unite_CommonFileRequestManager", "handleMessage wait timeout!");
            Object obj = message.obj;
            if (obj instanceof String) {
                String str = (String) obj;
                LogUtil.c("Unite_CommonFileRequestManager", "wait timeout! file id:", Integer.valueOf(sov.b(str)));
                snw.this.e(str, OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
                sol solVar = snw.this.l.c().get(str);
                sov.e(snw.this.b, solVar);
                snw.this.e(solVar);
                snw.this.a(message.what);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Object[] objArr = new Object[3];
        objArr[0] = "start send next, mIsCurrentTaskStop:";
        objArr[1] = Boolean.valueOf(this.f);
        objArr[2] = this.f ? "WAIT_MESSAGE, requestNextTask" : "";
        LogUtil.c("Unite_CommonFileRequestManager", objArr);
        try {
            if (this.l.b().size() > 0) {
                sol remove = this.l.b().remove(0);
                if (remove.p() != null) {
                    iyv.c("FileTransfer", "Error occurs between 5.44.4 and 5.44.5");
                    LogUtil.c("Unite_CommonFileRequestManager", "timeout error code : ", Integer.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN));
                    remove.p().onFailure(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, "");
                } else {
                    LogUtil.c("Unite_CommonFileRequestManager", "task is no callback.");
                }
            }
        } catch (RemoteException unused) {
            LogUtil.e("Unite_CommonFileRequestManager", "disconnected RemoteException");
        } catch (ArrayIndexOutOfBoundsException unused2) {
            LogUtil.e("Unite_CommonFileRequestManager", "dataTimeout ArrayIndexOutOfBoundsException");
        }
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ejH_(Message message) {
        LogUtil.d("Unite_CommonFileRequestManager", "handleMessage retry");
        Object obj = message.obj;
        if (obj instanceof String) {
            String str = (String) obj;
            int b2 = sov.b(str);
            int i = message.arg2;
            LogUtil.c("Unite_CommonFileRequestManager", "retry! file id:", Integer.valueOf(b2), ", offset:", Integer.valueOf(i));
            sol solVar = this.l.c().get(str);
            if (solVar == null) {
                LogUtil.a("Unite_CommonFileRequestManager", "no target info, please check log.");
                return;
            }
            solVar.l(solVar.ad() + 1);
            LogUtil.c("Unite_CommonFileRequestManager", "5.44.5 no send to app, retry count : ", Integer.valueOf(solVar.ad()));
            if (solVar.ad() == 3) {
                LogUtil.c("Unite_CommonFileRequestManager", "device no send info to app, finish this task ", Integer.valueOf(solVar.ad()));
                Message obtain = Message.obtain();
                obtain.arg1 = 100;
                obtain.obj = str;
                this.i.sendMessage(obtain);
                return;
            }
            a(b2, i, true, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        sol solVar;
        if (bky.e()) {
            iyv.c("FileTransfer", "Device replies 5.44.1 reach timeout");
        }
        Iterator<sol> it = this.l.b().iterator();
        while (true) {
            if (!it.hasNext()) {
                solVar = null;
                break;
            } else {
                solVar = it.next();
                if (solVar.u() == i) {
                    break;
                }
            }
        }
        if (solVar != null) {
            ITransferFileCallback p = solVar.p();
            if (p != null) {
                LogUtil.c("Unite_CommonFileRequestManager", "onResponse callback.");
                try {
                    if (solVar.at()) {
                        p.onFailure(100009, "5.44.1 time out");
                    } else {
                        p.onFailure(100001, "5.44.1 time out");
                    }
                } catch (RemoteException unused) {
                    LogUtil.e("Unite_CommonFileRequestManager", "reportFileInfoTimeout onFailure exception.");
                }
            }
            Iterator<sol> it2 = this.l.b().iterator();
            if (it2.hasNext()) {
                sol next = it2.next();
                LogUtil.c("Unite_CommonFileRequestManager", "reportFileInfoTimeout remove item");
                this.l.b().remove(next);
            }
            m();
            return;
        }
        LogUtil.a("Unite_CommonFileRequestManager", "toKitTimeoutError is null");
    }

    private void c(int i) {
        if (this.l.b().size() <= 0 || i == 30003) {
            return;
        }
        LogUtil.c("Unite_CommonFileRequestManager", "removeCacheFile remove item");
        this.l.b().remove(0);
        m();
    }

    private void l() {
        if (this.i == null) {
            this.i = HandlerCenter.yt_(new b(), "Unite_CommonFileRequestManager");
            Message obtain = Message.obtain();
            obtain.what = 544;
            obtain.arg1 = 544;
            this.i.sendMessage(obtain, PreConnectManager.CONNECT_INTERNAL);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 544;
        obtain2.arg1 = 544;
        this.i.sendMessage(obtain2, PreConnectManager.CONNECT_INTERNAL);
    }

    private void g() {
        ExtendHandler extendHandler = this.i;
        if (extendHandler != null) {
            extendHandler.removeMessages(544);
        }
    }

    private void c(bir birVar, DeviceInfo deviceInfo) {
        int i;
        if (deviceInfo != null) {
            Iterator<sol> it = this.l.b().iterator();
            String str = "";
            if (it.hasNext()) {
                sol next = it.next();
                i = next.u();
                if (i == 10) {
                    str = next.g();
                }
            } else {
                i = -1;
            }
            e(birVar, deviceInfo, i, str);
            return;
        }
        LogUtil.e("Unite_CommonFileRequestManager", "deviceInfo is null");
    }

    private void e(bir birVar, DeviceInfo deviceInfo, int i, String str) {
        if (deviceInfo != null) {
            LogUtil.c("Unite_CommonFileRequestManager", "sendCommand deviceInfo mac is", blt.a(deviceInfo.getDeviceMac()), " fileType: ", Integer.valueOf(i));
            List<Integer> list = bgz.e.get(deviceInfo.getDeviceMac());
            if (i == 10) {
                int e = blk.e().e(deviceInfo.getDeviceMac(), 44, str);
                birVar.c(e);
                LogUtil.c("Unite_CommonFileRequestManager", "5.44.1 HiWearKit file send socketChannel: ", Integer.valueOf(e));
            }
            if (list != null && list.contains(Integer.valueOf(i))) {
                LogUtil.c("Unite_CommonFileRequestManager", "socketChannel: ", bgz.b.get(deviceInfo.getDeviceMac()));
                birVar.c(bgz.b.get(deviceInfo.getDeviceMac()).intValue());
            }
            bgl.c().sendCommand(deviceInfo, birVar);
            return;
        }
        LogUtil.e("Unite_CommonFileRequestManager", "deviceInfo is null");
    }
}
