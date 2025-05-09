package com.huawei.hwcloudjs.core.a;

import android.text.TextUtils;
import com.huawei.hwcloudjs.annotation.JSMethod;
import com.huawei.hwcloudjs.core.JSRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static b f6196a = new b();
    private final Map<String, a> b = new HashMap();

    public void b(String str) {
        if (this.b.containsKey(str)) {
            this.b.remove(str);
        }
    }

    public void a(Class<? extends JSRequest> cls) {
        for (Method method : cls.getMethods()) {
            if (method.isAnnotationPresent(JSMethod.class)) {
                String name = ((JSMethod) method.getAnnotation(JSMethod.class)).name();
                if (TextUtils.isEmpty(name)) {
                    name = method.getName();
                }
                this.b.put(name, new a(method));
            }
        }
    }

    public a a(String str) {
        return this.b.get(str);
    }

    public static b a() {
        return f6196a;
    }
}
