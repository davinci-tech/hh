package com.huawei.hms.network.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import com.huawei.hms.framework.common.ContextCompat;
import com.huawei.hms.framework.common.Logger;

/* loaded from: classes9.dex */
public class RequestMobileNetwork {

    /* renamed from: a, reason: collision with root package name */
    private static final String f5136a = "RequestMobileNetwork";
    private static String b;
    private static final Object c = new Object();
    private static ConnectivityManager.NetworkCallback d;

    public static void stopMobileNetwork(Context context) {
        synchronized (RequestMobileNetwork.class) {
            if (d != null) {
                ((ConnectivityManager) context.getSystemService("connectivity")).unregisterNetworkCallback(d);
                d = null;
            }
        }
    }

    public static String requestMobileNiName(Context context, int i) {
        synchronized (RequestMobileNetwork.class) {
            if (!ContextCompat.checkSelfPermission(context, "android.permission.CHANGE_NETWORK_STATE")) {
                return null;
            }
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkRequest build = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
            b = "";
            if (d == null) {
                d = new ConnectivityManager.NetworkCallback() { // from class: com.huawei.hms.network.base.util.RequestMobileNetwork.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onUnavailable() {
                        super.onUnavailable();
                        synchronized (RequestMobileNetwork.c) {
                            RequestMobileNetwork.c.notify();
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public void onAvailable(Network network) {
                        super.onAvailable(network);
                        String unused = RequestMobileNetwork.b = connectivityManager.getLinkProperties(network).getInterfaceName();
                        synchronized (RequestMobileNetwork.c) {
                            RequestMobileNetwork.c.notify();
                        }
                    }
                };
            }
            connectivityManager.requestNetwork(build, d);
            try {
                Object obj = c;
                synchronized (obj) {
                    obj.wait(i);
                }
            } catch (InterruptedException e) {
                Logger.w(f5136a, e.getMessage());
            }
            return b;
        }
    }
}
