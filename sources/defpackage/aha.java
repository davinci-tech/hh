package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes3.dex */
public class aha {
    private static ConnectivityManager d;

    public static boolean d(Context context) {
        ConnectivityManager hI_;
        NetworkInfo activeNetworkInfo;
        return (context == null || (hI_ = hI_(context)) == null || (activeNetworkInfo = hI_.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    public static int e(Context context) {
        return hG_(hH_(context));
    }

    private static ConnectivityManager hI_(Context context) {
        if (d == null) {
            d = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        }
        return d;
    }

    public static NetworkInfo hH_(Context context) {
        ConnectivityManager hI_ = hI_(context);
        if (hI_ != null) {
            return hI_.getActiveNetworkInfo();
        }
        return null;
    }

    public static int hG_(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (1 == type || 13 == type) {
                return 1;
            }
            if (type == 0) {
                switch (networkInfo.getSubtype()) {
                }
                return 1;
            }
        }
        return 0;
    }
}
