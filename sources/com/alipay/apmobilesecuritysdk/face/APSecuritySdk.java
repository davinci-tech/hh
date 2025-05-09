package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.b.a;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.sdk.m.a0.f;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.mq;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class APSecuritySdk implements f {

    /* renamed from: a, reason: collision with root package name */
    public static APSecuritySdk f847a;
    public static APSecBgCheckerInterface bgChecker;
    public static IDeviceInfo c;
    public static Object d = new Object();
    public Context b;

    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    @Override // com.alipay.sdk.m.a0.f
    public boolean isBackgroundRunning() {
        APSecBgCheckerInterface aPSecBgCheckerInterface = bgChecker;
        if (aPSecBgCheckerInterface != null) {
            return aPSecBgCheckerInterface.isBackgroundRunning();
        }
        return false;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        a.a().a(i);
        String b = h.b(this.b);
        String c2 = a.a().c();
        if (mq.b(b) && !mq.d(b, c2)) {
            com.alipay.apmobilesecuritysdk.e.a.a(this.b);
            d.a(this.b);
            g.a(this.b);
            i.h();
        }
        if (!mq.d(b, c2)) {
            h.c(this.b, c2);
        }
        String b2 = mq.b(map, "utdid", "");
        String b3 = mq.b(map, "tid", "");
        String b4 = mq.b(map, JsbMapKeyNames.H5_USER_ID, "");
        if (mq.e(b2)) {
            b2 = UtdidWrapper.getUtdid(this.b);
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("utdid", b2);
        hashMap.put("tid", b3);
        hashMap.put(JsbMapKeyNames.H5_USER_ID, b4);
        hashMap.put("appName", "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "8");
        b.a().a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.APSecuritySdk.1
            @Override // java.lang.Runnable
            public void run() {
                new com.alipay.apmobilesecuritysdk.a.a(APSecuritySdk.this.b).a(hashMap);
                InitResultListener initResultListener2 = initResultListener;
                if (initResultListener2 != null) {
                    initResultListener2.onResult(APSecuritySdk.this.getTokenResult());
                }
            }
        });
    }

    public TokenResult getTokenResult() {
        TokenResult tokenResult;
        synchronized (this) {
            tokenResult = new TokenResult();
            try {
                tokenResult.apdidToken = com.alipay.apmobilesecuritysdk.a.a.a(this.b, "");
                tokenResult.clientKey = h.f(this.b);
                tokenResult.apdid = com.alipay.apmobilesecuritysdk.a.a.a(this.b);
                tokenResult.umidToken = UmidSdkWrapper.getSecurityToken(this.b);
                if (mq.e(tokenResult.apdid) || mq.e(tokenResult.apdidToken) || mq.e(tokenResult.clientKey)) {
                    initToken(0, new HashMap(), null);
                }
            } catch (Throwable unused) {
            }
        }
        return tokenResult;
    }

    @Override // com.alipay.sdk.m.a0.f
    public String getSubscriberId() {
        IDeviceInfo iDeviceInfo = c;
        if (iDeviceInfo != null) {
            return iDeviceInfo.getSubscriberId();
        }
        return null;
    }

    public String getSdkVersion() {
        return "3.4.0.202303020703";
    }

    public String getSdkName() {
        return "APPSecuritySDK-ALIPAYSDK";
    }

    public String getApdidToken() {
        String a2 = com.alipay.apmobilesecuritysdk.a.a.a(this.b, "");
        if (mq.e(a2)) {
            initToken(0, new HashMap(), null);
        }
        return a2;
    }

    @Override // com.alipay.sdk.m.a0.f
    public String getAndroidId() {
        IDeviceInfo iDeviceInfo = c;
        if (iDeviceInfo != null) {
            return iDeviceInfo.getAndroidId();
        }
        return null;
    }

    public static void registerDeviceInfo(IDeviceInfo iDeviceInfo) {
        c = iDeviceInfo;
    }

    public static void registerBgChecker(APSecBgCheckerInterface aPSecBgCheckerInterface) {
        bgChecker = aPSecBgCheckerInterface;
    }

    public static String getUtdid(Context context) {
        return UtdidWrapper.getUtdid(context);
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f847a == null) {
            synchronized (d) {
                if (f847a == null) {
                    f847a = new APSecuritySdk(context);
                }
            }
        }
        return f847a;
    }

    public static IDeviceInfo getDeviceInfo() {
        return c;
    }

    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult() {
        }
    }

    public APSecuritySdk(Context context) {
        this.b = context;
    }
}
