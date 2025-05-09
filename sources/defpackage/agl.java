package defpackage;

/* loaded from: classes3.dex */
public class agl {
    public static <T> T a(Class<T> cls) {
        T t = (T) ago.d(cls);
        if (t == null || !cls.isAssignableFrom(t.getClass())) {
            return null;
        }
        return t;
    }
}
