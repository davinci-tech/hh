package com.huawei.openalliance.ad.inter;

import android.content.Context;
import android.location.Location;
import android.util.Pair;
import com.huawei.hms.ads.jsb.inner.data.a;
import com.huawei.openalliance.ad.beans.inner.ApiReqParam;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.ek;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.m;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class AdContentRequestFactory {
    public static void prepareRequestParam(final Context context, final RequestOptions requestOptions) {
        try {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.AdContentRequestFactory.1
                @Override // java.lang.Runnable
                public void run() {
                    Context context2 = context;
                    RequestOptions requestOptions2 = requestOptions;
                    if (requestOptions2 == null) {
                        requestOptions2 = new RequestOptions();
                    }
                    AdContentRequestFactory.getAdRequestParameters(context2, 4, requestOptions2);
                }
            });
        } catch (Throwable th) {
            ho.c("AdContentRequestFactory", "prepare Param err, %s", th.getClass().getSimpleName());
        }
    }

    public static String getAdRequestParameters(Context context, String[] strArr, int i, int i2, RequestOptions requestOptions, ApiReqParam apiReqParam) {
        return a(context, strArr, i, i2, requestOptions, null, apiReqParam);
    }

    public static String getAdRequestParameters(Context context, String[] strArr, int i, int i2, RequestOptions requestOptions) {
        return a(context, strArr, i, i2, requestOptions, null, null);
    }

    public static String getAdRequestParameters(Context context, String[] strArr, int i, int i2, Location location, RequestOptions requestOptions) {
        a.C0078a c0078a = new a.C0078a();
        c0078a.a(location);
        return a(context, strArr, i, i2, requestOptions, c0078a.a(), null);
    }

    public static String getAdRequestParameters(Context context, int i, RequestOptions requestOptions, ApiReqParam apiReqParam) {
        return a(context, new String[0], -1, i, requestOptions, null, apiReqParam);
    }

    public static String getAdRequestParameters(Context context, int i, RequestOptions requestOptions) {
        return getAdRequestParameters(context, new String[0], -1, i, requestOptions);
    }

    public static String a(Context context, String[] strArr, int i, int i2, RequestOptions requestOptions, com.huawei.hms.ads.jsb.inner.data.a aVar, ApiReqParam apiReqParam) {
        String str;
        ho.b("AdContentRequestFactory", "getAdRequestParameters");
        if (context == null) {
            str = "empty request parameter";
        } else {
            if (ao.b(context)) {
                bi.b(context.getApplicationContext(), requestOptions);
                ek.a(context).e();
                if (strArr == null) {
                    strArr = new String[0];
                }
                AdSlotParam.Builder builder = new AdSlotParam.Builder();
                builder.setAdIds(Arrays.asList(strArr)).setDeviceType(i2).setWidth(com.huawei.openalliance.ad.utils.d.d(context)).setHeight(com.huawei.openalliance.ad.utils.d.e(context)).setRequestOptions(requestOptions);
                if (aVar != null) {
                    if (ho.a()) {
                        ho.a("AdContentRequestFactory", "api req param: %s.", be.b(aVar));
                    }
                    builder.setDetailedCreativeTypeList(aVar.a());
                    builder.b(aVar.b());
                    builder.setAdWidth(aVar.c());
                    builder.setAdHeight(aVar.d());
                    builder.setLocation(aVar.e());
                    builder.setContentBundle(aVar.f());
                }
                Pair<String, Boolean> b = com.huawei.openalliance.ad.utils.d.b(context, true);
                if (b != null) {
                    ho.b("AdContentRequestFactory", "use cached oaid " + System.currentTimeMillis());
                    builder.setOaid((String) b.first);
                    builder.setTrackLimited((Boolean) b.second);
                }
                return new ol(context).a(ao.a(), i, builder.build(), apiReqParam);
            }
            str = "api level too low";
        }
        ho.c("AdContentRequestFactory", str);
        return "";
    }

    public static String a(Context context, int i, RequestOptions requestOptions, com.huawei.hms.ads.jsb.inner.data.a aVar) {
        return a(context, new String[0], -1, i, requestOptions, aVar, null);
    }
}
