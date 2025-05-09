package org.apache.commons.io.function;

import java.io.IOException;
import java.util.function.IntSupplier;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface IOIntSupplier {
    int getAsInt() throws IOException;

    default IntSupplier asIntSupplier() {
        return new IntSupplier() { // from class: org.apache.commons.io.function.IOIntSupplier$$ExternalSyntheticLambda0
            @Override // java.util.function.IntSupplier
            public final int getAsInt() {
                int asInt;
                asInt = Uncheck.getAsInt(IOIntSupplier.this);
                return asInt;
            }
        };
    }
}
