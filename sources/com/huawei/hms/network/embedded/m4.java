package com.huawei.hms.network.embedded;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PackageManagerCompat;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public final class m4 {
    public static final String e = "NetSerManager";
    public static final String f = "networkservice_";
    public static final String h = "networkservice_config";

    /* renamed from: a, reason: collision with root package name */
    public PolicyNetworkService f5376a;
    public Context b;
    public volatile ConcurrentHashMap<String, NetworkService> c = new ConcurrentHashMap<>(8);
    public n4 d;
    public static final m4 g = new m4();
    public static final List<String> i = Collections.unmodifiableList(Arrays.asList("core_switch_dns", "core_switch_config"));

    public void d(String str) {
        if (str == null || !this.c.containsKey(str)) {
            return;
        }
        this.c.remove(str);
    }

    public Boolean c(String str) {
        return Boolean.valueOf(this.d.b(str));
    }

    public void b() {
        this.c.clear();
    }

    public NetworkService b(String str) {
        return this.c.get(str);
    }

    public void a(PolicyNetworkService policyNetworkService) {
        synchronized (this) {
            if (this.b == null) {
                return;
            }
            for (String str : NetworkService.Constants.NETWORK_SERVICES) {
                NetworkService b = b(str);
                if (b != null) {
                    Logger.i(e, str + "Service,setOptions:");
                    b.serviceOptions(policyNetworkService.getValues("", (String[]) PolicyNetworkService.GlobalConstants.GLOBAL_CONSTANTS.toArray(new String[0])));
                }
            }
        }
    }

    public void a(Context context, String str, Bundle bundle) {
        synchronized (this) {
            this.b = context;
            this.d = new n4(str);
            Map<String, String> a2 = a(context, bundle);
            this.d.a();
            if (!a2.isEmpty()) {
                for (Map.Entry<String, String> entry : a2.entrySet()) {
                    String key = entry.getKey();
                    String str2 = PolicyNetworkService.NETWORK_SWITCH_CONFIG_PRE + key;
                    if (i.contains(str2) || this.d.b(str2)) {
                        NetworkService b = b(context, entry.getValue(), bundle);
                        if (b != null) {
                            this.c.put(key, b);
                        }
                    }
                }
            }
            Logger.i(e, "networkServices map is: " + this.c);
        }
    }

    public List<NetworkService> a() {
        return new ArrayList(this.c.values());
    }

    public Boolean a(String str) {
        return this.d.a(str);
    }

    public static m4 c() {
        return g;
    }

    private NetworkService b(Context context, String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            Logger.w(e, "networkService classPath is null");
            return null;
        }
        try {
            ClassLoader classLoader = m4.class.getClassLoader();
            if (classLoader != null) {
                NetworkService networkService = (NetworkService) classLoader.loadClass(str).asSubclass(NetworkService.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                networkService.onCreate(context, bundle);
                return networkService;
            }
        } catch (Throwable unused) {
            Logger.w(e, "network service load failed");
        }
        return null;
    }

    private Map<String, String> a(Context context, Bundle bundle) {
        NetworkService b = b(context, PackageManagerCompat.getMetaDataFromKitOrApp(context, h, ""), bundle);
        if (!(b instanceof PolicyNetworkService)) {
            throw new IllegalStateException("configPolicyService is error");
        }
        this.f5376a = (PolicyNetworkService) b;
        this.c.put(NetworkService.Constants.CONFIG_SERVICE, this.f5376a);
        Map<String, String> metaDataMapFromKitOrApp = PackageManagerCompat.getMetaDataMapFromKitOrApp(context, f);
        if (!metaDataMapFromKitOrApp.isEmpty() && metaDataMapFromKitOrApp.containsKey(NetworkService.Constants.CONFIG_SERVICE)) {
            metaDataMapFromKitOrApp.remove(NetworkService.Constants.CONFIG_SERVICE);
        }
        return metaDataMapFromKitOrApp;
    }
}
