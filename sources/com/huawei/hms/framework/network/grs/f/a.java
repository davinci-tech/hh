package com.huawei.hms.framework.network.grs.f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    protected com.huawei.hms.framework.network.grs.local.model.a f4536a;
    protected List<com.huawei.hms.framework.network.grs.local.model.b> b;
    protected boolean c = false;
    protected boolean d = false;
    protected Set<String> e = new HashSet(16);

    public abstract int b(String str);

    public abstract int c(String str);

    public abstract int g(String str);

    public int f(String str) {
        try {
            b(new JSONObject(str).getJSONArray("services"));
            return 0;
        } catch (JSONException e) {
            Logger.w("AbstractLocalManager", "parse 2.0 services failed maybe because of json style.please check! %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }

    public int e(String str) {
        JSONArray jSONArray;
        this.b = new ArrayList(16);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("countryOrAreaGroups")) {
                jSONArray = jSONObject.getJSONArray("countryOrAreaGroups");
            } else if (jSONObject.has("countryGroups")) {
                jSONArray = jSONObject.getJSONArray("countryGroups");
            } else {
                Logger.e("AbstractLocalManager", "maybe local config json is wrong because the default countryOrAreaGroups isn't config.");
                jSONArray = null;
            }
            if (jSONArray == null) {
                return -1;
            }
            this.b.addAll(a(jSONArray));
            return 0;
        } catch (JSONException e) {
            Logger.w("AbstractLocalManager", "parse countrygroup failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }

    public boolean d(String str) {
        String str2;
        try {
            this.f4536a = new com.huawei.hms.framework.network.grs.local.model.a();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("name")) {
                this.f4536a.b(jSONObject.getString("name"));
            }
            if (jSONObject.has("cacheControl")) {
                this.f4536a.a(jSONObject.getLong("cacheControl"));
            }
            if (jSONObject.has("services")) {
                b(jSONObject.getJSONArray("services"));
            }
            if (jSONObject.has("countryGroups")) {
                JSONArray jSONArray = jSONObject.getJSONArray("countryGroups");
                ArrayList arrayList = new ArrayList(16);
                this.b = arrayList;
                arrayList.addAll(a(jSONArray));
            }
            Logger.i("AbstractLocalManager", "parse from sp services size : %d, countryGroups size: %d:", Integer.valueOf(this.e.size()), Integer.valueOf(this.b.size()));
            return true;
        } catch (JSONException unused) {
            str2 = "Parse local config from sp failed, JSONException";
            Logger.w("AbstractLocalManager", str2);
            return false;
        } catch (Throwable th) {
            str2 = "Parse local config from sp failed, Throwable:" + StringUtils.anonymizeMessage(th.getMessage());
            Logger.w("AbstractLocalManager", str2);
            return false;
        }
    }

    public boolean d() {
        return this.c;
    }

    public Set<String> c() {
        return this.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void b(org.json.JSONArray r15) {
        /*
            r14 = this;
            if (r15 == 0) goto Lf5
            int r0 = r15.length()
            if (r0 != 0) goto La
            goto Lf5
        La:
            r0 = 0
            r1 = r0
        Lc:
            int r2 = r15.length()
            if (r1 >= r2) goto Lf5
            org.json.JSONObject r2 = r15.getJSONObject(r1)
            com.huawei.hms.framework.network.grs.local.model.c r3 = new com.huawei.hms.framework.network.grs.local.model.c
            r3.<init>()
            java.lang.String r4 = "name"
            java.lang.String r4 = r2.getString(r4)
            r3.b(r4)
            java.util.Set<java.lang.String> r5 = r14.e
            boolean r5 = r5.contains(r4)
            if (r5 != 0) goto Lf1
            java.util.Set<java.lang.String> r5 = r14.e
            r5.add(r4)
            boolean r5 = r14.d
            if (r5 == 0) goto Lf1
            java.lang.String r5 = "routeBy"
            java.lang.String r5 = r2.getString(r5)
            r3.c(r5)
            java.lang.String r5 = "servings"
            org.json.JSONArray r5 = r2.getJSONArray(r5)
            r6 = r0
        L45:
            int r7 = r5.length()
            java.lang.String r8 = "AbstractLocalManager"
            if (r6 >= r7) goto Lbe
            java.lang.Object r7 = r5.get(r6)
            org.json.JSONObject r7 = (org.json.JSONObject) r7
            com.huawei.hms.framework.network.grs.local.model.d r9 = new com.huawei.hms.framework.network.grs.local.model.d
            r9.<init>()
            java.lang.String r10 = "countryOrAreaGroup"
            boolean r11 = r7.has(r10)
            if (r11 == 0) goto L61
            goto L69
        L61:
            java.lang.String r10 = "countryGroup"
            boolean r11 = r7.has(r10)
            if (r11 == 0) goto L6e
        L69:
            java.lang.String r8 = r7.getString(r10)
            goto L79
        L6e:
            java.lang.Object[] r10 = new java.lang.Object[]{r4}
            java.lang.String r11 = "maybe this service{%s} routeBy is unconditional."
            com.huawei.hms.framework.common.Logger.v(r8, r11, r10)
            java.lang.String r8 = "no-country"
        L79:
            r9.a(r8)
            java.lang.String r8 = "addresses"
            org.json.JSONObject r7 = r7.getJSONObject(r8)
            java.util.concurrent.ConcurrentHashMap r8 = new java.util.concurrent.ConcurrentHashMap
            r10 = 16
            r8.<init>(r10)
            java.util.Iterator r10 = r7.keys()
        L8d:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto Lb1
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r12 = r7.getString(r11)
            boolean r13 = android.text.TextUtils.isEmpty(r11)
            if (r13 != 0) goto L8d
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 != 0) goto L8d
            java.lang.String r12 = r7.getString(r11)
            r8.put(r11, r12)
            goto L8d
        Lb1:
            r9.a(r8)
            java.lang.String r7 = r9.b()
            r3.a(r7, r9)
            int r6 = r6 + 1
            goto L45
        Lbe:
            java.lang.String r5 = "countryOrAreaGroups"
            boolean r6 = r2.has(r5)
            if (r6 == 0) goto Lc7
            goto Lcf
        Lc7:
            java.lang.String r5 = "countryGroups"
            boolean r6 = r2.has(r5)
            if (r6 == 0) goto Ld8
        Lcf:
            org.json.JSONArray r2 = r2.getJSONArray(r5)
            java.util.List r2 = r14.a(r2)
            goto Lde
        Ld8:
            java.lang.String r2 = "service use default countryOrAreaGroup"
            com.huawei.hms.framework.common.Logger.i(r8, r2)
            r2 = 0
        Lde:
            r3.a(r2)
            com.huawei.hms.framework.network.grs.local.model.a r2 = r14.f4536a
            if (r2 != 0) goto Lec
            com.huawei.hms.framework.network.grs.local.model.a r2 = new com.huawei.hms.framework.network.grs.local.model.a
            r2.<init>()
            r14.f4536a = r2
        Lec:
            com.huawei.hms.framework.network.grs.local.model.a r2 = r14.f4536a
            r2.a(r4, r3)
        Lf1:
            int r1 = r1 + 1
            goto Lc
        Lf5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.f.a.b(org.json.JSONArray):void");
    }

    public com.huawei.hms.framework.network.grs.local.model.a b() {
        return this.f4536a;
    }

    public void a(Context context, List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str : list) {
            Logger.d("AbstractLocalManager", "getBatchLoadSdkSuccessFlag file:" + str);
            if (TextUtils.isEmpty(str) || !Pattern.matches("^grs_sdk_global_route_config_[a-zA-Z]+\\.json$", str)) {
                Logger.d("AbstractLocalManager", "load SDK_CONFIG_FILE: %s, skipped.", str);
            } else {
                Object[] objArr = {str};
                if (i(com.huawei.hms.framework.network.grs.h.c.a(GrsApp.getInstance().getBrand("/") + str, context)) == 0) {
                    Logger.d("AbstractLocalManager", "load SDK_CONFIG_FILE: %s, sucess.", objArr);
                } else {
                    Logger.w("AbstractLocalManager", "load SDK_CONFIG_FILE: %s, failure.", objArr);
                }
            }
        }
    }

    public JSONObject a() {
        JSONObject b = this.f4536a.b();
        if (this.b != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator<com.huawei.hms.framework.network.grs.local.model.b> it = this.b.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().c());
            }
            b.put("countryGroups", jSONArray);
        }
        return b;
    }

    public Map<String, String> a(Context context, com.huawei.hms.framework.network.grs.e.a aVar, GrsBaseInfo grsBaseInfo, String str, boolean z) {
        com.huawei.hms.framework.network.grs.local.model.a aVar2 = this.f4536a;
        if (aVar2 == null) {
            Logger.w("AbstractLocalManager", "application data is null.");
            return null;
        }
        com.huawei.hms.framework.network.grs.local.model.c a2 = aVar2.a(str);
        if (a2 == null) {
            Logger.w("AbstractLocalManager", "service not found in local config{%s}", str);
            return null;
        }
        String b = e.b(context, aVar, a2.b(), grsBaseInfo, z);
        if (b == null) {
            Logger.w("AbstractLocalManager", "country not found by routeby in local config{%s}", a2.b());
            return null;
        }
        List<com.huawei.hms.framework.network.grs.local.model.b> a3 = a2.a();
        com.huawei.hms.framework.network.grs.local.model.d a4 = a2.a((a3 == null || a3.size() == 0) ? a(b) : a(a3, grsBaseInfo, b).get(b));
        if (a4 == null) {
            return null;
        }
        return a4.a();
    }

    public List<com.huawei.hms.framework.network.grs.local.model.b> a(JSONArray jSONArray) {
        JSONArray jSONArray2;
        if (jSONArray == null || jSONArray.length() == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            com.huawei.hms.framework.network.grs.local.model.b bVar = new com.huawei.hms.framework.network.grs.local.model.b();
            bVar.b(jSONObject.getString("id"));
            bVar.c(jSONObject.getString("name"));
            bVar.a(jSONObject.getString("description"));
            String str = "countriesOrAreas";
            if (!jSONObject.has("countriesOrAreas")) {
                str = "countries";
                if (!jSONObject.has("countries")) {
                    Logger.w("AbstractLocalManager", "current country or area group has not config countries or areas.");
                    jSONArray2 = null;
                    HashSet hashSet = new HashSet(16);
                    if (jSONArray2 != null || jSONArray2.length() == 0) {
                        return new ArrayList();
                    }
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        hashSet.add((String) jSONArray2.get(i2));
                    }
                    bVar.a(hashSet);
                    arrayList.add(bVar);
                }
            }
            jSONArray2 = jSONObject.getJSONArray(str);
            HashSet hashSet2 = new HashSet(16);
            if (jSONArray2 != null) {
            }
            return new ArrayList();
        }
        return arrayList;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if ("no_route_country".equals(str)) {
            return "no-country";
        }
        List<com.huawei.hms.framework.network.grs.local.model.b> list = this.b;
        if (list != null && !list.isEmpty()) {
            for (com.huawei.hms.framework.network.grs.local.model.b bVar : this.b) {
                if (bVar.a().contains(str)) {
                    return bVar.b();
                }
            }
        }
        return null;
    }

    public String a(Context context, com.huawei.hms.framework.network.grs.e.a aVar, GrsBaseInfo grsBaseInfo, String str, String str2, boolean z) {
        Map<String, String> a2 = a(context, aVar, grsBaseInfo, str, z);
        if (a2 != null) {
            return a2.get(str2);
        }
        Logger.w("AbstractLocalManager", "addresses not found by routeby in local config{%s}", str);
        return null;
    }

    int a(String str, Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(GrsApp.getInstance().getBrand("/"));
        sb.append(str);
        return b(sb.toString(), context) != 0 ? -1 : 0;
    }

    private int i(String str) {
        List<com.huawei.hms.framework.network.grs.local.model.b> list;
        int e;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return (!this.d || !((list = this.b) == null || list.isEmpty()) || (e = e(str)) == 0) ? f(str) : e;
    }

    private int h(String str) {
        int c;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (this.d && (c = c(str)) != 0) {
            return c;
        }
        int b = b(str);
        return b != 0 ? b : g(str);
    }

    private int b(String str, Context context) {
        if (h(com.huawei.hms.framework.network.grs.h.c.a(str, context)) != 0) {
            return -1;
        }
        Logger.i("AbstractLocalManager", "load APP_CONFIG_FILE success{%s}.", str);
        return 0;
    }

    private Map<String, String> a(List<com.huawei.hms.framework.network.grs.local.model.b> list, GrsBaseInfo grsBaseInfo, String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.put("no_route_country", "no-country");
        for (com.huawei.hms.framework.network.grs.local.model.b bVar : list) {
            if (bVar.a().contains(grsBaseInfo.getIssueCountry())) {
                concurrentHashMap.put(grsBaseInfo.getIssueCountry(), bVar.b());
            }
            if (bVar.a().contains(grsBaseInfo.getRegCountry())) {
                concurrentHashMap.put(grsBaseInfo.getRegCountry(), bVar.b());
            }
            if (bVar.a().contains(grsBaseInfo.getSerCountry())) {
                concurrentHashMap.put(grsBaseInfo.getSerCountry(), bVar.b());
            }
            if (bVar.a().contains(str)) {
                Logger.v("AbstractLocalManager", "get countryGroupID from geoIp");
                concurrentHashMap.put(str, bVar.b());
            }
        }
        return concurrentHashMap;
    }
}
