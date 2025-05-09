package defpackage;

import android.content.Context;
import com.huawei.taboo.TabooReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes6.dex */
public class nhz {
    private static volatile nhz e;
    private Context c;

    /* renamed from: a, reason: collision with root package name */
    private c f15294a = null;
    private WeakHashMap<String, HashMap<String, String>> d = null;
    private List<String> b = null;

    public class c {
        private HashMap<String, String> d;
        private List<String> e = new ArrayList();
        private List<String> b = new ArrayList();
        private List<String> c = new ArrayList();

        public c(HashMap<String, String> hashMap) {
            this.d = new HashMap<>();
            if (hashMap.isEmpty()) {
                return;
            }
            this.d = hashMap;
            c(hashMap.get(TabooReader.ParamType.CITY_NAME.getScopeName()), hashMap.get(TabooReader.ParamType.LANGUAGE_NAME.getScopeName()), hashMap.get(TabooReader.ParamType.REGION_NAME.getScopeName()));
        }

        private void c(String str, String str2, String str3) {
            if (str != null && !str.isEmpty()) {
                this.e = Arrays.asList(str.split(","));
            }
            if (str2 != null && !str2.isEmpty()) {
                this.b = Arrays.asList(str2.split(","));
            }
            if (str3 == null || str3.isEmpty()) {
                return;
            }
            this.c = Arrays.asList(str3.split(","));
        }

        public List<String> b() {
            return this.e;
        }

        public List<String> a() {
            return this.b;
        }

        public List<String> c() {
            return this.c;
        }

        public String b(String str) {
            HashMap<String, String> hashMap = this.d;
            return hashMap != null ? hashMap.get(str) : "";
        }
    }

    private nhz(Context context) {
        this.c = context;
    }

    public static nhz c(Context context) {
        if (e == null) {
            synchronized (nhz.class) {
                if (e == null) {
                    e = new nhz(context);
                    e.b = nif.d(context);
                    nhz nhzVar = e;
                    nhz nhzVar2 = e;
                    nhzVar2.getClass();
                    nhzVar.f15294a = nhzVar2.new c(nif.b(context));
                    if (e.f15294a.d == null || e.f15294a.d.isEmpty()) {
                        e = null;
                    }
                }
            }
        }
        return e;
    }

    public String c(String str, String str2) {
        HashMap<String, String> hashMap;
        if (this.d == null) {
            this.d = new WeakHashMap<>();
        }
        if (this.d.containsKey(str) && (hashMap = this.d.get(str)) != null && hashMap.containsKey(str2)) {
            return hashMap.get(str2);
        }
        HashMap<String, String> e2 = new nif().e(str, this.c);
        if (e2.isEmpty()) {
            return "";
        }
        this.d.put(str, e2);
        return e2.get(str2);
    }

    public c c() {
        return this.f15294a;
    }

    public List<String> b() {
        return this.b;
    }

    public Context a() {
        return this.c;
    }
}
