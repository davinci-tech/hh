package okhttp3;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.hms.network.embedded.y;
import defpackage.closeFinally;
import defpackage.ueu;
import defpackage.uhx;
import defpackage.uhy;
import defpackage.uib;
import defpackage.uju;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J@\u0010\u0010\u001a\u0002H\u0011\"\b\b\u0000\u0010\u0011*\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002H\u00110\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u00020\u00170\u0014H\u0082\b¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH&J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH&J\b\u0010\u001d\u001a\u00020\u0015H&J\u0006\u0010\u001e\u001a\u00020\u001fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lokhttp3/ResponseBody;", "Ljava/io/Closeable;", "()V", "reader", "Ljava/io/Reader;", "byteStream", "Ljava/io/InputStream;", "byteString", "Lokio/ByteString;", "bytes", "", "charStream", "charset", "Ljava/nio/charset/Charset;", "close", "", "consumeSource", ExifInterface.GPS_DIRECTION_TRUE, "", "consumer", "Lkotlin/Function1;", "Lokio/BufferedSource;", "sizeMapper", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "string", "", "BomAwareReader", "Companion", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ResponseBody implements Closeable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Reader reader;

    /* renamed from: contentLength */
    public abstract long get$contentLength();

    /* renamed from: contentType */
    public abstract MediaType get$contentType();

    /* renamed from: source */
    public abstract BufferedSource get$this_asResponseBody();

    public final InputStream byteStream() {
        return get$this_asResponseBody().inputStream();
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [T, java.lang.Object] */
    private final <T> T consumeSource(Function1<? super BufferedSource, ? extends T> consumer, Function1<? super T, Integer> sizeMapper) {
        long j = get$contentLength();
        if (j > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + j);
        }
        BufferedSource bufferedSource = get$this_asResponseBody();
        try {
            T invoke = consumer.invoke(bufferedSource);
            uhx.a(1);
            closeFinally.d(bufferedSource, null);
            uhx.b(1);
            int intValue = sizeMapper.invoke(invoke).intValue();
            if (j == -1 || j == intValue) {
                return invoke;
            }
            throw new IOException("Content-Length (" + j + ") and stream length (" + intValue + ") disagree");
        } finally {
        }
    }

    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        BomAwareReader bomAwareReader = new BomAwareReader(get$this_asResponseBody(), charset());
        this.reader = bomAwareReader;
        return bomAwareReader;
    }

    public final String string() throws IOException {
        BufferedSource bufferedSource = get$this_asResponseBody();
        try {
            BufferedSource bufferedSource2 = bufferedSource;
            String readString = bufferedSource2.readString(Util.readBomAsCharset(bufferedSource2, charset()));
            closeFinally.d(bufferedSource, null);
            return readString;
        } finally {
        }
    }

    private final Charset charset() {
        Charset charset;
        MediaType mediaType = get$contentType();
        return (mediaType == null || (charset = mediaType.charset(uju.h)) == null) ? uju.h : charset;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.closeQuietly(get$this_asResponseBody());
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/ResponseBody$BomAwareReader;", "Ljava/io/Reader;", "source", "Lokio/BufferedSource;", "charset", "Ljava/nio/charset/Charset;", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)V", "closed", "", "delegate", "close", "", "read", "", "cbuf", "", "off", "len", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class BomAwareReader extends Reader {
        private final Charset charset;
        private boolean closed;
        private Reader delegate;
        private final BufferedSource source;

        public BomAwareReader(BufferedSource bufferedSource, Charset charset) {
            uhy.e((Object) bufferedSource, "");
            uhy.e((Object) charset, "");
            this.source = bufferedSource;
            this.charset = charset;
        }

        @Override // java.io.Reader
        public int read(char[] cbuf, int off, int len) throws IOException {
            uhy.e((Object) cbuf, "");
            if (this.closed) {
                throw new IOException("Stream closed");
            }
            InputStreamReader inputStreamReader = this.delegate;
            if (inputStreamReader == null) {
                inputStreamReader = new InputStreamReader(this.source.inputStream(), Util.readBomAsCharset(this.source, this.charset));
                this.delegate = inputStreamReader;
            }
            return inputStreamReader.read(cbuf, off, len);
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            ueu ueuVar;
            this.closed = true;
            Reader reader = this.delegate;
            if (reader != null) {
                reader.close();
                ueuVar = ueu.d;
            } else {
                ueuVar = null;
            }
            if (ueuVar == null) {
                this.source.close();
            }
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u000bH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\fH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\rH\u0007J'\u0010\u000e\u001a\u00020\u0004*\u00020\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u000f\u001a\u00020\u0004*\u00020\r2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0010"}, d2 = {"Lokhttp3/ResponseBody$Companion;", "", "()V", "create", "Lokhttp3/ResponseBody;", "contentType", "Lokhttp3/MediaType;", "content", "", "contentLength", "", "Lokio/BufferedSource;", "", "Lokio/ByteString;", "asResponseBody", "toResponseBody", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, String str, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(str, mediaType);
        }

        @JvmStatic
        public final ResponseBody create(String str, MediaType mediaType) {
            uhy.e((Object) str, "");
            Charset charset = uju.h;
            if (mediaType != null && (charset = MediaType.charset$default(mediaType, null, 1, null)) == null) {
                charset = uju.h;
                mediaType = MediaType.INSTANCE.parse(mediaType + "; charset=utf-8");
            }
            Buffer writeString = new Buffer().writeString(str, charset);
            return create(writeString, mediaType, writeString.size());
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, byte[] bArr, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(bArr, mediaType);
        }

        @JvmStatic
        public final ResponseBody create(byte[] bArr, MediaType mediaType) {
            uhy.e((Object) bArr, "");
            return create(new Buffer().write(bArr), mediaType, bArr.length);
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, ByteString byteString, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(byteString, mediaType);
        }

        @JvmStatic
        public final ResponseBody create(ByteString byteString, MediaType mediaType) {
            uhy.e((Object) byteString, "");
            return create(new Buffer().write(byteString), mediaType, byteString.size());
        }

        public static /* synthetic */ ResponseBody create$default(Companion companion, BufferedSource bufferedSource, MediaType mediaType, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            if ((i & 2) != 0) {
                j = -1;
            }
            return companion.create(bufferedSource, mediaType, j);
        }

        @JvmStatic
        public final ResponseBody create(final BufferedSource bufferedSource, final MediaType mediaType, final long j) {
            uhy.e((Object) bufferedSource, "");
            return new ResponseBody() { // from class: okhttp3.ResponseBody$Companion$asResponseBody$1
                @Override // okhttp3.ResponseBody
                /* renamed from: contentType, reason: from getter */
                public MediaType get$contentType() {
                    return MediaType.this;
                }

                @Override // okhttp3.ResponseBody
                /* renamed from: contentLength, reason: from getter */
                public long get$contentLength() {
                    return j;
                }

                @Override // okhttp3.ResponseBody
                /* renamed from: source, reason: from getter */
                public BufferedSource get$this_asResponseBody() {
                    return bufferedSource;
                }
            };
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType contentType, String content) {
            uhy.e((Object) content, "");
            return create(content, contentType);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType contentType, byte[] content) {
            uhy.e((Object) content, "");
            return create(content, contentType);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType contentType, ByteString content) {
            uhy.e((Object) content, "");
            return create(content, contentType);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
        @JvmStatic
        public final ResponseBody create(MediaType contentType, long contentLength, BufferedSource content) {
            uhy.e((Object) content, "");
            return create(content, contentType, contentLength);
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }

    public final byte[] bytes() throws IOException {
        long j = get$contentLength();
        if (j > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + j);
        }
        BufferedSource bufferedSource = get$this_asResponseBody();
        try {
            byte[] readByteArray = bufferedSource.readByteArray();
            closeFinally.d(bufferedSource, null);
            int length = readByteArray.length;
            if (j == -1 || j == length) {
                return readByteArray;
            }
            throw new IOException("Content-Length (" + j + ") and stream length (" + length + ") disagree");
        } finally {
        }
    }

    public final ByteString byteString() throws IOException {
        long j = get$contentLength();
        if (j > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + j);
        }
        BufferedSource bufferedSource = get$this_asResponseBody();
        try {
            ByteString readByteString = bufferedSource.readByteString();
            closeFinally.d(bufferedSource, null);
            int size = readByteString.size();
            if (j == -1 || j == size) {
                return readByteString;
            }
            throw new IOException("Content-Length (" + j + ") and stream length (" + size + ") disagree");
        } finally {
        }
    }

    @JvmStatic
    public static final ResponseBody create(byte[] bArr, MediaType mediaType) {
        return INSTANCE.create(bArr, mediaType);
    }

    @JvmStatic
    public static final ResponseBody create(ByteString byteString, MediaType mediaType) {
        return INSTANCE.create(byteString, mediaType);
    }

    @JvmStatic
    public static final ResponseBody create(BufferedSource bufferedSource, MediaType mediaType, long j) {
        return INSTANCE.create(bufferedSource, mediaType, j);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType, byte[] bArr) {
        return INSTANCE.create(mediaType, bArr);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType, ByteString byteString) {
        return INSTANCE.create(mediaType, byteString);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType, String str) {
        return INSTANCE.create(mediaType, str);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
    @JvmStatic
    public static final ResponseBody create(MediaType mediaType, long j, BufferedSource bufferedSource) {
        return INSTANCE.create(mediaType, j, bufferedSource);
    }

    @JvmStatic
    public static final ResponseBody create(String str, MediaType mediaType) {
        return INSTANCE.create(str, mediaType);
    }
}
