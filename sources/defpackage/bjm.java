package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.operation.utils.Constants;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import ohos.security.deviceauth.sdk.DeviceAuthCallback;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bjm {

    /* renamed from: a, reason: collision with root package name */
    private String f405a;
    private long b;
    private CountDownLatch c;
    private CountDownLatch d;
    private byte[] e;
    private byte[] f;
    private uwj g;
    private e h;
    private String i;
    private volatile boolean j;
    private HandshakeCommandBase l;
    private boolean o = false;

    public bjm(String str, String str2) {
        this.i = str2;
        uwj e2 = uwj.e();
        this.g = e2;
        e2.b(BaseApplication.e());
        this.h = new e();
        this.f405a = str;
        this.j = false;
    }

    public boolean c() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public byte[] d() {
        return this.f;
    }

    public void c(byte[] bArr) {
        this.f = bArr;
    }

    class e implements DeviceAuthCallback {
        e() {
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public boolean onTransmit(long j, byte[] bArr) {
            bjm.this.e(j, bArr);
            return true;
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public void onSessionKeyReturned(long j, byte[] bArr) {
            LogUtil.c("AuthCallback", "onSessionKeyReturned: (requestId: ", Long.valueOf(j), ", key length: ", Integer.valueOf(bArr.length), Constants.RIGHT_BRACKET_ONLY);
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public void onFinish(long j, int i, String str) {
            ReleaseLogUtil.a("DEVMGR_HiChain3Client", "onFinish: (requestId: ", Long.valueOf(j), ", operationCode:", Integer.valueOf(i), ",mCurrentRequestId:", Long.valueOf(bjm.this.b), "returnData: ", bjm.this.a(str), Constants.RIGHT_BRACKET_ONLY);
            DeviceInfo e = bjx.a().e(bjm.this.f405a);
            if (e != null) {
                bmw.e(100022, e.getDeviceName(), String.valueOf(i), String.valueOf(j));
            }
            if (bjm.this.b != j) {
                LogUtil.a("AuthCallback", "onTransmit requestId invalid,mCurrentRequestId:", Long.valueOf(bjm.this.b));
                return;
            }
            try {
                String string = new JSONObject(str).getString("sessionKey");
                if (string == null || string.length() == 0) {
                    LogUtil.e("AuthCallback", "onSessionKeyReturned sessionKey is empty");
                }
                bjm.this.c(blq.a(string));
            } catch (JSONException unused) {
                LogUtil.e("AuthCallback", "parse groupId failed JSONException");
            }
            bjm.this.j = true;
            bjm.this.h();
            bjm.this.f();
            bjm.this.e = null;
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public void onError(long j, int i, int i2, String str) {
            ReleaseLogUtil.b("DEVMGR_HiChain3Client", "onError: (requestId: ", Long.valueOf(j), ", operationCode: ", Integer.valueOf(i), ", errorCode: ", Integer.valueOf(i2), ", errorReturn:", str, Constants.RIGHT_BRACKET_ONLY);
            DeviceInfo e = bjx.a().e(bjm.this.f405a);
            if (e != null) {
                bmw.e(100021, e.getDeviceName(), String.valueOf(i2), String.valueOf(i));
            }
            if (bjm.this.b != j) {
                LogUtil.a("AuthCallback", "onTransmit requestId invalid,mCurrentRequestId:", Long.valueOf(bjm.this.b));
            } else {
                bjm.this.h();
            }
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public String onRequest(long j, int i, String str) {
            return new JSONObject().toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("sessionKey")) {
                jSONObject.put("sessionKey", bky.e(jSONObject.getString("sessionKey")));
            }
            if (jSONObject.has("groupId")) {
                jSONObject.put("groupId", bky.e(jSONObject.getString("groupId")));
            }
            if (jSONObject.has("peerAuthId")) {
                jSONObject.put("peerAuthId", bky.e(jSONObject.getString("peerAuthId")));
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            LogUtil.e("HiChain3Client", "fuzzDataPrint client json exception: ", e2.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, byte[] bArr) {
        if (Arrays.equals(bArr, this.e)) {
            LogUtil.c("HiChain3Client", "transmitDataToService isSameData");
            return;
        }
        this.e = bArr;
        ReleaseLogUtil.b("DEVMGR_HiChain3Client", "transmit data， phone to device");
        this.l = new bhi(bArr, (byte) 2, j);
        DeviceInfo e2 = bjx.a().e(this.f405a);
        if (e2 != null) {
            bmw.e(100020, e2.getDeviceName(), "", String.valueOf(j));
        }
        h();
    }

    private void a() {
        this.j = false;
        this.e = null;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("peerConnDeviceId", bjn.a(this.f405a));
            jSONObject.put("isClient", true);
            jSONObject.put("groupId", this.i);
            jSONObject.put("servicePkgName", BaseApplication.e().getPackageName());
        } catch (JSONException unused) {
            LogUtil.e("HiChain3Client", "authDevice failed JSONException");
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.b = currentTimeMillis;
        this.g.b(BaseApplication.e());
        LogUtil.c("HiChain3Client", "authDevice result: ", Integer.valueOf(this.g.e(currentTimeMillis, jSONObject.toString(), this.h)), ",json:", jSONObject.toString(), ",authRequestId:", Long.valueOf(currentTimeMillis), ",deviceIdentify:", blt.a(this.f405a));
    }

    private void g() {
        if (this.d == null) {
            this.d = new CountDownLatch(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.c("HiChain3Client", "releaseLock start");
        if (this.d == null) {
            LogUtil.a("HiChain3Client", "releaseLock mAsyLatch is null");
        } else {
            LogUtil.a("HiChain3Client", "releaseLock countdown");
            this.d.countDown();
            this.d = null;
        }
        LogUtil.c("HiChain3Client", "releaseLock end");
    }

    private void k() {
        LogUtil.c("HiChain3Client", "waitHiChain: start");
        if (this.d == null) {
            LogUtil.c("HiChain3Client", "mAsyncLatch is invalid.");
            return;
        }
        try {
            if (!r1.await(10L, TimeUnit.SECONDS)) {
                DeviceInfo e2 = bjx.a().e(this.f405a);
                if (e2 != null) {
                    bmw.e(100023, e2.getDeviceName(), "", "");
                }
                ReleaseLogUtil.a("DEVMGR_HiChain3Client", "await auth timeout, hiChain failed");
                this.l = null;
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChain3Client", "waitHiChain: InterruptedException");
            this.l = null;
        }
        LogUtil.c("HiChain3Client", "waitHiChain: end");
    }

    private void i() {
        if (this.c == null) {
            this.c = new CountDownLatch(1);
        }
    }

    private void l() {
        LogUtil.c("HiChain3Client", "waitAuthFinish: start");
        if (this.c == null) {
            LogUtil.a("HiChain3Client", "mAuthLatch is invalid.");
            return;
        }
        try {
            if (!r0.await(10L, TimeUnit.SECONDS)) {
                LogUtil.a("HiChain3Client", "await timeout, waitAuthFinish failed");
                this.l = null;
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChain3Client", "waitAuthFinish: InterruptedException");
            this.l = null;
        }
        LogUtil.c("HiChain3Client", "waitAuthFinish: end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.c("HiChain3Client", "releaseLock: start");
        if (this.c == null) {
            LogUtil.a("HiChain3Client", "releaseLock: mAuthLatch is null");
        } else {
            LogUtil.c("HiChain3Client", "releaseLock: countdown");
            this.c.countDown();
            this.c = null;
        }
        LogUtil.c("HiChain3Client", "releaseLock: end");
    }

    public bjc e() {
        a(true);
        g();
        bjc bjcVar = new bjc(false);
        a();
        k();
        if (this.l != null) {
            bjcVar.c(true);
        }
        bjcVar.c(this.l);
        return bjcVar;
    }

    public bjc c(bjo bjoVar) {
        LogUtil.c("HiChain3Client", "transmit data, device to phone");
        bjc bjcVar = new bjc(false);
        this.l = null;
        if (bjoVar == null) {
            return bjcVar;
        }
        g();
        long c = bjoVar.c();
        this.b = c;
        LogUtil.c("HiChain3Client", "transmitDataToClient authDevice transmit data, device to phone,requestId:", Long.valueOf(c));
        this.g.e(c, bjoVar.e(), this.h);
        k();
        if (this.l != null || this.j) {
            bjcVar.c(true);
        }
        bjcVar.c(this.l);
        return bjcVar;
    }

    public bjc b() {
        LogUtil.c("HiChain3Client", "transmit data, authFinishGetNextCommand");
        if (this.j) {
            return j();
        }
        i();
        l();
        if (this.j) {
            return j();
        }
        return new bjc(false);
    }

    private bjc j() {
        bjc bjcVar = new bjc(false);
        ReleaseLogUtil.b("DEVMGR_HiChain3Client", "auth success, start 5.1.7");
        this.l = new bhk(false);
        bjcVar.c(true);
        bjcVar.c(this.l);
        return bjcVar;
    }
}
