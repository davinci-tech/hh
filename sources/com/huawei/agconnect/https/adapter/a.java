package com.huawei.agconnect.https.adapter;

import com.huawei.agconnect.https.Adapter;
import com.huawei.agconnect.https.annotation.Body;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;

/* loaded from: classes8.dex */
public class a<Request> implements Adapter<Request, RequestBody> {

    /* renamed from: a, reason: collision with root package name */
    private static final MediaType f1795a = MediaType.parse("application/json; charset=UTF-8");

    @Override // com.huawei.agconnect.https.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RequestBody adapter(Request request) throws IOException {
        try {
            Class<?> cls = request.getClass();
            String str = null;
            boolean z = false;
            do {
                Field[] declaredFields = cls.getDeclaredFields();
                if (declaredFields.length > 0) {
                    int length = declaredFields.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        Field field = declaredFields[i];
                        if (field.isAnnotationPresent(Body.class)) {
                            str = a(field, request);
                            z = true;
                            break;
                        }
                        i++;
                    }
                    if (z) {
                        break;
                    }
                }
                cls = cls.getSuperclass();
            } while (cls != Object.class);
            MediaType mediaType = f1795a;
            if (str == null) {
                str = "{}";
            }
            return RequestBody.create(mediaType, str);
        } catch (IllegalAccessException e) {
            throw new IOException("catch IllegalAccessException:" + e.getMessage());
        } catch (JSONException e2) {
            throw new IOException("catch JSONException:" + e2.getMessage());
        }
    }

    private String a(final Field field, Request request) throws IllegalAccessException, JSONException {
        if (!field.isAccessible()) {
            AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.agconnect.https.adapter.a.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    field.setAccessible(true);
                    return null;
                }
            });
        }
        Object obj = field.get(request);
        if (obj == null) {
            return null;
        }
        return new JSONEncodeUtil(false).toJson(obj);
    }
}
