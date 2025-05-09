package org.apache.commons.io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

/* loaded from: classes10.dex */
final class UncheckedIOExceptions {
    public static UncheckedIOException create(Object obj) {
        String objects = Objects.toString(obj);
        return new UncheckedIOException(objects, new IOException(objects));
    }

    public static UncheckedIOException wrap(IOException iOException, Object obj) {
        return new UncheckedIOException(Objects.toString(obj), iOException);
    }

    private UncheckedIOExceptions() {
    }
}
