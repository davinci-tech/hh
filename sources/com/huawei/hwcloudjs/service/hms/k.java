package com.huawei.hwcloudjs.service.hms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.api.HuaweiApiClientImpl;
import com.huawei.hms.support.api.client.BundleResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.hwid.SignInResult;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.hms.a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6244a = "HmsCoreApiHelper";
    private static final int b = 9004;
    public static final int c = 9003;
    public static final int d = 1;

    public static boolean a(List<Scope> list, List<Scope> list2) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Scope scope : list) {
                if (scope != null && !arrayList.contains(scope.getScopeUri())) {
                    arrayList.add(scope.getScopeUri());
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (list2 != null) {
            for (Scope scope2 : list2) {
                if (scope2 != null && !arrayList2.contains(scope2.getScopeUri())) {
                    arrayList2.add(scope2.getScopeUri());
                }
            }
        }
        Collections.sort(arrayList);
        Collections.sort(arrayList2);
        return arrayList.equals(arrayList2);
    }

    public static final class b implements HuaweiApiClient.OnConnectionFailedListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<HuaweiApiClient> f6246a;
        private JsCallback b;
        private WeakReference<Activity> c;

        static final class a implements a.c {

            /* renamed from: a, reason: collision with root package name */
            private static final String f6247a = "intent.extra.RESULT";
            private WeakReference<HuaweiApiClient> b;
            private WeakReference<Activity> c;
            private JsCallback d;

            @Override // com.huawei.hwcloudjs.service.hms.a.c
            public void a(a.b bVar) {
                Intent a2;
                int i = -1;
                if (bVar != null && bVar.c() == -1 && (a2 = bVar.a()) != null) {
                    i = a2.getIntExtra("intent.extra.RESULT", -1);
                }
                com.huawei.hwcloudjs.f.d.b(k.f6244a, "ConnFailResult result:" + i, true);
                if (i == 0) {
                    HuaweiApiClient huaweiApiClient = this.b.get();
                    if (huaweiApiClient == null || huaweiApiClient.isConnecting() || huaweiApiClient.isConnected()) {
                        return;
                    }
                    com.huawei.hwcloudjs.f.d.c(k.f6244a, "ConnFailResult connect", true);
                    WeakReference<Activity> weakReference = this.c;
                    huaweiApiClient.connect(weakReference != null ? weakReference.get() : null);
                    return;
                }
                String str = i + "";
                com.huawei.hwcloudjs.f.d.b(k.f6244a, "ConnFailResult onConnectionFailed" + str, true);
                this.d.failure(str);
            }

            public a(WeakReference<HuaweiApiClient> weakReference, WeakReference<Activity> weakReference2, JsCallback jsCallback) {
                this.b = weakReference;
                this.c = weakReference2;
                this.d = jsCallback;
            }
        }

        @Override // com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult connectionResult) {
            int errorCode = connectionResult.getErrorCode();
            HuaweiApiAvailability huaweiApiAvailability = HuaweiApiAvailability.getInstance();
            if (huaweiApiAvailability.isUserResolvableError(errorCode)) {
                com.huawei.hwcloudjs.f.d.b(k.f6244a, "InvokeResultCallback onConnectionFailed REQUEST_CODE_CONN_ERROR", true);
                com.huawei.hwcloudjs.service.hms.a.a().a(9004, new a(this.f6246a, this.c, this.b));
                new Handler(Looper.getMainLooper()).post(new l(this, huaweiApiAvailability, errorCode));
                return;
            }
            String str = errorCode + "";
            com.huawei.hwcloudjs.f.d.b(k.f6244a, "InvokeResultCallback onConnectionFailed errorCode:" + str, true);
            this.b.failure(str);
        }

        public b(WeakReference<HuaweiApiClient> weakReference, JsCallback jsCallback, WeakReference<Activity> weakReference2) {
            this.f6246a = weakReference;
            this.b = jsCallback;
            this.c = weakReference2;
        }
    }

    public static void a(WeakReference<HuaweiApiClient> weakReference, String str, JSONObject jSONObject, JsCallback jsCallback) {
        HuaweiApiClient huaweiApiClient = weakReference.get();
        if (huaweiApiClient == null || !(huaweiApiClient instanceof HuaweiApiClientImpl)) {
            jsCallback.failure();
        } else {
            ((HuaweiApiClientImpl) huaweiApiClient).asyncRequest(u.a(2).a(jSONObject, new Bundle()), str, 2, new d(weakReference, jsCallback));
        }
    }

    static final class a implements a.c {

        /* renamed from: a, reason: collision with root package name */
        private String f6245a;
        private JsCallback b;

        @Override // com.huawei.hwcloudjs.service.hms.a.c
        public void a(a.b bVar) {
            Bundle extras;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("resultCode", bVar.c());
            } catch (JSONException unused) {
                com.huawei.hwcloudjs.f.d.b(k.f6244a, "getResultFromActivityResult put json error", true);
            }
            Intent a2 = bVar.a();
            if (a2 != null && (extras = a2.getExtras()) != null) {
                t a3 = u.a(2);
                JSONObject jSONObject2 = new JSONObject();
                a3.a(this.f6245a, extras, jSONObject2);
                try {
                    jSONObject.put("result", jSONObject2);
                } catch (JSONException unused2) {
                    com.huawei.hwcloudjs.f.d.b(k.f6244a, "InvokeResultCallback onResult JSONException", true);
                    this.b.failure();
                }
            }
            this.b.success(jSONObject.toString());
        }

        public a(String str, JsCallback jsCallback) {
            this.f6245a = str;
            this.b = jsCallback;
        }
    }

    public static final class c implements HuaweiApiClient.ConnectionCallbacks {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<HuaweiApiClient> f6248a;
        private JsCallback b;
        private String c;
        private JSONObject d;

        @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
        }

        @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
        public void onConnected() {
            k.a(this.f6248a, this.c, this.d, this.b);
        }

        public c(WeakReference<HuaweiApiClient> weakReference, JsCallback jsCallback, String str, JSONObject jSONObject) {
            this.f6248a = weakReference;
            this.b = jsCallback;
            this.c = str;
            this.d = jSONObject;
        }
    }

    static final class d implements ResultCallback<BundleResult> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<HuaweiApiClient> f6249a;
        private JsCallback b;

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(BundleResult bundleResult) {
            HuaweiApiClient huaweiApiClient = this.f6249a.get();
            if (huaweiApiClient == null) {
                this.b.failure();
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("statusCode", bundleResult.getResultCode());
                if (bundleResult.getRspBody() != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    u.a(2).a(huaweiApiClient.getAppID(), bundleResult.getRspBody(), jSONObject2);
                    jSONObject.put("result", jSONObject2);
                }
                this.b.success(jSONObject.toString());
            } catch (JSONException unused) {
                com.huawei.hwcloudjs.f.d.b(k.f6244a, "InvokeResultCallback JSONException", true);
                this.b.failure();
            }
        }

        public d(WeakReference<HuaweiApiClient> weakReference, JsCallback jsCallback) {
            this.f6249a = weakReference;
            this.b = jsCallback;
        }
    }

    public static void a(String str, JsCallback jsCallback) {
        com.huawei.hwcloudjs.service.hms.a.a().a(9003, new a(str, jsCallback));
    }

    public static String a(SignInResult signInResult, boolean z) {
        String familyName;
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            if (z) {
                familyName = signInResult.getAuthHuaweiId().getAuthorizationCode();
                str = "serverAuthCode";
            } else {
                jSONObject.put("openId", signInResult.getAuthHuaweiId().getOpenId());
                jSONObject.put("accessToken", signInResult.getAuthHuaweiId().getAccessToken());
                jSONObject.put(CommonConstant.KEY_DISPLAY_NAME, signInResult.getAuthHuaweiId().getDisplayName());
                jSONObject.put("photoUrl", signInResult.getAuthHuaweiId().getAvatarUriString());
                jSONObject.put(CommonConstant.KEY_UNION_ID, signInResult.getAuthHuaweiId().getUnionId());
                jSONObject.put("email", signInResult.getAuthHuaweiId().getEmail());
                jSONObject.put(CommonConstant.KEY_GIVEN_NAME, signInResult.getAuthHuaweiId().getGivenName());
                familyName = signInResult.getAuthHuaweiId().getFamilyName();
                str = CommonConstant.KEY_FAMILY_NAME;
            }
            jSONObject.put(str, familyName);
        } catch (JSONException unused) {
            com.huawei.hwcloudjs.f.d.b(f6244a, "signInRes2Json put json error", true);
        }
        return jSONObject.toString();
    }
}
