package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;
import com.huawei.operation.utils.Constants;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import ohos.security.deviceauth.sdk.DeviceAuthCallback;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bjl {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f404a = new Object();
    private static final String b;
    private CountDownLatch c;
    private ConcurrentHashMap<String, bjj> d;
    private CountDownLatch e;
    private c f;
    private volatile String g;
    private uwi h;
    private ConcurrentHashMap<Long, String> j;

    static {
        b = bky.f() ? BaseApplication.APP_PACKAGE_HEALTH_TV : "com.huawei.health";
    }

    class c implements DeviceAuthCallback {
        private c() {
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public boolean onTransmit(long j, byte[] bArr) {
            LogUtil.c("GroupManagerCallBack", "onDataTransmit requestId start,requestId: ", Long.valueOf(j));
            String str = (String) bjl.this.j.get(Long.valueOf(j));
            if (!TextUtils.isEmpty(str) || bjl.this.d.size() != 1) {
                bjj bjjVar = (bjj) bjl.this.d.get(str);
                if (bjjVar == null) {
                    LogUtil.c("GroupManagerCallBack", "onDataTransmit valid, controlInfo is null,requestId: ", Long.valueOf(j));
                    return true;
                }
                bjjVar.a(j, bArr);
                return true;
            }
            LogUtil.c("GroupManagerCallBack", "onDataTransmit deviceId is null");
            bjj bjjVar2 = (bjj) ((Map.Entry) bjl.this.d.entrySet().iterator().next()).getValue();
            if (bjjVar2 == null) {
                LogUtil.c("GroupManagerCallBack", "onDataTransmit valid, controlInfo is null,requestId: ", Long.valueOf(j));
                return true;
            }
            bjjVar2.a(j, bArr);
            return true;
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public void onSessionKeyReturned(long j, byte[] bArr) {
            LogUtil.c("GroupManagerCallBack", "onSessionKeyReturned: (requestId: ", Long.valueOf(j), ", key length: ", Integer.valueOf(bArr.length), Constants.RIGHT_BRACKET_ONLY);
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public void onFinish(long j, int i, String str) {
            ReleaseLogUtil.b("DEVMGR_HiChain3GroupManager", "onFinish: (requestId: ", Long.valueOf(j), ", operationCode: ", Integer.valueOf(i), ", returnData: ", bjl.this.g(str), Constants.RIGHT_BRACKET_ONLY);
            String str2 = (String) bjl.this.j.get(Long.valueOf(j));
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            DeviceInfo e = bjx.a().e(str2);
            if (e != null) {
                bmw.e(100014, e.getDeviceName(), String.valueOf(i), String.valueOf(j));
            }
            if (i == 0) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    bjl.this.g = jSONObject.getString("groupId");
                    bjl.e().f();
                    return;
                } catch (JSONException unused) {
                    LogUtil.e("GroupManagerCallBack", "parse groupId failed JSONException");
                    return;
                }
            }
            if (i == 1) {
                bjl.this.h();
            } else if (i == 2) {
                bjl.this.e(j, str);
            } else {
                LogUtil.a("GroupManagerCallBack", "onFinish default");
            }
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public void onError(long j, int i, int i2, String str) {
            ReleaseLogUtil.b("DEVMGR_HiChain3GroupManager", "onError: (requestId: ", Long.valueOf(j), ", operationCode: ", Integer.valueOf(i), ", errorCode: ", Integer.valueOf(i2), ", errorReturn:", str, Constants.RIGHT_BRACKET_ONLY);
            String str2 = (String) bjl.this.j.get(Long.valueOf(j));
            if (TextUtils.isEmpty(str2)) {
                bmw.e(100015, "", String.valueOf(i), String.valueOf(j));
            } else {
                DeviceInfo e = bjx.a().e(str2);
                if (e != null) {
                    bmw.e(100015, e.getDeviceName(), String.valueOf(i), String.valueOf(j));
                }
            }
            if ((i == 2 || i == 3 || i == 4) && TextUtils.isEmpty(str2)) {
                return;
            }
            bjl.this.f();
        }

        @Override // ohos.security.deviceauth.sdk.DeviceAuthCallback
        public String onRequest(long j, int i, String str) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String g(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("sessionKey")) {
                jSONObject.put("sessionKey", bky.e(jSONObject.getString("sessionKey")));
            }
            if (jSONObject.has("addId")) {
                jSONObject.put("addId", bky.e(jSONObject.getString("addId")));
            }
            if (jSONObject.has("groupId")) {
                jSONObject.put("groupId", bky.e(jSONObject.getString("groupId")));
            }
            if (jSONObject.has("groupOwner")) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString("groupOwner"));
                if (jSONObject2.has("pubKey")) {
                    jSONObject2.put("pubKey", bky.e(jSONObject2.getString("pubKey")));
                }
                jSONObject.put("groupOwner", jSONObject2);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            LogUtil.e("HiChain3GroupManager", "fuzzDataPrint group json exception: ", e2.getMessage());
            return "";
        }
    }

    private bjl() {
        this.j = new ConcurrentHashMap<>(16);
        this.d = new ConcurrentHashMap<>(16);
        this.g = "";
        this.h = uwi.b();
        this.f = new c();
        g();
    }

    public void d(String str, boolean z) {
        ConcurrentHashMap<String, bjj> concurrentHashMap = this.d;
        if (concurrentHashMap == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "mAddMemberControlMap is null");
            return;
        }
        if (!concurrentHashMap.containsKey(str)) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "not have HiChainClient setIsHiChain3On");
            return;
        }
        bjj bjjVar = this.d.get(str);
        if (bjjVar == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), ",controlInfo is null");
            return;
        }
        bjm c2 = bjjVar.c();
        if (c2 == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), ",client is null");
        } else {
            c2.a(z);
        }
    }

    public boolean e(String str) {
        ConcurrentHashMap<String, bjj> concurrentHashMap = this.d;
        if (concurrentHashMap == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "mHiChainClientMap is null isHiChain3On");
            return false;
        }
        if (!concurrentHashMap.containsKey(str)) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "not have HiChainClient isHiChain3On");
            return false;
        }
        bjj bjjVar = this.d.get(str);
        if (bjjVar == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "controlInfo is null");
            return false;
        }
        bjm c2 = bjjVar.c();
        if (c2 == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "hiChain3Client is null");
            return false;
        }
        return c2.c();
    }

    public boolean a(String str) {
        ConcurrentHashMap<String, bjj> concurrentHashMap = this.d;
        if (concurrentHashMap == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "mHiChainClientMap is null isHaveHiChainClient");
            return false;
        }
        return concurrentHashMap.containsKey(str);
    }

    public byte[] c(String str) {
        ConcurrentHashMap<String, bjj> concurrentHashMap = this.d;
        if (concurrentHashMap == null) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "mHiChainClientMap is null getCurrentSessionKey");
            return null;
        }
        if (!concurrentHashMap.containsKey(str)) {
            LogUtil.a("HiChain3GroupManager", "deviceIdentify", blt.b(str), "not have HiChainClient getCurrentSessionKey");
            return null;
        }
        bjj bjjVar = this.d.get(str);
        if (bjjVar == null) {
            LogUtil.a("HiChain3GroupManager", "getCurrentSessionKey is null, deviceIdentify", blt.b(str));
            return null;
        }
        bjm c2 = bjjVar.c();
        if (c2 == null) {
            LogUtil.a("HiChain3GroupManager", "getCurrentSessionKey Client is null, deviceIdentify", blt.b(str));
            return null;
        }
        return c2.d();
    }

    public void d() {
        this.g = null;
    }

    public void b(String str) {
        ConcurrentHashMap<String, bjj> concurrentHashMap = this.d;
        if (concurrentHashMap != null && concurrentHashMap.containsKey(str)) {
            this.d.remove(str);
            LogUtil.c("HiChain3GroupManager", "clearHiChain3Client mAddMemberControlMap remove ,", blt.a(str));
        }
        bhh.d(str);
        j(str);
    }

    public static bjl e() {
        return e.e;
    }

    public HandshakeCommandBase e(String str, String str2, biw biwVar, boolean z) {
        LogUtil.c("HiChain3GroupManager", "initHiChain3 start ", blt.a(str2));
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("HiChain3GroupManager", "initHiChain3: deviceIdentify is null");
            return null;
        }
        bjl e2 = e();
        String h = e2.h(str);
        if (h == null) {
            ReleaseLogUtil.a("DEVMGR_HiChain3GroupManager", "initHiChain3: connect hiChain failed");
            return null;
        }
        return e2.e(str2, biwVar, z, h);
    }

    private String h(String str) {
        String str2;
        synchronized (f404a) {
            d();
            this.g = m(str);
            if (TextUtils.isEmpty(this.g)) {
                k(str);
            }
            LogUtil.c("HiChain3GroupManager", "HiChainService is connected");
            str2 = this.g;
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, String str) {
        String str2 = this.j.get(Long.valueOf(j));
        if (TextUtils.isEmpty(str2)) {
            try {
                String string = new JSONObject(str).getString("addId");
                String d = bjn.d(string);
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(d)) {
                    LogUtil.c("HiChain3GroupManager", "add member onFinish requestId invalid,but deviceId conform to continue ,", blt.a(d));
                    str2 = d;
                }
                return;
            } catch (JSONException unused) {
                LogUtil.e("HiChain3GroupManager", "add member onFinish parse failed JSONException");
                return;
            }
        }
        bjj bjjVar = this.d.get(str2);
        if (bjjVar == null) {
            LogUtil.a("HiChain3GroupManager", "add member onFinish controlInfo is null");
        } else {
            bjjVar.a();
        }
    }

    private void j() {
        this.c = new CountDownLatch(1);
        LogUtil.c("HiChain3GroupManager", "initLock success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.c == null) {
            LogUtil.a("HiChain3GroupManager", "mConnectLatch is null, releaseLock fail.");
            return;
        }
        LogUtil.c("HiChain3GroupManager", "releaseLock: start");
        this.c.countDown();
        this.c = null;
        LogUtil.c("HiChain3GroupManager", "releaseLock: end");
    }

    private void c() {
        this.e = new CountDownLatch(1);
        LogUtil.c("HiChain3GroupManager", "initDelLock success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.e == null) {
            LogUtil.a("HiChain3GroupManager", "releaseDelLock is null, releaseLock fail.");
            return;
        }
        LogUtil.c("HiChain3GroupManager", "releaseDelLock: start");
        this.e.countDown();
        this.e = null;
        LogUtil.c("HiChain3GroupManager", "releaseDelLock: end");
    }

    private void i() {
        LogUtil.c("HiChain3GroupManager", "waitDelGroup: start");
        if (this.e == null) {
            LogUtil.a("HiChain3GroupManager", "waitDelGroup is invalid.");
            return;
        }
        try {
            if (!r0.await(2L, TimeUnit.SECONDS)) {
                LogUtil.a("HiChain3GroupManager", "waitDelGroup deviceAuthService timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChain3GroupManager", "waitDelGroup: InterruptedException");
        }
        LogUtil.c("HiChain3GroupManager", "waitDelGroup: end");
    }

    private HandshakeCommandBase e(String str, biw biwVar, boolean z, String str2) {
        boolean e2 = this.h.e(b, str2, bjn.a(str));
        ReleaseLogUtil.b("DEVMGR_HiChain3GroupManager", "openHiChainAuth isAdd:", Boolean.valueOf(e2), ",isFirstPair:", Boolean.valueOf(z), ",deviceIdentify:", blt.a(str));
        if (!this.d.containsKey(str)) {
            LogUtil.c("HiChain3GroupManager", "no exist");
            bjj bjjVar = new bjj();
            bjjVar.a(str);
            bjjVar.c(new bjm(str, str2));
            this.d.putIfAbsent(str, bjjVar);
            LogUtil.c("HiChain3GroupManager", "openHiChainAuth mAddMemberControlMap putIfAbsent ,", blt.a(str));
        }
        if (e2 && !z) {
            bjj bjjVar2 = this.d.get(str);
            if (bjjVar2 == null) {
                LogUtil.c("HiChain3GroupManager", "openHiChainAuth to processToAuth valid");
                return null;
            }
            bjjVar2.e(true);
            return bjjVar2.b().d();
        }
        bjj bjjVar3 = this.d.get(str);
        if (bjjVar3 == null) {
            LogUtil.c("HiChain3GroupManager", "openHiChainAuth to GetDevicePinCodeCommand valid");
            return null;
        }
        bjjVar3.e(false);
        DeviceInfo e3 = bjx.a().e(str);
        if (e3 != null) {
            bmw.e(100016, e3.getDeviceName(), "", "");
        }
        return new bhb(biwVar);
    }

    public HandshakeCommandBase d(String str) {
        bjj bjjVar = this.d.get(str);
        if (bjjVar == null) {
            LogUtil.c("HiChain3GroupManager", "addMemberToGroup valid");
            return null;
        }
        return bjjVar.c(this.g, this.h, this.j, this.d);
    }

    private void k(String str) {
        LogUtil.c("HiChain3GroupManager", "waitConnect: start");
        if (this.c == null) {
            LogUtil.c("HiChain3GroupManager", "mConnectLatch is invalid.");
            return;
        }
        try {
            if (!r1.await(5L, TimeUnit.SECONDS)) {
                bmw.e(100013, str, "", "");
                ReleaseLogUtil.a("DEVMGR_HiChain3GroupManager", "create group timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.e("HiChain3GroupManager", "waitConnect: InterruptedException");
        }
        LogUtil.c("HiChain3GroupManager", "waitConnect: end");
    }

    public bjc d(String str, bjo bjoVar) {
        bjj bjjVar = this.d.get(str);
        if (bjjVar == null) {
            LogUtil.c("HiChain3GroupManager", "transmitDataToClient valid");
            return new bjc(false);
        }
        return bjjVar.b(bjoVar, this.h);
    }

    private String a(String str, String str2) {
        if (this.h.e(b, str2, bmf.a())) {
            LogUtil.c("HiChain3GroupManager", "checkPhoneUdid,The udid already in group");
            return str2;
        }
        LogUtil.c("HiChain3GroupManager", "checkPhoneUdid to del group");
        c();
        i(str2);
        i();
        return f(str);
    }

    private String b() {
        String a2 = bmf.a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ComplaintConstants.GROUP_NAME_PARAM_KEY, "health_group_name");
            jSONObject.put("groupType", 256);
            jSONObject.put("deviceId", a2);
            jSONObject.put("userType", 0);
            jSONObject.put("groupVisibility", -1);
            jSONObject.put("expireTime", -1);
        } catch (JSONException unused) {
            LogUtil.e("HiChain3GroupManager", "createGroup failed JSONException");
        }
        return jSONObject.toString();
    }

    private String f(String str) {
        j();
        String b2 = b();
        int b3 = this.h.b(System.currentTimeMillis(), b, b2);
        LogUtil.c("HiChain3GroupManager", "createGroup result: ", Integer.valueOf(b3), ",json: ", b2);
        bmw.e(100012, str, String.valueOf(b3), "");
        return this.g;
    }

    private String m(String str) {
        String b2 = b();
        a();
        List<String> a2 = this.h.a(b, b2);
        if (!a2.isEmpty()) {
            try {
                String string = new JSONObject(a2.get(0)).getString("groupId");
                LogUtil.c("HiChain3GroupManager", "The group name already exists! groupId: ", string);
                return a(str, string);
            } catch (JSONException unused) {
                LogUtil.a("HiChain3GroupManager", "getGroupInfo JSONException");
                return this.g;
            }
        }
        return f(str);
    }

    private void j(String str) {
        if (this.h == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isClient", true);
            jSONObject.put("groupId", this.g);
            jSONObject.put("deleteId", bjn.a(str));
            jSONObject.put("isForceDelete", true);
            jSONObject.put("isIgnoreChannel", true);
        } catch (JSONException unused) {
            LogUtil.e("HiChain3GroupManager", "deleteMember failed JSONException");
        }
        long currentTimeMillis = System.currentTimeMillis();
        a();
        LogUtil.c("HiChain3GroupManager", "deleteMemberFromGroup result: ", Integer.valueOf(this.h.a(currentTimeMillis, b, jSONObject.toString())), ",json:", jSONObject.toString());
    }

    private void i(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("groupId", str);
        } catch (JSONException unused) {
            LogUtil.e("HiChain3GroupManager", "deleteGroup failed JSONException ");
        }
        LogUtil.c("HiChain3GroupManager", "deleteGroup result: ", Integer.valueOf(this.h.d(System.currentTimeMillis(), b, jSONObject.toString())), ",json:", jSONObject.toString());
        if (bky.a()) {
            bmf.d("");
            bli.c();
        }
    }

    private void g() {
        a();
    }

    public void a() {
        this.h.a(com.huawei.haf.application.BaseApplication.e());
        LogUtil.c("HiChain3GroupManager", "initService mGroupManagerCallBack: ", this.f);
        this.h.c(b, this.f);
    }

    static class e {
        private static bjl e = new bjl();
    }
}
