package defpackage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class tvu<T> {

    /* renamed from: a, reason: collision with root package name */
    private final String f17393a;
    private final List<tvw> d;

    private void d(Class<?> cls) throws ttr {
        if (cls == null) {
            return;
        }
        d(cls.getSuperclass());
        for (Field field : cls.getDeclaredFields()) {
            tvw tvwVar = new tvw(this.f17393a, field);
            if (tvwVar.d()) {
                this.d.add(tvwVar);
            }
        }
    }

    public tvu(Class<T> cls) throws ttr {
        this(null, cls);
    }

    public tvu(String str, Class<?> cls) throws ttr {
        String simpleName;
        this.d = new ArrayList();
        if (str != null) {
            simpleName = str + "." + cls.getSimpleName();
        } else {
            simpleName = cls.getSimpleName();
        }
        this.f17393a = simpleName;
        d(cls);
    }

    public boolean b() {
        return this.d.size() > 0;
    }

    public void a(T t) throws ttr {
        if (t == null) {
            return;
        }
        for (tvw tvwVar : this.d) {
            if (tvwVar.d()) {
                tvwVar.e(t);
            }
        }
    }
}
