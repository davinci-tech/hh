package org.apache.commons.io.build;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.function.IntUnaryOperator;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.file.PathUtils;

/* loaded from: classes10.dex */
public abstract class AbstractStreamBuilder<T, B extends AbstractStreamBuilder<T, B>> extends AbstractOriginSupplier<T, B> {
    private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;
    private static final OpenOption[] DEFAULT_OPEN_OPTIONS = PathUtils.EMPTY_OPEN_OPTION_ARRAY;
    private IntUnaryOperator bufferSizeChecker;
    private final IntUnaryOperator defaultSizeChecker;
    private int bufferSize = 8192;
    private int bufferSizeDefault = 8192;
    private int bufferSizeMax = Integer.MAX_VALUE;
    private Charset charset = Charset.defaultCharset();
    private Charset charsetDefault = Charset.defaultCharset();
    private OpenOption[] openOptions = DEFAULT_OPEN_OPTIONS;

    public AbstractStreamBuilder() {
        IntUnaryOperator intUnaryOperator = new IntUnaryOperator() { // from class: org.apache.commons.io.build.AbstractStreamBuilder$$ExternalSyntheticLambda0
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return AbstractStreamBuilder.this.m1144lambda$new$0$orgapachecommonsiobuildAbstractStreamBuilder(i);
            }
        };
        this.defaultSizeChecker = intUnaryOperator;
        this.bufferSizeChecker = intUnaryOperator;
    }

    /* renamed from: lambda$new$0$org-apache-commons-io-build-AbstractStreamBuilder, reason: not valid java name */
    /* synthetic */ int m1144lambda$new$0$orgapachecommonsiobuildAbstractStreamBuilder(int i) {
        int i2 = this.bufferSizeMax;
        return i > i2 ? throwIae(i, i2) : i;
    }

    private int checkBufferSize(int i) {
        return this.bufferSizeChecker.applyAsInt(i);
    }

    protected int getBufferSize() {
        return this.bufferSize;
    }

    protected int getBufferSizeDefault() {
        return this.bufferSizeDefault;
    }

    protected CharSequence getCharSequence() throws IOException {
        return checkOrigin().getCharSequence(getCharset());
    }

    public Charset getCharset() {
        return this.charset;
    }

    protected Charset getCharsetDefault() {
        return this.charsetDefault;
    }

    protected InputStream getInputStream() throws IOException {
        return checkOrigin().getInputStream(getOpenOptions());
    }

    protected OpenOption[] getOpenOptions() {
        return this.openOptions;
    }

    protected OutputStream getOutputStream() throws IOException {
        return checkOrigin().getOutputStream(getOpenOptions());
    }

    protected Path getPath() {
        return checkOrigin().getPath();
    }

    protected Reader getReader() throws IOException {
        return checkOrigin().getReader(getCharset());
    }

    protected Writer getWriter() throws IOException {
        return checkOrigin().getWriter(getCharset(), getOpenOptions());
    }

    public B setBufferSize(int i) {
        if (i <= 0) {
            i = this.bufferSizeDefault;
        }
        this.bufferSize = checkBufferSize(i);
        return (B) asThis();
    }

    public B setBufferSize(Integer num) {
        setBufferSize(num != null ? num.intValue() : this.bufferSizeDefault);
        return (B) asThis();
    }

    public B setBufferSizeChecker(IntUnaryOperator intUnaryOperator) {
        if (intUnaryOperator == null) {
            intUnaryOperator = this.defaultSizeChecker;
        }
        this.bufferSizeChecker = intUnaryOperator;
        return (B) asThis();
    }

    protected B setBufferSizeDefault(int i) {
        this.bufferSizeDefault = i;
        return (B) asThis();
    }

    public B setBufferSizeMax(int i) {
        if (i <= 0) {
            i = Integer.MAX_VALUE;
        }
        this.bufferSizeMax = i;
        return (B) asThis();
    }

    public B setCharset(Charset charset) {
        this.charset = Charsets.toCharset(charset, this.charsetDefault);
        return (B) asThis();
    }

    public B setCharset(String str) {
        return setCharset(Charsets.toCharset(str, this.charsetDefault));
    }

    protected B setCharsetDefault(Charset charset) {
        this.charsetDefault = charset;
        return (B) asThis();
    }

    public B setOpenOptions(OpenOption... openOptionArr) {
        if (openOptionArr == null) {
            openOptionArr = DEFAULT_OPEN_OPTIONS;
        }
        this.openOptions = openOptionArr;
        return (B) asThis();
    }

    private int throwIae(int i, int i2) {
        throw new IllegalArgumentException(String.format("Request %,d exceeds maximum %,d", Integer.valueOf(i), Integer.valueOf(i2)));
    }
}
