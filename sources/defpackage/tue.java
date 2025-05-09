package defpackage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class tue {
    private static final Map<Class<?>, tvu<?>> b = new ConcurrentHashMap();

    private static tvu<?> a(Class<?> cls) throws ttr {
        Map<Class<?>, tvu<?>> map = b;
        if (map.containsKey(cls)) {
            return map.get(cls);
        }
        tvu<?> tvuVar = new tvu<>(cls);
        map.put(cls, tvuVar);
        return tvuVar;
    }

    public static <T> void d(T t) throws ttr {
        if (t == null) {
            throw new ttr("validate bean is null");
        }
        tvu<?> a2 = a(t.getClass());
        if (a2.b()) {
            a2.a(t);
        }
    }
}
