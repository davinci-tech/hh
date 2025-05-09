package com.huawei.openalliance.ad.inter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.huawei.hms.ads.inner.IECCallback;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.i;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7027a = new byte[0];
    private static a b;
    private IECCallback c;
    private String d;

    public String c() {
        return this.d;
    }

    public String b() {
        IECCallback iECCallback = this.c;
        if (iECCallback != null) {
            return iECCallback.getAccessToken();
        }
        ho.b("ECATManager", "accessTokenProvider is null, return");
        return null;
    }

    public boolean a(Context context, String str) {
        String str2;
        if (context == null) {
            str2 = "context is null.";
        } else if (TextUtils.isEmpty(str)) {
            str2 = "url is null.";
        } else {
            Uri b2 = b(str);
            if (!a(b2)) {
                ho.b("ECATManager", "url invalid.");
                b2 = b(c(str));
                if (!a(b2)) {
                    str2 = "decode url invalid.";
                }
            }
            String d = i.d(context);
            if (TextUtils.isEmpty(d)) {
                str2 = "not install hms.";
            } else {
                try {
                    Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, b2);
                    intent.setPackage(d);
                    if (!(context instanceof Activity)) {
                        intent.addFlags(268435456);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(Constants.OPEN_FROM_SDK, true);
                    bundle.putString("accessToken", this.d);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    return true;
                } catch (Throwable th) {
                    str2 = "openLandingPage exception " + th.getClass().getSimpleName();
                }
            }
        }
        ho.b("ECATManager", str2);
        return false;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(IECCallback iECCallback) {
        this.c = iECCallback;
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Uri.decode(str);
        } catch (Throwable th) {
            ho.b("ECATManager", "decodeUrl exception " + th.getClass().getSimpleName());
            return null;
        }
    }

    private Uri b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Uri.parse(str);
        } catch (Throwable th) {
            ho.b("ECATManager", "getUri exception " + th.getClass().getSimpleName());
            return null;
        }
    }

    private boolean a(Uri uri) {
        String str;
        if (uri == null) {
            str = "uri is null.";
        } else if (URLUtil.isNetworkUrl(uri.toString())) {
            str = "uri is http or https url.";
        } else {
            try {
                String host = uri.getHost();
                if (TextUtils.equals("hwpps", uri.getScheme())) {
                    return TextUtils.equals(Constants.SCHEME_HOST, host);
                }
                return false;
            } catch (Throwable th) {
                str = "isUriValid exception " + th.getClass().getSimpleName();
            }
        }
        ho.b("ECATManager", str);
        return false;
    }

    public static a a() {
        a aVar;
        synchronized (f7027a) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    private a() {
    }
}
