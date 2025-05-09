package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiFunction;

@FunctionalInterface
/* loaded from: classes7.dex */
public interface IOBiFunction<T, U, R> {
    R apply(T t, U u) throws IOException;

    default <V> IOBiFunction<T, U, V> andThen(final IOFunction<? super R, ? extends V> iOFunction) {
        Objects.requireNonNull(iOFunction);
        return new IOBiFunction() { // from class: org.apache.commons.io.function.IOBiFunction$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Object apply;
                apply = iOFunction.apply(IOBiFunction.this.apply(obj, obj2));
                return apply;
            }
        };
    }

    default BiFunction<T, U, R> asBiFunction() {
        return new BiFunction() { // from class: org.apache.commons.io.function.IOBiFunction$$ExternalSyntheticLambda0
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Object apply;
                apply = Uncheck.apply(IOBiFunction.this, obj, obj2);
                return apply;
            }
        };
    }
}
