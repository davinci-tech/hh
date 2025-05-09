package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bjj {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f403a;
    private CountDownLatch b;
    private volatile boolean c;
    private String d;
    private bjm e;
    private HandshakeCommandBase g;
    private CountDownLatch h;

    public void e(boolean z) {
        this.c = z;
        this.f403a = null;
    }

    public void a(String str) {
        this.d = str;
    }

    public bjm c() {
        return this.e;
    }

    public void c(bjm bjmVar) {
        this.e = bjmVar;
    }

    public HandshakeCommandBase c(String str, uwi uwiVar, ConcurrentHashMap<Long, String> concurrentHashMap, ConcurrentHashMap<String, bjj> concurrentHashMap2) {
        if (TextUtils.isEmpty(str) || uwiVar == null || concurrentHashMap == null || concurrentHashMap2 == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "processAddMember groupId : ";
            objArr[1] = Boolean.valueOf(TextUtils.isEmpty(str));
            objArr[2] = " , hiChainGroupManager : ";
            objArr[3] = Boolean.valueOf(uwiVar == null);
            LogUtil.e("HiChain3AddMemberControlInfo", objArr);
            return null;
        }
        e();
        d(str, uwiVar, concurrentHashMap);
        i();
        LogUtil.c("HiChain3AddMemberControlInfo", "processAddMember result:", concurrentHashMap2.putIfAbsent(this.d, this));
        return this.g;
    }

    private void d(String str, uwi uwiVar, ConcurrentHashMap<Long, String> concurrentHashMap) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("groupId", str);
            jSONObject.put("groupType", 256);
            jSONObject.put(ComplaintConstants.GROUP_NAME_PARAM_KEY, "health_group_name");
            jSONObject.put("pinCode", bhh.e(this.d));
            jSONObject.put("isAdmin", true);
            jSONObject.put("isClient", true);
        } catch (JSONException unused) {
            LogUtil.e("HiChain3AddMemberControlInfo", "addMember failed JSONException");
        }
        long currentTimeMillis = System.currentTimeMillis();
        concurrentHashMap.putIfAbsent(Long.valueOf(currentTimeMillis), this.d);
        bjl.e().a();
        int c = uwiVar.c(currentTimeMillis, "com.huawei.health", jSONObject.toString());
        DeviceInfo e = bjx.a().e(this.d);
        if (e != null) {
            bmw.e(100018, e.getDeviceName(), String.valueOf(c), "");
        }
        LogUtil.c("HiChain3AddMemberControlInfo", "addMemberToGroup result: ", Integer.valueOf(c), ",json:", jSONObject.toString(), ",deviceIdentify:", blt.a(this.d));
    }

    public void a(long j, byte[] bArr) {
        if (Arrays.equals(bArr, this.f403a)) {
            LogUtil.c("HiChain3AddMemberControlInfo", "transmitDataToService isSameData");
            return;
        }
        this.f403a = bArr;
        ReleaseLogUtil.b("DEVMGR_HiChain3AddMemberControlInfo", "transmit data， phone to device:", blt.a(this.d));
        this.g = new bhi(bArr, (byte) 1, j);
        f();
    }

    private void e() {
        if (this.h == null) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.h = countDownLatch;
            LogUtil.c("HiChain3AddMemberControlInfo", "initTransmitLock: mTransmitLatch:", countDownLatch, ",deviceIdentify:", blt.a(this.d));
        }
    }

    private void i() {
        LogUtil.c("HiChain3AddMemberControlInfo", "waitHiChain: start");
        if (this.h == null) {
            LogUtil.c("HiChain3AddMemberControlInfo", "mTransmitLatch is invalid.deviceIdentify:", blt.a(this.d));
            return;
        }
        try {
            if (!r1.await(10L, TimeUnit.SECONDS)) {
                ReleaseLogUtil.a("DEVMGR_HiChain3AddMemberControlInfo", "await timeout, hiChain failed");
                DeviceInfo e = bjx.a().e(this.d);
                if (e != null) {
                    bmw.e(100019, e.getDeviceName(), "", "");
                }
                this.g = null;
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChain3AddMemberControlInfo", "waitHiChain: InterruptedException");
            this.g = null;
        }
        LogUtil.c("HiChain3AddMemberControlInfo", "waitHiChain: end");
    }

    private void f() {
        LogUtil.c("HiChain3AddMemberControlInfo", "releaseLock: start:", this.h);
        CountDownLatch countDownLatch = this.h;
        if (countDownLatch == null) {
            LogUtil.a("HiChain3AddMemberControlInfo", "releaseLock: mAsyLatch is null");
        } else {
            LogUtil.c("HiChain3AddMemberControlInfo", "releaseLock: countdown:", countDownLatch);
            this.h.countDown();
            this.h = null;
        }
        LogUtil.a("HiChain3AddMemberControlInfo", "releaseLock: end");
    }

    private void d() {
        if (this.b == null) {
            this.b = new CountDownLatch(1);
        }
    }

    private void h() {
        LogUtil.c("HiChain3AddMemberControlInfo", "waitBindFinish: start");
        if (this.b == null) {
            LogUtil.a("HiChain3AddMemberControlInfo", "mBindLatch is invalid.");
            return;
        }
        try {
            if (!r0.await(10L, TimeUnit.SECONDS)) {
                LogUtil.a("HiChain3AddMemberControlInfo", "await timeout, waitBindFinish failed");
                this.g = null;
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChain3AddMemberControlInfo", "waitBindFinish: InterruptedException");
            this.g = null;
        }
        LogUtil.c("HiChain3AddMemberControlInfo", "waitBindFinish: end");
    }

    private void j() {
        LogUtil.c("HiChain3AddMemberControlInfo", "releaseLock: start");
        if (this.b == null) {
            LogUtil.a("HiChain3AddMemberControlInfo", "releaseLock: mBindLatch is null");
        } else {
            LogUtil.c("HiChain3AddMemberControlInfo", "releaseLock: countdown");
            this.b.countDown();
            this.b = null;
        }
        LogUtil.c("HiChain3AddMemberControlInfo", "releaseLock: end");
    }

    public bjc b(bjo bjoVar, uwi uwiVar) {
        bjc bjcVar = new bjc(false);
        this.g = null;
        if (uwiVar != null && !c(this.d, bjoVar)) {
            int d = bjoVar.d();
            int b = bjoVar.b();
            ReleaseLogUtil.b("DEVMGR_HiChain3AddMemberControlInfo", "transmitDataToClient device to hiChain3 type:", Integer.valueOf(d), ",AuthFinishFlag:", Integer.valueOf(b));
            DeviceInfo e = bjx.a().e(this.d);
            if (e != null) {
                bmw.e(100024, e.getDeviceName(), String.valueOf(d), String.valueOf(b));
            }
            if (b == 4) {
                bjcVar.c(true);
                return bjcVar;
            }
            if (b == 1) {
                LogUtil.c("HiChain3AddMemberControlInfo", "transmitDataToClient mIsBind:", Boolean.valueOf(this.c));
                return g();
            }
            if (b == 2) {
                return this.e.b();
            }
            if (d == 1 && b == 0) {
                e();
                long c = bjoVar.c();
                LogUtil.c("HiChain3AddMemberControlInfo", "transmitDataToClient requestId:", Long.valueOf(c), ",deviceIdentify:", blt.a(this.d));
                uwiVar.b(c, bjoVar.e());
                i();
                if (this.g != null || this.c) {
                    bjcVar.c(true);
                }
                bjcVar.c(this.g);
                return bjcVar;
            }
            if (d == 2 && b == 0) {
                return this.e.c(bjoVar);
            }
        }
        return bjcVar;
    }

    private boolean c(String str, bjo bjoVar) {
        if (bjoVar != null && !TextUtils.isEmpty(str)) {
            return false;
        }
        LogUtil.a("HiChain3AddMemberControlInfo", "transmitDataToClient hiChainInfo or deviceIdentify is null");
        return true;
    }

    private bjc g() {
        if (this.c) {
            return b();
        }
        d();
        h();
        if (this.c) {
            return b();
        }
        return new bjc(false);
    }

    public bjc b() {
        bjc bjcVar = new bjc(false);
        bjm bjmVar = this.e;
        return bjmVar == null ? bjcVar : bjmVar.e();
    }

    public void a() {
        this.c = true;
        f();
        j();
        this.f403a = null;
    }
}
