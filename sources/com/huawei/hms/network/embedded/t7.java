package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.j7;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class t7 {

    /* renamed from: a, reason: collision with root package name */
    public final m7 f5500a;
    public final String b;
    public final j7 c;

    @Nullable
    public final u7 d;
    public final Map<Class<?>, Object> e;

    @Nullable
    public volatile s6 f;
    public final boolean g;
    public final ArrayList<InetAddress> h;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        public m7 f5501a;
        public String b;
        public j7.a c;

        @Nullable
        public u7 d;
        public Map<Class<?>, Object> e;
        public boolean f;
        public ArrayList<InetAddress> g;

        public a d(u7 u7Var) {
            return a(ProfileRequestConstants.PUT_TYPE, u7Var);
        }

        public a d() {
            return a("HEAD", (u7) null);
        }

        public a c(String str) {
            StringBuilder sb;
            int i;
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (!str.regionMatches(true, 0, "ws:", 0, 3)) {
                if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                    sb = new StringBuilder("https:");
                    i = 4;
                }
                return a(m7.f(str));
            }
            sb = new StringBuilder("http:");
            i = 3;
            sb.append(str.substring(i));
            str = sb.toString();
            return a(m7.f(str));
        }

        public a c(u7 u7Var) {
            return a("POST", u7Var);
        }

        public a c() {
            return a("GET", (u7) null);
        }

        public a b(String str, String str2) {
            this.c.d(str, str2);
            return this;
        }

        public a b(String str) {
            this.c.d(str);
            return this;
        }

        public a b(u7 u7Var) {
            return a("PATCH", u7Var);
        }

        public a b() {
            return a(f8.e);
        }

        public t7 a() {
            if (this.f5501a != null) {
                return new t7(this);
            }
            throw new IllegalStateException("url == null");
        }

        public a a(boolean z) {
            this.f = z;
            return this;
        }

        public a a(ArrayList<String> arrayList) throws UnknownHostException {
            if (arrayList == null) {
                throw new IllegalArgumentException("additionalIpAddresses is null");
            }
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            return this;
        }

        public a a(URL url) {
            if (url != null) {
                return a(m7.f(url.toString()));
            }
            throw new NullPointerException("url == null");
        }

        public a a(String str, String str2) {
            this.c.a(str, str2);
            return this;
        }

        public a a(String str, @Nullable u7 u7Var) {
            if (str == null) {
                throw new NullPointerException("method == null");
            }
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            }
            if (u7Var != null && !j9.b(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            }
            if (u7Var != null || !j9.e(str)) {
                this.b = str;
                this.d = u7Var;
                return this;
            }
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        }

        public a a(String str) throws UnknownHostException {
            if (str == null) {
                throw new IllegalArgumentException("IP address is null");
            }
            try {
                for (InetAddress inetAddress : InetAddress.getAllByName(str)) {
                    this.g.add(inetAddress);
                }
                return this;
            } catch (NullPointerException e) {
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + str);
                unknownHostException.initCause(e);
                throw unknownHostException;
            }
        }

        public a a(@Nullable Object obj) {
            return a((Class<? super Class>) Object.class, (Class) obj);
        }

        public <T> a a(Class<? super T> cls, @Nullable T t) {
            if (cls == null) {
                throw new NullPointerException("type == null");
            }
            if (t == null) {
                this.e.remove(cls);
            } else {
                if (this.e.isEmpty()) {
                    this.e = new LinkedHashMap();
                }
                this.e.put(cls, cls.cast(t));
            }
            return this;
        }

        public a a(@Nullable u7 u7Var) {
            return a(ProfileRequestConstants.DELETE_TYPE, u7Var);
        }

        public a a(s6 s6Var) {
            String s6Var2 = s6Var.toString();
            return s6Var2.isEmpty() ? b("Cache-Control") : b("Cache-Control", s6Var2);
        }

        public a a(m7 m7Var) {
            if (m7Var == null) {
                throw new NullPointerException("url == null");
            }
            this.f5501a = m7Var;
            return this;
        }

        public a a(j7 j7Var) {
            this.c = j7Var.c();
            return this;
        }

        public a(t7 t7Var) {
            this.e = Collections.emptyMap();
            this.f = false;
            this.g = new ArrayList<>();
            this.f5501a = t7Var.f5500a;
            this.b = t7Var.b;
            this.d = t7Var.d;
            this.e = t7Var.e.isEmpty() ? Collections.emptyMap() : new LinkedHashMap<>(t7Var.e);
            this.c = t7Var.c.c();
            this.f = t7Var.g;
            this.g = t7Var.h;
        }

        public a() {
            this.e = Collections.emptyMap();
            this.f = false;
            this.g = new ArrayList<>();
            this.b = "GET";
            this.c = new j7.a();
        }
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.f5500a + ", tags=" + this.e + '}';
    }

    public m7 k() {
        return this.f5500a;
    }

    @Nullable
    public Object j() {
        return a(Object.class);
    }

    public a i() {
        return new a(this);
    }

    public String h() {
        return this.b;
    }

    public boolean g() {
        return this.f5500a.i();
    }

    public boolean f() {
        return a("Http2ConnectionIndex") != null;
    }

    public j7 e() {
        return this.c;
    }

    public boolean d() {
        return this.g;
    }

    public s6 c() {
        s6 s6Var = this.f;
        if (s6Var != null) {
            return s6Var;
        }
        s6 a2 = s6.a(this.c);
        this.f = a2;
        return a2;
    }

    public List<String> b(String str) {
        return this.c.d(str);
    }

    @Nullable
    public u7 b() {
        return this.d;
    }

    public ArrayList<InetAddress> a() {
        return this.h;
    }

    @Nullable
    public String a(String str) {
        return this.c.a(str);
    }

    @Nullable
    public <T> T a(Class<? extends T> cls) {
        return cls.cast(this.e.get(cls));
    }

    public t7(a aVar) {
        this.f5500a = aVar.f5501a;
        this.b = aVar.b;
        this.c = aVar.c.a();
        this.d = aVar.d;
        this.e = f8.a(aVar.e);
        this.g = aVar.f;
        this.h = aVar.g;
    }
}
