package com.huawei.watchface;

import android.app.Activity;
import android.content.Intent;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.hwid.SignInResult;
import com.huawei.hms.support.api.pay.HuaweiPay;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.hms.support.common.ActivityMgr;
import com.huawei.hms.support.hwid.HuaweiIdAuthAPIManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.ui.activity.HMSSignAuthAgentActivity;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import defpackage.mcf;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class bb {

    /* renamed from: a, reason: collision with root package name */
    private static bb f10916a = new bb();
    private volatile HuaweiApiClient b;
    private boolean c;
    private boolean d;
    private volatile boolean e = false;
    private List<d> f = new CopyOnWriteArrayList();
    private List<a> g = new CopyOnWriteArrayList();

    public interface a {
        void a();

        void b();
    }

    public interface d {
        void a();

        void a(String str);
    }

    public static bb a() {
        return f10916a;
    }

    private bb() {
        this.b = a(new b(), new c());
    }

    public HuaweiApiClient b() {
        synchronized (bb.class) {
            if (this.b == null) {
                this.b = a(new b(), new c());
            }
        }
        return this.b;
    }

    private HuaweiApiClient a(HuaweiApiClient.ConnectionCallbacks connectionCallbacks, HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new HuaweiApiClient.Builder(Environment.getApplicationContext()).addApi(HuaweiIdAuthAPIManager.HUAWEI_OAUTH_API, HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).addApi(HuaweiPay.PAY_API).addScope(HuaweiIdAuthAPIManager.HUAWEIID_BASE_SCOPE).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(onConnectionFailedListener).build();
    }

    private void c() {
        a(ActivityMgr.INST.getCurrentActivity());
    }

    private void a(Activity activity) {
        a(activity, (a) null);
    }

    public void a(a aVar) {
        a(ActivityMgr.INST.getCurrentActivity(), aVar);
    }

    public void a(Activity activity, a aVar) {
        b(aVar);
        HuaweiApiClient b2 = b();
        if (b2.isConnected()) {
            d();
            return;
        }
        if (activity == null) {
            HwLog.i("HMSSignHelper", "activity == null");
            e();
        } else if (b2.isConnecting()) {
            HwLog.i("HMSSignHelper", "mClient.isConnecting()");
        } else {
            b2.connect(activity);
        }
    }

    public void a(d dVar) {
        HuaweiApiClient b2 = b();
        if (dVar != null) {
            b(dVar);
        }
        if (!b2.isConnected()) {
            if (ActivityMgr.INST.getCurrentActivity() == null) {
                HwLog.i("HMSSignHelper", "ActivityMgr.INST.getLastActivity() == null");
                f();
                return;
            } else {
                c();
                this.d = true;
                return;
            }
        }
        if (this.e) {
            HwLog.i("HMSSignHelper", "isSignIning");
        } else if (ActivityMgr.INST.getCurrentActivity() == null) {
            HwLog.i("HMSSignHelper", "lastActivity == null");
        } else {
            HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.signInBackend(b2).setResultCallback(new e());
        }
    }

    private void b(a aVar) {
        HwLog.i("HMSSignHelper", "addConnectCallbacks");
        if (aVar == null) {
            HwLog.i("HMSSignHelper", "null == connectCallback");
        } else {
            if (this.g.contains(aVar)) {
                return;
            }
            this.g.add(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HwLog.i("HMSSignHelper", "notifyConnectCallBacks " + this.g.size());
        for (a aVar : this.g) {
            this.g.remove(aVar);
            aVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        HwLog.i("HMSSignHelper", "notifyConnectCallBacksFail : " + this.g.size());
        for (a aVar : this.g) {
            this.g.remove(aVar);
            aVar.b();
        }
    }

    private void b(d dVar) {
        HwLog.i("HMSSignHelper", "addSignInCallbacks");
        if (this.f.contains(dVar)) {
            return;
        }
        this.f.add(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HwLog.i("HMSSignHelper", "notifySignInCallBacks : " + this.f.size());
        for (d dVar : this.f) {
            this.f.remove(dVar);
            dVar.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        for (d dVar : this.f) {
            this.f.remove(dVar);
            dVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        HwLog.i("HMSSignHelper", "resetStatus");
        this.d = false;
        this.e = false;
        this.f.clear();
    }

    public void a(int i, int i2, Intent intent) {
        SafeIntent safeIntent = new SafeIntent(intent);
        if (i != 1001) {
            if (i == 1000) {
                f();
                g();
                a(i2, safeIntent);
                return;
            }
            return;
        }
        if (i2 == -1) {
            HwLog.i("HMSSignHelper", "User has authorized!");
            SignInResult hwIdSignInResultFromIntent = HuaweiIdAuthAPIManager.HuaweiIdAuthAPIService.getHwIdSignInResultFromIntent(safeIntent);
            if (hwIdSignInResultFromIntent == null) {
                f();
                g();
                return;
            }
            if (hwIdSignInResultFromIntent.isSuccess()) {
                HwLog.i("HMSSignHelper", "User authorization successful, return account information");
                AuthHuaweiId authHuaweiId = hwIdSignInResultFromIntent.getAuthHuaweiId();
                if (authHuaweiId != null) {
                    a(authHuaweiId.getAccessToken());
                    g();
                    return;
                }
                return;
            }
            HwLog.i("HMSSignHelper", "Failed authorization! Reason:" + hwIdSignInResultFromIntent.getStatus().toString());
            f();
            g();
            return;
        }
        HwLog.i("HMSSignHelper", "User not authorized!");
        f();
        g();
    }

    private void a(int i, Intent intent) {
        SafeIntent safeIntent = new SafeIntent(intent);
        this.c = false;
        if (i == -1) {
            int intExtra = safeIntent.getIntExtra(BridgeActivity.EXTRA_RESULT, -1);
            if (intExtra == 0) {
                HwLog.i("HMSSignHelper", "Error resolved successfully!");
                c();
                return;
            } else if (intExtra == 13) {
                HwLog.i("HMSSignHelper", "Resolve error process canceled by user!");
                return;
            } else if (intExtra == 8) {
                HwLog.i("HMSSignHelper", "Internal error occurred, recommended retry.");
                return;
            } else {
                HwLog.i("HMSSignHelper", "Other error codes.");
                return;
            }
        }
        HwLog.i("HMSSignHelper", "An error occurred invoking the solution!");
    }

    class e implements ResultCallback<SignInResult> {
        private e() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(SignInResult signInResult) {
            if (signInResult == null) {
                bb.this.f();
                bb.this.g();
                return;
            }
            if (signInResult.isSuccess()) {
                HwLog.i("HMSSignHelper", "Login successful!");
                AuthHuaweiId authHuaweiId = signInResult.getAuthHuaweiId();
                if (authHuaweiId != null) {
                    bb.this.a(authHuaweiId.getAccessToken());
                    bb.this.g();
                    return;
                }
                return;
            }
            HwLog.i("HMSSignHelper", "onResult : result is Success false");
            if (signInResult.getStatus().getStatusCode() == 2002) {
                HwLog.i("HMSSignHelper", "Account is logged in and requires user authorization!");
                Intent data = signInResult.getData();
                if (data == null) {
                    bb.this.f();
                    bb.this.g();
                    return;
                }
                HwLog.i("HMSSignHelper", "SignInResult startActivityForResult");
                Activity currentActivity = ActivityMgr.INST.getCurrentActivity();
                if (currentActivity == null) {
                    bb.this.f();
                    bb.this.g();
                    return;
                } else {
                    Intent intent = new Intent(currentActivity, (Class<?>) HMSSignAuthAgentActivity.class);
                    intent.putExtra(HMSSignAuthAgentActivity.EXTRA_INTENT, data);
                    mcf.cfJ_(currentActivity, intent);
                    return;
                }
            }
            if (signInResult.getStatus().getStatusCode() == 2005) {
                HwLog.i("HMSSignHelper", "result.getStatus().getStatusCode() == HuaweiIdStatusCodes.SIGN_IN_NETWORK_ERROR");
                bb.this.f();
                bb.this.g();
            } else {
                HwLog.i("HMSSignHelper", "last branch");
                bb.this.f();
                bb.this.g();
            }
        }
    }

    class b implements HuaweiApiClient.ConnectionCallbacks {
        private b() {
        }

        @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
        public void onConnected() {
            if (bb.this.d) {
                bb.this.a((d) null);
            }
            HwLog.i("HMSSignHelper", "HuaweiApiClient Connect Successfully!");
            bb.this.d();
        }

        @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            HwLog.i("HMSSignHelper", "HuaweiApiClient Disconnected!");
            bb.this.e();
            Activity currentActivity = ActivityMgr.INST.getCurrentActivity();
            if (currentActivity != null) {
                bb.this.b().connect(currentActivity);
            }
        }
    }

    class c implements HuaweiApiClient.OnConnectionFailedListener {
        private c() {
        }

        @Override // com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult connectionResult) {
            HwLog.i("HMSSignHelper", "HuaweiApiClient Connect Failed!  Error code：" + connectionResult.getErrorCode());
            if (connectionResult.getErrorCode() == 6) {
                PayResultInfo payResultInfo = new PayResultInfo();
                payResultInfo.setReturnCode(CapabilityStatus.AWA_CAP_CODE_WIFI);
                s.a(Environment.getApplicationContext()).b(payResultInfo);
            }
            s.a(Environment.getApplicationContext()).a(connectionResult);
            bb.this.e();
            bb.this.f();
            if (!bb.this.c && HuaweiApiAvailability.getInstance().isUserResolvableError(connectionResult.getErrorCode())) {
                Activity currentActivity = ActivityMgr.INST.getCurrentActivity();
                if (currentActivity == null) {
                    HwLog.i("HMSSignHelper", "null == lastActivity");
                    return;
                }
                HwLog.e("HMSSignHelper", "resolveError");
                if (CommonUtils.s()) {
                    HuaweiApiAvailability.getInstance().resolveError(currentActivity, connectionResult.getErrorCode(), 1000);
                    bb.this.c = true;
                }
            }
        }
    }
}
