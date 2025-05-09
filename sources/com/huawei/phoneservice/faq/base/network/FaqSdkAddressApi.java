package com.huawei.phoneservice.faq.base.network;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.entity.ModuleConfigRequest;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.r;
import defpackage.uhy;
import defpackage.uib;

/* loaded from: classes5.dex */
public final class FaqSdkAddressApi extends FaqRestClient {

    /* renamed from: a, reason: collision with root package name */
    public static final c f8232a = new c(null);
    private static Context b;
    private static volatile FaqSdkAddressApi d;
    private final String c;
    private final String e;
    private Context i;

    public final Submit d(com.huawei.phoneservice.faq.base.entity.c cVar, Callback callback) {
        uhy.e((Object) cVar, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.i);
        uhy.d(initRestClientAnno);
        Context context = b;
        String b2 = b();
        String json = getGson().toJson(cVar);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, b2, json, callback);
    }

    public final Submit b(ModuleConfigRequest moduleConfigRequest, Callback callback) {
        uhy.e((Object) moduleConfigRequest, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.i);
        uhy.d(initRestClientAnno);
        Context context = b;
        String a2 = r.a(this.e);
        uhy.a(a2, "");
        String json = getGson().toJson(moduleConfigRequest);
        uhy.a(json, "");
        return initRestClientAnno.asyncRequest(context, a2, json, callback);
    }

    private final String b() {
        StringBuilder sb;
        String str;
        if (uhy.e((Object) "Y", (Object) j.c().getSdk(FaqConstants.FAQ_USE_OLD_DOMAIN))) {
            sb = new StringBuilder();
            sb.append(r.a());
            str = "/secured/CCPC/EN/";
        } else {
            sb = new StringBuilder();
            sb.append(r.c());
            str = "/forward/ccpc_gateway_sdk/";
        }
        sb.append(str);
        sb.append(this.c);
        sb.append("/1");
        return sb.toString();
    }

    public static final class c {
        public final FaqSdkAddressApi e(Context context) {
            FaqSdkAddressApi.b = context != null ? context.getApplicationContext() : null;
            if (FaqSdkAddressApi.d == null) {
                FaqSdkAddressApi.d = new FaqSdkAddressApi(FaqSdkAddressApi.b);
            }
            return FaqSdkAddressApi.d;
        }

        public /* synthetic */ c(uib uibVar) {
            this();
        }

        private c() {
        }
    }

    public FaqSdkAddressApi(Context context) {
        super(context);
        this.c = "ccpc/queryRoutesInfo";
        this.e = "ccpc/queryModuleList";
        this.i = context;
    }
}
