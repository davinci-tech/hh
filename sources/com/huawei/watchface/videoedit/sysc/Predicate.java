package com.huawei.watchface.videoedit.sysc;

import java.util.Objects;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() { // from class: com.huawei.watchface.videoedit.sysc.Predicate$$ExternalSyntheticLambda2
            @Override // com.huawei.watchface.videoedit.sysc.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$and$0(Predicate.this, predicate, obj);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$0(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) && predicate2.test(obj);
    }

    static /* synthetic */ boolean lambda$negate$1(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    default Predicate<T> negate() {
        return new Predicate() { // from class: com.huawei.watchface.videoedit.sysc.Predicate$$ExternalSyntheticLambda3
            @Override // com.huawei.watchface.videoedit.sysc.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$negate$1(Predicate.this, obj);
            }
        };
    }

    default Predicate<T> or(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() { // from class: com.huawei.watchface.videoedit.sysc.Predicate$$ExternalSyntheticLambda4
            @Override // com.huawei.watchface.videoedit.sysc.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$or$2(Predicate.this, predicate, obj);
            }
        };
    }

    static /* synthetic */ boolean lambda$or$2(Predicate predicate, Predicate predicate2, Object obj) {
        return predicate.test(obj) || predicate2.test(obj);
    }

    static <T> Predicate<T> isEqual(final Object obj) {
        if (obj == null) {
            return new Predicate() { // from class: com.huawei.watchface.videoedit.sysc.Predicate$$ExternalSyntheticLambda0
                @Override // com.huawei.watchface.videoedit.sysc.Predicate
                public final boolean test(Object obj2) {
                    boolean isNull;
                    isNull = Objects.isNull(obj2);
                    return isNull;
                }
            };
        }
        return new Predicate() { // from class: com.huawei.watchface.videoedit.sysc.Predicate$$ExternalSyntheticLambda1
            @Override // com.huawei.watchface.videoedit.sysc.Predicate
            public final boolean test(Object obj2) {
                boolean equals;
                equals = obj.equals(obj2);
                return equals;
            }
        };
    }
}
