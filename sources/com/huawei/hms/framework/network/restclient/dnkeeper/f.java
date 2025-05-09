package com.huawei.hms.framework.network.restclient.dnkeeper;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.network.restclient.hwhttp.dns.DnsResult;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4570a = "DNKeeperCallable";

    public static boolean a(DnsResult dnsResult) {
        return dnsResult == null || dnsResult.isEmpty();
    }

    public static boolean a(PLSharedPreferences pLSharedPreferences, String str) {
        if (pLSharedPreferences == null) {
            return true;
        }
        DnsResult a2 = a(pLSharedPreferences.getString(str));
        if (!a(a2)) {
            if (System.currentTimeMillis() - a2.getCreateTime() < 604800000) {
                return false;
            }
        }
        return true;
    }

    public static void a(JSONArray jSONArray, int i, String str, String str2, long j) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            jSONObject.put("value", str2);
            jSONObject.put("ttl", j);
            jSONArray.put(i, jSONObject);
        } catch (JSONException e) {
            Logger.w(f4570a, "JSONException", e);
            throw e;
        }
    }

    private static void a(List<DnsResult.Address> list, List<DnsResult.Address> list2, DnsResult dnsResult) {
        if (list.isEmpty() || list2.isEmpty()) {
            dnsResult.getAddressList().addAll(list);
            dnsResult.getAddressList().addAll(list2);
            return;
        }
        int max = Math.max(list.size(), list2.size());
        for (int i = 0; i < max; i++) {
            if (i < list.size()) {
                dnsResult.getAddressList().add(list.get(i));
            }
            if (i < list2.size()) {
                dnsResult.getAddressList().add(list2.get(i));
            }
        }
    }

    private static void a(List<DnsResult.Address> list, List<DnsResult.Address> list2, DnsResult.Address address) {
        if (address.getType().equalsIgnoreCase("A")) {
            list.add(address);
        } else {
            list2.add(address);
        }
    }

    private static JSONObject a(RequestHost requestHost, Context context) {
        JSONObject jSONObject = new JSONObject();
        String apkName = requestHost.getApkName();
        if (TextUtils.isEmpty(apkName) && context != null) {
            apkName = context.getPackageName();
        }
        try {
            jSONObject.put("DomainName", requestHost.getDomainName());
            jSONObject.put("ApkName", apkName);
            jSONObject.put("DnsFailType", requestHost.getDnsFailType());
            jSONObject.put("FailIP", requestHost.getFailIP());
            jSONObject.put("accelerate", requestHost.getAccelerate());
            jSONObject.put("type", requestHost.getType());
        } catch (JSONException e) {
            Logger.w(f4570a, "getRequestStr", e);
        }
        return jSONObject;
    }

    private static String a(List<DnsResult.Address> list, List<DnsResult.Address> list2, JSONObject jSONObject, String str) throws JSONException {
        Logger.i(f4570a, "Type is cname, and user localDns to parse %s", str);
        try {
            for (InetAddress inetAddress : InetAddress.getAllByName(str)) {
                a(list, list2, new DnsResult.Address.Builder().value(inetAddress.getHostAddress()).ttl(jSONObject.getLong("ttl") * 1000).type(inetAddress instanceof Inet4Address ? "A" : "AAAA").build());
            }
        } catch (IllegalArgumentException e) {
            e = e;
            Logger.w(f4570a, "DNKeeperManager parseResponse failed, Exception: " + str, e);
        } catch (NullPointerException e2) {
            e = e2;
            Logger.w(f4570a, "DNKeeperManager parseResponse failed, Exception: " + str, e);
        } catch (UnknownHostException unused) {
            Logger.w(f4570a, "DNKeeperManager parseResponse failed,UnknownHostException:" + str);
        }
        return (list.isEmpty() || list2.isEmpty()) ? (!list.isEmpty() || list2.isEmpty()) ? "A" : "AAAA" : "ALL";
    }

    public static String a(HashSet<RequestHost> hashSet) {
        Context appContext = ContextHolder.getAppContext();
        if (hashSet == null) {
            return "";
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<RequestHost> it = hashSet.iterator();
        while (it.hasNext()) {
            jSONArray.put(a(it.next(), appContext));
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("queryInfos", jSONArray);
        } catch (JSONException e) {
            Logger.w(f4570a, "getRequestStr :", e.getClass().getSimpleName());
        }
        return jSONObject.toString();
    }

    public static String a(RequestHost requestHost) {
        return requestHost != null ? a(requestHost, ContextHolder.getAppContext()).toString() : "";
    }

    public static DnsResult a(String str) {
        Logger.v(f4570a, "parseResponse: " + str);
        DnsResult dnsResult = new DnsResult();
        if (TextUtils.isEmpty(str)) {
            return dnsResult;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("type");
            dnsResult.setType(string);
            JSONArray jSONArray = jSONObject.getJSONArray(DnsResult.KEY_ADDRESSLIST);
            dnsResult.setCreateTime(jSONObject.getLong("createTime"));
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                String string2 = jSONObject2.getString("value");
                if (!TextUtils.isEmpty(string2)) {
                    if ("CNAME".equals(string)) {
                        dnsResult.setType(a(arrayList, arrayList2, jSONObject2, string2));
                    } else {
                        a(arrayList, arrayList2, new DnsResult.Address.Builder().value(string2).type(jSONObject2.getString("type")).ttl(jSONObject2.getLong("ttl") * 1000).build());
                    }
                }
            }
            a(arrayList, arrayList2, dnsResult);
            return dnsResult;
        } catch (JSONException e) {
            Logger.w(f4570a, "parseResponse", e);
            a(arrayList, arrayList2, dnsResult);
            return dnsResult;
        }
    }

    public static DnsResult a(DnsResult dnsResult, String str) {
        if (TextUtils.isEmpty(str)) {
            return dnsResult;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DnsResult.Address.Builder().value(str).build());
        if (dnsResult != null) {
            dnsResult.setAddressList(arrayList);
        }
        return dnsResult;
    }
}
