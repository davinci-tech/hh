package com.huawei.hms.support.hwid.service;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.account.AccountNaming;
import com.huawei.hms.support.api.entity.common.CommonNaming;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.support.hwid.bean.AckQrLoginReq;
import com.huawei.hms.support.hwid.bean.CheckPasswordByUserIdReq;
import com.huawei.hms.support.hwid.bean.GetRealNameInfoReq;
import com.huawei.hms.support.hwid.bean.LoginInfoReq;
import com.huawei.hms.support.hwid.bean.SignInByQrReq;
import com.huawei.hms.support.hwid.bean.StartQrAuthReq;
import com.huawei.hms.support.hwid.bean.StartQrLoginReq;
import com.huawei.hms.support.hwid.c.c;
import com.huawei.hms.support.hwid.c.d;
import com.huawei.hms.support.hwid.c.e;
import com.huawei.hms.support.hwid.c.f;
import com.huawei.hms.support.hwid.c.h;
import com.huawei.hms.support.hwid.c.i;
import com.huawei.hms.support.hwid.c.j;
import com.huawei.hms.support.hwid.c.k;
import com.huawei.hms.support.hwid.c.l;
import com.huawei.hms.support.hwid.c.m;
import com.huawei.hms.support.hwid.c.n;
import com.huawei.hms.support.hwid.common.cloudservice.CloudRequestHandler;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.hms.support.hwid.common.e.g;
import com.huawei.hms.support.hwid.result.SignInQrInfo;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b implements HuaweiIdAdvancedService {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Integer> f6024a;
    private Context b;

    static {
        HashMap hashMap = new HashMap();
        f6024a = hashMap;
        hashMap.put(CommonNaming.queryRealNameInfo, 40004300);
        hashMap.put(CommonNaming.queryAccountInfo, 50200300);
        hashMap.put(CommonNaming.signInByQrCode, 60100300);
        hashMap.put(CommonNaming.startQrLogin, 60100300);
        hashMap.put("hwid.queryAccountChanged", 50001300);
        hashMap.put("hwid.getDeviceInfo", 50001300);
        hashMap.put("hwid.queryRealNameInfoNppa", 50200300);
        hashMap.put("hwid.checkPasswordByUserId", 50300300);
        hashMap.put("hwid.registerFilterForLogin", 60100300);
        hashMap.put("hwid.startQrAuth", 60100300);
        hashMap.put("hwid.getVerifyTokenByQrcode", 60600300);
        hashMap.put("hwid.login", 60700300);
    }

    public b(Context context) {
        this.b = context;
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> getAccountInfo(List<String> list) {
        return a(list);
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> getRealNameInfo() {
        return a();
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> getRealNameInfo(GetRealNameInfoReq getRealNameInfoReq) {
        if (getRealNameInfoReq == null || getRealNameInfoReq.getRequestChannelInfo() == null || getRealNameInfoReq.getRequestChannelExtraInfo() == null) {
            g.a("HuaweiIdAdvanceServiceImpl", "Parameter getRealNameInfoReq, requestChannelInfo or requestChannelExtraInfo is null.");
            return a("Parameter getRealNameInfoReq, requestChannelInfo or requestChannelExtraInfo is null.", 2005);
        }
        g.a("HuaweiIdAdvanceServiceImpl", "getRealNameInfoForNppa with realNameReqInfo.");
        return a(getRealNameInfoReq);
    }

    private Task<String> a(List<String> list) {
        g.a("HuaweiIdAdvanceServiceImpl", "accountInfoRequest entry.");
        if (this.b == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        List<String> b = b(list);
        if (b == null || b.isEmpty()) {
            g.b("HuaweiIdAdvanceServiceImpl", "requestScopes is empty or null.");
            return a((b) "{}");
        }
        String reportEntry = HiAnalyticsClient.reportEntry(this.b, CommonNaming.queryAccountInfo, AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("accountInfoTransId", reportEntry);
            jSONObject.put("scopes", c(b));
            return a((TaskApiCall) new c(CommonNaming.queryAccountInfo, jSONObject.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "JSONException.");
            return a("build json error", 2005);
        }
    }

    private Task a(GetRealNameInfoReq getRealNameInfoReq) {
        g.a("HuaweiIdAdvanceServiceImpl", "realNameInfoRequestForNppa entry.");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        if (getRealNameInfoReq == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "getRealNameInfoReq is null.");
            return a("getRealNameInfoReq getRealNameInfoReq is null.", 2005);
        }
        try {
            getRealNameInfoReq.setTransId(HiAnalyticsClient.reportEntry(context, "hwid.queryRealNameInfoNppa", AuthInternalPickerConstant.HMS_SDK_VERSION));
            return a((TaskApiCall) new h("hwid.queryRealNameInfoNppa", getRealNameInfoReq.getGetRealNameInfoReqJsonString() != null ? getRealNameInfoReq.getGetRealNameInfoReqJsonString() : "", 50200300));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "JSONException.");
            return a("build json error", 2005);
        }
    }

    private Task a() {
        g.a("HuaweiIdAdvanceServiceImpl", "realNameInfoRequest entry.");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, CommonNaming.queryRealNameInfo, AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommonConstant.REALNAMEREQUEST.REQUEST_TRANS_ID, reportEntry);
            return a((TaskApiCall) new i(CommonNaming.queryRealNameInfo, jSONObject.toString(), 40004300));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "JSONException.");
            return a("build json error", 2005);
        }
    }

    private <TResult, TClient extends AnyClient> Task<TResult> a(TaskApiCall<TClient, TResult> taskApiCall) {
        g.a("HuaweiIdAdvanceServiceImpl", "innerRequest by taskApiCall entry.");
        try {
            return a(this.b, new Api.ApiOptions.NoOptions(), new Api(HuaweiApiAvailability.HMS_API_NAME_ID), new com.huawei.hms.support.hwid.a.b(), "").doWrite(taskApiCall);
        } catch (com.huawei.hms.support.hwid.b.a e) {
            g.b("HuaweiIdAdvanceServiceImpl", "InvalidVersionException.");
            return a(e.a().getErrorString(), e.a().getStatusCode());
        }
    }

    private List<String> b(List<String> list) {
        if (list == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "scopes is null.");
            return null;
        }
        HashSet hashSet = new HashSet();
        for (String str : list) {
            if (str != null) {
                String trim = str.trim();
                if ("https://www.huawei.com/auth/account/mobile.number".equals(trim) || "https://www.huawei.com/auth/account/mobile.flag".equals(trim) || CommonConstant.SCOPE.SCOPE_LOGIN_ACCOUNT.equals(trim) || "https://www.huawei.com/auth/account/base.profile".equals(trim)) {
                    hashSet.add(trim);
                } else {
                    g.b("HuaweiIdAdvanceServiceImpl", "invalid scope.");
                }
            } else {
                g.b("HuaweiIdAdvanceServiceImpl", "null scope.");
            }
        }
        return new ArrayList(hashSet);
    }

    private String c(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jSONArray.put(list.get(i));
        }
        return jSONArray.toString();
    }

    private <TResult> Task<TResult> a(String str, int i) {
        g.a("HuaweiIdAdvanceServiceImpl", str);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(new ApiException(new Status(i, str)));
        return taskCompletionSource.getTask();
    }

    private <TResult> Task<TResult> a(TResult tresult) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(tresult);
        return taskCompletionSource.getTask();
    }

    private boolean a(Context context) {
        boolean z = context instanceof Activity;
        g.a("HuaweiIdAdvanceServiceImpl", "check context result:" + z);
        return z;
    }

    private <TOption extends Api.ApiOptions> HuaweiApi a(Context context, TOption toption, Api<TOption> api, AbstractClientBuilder abstractClientBuilder, String str) throws com.huawei.hms.support.hwid.b.a {
        if (a(context)) {
            g.a("HuaweiIdAdvanceServiceImpl", "entry initService activity.");
            return new HuaweiApi((Activity) context, (Api) api, (Api.ApiOptions) toption, abstractClientBuilder, AuthInternalPickerConstant.HMS_SDK_VERSION);
        }
        g.a("HuaweiIdAdvanceServiceImpl", "entry initService context.");
        return new HuaweiApi(context, api, toption, abstractClientBuilder, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<SignInQrInfo> startQrLogin(StartQrLoginReq startQrLoginReq) {
        g.a("HuaweiIdAdvanceServiceImpl", "startQrLogin");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, CommonNaming.startQrLogin, AuthInternalPickerConstant.HMS_SDK_VERSION);
        try {
            JSONObject json = startQrLoginReq.toJson();
            json.put("transId", reportEntry);
            m mVar = new m(CommonNaming.startQrLogin, json.toString(), reportEntry);
            mVar.a(CommonConstant.StartQrLoginQrValue.QRSCENE_RECHECK_USER.equals(startQrLoginReq.getQrScene()));
            return a((TaskApiCall) mVar);
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "startQrLogin JSONException");
            return a("build json error", 2005);
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<Void> signInByQrCode(SignInByQrReq signInByQrReq) {
        g.a("HuaweiIdAdvanceServiceImpl", "signInByQrCode");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, CommonNaming.signInByQrCode, AuthInternalPickerConstant.HMS_SDK_VERSION);
        try {
            JSONObject json = signInByQrReq.toJson();
            json.put("transId", reportEntry);
            return a((TaskApiCall) new k(CommonNaming.signInByQrCode, json.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "2015JSONException");
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(new Status(2015)));
            return taskCompletionSource.getTask();
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> hasAccountChanged(String str, String str2) {
        g.a("HuaweiIdAdvanceServiceImpl", "queryAccountChanged");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "queryAccountChanged activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, "hwid.queryAccountChanged", AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("openId", str);
            jSONObject.put("uid", str2);
            jSONObject.put("transId", reportEntry);
            return a((TaskApiCall) new com.huawei.hms.support.hwid.c.g("hwid.queryAccountChanged", jSONObject.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "queryAccountChanged JSONException");
            return a("build json error", 2005);
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> getDeviceInfo() {
        g.a("HuaweiIdAdvanceServiceImpl", "getDeviceInfo");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "getDeviceInfo activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, "hwid.getDeviceInfo", AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("transId", reportEntry);
            return a((TaskApiCall) new d("hwid.getDeviceInfo", jSONObject.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "getDeviceInfo JSONException");
            return a("build json error", 2005);
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<Void> logout() {
        g.a("HuaweiIdAdvanceServiceImpl", "logout");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "logout activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, AccountNaming.logout, AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("transId", reportEntry);
            return a((TaskApiCall) new e(AccountNaming.logout, jSONObject.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "logout JSONException");
            return a("build json error", 2005);
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<Void> login(LoginInfoReq loginInfoReq) {
        boolean z;
        g.a("HuaweiIdAdvanceServiceImpl", "login entry.", true);
        Context context = this.b;
        if (context == null || loginInfoReq == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, "hwid.login", AuthInternalPickerConstant.HMS_SDK_VERSION);
        String a2 = com.huawei.hms.support.hwid.common.d.a.a(this.b).a("public-key", "");
        if (!TextUtils.isEmpty(a2)) {
            g.a("HuaweiIdAdvanceServiceImpl", "sp is useable");
            com.huawei.hms.support.hwid.common.e.d.a().a(this.b, reportEntry, loginInfoReq.getPackageName());
        } else {
            g.a("HuaweiIdAdvanceServiceImpl", "sp is unuseable" + System.currentTimeMillis());
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            com.huawei.hms.support.hwid.common.e.d.a().a(this.b, reportEntry, loginInfoReq.getPackageName(), new com.huawei.hms.support.hwid.result.c<com.huawei.hms.support.hwid.result.a>() { // from class: com.huawei.hms.support.hwid.service.b.1
                @Override // com.huawei.hms.support.hwid.result.c
                public void a(com.huawei.hms.support.hwid.result.a aVar) {
                    if (aVar.a().a() == 0) {
                        g.a("HuaweiIdAdvanceServiceImpl", "get publicKey success " + System.currentTimeMillis(), true);
                    } else {
                        g.a("HuaweiIdAdvanceServiceImpl", "get publicKey fail " + System.currentTimeMillis(), true);
                    }
                    countDownLatch.countDown();
                }
            });
            try {
                z = countDownLatch.await(10L, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                g.c("HuaweiIdAdvanceServiceImpl", "checkPermission InterruptedException.", true);
                z = false;
            }
            g.b("HuaweiIdAdvanceServiceImpl", "getDomainNameAndPublicKey awaitValue:" + z, true);
            if (!z) {
                return a("get publicKey fail", 2015);
            }
            a2 = com.huawei.hms.support.hwid.common.d.a.a(this.b).a("public-key", "");
        }
        loginInfoReq.setPassword(com.huawei.hms.support.hwid.common.b.b.a(loginInfoReq.getPassword(), a2));
        new JSONObject();
        try {
            JSONObject json = loginInfoReq.toJson();
            json.put("transId", reportEntry);
            return a((TaskApiCall) new f("hwid.login", json.toString(), reportEntry));
        } catch (JSONException unused2) {
            g.b("HuaweiIdAdvanceServiceImpl", "2015 JSONException");
            return a("build json error", 2015);
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public void checkPasswordByUserId(Context context, CheckPasswordByUserIdReq checkPasswordByUserIdReq, CloudRequestHandler cloudRequestHandler) {
        g.a("HuaweiIdAdvanceServiceImpl", "begin checkPasswordByUserId");
        com.huawei.hms.support.hwid.common.a.a().a(context);
        a.a(context, checkPasswordByUserIdReq, cloudRequestHandler);
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> registerFilterForLogin(String str, HashMap<String, String> hashMap, List<String> list, int i, String str2) {
        g.a("HuaweiIdAdvanceServiceImpl", "registerFilterFourLogin entry.");
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, "hwid.registerFilterForLogin", AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject(hashMap);
            jSONObject.put("appID", str);
            jSONObject.put("options", jSONObject2);
            jSONObject.put("callbackParams", d(list));
            jSONObject.put("callbackType", i);
            jSONObject.put(ParamConstants.Param.CALLBACK, str2);
            jSONObject.put("transId", reportEntry);
            jSONObject.put("packageName", this.b.getPackageName());
            return a((TaskApiCall) new j("hwid.registerFilterForLogin", jSONObject.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "JSONException.");
            return a("build json error", 2005);
        }
    }

    private JSONArray d(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            jSONArray.put(list.get(i));
        }
        return jSONArray;
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> startQrAuth(StartQrAuthReq startQrAuthReq) {
        g.a("HuaweiIdAdvanceServiceImpl", "enter startQrAuth");
        Context context = this.b;
        if (context == null || startQrAuthReq == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "startQrAuth context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, "hwid.startQrAuth", AuthInternalPickerConstant.HMS_SDK_VERSION);
        try {
            JSONObject json = startQrAuthReq.toJson();
            json.put("transId", reportEntry);
            return a((TaskApiCall) new l("hwid.startQrAuth", json.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "startQrAuth JSONException");
            return a("build json error", 2005);
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> ackQrLogin(AckQrLoginReq ackQrLoginReq) {
        g.a("HuaweiIdAdvanceServiceImpl", "enter ackQrLogin");
        Context context = this.b;
        if (context == null || ackQrLoginReq == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "context or ackQrLoginReq is null.");
            return a("context or sessionId is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, "hwid.ackQrLogin", AuthInternalPickerConstant.HMS_SDK_VERSION);
        try {
            JSONObject json = ackQrLoginReq.toJson();
            json.put("transId", reportEntry);
            return a((TaskApiCall) new com.huawei.hms.support.hwid.c.a("hwid.ackQrLogin", json.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "2015JSONException");
            new TaskCompletionSource().setException(new ApiException(new Status(2015)));
            return a("build json error", 2005);
        }
    }

    @Override // com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService
    public Task<String> getVerifyTokenByQrcode(String str) {
        g.a("HuaweiIdAdvanceServiceImpl", "getVerifyTokenByQrcode, sessionId is null ? " + TextUtils.isEmpty(str));
        Context context = this.b;
        if (context == null) {
            g.b("HuaweiIdAdvanceServiceImpl", "activity or context is null.");
            return a("activity or context is null.", 2005);
        }
        String reportEntry = HiAnalyticsClient.reportEntry(context, "hwid.getVerifyTokenByQrcode", AuthInternalPickerConstant.HMS_SDK_VERSION);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sessionId", str);
            return a((TaskApiCall) new n("hwid.getVerifyTokenByQrcode", jSONObject.toString(), reportEntry));
        } catch (JSONException unused) {
            g.b("HuaweiIdAdvanceServiceImpl", "2015JSONException");
            new TaskCompletionSource().setException(new ApiException(new Status(2015)));
            return a("build json error", 2005);
        }
    }
}
