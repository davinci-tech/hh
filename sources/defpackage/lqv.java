package defpackage;

import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

/* loaded from: classes5.dex */
class lqv extends ForwardingSink {
    private boolean e;

    protected void a(IOException iOException) {
    }

    lqv(Sink sink) {
        super(sink);
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (this.e) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e) {
            this.e = true;
            a(e);
        }
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (this.e) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.e = true;
            a(e);
        }
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.e) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.e = true;
            a(e);
        }
    }
}
