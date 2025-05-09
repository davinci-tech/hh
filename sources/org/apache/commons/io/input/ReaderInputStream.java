package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.charset.CharsetEncoders;
import org.apache.commons.io.input.ReaderInputStream;

/* loaded from: classes10.dex */
public class ReaderInputStream extends InputStream {
    private final CharsetEncoder charsetEncoder;
    private final CharBuffer encoderIn;
    private final ByteBuffer encoderOut;
    private boolean endOfInput;
    private CoderResult lastCoderResult;
    private final Reader reader;

    public static class Builder extends AbstractStreamBuilder<ReaderInputStream, Builder> {
        private CharsetEncoder charsetEncoder = ReaderInputStream.newEncoder(getCharset());

        @Override // org.apache.commons.io.function.IOSupplier
        public ReaderInputStream get() throws IOException {
            return new ReaderInputStream(getReader(), this.charsetEncoder, getBufferSize());
        }

        CharsetEncoder getCharsetEncoder() {
            return this.charsetEncoder;
        }

        @Override // org.apache.commons.io.build.AbstractStreamBuilder
        public Builder setCharset(Charset charset) {
            super.setCharset(charset);
            this.charsetEncoder = ReaderInputStream.newEncoder(getCharset());
            return this;
        }

        /* renamed from: lambda$setCharsetEncoder$0$org-apache-commons-io-input-ReaderInputStream$Builder, reason: not valid java name */
        /* synthetic */ CharsetEncoder m1183x5a2c7bac() {
            return ReaderInputStream.newEncoder(getCharsetDefault());
        }

        public Builder setCharsetEncoder(CharsetEncoder charsetEncoder) {
            CharsetEncoder charsetEncoder2 = CharsetEncoders.toCharsetEncoder(charsetEncoder, new Supplier() { // from class: org.apache.commons.io.input.ReaderInputStream$Builder$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ReaderInputStream.Builder.this.m1183x5a2c7bac();
                }
            });
            this.charsetEncoder = charsetEncoder2;
            super.setCharset(charsetEncoder2.charset());
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    static int checkMinBufferSize(CharsetEncoder charsetEncoder, int i) {
        float minBufferSize = minBufferSize(charsetEncoder);
        if (i >= minBufferSize) {
            return i;
        }
        throw new IllegalArgumentException(String.format("Buffer size %,d must be at least %s for a CharsetEncoder %s.", Integer.valueOf(i), Float.valueOf(minBufferSize), charsetEncoder.charset().displayName()));
    }

    static float minBufferSize(CharsetEncoder charsetEncoder) {
        return charsetEncoder.maxBytesPerChar() * 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CharsetEncoder newEncoder(Charset charset) {
        return Charsets.toCharset(charset).newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    }

    @Deprecated
    public ReaderInputStream(Reader reader) {
        this(reader, Charset.defaultCharset());
    }

    @Deprecated
    public ReaderInputStream(Reader reader, Charset charset) {
        this(reader, charset, 8192);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, Charset charset, int i) {
        this(reader, Charsets.toCharset(charset).newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), i);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder) {
        this(reader, charsetEncoder, 8192);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder, int i) {
        this.reader = reader;
        CharsetEncoder charsetEncoder2 = CharsetEncoders.toCharsetEncoder(charsetEncoder);
        this.charsetEncoder = charsetEncoder2;
        CharBuffer allocate = CharBuffer.allocate(checkMinBufferSize(charsetEncoder2, i));
        this.encoderIn = allocate;
        allocate.flip();
        ByteBuffer allocate2 = ByteBuffer.allocate(128);
        this.encoderOut = allocate2;
        allocate2.flip();
    }

    @Deprecated
    public ReaderInputStream(Reader reader, String str) {
        this(reader, str, 8192);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, String str, int i) {
        this(reader, Charsets.toCharset(str), i);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    private void fillBuffer() throws IOException {
        CoderResult coderResult;
        boolean z = this.endOfInput;
        if (z) {
            return;
        }
        if (!z && ((coderResult = this.lastCoderResult) == null || coderResult.isUnderflow())) {
            this.encoderIn.compact();
            int position = this.encoderIn.position();
            int read = this.reader.read(this.encoderIn.array(), position, this.encoderIn.remaining());
            if (read == -1) {
                this.endOfInput = true;
            } else {
                this.encoderIn.position(position + read);
            }
            this.encoderIn.flip();
        }
        this.encoderOut.compact();
        this.lastCoderResult = this.charsetEncoder.encode(this.encoderIn, this.encoderOut, this.endOfInput);
        if (this.endOfInput) {
            this.lastCoderResult = this.charsetEncoder.flush(this.encoderOut);
        }
        if (this.lastCoderResult.isError()) {
            this.lastCoderResult.throwException();
        }
        this.encoderOut.flip();
    }

    CharsetEncoder getCharsetEncoder() {
        return this.charsetEncoder;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (!this.encoderOut.hasRemaining()) {
            fillBuffer();
            if (this.endOfInput && !this.encoderOut.hasRemaining()) {
                return -1;
            }
        }
        return this.encoderOut.get() & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Objects.requireNonNull(bArr, "array");
        if (i2 < 0 || i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException("Array size=" + bArr.length + ", offset=" + i + ", length=" + i2);
        }
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        while (i2 > 0) {
            if (this.encoderOut.hasRemaining()) {
                int min = Math.min(this.encoderOut.remaining(), i2);
                this.encoderOut.get(bArr, i, min);
                i += min;
                i2 -= min;
                i3 += min;
            } else {
                if (this.endOfInput) {
                    break;
                }
                fillBuffer();
            }
        }
        if (i3 == 0 && this.endOfInput) {
            return -1;
        }
        return i3;
    }
}
