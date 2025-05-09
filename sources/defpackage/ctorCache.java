package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.uel;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.coroutines.internal.CtorCache;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a2\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u0007\"\b\b\u0000\u0010\b*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0002\u001a*\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u00072\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0002\u001a1\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u00072\u0014\b\u0004\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u0082\b\u001a!\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00062\u0006\u0010\u0011\u001a\u0002H\bH\u0000¢\u0006\u0002\u0010\u0012\u001a\u001b\u0010\u0013\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\n2\b\b\u0002\u0010\u0014\u001a\u00020\u0003H\u0082\u0010\u001a\u0018\u0010\u0015\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0016\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000*(\b\u0002\u0010\u0017\"\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¨\u0006\u0018"}, d2 = {"ctorCache", "Lkotlinx/coroutines/internal/CtorCache;", "throwableFields", "", "createConstructor", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/Ctor;", ExifInterface.LONGITUDE_EAST, "clz", "Ljava/lang/Class;", "createSafeConstructor", "constructor", "Ljava/lang/reflect/Constructor;", "safeCtor", "block", "tryCopyException", TrackConstants$Events.EXCEPTION, "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "fieldsCount", "accumulator", "fieldsCountOrDefault", "defaultValue", "Ctor", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* renamed from: upa, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class ctorCache {
    private static final int d = e(Throwable.class, -1);
    private static final CtorCache e;

    static {
        uqa uqaVar;
        try {
            uqaVar = ANDROID_DETECTED.a() ? uqa.c : uow.d;
        } catch (Throwable unused) {
            uqaVar = uqa.c;
        }
        e = uqaVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E extends Throwable> E d(E e2) {
        Object b2;
        if (e2 instanceof CopyableThrowable) {
            try {
                uel.b bVar = uel.d;
                b2 = uel.b(((CopyableThrowable) e2).createCopy());
            } catch (Throwable th) {
                uel.b bVar2 = uel.d;
                b2 = uel.b(createFailure.b(th));
            }
            if (uel.a(b2)) {
                b2 = null;
            }
            return (E) b2;
        }
        return (E) e.get(e2.getClass()).invoke(e2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E extends Throwable> Function1<Throwable, Throwable> c(Class<E> cls) {
        d dVar = d.b;
        if (d != e(cls, 0)) {
            return dVar;
        }
        Iterator it = uez.d((Object[]) cls.getConstructors(), (Comparator) new a()).iterator();
        while (it.hasNext()) {
            Function1<Throwable, Throwable> b2 = b((Constructor) it.next());
            if (b2 != null) {
                return b2;
            }
        }
        return dVar;
    }

    private static final Function1<Throwable, Throwable> b(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length == 0) {
            return new j(constructor);
        }
        if (length != 1) {
            if (length == 2 && uhy.e(parameterTypes[0], String.class) && uhy.e(parameterTypes[1], Throwable.class)) {
                return new b(constructor);
            }
        } else {
            Class<?> cls = parameterTypes[0];
            if (!uhy.e(cls, Throwable.class)) {
                if (uhy.e(cls, String.class)) {
                    return new c(constructor);
                }
            } else {
                return new e(constructor);
            }
        }
        return null;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "e", TrackConstants$Opers.INVOKE, "kotlinx/coroutines/internal/ExceptionsConstructorKt$safeCtor$1"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upa$b */
    public static final class b extends Lambda implements Function1<Throwable, Throwable> {
        final /* synthetic */ Constructor c;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Throwable invoke(Throwable th) {
            Object b;
            Object newInstance;
            try {
                uel.b bVar = uel.d;
                newInstance = this.c.newInstance(th.getMessage(), th);
            } catch (Throwable th2) {
                uel.b bVar2 = uel.d;
                b = uel.b(createFailure.b(th2));
            }
            if (newInstance != null) {
                b = uel.b((Throwable) newInstance);
                if (uel.a(b)) {
                    b = null;
                }
                return (Throwable) b;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Constructor constructor) {
            super(1);
            this.c = constructor;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "e", TrackConstants$Opers.INVOKE, "kotlinx/coroutines/internal/ExceptionsConstructorKt$safeCtor$1"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upa$c */
    public static final class c extends Lambda implements Function1<Throwable, Throwable> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Constructor f17495a;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final Throwable invoke(Throwable th) {
            Object b;
            Object newInstance;
            try {
                uel.b bVar = uel.d;
                newInstance = this.f17495a.newInstance(th.getMessage());
            } catch (Throwable th2) {
                uel.b bVar2 = uel.d;
                b = uel.b(createFailure.b(th2));
            }
            if (newInstance != null) {
                Throwable th3 = (Throwable) newInstance;
                th3.initCause(th);
                b = uel.b(th3);
                if (uel.a(b)) {
                    b = null;
                }
                return (Throwable) b;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Constructor constructor) {
            super(1);
            this.f17495a = constructor;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "e", TrackConstants$Opers.INVOKE, "kotlinx/coroutines/internal/ExceptionsConstructorKt$safeCtor$1"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upa$e */
    public static final class e extends Lambda implements Function1<Throwable, Throwable> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Constructor f17496a;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final Throwable invoke(Throwable th) {
            Object b;
            Object newInstance;
            try {
                uel.b bVar = uel.d;
                newInstance = this.f17496a.newInstance(th);
            } catch (Throwable th2) {
                uel.b bVar2 = uel.d;
                b = uel.b(createFailure.b(th2));
            }
            if (newInstance != null) {
                b = uel.b((Throwable) newInstance);
                if (uel.a(b)) {
                    b = null;
                }
                return (Throwable) b;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Constructor constructor) {
            super(1);
            this.f17496a = constructor;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "e", TrackConstants$Opers.INVOKE, "kotlinx/coroutines/internal/ExceptionsConstructorKt$safeCtor$1"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upa$j */
    public static final class j extends Lambda implements Function1<Throwable, Throwable> {
        final /* synthetic */ Constructor c;

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public final Throwable invoke(Throwable th) {
            Object b;
            Object newInstance;
            try {
                uel.b bVar = uel.d;
                newInstance = this.c.newInstance(new Object[0]);
            } catch (Throwable th2) {
                uel.b bVar2 = uel.d;
                b = uel.b(createFailure.b(th2));
            }
            if (newInstance != null) {
                Throwable th3 = (Throwable) newInstance;
                th3.initCause(th);
                b = uel.b(th3);
                if (uel.a(b)) {
                    b = null;
                }
                return (Throwable) b;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(Constructor constructor) {
            super(1);
            this.c = constructor;
        }
    }

    private static final int e(Class<?> cls, int i) {
        Object b2;
        annotationClass.c(cls);
        try {
            uel.b bVar = uel.d;
            b2 = uel.b(Integer.valueOf(c(cls, 0, 1, null)));
        } catch (Throwable th) {
            uel.b bVar2 = uel.d;
            b2 = uel.b(createFailure.b(th));
        }
        if (uel.a(b2)) {
            b2 = Integer.valueOf(i);
        }
        return ((Number) b2).intValue();
    }

    static /* synthetic */ int c(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return d(cls, i);
    }

    private static final int d(Class<?> cls, int i) {
        do {
            Field[] declaredFields = cls.getDeclaredFields();
            int length = declaredFields.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                Field field = declaredFields[i2];
                i2++;
                if (!Modifier.isStatic(field.getModifiers())) {
                    i3++;
                }
            }
            i += i3;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, VideoPlayFlag.PLAY_IN_ALL, "kotlin.jvm.PlatformType", com.huawei.hms.scankit.b.H, "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upa$a */
    public static final class a<T> implements Comparator {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return ugj.b(Integer.valueOf(((Constructor) t2).getParameterTypes().length), Integer.valueOf(((Constructor) t).getParameterTypes().length));
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "", "it", TrackConstants$Opers.INVOKE}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: upa$d */
    static final class d extends Lambda implements Function1 {
        public static final d b = new d();

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final Void invoke(Throwable th) {
            return null;
        }

        d() {
            super(1);
        }
    }
}
