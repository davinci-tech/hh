package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealthservice.util.HttpRequestApi;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import health.compact.a.GRSManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class ipb {
    public static final String b = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + ipo.d, "");
    static String c = "Authorization";

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Object, Submit<String>> f13527a;
    private int d;
    private Handler e;

    private ipb() {
        this.f13527a = new ConcurrentHashMap<>();
        this.e = new Handler(Looper.getMainLooper());
    }

    public static ipb d() {
        return d.c;
    }

    public <T> void c(Object obj, ipj<T> ipjVar) {
        if (ipjVar == null) {
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.d("HiH_HealthKitRequestManager", "Get AT from cache is empty, refresh at");
            while (TextUtils.isEmpty(accountInfo)) {
                int i = this.d;
                if (i <= 2) {
                    int i2 = i + 1;
                    this.d = i2;
                    LogUtil.a("HealthKitRequestManager", "Refresh AT again ", Integer.valueOf(i2));
                    accountInfo = HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
                } else {
                    this.d = 0;
                    c(ipjVar, -1, new Exception("get at err"));
                    ReleaseLogUtil.c("HiH_HealthKitRequestManager", "Finally, get at is empty, request error");
                    return;
                }
            }
        }
        this.d = 0;
        if (TextUtils.isEmpty(ipjVar.c()) || !ipjVar.c().startsWith("http")) {
            ReleaseLogUtil.c("HiH_HealthKitRequestManager", "url is err");
            c(ipjVar, -1, new Exception("url is err"));
        } else {
            LogUtil.a("HealthKitRequestManager", "HealthKitRequestManager get AT success and request http start");
            b(ipjVar, accountInfo, obj);
        }
    }

    private <T> void b(ipj<T> ipjVar, String str, Object obj) {
        String d2 = ipjVar.d();
        d2.hashCode();
        if (d2.equals("GET")) {
            a(ipjVar, str, obj);
        } else if (d2.equals(ProfileRequestConstants.PUT_TYPE)) {
            c(ipjVar, str, obj);
        } else {
            LogUtil.h("HealthKitRequestManager", "the request is not support:", ipjVar.d());
        }
    }

    private <T> void c(ipj<T> ipjVar, String str, Object obj) {
        HttpRequestApi httpRequestApi = (HttpRequestApi) lqi.d().b(HttpRequestApi.class);
        if (httpRequestApi == null) {
            ReleaseLogUtil.c("HiH_HealthKitRequestManager", "HttpRequestApi is null");
            return;
        }
        Submit<String> putReq = httpRequestApi.putReq(ipjVar.c(), b(str), RequestBody.create(MediaType.parse("application/json"), lql.b(ipjVar.a())));
        this.f13527a.put(obj, putReq);
        c(ipjVar, obj, putReq);
    }

    private <T> void a(ipj<T> ipjVar, String str, Object obj) {
        HttpRequestApi httpRequestApi = (HttpRequestApi) lqi.d().b(HttpRequestApi.class);
        if (httpRequestApi == null) {
            ReleaseLogUtil.c("HiH_HealthKitRequestManager", "HttpRequestApi is null");
            return;
        }
        Submit<String> req = httpRequestApi.getReq(ipjVar.c(), b(str), ipjVar.e());
        this.f13527a.put(obj, req);
        c(ipjVar, obj, req);
    }

    private <T> void c(final ipj<T> ipjVar, final Object obj, final Submit<String> submit) {
        final long currentTimeMillis = System.currentTimeMillis();
        submit.enqueue(new ResultCallback<String>() { // from class: ipb.1
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                LogUtil.a("HealthKitRequestManager", "Http request needs ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " ms");
                ipb.this.b(ipjVar, response, obj);
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.c("HiH_HealthKitRequestManager", "http request onFailure ");
                if (submit.isCanceled()) {
                    return;
                }
                ipb.this.c(ipjVar, -1, th);
                lqi.d().e(submit);
            }
        });
    }

    public void e(Object obj) {
        if (!this.f13527a.containsKey(obj)) {
            LogUtil.a("HealthKitRequestManager", "Kit http manager not contains tag");
            return;
        }
        Submit<String> remove = this.f13527a.remove(obj);
        if (remove.isExecuted()) {
            return;
        }
        remove.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> void b(final ipj<T> ipjVar, Response<String> response, Object obj) {
        if (response.isSuccessful() && response.getRawResponse().getBody() != null) {
            String body = response.getRawResponse().getBody();
            if (ipjVar.b() != null) {
                final Object fromJson = new Gson().fromJson(body, ipjVar.b().getGenericType());
                if (ipjVar.f()) {
                    ipjVar.b().onSuccess(fromJson);
                } else {
                    this.e.post(new Runnable() { // from class: ipf
                        @Override // java.lang.Runnable
                        public final void run() {
                            ipj.this.b().onSuccess(fromJson);
                        }
                    });
                }
            }
            this.d = 0;
            return;
        }
        if (response.getCode() == 401 && this.d < 2) {
            ReleaseLogUtil.e("HiH_HealthKitRequestManager", "AT is expired, refresh at");
            this.d++;
            d(ipjVar, obj);
        } else {
            ReleaseLogUtil.c("HiH_HealthKitRequestManager", "Request error, response code = ", Integer.valueOf(response.getCode()));
            this.d = 0;
            c(ipjVar, response.getCode(), new Exception(response.getMessage()));
        }
    }

    private <T> void d(ipj<T> ipjVar, Object obj) {
        a(ipjVar, HealthAccessTokenUtil.getAtInstance().refreshAccessToken(), obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void c(final ipj<T> ipjVar, final int i, final Throwable th) {
        if (ipjVar.b() != null) {
            if (ipjVar.f()) {
                ipjVar.b().onFail(i, th);
            } else {
                this.e.post(new Runnable() { // from class: ipi
                    @Override // java.lang.Runnable
                    public final void run() {
                        ipj.this.b().onFail(i, th);
                    }
                });
            }
        }
    }

    private Map<String, Object> b(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-type", "application/json");
        hashMap.put("Cache-Control", " no-cache");
        hashMap.put(c, HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + str);
        return hashMap;
    }

    static class d {
        private static final ipb c = new ipb();
    }
}
