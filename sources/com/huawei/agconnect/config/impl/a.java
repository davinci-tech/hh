package com.huawei.agconnect.config.impl;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectApp;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.CustomAuthProvider;
import com.huawei.agconnect.CustomCredentialsProvider;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class a extends AGConnectApp {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, AGConnectApp> f1702a = new HashMap();
    private static final Object b = new Object();
    private static String c;
    private AGConnectServicesConfig d;

    @Override // com.huawei.agconnect.AGConnectApp
    public void setProductId(String str) {
        this.d.setParam("/client/product_id", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setParam(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("path can not be empty");
        }
        this.d.setParam(str, str2);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setCustomCredentialsProvider(CustomCredentialsProvider customCredentialsProvider) {
        ((com.huawei.agconnect.core.a.b) AGConnectInstance.getInstance()).a(customCredentialsProvider);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setCustomAuthProvider(CustomAuthProvider customAuthProvider) {
        ((com.huawei.agconnect.core.a.b) AGConnectInstance.getInstance()).a(customAuthProvider);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setCpId(String str) {
        this.d.setParam("/client/cp_id", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setClientSecret(String str) {
        this.d.setParam("/client/client_secret", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setClientId(String str) {
        this.d.setParam("/client/client_id", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setAppId(String str) {
        this.d.setParam("/client/app_id", str);
    }

    @Override // com.huawei.agconnect.AGConnectApp
    public void setApiKey(String str) {
        this.d.setParam("/client/api_key", str);
    }

    public static AGConnectApp a(String str) {
        AGConnectApp aGConnectApp;
        synchronized (b) {
            aGConnectApp = f1702a.get(str);
            if (aGConnectApp == null) {
                throw new IllegalStateException("you should call AGConnectApp.initialize first");
            }
        }
        return aGConnectApp;
    }

    public static AGConnectApp a(Context context, String str) {
        AGConnectApp aGConnectApp;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName can not be empty");
        }
        synchronized (b) {
            Map<String, AGConnectApp> map = f1702a;
            aGConnectApp = map.get(str);
            if (aGConnectApp == null) {
                map.put(str, new a(context, str));
            }
        }
        return aGConnectApp;
    }

    public static AGConnectApp a(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        String packageName = context.getPackageName();
        c = packageName;
        return a(context, packageName);
    }

    public static AGConnectApp a() {
        return a(c);
    }

    private a(Context context, String str) {
        this.d = AGConnectServicesConfig.fromContext(context, str);
    }
}
