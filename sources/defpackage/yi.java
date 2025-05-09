package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.utils.AbiUtil;
import com.huawei.hms.network.embedded.g4;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class yi {

    /* renamed from: a, reason: collision with root package name */
    private final String f17757a;
    private boolean b;
    private int c;
    private List<String> d;
    private boolean e;
    private int f;
    private c g;
    private String h;
    private final String i;
    private String j;
    private long k;
    private String l;
    private int m;
    private List<String> n;
    private long o;

    private yi(String str, String str2) {
        this.i = str;
        this.f17757a = str2;
    }

    public String f() {
        return this.i;
    }

    public String g() {
        return this.l;
    }

    public String j() {
        return this.j;
    }

    public String i() {
        return this.h;
    }

    public long h() {
        return this.o;
    }

    public long o() {
        return this.k;
    }

    public int a() {
        return this.m;
    }

    public boolean k() {
        return this.e;
    }

    public boolean s() {
        return this.b;
    }

    public c b() {
        return this.g;
    }

    public List<String> e() {
        return this.d;
    }

    public boolean l() {
        return this.c > 0;
    }

    public boolean m() {
        c cVar = this.g;
        return (cVar == null || cVar.b().isEmpty()) ? false : true;
    }

    public List<String> n() {
        return this.n;
    }

    public int d() {
        return this.f;
    }

    boolean q() {
        return (TextUtils.isEmpty(this.i) || TextUtils.isEmpty(this.l) || TextUtils.isEmpty(this.j) || this.o <= 0) ? false : true;
    }

    public String c() {
        return this.f17757a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("{");
        sb.append(this.i);
        sb.append('_');
        sb.append(this.h);
        sb.append(", builtIn:");
        sb.append(this.e);
        sb.append(", isolated:");
        sb.append(this.b);
        sb.append(", size:");
        sb.append(this.o);
        if (!this.e) {
            sb.append(", zip(");
            sb.append(this.m);
            sb.append(", ");
            sb.append(this.k);
            sb.append(g4.l);
        }
        if (!this.d.isEmpty()) {
            sb.append(", dependencies:");
            sb.append(this.d);
        }
        if (!this.n.isEmpty()) {
            sb.append(", workProcesses:");
            sb.append(this.n);
        }
        c cVar = this.g;
        if (cVar != null) {
            cVar.b(sb);
        }
        sb.append('}');
        return sb.toString();
    }

    public static class c {
        private final String b;
        private final List<C0336c> c;

        private c(String str, List<C0336c> list) {
            this.b = str;
            this.c = list;
        }

        public String d() {
            return this.b;
        }

        public List<C0336c> b() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public StringBuilder b(StringBuilder sb) {
            sb.append(", lib(");
            sb.append(this.b);
            sb.append(", ");
            sb.append(this.c.size());
            sb.append(g4.l);
            return sb;
        }

        /* renamed from: yi$c$c, reason: collision with other inner class name */
        public static class C0336c {
            private final long b;
            private final String d;
            private final String e;

            private C0336c(String str, String str2, long j) {
                this.e = str;
                this.d = str2;
                this.b = j;
            }

            public String d() {
                return this.e;
            }

            public String e() {
                return this.d;
            }

            public long a() {
                return this.b;
            }
        }
    }

    static yi e(String str, String str2, List<String> list, JSONObject jSONObject) {
        yi yiVar = new yi(str2, str);
        yiVar.e = jSONObject.optBoolean("builtIn");
        yiVar.b = jSONObject.optBoolean("isolated");
        yiVar.h = jSONObject.optString("version");
        yiVar.l = jSONObject.optString("url");
        yiVar.j = jSONObject.optString("sha256");
        yiVar.k = jSONObject.optLong("zipSize");
        yiVar.m = jSONObject.optInt("zipFileNum");
        yiVar.o = jSONObject.optLong("size");
        yiVar.f = jSONObject.optInt("minSdkVersion");
        yiVar.c = jSONObject.optInt("dexNumber");
        yiVar.n = d(jSONObject);
        yiVar.d = a(jSONObject);
        yiVar.g = e(jSONObject).get(AbiUtil.b(list));
        return yiVar;
    }

    private static List<String> d(JSONObject jSONObject) {
        List<String> emptyList = Collections.emptyList();
        JSONArray optJSONArray = jSONObject.optJSONArray("workProcesses");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            String optString = optJSONArray.optString(i);
            if (optString != null) {
                arrayList.add(optString);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static List<String> a(JSONObject jSONObject) {
        List<String> emptyList = Collections.emptyList();
        JSONArray optJSONArray = jSONObject.optJSONArray("dependencies");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return emptyList;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            String optString = optJSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static Map<String, c> e(JSONObject jSONObject) {
        Map<String, c> emptyMap = Collections.emptyMap();
        JSONArray optJSONArray = jSONObject.optJSONArray("nativeLibraries");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            emptyMap = new HashMap<>(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("abi");
                    if (!TextUtils.isEmpty(optString)) {
                        emptyMap.put(optString, e(optString, optJSONObject));
                    }
                }
            }
        }
        return emptyMap;
    }

    private static c e(String str, JSONObject jSONObject) {
        List emptyList = Collections.emptyList();
        JSONArray optJSONArray = jSONObject.optJSONArray("jniLibs");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("name");
                String optString2 = optJSONObject.optString("sha256");
                long optLong = optJSONObject.optLong("size");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && optLong != 0 && (optString.startsWith("lib") || optString.endsWith(".so"))) {
                    arrayList.add(new c.C0336c(optString, optString2, optLong));
                }
            }
            emptyList = Collections.unmodifiableList(arrayList);
        }
        return new c(str, emptyList);
    }
}
