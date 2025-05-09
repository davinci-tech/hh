package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface IOTriFunction<T, U, V, R> {
    R apply(T t, U u, V v) throws IOException;

    default <W> IOTriFunction<T, U, V, W> andThen(final IOFunction<? super R, ? extends W> iOFunction) {
        Objects.requireNonNull(iOFunction);
        return new IOTriFunction() { // from class: org.apache.commons.io.function.IOTriFunction$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Object apply;
                apply = iOFunction.apply(IOTriFunction.this.apply(obj, obj2, obj3));
                return apply;
            }
        };
    }
}
