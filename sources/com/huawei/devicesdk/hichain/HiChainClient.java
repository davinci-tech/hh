package com.huawei.devicesdk.hichain;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import com.huawei.security.deviceauth.ConfirmParams;
import com.huawei.security.deviceauth.HwDevAuthCallback;
import com.huawei.security.deviceauth.HwDeviceAuthManager;
import com.huawei.security.deviceauth.OperationCode;
import com.huawei.security.deviceauth.OperationParameter;
import com.huawei.security.deviceauth.UserInfo;
import defpackage.bhb;
import defpackage.bhh;
import defpackage.bhi;
import defpackage.bhk;
import defpackage.biw;
import defpackage.bjc;
import defpackage.bjn;
import defpackage.blt;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class HiChainClient {

    /* renamed from: a, reason: collision with root package name */
    private OperationParameter f1945a;
    private UserInfo b;
    private String c;
    private byte[] d;
    private CountDownLatch e;
    private HwDeviceAuthManager g;
    private HandshakeCommandBase k;
    private biw m;
    private int n;
    private boolean h = false;
    private boolean i = true;
    private boolean j = false;
    private boolean f = false;

    public HiChainClient(String str, HwDeviceAuthManager hwDeviceAuthManager) {
        this.c = str;
        this.g = hwDeviceAuthManager;
        OperationParameter operationParameter = new OperationParameter(m());
        this.f1945a = operationParameter;
        operationParameter.setSelfId(e(str));
        this.f1945a.setSelfType(0);
        this.f1945a.setServiceType("HwSmartWatch");
        this.f1945a.setCallbackHandler(new HiChainCallbackHandler());
        UserInfo userInfo = new UserInfo();
        this.b = userInfo;
        userInfo.setAuthId(e(str));
        this.b.setServiceType("HwSmartWatch");
        this.b.setUserType(0);
    }

    public boolean a() {
        return this.h;
    }

    public void e(boolean z) {
        this.h = z;
    }

    public boolean b() {
        return this.j;
    }

    private void b(boolean z) {
        this.j = z;
    }

    private void d(boolean z) {
        this.i = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        return this.i;
    }

    public byte[] e() {
        return this.d;
    }

    public void a(byte[] bArr) {
        this.d = bArr;
    }

    public boolean h() {
        return this.f;
    }

    class HiChainCallbackHandler implements HwDevAuthCallback {
        private HiChainCallbackHandler() {
        }

        public void onOperationFinished(String str, OperationCode operationCode, int i, byte[] bArr) {
            ReleaseLogUtil.b("DEVMGR_HiChainClient", "onOperationFinished sessionId: ", blt.a(str), " operationCode: " + operationCode, " result: ", Integer.valueOf(i));
            if (operationCode == OperationCode.BIND) {
                if (i == 0) {
                    HiChainClient.this.e(bArr);
                    HiChainClient.this.c();
                    return;
                } else {
                    ReleaseLogUtil.b("DEVMGR_HiChainClient", "bind failed");
                    HiChainClient.this.j();
                    return;
                }
            }
            if (operationCode != OperationCode.AUTHENTICATE) {
                LogUtil.c("HiChainClient", "Unknown operation. ");
                return;
            }
            if (i != 0) {
                if (HiChainClient.this.l()) {
                    if (HiChainClient.this.n >= 2) {
                        HiChainClient.this.k = null;
                        HiChainClient.this.q();
                        return;
                    } else {
                        LogUtil.c("HiChainClient", "onOperationFinished: first auth failed");
                        return;
                    }
                }
                LogUtil.c("HiChainClient", "second auth failed");
                HiChainClient.this.j();
                return;
            }
            ReleaseLogUtil.b("DEVMGR_HiChainClient", "auth success, start 5.1.7");
            HiChainClient.this.f = true;
            HiChainClient.this.k = new bhk(false);
            HiChainClient.this.i();
            HiChainClient.this.q();
        }

        public void onSessionKeyReturned(String str, byte[] bArr) {
            LogUtil.c("HiChainClient", "onSessionKeyReturned", blt.a(str));
            if (bArr == null || bArr.length == 0) {
                LogUtil.e("HiChainClient", "onSessionKeyReturned sessionKey is empty");
            }
            HiChainClient.this.a(bArr);
        }

        public boolean onDataTransmit(String str, byte[] bArr) {
            LogUtil.c("HiChainClient", "onDataTransmit sessionId start,sessionId: ", blt.a(str));
            HiChainClient.this.c(bArr);
            return true;
        }

        public ConfirmParams onReceiveRequest(String str, OperationCode operationCode) {
            LogUtil.c("HiChainClient", "onReceiveRequest sessionId： ", blt.a(str), " operationCode " + operationCode);
            ConfirmParams confirmParams = new ConfirmParams();
            confirmParams.setConfirmation(-2147483642);
            if (operationCode == OperationCode.BIND || operationCode == OperationCode.AUTH_KEY_AGREEMENT) {
                confirmParams.setPin(bhh.e(HiChainClient.this.c));
            }
            confirmParams.setKeyLength(32);
            return confirmParams;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(byte[] bArr) {
        ReleaseLogUtil.b("DEVMGR_HiChainClient", "transmit data， phone to device");
        this.k = new bhi(bArr, (byte) 0, 0L);
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.c("HiChainClient", "hichain failed");
        i();
        this.k = null;
        q();
    }

    private String m() {
        return String.valueOf(System.currentTimeMillis());
    }

    private byte[] e(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.e("HiChainClient", "string to byte UnsupportedEncodingException");
            return new byte[0];
        }
    }

    public HandshakeCommandBase a(biw biwVar) {
        this.m = biwVar;
        e(true);
        f();
        return this.k;
    }

    private void f() {
        if (!o()) {
            n();
        }
        r();
        this.n = 0;
        c();
    }

    public void c() {
        LogUtil.c("HiChainClient", "authenticate peer .mClientParams , start");
        this.f1945a.setSessionId(m());
        ReleaseLogUtil.b("DEVMGR_HiChainClient", "start hiChain auth");
        k();
        int authenticatePeer = this.g.authenticatePeer(this.f1945a, (String) null, 32);
        if (authenticatePeer == -2147483642) {
            LogUtil.c("HiChainClient", "authenticate peer success::", Integer.valueOf(authenticatePeer));
            if (l()) {
                LogUtil.c("HiChainClient", "first hiChain auth, wait");
                s();
                return;
            }
            return;
        }
        ReleaseLogUtil.c("DEVMGR_HiChainClient", "authenticate peer failed.error code:", Integer.valueOf(authenticatePeer));
        j();
    }

    private void k() {
        if (this.e == null) {
            this.e = new CountDownLatch(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogUtil.a("HiChainClient", "releaseLock: start");
        if (this.e == null) {
            LogUtil.a("HiChainClient", "releaseLock: mAsyLatch is null");
            return;
        }
        LogUtil.a("HiChainClient", "releaseLock: countdown");
        this.e.countDown();
        this.e = null;
    }

    public HandshakeCommandBase d(biw biwVar) {
        this.m = biwVar;
        e(true);
        b(false);
        f();
        return this.k;
    }

    public void i() {
        LogUtil.c("HiChainClient", "data recovery");
        d(true);
        bhh.d(this.c);
        bjn.a(this.c, "");
        b(true);
        HiChainAuthManager.d().a();
    }

    private void n() {
        LogUtil.c("HiChainClient", "registerClient result:", Integer.valueOf(this.g.registerNewUser(this.b, 0, (String) null, (HwDevAuthCallback) null)));
    }

    private boolean o() {
        return this.g.isRegistered(this.b);
    }

    private void s() {
        ReleaseLogUtil.b("DEVMGR_HiChainClient", "waitHiChain: start");
        if (this.e == null) {
            LogUtil.c("HiChainClient", "mAsyncLatch is invalid.");
            return;
        }
        try {
            if (!r0.await(10L, TimeUnit.SECONDS)) {
                ReleaseLogUtil.a("DEVMGR_HiChainClient", "await timeout, hiChain failed");
                this.k = null;
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChainClient", "waitHiChain: InterruptedException");
            this.k = null;
        }
        LogUtil.c("HiChainClient", "waitHiChain: end");
    }

    public bjc d(byte[] bArr) {
        LogUtil.c("HiChainClient", "transmit data, device to phone");
        bjc bjcVar = new bjc(false);
        if (b(bArr)) {
            ReleaseLogUtil.a("DEVMGR_HiChainClient", "string to dealAuthenticateFailure");
            HandshakeCommandBase g = g();
            if (g != null) {
                bjcVar.c(true);
                bjcVar.c(g);
            }
            return bjcVar;
        }
        k();
        this.g.processReceivedData(this.f1945a, bArr);
        this.n++;
        s();
        HandshakeCommandBase handshakeCommandBase = this.k;
        if (handshakeCommandBase != null || (this.n >= 2 && handshakeCommandBase == null)) {
            bjcVar.c(true);
        }
        bjcVar.c(this.k);
        return bjcVar;
    }

    private boolean b(byte[] bArr) {
        return this.i && (bArr == null || bArr.length == 0);
    }

    private HandshakeCommandBase g() {
        ReleaseLogUtil.b("DEVMGR_HiChainClient", "UniteDevice first Authenticate failure,go to bindPeer");
        d(false);
        LogUtil.c("HiChainClient", "dealFirstAuthFailure-get pinCode from 5.1.44");
        return new bhb(this.m);
    }

    public HandshakeCommandBase d() {
        LogUtil.c("HiChainClient", "bindPeer peer.mClientParams");
        this.f1945a.setSessionId(m());
        ReleaseLogUtil.b("DEVMGR_HiChainClient", "start hiChain bind");
        k();
        int bindPeer = this.g.bindPeer(this.f1945a, bhh.e(this.c), 32);
        if (bindPeer == -2147483642) {
            LogUtil.c("HiChainClient", "client bind peer success.", Integer.valueOf(bindPeer));
            s();
        } else {
            ReleaseLogUtil.c("DEVMGR_HiChainClient", "client bind peer failed.", Integer.valueOf(bindPeer));
            j();
        }
        return this.k;
    }

    private void r() {
        try {
            LogUtil.c("HiChainClient", "registerGroup end result: ", this.g.getClass().getMethod("registerGroup", String.class, UserInfo.class, Integer.TYPE).invoke(this.g, "HwSmartWatchGroup", this.b, -1));
        } catch (IllegalAccessException unused) {
            LogUtil.e("HiChainClient", "registerGroup IllegalAccessException exception.");
        } catch (IllegalArgumentException unused2) {
            LogUtil.e("HiChainClient", "registerGroup IllegalArgumentException exception.");
        } catch (NoSuchMethodException unused3) {
            LogUtil.e("HiChainClient", "registerGroup NoSuchMethodException exception.");
        } catch (SecurityException unused4) {
            LogUtil.e("HiChainClient", "registerGroup SecurityException exception.");
        } catch (InvocationTargetException unused5) {
            LogUtil.e("HiChainClient", "registerGroup InvocationTargetException exception.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.e("HiChainClient", "UnsupportedEncodingException");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HiChainClient", "addTrustedDevice peerAuthId is null");
            return;
        }
        if (TextUtils.isEmpty(bjn.a(this.c))) {
            LogUtil.a("HiChainClient", "addTrustedDevice peerConnDeviceId is null");
            return;
        }
        try {
            LogUtil.c("HiChainClient", "addTrustedDevice result", this.g.getClass().getMethod("addTrustedDevice", String.class, String.class, String.class).invoke(this.g, "HwSmartWatchGroup", bjn.a(this.c), str));
        } catch (IllegalAccessException unused2) {
            LogUtil.e("HiChainClient", "addTrustedDevice IllegalAccessException exception.");
        } catch (IllegalArgumentException unused3) {
            LogUtil.e("HiChainClient", "addTrustedDevice IllegalArgumentException exception.");
        } catch (NoSuchMethodException unused4) {
            LogUtil.e("HiChainClient", "addTrustedDevice NoSuchMethodException exception.");
        } catch (SecurityException unused5) {
            LogUtil.e("HiChainClient", "addTrustedDevice SecurityException exception.");
        } catch (InvocationTargetException unused6) {
            LogUtil.e("HiChainClient", "addTrustedDevice InvocationTargetException exception.");
        }
    }
}
