package defpackage;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a+\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0002\u0010\u0005\u001a-\u0010\u0006\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"cast", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/reflect/KClass;", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "safeCast", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: uiw, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class cast {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T d(KClass<T> kClass, Object obj) {
        uhy.e((Object) kClass, "");
        if (!kClass.isInstance(obj)) {
            throw new ClassCastException("Value cannot be cast to " + kClass.getQualifiedName());
        }
        uhy.b(obj, "");
        return obj;
    }
}
