package defpackage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class kum {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dataType")
    private kun f14607a;

    @SerializedName("endLocalDate")
    private String b;

    @SerializedName("groupBy")
    private c c;

    @SerializedName("dataSourceOptions")
    private kuj d;

    @SerializedName("count")
    private int e;

    @SerializedName("startLocalDate")
    private String g;

    @SerializedName("sortOrder")
    private int h;

    @SerializedName("metrics")
    private JsonObject i;

    public c a() {
        return this.c;
    }

    public int c() {
        return this.e;
    }

    public List<b> g() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, JsonElement> entry : this.i.entrySet()) {
            b bVar = new b();
            bVar.c(entry.getKey());
            bVar.b(entry.getValue().getAsJsonArray());
            arrayList.add(bVar);
        }
        return arrayList;
    }

    public kuj d() {
        return this.d;
    }

    public String h() {
        return this.g;
    }

    public String b() {
        return this.b;
    }

    public kun e() {
        return this.f14607a;
    }

    public int i() {
        return this.h;
    }

    public String toString() {
        return "AggregateDataRequest{groupBy=" + this.c + ", metrics=" + this.i + ", startLocalDate='" + this.g + "', endLocalDate='" + this.b + "', dataType=" + this.f14607a + ", sortOrder=" + this.h + ", dataSourceOptions=" + this.d + '}';
    }

    public class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("unitType")
        private int f14609a;

        public int d() {
            return this.f14609a;
        }

        public String toString() {
            return "Group{unitType=" + this.f14609a + '}';
        }
    }

    public class b {
        private String b;

        /* renamed from: a, reason: collision with root package name */
        private List<Integer> f14608a = new ArrayList();
        private List<String> e = new ArrayList();

        public b() {
        }

        public String c() {
            return this.b;
        }

        public void c(String str) {
            this.b = str;
        }

        public List<String> d() {
            return this.e;
        }

        public List<Integer> a() {
            return this.f14608a;
        }

        public void b(JsonArray jsonArray) {
            this.f14608a.clear();
            this.e.clear();
            Iterator<JsonElement> it = jsonArray.iterator();
            while (it.hasNext()) {
                JsonElement next = it.next();
                this.f14608a.add(Integer.valueOf(kuh.c(next.getAsString())));
                this.e.add(next.getAsString());
            }
        }

        public String toString() {
            return "Metric{key='" + this.b + "', aggregateTypes=" + this.f14608a + '}';
        }
    }

    public Map<Integer, JsonObject> f() {
        HashMap hashMap = new HashMap();
        int i = 0;
        for (b bVar : g()) {
            for (String str : bVar.d()) {
                JsonObject jsonObject = new JsonObject();
                int c2 = kuf.a().c(this, bVar.c(), kuh.c(str));
                String str2 = kuh.i;
                if (c2 == -1) {
                    c2 = kuf.a().c(e().a(), bVar.c())[0];
                }
                jsonObject.addProperty(str2, Integer.valueOf(c2));
                jsonObject.addProperty(kuh.f14600a, bVar.c());
                jsonObject.addProperty(kuh.c, str);
                jsonObject.addProperty(kuh.d, Integer.valueOf(e().a()));
                hashMap.put(Integer.valueOf(i), jsonObject);
                i++;
            }
        }
        return hashMap;
    }
}
