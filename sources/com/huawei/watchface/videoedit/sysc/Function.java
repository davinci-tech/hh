package com.huawei.watchface.videoedit.sysc;

import java.util.Objects;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface Function<T, R> {
    static /* synthetic */ Object lambda$identity$2(Object obj) {
        return obj;
    }

    R apply(T t);

    default <V> Function<V, R> compose(final Function<? super V, ? extends T> function) {
        Objects.requireNonNull(function);
        return new Function() { // from class: com.huawei.watchface.videoedit.sysc.Function$$ExternalSyntheticLambda2
            @Override // com.huawei.watchface.videoedit.sysc.Function
            public final Object apply(Object obj) {
                Object apply;
                apply = Function.this.apply(function.apply(obj));
                return apply;
            }
        };
    }

    default <V> Function<T, V> andThen(final Function<? super R, ? extends V> function) {
        Objects.requireNonNull(function);
        return new Function() { // from class: com.huawei.watchface.videoedit.sysc.Function$$ExternalSyntheticLambda1
            @Override // com.huawei.watchface.videoedit.sysc.Function
            public final Object apply(Object obj) {
                Object apply;
                apply = function.apply(Function.this.apply(obj));
                return apply;
            }
        };
    }

    static <T> Function<T, T> identity() {
        return new Function() { // from class: com.huawei.watchface.videoedit.sysc.Function$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.videoedit.sysc.Function
            public final Object apply(Object obj) {
                return Function.lambda$identity$2(obj);
            }
        };
    }
}
