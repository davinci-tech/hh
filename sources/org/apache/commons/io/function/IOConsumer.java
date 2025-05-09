package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.commons.io.IOExceptionList;

@FunctionalInterface
/* loaded from: classes7.dex */
public interface IOConsumer<T> {
    public static final IOConsumer<?> NOOP_IO_CONSUMER = new IOConsumer() { // from class: org.apache.commons.io.function.IOConsumer$$ExternalSyntheticLambda3
        @Override // org.apache.commons.io.function.IOConsumer
        public final void accept(Object obj) {
            IOConsumer.lambda$static$0(obj);
        }
    };

    static /* synthetic */ void lambda$static$0(Object obj) throws IOException {
    }

    void accept(T t) throws IOException;

    static <T> void forAll(IOConsumer<T> iOConsumer, Iterable<T> iterable) throws IOExceptionList {
        IOStreams.forAll(IOStreams.of(iterable), iOConsumer);
    }

    static <T> void forAll(IOConsumer<T> iOConsumer, Stream<T> stream) throws IOExceptionList {
        IOStreams.forAll(stream, iOConsumer, new IOConsumer$$ExternalSyntheticLambda2());
    }

    @SafeVarargs
    static <T> void forAll(IOConsumer<T> iOConsumer, T... tArr) throws IOExceptionList {
        IOStreams.forAll(IOStreams.of(tArr), iOConsumer);
    }

    static <T> void forEach(Iterable<T> iterable, IOConsumer<T> iOConsumer) throws IOException {
        IOStreams.forEach(IOStreams.of(iterable), iOConsumer);
    }

    static <T> void forEach(Stream<T> stream, IOConsumer<T> iOConsumer) throws IOException {
        IOStreams.forEach(stream, iOConsumer);
    }

    static <T> void forEach(T[] tArr, IOConsumer<T> iOConsumer) throws IOException {
        IOStreams.forEach(IOStreams.of(tArr), iOConsumer);
    }

    static <T> IOConsumer<T> noop() {
        return (IOConsumer<T>) NOOP_IO_CONSUMER;
    }

    default IOConsumer<T> andThen(final IOConsumer<? super T> iOConsumer) {
        Objects.requireNonNull(iOConsumer, "after");
        return new IOConsumer() { // from class: org.apache.commons.io.function.IOConsumer$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                IOConsumer.lambda$andThen$1(IOConsumer.this, iOConsumer, obj);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$1(IOConsumer iOConsumer, IOConsumer iOConsumer2, Object obj) throws IOException {
        iOConsumer.accept(obj);
        iOConsumer2.accept(obj);
    }

    default Consumer<T> asConsumer() {
        return new Consumer() { // from class: org.apache.commons.io.function.IOConsumer$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Uncheck.accept(IOConsumer.this, obj);
            }
        };
    }
}
