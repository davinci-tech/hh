package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes10.dex */
public class AutoCloseInputStream extends ProxyInputStream {

    public static class Builder extends AbstractStreamBuilder<AutoCloseInputStream, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public AutoCloseInputStream get() throws IOException {
            return new AutoCloseInputStream(getInputStream());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Deprecated
    public AutoCloseInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream
    protected void afterRead(int i) throws IOException {
        if (i == -1) {
            close();
        }
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
        this.in = ClosedInputStream.INSTANCE;
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
