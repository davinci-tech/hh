package com.huawei.devicesdk.hichain;

import android.text.TextUtils;
import com.huawei.common.Constant;
import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import com.huawei.haf.application.BaseApplication;
import com.huawei.security.deviceauth.HwDevAuthConnectionCallback;
import com.huawei.security.deviceauth.HwDeviceAuthManager;
import defpackage.biw;
import defpackage.bjc;
import defpackage.blt;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class HiChainAuthManager {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private boolean f1944a;
    private ConcurrentHashMap<String, HiChainClient> b;
    private HwDeviceAuthManager c;
    private CountDownLatch d;

    static class HiChainServiceConnection implements HwDevAuthConnectionCallback {
        private HiChainServiceConnection() {
        }

        public void onServiceDisconnected() {
            LogUtil.c("HiChainAuthManager", "onServiceDisconnected");
            HiChainAuthManager.d().d(false);
        }

        public void onServiceConnected() {
            LogUtil.c("HiChainAuthManager", Constant.SERVICE_CONNECT_MESSAGE);
            HiChainAuthManager.d().d(true);
            HiChainAuthManager.d().f();
        }
    }

    private HiChainAuthManager() {
        this.f1944a = false;
        this.b = new ConcurrentHashMap<>(16);
    }

    public static HiChainAuthManager d() {
        return b.c;
    }

    private boolean g() {
        return this.f1944a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        this.f1944a = z;
    }

    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    public boolean e() {
        LogUtil.c("HiChainAuthManager", "isAllHaveResult() start.");
        if (this.b.isEmpty()) {
            return true;
        }
        for (HiChainClient hiChainClient : this.b.values()) {
            if (hiChainClient != null && !hiChainClient.b()) {
                return false;
            }
        }
        return true;
    }

    public void a() {
        LogUtil.c("HiChainAuthManager", "disHiChainAuth()");
        if (e()) {
            LogUtil.c("HiChainAuthManager", "isAllHaveResult(): ", Boolean.valueOf(e()));
            j();
        }
    }

    private void b() {
        LogUtil.c("HiChainAuthManager", "start connectDeviceAuthService");
        this.c.connectDeviceAuthService();
    }

    private void j() {
        LogUtil.c("HiChainAuthManager", "start disconnectDeviceAuthService");
        synchronized (e) {
            HwDeviceAuthManager hwDeviceAuthManager = this.c;
            if (hwDeviceAuthManager != null) {
                hwDeviceAuthManager.disconnectDeviceAuthService();
            }
        }
    }

    public static HandshakeCommandBase b(String str, biw biwVar) {
        LogUtil.c("HiChainAuthManager", "initHiChain start ");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HiChainAuthManager", "initHiChain: deviceIdentify is null");
            return null;
        }
        HiChainAuthManager d = d();
        d.d(str);
        if (!d.c()) {
            ReleaseLogUtil.a("DEVMGR_HiChainAuthManager", "initHiChain: connect hiChain failed");
            d.d(str);
            return null;
        }
        HandshakeCommandBase c = d.c(str, biwVar);
        if (c == null) {
            LogUtil.c("HiChainAuthManager", "nextCommand use to finish auth");
            d.d(str);
        }
        return c;
    }

    public void d(String str) {
        HiChainClient hiChainClient;
        LogUtil.c("HiChainAuthManager", "disConnect() start.");
        if (!this.b.containsKey(str) || (hiChainClient = this.b.get(str)) == null || hiChainClient.b()) {
            return;
        }
        LogUtil.c("HiChainAuthManager", "HiChain组件操作没有结束.");
        hiChainClient.i();
    }

    public byte[] e(String str) {
        HiChainClient hiChainClient = this.b.get(str);
        if (hiChainClient == null) {
            LogUtil.e("HiChainAuthManager", "deviceIdentify ", blt.b(str), " have not open hichain");
            return null;
        }
        return hiChainClient.e();
    }

    private boolean c() {
        synchronized (e) {
            h();
            if (this.c == null) {
                LogUtil.c("HiChainAuthManager", "HiChainAuthManager create");
                this.c = HwDeviceAuthManager.getInstance(BaseApplication.e(), new HiChainServiceConnection());
                i();
                return g();
            }
            LogUtil.c("HiChainAuthManager", "Check all processes are complete, then reconnect. isConnectFlag: ", Boolean.valueOf(g()));
            if (e() && !g()) {
                b();
                i();
                return g();
            }
            LogUtil.c("HiChainAuthManager", "HiChainService is connected");
            return true;
        }
    }

    private void h() {
        this.d = new CountDownLatch(1);
        LogUtil.c("HiChainAuthManager", "initLock success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.d == null) {
            LogUtil.e("HiChainAuthManager", "mConnectLatch is null, releaseLock fail.");
            return;
        }
        LogUtil.c("HiChainAuthManager", "releaseLock: start");
        this.d.countDown();
        this.d = null;
    }

    private HandshakeCommandBase c(String str, biw biwVar) {
        if (this.b.containsKey(str)) {
            return this.b.get(str).d(biwVar);
        }
        HiChainClient hiChainClient = new HiChainClient(str, this.c);
        this.b.put(str, hiChainClient);
        return hiChainClient.a(biwVar);
    }

    private void i() {
        ReleaseLogUtil.b("DEVMGR_HiChainAuthManager", "waitConnect: start");
        if (this.d == null) {
            LogUtil.c("HiChainAuthManager", "mConnectLatch is invalid.");
            return;
        }
        try {
            if (!r0.await(2L, TimeUnit.SECONDS)) {
                ReleaseLogUtil.a("DEVMGR_HiChainAuthManager", "connect deviceAuthService timeout");
                d(false);
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChainAuthManager", "waitConnect: InterruptedException");
            d(false);
        }
        LogUtil.c("HiChainAuthManager", "waitConnect: end");
    }

    public bjc a(String str, byte[] bArr) {
        bjc bjcVar = new bjc(false);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HiChainAuthManager", "transmitDataToClient: deviceIdentify is null");
            return bjcVar;
        }
        HiChainClient hiChainClient = this.b.get(str);
        if (hiChainClient == null) {
            LogUtil.a("HiChainAuthManager", "transmitDataToClient: hiChainClient is null");
            return bjcVar;
        }
        bjc d = hiChainClient.d(bArr);
        if ((!d.b() || hiChainClient.h()) && !hiChainClient.b()) {
            hiChainClient.i();
        }
        return d;
    }

    public HandshakeCommandBase b(String str) {
        HiChainClient hiChainClient = this.b.get(str);
        if (hiChainClient == null || !g()) {
            return null;
        }
        return hiChainClient.d();
    }

    public boolean i(String str) {
        HiChainClient hiChainClient = this.b.get(str);
        if (hiChainClient == null) {
            return false;
        }
        return hiChainClient.a();
    }

    public void b(String str, boolean z) {
        HiChainClient hiChainClient = this.b.get(str);
        if (hiChainClient == null) {
            return;
        }
        hiChainClient.e(z);
    }

    public void a(String str) {
        if (this.b.containsKey(str)) {
            this.b.remove(str);
        }
    }

    static class b {
        private static HiChainAuthManager c = new HiChainAuthManager();
    }
}
