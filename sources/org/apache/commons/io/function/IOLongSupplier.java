package org.apache.commons.io.function;

import java.io.IOException;
import java.util.function.LongSupplier;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface IOLongSupplier {
    long getAsLong() throws IOException;

    default LongSupplier asSupplier() {
        return new LongSupplier() { // from class: org.apache.commons.io.function.IOLongSupplier$$ExternalSyntheticLambda0
            @Override // java.util.function.LongSupplier
            public final long getAsLong() {
                long asLong;
                asLong = Uncheck.getAsLong(IOLongSupplier.this);
                return asLong;
            }
        };
    }
}
