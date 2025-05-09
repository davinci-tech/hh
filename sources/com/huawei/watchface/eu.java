package com.huawei.watchface;

import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.base.common.MediaType;
import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.videoedit.sysc.Consumer;
import com.huawei.watchface.videoedit.sysc.Optional;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class eu {
    private volatile et b;
    private volatile RestClient c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f11018a = false;
    private boolean d = false;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final eu f11020a = new eu();
    }

    public static eu a() {
        return a.f11020a;
    }

    public RestClient b() {
        if (this.c == null) {
            synchronized (eu.class) {
                d();
            }
        }
        return this.c;
    }

    public et c() {
        RestClient b;
        if (this.b == null) {
            synchronized (eu.class) {
                if (this.b == null && (b = b()) != null) {
                    this.b = (et) b.create(et.class);
                }
            }
        }
        return this.b;
    }

    public void d() {
        if (this.f11018a) {
            HwLog.i("NetworkKitClient", "isInit");
            return;
        }
        try {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            NetworkKit.init(Environment.getApplicationContext(), new NetworkKit.Callback() { // from class: com.huawei.watchface.eu.1
                @Override // com.huawei.hms.network.NetworkKit.Callback
                public void onResult(boolean z) {
                    eu.this.f11018a = z;
                    eu.this.d = !z;
                    HwLog.i("NetworkKitClient", "init time:" + (SystemClock.elapsedRealtime() - elapsedRealtime) + ",result:" + z);
                    if (eu.this.f11018a) {
                        eu.this.e();
                    }
                }
            });
            this.c = new RestClient.Builder().httpClient(new HttpClient.Builder().addNetworkInterceptor((Interceptor) new es()).build()).build();
        } catch (Exception e) {
            HwLog.e("NetworkKitClient", "init error : " + HwLog.printException(e));
            this.d = true;
        } catch (NoClassDefFoundError e2) {
            HwLog.e("NetworkKitClient", "init NoClassDefFoundError: " + HwLog.printException((Error) e2));
            this.d = true;
        } catch (VerifyError e3) {
            HwLog.e("NetworkKitClient", "init VerifyError: " + HwLog.printException((Error) e3));
            this.d = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        boolean b = NewSystemParamManager.b("client_watchface_network_config_ipv6", "1", true);
        HwLog.i("NetworkKitClient", "setNetworkKitOptions ipv6 enable:" + b);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("enable_ipv6_preferred", String.valueOf(b));
        } catch (Exception e) {
            HwLog.e("NetworkKitClient", "setNetworkKit error : " + HwLog.printException(e));
        }
        NetworkKit.getInstance().setOptions(jSONObject.toString());
        NetworkKit.getInstance().initConnectionPool(100, 120L, TimeUnit.SECONDS);
    }

    public ev a(String str, String str2, LinkedHashMap<String, String> linkedHashMap, String str3, eh ehVar) {
        Response<String> response;
        final ev evVar = new ev();
        evVar.b(str);
        et c = c();
        if (!this.d && c != null) {
            if (linkedHashMap == null) {
                try {
                    linkedHashMap = new LinkedHashMap<>();
                } catch (Exception e) {
                    HwLog.e("NetworkKitClient", "postRequest commonService request Exception =" + HwLog.printException(e));
                    ehVar.a(CommonUtils.a((Throwable) e));
                    if (!CommonUtils.f()) {
                        f();
                    } else {
                        a(str);
                    }
                    response = null;
                }
            }
            RequestBody a2 = a(linkedHashMap, str2);
            if (TextUtils.isEmpty(str3)) {
                response = c.a(str, linkedHashMap, a2).execute();
            } else {
                response = c.a(str, linkedHashMap, a2, str3).execute();
            }
            if (response == null) {
                HwLog.e("NetworkKitClient", "postRequest response is null");
            }
            Optional.ofNullable(response).ifPresent(new Consumer() { // from class: com.huawei.watchface.eu$$ExternalSyntheticLambda2
                @Override // com.huawei.watchface.videoedit.sysc.Consumer
                public final void accept(Object obj) {
                    eu.a(ev.this, (Response) obj);
                }
            });
            return evVar;
        }
        String b = WatchFaceHttpUtil.b(str, str2, linkedHashMap);
        ehVar.a("doPostHttpsURLConnection");
        evVar.a(b);
        return evVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(ev evVar, Response response) {
        evVar.a(response.getHeaders());
        evVar.a(response.getCode());
        evVar.a((String) response.getBody());
        HwLog.i("NetworkKitClient", "postRequest Response code =" + evVar.c());
    }

    private void f() {
        final Activity activity = WebViewActivity.getActivity();
        if (activity == null || !(activity instanceof WebViewActivity)) {
            return;
        }
        BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.eu$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                eu.b(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Activity activity) {
        ((WebViewActivity) activity).showNoNetworkPage();
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("NetworkKitClient", "showErrorPage url is empty");
            return;
        }
        if (!str.contains(WatchFaceHttpUtil.d()) && !str.contains(WatchFaceHttpUtil.h())) {
            HwLog.i("NetworkKitClient", "showErrorPage url is not sign or online state");
            return;
        }
        final Activity activity = WebViewActivity.getActivity();
        if (activity == null || !(activity instanceof WebViewActivity)) {
            return;
        }
        BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.eu$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                eu.a(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Activity activity) {
        ((WebViewActivity) activity).showWatchFaceNetworkRetryPage();
    }

    private RequestBody a(LinkedHashMap<String, String> linkedHashMap, String str) {
        String a2 = dg.a((Map<String, String>) linkedHashMap, "Content-Type");
        if (TextUtils.isEmpty(a2)) {
            return RequestBodyProviders.create(MediaType.parse("application/json"), str);
        }
        if (a2.contains(com.huawei.hms.framework.network.restclient.hwhttp.RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED)) {
            return RequestBodyProviders.create(MediaType.parse(com.huawei.hms.framework.network.restclient.hwhttp.RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED), str);
        }
        return RequestBodyProviders.create(MediaType.parse("application/json"), str);
    }
}
