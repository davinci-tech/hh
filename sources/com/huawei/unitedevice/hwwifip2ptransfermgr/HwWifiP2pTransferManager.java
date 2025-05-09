package com.huawei.unitedevice.hwwifip2ptransfermgr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import androidx.core.view.PointerIconCompat;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.p2p.P2pReceiver;
import defpackage.bgl;
import defpackage.bin;
import defpackage.bku;
import defpackage.bky;
import defpackage.bli;
import defpackage.bll;
import defpackage.blq;
import defpackage.blt;
import defpackage.blv;
import defpackage.bmd;
import defpackage.bmi;
import defpackage.bmj;
import defpackage.iyv;
import defpackage.snq;
import defpackage.snt;
import defpackage.snu;
import defpackage.snz;
import defpackage.sol;
import defpackage.sox;
import defpackage.soy;
import defpackage.soz;
import defpackage.spa;
import defpackage.spb;
import defpackage.spc;
import defpackage.spe;
import defpackage.spg;
import defpackage.sph;
import defpackage.spn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.zip.CRC32;

/* loaded from: classes7.dex */
public class HwWifiP2pTransferManager implements DeviceStatusChangeCallback {
    private static volatile HwWifiP2pTransferManager c;
    private Context g;
    private String j;
    private ExtendHandler k;
    private Timer q;
    private TransferBleToWifiCallback u;
    private spc x;
    private int y;
    private static final Object b = new Object();
    private static final Object e = new Object();
    private CountDownLatch i = new CountDownLatch(1);
    private CountDownLatch h = new CountDownLatch(1);
    private CountDownLatch f = null;
    private spa v = new spa();
    private boolean l = false;
    private boolean n = false;

    /* renamed from: a, reason: collision with root package name */
    private int f10795a = 0;
    private volatile int s = 0;
    private volatile BlockingQueue<soz> m = new LinkedBlockingQueue();
    private SendCallback r = new d();
    private volatile LinkedHashMap<Integer, soz> d = new LinkedHashMap<>(16);
    private Map<Integer, List<IresponseCallback>> p = new HashMap(16);
    private P2pReceiver t = new P2pReceiver() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.2
        @Override // com.huawei.unitedevice.p2p.P2pReceiver
        public void onReceiveMessage(DeviceInfo deviceInfo, spn spnVar) {
            soz i = HwWifiP2pTransferManager.this.i();
            if (i == null) {
                byte[] b2 = HwWifiP2pTransferManager.this.b("onReceiveMessage", spnVar);
                if (b2 == null) {
                    return;
                }
                byte b3 = b2[2];
                LogUtil.c("HwWifiP2pTransferManager", "onReceiveMessage commandId is: ", Byte.valueOf(b3));
                if (b3 == 8) {
                    if (HwWifiP2pTransferManager.this.k != null) {
                        HwWifiP2pTransferManager.this.k.removeMessages(101);
                    }
                    HwWifiP2pTransferManager.this.an();
                }
                LogUtil.a("HwWifiP2pTransferManager", "wifiP2pFileInfo is null.");
                return;
            }
            soz sozVar = (soz) HwWifiP2pTransferManager.this.d.get(Integer.valueOf(i.o()));
            String identify = (sozVar == null || sozVar.e() == null) ? null : sozVar.e().getIdentify();
            String deviceMac = deviceInfo != null ? deviceInfo.getDeviceMac() : null;
            LogUtil.c("HwWifiP2pTransferManager", "fileInfo macAddress:", blt.b(identify), "uniteDevice desAddress:", deviceMac);
            if (identify != null && identify.equals(deviceMac)) {
                LogUtil.c("HwWifiP2pTransferManager", "fileInfo :", sozVar);
                HwWifiP2pTransferManager.this.c(530003, spnVar, deviceInfo);
            } else {
                LogUtil.a("HwWifiP2pTransferManager", "mac is not the same");
                HwWifiP2pTransferManager.this.c(530003, spnVar, (DeviceInfo) null);
            }
        }
    };
    private final BroadcastReceiver o = new BroadcastReceiver() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Object obj;
            if (intent == null) {
                LogUtil.a("HwWifiP2pTransferManager", "mDeviceStatusReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                LogUtil.a("HwWifiP2pTransferManager", "mDeviceStatusReceiver onReceive action is null");
                return;
            }
            LogUtil.c("HwWifiP2pTransferManager", "mDeviceStatusReceiver onReceive action :", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                try {
                    obj = intent.getParcelableExtra("deviceinfo");
                } catch (BadParcelableException unused) {
                    LogUtil.e("HwWifiP2pTransferManager", "fuzzy test exception, no care this.");
                    obj = null;
                }
                com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = obj instanceof com.huawei.health.devicemgr.business.entity.DeviceInfo ? (com.huawei.health.devicemgr.business.entity.DeviceInfo) obj : null;
                if (deviceInfo != null) {
                    ConnectState connectState = ConnectState.getConnectState(deviceInfo.getDeviceConnectState());
                    HwWifiP2pTransferManager.this.d(connectState);
                    if (connectState != null) {
                        HwWifiP2pTransferManager.this.a(connectState);
                    }
                }
            }
        }
    };

    public interface TransferBleToWifiCallback {
        void onResult(boolean z);
    }

    static class d implements SendCallback {
        private d() {
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendResult(int i) {
            LogUtil.c("HwWifiP2pTransferManager", "sendCommand errCode:", Integer.valueOf(i));
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendProgress(long j) {
            LogUtil.c("HwWifiP2pTransferManager", "count : ", Long.valueOf(j));
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onFileTransferReport(String str) {
            LogUtil.c("HwWifiP2pTransferManager", "DefaultSendCallback onFileTransferReport transferWay: ", str);
        }
    }

    private HwWifiP2pTransferManager(Context context) {
        this.g = context;
        snq.c().a(this.g);
        snq.c().registerDeviceStateListener(UUID.randomUUID().toString(), this);
        ai();
        ah();
        this.k = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.3
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message == null) {
                    return false;
                }
                LogUtil.a("HwWifiP2pTransferManager", "wifi handle : ", Integer.valueOf(message.what));
                int i = message.what;
                if (i == 100) {
                    HwWifiP2pTransferManager.this.al();
                    return true;
                }
                if (i == 101) {
                    HwWifiP2pTransferManager.this.ac();
                    return true;
                }
                if (i == 500) {
                    HwWifiP2pTransferManager.this.e();
                    return true;
                }
                LogUtil.a("HwWifiP2pTransferManager", "unknown what : ", Integer.valueOf(message.what));
                return false;
            }
        }, "HwWifiP2pTransferManager");
        LogUtil.c("HwWifiP2pTransferManager", "HwWifiP2pTransferMgr create.");
    }

    @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
    public void onConnectStatusChanged(DeviceInfo deviceInfo, int i, int i2) {
        LogUtil.c("HwWifiP2pTransferManager", "onConnectStatusChanged: ", Integer.valueOf(i));
    }

    private void ah() {
        LogUtil.c("HwWifiP2pTransferManager", "enter registerBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.e(), this.o, intentFilter, bin.d, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        iyv.c("WifiP2PTransfer", "Device replies 5.54.x reach timeout.");
        d(1016, "5.54.x timeout.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        CountDownLatch countDownLatch = this.i;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            this.i = null;
        }
        CountDownLatch countDownLatch2 = this.f;
        if (countDownLatch2 != null) {
            countDownLatch2.countDown();
            this.f = null;
        }
    }

    public void y() {
        ExtendHandler extendHandler = this.k;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(100, 20000L);
        }
    }

    public void r() {
        ExtendHandler extendHandler = this.k;
        if (extendHandler != null) {
            extendHandler.removeMessages(100);
        }
    }

    public static HwWifiP2pTransferManager d() {
        HwWifiP2pTransferManager hwWifiP2pTransferManager;
        synchronized (b) {
            if (c == null) {
                c = new HwWifiP2pTransferManager(BaseApplication.e());
            }
            hwWifiP2pTransferManager = c;
        }
        return hwWifiP2pTransferManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] b(String str, spn spnVar) {
        if (spnVar == null) {
            LogUtil.a("HwWifiP2pTransferManager", str, "P2pMessage is null");
            return null;
        }
        byte[] b2 = spnVar.b();
        if (b2 == null || b2.length <= 1) {
            LogUtil.e("HwWifiP2pTransferManager", str, "P2pMessage dataInfo illegal");
            return null;
        }
        if (b2.length <= 2) {
            LogUtil.c("HwWifiP2pTransferManager", "can not get commandId");
            return null;
        }
        blt.d("HwWifiP2pTransferManager", b2, str, "P2pMsg_dataInfos is: ");
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, spn spnVar, DeviceInfo deviceInfo) {
        LogUtil.c("HwWifiP2pTransferManager", "onReceiveDeviceCommand errorCode ", Integer.valueOf(i));
        byte[] b2 = b("onReceiveDeviceCommand", spnVar);
        if (b2 == null) {
        }
        byte b3 = b2[2];
        LogUtil.c("HwWifiP2pTransferManager", "onReceiveDeviceCommand commandId is: ", Byte.valueOf(b3));
        if (e(b3, b2)) {
            return;
        }
        switch (b3) {
            case 1:
            case 3:
                soy.b(b2);
                break;
            case 2:
                soy.d(b2);
                break;
            case 4:
                soy.e(b2);
                break;
            case 5:
                soy.a(b2);
                break;
            case 6:
                d(b2);
                break;
            case 7:
                d(b2, i());
                break;
            case 8:
                an();
                break;
            case 9:
                c(b2, deviceInfo);
                break;
            case 10:
                soy.c(b2);
                break;
            default:
                LogUtil.a("HwWifiP2pTransferManager", "unknown id : ", Byte.valueOf(b3));
                break;
        }
    }

    private boolean e(byte b2, byte[] bArr) {
        List<IresponseCallback> list;
        if (this.p.isEmpty() || (list = this.p.get(Integer.valueOf(b2))) == null || list.isEmpty()) {
            return false;
        }
        LogUtil.c("HwWifiP2pTransferManager", "adapterReturn : ", Byte.valueOf(b2));
        list.remove(0).onResponse(bArr);
        return true;
    }

    private WearEngineModule ad() {
        return WearEngineModule.WIFIP2P_TRANSFER_MODULE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnectState connectState) {
        LogUtil.c("HwWifiP2pTransferManager", "onDeviceConnectionChange connectState : ", connectState);
    }

    public void a(spn spnVar) {
        b(spnVar, this.r);
    }

    public byte[] e(spn spnVar, final int i) {
        final byte[][] bArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        List<IresponseCallback> list = this.p.get(Integer.valueOf(i));
        if (list == null) {
            list = new LinkedList<>();
            this.p.put(Integer.valueOf(i), list);
        }
        list.add(new IresponseCallback() { // from class: sot
            @Override // com.huawei.unitedevice.hwwifip2ptransfermgr.IresponseCallback
            public final void onResponse(Object obj) {
                HwWifiP2pTransferManager.this.d(bArr, i, countDownLatch, obj);
            }
        });
        b(spnVar, this.r);
        try {
            LogUtil.c("HwWifiP2pTransferManager", "await : ", Boolean.valueOf(countDownLatch.await(20000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.c("HwWifiP2pTransferManager", "send message time out : ", Integer.valueOf(i), " exception : ", ExceptionUtils.d(e2));
            countDownLatch.countDown();
        }
        return bArr[0];
    }

    public /* synthetic */ void d(byte[][] bArr, int i, CountDownLatch countDownLatch, Object obj) {
        if (obj instanceof byte[]) {
            bArr[0] = (byte[]) obj;
        }
        List<IresponseCallback> list = this.p.get(Integer.valueOf(i));
        if (list != null && list.size() > 0) {
            list.remove(0);
        } else {
            LogUtil.c("HwWifiP2pTransferManager", "no callback remove, please check : ", Integer.valueOf(i));
        }
        LogUtil.c("HwWifiP2pTransferManager", "response success, countDown");
        countDownLatch.countDown();
    }

    public int a() {
        LogUtil.c("HwWifiP2pTransferManager", "createServerSocket");
        spc d2 = spc.d();
        this.x = d2;
        return d2.e();
    }

    public void b() {
        spc spcVar = this.x;
        if (spcVar != null) {
            spcVar.c();
        } else {
            LogUtil.e("HwWifiP2pTransferManager", "mServerSocket is null. please check.");
        }
    }

    public void u() {
        this.i = new CountDownLatch(1);
        this.h = new CountDownLatch(1);
    }

    public boolean s() {
        try {
            CountDownLatch countDownLatch = this.i;
            if (countDownLatch != null && !countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                LogUtil.e("HwWifiP2pTransferManager", "getWifiP2pIsEnable is timeout");
                spb.e(BaseApplication.e()).b();
                return false;
            }
        } catch (InterruptedException e2) {
            LogUtil.e("HwWifiP2pTransferManager", "getWifiP2pIsEnable InterruptedException : ", bll.a(e2));
        }
        LogUtil.c("HwWifiP2pTransferManager", "getWifiP2pIsEnable mIsReady is: ", Boolean.valueOf(this.l));
        return this.l;
    }

    public void c(boolean z) {
        LogUtil.c("HwWifiP2pTransferManager", "setWifiP2pIsEnable isEnable: ", Boolean.valueOf(z));
        this.l = z;
        CountDownLatch countDownLatch = this.i;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    private void ak() {
        LogUtil.c("HwWifiP2pTransferManager", "startWifiP2pGo");
        ab();
        spb.e(this.g).d();
    }

    private void ab() {
        if (bky.g()) {
            Timer timer = this.q;
            if (timer != null) {
                timer.cancel();
                as();
            }
            Timer timer2 = new Timer("wifi_p2p");
            timer2.schedule(new TimerTask() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.5
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (TransferFileQueueManager.d().e("powerKit_check").size() == 0) {
                        HwWifiP2pTransferManager.this.as();
                    } else {
                        bmd.b("wifi-p2p-send-file", 512, "user-wifip2p");
                        bmd.d("wifi-p2p-send-file", 512, 600000L, "user-wifip2p");
                    }
                }
            }, 540000L, 540000L);
            this.q = timer2;
            bmd.d("wifi-p2p-send-file", 512, 600000L, "user-wifip2p");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        if (bky.g()) {
            Timer timer = this.q;
            if (timer != null) {
                LogUtil.c("HwWifiP2pTransferManager", "unApplyPowerKit");
                timer.cancel();
                this.q = null;
            }
            bmd.b("wifi-p2p-send-file", 512, "user-wifip2p");
        }
    }

    public void a(String str) {
        this.v.d(str);
    }

    public void c(int i) {
        this.v.c(i);
    }

    public void e(String str) {
        LogUtil.c("HwWifiP2pTransferManager", "setWifiP2pPassPhrase");
        this.v.e(str);
    }

    public void b(String str) {
        LogUtil.c("HwWifiP2pTransferManager", "setWifiP2pServerSocketIp serverSocketIp is: ", str);
        this.v.c(str);
        CountDownLatch countDownLatch = this.h;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public spa m() {
        try {
            CountDownLatch countDownLatch = this.h;
            if (countDownLatch != null) {
                LogUtil.c("HwWifiP2pTransferManager", "isWaitSuccess : ", Boolean.valueOf(countDownLatch.await(3000L, TimeUnit.MILLISECONDS)));
            }
        } catch (InterruptedException e2) {
            LogUtil.e("HwWifiP2pTransferManager", "getWifiP2pIsEnable InterruptedException : ", bll.a(e2));
        }
        return this.v;
    }

    public TransferBleToWifiCallback g() {
        return this.u;
    }

    public void d(TransferBleToWifiCallback transferBleToWifiCallback) {
        LogUtil.c("HwWifiP2pTransferManager", "setTransferBleToWifiCallback enter.");
        this.u = transferBleToWifiCallback;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c(java.lang.String r7, com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.TransferBleToWifiCallback r8) {
        /*
            r6 = this;
            r6.u = r8
            r8 = 1
            java.lang.String r0 = r6.c(r8, r7)
            soz r1 = r6.i()
            r2 = 0
            java.lang.String r3 = "HwWifiP2pTransferManager"
            if (r1 == 0) goto L2e
            int r1 = r1.o()
            byte[] r4 = defpackage.blq.a(r0)
            r5 = 31
            boolean r4 = defpackage.bky.c(r4, r5)
            r5 = -1
            if (r1 != r5) goto L2e
            if (r4 == 0) goto L2e
            java.lang.String r1 = "test isSkipQueryCapability set true"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.LogUtil.c(r3, r1)
            r1 = r8
            goto L2f
        L2e:
            r1 = r2
        L2f:
            r4 = 76
            boolean r0 = r6.c(r0, r4)
            if (r0 == 0) goto L54
            if (r1 != 0) goto L54
            soz r7 = r6.i()
            java.lang.String r0 = "startWifiP2pToTransfer isSupportExpandCapability true"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.c(r3, r0)
            if (r7 == 0) goto L52
            int r0 = r7.o()
            r6.y = r0
            defpackage.soy.d(r7)
            goto L81
        L52:
            r8 = r2
            goto L81
        L54:
            java.lang.String r0 = "startWifiP2pToTransfer startWifiP2pGo"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.LogUtil.c(r3, r0)
            r6.ak()
            r6.f(r7)
            boolean r7 = r6.s()
            if (r7 == 0) goto L6f
            spa r7 = r6.v
            defpackage.soy.b(r7)
            goto L81
        L6f:
            com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager r7 = d()
            r7.r()
            com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager r7 = d()
            r0 = 1029(0x405, float:1.442E-42)
            java.lang.String r1 = "normal watch create wifi go error"
            r7.d(r0, r1)
        L81:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.c(java.lang.String, com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager$TransferBleToWifiCallback):boolean");
    }

    private void f(String str) {
        Collection<UniteDevice> values = snq.c().getDeviceList().values();
        if (values.isEmpty()) {
            LogUtil.a("HwWifiP2pTransferManager", "try get device wrong, please check.");
            return;
        }
        LogUtil.c("HwWifiP2pTransferManager", "values size : ", Integer.valueOf(values.size()));
        Iterator<UniteDevice> it = values.iterator();
        while (it.hasNext() && !a(str, it.next().getDeviceInfo())) {
        }
    }

    private boolean a(String str, DeviceInfo deviceInfo) {
        if (str != null) {
            if (!str.equals(deviceInfo.getDeviceMac()) || !deviceInfo.isUsing() || deviceInfo.getDeviceConnectState() != 2 || bku.e(deviceInfo.getDeviceType())) {
                return false;
            }
            String deviceMac = deviceInfo.getDeviceMac();
            this.j = deviceMac;
            LogUtil.d("HwWifiP2pTransferManager", "macAddress is not null:", blt.b(deviceMac));
            return true;
        }
        if (!deviceInfo.isUsing() || deviceInfo.getDeviceConnectState() != 2 || bku.e(deviceInfo.getDeviceType())) {
            return false;
        }
        String deviceMac2 = deviceInfo.getDeviceMac();
        this.j = deviceMac2;
        LogUtil.d("HwWifiP2pTransferManager", "mCurrentAddress:", blt.b(deviceMac2));
        return true;
    }

    public boolean b(int i, UniteDevice uniteDevice) {
        LogUtil.c("HwWifiP2pTransferManager", "Build.VERSION.SDK_INT：", Integer.valueOf(Build.VERSION.SDK_INT));
        if (Build.VERSION.SDK_INT >= 29) {
            String identify = uniteDevice != null ? uniteDevice.getIdentify() : null;
            boolean a2 = a(i, c(false, identify));
            boolean c2 = c(c(true, identify), 76);
            LogUtil.c("HwWifiP2pTransferManager", "macAddress:", blt.b(identify), "isSupportCapability : ", Boolean.valueOf(a2), " isSupportExpandCapability:", Boolean.valueOf(c2));
            return a2 || c2;
        }
        iyv.c("WifiP2PTransfer", "Android version too low, not support wifip2p file transfer.");
        return false;
    }

    public boolean c(soz sozVar) {
        if (sozVar == null) {
            return false;
        }
        UniteDevice e2 = sozVar.e();
        return c(c(true, e2 != null ? e2.getIdentify() : null), 76);
    }

    public String c(boolean z, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Collection<UniteDevice> values = bgl.c().getDeviceList().values();
        if (values.isEmpty()) {
            LogUtil.c("HwWifiP2pTransferManager", "try get device wrong, please check.");
            return "";
        }
        LogUtil.c("HwWifiP2pTransferManager", "values size : ", Integer.valueOf(values.size()));
        Iterator<UniteDevice> it = values.iterator();
        String str2 = null;
        while (it.hasNext()) {
            str2 = c(str, it.next(), z);
            if (!TextUtils.isEmpty(str2)) {
                break;
            }
        }
        return str2;
    }

    private String c(String str, UniteDevice uniteDevice, boolean z) {
        if (uniteDevice == null) {
            return null;
        }
        DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
        if (str != null) {
            if (str.equals(deviceInfo.getDeviceMac()) && deviceInfo.isUsing() && deviceInfo.getDeviceConnectState() == 2 && !bku.e(deviceInfo.getDeviceType())) {
                String e2 = e(z, uniteDevice);
                LogUtil.c("HwWifiP2pTransferManager", "muti device isSupportWifiFile:", e2, "isExpandCapability:", Boolean.valueOf(z));
                return e2;
            }
        } else if (deviceInfo.isUsing() && deviceInfo.getDeviceConnectState() == 2 && !bku.e(deviceInfo.getDeviceType())) {
            String e3 = e(z, uniteDevice);
            LogUtil.c("HwWifiP2pTransferManager", "isSupportWifiFile:", e3, "isExpandCapability:", Boolean.valueOf(z));
            return e3;
        }
        return null;
    }

    private String e(boolean z, UniteDevice uniteDevice) {
        if (z) {
            return c(uniteDevice);
        }
        return uniteDevice.getCapability().getCapacity();
    }

    private String c(UniteDevice uniteDevice) {
        ExternalDeviceCapability capability = uniteDevice.getCapability();
        if (capability != null) {
            return capability.getCapacity();
        }
        return null;
    }

    private boolean c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HwWifiP2pTransferManager", "checkSupportCapability deviceInfo is null");
            return false;
        }
        boolean c2 = bli.c(blq.a(str), i);
        LogUtil.c("HwWifiP2pTransferManager", "checkSupportCapability isSupport:", Boolean.valueOf(c2));
        return c2;
    }

    private boolean a(int i, String str) {
        LogUtil.c("HwWifiP2pTransferManager", "file type : ", Integer.valueOf(i));
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Map<Integer, Integer> e2 = spg.e();
        LogUtil.c("HwWifiP2pTransferManager", "trustList.size() : ", Integer.valueOf(e2.size()));
        Integer num = e2.get(Integer.valueOf(i));
        if (num == null) {
            return false;
        }
        return bli.c(blq.a(str), num.intValue());
    }

    public void d(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        LogUtil.c("HwWifiP2pTransferManager", "deviceIdentify : ", blt.b(deviceIdentify), "mCurrentAddress : ", blt.b(this.j));
        if (!TextUtils.equals(deviceIdentify, this.j)) {
            LogUtil.a("HwWifiP2pTransferManager", "not current device disconnect.");
        } else {
            if (deviceInfo.getDeviceConnectState() == 3) {
                e();
                this.d.clear();
                d(1021, "device disconnect. may user close bt switch.");
                return;
            }
            LogUtil.c("HwWifiP2pTransferManager", "normal state, not deal.");
        }
    }

    public void c(sol solVar, WifiP2pTransferListener wifiP2pTransferListener, int i) {
        LogUtil.c("HwWifiP2pTransferManager", "setCurrentFileInfo");
        p();
        if (solVar == null) {
            return;
        }
        soz a2 = a(solVar, wifiP2pTransferListener, i);
        synchronized (e) {
            this.d.put(Integer.valueOf(solVar.u()), a2);
            LogUtil.c("HwWifiP2pTransferManager", "cache size : ", Integer.valueOf(this.d.size()));
        }
    }

    public soz a(sol solVar, WifiP2pTransferListener wifiP2pTransferListener, int i) {
        soz sozVar = new soz();
        if (solVar == null) {
            LogUtil.a("HwWifiP2pTransferManager", "createWifiTask param is null.");
            return sozVar;
        }
        sozVar.j(solVar.s());
        sozVar.e(solVar.m());
        sozVar.b(solVar.v());
        sozVar.c(solVar.u());
        sozVar.a(solVar.ap());
        sozVar.ekf_(solVar.ejT_());
        sozVar.f(solVar.ai());
        sozVar.i(solVar.w());
        sozVar.k(solVar.ar());
        LogUtil.c("HwWifiP2pTransferManager", "mCheckModel value:", Integer.valueOf(this.f10795a));
        if (solVar.i() != null) {
            LogUtil.c("HwWifiP2pTransferManager", "currentFileInfo.getDevice() is not null");
            sozVar.c(solVar.i());
        }
        if (this.f10795a == 3) {
            sozVar.d(solVar.ai());
        } else {
            sozVar.d(ejY_(solVar.ejT_(), solVar.s(), solVar.ap()));
        }
        sozVar.d(wifiP2pTransferListener);
        sozVar.a(i);
        sozVar.a(solVar.j());
        sozVar.i(solVar.ae());
        sozVar.c(solVar.g());
        sozVar.h(solVar.ah());
        sozVar.b(solVar.h());
        return sozVar;
    }

    public boolean d(int i) {
        soz remove;
        synchronized (e) {
            remove = this.d.remove(Integer.valueOf(i));
        }
        Object[] objArr = new Object[2];
        objArr[0] = "remove : ";
        objArr[1] = Boolean.valueOf(remove == null);
        LogUtil.c("HwWifiP2pTransferManager", objArr);
        return remove != null;
    }

    public void d(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.c("HwWifiP2pTransferManager", "onFailToLister code : ", Integer.valueOf(i), " msg : ", str);
        TransferBleToWifiCallback g = d().g();
        if (g != null) {
            LogUtil.c("HwWifiP2pTransferManager", "callback is not null. try switch wifi fail");
            g.onResult(false);
            new Timer("cancel null").schedule(new TimerTask() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    HwWifiP2pTransferManager.d().d((TransferBleToWifiCallback) null);
                }
            }, 3000L);
        } else {
            e(i, str);
            b(i, str);
        }
    }

    private void e(int i, String str) {
        soz i2 = i();
        if (i2 != null) {
            WifiP2pTransferListener k = i2.k();
            if (k != null) {
                k.onFail(i, str, i2.o());
            } else {
                LogUtil.a("HwWifiP2pTransferManager", "lister is null. please check.");
            }
            d(i2.o());
            return;
        }
        LogUtil.a("HwWifiP2pTransferManager", "fileInfo is null, please check add and remove.");
    }

    private void b(int i, String str) {
        if (i == 1021) {
            LogUtil.c("HwWifiP2pTransferManager", "bluetooth close, report all task.");
            while (i() != null) {
                e(i, str);
            }
        }
    }

    public void e(int i, String str, int i2) {
        soz sozVar;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.c("HwWifiP2pTransferManager", "onFailToLister code : ", Integer.valueOf(i), " msg : ", str, "fileType : ", Integer.valueOf(i2));
        synchronized (e) {
            sozVar = this.d.get(Integer.valueOf(i2));
        }
        if (sozVar != null) {
            WifiP2pTransferListener k = sozVar.k();
            if (k != null) {
                k.onFail(i, str, sozVar.o());
                d(sozVar.o());
                return;
            } else {
                LogUtil.a("HwWifiP2pTransferManager", "lister is null. please check.");
                return;
            }
        }
        LogUtil.a("HwWifiP2pTransferManager", "fileInfo is null, please check add and remove ", Integer.valueOf(i2));
    }

    public soz i() {
        soz value;
        synchronized (e) {
            value = this.d.isEmpty() ? null : this.d.entrySet().iterator().next().getValue();
        }
        return value;
    }

    public void d(String str) {
        LogUtil.c("HwWifiP2pTransferManager", "clearFileInfo enter : ", str);
        synchronized (e) {
            this.d.clear();
        }
    }

    public boolean b(int i) {
        synchronized (e) {
            return (this.d == null || this.d.get(Integer.valueOf(i)) == null) ? false : true;
        }
    }

    public soz h() {
        synchronized (e) {
            soz sozVar = null;
            if (this.d.isEmpty()) {
                return null;
            }
            Iterator<Map.Entry<Integer, soz>> it = this.d.entrySet().iterator();
            Map.Entry<Integer, soz> entry = null;
            while (it.hasNext()) {
                entry = it.next();
            }
            if (entry != null) {
                sozVar = entry.getValue();
            }
            return sozVar;
        }
    }

    public void w() {
        v();
    }

    public boolean l() {
        Object systemService = this.g.getApplicationContext().getSystemService("wifi");
        if (systemService instanceof WifiManager) {
            WifiManager wifiManager = (WifiManager) systemService;
            LogUtil.c("HwWifiP2pTransferManager", "isWifiEnable", Boolean.valueOf(wifiManager.isWifiEnabled()));
            return wifiManager.isWifiEnabled();
        }
        LogUtil.e("HwWifiP2pTransferManager", "isWifiEnable wifiManager is null");
        return false;
    }

    public int c(String str) {
        if (g(snu.e().getSwitchSetting(str, "auto_open_wlan_status"))) {
            return am();
        }
        return l() ? 4 : 1;
    }

    private boolean g(String str) {
        return bky.a() && !l() && "1".equals(str) && !o();
    }

    public int c(sol solVar) {
        if (solVar == null) {
            return 0;
        }
        boolean b2 = b(solVar);
        if (g(snu.e().getSwitchSetting(solVar.i().getIdentify(), "auto_open_wlan_status")) && b2) {
            return am();
        }
        return l() ? 4 : 1;
    }

    private static boolean b(sol solVar) {
        return "com.huawei.hms.dupdateengine".equals(solVar.ac()) && "6AA7E4A7995987A357D744C3A9E90FD0C990DA3295304CA985B8D7D2F7AD158D".equals(solVar.h());
    }

    private int am() {
        bmd.a("power_kit_open_wifi_module", 512, "user-ota-trans");
        ap();
        Object systemService = this.g.getApplicationContext().getSystemService("wifi");
        if (systemService instanceof WifiManager) {
            WifiManager wifiManager = (WifiManager) systemService;
            if (wifiManager.isWifiEnabled()) {
                LogUtil.c("HwWifiP2pTransferManager", "powerKit open wifi success.");
                return 5;
            }
            wifiManager.setWifiEnabled(true);
            ap();
            if (wifiManager.isWifiEnabled()) {
                return 6;
            }
            bmd.b("power_kit_open_wifi_module", 512, "user-ota-trans");
            return 3;
        }
        LogUtil.e("HwWifiP2pTransferManager", "isWifiEnable wifiManager is null");
        bmd.b("power_kit_open_wifi_module", 512, "user-ota-trans");
        return 2;
    }

    private void ap() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e2) {
            LogUtil.e("HwWifiP2pTransferManager", "wait fail : ", ExceptionUtils.d(e2));
        }
    }

    public boolean o() {
        try {
            Object systemService = BaseApplication.e().getSystemService("wifi");
            if (!(systemService instanceof WifiManager)) {
                return false;
            }
            WifiManager wifiManager = (WifiManager) systemService;
            int intValue = ((Integer) wifiManager.getClass().getDeclaredMethod("getWifiApState", new Class[0]).invoke(wifiManager, new Object[0])).intValue();
            int intValue2 = ((Integer) wifiManager.getClass().getDeclaredField("WIFI_AP_STATE_ENABLED").get(wifiManager)).intValue();
            Object[] objArr = new Object[2];
            objArr[0] = "isHotSpotOpen : ";
            objArr[1] = Boolean.valueOf(intValue == intValue2);
            LogUtil.c("HwWifiP2pTransferManager", objArr);
            return intValue == intValue2;
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e2) {
            LogUtil.e("HwWifiP2pTransferManager", ExceptionUtils.d(e2));
            return false;
        }
    }

    public void e(boolean z) {
        LogUtil.c("HwWifiP2pTransferManager", "setWifiP2pIsConnected: ", Boolean.valueOf(z));
        this.n = z;
    }

    public boolean k() {
        LogUtil.c("HwWifiP2pTransferManager", "getWifiP2pIsConnected: ", Boolean.valueOf(this.n));
        CountDownLatch countDownLatch = this.f;
        if (countDownLatch != null) {
            try {
                countDownLatch.await(ProfileExtendConstants.TIME_OUT, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e2) {
                LogUtil.e("HwWifiP2pTransferManager", "getWifiP2pIsConnected exception : ", bll.a(e2));
            }
        }
        return this.n;
    }

    public int n() {
        LogUtil.c("HwWifiP2pTransferManager", "isRunning: ", Integer.valueOf(this.s));
        return this.s;
    }

    public void v() {
        this.s = 2;
    }

    private void aj() {
        this.s = 5;
    }

    public void q() {
        this.s = 1;
        e();
    }

    public boolean f() {
        CountDownLatch countDownLatch = this.f;
        if (countDownLatch != null) {
            try {
                countDownLatch.await(ProfileExtendConstants.TIME_OUT, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e2) {
                LogUtil.e("HwWifiP2pTransferManager", "getSocketIsAvailable exception : ", bll.a(e2));
            }
        }
        spc spcVar = this.x;
        if (spcVar == null) {
            LogUtil.e("HwWifiP2pTransferManager", "getSocketIsAvailable wifiP2pTransferServerSocket is null");
            return false;
        }
        Socket a2 = spcVar.a();
        if (a2 == null) {
            LogUtil.e("HwWifiP2pTransferManager", "getSocketIsAvailable acceptedSocket is null");
            return false;
        }
        if (a2.isConnected() && !a2.isClosed()) {
            LogUtil.e("HwWifiP2pTransferManager", "socket is available");
            return true;
        }
        LogUtil.c("HwWifiP2pTransferManager", "socket is closed");
        return false;
    }

    public Context c() {
        return this.g;
    }

    private String ejY_(ParcelFileDescriptor parcelFileDescriptor, String str, long j) {
        FileInputStream fileInputStream;
        CRC32 crc32 = new CRC32();
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        r6 = null;
        String str2 = null;
        try {
            try {
                fileInputStream = ejZ_(parcelFileDescriptor, str, j);
                try {
                } catch (FileNotFoundException e2) {
                    e = e2;
                    LogUtil.e("HwWifiP2pTransferManager", "getFileCrc FileNotFoundException: ", bll.a(e));
                    LogUtil.c("HwWifiP2pTransferManager", "getFileCrc close stream");
                    blv.d(fileInputStream);
                    return str2;
                } catch (IOException e3) {
                    e = e3;
                    LogUtil.e("HwWifiP2pTransferManager", "getFileCrc IOException: ", bll.a(e));
                    LogUtil.c("HwWifiP2pTransferManager", "getFileCrc close stream");
                    blv.d(fileInputStream);
                    return str2;
                }
            } catch (Throwable th) {
                parcelFileDescriptor2 = parcelFileDescriptor;
                th = th;
                LogUtil.c("HwWifiP2pTransferManager", "getFileCrc close stream");
                blv.d(parcelFileDescriptor2);
                throw th;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            fileInputStream = null;
        } catch (IOException e5) {
            e = e5;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            LogUtil.c("HwWifiP2pTransferManager", "getFileCrc close stream");
            blv.d(parcelFileDescriptor2);
            throw th;
        }
        if (fileInputStream == null) {
            LogUtil.e("HwWifiP2pTransferManager", "getFileCrc fileInputStream is null");
            LogUtil.c("HwWifiP2pTransferManager", "getFileCrc close stream");
            blv.d(fileInputStream);
            return null;
        }
        byte[] bArr = new byte[8192];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                break;
            }
            crc32.update(bArr, 0, read);
        }
        str2 = Long.toHexString(crc32.getValue());
        LogUtil.c("HwWifiP2pTransferManager", "getFileCrc close stream");
        blv.d(fileInputStream);
        return str2;
    }

    public FileInputStream ejZ_(ParcelFileDescriptor parcelFileDescriptor, String str, long j) {
        if (parcelFileDescriptor != null) {
            LogUtil.a("HwWifiP2pTransferManager", "FileInputStream by ParcelFileDescriptor");
            return new FileInputStream(parcelFileDescriptor.getFileDescriptor());
        }
        LogUtil.a("HwWifiP2pTransferManager", "getFileInputStreamByUriId");
        return e(str, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [android.os.ParcelFileDescriptor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.FileInputStream e(java.lang.String r7, long r8) {
        /*
            r6 = this;
            boolean r0 = defpackage.sor.b(r7)
            java.lang.String r1 = "HwWifiP2pTransferManager"
            r2 = 0
            if (r0 != 0) goto L13
            java.lang.String r7 = "getFileInputStreamByUriId checkFilepath error"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            health.compact.a.LogUtil.a(r1, r7)
            return r2
        L13:
            r3 = -1
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            java.lang.String r3 = "getFileInputStreamByUriId IOException."
            r4 = 1
            r5 = 0
            if (r0 != 0) goto L2b
            java.lang.String r7 = defpackage.bli.d(r7)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            if (r7 == 0) goto L29
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            r8.<init>(r7)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            goto L71
        L29:
            r8 = r2
            goto L71
        L2b:
            android.net.Uri r7 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            android.net.Uri r7 = android.content.ContentUris.withAppendedId(r7, r8)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            android.content.Context r8 = r6.g     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            java.io.InputStream r8 = r8.openInputStream(r7)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            boolean r9 = r8 instanceof java.io.FileInputStream     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            if (r9 == 0) goto L4b
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            java.lang.String r9 = "transform type success "
            r7[r5] = r9     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            health.compact.a.LogUtil.e(r1, r7)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            java.io.FileInputStream r8 = (java.io.FileInputStream) r8     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            goto L71
        L4b:
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            java.lang.String r9 = "transform type fail "
            r8[r5] = r9     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            health.compact.a.LogUtil.e(r1, r8)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            android.content.Context r8 = r6.g     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            java.lang.String r9 = "r"
            android.os.ParcelFileDescriptor r7 = r8.openFileDescriptor(r7, r9)     // Catch: java.lang.Throwable -> L80 java.io.FileNotFoundException -> L83
            if (r7 == 0) goto L6f
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L6d java.lang.Throwable -> La4
            java.io.FileDescriptor r9 = r7.getFileDescriptor()     // Catch: java.io.FileNotFoundException -> L6d java.lang.Throwable -> La4
            r8.<init>(r9)     // Catch: java.io.FileNotFoundException -> L6d java.lang.Throwable -> La4
            r2 = r8
            goto L6f
        L6d:
            r8 = move-exception
            goto L86
        L6f:
            r8 = r2
            r2 = r7
        L71:
            if (r2 == 0) goto L7e
            r2.close()     // Catch: java.io.IOException -> L77
            goto L7e
        L77:
            java.lang.Object[] r7 = new java.lang.Object[]{r3}
            health.compact.a.LogUtil.e(r1, r7)
        L7e:
            r2 = r8
            goto La3
        L80:
            r7 = move-exception
            r8 = r7
            goto La6
        L83:
            r7 = move-exception
            r8 = r7
            r7 = r2
        L86:
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> La4
            java.lang.String r0 = "getFileInputStreamByUriId FileNotFoundException: "
            r9[r5] = r0     // Catch: java.lang.Throwable -> La4
            java.lang.String r8 = defpackage.bll.a(r8)     // Catch: java.lang.Throwable -> La4
            r9[r4] = r8     // Catch: java.lang.Throwable -> La4
            health.compact.a.LogUtil.e(r1, r9)     // Catch: java.lang.Throwable -> La4
            if (r7 == 0) goto La3
            r7.close()     // Catch: java.io.IOException -> L9c
            goto La3
        L9c:
            java.lang.Object[] r7 = new java.lang.Object[]{r3}
            health.compact.a.LogUtil.e(r1, r7)
        La3:
            return r2
        La4:
            r8 = move-exception
            r2 = r7
        La6:
            if (r2 == 0) goto Lb3
            r2.close()     // Catch: java.io.IOException -> Lac
            goto Lb3
        Lac:
            java.lang.Object[] r7 = new java.lang.Object[]{r3}
            health.compact.a.LogUtil.e(r1, r7)
        Lb3:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.e(java.lang.String, long):java.io.FileInputStream");
    }

    public void a(soz sozVar) {
        if (sozVar == null || sozVar.k() == null) {
            LogUtil.a("HwWifiP2pTransferManager", "startSendDataByWifi params is wrong. please check.");
            return;
        }
        if (this.s == 1) {
            LogUtil.c("HwWifiP2pTransferManager", "startSendDataByWifi wait file, task is running. add file : ", sozVar, "file path : ", sozVar.j(), " tag : ", Integer.valueOf(sozVar.y()));
            this.m.add(sozVar);
            return;
        }
        this.m.clear();
        this.s = 1;
        LogUtil.c("HwWifiP2pTransferManager", "startSendDataByWifi wait file. add file : ", sozVar, "file path : ", sozVar.j(), " tag : ", Integer.valueOf(sozVar.y()));
        this.m.add(sozVar);
        new sox().start();
    }

    public BlockingQueue<soz> j() {
        return this.m;
    }

    public void x() {
        if (this.s != 0) {
            soz sozVar = new soz();
            sozVar.h(1);
            LogUtil.a("HwWifiP2pTransferManager", "add stop task : ", sozVar);
            this.m.clear();
            this.m.add(sozVar);
        } else {
            LogUtil.a("HwWifiP2pTransferManager", "wifi task not start.");
        }
        this.s = 0;
    }

    public void e() {
        LogUtil.a("HwWifiP2pTransferManager", "close wifi enter.");
        as();
        this.f = new CountDownLatch(1);
        this.p.clear();
        ExtendHandler extendHandler = this.k;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(101, 20000L);
        }
        this.u = null;
        this.f10795a = 0;
        this.s = 3;
        spc.d().b();
        spb.e(this.g).a();
        x();
        soy.b(1);
    }

    private void d(byte[] bArr, soz sozVar) {
        if (sozVar == null) {
            LogUtil.a("HwWifiP2pTransferManager", "notifySuccess file info is null,please check.");
            return;
        }
        WifiP2pTransferListener k = sozVar.k();
        if (k == null) {
            LogUtil.a("HwWifiP2pTransferManager", "notifySuccess callback is null, pelaes check.");
            return;
        }
        if (bArr == null) {
            LogUtil.e("HwWifiP2pTransferManager", "notifySuccess param is null");
            k.onFail(1026, "5.44.7 data is null", sozVar.o());
            return;
        }
        bmj e2 = spe.e(bArr);
        if (e2 == null) {
            return;
        }
        int i = -1;
        int i2 = 0;
        for (bmi bmiVar : e2.b()) {
            int e3 = bli.e(bmiVar.e());
            if (e3 == 1) {
                i2 = bli.e(bmiVar.c());
            } else if (e3 == 127) {
                i = bli.e(bmiVar.c());
            } else {
                LogUtil.a("HwWifiP2pTransferManager", "unknown tag : ", Integer.valueOf(bli.e(bmiVar.e())));
            }
        }
        d(sozVar, k, i, i2);
    }

    private void d(soz sozVar, WifiP2pTransferListener wifiP2pTransferListener, int i, int i2) {
        LogUtil.c("HwWifiP2pTransferManager", "5.54.7 code : ", Integer.valueOf(i), " file id : ", Integer.valueOf(i2));
        LogUtil.c("HwWifiP2pTransferManager", "fileCrc : " + sozVar.c() + " resultCrc : " + d().ejY_(sozVar.eke_(), sozVar.j(), sozVar.x()));
        snz.a().c();
        if (b(sozVar)) {
            return;
        }
        ao();
        if (i == 100000) {
            wifiP2pTransferListener.onSuccess(1, "5.54.7 success.", sozVar.o());
        } else if (i == 140008) {
            ae();
            wifiP2pTransferListener.onSuccess(140008, "5.54.7 success.", sozVar.o());
        } else {
            wifiP2pTransferListener.onFail(140010, "5.54.7 translate fail. change bt", sozVar.o());
        }
    }

    private boolean b(soz sozVar) {
        sol c2 = TransferFileQueueManager.d().c("100%wifiSendCancel");
        if (c2 == null || c2.d() == null || c2.u() != sozVar.o()) {
            LogUtil.c("HwWifiP2pTransferManager", "no cancel callback");
            return false;
        }
        LogUtil.c("HwWifiP2pTransferManager", "100% wifi send cancel enter.");
        sozVar.k().onFail(1014, "user stop wifi", sozVar.o());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        LogUtil.c("HwWifiP2pTransferManager", "get 5.54.8");
        this.s = 0;
        CountDownLatch countDownLatch = this.f;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null) {
            LogUtil.e("HwWifiP2pTransferManager", "fileSupportP2pStatus param is null");
            d().d(1021, "5.54.9 data null");
            return;
        }
        bmj e2 = spe.e(bArr);
        if (e2 == null) {
            return;
        }
        int i = 0;
        for (bmi bmiVar : e2.b()) {
            int e3 = bli.e(bmiVar.e());
            if (e3 == 2) {
                i = bli.e(bmiVar.c());
            } else if (e3 == 8) {
                this.f10795a = bli.e(bmiVar.c());
            } else {
                LogUtil.a("HwWifiP2pTransferManager", "unknown tag : ", Integer.valueOf(bli.e(bmiVar.e())));
            }
        }
        LogUtil.c("HwWifiP2pTransferManager", "5.54.9 supportType:", Integer.valueOf(i), "mCheckModel:", Integer.valueOf(this.f10795a));
        d(deviceInfo, i);
    }

    private void d(DeviceInfo deviceInfo, int i) {
        if (i == 1) {
            if (this.f10795a == 3) {
                int i2 = this.y;
                synchronized (e) {
                    soz sozVar = this.d.get(Integer.valueOf(i2));
                    if (sozVar != null && !TextUtils.isEmpty(sozVar.q())) {
                        sozVar.d(sozVar.q());
                        this.d.put(Integer.valueOf(i2), sozVar);
                    } else {
                        LogUtil.a("HwWifiP2pTransferManager", "shaValue is empty");
                    }
                }
            }
            ak();
            String deviceMac = deviceInfo != null ? deviceInfo.getDeviceMac() : null;
            LogUtil.c("HwWifiP2pTransferManager", "fileSupportP2pStatus macAddress:", blt.b(deviceMac));
            f(deviceMac);
            if (s()) {
                soy.b(this.v);
                return;
            } else {
                d().r();
                d().d(1029, "create wifi go error");
                return;
            }
        }
        d().d(PointerIconCompat.TYPE_GRAB, "5.54.9 error");
    }

    private void d(byte[] bArr) {
        if (bArr == null) {
            LogUtil.e("HwWifiP2pTransferManager", "cancelWifiSuccess param is null");
            d().d(1028, "5.54.6 data null");
            return;
        }
        bmj e2 = spe.e(bArr);
        if (e2 == null) {
            return;
        }
        int i = -1;
        for (bmi bmiVar : e2.b()) {
            if (bli.e(bmiVar.e()) == 127) {
                i = bli.e(bmiVar.c());
            } else {
                LogUtil.a("HwWifiP2pTransferManager", "unknown tag : ", Integer.valueOf(bli.e(bmiVar.e())));
            }
        }
        LogUtil.c("HwWifiP2pTransferManager", "5.54.6 code : ", Integer.valueOf(i));
        if (i == 100000) {
            d().d(20003, "5.54.6 cancel success.");
        } else {
            d().d(20004, "5.44.6 cancel fail");
        }
    }

    public boolean a(int i) {
        List<Integer> d2 = spg.d();
        LogUtil.c("HwWifiP2pTransferManager", "wifiPairErrorCodeList.size: ", Integer.valueOf(d2.size()));
        return d2.contains(Integer.valueOf(i));
    }

    public boolean e(int i) {
        List<Integer> a2 = spg.a();
        LogUtil.c("HwWifiP2pTransferManager", "wifiPairErrorCodeList.size: ", Integer.valueOf(a2.size()));
        return a2.contains(Integer.valueOf(i));
    }

    private void ao() {
        boolean z = this.d.size() >= 2;
        ExtendHandler extendHandler = this.k;
        if (!z && extendHandler != null) {
            LogUtil.c("HwWifiP2pTransferManager", "ready close wifi.");
            extendHandler.sendEmptyMessage(500, PreConnectManager.CONNECT_INTERNAL);
        } else {
            LogUtil.c("HwWifiP2pTransferManager", "multi task, can not close wifi.");
        }
    }

    private void ae() {
        LogUtil.c("HwWifiP2pTransferManager", "repeatFile reset wifi tag, stop wifi send.");
        aj();
    }

    public void p() {
        LogUtil.c("HwWifiP2pTransferManager", "remove wifi close");
        ExtendHandler extendHandler = this.k;
        if (extendHandler != null) {
            extendHandler.removeMessages(500);
        } else {
            LogUtil.a("HwWifiP2pTransferManager", "removeWifiCloseTask fail.");
        }
    }

    public void z() {
        synchronized (e) {
            LogUtil.c("HwWifiP2pTransferManager", "wait task process.");
            LinkedHashMap<Integer, soz> linkedHashMap = this.d;
            if (linkedHashMap.size() == 1) {
                LogUtil.a("HwWifiP2pTransferManager", "no wait task.");
                return;
            }
            boolean z = true;
            for (Map.Entry<Integer, soz> entry : linkedHashMap.entrySet()) {
                if (z) {
                    z = false;
                } else {
                    soz value = entry.getValue();
                    if (value == null) {
                        LogUtil.a("HwWifiP2pTransferManager", "task is null, please check.");
                    } else {
                        WifiP2pTransferListener k = value.k();
                        LogUtil.c("HwWifiP2pTransferManager", "wait wifi task process : ", Integer.valueOf(value.o()));
                        k.onProcess(0, value.o());
                    }
                }
            }
        }
    }

    private void ai() {
        snt sntVar = new snt();
        sntVar.e(ad());
        sntVar.i(af());
        sntVar.f(ag());
        snq.c().registerReceiver(this.g, sntVar, this.t, new SendCallback() { // from class: com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.8
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.c("HwWifiP2pTransferManager", "registerReceiverToEngine errCode:", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.c("HwWifiP2pTransferManager", "registerReceiverToEngine onFileTransferReport transferWay: ", str);
            }
        });
    }

    protected void b(spn spnVar, SendCallback sendCallback) {
        snt sntVar = new snt();
        sntVar.e(ad());
        sntVar.i(af());
        sntVar.f(ag());
        sntVar.e(spnVar);
        snq.c().p2pSendForWearEngine(this.g, sph.d("HwWifiP2pTransferManager"), sntVar, sendCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ConnectState connectState) {
        if (connectState == ConnectState.CONNECTED) {
            ai();
        }
        a(connectState);
    }

    public void t() {
        if (this.g != null && this.t != null) {
            snt sntVar = new snt();
            sntVar.e(ad());
            sntVar.i(af());
            snq.c().unregisterReceiver(this.g, this.t, sntVar);
        }
        aa();
        snq.c().e(ad());
    }

    private static void aa() {
        synchronized (b) {
            c = null;
        }
    }

    public void f(int i) {
        LogUtil.c("HwWifiP2pTransferManager", "tryResetWifi : ", Integer.valueOf(i));
        if (i == 5) {
            bmd.b("power_kit_open_wifi_module", 512, "user-ota-trans");
        }
        if (i == 6) {
            bmd.b("power_kit_open_wifi_module", 512, "user-ota-trans");
            Object systemService = BaseApplication.e().getSystemService("wifi");
            if (systemService instanceof WifiManager) {
                ((WifiManager) systemService).setWifiEnabled(false);
            }
        }
    }

    private String af() {
        return "in.huawei.AppWifi";
    }

    private String ag() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
