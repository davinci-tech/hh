package com.huawei.hms.network.embedded;

import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.ClientConfiguration;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.base.util.TypeUtils;
import com.huawei.hms.network.embedded.g6;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.SubmitAdapter;
import com.huawei.hms.network.restclient.anno.NetworkParameters;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class k6<R, T> {
    public static final String o = "SubmitMethod";

    /* renamed from: a, reason: collision with root package name */
    public final SubmitAdapter<R, T> f5343a;
    public final Converter<ResponseBody, R> b;
    public final String c;
    public final String d;
    public final Headers e;
    public final String f;
    public final boolean g;
    public final boolean h;
    public final g6<?>[] i;
    public final d3 j;
    public boolean k;
    public static final Pattern m = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    public static final String l = "[a-zA-Z][a-zA-Z0-9_-]*";
    public static final Pattern n = Pattern.compile(l);

    public static final class b<R, T> {

        /* renamed from: a, reason: collision with root package name */
        public final h6 f5344a;
        public final Method b;
        public final Annotation[] c;
        public final Annotation[][] d;
        public final Type[] e;
        public Type f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public String l;
        public boolean m;
        public boolean n;
        public String o;
        public Headers p;
        public String q;
        public Set<String> r;
        public g6<?>[] s;
        public Converter<ResponseBody, R> t;
        public SubmitAdapter<R, T> u;
        public Annotation[] v;
        public boolean w;

        public k6 build() {
            SubmitAdapter<R, T> a2 = a(this.b);
            this.u = a2;
            this.f = a2.responseType();
            this.t = b();
            for (Annotation annotation : this.v) {
                c(annotation);
            }
            for (Annotation annotation2 : this.c) {
                d(annotation2);
            }
            if (this.l == null) {
                throw a("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if (!this.m && this.n) {
                throw a("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            }
            int length = this.d.length;
            this.s = new g6[length];
            for (int i = 0; i < length; i++) {
                Type type = this.e[i];
                if (TypeUtils.hasUnresolvableType(type)) {
                    throw a(i, "Parameter type must not include a type variable or wildcard: " + type);
                }
                Annotation[] annotationArr = this.d[i];
                if (annotationArr == null) {
                    throw a(i, "No RestClient annotation found.");
                }
                this.s[i] = d(i, type, annotationArr);
            }
            a();
            return new k6(this);
        }

        private g6<?> f(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            Class<?> rawType = TypeUtils.getRawType(type);
            if (!Map.class.isAssignableFrom(rawType)) {
                throw a(i, "@QueryMap parameter type must be Map.");
            }
            Type supertype = TypeUtils.getSupertype(type, rawType, Map.class);
            if (!(supertype instanceof ParameterizedType)) {
                throw a(i, "Map must include generic types (e.g., Map<String, String>)");
            }
            ParameterizedType parameterizedType = (ParameterizedType) supertype;
            Type parameterUpperBound = TypeUtils.getParameterUpperBound(0, parameterizedType);
            if (String.class == parameterUpperBound) {
                return new g6.k(this.f5344a.stringConverter(TypeUtils.getParameterUpperBound(1, parameterizedType), annotationArr), a(annotation));
            }
            throw a(i, "@QueryMap keys must be of type String: " + parameterUpperBound);
        }

        private g6<?> e(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            String str = (String) b(annotation);
            boolean a2 = a(annotation);
            Class<?> rawType = TypeUtils.getRawType(type);
            this.j = true;
            if (!Iterable.class.isAssignableFrom(rawType)) {
                if (!rawType.isArray()) {
                    return new g6.l(str, this.f5344a.stringConverter(type, annotationArr), a2);
                }
                return new g6.l(str, this.f5344a.stringConverter(TypeUtils.boxIfPrimitive(rawType.getComponentType()), annotationArr), a2).a();
            }
            if (type instanceof ParameterizedType) {
                return new g6.l(str, this.f5344a.stringConverter(TypeUtils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), a2).b();
            }
            throw a(i, rawType.getSimpleName() + " must include generic type (e.g., " + rawType.getSimpleName() + "<String>)");
        }

        private g6<?> e(int i, Type type, Annotation[] annotationArr) {
            Class<?> rawType = TypeUtils.getRawType(type);
            if (!Map.class.isAssignableFrom(rawType)) {
                throw a(i, "@RecordMap parameter type must be Map.");
            }
            Type supertype = TypeUtils.getSupertype(type, rawType, Map.class);
            if (!(supertype instanceof ParameterizedType)) {
                throw a(i, "Map must include generic types (e.g., Map<String, String>)");
            }
            ParameterizedType parameterizedType = (ParameterizedType) supertype;
            Type parameterUpperBound = TypeUtils.getParameterUpperBound(0, parameterizedType);
            if (String.class == parameterUpperBound) {
                return new g6.m(this.f5344a.stringConverter(TypeUtils.getParameterUpperBound(1, parameterizedType), annotationArr));
            }
            throw a(i, "@RecordMap keys must be of type String: " + parameterUpperBound);
        }

        private void d(Annotation annotation) {
            String str = "GET";
            if (!a(annotation, "GET")) {
                str = ProfileRequestConstants.DELETE_TYPE;
                if (!a(annotation, ProfileRequestConstants.DELETE_TYPE)) {
                    String str2 = ProfileRequestConstants.PUT_TYPE;
                    if (!a(annotation, ProfileRequestConstants.PUT_TYPE)) {
                        str2 = "POST";
                        if (!a(annotation, "POST")) {
                            if (a(annotation, "HEAD")) {
                                a("HEAD", (String) b(annotation), false);
                                if (!Void.class.equals(this.f)) {
                                    throw a("HEAD method must use Void as response type.", new Object[0]);
                                }
                                return;
                            } else {
                                if (a(annotation, "Headers")) {
                                    String[] strArr = (String[]) b(annotation);
                                    if (strArr.length == 0) {
                                        throw a("@Headers annotation is empty.", new Object[0]);
                                    }
                                    this.p = a(strArr);
                                    return;
                                }
                                if (a(annotation, "FormUrlEncoded")) {
                                    this.n = true;
                                    return;
                                } else {
                                    if (a(annotation, "OnlyConnect")) {
                                        this.w = true;
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                    }
                    a(str2, (String) b(annotation), true);
                    return;
                }
            }
            a(str, (String) b(annotation), false);
        }

        private g6<?> d(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (this.j) {
                throw a(i, "A @Path parameter must not come after a @Query.");
            }
            if (this.k) {
                throw a(i, "@Path parameters may not be used with @Url.");
            }
            if (this.o == null) {
                throw a(i, "@Path can only be used with relative url on @" + this.l);
            }
            this.i = true;
            String str = (String) b(annotation);
            b(i, str);
            return new g6.j(str, this.f5344a.stringConverter(type, annotationArr));
        }

        private g6<?> d(int i, Type type, Annotation[] annotationArr) {
            g6<?> g6Var = null;
            for (Annotation annotation : annotationArr) {
                g6<?> c = c(i, type, annotationArr, annotation);
                if (c != null) {
                    if (g6Var != null) {
                        throw a(i, "Multiple RestClient annotations found, only one allowed.");
                    }
                    g6Var = c;
                }
            }
            if (g6Var != null) {
                return g6Var;
            }
            throw a(i, "No RestClient annotation found.");
        }

        private void c(Annotation annotation) {
            if (a(annotation, "Headers")) {
                String[] strArr = (String[]) b(annotation);
                if (strArr.length == 0) {
                    throw a("@Headers annotation is empty.", new Object[0]);
                }
                this.p = a(strArr);
            }
        }

        private g6<?> c(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (a(annotation, SingleDailyMomentContent.URL_TYPE)) {
                return c(i, type);
            }
            if (a(annotation, "Path")) {
                return d(i, type, annotationArr, annotation);
            }
            if (a(annotation, "Query")) {
                return e(i, type, annotationArr, annotation);
            }
            if (a(annotation, "QueryMap")) {
                return f(i, type, annotationArr, annotation);
            }
            if (a(annotation, "Header")) {
                return b(i, type, annotationArr, annotation);
            }
            if (a(annotation, "HeaderMap")) {
                return c(i, type, annotationArr);
            }
            if (a(annotation, "Field")) {
                return a(i, type, annotationArr, annotation);
            }
            if (a(annotation, "FieldMap")) {
                return b(i, type, annotationArr);
            }
            if (a(annotation, "Body")) {
                return a(i, type, annotationArr);
            }
            if (a(annotation, "ClientConfig")) {
                return a(i, type);
            }
            if (annotation instanceof NetworkParameters) {
                return b(i, type);
            }
            if (a(annotation, "RecordMap")) {
                return e(i, type, annotationArr);
            }
            return null;
        }

        private g6<?> c(int i, Type type, Annotation[] annotationArr) {
            Class<?> rawType = TypeUtils.getRawType(type);
            if (!Map.class.isAssignableFrom(rawType)) {
                throw a(i, "@HeaderMap parameter type must be Map.");
            }
            Type supertype = TypeUtils.getSupertype(type, rawType, Map.class);
            if (!(supertype instanceof ParameterizedType)) {
                throw a(i, "Map must include generic types (e.g., Map<String, String>)");
            }
            ParameterizedType parameterizedType = (ParameterizedType) supertype;
            Type parameterUpperBound = TypeUtils.getParameterUpperBound(0, parameterizedType);
            if (String.class == parameterUpperBound) {
                return new g6.h(this.f5344a.stringConverter(TypeUtils.getParameterUpperBound(1, parameterizedType), annotationArr));
            }
            throw a(i, "@HeaderMap keys must be of type String: " + parameterUpperBound);
        }

        private g6<?> c(int i, Type type) {
            if (this.k) {
                throw a(i, "Multiple @Url method annotations found.");
            }
            if (this.i) {
                throw a(i, "@Path parameters may not be used with @Url.");
            }
            if (this.j) {
                throw a(i, "A @Url parameter must not come after a @Query");
            }
            if (this.o != null) {
                throw a(i, "@Url cannot be used with " + this.l);
            }
            this.k = true;
            if (type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                return new g6.n();
            }
            throw a(i, "@Url must be String, java.net.URI, or android.net.Uri type.");
        }

        private void b(int i, String str) {
            if (!k6.n.matcher(str).matches()) {
                throw a(i, StringUtils.format("@Path parameter name must match %s. Found: %s", k6.m.pattern(), str));
            }
            if (!this.r.contains(str)) {
                throw a(i, StringUtils.format("URL \"%s\" does not contain \"{%s}\".", this.o, str));
            }
        }

        private Object b(Annotation annotation) {
            String str;
            try {
                return annotation.annotationType().getMethod("value", new Class[0]).invoke(annotation, new Object[0]);
            } catch (IllegalAccessException unused) {
                str = "IllegalAccessException";
                Logger.w(k6.o, str);
                throw new IllegalStateException("get annotation value error:" + annotation.getClass().getSimpleName());
            } catch (NoSuchMethodException unused2) {
                str = "NoSuchMethodException";
                Logger.w(k6.o, str);
                throw new IllegalStateException("get annotation value error:" + annotation.getClass().getSimpleName());
            } catch (InvocationTargetException unused3) {
                str = "InvocationTargetException";
                Logger.w(k6.o, str);
                throw new IllegalStateException("get annotation value error:" + annotation.getClass().getSimpleName());
            }
        }

        private Converter<ResponseBody, R> b() {
            try {
                return this.f5344a.responseBodyConverter(this.f, this.c);
            } catch (RuntimeException e) {
                throw a(e, "Unable to create converter for " + this.f, new Object[0]);
            }
        }

        private g6<?> b(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            String str = (String) b(annotation);
            Class<?> rawType = TypeUtils.getRawType(type);
            if (!Iterable.class.isAssignableFrom(rawType)) {
                if (!rawType.isArray()) {
                    return new g6.g(str, this.f5344a.stringConverter(type, annotationArr));
                }
                return new g6.g(str, this.f5344a.stringConverter(TypeUtils.boxIfPrimitive(rawType.getComponentType()), annotationArr)).a();
            }
            if (type instanceof ParameterizedType) {
                return new g6.g(str, this.f5344a.stringConverter(TypeUtils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr)).b();
            }
            throw a(i, rawType.getSimpleName() + " must include generic type (e.g., " + rawType.getSimpleName() + "<String>)");
        }

        private g6<?> b(int i, Type type, Annotation[] annotationArr) {
            if (!this.n) {
                throw a(i, "@FieldMap parameters can only be used with form encoding.");
            }
            Class<?> rawType = TypeUtils.getRawType(type);
            if (!Map.class.isAssignableFrom(rawType)) {
                throw a(i, "@FieldMap parameter type must be Map.");
            }
            Type supertype = TypeUtils.getSupertype(type, rawType, Map.class);
            if (!(supertype instanceof ParameterizedType)) {
                throw a(i, "Map must include generic types (e.g., Map<String, String>)");
            }
            ParameterizedType parameterizedType = (ParameterizedType) supertype;
            Type parameterUpperBound = TypeUtils.getParameterUpperBound(0, parameterizedType);
            if (String.class != parameterUpperBound) {
                throw a(i, "@FieldMap keys must be of type String: " + parameterUpperBound);
            }
            Converter<T, String> stringConverter = this.f5344a.stringConverter(TypeUtils.getParameterUpperBound(1, parameterizedType), annotationArr);
            this.g = true;
            return new g6.e(stringConverter);
        }

        private g6<?> b(int i, Type type) {
            if (type == String.class) {
                return new g6.i();
            }
            throw a(i, "@NetworkParameters must be of type String.");
        }

        private boolean a(Annotation annotation, String str) {
            return annotation.annotationType().getSimpleName().equals(str);
        }

        private boolean a(Annotation annotation) {
            String str;
            try {
                return ((Boolean) annotation.annotationType().getMethod("encoded", new Class[0]).invoke(annotation, new Object[0])).booleanValue();
            } catch (IllegalAccessException unused) {
                str = "IllegalAccessException";
                Logger.w(k6.o, str);
                return false;
            } catch (NoSuchMethodException unused2) {
                str = "NoSuchMethodException";
                Logger.w(k6.o, str);
                return false;
            } catch (InvocationTargetException unused3) {
                str = "InvocationTargetException";
                Logger.w(k6.o, str);
                return false;
            }
        }

        private void a(String str, String str2, boolean z) {
            String str3 = this.l;
            if (str3 != null) {
                throw a("Only one HTTP method is allowed. Found: %s and %s.", str3, str);
            }
            this.l = str;
            this.m = z;
            if (str2.isEmpty()) {
                return;
            }
            int indexOf = str2.indexOf(63);
            if (indexOf != -1 && indexOf < str2.length() - 1) {
                String substring = str2.substring(indexOf + 1);
                if (k6.m.matcher(substring).find()) {
                    throw a("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", substring);
                }
            }
            this.o = str2;
            this.r = k6.b(str2);
        }

        private void a() {
            if (this.o == null && !this.k) {
                throw a("Missing either @%s URL or @Url parameter.", this.l);
            }
            boolean z = this.n;
            if (!z && !this.m && this.h) {
                throw a("Non-body HTTP method cannot contain @Body.", new Object[0]);
            }
            if (z && !this.g) {
                throw a("Form-encoded method must contain at least one @Field.", new Object[0]);
            }
        }

        private RuntimeException a(Throwable th, String str, Object... objArr) {
            return new IllegalArgumentException(StringUtils.format(str, objArr) + "    for method: " + this.b.getDeclaringClass().getSimpleName() + "." + this.b.getName(), th);
        }

        private RuntimeException a(Throwable th, int i, String str) {
            return a(th, str + " (parameter #" + (i + 1) + Constants.RIGHT_BRACKET_ONLY, new Object[0]);
        }

        private RuntimeException a(String str, Object... objArr) {
            return a((Throwable) null, str, objArr);
        }

        private RuntimeException a(int i, String str) {
            return a(str + " (parameter #" + (i + 1) + Constants.RIGHT_BRACKET_ONLY, new Object[0]);
        }

        private SubmitAdapter<R, T> a(Method method) {
            Type genericReturnType = method.getGenericReturnType();
            if (TypeUtils.hasUnresolvableType(genericReturnType)) {
                throw a("Method return type must not include a type variable or wildcard: " + genericReturnType, new Object[0]);
            }
            if (genericReturnType == Void.TYPE) {
                throw a("Service methods cannot return void.", new Object[0]);
            }
            try {
                return (SubmitAdapter<R, T>) this.f5344a.submitAdapter(genericReturnType, this.c);
            } catch (RuntimeException e) {
                throw a(e, "Unable to create submit adapter for " + genericReturnType, new Object[0]);
            }
        }

        private g6<?> a(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (!this.n) {
                throw a(i, "@Field parameters can only be used with form encoding.");
            }
            String str = (String) b(annotation);
            this.g = true;
            Class<?> rawType = TypeUtils.getRawType(type);
            if (!Iterable.class.isAssignableFrom(rawType)) {
                if (!rawType.isArray()) {
                    return new g6.f(str, this.f5344a.stringConverter(type, annotationArr));
                }
                return new g6.f(str, this.f5344a.stringConverter(TypeUtils.boxIfPrimitive(rawType.getComponentType()), annotationArr)).a();
            }
            if (type instanceof ParameterizedType) {
                return new g6.f(str, this.f5344a.stringConverter(TypeUtils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr)).b();
            }
            throw a(i, rawType.getSimpleName() + " must include generic type (e.g., " + rawType.getSimpleName() + "<String>)");
        }

        private g6<?> a(int i, Type type, Annotation[] annotationArr) {
            if (this.n) {
                throw a(i, "@Body parameters cannot be used with form or multi-part encoding.");
            }
            if (this.h) {
                throw a(i, "Multiple @Body method annotations found.");
            }
            try {
                Converter<T, RequestBody> requestBodyConverter = this.f5344a.requestBodyConverter(type, annotationArr, this.c);
                this.h = true;
                return new g6.c(requestBodyConverter);
            } catch (RuntimeException e) {
                throw a(e, i, "Unable to create @Body converter for " + type);
            }
        }

        private g6<?> a(int i, Type type) {
            if (type == ClientConfiguration.class) {
                return new g6.d();
            }
            throw a(i, "@ClientConfig must be com.huawei.hms.framework.network.rest.hwhttp.ClientConfiguration .");
        }

        private Headers a(String[] strArr) {
            Headers headers = this.p;
            Headers.Builder builder = headers == null ? new Headers.Builder() : headers.newBuilder();
            for (String str : strArr) {
                int indexOf = str.indexOf(58);
                if (indexOf == 0 || indexOf == -1 || indexOf == str.length() - 1) {
                    throw a("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String substring = str.substring(0, indexOf);
                String trim = str.substring(indexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(substring)) {
                    this.q = trim;
                } else {
                    builder.add(substring, trim);
                }
            }
            return builder.build();
        }

        public b(h6 h6Var, Method method, Class cls) {
            this.f5344a = h6Var;
            this.b = method;
            this.c = method.getAnnotations();
            this.e = method.getGenericParameterTypes();
            this.d = method.getParameterAnnotations();
            this.v = cls.getAnnotations();
        }
    }

    public T a(Submit<R> submit) {
        return this.f5343a.adapt2(submit);
    }

    public R a(ResponseBody responseBody) throws IOException {
        return this.b.convert(responseBody);
    }

    public Request a(Submit.Factory factory, @Nullable Object... objArr) throws IOException {
        j6 j6Var = new j6(factory, this.c, this.j, this.d, this.e, this.f, this.g, this.h, this.k);
        g6<?>[] g6VarArr = this.i;
        int length = objArr != null ? objArr.length : 0;
        if (g6VarArr == null) {
            throw new IllegalArgumentException("parameterHandlers is null");
        }
        if (length == g6VarArr.length) {
            for (int i = 0; i < length; i++) {
                g6VarArr[i].a(j6Var, objArr[i]);
            }
            return j6Var.a();
        }
        throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + g6VarArr.length + Constants.RIGHT_BRACKET_ONLY);
    }

    public static Set<String> b(String str) {
        Matcher matcher = m.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    public k6(b<R, T> bVar) {
        this.f5343a = bVar.u;
        this.j = bVar.f5344a.getBaseUrl();
        this.b = bVar.t;
        this.c = bVar.l;
        this.d = bVar.o;
        this.e = bVar.p;
        this.f = bVar.q;
        this.g = bVar.m;
        this.h = bVar.n;
        this.i = bVar.s;
        this.k = bVar.w;
    }
}
