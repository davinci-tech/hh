package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

/* loaded from: classes5.dex */
public class lra {
    private static DateTimeFormatter b = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static String h = "data_traffic";
    public static String c = JsNetwork.NetworkType.MOBILE;

    /* renamed from: a, reason: collision with root package name */
    public static String f14845a = "wifi";
    public static String d = "other";
    public static String e = "total";
    private static String f = "last";

    public static void a(final String str, final String str2) {
        if (!NetworkUtil.j() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: lrj
            @Override // java.lang.Runnable
            public final void run() {
                String str3 = str;
                String str4 = str2;
                lra.e(lra.a(str3), str4.getBytes(StandardCharsets.UTF_8).length);
            }
        });
    }

    public static void e(final String str, final String str2, final long j) {
        if (!NetworkUtil.j() || TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.isEmpty(str2) || j > 0) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: lrf
                @Override // java.lang.Runnable
                public final void run() {
                    String str3 = str2;
                    lra.e(lra.a(str), str3.getBytes(StandardCharsets.UTF_8).length + j);
                }
            });
        }
    }

    public static void d(final String str, final long j) {
        if (!NetworkUtil.j() || TextUtils.isEmpty(str) || j <= 0) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: lrc
            @Override // java.lang.Runnable
            public final void run() {
                lra.e(lra.a(str), j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, long j) {
        SharedPreferences zZ_ = SharedStoreManager.zZ_(h);
        String d2 = d();
        String str2 = d2 + e;
        HashMap<String, HashMap<String, Long>> bZl_ = bZl_(zZ_, d2);
        HashMap<String, Long> bZk_ = bZk_(zZ_, str2);
        d(str, j, bZl_, bZk_);
        SharedPreferences.Editor edit = zZ_.edit();
        String json = new Gson().toJson(bZl_);
        String json2 = new Gson().toJson(bZk_);
        edit.putString(d2, json);
        edit.putString(str2, json2);
        edit.apply();
    }

    private static HashMap<String, Long> bZk_(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(str, null);
        if (string == null) {
            return new HashMap<>();
        }
        return (HashMap) new Gson().fromJson(string, new TypeToken<HashMap<String, Long>>() { // from class: lra.2
        }.getType());
    }

    public static String e() {
        return SharedStoreManager.zZ_(h).getString(f, null);
    }

    private static String a(String str) {
        int indexOf = str.indexOf(63);
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }

    private static void d(String str, long j, HashMap<String, HashMap<String, Long>> hashMap, HashMap<String, Long> hashMap2) {
        HashMap<String, Long> hashMap3 = hashMap.get(str);
        if (hashMap3 == null) {
            hashMap3 = new HashMap<>();
        }
        if (NetworkUtil.f()) {
            String str2 = c;
            hashMap3.put(str2, Long.valueOf(hashMap3.getOrDefault(str2, 0L).longValue() + j));
            String str3 = c;
            hashMap2.put(str3, Long.valueOf(hashMap2.getOrDefault(str3, 0L).longValue() + j));
        } else if (NetworkUtil.m()) {
            String str4 = f14845a;
            hashMap3.put(str4, Long.valueOf(hashMap3.getOrDefault(str4, 0L).longValue() + j));
            String str5 = f14845a;
            hashMap2.put(str5, Long.valueOf(hashMap2.getOrDefault(str5, 0L).longValue() + j));
        } else {
            String str6 = d;
            hashMap3.put(str6, Long.valueOf(hashMap3.getOrDefault(str6, 0L).longValue() + j));
            String str7 = d;
            hashMap2.put(str7, Long.valueOf(hashMap2.getOrDefault(str7, 0L).longValue() + j));
        }
        String str8 = e;
        hashMap3.put(str8, Long.valueOf(hashMap3.getOrDefault(str8, 0L).longValue() + j));
        String str9 = e;
        hashMap2.put(str9, Long.valueOf(hashMap2.getOrDefault(str9, 0L).longValue() + j));
        hashMap.put(str, hashMap3);
    }

    private static HashMap<String, HashMap<String, Long>> bZl_(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(str, null);
        if (string == null) {
            return new HashMap<>();
        }
        return (HashMap) new Gson().fromJson(string, new TypeToken<HashMap<String, HashMap<String, Long>>>() { // from class: lra.3
        }.getType());
    }

    private static String d() {
        return LocalDate.now().format(b);
    }

    public static Map<String, Long> d(String str) {
        String string = SharedStoreManager.zZ_(h).getString(str + e, null);
        if (string == null) {
            return null;
        }
        Map<String, Long> map = (Map) new Gson().fromJson(string, new TypeToken<Map<String, Long>>() { // from class: lra.4
        }.getType());
        if (map.get(e) == null || map.get(e).longValue() < Constants.WEB_VIEW_CACHE_TOTAL_MAX_SIZE) {
            return null;
        }
        return map;
    }

    public static Map<String, Map<String, Long>> e(String str) {
        String string = SharedStoreManager.zZ_(h).getString(str, null);
        if (string == null) {
            return null;
        }
        return e((Map<String, Map<String, Long>>) new Gson().fromJson(string, new TypeToken<Map<String, Map<String, Long>>>() { // from class: lra.1
        }.getType()));
    }

    private static Map<String, Map<String, Long>> e(Map<String, Map<String, Long>> map) {
        PriorityQueue priorityQueue = new PriorityQueue(Comparator.comparing(new Function() { // from class: lrb
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Long valueOf;
                valueOf = Long.valueOf(((Map) r2.getValue()).get(lra.e) == null ? 0L : ((Long) ((Map) ((Map.Entry) obj).getValue()).get(lra.e)).longValue());
                return valueOf;
            }
        }));
        Iterator<Map.Entry<String, Map<String, Long>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            priorityQueue.offer(it.next());
            if (priorityQueue.size() > 5) {
                priorityQueue.poll();
            }
        }
        HashMap hashMap = new HashMap();
        while (!priorityQueue.isEmpty()) {
            Map.Entry entry = (Map.Entry) priorityQueue.poll();
            hashMap.put((String) entry.getKey(), (Map) entry.getValue());
        }
        return hashMap;
    }

    public static void e(String str, String str2) {
        SharedPreferences zZ_ = SharedStoreManager.zZ_(h);
        String string = zZ_.getString(str, null);
        String string2 = zZ_.getString(str + e, null);
        SharedPreferences.Editor edit = zZ_.edit();
        edit.clear();
        edit.apply();
        edit.putString(str, string);
        edit.putString(str + e, string2);
        edit.putString(f, str2);
        edit.apply();
    }
}
