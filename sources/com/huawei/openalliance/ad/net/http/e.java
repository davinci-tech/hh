package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.kf;
import com.huawei.openalliance.ad.kl;
import com.huawei.openalliance.ad.ko;
import com.huawei.openalliance.ad.net.http.a;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes5.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    final f f7304a;
    final int b;
    final int c;
    final h d;
    final ko e;
    final ko f;
    final boolean g;
    final Context h;
    final boolean i;
    final boolean j;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        Context f7306a;
        f b;
        h e;
        ko f;
        ko g;
        boolean i;
        boolean k;
        int c = 10000;
        int d = 10000;
        int h = 1;
        boolean j = true;

        public a c(boolean z) {
            this.k = z;
            return this;
        }

        public a c(int i) {
            this.h = i;
            return this;
        }

        public a b(boolean z) {
            this.j = z;
            return this;
        }

        public a b(ko koVar) {
            this.g = koVar;
            return this;
        }

        public a b(int i) {
            this.d = i;
            return this;
        }

        public e a() {
            return new e(this);
        }

        public a a(boolean z) {
            this.i = z;
            return this;
        }

        public a a(ko koVar) {
            this.f = koVar;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public a(Context context) {
            this.f7306a = context.getApplicationContext();
        }
    }

    public <T> T a(final Class<T> cls) {
        if (cls == null) {
            throw new NullPointerException("service class cannot be null");
        }
        if (cls.isInterface()) {
            return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.huawei.openalliance.ad.net.http.e.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) {
                    if (method.getDeclaringClass() == Object.class) {
                        return method.invoke(this, objArr);
                    }
                    kl b = e.this.b(cls);
                    com.huawei.openalliance.ad.net.http.a a2 = new a.C0201a(e.this, method, objArr, e.this.c(cls), b).a();
                    Response response = new Response();
                    try {
                        response = e.this.d.b(e.this, a2);
                    } catch (IllegalStateException | Exception e) {
                        response.a(e);
                    }
                    ho.b("HttpCall", "response http code: %d", Integer.valueOf(response.a()));
                    if (ho.a()) {
                        ho.a("HttpCall", "response exception: %s", response.d());
                    }
                    return response;
                }
            });
        }
        throw new IllegalArgumentException("Service must be interface.");
    }

    c a(kf kfVar) {
        c cVar = new c();
        if (kfVar != null) {
            for (String str : kfVar.a()) {
                String[] split = str.split(":");
                if (split.length >= 2) {
                    cVar.a(split[0].trim(), split[1].trim());
                }
            }
        }
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> c c(Class<T> cls) {
        return a((kf) cls.getAnnotation(kf.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> kl b(Class<T> cls) {
        return (kl) cls.getAnnotation(kl.class);
    }

    e(a aVar) {
        this.f7304a = aVar.b;
        this.b = aVar.c;
        this.c = aVar.d;
        this.d = aVar.e != null ? aVar.e : HttpCallerFactory.a(aVar.f7306a, aVar.h);
        this.e = aVar.f;
        this.f = aVar.g;
        this.g = aVar.i;
        this.h = aVar.f7306a;
        this.i = aVar.j;
        this.j = aVar.k;
    }
}
