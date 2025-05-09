package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes3.dex */
public class eyb {
    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        return context != null && (context.getSystemService("connectivity") instanceof ConnectivityManager) && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    public static boolean e(Context context) {
        NetworkInfo activeNetworkInfo;
        return (context == null || !(context.getSystemService("connectivity") instanceof ConnectivityManager) || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getType() == 1) ? false : true;
    }
}
