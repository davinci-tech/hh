package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsConstraint;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsValid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class tvw {
    private final Field b;
    private final List<tvy> c = new ArrayList();
    private final String d;
    private tvu e;

    public tvw(String str, Field field) throws ttr {
        this.d = str;
        this.b = field;
        field.setAccessible(true);
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            if (((KfsConstraint) annotation.annotationType().getAnnotation(KfsConstraint.class)) != null) {
                if (annotation.annotationType() != KfsValid.class) {
                    this.c.add(new tvy(a(), annotation, field.getType()));
                } else {
                    this.e = new tvu(str, field.getType());
                }
            }
        }
    }

    private String a() {
        return this.d + "." + this.b.getName();
    }

    public boolean d() {
        return this.c.size() > 0 || this.e != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> void e(T t) throws ttr {
        try {
            Object obj = this.b.get(t);
            Iterator<tvy> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().b(obj);
            }
            tvu tvuVar = this.e;
            if (tvuVar != 0) {
                tvuVar.a(obj);
            }
        } catch (IllegalAccessException e) {
            throw new ttr("field validate failed:" + e.getMessage());
        }
    }
}
