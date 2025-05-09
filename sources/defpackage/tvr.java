package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes7.dex */
public class tvr {
    public static Type a(Class<? extends KfsConstraintValidator<?, ?>> cls) {
        return ((ParameterizedType) cls.getGenericInterfaces()[0]).getActualTypeArguments()[1];
    }
}
