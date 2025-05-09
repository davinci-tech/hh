package org.apache.commons.io.output;

import java.io.Writer;

/* loaded from: classes10.dex */
public class CloseShieldWriter extends ProxyWriter {
    public static CloseShieldWriter wrap(Writer writer) {
        return new CloseShieldWriter(writer);
    }

    @Deprecated
    public CloseShieldWriter(Writer writer) {
        super(writer);
    }

    @Override // org.apache.commons.io.output.ProxyWriter, java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out = ClosedWriter.INSTANCE;
    }
}
