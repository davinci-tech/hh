package com.huawei.hms.network.embedded;

import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class ob {

    /* renamed from: a, reason: collision with root package name */
    public static final Logger f5403a = Logger.getLogger(ob.class.getName());

    public static zb c(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static za c(Socket socket) {
        return new d(socket);
    }

    public static zb b(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return a(Files.newInputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }

    public static zb b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        }
        za c2 = c(socket);
        return c2.a(a(socket.getInputStream(), c2));
    }

    public static yb b(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static zb a(InputStream inputStream, ac acVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (acVar != null) {
            return new b(acVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static zb a(InputStream inputStream) {
        return a(inputStream, new ac());
    }

    public static yb a(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return a(Files.newOutputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }

    public static yb a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        }
        za c2 = c(socket);
        return c2.a(a(socket.getOutputStream(), c2));
    }

    public static yb a(OutputStream outputStream, ac acVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (acVar != null) {
            return new a(acVar, outputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public final class a implements yb {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ac f5404a;
        public final /* synthetic */ OutputStream b;

        @Override // com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            cc.a(bbVar.b, 0L, j);
            while (j > 0) {
                this.f5404a.throwIfReached();
                vb vbVar = bbVar.f5191a;
                int min = (int) Math.min(j, vbVar.c - vbVar.b);
                this.b.write(vbVar.f5550a, vbVar.b, min);
                vbVar.b += min;
                long j2 = min;
                j -= j2;
                bbVar.b -= j2;
                if (vbVar.b == vbVar.c) {
                    bbVar.f5191a = vbVar.b();
                    wb.a(vbVar);
                }
            }
        }

        public String toString() {
            return "sink(" + this.b + Constants.RIGHT_BRACKET_ONLY;
        }

        @Override // com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5404a;
        }

        @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
            this.b.flush();
        }

        @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
            this.b.close();
        }

        public a(ac acVar, OutputStream outputStream) {
            this.f5404a = acVar;
            this.b = outputStream;
        }
    }

    public static yb a(OutputStream outputStream) {
        return a(outputStream, new ac());
    }

    public final class b implements zb {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ac f5405a;
        public final /* synthetic */ InputStream b;

        public String toString() {
            return "source(" + this.b + Constants.RIGHT_BRACKET_ONLY;
        }

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5405a;
        }

        @Override // com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (j == 0) {
                return 0L;
            }
            try {
                this.f5405a.throwIfReached();
                vb e = bbVar.e(1);
                int read = this.b.read(e.f5550a, e.c, (int) Math.min(j, 8192 - e.c));
                if (read != -1) {
                    e.c += read;
                    long j2 = read;
                    bbVar.b += j2;
                    return j2;
                }
                if (e.b != e.c) {
                    return -1L;
                }
                bbVar.f5191a = e.b();
                wb.a(e);
                return -1L;
            } catch (AssertionError e2) {
                if (ob.a(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            this.b.close();
        }

        public b(ac acVar, InputStream inputStream) {
            this.f5405a = acVar;
            this.b = inputStream;
        }
    }

    public final class c implements yb {
        @Override // com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
        public void close() throws IOException {
        }

        @Override // com.huawei.hms.network.embedded.yb, java.io.Flushable
        public void flush() throws IOException {
        }

        @Override // com.huawei.hms.network.embedded.yb
        public void write(bb bbVar, long j) throws IOException {
            bbVar.skip(j);
        }

        @Override // com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return ac.d;
        }
    }

    public static yb a(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static yb a() {
        return new c();
    }

    public final class d extends za {
        public final /* synthetic */ Socket l;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.network.embedded.za
        public void timedOut() {
            Level level;
            StringBuilder sb;
            Logger logger;
            Exception exc;
            try {
                this.l.close();
            } catch (AssertionError e) {
                if (!ob.a(e)) {
                    throw e;
                }
                Logger logger2 = ob.f5403a;
                level = Level.WARNING;
                sb = new StringBuilder("Failed to close timed out socket ");
                exc = e;
                logger = logger2;
                sb.append(this.l);
                logger.log(level, sb.toString(), (Throwable) exc);
            } catch (Exception e2) {
                Logger logger3 = ob.f5403a;
                level = Level.WARNING;
                sb = new StringBuilder("Failed to close timed out socket ");
                exc = e2;
                logger = logger3;
                sb.append(this.l);
                logger.log(level, sb.toString(), (Throwable) exc);
            }
        }

        @Override // com.huawei.hms.network.embedded.za
        public IOException newTimeoutException(@Nullable IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public d(Socket socket) {
            this.l = socket;
        }
    }

    public static db a(zb zbVar) {
        return new ub(zbVar);
    }

    public static cb a(yb ybVar) {
        return new tb(ybVar);
    }
}
