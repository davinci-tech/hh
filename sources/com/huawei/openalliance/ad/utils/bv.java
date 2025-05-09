package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Pair;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.operation.utils.Constants;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class bv {
    private static int a(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return 4;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return 5;
            case 13:
            case 18:
                return 6;
            case 19:
            default:
                return 0;
            case 20:
                return 7;
        }
    }

    private static Context h(Context context) {
        if (context == null) {
            return null;
        }
        return context.getApplicationContext();
    }

    private static Pair<Integer, Pair<String, String>> g(Context context) {
        String str;
        String str2;
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String networkOperator = telephonyManager.getNetworkOperator();
                int i = 0;
                if (networkOperator == null || Constants.NULL.equalsIgnoreCase(networkOperator) || networkOperator.length() <= 3) {
                    str = null;
                    str2 = null;
                } else {
                    str2 = networkOperator.substring(0, 3);
                    str = networkOperator.substring(3);
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                    i = activeNetworkInfo.getSubtype();
                }
                if (i == 0) {
                    i = telephonyManager.getNetworkType();
                }
                return a(str2, str, i);
            }
        } catch (Throwable th) {
            ho.c("NetworkUtil", "getDefaultNetworkOperatorInfo error: %s", th.getClass().getSimpleName());
        }
        return null;
    }

    public static Pair<Integer, Pair<String, String>> f(Context context) {
        Pair<Integer, Pair<String, String>> pair = null;
        if (!HiAd.getInstance(context).isEnableUserInfo()) {
            return null;
        }
        if (!d.d() && bq.b(context)) {
            ho.b("NetworkUtil", "multicard device");
            bp a2 = bq.a(context);
            pair = a2.a(a2.a());
        }
        return pair == null ? g(context) : pair;
    }

    public static boolean e(Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || h(context) == null || (connectivityManager = (ConnectivityManager) h(context).getSystemService("connectivity")) == null) {
            return false;
        }
        try {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(connectivityManager.getActiveNetwork());
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
            return false;
        } catch (Throwable unused) {
            ho.c("NetworkUtil", "fail to check network connection");
            return false;
        }
    }

    public static int d(Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || h(context) == null || (connectivityManager = (ConnectivityManager) h(context).getSystemService("connectivity")) == null) {
            return 0;
        }
        try {
            NetworkInfo a2 = a(connectivityManager);
            if (a2 != null) {
                int type = a2.getType();
                if (type == 0) {
                    return a(a2.getSubtype());
                }
                if (9 == type) {
                    return 1;
                }
                if (1 == type) {
                    return 2;
                }
            }
        } catch (Throwable unused) {
            ho.c("NetworkUtil", "fail to get network info");
        }
        return 0;
    }

    public static boolean c(Context context) {
        return a(context) || b(context);
    }

    public static boolean b(Context context) {
        return context != null && 1 == d(context);
    }

    public static boolean b() {
        return ck.b(g2.h) || ck.c(g2.h);
    }

    public static boolean a(Context context) {
        return context != null && 2 == d(context);
    }

    public static boolean a() {
        try {
            bu.a();
            return true;
        } catch (Throwable th) {
            ho.c("NetworkUtil", "isNetWorKkitSupported Exception:" + th.getClass().getSimpleName());
            return false;
        }
    }

    public static HttpClient.Builder a(Context context, boolean z) {
        HttpClient.Builder builder = new HttpClient.Builder();
        if (!z) {
            return builder;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("smallpkt_fec", true);
            jSONObject.put("tls_zero_rtt", true);
            String str = cw.b(x.i(context)) + File.separator + com.huawei.openalliance.ad.constant.Constants.PPS_ROOT_PATH + File.separator + g2.QUIC;
            ae.g(new File(str));
            jSONObject.put("storage_path", str);
            jSONObject.put("max_server_configs_stored_properties", 10);
        } catch (JSONException unused) {
            Log.w("NetworkUtil", "jsonObject catch a JSONException");
        }
        builder.enableQuic(true).options(jSONObject.toString());
        return builder;
    }

    private static Pair<Integer, Pair<String, String>> a(String str, String str2, int i) {
        return new Pair<>(Integer.valueOf(i), new Pair(str, str2));
    }

    public static NetworkInfo a(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(connectivityManager.getActiveNetwork());
        return (networkInfo == null || 17 != networkInfo.getType()) ? networkInfo : connectivityManager.getActiveNetworkInfo();
    }
}
