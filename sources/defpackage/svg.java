package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.health.R;
import com.huawei.hms.network.embedded.w;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class svg {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f17247a = new byte[0];
    private static volatile svg e;
    private Map<String, List<svh>> b = new HashMap();

    private svg() {
    }

    public static svg a() {
        if (e == null) {
            synchronized (f17247a) {
                if (e == null) {
                    e = new svg();
                }
            }
        }
        return e;
    }

    public static String d(Context context, String str, String str2) {
        String canonicalName;
        String b = svm.b(context).b(str, str2);
        if (TextUtils.isEmpty(b)) {
            stq.c("AddressNameMgr", "getGrsUrlSync(),check empty", false);
            if (!"CN".equals(str2)) {
                stq.c("AddressNameMgr", "getGrsUrlSync(),oversea grsUrl is null", false);
                return " ";
            }
            b = svm.b(context).c(str2, str);
            if (TextUtils.isEmpty(b)) {
                stq.c("AddressNameMgr", "getGrsUrlSync(),getUrlFromLocal is null", false);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("log_time", new Date(System.currentTimeMillis()).toString());
            if (context.getClass().getCanonicalName() == null) {
                canonicalName = context.getClass().getName();
            } else {
                canonicalName = context.getClass().getCanonicalName();
            }
            linkedHashMap.put("event_result_des", canonicalName);
            linkedHashMap.put("log", stt.c());
            linkedHashMap.put(HeaderType.EVENT_ID, "cfg.url");
            linkedHashMap.put("local", str2);
            if (TextUtils.equals(str, "TRANSPORTATIONCARD")) {
                linkedHashMap.put("server_name", "TRANSPORTATIONCARD");
            } else if (TextUtils.equals(str, "BANKCARD")) {
                linkedHashMap.put("server_name", "BANKCARD");
            } else if (TextUtils.equals(str, "VIRTUALCARD")) {
                linkedHashMap.put("server_name", "VIRTUALCARD");
            } else if (TextUtils.equals(str, "PAYCHANGE")) {
                linkedHashMap.put("server_name", "PAYCHANGE");
            } else if (TextUtils.equals(str, "HUAWEICARD")) {
                linkedHashMap.put("server_name", "HUAWEICARD");
            } else if (TextUtils.equals(str, "WALLET")) {
                linkedHashMap.put("server_name", "WALLET");
            } else if (TextUtils.equals(str, "WALLETPASSTRUST")) {
                linkedHashMap.put("server_name", "WALLETPASSTRUST");
            } else {
                linkedHashMap.put("server_name", str);
                stq.c("AddressNameMgr", "getGrsUrlSync,not traffic,bank and wallet condition", false);
            }
            sun.b(0, "cfg.url", linkedHashMap);
        }
        return b;
    }

    public void c(Context context) {
        new svs().b(context, this.b, R.xml._2132082703_res_0x7f15000f);
    }

    public String d(String str, String str2, String str3, String str4, Context context) {
        if (TextUtils.equals(str2, "TransportationCard")) {
            str = a(str, "TransportationCard", null);
        } else if (TextUtils.equals(str2, "BankCard")) {
            str = a(str, "BankCard", null);
        } else if (TextUtils.equals(str2, "WalletPass")) {
            str = a(str, "WalletPass", null);
        } else if (TextUtils.equals(str2, "VirtualCard")) {
            str = a(str, "VirtualCard", null);
        } else if (TextUtils.equals(str2, "PayChange")) {
            str = a(str, "PayChange", null);
        } else if (TextUtils.equals(str2, "HuaweiCard")) {
            str = a(str, "HuaweiCard", null);
        } else if (TextUtils.equals(str2, "Wallet")) {
            str = a(str, "Wallet", null);
        } else if (TextUtils.equals(str2, "WalletTrusteeship")) {
            str = a(str, "WalletTrusteeship", null);
        }
        stq.c("AddressNameMgr", "getAddress success = " + str, false);
        return d(context, str, str4) + "?clientVersion=" + str3;
    }

    public String a(String str, String str2, Map<String, String> map) {
        String str3;
        Map<String, List<svh>> b = b();
        if (b.containsKey(str2)) {
            str3 = c(str, map, b.get(str2));
            stq.c("AddressNameMgr", "getAddressName= " + str3, false);
        } else {
            str3 = null;
        }
        if (str3 == null) {
            str3 = c(str, map, b.get(w.j));
            stq.c("AddressNameMgr", "getAddressName Default = " + str3, false);
        }
        if (str3 == null) {
            return str;
        }
        stq.c("AddressNameMgr", "getAddressName serverAddressName = " + str3, false);
        return str3;
    }

    private String c(String str, Map<String, String> map, List<svh> list) {
        if (list == null || str == null) {
            return " ";
        }
        for (svh svhVar : list) {
            if (str.equalsIgnoreCase(svhVar.c()) && svhVar.d(map)) {
                stq.c("AddressNameMgr", "getServerAddressName = " + svhVar.a(), false);
                return svhVar.a();
            }
        }
        return " ";
    }

    private Map<String, List<svh>> b() {
        return this.b;
    }
}
