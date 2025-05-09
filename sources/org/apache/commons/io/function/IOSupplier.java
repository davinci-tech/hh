package org.apache.commons.io.function;

import java.io.IOException;
import java.util.function.Supplier;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface IOSupplier<T> {
    T get() throws IOException;

    default Supplier<T> asSupplier() {
        return new Supplier() { // from class: org.apache.commons.io.function.IOSupplier$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                Object obj;
                obj = Uncheck.get(IOSupplier.this);
                return obj;
            }
        };
    }
}
