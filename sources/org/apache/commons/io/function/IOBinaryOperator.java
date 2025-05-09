package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BinaryOperator;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface IOBinaryOperator<T> extends IOBiFunction<T, T, T> {
    static <T> IOBinaryOperator<T> maxBy(final IOComparator<? super T> iOComparator) {
        Objects.requireNonNull(iOComparator);
        return new IOBinaryOperator() { // from class: org.apache.commons.io.function.IOBinaryOperator$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                return IOBinaryOperator.lambda$maxBy$0(IOComparator.this, obj, obj2);
            }
        };
    }

    static /* synthetic */ Object lambda$maxBy$0(IOComparator iOComparator, Object obj, Object obj2) throws IOException {
        return iOComparator.compare(obj, obj2) >= 0 ? obj : obj2;
    }

    static <T> IOBinaryOperator<T> minBy(final IOComparator<? super T> iOComparator) {
        Objects.requireNonNull(iOComparator);
        return new IOBinaryOperator() { // from class: org.apache.commons.io.function.IOBinaryOperator$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                return IOBinaryOperator.lambda$minBy$1(IOComparator.this, obj, obj2);
            }
        };
    }

    static /* synthetic */ Object lambda$minBy$1(IOComparator iOComparator, Object obj, Object obj2) throws IOException {
        return iOComparator.compare(obj, obj2) <= 0 ? obj : obj2;
    }

    default BinaryOperator<T> asBinaryOperator() {
        return new BinaryOperator() { // from class: org.apache.commons.io.function.IOBinaryOperator$$ExternalSyntheticLambda1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                Object apply;
                apply = Uncheck.apply(IOBinaryOperator.this, obj, obj2);
                return apply;
            }
        };
    }
}
