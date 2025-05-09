package defpackage;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class soa {
    private static final Object c = new Object();
    private static volatile soa d;

    /* renamed from: a, reason: collision with root package name */
    private CountDownLatch f17172a;
    private sol b;
    private BlockingQueue<sol> g = new LinkedBlockingDeque();
    private WifiP2pTransferListener f = new WifiP2pTransferListener() { // from class: soa.2
        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onSuccess(int i, String str, int i2) {
            soa.this.c(i, str, i2);
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onFail(int i, String str, int i2) {
            soa.this.a(i, str, i2);
        }

        @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener
        public void onProcess(int i, int i2) {
            soa.this.e(i, i2);
        }
    };
    private ExtendHandler e = HandlerCenter.yt_(new c(), "CommonFileRequestWifiManager");

    private soa() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: sny
            @Override // java.lang.Runnable
            public final void run() {
                soa.this.e();
            }
        });
    }

    /* synthetic */ void e() {
        try {
            a();
        } catch (InterruptedException e) {
            LogUtil.e("CommonFileRequestWifiManager", "dispatchMainLoop exception", bll.a(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str, int i2) {
        LogUtil.c("CommonFileRequestWifiManager", "mWifiListener parameter： code : ", Integer.valueOf(i), "; msg: ", str, "; fileType: ", Integer.valueOf(i2));
        if (i != 0 && i != 1) {
            if (i == 10000) {
                sol solVar = this.b;
                if (solVar != null) {
                    LogUtil.c("CommonFileRequestWifiManager", "resultSha256: " + sod.ejR_(solVar, solVar.ejT_()) + ", fileSha256: " + HwWifiP2pTransferManager.d().i().c());
                    soy.d(i2);
                    c(15, i2);
                    return;
                }
                LogUtil.c("CommonFileRequestWifiManager", "no fileInfo, please check, stop wifi");
                HwWifiP2pTransferManager.d().e();
                return;
            }
            if (i != 140008) {
                LogUtil.a("CommonFileRequestWifiManager", "unknown code, please deal this : ", Integer.valueOf(i));
                return;
            }
        }
        d(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, int i2) {
        LogUtil.c("CommonFileRequestWifiManager", "mWifiListener fail code : ", Integer.valueOf(i), " msg : ", str);
        sol solVar = this.b;
        if (solVar != null) {
            HwWifiP2pTransferManager.d().e();
            if (!HwWifiP2pTransferManager.d().b(i2)) {
                LogUtil.a("CommonFileRequestWifiManager", "isHasFile is false");
                return;
            }
            HwWifiP2pTransferManager.d().d(i2);
            d(i, solVar.p(), str);
            d(solVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        LogUtil.c("CommonFileRequestWifiManager", "mWifiListener process ", Integer.valueOf(i));
        sol solVar = this.b;
        if (solVar != null) {
            ITransferFileCallback p = solVar.p();
            if (p != null) {
                try {
                    p.onProgress(i, "");
                    p.onResponse(i2, String.format("request file by wifi,{%s}", solVar.m()));
                    return;
                } catch (RemoteException e) {
                    LogUtil.e("CommonFileRequestWifiManager", "wifi process listener exception : ", bll.a(e));
                    return;
                }
            }
            LogUtil.a("CommonFileRequestWifiManager", "callback is null. please check.");
            return;
        }
        LogUtil.a("CommonFileRequestWifiManager", "mCurrentCommonFileInfo is null. please check.");
    }

    private void d(int i, int i2) {
        ExtendHandler extendHandler = this.e;
        if (extendHandler != null) {
            extendHandler.removeMessages(i2);
        }
        if (this.b != null) {
            HwWifiP2pTransferManager.d().d(i2);
            d(i, this.b.p(), "");
            d(this.b);
            return;
        }
        LogUtil.a("CommonFileRequestWifiManager", "file valid file info is null. please check");
    }

    private void d(sol solVar) {
        if (solVar == null) {
            LogUtil.a("CommonFileRequestWifiManager", "handleRequestEnd error, file info is null");
            return;
        }
        LogUtil.c("CommonFileRequestWifiManager", "finish file:", solVar.m());
        blv.d(solVar.z());
        CountDownLatch countDownLatch = this.f17172a;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    private void a() throws InterruptedException {
        LogUtil.c("CommonFileRequestWifiManager", "begin dispatchMainLoop");
        while (true) {
            sol take = this.g.take();
            boolean e = sow.e(take, this.f, new HashMap(0), -10);
            boolean b = HwWifiP2pTransferManager.d().b(take.u());
            LogUtil.c("CommonFileRequestWifiManager", "next fileInfo from waitQueue: ", Integer.valueOf(take.u()), "; ", take.m(), "task is accept by wifi: ", Boolean.valueOf(e), "; ", Boolean.valueOf(b));
            if (!e || !b) {
                HwWifiP2pTransferManager.d().e();
                d(1029, take.p(), "task is accept by wifi: " + e + "; " + b);
            } else {
                this.b = take;
                this.f17172a = new CountDownLatch(1);
                LogUtil.c("CommonFileRequestWifiManager", "file wait: ", take.m());
                boolean await = this.f17172a.await(600000L, TimeUnit.MILLISECONDS);
                this.f17172a = null;
                if (!await) {
                    LogUtil.e("CommonFileRequestWifiManager", "file wait is timeout");
                    a(GLMapStaticValue.MAP_PARAMETERNAME_SCENIC, "file transfer timeout", this.b.u());
                }
                this.b = null;
                LogUtil.c("CommonFileRequestWifiManager", "file transfer end:", take.m());
            }
        }
    }

    private void d(int i, ITransferFileCallback iTransferFileCallback, String str) {
        if (iTransferFileCallback != null) {
            try {
                if (i == 1) {
                    iTransferFileCallback.onSuccess(i, "", "");
                } else {
                    LogUtil.c("CommonFileRequestWifiManager", "callback.onTransferFailed(), code: ", Integer.valueOf(i));
                    iTransferFileCallback.onFailure(i, str);
                }
            } catch (RemoteException e) {
                LogUtil.e("CommonFileRequestWifiManager", "wifi success listener exception : ", bll.a(e));
            }
        }
    }

    public static soa c() {
        soa soaVar;
        synchronized (c) {
            if (d == null) {
                d = new soa();
            }
            soaVar = d;
        }
        return soaVar;
    }

    public void d(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        if (requestFileInfo == null || iTransferFileCallback == null) {
            LogUtil.a("CommonFileRequestWifiManager", "start request param null");
            return;
        }
        LogUtil.c("CommonFileRequestWifiManager", "start request file:", requestFileInfo.getFileName(), " ;", Integer.valueOf(requestFileInfo.getFileType()), " by wifi");
        sol b = b(requestFileInfo, iTransferFileCallback);
        if (b.i() == null) {
            try {
                iTransferFileCallback.onFailure(130004, "");
                return;
            } catch (RemoteException e) {
                LogUtil.e("CommonFileRequestWifiManager", "startRequest exception : ", bll.a(e));
                return;
            }
        }
        if (!b(b)) {
            LogUtil.a("CommonFileRequestWifiManager", "file put wait queue fail");
            try {
                iTransferFileCallback.onFailure(130005, "");
            } catch (RemoteException e2) {
                LogUtil.e("CommonFileRequestWifiManager", "startRequest exception : ", bll.a(e2));
            }
        }
        LogUtil.c("CommonFileRequestWifiManager", "file put wait queue success");
    }

    private boolean b(sol solVar) {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            if (((sol) it.next()).equals(solVar)) {
                LogUtil.c("CommonFileRequestWifiManager", "file already exit ", solVar.m());
                return false;
            }
        }
        try {
            this.g.put(solVar);
            return true;
        } catch (InterruptedException e) {
            LogUtil.e("CommonFileRequestWifiManager", "putFileInfo2WaitQueue put exception:", bll.a(e));
            return false;
        }
    }

    private sol b(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        sol solVar = new sol();
        solVar.m(requestFileInfo.getFileType());
        if (!TextUtils.isEmpty(requestFileInfo.getFileName())) {
            solVar.a(requestFileInfo.getFileName());
        }
        solVar.r(2);
        solVar.i(Integer.MAX_VALUE);
        solVar.e(requestFileInfo.getSavePath());
        solVar.a(iTransferFileCallback);
        solVar.g(sov.e(new File(requestFileInfo.getSavePath())));
        LogUtil.c("CommonFileRequestWifiManager", "getCommonFileInfo fileInfo.getFilePath = " + solVar.s() + ", fileInfo.getSha256Result = " + solVar.ai());
        if (!TextUtils.isEmpty(requestFileInfo.getDeviceMac())) {
            solVar.d(sov.e(requestFileInfo.getDeviceMac()));
        } else {
            LogUtil.c("CommonFileRequestWifiManager", "deviceMac is null");
        }
        solVar.b(requestFileInfo.getDictTypeId());
        return solVar;
    }

    private void c(int i, int i2) {
        if (this.e == null) {
            this.e = HandlerCenter.yt_(new c(), "CommonFileRequestWifiManager");
        }
        if (i != 0) {
            Message obtain = Message.obtain();
            obtain.what = i2;
            obtain.arg1 = 101;
            this.e.sendMessage(obtain, i * 1000);
        }
    }

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                LogUtil.a("CommonFileRequestWifiManager", "handleMessage param null");
                return false;
            }
            if (message.arg1 == 101) {
                soa.this.a(GLMapStaticValue.MAP_PARAMETERNAME_SCENIC, "54.7 time out", message.what);
                return true;
            }
            LogUtil.a("CommonFileRequestWifiManager", "handleMessage no match value");
            return false;
        }
    }
}
