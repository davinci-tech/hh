package org.apache.commons.io.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.CharBuffer;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IORunnable;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.IOTriFunction;
import org.apache.commons.io.function.Uncheck;
import org.apache.commons.io.input.UncheckedBufferedReader;

/* loaded from: classes10.dex */
public final class UncheckedBufferedReader extends BufferedReader {

    public static class Builder extends AbstractStreamBuilder<UncheckedBufferedReader, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public UncheckedBufferedReader get() {
            return (UncheckedBufferedReader) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$Builder$$ExternalSyntheticLambda0
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    return UncheckedBufferedReader.Builder.this.m1194xc48f3479();
                }
            });
        }

        /* renamed from: lambda$get$0$org-apache-commons-io-input-UncheckedBufferedReader$Builder, reason: not valid java name */
        /* synthetic */ UncheckedBufferedReader m1194xc48f3479() throws IOException {
            return new UncheckedBufferedReader(getReader(), getBufferSize());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private UncheckedBufferedReader(Reader reader, int i) {
        super(reader, i);
    }

    @Override // java.io.BufferedReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedBufferedReader.this.m1184xabdb1404();
            }
        });
    }

    /* renamed from: lambda$close$0$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ void m1184xabdb1404() throws IOException {
        super.close();
    }

    /* renamed from: lambda$mark$1$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ void m1185x1d875af4(int i) throws IOException {
        super.mark(i);
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public void mark(int i) throws UncheckedIOException {
        Uncheck.accept(new IOConsumer() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                UncheckedBufferedReader.this.m1185x1d875af4(((Integer) obj).intValue());
            }
        }, Integer.valueOf(i));
    }

    /* renamed from: lambda$read$2$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ Integer m1186xcbc9ce7e() throws IOException {
        return Integer.valueOf(super.read());
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public int read() throws UncheckedIOException {
        return ((Integer) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda9
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return UncheckedBufferedReader.this.m1186xcbc9ce7e();
            }
        })).intValue();
    }

    /* renamed from: lambda$read$3$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ Integer m1187x59047fff(char[] cArr) throws IOException {
        return Integer.valueOf(super.read(cArr));
    }

    @Override // java.io.Reader
    public int read(char[] cArr) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedBufferedReader.this.m1187x59047fff((char[]) obj);
            }
        }, cArr)).intValue();
    }

    /* renamed from: lambda$read$4$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ Integer m1188xe63f3180(char[] cArr, int i, int i2) throws IOException {
        return Integer.valueOf(super.read(cArr, i, i2));
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return UncheckedBufferedReader.this.m1188xe63f3180((char[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, cArr, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
    }

    /* renamed from: lambda$read$5$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ Integer m1189x7379e301(CharBuffer charBuffer) throws IOException {
        return Integer.valueOf(super.read(charBuffer));
    }

    @Override // java.io.Reader, java.lang.Readable
    public int read(CharBuffer charBuffer) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedBufferedReader.this.m1189x7379e301((CharBuffer) obj);
            }
        }, charBuffer)).intValue();
    }

    /* renamed from: lambda$readLine$6$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ String m1190x7a7a2016() throws IOException {
        return super.readLine();
    }

    @Override // java.io.BufferedReader
    public String readLine() throws UncheckedIOException {
        return (String) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return UncheckedBufferedReader.this.m1190x7a7a2016();
            }
        });
    }

    /* renamed from: lambda$ready$7$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ Boolean m1191xc2f09bf6() throws IOException {
        return Boolean.valueOf(super.ready());
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public boolean ready() throws UncheckedIOException {
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return UncheckedBufferedReader.this.m1191xc2f09bf6();
            }
        })).booleanValue();
    }

    /* renamed from: lambda$reset$8$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ void m1192x27249823() throws IOException {
        super.reset();
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public void reset() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedBufferedReader.this.m1192x27249823();
            }
        });
    }

    /* renamed from: lambda$skip$9$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    /* synthetic */ Long m1193x10708c2e(long j) throws IOException {
        return Long.valueOf(super.skip(j));
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public long skip(long j) throws UncheckedIOException {
        return ((Long) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedBufferedReader.this.m1193x10708c2e(((Long) obj).longValue());
            }
        }, Long.valueOf(j))).longValue();
    }
}
