package defpackage;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Patterns;
import com.huawei.health.device.wifi.control.logic.AccessNetWorkTask;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.net.DatagramSocket;
import java.nio.charset.Charset;

/* loaded from: classes3.dex */
public class csy extends AccessNetWorkTask {

    /* renamed from: a, reason: collision with root package name */
    private int[] f11454a;
    private int[][] b;
    private BaseCallbackInterface c;
    private Context d;
    private Handler e = new Handler() { // from class: csy.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                csy.this.Ma_(message);
                csy.this.stopTask();
                return;
            }
            if (i == 2) {
                if (csy.this.c != null) {
                    csy.this.c.onFailure(2108);
                }
                csy.this.stopTask();
                return;
            }
            if (i == 3) {
                if (csy.this.c != null) {
                    csy.this.c.onFailure(2101);
                }
                csy.this.stopTask();
                return;
            }
            if (i == 4) {
                LogUtil.c("ProbeRequestTask", "stop send package");
                if (csy.this.c != null) {
                    csy.this.c.onStatus(2210);
                }
                csy.this.pauseTask();
                return;
            }
            if (i == 5) {
                LogUtil.a("ProbeRequestTask", "SDK_INT ", Integer.valueOf(Build.VERSION.SDK_INT));
                if (Build.VERSION.SDK_INT >= 28) {
                    for (int i2 : csy.this.g) {
                        csy.this.f.enableNetwork(i2, true);
                    }
                }
                csy.this.f.startScan();
                csy.this.e.sendEmptyMessageDelayed(5, 2000L);
                return;
            }
            LogUtil.h("ProbeRequestTask", "unknown msg:", Integer.valueOf(message.what));
        }
    };
    private final WifiManager f;
    private int[] g;
    private a i;

    public csy(Context context, csr csrVar, BaseCallbackInterface baseCallbackInterface) {
        this.d = context;
        this.c = baseCallbackInterface;
        if (csrVar != null) {
            this.f11454a = csrVar.d();
        }
        this.i = new a();
        if (this.d == null) {
            LogUtil.h("ProbeRequestTask", "ProbeRequestTask mContext is null");
            this.d = BaseApplication.getContext();
        }
        LogUtil.c("ProbeRequestTask", "ProbeRequestTask mIpData: ", this.f11454a);
        if (this.f11454a == null) {
            this.f11454a = new int[1];
        }
        this.f = (WifiManager) this.d.getSystemService("wifi");
        this.b = cst.a(this.f11454a);
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void startTask() {
        this.e.sendEmptyMessageDelayed(4, 60000L);
        this.e.sendEmptyMessageDelayed(2, 90000L);
        e();
        this.e.sendEmptyMessage(5);
        a aVar = this.i;
        if (aVar != null) {
            aVar.d();
        }
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void pauseTask() {
        cst.c(this.d);
        this.e.removeMessages(5);
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void stopTask() {
        cst.c(this.d);
        a aVar = this.i;
        if (aVar != null) {
            aVar.c();
        }
        this.e.removeMessages(4);
        this.e.removeMessages(2);
        this.e.removeMessages(5);
        this.e.removeMessages(3);
    }

    class a implements Runnable {
        DatagramSocket b;
        private volatile boolean e;

        private a() {
            this.b = null;
            this.e = false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x008d, code lost:
        
            if (r8 == null) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x008f, code lost:
        
            r0 = r8.toString().replaceAll("/", "");
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x009c, code lost:
        
            com.huawei.hwlogsmodel.LogUtil.c("ProbeRequestTask", "UdpServer: ipClientAddress is ", r0);
            com.huawei.hwlogsmodel.LogUtil.a("ProbeRequestTask", "UdpServer get message: ", r0);
            r6 = android.os.Message.obtain();
            r6.what = 1;
            r6.obj = r0;
            r13.d.e.sendMessage(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x009a, code lost:
        
            r0 = "0.0.0.0";
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 288
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: csy.a.run():void");
        }

        public void d() {
            csy.this.i.e = false;
            jdx.b(csy.this.i);
        }

        public void c() {
            csy.this.i.e = true;
            if (csy.this.i.b == null) {
                return;
            }
            LogUtil.c("ProbeRequestTask", "mUdpServer: is close ", Boolean.valueOf(csy.this.i.b.isClosed()));
            csy.this.i.b.close();
            LogUtil.h("ProbeRequestTask", "UdpServer: stop() mServerSocket closed");
        }
    }

    private void e() {
        int[][] iArr = this.b;
        if (iArr.length > 0) {
            this.g = new int[iArr.length];
        }
        int i = 0;
        while (true) {
            int[][] iArr2 = this.b;
            if (i >= iArr2.length) {
                return;
            }
            int addNetwork = this.f.addNetwork(cst.LW_(new String(ctv.d(iArr2[i]), Charset.forName("UTF-8"))));
            this.g[i] = addNetwork;
            this.f.saveConfiguration();
            LogUtil.c("ProbeRequestTask", "addNetwork ", Integer.valueOf(addNetwork));
            LogUtil.c("ProbeRequestTask", "HiddenSSID =", Boolean.valueOf(this.f.getConnectionInfo().getHiddenSSID()));
            if (Build.VERSION.SDK_INT < 28) {
                this.f.enableNetwork(addNetwork, false);
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Ma_(Message message) {
        try {
            Object obj = message.obj;
            if (obj instanceof String) {
                String str = (String) obj;
                if (Patterns.IP_ADDRESS.matcher(str).matches()) {
                    LogUtil.a("ProbeRequestTask", "baseurl is ", cpw.d(str));
                    BaseCallbackInterface baseCallbackInterface = this.c;
                    if (baseCallbackInterface != null) {
                        baseCallbackInterface.onSuccess(str);
                    }
                } else {
                    BaseCallbackInterface baseCallbackInterface2 = this.c;
                    if (baseCallbackInterface2 != null) {
                        baseCallbackInterface2.onFailure(2101);
                    }
                }
            } else {
                LogUtil.h("ProbeRequestTask", "ipAddressObj not instanceof String");
            }
        } catch (IndexOutOfBoundsException unused) {
            LogUtil.b("ProbeRequestTask", "processGetMessage IndexOutOfBoundsException");
        }
    }
}
