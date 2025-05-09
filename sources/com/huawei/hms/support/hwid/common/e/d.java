package com.huawei.hms.support.hwid.common.e;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsApi;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes9.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final d f6003a = new d();
    private com.huawei.hms.support.hwid.result.c<com.huawei.hms.support.hwid.result.a> d;
    private Context g;
    private JSONObject h;
    private JSONObject i;
    private String b = "";
    private String c = "";
    private boolean e = false;
    private boolean f = false;
    private boolean j = false;

    public static d a() {
        return f6003a;
    }

    public void a(Context context, String str, String str2, com.huawei.hms.support.hwid.result.c<com.huawei.hms.support.hwid.result.a> cVar) {
        this.b = str;
        this.c = str2;
        this.d = cVar;
        this.g = context;
        new Thread(new Runnable() { // from class: com.huawei.hms.support.hwid.common.e.d.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Map<String, String> synGetGrsUrls = GrsApi.synGetGrsUrls("com.huawei.cloud.hwid");
                    if (synGetGrsUrls != null) {
                        String str3 = synGetGrsUrls.get("Root");
                        if (TextUtils.isEmpty(str3)) {
                            g.b("GetDomainNameUtil", "url is empty exit");
                            d.this.a(2003, "url is empty exit");
                            f.a(d.this.b, "hwid.checkPasswordByUserId", 2003, d.this.c);
                            return;
                        } else {
                            g.a("GetDomainNameUtil", "get domain url from grs success");
                            d.this.a(str3, "siteDomain");
                            d.this.a(str3, "upLogin503");
                            return;
                        }
                    }
                    g.b("GetDomainNameUtil", "urlMap null");
                    d.this.a(2003, "urlMap null");
                    f.a(d.this.b, "hwid.checkPasswordByUserId", 2003, d.this.c);
                } catch (NoClassDefFoundError unused) {
                    g.b("GetDomainNameUtil", "NoClassDefFoundError");
                    d.this.a(2003, "NoClassDefFoundError");
                    f.a(d.this.b, "hwid.checkPasswordByUserId", 2003, d.this.c);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, final String str2) {
        g.a("GetDomainNameUtil", "getSiteDomianFromServer");
        RestClientGlobalInstance.getInstance().init(this.g);
        RestClient build = new RestClient.Builder(this.g).baseUrl(str).build();
        if (build == null) {
            a(2003, "restClient is null");
            f.a(this.b, "hwid.checkPasswordByUserId", 2003, this.c);
            return;
        }
        a aVar = (a) build.create(a.class);
        final com.huawei.hms.support.hwid.common.c.a.a aVar2 = new com.huawei.hms.support.hwid.common.c.a.a(this.g, str2);
        try {
            aVar.a(aVar2.d(), RequestBody.create(" text/html; charset=utf-8", aVar2.e().getBytes("UTF-8"))).enqueue(new ResultCallback<ResponseBody>() { // from class: com.huawei.hms.support.hwid.common.e.d.2
                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onResponse(Response<ResponseBody> response) {
                    g.a("GetDomainNameUtil", "request getResource for siteDomain success");
                    d.this.a(aVar2, response, str2);
                }

                @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                public void onFailure(Throwable th) {
                    g.a("GetDomainNameUtil", "request getResource for siteDomain onFailure");
                    d.this.a(2016, "request getResource for siteDomain onFailure");
                    f.a(d.this.b, "hwid.checkPasswordByUserId", 2016, d.this.c);
                }
            });
        } catch (IOException unused) {
            g.a("GetDomainNameUtil", "request getResource for siteDomain IOException");
            a(2015, "request getResource for siteDomain IOException");
            f.a(this.b, "hwid.checkPasswordByUserId", 2015, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.hms.support.hwid.common.c.a.a aVar, Response<ResponseBody> response, String str) {
        g.a("GetDomainNameUtil", "handleRequestNet");
        int code = response.getCode();
        g.a("GetDomainNameUtil", "code:" + code);
        if (code == 200) {
            g.a("GetDomainNameUtil", "GetResourceRequest success");
            try {
                aVar.a(new String(response.getBody().bytes(), "UTF-8"));
                String f = aVar.f();
                if (!TextUtils.isEmpty(f) && f != null) {
                    g.a("GetDomainNameUtil", "get getResourceResponseJson success");
                    JSONObject jSONObject = new JSONObject(f);
                    if (str.equals("siteDomain")) {
                        this.h = jSONObject;
                        this.e = true;
                    } else {
                        this.i = jSONObject;
                        this.f = true;
                    }
                    b();
                    return;
                }
                g.a("GetDomainNameUtil", "getResourceResponseJson is null, errorcode is " + aVar.g());
                g.a("GetDomainNameUtil", "getResourceResponseJson is null, errorcode is " + aVar.g() + " errorMsg: " + aVar.h(), false);
                a(2016, "getResourceResponseJson is null");
                f.a(this.b, "hwid.checkPasswordByUserId", 2016, this.c);
                return;
            } catch (IOException unused) {
                g.a("GetDomainNameUtil", "IOException");
                a(2015, "IOException");
                f.a(this.b, "hwid.checkPasswordByUserId", 2015, this.c);
                return;
            } catch (JSONException unused2) {
                g.a("GetDomainNameUtil", "JSONException");
                a(2015, "JSONException");
                f.a(this.b, "hwid.checkPasswordByUserId", 2015, this.c);
                return;
            } catch (XmlPullParserException unused3) {
                g.a("GetDomainNameUtil", "XmlPullParserException");
                a(2015, "XmlPullParserException");
                f.a(this.b, "hwid.checkPasswordByUserId", 2015, this.c);
                return;
            }
        }
        f.a(this.b, "hwid.checkPasswordByUserId", code, this.c);
        a(2016, "request error");
    }

    public void a(final Context context, final String str, final String str2) {
        if (a(context)) {
            new Thread(new Runnable() { // from class: com.huawei.hms.support.hwid.common.e.d.3
                @Override // java.lang.Runnable
                public void run() {
                    d.this.a(context, str, str2, new com.huawei.hms.support.hwid.result.c<com.huawei.hms.support.hwid.result.a>() { // from class: com.huawei.hms.support.hwid.common.e.d.3.1
                        @Override // com.huawei.hms.support.hwid.result.c
                        public void a(com.huawei.hms.support.hwid.result.a aVar) {
                            com.huawei.hms.support.hwid.result.d a2 = aVar.a();
                            if (a2.a() == 0) {
                                g.a("GetDomainNameUtil", "updateSiteDomain success: ", true);
                                f.a(d.this.b, "hwid.checkPasswordByUserId", 0, d.this.c);
                            } else {
                                g.b("GetDomainNameUtil", "updateSiteDomain fail: ", true);
                                f.a(d.this.b, "hwid.checkPasswordByUserId", a2.a(), d.this.c);
                            }
                        }
                    });
                }
            }).start();
        }
    }

    private boolean a(Context context) {
        long j;
        g.a("GetDomainNameUtil", "check needUpdateProp");
        try {
            j = Long.parseLong(com.huawei.hms.support.hwid.common.d.a.a(context).a("lastupdate", ""));
        } catch (NumberFormatException unused) {
            j = 0;
        }
        long time = new Date().getTime();
        if (j > time) {
            j = 0;
        }
        if (j == 0 || time - j > 86400000) {
            g.a("GetDomainNameUtil", "need update siteDomainName file");
            return true;
        }
        g.a("GetDomainNameUtil", "no need update siteDomainName file");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        com.huawei.hms.support.hwid.result.a aVar = new com.huawei.hms.support.hwid.result.a(new com.huawei.hms.support.hwid.result.d(i, str));
        com.huawei.hms.support.hwid.result.c<com.huawei.hms.support.hwid.result.a> cVar = this.d;
        if (cVar == null) {
            return;
        }
        if (!this.j) {
            this.j = true;
            cVar.a(aVar);
        }
        g.a("GetDomainNameUtil", "hasOnCallback is:" + this.j);
    }

    private void b() {
        g.a("GetDomainNameUtil", "enter callback");
        if (this.e && this.f) {
            g.a("GetDomainNameUtil", "request siteDomain and publickey all finish");
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("public-key", this.i.optString("public-key"));
            hashMap.put("1", this.h.optString("as_1"));
            hashMap.put("5", this.h.optString("as_5"));
            hashMap.put("7", this.h.optString("as_7"));
            hashMap.put("8", this.h.optString("as_8"));
            hashMap.put("lastupdate", String.valueOf(System.currentTimeMillis()));
            com.huawei.hms.support.hwid.common.d.a.a(this.g).a(hashMap);
            g.a("GetDomainNameUtil", "save publickey and siteDomain success");
            a(0, "save publickey and siteDomain success");
            f.a(this.b, "hwid.checkPasswordByUserId", 0, this.c);
            return;
        }
        g.a("GetDomainNameUtil", "request siteDomain and publickey no finish");
    }
}
