package com.huawei.haf.common.utils;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class NetworkUtil {
    private static volatile Boolean b;
    private static volatile Boolean d;
    private static volatile Boolean e;
    private static volatile ConnectivityManager.NetworkCallback f;
    private static volatile Boolean g;
    private static volatile Boolean i;
    private static final WeakHashMap<ConnectivityManager.NetworkCallback, Object> c = new WeakHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private static ConnectivityManager f2104a = CommonUtils.xz_();

    static {
        n();
    }

    private NetworkUtil() {
    }

    public static void xJ_(ConnectivityManager.NetworkCallback networkCallback) {
        if (networkCallback == null) {
            return;
        }
        WeakHashMap<ConnectivityManager.NetworkCallback, Object> weakHashMap = c;
        synchronized (weakHashMap) {
            RuntimeException n = n();
            if (n != null) {
                throw n;
            }
            weakHashMap.put(networkCallback, Boolean.TRUE);
            LogUtil.c("HAF_NetworkUtil", "registerNetworkCallback size=", Integer.valueOf(weakHashMap.size()));
        }
    }

    public static void xK_(ConnectivityManager.NetworkCallback networkCallback) {
        if (networkCallback == null) {
            return;
        }
        WeakHashMap<ConnectivityManager.NetworkCallback, Object> weakHashMap = c;
        synchronized (weakHashMap) {
            weakHashMap.remove(networkCallback);
        }
    }

    public static boolean j() {
        return f() || m() || g() || o();
    }

    public static boolean i() {
        return j();
    }

    public static boolean f() {
        if (b != null) {
            return b.booleanValue();
        }
        try {
            ConnectivityManager connectivityManager = f2104a;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(16)) {
                if (networkCapabilities.hasTransport(0)) {
                    return true;
                }
            }
            return false;
        } catch (SecurityException e2) {
            ReleaseLogUtil.c("HAF_NetworkUtil", "isMobileConnected getNetworkCapabilities fail: ", LogUtil.a(e2));
            NetworkInfo activeNetworkInfo = f2104a.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
        }
    }

    public static boolean m() {
        if (i != null) {
            return i.booleanValue();
        }
        try {
            ConnectivityManager connectivityManager = f2104a;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || !networkCapabilities.hasCapability(16)) {
                return false;
            }
            return networkCapabilities.hasTransport(1);
        } catch (SecurityException e2) {
            ReleaseLogUtil.c("HAF_NetworkUtil", "isWifiConnected getNetworkCapabilities fail: ", LogUtil.a(e2));
            NetworkInfo activeNetworkInfo = f2104a.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
        }
    }

    public static boolean g() {
        if (g != null) {
            return g.booleanValue();
        }
        try {
            ConnectivityManager connectivityManager = f2104a;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || !networkCapabilities.hasCapability(16)) {
                return false;
            }
            return networkCapabilities.hasTransport(4);
        } catch (SecurityException e2) {
            ReleaseLogUtil.c("HAF_NetworkUtil", "isVpnConnected getNetworkCapabilities fail: ", LogUtil.a(e2));
            NetworkInfo activeNetworkInfo = f2104a.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 17;
        }
    }

    private static boolean o() {
        if (e != null) {
            return e.booleanValue();
        }
        try {
            ConnectivityManager connectivityManager = f2104a;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null || !networkCapabilities.hasCapability(16)) {
                return false;
            }
            return xH_(networkCapabilities);
        } catch (SecurityException e2) {
            ReleaseLogUtil.c("HAF_NetworkUtil", "isOtherConnected getNetworkCapabilities fail: ", LogUtil.a(e2));
            NetworkInfo activeNetworkInfo = f2104a.getActiveNetworkInfo();
            return activeNetworkInfo != null && xI_(activeNetworkInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean xH_(NetworkCapabilities networkCapabilities) {
        boolean z = true;
        boolean z2 = networkCapabilities.hasTransport(2) || networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(5);
        if (Build.VERSION.SDK_INT < 27) {
            return z2;
        }
        if (!z2 && !networkCapabilities.hasTransport(6)) {
            z = false;
        }
        return z;
    }

    private static boolean xI_(NetworkInfo networkInfo) {
        int type = networkInfo.getType();
        return type == 6 || type == 7 || type == 9 || type == 8 || type == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<ConnectivityManager.NetworkCallback> l() {
        List<ConnectivityManager.NetworkCallback> list = Collections.EMPTY_LIST;
        WeakHashMap<ConnectivityManager.NetworkCallback, Object> weakHashMap = c;
        synchronized (weakHashMap) {
            if (!weakHashMap.isEmpty()) {
                list = new ArrayList<>(weakHashMap.keySet());
            }
        }
        return list;
    }

    private static RuntimeException n() {
        if (f != null) {
            return null;
        }
        try {
            NetworkCallbackAdpter networkCallbackAdpter = new NetworkCallbackAdpter();
            f2104a.registerDefaultNetworkCallback(networkCallbackAdpter);
            f = networkCallbackAdpter;
            ReleaseLogUtil.b("HAF_NetworkUtil", "registerDefaultNetworkCallback is success.");
            return null;
        } catch (Exception e2) {
            ReleaseLogUtil.b("HAF_NetworkUtil", "registerDefaultNetworkCallback fail, downgrade process, ex=", LogUtil.a(e2));
            if (e2 instanceof RuntimeException) {
                return (RuntimeException) e2;
            }
            return null;
        }
    }

    static class NetworkCallbackAdpter extends ConnectivityManager.NetworkCallback {
        private NetworkCallbackAdpter() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            Iterator it = NetworkUtil.l().iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onAvailable(network);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int i) {
            super.onLosing(network, i);
            Iterator it = NetworkUtil.l().iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onLosing(network, i);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            xM_(network);
            Iterator it = NetworkUtil.l().iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onLost(network);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onUnavailable() {
            super.onUnavailable();
            Iterator it = NetworkUtil.l().iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onUnavailable();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            xL_(network, networkCapabilities);
            Iterator it = NetworkUtil.l().iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onCapabilitiesChanged(network, networkCapabilities);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties);
            Iterator it = NetworkUtil.l().iterator();
            while (it.hasNext()) {
                ((ConnectivityManager.NetworkCallback) it.next()).onLinkPropertiesChanged(network, linkProperties);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onBlockedStatusChanged(Network network, boolean z) {
            super.onBlockedStatusChanged(network, z);
            if (Build.VERSION.SDK_INT >= 29) {
                Iterator it = NetworkUtil.l().iterator();
                while (it.hasNext()) {
                    ((ConnectivityManager.NetworkCallback) it.next()).onBlockedStatusChanged(network, z);
                }
            }
        }

        private void xL_(Network network, NetworkCapabilities networkCapabilities) {
            Boolean bool = NetworkUtil.b;
            Boolean unused = NetworkUtil.b = Boolean.valueOf(networkCapabilities.hasTransport(0));
            boolean z = !NetworkUtil.b.equals(bool);
            Boolean bool2 = NetworkUtil.i;
            Boolean unused2 = NetworkUtil.i = Boolean.valueOf(networkCapabilities.hasTransport(1));
            boolean z2 = z || !NetworkUtil.i.equals(bool2);
            Boolean bool3 = NetworkUtil.g;
            Boolean unused3 = NetworkUtil.g = Boolean.valueOf(networkCapabilities.hasTransport(4));
            boolean z3 = z2 || !NetworkUtil.g.equals(bool3);
            Boolean bool4 = NetworkUtil.e;
            Boolean unused4 = NetworkUtil.e = Boolean.valueOf(NetworkUtil.xH_(networkCapabilities));
            boolean z4 = z3 || !NetworkUtil.e.equals(bool4);
            Boolean bool5 = NetworkUtil.d;
            Boolean unused5 = NetworkUtil.d = Boolean.valueOf(networkCapabilities.hasCapability(16));
            if (z4 || !NetworkUtil.d.equals(bool5)) {
                ReleaseLogUtil.b("HAF_NetworkUtil", "network available: mobile=", NetworkUtil.b, ", wifi=", NetworkUtil.i, ", vpn=", NetworkUtil.g, ", other=", NetworkUtil.e, ", network=", network, ", internet=", Boolean.valueOf(networkCapabilities.hasCapability(12)), ", validated=", NetworkUtil.d);
            }
        }

        private void xM_(Network network) {
            ReleaseLogUtil.b("HAF_NetworkUtil", "network lost: network=", network);
            Boolean unused = NetworkUtil.b = false;
            Boolean unused2 = NetworkUtil.i = false;
            Boolean unused3 = NetworkUtil.g = false;
            Boolean unused4 = NetworkUtil.e = false;
            Boolean unused5 = NetworkUtil.d = false;
        }
    }
}
