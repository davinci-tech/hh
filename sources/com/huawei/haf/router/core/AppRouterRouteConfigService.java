package com.huawei.haf.router.core;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.router.AppRouterUtils;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class AppRouterRouteConfigService {
    private final Map<String, RouteConfigInfo> c = new HashMap();
    private final Map<String, RouteConfigInfo> b = new HashMap();
    private final Map<String, RouteConfigInfo> e = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, Uri> f2150a = new HashMap();
    private Uri d = null;

    AppRouterRouteConfigService() {
        d(BaseApplication.e());
    }

    String d(String str) {
        RouteConfigInfo h = h(str);
        if (h == null) {
            return null;
        }
        return !TextUtils.isEmpty(h.f2151a) ? h.f2151a : str;
    }

    Uri zR_(Uri uri) {
        RouteConfigInfo h = h(uri.getPath());
        if (h == null) {
            return null;
        }
        if (!TextUtils.isEmpty(h.c)) {
            String query = uri.getQuery();
            if (!TextUtils.isEmpty(query) && query.startsWith(h.c)) {
                uri = zP_(uri, query.substring(h.c.length()));
            }
        }
        return zQ_(uri, h.f2151a, h.b);
    }

    Uri zS_(String str) {
        return this.f2150a.get(str);
    }

    Uri zT_() {
        return this.d;
    }

    boolean g(String str) {
        return this.e.containsKey(str);
    }

    String a(String str) {
        RouteConfigInfo routeConfigInfo = this.e.get(str);
        return routeConfigInfo != null ? routeConfigInfo.e : "";
    }

    String e(String str) {
        RouteConfigInfo routeConfigInfo = this.e.get(str);
        return routeConfigInfo != null ? routeConfigInfo.f : "";
    }

    int c(String str) {
        RouteConfigInfo routeConfigInfo = this.e.get(str);
        if (routeConfigInfo != null) {
            return routeConfigInfo.d;
        }
        return 0;
    }

    boolean a(String str, String str2, RouteConfigInfo routeConfigInfo) {
        if (!str2.startsWith("/router/")) {
            return true;
        }
        RouteConfigInfo routeConfigInfo2 = routeConfigInfo == null ? this.e.get(str2) : routeConfigInfo;
        if (routeConfigInfo == null && routeConfigInfo2 != null && !TextUtils.isEmpty(routeConfigInfo2.e)) {
            return false;
        }
        if (routeConfigInfo2 != null && (!TextUtils.isEmpty(routeConfigInfo2.f2151a) || !TextUtils.isEmpty(routeConfigInfo2.e))) {
            return true;
        }
        LogUtil.a(str, "route config error. path=", str2, " not allowed real exist,", " need config mappingPath and/or pretreatmentPath");
        return false;
    }

    private static Uri zP_(Uri uri, String str) {
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.clearQuery();
        if (!TextUtils.isEmpty(str)) {
            buildUpon.encodedQuery(str);
        }
        return buildUpon.build();
    }

    private static Uri zQ_(Uri uri, String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return uri;
        }
        Uri.Builder buildUpon = uri.buildUpon();
        if (!TextUtils.isEmpty(str)) {
            buildUpon.encodedPath(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            zO_(buildUpon, uri.getQueryParameterNames(), str2);
        }
        return buildUpon.build();
    }

    private static void zO_(Uri.Builder builder, Set<String> set, String str) {
        int i = 0;
        do {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            String substring = str.substring(i, indexOf2);
            if (!TextUtils.isEmpty(substring)) {
                String substring2 = indexOf2 == indexOf ? "" : str.substring(indexOf2 + 1, indexOf);
                String decode = Uri.decode(substring);
                if (!set.contains(decode)) {
                    builder.appendQueryParameter(decode, Uri.decode(substring2));
                }
            }
            i = indexOf + 1;
        } while (i < str.length());
    }

    private JSONObject a(Context context) throws IOException, JSONException {
        InputStream open = context.getAssets().open("router_plugins/route_config.json");
        if (open == null) {
            LogUtil.c("HAF_RouteConfig", "not router map config in assets.");
            return null;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject(FileUtils.a(open, 5242880L));
            LogUtil.c("HAF_RouteConfig", "read router map config from assets, times=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return jSONObject;
        } finally {
            FileUtils.d(open);
        }
    }

    private void d(Context context) {
        try {
            JSONObject a2 = a(context);
            if (a2 == null) {
                return;
            }
            JSONArray optJSONArray = a2.optJSONArray("routeMap");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    d(optJSONArray.getJSONObject(i));
                }
            }
            JSONArray optJSONArray2 = a2.optJSONArray("groupMap");
            if (optJSONArray2 != null) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    b(optJSONArray2.getJSONObject(i2));
                }
            }
            JSONArray optJSONArray3 = a2.optJSONArray("remoteMap");
            if (optJSONArray3 != null) {
                for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                    c(optJSONArray3.getJSONObject(i3));
                }
            }
            String a3 = a(a2, "defaultRouteUri");
            if (!TextUtils.isEmpty(a3)) {
                this.d = Uri.parse(a3);
            }
            LogUtil.c("HAF_RouteConfig", "parseRouterInfo success. routeMap=(", Integer.valueOf(this.c.size()), ", ", Integer.valueOf(this.b.size()), "), routeInfoMap=", Integer.valueOf(this.e.size()), ", remoteMap=", Integer.valueOf(this.f2150a.size()));
        } catch (IOException | JSONException e) {
            LogUtil.a("HAF_RouteConfig", "parseRouterInfo ex=", LogUtil.a(e));
        }
    }

    private void d(JSONObject jSONObject) {
        try {
            b(jSONObject.getString("matchPaths").split("#"), new RouteConfigInfo(jSONObject));
        } catch (JSONException e) {
            LogUtil.a("HAF_RouteConfig", "parseRouteInfo ex=", LogUtil.a(e));
        }
    }

    private void b(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("pluginName");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            AppRouterRouteInfoLoader.a(string, jSONObject.getString("moduleOrGroupList").split("#"));
        } catch (JSONException e) {
            LogUtil.a("HAF_RouteConfig", "parseGroupMap ex=", LogUtil.a(e));
        }
    }

    private void c(JSONObject jSONObject) {
        try {
            String a2 = a(jSONObject);
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            String string = jSONObject.getString("authoritiesUri");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            this.f2150a.put(a2, Uri.parse(string));
        } catch (JSONException e) {
            LogUtil.a("HAF_RouteConfig", "parseRemoteMap ex=", LogUtil.a(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(JSONObject jSONObject, String str) {
        String optString = jSONObject.optString(str);
        return !TextUtils.isEmpty(optString) ? optString.intern() : optString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String a(JSONObject jSONObject) {
        if (jSONObject.has("runningProcess")) {
            return ProcessUtil.b(jSONObject.optString("runningProcess"));
        }
        return null;
    }

    private void b(String[] strArr, RouteConfigInfo routeConfigInfo) {
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                e(str, routeConfigInfo);
            }
        }
        if (TextUtils.isEmpty(routeConfigInfo.f2151a)) {
            return;
        }
        a(routeConfigInfo.f2151a, routeConfigInfo);
    }

    private void a(String str, RouteConfigInfo routeConfigInfo) {
        a("HAF_RouteConfig", str, routeConfigInfo);
        this.e.put(str, routeConfigInfo);
        if (routeConfigInfo.d > 0 || !TextUtils.isEmpty(routeConfigInfo.f)) {
            LogUtil.d("HAF_RouteConfig", "path=", str, ", runningProcess=", routeConfigInfo.f, ", extras=", AppRouterUtils.a(routeConfigInfo.d));
        }
    }

    private void e(String str, RouteConfigInfo routeConfigInfo) {
        boolean z;
        if (str.endsWith("/*")) {
            String substring = str.substring(0, str.length() - 1);
            z = f(substring) != null;
            this.b.put(substring, routeConfigInfo);
            if (TextUtils.isEmpty(routeConfigInfo.f2151a)) {
                LogUtil.a("HAF_RouteConfig", "route config error. path=", str, " need config mappingPath");
            }
        } else {
            String intern = str.intern();
            z = this.c.put(intern, routeConfigInfo) != null;
            if (TextUtils.isEmpty(routeConfigInfo.f2151a)) {
                a(intern, routeConfigInfo);
            }
        }
        if (z) {
            LogUtil.a("HAF_RouteConfig", "route config error. repeat path=", str);
        }
    }

    private RouteConfigInfo h(String str) {
        RouteConfigInfo routeConfigInfo = this.c.get(str);
        return routeConfigInfo != null ? routeConfigInfo : f(str);
    }

    private RouteConfigInfo f(String str) {
        if (this.b.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, RouteConfigInfo> entry : this.b.entrySet()) {
            if (str.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    static class RouteConfigInfo {

        /* renamed from: a, reason: collision with root package name */
        final String f2151a;
        final String b;
        final String c;
        final int d;
        final String e;
        final String f;

        RouteConfigInfo(JSONObject jSONObject) {
            this.c = jSONObject.optString("removeQueryPrefix");
            this.f2151a = AppRouterRouteConfigService.a(jSONObject, "mappingPath");
            this.b = jSONObject.optString("addParameters");
            this.e = AppRouterRouteConfigService.a(jSONObject, "pretreatmentPath");
            this.d = AppRouterUtils.a(jSONObject.optString("pathExtras"));
            this.f = AppRouterRouteConfigService.a(jSONObject);
        }
    }
}
