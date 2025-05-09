package defpackage;

import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.MutablePropertyReference1;
import kotlin.jvm.internal.MutablePropertyReference2;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference2;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;

/* loaded from: classes7.dex */
public class uid {
    public KMutableProperty1 a(MutablePropertyReference1 mutablePropertyReference1) {
        return mutablePropertyReference1;
    }

    public KMutableProperty2 a(MutablePropertyReference2 mutablePropertyReference2) {
        return mutablePropertyReference2;
    }

    public KProperty1 a(PropertyReference1 propertyReference1) {
        return propertyReference1;
    }

    public KFunction d(uhz uhzVar) {
        return uhzVar;
    }

    public KMutableProperty0 d(MutablePropertyReference0 mutablePropertyReference0) {
        return mutablePropertyReference0;
    }

    public KProperty0 e(PropertyReference0 propertyReference0) {
        return propertyReference0;
    }

    public KProperty2 e(PropertyReference2 propertyReference2) {
        return propertyReference2;
    }

    public KDeclarationContainer d(Class cls, String str) {
        return new uic(cls, str);
    }

    public KClass b(Class cls) {
        return new uht(cls);
    }

    public String d(Lambda lambda) {
        return c(lambda);
    }

    public String c(FunctionBase functionBase) {
        String obj = functionBase.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring(21) : obj;
    }
}
