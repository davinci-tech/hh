package com.huawei.hms.network;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.network.RemoteInitializer;
import com.huawei.hms.network.httpclient.internal.IHttpClientBuilder;
import com.huawei.hms.network.restclient.internal.IRestClientBuilder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class NetworkKitProvider extends BaseProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final String f5109a = "NetworkKitProvider";
    private static final String b = "com.huawei.hms.network.httpclient.DefaultNetworkKitProvider";
    private static final Object c = new Object();
    private static volatile NetworkKitProvider d;

    private static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public abstract IHttpClientBuilder createHttpClientBuilder();

    public abstract NetworkKit createNetworkKit();

    public abstract IRestClientBuilder createRestClientBuilder();

    static NetworkKitProvider loadLocalProvider() {
        synchronized (c) {
            if (d != null) {
                return d;
            }
            b(NetworkKit.getContext(), new ArrayList());
            return d;
        }
    }

    static NetworkKitProvider getEnableProvider(boolean z) {
        if (!z) {
            NetworkKit.getInstance();
        }
        synchronized (c) {
            if (d != null) {
                return d;
            }
            List<NetworkKitProvider> a2 = a(NetworkKit.getContext());
            int a3 = a(Version.getVersion());
            for (int size = a2.size() - 1; size >= 0; size--) {
                NetworkKitProvider networkKitProvider = a2.get(size);
                new PLSharedPreferences(NetworkKit.getContext(), RemoteInitializer.b.i).putString("kit_version", networkKitProvider.getVersion());
                if (networkKitProvider.getApiLevel() >= Version.getApi() && a(a(networkKitProvider.getVersion()), a3) > 0) {
                }
                a2.remove(size);
            }
            b(NetworkKit.getContext(), a2);
            return d;
        }
    }

    public static NetworkKitProvider getEnableProvider() {
        return getEnableProvider(false);
    }

    private static void b(Context context, List<NetworkKitProvider> list) {
        if (list.size() == 0) {
            Logger.d(f5109a, "start load local");
            Logger.i(f5109a, "load local result is: " + a(context, list));
        }
        if (list.size() <= 0) {
            throw new IllegalStateException("Unable to find NetworkKitProvider provider, context: " + context.getPackageName());
        }
        d = list.get(0);
        Logger.i(f5109a, "provider client version code: %s, api level: %s", d.getVersion(), Integer.valueOf(d.getApiLevel()));
        DynamicLoaderHelper.getInstance().setDynamicStatus(d.isDynamicProvider());
        Logger.i(f5109a, "NetworkKitProvider is dynamic provider result : %s", Boolean.valueOf(d.isDynamicProvider()));
    }

    private static boolean a(List<NetworkKitProvider> list) {
        Context remoteContext = NetworkKit.getRemoteInitializer().getRemoteContext();
        if (remoteContext != null) {
            return a(remoteContext, list);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean a(Context context, List<NetworkKitProvider> list) {
        try {
            list.add(context.getClassLoader().loadClass(b).asSubclass(NetworkKitProvider.class).getConstructor(new Class[0]).newInstance(new Object[0]));
            return true;
        } catch (Exception e) {
            Logger.e(f5109a, context.getPackageName() + " DefaultNetworkKitProvider not found", e);
            return false;
        }
    }

    private static List<NetworkKitProvider> a(Context context) {
        ArrayList arrayList = new ArrayList();
        Logger.i(f5109a, "load hms result is: %s", Boolean.valueOf(a(arrayList)));
        return new ArrayList(arrayList);
    }

    private static int a(String str) {
        if (str == null) {
            return 0;
        }
        String[] split = str.split("\\.");
        if (split.length < 4) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            try {
                String str2 = split[i];
                if (Integer.parseInt(str2) <= 9 && (i == 1 || i == 2)) {
                    sb.append("0");
                }
                sb.append(str2);
            } catch (NumberFormatException unused) {
                Logger.e(f5109a, "getVersionNumber error " + str);
                return 0;
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
