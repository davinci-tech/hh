package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.core.util.Pair;

/* loaded from: classes6.dex */
public class mcl {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f14883a = true;

    public static Pair<Boolean, String> a(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context == null) {
            sb.append("isNetworkAvailable: context is null.");
            mcj.d("NetWorkUtil", "isNetworkAvailable: context is null.");
            f14883a = false;
            return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                sb.append('|');
                sb.append("Network no Available: ConnectivityManager is null.");
                mcj.d("NetWorkUtil", "Network no Available: ConnectivityManager is null.");
                f14883a = false;
                return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
            }
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                sb.append('|');
                sb.append("Network no Available: ActiveNetwork is null.");
                mcj.d("NetWorkUtil", "Network no Available: ActiveNetwork is null.");
                f14883a = false;
                return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                sb.append('|');
                sb.append("Network no Available: NetworkCapabilities is null.");
                mcj.d("NetWorkUtil", "Network no Available: NetworkCapabilities is null.");
                f14883a = false;
                return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
            }
            if (networkCapabilities.hasCapability(16)) {
                f14883a = true;
                return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
            }
            sb.append('|');
            sb.append("Network not has capability.");
            mcj.d("NetWorkUtil", "Network not has capability.");
            f14883a = connectivityManager.isDefaultNetworkActive();
            return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
        } catch (IncompatibleClassChangeError e) {
            String str = "isNetworkAvailable IncompatibleClassChangeError " + mcj.e(e);
            sb.append('|');
            sb.append(str);
            mcj.b("NetWorkUtil", str);
            f14883a = false;
            return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
        } catch (SecurityException e2) {
            String str2 = "isNetworkAvailable SecurityException " + mcj.e(e2);
            sb.append('|');
            sb.append(str2);
            mcj.b("NetWorkUtil", str2);
            f14883a = false;
            return new Pair<>(Boolean.valueOf(f14883a), sb.toString());
        }
    }

    public static boolean b(Context context) {
        Boolean bool = a(context).first;
        return bool != null && bool.booleanValue();
    }
}
