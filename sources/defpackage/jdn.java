package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class jdn {
    private static final Class[] d = {String.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class, Character.class, Void.class, Class.class, Object.class};

    private static Object e(Object obj) {
        try {
            return obj.getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            LogUtil.b("HealthCloneUtil", "createNewObject Exception=", ExceptionUtils.d(e));
            return null;
        }
    }

    private static boolean d(Class cls) {
        if (cls.isPrimitive()) {
            return true;
        }
        for (Class cls2 : d) {
            if (cls.equals(cls2)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T a(Object obj) {
        try {
            return (T) a(obj, -1);
        } catch (ClassCastException | IllegalAccessException | InstantiationException e) {
            LogUtil.b("HealthCloneUtil", "deep Exception=", ExceptionUtils.d(e));
            return null;
        }
    }

    private static Object a(Object obj, int i) throws InstantiationException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        if (i == 0) {
            return obj;
        }
        Class<?> cls = obj.getClass();
        if (d(cls)) {
            return obj;
        }
        int i2 = i - 1;
        if (obj instanceof Collection) {
            Collection collection = (Collection) cls.newInstance();
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                collection.add(a(it.next(), i2));
            }
            return collection;
        }
        if (obj instanceof Map) {
            return a(obj, cls, i2);
        }
        if (cls.isArray()) {
            return d(obj, i2);
        }
        return d(obj, cls, i2);
    }

    private static Map a(Object obj, Class cls, int i) throws InstantiationException, IllegalAccessException {
        Map map = (Map) cls.newInstance();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            map.put(entry.getKey(), a(entry.getValue(), i));
        }
        return map;
    }

    private static Object d(Object obj, Class cls, int i) throws InstantiationException, IllegalAccessException {
        Object e = e(obj);
        if (e == null) {
            return obj;
        }
        HashSet<Field> hashSet = new HashSet();
        while (cls != null && !cls.equals(Object.class)) {
            hashSet.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        for (final Field field : hashSet) {
            if (!Modifier.isFinal(field.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: jdn.2
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        field.setAccessible(true);
                        return null;
                    }
                });
                field.set(e, a(field.get(obj), i));
            }
        }
        return e;
    }

    private static Object d(Object obj, int i) throws InstantiationException, IllegalAccessException {
        Object[] objArr = (Object[]) obj;
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length, objArr.getClass());
        for (int i2 = 0; i2 < objArr.length; i2++) {
            copyOf[i2] = a(objArr[i2], i);
        }
        return copyOf;
    }
}
