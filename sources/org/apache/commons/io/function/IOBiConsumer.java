package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiConsumer;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface IOBiConsumer<T, U> {
    void accept(T t, U u) throws IOException;

    static <T, U> IOBiConsumer<T, U> noop() {
        return Constants.IO_BI_CONSUMER;
    }

    default IOBiConsumer<T, U> andThen(final IOBiConsumer<? super T, ? super U> iOBiConsumer) {
        Objects.requireNonNull(iOBiConsumer);
        return new IOBiConsumer() { // from class: org.apache.commons.io.function.IOBiConsumer$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOBiConsumer
            public final void accept(Object obj, Object obj2) {
                IOBiConsumer.lambda$andThen$0(IOBiConsumer.this, iOBiConsumer, obj, obj2);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$0(IOBiConsumer iOBiConsumer, IOBiConsumer iOBiConsumer2, Object obj, Object obj2) throws IOException {
        iOBiConsumer.accept(obj, obj2);
        iOBiConsumer2.accept(obj, obj2);
    }

    default BiConsumer<T, U> asBiConsumer() {
        return new BiConsumer() { // from class: org.apache.commons.io.function.IOBiConsumer$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Uncheck.accept(IOBiConsumer.this, obj, obj2);
            }
        };
    }
}
