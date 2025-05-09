package org.apache.commons.io.function;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes10.dex */
final class UncheckedIOIterator<E> implements Iterator<E> {
    private final IOIterator<E> delegate;

    UncheckedIOIterator(IOIterator<E> iOIterator) {
        this.delegate = (IOIterator) Objects.requireNonNull(iOIterator, "delegate");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        final IOIterator<E> iOIterator = this.delegate;
        Objects.requireNonNull(iOIterator);
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.function.UncheckedIOIterator$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return Boolean.valueOf(IOIterator.this.hasNext());
            }
        })).booleanValue();
    }

    @Override // java.util.Iterator
    public E next() {
        final IOIterator<E> iOIterator = this.delegate;
        Objects.requireNonNull(iOIterator);
        return (E) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.function.UncheckedIOIterator$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return IOIterator.this.next();
            }
        });
    }

    @Override // java.util.Iterator
    public void remove() {
        final IOIterator<E> iOIterator = this.delegate;
        Objects.requireNonNull(iOIterator);
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.function.UncheckedIOIterator$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                IOIterator.this.remove();
            }
        });
    }
}
