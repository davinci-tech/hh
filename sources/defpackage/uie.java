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
public class uie {
    private static final KClass[] c;
    private static final uid e;

    static {
        uid uidVar;
        try {
            uidVar = (uid) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            uidVar = null;
        }
        if (uidVar == null) {
            uidVar = new uid();
        }
        e = uidVar;
        c = new KClass[0];
    }

    public static KDeclarationContainer a(Class cls) {
        return e.d(cls, "");
    }

    public static KClass d(Class cls) {
        return e.b(cls);
    }

    public static String c(Lambda lambda) {
        return e.d(lambda);
    }

    public static String d(FunctionBase functionBase) {
        return e.c(functionBase);
    }

    public static KFunction a(uhz uhzVar) {
        return e.d(uhzVar);
    }

    public static KProperty0 c(PropertyReference0 propertyReference0) {
        return e.e(propertyReference0);
    }

    public static KMutableProperty0 e(MutablePropertyReference0 mutablePropertyReference0) {
        return e.d(mutablePropertyReference0);
    }

    public static KProperty1 c(PropertyReference1 propertyReference1) {
        return e.a(propertyReference1);
    }

    public static KMutableProperty1 d(MutablePropertyReference1 mutablePropertyReference1) {
        return e.a(mutablePropertyReference1);
    }

    public static KProperty2 b(PropertyReference2 propertyReference2) {
        return e.e(propertyReference2);
    }

    public static KMutableProperty2 a(MutablePropertyReference2 mutablePropertyReference2) {
        return e.a(mutablePropertyReference2);
    }
}
