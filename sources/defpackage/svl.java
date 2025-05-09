package defpackage;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class svl {
    private static volatile svl b = null;
    private static final byte[] d = new byte[0];
    public static final String e = "WalletLocalManager";

    /* renamed from: a, reason: collision with root package name */
    private svi f17250a;
    private List<svn> c;
    private boolean g;

    private svl(Context context) {
        this.g = false;
        if (a(a(context, "grs_sdk_global_route_config.json")) == 0) {
            this.g = true;
        }
    }

    private String a(Context context, String str) {
        if (context == null) {
            stq.c(e, "getConfigContent context is null.", false);
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            stq.c(e, "getConfigContent fileName is null.", false);
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            try {
                inputStream = context.getAssets().open(str);
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (-1 == read) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                    stq.c(e, "closeQuietly IOException", false);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                        stq.c(e, "closeQuietly IOException", false);
                    }
                }
                return str2;
            } catch (IOException unused3) {
                stq.c(e, "getConfigContent IOException: ", false);
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused4) {
                    stq.c(e, "closeQuietly IOException", false);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused5) {
                        stq.c(e, "closeQuietly IOException", false);
                    }
                }
                return "";
            }
        } finally {
        }
    }

    public Map<String, String> d(String str, String str2) {
        if (!this.g) {
            return new HashMap(0);
        }
        svp c = this.f17250a.c(str2);
        if (c == null) {
            stq.c(e, "service not found in local config", false);
            return new HashMap(0);
        }
        svo e2 = c.e(d(str));
        if (e2 == null) {
            stq.c(e, "countryGroupId not found by routeby in local config", false);
            return new HashMap(0);
        }
        stq.c(e, "getServicesUrlsFromLocal = " + e2.b(), false);
        return e2.b();
    }

    private int a(String str) {
        if (TextUtils.isEmpty(str)) {
            stq.c(e, "getConfigMgr configContent is null.", false);
            return -1;
        }
        int e2 = e(str);
        if (e2 != 0) {
            return e2;
        }
        int b2 = b(str);
        return b2 != 0 ? b2 : c(str);
    }

    private int b(String str) {
        this.f17250a = new svi();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("application");
            String string = jSONObject.getString("name");
            long j = jSONObject.getLong("cacheControl");
            JSONArray jSONArray = jSONObject.getJSONArray("services");
            this.f17250a.d(string);
            this.f17250a.a(j);
            if (jSONArray != null && jSONArray.length() != 0) {
                return 0;
            }
            stq.c(e, "local config application.services is not config or no any services", false);
            return -1;
        } catch (JSONException unused) {
            stq.c(e, "parse appbean failed", false);
            return -1;
        }
    }

    private int e(String str) {
        this.c = new ArrayList(16);
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("countryGroups");
            if (jSONObject.length() != 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next instanceof String) {
                        String str2 = next;
                        svn svnVar = new svn();
                        svnVar.c(str2);
                        JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                        svnVar.b(jSONObject2.getString("name"));
                        svnVar.a(jSONObject2.getString("description"));
                        JSONArray jSONArray = jSONObject2.getJSONArray("countries");
                        HashSet hashSet = new HashSet(16);
                        if (jSONArray != null && jSONArray.length() != 0) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                if (jSONArray.get(i) instanceof String) {
                                    hashSet.add((String) jSONArray.get(i));
                                }
                            }
                            svnVar.e(hashSet);
                            this.c.add(svnVar);
                        }
                        stq.c(e, "local config countryGroups.groupId.countries is not config or no any countries", false);
                        return -1;
                    }
                }
            }
            return 0;
        } catch (JSONException unused) {
            stq.c(e, "parse countryGroup failed", false);
            return -1;
        }
    }

    private int c(String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("services");
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next instanceof String) {
                    String str3 = next;
                    svp svpVar = new svp();
                    svpVar.d(str3);
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str3);
                    svpVar.a(jSONObject2.getString("routeBy"));
                    JSONArray jSONArray = jSONObject2.getJSONArray("servings");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (jSONArray.get(i) instanceof JSONObject) {
                            JSONObject jSONObject3 = (JSONObject) jSONArray.get(i);
                            svo svoVar = new svo();
                            if (!jSONObject3.has("countryGroup")) {
                                str2 = "no-country";
                            } else {
                                str2 = jSONObject3.getString("countryGroup");
                            }
                            svoVar.c(str2);
                            JSONObject jSONObject4 = jSONObject3.getJSONObject("addresses");
                            HashMap hashMap = new HashMap(16);
                            Iterator<String> keys2 = jSONObject4.keys();
                            while (keys2.hasNext()) {
                                String next2 = keys2.next();
                                if (next2 instanceof String) {
                                    String str4 = next2;
                                    hashMap.put(str4, jSONObject4.getString(str4));
                                }
                            }
                            svoVar.b(hashMap);
                            svpVar.d(svoVar.e(), svoVar);
                        }
                    }
                    this.f17250a.c(str3, svpVar);
                }
            }
            return 0;
        } catch (JSONException unused) {
            stq.c(e, "parse services failed", false);
            return -1;
        }
    }

    public String d(String str) {
        List<svn> list = this.c;
        if (list == null || list.size() <= 0) {
            return "";
        }
        for (svn svnVar : this.c) {
            if (svnVar != null && svnVar.b() != null && svnVar.b().contains(str)) {
                return svnVar.a();
            }
        }
        return "";
    }

    public static svl c(Context context) {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new svl(context);
                }
            }
        }
        return b;
    }
}
