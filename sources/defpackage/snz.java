package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.StatusCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.ParserInterface;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener;
import defpackage.bir;
import defpackage.bmt;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class snz implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static volatile snz f17169a;
    private int ab;
    private Context k;
    private sol l;
    private sol o;
    private boolean r;
    private ParcelFileDescriptor x;
    private static final String d = BaseApplication.e().getFilesDir() + "/fileShare/";
    private static final Object c = new Object();
    private static final Object b = new Object();
    private static final byte[] e = new byte[0];
    private final HashMap<Integer, LinkedList<sol>> p = new HashMap<>(16);
    private int m = -1;
    private FileInputStream n = null;
    private FileChannel g = null;
    private Map<Integer, Map<Integer, byte[]>> f = new LinkedHashMap(4);
    private ConcurrentHashMap<String, sol> z = new ConcurrentHashMap<>(20);
    private Queue<bir> i = new LinkedList();
    private bmn y = new bmn();
    private IResultAIDLCallback w = null;
    private boolean u = false;
    private boolean t = false;
    private ConcurrentHashMap<String, soo> j = new ConcurrentHashMap<>(20);
    private StatusCallback.Stub h = new StatusCallback.Stub() { // from class: snz.3
        @Override // com.huawei.devicesdk.callback.StatusCallback
        public void onStatusChanged(int i, UniteDevice uniteDevice, int i2) {
            if (uniteDevice == null) {
                LogUtil.e("unite_HwCommonFileMgr", "onStatusChanged error. UniteDevice is null");
                return;
            }
            DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
            if (deviceInfo == null || deviceInfo.getDeviceBtType() <= 0) {
                Object[] objArr = new Object[1];
                objArr[0] = Boolean.valueOf(deviceInfo == null);
                LogUtil.c("onStatusChanged. deviceInfo is", objArr);
            } else {
                ConnectState connectState = ConnectState.getConnectState(i2);
                LogUtil.c("unite_HwCommonFileMgr", "mConnectStateChangedReceiver(), status :", Integer.valueOf(uniteDevice.getDeviceInfo().getDeviceConnectState()));
                if (connectState == ConnectState.DISCONNECTED) {
                    snz.this.b(uniteDevice);
                }
            }
        }
    };
    private final BroadcastReceiver v = new BroadcastReceiver() { // from class: snz.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("unite_HwCommonFileMgr", "mNetworkStateReceiver , intent is null.");
                return;
            }
            int intExtra = intent.getIntExtra("wifi_state", 0);
            LogUtil.c("unite_HwCommonFileMgr", "mNetworkStateReceiver wifiState: ", Integer.valueOf(intExtra), "mTransferFileMode:", Integer.valueOf(snz.this.ab));
            if (TransferFileQueueManager.d().c("wifi_change") != null && intExtra == 3 && snz.this.ab == 1) {
                snz.this.s.a(snz.this.l, snz.this, 1);
            }
        }
    };
    private WifiP2pTransferListener aa = new WifiP2pTransferListener() { // from class: snz.5
        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onSuccess(int i, String str, int i2) {
            LogUtil.c("unite_HwCommonFileMgr", "mWifiListener parameter： code : ", Integer.valueOf(i), "； msg: ", str, "； fileType: ", Integer.valueOf(i2));
            snz.this.ab = 0;
            if (i != 0 && i != 1) {
                if (i == 10000) {
                    sol solVar = (sol) snz.this.z.get(String.valueOf(i2));
                    if (solVar != null) {
                        soy.d(i2);
                        int v = solVar.v();
                        int max = Math.max((v / Constants.GIF_SIZE_UPPER_LIMIT) + (v % Constants.GIF_SIZE_UPPER_LIMIT != 0 ? 1 : 0), 1);
                        LogUtil.c("unite_HwCommonFileMgr", "file sizek is : ", Integer.valueOf(v), "factor is : ", Integer.valueOf(max));
                        snz.this.b(max * 15, i2);
                        return;
                    }
                    LogUtil.c("unite_HwCommonFileMgr", "no fileInfo, please check, stop wifi");
                    HwWifiP2pTransferManager.d().e();
                    return;
                }
                if (i != 140008) {
                    LogUtil.a("unite_HwCommonFileMgr", "unknown code, please deal this : ", Integer.valueOf(i));
                    return;
                }
            }
            snz.this.c(i, i2);
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onFail(int i, String str, int i2) {
            snz.this.e(i, str, i2);
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onProcess(int i, int i2) {
            snz.this.e(i, i2);
        }
    };
    private ExtendHandler q = HandlerCenter.yt_(new c(), "unite_HwCommonFileMgr");
    private sod s = sod.b();

    private void j() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        this.k.registerReceiver(this.v, intentFilter);
    }

    private snz(Context context) {
        this.k = context;
        LogUtil.c("unite_HwCommonFileMgr", "registerNetworkStateBroadcast: ");
        j();
        sob.e().d(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2) {
        boolean z = this.t;
        if (z) {
            LogUtil.c("unite_HwCommonFileMgr", "current has excel file , mIsWifiExcelStatus: ", Boolean.valueOf(z));
            return;
        }
        c();
        d(i, i2);
        LogUtil.c("unite_HwCommonFileMgr", "wait next file.");
    }

    public void d(int i, int i2) {
        sol solVar = this.z.get(String.valueOf(i2));
        if (solVar != null) {
            HwWifiP2pTransferManager.d().d(i2);
            UniteDevice i3 = solVar.i();
            IResultAIDLCallback n = solVar.n();
            e(solVar, i3, 0);
            d(i, n);
            return;
        }
        LogUtil.a("unite_HwCommonFileMgr", "file valid file info is null. please check");
    }

    private void d(int i, IResultAIDLCallback iResultAIDLCallback) {
        if (iResultAIDLCallback != null) {
            try {
                if (i == 140008) {
                    iyv.c("FileTransfer", "Transferring duplicate file.");
                    LogUtil.c("unite_HwCommonFileMgr", "callback.onTransferFailed(), code: ", Integer.valueOf(i));
                    iResultAIDLCallback.onTransferFailed(i, "");
                } else {
                    iResultAIDLCallback.onFileRespond(i, "");
                }
            } catch (Exception e2) {
                LogUtil.e("unite_HwCommonFileMgr", "wifi success listener exception : ", bll.a(e2));
            }
        }
    }

    private void d(sol solVar) {
        soz i = HwWifiP2pTransferManager.d().i();
        if (i != null) {
            LogUtil.c("unite_HwCommonFileMgr", "task type .", Integer.valueOf(i.o()));
            int o = i.o();
            if (i.m() == 2) {
                LogUtil.c("unite_HwCommonFileMgr", "next task is ota.");
                WifiP2pTransferListener k = i.k();
                if (k != null) {
                    sow.b();
                    k.onSuccess(1000, "send 5.9.9", -1);
                    return;
                }
                return;
            }
            sol solVar2 = this.z.get(String.valueOf(o));
            if (solVar2 != null) {
                LogUtil.a("unite_HwCommonFileMgr", "wifi wrong, please check.");
                HwWifiP2pTransferManager.d().d(o);
                this.s.a(solVar2, this, 0);
                b(solVar2);
                return;
            }
            LogUtil.a("unite_HwCommonFileMgr", "file list is null, please check.");
            HwWifiP2pTransferManager.d().d(i.o());
            return;
        }
        if (this.s.b(solVar)) {
            this.s.a(solVar, this, 0);
            LogUtil.c("unite_HwCommonFileMgr", "no next task.");
            b(solVar);
            return;
        }
        this.s.a(solVar.n(), 141001, "");
    }

    private void b(sol solVar) {
        this.u = false;
        this.l = solVar;
        LogUtil.c("unite_HwCommonFileMgr", "sendFileInfoByBluetooth");
        if (solVar.u() == 7) {
            sor.c(solVar.i(), spl.b(solVar));
        } else {
            sor.e(solVar.m(), solVar.v(), solVar.u(), solVar.ac(), solVar.i());
        }
        int max = Math.max(solVar.v() / Constants.GIF_SIZE_UPPER_LIMIT, 1);
        e(solVar.u(), max * 15, solVar.i().getDeviceInfo());
        b(max, solVar);
    }

    private void b(int i, sol solVar) {
        int i2 = ((i * 15) / 60) + 1;
        if (i2 <= 1 || !sod.b().c()) {
            return;
        }
        LogUtil.c("unite_HwCommonFileMgr", "5.40.3 timeout too much, try wifi need delay : " + i2);
        sod.b().a(solVar, this, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str, int i2) {
        LogUtil.c("unite_HwCommonFileMgr", "mWifiListener fail code : ", Integer.valueOf(i), " msg : ", str);
        sol solVar = this.z.get(String.valueOf(i2));
        this.ab = 0;
        if (i == 1014) {
            if (solVar != null) {
                this.t = true;
                TransferFileQueueManager.d().c("closeWifiP2p").s(1);
                c(solVar);
                return;
            }
            return;
        }
        if (i == 100005) {
            a(i2, str);
            return;
        }
        if (i != 20003 && i != 20004) {
            d(i, str, solVar, i2);
            return;
        }
        if (solVar == null) {
            LogUtil.c("unite_HwCommonFileMgr", "cancel from cache.");
            solVar = TransferFileQueueManager.d().c("cancel wifi task");
        }
        if (solVar != null) {
            this.u = false;
            this.t = false;
            ITransferFileCallback d2 = solVar.d();
            e(this.z.get(String.valueOf(i2)), solVar.i(), 0);
            HwWifiP2pTransferManager.d().d(i2);
            if (d2 != null) {
                try {
                    LogUtil.c("unite_HwCommonFileMgr", "String.valueOf(fileInfo.getFileName()):", String.valueOf(solVar.m()));
                    if (solVar.u() == 1) {
                        d2.onResponse(i, solVar.m());
                    } else {
                        d2.onResponse(i, "cancel file.");
                    }
                } catch (Exception e2) {
                    LogUtil.e("unite_HwCommonFileMgr", "cancel response fail : ", bll.a(e2));
                }
                HwWifiP2pTransferManager.d().e();
                return;
            }
            LogUtil.a("unite_HwCommonFileMgr", "callback is null. cancel not response.");
            return;
        }
        LogUtil.a("unite_HwCommonFileMgr", "cancel fail, file is null");
    }

    private void a(int i, String str) {
        this.u = false;
        LogUtil.c("unite_HwCommonFileMgr", "busy : fileType : ", Integer.valueOf(i), " msg : ", str);
    }

    public void d(int i, String str, sol solVar, int i2) {
        this.u = false;
        this.t = false;
        HwWifiP2pTransferManager.d().e();
        if (solVar != null) {
            if (!HwWifiP2pTransferManager.d().b(solVar.u())) {
                LogUtil.a("unite_HwCommonFileMgr", "isHasFile is false");
                return;
            }
            HwWifiP2pTransferManager.d().d(solVar.u());
            this.z.remove(String.valueOf(solVar.u()));
            if (!HwWifiP2pTransferManager.d().a(i)) {
                this.s.c();
                c(i, solVar);
                return;
            } else {
                if (this.s.a(solVar, i)) {
                    return;
                }
                LogUtil.c("unite_HwCommonFileMgr", "wifi pair fail, repeat bluetooth send file.");
                if (TransferFileQueueManager.d().c("wifi Pairing failed") == null) {
                    LogUtil.a("unite_HwCommonFileMgr", "may bluetooth was closed and cleared all task when wifi Pairing failed");
                    return;
                } else {
                    this.s.a(this.l, this, 0);
                    b(solVar);
                    return;
                }
            }
        }
        HwWifiP2pTransferManager.d().d(i2);
        LogUtil.a("unite_HwCommonFileMgr", "mCurrentCommonFileInfo is null.please check.");
    }

    private void c(int i, sol solVar) {
        LogUtil.c("unite_HwCommonFileMgr", "start bluetooth transfer:", Boolean.valueOf(this.r));
        c(solVar);
        if (this.s.e(HwWifiP2pTransferManager.d().e(i), solVar, this)) {
            if (this.z.get(String.valueOf(solVar.u())) != null) {
                this.z.remove(String.valueOf(solVar.u()));
            }
            if (TransferFileQueueManager.d().c("check Queue before wifi change to BT") == null) {
                LogUtil.a("unite_HwCommonFileMgr", "may bluetooth was closed and cleared all task when transfering by wifi");
            } else {
                d(solVar);
            }
        }
    }

    private void c(sol solVar) {
        LogUtil.a("unite_HwCommonFileMgr", "send 54.6, and closeWifi");
        soy.b(solVar.u(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        this.ab = 2;
        LogUtil.c("unite_HwCommonFileMgr", "mWifiListener process ", Integer.valueOf(i));
        sol solVar = this.z.get(String.valueOf(i2));
        if (solVar != null) {
            IResultAIDLCallback n = solVar.n();
            if (n != null) {
                try {
                    n.onFileTransferState(i, "");
                    n.onFileTransferReport(sod.e("transferWay", "wifi"));
                } catch (Exception e2) {
                    LogUtil.e("unite_HwCommonFileMgr", "wifi process listener exception : ", bll.a(e2));
                }
            } else {
                LogUtil.a("unite_HwCommonFileMgr", "callback is null. please check.");
            }
        } else {
            LogUtil.a("unite_HwCommonFileMgr", "mCurrentCommonFileInfo is null. please check.");
        }
        d("wifi");
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "TRANSFER_WAY_BLUETOOTH";
        }
        List<sol> d2 = TransferFileQueueManager.d().d(str + "_process");
        LogUtil.c("unite_HwCommonFileMgr", "wait queues report process 0 , wait task size : ", Integer.valueOf(d2.size()));
        Iterator<sol> it = d2.iterator();
        while (it.hasNext()) {
            IResultAIDLCallback n = it.next().n();
            if (n != null) {
                try {
                    n.onFileTransferState(0, "");
                    n.onFileTransferReport(sod.e("transferWay", str));
                } catch (Exception e2) {
                    LogUtil.e("unite_HwCommonFileMgr", "wifi process listener exception : ", bll.a(e2));
                }
            }
        }
    }

    public void e(final UniteDevice uniteDevice, final sol solVar) {
        int i;
        LogUtil.c("unite_HwCommonFileMgr", "transferFileByQueue");
        if (solVar == null || solVar.n() == null) {
            LogUtil.a("unite_HwCommonFileMgr", "transferFileByQueue bean is null");
            return;
        }
        try {
            if (solVar.ejT_() != null) {
                i = sod.ejP_(solVar.ejT_(), this.x);
            } else if (!TextUtils.isEmpty(solVar.s())) {
                i = sod.ejQ_(new File(solVar.s()), -1L, this.x);
            } else {
                LogUtil.a("unite_HwCommonFileMgr", "transferFileByQueue getFileSize default");
                i = 0;
            }
            if (i == 0) {
                LogUtil.a("unite_HwCommonFileMgr", "transferFileByQueue getFileSize size is 0");
                TransferFileQueueManager.d().b("fileSize size is 0");
                solVar.n().onTransferFailed(20000, "");
            } else if (i == -1) {
                LogUtil.a("unite_HwCommonFileMgr", "transferFileByQueue getFileSize size is exceeded");
                TransferFileQueueManager.d().b("fileSize size is too big");
                solVar.n().onTransferFailed(20005, "");
            } else {
                solVar.i(i);
                ThreadPoolManager.d().execute(new Runnable() { // from class: soc
                    @Override // java.lang.Runnable
                    public final void run() {
                        snz.this.b(solVar, uniteDevice);
                    }
                });
            }
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgr", "transferFileByQueue Exception", bll.a(e2));
        }
    }

    /* synthetic */ void b(sol solVar, UniteDevice uniteDevice) {
        synchronized (this.p) {
            c(solVar, uniteDevice);
        }
    }

    private void c(sol solVar, UniteDevice uniteDevice) {
        int u = solVar.u();
        long currentTimeMillis = System.currentTimeMillis();
        if (this.p.get(Integer.valueOf(u)) == null) {
            LogUtil.c("unite_HwCommonFileMgr", "putCommonFileInfo fileBeanList is null");
            this.p.put(Integer.valueOf(u), new LinkedList<>());
        }
        LinkedList<sol> linkedList = this.p.get(Integer.valueOf(u));
        if (linkedList != null) {
            linkedList.clear();
            solVar.d(currentTimeMillis);
            solVar.a(currentTimeMillis);
            linkedList.add(solVar);
            d(solVar, uniteDevice);
        }
    }

    private void d(sol solVar, UniteDevice uniteDevice) {
        LogUtil.c("unite_HwCommonFileMgr", "enter startTransferFileByQueue");
        solVar.d(uniteDevice);
        i();
        synchronized (e) {
            this.o = solVar;
            this.l = solVar;
        }
        solVar.c(solVar.u());
        LogUtil.c("unite_HwCommonFileMgr", "send file queue.");
        solVar.g(sod.ejR_(solVar, this.x));
        this.z.put(String.valueOf(solVar.u()), solVar);
        b("startTransferFileByQueue", solVar);
    }

    public void c(UniteDevice uniteDevice, FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) {
        if (!this.s.e(iResultAIDLCallback, fileInfo)) {
            LogUtil.a("unite_HwCommonFileMgr", "checkAvailability is failed");
            return;
        }
        sol solVar = new sol();
        solVar.a(fileInfo.getFileName());
        solVar.e(fileInfo.getFilePath());
        solVar.ejU_(fileInfo.getFileDescriptor());
        if (c(solVar, fileInfo, iResultAIDLCallback)) {
            b(fileInfo, solVar.v(), uniteDevice, iResultAIDLCallback);
        }
    }

    private boolean c(sol solVar, FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) {
        int ejQ_;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ContentResource.FILE_NAME, fileInfo.getFileName());
            jSONObject.put("fileType", fileInfo.getFileType());
        } catch (JSONException unused) {
            LogUtil.a("unite_HwCommonFileMgr", "param to JSONObject error");
        }
        try {
            if (fileInfo.getFileDescriptor() != null) {
                int fileSize = fileInfo.getFileSize();
                solVar.i(fileSize);
                if (fileSize != 0) {
                    return true;
                }
                LogUtil.a("unite_HwCommonFileMgr", "from ParcelFileDescriptor file size is 0");
                iResultAIDLCallback.onTransferFailed(20000, jSONObject.toString());
                return false;
            }
            File file = new File(fileInfo.getFilePath());
            if (file.exists()) {
                LogUtil.c("unite_HwCommonFileMgr", "fileBaseInfo.getSourceId() :", Long.valueOf(fileInfo.getSourceId()));
                if (fileInfo.getSourceId() > 0) {
                    ejQ_ = sod.ejQ_(file, fileInfo.getSourceId(), this.x);
                } else {
                    ejQ_ = sod.ejQ_(file, -1L, this.x);
                }
                if (ejQ_ == 0) {
                    LogUtil.a("unite_HwCommonFileMgr", "file size is 0");
                    iResultAIDLCallback.onTransferFailed(20000, jSONObject.toString());
                    return false;
                }
                solVar.i(ejQ_);
                return true;
            }
            LogUtil.a("unite_HwCommonFileMgr", "file is not exist");
            iResultAIDLCallback.onTransferFailed(20000, jSONObject.toString());
            return false;
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgr", "startTransfer Exception", bll.a(e2));
            return false;
        }
    }

    public void b(IResultAIDLCallback iResultAIDLCallback, List<Integer> list) {
        this.w = iResultAIDLCallback;
        LogUtil.c("unite_HwCommonFileMgr", "registToDevicesCallback success ! ");
    }

    private void i() {
        synchronized (this) {
            this.f.clear();
            this.m = -1;
            blv.d(this.g);
            blv.d(this.n);
            blv.d(this.x);
            this.g = null;
            this.n = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(UniteDevice uniteDevice) {
        LogUtil.c("unite_HwCommonFileMgr", "disconnected identify:", blt.a(sov.a(uniteDevice)));
        List<sol> e2 = TransferFileQueueManager.d().e("disconnectDevice");
        if (!e2.isEmpty()) {
            if (e2.get(0).i().getIdentify().equals(uniteDevice.getIdentify())) {
                LogUtil.c("unite_HwCommonFileMgr", "same device. disconnect");
            } else {
                LogUtil.c("unite_HwCommonFileMgr", "other device disconnect, no deal");
                return;
            }
        }
        TransferFileQueueManager.d().a("disconnectDevice");
        for (sol solVar : e2) {
            String b2 = sov.b(solVar);
            if (TextUtils.isEmpty(b2) || !b2.equals(sov.a(uniteDevice))) {
                LogUtil.c("unite_HwCommonFileMgr", "not disconnected, fileId:", Integer.valueOf(solVar.l()), " file type : ", Integer.valueOf(solVar.u()));
            } else {
                f();
                sod.e(uniteDevice);
                b(uniteDevice, solVar);
                e(uniteDevice);
            }
        }
    }

    private void e(UniteDevice uniteDevice) {
        synchronized (e) {
            this.o = null;
        }
        this.p.clear();
        this.t = false;
    }

    private void b(UniteDevice uniteDevice, sol solVar) {
        if (solVar != null) {
            LogUtil.c("unite_HwCommonFileMgr", "disconnected fileId: ", Integer.valueOf(solVar.l()));
            this.s.a(solVar.n(), 141001, "");
            e(solVar, uniteDevice, 2);
            synchronized (e) {
                if (solVar.equals(this.o)) {
                    this.o = null;
                }
            }
        }
    }

    private void e(sol solVar, UniteDevice uniteDevice, int i) {
        if (solVar == null) {
            LogUtil.a("unite_HwCommonFileMgr", "handleFailed error, fileInfo is null");
            return;
        }
        this.s.c();
        String e2 = sov.e(uniteDevice.getIdentify(), solVar.l());
        LogUtil.c("unite_HwCommonFileMgr", "handleFailed has fileInfo file id: ", Integer.valueOf(solVar.l()), "handleFailed has fileInfo device: ", blt.a(uniteDevice.getIdentify()));
        if (this.z.get(e2) != null) {
            this.z.remove(e2);
        }
        i();
        synchronized (e) {
            if (solVar.equals(this.o)) {
                LogUtil.c("unite_HwCommonFileMgr", "handleFailed set mCurrentCommonFileInfo null: ", Integer.valueOf(solVar.l()));
                this.o = null;
            }
        }
        e(solVar.u(), uniteDevice, i);
        if (this.j.get(e2) != null) {
            this.j.remove(e2);
        }
        if (i == 0) {
            TransferFileQueueManager.d().b("normal_next_task");
        }
    }

    private void e(int i, UniteDevice uniteDevice, int i2) {
        LogUtil.c("unite_HwCommonFileMgr", "removeFirstAndSendNext : ", Integer.valueOf(i), " fileSendType : ", Integer.valueOf(i2));
        synchronized (this.p) {
            LinkedList<sol> linkedList = this.p.get(Integer.valueOf(i));
            if (linkedList != null) {
                LogUtil.c("unite_HwCommonFileMgr", "removeFirstAndSendNext fileBeanList size :", Integer.valueOf(linkedList.size()));
                linkedList.poll();
                sol peek = linkedList.peek();
                if (peek != null) {
                    LogUtil.c("unite_HwCommonFileMgr", "ready send next kit");
                    peek.a(System.currentTimeMillis());
                    d(peek, uniteDevice);
                    return;
                }
            }
            LogUtil.c("unite_HwCommonFileMgr", "removeFirstAndSendNext no kit, next task");
        }
    }

    private void f() {
        ExtendHandler extendHandler = this.q;
        if (extendHandler != null) {
            extendHandler.removeMessages(100);
        }
    }

    public void c() {
        ExtendHandler extendHandler = this.q;
        if (extendHandler != null) {
            extendHandler.removeMessages(101);
        }
    }

    @Override // com.huawei.unitedevice.hwcommonfilemgr.ParserInterface
    public boolean getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 1 || sov.e(bArr)) {
            return true;
        }
        blt.d("unite_HwCommonFileMgr", bArr, "getResult(), message :");
        byte b2 = bArr[1];
        switch (b2) {
            case 1:
                d(deviceInfo, bArr);
                break;
            case 2:
                a(deviceInfo, bArr);
                break;
            case 3:
                g(deviceInfo, bArr);
                break;
            case 4:
                c(deviceInfo, bArr);
                break;
            case 5:
                b(deviceInfo, bArr);
                break;
            case 6:
            default:
                LogUtil.a("unite_HwCommonFileMgr", "getResult()  default switch ", Byte.valueOf(b2));
                break;
            case 7:
                j(deviceInfo, bArr);
                break;
            case 8:
                h(deviceInfo, bArr);
                break;
            case 9:
                e(deviceInfo, bArr);
                break;
        }
        return true;
    }

    private void d(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("unite_HwCommonFileMgr", bArr, "5.40.1 handleDeviceRequest :");
        if (bArr == null || bArr.length < 2) {
            LogUtil.a("unite_HwCommonFileMgr", "handleDeviceRequest data is error");
            return;
        }
        try {
            bmt.b b2 = new bmt().b(bArr, 2);
            String b3 = b2.b((byte) 1, "");
            int a2 = b2.a((byte) 2, 3);
            int a3 = b2.a((byte) 5, -1);
            String b4 = b2.b((byte) 4, "");
            c(b4, b2.b((byte) 3, b4), b3, a2, a3, deviceInfo);
        } catch (bmk unused) {
            LogUtil.e("unite_HwCommonFileMgr", "handleDeviceRequest error");
        }
    }

    private void c(String str, String str2, String str3, int i, int i2, DeviceInfo deviceInfo) {
        if (!sor.d(i, str3)) {
            sor.b(str, str3, i, i2, 140006, deviceInfo);
            return;
        }
        if (i == 7) {
            LogUtil.c("unite_HwCommonFileMgr", "fileType 7 request through 5.40.1, destPkgName: ", str, " srcPkgName: ", str2);
            sor.c(str, str3, i, 100000, deviceInfo, str2, str);
            b(str3, str2, str);
        } else {
            sor.b(str, str3, i, i2, 100000, deviceInfo);
            if (i == 4 || i == 5) {
                e(str3, i, deviceInfo);
            } else {
                a(str3, i, i2, deviceInfo);
            }
        }
    }

    private void a(String str, int i, int i2, DeviceInfo deviceInfo) {
        String a2;
        try {
            if (i == 3 || i == 10 || i == 11) {
                a2 = sor.a(str, i, i2);
            } else {
                a2 = sor.d(str, i);
                LogUtil.c("unite_HwCommonFileMgr", "biMapFileName is :", a2);
            }
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilePath(a2);
            fileInfo.setFileName(str);
            fileInfo.setFileType(i);
            fileInfo.setPackageName(null);
            if (this.w != null) {
                LogUtil.c("unite_HwCommonFileMgr", "deviceStartToDeviceTransFile entry");
                d(deviceInfo, fileInfo);
            } else {
                LogUtil.c("unite_HwCommonFileMgr", "mToDevicesFileCallback is null ");
            }
        } catch (IOException unused) {
            LogUtil.e("unite_HwCommonFileMgr", "deviceStartTransfer IOException");
        }
    }

    private void b(String str, String str2, String str3) {
        if (StringUtils.strEquals(str2, "hw.wearable.httpProxy") || StringUtils.strEquals(str2, "hw.unitedevice.httpProxy")) {
            LogUtil.c("unite_HwCommonFileMgr", "enter httpProxy send cloud response file to device");
            spf.a().c(str);
        } else {
            LogUtil.c("unite_HwCommonFileMgr", "not enter httpProxy send cloud response file to device");
        }
    }

    private void d(DeviceInfo deviceInfo, FileInfo fileInfo) {
        final JSONObject b2 = this.s.b(fileInfo);
        c(sod.b(deviceInfo), fileInfo, new IResultAIDLCallback.Stub() { // from class: snz.1
            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferState(int i, String str) {
                LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer startTransfer onFileTransferState");
                if (snz.this.w != null) {
                    try {
                        LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer onFileTransferState percentage: ", Integer.valueOf(i), "des: ", b2.toString());
                        snz.this.w.onFileTransferState(i, b2.toString());
                        LogUtil.c("unite_HwCommonFileMgr", "onFileTransferState success");
                    } catch (Exception e2) {
                        LogUtil.e("unite_HwCommonFileMgr", "onFileTransferState remoteException", bll.a(e2));
                    }
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onTransferFailed(int i, String str) {
                LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer startTransfer onTransferFailed");
                if (snz.this.w != null) {
                    try {
                        LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer onTransferFailed errorCode: ", Integer.valueOf(i), "des: ", b2.toString());
                        snz.this.w.onTransferFailed(i, b2.toString());
                        LogUtil.c("unite_HwCommonFileMgr", "onTransferFailed success");
                    } catch (Exception e2) {
                        LogUtil.e("unite_HwCommonFileMgr", "onTransferFailed remoteException", bll.a(e2));
                    }
                }
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileRespond(int i, String str) {
                snz.this.e(i, str, b2);
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferReport(String str) {
                snz.this.a(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer startTransfer onFileTransferReport");
        if (this.w != null) {
            try {
                LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer onFileTransferReport transferWay: ", str);
                this.w.onFileTransferReport(str);
                LogUtil.c("unite_HwCommonFileMgr", "onFileRespond success");
            } catch (Exception e2) {
                LogUtil.e("unite_HwCommonFileMgr", "onFileRespond remoteException", bll.a(e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str, JSONObject jSONObject) {
        LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer startTransfer onFileRespond");
        if (this.w != null) {
            try {
                LogUtil.c("unite_HwCommonFileMgr", "deviceStartTransfer onFileRespond checkResult: ", Integer.valueOf(i), "des: ", jSONObject.toString());
                this.w.onFileRespond(i, jSONObject.toString());
                LogUtil.c("unite_HwCommonFileMgr", "onFileRespond success");
            } catch (Exception e2) {
                LogUtil.e("unite_HwCommonFileMgr", "onFileRespond remoteException", bll.a(e2));
            }
        }
    }

    public void e(UniteDevice uniteDevice, FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) {
        if (uniteDevice == null || fileInfo == null) {
            return;
        }
        TransferFileReqType requestType = fileInfo.getRequestType();
        List<Integer> attentionTypes = fileInfo.getAttentionTypes();
        LogUtil.a("unite_HwCommonFileMgr", "startTransferManager entry. requestType is :", Integer.valueOf(requestType.getValue()), "device is :", blt.a(uniteDevice.getIdentify()));
        int i = AnonymousClass8.d[requestType.ordinal()];
        if (i == 1) {
            c(uniteDevice, fileInfo, iResultAIDLCallback);
        } else if (i == 2) {
            b(iResultAIDLCallback, attentionTypes);
        } else {
            if (i != 3) {
                return;
            }
            d(uniteDevice, fileInfo, iResultAIDLCallback);
        }
    }

    /* renamed from: snz$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[TransferFileReqType.values().length];
            d = iArr;
            try {
                iArr[TransferFileReqType.APP_DELIVERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[TransferFileReqType.DEVICE_REGISTRATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[TransferFileReqType.APP_STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void d(UniteDevice uniteDevice, FileInfo fileInfo, final IResultAIDLCallback iResultAIDLCallback) {
        e(uniteDevice, sov.e(fileInfo), new ITransferFileCallback.Stub() { // from class: snz.4
            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                LogUtil.c("unite_HwCommonFileMgr", "stopTransmit onSuccess");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                LogUtil.c("unite_HwCommonFileMgr", "stopTransmit onFailure");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.c("unite_HwCommonFileMgr", "stopTransmit onProgress");
            }

            @Override // com.huawei.unitedevice.callback.ITransferFileCallback
            public void onResponse(int i, String str) throws RemoteException {
                IResultAIDLCallback iResultAIDLCallback2 = iResultAIDLCallback;
                if (iResultAIDLCallback2 != null) {
                    iResultAIDLCallback2.onFileRespond(i, str);
                    LogUtil.c("unite_HwCommonFileMgr", "stopTransmit onResponse");
                }
            }
        });
    }

    private void e(String str, int i, DeviceInfo deviceInfo) {
        LogUtil.c("unite_HwCommonFileMgr", "deviceStartPayFileTransfer fileName:", str, ", fileType:", Integer.valueOf(i));
        if (str == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String str2 = d;
        sb.append(str2);
        sb.append(str);
        String sb2 = sb.toString();
        if (i == 5 && str.endsWith(".png")) {
            sb2 = str2 + (str.substring(0, str.lastIndexOf(".")) + WatchFaceConstant.BIN_SUFFIX);
            snv.b().d(str2 + str, sb2);
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilePath(sb2);
        fileInfo.setFileName(str);
        fileInfo.setFileType(i);
        fileInfo.setPackageName(null);
        c(sod.b(deviceInfo), fileInfo, new IResultAIDLCallback.Stub() { // from class: snz.10
            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferState(int i2, String str3) {
                LogUtil.e("unite_HwCommonFileMgr", "deviceStartPayFileTransfer onFileTransferState");
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onTransferFailed(int i2, String str3) {
                LogUtil.e("unite_HwCommonFileMgr", "deviceStartPayFileTransfer onUpgradeFailed");
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileRespond(int i2, String str3) {
                LogUtil.e("unite_HwCommonFileMgr", "deviceStartPayFileTransfer onFileRespond");
            }

            @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
            public void onFileTransferReport(String str3) {
                LogUtil.c("unite_HwCommonFileMgr", "deviceStartToDeviceTransferOld startTransfer onFileTransferReport");
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
    
        if (r5 != 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.huawei.devicesdk.entity.DeviceInfo r4, byte[] r5) {
        /*
            r3 = this;
            java.lang.String r0 = "5.40.2 handleAppSend :"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "unite_HwCommonFileMgr"
            defpackage.blt.d(r1, r5, r0)
            if (r5 == 0) goto L2c
            int r0 = r5.length
            r2 = 2
            if (r0 >= r2) goto L13
            goto L2c
        L13:
            sol r0 = new sol
            r0.<init>()
            int r5 = defpackage.spl.c(r5, r0)     // Catch: defpackage.bmk -> L1f
            if (r5 == 0) goto L28
            goto L29
        L1f:
            java.lang.String r5 = "handleAppSend TlvException"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.LogUtil.e(r1, r5)
        L28:
            r5 = 0
        L29:
            r3.b(r4, r0, r5)
        L2c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.snz.a(com.huawei.devicesdk.entity.DeviceInfo, byte[]):void");
    }

    private void b(DeviceInfo deviceInfo, sol solVar, int i) {
        sol c2 = TransferFileQueueManager.d().c("5.40.2");
        if (c2 != null && c2.u() == solVar.u() && c(solVar, c2)) {
            this.s.c(solVar, c2);
            UniteDevice uniteDevice = new UniteDevice();
            uniteDevice.setDeviceInfo(deviceInfo);
            solVar.d(uniteDevice);
            if (i != 100000) {
                if (bky.e()) {
                    iyv.c("FileTransfer", "Device replies 5.40.2 with error code " + i);
                }
                if (solVar.n() != null) {
                    try {
                        f();
                        solVar.n().onTransferFailed(i, "");
                        LogUtil.c("unite_HwCommonFileMgr", "onUpgradeFailed errorCode :", Integer.valueOf(i));
                        e(solVar, sod.b(deviceInfo), 0);
                        return;
                    } catch (Exception e2) {
                        LogUtil.e("unite_HwCommonFileMgr", "handleRequest, Exception", bll.a(e2));
                        return;
                    }
                }
                LogUtil.a("unite_HwCommonFileMgr", "file callBack is null");
                return;
            }
            LogUtil.c("unite_HwCommonFileMgr", "device support transfer file");
            c2.c(solVar.l());
            this.z.put(sov.e(deviceInfo.getDeviceMac(), solVar.l()), solVar);
            this.s.d(solVar, this.z);
        }
    }

    private boolean c(sol solVar, sol solVar2) {
        String m = solVar2.m();
        String m2 = solVar.m();
        if (m == null) {
            return true;
        }
        return m.equals(m2);
    }

    private void g(DeviceInfo deviceInfo, byte[] bArr) {
        this.s.d(deviceInfo, bArr);
    }

    public void c(int i, int i2, DeviceInfo deviceInfo, int i3) {
        LogUtil.c("unite_HwCommonFileMgr", "enter sendFileHashToDevice, fileId :", Integer.valueOf(i), ", check_mode :", Integer.valueOf(i2));
        if (deviceInfo == null) {
            return;
        }
        if (this.z.get(sov.e(deviceInfo.getDeviceMac(), i)) != null) {
            if (i2 == 3) {
                b(i, deviceInfo, i3);
                return;
            }
            return;
        }
        sol c2 = TransferFileQueueManager.d().c("5.40.3 no task");
        if (c2 == null) {
            return;
        }
        if (c2.l() == i) {
            LogUtil.a("unite_HwCommonFileMgr", "sendFileHashToDevice, mFileInfoList have not or filePath isEmpty");
            sor.d(i, deviceInfo);
        } else {
            LogUtil.c("unite_HwCommonFileMgr", "5.40.3 response no task receive");
        }
    }

    private void b(int i, DeviceInfo deviceInfo, int i2) {
        sol solVar = this.z.get(sov.e(deviceInfo.getDeviceMac(), i));
        if (solVar == null) {
            sor.d(i, deviceInfo);
            LogUtil.a("unite_HwCommonFileMgr", "handleShare commonFileInfo error");
            return;
        }
        String ai = solVar.ai();
        if (TextUtils.isEmpty(ai) || i2 > 0) {
            LogUtil.c("unite_HwCommonFileMgr", "handleShare not from cache :", Integer.valueOf(i));
            ai = sov.a(solVar, i2, 1024, sod.e(solVar, solVar.ap()));
            blv.d(this.x);
        }
        if (!TextUtils.isEmpty(ai)) {
            LogUtil.c("unite_HwCommonFileMgr", "sendFileHashToDevice, get hash success");
            sor.e(i, ai, deviceInfo, i2);
        } else {
            LogUtil.a("unite_HwCommonFileMgr", "sendFileHashToDevice, get hash failed");
            sor.d(i, deviceInfo);
        }
    }

    private void c(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("unite_HwCommonFileMgr", bArr, "5.40.4 handleConsult :");
        if (bArr == null || bArr.length < 2) {
            return;
        }
        soo sooVar = new soo();
        try {
            if (!sod.a(sooVar, bArr)) {
                iyv.c("FileTransfer", "Invalid 5.40.4 send by device.");
                if (this.o.n() != null) {
                    try {
                        f();
                        this.o.n().onTransferFailed(140005, "");
                        LogUtil.c("unite_HwCommonFileMgr", "onUpgradeFailed errorCode :", 140005);
                        e(this.o, sod.b(deviceInfo), 0);
                    } catch (Exception e2) {
                        LogUtil.e("unite_HwCommonFileMgr", "handleRequest, Exception", bll.a(e2));
                    }
                } else {
                    LogUtil.a("unite_HwCommonFileMgr", "file callBack is null");
                }
            }
            this.j.put(sov.e(deviceInfo.getDeviceMac(), sooVar.b()), sooVar);
            sor.d(sooVar.b(), sooVar.j(), deviceInfo);
        } catch (bmk unused) {
            LogUtil.e("unite_HwCommonFileMgr", "handleConsult TlvException");
        }
    }

    private void b(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("unite_HwCommonFileMgr", bArr, "5.40.5 handleDeviceRequestData : ");
        if (bArr == null || bArr.length < 2) {
            return;
        }
        try {
            bmt.b b2 = new bmt().b(bArr, 2);
            b(b2.a((byte) 1, 0), b2.a((byte) 2, 0), b2.a((byte) 3, 0), snx.c(b2.c((byte) 4, null)), deviceInfo);
        } catch (bmk unused) {
            LogUtil.e("unite_HwCommonFileMgr", "handleRequestHash TlvException");
        }
    }

    private void b(int i, int i2, int i3, List<Integer> list, DeviceInfo deviceInfo) {
        if (this.z.containsKey(sov.e(deviceInfo.getDeviceMac(), i))) {
            f();
            a(i, i2, i3, list, deviceInfo);
        } else {
            LogUtil.a("unite_HwCommonFileMgr", "fileId is not in mTransferingFileList");
        }
    }

    private void a(int i, int i2, int i3, List<Integer> list, DeviceInfo deviceInfo) {
        byte[] bArr;
        LogUtil.c("unite_HwCommonFileMgr", "Enter sendDataToDevice fileId: ", Integer.valueOf(i), ", offset: ", Integer.valueOf(i2), ", length: ", Integer.valueOf(i3), ", bitmap.size :", Integer.valueOf(list.size()));
        String e2 = sov.e(deviceInfo.getDeviceMac(), i);
        if (!this.z.containsKey(e2)) {
            LogUtil.a("unite_HwCommonFileMgr", "sendDataToDevice, found no fileId");
            return;
        }
        if (list.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                stringBuffer.append(it.next());
            }
            LogUtil.a("unite_HwCommonFileMgr", "this request is retransmission. bitmap content is :", stringBuffer.toString());
        }
        byte[] d2 = d(this.z.get(e2), i, i2, i3);
        if (d2.length == 0) {
            LogUtil.c("unite_HwCommonFileMgr", "send file may delete, no need to trans.");
            sol c2 = TransferFileQueueManager.d().c("file_fail_check_is_cancel");
            if (c2 != null && c2.d() != null) {
                LogUtil.c("unite_HwCommonFileMgr", "user cancel this task, no need to deal this.");
                return;
            } else {
                d(i, 140006, deviceInfo);
                return;
            }
        }
        soo sooVar = this.j.get(e2);
        if (sooVar != null && sooVar.g()) {
            try {
                bArr = MessageDigest.getInstance("SHA-256").digest(d2);
            } catch (NoSuchAlgorithmException unused) {
                bArr = new byte[0];
                LogUtil.e("unite_HwCommonFileMgr", "digest, NoSuchAlgorithmException");
            }
            String d3 = blq.d(bArr);
            if (!TextUtils.isEmpty(d3)) {
                LogUtil.c("unite_HwCommonFileMgr", "send suframecheckresult hash to device, get hash success! checkResult: ", d3);
                sor.c(i, i2, i3, d3, deviceInfo);
            } else {
                LogUtil.a("unite_HwCommonFileMgr", "send suframecheckresult hash to device, get hash failed");
                sor.c(i, i2, i3, null, deviceInfo);
            }
        }
        c(this.s.e(i, i2, i3, list, deviceInfo), d2, e2);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00e6 A[Catch: all -> 0x010f, TRY_LEAVE, TryCatch #2 {, blocks: (B:8:0x000d, B:10:0x001c, B:12:0x0026, B:16:0x0048, B:18:0x004c, B:20:0x006b, B:22:0x006f, B:25:0x0084, B:27:0x0088, B:30:0x0096, B:33:0x00e6, B:36:0x00f6, B:41:0x00d1, B:45:0x0050, B:46:0x00ff), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f6 A[Catch: all -> 0x010f, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:8:0x000d, B:10:0x001c, B:12:0x0026, B:16:0x0048, B:18:0x004c, B:20:0x006b, B:22:0x006f, B:25:0x0084, B:27:0x0088, B:30:0x0096, B:33:0x00e6, B:36:0x00f6, B:41:0x00d1, B:45:0x0050, B:46:0x00ff), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte[] d(defpackage.sol r7, int r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.snz.d(sol, int, int, int):byte[]");
    }

    private void e(int i, int i2, byte[] bArr) {
        if (this.f.size() >= 4) {
            this.f.remove(Integer.valueOf(this.f.entrySet().iterator().next().getKey().intValue()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(i2), bArr);
        LogUtil.c("unite_HwCommonFileMgr", "bufferArrayMap add.", Integer.valueOf(hashMap.size()));
        this.f.put(Integer.valueOf(i), hashMap);
    }

    public void ejO_(ParcelFileDescriptor parcelFileDescriptor) {
        this.x = parcelFileDescriptor;
    }

    private void c(son sonVar, byte[] bArr, String str) {
        int i;
        int b2 = sonVar.b();
        d(str, sonVar.c());
        soo sooVar = this.j.get(str);
        if (sooVar == null) {
            LogUtil.a("unite_HwCommonFileMgr", "fileTransferParameter is null,fileId is :", Integer.valueOf(b2));
            return;
        }
        int e2 = sooVar.e();
        int i2 = 1;
        boolean z = !sooVar.j();
        LogUtil.c("unite_HwCommonFileMgr", "sendDataToDevice, isNeedEncrypt :", Boolean.valueOf(z), ", frameLength :", Integer.valueOf(e2));
        int b3 = sor.b(sonVar.e(), e2);
        int c2 = sonVar.c();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < b3) {
            int b4 = sor.b(sonVar.e(), e2, i4);
            if (i5 > 255) {
                i5 = 0;
            }
            List<Integer> d2 = sonVar.d();
            if (sooVar.f() && d2.size() > 0 && d2.get(i5).intValue() == i2) {
                LogUtil.c("unite_HwCommonFileMgr", "sendDataToDevice, this frame does not need to be retransmitted!");
                i = e2;
            } else {
                som somVar = new som();
                somVar.a(b2);
                somVar.c(i5);
                somVar.b(c2);
                somVar.b(z);
                i = e2;
                c(somVar, sor.a(i6, b4, bArr), sonVar.a());
                this.s.b(i5, b4, c2, i4);
            }
            i4 += b4;
            c2 += b4;
            i6 += b4;
            i5++;
            i3++;
            e2 = i;
            i2 = 1;
        }
        int a2 = sooVar.a();
        LogUtil.c("unite_HwCommonFileMgr", "sendDataToDevice, mWaitTimeout :", Integer.valueOf(a2));
        e(b2, a2, sonVar.a());
    }

    private void g() {
        sol c2 = TransferFileQueueManager.d().c("left_file_size_small");
        if (c2 != null) {
            c2.a(0);
        }
        this.s.c();
    }

    private void d(String str, int i) {
        int v;
        sol solVar = this.z.get(str);
        if (solVar != null && solVar.n() != null && (v = solVar.v()) != 0) {
            this.ab = 1;
            sol solVar2 = this.o;
            if (solVar2 != null) {
                solVar2.g(i);
            }
            long j = (i * 100) / v;
            if (v - i < PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) {
                g();
            }
            if (j < -2147483648L || j > 2147483647L) {
                LogUtil.c("unite_HwCommonFileMgr", "progressLong is not Integer.");
                return;
            }
            int i2 = (int) j;
            LogUtil.c("unite_HwCommonFileMgr", "fileSize :", Integer.valueOf(v), " , offset :", Integer.valueOf(i), " , progress :", Integer.valueOf(i2));
            try {
                solVar.n().onFileTransferState(i2, "");
                solVar.n().onFileTransferReport(sod.e("transferWay", "bluetooth"));
            } catch (Exception e2) {
                LogUtil.e("unite_HwCommonFileMgr", "reportProgressForUi Exception", bll.a(e2));
            }
        }
        d("bluetooth");
    }

    private void c(som somVar, byte[] bArr, DeviceInfo deviceInfo) {
        bir b2 = sod.b(somVar, bArr);
        bir.a aVar = new bir.a();
        if (!somVar.c()) {
            aVar.c(false);
        }
        synchronized (c) {
            this.i.add(aVar.b(b2));
        }
        a(deviceInfo);
    }

    private void a(DeviceInfo deviceInfo) {
        if (this.q == null) {
            this.q = HandlerCenter.yt_(new c(), "unite_HwCommonFileMgr");
            Message obtain = Message.obtain();
            obtain.what = 400;
            obtain.obj = deviceInfo;
            this.q.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 400;
        obtain2.obj = deviceInfo;
        this.q.sendMessage(obtain2);
    }

    private void e(int i, int i2, DeviceInfo deviceInfo) {
        if (this.q != null) {
            if (i2 != 0) {
                Message obtain = Message.obtain();
                obtain.what = 100;
                obtain.arg1 = i;
                obtain.obj = deviceInfo;
                this.q.sendMessage(obtain, i2 * 1000);
                return;
            }
            return;
        }
        this.q = HandlerCenter.yt_(new c(), "unite_HwCommonFileMgr");
        if (i2 != 0) {
            Message obtain2 = Message.obtain();
            obtain2.what = 100;
            obtain2.arg1 = i;
            obtain2.obj = deviceInfo;
            this.q.sendMessage(obtain2, i2 * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        if (this.q == null) {
            this.q = HandlerCenter.yt_(new c(), "unite_HwCommonFileMgr");
        }
        if (i != 0) {
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.arg1 = i2;
            this.q.sendMessage(obtain, i * 1000);
        }
    }

    private void j(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("unite_HwCommonFileMgr", bArr, "5.40.7 handleDeviceResultReport :");
        i();
        if (bArr == null || bArr.length < 2) {
            return;
        }
        try {
            bmt.b b2 = new bmt().b(bArr, 2);
            a(b2.a((byte) 1, 0), b2.a((byte) 2, -1), deviceInfo);
        } catch (bmk unused) {
            LogUtil.e("unite_HwCommonFileMgr", "handleDeviceResultReport TlvException");
        }
    }

    private void a(int i, int i2, DeviceInfo deviceInfo) {
        if (this.q == null) {
            this.q = HandlerCenter.yt_(new c(), "unite_HwCommonFileMgr");
            Message obtain = Message.obtain();
            obtain.what = 200;
            obtain.arg1 = i;
            obtain.arg2 = i2;
            obtain.obj = deviceInfo;
            this.q.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 200;
        obtain2.arg1 = i;
        obtain2.arg2 = i2;
        obtain2.obj = deviceInfo;
        this.q.sendMessage(obtain2);
    }

    private void h(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("unite_HwCommonFileMgr", bArr, "5.40.8 handleDeviceStatusReport :");
        i();
        f();
        if (bArr == null || bArr.length < 2) {
            return;
        }
        try {
            bmt.b b2 = new bmt().b(bArr, 2);
            d(b2.a((byte) 1, 0), b2.a(Byte.MAX_VALUE, 0), deviceInfo);
        } catch (bmk unused) {
            LogUtil.e("unite_HwCommonFileMgr", "handleDeviceStatusReport TlvException");
        }
    }

    private void d(int i, int i2, DeviceInfo deviceInfo) {
        if (this.q == null) {
            this.q = HandlerCenter.yt_(new c(), "unite_HwCommonFileMgr");
            Message obtain = Message.obtain();
            obtain.what = 300;
            obtain.arg1 = i;
            obtain.arg2 = i2;
            obtain.obj = deviceInfo;
            this.q.sendMessage(obtain);
            return;
        }
        Message obtain2 = Message.obtain();
        obtain2.what = 300;
        obtain2.arg1 = i;
        obtain2.arg2 = i2;
        obtain2.obj = deviceInfo;
        this.q.sendMessage(obtain2);
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("unite_HwCommonFileMgr", bArr, "5.40.9 handleCancelReply :");
        if (bArr == null || bArr.length < 2) {
            return;
        }
        try {
            bmt.b b2 = new bmt().b(bArr, 2);
            c(b2.a((byte) 1, 0), b2.a(Byte.MAX_VALUE, 0), deviceInfo);
        } catch (bmk unused) {
            LogUtil.e("unite_HwCommonFileMgr", "handleCancelReply TlvException");
        }
    }

    private void b(final String str, final sol solVar) {
        this.ab = 0;
        ThreadPoolManager.d().execute(new Runnable() { // from class: soe
            @Override // java.lang.Runnable
            public final void run() {
                snz.this.d(solVar, str);
            }
        });
    }

    /* synthetic */ void d(sol solVar, String str) {
        soz i = HwWifiP2pTransferManager.d().i();
        if (i != null && i.o() == -1) {
            this.u = false;
            solVar.n(1);
        } else {
            this.u = sow.e(solVar, this.aa, this.p, -10);
        }
        LogUtil.c("unite_HwCommonFileMgr", "wifiAndBtTask ", str, " : ", Boolean.valueOf(this.u));
        if (this.u) {
            return;
        }
        this.z.remove(String.valueOf(solVar.u()));
        b(solVar);
    }

    private void c(int i, int i2, DeviceInfo deviceInfo) {
        sol c2;
        String e2 = sov.e(deviceInfo.getDeviceMac(), i);
        LogUtil.c("unite_HwCommonFileMgr", "mIsBtToWifiState:", Boolean.valueOf(this.r), "errorCode:", Integer.valueOf(i2));
        sol solVar = this.z.get(e2);
        if (i2 == 100000 && this.r && solVar != null) {
            this.r = false;
            f();
            e(solVar, sod.b(deviceInfo), 1);
            solVar.d(sod.b(deviceInfo));
            this.z.put(String.valueOf(solVar.u()), solVar);
            b("reportCancelResult", solVar);
            return;
        }
        if (this.r && (c2 = TransferFileQueueManager.d().c("want change wifi and stop bt, but device wrong.")) != null) {
            HwWifiP2pTransferManager.d().d(c2.u());
        }
        a(i2, deviceInfo, solVar);
    }

    protected void a(int i, DeviceInfo deviceInfo, sol solVar) {
        if (solVar == null || solVar.d() == null) {
            return;
        }
        try {
            LogUtil.c("unite_HwCommonFileMgr", "handleCancelReply errorCode :", Integer.valueOf(i));
            if (i == 100000) {
                if (solVar.u() == 1) {
                    solVar.d().onResponse(20003, String.valueOf(solVar.m()));
                    LogUtil.c("unite_HwCommonFileMgr", "entry  watchType callback , fileName: ", solVar.m());
                } else {
                    solVar.d().onResponse(20003, "");
                }
                f();
                e(solVar, sod.b(deviceInfo), 0);
                return;
            }
            if (solVar.u() == 1) {
                solVar.d().onResponse(20004, String.valueOf(solVar.m()));
            } else {
                solVar.d().onResponse(20004, "");
            }
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgr", "handleCancelReply Exception", bll.a(e2));
        }
    }

    public class c implements Handler.Callback {
        public c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 100) {
                snz.this.ejM_(message);
                return true;
            }
            if (i == 101) {
                snz.this.s.ejS_(message, snz.this.z);
                return true;
            }
            if (i == 200) {
                int i2 = message.arg1;
                int i3 = message.arg2;
                Object obj = message.obj;
                if (obj instanceof DeviceInfo) {
                    DeviceInfo deviceInfo = (DeviceInfo) obj;
                    LogUtil.c("unite_HwCommonFileMgr", "handleMessage receive result! fileId :", Integer.valueOf(i2), " , result :", Integer.valueOf(i3), " , deviceInfo :", blt.a(deviceInfo.getDeviceMac()));
                    sor.e(i2, deviceInfo);
                    snz.this.c(deviceInfo, i2, i3);
                }
                return true;
            }
            if (i == 300) {
                snz.this.ejN_(message);
                return true;
            }
            if (i == 400) {
                Object obj2 = message.obj;
                if (obj2 instanceof DeviceInfo) {
                    snz.this.b((DeviceInfo) obj2);
                }
                return true;
            }
            LogUtil.a("unite_HwCommonFileMgr", "handleMessage default msg.what :", Integer.valueOf(message.what));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ejN_(Message message) {
        int i = message.arg1;
        int i2 = message.arg2;
        Object obj = message.obj;
        if (obj instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) obj;
            LogUtil.c("unite_HwCommonFileMgr", "handleMessage device report error, fileId :", Integer.valueOf(i), " , result :", Integer.valueOf(i2), " , deviceInfo :", blt.a(deviceInfo.getDeviceMac()));
            sol solVar = this.z.get(sov.e(deviceInfo.getDeviceMac(), i));
            if (solVar == null) {
                LogUtil.c("unite_HwCommonFileMgr", "5.40.2 no response.");
                solVar = TransferFileQueueManager.d().c("5.40.8");
            }
            b(solVar, i2);
            e(solVar, sod.b(deviceInfo), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ejM_(Message message) {
        this.ab = 0;
        LogUtil.c("unite_HwCommonFileMgr", "handleMessage wait timeout! fileId :", Integer.valueOf(message.arg1));
        Object obj = message.obj;
        if (obj instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) obj;
            if (bky.e()) {
                iyv.c("FileTransfer", "Device replies 5.40.x reach timeout");
            }
            sol c2 = TransferFileQueueManager.d().c("5.40.x timeout");
            if (c2 == null || c2.n() == null) {
                return;
            }
            try {
                LogUtil.c("unite_HwCommonFileMgr", "getSendFileStatus : ", Integer.valueOf(c2.ag()));
                if (c2.ag() == 2) {
                    c2.n().onTransferFailed(109022, c2.i().getIdentify());
                } else {
                    c2.n().onTransferFailed(141000, "");
                }
            } catch (Exception e2) {
                LogUtil.e("unite_HwCommonFileMgr", "processTimeout Exception", bll.a(e2));
            }
            e(c2, sod.b(deviceInfo), 0);
        }
    }

    protected void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: soh
            @Override // java.lang.Runnable
            public final void run() {
                snz.this.b();
            }
        });
    }

    /* synthetic */ void b() {
        LogUtil.c("unite_HwCommonFileMgr", "processWifiEnableMessage enter.");
        if (this.s.d(this.l)) {
            String identify = this.l.i() != null ? this.l.i().getIdentify() : "";
            HwWifiP2pTransferManager.d().p();
            HwWifiP2pTransferManager.d().c(this.l, this.aa, 1);
            HwWifiP2pTransferManager.d().c(identify, new HwWifiP2pTransferManager.TransferBleToWifiCallback() { // from class: sog
                @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.TransferBleToWifiCallback
                public final void onResult(boolean z) {
                    snz.this.d(z);
                }
            });
        }
    }

    /* synthetic */ void d(boolean z) {
        LogUtil.c("unite_HwCommonFileMgr", "isWifiSuccess: ", Boolean.valueOf(z), " fileId: ", Integer.valueOf(this.l.l()));
        if (z) {
            this.r = true;
            i();
            sod.a(this.l, this.z);
            sor.b(this.l.i(), this.l.l(), this.l);
            return;
        }
        HwWifiP2pTransferManager.d().e();
        HwWifiP2pTransferManager.d().d(this.l.u());
        this.r = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo, int i, int i2) {
        try {
            sol solVar = this.z.get(sov.e(deviceInfo.getDeviceMac(), i));
            if (solVar == null || solVar.n() == null) {
                return;
            }
            f();
            e(solVar, sod.b(deviceInfo), 0);
            this.ab = 0;
            if (i2 == 1) {
                solVar.n().onFileTransferState(100, "");
            }
            solVar.n().onFileRespond(i2, "");
            LogUtil.c("unite_HwCommonFileMgr", "handleMessage receive result! onFileRespond");
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgr", "handleMessage receive result! Exception", bll.a(e2));
        }
    }

    private void b(sol solVar, int i) {
        if (solVar != null) {
            try {
                if (solVar.n() != null) {
                    solVar.n().onTransferFailed(i, "");
                    LogUtil.c("unite_HwCommonFileMgr", "reportFailedForUi fileId errorCode :", Integer.valueOf(i));
                }
            } catch (Exception e2) {
                LogUtil.e("unite_HwCommonFileMgr", "reportFailedForUi Exception", bll.a(e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo) {
        ArrayList arrayList = new ArrayList(16);
        synchronized (c) {
            bir poll = this.i.poll();
            while (poll != null) {
                arrayList.add(poll);
                poll = this.i.poll();
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sor.c(sod.b(deviceInfo), (bir) it.next());
        }
    }

    public void e(UniteDevice uniteDevice, CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback) {
        if (commonFileInfoParcel == null || iTransferFileCallback == null || TextUtils.isEmpty(commonFileInfoParcel.getFileName())) {
            LogUtil.a("unite_HwCommonFileMgr", "stopTransferByQueue param empty");
            return;
        }
        if (TransferFileQueueManager.d().a("stopTransferByQueue", commonFileInfoParcel.getFileType(), commonFileInfoParcel.getFileName()) != null) {
            LogUtil.c("unite_HwCommonFileMgr", "task no in header, remove and stop it.");
            e(iTransferFileCallback, commonFileInfoParcel);
            return;
        }
        sol c2 = TransferFileQueueManager.d().c("stop_task");
        if (c2 != null) {
            c2.b(iTransferFileCallback);
            this.ab = 0;
            this.r = false;
            LogUtil.a("unite_HwCommonFileMgr", "mIsWifiP2pTransfer:", Boolean.valueOf(this.u));
            if (this.u) {
                if (sow.e(commonFileInfoParcel, iTransferFileCallback)) {
                    LogUtil.a("unite_HwCommonFileMgr", "wifiP2pStopTransferFileByQueue removeCache");
                    c(commonFileInfoParcel);
                    return;
                }
                sol solVar = this.z.get(String.valueOf(commonFileInfoParcel.getFileType()));
                if (solVar != null) {
                    LogUtil.a("unite_HwCommonFileMgr", "stopTransferByQueue: fileInfo.setCallback()");
                    solVar.b(iTransferFileCallback);
                    return;
                } else {
                    LogUtil.a("unite_HwCommonFileMgr", "stopTransferByQueue: fileInfo is null");
                    return;
                }
            }
            if (!commonFileInfoParcel.isInTheQueue()) {
                LogUtil.c("unite_HwCommonFileMgr", "stop transfer file is not in the queue");
                this.s.c();
                this.s.d();
                e(uniteDevice, commonFileInfoParcel.getFileName(), commonFileInfoParcel.getFileType(), iTransferFileCallback);
                return;
            }
            c(uniteDevice, commonFileInfoParcel, iTransferFileCallback);
            return;
        }
        LogUtil.e("unite_HwCommonFileMgr", "no any task in queue, immediately report cancel success.");
        e(iTransferFileCallback, commonFileInfoParcel);
    }

    private void e(ITransferFileCallback iTransferFileCallback, CommonFileInfoParcel commonFileInfoParcel) {
        try {
            String fileName = commonFileInfoParcel.getFileName();
            if (commonFileInfoParcel.getFileType() == 1) {
                iTransferFileCallback.onResponse(20003, String.valueOf(fileName));
                LogUtil.c("unite_HwCommonFileMgr", "entry  watchType callback , fileName: ", fileName);
            } else {
                iTransferFileCallback.onResponse(20003, "");
            }
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgr", "immediatelyReportCancelSuccess Exception", bll.a(e2));
        }
    }

    private void c(UniteDevice uniteDevice, CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback) {
        sol a2 = this.s.a(commonFileInfoParcel);
        synchronized (this.p) {
            LinkedList<sol> linkedList = this.p.get(Integer.valueOf(a2.u()));
            if (linkedList != null && linkedList.peek() != null) {
                sol peek = linkedList.peek();
                if (peek != null && peek.equals(a2)) {
                    LogUtil.c("unite_HwCommonFileMgr", "stopTransfer firstTask");
                    e(uniteDevice, peek.m(), peek.u(), iTransferFileCallback);
                    return;
                }
                try {
                    Iterator<sol> it = linkedList.iterator();
                    while (it.hasNext()) {
                        if (it.next().equals(a2)) {
                            it.remove();
                            iTransferFileCallback.onResponse(20003, "");
                            return;
                        }
                    }
                    iTransferFileCallback.onResponse(20004, "");
                } catch (Exception e2) {
                    LogUtil.e("unite_HwCommonFileMgr", "synchronizedFileQueueMap Exception", bll.a(e2));
                }
                return;
            }
            LogUtil.a("unite_HwCommonFileMgr", "stopTransfer error");
        }
    }

    private void b(FileInfo fileInfo, int i, UniteDevice uniteDevice, IResultAIDLCallback iResultAIDLCallback) {
        sol solVar = new sol();
        solVar.a(fileInfo.getFileName());
        solVar.e(fileInfo.getFilePath());
        solVar.m(fileInfo.getFileType());
        solVar.i(i);
        solVar.b(iResultAIDLCallback);
        solVar.d(uniteDevice);
        if (fileInfo.getSourceId() > 0) {
            solVar.e(fileInfo.getSourceId());
        }
        solVar.j(fileInfo.getPackageName());
        solVar.c(solVar.u());
        solVar.ejU_(fileInfo.getFileDescriptor());
        solVar.g(sod.ejR_(solVar, this.x));
        solVar.e(TransferFileQueueManager.TaskMode.APP);
        if (TransferFileQueueManager.d().a(solVar, "app.startToSendFileInfo")) {
            a(solVar);
        } else {
            LogUtil.c("unite_HwCommonFileMgr", "app want send file, wait queue.");
        }
    }

    public void a(sol solVar) {
        i();
        synchronized (e) {
            this.o = solVar;
            this.l = solVar;
        }
        this.z.put(String.valueOf(solVar.u()), solVar);
        b("startToSendFileInfo", solVar);
    }

    private void c(CommonFileInfoParcel commonFileInfoParcel) {
        LogUtil.c("unite_HwCommonFileMgr", "remove cache");
        this.z.remove(String.valueOf(commonFileInfoParcel.getFileType()));
        TransferFileQueueManager.d().h("stop_file_success.");
        sol solVar = new sol();
        solVar.a(commonFileInfoParcel.getFileName());
        solVar.m(commonFileInfoParcel.getFileType());
        solVar.h(commonFileInfoParcel.getSourcePackageName());
        solVar.d(commonFileInfoParcel.getDestinationPackageName());
        synchronized (this.p) {
            LinkedList<sol> linkedList = this.p.get(Integer.valueOf(commonFileInfoParcel.getFileType()));
            if (linkedList != null && !linkedList.isEmpty()) {
                Iterator<sol> it = linkedList.iterator();
                while (it.hasNext()) {
                    if (it.next().equals(solVar)) {
                        it.remove();
                        LogUtil.c("unite_HwCommonFileMgr", "remove file queue map.");
                        return;
                    }
                }
            }
        }
    }

    public void e(UniteDevice uniteDevice, String str, int i, ITransferFileCallback iTransferFileCallback) {
        if (uniteDevice == null || str == null || iTransferFileCallback == null) {
            LogUtil.a("unite_HwCommonFileMgr", "stopTransferFile param empty");
            return;
        }
        LogUtil.c("unite_HwCommonFileMgr", "stopTransferFile fileName : ", str, "fileType: ", Integer.valueOf(i));
        for (Map.Entry<String, sol> entry : this.z.entrySet()) {
            LogUtil.c("unite_HwCommonFileMgr", "stopTransferFile fileMap.getValue().getFileName():", entry.getValue().m(), "stopTransferFile fileMap.getValue().getFileType():", Integer.valueOf(entry.getValue().u()));
            if (TextUtils.equals(entry.getValue().m(), str) && entry.getValue().u() == i) {
                LogUtil.c("unite_HwCommonFileMgr", "stopTransferFile fileName :", entry.getValue().m());
                entry.getValue().b(iTransferFileCallback);
                a(uniteDevice, entry.getValue().l(), entry.getKey());
            } else {
                LogUtil.a("unite_HwCommonFileMgr", "stopTransferFile else branch");
            }
        }
    }

    private void a(UniteDevice uniteDevice, int i, String str) {
        i();
        sor.b(uniteDevice, i, this.z.get(str));
    }

    public void e() {
        sob.e().e(this.h);
        this.k.unregisterReceiver(this.v);
        h();
    }

    private static void h() {
        synchronized (b) {
            f17169a = null;
        }
    }

    public static snz a() {
        snz snzVar;
        synchronized (b) {
            if (f17169a == null) {
                f17169a = new snz(BaseApplication.e());
            }
            snzVar = f17169a;
        }
        return snzVar;
    }
}
