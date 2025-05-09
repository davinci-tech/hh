package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.UrlConstant;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ej {

    /* renamed from: a, reason: collision with root package name */
    private static ej f6839a;
    private static final byte[] b = new byte[0];
    private Map<String, String> c = new HashMap();
    private Map<String, String> d = new HashMap();
    private Context e;

    public String b(String str) {
        return TextUtils.isEmpty(this.d.get(str)) ? "" : this.d.get(str);
    }

    public String a(String str) {
        return this.c.get(str) + com.huawei.openalliance.ad.utils.cz.a(this.e);
    }

    private void b() {
        this.d.put("adxServer", Constants.ACD_REQ_URI);
        this.d.put("analyticsServer", Constants.ANALYSIS_CONTENT_SERVER_REQ_URI);
        this.d.put("eventServer", Constants.CONTENT_SERVER_REQ_URI);
        this.d.put("configServer", Constants.SDK_SERVER_REQ_URI);
        this.d.put("permissionServer", Constants.PERMISSION_SERVER_REQ_URI);
        this.d.put("appDataServer", Constants.SDK_APP_DATA_REPORT_SERVER_URI);
        this.d.put("consentConfigServer", Constants.KIT_CONSENT_CONFIG_URI);
        this.d.put("appInsListConfigServer", Constants.APP_INS_LIST_CONFIG_SERVER_URI);
        this.d.put(UrlConstant.BASE_COMPLAIN_H5_URL, "?lang=");
    }

    private void a() {
        this.c.put("adxServer", UrlConstant.BASE_ADX_SERVER);
        this.c.put("analyticsServer", UrlConstant.BASE_EVENT_SERVER);
        this.c.put("eventServer", UrlConstant.BASE_EVENT_SERVER);
        this.c.put("configServer", UrlConstant.BASE_SDK_SERVER);
        this.c.put("permissionServer", UrlConstant.BASE_ADX_SERVER);
        this.c.put("appDataServer", UrlConstant.BASE_EVENT_SERVER);
        this.c.put("consentConfigServer", UrlConstant.BASE_SDK_SERVER);
        this.c.put("appInsListConfigServer", UrlConstant.BASE_SDK_SERVER);
        this.c.put(UrlConstant.BASE_COMPLAIN_H5_URL, "complainH5ServerBaseUrl");
    }

    public static ej a(Context context) {
        ej ejVar;
        synchronized (b) {
            if (f6839a == null) {
                f6839a = new ej(context);
            }
            ejVar = f6839a;
        }
        return ejVar;
    }

    private ej(Context context) {
        this.e = context.getApplicationContext();
        a();
        b();
    }
}
