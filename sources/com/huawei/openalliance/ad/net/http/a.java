package com.huawei.openalliance.ad.net.http;

import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.server.AdContentReq;
import com.huawei.openalliance.ad.dw;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jz;
import com.huawei.openalliance.ad.ka;
import com.huawei.openalliance.ad.kb;
import com.huawei.openalliance.ad.kc;
import com.huawei.openalliance.ad.kd;
import com.huawei.openalliance.ad.ke;
import com.huawei.openalliance.ad.kf;
import com.huawei.openalliance.ad.kg;
import com.huawei.openalliance.ad.kh;
import com.huawei.openalliance.ad.ki;
import com.huawei.openalliance.ad.kj;
import com.huawei.openalliance.ad.kk;
import com.huawei.openalliance.ad.kl;
import com.huawei.openalliance.ad.km;
import com.huawei.openalliance.ad.kn;
import com.huawei.openalliance.ad.ks;
import com.huawei.openalliance.ad.kx;
import com.huawei.openalliance.ad.net.http.f;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.ck;
import com.huawei.openalliance.ad.utils.cy;
import com.huawei.openalliance.ad.utils.o;
import com.huawei.operation.utils.Constants;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    static final Pattern f7299a = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    final Class<?> b;
    final f c;
    final String d;
    final String e;
    final c f;
    final String g;
    final int h;
    final List<String> i;
    final byte[] j;
    final boolean k;
    final int l;
    final kx m;

    /* renamed from: com.huawei.openalliance.ad.net.http.a$a, reason: collision with other inner class name */
    static class C0201a {

        /* renamed from: a, reason: collision with root package name */
        final e f7300a;
        final Method b;
        final Object[] c;
        final String d;
        final c e;
        Class<?> f;
        f g;
        String h;
        String i;
        byte[] l;
        String m;
        kx t;
        final List<String> j = new ArrayList(4);
        final Set<String> k = new LinkedHashSet();
        int n = 0;
        int o = 0;
        int p = 0;
        boolean q = false;
        boolean r = false;
        int s = 0;

        public a a() {
            b();
            d();
            if (TextUtils.isEmpty(this.h)) {
                throw a("Http method annotation is needed! (eg. GET, POST etc.", new Object[0]);
            }
            if (this.i == null) {
                throw a("Url is not specified in @GET or @POST etc.", new Object[0]);
            }
            c();
            if (this.g != null) {
                return new a(this);
            }
            throw a("No url found in the request!", new Object[0]);
        }

        private void f(Object obj, int i) {
            if (obj == null) {
                throw a("Argument %d with @HeaderMap annotation must not be null!", Integer.valueOf(i));
            }
            if (!(obj instanceof Map)) {
                throw a("Argument %d with @HeaderMap annotation can only be Map type!", Integer.valueOf(i));
            }
            for (Object obj2 : ((Map) obj).entrySet()) {
                if (!(obj2 instanceof Map.Entry)) {
                    return;
                }
                Map.Entry entry = (Map.Entry) obj2;
                Object key = entry.getKey();
                if (!(key instanceof String)) {
                    throw a("The key in HeaderMap must be String type [Argument %d]!", Integer.valueOf(i));
                }
                Object value = entry.getValue();
                if (!(value instanceof String)) {
                    throw a("The value in HeaderMap must be String type [Argument %d]!", Integer.valueOf(i));
                }
                this.e.a((String) key, (String) value);
            }
        }

        private void e(Object obj, int i) {
            if (obj == null) {
                throw a("Argument %d with @ResponseEntity annotation must not be null!", Integer.valueOf(i));
            }
            if (!(obj instanceof Class)) {
                throw a("Argument %d with @HeaderMap annotation can only be Class type!", Integer.valueOf(i));
            }
            this.f = (Class) obj;
        }

        private void e() {
            this.e.a(this.f7300a.a((kf) this.b.getAnnotation(kf.class)));
        }

        private void d(Object obj, int i) {
            if (obj == null) {
                throw a("Argument %d with @ResponseConverter annotation must not be null!", Integer.valueOf(i));
            }
            if (!(obj instanceof kx)) {
                throw a("Argument %d with @Url annotation can only be IResponseConversionInterceptor type!", Integer.valueOf(i));
            }
            this.t = (kx) obj;
        }

        private void d() {
            for (Annotation annotation : this.b.getAnnotations()) {
                a(annotation);
            }
        }

        private void c(Object obj, int i) {
            if (obj == null) {
                throw a("Argument %d with @UserAgentSource annotation must not be null!", Integer.valueOf(i));
            }
            if (obj.getClass() != Integer.TYPE && !(obj instanceof Integer)) {
                throw a("Argument %d with @UserAgentSource annotation must be int or Integer", Integer.valueOf(i));
            }
            this.p = ((Integer) obj).intValue();
        }

        private void c() {
            Annotation[][] parameterAnnotations = this.b.getParameterAnnotations();
            int length = parameterAnnotations.length;
            if (length != this.c.length) {
                throw a("Parameter annotation number doesn't equal to parameter number", new Object[0]);
            }
            for (int i = 0; i < length; i++) {
                Annotation[] annotationArr = parameterAnnotations[i];
                if (annotationArr == null || annotationArr.length == 0) {
                    throw a("Argument " + i + " doesn't have annotations!", new Object[0]);
                }
                for (Annotation annotation : annotationArr) {
                    a(annotation, i);
                }
            }
        }

        private void b(Object obj, int i) {
            if (obj == null) {
                throw a("Argument %d with @Gzip annotation must not be null!", Integer.valueOf(i));
            }
            if (obj.getClass() != Boolean.TYPE && !(obj instanceof Boolean)) {
                throw a("Argument %d with @Gzip annotation must be boolean or Boolean", Integer.valueOf(i));
            }
            this.r = ((Boolean) obj).booleanValue();
        }

        private void b() {
            if (this.b.getReturnType() != Response.class) {
                throw a("Return type must be com.huawei.openplatform.baselibrary.net.http.Response!", new Object[0]);
            }
            Type genericReturnType = this.b.getGenericReturnType();
            if (!(genericReturnType instanceof ParameterizedType)) {
                throw a("Return type must be parameterized, eg. Response<Foo>.", new Object[0]);
            }
            this.f = ck.a(ck.a(0, (ParameterizedType) genericReturnType));
        }

        private static byte[] a(byte[] bArr) {
            GZIPOutputStream gZIPOutputStream;
            ho.b("AccessMethod.Builder", "gzip content");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.finish();
                    return byteArrayOutputStream.toByteArray();
                } catch (Throwable th) {
                    th = th;
                    try {
                        ho.c("AccessMethod.Builder", "gzip fail " + th.getClass().getSimpleName());
                        return null;
                    } finally {
                        cy.a(gZIPOutputStream);
                        cy.a(byteArrayOutputStream);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
            }
        }

        private void a(Annotation annotation, int i) {
            if (annotation instanceof kh) {
                a((kh) annotation, this.c[i], i);
                return;
            }
            if (annotation instanceof ki) {
                a((ki) annotation, this.c[i], i);
                return;
            }
            if (annotation instanceof jz) {
                a(this.c[i]);
                return;
            }
            if (annotation instanceof kd) {
                a((kd) annotation, this.c[i], i);
                return;
            }
            if (annotation instanceof ke) {
                f(this.c[i], i);
                return;
            }
            if (annotation instanceof kk) {
                e(this.c[i], i);
                return;
            }
            if (annotation instanceof kl) {
                a(this.c[i], ((kl) annotation).b(), i);
                return;
            }
            if (annotation instanceof kj) {
                d(this.c[i], i);
                return;
            }
            if (annotation instanceof kn) {
                c(this.c[i], i);
            } else if (annotation instanceof kc) {
                b(this.c[i], i);
            } else if (annotation instanceof ka) {
                a(this.c[i], i);
            }
        }

        private void a(Annotation annotation) {
            String a2;
            String str;
            if (annotation instanceof kb) {
                a2 = ((kb) annotation).a();
                str = "GET";
            } else {
                if (!(annotation instanceof kg)) {
                    if (annotation instanceof kf) {
                        e();
                        return;
                    } else if (annotation instanceof kl) {
                        a((kl) annotation);
                        return;
                    } else {
                        if (annotation instanceof km) {
                            a((km) annotation);
                            return;
                        }
                        return;
                    }
                }
                a2 = ((kg) annotation).a();
                str = "POST";
            }
            a(str, a2);
        }

        private void a(String str, String str2) {
            this.h = str;
            this.i = str2;
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            int indexOf = str2.indexOf(63);
            if (indexOf != -1 && indexOf < str2.length() - 1) {
                String substring = str2.substring(indexOf + 1);
                if (a.f7299a.matcher(substring).find()) {
                    throw a("URL query \"%s\" must not have replace block. use @Query instead.", substring);
                }
            }
            this.k.addAll(a(str2));
        }

        private void a(Object obj, boolean z, int i) {
            if (obj == null) {
                throw a("Argument %d with @Url annotation must not be null!", Integer.valueOf(i));
            }
            if (!(obj instanceof String)) {
                throw a("Argument %d with @Url annotation can only be String type!", Integer.valueOf(i));
            }
            if (!TextUtils.isEmpty(this.i)) {
                throw a("Relative path in GET/POST annotation must be empty with @Url annotation as parameter!", new Object[0]);
            }
            this.g = new f.a(z).a(this.f7300a.h, (String) obj).a();
        }

        private void a(Object obj, int i) {
            if (obj == null) {
                throw a("Argument %d with @FB annotation must not be null!", Integer.valueOf(i));
            }
            if (obj.getClass() != Integer.TYPE && !(obj instanceof Integer)) {
                throw a("Argument %d with @Fb annotation must be int or Integer", Integer.valueOf(i));
            }
            this.s = ((Integer) obj).intValue();
        }

        private void a(Object obj) {
            int i = this.o + 1;
            this.o = i;
            if (i > 1) {
                throw a("There are more than one @Body arguments in method!", new Object[0]);
            }
            if (obj == null) {
                this.l = null;
                return;
            }
            if (obj instanceof byte[]) {
                this.l = (byte[]) obj;
                return;
            }
            if (2 == this.s && (obj instanceof AdContentReq)) {
                this.l = dw.a((AdContentReq) obj);
                if (ho.a()) {
                    ho.a("AccessMethod.Builder", "req in fb, origin body: %s", be.b((AdContentReq) obj));
                    ho.a("AccessMethod.Builder", "req in fb, base64 body: %s", o.a(this.l));
                    return;
                }
                return;
            }
            ks a2 = ks.a.a(this.f7300a.h, obj.getClass());
            this.m = a2.a();
            try {
                try {
                    byte[] bytes = a2.a(obj, this.f7300a.e).getBytes("UTF-8");
                    this.l = bytes;
                    if (this.r) {
                        this.l = a(bytes);
                    }
                    this.q = this.r;
                } catch (UnsupportedEncodingException unused) {
                    throw a("UnsupportedEncodingException in get bytes from content", new Object[0]);
                }
            } catch (Exception unused2) {
                throw a("process body annotation error", new Object[0]);
            }
        }

        private void a(km kmVar) {
            this.p = kmVar.a();
        }

        private void a(kl klVar) {
            this.g = new f.a(klVar.b()).a(this.f7300a.h, klVar.a()).a();
        }

        private void a(ki kiVar, Object obj, int i) {
            String a2;
            if (obj == null) {
                a2 = Constants.NULL;
            } else {
                try {
                    a2 = ks.a.a(this.f7300a.h, obj.getClass()).a(obj, this.f7300a.e);
                } catch (Exception unused) {
                    throw a("process query annotation error", new Object[0]);
                }
            }
            String a3 = kiVar.a();
            if (TextUtils.isEmpty(a3)) {
                throw a("Query name of " + i + " arg cannot not be empty!", new Object[0]);
            }
            this.j.add(a3 + "=" + a2);
        }

        private void a(kh khVar, Object obj, int i) {
            this.n++;
            String a2 = khVar.a();
            if (!this.k.contains(a2)) {
                throw a("Path name {" + a2 + "} (arg " + i + ") not existed in path url!", new Object[0]);
            }
            if (this.n > this.k.size()) {
                throw a("@Path argument number is more than the path param elements in url", new Object[0]);
            }
            if (obj == null) {
                throw a("Path {" + a2 + "} argument value cannot be null!", new Object[0]);
            }
            try {
                String a3 = ks.a.a(this.f7300a.h, obj.getClass()).a(obj, this.f7300a.e);
                this.i = this.i.replace("{" + a2 + "}", a3);
            } catch (Exception unused) {
                throw a("process path annotation error", new Object[0]);
            }
        }

        private void a(kd kdVar, Object obj, int i) {
            if (obj == null) {
                throw a("Argument %d with @Header annotation must not be null!", Integer.valueOf(i));
            }
            if (!(obj instanceof String)) {
                throw a("Argument %d with @Header annotation can only be String type!", Integer.valueOf(i));
            }
            this.e.a(kdVar.a(), (String) obj);
        }

        private Set<String> a(String str) {
            Matcher matcher = a.f7299a.matcher(str);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (matcher.find()) {
                linkedHashSet.add(matcher.group(1));
            }
            return linkedHashSet;
        }

        private RuntimeException a(String str, Object... objArr) {
            StringBuilder sb = new StringBuilder();
            if (objArr != null && objArr.length != 0) {
                str = String.format(Locale.ENGLISH, str, objArr);
            }
            sb.append(str);
            sb.append(" (method: ");
            sb.append(this.d);
            sb.append(Constants.RIGHT_BRACKET_ONLY);
            String sb2 = sb.toString();
            ho.c("AccessMethod.Builder", sb2);
            return new IllegalArgumentException(sb2);
        }

        C0201a(e eVar, Method method, Object[] objArr, c cVar, kl klVar) {
            this.f7300a = eVar;
            this.b = method;
            this.d = method.getName();
            this.c = objArr == null ? new Object[0] : objArr;
            this.e = cVar;
            this.g = klVar != null ? new f.a(klVar.b()).a(eVar.h, klVar.a()).a() : eVar.f7304a;
        }
    }

    String c() {
        StringBuilder sb = new StringBuilder(this.c.c());
        if (!TextUtils.isEmpty(this.d)) {
            if (!this.d.startsWith("/")) {
                sb.append('/');
            }
            sb.append(this.d);
        }
        return sb.toString();
    }

    String b() {
        return this.c.b();
    }

    boolean a() {
        return this.c.a();
    }

    private a(C0201a c0201a) {
        this.b = c0201a.f;
        this.c = c0201a.g;
        this.d = c0201a.i;
        this.e = c0201a.h;
        this.i = c0201a.j;
        this.j = c0201a.l;
        this.f = c0201a.e;
        this.m = c0201a.t;
        this.g = c0201a.m;
        this.h = c0201a.p;
        this.k = c0201a.q;
        this.l = c0201a.s;
    }
}
