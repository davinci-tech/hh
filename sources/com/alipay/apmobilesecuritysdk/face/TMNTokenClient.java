package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.mq;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class TMNTokenClient {

    /* renamed from: a, reason: collision with root package name */
    public static TMNTokenClient f850a;
    public Context b;

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (mq.e(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (mq.e(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("utdid", UtdidWrapper.getUtdid(this.b));
        hashMap.put("tid", "");
        hashMap.put(JsbMapKeyNames.H5_USER_ID, "");
        hashMap.put("appName", str);
        hashMap.put("appKeyClient", str2);
        hashMap.put("appchannel", "openapi");
        hashMap.put("sessionId", str3);
        hashMap.put("rpcVersion", "8");
        b.a().a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.TMNTokenClient.1
            @Override // java.lang.Runnable
            public void run() {
                int a2 = new a(TMNTokenClient.this.b).a(hashMap);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 == null) {
                    return;
                }
                if (a2 != 0) {
                    initResultListener2.onResult("", a2);
                } else {
                    initResultListener.onResult(a.a(TMNTokenClient.this.b, str), 0);
                }
            }
        });
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f850a == null) {
            synchronized (TMNTokenClient.class) {
                if (f850a == null) {
                    f850a = new TMNTokenClient(context);
                }
            }
        }
        return f850a;
    }

    public TMNTokenClient(Context context) {
        this.b = null;
        if (context == null) {
            throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
        }
        this.b = context;
    }
}
