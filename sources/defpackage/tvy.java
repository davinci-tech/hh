package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.lang.annotation.Annotation;

/* loaded from: classes7.dex */
public class tvy {

    /* renamed from: a, reason: collision with root package name */
    private final Annotation f17395a;
    private final KfsConstraintValidator c;
    private final String d;

    public tvy(String str, Annotation annotation, Class<?> cls) throws ttr {
        try {
            this.d = str;
            this.f17395a = annotation;
            this.c = (KfsConstraintValidator) tvs.d(annotation.annotationType(), cls).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new ttr("create constraint meta data for field:" + str + " failed, " + e.getMessage());
        }
    }

    public <T> void b(T t) throws ttr {
        KfsConstraintValidator kfsConstraintValidator = this.c;
        if (kfsConstraintValidator == null) {
            return;
        }
        kfsConstraintValidator.initialize(this.d, this.f17395a);
        if (!this.c.isValid(t)) {
            throw new ttr(this.c.getMessage());
        }
    }
}
