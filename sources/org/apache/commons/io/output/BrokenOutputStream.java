package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

/* loaded from: classes10.dex */
public class BrokenOutputStream extends OutputStream {
    public static final BrokenOutputStream INSTANCE = new BrokenOutputStream();
    private final Supplier<Throwable> exceptionSupplier;

    static /* synthetic */ Throwable lambda$new$1(IOException iOException) {
        return iOException;
    }

    static /* synthetic */ Throwable lambda$new$2(Throwable th) {
        return th;
    }

    public BrokenOutputStream() {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.output.BrokenOutputStream$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenOutputStream.lambda$new$0();
            }
        });
    }

    static /* synthetic */ Throwable lambda$new$0() {
        return new IOException("Broken output stream");
    }

    @Deprecated
    public BrokenOutputStream(final IOException iOException) {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.output.BrokenOutputStream$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenOutputStream.lambda$new$1(iOException);
            }
        });
    }

    public BrokenOutputStream(Supplier<Throwable> supplier) {
        this.exceptionSupplier = supplier;
    }

    public BrokenOutputStream(final Throwable th) {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.output.BrokenOutputStream$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenOutputStream.lambda$new$2(th);
            }
        });
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw rethrow();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        throw rethrow();
    }

    private RuntimeException rethrow() {
        return Erase.rethrow(this.exceptionSupplier.get());
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        throw rethrow();
    }
}
