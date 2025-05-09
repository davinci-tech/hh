package com.huawei.agconnect.https;

import com.huawei.agconnect.https.annotation.Header;
import com.huawei.agconnect.https.annotation.HeaderMap;
import com.huawei.agconnect.https.annotation.Query;
import com.huawei.agconnect.https.annotation.Url;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.InvalidParameterException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Request;

/* loaded from: classes2.dex */
abstract class a<Annotation, Builder> {

    /* renamed from: a, reason: collision with root package name */
    private static c f1791a = new c();
    private static b b = new b();
    private static C0034a c = new C0034a();

    abstract <RequestBean> Builder a(Field field, RequestBean requestbean, Builder builder);

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Field field) {
        if (field.isAccessible()) {
            return;
        }
        AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.agconnect.https.a.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                field.setAccessible(true);
                return null;
            }
        });
    }

    private static <RequestBean> HttpUrl.Builder b(RequestBean requestbean) {
        ArrayList arrayList = new ArrayList(1);
        Class<?> cls = requestbean.getClass();
        do {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(Url.class)) {
                        arrayList.add(field);
                    }
                }
            }
            cls = cls.getSuperclass();
        } while (cls != Object.class);
        if (arrayList.size() == 1) {
            return new d().a2((Field) arrayList.get(0), (Field) requestbean, (HttpUrl.Builder) null);
        }
        throw new IllegalArgumentException("ONLY ONE String Field can be annotated as Url");
    }

    /* renamed from: com.huawei.agconnect.https.a$a, reason: collision with other inner class name */
    static class C0034a extends a<Header, Request.Builder> {
        /* renamed from: a, reason: avoid collision after fix types in other method */
        <RequestBean> Request.Builder a2(Field field, RequestBean requestbean, Request.Builder builder) {
            if (builder == null) {
                throw new IllegalArgumentException("builder cannot be null");
            }
            try {
                a.b(field);
                Object obj = field.get(requestbean);
                String value = ((Header) field.getAnnotation(Header.class)).value();
                if (value.isEmpty()) {
                    value = field.getName();
                }
                if (!String.class.equals(field.getType())) {
                    throw new InvalidParameterException("Header should be the annotation of String type");
                }
                String str = (String) obj;
                if (!h.a(str)) {
                    builder.addHeader(value, str);
                }
                return builder;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.huawei.agconnect.https.a
        /* bridge */ /* synthetic */ Request.Builder a(Field field, Object obj, Request.Builder builder) {
            return a2(field, (Field) obj, builder);
        }

        C0034a() {
        }
    }

    static class b extends a<HeaderMap, Request.Builder> {
        /* renamed from: a, reason: avoid collision after fix types in other method */
        <RequestBean> Request.Builder a2(Field field, RequestBean requestbean, Request.Builder builder) {
            if (builder == null) {
                throw new IllegalArgumentException("builder cannot be null");
            }
            try {
                a.b(field);
                Object obj = field.get(requestbean);
                if (!Map.class.isAssignableFrom(field.getType())) {
                    throw new InvalidParameterException("HeaderMap should be the annotation of Map type");
                }
                Map map = (Map) obj;
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry entry : map.entrySet()) {
                        builder.addHeader((String) entry.getKey(), String.valueOf(entry.getValue()));
                    }
                }
                return builder;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.huawei.agconnect.https.a
        /* bridge */ /* synthetic */ Request.Builder a(Field field, Object obj, Request.Builder builder) {
            return a2(field, (Field) obj, builder);
        }

        b() {
        }
    }

    static final class c extends a<Query, HttpUrl.Builder> {
        /* renamed from: a, reason: avoid collision after fix types in other method */
        <RequestBean> HttpUrl.Builder a2(Field field, RequestBean requestbean, HttpUrl.Builder builder) {
            if (builder == null) {
                throw new IllegalArgumentException("builder cannot be null");
            }
            try {
                a.b(field);
                Object obj = field.get(requestbean);
                if (!String.class.equals(field.getType())) {
                    throw new InvalidParameterException("QueryHandler should be the annotation of String");
                }
                Query query = (Query) field.getAnnotation(Query.class);
                String value = query.value();
                boolean encoded = query.encoded();
                if (value == null || value.isEmpty()) {
                    value = field.getName();
                }
                if (obj == null) {
                    obj = "";
                }
                if (encoded) {
                    builder.addEncodedQueryParameter(value, String.valueOf(obj));
                } else {
                    builder.addQueryParameter(value, String.valueOf(obj));
                }
                return builder;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.huawei.agconnect.https.a
        /* bridge */ /* synthetic */ HttpUrl.Builder a(Field field, Object obj, HttpUrl.Builder builder) {
            return a2(field, (Field) obj, builder);
        }

        c() {
        }
    }

    static final class d extends a<Url, HttpUrl.Builder> {
        /* renamed from: a, reason: avoid collision after fix types in other method */
        <RequestBean> HttpUrl.Builder a2(Field field, RequestBean requestbean, HttpUrl.Builder builder) {
            try {
                if (!field.isAnnotationPresent(Url.class)) {
                    throw new IllegalArgumentException("field is not Url annotation");
                }
                a.b(field);
                Object obj = field.get(requestbean);
                if (!String.class.equals(field.getType())) {
                    throw new InvalidParameterException("Url should be the annotation of String");
                }
                String str = (String) obj;
                if (str == null || str.isEmpty()) {
                    throw new IllegalArgumentException("url cannot be null or empty");
                }
                return HttpUrl.parse(str).newBuilder();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.huawei.agconnect.https.a
        /* bridge */ /* synthetic */ HttpUrl.Builder a(Field field, Object obj, HttpUrl.Builder builder) {
            return a2(field, (Field) obj, builder);
        }

        d() {
        }
    }

    static <RequestBean> Request.Builder a(RequestBean requestbean) {
        HttpUrl.Builder b2 = b(requestbean);
        Request.Builder builder = new Request.Builder();
        Class<?> cls = requestbean.getClass();
        do {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    b(field);
                    for (Annotation annotation : field.getDeclaredAnnotations()) {
                        Class<? extends Annotation> annotationType = annotation.annotationType();
                        if (Query.class.equals(annotationType)) {
                            f1791a.a2(field, (Field) requestbean, b2);
                        } else if (HeaderMap.class.equals(annotationType)) {
                            b.a2(field, (Field) requestbean, builder);
                        } else if (Header.class.equals(annotationType)) {
                            c.a2(field, (Field) requestbean, builder);
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        } while (cls != Object.class);
        return builder.url(b2.build());
    }

    a() {
    }
}
