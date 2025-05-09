package com.huawei.watchface.videoedit.sysc;

import java.util.Objects;

@FunctionalInterface
/* loaded from: classes7.dex */
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> andThen(final Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return new Consumer() { // from class: com.huawei.watchface.videoedit.sysc.Consumer$$ExternalSyntheticLambda0
            @Override // com.huawei.watchface.videoedit.sysc.Consumer
            public final void accept(Object obj) {
                Consumer.lambda$andThen$0(Consumer.this, consumer, obj);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$0(Consumer consumer, Consumer consumer2, Object obj) {
        consumer.accept(obj);
        consumer2.accept(obj);
    }
}
