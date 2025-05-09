package defpackage;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

/* loaded from: classes7.dex */
public class svx {
    public static boolean d() {
        ConnectivityManager connectivityManager;
        try {
            connectivityManager = (ConnectivityManager) ssz.e().getSystemService("connectivity");
        } catch (RuntimeException unused) {
            stq.e("BaseOverSeaUtil", "isNetworkConnected error Runtime");
        } catch (Exception unused2) {
            stq.e("BaseOverSeaUtil", "isNetworkConnected error");
        }
        if (connectivityManager == null) {
            stq.b("BaseOverSeaUtil", "isNetworkConnected has no ConnectivityManager");
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected()) || eXS_(connectivityManager);
    }

    private static boolean eXS_(ConnectivityManager connectivityManager) {
        stq.b("BaseOverSeaUtil", "judgeNetAgain start");
        if (Build.VERSION.SDK_INT >= 29) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null) {
                stq.b("BaseOverSeaUtil", "judgeNetAgain has no NetworkCapabilities");
                return false;
            }
            if (networkCapabilities.hasCapability(12)) {
                stq.b("BaseOverSeaUtil", "judgeNetAgain true 12");
                return true;
            }
        }
        return false;
    }
}
