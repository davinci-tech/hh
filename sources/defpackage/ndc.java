package defpackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes6.dex */
public class ndc {

    /* renamed from: a, reason: collision with root package name */
    private Constructor f15264a;
    private Field c;
    private Method d;
    Class<?> e;

    ndc() {
    }

    public static ndc d(Class<?> cls) {
        ndc ndcVar = new ndc();
        ndcVar.e = cls;
        return ndcVar;
    }

    protected Object d(Object obj) throws b {
        if (obj == null || this.e.isInstance(obj)) {
            return obj;
        }
        throw new b("Caller [" + obj + "] is not a instance of type [" + this.e + "]!");
    }

    protected void b(Object obj, Member member, String str) throws b {
        if (member == null) {
            throw new b(str + " was null!");
        }
        if (obj == null && !Modifier.isStatic(member.getModifiers())) {
            throw new b("Need a caller!");
        }
        d(obj);
    }

    public ndc a(String str) throws b {
        try {
            Field b2 = b(str);
            this.c = b2;
            b2.setAccessible(true);
            this.f15264a = null;
            this.d = null;
            return this;
        } catch (Throwable th) {
            throw new b("Oops!", th);
        }
    }

    protected Field b(String str) throws NoSuchFieldException {
        try {
            return this.e.getField(str);
        } catch (NoSuchFieldException e) {
            for (Class<?> cls = this.e; cls != null; cls = cls.getSuperclass()) {
                try {
                    return cls.getDeclaredField(str);
                } catch (NoSuchFieldException unused) {
                }
            }
            throw e;
        }
    }

    public ndc e(Object obj, Object obj2) throws b {
        b(obj, this.c, "Field");
        try {
            this.c.set(obj, obj2);
            return this;
        } catch (Throwable th) {
            throw new b("Oops!", th);
        }
    }

    public static class b extends Exception {
        public b(String str) {
            super(str);
        }

        public b(String str, Throwable th) {
            super(str, th);
        }
    }
}
